drop database if exists discocompacto;
create database discocompacto;
Use discocompacto;

CREATE TABLE Disco_Compacto (
    Codigo_Disco INT PRIMARY KEY AUTO_INCREMENT,
    Titulo VARCHAR(70),
    Genero VARCHAR(30),
    Preco DECIMAL(7 , 2 ),
    Ano_Edicao YEAR
);

CREATE TABLE Produtor (
    Codigo_Prod INT PRIMARY KEY AUTO_INCREMENT,
    Nome_Prod VARCHAR(60) NOT NULL,
    Apelido_Produtor VARCHAR(60),
    Nome_Art_Prod VARCHAR(60),
    Contacto_Prod VARCHAR(20) NOT NULL,
    Email_Prod VARCHAR(150) NOT NULL
);

CREATE TABLE ProDC (
    Codigo_Disco INT,
    Codigo_Produtor INT,
    FOREIGN KEY (Codigo_Disco)
        REFERENCES Disco_Compacto (Codigo_Disco),
    FOREIGN KEY (Codigo_Produtor)
        REFERENCES Produtor (Codigo_Prod),
		PRIMARY KEY (Codigo_Disco, Codigo_Produtor)
);

CREATE TABLE Editora (
    Codigo_Editora INT PRIMARY KEY AUTO_INCREMENT,
    Nome_Editora VARCHAR(80) NOT NULL,
    Email_Editora VARCHAR(150) NOT NULL,
    Endereco VARCHAR(80)
);

CREATE TABLE Edicao (
    Data_Edicao DATE NOT NULL,
    Codigo_Disco INT,
    Codigo_Editora INT,
    FOREIGN KEY (Codigo_Disco)
        REFERENCES Disco_Compacto (Codigo_Disco),
    FOREIGN KEY (Codigo_Editora)
        REFERENCES Editora (Codigo_Editora),
        PRIMARY KEY (Codigo_Disco, Codigo_Editora)
);

CREATE TABLE Gravadora (
    Codigo_Gravadora INT PRIMARY KEY AUTO_INCREMENT,
    Nome_Gravadora VARCHAR(80) UNIQUE NOT NULL,
    Email_Gravadora VARCHAR(150) NOT NULL,
    Endereco_Gravadora VARCHAR(80),
    Contacto_Gravadora VARCHAR(20)
);

CREATE TABLE GravadoraDisco (
    Codigo_Disco INT,
    Codigo_Gravadora INT,
    FOREIGN KEY (Codigo_Disco)
        REFERENCES Disco_Compacto (Codigo_Disco),
    FOREIGN KEY (Codigo_Gravadora)
        REFERENCES Gravadora (Codigo_Gravadora),
        PRIMARY KEY (Codigo_Disco, Codigo_Gravadora)
);

CREATE TABLE Cantor (
    Codigo_Cantor INT PRIMARY KEY AUTO_INCREMENT,
    Nome_Cantor VARCHAR(60),
    Apelido_Cantor VARCHAR(60),
    Contacto_Cantor VARCHAR(20),
    Email_Cantor VARCHAR(150) NOT NULL
);

CREATE TABLE Cantor_DC (
    Codigo_Cantor INT,
    Codigo_DC INT,
    FOREIGN KEY (Codigo_Cantor)
        REFERENCES Cantor (Codigo_Cantor),
    FOREIGN KEY (Codigo_DC)
        REFERENCES disco_compacto (Codigo_Disco),
        PRIMARY KEY (Codigo_Cantor, Codigo_DC)
);

CREATE TABLE Compositor (
    Codigo_Compositor INT PRIMARY KEY AUTO_INCREMENT,
    Nome_Compositor VARCHAR(60),
    Apelido_Compositor VARCHAR(60),
    Contacto_Compositor VARCHAR(20),
    Email_Compositor VARCHAR(150) NOT NULL
);
Drop Table if exists Compositor_DC;
CREATE TABLE Compositor_DC (
    Codigo_Compositor INT,
    Codigo_DC INT,
    FOREIGN KEY (Codigo_Compositor)
        REFERENCES Compositor (Codigo_Compositor),
    FOREIGN KEY (Codigo_DC)
        REFERENCES disco_compacto (codigo_disco),
        PRIMARY KEY(Codigo_Compositor, Codigo_DC)
);

CREATE TABLE Musico (
    Codigo_Musico INT PRIMARY KEY AUTO_INCREMENT,
    Nome_Art_Musico VARCHAR(50),
    Nome_Musico VARCHAR(60),
    Apelido_Musico VARCHAR(60),
    Contacto_Musico VARCHAR(20),
    Email_Musico VARCHAR(150) NOT NULL
);

CREATE TABLE Musico_DC (
    Codigo_Musico INT,
    Codigo_DC INT,
    FOREIGN KEY (Codigo_Musico)
        REFERENCES Musico (Codigo_Musico),
    FOREIGN KEY (Codigo_DC)
        REFERENCES disco_compacto (Codigo_Disco),
        PRIMARY KEY (Codigo_Musico, Codigo_DC)
);

DROP TABLE if exists Utilizador;
CREATE TABLE Utilizador (
    Codigo_User INT PRIMARY KEY AUTO_INCREMENT,
    Nome VARCHAR(60) NOT NULL,
    Apelido VARCHAR(60),
    UserName VARCHAR(100),
    Genero VARCHAR(10),
    Perfil VARCHAR(30) NOT NULL,
    Email VARCHAR(100),
    Contacto VARCHAR(20) NOT NULL,
    Senha VARCHAR(20) NOT NULL,
    Primeiro_Acesso BOOLEAN
);

CREATE TABLE Logs (
	Codigo INT PRIMARY KEY AUTO_INCREMENT,
    Nome VARCHAR(50),
    Apelido VARCHAR(50),
    Perfil VARCHAR(30) NOT NULL,
    Email VARCHAR(100),
    Accao VARCHAR(100),
    Hora DATETIME NOT NULL
);


CREATE TABLE Instrumento (
	Codigo INT PRIMARY KEY AUTO_INCREMENT,
    NomeInstrumento VARCHAR(40)
);

alter table utilizador
add foto BLOB;

Use discocompacto;

ALTER TABLE compositor
Rename column Email_Compositorr to Email_Compositor;
ALTER TABLE musico
Rename column contacto_musico to instrumento;

ALTER TABLE musico
MODIFY instrumento VARCHAR(80);

ALTER TABLE produtor
DROP COLUMN Nome_Art_Prod;

drop table nivelAcesso;
CREATE TABLE NivelAcesso (
	codigoNivel INT PRIMARY KEY auto_increment,
    Nome VARCHAR(40) NOT NULL UNIQUE
);

INSERT Into nivelAcesso (Nome) VALUES ('Operador');
INSERT Into nivelAcesso (Nome) VALUES ('Super Operador');
INSERT Into nivelAcesso (Nome) VALUES ('Administrador');
INSERT Into nivelAcesso (Nome) VALUES ('Auditor');


ALTER TABLE Utilizador
ADD codigo_Nivel INT;

ALTER TABLE utilizador
ADD CONSTRAINT fk_NivelAcesso
FOREIGN KEY (codigo_Nivel) REFERENCES nivelacesso(codigoNivel);

UPDATE Utilizador u
JOIN NivelAcesso n ON UPPER(REPLACE(n.Nome, ' ', '')) = UPPER(u.Perfil)
SET u.codigo_Nivel = n.codigoNivel;

SELECT Codigo_User, Nome, Perfil, codigo_Nivel FROM Utilizador WHERE codigo_Nivel IS NULL;

SET SQL_SAFE_UPDATES = 1;