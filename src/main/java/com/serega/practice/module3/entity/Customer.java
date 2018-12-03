package com.serega.practice.module3.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "customer")
@NamedQuery(name = "Customer.getAll", query = "SELECT customer FROM Customer customer")
public class Customer {
    private int idCustomer;
    private String name;
    private String secondName;
    private List<Project> projects;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCustomer", nullable = false)
    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    @Column(name = "name", length = 45, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "secondName", length = 45, nullable = false)
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Customer() {
    }

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (idCustomer != customer.idCustomer) return false;
        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
        if (secondName != null ? !secondName.equals(customer.secondName) : customer.secondName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCustomer;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        return result;
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
                "id:" + idCustomer +
                ", name: '" + name + '\'' +
                ", secondName: '" + secondName + '\'' +
                ", projects: " + (proj.length()<1 ? "Don't have a projects" : proj) ;
    }
}
