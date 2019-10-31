-- MySQL Script generated by MySQL Workbench
-- Thu Oct 31 09:20:14 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Cliente` (
  `cli_cfp` VARCHAR(11) NOT NULL,
  `cli_nome` VARCHAR(45) NOT NULL,
  `cli_email` VARCHAR(45) NULL,
  `cli_cel` VARCHAR(15) NULL,
  `cli_tel` VARCHAR(14) NULL,
  `cli_cep` VARCHAR(8) NULL,
  `cli_endereco` VARCHAR(45) NULL,
  `cli_cidade` VARCHAR(25) NULL,
  `cli_bairro` VARCHAR(20) NULL,
  `cli_complemento` VARCHAR(10) NULL,
  `cli_status` CHAR NOT NULL,
  PRIMARY KEY (`cli_cfp`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Titulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Titulo` (
  `ti_id` INT NOT NULL,
  `ti_nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ti_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Estilo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Estilo` (
  `es_id` INT NOT NULL,
  `es_nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`es_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Autor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Autor` (
  `au_id` INT NOT NULL,
  `au_nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`au_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Fornecedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Fornecedor` (
  `for_id` INT NOT NULL,
  `for_nome` VARCHAR(45) NOT NULL,
  `for_status` CHAR NOT NULL,
  PRIMARY KEY (`for_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Genero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Genero` (
  `gen_id` INT NOT NULL,
  `gen_nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`gen_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Estante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Estante` (
  `es_id` INT NOT NULL,
  `es_letra` VARCHAR(1) NOT NULL,
  `es_prateleiras` SMALLINT NOT NULL,
  PRIMARY KEY (`es_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Prateleira`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Prateleira` (
  `pt_id` INT NOT NULL,
  `pt_numero` SMALLINT NOT NULL,
  PRIMARY KEY (`pt_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Produto` (
  `pd_id` INT NOT NULL,
  `pd_titulo` INT NOT NULL,
  `pd_autor` INT NOT NULL,
  `pd_fornecedor` INT NOT NULL,
  `pd_genero` TINYINT NOT NULL,
  `pd_preco` FLOAT NOT NULL,
  `pd_estante` INT NOT NULL,
  `pd_prateleira` INT NOT NULL,
  `pd_edicao` INT NOT NULL,
  `pd_status` CHAR NOT NULL,
  `pd_estilo` INT NOT NULL,
  PRIMARY KEY (`pd_id`),
  INDEX `fk_Produto_Titulo1_idx` (`pd_titulo` ASC) VISIBLE,
  INDEX `fk_Produto_Estilo1_idx` (`pd_estilo` ASC) VISIBLE,
  INDEX `fk_Produto_Autor1_idx` (`pd_autor` ASC) VISIBLE,
  INDEX `fk_Produto_Fornecedor1_idx` (`pd_fornecedor` ASC) VISIBLE,
  INDEX `fk_Produto_Genero1_idx` (`pd_genero` ASC) VISIBLE,
  INDEX `fk_Produto_Estante1_idx` (`pd_estante` ASC) VISIBLE,
  INDEX `fk_Produto_Prateleira1_idx` (`pd_prateleira` ASC) VISIBLE,
  CONSTRAINT `fk_Produto_Titulo1`
    FOREIGN KEY (`pd_titulo`)
    REFERENCES `mydb`.`Titulo` (`ti_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_Estilo1`
    FOREIGN KEY (`pd_estilo`)
    REFERENCES `mydb`.`Estilo` (`es_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_Autor1`
    FOREIGN KEY (`pd_autor`)
    REFERENCES `mydb`.`Autor` (`au_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_Fornecedor1`
    FOREIGN KEY (`pd_fornecedor`)
    REFERENCES `mydb`.`Fornecedor` (`for_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_Genero1`
    FOREIGN KEY (`pd_genero`)
    REFERENCES `mydb`.`Genero` (`gen_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_Estante1`
    FOREIGN KEY (`pd_estante`)
    REFERENCES `mydb`.`Estante` (`es_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_Prateleira1`
    FOREIGN KEY (`pd_prateleira`)
    REFERENCES `mydb`.`Prateleira` (`pt_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Estante_Prateleira`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Estante_Prateleira` (
  `ep_id` INT NOT NULL,
  `ep_estante` INT NOT NULL,
  `ep_prateleira` INT NOT NULL,
  PRIMARY KEY (`ep_id`),
  INDEX `fk_Estante_Prateleira_Estante1_idx` (`ep_estante` ASC) VISIBLE,
  INDEX `fk_Estante_Prateleira_Prateleira1_idx` (`ep_prateleira` ASC) VISIBLE,
  CONSTRAINT `fk_Estante_Prateleira_Estante1`
    FOREIGN KEY (`ep_estante`)
    REFERENCES `mydb`.`Estante` (`es_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Estante_Prateleira_Prateleira1`
    FOREIGN KEY (`ep_prateleira`)
    REFERENCES `mydb`.`Prateleira` (`pt_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Funcionarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Funcionarios` (
  `func_id` INT NOT NULL,
  `func_nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`func_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Venda` (
  `ve_id` INT NOT NULL,
  `ve_cliente` INT NOT NULL,
  `ve_produtos` INT NOT NULL,
  `ve_funcionario` INT NOT NULL,
  PRIMARY KEY (`ve_id`),
  INDEX `fk_Compra_Cliente1_idx` (`ve_cliente` ASC) VISIBLE,
  INDEX `fk_Venda_Funcionarios1_idx` (`ve_funcionario` ASC) VISIBLE,
  CONSTRAINT `fk_Compra_Cliente1`
    FOREIGN KEY (`ve_cliente`)
    REFERENCES `mydb`.`Cliente` (`cli_cfp`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Venda_Funcionarios1`
    FOREIGN KEY (`ve_funcionario`)
    REFERENCES `mydb`.`Funcionarios` (`func_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Itens`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Itens` (
  `cp_produto` INT NOT NULL,
  `cp_compras` INT NOT NULL,
  INDEX `fk_Compra_Produt_Produto1_idx` (`cp_produto` ASC) VISIBLE,
  INDEX `fk_Itens_Compra1_idx` (`cp_compras` ASC) VISIBLE,
  CONSTRAINT `fk_Compra_Produt_Produto1`
    FOREIGN KEY (`cp_produto`)
    REFERENCES `mydb`.`Produto` (`pd_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Itens_Compra1`
    FOREIGN KEY (`cp_compras`)
    REFERENCES `mydb`.`Venda` (`ve_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
