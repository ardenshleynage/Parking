-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 15, 2024 at 06:27 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `parking`
--

-- --------------------------------------------------------

--
-- Table structure for table `maintenance_list`
--

CREATE TABLE `maintenance_list` (
  `id` int(11) NOT NULL,
  `type` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `access` int(11) NOT NULL,
  `add_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `maintenance_list`
--

INSERT INTO `maintenance_list` (`id`, `type`, `description`, `price`, `access`, `add_date`) VALUES
(9, 'Vidange d\'huile', 'Remplacer l\'huile moteur et le filtre a huile pour assurer une bonne lubrification du moteur.', 30000.00, 1, '2024-07-14'),
(10, 'Verification des phares et feux', 'S\'assurer que tous les phares, feux de freinage, clignotants et feux arriere fonctionnent correctement.', 3000.00, 1, '2024-07-14'),
(12, 'Inspection des freins', 'Verifier l\'etat des plaquettes de frein, des disques et du liquide de frein pour assurer un freinage optimal.', 10000.00, 1, '2024-07-14');

-- --------------------------------------------------------

--
-- Table structure for table `maintenance_meet_up`
--

CREATE TABLE `maintenance_meet_up` (
  `id` int(11) NOT NULL,
  `id_maintenance` int(11) NOT NULL,
  `type_maintenance` varchar(255) NOT NULL,
  `description_maintenance` varchar(255) NOT NULL,
  `price_maintenance` decimal(10,2) NOT NULL,
  `id_vehicle` int(11) NOT NULL,
  `numb_plate_vehicle` varchar(255) NOT NULL,
  `mark_vehicle` varchar(255) NOT NULL,
  `model_vehicle` varchar(255) NOT NULL,
  `color_vehicle` varchar(255) NOT NULL,
  `year_vehicle` int(11) NOT NULL,
  `access` int(11) NOT NULL,
  `add_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `maintenance_meet_up`
--

INSERT INTO `maintenance_meet_up` (`id`, `id_maintenance`, `type_maintenance`, `description_maintenance`, `price_maintenance`, `id_vehicle`, `numb_plate_vehicle`, `mark_vehicle`, `model_vehicle`, `color_vehicle`, `year_vehicle`, `access`, `add_date`) VALUES
(9, 10, 'Inspection des freins', 'Verifier l\'etat des plaquettes de frein, des disques et du liquide de frein pour assurer un freinage optimal.', 10000.00, 9, '11-WW234', 'Ford', 'Focus', 'Gris', 2024, 1, '2024-07-14'),
(10, 10, 'Verification des phares et feux', 'S\'assurer que tous les phares, feux de freinage, clignotants et feux arriere fonctionnent correctement.', 3000.00, 10, '00-DDEE1', 'Honda', 'Civic', 'Rouge', 2023, 1, '2024-07-14');

-- --------------------------------------------------------

--
-- Table structure for table `reparations_list`
--

CREATE TABLE `reparations_list` (
  `id` int(11) NOT NULL,
  `problem` text NOT NULL,
  `solution` text NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `access` int(11) NOT NULL,
  `add_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reparations_list`
--

INSERT INTO `reparations_list` (`id`, `problem`, `solution`, `price`, `access`, `add_date`) VALUES
(5, 'Changement de la courroie de distribution', 'Remplacer la courroie de distribution par une neuve et verifier l\'alignement des poulies.', 10000.00, 1, '2024-07-14'),
(7, 'Reparation du moteur', 'Diagnostiquer les problemes du moteur, remplacer les pieces defectueuses et effectuer les reglages necessaires.', 4000.00, 1, '2024-07-14'),
(8, 'Reparation du systeme d\'echappement', 'Identifier et reparer les fuites, remplacer les pieces endommagees, et verifier les emissions.', 8000.00, 1, '2024-07-14');

-- --------------------------------------------------------

--
-- Table structure for table `reparations_meet_up`
--

CREATE TABLE `reparations_meet_up` (
  `id` int(11) NOT NULL,
  `id_reparation` int(11) NOT NULL,
  `problem_reparation` text NOT NULL,
  `solution_reparation` text NOT NULL,
  `price_reparation` decimal(10,2) NOT NULL,
  `id_vehicle` int(11) NOT NULL,
  `numb_plate_vehicle` varchar(255) NOT NULL,
  `mark_vehicle` varchar(255) NOT NULL,
  `model_vehicle` varchar(255) NOT NULL,
  `color_vehicle` varchar(255) NOT NULL,
  `year_vehicle` int(11) NOT NULL,
  `access` int(11) NOT NULL,
  `add_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `vehicule`
--

CREATE TABLE `vehicule` (
  `id` int(11) NOT NULL,
  `numb_plate` varchar(255) NOT NULL,
  `mark` varchar(255) NOT NULL,
  `model` varchar(255) NOT NULL,
  `year` int(11) NOT NULL,
  `color` varchar(255) NOT NULL,
  `kilometer` int(11) NOT NULL,
  `access` int(11) NOT NULL,
  `add_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `vehicule`
--

INSERT INTO `vehicule` (`id`, `numb_plate`, `mark`, `model`, `year`, `color`, `kilometer`, `access`, `add_date`) VALUES
(9, '11-WW234', 'Ford', 'Focus', 2024, 'Gris', 120000, 1, '2024-07-14'),
(10, '00-DDEE1', 'Honda', 'Civic', 2023, 'Rouge', 250000, 1, '2024-07-14');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `maintenance_list`
--
ALTER TABLE `maintenance_list`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `maintenance_meet_up`
--
ALTER TABLE `maintenance_meet_up`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_maintenance` (`id_maintenance`),
  ADD KEY `id_vehicle` (`id_vehicle`);

--
-- Indexes for table `reparations_list`
--
ALTER TABLE `reparations_list`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reparations_meet_up`
--
ALTER TABLE `reparations_meet_up`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_reparation` (`id_reparation`,`id_vehicle`),
  ADD KEY `id_vehicle` (`id_vehicle`);

--
-- Indexes for table `vehicule`
--
ALTER TABLE `vehicule`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `maintenance_list`
--
ALTER TABLE `maintenance_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `maintenance_meet_up`
--
ALTER TABLE `maintenance_meet_up`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `reparations_list`
--
ALTER TABLE `reparations_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `reparations_meet_up`
--
ALTER TABLE `reparations_meet_up`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `vehicule`
--
ALTER TABLE `vehicule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `maintenance_meet_up`
--
ALTER TABLE `maintenance_meet_up`
  ADD CONSTRAINT `maintenance_meet_up_ibfk_1` FOREIGN KEY (`id_maintenance`) REFERENCES `maintenance_list` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `maintenance_meet_up_ibfk_2` FOREIGN KEY (`id_vehicle`) REFERENCES `vehicule` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `reparations_meet_up`
--
ALTER TABLE `reparations_meet_up`
  ADD CONSTRAINT `reparations_meet_up_ibfk_1` FOREIGN KEY (`id_reparation`) REFERENCES `reparations_list` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reparations_meet_up_ibfk_2` FOREIGN KEY (`id_vehicle`) REFERENCES `vehicule` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
