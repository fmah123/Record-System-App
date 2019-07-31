package com.PracticeJava;

import java.io.Serializable;
import java.util.*;

public final class DevelopementManager extends Employee implements Management, Serializable {

    //private static int count = 0;
    private final int experience;
    private static List<DevelopementManager> manager;


    public DevelopementManager(String name, int experience) {
        super(name, EmployeeType.DEVELOPEMENT_MANAGER);
        this.experience = experience;
        if(this.manager != null){
            this.manager = new ArrayList<>(manager);
        } else{
            this.manager = new ArrayList<>();
        }
    }

    public List<DevelopementManager> getManager() {
        return manager;
    }

    public DevelopementManager CreateDevManger(String name, int experience){
        return new DevelopementManager(name,experience);
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
        return "Manager name: " + getName() + "\nPosition: Development Manager " + "\nManagement experience: " + ManagementExperience(experience);
    }
}
