package com.PracticeJava;

import java.io.Serializable;

public abstract class Employee implements Serializable{
    private String name;
    private String position;

    private long SerialVersionUID = 1L;

    public Employee(String name, EmployeeType type) {
        this.name = name;

        //This is used to distinguish different types.
        if(type.toString().toLowerCase().equals("developer") ){
            this.position = "Developer";
        }else if(type.toString().toLowerCase().equals("tester")){
            this.position = "Tester";
        }else{
            this.position = "Manager";
        }
    }

    //This is enumerated data type is used to classify different employee types from the developer to the tester.
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


    @Override
    public String toString() {
        return "Employee name: " + name + "\nPosition: " + position;
    }
}
