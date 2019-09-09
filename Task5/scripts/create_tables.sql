create table users(
  id          int auto_increment,
  login       varchar(255) not null,
  password    varchar(255) not null,
  name        varchar(255) ,
  email       varchar(255) ,
  avatarUrl   varchar(255) ,
  phoneNumber bigint       ,
  age         int          ,
  role        enum('USER', 'ADMIN') default 'USER' not null ,
constraint users_pk
primary key(id)
);

CREATE TABLE petbook.pets(
  id     int PRIMARY KEY AUTO_INCREMENT,
  user_id int,
  name   varchar(255) NOT NULL,
  breed  varchar(255) NOT NULL,
  age    int          NOT NULL,
  avatarUrl   varchar(255) ,
FOREIGN KEY(user_id)REFERENCES petbook.users(id)ON
DELETE CASCADE
);

create table petbook.articles
(
  title varchar(255) not null,
  text varchar(255) not null
);

CREATE TABLE petbook.messages(
  message   varchar(255) NOT NULL,
  user_id   int NOT NULL,
  sender_id int NOT NULL
);