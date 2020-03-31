/*
 Navicat MySQL Data Transfer

 Source Server         : 1111
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : test2

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 31/03/2020 12:13:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menu_path` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态1正常,0禁用',
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` int(11) NULL DEFAULT NULL,
  `show_index` int(4) NULL DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (2, '测试的', 'http://baidu.com', '1', '&#xe705;', 50, 3, 'admin', '2020-03-15 17:15:04', 'admin', '2020-03-16 16:55:34');
INSERT INTO `menu` VALUES (3, '账号管理', '#', '1', '&#xe770;', 0, 1, 'admin', '2020-03-15 17:09:42', 'admin', '2020-03-16 16:33:01');
INSERT INTO `menu` VALUES (4, '资源管理', '#', '1', '&#xe656;', 0, 2, 'admin', '2020-03-15 17:14:49', 'admin', '2020-03-16 16:32:57');
INSERT INTO `menu` VALUES (5, '管理员权限', '#', '1', '&#xe672;', 0, 4, 'admin', '2020-03-15 18:43:46', 'admin', '2020-03-16 16:55:40');
INSERT INTO `menu` VALUES (11, '角色管理', './pages/admin/role.html', '1', '&#xe613;', 5, 1, 'admin', '2020-03-16 14:20:39', 'admin', '2020-03-16 14:20:39');
INSERT INTO `menu` VALUES (12, '菜单管理', './pages/admin/menu.html', '1', '&#xe62d;', 5, 2, NULL, NULL, NULL, NULL);
INSERT INTO `menu` VALUES (22, '图标一览', './pages/admin/school.html', '1', '&#xe68e;', 4, 3, 'admin', '2020-03-16 14:27:47', 'admin', '2020-03-16 16:34:13');
INSERT INTO `menu` VALUES (31, '账号列表', './pages/acc/acc.html', '1', '&#xe66f;', 3, 1, 'admin', '2020-03-15 21:15:51', 'admin', '2020-03-16 15:53:26');
INSERT INTO `menu` VALUES (42, '资源管理', './pages/acc/module.html', '1', '&#xe62a;', 4, 1, 'admin', '2020-03-17 17:36:34', 'admin', '2020-03-17 17:36:34');
INSERT INTO `menu` VALUES (44, '系统日志', './pages/admin/syslog.html', '1', '&#xe63c;', 4, 4, NULL, NULL, NULL, NULL);
INSERT INTO `menu` VALUES (49, '权限管理', './pages/admin/permission.html', '1', '&#xe665;', 5, 3, NULL, NULL, NULL, NULL);
INSERT INTO `menu` VALUES (50, '测试的', '#', '1', '&#xe626;', 0, 0, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `module` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `path` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('3A385D0B0E6545F286F8F89165241558', '权限模块', '/permission/');
INSERT INTO `module` VALUES ('3E2B75D82D9147F1B2F828FE947A11BE', '账号模块', '/adminAcc/');
INSERT INTO `module` VALUES ('B50E6625AB7B44B5AF16803799265BC7', '角色模块', '/role/');
INSERT INTO `module` VALUES ('BF872AD4B89D4BA98E9B95775D16398F', '菜单模块', '/menu/');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态 0未审核 1审核通过 2 过期 3草稿 4删除 5审核不通过',
  `type_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_date` datetime(0) NULL DEFAULT NULL,
  `end_date` datetime(0) NULL DEFAULT NULL,
  `title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `place` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `publisher_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `school_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对应高校的ID',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('0E7B774121A8411A86717AA8E1170EDB', '0', 'A224A788B10147B7B269EA8C74EF185E', '2019-10-31 19:00:00', '2019-10-31 21:10:10', '大米校招111111111', '工二阶梯教室', '52EE7FDE3A7A4E7588DEC14C173B293F', 'school1', '大米校招，欢迎参与', '52EE7FDE3A7A4E7588DEC14C173B293F', '2019-10-31 10:41:23', '52EE7FDE3A7A4E7588DEC14C173B293F', '2019-10-31 10:41:23');
INSERT INTO `notice` VALUES ('32F9AAE754BA49408E2A0862D1655A72', '1', 'D82B9611AB8E4AABA969E585E64DFB95', '2020-02-06 00:00:00', '2020-02-07 00:00:00', 'ces讲座', '工一阶梯教室', '1', 'school1', 'xxx讲座，欢迎广大同学前来参与', '1', '2020-02-06 14:17:46', 'admin', '2020-03-06 21:55:23');
INSERT INTO `notice` VALUES ('4651057B670648928D8EDB5D1B6D5BBF', '1', 'A224A788B10147B7B269EA8C74EF185E', '2019-10-31 19:00:00', '2019-10-31 21:10:10', '小米校招', '工二阶梯教室', '52EE7FDE3A7A4E7588DEC14C173B293F', 'school1', '小米校招，欢迎参与', '52EE7FDE3A7A4E7588DEC14C173B293F', '2019-10-31 10:41:17', 'admin', '2020-01-10 16:01:28');
INSERT INTO `notice` VALUES ('472E93ABAB4E4BCBA2156B521DBE1F93', '1', 'D82B9611AB8E4AABA969E585E64DFB95', '2020-03-27 00:00:00', '2020-04-04 00:00:00', '考研讲座', '工一阶梯教室', '1', 'school1', '考研讲座来了', '1', '2020-03-09 17:20:48', 'admin', '2020-03-09 17:21:17');
INSERT INTO `notice` VALUES ('58A08F97E9F94D7BA41E083993B215B5', '1', 'A224A788B10147B7B269EA8C74EF185E', '2020-01-10 00:00:00', '2020-10-10 00:00:00', 'xxxxx校招', '工一阶梯教室', '52EE7FDE3A7A4E7588DEC14C173B293F', 'school1', '校招讲座123456789', '52EE7FDE3A7A4E7588DEC14C173B293F', '2020-01-09 09:34:32', 'admin', '2020-02-05 17:30:41');
INSERT INTO `notice` VALUES ('B3B7E62C3C8945319276F879FE9F00DB', '1', 'D82B9611AB8E4AABA969E585E64DFB95', '2019-12-30 00:00:00', '2020-01-01 00:00:00', 'xxxxx讲座', '工一阶梯教室', 'D00465EE651748D1BBC50C9A5D0116E1', 'school2', 'xxx讲座，欢迎广大同学前来参与', 'D00465EE651748D1BBC50C9A5D0116E1', '2019-12-30 15:09:55', 'admin', '2020-02-05 17:30:41');
INSERT INTO `notice` VALUES ('F0464E8B6FEF4FE18AF2543725205014', '0', '949E2ACA2FA645B7BBC7AADD6AD86772', '2019-10-31 19:00:00', '2019-10-31 21:10:10', '国庆放假通知', '无', '52EE7FDE3A7A4E7588DEC14C173B293F', 'school2', '国庆放假安排如下，，，，', '52EE7FDE3A7A4E7588DEC14C173B293F', '2019-10-31 10:42:11', 'admin', '2020-03-06 22:02:29');
INSERT INTO `notice` VALUES ('F0510E3B1231491EBC6B29A20A415B2E', '0', 'D82B9611AB8E4AABA969E585E64DFB95', '2019-10-31 19:00:00', '2019-11-08 21:10:10', '1讲座', '工一阶梯教室1', '52EE7FDE3A7A4E7588DEC14C173B293F', 'school2', 'xxx讲座，欢迎广大同学前来参与11', '52EE7FDE3A7A4E7588DEC14C173B293F', '2019-10-31 10:40:37', '52EE7FDE3A7A4E7588DEC14C173B293F', '2019-11-08 15:03:51');
INSERT INTO `notice` VALUES ('F70C0B16A49B4555B9B4FA37399E8DD2', '5', 'A224A788B10147B7B269EA8C74EF185E', '2020-03-14 00:00:00', '2020-03-21 00:00:00', 'a信息科技校招', '工一阶梯教室', '1', 'school2', '公司校招', NULL, NULL, '1', '2020-03-09 15:18:47');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `permission_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mark` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态1正常,0禁用',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('0E7B774121A8411A86717AA8E1170EDB', '超级管理员', '超级管理员', '2EE10E1823A5443BBCA6206A46636C2C', '52EE7FDE3A7A4E7588DEC14C173B293F', '2019-10-31 10:41:23', 'admin', '2020-03-17 18:31:25');
INSERT INTO `permission` VALUES ('176DBC4E7F374F0DB7273FA337986BAA', 'add', '添加数据', 'AFD87A32ACF6406F876D369B1A8F6094', 'admin', '2020-01-13 15:51:07', 'admin', '2020-03-22 10:12:29');
INSERT INTO `permission` VALUES ('2E5C662B37524343A5FE4FC754D7646C', 'edit', '编辑', '4651057B670648928D8EDB5D1B6D5BBF', 'admin', '2020-01-29 11:08:19', 'admin', '2020-03-22 10:11:43');
INSERT INTO `permission` VALUES ('4B8AF8A678F94F2583014E315D39E88A', 'delete', '删除数据', '76E780369E634478AD1CDC1B71EC8322', 'admin', '2020-03-22 10:12:53', 'admin', '2020-03-22 12:44:44');
INSERT INTO `permission` VALUES ('EF258FE295C943739E76A5EBC7757600', 'see', '浏览者', 'F0510E3B1231491EBC6B29A20A415B2E', 'admin', '2020-01-13 16:02:15', 'admin', '2020-03-22 10:12:01');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mark` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态1正常,0禁用',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `level` int(2) NULL DEFAULT NULL COMMENT '级数，0为最大',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('2EE10E1823A5443BBCA6206A46636C2C', '超级管理员', '有所有权限', '1', 'admin', '2020-02-06 09:27:47', 'admin', '2020-03-19 19:23:14', 0);
INSERT INTO `role` VALUES ('4651057B670648928D8EDB5D1B6D5BBF', 'onlyEdit', '只能编辑的角色', '1', '52EE7FDE3A7A4E7588DEC14C173B293F', '2019-10-31 10:41:17', 'admin', '2020-03-19 17:45:38', 5);
INSERT INTO `role` VALUES ('76E780369E634478AD1CDC1B71EC8322', 'onlyDelete', '只能删除', '1', 'admin', '2020-02-04 17:32:25', 'admin', '2020-02-06 09:27:09', 6);
INSERT INTO `role` VALUES ('AFD87A32ACF6406F876D369B1A8F6094', 'onlyInsert', '只能新增数据', '1', 'admin', '2020-02-02 11:13:38', 'admin', '2020-02-06 09:27:19', 9);
INSERT INTO `role` VALUES ('F0510E3B1231491EBC6B29A20A415B2E', 'onlysee', '只能浏览，不能增删改', '1', '52EE7FDE3A7A4E7588DEC14C173B293F', '2019-10-31 10:40:37', 'admin', '2020-01-29 11:02:42', 20);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menu_id` int(11) NULL DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ind_role_`(`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('01C4DC40B80E43ECA1BD3B37D3201C67', '2EE10E1823A5443BBCA6206A46636C2C', 2, 'system', 'system', '2020-03-27 21:21:01', '2020-03-27 21:21:01');
INSERT INTO `role_menu` VALUES ('068ED9C9952445DA99D88DA3247D232E', '2EE10E1823A5443BBCA6206A46636C2C', 12, 'system', 'system', '2020-03-27 21:21:01', '2020-03-27 21:21:01');
INSERT INTO `role_menu` VALUES ('0774CD113C2946B5B3F417C2D0583E45', '2EE10E1823A5443BBCA6206A46636C2C', 3, 'system', 'system', '2020-03-27 21:21:01', '2020-03-27 21:21:01');
INSERT INTO `role_menu` VALUES ('0C67E7EEFB294D3D88F6541002F2A22A', '76E780369E634478AD1CDC1B71EC8322', 4, 'system', 'system', '2020-03-30 23:00:00', '2020-03-30 23:00:00');
INSERT INTO `role_menu` VALUES ('1D973AC0B124422BB4BECFE07E7A70E2', 'F0510E3B1231491EBC6B29A20A415B2E', 11, 'system', 'system', '2020-03-30 23:00:38', '2020-03-30 23:00:38');
INSERT INTO `role_menu` VALUES ('22072A83FDA54BCF93D5B227C27389B2', '76E780369E634478AD1CDC1B71EC8322', 22, 'system', 'system', '2020-03-30 23:00:00', '2020-03-30 23:00:00');
INSERT INTO `role_menu` VALUES ('2274284E91524762A187095B88D87166', '2EE10E1823A5443BBCA6206A46636C2C', 5, 'system', 'system', '2020-03-27 21:21:01', '2020-03-27 21:21:01');
INSERT INTO `role_menu` VALUES ('248F792291034F109D14042C32BE2136', 'AFD87A32ACF6406F876D369B1A8F6094', 42, 'system', 'system', '2020-03-30 23:00:16', '2020-03-30 23:00:16');
INSERT INTO `role_menu` VALUES ('2664F6DF9D294D04822E2AFA2C5C7ACC', '76E780369E634478AD1CDC1B71EC8322', 12, 'system', 'system', '2020-03-30 23:00:00', '2020-03-30 23:00:00');
INSERT INTO `role_menu` VALUES ('389E13C820E043C2B54900768C806D5E', '76E780369E634478AD1CDC1B71EC8322', 5, 'system', 'system', '2020-03-30 23:00:00', '2020-03-30 23:00:00');
INSERT INTO `role_menu` VALUES ('4E8AEDDACD26432C824462874838B322', 'AFD87A32ACF6406F876D369B1A8F6094', 49, 'system', 'system', '2020-03-30 23:00:16', '2020-03-30 23:00:16');
INSERT INTO `role_menu` VALUES ('52975896534E4A48A88073020E23BABA', 'F0510E3B1231491EBC6B29A20A415B2E', 12, 'system', 'system', '2020-03-30 23:00:38', '2020-03-30 23:00:38');
INSERT INTO `role_menu` VALUES ('5F5E3A151F034B94B68E0E6DB4435754', '2EE10E1823A5443BBCA6206A46636C2C', 4, 'system', 'system', '2020-03-27 21:21:01', '2020-03-27 21:21:01');
INSERT INTO `role_menu` VALUES ('64B98565D8084C5C9E3B919DEFF4F5E4', 'F0510E3B1231491EBC6B29A20A415B2E', 2, 'system', 'system', '2020-03-30 23:00:38', '2020-03-30 23:00:38');
INSERT INTO `role_menu` VALUES ('6C6E7A2880AA44FCB2C42B2343F8F5E5', 'AFD87A32ACF6406F876D369B1A8F6094', 5, 'system', 'system', '2020-03-30 23:00:16', '2020-03-30 23:00:16');
INSERT INTO `role_menu` VALUES ('6E910D23C484486F94AD747F097EB9E9', 'F0510E3B1231491EBC6B29A20A415B2E', 22, 'system', 'system', '2020-03-30 23:00:38', '2020-03-30 23:00:38');
INSERT INTO `role_menu` VALUES ('716B80F439E143EE8593A1A7D6111D0C', '4651057B670648928D8EDB5D1B6D5BBF', 22, 'system', 'system', '2020-03-27 21:12:29', '2020-03-27 21:12:29');
INSERT INTO `role_menu` VALUES ('73CEA4CE29554FB1B3D43CD4B6C39494', '2EE10E1823A5443BBCA6206A46636C2C', 31, 'system', 'system', '2020-03-27 21:21:01', '2020-03-27 21:21:01');
INSERT INTO `role_menu` VALUES ('7590375259124458BE40B6FCA637E874', 'F0510E3B1231491EBC6B29A20A415B2E', 5, 'system', 'system', '2020-03-30 23:00:38', '2020-03-30 23:00:38');
INSERT INTO `role_menu` VALUES ('769BE2E386A5465CB268AEBE45A8C522', 'AFD87A32ACF6406F876D369B1A8F6094', 4, 'system', 'system', '2020-03-30 23:00:16', '2020-03-30 23:00:16');
INSERT INTO `role_menu` VALUES ('77906FEC041E4DE2A847858117585DB4', '4651057B670648928D8EDB5D1B6D5BBF', 4, 'system', 'system', '2020-03-27 21:12:29', '2020-03-27 21:12:29');
INSERT INTO `role_menu` VALUES ('78037FFB3D3744B9894812DADCD913E5', '4651057B670648928D8EDB5D1B6D5BBF', 42, 'system', 'system', '2020-03-27 21:12:29', '2020-03-27 21:12:29');
INSERT INTO `role_menu` VALUES ('82C6BCCB55E2401193CA7BD022A8FA03', '76E780369E634478AD1CDC1B71EC8322', 2, 'system', 'system', '2020-03-30 23:00:00', '2020-03-30 23:00:00');
INSERT INTO `role_menu` VALUES ('838C0F72F7A3474B8249310D64303280', '76E780369E634478AD1CDC1B71EC8322', 50, 'system', 'system', '2020-03-30 23:00:00', '2020-03-30 23:00:00');
INSERT INTO `role_menu` VALUES ('8425ADA5997E40ACAFA4BA294EC5C4D1', '2EE10E1823A5443BBCA6206A46636C2C', 22, 'system', 'system', '2020-03-27 21:21:01', '2020-03-27 21:21:01');
INSERT INTO `role_menu` VALUES ('867D06C9148B437B941CF71AC72A4EA6', 'F0510E3B1231491EBC6B29A20A415B2E', 50, 'system', 'system', '2020-03-30 23:00:38', '2020-03-30 23:00:38');
INSERT INTO `role_menu` VALUES ('8862DB89259E463A86B5CE36CBA63D6C', '4651057B670648928D8EDB5D1B6D5BBF', 5, 'system', 'system', '2020-03-27 21:12:29', '2020-03-27 21:12:29');
INSERT INTO `role_menu` VALUES ('88A2672F5231455E8BB2F54CC1CD05D1', '2EE10E1823A5443BBCA6206A46636C2C', 44, 'system', 'system', '2020-03-27 21:21:01', '2020-03-27 21:21:01');
INSERT INTO `role_menu` VALUES ('8C60F90690F642CEBDB99BA499B318A2', '4651057B670648928D8EDB5D1B6D5BBF', 12, 'system', 'system', '2020-03-27 21:12:29', '2020-03-27 21:12:29');
INSERT INTO `role_menu` VALUES ('91898E07D5994872A3AE80C3D9498ADA', '4651057B670648928D8EDB5D1B6D5BBF', 44, 'system', 'system', '2020-03-27 21:12:29', '2020-03-27 21:12:29');
INSERT INTO `role_menu` VALUES ('95919EAC0C6A404FBCECF25424C015A7', 'AFD87A32ACF6406F876D369B1A8F6094', 44, 'system', 'system', '2020-03-30 23:00:16', '2020-03-30 23:00:16');
INSERT INTO `role_menu` VALUES ('A36A264EE0514ABFA4C817AC9D5D1F61', '2EE10E1823A5443BBCA6206A46636C2C', 11, 'system', 'system', '2020-03-27 21:21:01', '2020-03-27 21:21:01');
INSERT INTO `role_menu` VALUES ('ABAA8DFD83184B5D82451DAACAE4F6C7', 'F0510E3B1231491EBC6B29A20A415B2E', 3, 'system', 'system', '2020-03-30 23:00:38', '2020-03-30 23:00:38');
INSERT INTO `role_menu` VALUES ('AD22A23592914DCE963553909DB73FBA', 'F0510E3B1231491EBC6B29A20A415B2E', 31, 'system', 'system', '2020-03-30 23:00:38', '2020-03-30 23:00:38');
INSERT INTO `role_menu` VALUES ('B4F30392A6B345CA9CC081BEEABC9BD3', 'AFD87A32ACF6406F876D369B1A8F6094', 2, 'system', 'system', '2020-03-30 23:00:16', '2020-03-30 23:00:16');
INSERT INTO `role_menu` VALUES ('B8CAB126BE2A45499E87C37ECDB44BBD', '76E780369E634478AD1CDC1B71EC8322', 42, 'system', 'system', '2020-03-30 23:00:00', '2020-03-30 23:00:00');
INSERT INTO `role_menu` VALUES ('BC8A13D155D94CEDAA94C7734CAD4323', 'F0510E3B1231491EBC6B29A20A415B2E', 4, 'system', 'system', '2020-03-30 23:00:38', '2020-03-30 23:00:38');
INSERT INTO `role_menu` VALUES ('BCB10BEC1B2349468543E103979CAD65', '2EE10E1823A5443BBCA6206A46636C2C', 42, 'system', 'system', '2020-03-27 21:21:01', '2020-03-27 21:21:01');
INSERT INTO `role_menu` VALUES ('C5D342F594794CC19B75CAFBEFFADF4A', '4651057B670648928D8EDB5D1B6D5BBF', 11, 'system', 'system', '2020-03-27 21:12:29', '2020-03-27 21:12:29');
INSERT INTO `role_menu` VALUES ('CC3D3553EDE0423A8ED2EB5F1409E280', '2EE10E1823A5443BBCA6206A46636C2C', 50, 'system', 'system', '2020-03-27 21:21:01', '2020-03-27 21:21:01');
INSERT INTO `role_menu` VALUES ('CD003CC3A4C54204965A193B9A05029F', 'AFD87A32ACF6406F876D369B1A8F6094', 12, 'system', 'system', '2020-03-30 23:00:16', '2020-03-30 23:00:16');
INSERT INTO `role_menu` VALUES ('D28324D0CEEF44E8B5BFA03B32C7910D', 'AFD87A32ACF6406F876D369B1A8F6094', 50, 'system', 'system', '2020-03-30 23:00:16', '2020-03-30 23:00:16');
INSERT INTO `role_menu` VALUES ('D2B9CE4E5C2A44FBA0B03F6D69D5F70F', '4651057B670648928D8EDB5D1B6D5BBF', 49, 'system', 'system', '2020-03-27 21:12:29', '2020-03-27 21:12:29');
INSERT INTO `role_menu` VALUES ('D3239FC3B2E44A21B684080A9F9F4D3C', '76E780369E634478AD1CDC1B71EC8322', 44, 'system', 'system', '2020-03-30 23:00:00', '2020-03-30 23:00:00');
INSERT INTO `role_menu` VALUES ('D90AF2ED2B9A4F809E02607A50C6A64E', 'F0510E3B1231491EBC6B29A20A415B2E', 42, 'system', 'system', '2020-03-30 23:00:38', '2020-03-30 23:00:38');
INSERT INTO `role_menu` VALUES ('DBFCB41D9F984E3F9B035D75C1BC550E', 'F0510E3B1231491EBC6B29A20A415B2E', 44, 'system', 'system', '2020-03-30 23:00:38', '2020-03-30 23:00:38');
INSERT INTO `role_menu` VALUES ('DECE4327FD0A42789870BCEC86679C26', '4651057B670648928D8EDB5D1B6D5BBF', 2, 'system', 'system', '2020-03-27 21:12:29', '2020-03-27 21:12:29');
INSERT INTO `role_menu` VALUES ('E2067F456185444EBB7D8A9C6143082C', '4651057B670648928D8EDB5D1B6D5BBF', 50, 'system', 'system', '2020-03-27 21:12:29', '2020-03-27 21:12:29');
INSERT INTO `role_menu` VALUES ('E42F566BCFF24544827C0FE4DFACF3AC', 'AFD87A32ACF6406F876D369B1A8F6094', 22, 'system', 'system', '2020-03-30 23:00:16', '2020-03-30 23:00:16');
INSERT INTO `role_menu` VALUES ('EA28FFD66E154F86A206776DC308D2B5', '76E780369E634478AD1CDC1B71EC8322', 49, 'system', 'system', '2020-03-30 23:00:00', '2020-03-30 23:00:00');
INSERT INTO `role_menu` VALUES ('F4D7FE688482486EA7EBA52DF42AFD68', 'F0510E3B1231491EBC6B29A20A415B2E', 49, 'system', 'system', '2020-03-30 23:00:38', '2020-03-30 23:00:38');
INSERT INTO `role_menu` VALUES ('F7E2F510E27B41BFA96817E3A13B2FB9', '2EE10E1823A5443BBCA6206A46636C2C', 49, 'system', 'system', '2020-03-27 21:21:01', '2020-03-27 21:21:01');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permision_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (4, 'F0510E3B1231491EBC6B29A20A415B2E', 'EF258FE295C943739E76A5EBC7757600');
INSERT INTO `role_permission` VALUES (6, '2EE10E1823A5443BBCA6206A46636C2C', '4B8AF8A678F94F2583014E315D39E88A');
INSERT INTO `role_permission` VALUES (7, '2EE10E1823A5443BBCA6206A46636C2C', '2E5C662B37524343A5FE4FC754D7646C');
INSERT INTO `role_permission` VALUES (8, '2EE10E1823A5443BBCA6206A46636C2C', 'EF258FE295C943739E76A5EBC7757600');
INSERT INTO `role_permission` VALUES (9, '2EE10E1823A5443BBCA6206A46636C2C', '176DBC4E7F374F0DB7273FA337986BAA');
INSERT INTO `role_permission` VALUES (10, '2EE10E1823A5443BBCA6206A46636C2C', '0E7B774121A8411A86717AA8E1170EDB');
INSERT INTO `role_permission` VALUES (11, '76E780369E634478AD1CDC1B71EC8322', '4B8AF8A678F94F2583014E315D39E88A');
INSERT INTO `role_permission` VALUES (12, '76E780369E634478AD1CDC1B71EC8322', 'EF258FE295C943739E76A5EBC7757600');
INSERT INTO `role_permission` VALUES (13, 'AFD87A32ACF6406F876D369B1A8F6094', 'EF258FE295C943739E76A5EBC7757600');
INSERT INTO `role_permission` VALUES (14, 'AFD87A32ACF6406F876D369B1A8F6094', '176DBC4E7F374F0DB7273FA337986BAA');
INSERT INTO `role_permission` VALUES (15, '4651057B670648928D8EDB5D1B6D5BBF', '2E5C662B37524343A5FE4FC754D7646C');
INSERT INTO `role_permission` VALUES (16, '4651057B670648928D8EDB5D1B6D5BBF', 'EF258FE295C943739E76A5EBC7757600');

-- ----------------------------
-- Table structure for sys_user_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_log`;
CREATE TABLE `sys_user_log`  (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `module_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `operate` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  `class_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `method_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 143 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_log
-- ----------------------------
INSERT INTO `sys_user_log` VALUES (14, 'admin', '菜单模块', '添加', '2020-03-17 17:57:45', 'com.service.impl.MenuServiceImpl', 'insert', '[\"admin\",{\"createDate\":1584439064980,\"createUser\":\"admin\",\"id\":\"8E0185825E0045ECA585B97F00C84203\",\"menuName\":\"账号管理1\",\"menuPath\":\"/a/b\",\"parentId\":\"0\",\"showIndex\":1,\"status\":\"1\",\"updateDate\":1584439064980,\"updateUser\":\"admin\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (15, 'admin', '菜单管理', '修改菜单', '2020-03-17 18:00:51', 'com.service.impl.MenuServiceImpl', 'updateStatus', '[{\"menuId\":\"8E0185825E0045ECA585B97F00C84203\",\"status\":\"0\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (16, 'admin', '菜单管理', '修改菜单', '2020-03-17 18:00:52', 'com.service.impl.MenuServiceImpl', 'updateStatus', '[{\"menuId\":\"969233721A2E41F5B7312D545FDD9737\",\"status\":\"0\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (17, 'admin', '菜单模块', '删除', '2020-03-17 18:00:55', 'com.service.impl.MenuServiceImpl', 'deleteBatch', '[[\"8E0185825E0045ECA585B97F00C84203\"]]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (18, 'admin', '菜单模块', '删除', '2020-03-17 18:00:58', 'com.service.impl.MenuServiceImpl', 'deleteBatch', '[[\"969233721A2E41F5B7312D545FDD9737\"]]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (40, 'admin', '菜单模块', '添加', '2020-03-19 21:18:51', 'MenuServiceImpl', 'insert', 'null,{\"id\":\"42919DE03815442389B29D657C5BF78F\",\"menuName\":\"菜单管理\",\"menuPath\":\"./pages/admin/menu.html\",\"parentId\":\"1\",\"showIndex', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (41, 'admin', '菜单模块', '更新', '2020-03-19 21:19:04', 'MenuServiceImpl', 'updateSelective', '[null,{\"id\":\"B541DDAD6FA643A1A48F4AA79619E77A\",\"menuName\":\"权限\",\"menuPath\":\"/a/b\",\"parentId\":\"1\",\"showIndex\":3,\"status\":\"1\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (42, 'admin', '菜单模块', '更新', '2020-03-19 21:22:06', 'MenuServiceImpl', 'updateSelective', '[null,{\"id\":\"1\",\"menuName\":\"管理员权限\",\"menuPath\":\"#\",\"parentId\":\"0\",\"showIndex\":4,\"status\":\"1\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (43, 'admin', '菜单模块', '更新', '2020-03-19 21:29:00', 'MenuServiceImpl', 'updateSelective', 'null,{\"id\":\"4496225F846C42769719802F4962D6F3\",\"menuName\":\"资源管理\",\"menuPath\":\"./pages/acc/module.html\",\"parentId\":\"3\",\"showIndex', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (49, 'admin', '菜单模块', '更新', '2020-03-19 21:31:19', 'MenuServiceImpl', 'updateSelective', 'null,{\"id\":\"9C16CBC7E7804AD3A685652DA9E7967D\",\"menuName\":\"系统日志\",\"menuPath\":\"./pages/admin/syslog.html\",\"parentId\":\"4\",\"showInd', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (50, 'admin', '菜单模块', '添加', '2020-03-19 21:32:23', 'MenuServiceImpl', 'insert', 'null,{\"id\":\"8E4A8B8BE5CB4CB6A826BBC1ED67BA81\",\"menuName\":\"a\",\"menuPath\":\"/a/b\",\"parentId\":\"9C16CBC7E7804AD3A685652DA9E7967D\",\"', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (51, 'admin', '菜单模块', '更新', '2020-03-19 21:33:15', 'MenuServiceImpl', 'updateSelective', '[null,{\"id\":\"8E4A8B8BE5CB4CB6A826BBC1ED67BA81\",\"menuName\":\"a\",\"menuPath\":\"/a/b\",\"parentId\":\"4\",\"showIndex\":0,\"status\":\"1\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (52, 'admin', '菜单模块', '更新', '2020-03-19 21:35:22', 'MenuServiceImpl', 'updateSelective', 'null,{\"id\":\"8E4A8B8BE5CB4CB6A826BBC1ED67BA81\",\"menuName\":\"a\",\"menuPath\":\"/a/b\",\"parentId\":\"8E4A8B8BE5CB4CB6A826BBC1ED67BA81\",\"', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (53, 'admin', '菜单模块', '更新', '2020-03-19 21:40:57', 'MenuServiceImpl', 'updateSelective', '[null,{\"id\":\"8E4A8B8BE5CB4CB6A826BBC1ED67BA81\",\"menuName\":\"a\",\"menuPath\":\"/a/b\",\"parentId\":\"5\",\"showIndex\":0,\"status\":\"1\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (54, 'admin', '菜单模块', '更新', '2020-03-19 21:43:29', 'MenuServiceImpl', 'updateSelective', '[null,{\"id\":\"8E4A8B8BE5CB4CB6A826BBC1ED67BA81\",\"menuName\":\"a\",\"menuPath\":\"/a/b\",\"parentId\":\"3\",\"showIndex\":0,\"status\":\"1\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (58, 'admin', '菜单模块', '添加', '2020-03-20 10:45:07', 'MenuServiceImpl', 'insert', '[null,{\"id\":\"766006096C50446A8B17E1F9E39459F7\",\"menuName\":\"1\",\"menuPath\":\"/a/b\",\"parentId\":3,\"showIndex\":0,\"status\":\"1\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (59, 'admin', '菜单模块', '更新', '2020-03-20 10:45:15', 'MenuServiceImpl', 'updateSelective', '[null,{\"id\":\"45\",\"menuName\":\"12\",\"menuPath\":\"/a/b\",\"parentId\":4,\"showIndex\":0,\"status\":\"1\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (60, 'admin', '菜单模块', '更新', '2020-03-20 10:45:32', 'MenuServiceImpl', 'updateSelective', '[null,{\"id\":\"45\",\"menuName\":\"12\",\"menuPath\":\"/a/b\",\"parentId\":4,\"showIndex\":0,\"status\":\"1\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (65, 'admin', '菜单模块', '更新', '2020-03-20 10:54:06', 'MenuServiceImpl', 'updateSelective', '[null,{\"id\":\"46\",\"menuName\":\"as\",\"menuPath\":\"/a/b\",\"parentId\":3,\"showIndex\":0,\"status\":\"1\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (66, 'admin', '菜单模块', '更新', '2020-03-20 11:04:53', 'MenuServiceImpl', 'updateSelective', '[null,{\"id\":\"46\",\"menuName\":\"as\",\"menuPath\":\"/a/b\",\"parentId\":3,\"showIndex\":0,\"status\":\"1\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (67, 'admin', '菜单模块', '更新', '2020-03-20 11:04:59', 'MenuServiceImpl', 'updateSelective', '[null,{\"id\":\"46\",\"menuName\":\"as\",\"menuPath\":\"/a/b\",\"parentId\":3,\"showIndex\":0,\"status\":\"1\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (68, 'admin', '菜单模块', '更新', '2020-03-20 11:05:49', 'MenuServiceImpl', 'updateSelective', '[null,{\"id\":\"46\",\"menuName\":\"as\",\"menuPath\":\"/a/b\",\"parentId\":3,\"showIndex\":0,\"status\":\"1\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (69, 'admin', '菜单模块', '更新', '2020-03-20 11:06:14', 'MenuServiceImpl', 'updateSelective', '[null,{\"id\":\"46\",\"menuName\":\"as\",\"menuPath\":\"/a/b\",\"parentId\":3,\"showIndex\":0,\"status\":\"1\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (70, 'admin', '菜单模块', '更新', '2020-03-20 11:07:04', 'MenuServiceImpl', 'updateSelective', '[null,{\"id\":\"46\",\"menuName\":\"as\",\"menuPath\":\"/a/b\",\"parentId\":3,\"showIndex\":0,\"status\":\"1\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (71, 'admin', '菜单模块', '更新', '2020-03-20 11:07:10', 'MenuServiceImpl', 'updateSelective', '[null,{\"id\":\"46\",\"menuName\":\"as\",\"menuPath\":\"/a/b\",\"parentId\":0,\"showIndex\":0,\"status\":\"1\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (80, 'admin', '菜单模块', '更新', '2020-03-20 12:44:44', 'MenuServiceImpl', 'updateSelective', '[null,{\"id\":\"47\",\"menuName\":\"账号管理1\",\"menuPath\":\"/a/b\",\"parentId\":4,\"showIndex\":0,\"status\":\"1\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (115, 'admin', '菜单模块', '更新', '2020-03-20 16:54:49', 'MenuServiceImpl', 'updateSelective', 'null,{\"icon\":\"&#xe68e;\",\"id\":\"22\",\"menuName\":\"图标一览\",\"menuPath\":\"./pages/admin/school.html\",\"parentId\":4,\"showIndex\":1,\"status\"', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (116, 'admin', '菜单模块', '更新', '2020-03-20 16:54:58', 'MenuServiceImpl', 'updateSelective', 'null,{\"icon\":\"&#xe62a;\",\"id\":\"42\",\"menuName\":\"资源管理\",\"menuPath\":\"./pages/acc/module.html\",\"parentId\":4,\"showIndex\":1,\"status\":\"', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (117, 'admin', '菜单模块', '更新', '2020-03-20 16:55:06', 'MenuServiceImpl', 'updateSelective', 'null,{\"icon\":\"&#xe68e;\",\"id\":\"22\",\"menuName\":\"图标一览\",\"menuPath\":\"./pages/admin/school.html\",\"parentId\":4,\"showIndex\":3,\"status\"', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (118, 'admin', '菜单模块', '更新', '2020-03-22 10:05:00', 'MenuServiceImpl', 'updateSelective', '[null,{\"icon\":\"&#xe705;\",\"id\":\"2\",\"menuName\":\"测试的\",\"menuPath\":\"http://baidu.com\",\"parentId\":50,\"showIndex\":3,\"status\":\"1\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (119, 'admin', '角色模块', '更新', '2020-03-22 10:07:47', 'RoleServiceImpl', 'updateSelective', '[null,{\"id\":\"4651057B670648928D8EDB5D1B6D5BBF\",\"level\":5,\"mark\":\"只能编辑的角色\",\"roleName\":\"editor\",\"status\":\"1\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (120, 'admin', '角色模块', '更新', '2020-03-22 10:08:42', 'RoleServiceImpl', 'updateSelective', '[null,{\"id\":\"F0510E3B1231491EBC6B29A20A415B2E\",\"level\":20,\"mark\":\"只能浏览，不能增删改\",\"roleName\":\"onlysee\",\"status\":\"1\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (121, 'admin', '角色模块', '更新', '2020-03-22 10:08:58', 'RoleServiceImpl', 'updateSelective', '[null,{\"id\":\"4651057B670648928D8EDB5D1B6D5BBF\",\"level\":5,\"mark\":\"只能编辑的角色\",\"roleName\":\"onliyEidt\",\"status\":\"1\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (122, 'admin', '角色模块', '更新', '2020-03-22 10:10:07', 'RoleServiceImpl', 'updateSelective', '[null,{\"id\":\"AFD87A32ACF6406F876D369B1A8F6094\",\"level\":99,\"mark\":\"只能新增数据\",\"roleName\":\"onlyInsert\",\"status\":\"1\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (123, 'admin', '角色模块', '更新', '2020-03-22 10:10:22', 'RoleServiceImpl', 'updateSelective', '[null,{\"id\":\"AFD87A32ACF6406F876D369B1A8F6094\",\"level\":9,\"mark\":\"只能新增数据\",\"roleName\":\"onlyInsert\",\"status\":\"1\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (124, 'admin', '角色模块', '更新', '2020-03-22 10:10:53', 'RoleServiceImpl', 'updateSelective', '[null,{\"id\":\"76E780369E634478AD1CDC1B71EC8322\",\"level\":6,\"mark\":\"只能删除\",\"roleName\":\"onlyDelete\",\"status\":\"1\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (125, NULL, '权限模块', '更新', '2020-03-22 10:11:43', 'PermissionServiceImpl', 'updateSelective', '\"admin\",{\"id\":\"2E5C662B37524343A5FE4FC754D7646C\",\"mark\":\"edit\",\"permissionName\":\"edit\",\"roleId\":\"4651057B670648928D8EDB5D1B6D5', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (126, NULL, '权限模块', '更新', '2020-03-22 10:12:01', 'PermissionServiceImpl', 'updateSelective', '\"admin\",{\"id\":\"EF258FE295C943739E76A5EBC7757600\",\"mark\":\"浏览者\",\"permissionName\":\"see\",\"roleId\":\"F0510E3B1231491EBC6B29A20A415B2', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (127, NULL, '权限模块', '更新', '2020-03-22 10:12:29', 'PermissionServiceImpl', 'updateSelective', '\"admin\",{\"id\":\"176DBC4E7F374F0DB7273FA337986BAA\",\"mark\":\"添加数据\",\"permissionName\":\"add\",\"roleId\":\"AFD87A32ACF6406F876D369B1A8F60', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (128, NULL, '权限模块', '添加', '2020-03-22 10:12:53', 'PermissionServiceImpl', 'insert', '\"admin\",{\"createDate\":1584843172547,\"createUser\":\"admin\",\"id\":\"4B8AF8A678F94F2583014E315D39E88A\",\"mark\":\"删除数据\",\"permissionName', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (129, NULL, '权限模块', '添加', '2020-03-22 12:44:12', 'PermissionServiceImpl', 'insert', '\"admin\",{\"createDate\":1584852251613,\"createUser\":\"admin\",\"id\":\"3C92EEDC56344FC586A114AAD230AA84\",\"mark\":\"a\",\"permissionName\":\"', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (130, NULL, '权限模块', '删除', '2020-03-22 12:44:15', 'PermissionServiceImpl', 'deleteBatch', '[[\"3C92EEDC56344FC586A114AAD230AA84\"]]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (131, NULL, '权限模块', '更新', '2020-03-22 12:44:35', 'PermissionServiceImpl', 'updateSelective', '\"admin\",{\"id\":\"4B8AF8A678F94F2583014E315D39E88A\",\"mark\":\"删除数据\",\"permissionName\":\"delete1\",\"updateDate\":1584852274929,\"updateUs', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (132, NULL, '权限模块', '更新', '2020-03-22 12:44:39', 'PermissionServiceImpl', 'updateSelective', '\"admin\",{\"id\":\"4B8AF8A678F94F2583014E315D39E88A\",\"mark\":\"删除数据1\",\"permissionName\":\"delete\",\"updateDate\":1584852279359,\"updateUs', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (133, NULL, '权限模块', '更新', '2020-03-22 12:44:44', 'PermissionServiceImpl', 'updateSelective', '\"admin\",{\"id\":\"4B8AF8A678F94F2583014E315D39E88A\",\"mark\":\"删除数据\",\"permissionName\":\"delete\",\"updateDate\":1584852284128,\"updateUse', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (134, 'admin', '权限模块', '更新', '2020-03-22 12:47:56', 'PermissionServiceImpl', 'updateSelective', '[null,{\"id\":\"2E5C662B37524343A5FE4FC754D7646C\",\"mark\":\"编辑\",\"permissionName\":\"edit\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (135, 'canEdit', '账号模块', '更新', '2020-03-27 20:13:43', 'UserServiceImpl', 'updateSelective', '[null,{\"id\":\"1\",\"nickname\":\"李四1\",\"phone\":\"1332121113\",\"roles\":[],\"sex\":\"男\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (136, 'canEdit', '账号模块', '更新', '2020-03-27 20:13:54', 'UserServiceImpl', 'updateSelective', '[null,{\"id\":\"1\",\"nickname\":\"李四\",\"phone\":\"1332121113\",\"roles\":[],\"sex\":\"男\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (137, 'canEdit', '账号模块', '更新', '2020-03-27 20:16:44', 'UserServiceImpl', 'updateSelective', '[null,{\"id\":\"1\",\"nickname\":\"李四1\",\"phone\":\"1332121113\",\"roles\":[],\"sex\":\"男\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (138, 'canEdit', '账号模块', '更新', '2020-03-27 20:17:17', 'UserServiceImpl', 'updateSelective', '[null,{\"id\":\"D00465EE651748D1BBC50C9A5D0116E1\",\"nickname\":\"能编辑的老王\",\"phone\":\"1212316788\",\"roles\":[],\"sex\":\"男\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (139, 'canEdit', '账号模块', '更新', '2020-03-27 20:17:35', 'UserServiceImpl', 'updateSelective', '[null,{\"id\":\"D2C294AA630A4AF8A8E66D1C4A0A5D91\",\"nickname\":\"只能看的老王\",\"phone\":\"1531155789\",\"roles\":[],\"sex\":\"男\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (140, 'canEdit', '账号模块', '更新', '2020-03-27 20:18:02', 'UserServiceImpl', 'updateSelective', '[null,{\"id\":\"1\",\"nickname\":\"我能添加\",\"phone\":\"1332121113\",\"roles\":[],\"sex\":\"男\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (141, 'canInsert', '资源模块', '添加', '2020-03-27 20:38:39', 'ModuleServiceImpl', 'insert', '[null,{\"id\":\"BF872AD4B89D4BA98E9B95775D16398F\",\"module\":\"菜单模块\",\"path\":\"/menu/\"}]', '127.0.0.1');
INSERT INTO `sys_user_log` VALUES (142, 'canInsert', '资源模块', '添加', '2020-03-31 08:29:40', 'ModuleServiceImpl', 'insert', '[null,{\"id\":\"4CFAD1574B9D4EF09E85A518F277173F\",\"module\":\"菜单模块\",\"path\":\"/menu/\"}]', '127.0.0.1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1',
  `acc` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(3) NULL DEFAULT NULL,
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `idcard` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introduce` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '显示的昵称',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '状态1正常 0冻结',
  `school_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `acc_type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0游客账号 1自己注册 2校级账号分发 3管理员分发',
  `update_date` datetime(0) NULL DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('6FBDEF126046426983ABCA68F1F33D91', '3', 11, '1@', NULL, '阿达', '霭', '4e07408562bedb8b60ce05c1decfe3ad16b72230967de01f640b7e4729b49fce', '1334232321', '男', NULL, '1', 'school2', NULL, '2019-10-22 11:41:22', 'system', 'system', '2019-10-22 11:41:22');
INSERT INTO `user` VALUES ('7ECB18CF0B1A41708EAD0BEDEA534583', '22', 22, '1@', NULL, '阿达', '啦啦', '785f3ec7eb32f30b90cd0fcf3657d388b5ff4297f2f9716ff66e9b69c05ddd09', '123145789', '男', NULL, '1', 'school2', NULL, '2019-10-22 17:20:44', 'system', 'system', '2019-10-22 17:20:44');
INSERT INTO `user` VALUES ('D2C294AA630A4AF8A8E66D1C4A0A5D91', 'canSee', 19, '1@', NULL, '阿达', '只能看的老王', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '1531155789', '男', NULL, '1', 'school2', NULL, '2020-03-19 13:29:24', 'admin', 'system', '2019-10-23 09:29:44');
INSERT INTO `user` VALUES ('1', 'canInsert', 23, '11116@qq.com', NULL, '学校官方号', '我能添加', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '1332121113', '男', 'E58F2045852D422C8B2A36EB95237579.jpg', '1', 'school1', NULL, '2020-03-19 13:28:08', 'admin', 'system', '2020-03-09 16:17:22');
INSERT INTO `user` VALUES ('111', 'canDelete', NULL, '1@11.com', '1', '2', '3', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '1231234114', '男', NULL, '1', 'school1', NULL, '2020-03-19 13:29:57', 'admin', NULL, NULL);
INSERT INTO `user` VALUES ('D00465EE651748D1BBC50C9A5D0116E1', 'canEdit', 18, '123@qq.com', NULL, '', '能编辑的老王', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '1212316788', '男', '8EA95CA0FEC8421D852425D901B113D8.jpg', '1', 'school1', NULL, '2020-03-19 13:29:02', 'admin', 'system', '2019-12-30 15:00:00');
INSERT INTO `user` VALUES ('visitor', 'victor', 18, NULL, NULL, '游客', '游客', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '1435756788', '女', NULL, '1', NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('admin', 'admin', NULL, NULL, NULL, '超级管理员', '超级管理员', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '1334553677', '女', NULL, '1', NULL, NULL, '2020-03-19 13:29:33', 'admin', NULL, NULL);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('14F24815FC8F4C7C9EE13B8052E54456', 'F0510E3B1231491EBC6B29A20A415B2E', '7ECB18CF0B1A41708EAD0BEDEA534583', 'admin', 'admin', '2020-03-19 12:51:13', '2020-03-19 12:51:13');
INSERT INTO `user_role` VALUES ('35732A2C5BD74E878ECAB0F5D3CE6EC5', 'AFD87A32ACF6406F876D369B1A8F6094', '1', NULL, NULL, NULL, NULL);
INSERT INTO `user_role` VALUES ('3EA6B9A45DC2404A9EA6E507A11B6F0C', 'F0510E3B1231491EBC6B29A20A415B2E', '6FBDEF126046426983ABCA68F1F33D91', 'admin', 'admin', '2020-03-19 12:51:09', '2020-03-19 12:51:09');
INSERT INTO `user_role` VALUES ('7877DF24382D4AF989E1C71274BFF358', 'F0510E3B1231491EBC6B29A20A415B2E', 'D2C294AA630A4AF8A8E66D1C4A0A5D91', NULL, NULL, NULL, NULL);
INSERT INTO `user_role` VALUES ('86BBE3B2807E4BF4BB00269ED94122AC', '2EE10E1823A5443BBCA6206A46636C2C', 'admin', 'admin', 'admin', '2020-03-19 17:44:20', '2020-03-19 17:44:20');
INSERT INTO `user_role` VALUES ('8DADD4302FBF4B0BA36FEF28AB825523', '4651057B670648928D8EDB5D1B6D5BBF', 'D00465EE651748D1BBC50C9A5D0116E1', NULL, NULL, NULL, NULL);
INSERT INTO `user_role` VALUES ('DFD63E24848E4CECA981D1615A9D8360', '76E780369E634478AD1CDC1B71EC8322', '111', NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
