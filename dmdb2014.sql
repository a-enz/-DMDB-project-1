-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 26, 2014 at 03:11 PM
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `Cases`
--

INSERT INTO `Cases` (`CaseNr`, `Title`, `Date`, `Location`, `Status`, `DateCon`, `DateEnd`) VALUES
(1, 'Uebermuedetes Programmieren', '2014-05-25', 'CAB', 'closed', NULL, NULL),
(2, 'Car theft on drugs', '2014-01-30', 'Zurich HB', 'open', '2014-05-07', NULL),
(3, 'Naked Hiking', '2013-12-04', 'Appenzell', 'open', '2014-03-06', NULL),
(4, 'Bike theft', '2014-03-12', 'Zurich HB', 'closed', NULL, NULL),
(5, 'Family Drama', '2011-02-17', 'Bern', 'open', NULL, '2014-11-13'),
(6, 'Drunk Driving', '2013-06-13', NULL, 'closed', '2014-02-19', NULL),
(7, 'Museum Murder', '1999-06-16', 'Basel', 'open', NULL, '2017-06-14');

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

--
-- Dumping data for table `Category`
--

INSERT INTO `Category` (`CatName`, `Parent`, `Bounty`) VALUES
('Assault', '', 10000),
('Blackmailing', '', 2000),
('Exhibitionism', '', 100),
('Illegal Substances', '', 2700),
('Kidnapping', '', 6000),
('Murder', '', 4000),
('Other', '', 0),
('Theft', '', 1000);

-- --------------------------------------------------------

--
-- Table structure for table `Connected`
--

CREATE TABLE IF NOT EXISTS `Connected` (
  `CaseID` int(11) NOT NULL,
  `PersonID` int(11) NOT NULL,
  `Reason` varchar(30) DEFAULT NULL,
  `Role` varchar(30) NOT NULL,
  PRIMARY KEY (`CaseID`,`PersonID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Connected`
--

INSERT INTO `Connected` (`CaseID`, `PersonID`, `Reason`, `Role`) VALUES
(1, 1, '', 'perpetrator'),
(1, 2, 'Mother of the Victim', ''),
(1, 3, '', 'perpetrator'),
(2, 6, 'witnessed the deed', 'suspect'),
(2, 8, 'running from scene', 'suspect'),
(3, 7, 'not good looking', 'suspect'),
(4, 6, 'owner of bike', 'perpetrator'),
(5, 9, 'Father of victim', 'suspect'),
(5, 12, 'daughter and victim', 'suspect'),
(6, 11, '', 'perpetrator'),
(7, 6, 'guard on duty', 'suspect'),
(7, 8, 'middle man', 'suspect'),
(7, 13, 'Curator', 'suspect');

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
(1, 'Assault'),
(1, 'Kidnapping'),
(1, 'Landesverrat'),
(1, 'Theft'),
(2, 'Assault'),
(2, 'Industriespionage'),
(2, 'Theft'),
(3, 'Exhibitionism'),
(3, 'Illegal Substances'),
(4, 'Theft'),
(5, 'Assault'),
(5, 'Illegal Substances'),
(5, 'Kidnapping'),
(6, 'Illegal Substances'),
(6, 'Other'),
(7, 'Blackmailing'),
(7, 'Murder'),
(7, 'Other');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `Person`
--

INSERT INTO `Person` (`PersonID`, `FirstName`, `SurName`, `Street`, `BirthDate`, `Bounty`, `Nationality`) VALUES
(1, 'Andi', 'Enz', 'Sandgruebstrasse 22', NULL, 0, NULL),
(2, 'Matthias', 'Lei', NULL, '1999-02-15', 0, 'Schweiz'),
(3, 'Daniel', 'Yu', 'Seestrasse 6', '1978-06-04', 0, 'Arbonianer'),
(4, 'Heinz', 'Hoeller', NULL, '1970-07-18', 0, NULL),
(5, 'Bild', 'Schirm', NULL, NULL, 0, NULL),
(6, 'Bert', 'Binder', 'Hauptstrasse 7', '2014-05-22', 0, 'swiss'),
(7, 'Horst', 'Meier', 'Hauptstrasse 10', '2003-06-18', 0, 'Austrian'),
(8, 'Dave', 'McHerbert', 'Main Street 13', '1989-02-07', 0, 'irish'),
(9, 'Elias', 'Schubert', 'Second Street 84', '1945-04-05', 0, 'american'),
(10, 'Nina', 'Meier', NULL, '1994-06-14', 0, 'Swiss'),
(11, 'Barbara', 'Konrad', 'Baumstrasse 89', '1975-07-08', 0, NULL),
(12, 'Erika', 'Schubert', 'Schwarzweg 7', '1980-04-07', 0, 'swiss'),
(13, 'Diana', 'Zinder', 'Third Street 109', '1987-12-05', 0, 'american');

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
