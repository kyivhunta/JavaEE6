package com.serega.practice.module2.task1.consolehelper;

import com.serega.practice.module2.task1.api.API;
import com.serega.practice.module2.task1.exceptions.WrongId;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleCompany {


    private BufferedReader br;
    private API api;

    public ConsoleCompany(BufferedReader br, API api) {
        this.br = br;
        this.api = api;
    }

    public void chooseOperationWithCompany() {
        while (true) {
            System.out.println("1. Create company.\n" +
                    "2. Read company.\n" +
                    "3. Update company.\n" +
                    "4. Delete company.\n" +
                    "0. Return to main menu.");

            try {
                switch (Integer.parseInt(br.readLine())) {
                    case 1:
                        createCompany();
                        break;
                    case 2:
                        readCompany();
                        break;
                    case 3:
                        updateCompany();
                        break;
                    case 4:
                        deleteCompany();
                        break;
                    case 0:
                        return;
                    default:

                        System.out.println("Не верный номер операции, повторите ввод!");

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException n) {
                System.out.println("Wrong operations number, try again!");
            }
        }
    }

    private void createCompany() {
        System.out.println("Создание компании");
        String name;
        try {
            System.out.println("\nВведине название компании:");
            name = br.readLine();
            api.createCompany(name);
            System.out.println("Компания была успешно добавлена!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readCompany() {
        while (true) {
            System.out.println("Чтение компании по ID");
            int id;

            try {
                System.out.println("\nВведите ID омпании: ");
                id = Integer.parseInt(br.readLine());

                System.out.println(api.readCompany(id));

                return;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (WrongId wrongId) {
                System.out.println(wrongId.getMessage());
            }
        }


    }

    private void updateCompany() {

        System.out.println("Изменение даных компании");
        int id;
        String name;
        while (true) {
            try {
                System.out.println("\nВведите айди компании даные которой вы хотите изменить: ");
                id = Integer.parseInt(br.readLine());
                System.out.println(api.readCompany(id));
                System.out.println("Введите новое название компании:");
                name = br.readLine();

                api.updateCompany(id, name);
                System.out.println("Изменения ушпешно внесены");
                System.out.println(api.readCompany(id));

                return;

            } catch (WrongId wrongId) {
                System.out.println(wrongId.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private void deleteCompany() {
        while (true) {
            System.out.println("Удаление компании по ID");
            int id;

            try {
                System.out.println("\nВведите ID омпании: ");
                id = Integer.parseInt(br.readLine());

                System.out.println(api.deleteCompany(id));
                System.out.println("Компания с ID = " + id + " успешно удалена!");

                return;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (WrongId wrongId) {
                System.out.println(wrongId.getMessage());
            }
        }

    }


}
