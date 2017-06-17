package com.serega.practice.module2.task1.entity;

import java.util.ArrayList;

public class Project {

    private int id;
    private String name;
    private int cost;
    private ArrayList<Developer> developers;
    private Customer customer;
    private Company company;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(ArrayList<Developer> developers) {
        this.developers = developers;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        String dev = "";
        if (developers != null) {
            for (int i = 0; i < developers.size(); i++) {
                dev+=developers.get(i).getName()+" "+developers.get(i).getSecondName()+(i==developers.size()-1?"":", ");
            }
        }else dev = "Dont have developers";

        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", developers=" + dev +
                ", customer=" + (customer==null?"Dont have customer":customer.getName() + " " + customer.getSecondName()) +
                ", company=" +(company==null? "Dont have a company": company.getName()) +
                '}';
    }
}
