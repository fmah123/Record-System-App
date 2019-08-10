package com.RecordSystemApp.Employee;

import com.RecordSystemApp.BaseClass.Employee;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class TestManager extends Employee implements Management, Serializable {

    private List<TestManager> manager;
    private int experience;

    //Constructor sets the manager field variable with a new linked list or passes a existing linked list
    public TestManager(String name, int experienceInMonths) {
        super(name, EmployeeType.TEST_MANAGER);
        this.experience = experienceInMonths;
          if(this.manager != null){
            this.manager = new LinkedList<>(manager);
        }else {
            this.manager = new LinkedList<>();
        }
    }


    public List<TestManager> getManager() {
        return manager;
    }

    @Override
    public String ManagementExperience(int months) {
        int years = 0;
        if(months >= 0){
            years = months / 12;
            months -= (years * 12);
        }
        return  " " + years + " years, " + months + " months.";

    }

    @Override
    public String toString() {
        return "Manager name: " + getName() + "\nPosition: Test Manager " + "\nManagement experience: " + ManagementExperience(experience);
    }
}
