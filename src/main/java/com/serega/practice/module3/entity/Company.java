package com.serega.practice.module3.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@NamedQuery(name = "Company.getAll", query = "SELECT company FROM Company company")
public class Company {
    private int idCompany;
    private String name;
    private List<Project> projects;

    public Company() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCompany", nullable = false)
    public int getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    }


    @Column(name = "name", length = 45, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
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

        Company company = (Company) o;

        if (idCompany != company.idCompany) return false;
        if (name != null ? !name.equals(company.name) : company.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCompany;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
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
                "id: " + idCompany +
                ", name: '" + name + '\'' +
                ", [ projects: " + (comp.length()>0 ? comp : "Dont have a projects") + " ]" + '}';

    }

}
