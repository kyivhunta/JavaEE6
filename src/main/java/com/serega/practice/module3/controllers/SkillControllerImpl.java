package com.serega.practice.module3.controllers;

import com.serega.practice.module2.task1.exceptions.ThisSkillAlreadyExisted;
import com.serega.practice.module2.task1.exceptions.WrongId;
import com.serega.practice.module3.dao.CommonDao;
import com.serega.practice.module3.entity.Skill;

import java.util.List;


public class SkillControllerImpl implements SkillsController {

    CommonDao<Skill, Integer> skillDao;

    public SkillControllerImpl(CommonDao<Skill, Integer> skillDao) {
        this.skillDao = skillDao;
    }

    public boolean create(String nameSkill) throws ThisSkillAlreadyExisted {
        if (skillDao.getAll().stream().anyMatch(skill -> skill.getSkill().equals(nameSkill)))
            throw new ThisSkillAlreadyExisted("Такой навык уже существует");
        Skill skill = new Skill();
        skill.setSkill(nameSkill);

        skillDao.create(skill);
        return true;
    }

    public Skill read(int id) throws WrongId {
        if (skillDao.getAll().stream().noneMatch(skill -> skill.getId() == id))
            throw new WrongId("Навыка с таким айди не существует");

        return skillDao.read(id);
    }

    @Override
    public boolean update(int id, String newName) throws WrongId {
        if (skillDao.getAll().stream().noneMatch(skill -> skill.getId() == id))
            throw new WrongId("Навыка с таким айди не существует");
        Skill skill = read(id);
        skill.setSkill(newName);

        skillDao.update(skill);
        return false;
    }

    @Override
    public boolean delete(int id) throws WrongId {
        if (skillDao.getAll().stream().noneMatch(skill -> skill.getId() == id))
            throw new WrongId("Навыка с таким айди не существует");

        Skill skill = read(id);

        skillDao.delete(skill);

        return true;
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillDao.getAll();
    }
}
