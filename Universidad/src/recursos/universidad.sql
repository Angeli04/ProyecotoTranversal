--
-- Base de datos: `universidad`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE `alumnos` (
  `idAlumno` int(11) NOT NULL,
  `apellido` varchar(32) NOT NULL,
  `nombre` varchar(32) NOT NULL,
  `dni` varchar(16) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `estado` tinyint(4) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`idAlumno`, `apellido`, `nombre`, `dni`, `fechaNacimiento`, `estado`) VALUES
(1, 'Lucero', 'Alejandro', '30000001', '2000-04-01', 1),
(2, 'Robledo', 'Esteban', '30000002', '2000-06-15', 1),
(3, 'Gimenez', 'Marcelo', '30000003', '1996-07-07', 1),
(4, 'Ramirez', 'Rebeca', '30000004', '1988-08-10', 1),
(5, 'Valdez', 'Antonio', '27822898', '1979-12-30', 1),
(6, 'Barrios', 'Juan', '32000001', '2000-01-15', 1),
(7, 'Lucero', 'Melisa', '32000002', '2002-04-25', 1),
(8, 'Zarate', 'Mariana', '32000004', '2001-02-18', 1),
(9, 'Paez', 'Carlos', '32000003', '2003-09-30', 1),
(11, 'Filippova', 'Iulia Valeria', '94286195', '1988-09-05', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inscripciones`
--

CREATE TABLE `inscripciones` (
  `idInscripcion` int(11) NOT NULL,
  `idAlumno` int(11) NOT NULL,
  `idMateria` int(11) NOT NULL,
  `notaFinal` decimal(10,2) NOT NULL DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `inscripciones`
--

INSERT INTO `inscripciones` (`idInscripcion`, `idAlumno`, `idMateria`, `notaFinal`) VALUES
(1, 1, 1, '7.50'),
(2, 1, 4, '0.00'),
(3, 2, 2, '0.00'),
(4, 2, 5, '0.00'),
(5, 3, 2, '0.00'),
(6, 3, 3, '0.00'),
(7, 4, 1, '0.00'),
(8, 4, 3, '0.00'),
(12, 7, 1, '0.00'),
(13, 7, 2, '0.00'),
(14, 7, 4, '0.00'),
(17, 7, 3, '0.00'),
(18, 2, 1, '0.00'),
(19, 6, 1, '10.00'),
(20, 9, 2, '0.00'),
(22, 5, 1, '8.00'),
(23, 11, 1, '0.00'),
(25, 11, 5, '0.00'),
(26, 7, 5, '0.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materias`
--

CREATE TABLE `materias` (
  `idMateria` int(11) NOT NULL,
  `nombre` varchar(32) NOT NULL,
  `periodo` int(11) NOT NULL,
  `estado` tinyint(4) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `materias`
--

INSERT INTO `materias` (`idMateria`, `nombre`, `periodo`, `estado`) VALUES
(1, 'Laboratorio 3', 1, 1),
(2, 'Laboratorio 2', 2, 1),
(3, 'Moviles', 2, 1),
(4, 'Web I', 1, 0),
(5, 'Web II', 2, 1),
(8, 'Matematicas 2', 2, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`idAlumno`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `inscripciones`
--
ALTER TABLE `inscripciones`
  ADD PRIMARY KEY (`idInscripcion`),
  ADD UNIQUE KEY `idAlumno` (`idAlumno`,`idMateria`),
  ADD KEY `inscripciones_idMateria` (`idMateria`);

--
-- Indices de la tabla `materias`
--
ALTER TABLE `materias`
  ADD PRIMARY KEY (`idMateria`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  MODIFY `idAlumno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `inscripciones`
--
ALTER TABLE `inscripciones`
  MODIFY `idInscripcion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `materias`
--
ALTER TABLE `materias`
  MODIFY `idMateria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `inscripciones`
--
ALTER TABLE `inscripciones`
  ADD CONSTRAINT `inscripciones_idAlumno` FOREIGN KEY (`idAlumno`) REFERENCES `alumnos` (`idAlumno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `inscripciones_idMateria` FOREIGN KEY (`idMateria`) REFERENCES `materias` (`idMateria`) ON DELETE NO ACTION ON UPDATE NO ACTION;
