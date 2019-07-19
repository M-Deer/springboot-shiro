/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : t_shiro

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 19/07/2019 13:10:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for PERMISSION
-- ----------------------------
DROP TABLE IF EXISTS `PERMISSION`;
CREATE TABLE `PERMISSION`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `PERMISSION_EXPRESSION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限表达式',
  `PERMISSION_DESCRIPTION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of PERMISSION
-- ----------------------------
INSERT INTO `PERMISSION` VALUES (1, '*:*', '通配符，拥有全部权限');
INSERT INTO `PERMISSION` VALUES (2, 'user:select', '用户管理查询数据');
INSERT INTO `PERMISSION` VALUES (3, 'user:insert', '用户管理新增数据');
INSERT INTO `PERMISSION` VALUES (4, 'user:update', '用户管理修改数据');
INSERT INTO `PERMISSION` VALUES (5, 'user:delete', '用户管理删除数据');
INSERT INTO `PERMISSION` VALUES (6, 'user:view', '用户管理页面路由请求');
INSERT INTO `PERMISSION` VALUES (7, 'department:select', '部门管理查询数据');
INSERT INTO `PERMISSION` VALUES (8, 'department:insert', '部门管理新增数据');
INSERT INTO `PERMISSION` VALUES (9, 'department:update', '部门管理修改数据');
INSERT INTO `PERMISSION` VALUES (10, 'department:delete', '部门管理删除数据');
INSERT INTO `PERMISSION` VALUES (11, 'department:view', '部门管理页面路由请求');

-- ----------------------------
-- Table structure for ROLE
-- ----------------------------
DROP TABLE IF EXISTS `ROLE`;
CREATE TABLE `ROLE`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ROLE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `DESCRIPTION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ROLE
-- ----------------------------
INSERT INTO `ROLE` VALUES (1, 'admin', '管理员');
INSERT INTO `ROLE` VALUES (2, 'userManager', '人事经理');
INSERT INTO `ROLE` VALUES (3, 'departmentManager', '部门经理');

-- ----------------------------
-- Table structure for ROLE_PERMISSION
-- ----------------------------
DROP TABLE IF EXISTS `ROLE_PERMISSION`;
CREATE TABLE `ROLE_PERMISSION`  (
  `ROLE_ID` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  `PERMISSION_ID` int(11) NULL DEFAULT NULL COMMENT '权限ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ROLE_PERMISSION
-- ----------------------------
INSERT INTO `ROLE_PERMISSION` VALUES (1, 1);
INSERT INTO `ROLE_PERMISSION` VALUES (2, 2);
INSERT INTO `ROLE_PERMISSION` VALUES (2, 3);
INSERT INTO `ROLE_PERMISSION` VALUES (2, 4);
INSERT INTO `ROLE_PERMISSION` VALUES (2, 5);
INSERT INTO `ROLE_PERMISSION` VALUES (2, 6);
INSERT INTO `ROLE_PERMISSION` VALUES (3, 7);
INSERT INTO `ROLE_PERMISSION` VALUES (3, 8);
INSERT INTO `ROLE_PERMISSION` VALUES (3, 9);
INSERT INTO `ROLE_PERMISSION` VALUES (3, 10);
INSERT INTO `ROLE_PERMISSION` VALUES (3, 11);

-- ----------------------------
-- Table structure for USER
-- ----------------------------
DROP TABLE IF EXISTS `USER`;
CREATE TABLE `USER`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USERNAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of USER
-- ----------------------------
INSERT INTO `USER` VALUES (1, 'Tom', 'b1e6d1bd047f43af0ab59556c394a376');
INSERT INTO `USER` VALUES (2, 'Jerry', 'f07234609129827c514995bf3fc9608a');
INSERT INTO `USER` VALUES (3, 'Amy', 'a8aeb8af47fda42917ac7be2a66baa7b');

-- ----------------------------
-- Table structure for USER_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `USER_ROLE`;
CREATE TABLE `USER_ROLE`  (
  `USER_ID` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `ROLE_ID` int(11) NULL DEFAULT NULL COMMENT '角色ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of USER_ROLE
-- ----------------------------
INSERT INTO `USER_ROLE` VALUES (2, 1);
INSERT INTO `USER_ROLE` VALUES (3, 2);
INSERT INTO `USER_ROLE` VALUES (1, 3);

SET FOREIGN_KEY_CHECKS = 1;
