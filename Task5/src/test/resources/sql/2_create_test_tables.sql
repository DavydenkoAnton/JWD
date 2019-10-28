USE `test_db`;

CREATE TABLE `articles`
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `title`       VARCHAR(255) NOT NULL,
    `text`        MEDIUMTEXT   NOT NULL,
    `description` TEXT         NOT NULL,
    `type`     ENUM('DOG', 'CAT', 'BIRD','OTHER')DEFAULT('OTHER')NOT NULL,
    CONSTRAINT PK_id PRIMARY KEY (`id`)
) ENGINE = INNODB
DEFAULT CHARACTER
SET utf8;

CREATE TABLE `messages`
(
    `message` VARCHAR(255) NOT NULL,
    `userId`  INT          NOT NULL,
    `sederId` INT          NOT NULL,
    `date`    DATETIME     NOT NULL
) ENGINE = INNODB
DEFAULT CHARACTER
SET utf8;

CREATE TABLE `pets`
(
    `id`        INT                                                   NOT NULL PRIMARY KEY,
    `userId`    INT                                                   NOT NULL,
    `name`      VARCHAR(255),
    `breed`     VARCHAR(255),
    `age`       INT,
    `avatarUrl` VARCHAR(4096),
`type`      ENUM ('DOG', 'CAT', 'BIRD','OTHER') DEFAULT ('OTHER') NOT NULL,
CONSTRAINT PK_id PRIMARY KEY (`id`)
) ENGINE = INNODB
DEFAULT CHARACTER
SET utf8;

CREATE TABLE `photo`
(
    `id`        INT                                                   NOT NULL PRIMARY KEY,
    `url`      VARCHAR(4096),
    `userId`    INT                                                   NOT NULL,
CONSTRAINT PK_id PRIMARY KEY (`id`)
) ENGINE = INNODB
DEFAULT CHARACTER
SET utf8;

CREATE TABLE `users`
(
    `id`       INT                                             NOT NULL AUTO_INCREMENT,
    `login`    VARCHAR(255)                                    NOT NULL UNIQUE,
    `password` CHAR(32)                                        NOT NULL,
    `role`     ENUM('ADMIN', 'USER', 'GUEST')DEFAULT('USER')NOT NULL,
    CONSTRAINT PK_id PRIMARY KEY(`id`)
)ENGINE = INNODB DEFAULT CHARACTER
SET utf8;
