package com.serega.practice.module2.task1.controllers;

import com.serega.practice.module2.task1.entity.Skill;
import com.serega.practice.module2.task1.exceptions.WrongId;


public interface SkillsController {

    boolean create(String nameSkill);

    Skill read(int id) throws WrongId;

    boolean update(int id, String newName) throws WrongId;

    boolean delete(int id) throws WrongId;

    void showAllSkills();
}
