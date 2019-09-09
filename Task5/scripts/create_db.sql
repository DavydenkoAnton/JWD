CREATE SCHEMA IF NOT EXISTS petbook;

create table users(
  id          int auto_increment,
  login       varchar(255) not null,
  password    varchar(255) not null,
  name        varchar(255) not null,
  email       varchar(255) not null,
  phoneNumber bigint       not null,
  age         int          not null,
constraint users_pk
primary key (id)
);

CREATE TABLE petbook.pets(
    id     int PRIMARY KEY AUTO_INCREMENT,
    user_id int,
    name   varchar(255) NOT NULL,
    breed  varchar(255) NOT NULL,
    age    int          NOT NULL,
FOREIGN KEY(userId)REFERENCES petbook.users(id)ON
DELETE CASCADE
);

CREATE TABLE petbook.messages (
  message varchar(255) NOT NULL,
  user_id int NOT NULL,
  sender_id int NOT NULL
);
