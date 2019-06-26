package com.PracticeJava;

public class Tester extends Employee {
    private String name;

    public Tester(String name) {
        super(name, EmployeeType.TESTER);
    }

    @Override
    public String getName() {
        return name;
    }
}
