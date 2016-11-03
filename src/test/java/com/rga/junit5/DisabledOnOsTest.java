package com.rga.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.rga.core.annotation.DisabledOnOs;
import com.rga.core.entity.OS;

@RunWith(JUnitPlatform.class)
public class DisabledOnOsTest {

    @Test
    @DisabledOnOs(OS.MAC_OS)
    void does_not_run_on_mac_os() throws Exception {
        assertEquals(1 + 1, 3, "change your computer to Mac will solve this failing test");
    }

    @Test
    @DisabledOnOs(OS.WINDOWS_7)
    public void does_not_run_on_windows_7() throws Exception {
        assertTrue(true);
    }
}
