CREATE DATABASE LibraryDB;
GO

USE LibraryDB;
GO

CREATE TABLE Books (
    BookID INT PRIMARY KEY,
    Title VARCHAR(100),
    Author VARCHAR(100),
    Quantity INT,
    Available INT
);
GO

CREATE TABLE IssuedBooks (
    IssueID INT IDENTITY(1,1) PRIMARY KEY,
    BookID INT,
    MemberID INT,
    IssueDate DATE,
    ReturnDate DATE NULL,
    Status VARCHAR(20)
);
GO

SELECT local_tcp_port 
FROM sys.dm_exec_connections 
WHERE session_id = @@SPID;

select * from Books
EXEC xp_readerrorlog 0, 1, N'Server is listening on';

CREATE LOGIN libraryuser WITH PASSWORD = 'Library@123';
GO

USE LibraryDB;
GO

CREATE USER libraryuser FOR LOGIN libraryuser;
GO

ALTER ROLE db_owner ADD MEMBER libraryuser;
GO