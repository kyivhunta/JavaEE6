package com.serega.practice.module2.task1.entity;

import java.util.ArrayList;
import java.util.List;

public class Company {

    private int id;
    private String name;
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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        String comp = "";
        if (projects != null) {
            for (int i = 0; i < projects.size(); i++) {


                comp += projects.get(i).getName() + (i == projects.size() - 1 ? "" : ", ");
            }
        }

        return "Company{" +
                "id: " + id +
                ", name: '" + name + '\'' +
                ", [ projects: " + (projects != null ? comp : "Dont have a projects") + " ]" + '}';

    }
}
