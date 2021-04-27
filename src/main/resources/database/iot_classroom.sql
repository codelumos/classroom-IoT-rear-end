/*
 Navicat Premium Data Transfer

 Source Server         : 172.19.241.2_3306
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : 172.19.241.2:3306
 Source Schema         : iot_classroom

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Author: HaoNShi
 Date: 20/04/2021 00:11:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `device_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `device_type` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `online_time` timestamp NULL DEFAULT NULL,
  `group_id` bigint(20) NULL DEFAULT 0,
  `credential` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `device_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1461 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES (1447, '教202-空调1', 1, '2021-04-26 23:09:06', '{\"state\":\"{\\\"openState\\\":1,\\\"pattern\\\":1,\\\"temperature\\\":-14,\\\"gear\\\":2}\"}', NULL, 2, '6d4428ff02b5454f9bee72d228b5229a');
INSERT INTO `device` VALUES (1450, '教202-空调2', 1, '2021-04-21 18:47:14', NULL, NULL, 3, 'c1d242696f2243f48909ebfb6e11ef49');
INSERT INTO `device` VALUES (1454, '教202-投影仪1', 2, '2021-04-26 23:09:06', '{\"state\":\"{\\\"openState\\\":1}\"}', NULL, 4, '5953dd8b47624bf5a544cbce59324304');
INSERT INTO `device` VALUES (1456, '教202-投影仪2', 2, '2021-04-26 23:09:06', '{\"state\":\"{\\\"openState\\\":1}\"}', NULL, 0, '5013c50ce1d44fe5a271ea7ea16193c8');
INSERT INTO `device` VALUES (1457, '教101-灯3', 0, '2021-04-26 23:09:06', '{\"state\":\"{\\\"brightness\\\":0,\\\"openState\\\":1,\\\"lampSense\\\":0}\"}', NULL, 0, '5b11a72a71504e189c86c2a5c6dfe4b5');

-- ----------------------------
-- Table structure for request_log
-- ----------------------------
DROP TABLE IF EXISTS `request_log`;
CREATE TABLE `request_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `device_id` bigint(20) NOT NULL,
  `request_time` timestamp NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `request_log_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of request_log
-- ----------------------------
INSERT INTO `request_log` VALUES (6, 1447, '2021-04-26 20:18:04', '{\"state\":\"{\\\"openState\\\":0,\\\"temperature\\\":0,\\\"pattern\\\":0,\\\"gear\\\":0}\"}');
INSERT INTO `request_log` VALUES (9, 1447, '2021-04-26 21:01:47', '{\"state\":\"{\\\"openState\\\":1,\\\"pattern\\\":1,\\\"temperature\\\":-14,\\\"gear\\\":2}\"}');
INSERT INTO `request_log` VALUES (10, 1454, '2021-04-26 21:05:09', '{\"state\":\"{\\\"openState\\\":0}\"}');
INSERT INTO `request_log` VALUES (11, 1454, '2021-04-26 21:05:15', '{\"state\":\"{\\\"openState\\\":1}\"}');
INSERT INTO `request_log` VALUES (12, 1447, '2021-04-26 22:18:15', '{\"state\":\"{\\\"openState\\\":0,\\\"pattern\\\":1,\\\"temperature\\\":-14,\\\"gear\\\":2}\"}');
INSERT INTO `request_log` VALUES (13, 1454, '2021-04-26 22:19:51', '{\"state\":\"{\\\"openState\\\":0}\"}');
INSERT INTO `request_log` VALUES (14, 1447, '2021-04-26 22:34:31', '{\"state\":\"{\\\"openState\\\":0,\\\"pattern\\\":1,\\\"temperature\\\":-14,\\\"gear\\\":2}\"}');
INSERT INTO `request_log` VALUES (15, 1454, '2021-04-26 22:34:31', '{\"state\":\"{\\\"openState\\\":1}\"}');
INSERT INTO `request_log` VALUES (16, 1456, '2021-04-26 23:00:17', '{\"state\":\"{\\\"openState\\\":0}\"}');
INSERT INTO `request_log` VALUES (17, 1456, '2021-04-26 23:02:24', '{\"state\":\"{\\\"openState\\\":1}\"}');
INSERT INTO `request_log` VALUES (18, 1447, '2021-04-26 23:02:24', '{\"state\":\"{\\\"openState\\\":0,\\\"pattern\\\":2,\\\"temperature\\\":-14,\\\"gear\\\":2}\"}');
INSERT INTO `request_log` VALUES (19, 1454, '2021-04-26 23:02:24', '{\"state\":\"{\\\"openState\\\":1}\"}');
INSERT INTO `request_log` VALUES (20, 1456, '2021-04-26 23:08:07', '{\"state\":\"{\\\"openState\\\":0}\"}');
INSERT INTO `request_log` VALUES (21, 1447, '2021-04-26 23:08:07', '{\"state\":\"{\\\"openState\\\":0,\\\"pattern\\\":2,\\\"temperature\\\":-14,\\\"gear\\\":2}\"}');
INSERT INTO `request_log` VALUES (22, 1454, '2021-04-26 23:08:07', '{\"state\":\"{\\\"openState\\\":0}\"}');
INSERT INTO `request_log` VALUES (23, 1457, '2021-04-26 23:09:19', '{\"state\":\"{\\\"brightness\\\":0,\\\"openState\\\":0,\\\"lampSense\\\":0}\"}');
INSERT INTO `request_log` VALUES (24, 1456, '2021-04-26 23:09:28', '{\"state\":\"{\\\"openState\\\":1}\"}');
INSERT INTO `request_log` VALUES (25, 1457, '2021-04-26 23:09:28', '{\"state\":\"{\\\"brightness\\\":0,\\\"openState\\\":1,\\\"lampSense\\\":0}\"}');
INSERT INTO `request_log` VALUES (26, 1447, '2021-04-26 23:09:28', '{\"state\":\"{\\\"openState\\\":1,\\\"pattern\\\":1,\\\"temperature\\\":-14,\\\"gear\\\":2}\"}');
INSERT INTO `request_log` VALUES (27, 1454, '2021-04-26 23:09:28', '{\"state\":\"{\\\"openState\\\":1}\"}');

-- ----------------------------
-- Table structure for rule
-- ----------------------------
DROP TABLE IF EXISTS `rule`;
CREATE TABLE `rule`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `rule_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rule
-- ----------------------------
INSERT INTO `rule` VALUES ('0', '下课', '关闭所有设备', '2021-04-20 00:05:00', '2021-04-20 00:05:00');
INSERT INTO `rule` VALUES ('1', '日间上课', '打开投影仪', '2021-04-20 00:05:41', '2021-04-20 00:05:41');
INSERT INTO `rule` VALUES ('2', '夜间上课', '打开灯、投影仪', '2021-04-20 00:05:54', '2021-04-20 00:05:54');
INSERT INTO `rule` VALUES ('3', '夏季日间上课', '打开空调、投影仪，空调设为制冷3档', '2021-04-20 00:05:58', '2021-04-20 00:05:58');
INSERT INTO `rule` VALUES ('4', '夏季夜间上课', '打开灯、空调、投影仪，空调设为制冷2档', '2021-04-20 00:06:01', '2021-04-20 00:06:01');
INSERT INTO `rule` VALUES ('5', '冬季日间上课', '打开空调、投影仪，空调设为制热2档', '2021-04-20 00:06:05', '2021-04-20 00:06:05');
INSERT INTO `rule` VALUES ('6', '冬季夜间上课', '打开灯、空调、投影仪，空调设为制热3档', '2021-04-20 00:06:08', '2021-04-20 00:06:08');

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `team_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `group_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team
-- ----------------------------
INSERT INTO `team` VALUES (1, '教101', '2021-04-20 20:51:05');
INSERT INTO `team` VALUES (2, '教102', '2021-04-20 20:51:36');
INSERT INTO `team` VALUES (3, '教103', '2021-04-20 20:51:41');
INSERT INTO `team` VALUES (4, '教201', '2021-04-20 20:51:48');
INSERT INTO `team` VALUES (5, '教202', '2021-04-20 20:51:51');
INSERT INTO `team` VALUES (7, '教222', '2021-04-20 20:52:00');
INSERT INTO `team` VALUES (9, '教301', '2021-04-21 18:55:42');

SET FOREIGN_KEY_CHECKS = 1;
