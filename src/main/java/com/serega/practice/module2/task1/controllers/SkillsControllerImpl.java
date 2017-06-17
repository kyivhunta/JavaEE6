package com.serega.practice.module2.task1.controllers;

import com.serega.practice.module2.task1.daos.DaoSkills;
import com.serega.practice.module2.task1.entity.Skill;
import com.serega.practice.module2.task1.exceptions.WrongId;

public class SkillsControllerImpl implements SkillsController {

    private DaoSkills daoSkills;

    public SkillsControllerImpl(DaoSkills daoSkills) {
        this.daoSkills = daoSkills;
    }

    public boolean create(String nameSkill) {
        daoSkills.create(nameSkill);
        return true;
    }

    public Skill read(int id) throws WrongId {
        if (daoSkills.getAll().stream().map(Skill::getId).noneMatch(integer -> integer == id))
            throw new WrongId("Навыка с таким ID не существует, повторите ввод!");
        return daoSkills.read(id);
    }

    public boolean update(int id, String newName) throws WrongId {
        if (daoSkills.getAll().stream().map(Skill::getId).noneMatch(integer -> integer == id))
            throw new WrongId("Навыка с таким ID не существует, повторите ввод!");
        daoSkills.update(id, newName);
        return true;
    }

    public boolean delete(int id) throws WrongId {
        if (daoSkills.getAll().stream().map(Skill::getId).noneMatch(integer -> integer == id))
            throw new WrongId("Навыка с таким ID не существует, повторите ввод!");
        daoSkills.delete(id);
        return true;
    }

    public void showAllSkills() {
        daoSkills.getAll().forEach(System.out::println);
    }
}
