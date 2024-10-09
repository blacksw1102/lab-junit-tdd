package com.blacksw.rules;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import javax.swing.plaf.nimbus.State;

public class CustomStatement extends Statement {
    private Statement base;
    private Description description;

    public CustomStatement(Statement base, Description description) {
        this.base = base;
        this.description = description;
    }

    @Override
    public void evaluate() throws Throwable {
        System.out.println(getClass().getSimpleName() + " " + description.getMethodName() + " has started");
        try {
            base.evaluate();
        } finally {
            System.out.println(getClass().getSimpleName() + " " + description.getMethodName() + " has finished");
        }
    }
}
