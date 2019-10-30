/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : bid

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-10-30 10:22:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for expertscoresheet
-- ----------------------------
DROP TABLE IF EXISTS `expertscoresheet`;
CREATE TABLE `expertscoresheet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '项目名称',
  `projectNumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '项目编号',
  `companyName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '待评标打分的单位名称',
  `expertName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '打分专家名称登录该系统的用户名',
  `totalItems` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `sequenceNumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分项序号（使用逗号分割）',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '0：已打分；1：未打分',
  `itemWeight` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '权重',
  `point` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '专家打分（每个投标单位的每个评分项具体得分，使用逗号分割）',
  `createTime` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of expertscoresheet
-- ----------------------------
INSERT INTO `expertscoresheet` VALUES ('1', 'XX工程GS-1307 高速电视测量系统改造(三次)', '0730-196012JP0027/04', '临沂', 'xl', '', '', '0', '1,2,3,4,5,6,7', '1,2,3,4,5,6,7', '2019-10-28 16:37:01', '2019-10-28 16:37:01');
INSERT INTO `expertscoresheet` VALUES ('2', 'XX工程GS-1307 高速电视测量系统改造(三次)', '0730-196012JP0027/04', '山东建筑', 'llllllll', '', '', '0', '1,2,3,4,5,6,7,8,9', '1,2,3,4,5,6,7,8,9', '2019-10-28 16:37:06', '2019-10-28 16:37:06');
INSERT INTO `expertscoresheet` VALUES ('3', 'XX工程GS-1307 高速电视测量系统改造(三次)', '0730-196012JP0027/04', '山东建筑', 'llllllll', '', '', '0', '1,2,3,4,5,6,7,8,9', '1,2,3,4,5,6,7,8,9', '2019-10-28 16:37:08', '2019-10-28 16:37:08');
INSERT INTO `expertscoresheet` VALUES ('4', 'XX工程GS-1307 高速电视测量系统改造(三次)', '0730-196012JP0027/04', '山东建筑', 'xlllllllll', '', '', '0', '1,2,3,4,5,6,7,8,9', '1,2,3,4,5,6,7,8,9', '2019-10-28 16:37:08', '2019-10-28 16:37:08');
INSERT INTO `expertscoresheet` VALUES ('5', 'XX工程GS-1307 高速电视测量系统改造(三次)', '0730-196012JP0027/04', '山东建筑公司', 'xlllllllll', '', '', '0', '1,2,3,4,5,6,7,8,9', '1,2,3,4,5,6,7,8,9', '2019-10-28 16:37:09', '2019-10-28 16:37:09');
INSERT INTO `expertscoresheet` VALUES ('6', 'XX工程GS-1307 高速电视测量系统改造(三次)', '0730-196012JP0027/04', '山东建筑公司', 'xlllllllll', '', '', '0', '3,4,1,2,5,6,9,4,7', '1,2,3,4,5,6,7,8,9', '2019-10-28 16:37:10', '2019-10-28 16:37:10');
INSERT INTO `expertscoresheet` VALUES ('7', 'XX工程GS-1307 高速电视测量系统改造(三次)', '0730-196012JP0027/04', '山东建筑公司', 'xlllllllll', '60,10,10,20', '', '0', '3,4,1,2,5,6,9,4,7', '1,2,3,4,5,6,7,8,9', '2019-10-28 16:37:10', '2019-10-28 16:37:10');
INSERT INTO `expertscoresheet` VALUES ('8', '1111', '2222', '33333', null, '5', '', '0', '4444', null, '2019-10-28 16:37:11', '2019-10-28 16:37:11');
INSERT INTO `expertscoresheet` VALUES ('9', '1111', '2222', '33333', null, '5', '', '0', '4444', null, '2019-10-28 16:37:13', '2019-10-28 16:37:13');
INSERT INTO `expertscoresheet` VALUES ('50', 'XX工程GS-1307 高速电视测量系统改造(五次)', '0730-196012JP0027/12', '西安公司', 'xlllllllll', '技术得分(60),商务得分(10),价格得分(10),测试得分(20)', '[1,2,3,4],[5,6,7],[8,9,10],[11,12]', '1', '[2,12,9,7],[5,2,2],[4,2,2],[4,2]', '1,2,3,4,5,6,7,8,9', '2019-10-29 14:32:03', '2019-10-29 14:32:03');
INSERT INTO `expertscoresheet` VALUES ('51', 'XX工程GS-1307 高速电视测量系统改造(五次)', '0730-196012JP0027/12', '江西公司', 'xlllllllll', '技术得分(60),商务得分(10),价格得分(10),测试得分(20)', '[1,2,3,4],[5,6,7],[8,9,10],[11,12]', '1', '[2,12,9,7],[5,2,2],[4,2,2],[4,2]', '1,2,3,4,5,6,7,8,9', '2019-10-29 14:32:04', '2019-10-29 14:32:04');
INSERT INTO `expertscoresheet` VALUES ('52', 'XX工程GS-1307 高速电视测量系统改造(三次)', '0730-196012JP0027/04', 'AAA', 'xlllllllll', '60,10,10,20', '[1,2,3,4],[5,6,7],[8,9,10],[11,12]', '0', '[2,12,9,7],[5,2,2],[4,2,2],[4,2]', '1,2,3,4,5,6,7,8,9', '2019-10-29 15:42:58', '2019-10-29 15:42:58');
INSERT INTO `expertscoresheet` VALUES ('53', 'XX工程GS-1307 高速电视测量系统改造(五次)', '0730-196012JP0027/12', '山东建筑公司', 'xlllllllll', '技术得分(60),商务得分(10),价格得分(10),测试得分(20)', '[1,2,3,4],[5,6,7],[8,9,10],[11,12]', '1', '[2,12,9,7],[5,2,2],[4,2,2],[4,2]', '1,2,3,4,5,6,7,8,9', '2019-10-29 14:32:05', '2019-10-29 14:32:05');

-- ----------------------------
-- Table structure for finalscoresheet
-- ----------------------------
DROP TABLE IF EXISTS `finalscoresheet`;
CREATE TABLE `finalscoresheet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectNumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `projectName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `companyName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `isGenerate` int(255) unsigned zerofill NOT NULL COMMENT '是否已生成得分表，默认为0',
  `averageScore` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '各项平均得分',
  `totalScore` int(11) DEFAULT NULL COMMENT '最终总得分',
  `createTime` timestamp NOT NULL,
  `updateTime` timestamp NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of finalscoresheet
-- ----------------------------
INSERT INTO `finalscoresheet` VALUES ('1', '0730-196012JP0027/04', 'XX工程GS-1307 高速电视测量系统改造(三次)', '山东建筑公司', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', null, '0', '2019-10-25 16:46:53', '2019-10-25 16:46:53');
INSERT INTO `finalscoresheet` VALUES ('3', '0730-196012JP0027/04', 'XX工程GS-1307 高速电视测量系统改造(三次)', 'AAA', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', null, '0', '2019-10-29 15:42:58', '2019-10-29 15:42:58');

-- ----------------------------
-- Table structure for scoresheettemplate
-- ----------------------------
DROP TABLE IF EXISTS `scoresheettemplate`;
CREATE TABLE `scoresheettemplate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '项目名称',
  `projectNumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '项目编号',
  `totalItems` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '评分总项（使用逗号分割）',
  `sequenceNumber` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分项序号（使用逗号分割）',
  `itemWeight` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分项权重（使用逗号分割）',
  `scoredComName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '待评标单位名称(多家单位使用逗号分割)',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '生效状态（0：生效,   1：未生效，  默认值是1） 控制模板下发',
  `remove` int(11) NOT NULL DEFAULT '0' COMMENT '删除状态（0：正常状态，1：删除状态，默认值是0）	',
  `createTime` timestamp NOT NULL,
  `updateTime` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `findByNameNumber` (`projectName`,`projectNumber`) COMMENT '根据项目名称和项目编号 查询评标打分模板'
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of scoresheettemplate
-- ----------------------------
INSERT INTO `scoresheettemplate` VALUES ('18', 'XX工程GS-1307 高速电视测量系统改造(五次)', '0730-196012JP0027/01', '技术得分(60),商务得分(10),价格得分(10),测试得分(20)', '[1,2,3,4],[5,6,7],[8,9,10],[11,12]', '[2,12,9,7],[5,2,2],[4,2,2],[4,2]', '西安公司,江西公司,广东公司,山东建筑公司', '1', '0', '2019-10-23 15:13:48', '2019-10-23 15:13:48');
INSERT INTO `scoresheettemplate` VALUES ('19', 'XX工程GS-1307 高速电视测量系统改造(五次)', '0730-196012JP0027/02', '技术得分(60),商务得分(10),价格得分(10),测试得分(20)', '[1,2,3,4],[5,6,7],[8,9,10],[11,12]', '[2,12,9,7],[5,2,2],[4,2,2],[4,2]', '西安公司,江西公司,广东公司,山东建筑公司', '1', '0', '2019-10-23 15:21:49', '2019-10-23 15:21:49');
INSERT INTO `scoresheettemplate` VALUES ('20', 'XX工程GS-1307 高速电视测量系统改造(五次)', '0730-196012JP0027/03', '技术得分(60),商务得分(10),价格得分(10),测试得分(20)', '[1,2,3,4],[5,6,7],[8,9,10],[11,12]', '[2,12,9,7],[5,2,2],[4,2,2],[4,2]', '西安公司,江西公司,广东公司,山东建筑公司', '1', '0', '2019-10-23 15:21:54', '2019-10-24 14:41:50');
INSERT INTO `scoresheettemplate` VALUES ('22', 'XX工程GS-1307 高速电视测量系统改造(五次)', '0730-196012JP0027/05', '技术得分(60),商务得分(10),价格得分(10)', '[1,2,3,4],[5,6,7],[8,9,10]', '[2,12,9,7],[5,2,2],[4,2,2]', '西安公司,江西公司,广东公司', '1', '0', '2019-10-23 15:22:03', '2019-10-24 14:05:18');
INSERT INTO `scoresheettemplate` VALUES ('23', 'XX工程GS-1307 高速电视测量系统改造(五次)', '0730-196012JP0027/06', '技术得分(60),商务得分(10),价格得分(10)', '[1,2,3,4],[5,6,7],[8,9,10]', '[2,12,9,7],[5,2,2],[4,2,2]', '西安公司,江西公司,AA公司,BB公司', '1', '0', '2019-10-23 15:22:07', '2019-10-23 15:37:42');
INSERT INTO `scoresheettemplate` VALUES ('24', 'XX工程GS-1307 高速电视测量系统改造(五次)', '0730-196012JP0027/010', '技术得分(60),商务得分(10),价格得分(10),测试得分(20)', '[1,2,3,4],[5,6,7],[8,9,10],[11,12]', '[2,12,9,7],[5,2,2],[4,2,2],[4,2]', '西安公司,江西公司,广东公司,山东建筑公司', '1', '0', '2019-10-24 14:15:03', '2019-10-24 14:15:03');
INSERT INTO `scoresheettemplate` VALUES ('25', 'XX工程GS-1307 高速电视测量系统改造(五次)', '0730-196012JP0027/12', '技术得分(60),商务得分(10),价格得分(10),测试得分(20)', '[1,2,3,4],[5,6,7],[8,9,10],[11,12]', '[2,12,9,7],[5,2,2],[4,2,2],[4,2]', '西安公司,江西公司,广东公司,山东建筑公司', '1', '0', '2019-10-28 09:05:53', '2019-10-29 13:17:17');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `account_type` int(11) DEFAULT NULL COMMENT '0项目经理，1评标专家',
  `account_status` int(11) DEFAULT NULL COMMENT '默认为0，账号正常启用，停用为1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'lsc', 'E10ADC3949BA59ABBE56E057F20F883E', '2019-10-14 16:26:13', '1', '0');
INSERT INTO `users` VALUES ('2', 'xxjshb', 'E10ADC3949BA59ABBE56E057F20F883E', '2019-10-14 16:26:50', '0', '0');
