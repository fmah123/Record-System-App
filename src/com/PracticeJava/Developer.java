package com.PracticeJava;

import java.io.Serializable;

public class Developer extends Employee implements Serializable{
    private String name;

    public Developer(String name) {
        super(name, EmployeeType.DEVELOPER);
    }

    @Override
    public String getName() {
        return name;
    }

}
