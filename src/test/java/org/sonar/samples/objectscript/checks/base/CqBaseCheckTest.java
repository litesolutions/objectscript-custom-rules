package org.sonar.samples.objectscript.checks.base;

import java.io.IOException;
import java.net.URISyntaxException;

import org.sonar.objectscript.api.CheckTest;
import org.sonar.plugins.objectscript.api.ObjectscriptCustomRuleRepository;
import org.sonar.samples.objectscript.MyObjectscriptRules;

@org.testng.annotations.Test
public abstract class CqBaseCheckTest
    extends CheckTest
{
    private static final ObjectscriptCustomRuleRepository DEF
        = new MyObjectscriptRules();

    protected CqBaseCheckTest(final String key)
        throws IOException, URISyntaxException
    {
        super(DEF.repositoryKey(), DEF.checkClasses(), key);
    }
}
