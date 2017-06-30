package com.serega.practice.module3.entity;

import javax.persistence.*;


@Entity
@Table(name = "skill")
@NamedQuery(name = "Skill.getAll",query = "SELECT skill FROM Skill as skill")
public class Skill {
    private int id;
    private String skill;
//    List<Developer> developers;

    public Skill() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSkill",nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int idSkill) {
        this.id = idSkill;
    }

    @Basic
    @Column(name = "skillName", nullable = false, length = 45)
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

//    @ManyToMany()
//    public List<Developer> getDevelopers() {
//        return developers;
//    }
//
//    public void setDevelopers(List<Developer> developers) {
//        this.developers = developers;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skill skill1 = (Skill) o;

        if (id != skill1.id) return false;
        if (skill != null ? !skill.equals(skill1.skill) : skill1.skill != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (skill != null ? skill.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", skill='" + skill + '\'' +
                '}';
    }
}
