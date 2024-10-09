package com.blacksw.rules;

import com.blacksw.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JUnit5ExceptionTester {
    private Calculator calculator = new Calculator();

    @Test
    public void expectIllegalArgumentException() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> calculator.sqrt(-1));
        assertEquals("음수의 제곱근을 구할 수 없다", throwable.getMessage());
    }

    @Test
    public void expectArithmeticException() {
        Throwable throwable = assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0));
        assertEquals("0으로 나눌 수 없다", throwable.getMessage());
    }
}
