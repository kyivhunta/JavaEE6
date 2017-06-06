select project.name, idProject, sum(salary) from homework.developer, homework.project 
where idProject = Project_idProject
 group by idProject having sum(salary) > 0
  order by sum(salary) desc ;
