drop database if exists `Acme-Six-Pack`;
create database `Acme-Six-Pack`;
grant select, insert, update, delete
on `Acme-Six-Pack`.* to 'acme-user'@'%';
grant select, insert, update, delete, create, drop, references, index, alter, create temporary tables, lock tables, create view, create routine, alter routine, execute, trigger, show view
on `Acme-Six-Pack`.* to 'acme-manager'@'%';