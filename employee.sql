-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 28, 2023 at 10:39 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `employee`
--

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `id` int(12) NOT NULL,
  `in_time` varchar(50) NOT NULL,
  `out_time` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `attend_history`
--

CREATE TABLE `attend_history` (
  `id` int(12) NOT NULL,
  `in_time` varchar(40) NOT NULL,
  `out_time` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `emp_details`
--

CREATE TABLE `emp_details` (
  `id` varchar(12) NOT NULL,
  `fname` varchar(15) NOT NULL,
  `mname` varchar(15) NOT NULL,
  `lname` varchar(15) NOT NULL,
  `dob` varchar(10) NOT NULL,
  `job` varchar(15) NOT NULL,
  `hire` varchar(10) NOT NULL,
  `city` varchar(15) NOT NULL,
  `postal` varchar(7) NOT NULL,
  `address` varchar(70) NOT NULL,
  `gender` varchar(6) NOT NULL,
  `marital` varchar(7) NOT NULL,
  `phone1` varchar(15) NOT NULL,
  `phone2` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `emp_details`
--

INSERT INTO `emp_details` (`id`, `fname`, `mname`, `lname`, `dob`, `job`, `hire`, `city`, `postal`, `address`, `gender`, `marital`, `phone1`, `phone2`) VALUES
('1', 'Pramod', 'Pruthvi', 'anuradhapura', '20/01/2023', 'Director', '06/01/2023', 'Anutradhapura', '900', 'anuradhapura', 'Male', 'Single', '0215666', 'P@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `leave_r`
--

CREATE TABLE `leave_r` (
  `fname` varchar(10) NOT NULL,
  `id` varchar(12) NOT NULL,
  `title` varchar(20) NOT NULL,
  `reason` varchar(30) NOT NULL,
  `days` varchar(2) NOT NULL,
  `start` varchar(10) NOT NULL,
  `end` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `user` varchar(12) NOT NULL,
  `password` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`user`, `password`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `project_table`
--

CREATE TABLE `project_table` (
  `pid` int(100) NOT NULL,
  `pname` varchar(100) NOT NULL,
  `sdate` varchar(10) NOT NULL,
  `edate` varchar(10) NOT NULL,
  `pmanager` varchar(100) NOT NULL,
  `des` varchar(100) NOT NULL,
  `status` varchar(50) NOT NULL,
  `budget` varchar(10) NOT NULL,
  `cos` varchar(100) NOT NULL,
  `location` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `salaryhistory`
--

CREATE TABLE `salaryhistory` (
  `empid` varchar(50) NOT NULL,
  `date` varchar(50) NOT NULL,
  `previous_sal` varchar(50) NOT NULL,
  `new_sal` varchar(50) NOT NULL,
  `reason` varchar(500) NOT NULL,
  `changedby` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `project_table`
--
ALTER TABLE `project_table`
  ADD PRIMARY KEY (`pid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
