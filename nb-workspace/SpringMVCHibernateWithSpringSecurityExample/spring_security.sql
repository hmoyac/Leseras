/*All User's gets stored in APP_USER table*/
create table APP_USER (
   id NUMBER(8,2) NOT NULL,
   sso_id VARCHAR(30) NOT NULL,
   password VARCHAR(100) NOT NULL,
   first_name VARCHAR(30) NOT NULL,
   last_name  VARCHAR(30) NOT NULL,
   email VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (sso_id)
);

create SEQUENCE seq_user INCREMENT BY 1 MAXVALUE 999999999 CYCLE;

   
/* USER_PROFILE table contains all possible roles */ 
create table USER_PROFILE(
   id NUMBER(8,2) NOT NULL,
   type VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (type)
);

create SEQUENCE seq_user_profile INCREMENT BY 1 MAXVALUE 999999999 CYCLE;
   
/* JOIN TABLE for MANY-TO-MANY relationship*/  
CREATE TABLE APP_USER_USER_PROFILE (
    user_id NUMBER(8,2) NOT NULL,
    user_profile_id NUMBER(8,2) NOT NULL,
    PRIMARY KEY (user_id, user_profile_id),
    CONSTRAINT FK_APP_USER FOREIGN KEY (user_id) REFERENCES APP_USER (id),
    CONSTRAINT FK_USER_PROFILE FOREIGN KEY (user_profile_id) REFERENCES USER_PROFILE (id)
);
  
/* Populate USER_PROFILE Table */
INSERT INTO USER_PROFILE VALUES (seq_user.nextval, 'USER');
  
INSERT INTO USER_PROFILE VALUES (seq_user.nextval, 'ADMIN');
  
INSERT INTO USER_PROFILE VALUES (seq_user.nextval, 'DBA');
  
  
/* Populate one Admin User which will further create other users for the application using GUI */
INSERT INTO APP_USER(id, sso_id, password, first_name, last_name, email)
VALUES (seq_user_profile.nextval, 'sam','$2a$10$4eqIF5s/ewJwHK1p8lqlFOEm2QIA0S8g6./Lok.pQxqcxaBZYChRm', 'Sam','Smith','samy@xyz.com');
  
  
/* Populate JOIN Table */
INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
  SELECT user1.id, profile1.id FROM app_user user1, user_profile profile1
  where user1.sso_id='sam' and profile1.type='ADMIN';
 
/* Create persistent_logins Table used to store rememberme related stuff*/
CREATE TABLE persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);