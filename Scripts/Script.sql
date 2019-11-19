/*
-- DROP TABLES AND SEQUENCES BEFORE START DATABASE:
DROP SEQUENCE IF EXISTS SEQ_PERMISSION;
DROP SEQUENCE IF EXISTS SEQ_USER;
DROP SEQUENCE IF EXISTS SEQ_ROLE;

DROP TABLE IF EXISTS ROLE_PERMISSION;
DROP TABLE IF EXISTS USER_ROLE;
DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS `ROLE`;
DROP TABLE IF EXISTS PERMISSION;

-- ROLES: 
CREATE SEQUENCE IF NOT EXISTS SEQ_ROLE MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE TABLE IF NOT EXISTS `ROLE` (
	ID INT(19) NOT NULL DEFAULT NEXT VALUE FOR SEQ_ROLE COMMENT 'Entity ID, Primary Key',
	CODE VARCHAR(50) NOT NULL COMMENT 'Role Code to grant',
	DESCRIPTION VARCHAR(250) COMMENT 'Role Description about what this role grant on user profile',
	CREATED_AT DATE NOT NULL COMMENT 'Date when Role is created',
	UPDATED_AT DATE COMMENT 'Date when Role is updated',
	DELETED_AT DATE COMMENT 'Date when Role is deleted',
	
	CONSTRAINT PK_ROLE PRIMARY KEY (ID)
) COMMENT='Role detail for access permissions';

-- STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION
--SELECT @@global.sql_mode;
--ORIGINAL:
--SET SESSION sql_mode='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
--TEST:
--SET SESSION sql_mode='STRICT_ALL_TABLES,ALLOW_INVALID_DATES,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';


INSERT INTO `ROLE`(CODE,DESCRIPTION,CREATED_AT) VALUES('ADMINISTRATION_ROLE_ACCESS','This role grant all resources of application.','2019/10/27');
INSERT INTO `ROLE`(CODE,DESCRIPTION,CREATED_AT) VALUES('USER_ROLE_ACCESS','This role grant only User resources access. Just limited acctions.','2019/10/27');
INSERT INTO `ROLE`(CODE,DESCRIPTION,CREATED_AT) VALUES('GUEST_ROLE_ACCESS','This role grant only Guest resources access. Just see some resources.','2019/10/27');
--SELECT * FROM `ROLE`;
COMMIT;

-- ROLES: 
CREATE SEQUENCE IF NOT EXISTS SEQ_PERMISSION MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE TABLE IF NOT EXISTS PERMISSION (
	ID INT(19) NOT NULL DEFAULT NEXT VALUE FOR SEQ_PERMISSION COMMENT 'Entity ID, Primary Key',
	CODE VARCHAR(50) COMMENT 'Role Code to grant',
	DESCRIPTION VARCHAR(250) COMMENT 'AccessGrant Description about what role can do on application',
	CREATED_AT DATE NOT NULL COMMENT 'Date when granted access is created',
	UPDATED_AT DATE COMMENT 'Date when granted access is updated',
	DELETED_AT DATE COMMENT 'Date when granted access is deleted',
	
	CONSTRAINT PK_ACCESS_GRANT PRIMARY KEY (ID)
) COMMENT='Role detail for access permissions';

INSERT INTO PERMISSION(CODE,DESCRIPTION,CREATED_AT) VALUES('WRITE_ACCESS','This permission grant write application resources.','2019/10/27');
INSERT INTO PERMISSION(CODE,DESCRIPTION,CREATED_AT) VALUES('READ_ACCESS','This permission grant read application resources.','2019/10/27');
INSERT INTO PERMISSION(CODE,DESCRIPTION,CREATED_AT) VALUES('UPDATE_ACCESS','permission grant delete application resources.','2019/10/27');
INSERT INTO PERMISSION(CODE,DESCRIPTION,CREATED_AT) VALUES('DELETE_ACCESS','permission grant delete application resources.','2019/10/27');

COMMIT;


-- ROLE_PERMISSION TABLE RELATIONSHIP
CREATE TABLE IF NOT EXISTS ROLE_PERMISSION (
	ROLE_ID INT(19) NOT NULL COMMENT 'Reference to Role Primary Key',
	GRANT_ID INT(19) NOT NULL COMMENT 'Reference to AccessGrant Primary Key',
	
	CONSTRAINT FK_ROLE_ACCESS FOREIGN KEY (ROLE_ID) REFERENCES `ROLE`(ID),
	CONSTRAINT FK_ACCESS_GRANT_ACCESS FOREIGN KEY (GRANT_ID) REFERENCES PERMISSION(ID),
	CONSTRAINT PK_ROLE_ACCESS_GRANT PRIMARY KEY (ROLE_ID, GRANT_ID)
) COMMENT='Table relationship to User and Role';

INSERT INTO ROLE_PERMISSION(GRANT_ID,ROLE_ID) VALUES((SELECT ID FROM PERMISSION WHERE CODE = 'WRITE_ACCESS'), (SELECT ID FROM `ROLE` WHERE CODE = 'ADMINISTRATION_ROLE_ACCESS'));
INSERT INTO ROLE_PERMISSION(GRANT_ID,ROLE_ID) VALUES((SELECT ID FROM PERMISSION WHERE CODE = 'READ_ACCESS'), (SELECT ID FROM `ROLE` WHERE CODE = 'ADMINISTRATION_ROLE_ACCESS'));
INSERT INTO ROLE_PERMISSION(GRANT_ID,ROLE_ID) VALUES((SELECT ID FROM PERMISSION WHERE CODE = 'UPDATE_ACCESS'), (SELECT ID FROM `ROLE` WHERE CODE = 'ADMINISTRATION_ROLE_ACCESS'));
INSERT INTO ROLE_PERMISSION(GRANT_ID,ROLE_ID) VALUES((SELECT ID FROM PERMISSION WHERE CODE = 'DELETE_ACCESS'), (SELECT ID FROM `ROLE` WHERE CODE = 'ADMINISTRATION_ROLE_ACCESS'));

INSERT INTO ROLE_PERMISSION(GRANT_ID,ROLE_ID) VALUES((SELECT ID FROM PERMISSION WHERE CODE = 'WRITE_ACCESS'), (SELECT ID FROM `ROLE` WHERE CODE = 'USER_ROLE_ACCESS'));
INSERT INTO ROLE_PERMISSION(GRANT_ID,ROLE_ID) VALUES((SELECT ID FROM PERMISSION WHERE CODE = 'READ_ACCESS'), (SELECT ID FROM `ROLE` WHERE CODE = 'USER_ROLE_ACCESS'));
INSERT INTO ROLE_PERMISSION(GRANT_ID,ROLE_ID) VALUES((SELECT ID FROM PERMISSION WHERE CODE = 'UPDATE_ACCESS'), (SELECT ID FROM `ROLE` WHERE CODE = 'USER_ROLE_ACCESS'));

INSERT INTO ROLE_PERMISSION(GRANT_ID,ROLE_ID) VALUES((SELECT ID FROM PERMISSION WHERE CODE = 'READ_ACCESS'), (SELECT ID FROM `ROLE` WHERE CODE = 'GUEST_ROLE_ACCESS'));

COMMIT;

-- USERS:
CREATE SEQUENCE IF NOT EXISTS SEQ_USER MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE TABLE IF NOT EXISTS `USER` (
	ID INT(19) NOT NULL DEFAULT NEXT VALUE FOR SEQ_USER COMMENT 'Entity ID, Primary Key',
	USERNAME VARCHAR(50) NOT NULL COMMENT 'Username to login',
	PASSWORD VARCHAR(50) NOT NULL COMMENT 'Password to login',
	ACTIVE VARCHAR(1) NOT NULL DEFAULT('1') COMMENT 'State of user, default 1 (Active)',
	--ROLE_ID INT(19) NOT NULL,
	CREATED_AT DATE NOT NULL COMMENT 'Date when User is created',
	UPDATED_AT DATE COMMENT 'Date when User is updated',
	DELETED_AT DATE COMMENT 'Date when User is deleted',
	
	CONSTRAINT PK_USER PRIMARY KEY (ID),
	--CONSTRAINT FK_ROLE_USER FOREIGN KEY (ROLE_ID) REFERENCES `ROLE`(ID)
) COMMENT='User details for login on application';

--INSERT INTO `USER` (USERNAME,PASSWORD,ACTIVE,CREATED_AT,ROLE_ID) VALUES('admin', 'test', '1','2019/10/27',(SELECT ID FROM `ROLE` WHERE CODE = 'ADMINISTRATION_ROLE_ACCESS'));
--INSERT INTO `USER` (USERNAME,PASSWORD,ACTIVE,CREATED_AT,ROLE_ID) VALUES('user', 'userpass', '1','2019/10/27',(SELECT ID FROM `ROLE` WHERE CODE = 'USER_ROLE_ACCESS'));
--INSERT INTO `USER` (USERNAME,PASSWORD,ACTIVE,CREATED_AT,ROLE_ID) VALUES('guest', 'guestpass', '1','2019/10/27',(SELECT ID FROM `ROLE` WHERE CODE = 'GUEST_ROLE_ACCESS'));

INSERT INTO `USER` (USERNAME,PASSWORD,ACTIVE,CREATED_AT) VALUES('admin', 'test', '1','2019/10/27');
INSERT INTO `USER` (USERNAME,PASSWORD,ACTIVE,CREATED_AT) VALUES('user', 'userpass', '1','2019/10/27');
INSERT INTO `USER` (USERNAME,PASSWORD,ACTIVE,CREATED_AT) VALUES('guest', 'guestpass', '1','2019/10/27');


COMMIT;

-- USER_ROLE_RELATIONSHIP
CREATE TABLE IF NOT EXISTS USER_ROLE (
	USER_ID INT(19) NOT NULL,
	ROLE_ID INT(19) NOT NULL,
	
	CONSTRAINT FK_ROLE_USER_ROLE FOREIGN KEY (ROLE_ID) REFERENCES `ROLE`(ID)
	CONSTRAINT FK_USER_USER_ROLE FOREIGN KEY (ROLE_ID) REFERENCES `ROLE`(ID)
) COMMENT='User-Role relationship table';

INSERT INTO USER_ROLE (USER_ID,ROLE_ID) VALUES((SELECT ID FROM `USER` WHERE USERNAME = 'admin'),(SELECT ID FROM `ROLE` WHERE CODE = 'ADMINISTRATION_ROLE_ACCESS'));
INSERT INTO USER_ROLE (USER_ID,ROLE_ID) VALUES((SELECT ID FROM `USER` WHERE USERNAME = 'user'),(SELECT ID FROM `ROLE` WHERE CODE = 'USER_ROLE_ACCESS'));
INSERT INTO USER_ROLE (USER_ID,ROLE_ID) VALUES((SELECT ID FROM `USER` WHERE USERNAME = 'user'),(SELECT ID FROM `ROLE` WHERE CODE = 'ADMINISTRATION_ROLE_ACCESS'));


-- QUERY TO SEE DATA INSERTED ON TABLES:
SELECT U.USERNAME, U.ACTIVE, R.CODE, AG.CODE FROM `USER` U
INNER JOIN USER_ROLE UR ON UR.USER_ID = U.ID
INNER JOIN `ROLE` R ON UR.ROLE_ID = R.ID
INNER JOIN ROLE_PERMISSION RAG ON R.ID = RAG.ROLE_ID
INNER JOIN PERMISSION AG ON RAG.GRANT_ID = AG.ID;
*/



select user0_.id as id1_1_, user0_.active as active5_1_, user0_.password as password6_1_, user0_.username as username7_1_ from `USER`
user0_ where user0_.username = 'admin';


select 
	user0_.id as id1_1_, 
	user0_.active as active2_1_, 
	user0_.password as password3_1_, 
	user0_.username as username4_1_ 
from `USER` user0_ 
where user0_.username='admin';



select user0_.ID as ID1_1_, user0_.ACTIVE as ACTIVE2_1_, user0_.PASSWORD as PASSWORD3_1_, user0_.USERNAME as USERNAME4_1_ from USER user0_ where user0_.USERNAME='admin';
select roles0_.USER_ID as USER_ID1_2_0_, roles0_.ROLE_ID as ROLE_ID2_2_0_, role1_.ID as ID1_0_1_, role1_.CODE as CODE2_0_1_, role1_.DESCRIPTION as DESCRIPT3_0_1_ from USER_ROLE roles0_ inner join ROLE role1_ on roles0_.ROLE_ID=role1_.ID where roles0_.USER_ID=1;
select user0_.ID as ID1_1_, user0_.ACTIVE as ACTIVE2_1_, user0_.PASSWORD as PASSWORD3_1_, user0_.USERNAME as USERNAME4_1_ from USER user0_ where user0_.USERNAME='admin';
select user0_.ID as ID1_1_, user0_.ACTIVE as ACTIVE2_1_, user0_.PASSWORD as PASSWORD3_1_, user0_.USERNAME as USERNAME4_1_ from USER user0_ where user0_.USERNAME='admin';
select roles0_.USER_ID as USER_ID1_2_0_, roles0_.ROLE_ID as ROLE_ID2_2_0_, role1_.ID as ID1_0_1_, role1_.CODE as CODE2_0_1_, role1_.DESCRIPTION as DESCRIPT3_0_1_ from USER_ROLE roles0_ inner join ROLE role1_ on roles0_.ROLE_ID=role1_.ID where roles0_.USER_ID=1;


SELECT 1 FROM PAYMENT
WHERE ID_CLIENT = 3


select employee0_.ID as ID1_1_, employee0_.ID_BOSS_EMPLOYEE as ID_BOSS_8_1_, employee0_.EMAIL as EMAIL2_1_, employee0_.EXTENSION as EXTENSIO3_1_, employee0_.FIRSTNAME as FIRSTNAM4_1_, employee0_.LASTNAME as LASTNAME5_1_, employee0_.NAME as NAME6_1_, employee0_.ID_OFFICE as ID_OFFIC9_1_, employee0_.WORKSTATION as WORKSTAT7_1_ from EMPLOYEE employee0_ left outer join EMPLOYEE employee1_ on employee0_.ID_BOSS_EMPLOYEE=employee1_.ID where employee0_.ID=? and employee1_.ID=?



SELECT * FROM PAYMENT WHERE ID_CLIENT = 38;


