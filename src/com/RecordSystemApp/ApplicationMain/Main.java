package com.RecordSystemApp.ApplicationMain;


import com.RecordSystemApp.BaseClass.Employee;
import com.RecordSystemApp.Employee.*;
import com.RecordSystemApp.Recorder.Records;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Records records = Records.getInstance();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);


        Thread thread = new Thread(() ->  {
                int flag;

                printActions();

                boolean quit = false;

                //This indefinite while loop is the controller of the application.
                while (!quit) {
                    try {
                        System.out.println("Enter actions: ");
                        flag = scanner.nextInt();
                        scanner.nextLine();
                        switch (flag) {
                            case 0:
                                System.out.println("Closing records.....");
                                quit = true;
                                scanner.close();
                                System.out.println("Thread is now closed");
                                executorService.shutdown();
                                break;
                            case 1:
                                printActions();
                                break;
                            case 2:
                                addTeam();
                                break;
                            case 3:
                                addEmployee();
                                break;
                            case 4:
                                RecordsToFile();
                                break;
                            case 5:
                                RecordsFromDB();
                                break;
                            case 6:
                                getManagersList();
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("InputMisMatchError: " + e.getMessage());
                        quit = true;
                    }
                }
        });

        thread.setName("Records thread");
        //System.out.println("Thread: " + thread.getName() + " closing.");
        executorService.execute(thread);

    }

    //This method provides a nice console output and allows users to input employee information such as name,
    // position known as type, team name employee belongs, as well as the months XP for managers.
    // .
    private static void addEmployee() {
        Employee employee;
        String experience = "0";

        try {
            System.out.println("Add employee name: ");
            String name = scanner.nextLine();
            System.out.println("Add employee type: ");
            String type = scanner.nextLine();
            System.out.println("What is the team name:");
            String teamName = scanner.nextLine();
            if (type.toLowerCase().equals("test manager") || type.toLowerCase().equals("development manager")) {
                System.out.println("How many months experience: ");
                experience = scanner.nextLine();
            }
            Team team = records.getTeam(teamName);
            //Line 92 to 149: The switch statement allows the right code to execute for a particular employment type.
            switch (type.toLowerCase()) {
                case "developer":
                    employee = new Developer(name);
                    if (team != null) {
                        if (records.addEmployeeToTeam(team, employee)) {
                            System.out.println("Employee added successfully to team.");
                        }else{
                            System.out.println("Error employee not added.");
                        }
                    } else {
                        System.out.println("Team does not exist.");
                    }
                    break;
                case "tester":
                    employee = new Tester(name);
                    if (team != null) {
                        if (records.addEmployeeToTeam(team, employee)) {
                            System.out.println("Employee added successfully to team.");
                        }else{
                            System.out.println("Error employee not added.");
                        }
                    } else {
                        System.out.println("Team does not exist.");
                    }

                    break;
                case "test manager":
                    employee = new TestManager(name, Integer.parseInt(experience));
                    if (team != null) {
                        if (records.addEmployeeToTeam(team, employee)) {
                            System.out.println("Employee added successfully to team.");
                            if(records.addTestManager(records.getTestManager(employee, team))){
                                System.out.println("Test Manager recorded");
                            }
                        } else {
                            System.out.println("No more than one Test Manager in a team");
                        }
                    } else {
                        System.out.println("Team does not exist.");
                    }
                    break;
                case "development manager":
                    employee = new DevelopementManager(name, Integer.parseInt(experience));
                    if (team != null) {
                        if (records.addEmployeeToTeam(team, employee)) {
                            System.out.println("Employee added successfully to team.");
                            if(records.addDevManager(records.getDevManager(employee,team))){
                                System.out.println("Developement Manager recorded");
                            }
                        }
                    } else {
                        System.out.println("Team does not exist.");
                    }
                    break;

                default:
                    System.out.println("The type not recognised.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid character entered: " + e.getMessage());
        }

    }


    //Adds team to the record.
    private static void addTeam() {
        Team team;
        try {
            System.out.println("Add team name: ");
            String name = scanner.nextLine();
            team = new Team(name);
            if (records.addTeamToRecord(team)) {
                System.out.println("Team successfully added.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid character entered: " + e.getMessage());
        }
    }


    //Prints the options list.
    private static void printActions() {
        System.out.println("Select from the following options: \n" +
                "0 - to quit records app\n" +
                "1 - to print actions\n" +
                "2 - to add a team\n" +
                "3 - to add employees to team\n" +
                "4 - to save records to file\n" +
                "5 - to upload records from file.\n" +
                "6 - to get managers list.");
    }

    //Indicates if a record has been recorded successfully
    private static void RecordsToFile() {
        if (records.SaveRecords()) {
            System.out.println("Records saved successfully.");
        } else {
            System.out.println("Error saving file.");
        }
    }

    // Uploads records from data base.
    private static void RecordsFromDB() {
        records.uploadRecordsFromDB();
    }


    //gets the managers list from record.
    private static void getManagersList(){
        TestManager manager = new TestManager("name", 12);
        DevelopementManager manager1 = new DevelopementManager("name", 12);
        System.out.println("------------Developement Managers-------------------");
        for(DevelopementManager mng: manager1.getManager()){
            System.out.println(mng);
        }
        System.out.println("------------Test Managers-----------------------------");
        for(TestManager mng: manager.getManager()){
            System.out.println(mng);
        }
    }





}
