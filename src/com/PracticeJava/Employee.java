package com.PracticeJava;

import java.io.Serializable;

public abstract class Employee implements Serializable{
    private String name;
    private String position;

    private long SerialVersionUID = 1L;

    public Employee(String name, EmployeeType type) {
        this.name = name;
        if(type.toString().toLowerCase().equals("developer") ){
            this.position = "Developer";
        }else if(type.toString().toLowerCase().equals("tester")){
            this.position = "Tester";
        }else{
            this.position = "Manager";
        }
    }

    public enum EmployeeType{
        DEVELOPER,
        TESTER,
        TEST_MANAGER,
        DEVELOPEMENT_MANAGER,
        MANAGER
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Employee name: " + name + "\nPosition: " + position;
    }
}
