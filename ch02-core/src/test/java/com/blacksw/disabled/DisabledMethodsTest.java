package com.blacksw.disabled;

import com.blacksw.lifecycle.SUT;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DisabledMethodsTest {
    private final SUT systemUnderTest = new SUT("테스트 대상 시스템");

    @Test
    @Disabled
    void testReceiveRegularWork() {
        boolean canReceiveRegularWork = systemUnderTest.canReceiveRegularWork();
        assertTrue(canReceiveRegularWork);
    }

    @Test
    @Disabled("기능 개발 중")
    void testReceiveAdditionalWork() {
        boolean canReceiveAdditionalWork = systemUnderTest.canReceiveAdditionalWork();
        assertFalse(canReceiveAdditionalWork);
    }
}
