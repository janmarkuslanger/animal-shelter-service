CREATE TABLE IF NOT EXISTS ANIMAL (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    birth_year VARCHAR(4),
    gender VARCHAR(10),
    description TEXT,
    spayed BOOLEAN,
    at_shelter_since DATE,
    adopted BOOLEAN DEFAULT FALSE,
    image VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS USERS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS IMAGE (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    animal_id BIGINT,
    path TEXT,
    description TEXT,
    FOREIGN KEY (animal_id) REFERENCES ANIMAL(id)
);

SELECT COUNT(*) FROM USERS;

INSERT INTO USERS (username, password, role, email)
SELECT 'admin', 'admin', 'ADMIN', 'admin@admin'
WHERE NOT EXISTS (SELECT 1 FROM USERS);