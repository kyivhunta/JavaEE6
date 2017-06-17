package com.serega.practice.module2.task1.consolehelper;

import com.serega.practice.module2.task1.api.API;
import com.serega.practice.module2.task1.exceptions.WrongId;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleSkill {

    private BufferedReader br;
    private API api;

    public ConsoleSkill(BufferedReader br, API api) {
        this.br = br;
        this.api = api;
    }

    public void chooseOperationWithSkill() {
        while (true) {
            System.out.println("1. Create skill.\n" +
                    "2. Read skill.\n" +
                    "3. Update skill.\n" +
                    "4. Delete skill.\n" +
                    "0. Return to main menu.");

            try {
                switch (Integer.parseInt(br.readLine())) {
                    case 1:
                        createSkill();
                        break;
                    case 2:
                        readSkill();
                        break;
                    case 3:
                        updateSkill();
                        break;
                    case 4:
                        deleteSkill();
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


    private void createSkill() {
        while (true) {
            System.out.println("Добавление навыка");
            try {
                String name;

                System.out.println("\nВведите название навыка: ");
                name = br.readLine();

                api.createSkill(name);
                System.out.println("\nНавык " + name + " успешно добавлен!");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readSkill() {

        System.out.println("Чтение навыка по ID");
        int id;

        while (true) {
            try {
                System.out.println("\nВведите ID навыка: ");
                id = Integer.parseInt(br.readLine());

                System.out.println(api.readSkill(id));

                return;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (WrongId wrongId) {
                System.out.println(wrongId.getMessage());
            }
        }
    }

    private void updateSkill() {

        System.out.println("Редактирование даных навыков");
        int id;
        String newName;

        while (true) {
            try {
                System.out.println("\nВведите ID навыка: ");
                id = Integer.parseInt(br.readLine());
                System.out.println(api.readSkill(id));
                System.out.println("\nВведите новое имя навыка: ");
                newName = br.readLine();

                api.updateSkill(id, newName);
                System.out.println("Измкнения произведены успешно!");

                return;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (WrongId wrongId) {
                System.out.println(wrongId.getMessage());
            }
        }

    }

    private void deleteSkill() {

        System.out.println("Удаление навыка по ID");
        int id;

        while (true) {
            try {
                System.out.println("\nВведите ID навыка: ");
                id = Integer.parseInt(br.readLine());

                api.deleteSkill(id);
                System.out.println("Навык с ID " + id + " успешно уделён");
                return;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (WrongId wrongId) {
                System.out.println(wrongId.getMessage());
            }
        }

    }
}
