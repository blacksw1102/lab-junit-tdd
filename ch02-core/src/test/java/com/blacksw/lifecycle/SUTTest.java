package com.blacksw.lifecycle;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SUTTest {
    private static ResourceForAllTests resourceForAllTests;
    private SUT systemUserTest;

    @BeforeAll
    static void setUpClass() {
        resourceForAllTests = new ResourceForAllTests("테스트를 위한 리소스");
    }

    @AfterAll
    static void tearDownClass() {
        resourceForAllTests.close();
    }

    @BeforeEach
    void setUp() {
        systemUserTest = new SUT("테스트 대상 시스템");
    }

    @AfterEach
    void tearDown() {
        systemUserTest.close();
    }

    @Test
    void testRegularWork() {
        boolean canBeceiveRegularWork = systemUserTest.canReceiveRegularWork();
        assertTrue(canBeceiveRegularWork);
    }

    @Test
    void testAdditionalWork() {
        boolean canReceiveAdditionalWork = systemUserTest.canReceiveAdditionalWork();
        assertFalse(canReceiveAdditionalWork);
    }

}
