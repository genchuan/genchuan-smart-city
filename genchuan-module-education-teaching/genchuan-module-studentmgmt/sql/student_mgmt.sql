/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80045 (8.0.45)
 Source Host           : localhost:3306
 Source Schema         : student_mgmt

 Target Server Type    : MySQL
 Target Server Version : 80045 (8.0.45)
 File Encoding         : 65001

 Date: 15/04/2026 22:16:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for access_apply
-- ----------------------------
DROP TABLE IF EXISTS `access_apply`;
CREATE TABLE `access_apply`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `student_id` bigint NOT NULL COMMENT '学生 ID',
  `apply_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '申请类型：应急出入/其他',
  `apply_reason` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '申请原因',
  `apply_time` datetime NOT NULL COMMENT '申请时间',
  `audit_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：待审核/已通过',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_student_id`(`student_id` ASC) USING BTREE,
  INDEX `idx_apply_type`(`apply_type` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '出入申请表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of access_apply
-- ----------------------------
INSERT INTO `access_apply` VALUES (1, 1, 'emergency', '突发疾病需要外出就医', '2025-10-08 14:30:00', '辅导员张老师', '2025-10-08 14:45:00', 'approved', '急诊就医，已批准', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-08 14:30:00', '2026-04-15 21:20:17');
INSERT INTO `access_apply` VALUES (2, 2, 'emergency', '家庭紧急情况需要回家', '2025-10-09 10:20:00', '辅导员李老师', '2025-10-09 10:35:00', 'approved', '家庭急事，已批准', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-09 10:20:00', '2026-04-15 21:20:17');
INSERT INTO `access_apply` VALUES (3, 3, 'other', '参加校外实习面试', '2025-10-10 09:00:00', '辅导员王老师', '2025-10-10 09:20:00', 'approved', '实习面试，已批准', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-10 09:00:00', '2026-04-15 21:20:17');
INSERT INTO `access_apply` VALUES (4, 4, 'emergency', '身体不适需要去医院检查', '2025-10-11 16:00:00', '辅导员赵老师', '2025-10-11 16:15:00', 'approved', '就医检查，已批准', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-11 16:00:00', '2026-04-15 21:20:17');
INSERT INTO `access_apply` VALUES (5, 5, 'other', '参加校外培训课程', '2025-10-12 08:30:00', '辅导员刘老师', '2025-10-12 08:50:00', 'approved', '培训学习，已批准', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-12 08:30:00', '2026-04-15 21:20:17');
INSERT INTO `access_apply` VALUES (6, 6, 'emergency', '家人生病需要探望', '2025-10-13 11:00:00', '辅导员陈老师', NULL, 'pending', '等待审核中', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-13 11:00:00', '2026-04-15 21:20:17');
INSERT INTO `access_apply` VALUES (7, 7, 'other', '参加学术竞赛活动', '2025-10-14 07:30:00', '辅导员杨老师', '2025-10-14 07:45:00', 'approved', '竞赛活动，已批准', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-14 07:30:00', '2026-04-15 21:20:17');
INSERT INTO `access_apply` VALUES (8, 8, 'emergency', '紧急事务需要处理', '2025-10-15 15:20:00', '辅导员周老师', NULL, 'pending', '等待审核中', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-15 15:20:00', '2026-04-15 21:20:17');
INSERT INTO `access_apply` VALUES (9, 1, 'other', '购买生活用品', '2025-10-16 13:00:00', '辅导员吴老师', '2025-10-16 13:10:00', 'approved', '日常采购，已批准', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-16 13:00:00', '2026-04-15 21:20:17');
INSERT INTO `access_apply` VALUES (10, 2, 'emergency', '感冒发烧需要就医', '2025-10-17 10:30:00', '辅导员郑老师', '2025-10-17 10:45:00', 'approved', '就医治疗，已批准', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-17 10:30:00', '2026-04-15 21:20:17');

-- ----------------------------
-- Table structure for aid_work
-- ----------------------------
DROP TABLE IF EXISTS `aid_work`;
CREATE TABLE `aid_work`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `student_id` bigint NOT NULL COMMENT '学生 ID',
  `aid_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资助类型：奖学金/助学金/助学贷款/勤工俭学',
  `apply_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '申请金额',
  `apply_time` datetime NOT NULL COMMENT '申报时间',
  `audit_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
  `process_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '流程状态：跟进中/已完成',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：待审核/已通过/已完成',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_student_id`(`student_id` ASC) USING BTREE,
  INDEX `idx_aid_type`(`aid_type` ASC) USING BTREE,
  INDEX `idx_process_status`(`process_status` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '奖助勤贷表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aid_work
-- ----------------------------
INSERT INTO `aid_work` VALUES (1, 1, '1', 5000.00, '2024-10-01 10:00:00', '张老师', '2024-10-05 14:00:00', '2', '2', '国家奖学金', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-10-01 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `aid_work` VALUES (2, 5, '2', 3000.00, '2024-09-05 10:00:00', '李老师', '2024-09-10 14:00:00', '2', '2', '国家助学金', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-05 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `aid_work` VALUES (3, 10, '3', 8000.00, '2024-09-08 10:00:00', '王老师', '2024-09-12 14:00:00', '1', '1', '生源地贷款', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-08 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `aid_work` VALUES (4, 3, '4', 1500.00, '2024-10-10 10:00:00', '赵老师', '2024-10-15 14:00:00', '2', '2', '校内岗位', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-10-10 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `aid_work` VALUES (5, 6, '1', 3000.00, '2024-10-20 10:00:00', '孙老师', '2024-10-25 14:00:00', '1', '0', '校级奖学金', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-10-20 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `aid_work` VALUES (6, 2, '2', 2500.00, '2024-09-10 10:00:00', '周老师', '2024-09-15 14:00:00', '2', '2', '社会捐助', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-10 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `aid_work` VALUES (7, 7, '4', 1200.00, '2024-11-01 10:00:00', '吴老师', '2024-11-05 14:00:00', '2', '2', '机房管理员', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-01 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `aid_work` VALUES (8, 9, '1', 6000.00, '2024-10-15 10:00:00', '郑老师', '2024-10-20 14:00:00', '2', '2', '企业奖学金', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-10-15 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `aid_work` VALUES (9, 4, '3', 6000.00, '2024-09-12 10:00:00', '陈老师', '2024-09-18 14:00:00', '1', '1', '校园地贷款', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-12 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `aid_work` VALUES (10, 8, '2', 2000.00, '2024-11-10 10:00:00', '林老师', NULL, '1', '0', '临时困难', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-10 09:00:00', '2026-04-15 20:27:02');

-- ----------------------------
-- Table structure for assess_mgmt
-- ----------------------------
DROP TABLE IF EXISTS `assess_mgmt`;
CREATE TABLE `assess_mgmt`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `class_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '班级',
  `assess_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '考评类型：1:教室卫生/2:早操/3:文明班级/4:黑板报',
  `cycle` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '统计周期：1:周/2:月/3:学期',
  `score` decimal(5, 2) NOT NULL COMMENT '考评得分',
  `rank_no` int NULL DEFAULT NULL COMMENT '排名',
  `assess_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '考评人',
  `publish_time` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：1未发布/2:已发布',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_class_name`(`class_name` ASC) USING BTREE,
  INDEX `idx_assess_type`(`assess_type` ASC) USING BTREE,
  INDEX `idx_cycle`(`cycle` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '考评管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of assess_mgmt
-- ----------------------------
INSERT INTO `assess_mgmt` VALUES (1, '2024级软件工程1班', 'class_clean', 'week', 90.60, 0, '', '1970-01-01 08:00:00', 'un_publish', '你猜6', '', '', 'admin', '1', b'0', 1, '2025-10-07 10:00:00', '2026-04-14 11:28:32');
INSERT INTO `assess_mgmt` VALUES (2, '2024级软件工程2班', 'class_clean', 'week', 92.30, 2, '学生会卫生部', '2025-10-07 18:00:00', 'published', '整体干净，窗台需加强', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-07 10:00:00', '2026-04-15 20:27:02');
INSERT INTO `assess_mgmt` VALUES (3, '2024级计算机科学1班', 'morning_exercise', 'week', 88.80, 3, '体育部', '2025-10-07 18:00:00', 'published', '出勤率高，动作规范', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-07 10:00:00', '2026-04-15 20:27:02');
INSERT INTO `assess_mgmt` VALUES (4, '2024级网络工程1班', 'civilized_class', 'month', 90.60, 2, '德育处', '2025-10-31 18:00:00', 'published', '纪律良好，学风浓厚', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-31 10:00:00', '2026-04-15 20:27:02');
INSERT INTO `assess_mgmt` VALUES (5, '2024级信息安全1班', 'blackboard', 'month', 87.40, 4, '宣传部', '2025-10-31 18:00:00', 'published', '主题鲜明，内容丰富', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-31 10:00:00', '2026-04-15 20:27:02');
INSERT INTO `assess_mgmt` VALUES (6, '2024级软件工程1班', 'morning_exercise', 'week', 93.20, 1, '体育部', '2025-11-03 18:00:00', 'un_publish', '本周考评待发布', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-11-03 10:00:00', '2026-04-15 20:27:02');
INSERT INTO `assess_mgmt` VALUES (7, '2024级计算机科学1班', 'class_clean', 'week', 91.50, 2, '学生会卫生部', '2025-11-03 18:00:00', 'un_publish', '本周考评待发布', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-11-03 10:00:00', '2026-04-15 20:27:02');
INSERT INTO `assess_mgmt` VALUES (8, '2024级软件工程1班', 'civilized_class', 'semester', 94.80, 1, '德育处', '2026-01-15 18:00:00', 'published', '学期综合表现优秀', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-01-15 10:00:00', '2026-04-15 20:27:02');
INSERT INTO `assess_mgmt` VALUES (9, '2024级网络工程1班', 'blackboard', 'month', 89.70, 3, '宣传部', '2025-11-30 18:00:00', 'published', '设计新颖，色彩搭配好', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-11-30 10:00:00', '2026-04-15 20:27:02');
INSERT INTO `assess_mgmt` VALUES (10, '2024级信息安全1班', 'morning_exercise', 'semester', 86.50, 5, '体育部', '2026-01-15 18:00:00', 'published', '需要加强早操管理', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-01-15 10:00:00', '2026-04-15 20:27:02');

-- ----------------------------
-- Table structure for bed_mgmt
-- ----------------------------
DROP TABLE IF EXISTS `bed_mgmt`;
CREATE TABLE `bed_mgmt`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `building` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '楼栋',
  `floor` int NOT NULL COMMENT '楼层',
  `room_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '房间号',
  `bed_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '床位号',
  `student_id` bigint NULL DEFAULT NULL COMMENT '学生 ID',
  `assign_time` datetime NULL DEFAULT NULL COMMENT '分配时间',
  `adjust_time` datetime NULL DEFAULT NULL COMMENT '调整时间',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：未分配/已分配',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_building`(`building` ASC) USING BTREE,
  INDEX `idx_floor`(`floor` ASC) USING BTREE,
  INDEX `idx_room_num`(`room_num` ASC) USING BTREE,
  INDEX `idx_student_id`(`student_id` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '床位管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bed_mgmt
-- ----------------------------
INSERT INTO `bed_mgmt` VALUES (1, 'A栋', 1, '101', '1号床', 1, '2025-09-01 10:00:00', NULL, '已分配', '一楼第一间', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 10:00:00', '2025-09-01 10:00:00');
INSERT INTO `bed_mgmt` VALUES (2, 'A栋', 1, '101', '2号床', 2, '2025-09-01 10:00:00', NULL, '已分配', '一楼第一间', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 10:00:00', '2025-09-01 10:00:00');
INSERT INTO `bed_mgmt` VALUES (3, 'A栋', 1, '101', '3号床', 3, '2025-09-01 10:00:00', NULL, '已分配', '一楼第一间', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 10:00:00', '2025-09-01 10:00:00');
INSERT INTO `bed_mgmt` VALUES (4, 'A栋', 1, '101', '4号床', 4, '2025-09-01 10:00:00', NULL, '已分配', '一楼第一间', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 10:00:00', '2025-09-01 10:00:00');
INSERT INTO `bed_mgmt` VALUES (5, 'A栋', 2, '201', '1号床', 5, '2025-09-01 10:00:00', NULL, '已分配', '二楼第一间', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 10:00:00', '2025-09-01 10:00:00');
INSERT INTO `bed_mgmt` VALUES (6, 'A栋', 2, '201', '2号床', 6, '2025-09-01 10:00:00', NULL, '已分配', '二楼第一间', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 10:00:00', '2025-09-01 10:00:00');
INSERT INTO `bed_mgmt` VALUES (7, 'A栋', 2, '201', '3号床', 7, '2025-09-01 10:00:00', NULL, '已分配', '二楼第一间', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 10:00:00', '2025-09-01 10:00:00');
INSERT INTO `bed_mgmt` VALUES (8, 'A栋', 2, '201', '4号床', 8, '2025-09-01 10:00:00', NULL, '已分配', '二楼第一间', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 10:00:00', '2025-09-01 10:00:00');
INSERT INTO `bed_mgmt` VALUES (9, 'A栋', 3, '301', '1号床', NULL, NULL, NULL, '未分配', '三楼第一间空床位', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 10:00:00', '2025-08-25 10:00:00');
INSERT INTO `bed_mgmt` VALUES (10, 'A栋', 3, '301', '2号床', NULL, NULL, NULL, '未分配', '三楼第一间空床位', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 10:00:00', '2025-08-25 10:00:00');

-- ----------------------------
-- Table structure for behavior_mgmt
-- ----------------------------
DROP TABLE IF EXISTS `behavior_mgmt`;
CREATE TABLE `behavior_mgmt`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `student_id` bigint NOT NULL COMMENT '学生 ID',
  `leave_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '请假类型：事假/病假/其他',
  `start_time` datetime NOT NULL COMMENT '请假开始时间',
  `end_time` datetime NOT NULL COMMENT '请假结束时间',
  `leave_reason` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请假原因',
  `audit_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审批级别：班主任/辅导员',
  `audit_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审批人',
  `audit_time` datetime NULL DEFAULT NULL COMMENT '审批时间',
  `attendance_sync` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '考勤同步状态：未同步/已同步',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：待审批/已通过/已驳回',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_student_id`(`student_id` ASC) USING BTREE,
  INDEX `idx_leave_type`(`leave_type` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '行为管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of behavior_mgmt
-- ----------------------------
INSERT INTO `behavior_mgmt` VALUES (1, 1, '2', '2024-10-15 08:00:00', '2024-10-17 18:00:00', '感冒发烧', '1', '芋道源码', '2026-04-15 14:58:26', '1', '1', '不要了', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-10-14 15:00:00', '2026-04-15 20:27:02');
INSERT INTO `behavior_mgmt` VALUES (2, 2, '1', '2024-11-20 08:00:00', '2024-11-20 18:00:00', '家庭事务', '1', '李老师', '2024-11-19 16:00:00', '1', '1', NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-19 15:00:00', '2026-04-15 20:27:02');
INSERT INTO `behavior_mgmt` VALUES (3, 3, '2', '2024-12-01 08:00:00', '2024-12-03 18:00:00', '肠胃炎', '2', '王老师', '2024-11-30 16:00:00', '1', '1', '需休息', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-30 15:00:00', '2026-04-15 20:27:02');
INSERT INTO `behavior_mgmt` VALUES (4, 5, '1', '2024-09-25 08:00:00', '2024-09-26 18:00:00', '参加婚礼', '1', '赵老师', '2024-09-24 16:00:00', '1', '1', NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-24 15:00:00', '2026-04-15 20:27:02');
INSERT INTO `behavior_mgmt` VALUES (5, 6, '2', '2024-10-10 08:00:00', '2024-10-10 18:00:00', '头痛', '1', '孙老师', '2024-10-09 16:00:00', '1', '1', NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2024-10-09 15:00:00', '2026-04-15 20:27:02');
INSERT INTO `behavior_mgmt` VALUES (6, 7, '3', '2024-11-15 08:00:00', '2024-11-17 18:00:00', '参加比赛', '2', '周老师', '2024-11-14 16:00:00', '1', '1', '省级比赛', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-14 15:00:00', '2026-04-15 20:27:02');
INSERT INTO `behavior_mgmt` VALUES (7, 9, '1', '2024-12-10 08:00:00', '2024-12-10 18:00:00', '办理证件', '1', '吴老师', '2024-12-09 16:00:00', '0', '0', NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2024-12-09 15:00:00', '2026-04-15 20:27:02');
INSERT INTO `behavior_mgmt` VALUES (8, 10, '2', '2024-11-05 08:00:00', '2024-11-06 18:00:00', '发烧', '1', '郑老师', '2024-11-04 16:00:00', '1', '1', NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-04 15:00:00', '2026-04-15 20:27:02');
INSERT INTO `behavior_mgmt` VALUES (9, 4, '1', '2024-10-20 08:00:00', '2024-10-22 18:00:00', '回家处理事务', '2', '陈老师', '2024-10-19 16:00:00', '1', '2', '理由不充分', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-10-19 15:00:00', '2026-04-15 20:27:02');
INSERT INTO `behavior_mgmt` VALUES (10, 8, '2', '2024-12-15 08:00:00', '2024-12-16 18:00:00', '身体不适', '1', '林老师', '2024-12-14 16:00:00', '1', '1', NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2024-12-14 15:00:00', '2026-04-15 20:27:02');

-- ----------------------------
-- Table structure for check_in
-- ----------------------------
DROP TABLE IF EXISTS `check_in`;
CREATE TABLE `check_in`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `student_id` bigint NOT NULL COMMENT '学生 ID',
  `exam_score` decimal(5, 1) NULL DEFAULT NULL COMMENT '中考成绩',
  `补充信息` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '补充信息',
  `confirm_time` datetime NULL DEFAULT NULL COMMENT '报到确认时间',
  `audit_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
  `account_create_time` datetime NULL DEFAULT NULL COMMENT '账号创建时间',
  `account_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号状态：未创建/已创建',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：待确认/待审核/已报到',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_student_id`(`student_id` ASC) USING BTREE,
  INDEX `idx_account_status`(`account_status` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '报到管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of check_in
-- ----------------------------
INSERT INTO `check_in` VALUES (1, 1, 580.5, '家庭经济困难，申请绿色通道', '2025-09-01 09:30:00', '招生办张老师', '2025-09-01 10:00:00', '2025-09-01 10:30:00', '已创建', '已报到', '已完成所有报到手续', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-01 09:00:00', '2025-09-01 10:30:00');
INSERT INTO `check_in` VALUES (2, 2, 620.0, '体育特长生，已核实证书', '2025-09-01 10:00:00', '招生办李老师', '2025-09-01 10:30:00', '2025-09-01 11:00:00', '已创建', '已报到', '特长生报到完成', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-01 09:30:00', '2025-09-01 11:00:00');
INSERT INTO `check_in` VALUES (3, 3, 595.0, '外地学生，已安排宿舍', '2025-09-01 11:00:00', '招生办王老师', '2025-09-01 11:30:00', '2025-09-01 12:00:00', '已创建', '已报到', '外地学生报到', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-01 10:30:00', '2025-09-01 12:00:00');
INSERT INTO `check_in` VALUES (4, 4, 610.5, '本地学生，走读申请已通过', '2025-09-01 14:00:00', '招生办赵老师', '2025-09-01 14:30:00', '2025-09-01 15:00:00', '已创建', '已报到', '走读生报到', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-01 13:30:00', '2025-09-01 15:00:00');
INSERT INTO `check_in` VALUES (5, 5, 575.0, '需要助学贷款，已办理', '2025-09-02 09:00:00', '招生办孙老师', '2025-09-02 09:30:00', '2025-09-02 10:00:00', '已创建', '已报到', '贷款学生报到', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-02 08:30:00', '2025-09-02 10:00:00');
INSERT INTO `check_in` VALUES (6, 6, 605.0, '团员关系已转入', '2025-09-02 10:30:00', '招生办周老师', '2025-09-02 11:00:00', NULL, '未创建', '待审核', '等待账号创建', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-02 10:00:00', '2025-09-02 11:00:00');
INSERT INTO `check_in` VALUES (7, 7, 590.0, '档案材料齐全', '2025-09-02 14:00:00', '招生办吴老师', '2025-09-02 14:30:00', '2025-09-02 15:00:00', '已创建', '已报到', '正常报到', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-02 13:30:00', '2025-09-02 15:00:00');
INSERT INTO `check_in` VALUES (8, 8, 615.5, '优秀学生干部，有相关证明', '2025-09-03 09:00:00', '招生办郑老师', '2025-09-03 09:30:00', '2025-09-03 10:00:00', '已创建', '已报到', '优秀学生报到', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-03 08:30:00', '2025-09-03 10:00:00');
INSERT INTO `check_in` VALUES (9, 9, 585.0, '少数民族学生', '2025-09-03 11:00:00', '招生办陈老师', NULL, NULL, '未创建', '待确认', '等待现场确认', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-03 10:30:00', '2025-09-03 10:30:00');
INSERT INTO `check_in` VALUES (10, 10, 600.0, '复读生，去年成绩优异', '2025-09-03 15:00:00', '招生办林老师', '2025-09-03 15:30:00', '2025-09-03 16:00:00', '已创建', '已报到', '复读生报到', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-03 14:30:00', '2025-09-03 16:00:00');

-- ----------------------------
-- Table structure for class_assign
-- ----------------------------
DROP TABLE IF EXISTS `class_assign`;
CREATE TABLE `class_assign`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `rule_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分班规则',
  `student_num` int NULL DEFAULT NULL COMMENT '分班学生数',
  `assign_time` datetime NULL DEFAULT NULL COMMENT '分班时间',
  `confirm_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '确认人',
  `confirm_time` datetime NULL DEFAULT NULL COMMENT '确认时间',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：未分班/已分班',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '分班管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class_assign
-- ----------------------------
INSERT INTO `class_assign` VALUES (1, '按中考成绩均衡分班，每班50人，男女比例均衡', 50, '2025-08-20 10:00:00', '教务处张主任', '2025-08-20 15:00:00', 'assigned', '2025级软件工程分班', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-20 09:00:00', '2026-04-15 21:23:30');
INSERT INTO `class_assign` VALUES (2, '按中考成绩均衡分班，每班50人，男女比例均衡', 50, '2025-08-20 10:00:00', '教务处张主任', '2025-08-20 15:00:00', 'assigned', '2025级计算机科学分班', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-20 09:00:00', '2026-04-15 21:23:30');
INSERT INTO `class_assign` VALUES (3, '按中考成绩均衡分班，每班45人，男女比例均衡', 45, '2025-08-20 10:00:00', '教务处张主任', '2025-08-20 15:00:00', 'assigned', '2025级网络工程分班', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-20 09:00:00', '2026-04-15 21:23:30');
INSERT INTO `class_assign` VALUES (4, '按中考成绩均衡分班，每班45人，男女比例均衡', 45, '2025-08-20 10:00:00', '教务处张主任', '2025-08-20 15:00:00', 'assigned', '2025级信息安全分班', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-20 09:00:00', '2026-04-15 21:23:30');
INSERT INTO `class_assign` VALUES (5, '特长生单独编班，注重专业技能培养', 30, '2025-08-21 09:00:00', '教务处李副主任', '2025-08-21 14:00:00', 'assigned', '特长生班级', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-21 08:00:00', '2026-04-15 21:23:30');
INSERT INTO `class_assign` VALUES (6, '按生源地分布均衡分班，促进文化交流', 48, '2025-08-21 09:00:00', '教务处李副主任', '2025-08-21 14:00:00', 'assigned', '混合编班方案', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-21 08:00:00', '2026-04-15 21:23:30');
INSERT INTO `class_assign` VALUES (7, '按志愿优先原则分班，尊重学生选择', 50, NULL, NULL, NULL, 'unassigned', '2026级预备分班方案', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-03-15 10:00:00', '2026-04-15 21:23:30');
INSERT INTO `class_assign` VALUES (8, '综合素质评价分班，德智体美劳全面发展', 45, NULL, NULL, NULL, 'unassigned', '待制定详细规则', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-03-20 10:00:00', '2026-04-15 21:23:30');
INSERT INTO `class_assign` VALUES (9, '国际合作班单独编班，强化外语教学', 35, '2025-08-22 10:00:00', '国际交流处王主任', '2025-08-22 16:00:00', 'assigned', '中英合作班', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-22 09:00:00', '2026-04-15 21:23:30');
INSERT INTO `class_assign` VALUES (10, '校企合作定向班，企业参与培养', 40, '2025-08-22 10:00:00', '就业指导处赵主任', '2025-08-22 16:00:00', 'assigned', '华为定向班', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-22 09:00:00', '2026-04-15 21:23:30');

-- ----------------------------
-- Table structure for club_mgmt
-- ----------------------------
DROP TABLE IF EXISTS `club_mgmt`;
CREATE TABLE `club_mgmt`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `club_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '社团名称',
  `club_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '社团类型：文体/学术/志愿/其他',
  `student_id` bigint NOT NULL COMMENT '学生 ID',
  `apply_time` datetime NOT NULL COMMENT '入团申请时间',
  `audit_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
  `archive_time` datetime NULL DEFAULT NULL COMMENT '建档时间',
  `venue_apply_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '场馆申请状态：无/待申请/已通过',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：待审核/已通过/已建档',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_club_name`(`club_name` ASC) USING BTREE,
  INDEX `idx_club_type`(`club_type` ASC) USING BTREE,
  INDEX `idx_student_id`(`student_id` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '社团管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of club_mgmt
-- ----------------------------
INSERT INTO `club_mgmt` VALUES (1, '篮球社', '1', 1, '2024-09-15 10:00:00', '芋道源码', '2026-04-15 17:10:52', '2024-09-20 10:00:00', '2', '1', '体育爱好者', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-15 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `club_mgmt` VALUES (2, '赵六', '1', 1, '2024-09-15 10:00:00', 'admin', '2024-09-18 14:00:00', '2024-09-20 10:00:00', '2', '1', '随123便', '', '', 'admin', '1', b'0', 1, '2024-09-16 09:00:00', '2026-04-15 17:26:09');
INSERT INTO `club_mgmt` VALUES (3, '志愿者协会', '3', 5, '2024-09-17 10:00:00', '王老师', '2024-09-20 14:00:00', '2024-09-22 10:00:00', '2', '1', '热心公益', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-17 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `club_mgmt` VALUES (4, '音乐社', '1', 7, '2024-09-18 10:00:00', '赵老师', '2024-09-21 14:00:00', '2024-09-23 10:00:00', '1', '1', '吉他手', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-18 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `club_mgmt` VALUES (5, '英语角', '2', 9, '2024-09-19 10:00:00', '孙老师', '2024-09-22 14:00:00', '2024-09-24 10:00:00', '0', '1', '英语学习', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-19 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `club_mgmt` VALUES (6, '摄影社', '1', 2, '2024-09-20 10:00:00', '周老师', '2024-09-23 14:00:00', NULL, '1', '0', '摄影爱好', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-20 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `club_mgmt` VALUES (7, '读书社', '2', 6, '2024-09-21 10:00:00', '吴老师', '2024-09-24 14:00:00', '2024-09-26 10:00:00', '0', '1', '文学爱好', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-21 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `club_mgmt` VALUES (8, '舞蹈社', '1', 10, '2024-09-22 10:00:00', '郑老师', '2024-09-25 14:00:00', '2024-09-27 10:00:00', '2', '1', '民族舞', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-22 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `club_mgmt` VALUES (9, '环保社', '3', 4, '2024-09-23 10:00:00', '陈老师', '2024-09-26 14:00:00', NULL, '0', '0', '环保宣传', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-23 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `club_mgmt` VALUES (10, '辩论社', '2', 8, '2024-09-24 10:00:00', '林老师', '2024-09-27 14:00:00', '2024-09-29 10:00:00', '2', '1', '口才训练', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-24 09:00:00', '2026-04-15 20:27:02');

-- ----------------------------
-- Table structure for communicate_mgmt
-- ----------------------------
DROP TABLE IF EXISTS `communicate_mgmt`;
CREATE TABLE `communicate_mgmt`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '消息标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '消息内容',
  `send_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发布人',
  `send_time` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `reply_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '家长反馈内容',
  `reply_time` datetime NULL DEFAULT NULL COMMENT '反馈时间',
  `interact_rate` decimal(5, 2) NULL DEFAULT NULL COMMENT '互动率',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：未发布/已发布',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_send_time`(`send_time` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '沟通管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of communicate_mgmt
-- ----------------------------
INSERT INTO `communicate_mgmt` VALUES (1, '关于国庆节放假安排的通知', '尊敬的家长：国庆节放假时间为10月1日-7日，请安排好孩子的假期生活，注意安全。', '教务处张老师', '2025-09-28 10:00:00', '收到，谢谢老师提醒', '2025-09-28 15:00:00', 95.50, '已发布', '国庆放假通知', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-28 09:00:00', '2025-09-28 10:00:00');
INSERT INTO `communicate_mgmt` VALUES (2, '学生月考成绩通报', '各位家长：10月份月考成绩已出，请查收孩子各科成绩及排名情况。', '班主任李老师', '2025-10-15 16:00:00', '已查看，孩子进步很大，感谢老师', '2025-10-15 20:00:00', 92.30, '已发布', '月考成绩通知', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-15 15:00:00', '2025-10-15 16:00:00');
INSERT INTO `communicate_mgmt` VALUES (3, '家长会邀请通知', '定于10月20日下午2点召开家长会，请各位家长准时参加，共同关心孩子成长。', '班主任王老师', '2025-10-10 09:00:00', '会准时参加', '2025-10-10 14:00:00', 88.70, '已发布', '家长会通知', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-10 08:00:00', '2025-10-10 09:00:00');
INSERT INTO `communicate_mgmt` VALUES (4, '冬季校服订购通知', '学校将统一订购冬季校服，请家长在10月25日前完成订购缴费。', '后勤处赵老师', '2025-10-18 10:00:00', '已缴费', '2025-10-18 16:00:00', 85.20, '已发布', '校服订购', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-18 09:00:00', '2025-10-18 10:00:00');
INSERT INTO `communicate_mgmt` VALUES (5, '学生心理健康关怀', '近期发现部分学生压力较大，建议家长多与孩子沟通，关注心理健康。', '心理中心刘老师', '2025-10-20 14:00:00', '好的，会多关心孩子', '2025-10-20 19:00:00', 90.60, '已发布', '心理健康提醒', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-20 13:00:00', '2025-10-20 14:00:00');
INSERT INTO `communicate_mgmt` VALUES (6, '校园安全温馨提示', '提醒各位家长教育孩子注意交通安全、食品安全，提高自我保护意识。', '安保处陈老师', '2025-10-22 09:00:00', NULL, NULL, 78.40, '已发布', '安全教育', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-22 08:00:00', '2025-10-22 09:00:00');
INSERT INTO `communicate_mgmt` VALUES (7, '期末考试安排通知', '期末考试将于1月10日-15日进行，请督促孩子认真复习备考。', '教务处杨老师', '2025-12-20 10:00:00', '收到，会督促孩子复习', '2025-12-20 18:00:00', 93.80, '已发布', '期末考通知', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-12-20 09:00:00', '2025-12-20 10:00:00');
INSERT INTO `communicate_mgmt` VALUES (8, '寒假作业布置说明', '寒假作业已通过平台发布，请家长监督孩子按时完成。', '班主任周老师', '2026-01-12 15:00:00', '已下载作业清单', '2026-01-12 20:00:00', 91.20, '已发布', '寒假作业', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-01-12 14:00:00', '2026-01-12 15:00:00');
INSERT INTO `communicate_mgmt` VALUES (9, '春季开学报到通知', '春季学期于2月20日报到，2月21日正式上课，请做好准备。', '教务处吴老师', '2026-02-10 10:00:00', NULL, NULL, 65.30, '已发布', '开学通知', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-10 09:00:00', '2026-02-10 10:00:00');
INSERT INTO `communicate_mgmt` VALUES (10, '学生综合素质评价反馈', '本学期学生综合素质评价已完成，请家长查看评价报告并提出意见。', '德育处郑老师', '2026-01-18 14:00:00', '评价很全面，谢谢老师', '2026-01-18 21:00:00', 87.90, '已发布', '素质评价', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-01-18 13:00:00', '2026-01-18 14:00:00');

-- ----------------------------
-- Table structure for compare_mgmt
-- ----------------------------
DROP TABLE IF EXISTS `compare_mgmt`;
CREATE TABLE `compare_mgmt`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `class_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '班级',
  `cycle` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评比周期：周/月/学期',
  `total_score` decimal(5, 2) NOT NULL COMMENT '总得分',
  `rank_no` int NULL DEFAULT NULL COMMENT '排名',
  `award_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '授予称号',
  `award_time` datetime NULL DEFAULT NULL COMMENT '授予时间',
  `score_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '打分人',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：打分中/已汇总',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_class_name`(`class_name` ASC) USING BTREE,
  INDEX `idx_cycle`(`cycle` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评比管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of compare_mgmt
-- ----------------------------
INSERT INTO `compare_mgmt` VALUES (1, '2024级软件工程1班', 'month', 95.50, 1, '优秀班集体', '2025-10-01 10:00:00', '张老师', '已汇总', '月度评比第一名', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-01 09:00:00', '2026-04-15 21:25:26');
INSERT INTO `compare_mgmt` VALUES (2, '2024级软件工程2班', 'month', 92.30, 2, '文明班级', '2025-10-01 10:00:00', '李老师', '已汇总', '月度评比第二名', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-01 09:00:00', '2026-04-15 21:25:26');
INSERT INTO `compare_mgmt` VALUES (3, '2024级计算机科学1班', 'month', 89.80, 3, '进步班级', '2025-10-01 10:00:00', '王老师', '已汇总', '月度评比第三名', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-01 09:00:00', '2026-04-15 21:25:26');
INSERT INTO `compare_mgmt` VALUES (4, '2024级网络工程1班', 'week', 88.50, 4, NULL, NULL, '赵老师', '打分中', '周评比进行中', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-07 09:00:00', '2026-04-15 21:25:26');
INSERT INTO `compare_mgmt` VALUES (5, '2024级信息安全1班', 'week', 91.20, 2, NULL, NULL, '刘老师', '打分中', '周评比进行中', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-07 09:00:00', '2026-04-15 21:25:26');
INSERT INTO `compare_mgmt` VALUES (6, '2024级软件工程1班', 'semester', 93.60, 1, '先进班集体', '2026-01-15 14:00:00', '陈老师', '已汇总', '学期总评第一名', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-01-15 10:00:00', '2026-04-15 21:25:26');
INSERT INTO `compare_mgmt` VALUES (7, '2024级软件工程2班', 'semester', 90.40, 2, '优秀班级', '2026-01-15 14:00:00', '杨老师', '已汇总', '学期总评第二名', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-01-15 10:00:00', '2026-04-15 21:25:26');
INSERT INTO `compare_mgmt` VALUES (8, '2024级计算机科学1班', 'semester', 87.90, 3, '文明班级', '2026-01-15 14:00:00', '周老师', '已汇总', '学期总评第三名', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-01-15 10:00:00', '2026-04-15 21:25:26');
INSERT INTO `compare_mgmt` VALUES (9, '2024级网络工程1班', 'month', 86.70, 5, NULL, NULL, '吴老师', '已汇总', '月度评比第五名', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-11-01 09:00:00', '2026-04-15 21:25:26');
INSERT INTO `compare_mgmt` VALUES (10, '2024级信息安全1班', 'month', 90.10, 3, '团结班级', '2025-11-01 10:00:00', '郑老师', '已汇总', '月度评比第三名', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-11-01 09:00:00', '2026-04-15 21:25:26');

-- ----------------------------
-- Table structure for coop_enterprise
-- ----------------------------
DROP TABLE IF EXISTS `coop_enterprise`;
CREATE TABLE `coop_enterprise`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `enterprise_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '企业名称',
  `enterprise_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '企业类型：国企/民企/外企',
  `dept_id` bigint NOT NULL COMMENT '负责系部',
  `contact_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `coop_start_time` datetime NULL DEFAULT NULL COMMENT '合作开始时间',
  `coop_end_time` datetime NULL DEFAULT NULL COMMENT '合作结束时间',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：合作中/已结束',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_enterprise_name`(`enterprise_name` ASC) USING BTREE,
  INDEX `idx_enterprise_type`(`enterprise_type` ASC) USING BTREE,
  INDEX `idx_dept_id`(`dept_id` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '校企合作表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of coop_enterprise
-- ----------------------------
INSERT INTO `coop_enterprise` VALUES (1, '华为技术有限公司', 'private', 2001, '张经理', '13900139001', '2024-01-01 00:00:00', '2026-12-31 23:59:59', 'cooperating', '软件开发人才培养合作', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-01-01 09:00:00', '2026-04-15 21:26:01');
INSERT INTO `coop_enterprise` VALUES (2, '阿里巴巴集团', 'private', 2001, '李总监', '13900139002', '2024-03-01 00:00:00', '2027-02-28 23:59:59', 'cooperating', '云计算方向校企合作', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-03-01 09:00:00', '2026-04-15 21:26:01');
INSERT INTO `coop_enterprise` VALUES (3, '中国移动通信集团', 'state_owned', 2002, '王主任', '13900139003', '2023-06-01 00:00:00', '2026-05-31 23:59:59', 'cooperating', '网络工程专业实习基地', NULL, NULL, 'admin', 'admin', b'0', 1, '2023-06-01 09:00:00', '2026-04-15 21:26:01');
INSERT INTO `coop_enterprise` VALUES (4, '腾讯科技有限公司', 'private', 2001, '赵经理', '13900139004', '2024-06-01 00:00:00', '2027-05-31 23:59:59', 'cooperating', '游戏开发人才培养', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-06-01 09:00:00', '2026-04-15 21:26:01');
INSERT INTO `coop_enterprise` VALUES (5, '中国银行软件中心', 'state_owned', 2003, '刘处长', '13900139005', '2023-09-01 00:00:00', '2026-08-31 23:59:59', 'cooperating', '金融IT人才定向培养', NULL, NULL, 'admin', 'admin', b'0', 1, '2023-09-01 09:00:00', '2026-04-15 21:26:01');
INSERT INTO `coop_enterprise` VALUES (6, '百度在线网络技术', 'private', 2001, '陈主管', '13900139006', '2024-09-01 00:00:00', '2027-08-31 23:59:59', 'cooperating', '人工智能方向合作', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-01 09:00:00', '2026-04-15 21:26:01');
INSERT INTO `coop_enterprise` VALUES (7, 'IBM中国有限公司', 'foreign', 2002, '周经理', '13900139007', '2022-01-01 00:00:00', '2024-12-31 23:59:59', 'ended', '历史合作项目', NULL, NULL, 'admin', 'admin', b'0', 1, '2022-01-01 09:00:00', '2026-04-15 21:26:01');
INSERT INTO `coop_enterprise` VALUES (8, '京东集团', 'private', 2001, '吴总监', '13900139008', '2024-11-01 00:00:00', '2027-10-31 23:59:59', 'cooperating', '电商物流信息化合作', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-01 09:00:00', '2026-04-15 21:26:01');
INSERT INTO `coop_enterprise` VALUES (9, '中国电信股份有限公司', 'state_owned', 2002, '郑主任', '13900139009', '2023-03-01 00:00:00', '2026-02-28 23:59:59', 'cooperating', '通信技术人才培养', NULL, NULL, 'admin', 'admin', b'0', 1, '2023-03-01 09:00:00', '2026-04-15 21:26:01');
INSERT INTO `coop_enterprise` VALUES (10, '微软中国有限公司', 'foreign', 2003, '孙经理', '13900139010', '2024-01-15 00:00:00', '2027-01-14 23:59:59', 'cooperating', '办公软件培训合作', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-01-15 09:00:00', '2026-04-15 21:26:01');

-- ----------------------------
-- Table structure for dorm_assign
-- ----------------------------
DROP TABLE IF EXISTS `dorm_assign`;
CREATE TABLE `dorm_assign`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `student_id` bigint NOT NULL COMMENT '学生 ID',
  `dorm_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '宿舍号',
  `bed_id` bigint NULL DEFAULT NULL COMMENT '床位 ID',
  `rule_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '分配规则',
  `assign_time` datetime NULL DEFAULT NULL COMMENT '分配时间',
  `adjust_time` datetime NULL DEFAULT NULL COMMENT '调整时间',
  `finish_rate` decimal(5, 2) NULL DEFAULT NULL COMMENT '分配完成率',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：未分配/已分配',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_student_id`(`student_id` ASC) USING BTREE,
  INDEX `idx_dorm_num`(`dorm_num` ASC) USING BTREE,
  INDEX `idx_bed_id`(`bed_id` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '宿舍分配表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dorm_assign
-- ----------------------------
INSERT INTO `dorm_assign` VALUES (1, 1, 'A栋101', 1, '按专业班级就近分配原则', '2025-08-25 10:00:00', NULL, 100.00, 'assigned', '软件工程专业1班', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 09:00:00', '2026-04-15 21:30:29');
INSERT INTO `dorm_assign` VALUES (2, 2, 'A栋101', 2, '按专业班级就近分配原则', '2025-08-25 10:00:00', NULL, 100.00, 'assigned', '软件工程专业1班', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 09:00:00', '2026-04-15 21:30:29');
INSERT INTO `dorm_assign` VALUES (3, 3, 'A栋101', 3, '按专业班级就近分配原则', '2025-08-25 10:00:00', NULL, 100.00, 'assigned', '软件工程专业1班', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 09:00:00', '2026-04-15 21:30:29');
INSERT INTO `dorm_assign` VALUES (4, 4, 'A栋101', 4, '按专业班级就近分配原则', '2025-08-25 10:00:00', NULL, 100.00, 'assigned', '软件工程专业1班', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 09:00:00', '2026-04-15 21:30:29');
INSERT INTO `dorm_assign` VALUES (5, 5, 'A栋201', 5, '按专业班级就近分配原则', '2025-08-25 10:00:00', NULL, 100.00, 'assigned', '计算机专业1班', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 09:00:00', '2026-04-15 21:30:29');
INSERT INTO `dorm_assign` VALUES (6, 6, 'A栋201', 6, '按专业班级就近分配原则', '2025-08-25 10:00:00', NULL, 100.00, 'assigned', '计算机专业1班', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 09:00:00', '2026-04-15 21:30:29');
INSERT INTO `dorm_assign` VALUES (7, 7, 'A栋201', 7, '按专业班级就近分配原则', '2025-08-25 10:00:00', '2025-09-10 14:00:00', 100.00, 'assigned', '已调整过一次', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 09:00:00', '2026-04-15 21:30:29');
INSERT INTO `dorm_assign` VALUES (8, 8, 'A栋201', 8, '按专业班级就近分配原则', '2025-08-25 10:00:00', NULL, 100.00, 'assigned', '计算机专业1班', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 09:00:00', '2026-04-15 21:30:29');
INSERT INTO `dorm_assign` VALUES (9, 9, 'B栋101', NULL, '按生源地均衡分配原则', NULL, NULL, 85.50, 'unassigned', '等待最终确认', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 09:00:00', '2026-04-15 21:30:29');
INSERT INTO `dorm_assign` VALUES (10, 10, 'B栋102', NULL, '按生源地均衡分配原则', NULL, NULL, 85.50, 'unassigned', '等待床位安排', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 09:00:00', '2026-04-15 21:30:29');

-- ----------------------------
-- Table structure for dorm_check
-- ----------------------------
DROP TABLE IF EXISTS `dorm_check`;
CREATE TABLE `dorm_check`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `student_id` bigint NOT NULL COMMENT '学生 ID',
  `check_time` datetime NOT NULL COMMENT '考勤时间',
  `check_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '考勤状态：正常/迟到/未到',
  `abnormal_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '异常类型：无/晚归/未归',
  `repair_time` datetime NULL DEFAULT NULL COMMENT '补卡时间',
  `repair_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '补卡人',
  `push_time` datetime NULL DEFAULT NULL COMMENT '推送时间',
  `in_rate` decimal(5, 2) NULL DEFAULT NULL COMMENT '在寝率',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：正常/异常',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_student_id`(`student_id` ASC) USING BTREE,
  INDEX `idx_check_time`(`check_time` ASC) USING BTREE,
  INDEX `idx_check_status`(`check_status` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '宿舍考勤表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dorm_check
-- ----------------------------
INSERT INTO `dorm_check` VALUES (1, 1, '2025-10-07 22:30:00', '0', '0', NULL, NULL, '2025-10-07 23:00:00', 98.50, '0', '按时归寝', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-07 22:30:00', '2026-04-15 20:27:02');
INSERT INTO `dorm_check` VALUES (2, 2, '2025-10-07 22:45:00', '1', '1', NULL, NULL, '2025-10-07 23:00:00', 95.20, '1', '晚归15分钟', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-07 22:45:00', '2026-04-15 20:27:02');
INSERT INTO `dorm_check` VALUES (3, 3, '2025-10-07 23:30:00', '2', '2', '2025-10-08 08:00:00', '辅导员张老师', '2025-10-07 23:30:00', 85.30, '1', '未归已补卡', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-07 23:30:00', '2026-04-15 20:27:02');
INSERT INTO `dorm_check` VALUES (4, 4, '2025-10-08 22:20:00', '0', '0', NULL, NULL, '2025-10-08 23:00:00', 99.10, '0', '按时归寝', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-08 22:20:00', '2026-04-15 20:27:02');
INSERT INTO `dorm_check` VALUES (5, 5, '2025-10-08 22:35:00', '0', '0', NULL, NULL, '2025-10-08 23:00:00', 97.80, '0', '按时归寝', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-08 22:35:00', '2026-04-15 20:27:02');
INSERT INTO `dorm_check` VALUES (6, 6, '2025-10-09 22:50:00', '1', '1', NULL, NULL, '2025-10-09 23:00:00', 93.50, '1', '晚归20分钟', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-09 22:50:00', '2026-04-15 20:27:02');
INSERT INTO `dorm_check` VALUES (7, 7, '2025-10-09 23:45:00', '2', '2', '2025-10-10 09:00:00', '辅导员李老师', '2025-10-09 23:45:00', 82.60, '1', '未归已补卡说明', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-09 23:45:00', '2026-04-15 20:27:02');
INSERT INTO `dorm_check` VALUES (8, 8, '2025-10-10 22:25:00', '0', '0', NULL, NULL, '2025-10-10 23:00:00', 98.90, '0', '按时归寝', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-10 22:25:00', '2026-04-15 20:27:02');
INSERT INTO `dorm_check` VALUES (9, 1, '2025-10-10 22:30:00', '0', '0', NULL, NULL, '2025-10-10 23:00:00', 99.50, '0', '连续按时归寝', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-10 22:30:00', '2026-04-15 20:27:02');
INSERT INTO `dorm_check` VALUES (10, 2, '2025-10-11 22:28:00', '0', '0', NULL, NULL, '2025-10-11 23:00:00', 96.70, '0', '改善表现', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-11 22:28:00', '2026-04-15 20:27:02');

-- ----------------------------
-- Table structure for dorm_compare
-- ----------------------------
DROP TABLE IF EXISTS `dorm_compare`;
CREATE TABLE `dorm_compare`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `dorm_id` bigint NULL DEFAULT NULL COMMENT '宿舍 ID',
  `dorm_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '宿舍号',
  `cycle` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评比周期：周/月/学期',
  `score` decimal(5, 2) NOT NULL COMMENT '得分',
  `rank_no` int NULL DEFAULT NULL COMMENT '排名',
  `score_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '打分人',
  `sum_time` datetime NULL DEFAULT NULL COMMENT '汇总时间',
  `push_time` datetime NULL DEFAULT NULL COMMENT '推送时间',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：打分中/已汇总',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_dorm_id`(`dorm_id` ASC) USING BTREE,
  INDEX `idx_dorm_num`(`dorm_num` ASC) USING BTREE,
  INDEX `idx_cycle`(`cycle` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '宿舍评比表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dorm_compare
-- ----------------------------
INSERT INTO `dorm_compare` VALUES (1, 1, 'A栋101', 'week', 95.50, 1, '宿管张老师', '2025-10-07 18:00:00', '2025-10-07 19:00:00', '已汇总', '卫生优秀，纪律良好', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-07 10:00:00', '2026-04-15 21:32:51');
INSERT INTO `dorm_compare` VALUES (2, 2, 'A栋102', 'week', 92.30, 2, '宿管李老师', '2025-10-07 18:00:00', '2025-10-07 19:00:00', '已汇总', '内务整洁', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-07 10:00:00', '2026-04-15 21:32:51');
INSERT INTO `dorm_compare` VALUES (3, 3, 'A栋201', 'week', 88.80, 3, '宿管王老师', '2025-10-07 18:00:00', '2025-10-07 19:00:00', '已汇总', '整体表现良好', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-07 10:00:00', '2026-04-15 21:32:51');
INSERT INTO `dorm_compare` VALUES (4, 4, 'A栋202', 'month', 90.60, 2, '宿管赵老师', '2025-10-31 18:00:00', '2025-10-31 19:00:00', '已汇总', '月度评比第二名', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-31 10:00:00', '2026-04-15 21:32:51');
INSERT INTO `dorm_compare` VALUES (5, 5, 'A栋301', 'month', 87.40, 4, '宿管刘老师', '2025-10-31 18:00:00', '2025-10-31 19:00:00', '已汇总', '需要改进卫生', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-31 10:00:00', '2026-04-15 21:32:51');
INSERT INTO `dorm_compare` VALUES (6, 6, 'B栋101', 'week', 93.20, 1, '宿管陈老师', '2025-11-03 18:00:00', NULL, '打分中', '本周评比进行中', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-11-03 10:00:00', '2026-04-15 21:32:51');
INSERT INTO `dorm_compare` VALUES (7, 7, 'B栋102', 'week', 91.50, 2, '宿管杨老师', '2025-11-03 18:00:00', NULL, '打分中', '本周评比进行中', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-11-03 10:00:00', '2026-04-15 21:32:51');
INSERT INTO `dorm_compare` VALUES (8, 8, 'B栋201', 'semester', 89.70, 3, '宿管周老师', '2026-01-15 18:00:00', '2026-01-15 19:00:00', '已汇总', '学期总评第三名', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-01-15 10:00:00', '2026-04-15 21:32:51');
INSERT INTO `dorm_compare` VALUES (9, 9, 'B栋202', 'semester', 92.80, 1, '宿管吴老师', '2026-01-15 18:00:00', '2026-01-15 19:00:00', '已汇总', '学期优秀宿舍', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-01-15 10:00:00', '2026-04-15 21:32:51');
INSERT INTO `dorm_compare` VALUES (10, 10, 'B栋301', 'month', 85.90, 5, '宿管郑老师', '2025-11-30 18:00:00', '2025-11-30 19:00:00', '已汇总', '需加强管理', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-11-30 10:00:00', '2026-04-15 21:32:51');

-- ----------------------------
-- Table structure for duty_mgmt
-- ----------------------------
DROP TABLE IF EXISTS `duty_mgmt`;
CREATE TABLE `duty_mgmt`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `duty_date` date NOT NULL COMMENT '值班日期',
  `duty_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '值班人',
  `check_in_time` datetime NULL DEFAULT NULL COMMENT '打卡时间',
  `check_in_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '打卡状态：未打卡/已打卡',
  `transfer_reason` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '调班原因',
  `transfer_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '调班替代人',
  `transfer_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '调班状态：无/待审批/已通过/已驳回',
  `car_reason` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '出车事由',
  `car_destination` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '出车目的地',
  `car_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '出车状态：无/待审批/已通过',
  `record_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '值班记录',
  `record_upload_time` datetime NULL DEFAULT NULL COMMENT '记录上传时间',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：待打卡/待调班审批/待出车审批/已完成',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_duty_date`(`duty_date` ASC) USING BTREE,
  INDEX `idx_duty_user`(`duty_user` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '值班管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of duty_mgmt
-- ----------------------------
INSERT INTO `duty_mgmt` VALUES (1, '2024-12-01', '张老师', '2024-12-01 08:00:00', 'checked_in', NULL, NULL, NULL, NULL, NULL, NULL, '值班正常', '2024-12-01 18:00:00', 'completed', NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-25 10:00:00', '2026-04-15 21:39:58');
INSERT INTO `duty_mgmt` VALUES (2, '2024-12-02', '李老师', '2024-12-02 08:00:00', 'checked_in', NULL, NULL, NULL, '接送学生', '火车站', '已通过', '值班正常，出车一次', '2024-12-02 18:00:00', 'completed', NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-25 10:00:00', '2026-04-15 21:39:58');
INSERT INTO `duty_mgmt` VALUES (3, '2024-12-03', '王老师', NULL, 'not_checked_in', '身体不适', '赵老师', '已通过', NULL, NULL, NULL, '调班后值班', '2024-12-03 18:00:00', 'completed', NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-25 10:00:00', '2026-04-15 21:39:58');
INSERT INTO `duty_mgmt` VALUES (4, '2024-12-04', '孙老师', '2024-12-04 08:00:00', 'checked_in', NULL, NULL, NULL, NULL, NULL, NULL, '值班正常', '2024-12-04 18:00:00', 'completed', NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-25 10:00:00', '2026-04-15 21:39:58');
INSERT INTO `duty_mgmt` VALUES (5, '2024-12-05', '周老师', '2024-12-05 08:00:00', 'checked_in', NULL, NULL, NULL, '采购物资', '超市', '已通过', '值班正常', '2024-12-05 18:00:00', 'completed', NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-25 10:00:00', '2026-04-15 21:39:58');
INSERT INTO `duty_mgmt` VALUES (6, '2024-12-06', '吴老师', NULL, 'not_checked_in', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'pending_checkin', NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-25 10:00:00', '2026-04-15 21:39:58');
INSERT INTO `duty_mgmt` VALUES (7, '2024-12-07', '郑老师', NULL, 'not_checked_in', '家中有事', '陈老师', '待审批', NULL, NULL, NULL, NULL, NULL, 'pending_transfer', NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-25 10:00:00', '2026-04-15 21:39:58');
INSERT INTO `duty_mgmt` VALUES (8, '2024-12-08', '陈老师', '2024-12-08 08:00:00', 'checked_in', NULL, NULL, NULL, NULL, NULL, NULL, '值班正常', '2024-12-08 18:00:00', 'completed', NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-25 10:00:00', '2026-04-15 21:39:58');
INSERT INTO `duty_mgmt` VALUES (9, '2024-12-09', '林老师', '2024-12-09 08:00:00', 'checked_in', NULL, NULL, NULL, '接待访客', '校门口', '已通过', '值班正常', '2024-12-09 18:00:00', 'completed', NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-25 10:00:00', '2026-04-15 21:39:58');
INSERT INTO `duty_mgmt` VALUES (10, '2024-12-10', '黄老师', NULL, 'not_checked_in', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'pending_checkin', NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-25 10:00:00', '2026-04-15 21:39:58');

-- ----------------------------
-- Table structure for fund_system
-- ----------------------------
DROP TABLE IF EXISTS `fund_system`;
CREATE TABLE `fund_system`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `student_id` bigint NOT NULL COMMENT '学生 ID',
  `fund_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资助类型：助学金/勤工俭学/其他',
  `apply_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '申请金额',
  `apply_time` datetime NOT NULL COMMENT '申请时间',
  `audit_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：待审核/已汇总',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_student_id`(`student_id` ASC) USING BTREE,
  INDEX `idx_fund_type`(`fund_type` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '资助系统表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fund_system
-- ----------------------------
INSERT INTO `fund_system` VALUES (1, 1, '1', 3000.00, '2024-09-10 10:00:00', '芋道源码', '2026-04-15 15:26:41', '1', '家庭经济困难', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-10 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `fund_system` VALUES (2, 5, '2', 1500.00, '2024-10-05 10:00:00', '李老师', '2024-10-10 14:00:00', '1', '图书馆助理', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-10-05 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `fund_system` VALUES (3, 10, '1', 2500.00, '2024-09-12 10:00:00', '王老师', '2024-09-18 14:00:00', '1', '单亲家庭', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-12 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `fund_system` VALUES (4, 3, '3', 1000.00, '2024-11-01 10:00:00', '赵老师', '2024-11-05 14:00:00', '0', '临时困难补助', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-01 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `fund_system` VALUES (5, 6, '2', 1200.00, '2024-10-15 10:00:00', '孙老师', '2024-10-20 14:00:00', '1', '食堂助理', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-10-15 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `fund_system` VALUES (6, 2, '1', 2000.00, '2024-09-08 10:00:00', '周老师', '2024-09-12 14:00:00', '1', '农村户口', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-08 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `fund_system` VALUES (7, 7, '3', 800.00, '2024-11-20 10:00:00', '吴老师', '2024-11-25 14:00:00', '0', '突发困难', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-20 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `fund_system` VALUES (8, 9, '2', 1800.00, '2024-10-25 10:00:00', '郑老师', '2024-10-30 14:00:00', '1', '实验室助理', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-10-25 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `fund_system` VALUES (9, 4, '1', 3500.00, '2024-09-15 10:00:00', '陈老师', '2024-09-20 14:00:00', '1', '低保家庭', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-15 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `fund_system` VALUES (10, 8, '3', 500.00, '2024-12-01 10:00:00', '林老师', NULL, '0', '紧急救助', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-12-01 09:00:00', '2026-04-15 20:27:02');

-- ----------------------------
-- Table structure for honor_mgmt
-- ----------------------------
DROP TABLE IF EXISTS `honor_mgmt`;
CREATE TABLE `honor_mgmt`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `student_id` bigint NOT NULL COMMENT '学生 ID',
  `honor_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '荣誉类型：1:优秀学生/2:奖学金/3:竞赛获奖/4:其他',
  `honor_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '荣誉名称',
  `get_time` datetime NOT NULL COMMENT '获得时间',
  `audit_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
  `push_time` datetime NULL DEFAULT NULL COMMENT '推送时间',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：1:待审核/2:未通过/3:已通过/4:已推送',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_student_id`(`student_id` ASC) USING BTREE,
  INDEX `idx_honor_type`(`honor_type` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '荣誉管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of honor_mgmt
-- ----------------------------
INSERT INTO `honor_mgmt` VALUES (1, 1, '1', '三好学生', '2024-12-20 10:00:00', '张老师', '2024-12-18 14:00:00', '2024-12-19 09:00:00', '2', '表现优异', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-12-15 08:00:00', '2026-04-15 20:27:02');
INSERT INTO `honor_mgmt` VALUES (2, 2, '2', '一等奖学金', '2024-11-15 10:00:00', '1', '2026-04-14 11:23:57', '2024-11-14 09:00:00', '3', '不合格', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-10 08:00:00', '2026-04-15 20:27:02');
INSERT INTO `honor_mgmt` VALUES (3, 3, '3', '程序设计大赛二等奖', '2024-10-20 10:00:00', '王老师', '2024-10-18 14:00:00', NULL, '1', '省级比赛', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-10-15 08:00:00', '2026-04-15 20:27:02');
INSERT INTO `honor_mgmt` VALUES (4, 5, '1', '优秀班干部', '2024-12-25 10:00:00', '1', '2026-04-14 11:23:57', '2024-12-24 09:00:00', '3', '不合格', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-12-20 08:00:00', '2026-04-15 20:27:02');
INSERT INTO `honor_mgmt` VALUES (5, 7, '4', '文明学生', '2024-11-30 10:00:00', '孙老师', '2024-11-28 14:00:00', '2024-11-29 09:00:00', '2', '行为规范', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-25 08:00:00', '2026-04-15 20:27:02');
INSERT INTO `honor_mgmt` VALUES (6, 1, '2', '二等奖学金', '2024-06-15 10:00:00', '张老师', '2024-06-13 14:00:00', '2024-06-14 09:00:00', '2', '上学期成绩', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-06-10 08:00:00', '2026-04-15 20:27:02');
INSERT INTO `honor_mgmt` VALUES (7, 9, '3', '科技创新大赛一等奖', '2024-09-20 10:00:00', '周老师', '2024-09-18 14:00:00', '2024-09-19 09:00:00', '2', '国家级比赛', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-15 08:00:00', '2026-04-15 20:27:02');
INSERT INTO `honor_mgmt` VALUES (8, 6, '1', '学习标兵', '2024-12-10 10:00:00', '吴老师', '2024-12-08 14:00:00', NULL, '1', '学习刻苦', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-12-05 08:00:00', '2026-04-15 20:27:02');
INSERT INTO `honor_mgmt` VALUES (9, 3, '4', '优秀团员', '2024-07-01 10:00:00', '郑老师', '2024-06-29 14:00:00', '2024-06-30 09:00:00', '2', '团内表现优秀', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-06-25 08:00:00', '2026-04-15 20:27:02');
INSERT INTO `honor_mgmt` VALUES (10, 10, '2', '励志奖学金', '2024-10-15 10:00:00', '陈老师', '2024-10-13 14:00:00', '2024-10-14 09:00:00', '2', '自强不息', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-10-10 08:00:00', '2026-04-15 20:27:02');

-- ----------------------------
-- Table structure for leave_handle
-- ----------------------------
DROP TABLE IF EXISTS `leave_handle`;
CREATE TABLE `leave_handle`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `student_id` bigint NOT NULL COMMENT '学生 ID',
  `leave_time` datetime NOT NULL COMMENT '离校时间',
  `leave_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '离校去处',
  `parent_confirm_time` datetime NULL DEFAULT NULL COMMENT '家长确认时间',
  `handle_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '办理人',
  `handle_time` datetime NULL DEFAULT NULL COMMENT '办理时间',
  `checkout_time` datetime NULL DEFAULT NULL COMMENT '退宿时间',
  `checkout_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '退宿状态：未退宿/已退宿',
  `finish_rate` decimal(5, 2) NULL DEFAULT NULL COMMENT '办理完成率',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：待确认/待办理/已离校',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_student_id`(`student_id` ASC) USING BTREE,
  INDEX `idx_leave_time`(`leave_time` ASC) USING BTREE,
  INDEX `idx_checkout_status`(`checkout_status` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '离校办理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of leave_handle
-- ----------------------------
INSERT INTO `leave_handle` VALUES (1, 1, '2026-06-20 10:00:00', '北京市海淀区中关村大街1号', '2026-06-18 14:00:00', '辅导员张老师', '2026-06-19 10:00:00', '2026-06-20 09:00:00', 'checked_out', 100.00, 'left', '所有手续已完成', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-06-15 09:00:00', '2026-04-15 21:43:14');
INSERT INTO `leave_handle` VALUES (2, 2, '2026-06-21 14:00:00', '上海市浦东新区陆家嘴环路100号', '2026-06-19 10:00:00', '辅导员李老师', '2026-06-20 14:00:00', '2026-06-21 10:00:00', 'checked_out', 100.00, 'left', '离校手续齐全', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-06-16 09:00:00', '2026-04-15 21:43:14');
INSERT INTO `leave_handle` VALUES (3, 3, '2026-06-22 09:00:00', '广州市天河区天河路200号', '2026-06-20 15:00:00', '辅导员王老师', '2026-06-21 09:00:00', '2026-06-22 08:00:00', 'checked_out', 100.00, 'left', '顺利离校', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-06-17 09:00:00', '2026-04-15 21:43:14');
INSERT INTO `leave_handle` VALUES (4, 4, '2026-06-23 11:00:00', '深圳市南山区科技园南路300号', '2026-06-21 10:00:00', '辅导员赵老师', '2026-06-22 11:00:00', NULL, 'not_checked_out', 85.50, 'pending_handle', '等待退宿手续', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-06-18 09:00:00', '2026-04-15 21:43:14');
INSERT INTO `leave_handle` VALUES (5, 5, '2026-06-24 15:00:00', '杭州市西湖区文三路400号', '2026-06-22 14:00:00', '辅导员刘老师', '2026-06-23 15:00:00', '2026-06-24 10:00:00', 'checked_out', 100.00, 'left', '已完成所有流程', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-06-19 09:00:00', '2026-04-15 21:43:14');
INSERT INTO `leave_handle` VALUES (6, 6, '2026-06-25 10:00:00', '南京市鼓楼区中山北路500号', '2026-06-20 09:00:00', '辅导员陈老师', NULL, NULL, 'not_checked_out', 60.30, 'pending_confirm', '等待家长确认', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-06-20 09:00:00', '2026-04-15 21:43:14');
INSERT INTO `leave_handle` VALUES (7, 7, '2026-06-26 14:00:00', '武汉市洪山区珞喻路600号', '2026-06-24 10:00:00', '辅导员杨老师', '2026-06-25 14:00:00', '2026-06-26 09:00:00', 'checked_out', 100.00, 'left', '离校手续完备', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-06-21 09:00:00', '2026-04-15 21:43:14');
INSERT INTO `leave_handle` VALUES (8, 8, '2026-06-27 09:00:00', '成都市武侯区人民南路700号', '2026-06-25 15:00:00', '辅导员周老师', '2026-06-26 09:00:00', NULL, 'not_checked_out', 90.20, 'pending_handle', '图书归还中', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-06-22 09:00:00', '2026-04-15 21:43:14');
INSERT INTO `leave_handle` VALUES (9, 9, '2026-06-28 11:00:00', '西安市雁塔区长安南路800号', '2026-06-26 10:00:00', '辅导员吴老师', NULL, NULL, 'not_checked_out', 70.50, '待审核', '等待财务审核', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-06-23 09:00:00', '2026-04-15 21:43:14');
INSERT INTO `leave_handle` VALUES (10, 10, '2026-06-29 15:00:00', '重庆市渝中区解放碑步行街900号', '2026-06-27 14:00:00', '辅导员郑老师', '2026-06-28 15:00:00', '2026-06-29 10:00:00', 'checked_out', 100.00, 'left', '圆满毕业离校', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-06-24 09:00:00', '2026-04-15 21:43:14');

-- ----------------------------
-- Table structure for mental_mgmt
-- ----------------------------
DROP TABLE IF EXISTS `mental_mgmt`;
CREATE TABLE `mental_mgmt`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `student_id` bigint NOT NULL COMMENT '学生 ID',
  `mental_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '心理状态：正常/关注/高危',
  `risk_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '风险等级：低/中/高',
  `evaluate_time` datetime NULL DEFAULT NULL COMMENT '评估时间',
  `consult_time` datetime NULL DEFAULT NULL COMMENT '咨询预约时间',
  `intervene_time` datetime NULL DEFAULT NULL COMMENT '干预时间',
  `intervene_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '干预内容',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：待评估/咨询中/已干预',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_student_id`(`student_id` ASC) USING BTREE,
  INDEX `idx_mental_status`(`mental_status` ASC) USING BTREE,
  INDEX `idx_risk_level`(`risk_level` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '心理管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mental_mgmt
-- ----------------------------
INSERT INTO `mental_mgmt` VALUES (1, 4, 'focus', 'medium', '2024-10-20 10:00:00', '2024-08-21 14:13:20', '2024-10-25 15:00:00', '心理辅导谈话', 'intervened', '休学导致情绪低落', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-10-20 09:00:00', '2026-04-15 15:13:02');
INSERT INTO `mental_mgmt` VALUES (2, 8, 'high_risk', 'high', '2024-11-10 10:00:00', '2024-11-12 14:00:00', '2024-11-15 15:00:00', '专业心理咨询', 'intervened', '退学前情绪波动', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-10 09:00:00', '2026-04-14 12:32:49');
INSERT INTO `mental_mgmt` VALUES (3, 2, 'normal', 'low', '2024-09-25 10:00:00', NULL, NULL, NULL, 'wait_evaluate', '常规评估', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-25 09:00:00', '2026-04-14 12:32:53');
INSERT INTO `mental_mgmt` VALUES (4, 5, 'normal', 'low', '2024-12-01 10:00:00', NULL, NULL, NULL, 'wait_evaluate', '心理健康', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-12-01 09:00:00', '2026-04-14 12:32:53');
INSERT INTO `mental_mgmt` VALUES (5, 10, 'focus', 'medium', '2024-12-05 10:00:00', '2024-12-07 14:00:00', NULL, NULL, 'consulting', '转学适应问题', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-12-05 09:00:00', '2026-04-14 12:32:38');
INSERT INTO `mental_mgmt` VALUES (6, 1, 'normal', 'low', '2024-11-20 10:00:00', NULL, NULL, NULL, 'wait_evaluate', '状态良好', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-20 09:00:00', '2026-04-14 12:32:53');
INSERT INTO `mental_mgmt` VALUES (7, 3, 'normal', 'low', '2024-10-15 10:00:00', NULL, NULL, NULL, 'wait_evaluate', '积极向上', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-10-15 09:00:00', '2026-04-14 12:32:53');
INSERT INTO `mental_mgmt` VALUES (8, 6, 'focus', 'low', '2024-12-10 10:00:00', '2024-12-12 14:00:00', NULL, NULL, 'consulting', '学习压力大', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-12-10 09:00:00', '2026-04-14 12:32:38');
INSERT INTO `mental_mgmt` VALUES (9, 7, 'normal', 'low', '2024-09-30 10:00:00', NULL, NULL, NULL, 'wait_evaluate', '心态平稳', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-30 09:00:00', '2026-04-14 12:32:53');
INSERT INTO `mental_mgmt` VALUES (10, 9, 'normal', 'low', '2024-11-25 10:00:00', NULL, NULL, NULL, 'wait_evaluate', '表现优秀', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-25 09:00:00', '2026-04-14 12:32:53');

-- ----------------------------
-- Table structure for moral_activity
-- ----------------------------
DROP TABLE IF EXISTS `moral_activity`;
CREATE TABLE `moral_activity`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `activity_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动名称',
  `activity_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动类型：党团活动/志愿活动/其他',
  `host_dept` bigint NOT NULL COMMENT '主办部门',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `join_num` int NULL DEFAULT NULL COMMENT '参与人数',
  `photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动照片地址',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '活动详情',
  `publish_time` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：未发布/进行中/已结束',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_activity_type`(`activity_type` ASC) USING BTREE,
  INDEX `idx_host_dept`(`host_dept` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '德育活动表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of moral_activity
-- ----------------------------
INSERT INTO `moral_activity` VALUES (1, '学雷锋志愿服务活动', 'volunteer', 1001, '2025-03-05 09:00:00', '2025-03-05 17:00:00', 120, '/upload/activity/leifeng.jpg', '开展学雷锋志愿服务，弘扬雷锋精神', '2025-03-01 10:00:00', 'ended', '学雷锋月活动', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-03-01 10:00:00', '2026-04-15 21:45:31');
INSERT INTO `moral_activity` VALUES (2, '清明节祭扫烈士墓', 'party_league', 1002, '2025-04-04 08:00:00', '2025-04-04 12:00:00', 80, '/upload/activity/qingming.jpg', '缅怀革命先烈，传承红色基因', '2025-03-25 14:00:00', 'ended', '清明祭扫活动', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-03-25 14:00:00', '2026-04-15 21:45:31');
INSERT INTO `moral_activity` VALUES (3, '五四青年节文艺汇演', 'party_league', 1001, '2025-05-04 19:00:00', '2025-05-04 21:30:00', 300, '/upload/activity/wusi.jpg', '庆祝五四青年节，展现青春风采', '2025-04-20 10:00:00', 'ended', '五四文艺汇演', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-04-20 10:00:00', '2026-04-15 21:45:31');
INSERT INTO `moral_activity` VALUES (4, '暑期三下乡社会实践', 'volunteer', 1003, '2025-07-10 08:00:00', '2025-07-20 18:00:00', 50, '/upload/activity/sanxiaxiang.jpg', '大学生暑期文化科技卫生三下乡活动', '2025-06-15 09:00:00', 'ended', '暑期社会实践', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-06-15 09:00:00', '2026-04-15 21:45:31');
INSERT INTO `moral_activity` VALUES (5, '国庆节爱国主义教育活动', 'party_league', 1002, '2025-10-01 09:00:00', '2025-10-01 17:00:00', 200, '/upload/activity/guoqing.jpg', '庆祝国庆，加强爱国主义教育', '2025-09-20 10:00:00', 'ended', '国庆主题活动', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-20 10:00:00', '2026-04-15 21:45:31');
INSERT INTO `moral_activity` VALUES (6, '校园环保公益行', 'volunteer', 1001, '2025-11-15 09:00:00', '2025-11-15 16:00:00', 150, '/upload/activity/huanbao.jpg', '校园环境保护公益活动', '2025-11-01 10:00:00', 'ended', '环保公益活动', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-11-01 10:00:00', '2026-04-15 21:45:31');
INSERT INTO `moral_activity` VALUES (7, '元旦迎新晚会', 'other', 1003, '2025-12-31 19:00:00', '2025-12-31 22:00:00', 500, '/upload/activity/yuandan.jpg', '迎新年庆元旦文艺晚会', '2025-12-15 10:00:00', 'ended', '元旦晚会', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-12-15 10:00:00', '2026-04-15 21:45:31');
INSERT INTO `moral_activity` VALUES (8, '春季运动会', 'other', 1002, '2026-04-15 08:00:00', '2026-04-17 18:00:00', 800, '/upload/activity/yundonghui.jpg', '春季田径运动会', '2026-03-20 10:00:00', 'ongoing', '春季运动会', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-03-20 10:00:00', '2026-04-15 21:45:31');
INSERT INTO `moral_activity` VALUES (9, '读书月系列活动', 'other', 1001, '2026-04-23 09:00:00', '2026-05-23 17:00:00', NULL, NULL, '世界读书月主题系列活动', '2026-04-10 10:00:00', 'unpublished', '读书月活动', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-04-10 10:00:00', '2026-04-15 21:45:31');
INSERT INTO `moral_activity` VALUES (10, '毕业季感恩教育活动', 'party_league', 1003, '2026-06-01 09:00:00', '2026-06-30 17:00:00', NULL, NULL, '毕业生感恩母校主题教育活动', '2026-05-15 10:00:00', 'unpublished', '毕业季活动', NULL, NULL, 'admin', 'admin', b'0', 1, '2026-05-15 10:00:00', '2026-04-15 21:45:31');

-- ----------------------------
-- Table structure for moral_resource
-- ----------------------------
DROP TABLE IF EXISTS `moral_resource`;
CREATE TABLE `moral_resource`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `resource_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源名称',
  `resource_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源类型：课程/图书/专题包',
  `resource_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '资源地址',
  `learn_num` int NULL DEFAULT NULL COMMENT '学习人数',
  `learn_rate` decimal(5, 2) NULL DEFAULT NULL COMMENT '学习完成率',
  `publish_time` datetime NULL DEFAULT NULL COMMENT '上架时间',
  `off_time` datetime NULL DEFAULT NULL COMMENT '下架时间',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：未上架/已上架',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_resource_type`(`resource_type` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '德育资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of moral_resource
-- ----------------------------
INSERT INTO `moral_resource` VALUES (1, '思想道德修养与法律基础', '课程', '/resource/course/sixiu.mp4', 350, 85.50, '2025-09-01 08:00:00', NULL, '已上架', '思政必修课视频课程', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 10:00:00', '2025-09-01 08:00:00');
INSERT INTO `moral_resource` VALUES (2, '中华优秀传统文化读本', '图书', '/resource/book/chuantong.pdf', 280, 72.30, '2025-09-01 08:00:00', NULL, '已上架', '传统文化电子图书', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 10:00:00', '2025-09-01 08:00:00');
INSERT INTO `moral_resource` VALUES (3, '心理健康教育专题', '专题包', '/resource/topic/xinli/', 420, 90.20, '2025-09-01 08:00:00', NULL, '已上架', '心理健康教育资源包', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 10:00:00', '2025-09-01 08:00:00');
INSERT INTO `moral_resource` VALUES (4, '职业生涯规划指导', '课程', '/resource/course/zhiye.mp4', 310, 78.60, '2025-09-01 08:00:00', NULL, '已上架', '职业规划在线课程', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 10:00:00', '2025-09-01 08:00:00');
INSERT INTO `moral_resource` VALUES (5, '红色经典阅读书目', '图书', '/resource/book/hongse.pdf', 260, 68.90, '2025-09-01 08:00:00', NULL, '已上架', '红色经典电子书', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 10:00:00', '2025-09-01 08:00:00');
INSERT INTO `moral_resource` VALUES (6, '社会主义核心价值观学习', '专题包', '/resource/topic/jiazhiguan/', 450, 92.40, '2025-09-01 08:00:00', NULL, '已上架', '核心价值观专题学习', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 10:00:00', '2025-09-01 08:00:00');
INSERT INTO `moral_resource` VALUES (7, '创新创业教育课程', '课程', '/resource/course/chuangxin.mp4', 290, 75.80, '2025-09-01 08:00:00', NULL, '已上架', '创新创业在线课程', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 10:00:00', '2025-09-01 08:00:00');
INSERT INTO `moral_resource` VALUES (8, '安全教育知识手册', '图书', '/resource/book/anquan.pdf', 380, 88.50, '2025-09-01 08:00:00', NULL, '已上架', '校园安全电子手册', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 10:00:00', '2025-09-01 08:00:00');
INSERT INTO `moral_resource` VALUES (9, '礼仪修养培训教程', '课程', '/resource/course/liyi.mp4', 200, 65.30, '2025-10-01 08:00:00', NULL, '已上架', '礼仪培训课程', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-25 10:00:00', '2025-10-01 08:00:00');
INSERT INTO `moral_resource` VALUES (10, '古代文学鉴赏', '图书', '/resource/book/wenxue.pdf', 150, 58.20, '2025-08-01 08:00:00', '2025-12-31 23:59:59', '未上架', '已下架的文学资源', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-07-25 10:00:00', '2025-12-31 23:59:59');

-- ----------------------------
-- Table structure for new_push
-- ----------------------------
DROP TABLE IF EXISTS `new_push`;
CREATE TABLE `new_push`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `task_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '推送任务名称',
  `push_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '推送内容',
  `push_num` int NULL DEFAULT NULL COMMENT '推送人数',
  `push_time` datetime NULL DEFAULT NULL COMMENT '推送时间',
  `finish_rate` decimal(5, 2) NULL DEFAULT NULL COMMENT '推送完成率',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：未推送/已推送',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_push_time`(`push_time` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '迎新推送表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of new_push
-- ----------------------------
INSERT INTO `new_push` VALUES (1, '录取通知书发放通知', '恭喜您被我校录取！请登录系统查看录取通知书邮寄信息。', 500, '2025-07-15 10:00:00', 98.50, 'pushed', '第一批录取通知', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-07-15 09:00:00', '2026-04-15 21:54:15');
INSERT INTO `new_push` VALUES (2, '新生报到须知', '请于9月1日-3日到校报到，携带身份证、录取通知书等材料。', 500, '2025-08-01 09:00:00', 95.20, 'pushed', '报到注意事项', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-01 08:00:00', '2026-04-15 21:54:15');
INSERT INTO `new_push` VALUES (3, '入学准备清单', '请提前准备生活用品、学习用品，了解学校周边环境。', 500, '2025-08-10 10:00:00', 92.80, 'pushed', '入学准备指南', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-10 09:00:00', '2026-04-15 21:54:15');
INSERT INTO `new_push` VALUES (4, '军训安排通知', '新生军训将于9月5日-18日进行，请做好体能准备。', 500, '2025-08-20 14:00:00', 90.60, 'pushed', '军训时间安排', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-20 13:00:00', '2026-04-15 21:54:15');
INSERT INTO `new_push` VALUES (5, '学费缴纳提醒', '请于8月31日前完成学费缴纳，可通过网上支付或银行转账。', 500, '2025-08-15 09:00:00', 88.40, 'pushed', '缴费截止时间提醒', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-15 08:00:00', '2026-04-15 21:54:15');
INSERT INTO `new_push` VALUES (6, '宿舍分配结果通知', '您的宿舍已分配完毕，请登录系统查看宿舍号和床位号。', 500, '2025-08-25 10:00:00', 96.70, 'pushed', '宿舍信息查询', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-25 09:00:00', '2026-04-15 21:54:15');
INSERT INTO `new_push` VALUES (7, '开学典礼邀请', '诚邀您参加9月2日上午9点的开学典礼，地点：学校大礼堂。', 500, '2025-08-28 09:00:00', 94.30, 'pushed', '开学典礼通知', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-08-28 08:00:00', '2026-04-15 21:54:15');
INSERT INTO `new_push` VALUES (8, '选课系统使用指南', '选课系统将于9月10日开放，请提前熟悉操作流程。', 500, '2025-09-05 10:00:00', 87.50, 'pushed', '选课操作说明', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-05 09:00:00', '2026-04-15 21:54:15');
INSERT INTO `new_push` VALUES (9, '心理健康测评通知', '请新生在9月15日前完成心理健康测评，关注心理健康。', 500, '2025-09-08 14:00:00', 82.30, 'pushed', '心理测评提醒', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-08 13:00:00', '2026-04-15 21:54:15');
INSERT INTO `new_push` VALUES (10, '社团招新活动预告', '百团大战即将开始，欢迎加入感兴趣的社团！', 500, NULL, 0.00, 'unpushed', '计划9月下旬推送', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-10 09:00:00', '2026-04-15 21:54:15');

-- ----------------------------
-- Table structure for promote_mgmt
-- ----------------------------
DROP TABLE IF EXISTS `promote_mgmt`;
CREATE TABLE `promote_mgmt`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `task_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '宣传任务名称',
  `site` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '宣传站点',
  `promote_num` int NULL DEFAULT NULL COMMENT '宣传人数',
  `intent_num` int NULL DEFAULT NULL COMMENT '意向学生数',
  `execute_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行人',
  `execute_time` datetime NULL DEFAULT NULL COMMENT '执行时间',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：未执行/已执行',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_site`(`site` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '宣传管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of promote_mgmt
-- ----------------------------
INSERT INTO `promote_mgmt` VALUES (1, '重点中学宣讲会', '市第一中学', 500, 80, '招生组张老师', '2025-06-15 14:00:00', '已执行', '宣讲效果良好', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-06-10 09:00:00', '2025-06-15 14:00:00');
INSERT INTO `promote_mgmt` VALUES (2, '教育展参会宣传', '市会展中心', 1000, 150, '招生组李老师', '2025-06-20 09:00:00', '已执行', '参展人数众多', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-06-15 09:00:00', '2025-06-20 09:00:00');
INSERT INTO `promote_mgmt` VALUES (3, '网络平台线上宣传', '抖音/微信', 5000, 300, '宣传组王老师', '2025-06-25 10:00:00', '已执行', '线上咨询量大', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-06-20 09:00:00', '2025-06-25 10:00:00');
INSERT INTO `promote_mgmt` VALUES (4, '社区招生咨询点', '阳光社区', 200, 35, '招生组赵老师', '2025-07-01 09:00:00', '已执行', '社区居民反响好', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-06-25 09:00:00', '2025-07-01 09:00:00');
INSERT INTO `promote_mgmt` VALUES (5, '初中校长座谈会', '教育局会议室', 50, 20, '招生办主任', '2025-07-05 15:00:00', '已执行', '建立合作关系', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-07-01 09:00:00', '2025-07-05 15:00:00');
INSERT INTO `promote_mgmt` VALUES (6, '校园开放日活动', '本校校区', 800, 120, '宣传组刘老师', '2025-07-10 09:00:00', '已执行', '家长学生参观热情高', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-07-05 09:00:00', '2025-07-10 09:00:00');
INSERT INTO `promote_mgmt` VALUES (7, '电视台专访宣传', '市电视台', 3000, 200, '宣传组陈老师', '2025-07-15 19:00:00', '已执行', '电视报道效果好', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-07-10 09:00:00', '2025-07-15 19:00:00');
INSERT INTO `promote_mgmt` VALUES (8, '乡镇中学走访', '周边乡镇', 300, 50, '招生组杨老师', '2025-07-20 10:00:00', '已执行', '覆盖农村地区', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-07-15 09:00:00', '2025-07-20 10:00:00');
INSERT INTO `promote_mgmt` VALUES (9, '高考志愿填报指导会', '市图书馆', 600, 100, '招生组周老师', NULL, '未执行', '计划8月举办', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-07-20 09:00:00', '2025-07-20 09:00:00');
INSERT INTO `promote_mgmt` VALUES (10, '校友推荐宣传活动', '各地校友会', 400, 60, '校友办吴老师', NULL, '未执行', '筹备中', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-07-25 09:00:00', '2025-07-25 09:00:00');

-- ----------------------------
-- Table structure for register_mgmt
-- ----------------------------
DROP TABLE IF EXISTS `register_mgmt`;
CREATE TABLE `register_mgmt`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `student_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生姓名',
  `id_card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份证号',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `major` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '意向专业',
  `apply_time` datetime NOT NULL COMMENT '报名时间',
  `audit_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
  `confirm_time` datetime NULL DEFAULT NULL COMMENT '录取确认时间',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：待审核/已录取',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_id_card`(`id_card` ASC) USING BTREE,
  INDEX `idx_major`(`major` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '报名管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of register_mgmt
-- ----------------------------
INSERT INTO `register_mgmt` VALUES (1, '张三', '110101200801011234', '13800138001', '软件工程', '2025-07-01 09:00:00', '招生办张老师', '2025-07-02 10:00:00', '2025-07-05 14:00:00', 'admitted', '中考成绩优秀，符合录取条件', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-07-01 09:00:00', '2026-04-15 21:58:04');
INSERT INTO `register_mgmt` VALUES (2, '李四', '110101200802022345', '13800138002', '计算机科学', '2025-07-01 10:30:00', '招生办李老师', '2025-07-02 11:00:00', '2025-07-06 09:00:00', 'admitted', '特长生，优先录取', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-07-01 10:30:00', '2026-04-15 21:58:04');
INSERT INTO `register_mgmt` VALUES (3, '王五', '110101200803033456', '13800138003', '网络工程', '2025-07-02 08:00:00', '招生办王老师', '2025-07-03 09:00:00', '2025-07-07 10:00:00', 'admitted', '成绩达标，已确认录取', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-07-02 08:00:00', '2026-04-15 21:58:04');
INSERT INTO `register_mgmt` VALUES (4, '赵六', '110101200804044567', '13800138004', '信息安全', '2025-07-02 14:00:00', '招生办赵老师', '2025-07-03 15:00:00', '2025-07-08 11:00:00', 'admitted', '面试表现优秀', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-07-02 14:00:00', '2026-04-15 21:58:04');
INSERT INTO `register_mgmt` VALUES (5, '孙七', '110101200805055678', '13800138005', '软件工程', '2025-07-03 09:30:00', '招生办孙老师', '2025-07-04 10:00:00', NULL, 'pending', '资料待核实', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-07-03 09:30:00', '2026-04-15 21:58:04');
INSERT INTO `register_mgmt` VALUES (6, '周八', '110101200806066789', '13800138006', '计算机科学', '2025-07-03 11:00:00', '招生办周老师', '2025-07-04 14:00:00', '2025-07-09 15:00:00', 'admitted', '符合录取标准', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-07-03 11:00:00', '2026-04-15 21:58:04');
INSERT INTO `register_mgmt` VALUES (7, '吴九', '110101200807077890', '13800138007', '网络工程', '2025-07-04 08:30:00', '招生办吴老师', NULL, NULL, 'pending', '等待审核中', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-07-04 08:30:00', '2026-04-15 21:58:04');
INSERT INTO `register_mgmt` VALUES (8, '郑十', '110101200808088901', '13800138008', '信息安全', '2025-07-04 15:00:00', '招生办郑老师', '2025-07-05 10:00:00', '2025-07-10 09:00:00', 'admitted', '综合素质优秀', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-07-04 15:00:00', '2026-04-15 21:58:04');
INSERT INTO `register_mgmt` VALUES (9, '陈十一', '110101200809099012', '13800138009', '软件工程', '2025-07-05 09:00:00', '招生办陈老师', '2025-07-06 10:00:00', NULL, 'pending', '需要补充材料', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-07-05 09:00:00', '2026-04-15 21:58:04');

-- ----------------------------
-- Table structure for repair_mgmt
-- ----------------------------
DROP TABLE IF EXISTS `repair_mgmt`;
CREATE TABLE `repair_mgmt`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `dorm_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '宿舍号',
  `repair_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '报修类型：水电/家具/其他',
  `apply_time` datetime NOT NULL COMMENT '申请时间',
  `dispatch_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '派单人',
  `dispatch_time` datetime NULL DEFAULT NULL COMMENT '派单时间',
  `repair_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '维修人',
  `feedback_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '维修反馈',
  `feedback_time` datetime NULL DEFAULT NULL COMMENT '反馈时间',
  `check_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '验收人',
  `check_time` datetime NULL DEFAULT NULL COMMENT '验收时间',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：待派单/维修中/已维修',
  `check_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '验收状态：未验收/已验收',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_dorm_num`(`dorm_num` ASC) USING BTREE,
  INDEX `idx_repair_type`(`repair_type` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_check_status`(`check_status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '报修管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repair_mgmt
-- ----------------------------
INSERT INTO `repair_mgmt` VALUES (1, 'A栋101', 'water_electricity', '2025-10-08 09:00:00', '后勤张主任', '2025-10-08 09:30:00', '维修工李师傅', '水龙头已更换，恢复正常使用', '2025-10-08 14:00:00', '宿管王老师', '2025-10-08 15:00:00', '已维修', '已验收', '水龙头漏水', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-08 09:00:00', '2026-04-15 22:00:16');
INSERT INTO `repair_mgmt` VALUES (2, 'A栋102', 'furniture', '2025-10-09 10:30:00', '后勤张主任', '2025-10-09 11:00:00', '维修工王师傅', '床板已加固，可以正常使用', '2025-10-09 16:00:00', '宿管李老师', '2025-10-09 17:00:00', '已维修', '已验收', '床板松动', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-09 10:30:00', '2026-04-15 22:00:16');
INSERT INTO `repair_mgmt` VALUES (3, 'A栋201', 'water_electricity', '2025-10-10 08:00:00', '后勤张主任', '2025-10-10 08:30:00', '维修工赵师傅', '电路故障已修复，照明正常', '2025-10-10 12:00:00', '宿管赵老师', '2025-10-10 13:00:00', '已维修', '已验收', '灯管不亮', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-10 08:00:00', '2026-04-15 22:00:16');
INSERT INTO `repair_mgmt` VALUES (4, 'A栋202', 'other', '2025-10-11 14:00:00', '后勤李副主任', '2025-10-11 14:30:00', '维修工刘师傅', '门锁已更换，钥匙已交付', '2025-10-11 17:00:00', '宿管刘老师', '2025-10-11 18:00:00', '已维修', '已验收', '门锁损坏', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-11 14:00:00', '2026-04-15 22:00:16');
INSERT INTO `repair_mgmt` VALUES (5, 'B栋101', 'water_electricity', '2025-10-12 09:30:00', '后勤张主任', '2025-10-12 10:00:00', '维修工陈师傅', '水管破裂已修复', '2025-10-12 15:00:00', '宿管陈老师', NULL, '维修中', '未验收', '水管爆裂', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-12 09:30:00', '2026-04-15 22:00:16');
INSERT INTO `repair_mgmt` VALUES (6, 'B栋102', 'furniture', '2025-10-13 11:00:00', '后勤李副主任', NULL, NULL, NULL, NULL, NULL, NULL, '待派单', '未验收', '衣柜门脱落', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-13 11:00:00', '2026-04-15 22:00:16');
INSERT INTO `repair_mgmt` VALUES (7, 'B栋201', 'water_electricity', '2025-10-14 08:30:00', '后勤张主任', '2025-10-14 09:00:00', '维修工杨师傅', '插座已更换，通电正常', '2025-10-14 13:00:00', '宿管杨老师', '2025-10-14 14:00:00', '已维修', '已验收', '插座损坏', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-14 08:30:00', '2026-04-15 22:00:16');
INSERT INTO `repair_mgmt` VALUES (8, 'B栋202', 'other', '2025-10-15 10:00:00', '后勤李副主任', '2025-10-15 10:30:00', '维修工周师傅', '窗户玻璃已更换', '2025-10-15 16:00:00', '宿管周老师', '2025-10-15 17:00:00', '已维修', '已验收', '玻璃破碎', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-15 10:00:00', '2026-04-15 22:00:16');
INSERT INTO `repair_mgmt` VALUES (9, 'A栋301', 'furniture', '2025-10-16 13:00:00', '后勤张主任', '2025-10-16 13:30:00', '维修工吴师傅', NULL, NULL, NULL, NULL, '维修中', '未验收', '桌椅损坏待修', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-16 13:00:00', '2026-04-15 22:00:16');
INSERT INTO `repair_mgmt` VALUES (10, 'A栋302', 'water_electricity', '2025-10-17 09:00:00', '后勤李副主任', NULL, NULL, NULL, NULL, NULL, NULL, '待派单', '未验收', '卫生间漏水', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-17 09:00:00', '2026-04-15 22:00:16');

-- ----------------------------
-- Table structure for stay_mgmt
-- ----------------------------
DROP TABLE IF EXISTS `stay_mgmt`;
CREATE TABLE `stay_mgmt`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `student_id` bigint NOT NULL COMMENT '学生 ID',
  `stay_date` date NOT NULL COMMENT '留宿日期',
  `stay_reason` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '留宿原因',
  `apply_time` datetime NOT NULL COMMENT '申请时间',
  `parent_confirm_time` datetime NULL DEFAULT NULL COMMENT '家长确认时间',
  `audit_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：待确认/待审核/已通过',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_student_id`(`student_id` ASC) USING BTREE,
  INDEX `idx_stay_date`(`stay_date` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '留宿管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stay_mgmt
-- ----------------------------
INSERT INTO `stay_mgmt` VALUES (1, 1, '2025-10-01', '国庆节假期留校学习', '2025-09-28 10:00:00', '2025-09-28 14:00:00', '辅导员张老师', '2025-09-28 16:00:00', 'approved', '国庆留校备考', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-28 10:00:00', '2026-04-15 22:01:02');
INSERT INTO `stay_mgmt` VALUES (2, 2, '2025-10-01', '参加学校组织的活动', '2025-09-29 09:00:00', '2025-09-29 11:00:00', '辅导员李老师', '2025-09-29 14:00:00', 'approved', '志愿活动留校', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-29 09:00:00', '2026-04-15 22:01:02');
INSERT INTO `stay_mgmt` VALUES (3, 3, '2025-10-02', '实验室项目研究', '2025-09-30 08:30:00', '2025-09-30 10:00:00', '辅导员王老师', '2025-09-30 15:00:00', 'approved', '科研项目需要', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-30 08:30:00', '2026-04-15 22:01:02');
INSERT INTO `stay_mgmt` VALUES (4, 4, '2025-10-03', '准备考试复习', '2025-10-01 09:00:00', '2025-10-01 10:30:00', '辅导员赵老师', '2025-10-01 14:00:00', 'approved', '期末复习留校', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-01 09:00:00', '2026-04-15 22:01:02');
INSERT INTO `stay_mgmt` VALUES (5, 5, '2025-10-04', '参加校内培训', '2025-10-02 10:00:00', '2025-10-02 11:30:00', '辅导员刘老师', '2025-10-02 15:00:00', 'approved', '技能培训留校', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-02 10:00:00', '2026-04-15 22:01:02');
INSERT INTO `stay_mgmt` VALUES (6, 6, '2025-10-05', '家远不便往返', '2025-10-03 08:00:00', NULL, '辅导员陈老师', NULL, 'pending_confirm', '等待家长确认', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-03 08:00:00', '2026-04-15 22:01:02');
INSERT INTO `stay_mgmt` VALUES (7, 7, '2025-10-06', '实习工作需要', '2025-10-04 09:30:00', '2025-10-04 11:00:00', '辅导员杨老师', '2025-10-04 15:00:00', 'approved', '实习留校', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-04 09:30:00', '2026-04-15 22:01:02');
INSERT INTO `stay_mgmt` VALUES (8, 8, '2025-10-07', '参加社团活动', '2025-10-05 10:00:00', '2025-10-05 11:30:00', '辅导员周老师', NULL, 'pending_audit', '等待辅导员审核', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-05 10:00:00', '2026-04-15 22:01:02');
INSERT INTO `stay_mgmt` VALUES (9, 1, '2025-11-01', '周末留校自习', '2025-10-30 09:00:00', '2025-10-30 10:00:00', '辅导员张老师', '2025-10-30 14:00:00', 'approved', '周末学习', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-30 09:00:00', '2026-04-15 22:01:02');
INSERT INTO `stay_mgmt` VALUES (10, 2, '2025-11-02', '图书馆查阅资料', '2025-10-31 08:30:00', NULL, NULL, NULL, 'pending_confirm', '等待家长确认', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-31 08:30:00', '2026-04-15 22:01:02');

-- ----------------------------
-- Table structure for student_info
-- ----------------------------
DROP TABLE IF EXISTS `student_info`;
CREATE TABLE `student_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `student_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学号',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `id_card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份证号',
  `photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生照片地址',
  `education_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学历层次：中专/大专/本科/研究生',
  `study_form` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学习形式：全日制/非全日制/函授',
  `major` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '专业',
  `grade` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '年级',
  `class_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '班级',
  `student_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生类型：普通生/特长生/转学生',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学籍状态：在籍/休学/退学/异动',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `parent_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '家长联系电话',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_student_no`(`student_no` ASC) USING BTREE,
  UNIQUE INDEX `uk_id_card`(`id_card` ASC) USING BTREE,
  INDEX `idx_class_name`(`class_name` ASC) USING BTREE,
  INDEX `idx_major`(`major` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18140 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_info
-- ----------------------------
INSERT INTO `student_info` VALUES (1, '2024001', '张三', '110101200501011234', 'https://example.com/photo1.jpg', '2', '1', '软件工程', '2024', '软件2401班', '1', '1', '13800138001', '13900139001', '优秀学生', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-01 08:00:00', '2026-04-15 20:27:02');
INSERT INTO `student_info` VALUES (2, '2024002', '李四', '110101200502021234', 'https://example.com/photo2.jpg', '2', '1', '计算机应用', '2024', '计算机2401班', '2', '1', '13800138002', '13900139002', '体育特长', NULL, NULL, 'admin', '1', b'0', 1, '2024-09-01 08:30:00', '2026-04-15 20:27:02');
INSERT INTO `student_info` VALUES (3, '2024003', '王五', '110101200503031234', 'https://example.com/photo3.jpg', '3', '1', '电子信息', '2024', '电子2401班', '1', '1', '13800138003', '13900139003', NULL, NULL, NULL, 'admin', '1', b'0', 1, '2024-09-01 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `student_info` VALUES (4, '2024004', '赵六', '110101200504041234', 'https://example.com/photo4.jpg', '2', '2', '工商管理', '2024', '工商2401班', '3', '2', '13800138004', '13900139004', '因病休学', NULL, NULL, 'admin', '1', b'0', 1, '2024-09-01 09:30:00', '2026-04-15 20:27:02');
INSERT INTO `student_info` VALUES (5, '2024005', '孙七', '110101200505051234', 'https://example.com/photo5.jpg', '1', '1', '机械制造', '2024', '机械2401班', '1', '1', '13800138005', '13900139005', NULL, NULL, NULL, 'admin', '1', b'0', 1, '2024-09-01 10:00:00', '2026-04-15 20:27:02');
INSERT INTO `student_info` VALUES (6, '2024006', '周八', '110101200506061234', 'https://example.com/photo6.jpg', '2', '1', '会计', '2024', '会计2401班', '1', '1', '13800138006', '13900139006', NULL, NULL, NULL, 'admin', '1', b'0', 1, '2024-09-01 10:30:00', '2026-04-15 20:27:02');
INSERT INTO `student_info` VALUES (7, '2024007', '吴九', '110101200507071234', 'https://example.com/photo7.jpg', '3', '1', '土木工程', '2024', '土木2401班', '2', '1', '13800138007', '13900139007', '艺术特长', NULL, NULL, 'admin', '1', b'0', 1, '2024-09-01 11:00:00', '2026-04-15 20:27:02');
INSERT INTO `student_info` VALUES (8, '2024008', '郑十', '110101200508081234', 'https://example.com/photo8.jpg', '2', '3', '市场营销', '2024', '营销2401班', '1', '3', '13800138008', '13900139008', '个人原因退学', NULL, NULL, 'admin', '1', b'0', 1, '2024-09-01 11:30:00', '2026-04-15 20:27:02');
INSERT INTO `student_info` VALUES (9, '2024009', '陈十一', '110101200509091234', 'https://example.com/photo9.jpg', '4', '1', '人工智能', '2024', 'AI2401班', '1', '1', '13800138009', '13900139009', NULL, NULL, NULL, 'admin', '1', b'0', 1, '2024-09-01 12:00:00', '2026-04-15 20:27:02');
INSERT INTO `student_info` VALUES (10, '2024010', '林壵二', '110101200510101234', 'https://example.com/photo10.jpg', '2', '1', '护理', '2024', '护理2401班', '3', '4', '13800138012', '13900138810', '从外校转入', NULL, NULL, 'admin', '1', b'0', 1, '2024-09-01 12:30:00', '2026-04-15 20:27:02');
INSERT INTO `student_info` VALUES (18139, '2024103', '芋艿', '420222199901010001', '', '2', '2', '软件工程', '2024级', '软件工程01班', '1', '2', '', '', '你猜', '', '', '1', '1', b'0', 1, '2026-04-14 10:34:51', '2026-04-14 10:34:51');

-- ----------------------------
-- Table structure for study_up
-- ----------------------------
DROP TABLE IF EXISTS `study_up`;
CREATE TABLE `study_up`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `student_id` bigint NOT NULL COMMENT '学生 ID',
  `school_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '目标院校名称',
  `school_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '院校类型：公办/民办',
  `major` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '意向专业',
  `plan_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '升学规划内容',
  `plan_time` datetime NULL DEFAULT NULL COMMENT '规划时间',
  `record_time` datetime NULL DEFAULT NULL COMMENT '跟踪记录时间',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：待规划/已规划',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_student_id`(`student_id` ASC) USING BTREE,
  INDEX `idx_school_name`(`school_name` ASC) USING BTREE,
  INDEX `idx_school_type`(`school_type` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '升学管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of study_up
-- ----------------------------
INSERT INTO `study_up` VALUES (1, 1, '清华大学', 'public', '计算机科学与技术', '制定考研复习计划，重点突破数学和英语，参加专业课辅导', '2025-09-15 10:00:00', '2025-10-20 14:00:00', 'planned', '目标明确，学习刻苦', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-15 09:00:00', '2026-04-15 22:03:19');
INSERT INTO `study_up` VALUES (2, 2, '北京大学', 'public', '软件工程', '参加考研培训班，系统复习四门课程，每月模拟考试', '2025-09-16 10:00:00', '2025-10-21 15:00:00', 'planned', '基础扎实，潜力较大', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-16 09:00:00', '2026-04-15 22:03:19');
INSERT INTO `study_up` VALUES (3, 3, '浙江大学', 'public', '网络工程', '强化专业课学习，参与科研项目，提升实践能力', '2025-09-17 10:00:00', '2025-10-22 16:00:00', 'planned', '科研能力强', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-17 09:00:00', '2026-04-15 22:03:19');
INSERT INTO `study_up` VALUES (4, 4, '复旦大学', 'public', '信息安全', '制定详细复习时间表，参加线上课程，定期向导师汇报', '2025-09-18 10:00:00', '2025-10-23 14:00:00', 'planned', '学习态度端正', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-18 09:00:00', '2026-04-15 22:03:19');
INSERT INTO `study_up` VALUES (5, 5, '上海交通大学', 'public', '人工智能', '重点学习机器学习算法，参加竞赛积累经验', '2025-09-19 10:00:00', '2025-10-24 15:00:00', 'planned', '创新能力突出', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-19 09:00:00', '2026-04-15 22:03:19');
INSERT INTO `study_up` VALUES (6, 6, '南京大学', 'public', '数据科学', '加强统计学基础，学习Python数据分析，准备复试', '2025-09-20 10:00:00', NULL, 'pending_plan', '需要进一步指导', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-20 09:00:00', '2026-04-15 22:03:19');
INSERT INTO `study_up` VALUES (7, 7, '中国科学技术大学', 'public', '计算机科学', '参加暑期夏令营，联系导师，准备推免材料', '2025-09-21 10:00:00', '2025-10-25 16:00:00', 'planned', '成绩优异，有望推免', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-21 09:00:00', '2026-04-15 22:03:19');
INSERT INTO `study_up` VALUES (8, 8, '哈佛大学', 'private', '计算机科学', '准备托福雅思考试，整理申请材料，联系推荐信', '2025-09-22 10:00:00', '2025-10-26 14:00:00', 'planned', '出国留学意向', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-22 09:00:00', '2026-04-15 22:03:19');
INSERT INTO `study_up` VALUES (9, 9, '斯坦福大学', 'private', '软件工程', '提升英语水平，准备GRE考试，研究申请流程', '2025-09-23 10:00:00', NULL, 'pending_plan', '正在咨询留学机构', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-23 09:00:00', '2026-04-15 22:03:19');
INSERT INTO `study_up` VALUES (10, 10, '华中科技大学', 'public', '网络空间安全', '系统复习专业知识，参加学术讲座，撰写研究计划', '2025-09-24 10:00:00', '2025-10-27 15:00:00', 'planned', '专业兴趣浓厚', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-24 09:00:00', '2026-04-15 22:03:19');

-- ----------------------------
-- Table structure for target_mgmt
-- ----------------------------
DROP TABLE IF EXISTS `target_mgmt`;
CREATE TABLE `target_mgmt`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `target_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '指标名称',
  `total_score` decimal(5, 2) NOT NULL COMMENT '指标总分',
  `warn_threshold` decimal(5, 2) NULL DEFAULT NULL COMMENT '预警阈值',
  `evaluator_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评价人类型：教职工/家长/领导',
  `score_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '计分方式：累计赋分/接口赋分',
  `enable_time` datetime NULL DEFAULT NULL COMMENT '启用时间',
  `disable_time` datetime NULL DEFAULT NULL COMMENT '停用时间',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：未启用/已启用',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '指标管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of target_mgmt
-- ----------------------------
INSERT INTO `target_mgmt` VALUES (1, '课堂表现', 100.00, 60.00, 'teacher', '累计赋分', '2025-09-01 08:00:00', NULL, '已启用', '学生课堂参与度评价', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-01 08:00:00', '2026-04-15 22:05:15');
INSERT INTO `target_mgmt` VALUES (2, '作业完成', 100.00, 70.00, 'teacher', '累计赋分', '2025-09-01 08:00:00', NULL, '已启用', '作业按时完成情况', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-01 08:00:00', '2026-04-15 22:05:15');
INSERT INTO `target_mgmt` VALUES (3, '思想品德', 100.00, 80.00, 'parent', '累计赋分', '2025-09-01 08:00:00', NULL, '已启用', '思想品德综合评价', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-01 08:00:00', '2026-04-15 22:05:15');
INSERT INTO `target_mgmt` VALUES (4, '社会实践', 100.00, 60.00, 'leader', '接口赋分', '2025-09-01 08:00:00', NULL, '已启用', '社会实践活动参与', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-01 08:00:00', '2026-04-15 22:05:15');
INSERT INTO `target_mgmt` VALUES (5, '体育锻炼', 100.00, 70.00, 'teacher', '累计赋分', '2025-09-01 08:00:00', NULL, '已启用', '体育活动参与情况', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-01 08:00:00', '2026-04-15 22:05:15');
INSERT INTO `target_mgmt` VALUES (6, '艺术素养', 100.00, 60.00, 'teacher', '累计赋分', '2025-09-01 08:00:00', NULL, '已启用', '艺术课程表现', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-01 08:00:00', '2026-04-15 22:05:15');
INSERT INTO `target_mgmt` VALUES (7, '劳动实践', 100.00, 70.00, 'parent', '累计赋分', '2025-09-01 08:00:00', NULL, '已启用', '劳动实践活动', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-01 08:00:00', '2026-04-15 22:05:15');
INSERT INTO `target_mgmt` VALUES (8, '科技创新', 100.00, 60.00, 'leader', '接口赋分', '2025-09-01 08:00:00', NULL, '已启用', '科技创新项目参与', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-01 08:00:00', '2026-04-15 22:05:15');
INSERT INTO `target_mgmt` VALUES (9, '志愿服务', 100.00, 70.00, 'teacher', '累计赋分', '2025-09-01 08:00:00', NULL, '已启用', '志愿服务活动时长', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-01 08:00:00', '2026-04-15 22:05:15');
INSERT INTO `target_mgmt` VALUES (10, '综合素质', 100.00, 75.00, 'leader', '累计赋分', '2025-09-01 08:00:00', NULL, '已启用', '综合素质评价总分', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-09-01 08:00:00', '2026-04-15 22:05:15');

-- ----------------------------
-- Table structure for treat_mgmt
-- ----------------------------
DROP TABLE IF EXISTS `treat_mgmt`;
CREATE TABLE `treat_mgmt`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `student_id` bigint NOT NULL COMMENT '学生 ID',
  `treat_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '就诊类型：门诊/急诊/其他',
  `symptom` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '症状描述',
  `register_time` datetime NOT NULL COMMENT '就诊登记时间',
  `treat_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '就诊内容',
  `apply_time` datetime NULL DEFAULT NULL COMMENT '预约时间',
  `audit_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
  `feedback_time` datetime NULL DEFAULT NULL COMMENT '家长反馈时间',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：待审核/已就诊',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_student_id`(`student_id` ASC) USING BTREE,
  INDEX `idx_treat_type`(`treat_type` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '就诊管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of treat_mgmt
-- ----------------------------
INSERT INTO `treat_mgmt` VALUES (1, 1, 'outpatient', '感冒发烧，体温38.5度', '2025-10-08 09:00:00', '诊断为普通感冒，开具退烧药和感冒药，建议休息多喝水', '2025-10-08 08:30:00', '校医张医生', '2025-10-08 08:45:00', '2025-10-08 20:00:00', 'visited', '家长已反馈学生情况好转', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-08 08:30:00', '2026-04-15 22:12:05');
INSERT INTO `treat_mgmt` VALUES (2, 2, 'emergency', '腹痛剧烈，疑似急性肠胃炎', '2025-10-09 14:30:00', '急诊处理，诊断为急性肠胃炎，输液治疗，观察2小时', '2025-10-09 14:00:00', '校医李医生', '2025-10-09 14:10:00', '2025-10-09 22:00:00', 'visited', '病情已稳定，家长已知晓', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-09 14:00:00', '2026-04-15 22:12:05');
INSERT INTO `treat_mgmt` VALUES (3, 3, 'outpatient', '咳嗽咳痰一周，伴有胸痛', '2025-10-10 10:00:00', '听诊检查，建议拍胸片，诊断为支气管炎，开具抗生素', '2025-10-10 09:30:00', '校医王医生', '2025-10-10 09:45:00', '2025-10-10 19:00:00', 'visited', '按时服药，注意休息', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-10 09:30:00', '2026-04-15 22:12:05');
INSERT INTO `treat_mgmt` VALUES (4, 4, 'outpatient', '脚踝扭伤，肿胀疼痛', '2025-10-11 15:00:00', 'X光检查无骨折，软组织损伤，冰敷处理，开具消炎止痛药', '2025-10-11 14:30:00', '校医赵医生', '2025-10-11 14:45:00', '2025-10-11 21:00:00', 'visited', '建议休息一周，避免剧烈运动', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-11 14:30:00', '2026-04-15 22:12:05');
INSERT INTO `treat_mgmt` VALUES (5, 5, 'other', '皮肤过敏，出现红疹瘙痒', '2025-10-12 11:00:00', '诊断为过敏性皮炎，开具抗过敏药物和外用药膏', '2025-10-12 10:30:00', '校医刘医生', '2025-10-12 10:45:00', '2025-10-12 20:00:00', 'visited', '注意饮食，避免过敏源', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-12 10:30:00', '2026-04-15 22:12:05');
INSERT INTO `treat_mgmt` VALUES (6, 6, 'outpatient', '头痛头晕，视力模糊', '2025-10-13 09:30:00', '测量血压正常，建议眼科检查，开具缓解症状药物', '2025-10-13 09:00:00', '校医陈医生', '2025-10-13 09:15:00', NULL, 'pending', '等待家长反馈', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-13 09:00:00', '2026-04-15 22:09:02');
INSERT INTO `treat_mgmt` VALUES (7, 7, 'emergency', '高热不退，体温39.5度', '2025-10-14 22:00:00', '急诊降温处理，血常规检查，诊断为流感，输液治疗', '2025-10-14 21:30:00', '校医杨医生', '2025-10-14 21:40:00', '2025-10-15 08:00:00', 'visited', '夜间急诊，已通知家长', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-14 21:30:00', '2026-04-15 22:12:05');
INSERT INTO `treat_mgmt` VALUES (8, 8, 'outpatient', '牙痛严重，牙龈肿胀', '2025-10-15 14:00:00', '检查发现龋齿，建议到口腔医院治疗，开具止痛药', '2025-10-15 13:30:00', '校医周医生', '2025-10-15 13:45:00', '2025-10-15 20:00:00', 'visited', '建议尽快到专科医院治疗', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-15 13:30:00', '2026-04-15 22:12:05');
INSERT INTO `treat_mgmt` VALUES (9, 1, 'outpatient', '胃部不适，恶心呕吐', '2025-10-16 10:30:00', '诊断为胃炎，开具胃药，建议清淡饮食', '2025-10-16 10:00:00', '校医吴医生', '2025-10-16 10:15:00', '2025-10-16 19:00:00', 'visited', '注意饮食规律', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-16 10:00:00', '2026-04-15 22:12:05');
INSERT INTO `treat_mgmt` VALUES (10, 2, 'other', '失眠多梦，精神状态差', '2025-10-17 15:00:00', '心理咨询，诊断为轻度焦虑，建议心理辅导和适当运动', '2025-10-17 14:30:00', '校医郑医生', '2025-10-17 14:45:00', NULL, 'pending', '建议持续关注心理状态', NULL, NULL, 'admin', 'admin', b'0', 1, '2025-10-17 14:30:00', '2026-04-15 22:09:02');

-- ----------------------------
-- Table structure for violate_mgmt
-- ----------------------------
DROP TABLE IF EXISTS `violate_mgmt`;
CREATE TABLE `violate_mgmt`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `student_id` bigint NOT NULL COMMENT '学生 ID',
  `violate_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '违纪类型：1:仪容仪表/2:行为违规/3:其他',
  `punish_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '处分类型：1:警告/2:记过/3:留校察看/4:开除',
  `violate_time` datetime NOT NULL COMMENT '违纪时间',
  `violate_reason` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '违纪原因',
  `audit_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审批人',
  `audit_time` datetime NULL DEFAULT NULL COMMENT '审批时间',
  `push_time` datetime NULL DEFAULT NULL COMMENT '家长推送时间',
  `warn_time` datetime NULL DEFAULT NULL COMMENT '预警时间',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态：1:待审批/2:已执行/3:已预警',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `reserve1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 1',
  `reserve2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用字段 2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人账号/姓名',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人账号/姓名',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识 0-未删除 1-已删除',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户 ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_student_id`(`student_id` ASC) USING BTREE,
  INDEX `idx_violate_type`(`violate_type` ASC) USING BTREE,
  INDEX `idx_punish_type`(`punish_type` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '违纪管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of violate_mgmt
-- ----------------------------
INSERT INTO `violate_mgmt` VALUES (1, 4, 'behavior', 'warn', '2024-10-10 08:30:00', '上课迟到', '张老师', '2024-10-10 14:00:00', '2024-10-10 15:00:00', NULL, 'executed', '初次违纪', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-10-10 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `violate_mgmt` VALUES (2, 8, 'appearance', 'warn', '2024-11-05 09:00:00', '着装不规范', '李老师', '2024-11-05 14:00:00', '2026-04-14 11:33:38', '2026-04-14 11:36:12', 'warn', '已整改', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-05 09:30:00', '2026-04-15 20:27:02');
INSERT INTO `violate_mgmt` VALUES (3, 2, 'behavior', 'demerit', '2024-09-15 14:00:00', '课堂扰乱秩序', '王老师', '2024-09-15 16:00:00', '2024-09-15 17:00:00', '2024-09-20 10:00:00', 'warned', '多次违纪', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-15 14:30:00', '2026-04-15 20:27:02');
INSERT INTO `violate_mgmt` VALUES (4, 6, 'other', 'warn', '2024-12-01 10:00:00', '宿舍违规使用电器', '赵老师', '2024-12-01 14:00:00', '2024-12-01 15:00:00', NULL, 'executed', '安全隐患', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-12-01 10:30:00', '2026-04-15 20:27:02');
INSERT INTO `violate_mgmt` VALUES (5, 10, 'behavior', 'warn', '2024-11-20 08:00:00', '旷课', '1', '2026-04-11 17:27:30', '2024-11-20 15:00:00', NULL, '3', '无故缺勤', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-20 08:30:00', '2026-04-15 20:27:02');
INSERT INTO `violate_mgmt` VALUES (6, 1, 'appearance', 'warn', '2024-10-25 09:00:00', '发型不符合要求', '1', '2026-04-11 17:27:30', '2024-10-25 15:00:00', NULL, '3', '已整改', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-10-25 09:30:00', '2026-04-15 20:27:02');
INSERT INTO `violate_mgmt` VALUES (7, 2, 'behavior', 'demerit', '2024-12-10 15:00:00', '打架斗殴', '1', '2026-04-11 17:27:30', '2024-12-10 17:00:00', '2024-12-15 10:00:00', '3', '情节严重', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-12-10 15:30:00', '2026-04-15 20:27:02');
INSERT INTO `violate_mgmt` VALUES (8, 3, 'other', 'warn', '2024-11-15 11:00:00', '损坏公物', '郑老师', '2024-11-15 14:00:00', '2024-11-15 15:00:00', NULL, 'executed', '已赔偿', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-11-15 11:30:00', '2026-04-15 20:27:02');
INSERT INTO `violate_mgmt` VALUES (9, 7, 'appearance', 'warn', '2024-09-20 08:30:00', '佩戴首饰', '1', '2026-04-11 17:26:51', '2024-09-20 15:00:00', NULL, '3', '已整改', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-09-20 09:00:00', '2026-04-15 20:27:02');
INSERT INTO `violate_mgmt` VALUES (10, 9, 'behavior', 'probation', '2024-12-05 16:00:00', '考试作弊', '1', '2026-04-11 17:26:51', '2024-12-05 18:00:00', '2024-12-10 10:00:00', '3', '严重违纪', NULL, NULL, 'admin', 'admin', b'0', 1, '2024-12-05 16:30:00', '2026-04-15 20:27:02');

SET FOREIGN_KEY_CHECKS = 1;
