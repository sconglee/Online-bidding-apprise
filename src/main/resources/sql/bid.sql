/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : bid

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 18/10/2019 13:51:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for expertscoresheet
-- ----------------------------
DROP TABLE IF EXISTS `expertscoresheet`;
CREATE TABLE `expertscoresheet`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '项目名称',
  `projectNumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '项目编号',
  `companyName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '待评标打分的单位名称',
  `expertName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '打分专家名称登录该系统的用户名',
  `point` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '专家打分（每个投标单位的每个评分项具体得分，使用逗号分割）',
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expertscoresheet
-- ----------------------------
INSERT INTO `expertscoresheet` VALUES (1, 'XX工程GS-1307 高速电视测量系统改造(二次)', '0730-196012JP0027/02', '西安', 'lsc', '2,12,9,7,5,2,2,4,2,2,3,4,2,4,6,3,6,2,3,1,1,1,17', '2019-10-18 09:09:00', '2019-10-18 09:09:00');
INSERT INTO `expertscoresheet` VALUES (2, 'XX工程GS-1307 高速电视测量系统改造(二次)', '0730-196012JP0027/02', '北京', 'lsc', '2,12,9,7,5,2,2,4,2,2,3,4,2,4,6,3,6,2,3,1,1,1,17', '2019-10-18 09:15:00', '2019-10-18 09:15:00');

-- ----------------------------
-- Table structure for scoresheettemplate
-- ----------------------------
DROP TABLE IF EXISTS `scoresheettemplate`;
CREATE TABLE `scoresheettemplate`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '项目名称',
  `projectNumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '项目编号',
  `totalItems` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '评分总项（使用逗号分割）',
  `sequenceNumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '分项序号（使用逗号分割）',
  `itemWeight` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '分项权重（使用逗号分割）',
  `scoredComName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '待评标单位名称(多家单位使用逗号分割)',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '生效状态（0：生效,   1：未生效，  默认值是1）',
  `remove` int(11) NOT NULL DEFAULT 0 COMMENT '删除状态（0：正常状态，1：删除状态，默认值是0）	',
  `createTime` timestamp(0) NOT NULL,
  `updateTime` timestamp(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `findByNameNumber`(`projectName`, `projectNumber`) USING BTREE COMMENT '根据项目名称和项目编号 查询评标打分模板'
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scoresheettemplate
-- ----------------------------
INSERT INTO `scoresheettemplate` VALUES (1, 'XX工程GS-1307 高速电视测量系统改造(二次)', '0730-196012JP0027/02', '技术得分,商务得分,价格得分', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23', '2,12,9,7,5,2,2,4,2,2,3,4,2,4,6,3,6,2,3,1,1,1,17', '西安,江西,山东建筑', 1, 0, '2019-10-16 14:09:00', '2019-10-16 15:09:08');
INSERT INTO `scoresheettemplate` VALUES (2, 'XX工程GS-1307 高速电视测量系统改造(二次)', '0730-196012JP0027/03', '技术得分,商务得分,价格得分', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23', '2,12,9,7,5,2,2,4,2,2,3,4,2,4,6,3,6,2,3,1,1,1,17', '西安,江西', 1, 0, '2019-10-16 14:25:00', '2019-10-16 14:26:00');
INSERT INTO `scoresheettemplate` VALUES (3, 'XX工程GS-1307 高速电视测量系统改造(二次)', '0730-196012JP0027/04', '技术得分,商务得分,价格得分', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23', '2,12,9,7,5,2,2,4,2,2,3,4,2,4,6,3,6,2,3,1,1,1,17', '西安,江西,北京', 1, 0, '2019-10-16 14:16:05', '2019-10-16 15:17:53');
INSERT INTO `scoresheettemplate` VALUES (7, 'XX工程GS-1307 高速电视测量系统改造(二次)', '0730-196012JP0027/05', '技术得分,商务得分,价格得分', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23', '2,12,9,7,5,2,2,4,2,2,3,4,2,4,6,3,6,2,3,1,1,1,17', '西安,江西', 1, 0, '2019-10-16 15:44:05', '2019-10-16 15:44:05');
INSERT INTO `scoresheettemplate` VALUES (8, 'XX工程GS-1307 高速电视测量系统改造(二次)', '0730-196012JP0027/06', '技术得分,商务得分,价格得分', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23', '2,12,9,7,5,2,2,4,2,2,3,4,2,4,6,3,6,2,3,1,1,1,17', '西安,江西', 1, 0, '2019-10-16 16:09:14', '2019-10-16 16:09:14');
INSERT INTO `scoresheettemplate` VALUES (9, 'XX工程GS-1307 高速电视测量系统改造(三次)', '0730-196012JP0027/01', '技术得分,商务得分,价格得分,测试得分', '1,2,3,4,5,6,7,8,9,10,11,12', '2,12,9,7,5,2,2,4,2,2,3,4,2', '西安,江西,广东,山东建筑', 1, 0, '2019-10-17 10:09:14', '2019-10-17 10:09:14');
INSERT INTO `scoresheettemplate` VALUES (10, 'XX工程GS-1307 高速电视测量系统改造(三次)', '0730-196012JP0027/02', '技术得分,商务得分,价格得分,测试得分', '1,2,3,4,5,6,7,8,9,10,11,12', '2,12,9,7,5,2,2,4,2,2,3,4,2', '西安,江西,广东,山东建筑', 1, 0, '2019-10-17 10:09:14', '2019-10-17 10:09:14');
INSERT INTO `scoresheettemplate` VALUES (11, 'XX工程GS-1307 高速电视测量系统改造(三次)', '0730-196012JP0027/03', '技术得分,商务得分,价格得分,测试得分', '1,2,3,4,5,6,7,8,9,10,11,12', '2,12,9,7,5,2,2,4,2,2,3,4,2', '西安,江西,广东,山东建筑', 1, 0, '2019-10-17 10:09:14', '2019-10-17 10:09:14');
INSERT INTO `scoresheettemplate` VALUES (12, 'XX工程GS-1307 高速电视测量系统改造(三次)', '0730-196012JP0027/04', '技术得分,商务得分,价格得分,测试得分', '1,2,3,4,5,6,7,8,9,10,11,12', '2,12,9,7,5,2,2,4,2,12,3,4,26,66', '西安,江西,广东,山东建筑', 1, 0, '2019-10-17 11:17:35', '2019-10-17 13:13:19');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `account_type` int(11) NULL DEFAULT NULL COMMENT '0项目经理，1评标专家',
  `account_status` int(11) NULL DEFAULT NULL COMMENT '默认为0，账号正常启用，停用为1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'lsc', 'E10ADC3949BA59ABBE56E057F20F883E', '2019-10-14 16:26:13', 1, 0);
INSERT INTO `users` VALUES (2, 'xxjshb', 'E10ADC3949BA59ABBE56E057F20F883E', '2019-10-14 16:26:50', 1, 0);
INSERT INTO `users` VALUES (4, 'lishicong', 'E10ADC3949BA59ABBE56E057F20F883E', '2019-10-18 10:24:32', 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
