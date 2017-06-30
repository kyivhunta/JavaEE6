package com.serega.practice.module3.consolehelper;


import java.io.BufferedReader;
import java.io.IOException;

public class Mainconsole {

    private BufferedReader br;
    private ConsoleDeveloper consoleDeveloper;
    private ConsoleSkill consoleSkill;
    private ConsoleCompany consoleCompany;
    private ConsoleCustomer consoleCustomer;
    private ConsoleProject consoleProject;

    public Mainconsole(BufferedReader br, ConsoleDeveloper consoleDeveloper, ConsoleSkill consoleSkill, ConsoleCompany consoleCompany, ConsoleCustomer consoleCustomer, ConsoleProject consoleProject) {
        this.br = br;
        this.consoleDeveloper = consoleDeveloper;
        this.consoleSkill = consoleSkill;
        this.consoleCompany = consoleCompany;
        this.consoleCustomer = consoleCustomer;
        this.consoleProject = consoleProject;
    }

    public void chooseOperation() {
        while (true) {

            System.out.println("\t\tMain menu.\n\n" +
                    "1. Operations with skills.\n" +
                    "2. Operations with developers.\n" +
                    "3. Operations with customers.\n" +
                    "4. Operations with companies.\n" +
                    "5. Operations with projects.\n" +
                    "0. Stop the program.\n\n" +

                    "Choose operation!");
            try {
                switch (Integer.parseInt(br.readLine())) {
                    case 1:
                        System.out.println("Operations with SKILLS\n");
                        consoleSkill.chooseOperationWithSkill();
                        break;
                    case 2:
                        System.out.println("Operations with DEVELOPERS\n");
                        consoleDeveloper.chooseOperationWithDeveloper();
                        break;
                    case 3:
                        System.out.println("Operations with CUSTOMERS\n");
                        consoleCustomer.chooseOperationWithCustomer();
                        break;
                    case 4:
                        System.out.println("Operations with COMPANIES\n");
                        consoleCompany.chooseOperationWithCompany();
                        break;
                    case 5:
                        System.out.println("Operations with PROJECTS\n");
                        consoleProject.chooseOperationWithProject();
                        break;
                    case 0:
                        System.out.println("PROGRAM STOPPED");
                        return;

                    default:
                        System.out.println("Wrong operations number, try again!");
                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException n) {
                System.out.println("Wrong operations number, try again!");
            }
        }


    }
}
