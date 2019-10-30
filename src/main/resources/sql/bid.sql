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

 Date: 29/10/2019 16:36:37
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
  `totalItems` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `itemCount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '0：已打分；1：未打分',
  `itemWeight` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '权重',
  `point` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '专家打分（每个投标单位的每个评分项具体得分，使用逗号分割）',
  `createTime` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updateTime` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expertscoresheet
-- ----------------------------
INSERT INTO `expertscoresheet` VALUES (1, 'XX工程GS-1307 高速电视测量系统改造(三次)', '0730-196012JP0027/04', '临沂', 'xl', '', NULL, 0, '1,2,3,4,5,6,7', '1,2,3,4,5,6,7', '2019-10-28 16:37:01', '2019-10-28 16:37:01');
INSERT INTO `expertscoresheet` VALUES (2, 'XX工程GS-1307 高速电视测量系统改造(三次)', '0730-196012JP0027/04', '山东建筑', 'llllllll', '', NULL, 0, '1,2,3,4,5,6,7,8,9', '1,2,3,4,5,6,7,8,9', '2019-10-28 16:37:06', '2019-10-28 16:37:06');
INSERT INTO `expertscoresheet` VALUES (3, 'XX工程GS-1307 高速电视测量系统改造(三次)', '0730-196012JP0027/04', '山东建筑', 'llllllll', '', NULL, 0, '1,2,3,4,5,6,7,8,9', '1,2,3,4,5,6,7,8,9', '2019-10-28 16:37:08', '2019-10-28 16:37:08');
INSERT INTO `expertscoresheet` VALUES (4, 'XX工程GS-1307 高速电视测量系统改造(三次)', '0730-196012JP0027/04', '山东建筑', 'xlllllllll', '', NULL, 0, '1,2,3,4,5,6,7,8,9', '1,2,3,4,5,6,7,8,9', '2019-10-28 16:37:08', '2019-10-28 16:37:08');
INSERT INTO `expertscoresheet` VALUES (5, 'XX工程GS-1307 高速电视测量系统改造(三次)', '0730-196012JP0027/04', '山东建筑公司', 'xlllllllll', '', NULL, 0, '1,2,3,4,5,6,7,8,9', '1,2,3,4,5,6,7,8,9', '2019-10-28 16:37:09', '2019-10-28 16:37:09');
INSERT INTO `expertscoresheet` VALUES (6, 'XX工程GS-1307 高速电视测量系统改造(三次)', '0730-196012JP0027/04', '山东建筑公司', 'xlllllllll', '', NULL, 0, '3,4,1,2,5,6,9,4,7', '1,2,3,4,5,6,7,8,9', '2019-10-28 16:37:10', '2019-10-28 16:37:10');
INSERT INTO `expertscoresheet` VALUES (7, 'XX工程GS-1307 高速电视测量系统改造(三次)', '0730-196012JP0027/04', '山东建筑公司', 'xlllllllll', '60,10,10,20', NULL, 0, '3,4,1,2,5,6,9,4,7', '1,2,3,4,5,6,7,8,9', '2019-10-28 16:37:10', '2019-10-28 16:37:10');
INSERT INTO `expertscoresheet` VALUES (8, '1111', '2222', '33333', NULL, '5', NULL, 0, '4444', NULL, '2019-10-28 16:37:11', '2019-10-28 16:37:11');
INSERT INTO `expertscoresheet` VALUES (9, '1111', '2222', '33333', NULL, '5', NULL, 0, '4444', NULL, '2019-10-28 16:37:13', '2019-10-28 16:37:13');
INSERT INTO `expertscoresheet` VALUES (46, 'XX工程GS-1307 高速电视测量系统改造(五次)', '0730-196012JP0027/12', '西安公司', NULL, '技术得分(60),商务得分(10),价格得分(10),测试得分(20)', NULL, 1, '[2,12,9,7],[5,2,2],[4,2,2],[4,2]', NULL, '2019-10-28 09:05:53', '2019-10-29 09:58:42');
INSERT INTO `expertscoresheet` VALUES (47, 'XX工程GS-1307 高速电视测量系统改造(五次)', '0730-196012JP0027/12', '江西公司', NULL, '技术得分(60),商务得分(10),价格得分(10),测试得分(20)', NULL, 1, '[2,12,9,7],[5,2,2],[4,2,2],[4,2]', NULL, '2019-10-28 09:05:53', '2019-10-29 09:58:42');
INSERT INTO `expertscoresheet` VALUES (48, 'XX工程GS-1307 高速电视测量系统改造(五次)', '0730-196012JP0027/12', '广东公司', NULL, '技术得分(60),商务得分(10),价格得分(10),测试得分(20)', NULL, 1, '[2,12,9,7],[5,2,2],[4,2,2],[4,2]', NULL, '2019-10-28 09:05:53', '2019-10-29 09:58:42');
INSERT INTO `expertscoresheet` VALUES (49, 'XX工程GS-1307 高速电视测量系统改造(五次)', '0730-196012JP0027/12', '山东建筑公司', NULL, '技术得分(60),商务得分(10),价格得分(10),测试得分(20)', NULL, 1, '[2,12,9,7],[5,2,2],[4,2,2],[4,2]', NULL, '2019-10-28 09:05:53', '2019-10-29 09:58:42');

-- ----------------------------
-- Table structure for finalscoresheet
-- ----------------------------
DROP TABLE IF EXISTS `finalscoresheet`;
CREATE TABLE `finalscoresheet`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectNumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `projectName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `companyName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `isGenerate` int(1) UNSIGNED ZEROFILL NOT NULL COMMENT '是否已生成得分表，默认为0',
  `averageScore` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '各项平均得分',
  `totalScore` float(255, 2) NULL DEFAULT NULL COMMENT '最终总得分',
  `createTime` timestamp(0) NOT NULL,
  `updateTime` timestamp(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of finalscoresheet
-- ----------------------------
INSERT INTO `finalscoresheet` VALUES (1, '0730-196012JP0027/04', 'XX工程GS-1307 高速电视测量系统改造(三次)', '山东建筑公司', 1, '1.0,4.0,9.0,16.0,25.0,36.0,49.0,64.0,81.0', 285.00, '2019-10-25 16:46:53', '2019-10-29 16:14:44');

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
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '生效状态（0：生效,   1：未生效，  默认值是1） 控制模板下发',
  `remove` int(11) NOT NULL DEFAULT 0 COMMENT '删除状态（0：正常状态，1：删除状态，默认值是0）	',
  `createTime` timestamp(0) NOT NULL,
  `updateTime` timestamp(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `findByNameNumber`(`projectName`, `projectNumber`) USING BTREE COMMENT '根据项目名称和项目编号 查询评标打分模板'
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scoresheettemplate
-- ----------------------------
INSERT INTO `scoresheettemplate` VALUES (18, 'XX工程GS-1307 高速电视测量系统改造(五次)', '0730-196012JP0027/01', '技术得分(60),商务得分(10),价格得分(10),测试得分(20)', '[1,2,3,4],[5,6,7],[8,9,10],[11,12]', '[2,12,9,7],[5,2,2],[4,2,2],[4,2]', '西安公司,江西公司,广东公司,山东建筑公司', 1, 0, '2019-10-23 15:13:48', '2019-10-23 15:13:48');
INSERT INTO `scoresheettemplate` VALUES (19, 'XX工程GS-1307 高速电视测量系统改造(五次)', '0730-196012JP0027/02', '技术得分(60),商务得分(10),价格得分(10),测试得分(20)', '[1,2,3,4],[5,6,7],[8,9,10],[11,12]', '[2,12,9,7],[5,2,2],[4,2,2],[4,2]', '西安公司,江西公司,广东公司,山东建筑公司', 1, 0, '2019-10-23 15:21:49', '2019-10-23 15:21:49');
INSERT INTO `scoresheettemplate` VALUES (20, 'XX工程GS-1307 高速电视测量系统改造(五次)', '0730-196012JP0027/03', '技术得分(60),商务得分(10),价格得分(10),测试得分(20)', '[1,2,3,4],[5,6,7],[8,9,10],[11,12]', '[2,12,9,7],[5,2,2],[4,2,2],[4,2]', '西安公司,江西公司,广东公司,山东建筑公司', 1, 0, '2019-10-23 15:21:54', '2019-10-24 14:41:50');
INSERT INTO `scoresheettemplate` VALUES (22, 'XX工程GS-1307 高速电视测量系统改造(五次)', '0730-196012JP0027/05', '技术得分(60),商务得分(10),价格得分(10)', '[1,2,3,4],[5,6,7],[8,9,10]', '[2,12,9,7],[5,2,2],[4,2,2]', '西安公司,江西公司,广东公司', 1, 0, '2019-10-23 15:22:03', '2019-10-24 14:05:18');
INSERT INTO `scoresheettemplate` VALUES (23, 'XX工程GS-1307 高速电视测量系统改造(五次)', '0730-196012JP0027/06', '技术得分(60),商务得分(10),价格得分(10)', '[1,2,3,4],[5,6,7],[8,9,10]', '[2,12,9,7],[5,2,2],[4,2,2]', '西安公司,江西公司,AA公司,BB公司', 1, 0, '2019-10-23 15:22:07', '2019-10-23 15:37:42');
INSERT INTO `scoresheettemplate` VALUES (24, 'XX工程GS-1307 高速电视测量系统改造(五次)', '0730-196012JP0027/010', '技术得分(60),商务得分(10),价格得分(10),测试得分(20)', '[1,2,3,4],[5,6,7],[8,9,10],[11,12]', '[2,12,9,7],[5,2,2],[4,2,2],[4,2]', '西安公司,江西公司,广东公司,山东建筑公司', 1, 0, '2019-10-24 14:15:03', '2019-10-24 14:15:03');
INSERT INTO `scoresheettemplate` VALUES (25, 'XX工程GS-1307 高速电视测量系统改造(五次)', '0730-196012JP0027/12', '技术得分(60),商务得分(10),价格得分(10),测试得分(20)', '[1,2,3,4],[5,6,7],[8,9,10],[11,12]', '[2,12,9,7],[5,2,2],[4,2,2],[4,2]', '西安公司,江西公司,广东公司,山东建筑公司', 0, 0, '2019-10-28 09:05:53', '2019-10-29 09:58:42');

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
INSERT INTO `users` VALUES (2, 'xxjshb', 'E10ADC3949BA59ABBE56E057F20F883E', '2019-10-14 16:26:50', 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
