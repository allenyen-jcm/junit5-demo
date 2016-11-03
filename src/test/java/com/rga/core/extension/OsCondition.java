package com.rga.core.extension;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ContainerExecutionCondition;
import org.junit.jupiter.api.extension.ContainerExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionCondition;
import org.junit.jupiter.api.extension.TestExtensionContext;
import org.junit.platform.commons.util.AnnotationUtils;

import com.rga.core.annotation.DisabledOnOs;
import com.rga.core.entity.OS;

public class OsCondition implements ContainerExecutionCondition, TestExecutionCondition {

    @Override
    public ConditionEvaluationResult evaluate(ContainerExtensionContext context) {
        return evaluateIfAnnotated(context.getElement());
    }

    @Override
    public ConditionEvaluationResult evaluate(TestExtensionContext context) {
        return evaluateIfAnnotated(context.getElement());
    }

    private ConditionEvaluationResult evaluateIfAnnotated(Optional<AnnotatedElement> element) {
        Optional<DisabledOnOs> disabled = AnnotationUtils.findAnnotation(element, DisabledOnOs.class);

        if (disabled.isPresent())
            return disabledIfOn(disabled.get().value());

        return ConditionEvaluationResult.enabled("@DisabledOnOs is not present");
    }

    private ConditionEvaluationResult disabledIfOn(OS[] disabledOnOs) {
        OS os = OS.determine();
        if (Arrays.asList(disabledOnOs).contains(os))
            return ConditionEvaluationResult.disabled("Test is disabled on " + os + ".");
        else
            return ConditionEvaluationResult.enabled("Test is not disabled on " + os + ".");
    }

}
