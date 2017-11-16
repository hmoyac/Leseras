--------------------------------------------------------
--  DDL for Table USERS
--------------------------------------------------------

  CREATE TABLE "SPRING_SECURITY"."USERS" 
   (	"USERNAME" VARCHAR2(50 BYTE), 
	"PASSWORD" VARCHAR2(50 BYTE), 
	"ENABLED" NUMBER(*,0) DEFAULT 0
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table USERS
--------------------------------------------------------

  ALTER TABLE "SPRING_SECURITY"."USERS" ADD CONSTRAINT "USERS_PK" PRIMARY KEY ("USERNAME")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "SPRING_SECURITY"."USERS" MODIFY ("ENABLED" NOT NULL ENABLE);
  ALTER TABLE "SPRING_SECURITY"."USERS" MODIFY ("PASSWORD" NOT NULL ENABLE);
  ALTER TABLE "SPRING_SECURITY"."USERS" MODIFY ("USERNAME" NOT NULL ENABLE);


--################################################################################################################
--################################################################################################################
--################################################################################################################


--------------------------------------------------------
--  DDL for Table GROUPS
--------------------------------------------------------

  CREATE TABLE "SPRING_SECURITY"."GROUPS" 
   (	"ID" NUMBER(8,2), 
	"GROUP_NAME" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C007091
--------------------------------------------------------

  CREATE UNIQUE INDEX "SPRING_SECURITY"."SYS_C007091" ON "SPRING_SECURITY"."GROUPS" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table GROUPS
--------------------------------------------------------

  ALTER TABLE "SPRING_SECURITY"."GROUPS" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "SPRING_SECURITY"."GROUPS" MODIFY ("GROUP_NAME" NOT NULL ENABLE);


--################################################################################################################
--################################################################################################################
--################################################################################################################


--------------------------------------------------------
--  DDL for Table GROUP_MEMBERS
--------------------------------------------------------

  CREATE TABLE "SPRING_SECURITY"."GROUP_MEMBERS" 
   (	"ID" NUMBER(8,2), 
	"USERNAME" VARCHAR2(50 BYTE), 
	"GROUP_ID" NUMBER(8,2)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C007097
--------------------------------------------------------

  CREATE UNIQUE INDEX "SPRING_SECURITY"."SYS_C007097" ON "SPRING_SECURITY"."GROUP_MEMBERS" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table GROUP_MEMBERS
--------------------------------------------------------

  ALTER TABLE "SPRING_SECURITY"."GROUP_MEMBERS" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "SPRING_SECURITY"."GROUP_MEMBERS" MODIFY ("GROUP_ID" NOT NULL ENABLE);
  ALTER TABLE "SPRING_SECURITY"."GROUP_MEMBERS" MODIFY ("USERNAME" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table GROUP_MEMBERS
--------------------------------------------------------

  ALTER TABLE "SPRING_SECURITY"."GROUP_MEMBERS" ADD CONSTRAINT "FK_GROUP_MEMBERS_GROUP" FOREIGN KEY ("GROUP_ID")
	  REFERENCES "SPRING_SECURITY"."GROUPS" ("ID") ENABLE;


--################################################################################################################
--################################################################################################################
--################################################################################################################


--------------------------------------------------------
--  DDL for Table GROUP_AUTHORITIES
--------------------------------------------------------

  CREATE TABLE "SPRING_SECURITY"."GROUP_AUTHORITIES" 
   (	"GROUP_ID" NUMBER(8,2), 
	"AUTHORITY" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table GROUP_AUTHORITIES
--------------------------------------------------------

  ALTER TABLE "SPRING_SECURITY"."GROUP_AUTHORITIES" MODIFY ("AUTHORITY" NOT NULL ENABLE);
  ALTER TABLE "SPRING_SECURITY"."GROUP_AUTHORITIES" MODIFY ("GROUP_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table GROUP_AUTHORITIES
--------------------------------------------------------

  ALTER TABLE "SPRING_SECURITY"."GROUP_AUTHORITIES" ADD CONSTRAINT "FK_GROUP_AUTHORITIES_GROUP" FOREIGN KEY ("GROUP_ID")
	  REFERENCES "SPRING_SECURITY"."GROUPS" ("ID") ENABLE;


--################################################################################################################
--################################################################################################################
--################################################################################################################


--------------------------------------------------------
--  DDL for Table AUTHORITIES
--------------------------------------------------------

  CREATE TABLE "SPRING_SECURITY"."AUTHORITIES" 
   (	"USERNAME" VARCHAR2(50 BYTE), 
	"AUTHORITY" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index IX_AUTH_USERNAME
--------------------------------------------------------

  CREATE UNIQUE INDEX "SPRING_SECURITY"."IX_AUTH_USERNAME" ON "SPRING_SECURITY"."AUTHORITIES" ("USERNAME", "AUTHORITY") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table AUTHORITIES
--------------------------------------------------------

  ALTER TABLE "SPRING_SECURITY"."AUTHORITIES" MODIFY ("AUTHORITY" NOT NULL ENABLE);
  ALTER TABLE "SPRING_SECURITY"."AUTHORITIES" MODIFY ("USERNAME" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table AUTHORITIES
--------------------------------------------------------

  ALTER TABLE "SPRING_SECURITY"."AUTHORITIES" ADD CONSTRAINT "FK_AUTHORITIES_USERS" FOREIGN KEY ("USERNAME")
	  REFERENCES "SPRING_SECURITY"."USERS" ("USERNAME") ENABLE;


--################################################################################################################
--################################################################################################################
--################################################################################################################


INSERT INTO USERS VALUES ('enrique', 'enrique', 1);
INSERT INTO USERS VALUES ('juan', 'juan', 1);
INSERT INTO USERS VALUES ('lucas', 'lucas', 1);
INSERT INTO USERS VALUES ('pepe', 'pepe', 1);
INSERT INTO USERS VALUES ('rosa', 'rosa', 1);

SELECT * FROM USERS;

--################################################################################################################
--################################################################################################################
--################################################################################################################

INSERT INTO GROUPS VALUES (SEQ_SECURITY_GROUPS.NEXTVAL,  'USUARIOS');
INSERT INTO GROUPS VALUES (SEQ_SECURITY_GROUPS.NEXTVAL,  'ADMINISTRADORES');
INSERT INTO GROUPS VALUES (SEQ_SECURITY_GROUPS.NEXTVAL,  'INVITADOS');

SELECT * FROM GROUPS;

--################################################################################################################
--################################################################################################################
--################################################################################################################

INSERT INTO GROUP_AUTHORITIES VALUES (1, 'ROLE_USER');
INSERT INTO GROUP_AUTHORITIES VALUES (2, 'ROLE_ADMIN');

SELECT * FROM GROUP_AUTHORITIES;

--################################################################################################################
--################################################################################################################
--################################################################################################################
id  username   group_id
-----------------------
2   'lucas'     2
3   'juan'      1
4   'rosa'      3
5   'enrique'   2
6   'pepe'      1
8   'enrique'   1

2, 'lucas', 2
3, 'juan', 1
4, 'rosa', 3
5, 'enrique', 2
6, 'pepe', 1
8, 'enrique', 1

INSERT INTO GROUP_MEMBERS VALUES (2, 'lucas', 2);
INSERT INTO GROUP_MEMBERS VALUES (3, 'juan', 1);
INSERT INTO GROUP_MEMBERS VALUES (4, 'rosa', 3);
INSERT INTO GROUP_MEMBERS VALUES (5, 'enrique', 2);
INSERT INTO GROUP_MEMBERS VALUES (6, 'pepe', 1);
INSERT INTO GROUP_MEMBERS VALUES (8, 'enrique', 1);

SELECT * FROM GROUP_MEMBERS;

--################################################################################################################
--################################################################################################################
--################################################################################################################

