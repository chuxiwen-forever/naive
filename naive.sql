/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : 101.43.178.138:3306
 Source Schema         : naive

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 08/09/2022 10:39:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for classify
-- ----------------------------
DROP TABLE IF EXISTS `classify`;
CREATE TABLE `classify`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `parent_id` int NULL DEFAULT NULL COMMENT '父级分类',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `dsc` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `router` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由',
  `type` int NULL DEFAULT NULL COMMENT '分类种类 1-源码 2-软件 3-文章',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classify
-- ----------------------------
INSERT INTO `classify` VALUES (1, 0, '友链', '好朋友', '/links', 1);
INSERT INTO `classify` VALUES (2, 1, '挚友', '最好的朋友', '/friend', 1);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `parent_id` int NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问路径',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 0, '仪表盘', 'dashboard', 'IconCompass', '/dashboard', '@layout/page-view');
INSERT INTO `menu` VALUES (2, 1, '工作台2', 'workplace', NULL, '/dashboard/workplace', 'dashboard/workplace/index');
INSERT INTO `menu` VALUES (3, 1, '监控', 'monitor', NULL, '/dashboard/monitor', 'dashboard/monitor/index');
INSERT INTO `menu` VALUES (4, 0, '系统管理', 'system', 'icon-settings', '/system', '@layout/page-view');
INSERT INTO `menu` VALUES (5, 4, '菜单管理', 'menus', 'IconApps', '/system/menus', 'system/menus/index');
INSERT INTO `menu` VALUES (6, 4, '用户管理', 'users', 'IconUser', '/system/users', 'system/users/index');
INSERT INTO `menu` VALUES (7, 0, '异常', 'exception', 'icon-bug', '/exception', '@layout/page-view');
INSERT INTO `menu` VALUES (8, 7, '403', '403', NULL, '/403', 'exception/403/index');
INSERT INTO `menu` VALUES (9, 7, '404', '404', NULL, '/404', 'exception/404/index');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` int NULL DEFAULT 1 COMMENT '状态可用为1，不可用为0',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` int UNSIGNED NULL DEFAULT 0 COMMENT '未删除为0，删除为1',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `sex` int NULL DEFAULT NULL COMMENT '1-男生 0-女生',
  `role` int NULL DEFAULT NULL COMMENT '1-管理员 0-普通用户',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '$2a$10$OuoADWWIFjcsDz3SkkOJH.TRuKZpEfnLPgVvggt8cgaSxhMETs/Um', NULL, 'https://alifei04.cfp.cn/creative/vcg/800/new/VCG21gic13717120.jpg', 'admin@qq.com', 1, '2022-07-28 19:07:57', '2022-07-28 19:07:57', 0, NULL, NULL, 0, '181817');
INSERT INTO `user` VALUES (2, 'test', '$2a$10$QuETB7HC5ulj5j7xMhBWUOjuGfSQojgZTLK9P3xAfHwYcXRZAUrJ6', '', 'https://alifei04.cfp.cn/creative/vcg/800/new/VCG21gic13717120.jpg', 'test@qq.com', 0, '2022-07-28 19:43:52', '2022-07-28 20:22:14', 0, '', 0, 0, '9bdbc6');
INSERT INTO `user` VALUES (3, 'string', '$2a$10$mpBSq6YVUyv9jHk72g6jKeUuYUMmK8yopCBXhjJrviaExpkhGAfzq', 'string', 'https://alifei04.cfp.cn/creative/vcg/800/new/VCG21gic13717120.jpg', 'string@qq.com', 0, '2022-07-28 20:32:28', '2022-07-28 20:43:38', 0, '', 0, 0, '677d38');

SET FOREIGN_KEY_CHECKS = 1;
