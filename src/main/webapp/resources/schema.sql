DROP TABLE IF EXISTS ROLE;

CREATE TABLE ROLE
(
    ROLE_ID INT NOT NULL AUTO_INCREMENT,
    ROLE_NAME  VARCHAR(40) NOT NULL,
    UNIQUE UQ_ROLE_1 (ROLE_NAME),
    PRIMARY KEY (ROLE_ID)
);

DROP TABLE IF EXISTS USER;

CREATE TABLE USER (
       ID INT NOT NULL AUTO_INCREMENT
     , LOGIN VARCHAR(60) NOT NULL
     , PASSWORD VARCHAR(40) NOT NULL
	 , EMAIL VARCHAR(40) NOT NULL
	 , ROLE_ID INT NOT NULL
     , UNIQUE UQ_USER_1 (LOGIN, PASSWORD)
     , PRIMARY KEY (ID)
     , CONSTRAINT FK_USERROLE_1 FOREIGN KEY(ROLE_ID) 
    REFERENCES ROLE (ROLE_ID)
);

/*
DROP TABLE IF EXISTS authorities;


CREATE TABLE authorities
(
    username    varchar2 (50) NOT NULL,
    authority   varchar2 (50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY
        (username)
         REFERENCES users (LOGIN)
);
*/

DROP TABLE IF EXISTS PICTURE;

CREATE TABLE PICTURE (
	   ID INT NOT NULL AUTO_INCREMENT
	 , USER_ID INT NOT NULL
     , NAME VARCHAR(60) NOT NULL
     , DESCRIPTION VARCHAR(40) NOT NULL
     , creationTime DATE
     , PHOTO BLOB	 
	 , UNIQUE UQ_PICTURE_1 (NAME, DESCRIPTION)
     , PRIMARY KEY (ID)
	 , CONSTRAINT FK_PICTURE_1 FOREIGN KEY (USER_ID)
	   REFERENCES USER (ID)
);

insert into ROLE (ROLE_NAME) values ('ROLE_USER');
insert into ROLE (ROLE_NAME) values ('ROLE_ADMIN');

insert into USER (LOGIN, PASSWORD, EMAIL, ROLE_ID) values ('Alex', '1234', 'qwe@gmail.com', '1');
insert into USER (LOGIN, PASSWORD, EMAIL, ROLE_ID) values ('Scott', '123', 'qwe2@gmail.com', '1');
insert into USER (LOGIN, PASSWORD, EMAIL, ROLE_ID) values ('John', '12345', 'qwe3@gmail.com', '1');
insert into USER (LOGIN, PASSWORD, EMAIL, ROLE_ID) values ('admin', 'admin', 'qwe@gmail.com', '2');