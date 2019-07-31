package com.PracticeJava;

import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable {
    private String teamName;
    private ArrayList<Employee> team;
    private static int count = 0;

    public Team(String name) {
        this.teamName = name;
        this.team = new ArrayList<>();
        count++;
    }

    public ArrayList<Employee> getTeam() {
        return team;
    }

    public int getCount() {
        return count;
    }

    public String getTeamName() {
        return teamName;
    }

    //This adds team members to the team list object.
    public boolean addTeamMembers(Employee employee){
        if(employee!=null){
            team.add(employee);
            return true;
        }
        return false;
    }


    public int findEmployee(Employee employee){
        return this.team.indexOf(employee);
    }

    //This method will find the employee object with another method on line 39 being used to simplyfy this method for readability.
    public Employee getEmployee(Employee employee){
        int index = findEmployee(employee);
        if(index >= 0){
            return this.team.get(index);
        }
        return null;
    }

    //The toString() method will print out all record details like team name and members of the team.
    @Override
    public String toString() {
        String employeeString = "";
        for(Employee employee: team){
            employeeString += employee + "\n";
        }
        return ("-------There are " + count + (count>1?" teams:\n":" team:\n") + ("The team name is " + teamName + "\n") +
          employeeString);
    }
}
