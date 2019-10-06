/*
Navicat MySQL Data Transfer

Source Server         : locahost
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : zu

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2019-07-17 10:10:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply` (
  `apply_id` int(11) NOT NULL AUTO_INCREMENT,
  `house_id` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `area` double(255,0) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `userlist_id` int(255) NOT NULL,
  PRIMARY KEY (`apply_id`,`house_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apply
-- ----------------------------

-- ----------------------------
-- Table structure for applyout
-- ----------------------------
DROP TABLE IF EXISTS `applyout`;
CREATE TABLE `applyout` (
  `aoid` int(11) NOT NULL AUTO_INCREMENT,
  `house_id` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `userlist_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`aoid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of applyout
-- ----------------------------

-- ----------------------------
-- Table structure for checkout
-- ----------------------------
DROP TABLE IF EXISTS `checkout`;
CREATE TABLE `checkout` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `house_id` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `userlist_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of checkout
-- ----------------------------
INSERT INTO `checkout` VALUES ('2', 'k2', '贝岗街18号', '已退租', '15');
INSERT INTO `checkout` VALUES ('4', 'f1', '恒大1-1233', '已退租', '12');

-- ----------------------------
-- Table structure for hetong
-- ----------------------------
DROP TABLE IF EXISTS `hetong`;
CREATE TABLE `hetong` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chuzu` varchar(255) DEFAULT NULL,
  `chuzu_idcard` varchar(255) DEFAULT NULL,
  `zuke` varchar(255) DEFAULT NULL,
  `zuke_idcard` varchar(255) DEFAULT NULL,
  `fromdate` varchar(255) DEFAULT NULL,
  `todate` varchar(255) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `house_id` varchar(255) DEFAULT NULL,
  `payday` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hetong
-- ----------------------------
INSERT INTO `hetong` VALUES ('1', '隔壁老王', '12345689', '李四风', '987654321', '2019-06-05', '2019-07-09', '900.00', '洹园', 'b5', '25');

-- ----------------------------
-- Table structure for houselist
-- ----------------------------
DROP TABLE IF EXISTS `houselist`;
CREATE TABLE `houselist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `houseid` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `area` double DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`houseid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of houselist
-- ----------------------------
INSERT INTO `houselist` VALUES ('16', 'c4', '菊花园1-364aa', '60', '700.00', '未租赁');
INSERT INTO `houselist` VALUES ('20', 'b5', '穗石村18号', '60', '700.00', '已租赁');
INSERT INTO `houselist` VALUES ('21', 'k8', '穗石村1号', '66', '800.00', '未租赁');

-- ----------------------------
-- Table structure for paid
-- ----------------------------
DROP TABLE IF EXISTS `paid`;
CREATE TABLE `paid` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `house_id` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `paydate` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `userlist_id` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paid
-- ----------------------------
INSERT INTO `paid` VALUES ('5', 'c1', '广药1-364', '500.00', '2017-09-14', '2017-09-27', '赵颖欣', '10', '租金已缴');
INSERT INTO `paid` VALUES ('6', 'c3', '菊花园1-364', '700.00', '2017-09-30', '2017-09-28', '李四', '12', '租金已缴');
INSERT INTO `paid` VALUES ('7', 'c2', '碧桂园1-364', '4365.00', '2017-10-31', '2017-10-08', '张三', '14', '租金已缴');
INSERT INTO `paid` VALUES ('8', 'k2', '贝岗街18号', '700.00', '2017-10-31', '2017-10-10', '张思', '15', '租金已缴');
INSERT INTO `paid` VALUES ('9', 'c1', '广药1-364', '5000.00', '2017-10-31', '2017-10-10', '赵颖欣', '10', '租金已缴');

-- ----------------------------
-- Table structure for schedule
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of schedule
-- ----------------------------
INSERT INTO `schedule` VALUES ('3', '2017-09-20', '今天是收租日');
INSERT INTO `schedule` VALUES ('4', '2019-06-15', '213213213');

-- ----------------------------
-- Table structure for solve
-- ----------------------------
DROP TABLE IF EXISTS `solve`;
CREATE TABLE `solve` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `house_id` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `detail` text,
  `name` varchar(255) DEFAULT NULL,
  `userlist_id` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of solve
-- ----------------------------
INSERT INTO `solve` VALUES ('4', 'c1', '广药1-364', '2017-09-08', 'kk', '赵颖欣', '10', '已处理');
INSERT INTO `solve` VALUES ('5', 'c3', '菊花园1-364', '2017-09-28', '天花板漏水', '李四', '12', '已处理');
INSERT INTO `solve` VALUES ('6', 'c2', '碧桂园1-364', '2017-10-08', '窗户坏了', '张三', '14', '已处理');
INSERT INTO `solve` VALUES ('7', 'k2', '贝岗街18号', '2017-10-10', '天花板漏水', '张思', '15', '已处理');
INSERT INTO `solve` VALUES ('8', 'c1', '广药1-364', '2017-10-09', '空调漏水', '赵颖欣', '10', '已处理');

-- ----------------------------
-- Table structure for topaid
-- ----------------------------
DROP TABLE IF EXISTS `topaid`;
CREATE TABLE `topaid` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `house_id` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `userlist_id` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topaid
-- ----------------------------
INSERT INTO `topaid` VALUES ('7', 'yieryi', '黄鹤楼', '1010.00', '2019-03-16', '小黑黑', '12', '租金未缴');
INSERT INTO `topaid` VALUES ('8', 's123', '开发区', '800.00', '2019-06-30', '张思', '15', '租金未缴');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123456', 'admin');
INSERT INTO `user` VALUES ('3', 'zyx', '12345', 'zuke');
INSERT INTO `user` VALUES ('5', 'cwy', '12345', 'zuke');
INSERT INTO `user` VALUES ('6', 'zs123zu', '123456', 'zuke');
INSERT INTO `user` VALUES ('7', '1111', '123456', 'zuke');

-- ----------------------------
-- Table structure for userlist
-- ----------------------------
DROP TABLE IF EXISTS `userlist`;
CREATE TABLE `userlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `idcard` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`idcard`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userlist
-- ----------------------------
INSERT INTO `userlist` VALUES ('12', '李四风', '440421199509088121', '18826107777', '3');
INSERT INTO `userlist` VALUES ('15', '张思', '441601199312214414', '159192134000', '5');
INSERT INTO `userlist` VALUES ('16', '张三风', '44545456654645', '2313123', '7');

-- ----------------------------
-- Table structure for wrong
-- ----------------------------
DROP TABLE IF EXISTS `wrong`;
CREATE TABLE `wrong` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `house_id` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `detail` text,
  `name` varchar(255) DEFAULT NULL,
  `userlist_id` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wrong
-- ----------------------------
INSERT INTO `wrong` VALUES ('1', 'c3', '菊花园1-364', '2019-06-04', '房屋漏水', '李四', '12', '待处理');

-- ----------------------------
-- Table structure for zulist
-- ----------------------------
DROP TABLE IF EXISTS `zulist`;
CREATE TABLE `zulist` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `house_id` varchar(255) NOT NULL,
  `price` double(10,2) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `userlist_id` int(11) NOT NULL,
  `contract_id` int(11) NOT NULL,
  PRIMARY KEY (`zid`,`house_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zulist
-- ----------------------------
INSERT INTO `zulist` VALUES ('7', 'c3', '700.00', '菊花园1-364', '12', '5');
INSERT INTO `zulist` VALUES ('8', 's123', '888.00', '开发区', '15', '11');
INSERT INTO `zulist` VALUES ('9', 'b5', '700.00', '穗石村18号', '12', '6');
