drop database if exists `Sample-Project-1.5`;
create database `Sample-Project-1.5`;
grant select, insert, update, delete
on `Sample-Project-1.5`.* to 'acme-user'@'%';
grant select, insert, update, delete, create, drop, references, index, alter, create temporary tables, lock tables, create view, create routine, alter routine, execute, trigger, show view
on `Sample-Project-1.5`.* to 'acme-manager'@'%';