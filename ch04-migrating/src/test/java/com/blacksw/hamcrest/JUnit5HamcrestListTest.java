package com.blacksw.hamcrest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class JUnit5HamcrestListTest {
    private List<String> values;

    @BeforeEach
    public void setUp() {
        values = new ArrayList<>();
        values.add("Oliver");
        values.add("Jack");
        values.add("Harry");
    }

    @Test
    @DisplayName("Hamcrest를 사용한 테스트")
    public void testListWithHamcrest() {
        assertThat(values, hasSize(3));
        assertThat(values, hasItem(anyOf(equalTo("Oliver"), equalTo("Jack"), equalTo("Harry"))));
        assertThat("리스트의 순서에 맞게 객체를 포함하고 있는지 검증", values, contains("Oliver", "Jack", "Harry"));
        assertThat("리스트의 순서에 상관없이 객체를 포함하고 있는지 검증", values, containsInAnyOrder("Oliver", "Jack", "Harry"));
    }

}
