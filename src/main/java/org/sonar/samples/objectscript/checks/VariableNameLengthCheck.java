package org.sonar.samples.objectscript.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.objectscript.api.IssueTags;
import org.sonar.plugins.objectscript.api.ast.tokens.references.Variables;
import org.sonar.plugins.objectscript.api.check.ObjectScriptMethodCheck;


import com.google.common.annotations.VisibleForTesting;
import com.sonar.sslr.api.AstNode;

/**
 * Example of a "raw" rule
 *
 * <p>A rule will always extend {@link ObjectScriptCheck}.</p>
 *
 * <p>With this kind of rule, you will be able to specify which nodes in the AST
 * you want to {@link #subscribeTo(AstNodeType...) subscribe to} (this is done
 * in the {@link #init()} method).</p>
 *
 * <p>A visitor pattern is used; you will be able to perform the check either
 * when the node is visited (using {@link #visitNode(AstNode)}) or after the
 * node has been visited (using {@link #leaveNode(AstNode)}).</p>
 *
 * <p><em>Important:</em> do not forget the HTML description. It is in the
 * resources directory ({@code src/main/resources}) and must be named {@code
 * org/sonar/l10n/objectscript/rules/rulekey.html}, where {@code
 * repokey} is the repository name (defined by {@link
 * PluginConstants#RULEREPO_KEY} in this project) and {@code rulekey} is the key
 * of this rule (see the {@link #KEY} constant defined below).</p>
 */


@Rule(key = VariableNameLengthCheck.KEY, priority = Priority.CRITICAL,
        name = VariableNameLengthCheck.NAME,
        tags = { IssueTags.BUG, IssueTags.RUNTIME_FAILURE })

public final class VariableNameLengthCheck extends ObjectScriptMethodCheck {
        /*
     * The key of the check. It must be unique across all checks for a given
     * language; it is therefore recommended that you use, for instance, the
     * plugin key as a base for generating keys.
     *
     * Here, CQEX is an abbreviation for "cqexample".
     */
	public static final String KEY = "VNL0002";

    /*
     * The name of the check. This will appear in the check list in SonarQube,
     * and is searchable.
     */
    static final String NAME = "Detect whether a _local_ variable name has a length >= 20 characters";

    /*
     * The message associated with the check.
     *
     * If the message is parameterized, use format specifiers as below, and use
     * String.format() to generate the message.
     */
    @VisibleForTesting
    static final String MESSAGE = "Detect  %s whether a _local_ variable name has a length >= 20 characters ";

   
    @Override
    public void visitNode(final AstNode astNode)
    {
        AstNode nodes = getMethod().getBody();
        for (final AstNode node: nodes.getDescendants(Variables.LOCAL)) {
         if(node.getTokenValue().length()> 20)
            getContext().createLineViolation(this, String.format(MESSAGE,
            		node.getTokenValue()), node);
        }
    }


}
