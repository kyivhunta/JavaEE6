package com.serega.practice.module2.task1.daos;

import com.serega.practice.module2.task1.entity.Skill;

import java.util.ArrayList;
import java.util.List;

public interface DaoSkills {

    void create(String nameSkill);

    Skill read(int id);

    void update(int id, String newName);

    void delete(int id);

    List<Skill> getAll();

}
