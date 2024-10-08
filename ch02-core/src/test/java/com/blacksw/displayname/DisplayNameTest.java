package com.blacksw.displayname;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test class showing the @DisplayName annotation.")
public class DisplayNameTest {
    private final SUT systemUserTest = new SUT();

    @Test
    @DisplayName("Out systeme under test says hello")
    void testHello() {
        assertEquals("Hello", systemUserTest.hello());
    }

    @Test
    @DisplayName("\uD83D\uDE31")
    void testTalking() {
        assertEquals("How are you?", systemUserTest.talk());
    }

    @Test
    void testBye() {
        assertEquals("Bye", systemUserTest.bye());
    }

}
