

-- 어드민  테이블
CREATE TABLE "ADMINUSER"(
   ADMINUSERNUM NUMBER(10),
   ADMINUSERID VARCHAR2(20),
   ADMINUSERPW VARCHAR2(20),
   ADMINUSEREMAIL VARCHAR2(30),
   ADMINUSERNAME VARCHAR2(30),
   CONSTRAINTS ADMINUSER_PK PRIMARY KEY(ADMINUSERNUM)
);

-- 어드민 시퀀스 
CREATE SEQUENCE ADMINUSER_SEQ
INCREMENT BY 1
START WITH 1
MINVALUE 1
MAXVALUE 100000;


-- 상품 시퀀스
CREATE SEQUENCE PRODUCT_SEQ
INCREMENT BY 1
START WITH 1
MINVALUE 1
MAXVALUE 10000000;

-- 상품 테이블
  CREATE TABLE "HR"."PRODUCT" 
   (	"PRODUCT_NUM" NUMBER, 
	"CATECHORY_DIV_ID2" NUMBER, 
	"PRODUCT_BRAND" VARCHAR2(500 BYTE), 
	"PRODUCT_TITLE" VARCHAR2(500 BYTE), 
	"PRODUCT_EXPLANATION" VARCHAR2(2000 BYTE), 
	"PRODUCT_PRICE" NUMBER, 
	"PRODUCT_DATE" DATE, 
	"PRODUCT_IMAGE" VARCHAR2(500 BYTE), 
	 PRIMARY KEY ("PRODUCT_NUM")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 CONSTRAINT "PRODUCT_FK" FOREIGN KEY ("CATECHORY_DIV_ID2")
	  REFERENCES "HR"."CATECHORY2" ("CATECHORY_DIV_ID2") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

 
 
 
 DROP SEQUENCE ADMINUSER_SEQ;
DROP TABLE PRODUCT;
DROP TABLE "USERS";
DROP TABLE "MEMBER";
DROP TABLE REPORT_FILES;
DROP TABLE P_REVIEW;

  