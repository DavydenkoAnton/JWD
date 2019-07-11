CREATE TABLE petbook.pet
(
    id     int PRIMARY KEY AUTO_INCREMENT,
    userId int,
    name   varchar(255) NOT NULL,
    breed  varchar(255) NOT NULL,
    age    int          NOT NULL,
    FOREIGN KEY (userId) REFERENCES petbook.user (id) ON DELETE CASCADE
);