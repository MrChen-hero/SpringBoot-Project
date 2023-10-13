/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80100
 Source Host           : localhost:3306
 Source Schema         : epithesis

 Target Server Type    : MySQL
 Target Server Version : 80100
 File Encoding         : 65001

 Date: 31/08/2023 23:01:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for homepage
-- ----------------------------
DROP TABLE IF EXISTS `homepage`;
CREATE TABLE `homepage`  (
  `id` int(10) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '内容',
  `pubType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '话题类型',
  `imgUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片路径',
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of homepage
-- ----------------------------
INSERT INTO `homepage` VALUES (0000000001, '测试标题', '随便写点随便写点随便写点随便写点随便写点随便写点随便写点随便写点随便写点随便写点随便写点随便写点随便写点随便写点随便写点随便写点随便写点随便写点随便写点随便写点', '科学,技术,教育', NULL, '赵六');

-- ----------------------------
-- Table structure for publicationtype
-- ----------------------------
DROP TABLE IF EXISTS `publicationtype`;
CREATE TABLE `publicationtype`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `typeName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of publicationtype
-- ----------------------------
INSERT INTO `publicationtype` VALUES (1, '科学');
INSERT INTO `publicationtype` VALUES (2, '技术');
INSERT INTO `publicationtype` VALUES (3, '教育');

SET FOREIGN_KEY_CHECKS = 1;
