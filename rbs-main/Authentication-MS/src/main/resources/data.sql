create table if not exists appuser(userid varchar(25),username varchar(25),password varchar(25),role varchar(25));
insert into appuser (userid,username,password,role) values  ('EMP101','Yuvaraj','pass','Employee');
insert into appuser (userid,username,password,role) values ('CUST101','Vijay','pass','Customer');
insert into appuser (userid,username,password,role) values ('CUST102','Nada','pass','Customer');
insert into appuser (userid,username,password,role) values ('CUST103','Priya','pass','Customer');