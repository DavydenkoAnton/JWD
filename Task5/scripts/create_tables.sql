CREATE TABLE petbook.admin(
  login varchar(255) NOT NULL,
  password  int NOT NULL
);

create table users
(
id          int auto_increment
primary key,
  login       varchar(255) not null,
  password    varchar(255) not null,
  name        varchar(255) not null,
  email       varchar(255) not null,
  phoneNumber bigint       not null,
  age         int          not null
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