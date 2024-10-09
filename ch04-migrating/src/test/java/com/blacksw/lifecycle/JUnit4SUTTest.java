package com.blacksw.lifecycle;

import org.junit.*;

import static org.junit.Assert.*;

public class JUnit4SUTTest {
    private static ResourceForAllTests resourceForAllTests;
    private static SUT systemUnderTest;

    @BeforeClass
    public static void setUpClass() {
        resourceForAllTests = new ResourceForAllTests("테스트를 위한 리소스");
    }

    @AfterClass
    public static void tearDownClass() {
        resourceForAllTests.close();
    }

    @Before
    public void setUp() {
        systemUnderTest = new SUT("테스트 대상 시스템");
    }

    @After
    public void tearDown() {
        systemUnderTest.close();
    }

    @Test
    public void testRegularWork() {
        boolean canReceiveRegularWork = systemUnderTest.canReceiveRegularWork();
        assertTrue(canReceiveRegularWork);
    }

    @Test
    public void testAdditionalWork() {
        boolean canReceiveAdditionalWork = systemUnderTest.canReceiveAdditionalWork();
        assertFalse(canReceiveAdditionalWork);
    }

    @Test
    @Ignore
    public void myThirdTest() {
        assertEquals("2와 1이 다른지 검증", 2, 1);
    }

}