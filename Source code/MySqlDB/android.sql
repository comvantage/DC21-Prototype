-- phpMyAdmin SQL Dump
-- version 3.5.3
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 21, 2014 at 03:18 PM
-- Server version: 5.5.37-MariaDB
-- PHP Version: 5.5.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `android`
--

-- --------------------------------------------------------

--
-- Table structure for table `android`
--

CREATE TABLE IF NOT EXISTS `android` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Ime` varchar(255) COLLATE utf8_slovenian_ci NOT NULL,
  `Naziv` varchar(255) COLLATE utf8_slovenian_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `android`
--

INSERT INTO `android` (`ID`, `Ime`, `Naziv`) VALUES
(1, 'saso', 'Test!!!!!!!!!!!!!!!!');

-- --------------------------------------------------------

--
-- Table structure for table `cv_challanges`
--

CREATE TABLE IF NOT EXISTS `cv_challanges` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `cv_challangesideadetail`
--

CREATE TABLE IF NOT EXISTS `cv_challangesideadetail` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `cv_challangesideas`
--

CREATE TABLE IF NOT EXISTS `cv_challangesideas` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `cv_challangesparticipate`
--

CREATE TABLE IF NOT EXISTS `cv_challangesparticipate` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(255) COLLATE utf8_slovenian_ci NOT NULL,
  `Odakle` varchar(255) COLLATE utf8_slovenian_ci NOT NULL,
  `Datum` date NOT NULL,
  `imgHTML` varchar(255) COLLATE utf8_slovenian_ci DEFAULT NULL,
  `Description` text COLLATE utf8_slovenian_ci NOT NULL,
  `Votes` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci AUTO_INCREMENT=9 ;

-- --------------------------------------------------------

--
-- Table structure for table `cv_checkout`
--

CREATE TABLE IF NOT EXISTS `cv_checkout` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `cv_dashboard`
--

CREATE TABLE IF NOT EXISTS `cv_dashboard` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `cv_embroidery`
--

CREATE TABLE IF NOT EXISTS `cv_embroidery` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `cv_individualshirtdesign`
--

CREATE TABLE IF NOT EXISTS `cv_individualshirtdesign` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `cv_login`
--

CREATE TABLE IF NOT EXISTS `cv_login` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(255) COLLATE utf8_slovenian_ci NOT NULL,
  `Name` varchar(255) COLLATE utf8_slovenian_ci NOT NULL,
  `Surname` varchar(255) COLLATE utf8_slovenian_ci NOT NULL,
  `Password` varchar(255) COLLATE utf8_slovenian_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `cv_login`
--

INSERT INTO `cv_login` (`ID`, `Username`, `Name`, `Surname`, `Password`) VALUES
(1, 'Miha', 'Miha', 'Kuntu', 'geslo123');

-- --------------------------------------------------------

--
-- Table structure for table `cv_news`
--

CREATE TABLE IF NOT EXISTS `cv_news` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `cv_priceanddelivery`
--

CREATE TABLE IF NOT EXISTS `cv_priceanddelivery` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `cv_products`
--

CREATE TABLE IF NOT EXISTS `cv_products` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `cv_p_orders`
--

CREATE TABLE IF NOT EXISTS `cv_p_orders` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Login` int(11) NOT NULL,
  `Active` int(11) NOT NULL,
  `Status` int(11) NOT NULL,
  `Shirt` int(11) NOT NULL,
  `Date` date NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `cv_p_orders`
--

INSERT INTO `cv_p_orders` (`ID`, `Login`, `Active`, `Status`, `Shirt`, `Date`) VALUES
(1, 1, 0, 0, 1, '0000-00-00'),
(2, 1, 0, 0, 2, '0000-00-00');

-- --------------------------------------------------------

--
-- Table structure for table `cv_recommendation`
--

CREATE TABLE IF NOT EXISTS `cv_recommendation` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `cv_registration`
--

CREATE TABLE IF NOT EXISTS `cv_registration` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `cv_shoppingcart`
--

CREATE TABLE IF NOT EXISTS `cv_shoppingcart` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Active` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `cv_shoppingcart`
--

INSERT INTO `cv_shoppingcart` (`ID`, `Active`) VALUES
(1, 0),
(2, 0);

-- --------------------------------------------------------

--
-- Table structure for table `cv_standardshirt`
--

CREATE TABLE IF NOT EXISTS `cv_standardshirt` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
