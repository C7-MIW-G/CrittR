-- First drop any existing database and user
DROP DATABASE IF EXISTS crittrDB;
DROP USER IF EXISTS 'crittR'@'%';

create database crittrDB; -- Creates the new database
create user 'crittR'@'%' identified by 'crittrPW'; -- Creates the user
grant all on crittrDB.* to 'crittR'@'%'; -- Gives all privileges to the new user on the newly created database
