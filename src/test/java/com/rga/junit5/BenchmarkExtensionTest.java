package com.rga.junit5;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rga.core.annotation.Benchmark;

@RunWith(JUnitPlatform.class)
@Benchmark
public class BenchmarkExtensionTest {

    @Test
    @Benchmark
    void sleep20ms() throws Exception {
        Thread.sleep(20);
    }

    @Test
    @Benchmark
    void sleep50ms() throws Exception {
        Thread.sleep(50);
    }

}
