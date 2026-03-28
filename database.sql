CREATE DATABASE StudentDB;
GO

USE StudentDB;

CREATE TABLE students_data (
    id INT PRIMARY KEY IDENTITY(1,1),
    name VARCHAR(100),
    age INT,
    sub1 INT,
    sub2 INT,
    sub3 INT,
    sub4 INT,
    sub5 INT,
    total INT,
    percentage FLOAT,
    status VARCHAR(20)
);

select * from students_data

select name , age , total , status from students_data
where percentage > 90