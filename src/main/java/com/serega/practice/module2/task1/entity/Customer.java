package com.serega.practice.module2.task1.entity;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private int id;
    private String name;
    private String secondName;
    private List<Project> projects;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        String proj = "";
        if (projects != null) {
            for (int i = 0; i < projects.size(); i++) {


                proj+=projects.get(i).getName()+(i==projects.size()-1?"":", ");
            }
        }

        return "Customer" +
                "id:" + id +
                ", name: '" + name + '\'' +
                ", secondName: '" + secondName + '\'' +
                ", projects: " + (projects == null ? "Don't have a projects" : proj) ;
    }
}
