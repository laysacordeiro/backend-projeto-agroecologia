CREATE TABLE especies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    nome_cientifico VARCHAR(255) NOT NULL,
    ano VARCHAR(10),
    descricao TEXT, 
    taxonomia_id BIGINT,
    CONSTRAINT fk_especie_taxonomia FOREIGN KEY (taxonomia_id) REFERENCES taxonomias(id)
);
CREATE TABLE caracs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    caracteristica VARCHAR(255) NOT NULL
);
CREATE TABLE especie_carac (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    valor_carac VARCHAR(50),
    especie_id BIGINT NOT NULL,
    carac_id BIGINT NOT NULL,
    CONSTRAINT fk_especie FOREIGN KEY (especie_id) REFERENCES especies(id),
    CONSTRAINT fk_carac FOREIGN KEY (carac_id) REFERENCES caracs(id),
    CONSTRAINT uc_especie_carac UNIQUE (especie_id, carac_id)
);