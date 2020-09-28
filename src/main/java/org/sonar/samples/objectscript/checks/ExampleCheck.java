package org.sonar.samples.objectscript.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.objectscript.api.IssueTags;
import org.sonar.plugins.objectscript.api.check.ObjectScriptClassCheck;
import org.sonar.squidbridge.api.CheckMessage;

import com.google.common.annotations.VisibleForTesting;
import com.sonar.sslr.api.AstNode;

@Rule(key = ExampleCheck.KEY, priority = Priority.CRITICAL,
        name = ExampleCheck.NAME,
        tags = { IssueTags.BUG, IssueTags.RUNTIME_FAILURE })

public final class ExampleCheck extends ObjectScriptClassCheck {
    static final String NAME = "Test title";

    @VisibleForTesting
    public static final String KEY = "VNL0001";

    @VisibleForTesting
    static final String MESSAGE = "Test message ";


    @Override
    public void leaveNode(final AstNode astNode) {
        
        final CheckMessage message = new CheckMessage((Object) this, MESSAGE);
        message.setLine(astNode.getTokenLine());
        getEnclosingFile().log(message);
    }

}
