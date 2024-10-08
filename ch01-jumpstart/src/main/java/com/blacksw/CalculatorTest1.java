package com.blacksw;

public class CalculatorTest1 {

    private int nbErrors = 0;

    public void testAdd() {
        Calculator calculator = new Calculator();
        double result = calculator.add(10, 40);
        if (result != 60) {
            throw new IllegalStateException("Bad result : " + result);
        }
    }

    public static void main(String[] args) {
        CalculatorTest1 test = new CalculatorTest1();
        try {
            test.testAdd();
            System.out.println("Test Sucess");
        } catch (Exception e) {
            System.out.println("Test Fail");
            test.nbErrors++;
            e.printStackTrace();
        }
        if (test.nbErrors > 0) {
            throw new IllegalStateException("There were " + test.nbErrors + " error(s)");
        }
    }

}
