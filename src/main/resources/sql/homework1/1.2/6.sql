select avg(salary), project.name from homework.developer, homework.project
where developer.Project_idProject = (  
	select project.idProject from homework.project
	 order by cost limit 1  );