CREATE TABLE auth (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);


CREATE TABLE taxonomias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    nivel VARCHAR(100) NOT NULL,
    parent_id BIGINT,
    CONSTRAINT fk_taxonomia_parent FOREIGN KEY (parent_id) REFERENCES taxonomias(id)
);