package com.rga.core.extension;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ContainerExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.TestExtensionContext;

import com.rga.core.annotation.Benchmark;

public class BenchmarkExtension
        implements BeforeAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterAllCallback {

    private static final Namespace NAMESPACE = Namespace.DEFAULT;

    @Override
    public void beforeAll(ContainerExtensionContext context) {
        if (!shouldBeBenchmarked(context))
            return;

        writeCurrentTime(context, LaunchTimeKey.CLASS);
    }

    @Override
    public void beforeTestExecution(TestExtensionContext context) {
        if (!shouldBeBenchmarked(context))
            return;

        writeCurrentTime(context, LaunchTimeKey.TEST);
    }

    @Override
    public void afterTestExecution(TestExtensionContext context) {
        if (!shouldBeBenchmarked(context))
            return;

        long launchTime = loadLaunchTime(context, LaunchTimeKey.TEST);
        long runtime = System.currentTimeMillis() - launchTime;
        print("Test", context.getDisplayName(), runtime);
    }

    @Override
    public void afterAll(ContainerExtensionContext context) {
        if (!shouldBeBenchmarked(context))
            return;

        long launchTime = loadLaunchTime(context, LaunchTimeKey.CLASS);
        long runtime = System.currentTimeMillis() - launchTime;
        print("Test container", context.getDisplayName(), runtime);
    }

    private static boolean shouldBeBenchmarked(ExtensionContext context) {
        return context.getElement().map(el -> el.isAnnotationPresent(Benchmark.class)).orElse(false);
    }

    private static void writeCurrentTime(ExtensionContext context, LaunchTimeKey key) {
        context.getStore(NAMESPACE).put(key, System.currentTimeMillis());
    }

    private static long loadLaunchTime(ExtensionContext context, LaunchTimeKey key) {
        return (Long) context.getStore(NAMESPACE).remove(key);
    }

    private static void print(String unit, String displayName, long runtime) {
        System.out.printf("%s '%s' took %d ms.%n", unit, displayName, runtime);
    }

    private enum LaunchTimeKey {
        CLASS,
        TEST
    }

}
