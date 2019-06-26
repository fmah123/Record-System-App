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

    public Employee getEmployee(Employee employee){
        int index = findEmployee(employee);
        if(index >= 0){
            return this.team.get(index);
        }
        return null;
    }

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
