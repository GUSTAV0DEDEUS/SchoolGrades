CREATE DATABASE IF NOT EXISTS ams_education;

CREATE TABLE ams_education.escola (
  id_escola INT PRIMARY KEY AUTO_INCREMENT,
  nome_escola VARCHAR(255) NOT NULL,
  endereco VARCHAR(255) NOT NULL,
  cidade VARCHAR(255) NOT NULL,
  estado VARCHAR(255) NOT NULL
);

CREATE TABLE ams_education.disciplina (
  id_disciplina INT PRIMARY KEY AUTO_INCREMENT,
  nome_disciplina VARCHAR(255) NOT NULL,
  id_escola INT NOT NULL,
  FOREIGN KEY (id_escola) REFERENCES ams_education.escola(id_escola)
);

CREATE TABLE ams_education.professor (
  id_professor INT PRIMARY KEY AUTO_INCREMENT,
  id_escola INT NOT NULL,
  id_disciplina INT NOT NULL,
  nome_professor VARCHAR(255) NOT NULL,
  especialidade VARCHAR(255) NOT NULL,
  FOREIGN KEY (id_escola) REFERENCES ams_education.escola(id_escola),
  FOREIGN KEY (id_disciplina) REFERENCES ams_education.disciplina(id_disciplina)
);

CREATE TABLE ams_education.aluno (
  id_aluno INT PRIMARY KEY AUTO_INCREMENT,
  id_escola INT NOT NULL,
  nome_aluno VARCHAR(255) NOT NULL,
  data_nascimento DATE NOT NULL,
  FOREIGN KEY (id_escola) REFERENCES ams_education.escola(id_escola)
);

CREATE TABLE ams_education.usuario (
  id_login INT PRIMARY KEY AUTO_INCREMENT,
  id_usuario INT NOT NULL,
  tipo_usuario ENUM('professor', 'aluno', 'escola') NOT NULL,
  email VARCHAR(255) NOT NULL,
  senha VARCHAR(255) NOT NULL
);

CREATE TABLE ams_education.boletim (
  id_boletim INT PRIMARY KEY AUTO_INCREMENT,
  id_aluno INT NOT NULL,
  id_disciplina INT NOT NULL,
  nota DECIMAL(3,1) NOT NULL,
  FOREIGN KEY (id_aluno) REFERENCES ams_education.aluno(id_aluno),
  FOREIGN KEY (id_disciplina) REFERENCES ams_education.disciplina(id_disciplina)
);

INSERT INTO ams_education.escola (nome_escola, endereco, cidade, estado)
VALUES ('Escola Primária ABC', 'Rua das Escolas, 123', 'Cidade A', 'Estado A');

INSERT INTO ams_education.disciplina (id_escola, nome_disciplina)
VALUES (1, 'Matemática');

INSERT INTO ams_education.professor (id_escola, id_disciplina, nome_professor, especialidade)
VALUES (1, 1, 'João Silva', 'Matemática');

INSERT INTO ams_education.aluno (id_escola, nome_aluno, data_nascimento)
VALUES (1, 'Maria Souza', '2005-05-09');
