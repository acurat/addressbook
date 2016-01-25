CREATE DATABASE contacts;


USE contacts;
CREATE TABLE Person
(
Id int IDENTITY(1,1) PRIMARY KEY,
LastName varchar(50) NOT NULL,
FirstName varchar(50)
);

CREATE TABLE Phone
(
Id int IDENTITY(1,1) PRIMARY KEY,
Number varchar(20) NOT NULL,
Type varchar(10) NOT NULL,
PersonID int,
PrimaryPhone BIT DEFAULT 0,
CONSTRAINT FK_PhoneID_PersonID FOREIGN KEY (PersonID) 
    REFERENCES Person (Id)
);


CREATE TABLE Address
(
Id int PRIMARY KEY,
Address1 varchar(25),
Address2 varchar(25),
City varchar(15),
State varchar(20),
Country varchar(20),
Zip varchar(10)
);


CREATE TABLE Email
(
Id int IDENTITY(1,1) PRIMARY KEY,
Address varchar(100) NOT NULL,
PersonID int,
PrimaryEmail BIT DEFAULT 0,
CONSTRAINT FK_EmailID_PersonID FOREIGN KEY (PersonID) 
    REFERENCES Person (Id)
);


Insert into Person values ('Damon', 'Matt')
Insert into Person values ('Clooney', 'George')
Insert into Person values ('DiCaprio', 'Leonardo')
Insert into Person values ('Cruise', 'Tom')
Insert into Person values ('Pitt', 'Brad')
Insert into Person values ('Depp', 'Johnny')


Insert into Address values (1,'123 Main St','Apt 1','Beverly Hills','CA','USA','12345')
Insert into Address values (2,'123 Second St','Apt 2','Motherly Hills','MO','USA','12345')
Insert into Address values (3,'123 Third St','Apt 3','Brotherly Hills','WA','USA','12345')
Insert into Address values (4,'123 Fourth St','Apt 4','Fatherly Hills','NY','USA','12345')
Insert into Address values (5,'123 Fifth St','Apt 5','Foo Hills','MI','USA','12345')
Insert into Address values (6,'123 Sixth St','Apt 6','Hmmm Hills','IL','USA','12345')


Insert into Phone values ('+14255058255', 'Cell', 1, 1)
Insert into Phone values ('0987564884', 'Cell', 2, 1)
Insert into Phone values ('3727378+9', 'Cell', 3, 1)
Insert into Phone values ('838373x822', 'Cell', 4, 1)
Insert into Phone values ('123456', 'Cell', 5, 1)
Insert into Phone values ('*228P020020', 'Cell', 5, 0)


Insert into Phone values ('911', 'Cell', 1, 0)
Insert into Phone values ('029929', 'Cell', 1, 0)

Insert into Phone values ('222', 'Cell', 3, 0)
Insert into Phone values ('8938939293', 'Cell', 2, 0)


Insert into Email values ('foo@bar.com', 1, 1)
Insert into Email values ('bar@bar.com', 2, 1)
Insert into Email values ('foo@foo.com', 3, 1)
Insert into Email values ('bar@foo.co.in', 4, 1)
Insert into Email values ('foo@bar.org', 5, 1)
Insert into Email values ('foo@bar.edu', 6, 1)

Insert into Email values ('foo_bar@bar.com', 1, 0)















