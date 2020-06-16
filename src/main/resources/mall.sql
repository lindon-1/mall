/*
 Navicat MySQL Data Transfer

 Source Server         : security
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : mall

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 16/06/2020 15:42:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mall_resource
-- ----------------------------
DROP TABLE IF EXISTS `mall_resource`;
CREATE TABLE `mall_resource`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源',
  `method_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'get' COMMENT '资源类型',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_resource
-- ----------------------------
INSERT INTO `mall_resource` VALUES (1, '/mall/resource/findList', 'POST', '2020-06-12 15:17:25', '2020-06-12 16:25:49', 0);
INSERT INTO `mall_resource` VALUES (33, '/mall/user/list', 'POST', '2020-06-16 15:33:48', '2020-06-16 15:33:48', 0);

-- ----------------------------
-- Table structure for mall_role
-- ----------------------------
DROP TABLE IF EXISTS `mall_role`;
CREATE TABLE `mall_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `desc` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name_UNIQUE`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_role
-- ----------------------------
INSERT INTO `mall_role` VALUES (1, '超级管理员', 'ddd', 1, '2020-06-10 16:32:31', '2020-06-12 15:17:59', 0);
INSERT INTO `mall_role` VALUES (4, '/admin/add', '添加', 1, '2020-06-10 17:08:25', '2020-06-10 17:08:25', 0);
INSERT INTO `mall_role` VALUES (6, '/admin/delete', '添加', 1, '2020-06-10 17:16:57', '2020-06-10 17:16:57', 0);

-- ----------------------------
-- Table structure for mall_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `mall_role_resource`;
CREATE TABLE `mall_role_resource`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限资源关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_role_resource
-- ----------------------------
INSERT INTO `mall_role_resource` VALUES (32, 1, 1, '2020-06-12 15:18:09', '2020-06-12 15:18:09');
INSERT INTO `mall_role_resource` VALUES (33, 1, 33, '2020-06-16 15:34:13', '2020-06-16 15:34:13');

-- ----------------------------
-- Table structure for mall_user
-- ----------------------------
DROP TABLE IF EXISTS `mall_user`;
CREATE TABLE `mall_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名称',
  `password` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户密码',
  `gender` tinyint(3) NULL DEFAULT 0 COMMENT '性别：0 未知， 1男， 1 女',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最近一次登录时间',
  `last_login_ip` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最近一次登录IP地址',
  `nickname` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户昵称或网络名称',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户手机号码',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户头像图片',
  `openid` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '微信登录openid',
  `session_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '微信登录会话KEY',
  `status` tinyint(3) NULL DEFAULT 0 COMMENT '0 可用, 1 禁用, 2 注销',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_name`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_user
-- ----------------------------
INSERT INTO `mall_user` VALUES (1, 'lindl', 'e10adc3949ba59abbe56e057f20f883e', 0, '2020-06-10', NULL, '192.168.44.128', '', '13433836150', '', '', '', 0, '2020-06-10 16:49:48', '2020-06-11 14:29:20', 0);
INSERT INTO `mall_user` VALUES (3, 'huli', 'e10adc3949ba59abbe56e057f20f883e', 0, '2020-06-10', NULL, '192.168.44.128', NULL, '13433836150', NULL, NULL, NULL, 0, '2020-06-10 17:14:45', '2020-06-11 14:29:22', 0);
INSERT INTO `mall_user` VALUES (5, 'youxiang', 'e10adc3949ba59abbe56e057f20f883e', 0, '2020-06-10', NULL, '192.168.44.128', NULL, '13433836156', NULL, NULL, NULL, 0, '2020-06-10 17:16:57', '2020-06-11 14:29:26', 0);

-- ----------------------------
-- Table structure for mall_user_role
-- ----------------------------
DROP TABLE IF EXISTS `mall_user_role`;
CREATE TABLE `mall_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_user_role
-- ----------------------------
INSERT INTO `mall_user_role` VALUES (32, 1, 1, '2020-06-12 15:18:22', '2020-06-12 15:18:22');

SET FOREIGN_KEY_CHECKS = 1;
