CREATE DATABASE IF NOT EXISTS joc_rol;

CREATE TABLE IF NOT EXISTS joc_rol.ranking (
                                               id int auto_increment primary key,
                                               userName varchar(255),
    coins int,
    lifes int,
    didUserWon bool,
    gameDuration int
    );