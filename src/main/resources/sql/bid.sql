/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : bid

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-10-17 08:38:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for scoresheettemplate
-- ----------------------------
DROP TABLE IF EXISTS `scoresheettemplate`;
CREATE TABLE `scoresheettemplate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '项目名称',
  `projectNumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '项目编号',
  `totalItems` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '评分总项（使用逗号分割）',
  `sequenceNumber` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分项序号（使用逗号分割）',
  `itemWeight` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分项权重（使用逗号分割）',
  `scoredComName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '待评标单位名称(多家单位使用逗号分割)',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '生效状态（0：生效,   1：未生效，  默认值是1）',
  `remove` int(11) NOT NULL DEFAULT '0' COMMENT '删除状态（0：正常状态，1：删除状态，默认值是0）	',
  `createTime` timestamp NOT NULL,
  `updateTime` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of scoresheettemplate
-- ----------------------------
INSERT INTO `scoresheettemplate` VALUES ('1', 'XX工程GS-1307 高速电视测量系统改造(二次)', '0730-196012JP0027/02', '技术得分,商务得分,价格得分', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23', '2,12,9,7,5,2,2,4,2,2,3,4,2,4,6,3,6,2,3,1,1,1,17', '西安,江西,山东建筑', '1', '0', '2019-10-16 14:09:00', '2019-10-16 15:09:08');
INSERT INTO `scoresheettemplate` VALUES ('2', 'XX工程GS-1307 高速电视测量系统改造(二次)', '0730-196012JP0027/03', '技术得分,商务得分,价格得分', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23', '2,12,9,7,5,2,2,4,2,2,3,4,2,4,6,3,6,2,3,1,1,1,17', '西安,江西', '1', '0', '2019-10-16 14:25:00', '2019-10-16 14:26:00');
INSERT INTO `scoresheettemplate` VALUES ('3', 'XX工程GS-1307 高速电视测量系统改造(二次)', '0730-196012JP0027/04', '技术得分,商务得分,价格得分', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23', '2,12,9,7,5,2,2,4,2,2,3,4,2,4,6,3,6,2,3,1,1,1,17', '西安,江西,北京', '1', '0', '2019-10-16 14:16:05', '2019-10-16 15:17:53');
INSERT INTO `scoresheettemplate` VALUES ('7', 'XX工程GS-1307 高速电视测量系统改造(二次)', '0730-196012JP0027/05', '技术得分,商务得分,价格得分', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23', '2,12,9,7,5,2,2,4,2,2,3,4,2,4,6,3,6,2,3,1,1,1,17', '西安,江西', '1', '0', '2019-10-16 15:44:05', '2019-10-16 15:44:05');
INSERT INTO `scoresheettemplate` VALUES ('8', 'XX工程GS-1307 高速电视测量系统改造(二次)', '0730-196012JP0027/06', '技术得分,商务得分,价格得分', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23', '2,12,9,7,5,2,2,4,2,2,3,4,2,4,6,3,6,2,3,1,1,1,17', '西安,江西', '1', '0', '2019-10-16 16:09:14', '2019-10-16 16:09:14');

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
INSERT INTO `users` VALUES ('2', 'xxjshb', 'E10ADC3949BA59ABBE56E057F20F883E', '2019-10-14 16:26:50', '1', '0');
