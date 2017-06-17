package com.serega.practice.module2.task1.entity;


import java.util.ArrayList;

public class Developer {

    private int id;
    private String name;
    private String secondName;
    private int salary;
    private ArrayList<Skill> skills = new ArrayList<>();
    private Project project;


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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        String sk = "";
        if (skills != null) {
            for (int i = 0; i < skills.size(); i++) {

                sk += skills.get(i).getName() + (i == skills.size() - 1 ? "" : ",");
            }
        } else {
            sk = "Developer don't have a skills";
        }

        return "Developer " +
                "id=" + id +
                ",| name: '" + name + '\'' +
                ",| secondName: '" + secondName + '\'' +
                ",| salary: " + salary +
                ",| skills: " + sk +
                " | project: " + (project == null ? "Dont have a project!" : project.getName());
    }
}
