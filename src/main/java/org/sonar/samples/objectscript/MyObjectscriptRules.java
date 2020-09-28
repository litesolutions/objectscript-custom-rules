package org.sonar.samples.objectscript;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionAnnotationLoader;
import org.sonar.plugins.objectscript.api.ObjectscriptCustomRuleRepository;
import org.sonar.samples.objectscript.checks.ExampleCheck;
import org.sonar.samples.objectscript.checks.VariableNameLengthCheck;

import com.google.common.collect.ImmutableList;

public class MyObjectscriptRules implements RulesDefinition, ObjectscriptCustomRuleRepository{

	  /**
	   * Provide the repository key
	   */
	  @Override
	  public String repositoryKey() {
	    return "custom";
	  }

	  /**
	   * Provide the list of checks class that implements rules
	   * to be part of the rule repository
	   */
	  @Override
	  public ImmutableList<Class> checkClasses() {
	    return ImmutableList.of(ExampleCheck.class, VariableNameLengthCheck.class);
	  }

	  @Override
	  public void define(Context context) {
	    NewRepository repository = context.createRepository(repositoryKey(), "objectscript").setName("MyCompany Custom Repository");

	    // Load rule meta data from annotations
	    RulesDefinitionAnnotationLoader annotationLoader = new RulesDefinitionAnnotationLoader();
	    checkClasses().forEach(ruleClass -> annotationLoader.load(repository, ruleClass));

	    // Optionally override html description from annotation with content from html files
	    repository.rules().forEach(rule -> rule.setHtmlDescription(loadResource("/org/sonar/l10n/objectscript/rules/" + rule.key() + ".html")));

	    // Optionally define remediation costs
	    Map<String, String> remediationCosts = new HashMap<>();
	    remediationCosts.put(ExampleCheck.KEY, "5min");
	    remediationCosts.put(VariableNameLengthCheck.KEY, "5min");
	    repository.rules().forEach(rule -> rule.setDebtRemediationFunction(
	      rule.debtRemediationFunctions().constantPerIssue(remediationCosts.get(rule.key()))));

	    repository.done();
	  }

	  private String loadResource(String path) {
	    URL resource = getClass().getResource(path);
	    if (resource == null) {
	      throw new IllegalStateException("Resource not found: " + path);
	    }
	    ByteArrayOutputStream result = new ByteArrayOutputStream();
	    try (InputStream in = resource.openStream()) {
	      byte[] buffer = new byte[1024];
	      for (int len = in.read(buffer); len != -1; len = in.read(buffer)) {
	        result.write(buffer, 0, len);
	      }
	      return new String(result.toByteArray(), StandardCharsets.UTF_8);
	    } catch (IOException e) {
	      throw new IllegalStateException("Failed to read resource: " + path, e);
	    }
	  }
	}