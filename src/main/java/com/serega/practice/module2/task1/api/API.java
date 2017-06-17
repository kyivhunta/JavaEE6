package com.serega.practice.module2.task1.api;

import com.serega.practice.module2.task1.entity.*;
import com.serega.practice.module2.task1.exceptions.SomeSkillsDontExist;
import com.serega.practice.module2.task1.exceptions.WrongId;

import java.util.List;


public interface API {

    boolean createDeveloper(String name, String secondName, int salary, String projectName, List<String> skills) throws SomeSkillsDontExist;

    boolean updateDeveloper(int id, String newName, String newSecondName, int newSalary, List<String> skills) throws WrongId;

    Developer readDeveloper(int id) throws WrongId;

    boolean deleteDeveloper(int id) throws WrongId;

    void showAllDevelopers();

    boolean createSkill(String nameSkill);

    Skill readSkill(int id) throws WrongId;

    boolean updateSkill(int id, String newName) throws WrongId;

    boolean deleteSkill(int id) throws WrongId;

    void ShowAllSkills();

    boolean createCustomer(String name, String secondName);

    Customer readCustomer(int id) throws WrongId;

    boolean updateCustomer(int id, String newName, String newSecondName) throws WrongId;

    boolean deleteCustomer(int id) throws WrongId;

    void showAllCustomers();

    boolean createCompany(String name);

    Company readCompany(int id) throws WrongId;

    boolean updateCompany(int id, String newName) throws WrongId;

    boolean deleteCompany(int id) throws WrongId;

    void showAllCompanies();

    boolean createProject(String name, int cost, int idCompany, int idCustomer) throws WrongId;

    Project readProject(int id) throws WrongId;

    boolean updateProject(int id, String newName, int newCost) throws WrongId;

    boolean deleteProject(int id) throws WrongId;

    void showAllProjects();
}
