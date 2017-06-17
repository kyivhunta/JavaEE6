package com.serega.practice.module2.task1.consolehelper;

import com.serega.practice.module2.task1.api.API;
import com.serega.practice.module2.task1.exceptions.WrongId;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleCustomer {

    private BufferedReader br;
    private API api;

    public ConsoleCustomer(BufferedReader br, API api) {
        this.br = br;
        this.api = api;
    }

    public void chooseOperationWithCustomer() {
        while (true) {
            System.out.println("1. Create customer.\n" +
                    "2. Read customer.\n" +
                    "3. Update customer.\n" +
                    "4. Delete customer.\n" +
                    "0. Return to main menu.");

            try {
                switch (Integer.parseInt(br.readLine())) {
                    case 1:
                        createCustomer();
                        break;
                    case 2:
                        readCustomer();
                        break;
                    case 3:
                        updateCustomer();
                        break;
                    case 4:
                        deleteCustomer();
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

    private void createCustomer() {

        System.out.println("Создание заказчика");
        String name;
        String secondName;

        try {
            System.out.println("\nВведите имя заказчика: ");
            name = br.readLine();
            System.out.println("Введите фамилию заказчика: ");
            secondName = br.readLine();

            api.createCustomer(name, secondName);

            System.out.println("Заказчик " + name + " " + secondName + " успешно добавлен!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readCustomer() {

        System.out.println("Чтение заказчика по ID");
        int id;

        while (true) {

            try {
                System.out.println("\nВведите ID заказчика: ");
                id = Integer.parseInt(br.readLine());

                System.out.println(api.readCustomer(id));

                return;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (WrongId wrongId) {
                System.out.println(wrongId.getMessage());
            }
        }

    }

    private void updateCustomer() {

        System.out.println("Изменение даных заказчика");
        int id;
        String name;
        String secondName;
        while (true) {
            try {
                System.out.println("\nВведите ID заказчика даные которого вы хотите изменить: ");
                id = Integer.parseInt(br.readLine());
                System.out.println(api.readCustomer(id));

                System.out.println("\nВведите новое имя заказчика: ");
                name = br.readLine();

                System.out.println("Введите новую фамилию заказчика: ");
                secondName = br.readLine();

                api.updateCustomer(id, name, secondName);

                System.out.println("Изменения внесены успешно!");
                System.out.println(api.readCustomer(id));

                return;

            } catch (WrongId wrongId) {
                System.out.println(wrongId.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteCustomer() {

        System.out.println("Удаление заказчика по ID");
        int id;

        while (true) {

            try {
                System.out.println("\nВведите ID заказчика: ");
                id = Integer.parseInt(br.readLine());

                api.deleteCustomer(id);
                System.out.println("Заказчик с ID = " + id + " успешно удалён!");

                return;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (WrongId wrongId) {
                System.out.println(wrongId.getMessage());
            }
        }

    }


}
