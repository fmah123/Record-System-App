package com.PracticeJava;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public final class TestManager extends Employee implements Management, Serializable {

    //private static int count = 0;
    private static List<TestManager> manager;
    private int experience;


    public TestManager(String name, int experienceInMonths) {
        super(name, EmployeeType.TEST_MANAGER);
        this.experience = experienceInMonths;
          if(this.manager != null){
            this.manager = new LinkedList<>(manager);
        }else {
            this.manager = new LinkedList<>();
        }


//        if(count < 1){
//            count++;
//        } else {
//            System.out.println("No more than two test managers.");
//        }
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
