package com.PracticeJava;

import java.io.Serializable;

public class Manager extends Employee implements Serializable{
    private String name;

    public Manager(String name) {
        super(name, EmployeeType.MANAGER);
    }

    @Override
    public String getName() {
        return name;
    }
}
