package com.serega.practice.module2.task1.controllers;

import com.serega.practice.module2.task1.entity.Project;
import com.serega.practice.module2.task1.exceptions.WrongId;

public interface ProjectController {

    boolean create(String name, int cost, int idCompany, int idCustomer);

    Project read(int id) throws WrongId;

    boolean update(int id, String newName, int newCost) throws WrongId;

    boolean delete(int id) throws WrongId;

    void showAll();

}
