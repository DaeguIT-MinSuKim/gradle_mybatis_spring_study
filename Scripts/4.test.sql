SELECT *
  FROM USER_TABLES;
  
 
SELECT STUD_ID AS studId, NAME, EMAIL, DOB, PHONE 
  FROM STUDENTS
 WHERE STUD_ID = 1;
 
select stud_id, name, email, dob,
               substr(phone, 1, 3) as f, 
               substr(phone, 5, 3) as m, 
               substr(phone, 9, 4) as l
  from students 
 where stud_id = 1;
 
-- 일대일 매핑
SELECT STUD_ID, NAME, EMAIL, PHONE, DOB, A.ADDR_ID, STREET, CITY, STATE, ZIP, COUNTRY
  FROM STUDENTS S JOIN ADDRESSES A ON S.ADDR_ID=A.ADDR_ID
 WHERE STUD_ID = 1; 






