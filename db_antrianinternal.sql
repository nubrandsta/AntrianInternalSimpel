-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 07, 2021 at 03:41 PM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 7.3.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_antrianinternal`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_antrian`
--

CREATE TABLE `tb_antrian` (
  `id_pesanan` varchar(12) NOT NULL,
  `id_menu` varchar(4) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `no_urut` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_antrian`
--

INSERT INTO `tb_antrian` (`id_pesanan`, `id_menu`, `jumlah`, `no_urut`) VALUES
('AB0711210103', 'AB01', 2, 3),
('AG0711210105', 'AG01', 2, 5),
('AG0711210204', 'AG02', 1, 4),
('MA0711210101', 'MA01', 1, 1),
('MA0711210202', 'MA02', 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `tb_menu`
--

CREATE TABLE `tb_menu` (
  `id_menu` varchar(4) NOT NULL,
  `nama_menu` varchar(25) NOT NULL,
  `harga_menu` int(11) NOT NULL,
  `kategori_menu` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_menu`
--

INSERT INTO `tb_menu` (`id_menu`, `nama_menu`, `harga_menu`, `kategori_menu`) VALUES
('AB01', 'Ayam Bakar', 12000, 'Ayam Bakar'),
('AG01', 'Ayam Goreng', 7000, 'Ayam Goreng'),
('AG02', 'Ayam Geprek', 10000, 'Ayam Goreng'),
('AG03', 'Ayam Penyet', 10000, 'Ayam Goreng'),
('MA01', 'Mie Ayam', 10000, 'Mie Ayam'),
('MA02', 'Bakso', 10000, 'Mie Ayam');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_antrian`
--
ALTER TABLE `tb_antrian`
  ADD PRIMARY KEY (`id_pesanan`);

--
-- Indexes for table `tb_menu`
--
ALTER TABLE `tb_menu`
  ADD PRIMARY KEY (`id_menu`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
