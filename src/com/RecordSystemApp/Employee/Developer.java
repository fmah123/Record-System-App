package com.RecordSystemApp.Employee;

import com.RecordSystemApp.BaseClass.Employee;

import java.io.Serializable;

public class Developer extends Employee implements Serializable{
    String name;

    public Developer(String name) {
        super(name, EmployeeType.DEVELOPER);
    }

    @Override
    public String getName() {
        return name;
    }

}
