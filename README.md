# sql-query-code
this is java project which support all basic SQL query.
to run this pjroject run Test.java file.
this project explain how sql work.
list of queries-

#1. show databases; 
    show all the database.
    
#2. create database test;
    it create a test database in Database Folder.
    
#3. use test;
    default database is nehra, so it change current database as test.
    
#4. clear
    create the console.
#4. select tables from nehra;
    here the default sql query is (select * from tab) i cnaged the query.
    it shows all the tables in nehra database.
#5. select * from student;
    showing all data in student table.
#6 desc student;
    describe the structure of student table.
DataType Support:
    String, int, float, char, boolean, Date
#7. create table employee ( Id int, name String, salary float, gender char, status boolean, dob Date );
    create employee table.
#8. insert into employee values ( 1, 'virender', 1234.45, 'M', true, '10-10-89' );
    Date format 'dd-mm-yy', year should be in two digits. date should be in single quate.
#9. select Id, name from employee;
    fetchin only Id and name column from employee.
#10. update employee set status = false where Id=1;
    update table where Id = 1
#11. delete from employee where Id=1;
    delete row where Id=1
#12. drop employee;
    delete table employee.
#13 exit
