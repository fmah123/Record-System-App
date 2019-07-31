package com.PracticeJava;

import java.io.*;
import java.nio.channels.Channel;
import java.util.*;

public final class Records {
    private final RecordController controller;

    private Records(){
        this.controller = new RecordController();
    }

    //singleton design pattern shown below:
    //
    //    private static Records singleInstance = null;
    //
    //    public synchronized static Records getSingleInstance(){
    //        if(singleInstance == null){
    //            singleInstance = new Records();
    //        }
    //        return singleInstance;
    //    }
    // Utillised Intialisation-on-demand ---> .


    public static Records getInstance(){
        return RecordHolder.INSTANCE;
    }

    private static class RecordHolder{private static final Records INSTANCE = new Records();}


    //method adds an employee to the team object.
    public boolean addEmployeeToTeam(Team team, Employee employee){
        return controller.RecordEmployee(team, employee);
    }

    //method returns a Team object.
    public Team getTeam(String name){
        if(this.controller.getRecordTeam().containsKey(name)){
            return this.controller.getRecordTeam().get(name);
        }
        return null;
    }

    //method adds a test manager object
    public boolean addTestManager(TestManager manager){
        if(manager != null){
            manager.getManager().add(manager);
            return true;
        }
        return false;
    }

    // gets the test manager object from list
    public TestManager getTestManager(Employee employee, Team team){
        if(employee != null && team != null){
            return (TestManager) team.getEmployee(employee);
        }

        return null;
    }

    //adds development manager object
    public boolean addDevManager(DevelopementManager manager){
        if(manager != null){
            manager.getManager().add(manager);
            return true;
        }

        return false;
    }

    public DevelopementManager getDevManager(Employee employee, Team team){
        if(employee!= null && team != null){
            return (DevelopementManager)team.getEmployee(employee);
        }
        return null;
    }


    public boolean addTeamToRecord(Team team){
        return controller.RecordTeam(team);
    }

    //This method saves the team objects and the contained employee objects.
    public boolean SaveRecords() {
        try (ObjectOutputStream file = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("records.dat")))){
            for(Team team : controller.getRecordTeam().values()){
                file.writeObject(team);
            }
            return true;
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }

        return false;
    }

    //This method uploads the employee object from file stored on disk.
    public void uploadRecords() {
        try (ObjectInputStream file = new ObjectInputStream(new BufferedInputStream(new FileInputStream("records.dat")))) {
            boolean eof = false;
            while (!eof) {
                try {
                    Team team = (Team)(file.readObject());
                    System.out.println(team);
                }catch(EOFException e){
                    eof = true;
                }
            }
        } catch (InvalidClassException e) {
            System.out.println("InvalidClassException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: " + e.getMessage());
        }
    }

    //This inner class is used to abstract methods used to implement different actions of the program.
    private class RecordController{
        private Map<String, Team> recordTeam;

        private RecordController(){
            this.recordTeam = new HashMap<>();
        }

        //This prevents any user from modifying contents of the map like changing team objects.
        private Map<String, Team> getRecordTeam() {
            return Collections.unmodifiableMap(recordTeam);
        }

        private boolean RecordEmployee(Team team, Employee employee){
            if(employee != null){
                this.recordTeam.get(team.getTeamName()).addTeamMembers(employee);
                return true;
            }
            return false;
        }

        private boolean RecordTeam(Team team){
            if(team != null){
                recordTeam.put(team.getTeamName(), team);
                return true;
            }
            return false;
        }
    }
}
