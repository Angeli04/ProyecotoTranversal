CREATE DATABASE `universidad`;
 
USE `universidad`;
 
CREATE TABLE `alumnos` (
  `idAlumno` int(11) NOT NULL AUTO_INCREMENT,
  `apellido` VARCHAR(32) NOT NULL,
  `nombre` VARCHAR(32) NOT NULL,
  `dni` VARCHAR(16) NOT NULL,
  `fechaNacimiento` DATE NOT NULL,
  `estado` tinyint(4) NOT NULL DEFAULT 1,
  PRIMARY KEY (`idAlumno`)
) ENGINE=InnoDB;

ALTER TABLE `alumnos` ADD UNIQUE(`dni`);

CREATE TABLE `materias` (
  `idMateria` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(32) NOT NULL,
  `periodo` int(11) NOT NULL,
  `estado` tinyint(4) NOT NULL DEFAULT 1,
  PRIMARY KEY (`idMateria`)
) ENGINE=InnoDB;

CREATE TABLE `inscripciones` (
  `idInscripcion` int(11) NOT NULL AUTO_INCREMENT,
  `idAlumno` int(11) NOT NULL,
  `idMateria` int(11) NOT NULL,
  `notaFinal` decimal(10,2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idInscripcion`)
) ENGINE=InnoDB;
 
ALTER TABLE `inscripciones`
  ADD CONSTRAINT `inscripciones_idAlumno` FOREIGN KEY (`idAlumno`) REFERENCES `alumnos` (`idAlumno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `inscripciones_idMateria` FOREIGN KEY (`idMateria`) REFERENCES `materias` (`idMateria`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `inscripciones` ADD UNIQUE(`idAlumno`, `idMateria`);


/* 1. Dar de alta 3 alumnos */
INSERT INTO `alumnos` (`apellido`, `nombre`, `dni`, `fechaNacimiento`) VALUES
  ('Lucero', 'Juan', '30000001', '2000-04-01'),
  ('Robledo', 'Damian', '30000002', '2000-06-15'),
  ('Gimenez', 'Ernesto', '30000003', '1996-07-07'),
  ('Ramirez', 'Rebeca', '30000004', '1988-08-10'),
  ('Valdez', 'Alberto', '27822898', '1979-12-30');
 
/* 2. Dar de alta 5 materias */
INSERT INTO `materias` (`nombre`, `periodo`) VALUES
  ('Laboratorio I', '1'),
  ('Laboratorio II', '2'),
  ('Moviles', '2'),
  ('Web I', '1'),
  ('Web II', '2');

/* 3. Inscribir a cada alumno en 2 materias con nota 0 */
INSERT INTO `inscripciones` (`idAlumno`, `idMateria`) VALUES
  ('1', '1'),
  ('1', '4'),
  ('2', '2'),
  ('2', '5'),
  ('3', '2'),
  ('3', '3'),
  ('4', '1'),
  ('4', '3'),
  ('5', '2'),
  ('5', '3'),
  ('5', '5');
