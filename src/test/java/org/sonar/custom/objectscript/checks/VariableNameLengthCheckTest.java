package org.sonar.custom.objectscript.checks;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.sonar.custom.objectscript.checks.VariableNameLengthCheck;
import org.sonar.custom.objectscript.checks.base.OsqBaseCheckTest;
import org.sonar.objectscript.api.helpers.ViolationList;
import org.sonar.plugins.objectscript.api.check.ObjectScriptCheck;

public final class VariableNameLengthCheckTest
    extends OsqBaseCheckTest
{

    protected VariableNameLengthCheckTest()
        throws IOException, URISyntaxException
    {
        super(VariableNameLengthCheck.KEY);
    }

    @Override
    protected ObjectScriptCheck getCheck()
    {
        return new VariableNameLengthCheck();
    }

    @Override
    protected Iterator<Object[]> checkData()
    {
        final List<Object[]> list = new ArrayList<>();

        String resourceName;
        ViolationList violationList;

        resourceName = "C1.cls";
        violationList = ViolationList.create()
            .add(5, String.format(VariableNameLengthCheck.MESSAGE,	"statusverylongexample"))
            .add(7, String.format(VariableNameLengthCheck.MESSAGE,	"statusverylongexamplemore"))
            .build();
        list.add(new Object[] { resourceName, violationList });

      
        return list.iterator();
    }
}
