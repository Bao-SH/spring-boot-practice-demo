# Getting Started

## config local mysql
### run the mysql from docker
```
docker pull mysql
docker run --name mysql -e MYSQL_ROOT_PASSWORD=password -d -p 3306:3306 mysql:latest
docker exec -it mysql bin/bash
```
### create user and database
https://spring.io/guides/gs/accessing-data-mysql/
```
mysql -u -p
create database db_example; -- Creates the new database
create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user
grant all on db_example.* to 'springuser'@'%'; -- Gives all privileges to the new user on the newly created database
```

