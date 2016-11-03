package com.rga.junit5;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.rga.core.annotation.TestLogger;
import com.rga.core.runner.LoggedRunner;

@RunWith(LoggedRunner.class)
public class CalculatorTest {

    @BeforeClass
    public static void createCalculator() {
        System.out.println("BeforeAll");

    }

    @Test
    @TestLogger(log = "asass")
    public void simpleDivide() {
        System.out.println("Hello, JUnit 5.");
    }

}
