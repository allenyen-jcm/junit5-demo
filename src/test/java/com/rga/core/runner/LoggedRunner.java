package com.rga.core.runner;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import com.rga.core.annotation.TestLogger;

public class LoggedRunner extends BlockJUnit4ClassRunner {

    private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss_SSS");

    public LoggedRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected Statement methodBlock(FrameworkMethod method) {
        Method classMethod = method.getMethod();
        TestLogger loggerAnnotation = classMethod.getAnnotation(TestLogger.class);
        if (loggerAnnotation != null) {
            StringBuilder log = new StringBuilder(format.format(new Date()));
            log.append(" ").append(classMethod.getDeclaringClass().getName()).append("#").append(classMethod.getName())
                    .append(": ").append(loggerAnnotation.log());
            System.out.println(log.toString());
        }

        return super.methodBlock(method);
    }

    @Override
    protected void runChild(FrameworkMethod method, RunNotifier notifier) {
        System.out.println(method.getName());
        super.runChild(method, notifier);
    }
}
