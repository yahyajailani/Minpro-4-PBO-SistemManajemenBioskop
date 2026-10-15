-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 15, 2026 at 06:04 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sistem_manajemen_bioskop`
--

-- --------------------------------------------------------

--
-- Table structure for table `film`
--

CREATE TABLE `film` (
  `id_film` int(11) NOT NULL,
  `Judul` varchar(100) NOT NULL,
  `genre` varchar(50) NOT NULL,
  `durasi` int(11) NOT NULL,
  `harga_tiket` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `film`
--

INSERT INTO `film` (`id_film`, `Judul`, `genre`, `durasi`, `harga_tiket`) VALUES
(1, 'Avenger: Endgame', 'Action, Sci-fi', 180, 50000),
(2, 'Interstellar', 'Sci-fi', 150, 45000),
(3, 'Pabrik Rambut', 'Thriller', 170, 50000),
(4, 'Inside Out', 'Fantasy', 200, 45000),
(5, 'Ghost in the Cell', 'Horror', 170, 50000),
(6, 'Kuasa Gelap: 2', 'Horror', 130, 25000),
(7, 'Spongebob on the run', 'Comedy', 160, 50000);

-- --------------------------------------------------------

--
-- Table structure for table `penonton`
--

CREATE TABLE `penonton` (
  `id_penonton` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `no_hp` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `penonton`
--

INSERT INTO `penonton` (`id_penonton`, `nama`, `email`, `no_hp`, `password`) VALUES
(1, 'Andi Saputra', 'andi@gmail.com', '081234567801', 'andi123'),
(2, 'Budi Santoso', 'budi@gmail.com', '081234567802', 'budi123'),
(3, 'Citra Lestari', 'citra@gmail.com', '081234567803', 'citra123'),
(4, 'Dina Amelia', 'dina@gmail.com', '081234567804', 'dina123'),
(5, 'rifa', 'rifa@gmail.com', '0812638497374', 'rifa123'),
(6, 'yahy', 'yahya@gmail.com', '081234567890', 'yahya1');

-- --------------------------------------------------------

--
-- Table structure for table `studio`
--

CREATE TABLE `studio` (
  `id_studio` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `kapasitas` int(11) NOT NULL,
  `tipe` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `studio`
--

INSERT INTO `studio` (`id_studio`, `nama`, `kapasitas`, `tipe`) VALUES
(1, 'Studio 1', 100, 'Regular'),
(2, 'Studio 2', 80, 'VIP'),
(3, 'Studio 3', 120, 'Regular'),
(4, 'Studio 4', 60, 'VIP'),
(5, 'Studio 5', 100, 'Regular');

-- --------------------------------------------------------

--
-- Table structure for table `tiket`
--

CREATE TABLE `tiket` (
  `id_tiket` int(11) NOT NULL,
  `id_penonton` int(11) NOT NULL,
  `id_film` int(11) NOT NULL,
  `id_studio` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `harga_tiket` double NOT NULL,
  `total_harga` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tiket`
--

INSERT INTO `tiket` (`id_tiket`, `id_penonton`, `id_film`, `id_studio`, `jumlah`, `harga_tiket`, `total_harga`) VALUES
(1, 1, 1, 1, 2, 50000, 100000),
(2, 2, 2, 2, 1, 45000, 45000),
(3, 3, 3, 3, 2, 50000, 100000),
(4, 4, 4, 4, 1, 45000, 45000),
(5, 1, 3, 1, 2, 50000, 100000),
(6, 1, 3, 1, 1, 50000, 50000),
(7, 1, 5, 2, 1, 60000, 60000),
(8, 5, 5, 3, 1, 50000, 50000),
(9, 1, 3, 2, 1, 60000, 60000);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `kode_transaksi` varchar(100) NOT NULL,
  `id_tiket` int(11) NOT NULL,
  `total_awal` double NOT NULL,
  `diskon` double NOT NULL DEFAULT 0,
  `total_bayar` double NOT NULL,
  `tanggal_transaksi` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `kode_transaksi`, `id_tiket`, `total_awal`, `diskon`, `total_bayar`, `tanggal_transaksi`) VALUES
(1, 'TRX001', 1, 100000, 10000, 90000, '2026-10-12 09:59:25'),
(2, 'TRX002', 2, 55000, 5000, 50000, '2026-10-12 09:59:25'),
(3, 'TRX003', 3, 100000, 10000, 90000, '2026-10-12 09:59:25'),
(4, 'TRX004', 4, 55000, 0, 55000, '2026-10-12 09:59:25'),
(5, 'TRX-1791953780286', 5, 100000, 10000, 90000, '2026-10-14 04:56:20'),
(6, 'TRX-1791957641376', 6, 50000, 2500, 47500, '2026-10-14 06:00:41'),
(7, 'TRX-1791958022745', 7, 60000, 3000, 57000, '2026-10-14 06:07:02'),
(8, 'TRX-1792035995273', 9, 60000, 3000, 57000, '2026-10-15 03:46:35');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `film`
--
ALTER TABLE `film`
  ADD PRIMARY KEY (`id_film`);

--
-- Indexes for table `penonton`
--
ALTER TABLE `penonton`
  ADD PRIMARY KEY (`id_penonton`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `studio`
--
ALTER TABLE `studio`
  ADD PRIMARY KEY (`id_studio`);

--
-- Indexes for table `tiket`
--
ALTER TABLE `tiket`
  ADD PRIMARY KEY (`id_tiket`),
  ADD KEY `id_penonton` (`id_penonton`),
  ADD KEY `id_studio` (`id_studio`),
  ADD KEY `fk_tiket_film` (`id_film`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD UNIQUE KEY `kode_transaksi` (`kode_transaksi`),
  ADD KEY `id_tiket` (`id_tiket`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `film`
--
ALTER TABLE `film`
  MODIFY `id_film` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `penonton`
--
ALTER TABLE `penonton`
  MODIFY `id_penonton` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `studio`
--
ALTER TABLE `studio`
  MODIFY `id_studio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tiket`
--
ALTER TABLE `tiket`
  MODIFY `id_tiket` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tiket`
--
ALTER TABLE `tiket`
  ADD CONSTRAINT `fk_tiket_film` FOREIGN KEY (`id_film`) REFERENCES `film` (`id_film`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tiket_ibfk_1` FOREIGN KEY (`id_penonton`) REFERENCES `penonton` (`id_penonton`),
  ADD CONSTRAINT `tiket_ibfk_3` FOREIGN KEY (`id_studio`) REFERENCES `studio` (`id_studio`);

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_tiket`) REFERENCES `tiket` (`id_tiket`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
