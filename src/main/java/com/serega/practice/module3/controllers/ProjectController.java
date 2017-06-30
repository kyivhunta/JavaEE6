package com.serega.practice.module3.controllers;

import com.serega.practice.module2.task1.exceptions.WrongId;
import com.serega.practice.module3.entity.Company;
import com.serega.practice.module3.entity.Customer;
import com.serega.practice.module3.entity.Project;

import java.util.List;

public interface ProjectController {

    boolean create(String name, int cost, Company company, Customer customer);

    Project read(int id) throws WrongId;

    boolean update(int id, String newName, int newCost) throws WrongId;

    boolean delete(int id) throws WrongId;

    List<Project> getAllProject();

}
