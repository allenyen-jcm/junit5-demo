package com.rga.core.extension;

import java.io.IOException;

import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestExtensionContext;

public class IgnoreIOExceptionExtension implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(TestExtensionContext context, Throwable throwable) throws Throwable {

        if (throwable instanceof IOException) {
            System.out.println(context.getTestMethod().get());;
            return;
        }
        throw throwable;
    }
}
