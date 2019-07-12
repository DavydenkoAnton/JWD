CREATE TABLE petbook.admin(
  login varchar(255) NOT NULL,
  password  int NOT NULL
);

CREATE TABLE petbook.users
(
    id     int  PRIMARY KEY AUTO_INCREMENT,
    login   varchar(255) UNIQUE NOT NULL,
    password  varchar(255)  NOT NULL,
    name  varchar(255)      NOT NULL,
    email  varchar(255)     NOT NULL,
    phoneNumber    bigint   NOT NULL,
    age    int              NOT NULL
);

CREATE TABLE petbook.pets
(
    id     int PRIMARY KEY AUTO_INCREMENT,
    userId int,
    name   varchar(255) NOT NULL,
    breed  varchar(255) NOT NULL,
    age    int          NOT NULL,
FOREIGN KEY(userId)REFERENCES petbook.users(id)ON
DELETE CASCADE
)
;

CREATE TABLE petbook.messages (
    id int PRIMARY KEY AUTO_INCREMENT,
    message varchar(255) NOT NULL,
    userId int NOT NULL,
    fromUserId int NOT NULL,
FOREIGN KEY(userId)REFERENCES petbook.users(id)ON
DELETE CASCADE
)
;