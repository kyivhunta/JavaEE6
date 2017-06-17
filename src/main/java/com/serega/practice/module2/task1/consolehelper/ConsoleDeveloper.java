package com.serega.practice.module2.task1.consolehelper;

import com.serega.practice.module2.task1.api.API;
import com.serega.practice.module2.task1.exceptions.SomeSkillsDontExist;
import com.serega.practice.module2.task1.exceptions.WrongId;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConsoleDeveloper {

    private BufferedReader br;
    private API api;

    public ConsoleDeveloper(BufferedReader br, API api) {
        this.br = br;
        this.api = api;
    }

    public void chooseOperationWithDeveloper() {
        while (true) {
            System.out.println("1. Create developer.\n" +
                    "2. Read developer.\n" +
                    "3. Update developer.\n" +
                    "4. Delete developer.\n" +
                    "0. Return to main menu.");

            try {
                switch (Integer.parseInt(br.readLine())) {
                    case 1:
                        createDeveloper();
                        break;
                    case 2:
                        readDeveloper();
                        break;
                    case 3:
                        updateDeveloper();
                        break;
                    case 4:
                        deleteDeveloper();
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

    private void createDeveloper() {
        while (true) {
            System.out.println("Создать разработчика\n");

            String name;
            String secondName;
            int salary;
            String projectName;
            String skill;
            List<String> skills;

            try {
                System.out.println("Введите имя: ");
                name = br.readLine();

                System.out.println("Введите фамилию: ");
                secondName = br.readLine();

                System.out.println("Введите размер зарплаты: ");
                salary = Integer.parseInt(br.readLine());

                System.out.println("Введите название проекта над которым будет работать разработчик: ");
                projectName = br.readLine();

                System.out.println("Введите список навыков для разработчика! Для окончания ввода введите \"0\"!");
                skills = new ArrayList<>();

                while (!Objects.equals(skill = br.readLine(), "0")) {
                    skills.add(skill);
                }

                api.createDeveloper(name, secondName, salary, projectName, skills);
                System.out.println("Разработчик успешно добавлен в базу данных!");
                return;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (SomeSkillsDontExist someSkillsDontExist) {
                System.out.println(someSkillsDontExist.getMessage());
            }
        }
    }

    private void readDeveloper() {

        System.out.println("Чтение разработчика по ID");
        while (true) {

            int id;

            try {
                System.out.println("\nВведите ID разработчика");
                id = Integer.parseInt(br.readLine());

                System.out.println(api.readDeveloper(id));

                return;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (WrongId wrongId) {
                System.out.println(wrongId.getMessage());
            }
        }
    }


    private void updateDeveloper() {
        while (true) {

            System.out.println("Редактирование даных разработчика");

            int id;
            String newName;
            String newSecondName;
            int newSalary;
            String newskill;
            List<String> newSkills;

            try {
                System.out.println("Введите ID разработчика даные которого вы хотите изменить:");
                id = Integer.parseInt(br.readLine());
                System.out.println(api.readDeveloper(id));

                System.out.println("Введите новое имя: ");
                newName = br.readLine();

                System.out.println("Введите новую фамилию: ");
                newSecondName = br.readLine();

                System.out.println("Введите новый размер зарплаты: ");
                newSalary = Integer.parseInt(br.readLine());

                System.out.println("Введите новый список навыков для разработчика! Для окончания ввода введите \"0\"!");
                newSkills = new ArrayList<>();

                while (!Objects.equals(newskill = br.readLine(), "0")) {
                    newSkills.add(newskill);
                }

                api.updateDeveloper(id, newName, newSecondName, newSalary, newSkills);
                System.out.println("Измкнения произведены успешно!");
                api.readDeveloper(id);
                return;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (WrongId wrongId) {
                System.out.println(wrongId.getMessage());
            }

        }
    }

    private void deleteDeveloper() {

        System.out.println("Удаление разработчика по ID");
        while (true) {

            int id;

            try {
                System.out.println("\nВведите ID разработчика");
                id = Integer.parseInt(br.readLine());

                api.deleteDeveloper(id);
                System.out.println("Разработчик с ID " + id + " успешно удалён!");

                return;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (WrongId wrongId) {
                System.out.println(wrongId.getMessage());
            }
        }

    }


}
