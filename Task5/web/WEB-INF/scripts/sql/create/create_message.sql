CREATE TABLE petbook.messages (
    id int PRIMARY KEY AUTO_INCREMENT,
    message varchar(255) NOT NULL,
    userId int NOT NULL,
    fromUserId int NOT NULL,
    FOREIGN KEY (userId) REFERENCES petbook.user (id) ON DELETE CASCADE
);