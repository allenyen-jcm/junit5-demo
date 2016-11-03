package com.rga.junit5;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rga.core.extension.IgnoreIOExceptionExtension;

@RunWith(JUnitPlatform.class)
public class IgnoreIOExceptionTest {

    @Test
    @ExtendWith(IgnoreIOExceptionExtension.class)
    void test() throws IOException {
        throw new IOException("I/OException");
    }

}
