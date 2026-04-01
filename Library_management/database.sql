CREATE DATABASE LibraryDBB;
GO

USE LibraryDBB;
GO

CREATE TABLE Books (
    book_id INT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    quantity INT
);
GO

select * from Books;