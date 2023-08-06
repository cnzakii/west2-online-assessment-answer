/*
 Navicat Premium Data Transfer

 Source Server         : home_mysql
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : weather

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 07/08/2023 00:34:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for daily
-- ----------------------------
DROP TABLE IF EXISTS `daily`;
CREATE TABLE `daily` (
  `id` int NOT NULL AUTO_INCREMENT,
  `location_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fxDate` date NOT NULL,
  `sunrise` time NOT NULL,
  `sunset` time NOT NULL,
  `moonrise` time NOT NULL,
  `moonset` time NOT NULL,
  `moonPhase` varchar(255) NOT NULL,
  `tempMax` int NOT NULL,
  `tempMin` int NOT NULL,
  `textDay` varchar(255) NOT NULL,
  `textNight` varchar(255) NOT NULL,
  `wind360Day` int NOT NULL,
  `windDirDay` varchar(255) NOT NULL,
  `windScaleDay` varchar(255) NOT NULL,
  `windSpeedDay` int NOT NULL,
  `wind360Night` int NOT NULL,
  `windDirNight` varchar(255) NOT NULL,
  `windScaleNight` varchar(255) NOT NULL,
  `windSpeedNight` int NOT NULL,
  `humidity` int NOT NULL,
  `precip` double NOT NULL,
  `pressure` int NOT NULL,
  `vis` int NOT NULL,
  `cloud` int NOT NULL,
  `uvIndex` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for location
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `lat` double NOT NULL,
  `lon` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
