package com.serega.practice.module2.task1.consolehelper;

import com.serega.practice.module2.task1.api.API;
import com.serega.practice.module2.task1.exceptions.WrongId;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleProject {

    private BufferedReader br;
    private API api;

    public ConsoleProject(BufferedReader br, API api) {
        this.br = br;
        this.api = api;
    }

    public void chooseOperationWithProject() {
        while (true) {
            System.out.println("1. Create project.\n" +
                    "2. Read project.\n" +
                    "3. Update project.\n" +
                    "4. Delete project.\n" +
                    "0. Return to main menu.");

            try {
                switch (Integer.parseInt(br.readLine())) {
                    case 1:
                        createProject();
                        break;
                    case 2:
                        readproject();
                        break;
                    case 3:
                        updateProject();
                        break;
                    case 4:
                        deleteProject();
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

    private void createProject() {

        System.out.println("Создание проекта");
        String name;
        int cost;
        int idCompany;
        int idCustomer;
        while (true) {
            try {
                System.out.println("\nВведите название проекта: ");
                name = br.readLine();

                System.out.println("Введите стоимость проекта: ");
                cost = Integer.parseInt(br.readLine());

                System.out.println("Список доступных компаний >>>>>>>>>>>>>>>");
                api.showAllCompanies();
                System.out.println("Введите ID компании: >>>>>>>>>>>>>>>");
                idCompany = Integer.parseInt(br.readLine());

                System.out.println("Список заказчиков");
                api.showAllCustomers();
                System.out.println("Введите ID заказчика: ");
                idCustomer = Integer.parseInt(br.readLine());

                api.createProject(name, cost, idCompany, idCustomer);
                System.out.println("Проект: " + name +
                        "\nстоимость: " + cost +
                        "\nкомпания исполнитель: " + api.readCompany(idCompany).getName() +
                        "\nзаказчик: " + api.readCustomer(idCustomer).getName() + " " + api.readCustomer(idCustomer).getSecondName());
                System.out.println("Успешно добавлен в базу даных!");

                return;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (WrongId wrongId) {
                System.out.println(wrongId.getMessage());
            }
        }

    }

    private void readproject() {

        System.out.println("Чтение проекта по ID");
        int id;

        while (true) {
            try {
                System.out.println("\nВведите ID проекта: ");
                id = Integer.parseInt(br.readLine());

                System.out.println(api.readProject(id));
                return;
            } catch (WrongId wrongId) {
                System.out.println(wrongId.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void updateProject() {

        System.out.println("Изменение даных проекта");
        String name;
        int id;
        int cost;
        while (true) {
            try {
                System.out.println("\nВведите ID проекта даные которого вы хотите изменить: ");
                id = Integer.parseInt(br.readLine());

                System.out.println("Введите новое название проекта: ");
                name = br.readLine();

                System.out.println("Введите новую стоимость проекта: ");
                cost = Integer.parseInt(br.readLine());

                api.updateProject(id, name, cost);
                System.out.println(api.readProject(id) + "\nИзменения внесены!");

                return;

            } catch (WrongId wrongId) {
                System.out.println(wrongId.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteProject() {

        System.out.println("Удаление проекта по ID");
        int id;

        while (true) {
            try {
                System.out.println("\nВведите ID проекта: ");
                id = Integer.parseInt(br.readLine());

                api.deleteProject(id);

                System.out.println("Проект с ID успешно удалён! ");
                return;
            } catch (WrongId wrongId) {
                System.out.println(wrongId.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
