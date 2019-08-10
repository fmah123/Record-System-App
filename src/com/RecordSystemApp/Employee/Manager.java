package com.RecordSystemApp.Employee;

import com.RecordSystemApp.BaseClass.Employee;

import java.io.Serializable;

public class Manager extends Employee implements Serializable{

    //Class no longer in use

    private String name;

    public Manager(String name) {
        super(name, EmployeeType.MANAGER);
    }

    @Override
    public String getName() {
        return name;
    }
}

