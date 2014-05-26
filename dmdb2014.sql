-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 26, 2014 at 11:43 AM
-- Server version: 5.5.37
-- PHP Version: 5.3.10-1ubuntu3.11

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `dmdb2014`
--

-- --------------------------------------------------------

--
-- Table structure for table `CaseNote`
--

CREATE TABLE IF NOT EXISTS `CaseNote` (
  `NoteNr` int(11) NOT NULL AUTO_INCREMENT,
  `CaseID` int(11) NOT NULL,
  `Username` varchar(30) NOT NULL,
  `Text` varchar(255) NOT NULL,
  PRIMARY KEY (`NoteNr`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `CaseNote`
--

INSERT INTO `CaseNote` (`NoteNr`, `CaseID`, `Username`, `Text`) VALUES
(1, 1, 'admin', 'This is my first comment!'),
(2, 1, 'admin', 'This is my first comment!'),
(3, 1, 'admin', 'This is my first comment!'),
(5, 1, 'admin', 'blabla\r\n');

-- --------------------------------------------------------

--
-- Table structure for table `Cases`
--

CREATE TABLE IF NOT EXISTS `Cases` (
  `CaseNr` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(30) NOT NULL,
  `Date` date NOT NULL,
  `Location` varchar(30) DEFAULT NULL,
  `Status` varchar(30) NOT NULL,
  `DateCon` date DEFAULT NULL,
  `DateEnd` date DEFAULT NULL,
  PRIMARY KEY (`CaseNr`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `Cases`
--

INSERT INTO `Cases` (`CaseNr`, `Title`, `Date`, `Location`, `Status`, `DateCon`, `DateEnd`) VALUES
(1, 'Uebermuedetes Programmieren', '2014-05-25', 'CAB', 'open', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `Category`
--

CREATE TABLE IF NOT EXISTS `Category` (
  `CatName` varchar(30) NOT NULL,
  `Parent` varchar(30) NOT NULL,
  `Bounty` int(11) NOT NULL,
  PRIMARY KEY (`CatName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Connected`
--

CREATE TABLE IF NOT EXISTS `Connected` (
  `CaseID` int(11) NOT NULL,
  `PersonID` int(11) NOT NULL,
  `Reason` varchar(30) NOT NULL,
  `Role` varchar(30) NOT NULL,
  PRIMARY KEY (`CaseID`,`PersonID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Connected`
--

INSERT INTO `Connected` (`CaseID`, `PersonID`, `Reason`, `Role`) VALUES
(1, 2, 'Mother of the Victim', ''),
(2, 3, 'Friend of the Victim', '');

-- --------------------------------------------------------

--
-- Table structure for table `ContainedIn`
--

CREATE TABLE IF NOT EXISTS `ContainedIn` (
  `CaseID` int(11) NOT NULL,
  `CatName` varchar(30) NOT NULL,
  PRIMARY KEY (`CaseID`,`CatName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ContainedIn`
--

INSERT INTO `ContainedIn` (`CaseID`, `CatName`) VALUES
(1, 'Landesverrat'),
(2, 'Industriespionage');

-- --------------------------------------------------------

--
-- Table structure for table `Person`
--

CREATE TABLE IF NOT EXISTS `Person` (
  `PersonID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(30) NOT NULL,
  `SurName` varchar(30) NOT NULL,
  `Street` varchar(30) DEFAULT NULL,
  `BirthDate` date DEFAULT NULL,
  `Bounty` int(11) DEFAULT NULL,
  `Nationality` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`PersonID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `Person`
--

INSERT INTO `Person` (`PersonID`, `FirstName`, `SurName`, `Street`, `BirthDate`, `Bounty`, `Nationality`) VALUES
(1, 'Andi', 'Enz', 'Sandgruebstrasse 22', NULL, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `PersonNote`
--

CREATE TABLE IF NOT EXISTS `PersonNote` (
  `NoteNr` int(11) NOT NULL AUTO_INCREMENT,
  `PersonID` int(11) NOT NULL,
  `Username` varchar(30) NOT NULL,
  `Text` varchar(255) NOT NULL,
  PRIMARY KEY (`NoteNr`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `PersonNote`
--

INSERT INTO `PersonNote` (`NoteNr`, `PersonID`, `Username`, `Text`) VALUES
(1, 1, 'admin', 'this is my first comment. Lets see how it works!'),
(2, 1, 'admin', 'this is my second comment. It seems that the comment works fine =)!'),
(3, 2, 'admin', 'lets see if the autoincrement works!'),
(4, 2, 'admin', 'blabla'),
(5, 1, 'admin', 'lets see how it works.'),
(6, 3, 'admin', 'this is my first comment.'),
(7, 3, 'admin', 'this is my first comment.'),
(8, 3, 'admin', 'this is my first comment.'),
(9, 1, 'admin', 'hallihallo'),
(10, 1, 'admin', 'test lei'),
(11, 1, 'admin', 'test lei'),
(12, 3, 'admin', 'APPROACH WITH EXTREME CAUTION!!!'),
(13, 1, 'admin', 'Notorious Chicken Thief'),
(14, 2, 'admin', 'Crucified Jesus'),
(15, 0, 'admin', 'Exhibitionism\r\nPublic Swearing\r\n'),
(16, 0, 'admin', 'Exhibitionism\r\nPublic Swearing\r\n'),
(17, 0, 'admin', 'Some other notes'),
(18, 0, 'admin', 'blabla');

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `Username` varchar(30) NOT NULL,
  `Passwort` varchar(30) NOT NULL,
  `Name` varchar(30) NOT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`Username`, `Passwort`, `Name`) VALUES
('admin', '1234', 'Daniel'),
('andi', 'blarg', 'Andreas');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
