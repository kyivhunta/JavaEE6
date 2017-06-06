select sum(salary) from homework.developer where id in (
	select developer_has_skill.Developer_id from homework.developer_has_skill where Skill_idSkill = ( 
	 select skill.idSkill from homework.skill where skill = 'Java'   ));
