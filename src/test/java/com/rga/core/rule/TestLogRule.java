package com.rga.core.rule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import com.rga.core.annotation.TestLogger;

public class TestLogRule implements TestRule {

    private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss_SSS");

    public Statement apply(Statement base, Description description) {
        TestLogger testLogger = description.getAnnotation(TestLogger.class);
        if (testLogger != null) {
            StringBuilder log = new StringBuilder(format.format(new Date()));
            log.append(" ").append(description.getClassName()).append("#").append(description.getMethodName()).append(": ")
                    .append(testLogger.log());
            System.out.println(log.toString());
        }

        return base;
    }

}
