package org.sonar.samples.objectscript.checks;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.sonar.objectscript.api.helpers.ViolationList;
import org.sonar.plugins.objectscript.api.check.ObjectScriptCheck;
import org.sonar.samples.objectscript.checks.base.CqBaseCheckTest;

public final class ExampleCheckTest
    extends CqBaseCheckTest
{

    protected ExampleCheckTest()
        throws IOException, URISyntaxException
    {
        super(ExampleCheck.KEY);
    }

    @Override
    protected ObjectScriptCheck getCheck()
    {
        return new ExampleCheck();
    }

    @Override
    protected Iterator<Object[]> checkData()
    {
        final List<Object[]> list = new ArrayList<>();

        String resourceName;
        ViolationList violationList;

        resourceName = "C1.cls";
        violationList = ViolationList.create()
            .add(1, ExampleCheck.MESSAGE)
            .build();
        list.add(new Object[] { resourceName, violationList });

      
        return list.iterator();
    }
}
