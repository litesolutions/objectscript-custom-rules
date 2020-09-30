package org.sonar.custom.objectscript.checks.base;

import java.io.IOException;
import java.net.URISyntaxException;

import org.sonar.custom.objectscript.MyObjectscriptRules;
import org.sonar.objectscript.api.CheckTest;
import org.sonar.plugins.objectscript.api.ObjectscriptCustomRuleRepository;

@org.testng.annotations.Test
public abstract class OsqBaseCheckTest
    extends CheckTest
{
    private static final ObjectscriptCustomRuleRepository DEF
        = new MyObjectscriptRules();

    protected OsqBaseCheckTest(final String key)
        throws IOException, URISyntaxException
    {
        super(DEF.repositoryKey(), DEF.checkClasses(), key);
    }
}
