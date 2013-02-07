DROP TABLE IF EXISTS USER;

CREATE TABLE USER (
       ID INT NOT NULL AUTO_INCREMENT
     , LOGIN VARCHAR(60) NOT NULL
     , PASSWORD VARCHAR(40) NOT NULL
	 , EMAIL VARCHAR(40) NOT NULL
     , UNIQUE UQ_USER_1 (LOGIN, PASSWORD)
     , PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS authorities;

CREATE TABLE authorities
(
    username    varchar2 (50) NOT NULL,
    authority   varchar2 (50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY
        (username)
         REFERENCES users (LOGIN)
);

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

insert into USER (LOGIN, PASSWORD, EMAIL) values ('Alex', '1234', 'qwe@gmail.com');
insert into USER (LOGIN, PASSWORD, EMAIL) values ('Scott', '123', 'qwe2@gmail.com');
insert into USER (LOGIN, PASSWORD, EMAIL) values ('John', '12345', 'qwe3@gmail.com');