-- DROP DATABASE IF EXISTS garden;
-- CREATE DATABASE IF NOT EXISTS garden CHARACTER SET utf8mb4;

USE gardendb;


DROP SEQUENCE IF EXISTS SEQ_OFFICE;
DROP SEQUENCE IF EXISTS SEQ_EMPLOYEE;
DROP SEQUENCE IF EXISTS SEQ_PRODUCT_RANGE;
DROP SEQUENCE IF EXISTS SEQ_CLIENT;
DROP SEQUENCE IF EXISTS SEQ_ORDER_REGISTRATION;
DROP SEQUENCE IF EXISTS SEQ_PRODUCT;
DROP SEQUENCE IF EXISTS SEQ_PAYMENT;


DROP TABLE IF EXISTS ORDER_DETAIL;
DROP TABLE IF EXISTS PRODUCT;
DROP TABLE IF EXISTS PRODUCT_RANGE;
DROP TABLE IF EXISTS ORDER_REGISTRATION;
DROP TABLE IF EXISTS PAYMENT;
DROP TABLE IF EXISTS CLIENT;
DROP TABLE IF EXISTS EMPLOYEE;
DROP TABLE IF EXISTS OFFICE;



CREATE SEQUENCE IF NOT EXISTS SEQ_OFFICE MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE TABLE OFFICE (
    ID INT(20) UNSIGNED NOT NULL DEFAULT NEXT VALUE FOR SEQ_OFFICE COMMENT 'Entity ID, Primary Key',
    OFFICE_CODE varchar(10) NOT NULL,
    CITY VARCHAR(30) NOT NULL,
    COUNTRY VARCHAR(50) NOT NULL,
    REGION VARCHAR(50) DEFAULT NULL,
    ZIP_CODE VARCHAR(10) NOT NULL,
    PHONE VARCHAR(20) NOT NULL,
    LINE_DIRECTION1 VARCHAR(50) NOT NULL,
    LINE_DIRECTION2 VARCHAR(50) DEFAULT NULL,

    CONSTRAINT PRIMARY KEY (ID)
);


CREATE SEQUENCE IF NOT EXISTS SEQ_EMPLOYEE MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE TABLE EMPLOYEE (
    ID INT(20) UNSIGNED NOT NULL DEFAULT NEXT VALUE FOR SEQ_EMPLOYEE COMMENT 'Entity ID, Primary Key',
    NAME VARCHAR(50) NOT NULL,
    FIRSTNAME VARCHAR(50) NOT NULL,
    LASTNAME VARCHAR(50) DEFAULT NULL,
    EXTENSION VARCHAR(10) NOT NULL,
    EMAIL VARCHAR(100) NOT NULL,
    FK_OFFICE INT(20) UNSIGNED NOT NULL,
    FK_ID_BOSS_EMPLOYEE INT(20) UNSIGNED DEFAULT NULL,
    WORKSTATION VARCHAR(50) DEFAULT NULL,

    CONSTRAINT PRIMARY KEY (ID),
    CONSTRAINT FOREIGN KEY (FK_OFFICE) REFERENCES OFFICE (ID),
    CONSTRAINT FOREIGN KEY (FK_ID_BOSS_EMPLOYEE) REFERENCES EMPLOYEE (ID)
);


CREATE SEQUENCE IF NOT EXISTS SEQ_PRODUCT_RANGE MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE TABLE PRODUCT_RANGE (
    ID INT(20) UNSIGNED NOT NULL DEFAULT NEXT VALUE FOR SEQ_PRODUCT_RANGE COMMENT 'Entity ID, Primary Key',
    CODE varchar(50) NOT NULL,
    TXT_DESCRIPTION TEXT,
    HTML_DESCRIPTION TEXT,
    IMAGE BLOB DEFAULT NULL,

    CONSTRAINT PRIMARY KEY (ID)
);


CREATE SEQUENCE IF NOT EXISTS SEQ_CLIENT MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE TABLE CLIENT (
    ID INT(20) UNSIGNED NOT NULL DEFAULT NEXT VALUE FOR SEQ_CLIENT COMMENT 'Entity ID, Primary Key',
    NAME VARCHAR(50) NOT NULL,
    CONTACT_NAME VARCHAR(30) DEFAULT NULL,
    CONTACT_FIRST_NAME VARCHAR(30) DEFAULT NULL,
    PHONE VARCHAR(15) NOT NULL,
    FAX VARCHAR(15) NOT NULL,
    LINE_DIRECTION1 VARCHAR(50) NOT NULL,
    LINE_DIRECTION2 VARCHAR(50) DEFAULT NULL,
    CITY VARCHAR(50) NOT NULL,
    REGION VARCHAR(50) DEFAULT NULL,
    COUNTRY VARCHAR(50) DEFAULT NULL,
    ZIP_CODE VARCHAR(10) DEFAULT NULL,
    FK_SALES_REP_EMPLOYEE INT(20) UNSIGNED DEFAULT NULL,
    CREDIT_LIMIT NUMERIC(15,2) DEFAULT NULL,

    CONSTRAINT PRIMARY KEY (ID),
    CONSTRAINT FOREIGN KEY (FK_SALES_REP_EMPLOYEE) REFERENCES EMPLOYEE (ID)
);


CREATE SEQUENCE IF NOT EXISTS SEQ_ORDER_REGISTRATION MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE TABLE ORDER_REGISTRATION (
    ID INT(20) UNSIGNED NOT NULL DEFAULT NEXT VALUE FOR SEQ_ORDER_REGISTRATION COMMENT 'Entity ID, Primary Key',
    ORDER_DATE DATE NOT NULL,
    EXPECTED_DATE DATE NOT NULL,
    DELIVERY_DATE DATE DEFAULT NULL,
    ORDER_STATE VARCHAR(15) NOT NULL,
    COMMENT TEXT,
    FK_CLIENT INT(20) UNSIGNED NOT NULL,

    CONSTRAINT PRIMARY KEY (ID),
    CONSTRAINT FOREIGN KEY (FK_CLIENT) REFERENCES CLIENT (ID)
);


CREATE SEQUENCE IF NOT EXISTS SEQ_PRODUCT MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE TABLE PRODUCT (
    ID INT(20) UNSIGNED NOT NULL DEFAULT NEXT VALUE FOR SEQ_PRODUCT COMMENT 'Entity ID, Primary Key',
    CODE varchar(15) NOT NULL,
    NAME VARCHAR(70) NOT NULL,
    FK_PRODUCT_RANGE INT(20) UNSIGNED NOT NULL,
    DIMENSIONS VARCHAR(25) NULL,
    PROVIDER VARCHAR(50) DEFAULT NULL,
    DESCRIPTION text NULL,
    STOCK SMALLINT NOT NULL,
    SALES_PRICE NUMERIC(15,2) NOT NULL,
    PROVIDER_PRICE NUMERIC(15,2) DEFAULT NULL,

    CONSTRAINT PRIMARY KEY (ID),
    CONSTRAINT FOREIGN KEY (FK_PRODUCT_RANGE) REFERENCES PRODUCT_RANGE (ID)
);


CREATE TABLE ORDER_DETAIL (
    FK_ORDER INT(20) UNSIGNED NOT NULL,
    FK_PRODUCT INT(20) UNSIGNED NOT NULL,
    QUANTITY INTEGER NOT NULL,
    UNIT_PRICE NUMERIC(15,2) NOT NULL,
    LINE_NUMBER SMALLINT NOT NULL,

    CONSTRAINT PRIMARY KEY (FK_ORDER, FK_PRODUCT),
    CONSTRAINT FOREIGN KEY (FK_ORDER) REFERENCES ORDER_REGISTRATION (ID),
    CONSTRAINT FOREIGN KEY (FK_PRODUCT) REFERENCES PRODUCT (ID)
);


CREATE SEQUENCE IF NOT EXISTS SEQ_PAYMENT MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE TABLE PAYMENT (
    FK_CLIENT INT(20) UNSIGNED NOT NULL,
    PAYMENT_TYPE VARCHAR(40) NOT NULL,
    ID_TRANSACTION VARCHAR(50) NOT NULL,
    PAYMENT_DATE DATE NOT NULL,
    TOTAL NUMERIC(15,2) NOT NULL,

    CONSTRAINT PRIMARY KEY (FK_CLIENT, ID_TRANSACTION),
    CONSTRAINT FOREIGN KEY (FK_CLIENT) REFERENCES CLIENT (ID)
);

COMMIT;
