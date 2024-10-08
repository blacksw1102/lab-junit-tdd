package com.blacksw.assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AssertThrowsTest {
    private SUT systemUnderTest = new SUT("테스트 대상 시스템");

    @Test
    @DisplayName("예외가 발생하는지 검증한다")
    void testExpectedException() {
        assertThrows(NoJobException.class, systemUnderTest::run);
    }

    @Test
    @DisplayName("예외가 발생하고 예외에 대한 참조가 유지되는지 검증한다")
    void testCatchException() throws InterruptedException {
        Throwable throwable = assertThrows(NoJobException.class, () -> systemUnderTest.run(1000));
        assertEquals("실행할 작업이 없습니다", throwable.getMessage());
    }

}
