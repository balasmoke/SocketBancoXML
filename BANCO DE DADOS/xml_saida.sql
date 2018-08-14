-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 14-Ago-2018 às 17:58
-- Versão do servidor: 10.1.30-MariaDB
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `xml_saida`
--
CREATE DATABASE IF NOT EXISTS `xml_saida` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `xml_saida`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos`
--

CREATE TABLE `produtos` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `preco` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `produtos`
--

INSERT INTO `produtos` (`id`, `nome`, `preco`) VALUES
(1, 'Arroz', '5.20'),
(2, 'acucar', '4.20'),
(3, 'cha', '2.50'),
(4, 'coco ralado', '3.22'),
(5, 'Feijão', '6.00'),
(6, 'gelatina', '1.50'),
(7, 'farinha de trigo', '7.30'),
(8, 'leite', '6.30'),
(9, 'cafe', '5.40'),
(10, 'fermento', '10.00'),
(11, 'azeite', '5.50'),
(12, 'sal', '6.30'),
(13, 'carne', '30.00'),
(14, 'peixe', '15.33'),
(15, 'adocante', '7.30'),
(16, 'fermento', '6.40'),
(17, 'tomate', '4.30'),
(18, 'laranja', '2.20'),
(19, 'limao', '11.30'),
(20, 'banana', '7.30'),
(21, 'achocolatado', '8.20'),
(22, 'agua', '2.50'),
(23, 'coca cola', '5.00'),
(24, 'fanta', '4.50'),
(25, 'oleo', '4.30'),
(26, 'creme de leite', '5.00'),
(27, 'chu chu', '15.00'),
(28, 'gilo', '22.40'),
(29, 'bolacha', '2.75'),
(30, 'massa de pastel', '7.50');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `produtos`
--
ALTER TABLE `produtos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
