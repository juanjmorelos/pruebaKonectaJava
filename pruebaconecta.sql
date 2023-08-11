-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-08-2023 a las 04:55:19
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pruebaconecta`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `idProducto` int(11) NOT NULL,
  `nombreProd` varchar(100) NOT NULL COMMENT 'Nombre del producto',
  `referenciaProd` varchar(100) NOT NULL,
  `precioProd` int(11) NOT NULL COMMENT 'Precio de los productos',
  `pesoProd` int(11) NOT NULL COMMENT 'Peso del producto en kilogramos',
  `categoriaProd` varchar(100) NOT NULL COMMENT 'Categoria del producto',
  `stockProd` int(11) NOT NULL COMMENT 'Cantidad de productos en bodega',
  `prodCreateDate` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT 'Fecha en la que se creo el producto'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`idProducto`, `nombreProd`, `referenciaProd`, `precioProd`, `pesoProd`, `categoriaProd`, `stockProd`, `prodCreateDate`) VALUES
(18, 'Mouse', 'Referencia mouse', 150000, 45, 'Aparato tecnologico', 0, '2023-08-10 12:59:20'),
(19, 'Computador', 'Computador portatil', 4500000, 150, '1', 0, '2023-08-10 13:18:35'),
(20, 'celular', 'samsung', 7000000, 25, 'Tecnologia', 78, '2023-08-11 00:24:53'),
(21, 'balon', 'balon de basketbol', 400000, 50, 'deportes', 14, '2023-08-11 00:32:42'),
(30, 'Parlante', 'Lg', 400000, 5, 'Sonido', 6, '2023-08-11 01:12:01'),
(31, 'Silla gamer', 'Silla gamer amarilla', 200560, 100, 'Sillas', 38, '2023-08-11 02:36:32');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `idVenta` int(11) NOT NULL,
  `producto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `fechaVenta` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`idVenta`, `producto`, `cantidad`, `fechaVenta`) VALUES
(2, 18, 5, '2023-08-10 13:00:16'),
(3, 18, 1, '2023-08-10 13:00:25'),
(4, 19, 10, '2023-08-10 23:50:21'),
(5, 20, 20, '2023-08-11 00:25:10'),
(6, 21, 31, '2023-08-11 00:32:49'),
(7, 18, 40, '2023-08-11 00:33:54'),
(9, 21, 5, '2023-08-11 02:24:42'),
(10, 30, 3, '2023-08-11 02:26:47'),
(11, 21, 2, '2023-08-11 02:29:25'),
(12, 18, 2, '2023-08-11 02:30:20'),
(13, 18, 2, '2023-08-11 02:31:42'),
(14, 20, 2, '2023-08-11 02:32:11'),
(15, 19, 1, '2023-08-11 02:33:09'),
(16, 21, 3, '2023-08-11 02:33:21'),
(17, 31, 62, '2023-08-11 02:37:39');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`idProducto`),
  ADD KEY `categoriaProd` (`categoriaProd`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`idVenta`),
  ADD KEY `idVentaDetail` (`producto`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `idProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `idVenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
