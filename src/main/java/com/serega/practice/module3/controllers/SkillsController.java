package com.serega.practice.module3.controllers;


import com.serega.practice.module2.task1.exceptions.ThisSkillAlreadyExisted;
import com.serega.practice.module2.task1.exceptions.WrongId;
import com.serega.practice.module3.entity.Skill;

import java.util.List;


public interface SkillsController {

    boolean create(String nameSkill) throws ThisSkillAlreadyExisted;

    Skill read(int id) throws WrongId;

    boolean update(int id, String newName) throws WrongId;

    boolean delete(int id) throws WrongId;

    List<Skill> getAllSkills();
}
