package com.blacksw;

public class CalculatorTest {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        double result = calculator.add(10, 40);
        if (result != 60) {
            System.out.println("Bad result : " + result);
        }
    }
}
