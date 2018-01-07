/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : library

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2018-01-07 21:52:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(20) DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL,
  `amont` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', 'haha', 'shufd', '2');
INSERT INTO `book` VALUES ('10', 'wshiyibenshu', 'zuozhe', '5');

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `book_name` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `is_accepted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `borrow_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `borrow_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES ('1', '3', '1', 'haha', 'hehe', '2016-12-08', '1');

-- ----------------------------
-- Table structure for favourates
-- ----------------------------
DROP TABLE IF EXISTS `favourates`;
CREATE TABLE `favourates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `book_id` (`book_id`),
  KEY `favourates_ibfk_2` (`user_id`),
  CONSTRAINT `favourates_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `favourates_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of favourates
-- ----------------------------

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `borrow_day` varchar(20) DEFAULT NULL,
  `return_day` varchar(20) NOT NULL,
  `other` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `book_id` (`book_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `log_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `log_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('1', '10', '3', '2016-12-27', ' ', '未还');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `identify` varchar(20) DEFAULT NULL,
  `is_real` tinyint(1) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '你妹', '123456', 'manager', '1', '2016-12-06');
INSERT INTO `user` VALUES ('2', '哈哈哈哈', '123456', 'student', '0', '2016-12-08');
INSERT INTO `user` VALUES ('3', '哈哈哈哈哈', '111111', 'student', '1', '2016-12-08');

-- ----------------------------
-- Procedure structure for accept_borrow
-- ----------------------------
DROP PROCEDURE IF EXISTS `accept_borrow`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `accept_borrow`(IN `uid` integer,IN `bid` integer,IN `indate` date)
BEGIN
	UPDATE borrow SET is_accepted=1 where book_id=bid;
	INSERT INTO log VALUES(null, bid, uid, indate, ' ', '未还');
	update book set amont=amont-1 where id=bid;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for return_book
-- ----------------------------
DROP PROCEDURE IF EXISTS `return_book`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `return_book`(IN uid int,IN i INT,in rd varchar(60),in o varchar(3))
BEGIN
	UPDATE LOG SET other = o WHERE user_id = uid AND book_id = i and other = '未还';
	UPDATE LOG SET return_day = rd WHERE username = u AND id = i and return_day = ' ';
	delete from borrow where user_id = uid AND book_id = i;
	update book set amont=amont+1 where id=bid;
END
;;
DELIMITER ;
