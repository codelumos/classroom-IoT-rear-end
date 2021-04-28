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
 Date: 27/04/2021 12:36:41
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
) ENGINE = InnoDB AUTO_INCREMENT = 1018 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES (1000, '教101-灯1', 0, '2021-04-27 12:04:38', '{\"state\":\"{\\\"brightness\\\":0,\\\"openState\\\":0,\\\"lampSense\\\":0}\"}', NULL, 1, '5438881fba634dab9f67b59ef472290b');
INSERT INTO `device` VALUES (1001, '教101-灯2', 0, '2021-04-27 12:06:31', '{\"state\":\"{\\\"brightness\\\":0,\\\"openState\\\":0,\\\"lampSense\\\":0}\"}', NULL, 1, 'c4c775322cd1497eba050b6860f49b59');
INSERT INTO `device` VALUES (1002, '教101-灯3', 0, '2021-04-27 12:11:46', '{\"state\":\"{\\\"brightness\\\":9,\\\"openState\\\":1,\\\"lampSense\\\":4}\"}', NULL, 1, '5b11a72a71504e189c86c2a5c6dfe4b5');
INSERT INTO `device` VALUES (1003, '教101-空调1', 1, '2021-04-27 12:07:40', '{\"state\":\"{\\\"openState\\\":0,\\\"temperature\\\":0,\\\"pattern\\\":0,\\\"gear\\\":0}\"}', NULL, 1, '202915d9952a4ea6b1a9b8ac119eafa1');
INSERT INTO `device` VALUES (1004, '教101-空调2', 1, '2021-04-27 12:07:56', '{\"state\":\"{\\\"openState\\\":0,\\\"temperature\\\":0,\\\"pattern\\\":0,\\\"gear\\\":0}\"}', NULL, 1, '41052d81da1c440bb709d12ef1d3474a');
INSERT INTO `device` VALUES (1005, '教101-投影仪', 2, '2021-04-27 12:07:25', '{\"state\":\"{\\\"openState\\\":0}\"}', NULL, 1, '7ad735f6acff469da6c5f02e57c445cd');
INSERT INTO `device` VALUES (1006, '教102-灯1', 0, '2021-04-27 12:01:57', NULL, NULL, 2, 'a06506bdad934cbda0a832e06de2c0db');
INSERT INTO `device` VALUES (1007, '教102-灯2', 0, '2021-04-27 12:02:00', NULL, NULL, 2, '6f6e8e02cfa34abfb86218d7c90f3dfa');
INSERT INTO `device` VALUES (1008, '教102-灯3', 0, '2021-04-27 12:02:03', NULL, NULL, 2, 'e1f275307e5548bf9e8cd010e42d1948');
INSERT INTO `device` VALUES (1009, '教102-空调1', 1, '2021-04-27 12:01:47', NULL, NULL, 2, 'b650326549c045e38775c3fb35d269e9');
INSERT INTO `device` VALUES (1010, '教102-空调2', 1, '2021-04-27 12:01:44', NULL, NULL, 2, '978bc2a2f19e41488bf434ebf2436e82');
INSERT INTO `device` VALUES (1011, '教102-投影仪', 2, '2021-04-27 12:02:10', NULL, NULL, 2, '0184674473ab44d19e11bf3542733c39');
INSERT INTO `device` VALUES (1012, '教103-灯1', 0, '2021-04-27 12:02:19', NULL, NULL, 3, '226a5e6e3afd4301b84be2c443bca752');
INSERT INTO `device` VALUES (1013, '教103-灯2', 0, '2021-04-27 12:02:22', NULL, NULL, 3, 'd5cf3efb88c1423cbd1603007ab459c2');
INSERT INTO `device` VALUES (1014, '教103-灯3', 0, '2021-04-27 12:02:25', NULL, NULL, 3, '38102aee461f4a57a3e51e07241b8f9c');
INSERT INTO `device` VALUES (1015, '教103-空调1', 1, '2021-04-27 12:02:32', NULL, NULL, 3, 'af23223eb77147f4a2f9c088c6fd137d');
INSERT INTO `device` VALUES (1016, '教103-空调2', 1, '2021-04-27 12:02:36', NULL, NULL, 3, '96e7365648244b85abac00830be9b253');
INSERT INTO `device` VALUES (1017, '教103-投影仪', 2, '2021-04-27 12:02:42', NULL, NULL, 3, '200b9b4904e54646848b8e524db2634a');

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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of request_log
-- ----------------------------
INSERT INTO `request_log` VALUES (1, 1000, '2021-04-27 12:04:19', '{\"state\":\"{\\\"brightness\\\":0,\\\"openState\\\":0,\\\"lampSense\\\":0}\"}');
INSERT INTO `request_log` VALUES (2, 1001, '2021-04-27 12:06:27', '{\"state\":\"{\\\"brightness\\\":0,\\\"openState\\\":0,\\\"lampSense\\\":0}\"}');
INSERT INTO `request_log` VALUES (3, 1002, '2021-04-27 12:07:21', '{\"state\":\"{\\\"brightness\\\":9,\\\"openState\\\":1,\\\"lampSense\\\":4}\"}');
INSERT INTO `request_log` VALUES (4, 1003, '2021-04-27 12:07:35', '{\"state\":\"{\\\"openState\\\":0,\\\"temperature\\\":0,\\\"pattern\\\":0,\\\"gear\\\":0}\"}');
INSERT INTO `request_log` VALUES (5, 1004, '2021-04-27 12:07:51', '{\"state\":\"{\\\"openState\\\":0,\\\"temperature\\\":0,\\\"pattern\\\":0,\\\"gear\\\":0}\"}');
INSERT INTO `request_log` VALUES (6, 1005, '2021-04-27 12:10:40', '{\"state\":\"{\\\"openState\\\":0}\"}');

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
-- Table structure for device_group
-- ----------------------------
DROP TABLE IF EXISTS `device_group`;
CREATE TABLE `device_group`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `group_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_group
-- ----------------------------
INSERT INTO `device_group` VALUES (1, '教101', '2021-04-20 20:51:05');
INSERT INTO `device_group` VALUES (2, '教102', '2021-04-20 20:51:48');
INSERT INTO `device_group` VALUES (3, '教103', '2021-04-21 18:55:42');

SET FOREIGN_KEY_CHECKS = 1;
