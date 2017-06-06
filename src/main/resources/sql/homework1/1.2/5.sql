SELECT customer.name, sum(cost), project.Company_idCompany FROM homework.project, homework.customer
 where project.Company_idCompany = 2 and project.Customer_idCustomer = customer.idCustomer 
 group by project.Customer_idCustomer
  order by sum(cost) ;
