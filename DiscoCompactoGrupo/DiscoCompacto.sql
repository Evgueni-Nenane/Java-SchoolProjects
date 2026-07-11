-- ============================================
-- DATABASE INITIALIZATION
-- ============================================
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Drop and Create Schema
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `discocompacto`;
CREATE SCHEMA IF NOT EXISTS `discocompacto` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `discocompacto`;

-- ============================================
-- CORE TABLES (Parent Tables)
-- ============================================

-- -----------------------------------------------------
-- Table: genero
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `discocompacto`.`genero` (
    `Codigo_Genero` INT NOT NULL AUTO_INCREMENT,
    `Nome_Genero` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`Codigo_Genero`),
    UNIQUE INDEX `Nome_Genero` (`Nome_Genero`)
) ENGINE = InnoDB AUTO_INCREMENT = 42 DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table: disco_compacto
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `discocompacto`.`disco_compacto` (
    `Codigo_Disco` INT NOT NULL AUTO_INCREMENT,
    `Titulo` VARCHAR(70) NULL DEFAULT NULL,
    `Preco` DECIMAL(7,2) NULL DEFAULT NULL,
    `Ano_Edicao` YEAR NULL DEFAULT NULL,
    `Codigo_Genero` INT NULL DEFAULT NULL,
    PRIMARY KEY (`Codigo_Disco`),
    INDEX `FK_Disco_Genero` (`Codigo_Genero`),
    CONSTRAINT `FK_Disco_Genero`
        FOREIGN KEY (`Codigo_Genero`)
        REFERENCES `discocompacto`.`genero` (`Codigo_Genero`)
) ENGINE = InnoDB AUTO_INCREMENT = 7 DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table: cantor
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `discocompacto`.`cantor` (
    `Codigo_Cantor` INT NOT NULL AUTO_INCREMENT,
    `Nome_Cantor` VARCHAR(60) NULL DEFAULT NULL,
    `Apelido_Cantor` VARCHAR(60) NULL DEFAULT NULL,
    `Contacto_Cantor` VARCHAR(20) NULL DEFAULT NULL,
    `Email_Cantor` VARCHAR(150) NOT NULL,
    PRIMARY KEY (`Codigo_Cantor`)
) ENGINE = InnoDB AUTO_INCREMENT = 6 DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table: compositor
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `discocompacto`.`compositor` (
    `Codigo_Compositor` INT NOT NULL AUTO_INCREMENT,
    `Nome_Compositor` VARCHAR(60) NULL DEFAULT NULL,
    `Apelido_Compositor` VARCHAR(60) NULL DEFAULT NULL,
    `Contacto_Compositor` VARCHAR(20) NULL DEFAULT NULL,
    `Email_Compositor` VARCHAR(150) NOT NULL,
    PRIMARY KEY (`Codigo_Compositor`)
) ENGINE = InnoDB AUTO_INCREMENT = 8 DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table: musico
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `discocompacto`.`musico` (
    `Codigo_Musico` INT NOT NULL AUTO_INCREMENT,
    `Nome_Musico` VARCHAR(60) NULL DEFAULT NULL,
    `Apelido_Musico` VARCHAR(60) NULL DEFAULT NULL,
    `Instrumento` VARCHAR(80) NULL DEFAULT NULL,
    `Email_Musico` VARCHAR(150) NOT NULL,
    `Contacto_Musico` VARCHAR(20) NULL DEFAULT NULL,
    PRIMARY KEY (`Codigo_Musico`)
) ENGINE = InnoDB AUTO_INCREMENT = 8 DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table: editora
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `discocompacto`.`editora` (
    `Codigo_Editora` INT NOT NULL AUTO_INCREMENT,
    `Nome_Editora` VARCHAR(80) NOT NULL,
    `Email_Editora` VARCHAR(150) NOT NULL,
    `Endereco` VARCHAR(80) NULL DEFAULT NULL,
    `Contacto_Editora` VARCHAR(20) NULL DEFAULT NULL,
    PRIMARY KEY (`Codigo_Editora`)
) ENGINE = InnoDB AUTO_INCREMENT = 6 DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table: gravadora
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `discocompacto`.`gravadora` (
    `Codigo_Gravadora` INT NOT NULL AUTO_INCREMENT,
    `Nome_Gravadora` VARCHAR(80) NOT NULL,
    `Endereco_Gravadora` VARCHAR(150) NOT NULL,
    `Email_Gravadora` VARCHAR(80) NULL DEFAULT NULL,
    `Contacto_Gravadora` VARCHAR(20) NULL DEFAULT NULL,
    PRIMARY KEY (`Codigo_Gravadora`),
    UNIQUE INDEX `Nome_Gravadora` (`Nome_Gravadora`)
) ENGINE = InnoDB AUTO_INCREMENT = 6 DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table: produtor
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `discocompacto`.`produtor` (
    `Codigo_Prod` INT NOT NULL AUTO_INCREMENT,
    `Nome_Prod` VARCHAR(60) NOT NULL,
    `Apelido_Produtor` VARCHAR(60) NULL DEFAULT NULL,
    `Contacto_Prod` VARCHAR(20) NOT NULL,
    `Email_Prod` VARCHAR(150) NOT NULL,
    PRIMARY KEY (`Codigo_Prod`)
) ENGINE = InnoDB AUTO_INCREMENT = 8 DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table: instrumento
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `discocompacto`.`instrumento` (
    `Codigo` INT NOT NULL AUTO_INCREMENT,
    `NomeInstrumento` VARCHAR(50) NULL DEFAULT NULL,
    PRIMARY KEY (`Codigo`),
    UNIQUE INDEX `NomeInstrumento` (`NomeInstrumento`)
) ENGINE = InnoDB AUTO_INCREMENT = 5 DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table: nivelacesso
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `discocompacto`.`nivelacesso` (
    `codigoNivel` INT NOT NULL AUTO_INCREMENT,
    `NomeNivel` VARCHAR(40) NOT NULL,
    PRIMARY KEY (`codigoNivel`),
    UNIQUE INDEX `Nome` (`NomeNivel`)
) ENGINE = InnoDB AUTO_INCREMENT = 5 DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ============================================
-- RELATIONSHIP TABLES (Many-to-Many)
-- ============================================

-- -----------------------------------------------------
-- Table: cantor_dc
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `discocompacto`.`cantor_dc` (
    `Codigo_Cantor` INT NOT NULL,
    `Codigo_DC` INT NOT NULL,
    PRIMARY KEY (`Codigo_Cantor`, `Codigo_DC`),
    INDEX `Codigo_DC` (`Codigo_DC`),
    CONSTRAINT `cantor_dc_ibfk_1`
        FOREIGN KEY (`Codigo_Cantor`)
        REFERENCES `discocompacto`.`cantor` (`Codigo_Cantor`),
    CONSTRAINT `cantor_dc_ibfk_2`
        FOREIGN KEY (`Codigo_DC`)
        REFERENCES `discocompacto`.`disco_compacto` (`Codigo_Disco`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table: compositor_dc
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `discocompacto`.`compositor_dc` (
    `Codigo_Compositor` INT NOT NULL,
    `Codigo_DC` INT NOT NULL,
    PRIMARY KEY (`Codigo_Compositor`, `Codigo_DC`),
    INDEX `Codigo_DC` (`Codigo_DC`),
    CONSTRAINT `compositor_dc_ibfk_1`
        FOREIGN KEY (`Codigo_Compositor`)
        REFERENCES `discocompacto`.`compositor` (`Codigo_Compositor`),
    CONSTRAINT `compositor_dc_ibfk_2`
        FOREIGN KEY (`Codigo_DC`)
        REFERENCES `discocompacto`.`disco_compacto` (`Codigo_Disco`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table: disco_genero
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `discocompacto`.`disco_genero` (
    `Codigo_DC` INT NULL DEFAULT NULL,
    `Codigo_Genero` INT NULL DEFAULT NULL,
    INDEX `Codigo_DC` (`Codigo_DC`),
    INDEX `Codigo_Genero` (`Codigo_Genero`),
    CONSTRAINT `disco_genero_ibfk_1`
        FOREIGN KEY (`Codigo_DC`)
        REFERENCES `discocompacto`.`disco_compacto` (`Codigo_Disco`),
    CONSTRAINT `disco_genero_ibfk_2`
        FOREIGN KEY (`Codigo_Genero`)
        REFERENCES `discocompacto`.`genero` (`Codigo_Genero`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table: edicao
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `discocompacto`.`edicao` (
    `Data_Edicao` DATE NOT NULL,
    `Codigo_DC` INT NOT NULL,
    `Codigo_Editora` INT NOT NULL,
    PRIMARY KEY (`Codigo_DC`, `Codigo_Editora`),
    INDEX `Codigo_Editora` (`Codigo_Editora`),
    CONSTRAINT `edicao_ibfk_1`
        FOREIGN KEY (`Codigo_DC`)
        REFERENCES `discocompacto`.`disco_compacto` (`Codigo_Disco`),
    CONSTRAINT `edicao_ibfk_2`
        FOREIGN KEY (`Codigo_Editora`)
        REFERENCES `discocompacto`.`editora` (`Codigo_Editora`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table: gravadoradisco
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `discocompacto`.`gravadoradisco` (
    `Codigo_DC` INT NOT NULL,
    `Codigo_Gravadora` INT NOT NULL,
    PRIMARY KEY (`Codigo_DC`, `Codigo_Gravadora`),
    INDEX `Codigo_Gravadora` (`Codigo_Gravadora`),
    CONSTRAINT `gravadoradisco_ibfk_1`
        FOREIGN KEY (`Codigo_DC`)
        REFERENCES `discocompacto`.`disco_compacto` (`Codigo_Disco`),
    CONSTRAINT `gravadoradisco_ibfk_2`
        FOREIGN KEY (`Codigo_Gravadora`)
        REFERENCES `discocompacto`.`gravadora` (`Codigo_Gravadora`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table: musico_dc
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `discocompacto`.`musico_dc` (
    `Codigo_Musico` INT NOT NULL,
    `Codigo_DC` INT NOT NULL,
    PRIMARY KEY (`Codigo_Musico`, `Codigo_DC`),
    INDEX `Codigo_DC` (`Codigo_DC`),
    CONSTRAINT `musico_dc_ibfk_1`
        FOREIGN KEY (`Codigo_Musico`)
        REFERENCES `discocompacto`.`musico` (`Codigo_Musico`),
    CONSTRAINT `musico_dc_ibfk_2`
        FOREIGN KEY (`Codigo_DC`)
        REFERENCES `discocompacto`.`disco_compacto` (`Codigo_Disco`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table: musico_instrumento
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `discocompacto`.`musico_instrumento` (
    `Codigo_Instr` INT NOT NULL,
    `Codigo_Musico` INT NOT NULL,
    PRIMARY KEY (`Codigo_Instr`, `Codigo_Musico`),
    INDEX `Codigo_Musico` (`Codigo_Musico`),
    CONSTRAINT `musico_instrumento_ibfk_1`
        FOREIGN KEY (`Codigo_Instr`)
        REFERENCES `discocompacto`.`instrumento` (`Codigo`),
    CONSTRAINT `musico_instrumento_ibfk_2`
        FOREIGN KEY (`Codigo_Musico`)
        REFERENCES `discocompacto`.`musico` (`Codigo_Musico`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table: prodc
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `discocompacto`.`prodc` (
    `Codigo_DC` INT NOT NULL,
    `Codigo_Produtor` INT NOT NULL,
    PRIMARY KEY (`Codigo_DC`, `Codigo_Produtor`),
    INDEX `Codigo_Produtor` (`Codigo_Produtor`),
    CONSTRAINT `prodc_ibfk_1`
        FOREIGN KEY (`Codigo_DC`)
        REFERENCES `discocompacto`.`disco_compacto` (`Codigo_Disco`),
    CONSTRAINT `prodc_ibfk_2`
        FOREIGN KEY (`Codigo_Produtor`)
        REFERENCES `discocompacto`.`produtor` (`Codigo_Prod`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ============================================
-- USER AND AUDIT TABLES
-- ============================================

-- -----------------------------------------------------
-- Table: utilizador
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `discocompacto`.`utilizador` (
    `Codigo_User` INT NOT NULL AUTO_INCREMENT,
    `Nome` VARCHAR(60) NOT NULL,
    `Apelido` VARCHAR(60) NULL DEFAULT NULL,
    `UserName` VARCHAR(100) NULL DEFAULT NULL,
    `Genero` VARCHAR(10) NULL DEFAULT NULL,
    `Email` VARCHAR(100) NULL DEFAULT NULL,
    `Contacto` VARCHAR(20) NOT NULL,
    `Senha` VARCHAR(20) NOT NULL,
    `Primeiro_Acesso` TINYINT(1) NULL DEFAULT NULL,
    `foto` LONGBLOB NULL DEFAULT NULL,
    `codigo_Nivel` INT NULL DEFAULT NULL,
    PRIMARY KEY (`Codigo_User`),
    INDEX `fk_NivelAcesso` (`codigo_Nivel`),
    CONSTRAINT `fk_NivelAcesso`
        FOREIGN KEY (`codigo_Nivel`)
        REFERENCES `discocompacto`.`nivelacesso` (`codigoNivel`)
) ENGINE = InnoDB AUTO_INCREMENT = 24 DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table: logs
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `discocompacto`.`logs` (
    `Codigo` INT NOT NULL AUTO_INCREMENT,
    `Nome` VARCHAR(50) NULL DEFAULT NULL,
    `Apelido` VARCHAR(50) NULL DEFAULT NULL,
    `Perfil` VARCHAR(30) NOT NULL,
    `Email` VARCHAR(100) NULL DEFAULT NULL,
    `Accao` VARCHAR(100) NULL DEFAULT NULL,
    `Hora` DATETIME NOT NULL,
    PRIMARY KEY (`Codigo`)
) ENGINE = InnoDB AUTO_INCREMENT = 363 DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ============================================
-- RESTORE SETTINGS
-- ============================================
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;