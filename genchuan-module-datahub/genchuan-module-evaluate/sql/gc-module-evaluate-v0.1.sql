/*
 Navicat Premium Data Transfer

 Source Server         : 上机实验
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : gc-module-evaluate

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 26/02/2026 09:51:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for appeal_review
-- ----------------------------
DROP TABLE IF EXISTS `appeal_review`;
CREATE TABLE `appeal_review`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `appeal_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '申诉复核ID（UUID）',
  `appeal_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '申诉编号',
  `publicity_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联公示ID（关联result_publicity.publicity_id）',
  `object_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '申诉对象ID（关联eval_object.object_id）',
  `appeal_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '申诉人ID（关联sys_user.user_id）',
  `appeal_reason` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '申诉理由',
  `proof_materials` json NULL COMMENT '证明材料（JSON）',
  `reviewer_ids` json NULL COMMENT '复核人员ID集合（JSON）',
  `submit_time` datetime(0) NULL DEFAULT NULL COMMENT '提交时间',
  `accept_time` datetime(0) NULL DEFAULT NULL COMMENT '受理时间',
  `review_opinion` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '复核意见',
  `final_result_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '最终结果ID（关联sys_review_result.result_id）',
  `corrected_score` decimal(8, 2) NULL DEFAULT NULL COMMENT '修正后得分',
  `corrected_grade_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '修正后等级ID（关联sys_grade.grade_id）',
  `status_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '申诉状态ID（关联sys_appeal_status.status_id）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '申诉复核表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of appeal_review
-- ----------------------------
INSERT INTO `appeal_review` VALUES (1, 'appeal001-1111-1111-1111-111111111', 'APPEAL-TX-2024-001', 'pub004-4444-4444-4444-444444444444', 'obj004-4444-4444-4444-444444444444', '1004-0c5d-432e-711f-2a1908765432', '资产负债率计算有误，应为55%', '[\"/proof/balance_sheet.pdf\"]', '[\"1001-7f9d-499a-b99c-8e7d6f8c7b6a\", \"1005-1d4e-410f-6990-190876543210\"]', '2024-02-28 09:00:00', '2024-02-28 14:00:00', '经复核，数据源错误，修正为55%', 'review002', 85.00, 'grade002', 'appeal003', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `appeal_review` VALUES (2, 'appeal002-2222-2222-2222-222222222', 'APPEAL-HOSP-2024-001', 'pub002-2222-2222-2222-222222222222', 'obj002-2222-2222-2222-222222222222', '1002-8a7b-476c-955d-4c3b2a190876', '评分偏低，申请复议', NULL, '[\"1003-9b6c-454d-833e-3b2a19087654\"]', '2024-12-29 11:00:00', NULL, NULL, NULL, NULL, NULL, 'appeal001', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `appeal_review` VALUES (3, 'appeal003-3333-3333-3333-333333333', 'APPEAL-GOV-2024-001', 'pub001-1111-1111-1111-111111111111', 'obj001-1111-1111-1111-111111111111', '1001-7f9d-499a-b99c-8e7d6f8c7b6a', '暂无异议，测试数据', NULL, NULL, '2024-03-01 10:00:00', NULL, NULL, NULL, NULL, NULL, 'appeal005', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `appeal_review` VALUES (4, 'appeal004-4444-4444-4444-444444444', 'APPEAL-AUTO-2024-001', 'pub003-3333-3333-3333-333333333333', 'obj003-3333-3333-3333-333333333333', '1003-9b6c-454d-833e-3b2a19087654', '尚未公示，无异议', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'appeal001', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `appeal_review` VALUES (5, 'appeal005-5555-5555-5555-555555555', 'APPEAL-CHARITY-2024-001', 'pub005-5555-5555-5555-555555555555', 'obj005-5555-5555-5555-555555555555', '1005-1d4e-410f-6990-190876543210', '尚未公示，无异议', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'appeal001', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');

-- ----------------------------
-- Table structure for eval_appeal_feedback
-- ----------------------------
DROP TABLE IF EXISTS `eval_appeal_feedback`;
CREATE TABLE `eval_appeal_feedback`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `feedback_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '申诉反馈UUID（业务主键）',
  `appeal_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '申诉复核ID，关联eval_appeal_review.appeal_id',
  `feedback_content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '反馈内容',
  `feedback_time` datetime(0) NULL DEFAULT NULL COMMENT '反馈时间',
  `feedback_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '反馈人，关联sys_user.user_id',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '反馈状态，关联sys_data_status.status_id',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '申诉反馈表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_appeal_feedback
-- ----------------------------
INSERT INTO `eval_appeal_feedback` VALUES (1, 'fdb001-1111-1111-1111-111111111111', 'appeal001-1111-1111-1111-111111111', '经复核，资产负债率数据源确有错误，同意按申诉材料修正为55%，修正后评分85分（B级）', '2024-02-28 16:00:00', '1001-7f9d-499a-b99c-8e7d6f8c7b6a', 'data_status002', NULL, NULL, NULL, NULL, '1001-7f9d-499a-b99c-8e7d6f8c7b6a', '1001-7f9d-499a-b99c-8e7d6f8c7b6a', b'0', '1', '2026-02-13 14:27:22', '2026-02-13 14:27:22');
INSERT INTO `eval_appeal_feedback` VALUES (2, 'fdb002-2222-2222-2222-222222222222', 'appeal002-2222-2222-2222-222222222', '申诉理由未提供有效佐证材料，现有考核数据真实有效，驳回申诉。如需重新申请，请补充服务质量相关证明材料', '2024-12-29 15:30:00', '1003-9b6c-454d-833e-3b2a19087654', 'data_status003', NULL, NULL, NULL, NULL, '1003-9b6c-454d-833e-3b2a19087654', '1003-9b6c-454d-833e-3b2a19087654', b'0', '1', '2026-02-13 14:27:22', '2026-02-13 14:27:22');
INSERT INTO `eval_appeal_feedback` VALUES (3, 'fdb003-3333-3333-3333-333333333333', 'appeal003-3333-3333-3333-333333333', '申诉人确认无异议，本次申诉为测试数据，无需调整评价结果', '2024-03-01 11:00:00', '1002-8a7b-476c-955d-4c3b2a190876', 'data_status001', NULL, NULL, NULL, NULL, '1002-8a7b-476c-955d-4c3b2a190876', '1002-8a7b-476c-955d-4c3b2a190876', b'0', '1', '2026-02-13 14:27:22', '2026-02-13 14:27:22');
INSERT INTO `eval_appeal_feedback` VALUES (4, 'fdb004-4444-4444-4444-444444444444', 'appeal004-4444-4444-4444-444444444', '申诉对应的评价结果尚未公示，暂不处理申诉请求，公示后可重新提交', '2024-03-02 10:15:00', '1004-0c5d-432e-711f-2a1908765432', 'data_status004', NULL, NULL, NULL, NULL, '1004-0c5d-432e-711f-2a1908765432', '1004-0c5d-432e-711f-2a1908765432', b'0', '1', '2026-02-13 14:27:22', '2026-02-13 14:27:22');
INSERT INTO `eval_appeal_feedback` VALUES (5, 'fdb005-5555-5555-5555-555555555555', 'appeal005-5555-5555-5555-555555555', '申诉对应的评价结果尚未公示，暂不处理申诉请求，公示后可重新提交', '2024-03-02 10:20:00', '1005-1d4e-410f-6990-190876543210', 'data_status004', NULL, NULL, NULL, NULL, '1005-1d4e-410f-6990-190876543210', '1005-1d4e-410f-6990-190876543210', b'0', '1', '2026-02-13 14:27:22', '2026-02-13 14:27:22');
INSERT INTO `eval_appeal_feedback` VALUES (6, 'fdb001-1111-1111-1111-111111111111', 'appeal001-1111-1111-1111-111111111', '经复核，资产负债率数据源确有错误，同意按申诉材料修正为55%，修正后评分85分（B级）', '2024-02-28 16:00:00', '1001-7f9d-499a-b99c-8e7d6f8c7b6a', 'data_status002', NULL, NULL, NULL, NULL, '1001-7f9d-499a-b99c-8e7d6f8c7b6a', '1001-7f9d-499a-b99c-8e7d6f8c7b6a', b'0', '1', '2026-02-13 14:40:59', '2026-02-13 14:40:59');
INSERT INTO `eval_appeal_feedback` VALUES (7, 'fdb002-2222-2222-2222-222222222222', 'appeal002-2222-2222-2222-222222222', '申诉理由未提供有效佐证材料，现有考核数据真实有效，驳回申诉。如需重新申请，请补充服务质量相关证明材料', '2024-12-29 15:30:00', '1003-9b6c-454d-833e-3b2a19087654', 'data_status003', NULL, NULL, NULL, NULL, '1003-9b6c-454d-833e-3b2a19087654', '1003-9b6c-454d-833e-3b2a19087654', b'0', '1', '2026-02-13 14:40:59', '2026-02-13 14:40:59');
INSERT INTO `eval_appeal_feedback` VALUES (8, 'fdb003-3333-3333-3333-333333333333', 'appeal003-3333-3333-3333-333333333', '申诉人确认无异议，本次申诉为测试数据，无需调整评价结果', '2024-03-01 11:00:00', '1002-8a7b-476c-955d-4c3b2a190876', 'data_status001', NULL, NULL, NULL, NULL, '1002-8a7b-476c-955d-4c3b2a190876', '1002-8a7b-476c-955d-4c3b2a190876', b'0', '1', '2026-02-13 14:40:59', '2026-02-13 14:40:59');
INSERT INTO `eval_appeal_feedback` VALUES (9, 'fdb004-4444-4444-4444-444444444444', 'appeal004-4444-4444-4444-444444444', '申诉对应的评价结果尚未公示，暂不处理申诉请求，公示后可重新提交', '2024-03-02 10:15:00', '1004-0c5d-432e-711f-2a1908765432', 'data_status004', NULL, NULL, NULL, NULL, '1004-0c5d-432e-711f-2a1908765432', '1004-0c5d-432e-711f-2a1908765432', b'0', '1', '2026-02-13 14:40:59', '2026-02-13 14:40:59');
INSERT INTO `eval_appeal_feedback` VALUES (10, 'fdb005-5555-5555-5555-555555555555', 'appeal005-5555-5555-5555-555555555', '申诉对应的评价结果尚未公示，暂不处理申诉请求，公示后可重新提交', '2024-03-02 10:20:00', '1005-1d4e-410f-6990-190876543210', 'data_status004', NULL, NULL, NULL, NULL, '1005-1d4e-410f-6990-190876543210', '1005-1d4e-410f-6990-190876543210', b'0', '1', '2026-02-13 14:40:59', '2026-02-13 14:40:59');

-- ----------------------------
-- Table structure for eval_appeal_record
-- ----------------------------
DROP TABLE IF EXISTS `eval_appeal_record`;
CREATE TABLE `eval_appeal_record`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `appeal_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '申诉UUID（主键，UUID）',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '申诉编号',
  `object_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '申诉对象ID（关联eval_object.object_id）',
  `public_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联公示记录ID（关联eval_public_record.public_id）',
  `audit_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联审核记录ID（关联eval_audit_record.audit_id）',
  `appeal_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '申诉人（关联sys_user.user_id）',
  `submit_time` datetime(0) NULL DEFAULT NULL COMMENT '申诉提交时间',
  `status` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '复核状态（关联sys_appeal_status.status_id）',
  `original_score` decimal(10, 2) NULL DEFAULT NULL COMMENT '原评价得分',
  `review_by` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '复核人员ID（关联sys_user.user_id，多个用逗号分隔）',
  `review_time` datetime(0) NULL DEFAULT NULL COMMENT '复核完成时间',
  `final_result` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最终复核结果（维持原结果/修正结果）',
  `correct_score` decimal(10, 2) NULL DEFAULT NULL COMMENT '修正后得分',
  `correct_standard_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修正后标准ID（关联eval_standard_item.standard_item_id）',
  `close_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '结案状态（未结案/已结案）',
  `close_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '结案人（关联sys_user.user_id）',
  `close_time` datetime(0) NULL DEFAULT NULL COMMENT '结案时间',
  `appeal_reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '申诉理由摘要',
  `file_count` int NULL DEFAULT NULL COMMENT '证明材料数量',
  `wait_hour` decimal(5, 1) NULL DEFAULT NULL COMMENT '待受理时长（小时）',
  `appeal_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '申诉类型',
  `original_grade` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '原评价等级',
  `file_check_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '材料审核状态',
  `reject_reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '驳回原因完整内容',
  `file_check_result` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '证明材料审核结果',
  `notify_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '驳回通知推送状态（已推送/未推送）',
  `review_hour` decimal(5, 1) NULL DEFAULT NULL COMMENT '受理中时长（小时）',
  `review_progress` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '复核进度',
  `stage_result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '阶段性核查结果',
  `latest_oper_time` datetime(0) NULL DEFAULT NULL COMMENT '最新操作时间',
  `warning_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '复核超期预警（正常/超期预警）',
  `pre_result` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '复核初步结果',
  `check_file_count` int NULL DEFAULT NULL COMMENT '核查材料数量',
  `correct_suggest` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '数据修正建议',
  `close_report_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '结案报告下载链接',
  `data_sync_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据同步状态（已同步/未同步/同步中）',
  `sync_time` datetime(0) NULL DEFAULT NULL COMMENT '同步完成时间',
  `feedback_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '申诉人反馈状态（已反馈/未反馈）',
  `archive_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联存档记录ID（关联eval_archive_record.archive_id）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '申诉复核表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_appeal_record
-- ----------------------------

-- ----------------------------
-- Table structure for eval_archive_record
-- ----------------------------
DROP TABLE IF EXISTS `eval_archive_record`;
CREATE TABLE `eval_archive_record`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `archive_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '存档UUID（主键，UUID）',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '存档编号',
  `public_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联公示记录ID（关联eval_public_record.public_id）',
  `object_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评价对象ID（关联eval_object.object_id）',
  `audit_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联审核记录ID（关联eval_audit_record.audit_id）',
  `standard_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评价标准ID（关联eval_standard_item.standard_item_id）',
  `eval_score` decimal(10, 2) NULL DEFAULT NULL COMMENT '评价得分',
  `status` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '存档状态（关联sys_archive_status.status_id）',
  `apply_time` datetime(0) NULL DEFAULT NULL COMMENT '申请存档时间',
  `archive_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '存档人（关联sys_user.user_id）',
  `actual_time` datetime(0) NULL DEFAULT NULL COMMENT '实际存档时间',
  `attachment_count` int NULL DEFAULT NULL COMMENT '存档附件数量',
  `store_location` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '存档存储位置',
  `trace_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据溯源链接',
  `query_count` int NULL DEFAULT NULL COMMENT '查询次数',
  `latest_query_time` datetime(0) NULL DEFAULT NULL COMMENT '最新查询时间',
  `wait_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '待存档原因',
  `wait_hour` decimal(5, 1) NULL DEFAULT NULL COMMENT '待存档时长（小时）',
  `check_result` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '存档条件校验结果（已满足/未满足）',
  `appeal_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联申诉记录ID（关联eval_appeal_record.appeal_id）',
  `appeal_status` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '申诉复核状态',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评价结果存档表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_archive_record
-- ----------------------------

-- ----------------------------
-- Table structure for eval_audit_record
-- ----------------------------
DROP TABLE IF EXISTS `eval_audit_record`;
CREATE TABLE `eval_audit_record`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `audit_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核UUID（主键，UUID）',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核编号',
  `task_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联评价任务ID（关联eval_task.task_id）',
  `object_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评价对象ID（关联eval_object.object_id）',
  `eval_score` decimal(10, 2) NULL DEFAULT NULL COMMENT '评价得分',
  `standard_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评价标准ID（关联eval_standard_item.standard_item_id）',
  `status` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核状态（关联sys_audit_status.status_id）',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间（创建时间）',
  `audit_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人（关联sys_user.user_id）',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `data_source` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '数据来源汇总',
  `reject_check_result` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '否决项检查结果（通过/触发）',
  `reject_reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '驳回原因完整内容',
  `audit_opinion` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核意见摘要',
  `data_source_detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '数据来源明细',
  `index_score` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '各指标得分',
  `assign_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分配审核人（关联sys_user.user_id）',
  `task_create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务创建人（关联sys_user.user_id）',
  `wait_hour` decimal(5, 1) NULL DEFAULT NULL COMMENT '待审核时长（小时）',
  `error_data_source` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '问题数据来源',
  `recalc_count` int NULL DEFAULT NULL COMMENT '重算次数',
  `recalc_time` datetime(0) NULL DEFAULT NULL COMMENT '最后重算时间',
  `correct_guide` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '修正指引',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评价结果审核表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_audit_record
-- ----------------------------

-- ----------------------------
-- Table structure for eval_index_category
-- ----------------------------
DROP TABLE IF EXISTS `eval_index_category`;
CREATE TABLE `eval_index_category`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `category_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '指标分类ID（UUID）',
  `system_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '指标体系ID（关联eval_index_system.system_id）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类名称',
  `weight` decimal(5, 2) NULL DEFAULT NULL COMMENT '分类权重（如90.50）',
  `sort_no` int NULL DEFAULT NULL COMMENT '排序序号',
  `create_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人，关联sys_user.user_id',
  `update_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人，关联sys_user.user_id',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间（业务字段）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间（业务字段）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '指标分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_index_category
-- ----------------------------
INSERT INTO `eval_index_category` VALUES (1, 'cate_001', 'system_001', '履职尽责', 40.00, 1, NULL, NULL, '2024-01-10 09:10:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:08:12', '2026-02-11 17:08:12');
INSERT INTO `eval_index_category` VALUES (2, 'cate_002', 'system_002', '业务能力', 50.00, 1, NULL, NULL, '2024-01-11 10:10:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:08:12', '2026-02-11 17:08:12');
INSERT INTO `eval_index_category` VALUES (3, 'cate_003', 'system_003', '经营效益', 60.00, 1, NULL, NULL, '2024-01-12 11:10:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:08:12', '2026-02-11 17:08:12');
INSERT INTO `eval_index_category` VALUES (4, 'cate_004', 'system_004', '财务信用', 50.00, 1, NULL, NULL, '2024-01-13 13:10:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:08:12', '2026-02-11 17:08:12');
INSERT INTO `eval_index_category` VALUES (5, 'cate_005', 'system_005', '内部治理', 100.00, 1, NULL, NULL, '2024-01-14 14:10:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:08:12', '2026-02-11 17:08:12');

-- ----------------------------
-- Table structure for eval_index_item
-- ----------------------------
DROP TABLE IF EXISTS `eval_index_item`;
CREATE TABLE `eval_index_item`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `item_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '指标项ID（UUID）',
  `category_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '指标分类ID（关联eval_index_category.category_id）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '指标项名称',
  `index_type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '指标类型ID（关联sys_index_type.type_id）',
  `calc_way_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '计算方式ID（关联sys_calc_way.way_id）',
  `threshold` decimal(10, 2) NULL DEFAULT NULL COMMENT '达标阈值',
  `weight` decimal(5, 2) NULL DEFAULT NULL COMMENT '指标项权重',
  `sort_no` int NULL DEFAULT NULL COMMENT '排序序号',
  `update_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人，关联sys_user.user_id',
  `create_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人，关联sys_user.user_id',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间（业务字段）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间（业务字段）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '指标项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_index_item
-- ----------------------------
INSERT INTO `eval_index_item` VALUES (1, 'item_001', 'cate_001', '年度任务完成率', 'idx_type_001', 'calc_way_004', 95.00, 100.00, NULL, NULL, NULL, '2024-01-10 09:20:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:10:43', '2026-02-11 17:10:43');
INSERT INTO `eval_index_item` VALUES (2, 'item_002', 'cate_002', '专业资质持证率', 'idx_type_001', 'calc_way_004', 90.00, 100.00, NULL, NULL, NULL, '2024-01-11 10:20:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:10:43', '2026-02-11 17:10:43');
INSERT INTO `eval_index_item` VALUES (3, 'item_003', 'cate_003', '营业收入增长率', 'idx_type_001', 'calc_way_004', 10.00, 100.00, NULL, NULL, NULL, '2024-01-12 11:20:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:10:43', '2026-02-11 17:10:43');
INSERT INTO `eval_index_item` VALUES (4, 'item_004', 'cate_004', '资产负债率', 'idx_type_001', 'calc_way_004', 60.00, 100.00, NULL, NULL, NULL, '2024-01-13 13:20:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:10:43', '2026-02-11 17:10:43');
INSERT INTO `eval_index_item` VALUES (5, 'item_005', 'cate_005', '章程制度完善度', 'idx_type_002', 'calc_way_002', 95.00, 100.00, NULL, NULL, NULL, '2024-01-14 14:20:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:10:43', '2026-02-11 17:10:43');

-- ----------------------------
-- Table structure for eval_index_system
-- ----------------------------
DROP TABLE IF EXISTS `eval_index_system`;
CREATE TABLE `eval_index_system`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `system_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '指标体系ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '体系名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '体系编码',
  `object_type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '适用对象类型ID（关联sys_object_type.type_id）',
  `version` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '版本号',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述信息',
  `category_count` int NULL DEFAULT NULL COMMENT '分类总数',
  `use_count` int NULL DEFAULT NULL COMMENT '使用次数',
  `item_count` int NULL DEFAULT NULL COMMENT '指标项总数',
  `status_id` int NULL DEFAULT NULL COMMENT '状态ID（关联sys_status.status_id）',
  `last_use_time` datetime(0) NULL DEFAULT NULL COMMENT '最近使用时间',
  `create_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人ID（关联sys_user.user_id）',
  `update_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人，关联sys_user.user_id',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间（业务字段）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间（业务字段）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '指标体系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_index_system
-- ----------------------------
INSERT INTO `eval_index_system` VALUES (1, 'system_001', '政府部门绩效评价体系', 'GOV_PERFORMANCE', 'obj_type_001', 'V1.0', '政府部门年度绩效评价', 3, NULL, 12, 1, NULL, '1001-7f9d-499a-b99c-8e7d6f8c7b6a', NULL, '2024-01-10 09:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:07:55', '2026-02-11 17:07:55');
INSERT INTO `eval_index_system` VALUES (2, 'system_002', '事业单位考核体系', 'PUBLIC_ASSESS', 'obj_type_002', 'V1.2', '事业单位综合考核', 2, NULL, 8, 1, NULL, '1002-8a7b-476c-955d-4c3b2a190876', NULL, '2024-01-11 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:07:55', '2026-02-11 17:07:55');
INSERT INTO `eval_index_system` VALUES (3, 'system_003', '国有企业经营评价', 'STATE_EVALUATE', 'obj_type_003', 'V2.0', '国有企业经营业绩评价', 2, NULL, 6, 1, NULL, '1003-9b6c-454d-833e-3b2a19087654', NULL, '2024-01-12 11:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:07:55', '2026-02-11 17:07:55');
INSERT INTO `eval_index_system` VALUES (4, 'system_004', '民营企业信用评价', 'PRIVATE_CREDIT', 'obj_type_004', 'V1.5', '民营企业信用评级', 2, NULL, 4, 0, NULL, '1004-0c5d-432e-711f-2a1908765432', NULL, '2024-01-13 13:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:07:55', '2026-02-11 17:07:55');
INSERT INTO `eval_index_system` VALUES (5, 'system_005', '社会组织评估体系', 'SOCIAL_ORG_EVAL', 'obj_type_005', 'V1.1', '社会组织能力评估', 1, NULL, 3, 1, NULL, '1005-1d4e-410f-6990-190876543210', NULL, '2024-01-14 14:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:07:55', '2026-02-11 17:07:55');

-- ----------------------------
-- Table structure for eval_inspect_attach
-- ----------------------------
DROP TABLE IF EXISTS `eval_inspect_attach`;
CREATE TABLE `eval_inspect_attach`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `attach_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '附件UUID（业务主键）',
  `record_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '考察记录ID，关联inspection_record.record_id',
  `attach_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '附件名称',
  `attach_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '附件链接',
  `attach_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '附件类型：图片/文档',
  `file_size` bigint NULL DEFAULT 0 COMMENT '文件大小（单位：字节）',
  `upload_time` datetime(0) NULL DEFAULT NULL COMMENT '上传时间',
  `upload_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '上传人，关联sys_user.user_id',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '考察附件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_inspect_attach
-- ----------------------------
INSERT INTO `eval_inspect_attach` VALUES (1, 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'rec001-1111-1111-1111-111111111111', '朝阳政务中心服务大厅现场照片.jpg', 'https://attach.eval.com/inspect/20240320/rec001_photo1.jpg', '图片', 204800, '2024-03-20 09:30:00', '1001-7f9d-499a-b99c-8e7d6f8c7b6a', NULL, NULL, NULL, NULL, '1001-7f9d-499a-b99c-8e7d6f8c7b6a', '1001-7f9d-499a-b99c-8e7d6f8c7b6a', b'0', '1', '2026-02-13 14:23:35', '2026-02-13 14:23:35');
INSERT INTO `eval_inspect_attach` VALUES (2, 'b1e2d3c4-5678-4abc-9def-0123456789ab', 'rec002-2222-2222-2222-222222222222', '浦东人民医院排队时长问题说明.docx', 'https://attach.eval.com/inspect/20241015/rec002_doc1.docx', '文档', 819200, '2024-10-15 16:40:00', '1002-8a7b-476c-955d-4c3b2a190876', NULL, NULL, NULL, NULL, '1002-8a7b-476c-955d-4c3b2a190876', '1002-8a7b-476c-955d-4c3b2a190876', b'0', '1', '2026-02-13 14:23:35', '2026-02-13 14:23:35');
INSERT INTO `eval_inspect_attach` VALUES (3, 'c2f3e4d5-6789-4fgh-ijkl-1234567890cd', 'rec003-3333-3333-3333-333333333333', '广汽集团经营效益座谈纪要.pdf', 'https://attach.eval.com/inspect/20241205/rec003_doc2.pdf', '文档', 1048576, '2024-12-05 11:30:00', '1003-9b6c-454d-833e-3b2a19087654', NULL, NULL, NULL, NULL, '1003-9b6c-454d-833e-3b2a19087654', '1003-9b6c-454d-833e-3b2a19087654', b'0', '1', '2026-02-13 14:23:35', '2026-02-13 14:23:35');
INSERT INTO `eval_inspect_attach` VALUES (4, 'd3g4h5j6-7890-4klm-nopq-2345678901ef', 'rec004-4444-4444-4444-444444444444', '报表数据口径错误截图.png', 'https://attach.eval.com/inspect/20240220/rec004_photo2.png', '图片', 307200, '2024-02-20 11:25:00', '1004-0c5d-432e-711f-2a1908765432', NULL, NULL, NULL, NULL, '1004-0c5d-432e-711f-2a1908765432', '1004-0c5d-432e-711f-2a1908765432', b'0', '1', '2026-02-13 14:23:35', '2026-02-13 14:23:35');
INSERT INTO `eval_inspect_attach` VALUES (5, 'e4h5j6k7-8901-4nop-qrst-3456789012fg', 'rec005-5555-5555-5555-555555555555', '浙江慈善总会远程考察会议录屏.mp4', 'https://attach.eval.com/inspect/20240301/rec005_video1.mp4', '文档', 5242880, '2024-03-01 16:00:00', '1005-1d4e-410f-6990-190876543210', NULL, NULL, NULL, NULL, '1005-1d4e-410f-6990-190876543210', '1005-1d4e-410f-6990-190876543210', b'0', '1', '2026-02-13 14:23:35', '2026-02-13 14:23:35');

-- ----------------------------
-- Table structure for eval_object
-- ----------------------------
DROP TABLE IF EXISTS `eval_object`;
CREATE TABLE `eval_object`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `object_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评价对象ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '对象名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '对象编码',
  `area_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所属区域编码（关联sys_area.area_code）',
  `object_type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '对象类型ID，关联sys_object_type.type_id',
  `manager_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '负责人ID（关联sys_user.user_id）',
  `related_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联网格/部门ID（关联eval_related_object.related_id）',
  `status_id` int NULL DEFAULT NULL COMMENT '状态ID（关联sys_status.status_id）',
  `create_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人ID（关联sys_user.user_id）',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间（业务字段）',
  `update_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人ID（关联sys_user.user_id）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间（业务字段）',
  `change_log` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '变更日志',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30083 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '评价对象表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_object
-- ----------------------------
INSERT INTO `eval_object` VALUES (1, 'obj001-1111-1111-1111-111111111111', '北京市朝阳区政务服务中心', 'OBJ_BJ_CY', '110000', 'obj_type_001', '1001-7f9d-499a-b99c-8e7d6f8c7b6a', 'rel001-aaaa-1111-aaaa-111111111111', 1, '1001-7f9d-499a-b99c-8e7d6f8c7b6a', '2024-02-01 11:00:00', NULL, NULL, '新创建对象', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:42:55', '2026-02-12 11:42:55');
INSERT INTO `eval_object` VALUES (2, 'obj002-2222-2222-2222-222222222222', '上海市浦东新区人民医院', 'OBJ_SH_PD', '310000', 'obj_type_002', '1002-8a7b-476c-955d-4c3b2a190876', 'rel002-bbbb-2222-bbbb-222222222222', 1, '1002-8a7b-476c-955d-4c3b2a190876', '2024-02-01 11:10:00', NULL, NULL, '新创建对象', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:42:55', '2026-02-12 11:42:55');
INSERT INTO `eval_object` VALUES (3, 'obj003-3333-3333-3333-333333333333', '广州汽车集团股份有限公司', 'OBJ_GZ_QC', '440100', 'obj_type_003', '1003-9b6c-454d-833e-3b2a19087654', 'rel003-cccc-3333-cccc-333333333333', 1, '1003-9b6c-454d-833e-3b2a19087654', '2024-02-01 11:20:00', NULL, NULL, '新创建对象', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:42:55', '2026-02-12 11:42:55');
INSERT INTO `eval_object` VALUES (4, 'obj004-4444-4444-4444-444444444444', '成都腾讯科技有限公司', 'OBJ_CD_TX', '510100', 'obj_type_004', '1004-0c5d-432e-711f-2a1908765432', 'rel004-dddd-4444-dddd-444444444444', 1, '1004-0c5d-432e-711f-2a1908765432', '2024-02-01 11:30:00', NULL, NULL, '新创建对象', NULL, NULL, NULL, NULL, '', '1', b'0', 1, '2026-02-12 11:42:55', '2026-02-25 16:44:40');
INSERT INTO `eval_object` VALUES (5, 'obj005-5555-5555-5555-555555555555', '浙江省慈善联合总会', 'OBJ_ZJ_CS', '330100', 'obj_type_005', '1005-1d4e-410f-6990-190876543210', 'rel005-eeee-5555-eeee-555555555555', 1, '1005-1d4e-410f-6990-190876543210', '2024-02-01 11:40:00', NULL, NULL, '新创建对象', NULL, NULL, NULL, NULL, '', '1', b'0', 1, '2026-02-12 11:42:55', '2026-02-25 16:44:19');

-- ----------------------------
-- Table structure for eval_public_record
-- ----------------------------
DROP TABLE IF EXISTS `eval_public_record`;
CREATE TABLE `eval_public_record`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `public_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公示UUID（主键，UUID）',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公示编号',
  `audit_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联审核记录ID（关联eval_audit_record.audit_id）',
  `object_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评价对象ID（关联eval_object.object_id）',
  `standard_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评价标准ID（关联eval_standard_item.standard_item_id）',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '公示开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '公示结束时间',
  `status` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公示状态（关联sys_public_status.status_id）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公示创建人（关联sys_user.user_id）',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间（创建时间）',
  `public_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公示链接',
  `objection_count` int NULL DEFAULT NULL COMMENT '异议数量',
  `complete_time` datetime(0) NULL DEFAULT NULL COMMENT '公示完成时间',
  `appeal_record_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联申诉记录编号',
  `remain_hour` decimal(5, 1) NULL DEFAULT NULL COMMENT '公示剩余时长（小时）',
  `visit_count` bigint NULL DEFAULT NULL COMMENT '公示访问量',
  `latest_objection_time` datetime(0) NULL DEFAULT NULL COMMENT '最新异议时间',
  `publish_channel` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公示发布渠道',
  `admin_contact` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '管理员联系方式',
  `stop_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '终止原因',
  `stop_time` datetime(0) NULL DEFAULT NULL COMMENT '终止时间',
  `stop_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '终止人（关联sys_user.user_id）',
  `stop_remain_hour` decimal(5, 1) NULL DEFAULT NULL COMMENT '公示终止时剩余时长（小时）',
  `stop_objection_count` int NULL DEFAULT NULL COMMENT '终止前异议数量',
  `follow_suggest` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '后续处理建议',
  `url_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '历史公示链接状态（已失效/可查看）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评价结果公示表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_public_record
-- ----------------------------

-- ----------------------------
-- Table structure for eval_push_feedback
-- ----------------------------
DROP TABLE IF EXISTS `eval_push_feedback`;
CREATE TABLE `eval_push_feedback`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `feedback_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '推送反馈UUID（业务主键）',
  `push_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '结果推送ID，关联result_push.push_id（文档无eval_push_record，按现有表关联）',
  `feedback_content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '反馈内容',
  `feedback_time` datetime(0) NULL DEFAULT NULL COMMENT '反馈时间',
  `feedback_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '反馈人，关联sys_user.user_id',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '反馈状态，关联sys_data_status.status_id',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '推送反馈表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_push_feedback
-- ----------------------------
INSERT INTO `eval_push_feedback` VALUES (1, 'pfb001-1111-1111-1111-111111111111', 'push001-1111-1111-1111-111111111111', '已成功接收医院2024年度考核结果推送，数据完整，评分与等级信息一致，无异议', '2024-12-29 14:00:00', '1003-9b6c-454d-833e-3b2a19087654', 'data_status002', NULL, NULL, NULL, NULL, '1003-9b6c-454d-833e-3b2a19087654', '1003-9b6c-454d-833e-3b2a19087654', b'0', '1', '2026-02-13 14:50:43', '2026-02-13 14:50:43');
INSERT INTO `eval_push_feedback` VALUES (2, 'pfb002-2222-2222-2222-222222222222', 'push002-2222-2222-2222-222222222222', '确认接收2月信用监测数据推送，资产负债率78%、信用等级C级与我方记录一致，无修正意见', '2024-03-05 15:30:00', '1004-0c5d-432e-711f-2a1908765432', 'data_status001', NULL, NULL, NULL, NULL, '1004-0c5d-432e-711f-2a1908765432', '1004-0c5d-432e-711f-2a1908765432', b'0', '1', '2026-02-13 14:50:43', '2026-02-13 14:50:43');
INSERT INTO `eval_push_feedback` VALUES (3, 'pfb003-3333-3333-3333-333333333333', 'push003-3333-3333-3333-333333333333', '推送的政务评价报告下载链接无法访问，提示\"文件不存在\"，请重新提供有效下载地址或直接补发报告文件', '2024-02-02 10:15:00', '1001-7f9d-499a-b99c-8e7d6f8c7b6a', 'data_status003', NULL, NULL, NULL, NULL, '1001-7f9d-499a-b99c-8e7d6f8c7b6a', '1001-7f9d-499a-b99c-8e7d6f8c7b6a', b'0', '1', '2026-02-13 14:50:43', '2026-02-13 14:50:43');
INSERT INTO `eval_push_feedback` VALUES (4, 'pfb004-4444-4444-4444-444444444444', 'push004-4444-4444-4444-444444444444', 'FTP上传的经营评价数据文件格式异常，JSON字段缺少\"grade\"关键字段，无法正常解析，需按标准格式重新推送', '2024-02-02 14:40:00', '1003-9b6c-454d-833e-3b2a19087654', 'data_status004', NULL, NULL, NULL, NULL, '1003-9b6c-454d-833e-3b2a19087654', '1003-9b6c-454d-833e-3b2a19087654', b'0', '1', '2026-02-13 14:50:43', '2026-02-13 14:50:43');
INSERT INTO `eval_push_feedback` VALUES (5, 'pfb005-5555-5555-5555-555555555555', 'push005-5555-5555-5555-555555555555', '已接收公示平台推送的能力评估数据，待公示期结束后核对完整性与准确性，后续将反馈确认结果', '2024-02-02 16:20:00', '1005-1d4e-410f-6990-190876543210', 'data_status001', NULL, NULL, NULL, NULL, '1005-1d4e-410f-6990-190876543210', '1005-1d4e-410f-6990-190876543210', b'0', '1', '2026-02-13 14:50:43', '2026-02-13 14:50:43');

-- ----------------------------
-- Table structure for eval_push_record
-- ----------------------------
DROP TABLE IF EXISTS `eval_push_record`;
CREATE TABLE `eval_push_record`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `push_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '推送UUID（主键，UUID）',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '推送编号',
  `archive_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联存档记录ID（关联eval_archive_record.archive_id）',
  `target_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联推送目标ID（关联sys_push_target.target_id）',
  `type_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联推送方式ID（关联eval_push_type.type_id）',
  `status` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '推送状态（关联sys_push_status.status_id）',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '推送内容摘要',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人（关联sys_user.user_id）',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间（创建时间）',
  `push_count` int NULL DEFAULT NULL COMMENT '推送次数',
  `latest_push_time` datetime(0) NULL DEFAULT NULL COMMENT '最新推送时间',
  `fail_reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '失败原因',
  `feedback_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '接收方反馈状态（已反馈/未反馈/无需反馈）',
  `data_sync_num` int NULL DEFAULT NULL COMMENT '数据同步量',
  `target_addr` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '推送地址',
  `wait_reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '待推送原因',
  `wait_hour` decimal(5, 1) NULL DEFAULT NULL COMMENT '待推送时长（小时）',
  `data_check_result` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据完整性校验结果（已通过/未通过）',
  `content_format` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '推送内容格式（JSON/Excel/文本）',
  `target_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '目标系统状态（正常/维护/异常）',
  `receiver_resp` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '接收方响应',
  `repush_count` int NULL DEFAULT NULL COMMENT '重新推送次数',
  `latest_repush_time` datetime(0) NULL DEFAULT NULL COMMENT '最新重新推送时间',
  `feedback_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '接收方反馈内容摘要',
  `log_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '推送日志链接',
  `data_consist_result` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据一致性校验结果（已核对/未核对/一致/不一致）',
  `fail_type_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '失败类型ID（关联sys_dock_fail_type.type_id）',
  `error_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '目标系统错误码',
  `fix_suggest` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '修正方案建议',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '结果推送记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_push_record
-- ----------------------------

-- ----------------------------
-- Table structure for eval_push_type
-- ----------------------------
DROP TABLE IF EXISTS `eval_push_type`;
CREATE TABLE `eval_push_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型ID（主键UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型名称',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型编码',
  `type_desc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间（创建时间）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间（更新时间）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '推送方式字典（结果应用）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_push_type
-- ----------------------------

-- ----------------------------
-- Table structure for eval_related_object
-- ----------------------------
DROP TABLE IF EXISTS `eval_related_object`;
CREATE TABLE `eval_related_object`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `related_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联对象ID（UUID）',
  `related_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联对象名称',
  `related_type` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联对象类型：关联sys_object_type.type_id',
  `related_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联对象编码',
  `parent_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上级关联对象ID（关联eval_related_object.related_id）',
  `status_id` int NULL DEFAULT NULL COMMENT '状态ID（关联sys_status.status_id）',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间（业务字段）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间（业务字段）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `update_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人，关联sys_user.user_id',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '关联对象表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_related_object
-- ----------------------------
INSERT INTO `eval_related_object` VALUES (1, 'rel001-aaaa-1111-aaaa-111111111111', '第一网格', '网格', NULL, NULL, 1, '2024-02-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', NULL, '', b'0', 1, '2026-02-12 11:36:32', '2026-02-12 11:36:32');
INSERT INTO `eval_related_object` VALUES (2, 'rel002-bbbb-2222-bbbb-222222222222', '第二网格', '网格', NULL, 'rel001-aaaa-1111-aaaa-111111111111', 1, '2024-02-01 10:05:00', NULL, NULL, NULL, NULL, NULL, '', NULL, '', b'0', 1, '2026-02-12 11:36:32', '2026-02-12 11:36:32');
INSERT INTO `eval_related_object` VALUES (3, 'rel003-cccc-3333-cccc-333333333333', '技术研发部', '部门', NULL, NULL, 1, '2024-02-01 10:10:00', NULL, NULL, NULL, NULL, NULL, '', NULL, '', b'0', 1, '2026-02-12 11:36:32', '2026-02-12 11:36:32');
INSERT INTO `eval_related_object` VALUES (4, 'rel004-dddd-4444-dddd-444444444444', '市场运营部', '部门', NULL, NULL, 1, '2024-02-01 10:15:00', NULL, NULL, NULL, NULL, NULL, '', NULL, '', b'0', 1, '2026-02-12 11:36:32', '2026-02-12 11:36:32');
INSERT INTO `eval_related_object` VALUES (5, 'rel005-eeee-5555-eeee-555555555555', '客户服务部', '部门', NULL, NULL, 1, '2024-02-01 10:20:00', NULL, NULL, NULL, NULL, NULL, '', NULL, '', b'0', 1, '2026-02-12 11:36:32', '2026-02-12 11:36:32');

-- ----------------------------
-- Table structure for eval_report
-- ----------------------------
DROP TABLE IF EXISTS `eval_report`;
CREATE TABLE `eval_report`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `report_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报告UUID（主键，UUID）',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报告编号',
  `template_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联模板ID（关联eval_report_template.template_id）',
  `object_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评价对象ID（关联eval_object.object_id）',
  `task_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联评价任务ID（关联eval_task.task_id）',
  `status_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报告状态ID（关联sys_report_status.status_id）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '生成人（关联sys_user.user_id）',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务生成时间（生成时间）',
  `create_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '生成方式（单份生成/批量生成）',
  `file_size` bigint NULL DEFAULT NULL COMMENT '报告文件大小（字节）',
  `download_count` int NULL DEFAULT NULL COMMENT '下载次数',
  `latest_download_time` datetime(0) NULL DEFAULT NULL COMMENT '最新下载时间',
  `edit_count` int NULL DEFAULT NULL COMMENT '补充编辑次数',
  `start_create_time` datetime(0) NULL DEFAULT NULL COMMENT '生成启动时间',
  `create_progress` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '生成进度',
  `process_node` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '当前处理节点',
  `expect_complete_time` datetime(0) NULL DEFAULT NULL COMMENT '预计完成时间',
  `data_sync_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据同步状态（已同步/同步中/同步失败）',
  `fail_reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '生成失败原因',
  `data_check_result` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '核心数据完整性校验结果（已通过/未通过）',
  `edit_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '补充编辑入口状态（可编辑/不可编辑）',
  `recreate_count` int NULL DEFAULT NULL COMMENT '重新生成次数',
  `latest_recreate_time` datetime(0) NULL DEFAULT NULL COMMENT '最新重新生成时间',
  `preview_count` int NULL DEFAULT NULL COMMENT '预览次数',
  `need_edit_chapter` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '待补充章节',
  `edit_progress` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '补充编辑状态（未开始/编辑中/待提交）',
  `edited_chapter_num` int NULL DEFAULT NULL COMMENT '已补充章节数',
  `total_need_chapter_num` int NULL DEFAULT NULL COMMENT '总待补充章节数',
  `attach_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '附件上传状态（未上传/部分上传/已完成）',
  `latest_edit_time` datetime(0) NULL DEFAULT NULL COMMENT '最晚补充完成时间',
  `finish_time` datetime(0) NULL DEFAULT NULL COMMENT '完成时间',
  `dist_dept_num` int NULL DEFAULT NULL COMMENT '分发部门数',
  `archive_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '归档状态（已归档/待归档）',
  `edit_record_num` int NULL DEFAULT NULL COMMENT '补充编辑记录数',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评价报告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_report
-- ----------------------------

-- ----------------------------
-- Table structure for eval_report_attach
-- ----------------------------
DROP TABLE IF EXISTS `eval_report_attach`;
CREATE TABLE `eval_report_attach`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `attach_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '附件UUID（业务主键）',
  `report_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '评价报告ID，关联evaluation_report.report_id',
  `attach_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '附件名称',
  `attach_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '附件链接',
  `attach_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '附件类型：图片/文档',
  `file_size` bigint NULL DEFAULT 0 COMMENT '文件大小（单位：字节）',
  `upload_time` datetime(0) NULL DEFAULT NULL COMMENT '上传时间',
  `upload_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '上传人，关联sys_user.user_id',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '报告附件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_report_attach
-- ----------------------------
INSERT INTO `eval_report_attach` VALUES (1, 'ra001-1111-1111-1111-111111111111', 'eval_001-1111-1111-1111-111111111111', '朝阳政务中心一季度评价报告正文.pdf', 'https://attach.eval.com/report/202404/eval001_main.pdf', '文档', 1228800, '2024-04-01 10:00:00', '1001-7f9d-499a-b99c-8e7d6f8c7b6a', NULL, NULL, NULL, NULL, '1001-7f9d-499a-b99c-8e7d6f8c7b6a', '1001-7f9d-499a-b99c-8e7d6f8c7b6a', b'0', '1', '2026-02-13 14:53:44', '2026-02-13 14:53:44');
INSERT INTO `eval_report_attach` VALUES (2, 'ra002-2222-2222-2222-222222222222', 'eval_002-2222-2222-2222-222222222222', '浦东人民医院年度考核服务质量截图.png', 'https://attach.eval.com/report/202412/eval002_photo.png', '图片', 409600, '2024-12-20 18:30:00', '1002-8a7b-476c-955d-4c3b2a190876', NULL, NULL, NULL, NULL, '1002-8a7b-476c-955d-4c3b2a190876', '1002-8a7b-476c-955d-4c3b2a190876', b'0', '1', '2026-02-13 14:53:44', '2026-02-13 14:53:44');
INSERT INTO `eval_report_attach` VALUES (3, 'ra003-3333-3333-3333-333333333333', 'eval_003-3333-3333-3333-333333333333', '广汽集团半年经营评价补充数据.xlsx', 'https://attach.eval.com/report/202407/eval003_supplement.xlsx', '文档', 819200, '2024-07-01 09:15:00', '1003-9b6c-454d-833e-3b2a19087654', NULL, NULL, NULL, NULL, '1003-9b6c-454d-833e-3b2a19087654', '1003-9b6c-454d-833e-3b2a19087654', b'0', '1', '2026-02-13 14:53:44', '2026-02-13 14:53:44');
INSERT INTO `eval_report_attach` VALUES (4, 'ra004-4444-4444-4444-444444444444', 'eval_004-4444-4444-4444-444444444444', '成都腾讯二月信用评级佐证材料.jpg', 'https://attach.eval.com/report/202402/eval004_proof.jpg', '图片', 307200, '2024-02-25 17:40:00', '1004-0c5d-432e-711f-2a1908765432', NULL, NULL, NULL, NULL, '1004-0c5d-432e-711f-2a1908765432', '1004-0c5d-432e-711f-2a1908765432', b'0', '1', '2026-02-13 14:53:44', '2026-02-13 14:53:44');
INSERT INTO `eval_report_attach` VALUES (5, 'ra005-5555-5555-5555-555555555555', 'eval_005-5555-5555-5555-555555555555', '浙江慈善总会能力评估附件包.zip', 'https://attach.eval.com/report/202403/eval005_package.zip', '文档', 2097152, '2024-03-16 10:30:00', '1005-1d4e-410f-6990-190876543210', NULL, NULL, NULL, NULL, '1005-1d4e-410f-6990-190876543210', '1005-1d4e-410f-6990-190876543210', b'0', '1', '2026-02-13 14:53:44', '2026-02-13 14:53:44');

-- ----------------------------
-- Table structure for eval_report_template
-- ----------------------------
DROP TABLE IF EXISTS `eval_report_template`;
CREATE TABLE `eval_report_template`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `template_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '模板UUID（主键，UUID）',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '模板编号',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '模板名称',
  `task_type_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '适用任务类型ID（关联sys_task_type.type_id）',
  `version` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '版本号',
  `status_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '模板状态ID（关联sys_template_status.status_id）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人（关联sys_user.user_id）',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间（创建时间）',
  `use_count` int NULL DEFAULT NULL COMMENT '使用次数',
  `latest_use_time` datetime(0) NULL DEFAULT NULL COMMENT '最近生成时间',
  `map_rule_num` int NULL DEFAULT NULL COMMENT '字段映射规则数',
  `file_size` bigint NULL DEFAULT NULL COMMENT '模板文件大小（字节）',
  `file_format` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '模板文件格式（Word/PDF）',
  `version_log` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '版本迭代记录',
  `use_rate` decimal(5, 2) NULL DEFAULT NULL COMMENT '模板使用率（%）',
  `map_complete_rate` decimal(5, 2) NULL DEFAULT NULL COMMENT '字段映射完整度（%）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务模板更新时间（模板更新时间）',
  `dept_dist` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '使用部门分布',
  `stop_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '停用操作人（关联sys_user.user_id）',
  `stop_time` datetime(0) NULL DEFAULT NULL COMMENT '停用时间',
  `stop_reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '停用原因',
  `stop_hour` decimal(5, 1) NULL DEFAULT NULL COMMENT '停用时长（小时）',
  `file_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '模板文件状态（正常/损坏/缺失）',
  `map_valid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字段映射规则有效性（有效/无效）',
  `latest_version_log` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '最新版本迭代记录',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '报告模板表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_report_template
-- ----------------------------

-- ----------------------------
-- Table structure for eval_rule_category
-- ----------------------------
DROP TABLE IF EXISTS `eval_rule_category`;
CREATE TABLE `eval_rule_category`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `rule_category_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '规则分类ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '规则分类名称',
  `system_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '适用指标体系ID（关联eval_index_system.system_id）',
  `item_count` int NULL DEFAULT NULL COMMENT '规则项数量',
  `status_id` int NULL DEFAULT NULL COMMENT '状态ID（关联sys_status.status_id）',
  `create_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人ID（关联sys_user.user_id）',
  `update_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人ID，关联sys_user.id',
  `use_count` int NULL DEFAULT NULL COMMENT '使用次数',
  `last_use_time` datetime(0) NULL DEFAULT NULL COMMENT '最近使用时间',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间（业务字段）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间（业务字段）',
  `change_log` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '变更日志',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '规则分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_rule_category
-- ----------------------------
INSERT INTO `eval_rule_category` VALUES (1, 'rc001-1111-1111-1111-111111111111', '履职尽责类规则', 'system_001', 1, 1, '1001-7f9d-499a-b99c-8e7d6f8c7b6a', NULL, NULL, NULL, '2024-02-01 16:00:00', NULL, '初始创建', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:38:10', '2026-02-12 12:38:10');
INSERT INTO `eval_rule_category` VALUES (2, 'rc002-2222-2222-2222-222222222222', '业务能力类规则', 'system_002', 1, 1, '1002-8a7b-476c-955d-4c3b2a190876', NULL, NULL, NULL, '2024-02-01 16:10:00', NULL, '初始创建', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:38:10', '2026-02-12 12:38:10');
INSERT INTO `eval_rule_category` VALUES (3, 'rc003-3333-3333-3333-333333333333', '经营效益类规则', 'system_003', 1, 1, '1003-9b6c-454d-833e-3b2a19087654', NULL, NULL, NULL, '2024-02-01 16:20:00', NULL, '初始创建', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:38:10', '2026-02-12 12:38:10');
INSERT INTO `eval_rule_category` VALUES (4, 'rc004-4444-4444-4444-444444444444', '财务信用类规则', 'system_004', 1, 1, '1004-0c5d-432e-711f-2a1908765432', NULL, NULL, NULL, '2024-02-01 16:30:00', NULL, '初始创建', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:38:10', '2026-02-12 12:38:10');
INSERT INTO `eval_rule_category` VALUES (5, 'rc005-5555-5555-5555-555555555555', '内部治理类规则', 'system_005', 1, 1, '1005-1d4e-410f-6990-190876543210', NULL, NULL, NULL, '2024-02-01 16:40:00', NULL, '初始创建', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:38:10', '2026-02-12 12:38:10');

-- ----------------------------
-- Table structure for eval_rule_item
-- ----------------------------
DROP TABLE IF EXISTS `eval_rule_item`;
CREATE TABLE `eval_rule_item`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `rule_item_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '规则项ID（UUID）',
  `rule_category_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '规则分类ID（关联eval_rule_category.rule_category_id）',
  `index_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联指标项ID（关联eval_index_item.item_id）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '规则项名称',
  `score_logic` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评分逻辑',
  `full_score` decimal(8, 2) NULL DEFAULT NULL COMMENT '满分值',
  `rule_type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '规则类型ID（关联sys_rule_type.type_id）',
  `create_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人ID（关联sys_user.user_id）',
  `update_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人ID，关联sys_user.user_id',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间（业务字段）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间（业务字段）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '规则项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_rule_item
-- ----------------------------
INSERT INTO `eval_rule_item` VALUES (1, 'ritem001-1111-1111-1111-111111111111', 'rc001-1111-1111-1111-111111111111', 'item_001', '年度任务完成率评分', '完成率≥95%得满分，每低1%扣2分', 100.00, 'rule_type001', NULL, NULL, '2024-02-01 17:10:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:43:07', '2026-02-12 12:43:07');
INSERT INTO `eval_rule_item` VALUES (2, 'ritem002-2222-2222-2222-222222222222', 'rc002-2222-2222-2222-222222222222', 'item_002', '专业资质持证率评分', '持证率≥90%得满分，低于90%按比例得分', 100.00, 'rule_type002', NULL, NULL, '2024-02-01 17:20:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:43:07', '2026-02-12 12:43:07');
INSERT INTO `eval_rule_item` VALUES (3, 'ritem003-3333-3333-3333-333333333333', 'rc003-3333-3333-3333-333333333333', 'item_003', '营业收入增长率评分', '增长率≥10%得满分，每低1%扣5分', 100.00, 'rule_type001', NULL, NULL, '2024-02-01 17:30:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:43:07', '2026-02-12 12:43:07');
INSERT INTO `eval_rule_item` VALUES (4, 'ritem004-4444-4444-4444-444444444444', 'rc004-4444-4444-4444-444444444444', 'item_004', '资产负债率评分', '资产负债率≤60%得满分，每高1%扣3分', 100.00, 'rule_type001', NULL, NULL, '2024-02-01 17:40:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:43:07', '2026-02-12 12:43:07');
INSERT INTO `eval_rule_item` VALUES (5, 'ritem005-5555-5555-5555-555555555555', 'rc005-5555-5555-5555-555555555555', 'item_005', '章程制度完善度评分', '专家综合评分（0-100）', 100.00, 'rule_type005', NULL, NULL, '2024-02-01 17:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:43:07', '2026-02-12 12:43:07');

-- ----------------------------
-- Table structure for eval_standard_category
-- ----------------------------
DROP TABLE IF EXISTS `eval_standard_category`;
CREATE TABLE `eval_standard_category`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `standard_category_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标准分类ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标准分类名称',
  `system_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '适用指标体系ID（关联eval_index_system.system_id）',
  `item_count` int NULL DEFAULT NULL COMMENT '标准项数量',
  `status_id` int NULL DEFAULT NULL COMMENT '状态ID（关联sys_status.status_id）',
  `last_use_time` datetime(0) NULL DEFAULT NULL COMMENT '最近使用时间',
  `use_count` int NULL DEFAULT NULL COMMENT '使用次数',
  `create_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人ID（关联sys_user.user_id）',
  `update_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人ID（关联sys_user.user_id）',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间（业务字段）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间（业务字段）',
  `change_log` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '变更日志',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '标准分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_standard_category
-- ----------------------------
INSERT INTO `eval_standard_category` VALUES (1, 'std_cat001', '等级划分标准', 'system_001', 1, 1, NULL, NULL, '1001-7f9d-499a-b99c-8e7d6f8c7b6a', NULL, '2024-02-02 09:00:00', NULL, '初始创建', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 14:06:31', '2026-02-12 14:06:31');
INSERT INTO `eval_standard_category` VALUES (2, 'std_cat002', '评分等级', 'system_002', 1, 1, NULL, NULL, '1002-8a7b-476c-955d-4c3b2a190876', NULL, '2024-02-02 09:10:00', NULL, '初始创建', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 14:06:31', '2026-02-12 14:06:31');
INSERT INTO `eval_standard_category` VALUES (3, 'std_cat003', '效益等级', 'system_003', 1, 1, NULL, NULL, '1003-9b6c-454d-833e-3b2a19087654', NULL, '2024-02-02 09:20:00', NULL, '初始创建', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 14:06:31', '2026-02-12 14:06:31');
INSERT INTO `eval_standard_category` VALUES (4, 'std_cat004', '信用等级', 'system_004', 1, 1, NULL, NULL, '1004-0c5d-432e-711f-2a1908765432', NULL, '2024-02-02 09:30:00', NULL, '初始创建', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 14:06:31', '2026-02-12 14:06:31');
INSERT INTO `eval_standard_category` VALUES (5, 'std_cat005', '能力等级', 'system_005', 1, 1, NULL, NULL, '1005-1d4e-410f-6990-190876543210', NULL, '2024-02-02 09:40:00', NULL, '初始创建', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 14:06:31', '2026-02-12 14:06:31');

-- ----------------------------
-- Table structure for eval_standard_item
-- ----------------------------
DROP TABLE IF EXISTS `eval_standard_item`;
CREATE TABLE `eval_standard_item`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `standard_item_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标准项ID（UUID）',
  `standard_category_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标准分类ID（关联eval_standard_category.standard_category_id）',
  `grade` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标准项等级',
  `score_range` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分数范围',
  `sort_no` int NULL DEFAULT NULL COMMENT '排序序号',
  `create_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人ID，关联sys_user.user_id',
  `update_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人ID，关联sys_user.user_id',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间（业务字段）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间（业务字段）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '标准项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_standard_item
-- ----------------------------
INSERT INTO `eval_standard_item` VALUES (1, 'std_item001', 'std_cat001', '优秀', '90-100', 1, NULL, NULL, '2024-02-02 09:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 14:06:31', '2026-02-12 14:06:31');
INSERT INTO `eval_standard_item` VALUES (2, 'std_item002', 'std_cat002', '良好', '75-89', 2, NULL, NULL, '2024-02-02 09:55:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 14:06:31', '2026-02-12 14:06:31');
INSERT INTO `eval_standard_item` VALUES (3, 'std_item003', 'std_cat003', '合格', '60-74', 3, NULL, NULL, '2024-02-02 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 14:06:31', '2026-02-12 14:06:31');
INSERT INTO `eval_standard_item` VALUES (4, 'std_item004', 'std_cat004', '待改进', '40-59', 4, NULL, NULL, '2024-02-02 10:05:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 14:06:31', '2026-02-12 14:06:31');
INSERT INTO `eval_standard_item` VALUES (5, 'std_item005', 'std_cat005', '不合格', '0-39', 5, NULL, NULL, '2024-02-02 10:10:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 14:06:31', '2026-02-12 14:06:31');

-- ----------------------------
-- Table structure for eval_subject
-- ----------------------------
DROP TABLE IF EXISTS `eval_subject`;
CREATE TABLE `eval_subject`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `subject_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评价主体ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '主体名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '主体编码',
  `subject_type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '主体类型ID（关联sys_subject_type.type_id）',
  `contact_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系人ID（关联sys_user.user_id）',
  `use_count` int NULL DEFAULT NULL COMMENT '使用次数',
  `member_count` int NULL DEFAULT NULL COMMENT '成员数量',
  `status_id` int NULL DEFAULT NULL COMMENT '状态ID（关联sys_status.status_id）',
  `update_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人ID，关联sys_user.user_id',
  `create_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人ID（关联sys_user.user_id）',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间（业务字段）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间（业务字段）',
  `change_log` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '变更日志',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '评价主体表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_subject
-- ----------------------------
INSERT INTO `eval_subject` VALUES (1, 'sub001-3a4b-567c-890d-765432190876', '技术部评价组', 'TECH_001', 'st001-2f3e-456d-876c-543b2a190876', '1001-7f9d-499a-b99c-8e7d6f8c7b6a', NULL, 3, 1, NULL, '1001-7f9d-499a-b99c-8e7d6f8c7b6a', '2024-01-10 09:30:00', NULL, '初始创建，成员3人', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 10:23:02', '2026-02-11 14:50:03');
INSERT INTO `eval_subject` VALUES (2, 'sub002-4b5c-678d-901e-876543201987', '自动评价系统A', 'SYS_A_001', 'st002-3e4f-567d-987c-654b3a201987', '1002-8a7b-476c-955d-4c3b2a190876', NULL, 0, 1, NULL, '1001-7f9d-499a-b99c-8e7d6f8c7b6a', '2024-01-10 10:15:00', NULL, '初始创建，系统自动评价', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 10:23:02', '2026-02-11 14:50:12');
INSERT INTO `eval_subject` VALUES (3, 'sub003-5c6d-789e-012f-987654312098', '第三方审核机构X', 'THIRD_X_01', 'st003-4f5e-678d-098c-765b4a312098', '1003-9b6c-454d-833e-3b2a19087654', NULL, 2, 2, NULL, '1002-8a7b-476c-955d-4c3b2a190876', '2024-01-10 14:20:00', NULL, '初始创建，后因合作到期停用', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 10:23:02', '2026-02-11 14:50:20');
INSERT INTO `eval_subject` VALUES (4, 'sub004-6d7e-890f-123a-098765423109', '运营混合评价组', 'OP_MIX_001', 'st004-5e6f-789d-109c-876b5a423109', '1004-0c5d-432e-711f-2a1908765432', NULL, 4, 1, NULL, '1002-8a7b-476c-955d-4c3b2a190876', '2024-01-10 16:40:00', NULL, '初始创建，人工+系统混合模式', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 10:23:02', '2026-02-11 14:50:30');
INSERT INTO `eval_subject` VALUES (5, 'sub005-7e8f-901a-234b-109876534210', '春节专项评价组', 'SPRING_001', 'st005-6f7e-890d-210c-987b6a534210', '1005-1d4e-410f-6990-190876543210', NULL, 5, 2, NULL, '1003-9b6c-454d-833e-3b2a19087654', '2024-01-10 11:50:00', NULL, '春节专项任务，任务结束后停用', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 10:23:02', '2026-02-11 14:50:33');

-- ----------------------------
-- Table structure for eval_subject_member
-- ----------------------------
DROP TABLE IF EXISTS `eval_subject_member`;
CREATE TABLE `eval_subject_member`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评价主体成员ID（UUID）',
  `subject_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评价主体ID（关联eval_subject.subject_id）',
  `user_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '成员用户ID（关联sys_user.user_id）',
  `exit_time` datetime(0) NULL DEFAULT NULL COMMENT '退出时间，未退出为空',
  `join_time` datetime(0) NULL DEFAULT NULL COMMENT '加入时间',
  `status_id` int NULL DEFAULT NULL COMMENT '状态ID（关联sys_status.status_id）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间（业务字段）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '评价主体成员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_subject_member
-- ----------------------------
INSERT INTO `eval_subject_member` VALUES (1, 'mem001-8f9a-012b-345c-210987654321', 'sub001-3a4b-567c-890d-765432190876', '1001-7f9d-499a-b99c-8e7d6f8c7b6a', NULL, '2024-01-10 10:00:00', 1, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 10:24:23', '2026-02-11 10:24:23');
INSERT INTO `eval_subject_member` VALUES (2, 'mem002-9a0b-123c-456d-321098765432', 'sub001-3a4b-567c-890d-765432190876', '1002-8a7b-476c-955d-4c3b2a190876', NULL, '2024-01-10 10:10:00', 1, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 10:24:23', '2026-02-11 10:24:23');
INSERT INTO `eval_subject_member` VALUES (3, 'mem003-0b1c-234d-567e-432109876543', 'sub001-3a4b-567c-890d-765432190876', '1003-9b6c-454d-833e-3b2a19087654', NULL, '2024-01-10 10:20:00', 1, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 10:24:23', '2026-02-11 10:24:23');
INSERT INTO `eval_subject_member` VALUES (4, 'mem004-1c2d-345e-678f-543210987654', 'sub003-5c6d-789e-012f-987654312098', '1004-0c5d-432e-711f-2a1908765432', NULL, '2024-01-10 15:00:00', 2, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 10:24:23', '2026-02-11 10:24:23');
INSERT INTO `eval_subject_member` VALUES (5, 'mem005-2d3e-456f-789a-654321098765', 'sub003-5c6d-789e-012f-987654312098', '1005-1d4e-410f-6990-190876543210', NULL, '2024-01-10 15:10:00', 2, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 10:24:23', '2026-02-11 10:24:23');

-- ----------------------------
-- Table structure for eval_task
-- ----------------------------
DROP TABLE IF EXISTS `eval_task`;
CREATE TABLE `eval_task`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `task_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评价任务ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '任务名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '任务编码',
  `template_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联模板ID（关联eval_task_template.template_id）',
  `object_scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评价对象范围',
  `scope_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评价对象范围ID（关联sys_scope.scope_id）',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '任务开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '任务结束时间',
  `collect_type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '数据采集方式ID（关联sys_collect_type.type_id）',
  `object_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评价对象ID（关联eval_object.object_id）',
  `total_count` int NULL DEFAULT NULL COMMENT '总对象数',
  `completed_count` int NULL DEFAULT NULL COMMENT '已完成对象数',
  `uncompleted_object` int NULL DEFAULT NULL COMMENT '未完成对象数',
  `completion_rate` decimal(5, 2) NULL DEFAULT NULL COMMENT '完成率（如100.00）',
  `cancel_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '取消操作人，关联sys_user.user_id',
  `cancel_time` datetime(0) NULL DEFAULT NULL COMMENT '取消时间',
  `cancel_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '取消原因',
  `original_end_time` datetime(0) NULL DEFAULT NULL COMMENT '原结束时间',
  `status_id` int NULL DEFAULT NULL COMMENT '任务状态ID（关联sys_task_status.status_id）',
  `create_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人ID（关联sys_user.user_id）',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间（业务字段）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间（业务字段）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint(20) UNSIGNED ZEROFILL NULL DEFAULT 00000000000000000001 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '评价任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_task
-- ----------------------------
INSERT INTO `eval_task` VALUES (1, 'task001-1111-1111-1111-111111111111', '北京市朝阳区政务中心一季度评价', 'TASK_GOV_Q1_2024', 'tmpl001-1111-1111-1111-111111111111', NULL, 'scope001-1111-1111-1111-111111111111', '2024-03-01 00:00:00', '2024-03-31 23:59:59', 'colle002-2222-2222-2222-222222222222', NULL, 1, 0, NULL, 0.00, NULL, NULL, NULL, NULL, 1, '1001-7f9d-499a-b99c-8e7d6f8c7b6a', '2024-02-01 14:30:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 00000000000000000001, '2026-02-12 11:59:43', '2026-02-12 11:59:43');
INSERT INTO `eval_task` VALUES (2, 'task002-2222-2222-2222-222222222222', '上海浦东人民医院年度考核', 'TASK_HOSP_YEAR_2024', 'tmpl002-2222-2222-2222-222222222222', NULL, 'scope001-1111-1111-1111-111111111111', '2024-01-01 00:00:00', '2024-12-31 23:59:59', 'colle004-4444-4444-4444-444444444444', NULL, 1, 1, NULL, 100.00, NULL, NULL, NULL, NULL, 3, '1002-8a7b-476c-955d-4c3b2a190876', '2024-01-01 09:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 00000000000000000001, '2026-02-12 11:59:43', '2026-02-12 11:59:43');
INSERT INTO `eval_task` VALUES (3, 'task003-3333-3333-3333-333333333333', '广汽集团半年经营评价', 'TASK_AUTO_H1_2024', 'tmpl003-3333-3333-3333-333333333333', NULL, 'scope001-1111-1111-1111-111111111111', '2024-07-01 00:00:00', '2024-12-31 23:59:59', 'colle001-1111-1111-1111-111111111111', NULL, 1, 0, NULL, 0.00, NULL, NULL, NULL, NULL, 1, '1003-9b6c-454d-833e-3b2a19087654', '2024-02-01 14:40:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 00000000000000000001, '2026-02-12 11:59:43', '2026-02-12 11:59:43');
INSERT INTO `eval_task` VALUES (4, 'task004-4444-4444-4444-444444444444', '成都腾讯信用月度监测', 'TASK_TX_MON_202402', 'tmpl004-4444-4444-4444-444444444444', NULL, 'scope001-1111-1111-1111-111111111111', '2024-02-01 00:00:00', '2024-02-29 23:59:59', 'colle003-3333-3333-3333-333333333333', NULL, 1, 1, NULL, 100.00, NULL, NULL, NULL, NULL, 3, '1004-0c5d-432e-711f-2a1908765432', '2024-02-01 08:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 00000000000000000001, '2026-02-12 11:59:43', '2026-02-12 11:59:43');
INSERT INTO `eval_task` VALUES (5, 'task005-5555-5555-5555-555555555555', '浙江慈善总会能力评估', 'TASK_CHARITY_2024', 'tmpl005-5555-5555-5555-555555555555', NULL, 'scope001-1111-1111-1111-111111111111', '2024-02-15 00:00:00', '2024-03-15 23:59:59', 'colle005-5555-5555-5555-555555555555', NULL, 1, 0, NULL, 0.00, NULL, NULL, NULL, NULL, 1, '1005-1d4e-410f-6990-190876543210', '2024-02-01 14:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 00000000000000000001, '2026-02-12 11:59:43', '2026-02-12 11:59:43');

-- ----------------------------
-- Table structure for eval_task_template
-- ----------------------------
DROP TABLE IF EXISTS `eval_task_template`;
CREATE TABLE `eval_task_template`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `template_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评价任务模板ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '模板名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '模板编码',
  `object_type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '适用对象类型ID（关联sys_object_type.type_id）',
  `system_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联指标体系ID（关联eval_index_system.system_id）',
  `subject_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评价主体ID（关联eval_subject.subject_id）',
  `cycle_type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '任务周期ID（关联sys_cycle_type.type_id）',
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述信息',
  `use_count` int NULL DEFAULT NULL COMMENT '使用次数',
  `last_use_time` datetime(0) NULL DEFAULT NULL COMMENT '最近使用时间',
  `status_id` int NULL DEFAULT NULL COMMENT '状态ID（关联sys_status.status_id）',
  `create_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人ID（关联sys_user.user_id）',
  `update_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人ID（关联sys_user.user_id）',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间（业务字段）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间（业务字段）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '评价任务模板表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_task_template
-- ----------------------------
INSERT INTO `eval_task_template` VALUES (1, 'tmpl001-1111-1111-1111-111111111111', '政府部门季度评价模板', 'TMPL_GOV_Q1', 'obj_type_001', 'system_001', 'sub001-3a4b-567c-890d-765432190876', 'cycle002-2222-2222-2222-222222222222', '针对政府部门的季度评价', 0, NULL, 1, '1001-7f9d-499a-b99c-8e7d6f8c7b6a', NULL, '2024-02-01 13:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:52:52', '2026-02-12 11:52:52');
INSERT INTO `eval_task_template` VALUES (2, 'tmpl002-2222-2222-2222-222222222222', '事业单位年度考核模板', 'TMPL_PUB_YEAR', 'obj_type_002', 'system_002', 'sub002-4b5c-678d-901e-876543201987', 'cycle004-4444-4444-4444-444444444444', '事业单位年度综合考核', 0, NULL, 1, '1002-8a7b-476c-955d-4c3b2a190876', NULL, '2024-02-01 13:10:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:52:52', '2026-02-12 11:52:52');
INSERT INTO `eval_task_template` VALUES (3, 'tmpl003-3333-3333-3333-333333333333', '国企经营业绩半年评价', 'TMPL_STATE_H1', 'obj_type_003', 'system_003', 'sub003-5c6d-789e-012f-987654312098', 'cycle003-3333-3333-3333-333333333333', '国有企业经营业绩半年评价', 0, NULL, 2, '1003-9b6c-454d-833e-3b2a19087654', NULL, '2024-02-01 13:20:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:52:52', '2026-02-12 11:52:52');
INSERT INTO `eval_task_template` VALUES (4, 'tmpl004-4444-4444-4444-444444444444', '民营企业信用月度监测', 'TMPL_PRI_MON', 'obj_type_004', 'system_004', 'sub004-6d7e-890f-123a-098765423109', 'cycle001-1111-1111-1111-111111111111', '民营企业信用状况月度监测', 0, NULL, 1, '1004-0c5d-432e-711f-2a1908765432', NULL, '2024-02-01 13:30:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:52:52', '2026-02-12 11:52:52');
INSERT INTO `eval_task_template` VALUES (5, 'tmpl005-5555-5555-5555-555555555555', '社会组织能力临时评估', 'TMPL_SOC_TEMP', 'obj_type_005', 'system_005', 'sub005-7e8f-901a-234b-109876534210', 'cycle005-5555-5555-5555-555555555555', '社会组织能力临时评估', 0, NULL, 1, '1005-1d4e-410f-6990-190876543210', NULL, '2024-02-01 13:40:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:52:52', '2026-02-12 11:52:52');

-- ----------------------------
-- Table structure for eval_veto_item
-- ----------------------------
DROP TABLE IF EXISTS `eval_veto_item`;
CREATE TABLE `eval_veto_item`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `veto_item_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '否决项ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '否决项名称',
  `object_type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '适用对象类型ID（关联sys_object_type.type_id）',
  `condition` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '否决条件',
  `valid_cycle` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '生效周期',
  `count` int NULL DEFAULT NULL COMMENT '否决项数量',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间（业务字段）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间（业务字段）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '否决项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eval_veto_item
-- ----------------------------
INSERT INTO `eval_veto_item` VALUES (1, 'veto001-1111-1111-1111-111111111111', '重大安全事故', 'obj_type_001', '发生死亡1人以上事故', '2024全年', 0, '2024-02-01 15:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:38:10', '2026-02-12 12:38:10');
INSERT INTO `eval_veto_item` VALUES (2, 'veto002-2222-2222-2222-222222222222', '医疗事故', 'obj_type_002', '二级以上医疗事故', '2024全年', 0, '2024-02-01 15:10:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:38:10', '2026-02-12 12:38:10');
INSERT INTO `eval_veto_item` VALUES (3, 'veto003-3333-3333-3333-333333333333', '国有资产流失', 'obj_type_003', '造成国有资产损失超100万', '2024全年', 0, '2024-02-01 15:20:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:38:10', '2026-02-12 12:38:10');
INSERT INTO `eval_veto_item` VALUES (4, 'veto004-4444-4444-4444-444444444444', '严重失信', 'obj_type_004', '被列入失信被执行人', '2024全年', 0, '2024-02-01 15:30:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:38:10', '2026-02-12 12:38:10');
INSERT INTO `eval_veto_item` VALUES (5, 'veto005-5555-5555-5555-555555555555', '违规募捐', 'obj_type_005', '未按规定进行募捐备案', '2024全年', 0, '2024-02-01 15:40:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:38:10', '2026-02-12 12:38:10');

-- ----------------------------
-- Table structure for inspect_record
-- ----------------------------
DROP TABLE IF EXISTS `inspect_record`;
CREATE TABLE `inspect_record`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `record_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '考察记录UUID',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '记录编号',
  `plan_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联考察计划ID',
  `object_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '考察对象ID',
  `inspect_by` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '考察人员ID（多个用逗号分隔）',
  `inspect_time` datetime(0) NULL DEFAULT NULL COMMENT '考察时间',
  `total_score` decimal(10, 2) NULL DEFAULT NULL COMMENT '考察得分',
  `final_score` decimal(10, 2) NULL DEFAULT NULL COMMENT '最终考察得分',
  `problem_desc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '问题描述摘要',
  `photo_count` int NULL DEFAULT NULL COMMENT '照片数量',
  `status` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态',
  `submit_time` datetime(0) NULL DEFAULT NULL COMMENT '提交时间',
  `audit_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `reject_opinion` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '驳回意见摘要',
  `draft_time` datetime(0) NULL DEFAULT NULL COMMENT '草稿保存时间',
  `last_edit_time` datetime(0) NULL DEFAULT NULL COMMENT '最后编辑时间',
  `last_edit_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最后编辑人',
  `photo_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '照片上传状态：未上传/部分上传/全部上传',
  `recall_count` int NULL DEFAULT NULL COMMENT '撤回次数',
  `last_recall_time` datetime(0) NULL DEFAULT NULL COMMENT '最后撤回时间',
  `wait_audit_hour` decimal(5, 1) NULL DEFAULT NULL COMMENT '待审核时长（小时）',
  `data_sync_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据同步状态：已同步/同步中/未同步',
  `sync_time` datetime(0) NULL DEFAULT NULL COMMENT '同步时间',
  `resubmit_count` int NULL DEFAULT NULL COMMENT '重新提交次数',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '考察记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inspect_record
-- ----------------------------

-- ----------------------------
-- Table structure for inspection_plan
-- ----------------------------
DROP TABLE IF EXISTS `inspection_plan`;
CREATE TABLE `inspection_plan`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `plan_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '考察计划ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '计划名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '计划编码',
  `task_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联评价任务ID（关联eval_task.task_id）',
  `inspection_time` datetime(0) NULL DEFAULT NULL COMMENT '考察时间',
  `inspection_type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '考察方式ID（关联sys_inspection_type.type_id）',
  `notify_status_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通知状态ID（关联sys_notify_status.status_id）',
  `confirm_count` int NULL DEFAULT NULL COMMENT '确认人数',
  `status_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '计划状态ID（关联sys_plan_status.status_id）',
  `create_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人ID（关联sys_user.user_id）',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间（业务字段）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间（业务字段）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '考察计划表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inspection_plan
-- ----------------------------
INSERT INTO `inspection_plan` VALUES (1, 'plan001-1111-1111-1111-111111111111', '朝阳政务中心现场考察', 'PLAN_GOV_01', 'task001-1111-1111-1111-111111111111', '2024-03-20 09:00:00', 'insp_type001', 'notify001', 0, 'plan_status_001', '1001-7f9d-499a-b99c-8e7d6f8c7b6a', '2024-02-20 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:04:12', '2026-02-12 13:04:12');
INSERT INTO `inspection_plan` VALUES (2, 'plan002-2222-2222-2222-222222222222', '浦东人民医院暗访', 'PLAN_HOSP_01', 'task002-2222-2222-2222-222222222222', '2024-10-15 14:00:00', 'insp_type002', 'notify003', 3, 'plan_status_004', '1002-8a7b-476c-955d-4c3b2a190876', '2024-10-01 09:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:04:12', '2026-02-12 13:04:12');
INSERT INTO `inspection_plan` VALUES (3, 'plan003-3333-3333-3333-333333333333', '广汽集团座谈', 'PLAN_AUTO_01', 'task003-3333-3333-3333-333333333333', '2024-12-05 10:00:00', 'insp_type003', 'notify001', 0, 'plan_status_001', '1003-9b6c-454d-833e-3b2a19087654', '2024-11-20 11:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:04:12', '2026-02-12 13:04:12');
INSERT INTO `inspection_plan` VALUES (4, 'plan004-4444-4444-4444-444444444444', '腾讯信用资料审查', 'PLAN_TX_01', 'task004-4444-4444-4444-444444444444', '2024-02-20 09:30:00', 'insp_type004', 'notify003', 1, 'plan_status_004', '1004-0c5d-432e-711f-2a1908765432', '2024-02-15 14:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:04:12', '2026-02-12 13:04:12');
INSERT INTO `inspection_plan` VALUES (5, 'plan005-5555-5555-5555-555555555555', '浙江慈善总会远程考察', 'PLAN_CHARITY_01', 'task005-5555-5555-5555-555555555555', '2024-03-01 15:00:00', 'insp_type005', 'notify001', 0, 'plan_status_001', '1005-1d4e-410f-6990-190876543210', '2024-02-25 16:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:04:12', '2026-02-12 13:04:12');

-- ----------------------------
-- Table structure for iot_device
-- ----------------------------
DROP TABLE IF EXISTS `iot_device`;
CREATE TABLE `iot_device`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `device_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '设备ID（UUID）',
  `device_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '设备名称',
  `device_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '设备类型',
  `device_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '设备编码',
  `last_sync_time` datetime(0) NULL DEFAULT NULL COMMENT '最近同步时间',
  `device_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '设备安装地址',
  `status_id` int NULL DEFAULT NULL COMMENT '设备状态ID（关联sys_status.status_id）',
  `install_time` datetime(0) NULL DEFAULT NULL COMMENT '安装时间',
  `maintain_time` datetime(0) NULL DEFAULT NULL COMMENT '最近维护时间',
  `update_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人ID，关联sys_user.user_id',
  `create_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人ID（关联sys_user.user_id）',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间（业务字段）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '设备表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of iot_device
-- ----------------------------
INSERT INTO `iot_device` VALUES (1, 'dev001-1111-1111-1111-111111111111', '环境监测仪A1', 'ENV_SENSOR', NULL, NULL, '北京市朝阳区', 1, '2024-01-15 08:00:00', '2024-02-01 09:00:00', NULL, '1001-7f9d-499a-b99c-8e7d6f8c7b6a', '2024-01-15 08:00:00', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:36:32', '2026-02-12 11:36:32');
INSERT INTO `iot_device` VALUES (2, 'dev002-2222-2222-2222-222222222222', '客流计数器B2', 'PERSON_FLOW', NULL, NULL, '上海市浦东新区', 1, '2024-01-16 09:00:00', '2024-02-02 10:00:00', NULL, '1002-8a7b-476c-955d-4c3b2a190876', '2024-01-16 09:00:00', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:36:32', '2026-02-12 11:36:32');
INSERT INTO `iot_device` VALUES (3, 'dev003-3333-3333-3333-333333333333', '水质检测仪C3', 'WATER_QUALITY', NULL, NULL, '广州市天河区', 1, '2024-01-17 10:00:00', '2024-02-03 11:00:00', NULL, '1003-9b6c-454d-833e-3b2a19087654', '2024-01-17 10:00:00', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:36:32', '2026-02-12 11:36:32');
INSERT INTO `iot_device` VALUES (4, 'dev004-4444-4444-4444-444444444444', '空气质量仪D4', 'AIR_QUALITY', NULL, NULL, '成都市武侯区', 1, '2024-01-18 11:00:00', '2024-02-04 14:00:00', NULL, '1004-0c5d-432e-711f-2a1908765432', '2024-01-18 11:00:00', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:36:32', '2026-02-12 11:36:32');
INSERT INTO `iot_device` VALUES (5, 'dev005-5555-5555-5555-555555555555', '噪音监测仪E5', 'NOISE_SENSOR', NULL, NULL, '杭州市西湖区', 1, '2024-01-19 14:00:00', '2024-02-05 15:00:00', NULL, '1005-1d4e-410f-6990-190876543210', '2024-01-19 14:00:00', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:36:32', '2026-02-12 11:36:32');

-- ----------------------------
-- Table structure for platform_report
-- ----------------------------
DROP TABLE IF EXISTS `platform_report`;
CREATE TABLE `platform_report`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `report_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上报UUID',
  `batch_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上报批次号',
  `task_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联评价任务ID',
  `report_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上报人',
  `report_time` datetime(0) NULL DEFAULT NULL COMMENT '上报时间',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上报文件名称',
  `data_count` int NULL DEFAULT NULL COMMENT '数据条数',
  `success_count` int NULL DEFAULT NULL COMMENT '成功条数',
  `fail_count` int NULL DEFAULT NULL COMMENT '失败条数',
  `status` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据状态',
  `check_time` datetime(0) NULL DEFAULT NULL COMMENT '校验时间',
  `check_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '校验操作人',
  `error_file_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '错误文件下载链接',
  `template_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '模板下载状态：可下载/已下载',
  `file_preview_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件预览链接：可预览/无',
  `reupload_count` int NULL DEFAULT NULL COMMENT '重新上传次数',
  `last_reupload_time` datetime(0) NULL DEFAULT NULL COMMENT '最近重新上传时间',
  `fail_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '失败原因摘要',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '平台上报表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of platform_report
-- ----------------------------

-- ----------------------------
-- Table structure for real_time_access_rule
-- ----------------------------
DROP TABLE IF EXISTS `real_time_access_rule`;
CREATE TABLE `real_time_access_rule`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `rule_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规则UUID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规则名称',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规则编码',
  `task_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联评价任务ID',
  `index_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联指标项ID',
  `device_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据来源设备ID',
  `sync_freq_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '同步频率ID',
  `clean_rule` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '数据清洗规则',
  `status` bigint NULL DEFAULT NULL COMMENT '状态',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间（原create_time）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间（原update_time）',
  `last_sync_time` datetime(0) NULL DEFAULT NULL COMMENT '最近同步时间',
  `sync_success_rate` decimal(5, 2) NULL DEFAULT NULL COMMENT '同步成功率',
  `today_sync_count` int NULL DEFAULT NULL COMMENT '今日同步次数',
  `total_sync_count` bigint NULL DEFAULT NULL COMMENT '累计同步次数',
  `stop_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '停用原因',
  `stop_time` datetime(0) NULL DEFAULT NULL COMMENT '停用时间',
  `stop_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '停用操作人',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '实时接入规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of real_time_access_rule
-- ----------------------------

-- ----------------------------
-- Table structure for real_time_sync_log
-- ----------------------------
DROP TABLE IF EXISTS `real_time_sync_log`;
CREATE TABLE `real_time_sync_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `log_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '日志UUID（业务主键）',
  `rule_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '实时接入规则ID，关联real_time_access_rule.rule_id',
  `task_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联评价任务ID，关联eval_task.task_id',
  `index_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联指标项ID，关联eval_index_item.item_id',
  `device_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '数据来源设备ID，关联iot_device.device_id',
  `data_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '数据值',
  `sync_time` datetime(0) NULL DEFAULT NULL COMMENT '同步时间',
  `sync_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '同步方式：自动/手动',
  `clean_result` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '数据清洗结果：已清洗/无需清洗',
  `store_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '存储状态',
  `fail_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '失败原因',
  `retry_count` int NULL DEFAULT 0 COMMENT '重试次数',
  `last_retry_time` datetime(0) NULL DEFAULT NULL COMMENT '最近重试时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '实时同步日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of real_time_sync_log
-- ----------------------------
INSERT INTO `real_time_sync_log` VALUES (1, 'LOG001-1111-1111-1111-111111111111', 'drule001-1111-1111-1111-111111111111', 'task001-1111-1111-1111-111111111111', 'item_001', 'dev001-1111-1111-1111-111111111111', '98', '2024-03-15 10:30:00', '自动', '无需清洗', '成功', '', 0, NULL, NULL, NULL, NULL, NULL, '1001-7f9d-499a-b99c-8e7d6f8c7b6a', '1001-7f9d-499a-b99c-8e7d6f8c7b6a', b'0', 1, '2026-02-13 14:14:45', '2026-02-13 14:14:45');
INSERT INTO `real_time_sync_log` VALUES (2, 'LOG002-2222-2222-2222-222222222222', 'drule002-2222-2222-2222-222222222222', 'task002-2222-2222-2222-222222222222', 'item_002', 'dev002-2222-2222-2222-222222222222', '92', '2024-11-20 14:20:00', '自动', '已清洗', '成功', '', 0, NULL, NULL, NULL, NULL, NULL, '1002-8a7b-476c-955d-4c3b2a190876', '1002-8a7b-476c-955d-4c3b2a190876', b'0', 1, '2026-02-13 14:14:45', '2026-02-13 14:14:45');
INSERT INTO `real_time_sync_log` VALUES (3, 'LOG003-3333-3333-3333-333333333333', 'drule003-3333-3333-3333-333333333333', 'task003-3333-3333-3333-333333333333', 'item_003', 'dev003-3333-3333-3333-333333333333', '15', '2024-12-10 09:15:00', '手动', '已清洗', '失败', '网络超时，无法连接物联网设备采集数据', 1, '2024-12-10 09:18:00', NULL, NULL, NULL, NULL, '1003-9b6c-454d-833e-3b2a19087654', '1003-9b6c-454d-833e-3b2a19087654', b'0', 1, '2026-02-13 14:14:45', '2026-02-13 14:14:45');
INSERT INTO `real_time_sync_log` VALUES (4, 'LOG004-4444-4444-4444-444444444444', 'drule004-4444-4444-4444-444444444444', 'task004-4444-4444-4444-444444444444', 'item_004', 'dev004-4444-4444-4444-444444444444', '58', '2024-02-25 16:00:00', '自动', '无需清洗', '成功', '', 0, NULL, NULL, NULL, NULL, NULL, '1004-0c5d-432e-711f-2a1908765432', '1004-0c5d-432e-711f-2a1908765432', b'0', 1, '2026-02-13 14:14:45', '2026-02-13 14:14:45');
INSERT INTO `real_time_sync_log` VALUES (5, 'LOG005-5555-5555-5555-555555555555', 'drule005-5555-5555-5555-555555555555', 'task005-5555-5555-5555-555555555555', 'item_005', 'dev005-5555-5555-5555-555555555555', '88', '2024-03-10 11:00:00', '手动', '已清洗', '失败', '设备返回数据格式错误，缺少评分维度标识', 3, '2024-03-10 11:08:00', NULL, NULL, NULL, NULL, '1005-1d4e-410f-6990-190876543210', '1005-1d4e-410f-6990-190876543210', b'0', 1, '2026-02-13 14:14:45', '2026-02-13 14:14:45');

-- ----------------------------
-- Table structure for report_data
-- ----------------------------
DROP TABLE IF EXISTS `report_data`;
CREATE TABLE `report_data`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `report_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上报数据ID（UUID）',
  `task_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联评价任务ID（关联eval_task.task_id）',
  `object_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评价对象ID（关联eval_object.object_id）',
  `index_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '指标项ID（关联eval_index_item.item_id）',
  `data_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '数据值',
  `report_time` datetime(0) NULL DEFAULT NULL COMMENT '上报时间',
  `report_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上报人ID（关联sys_user.user_id）',
  `data_status_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '数据状态ID（关联sys_data_status.status_id）',
  `verify_result_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '校验结果ID（关联sys_verify_result.result_id）',
  `error_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '错误原因',
  `process_time` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '上报数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of report_data
-- ----------------------------
INSERT INTO `report_data` VALUES (1, 'rdata001-1111-1111-1111-111111111111', 'task001-1111-1111-1111-111111111111', 'obj001-1111-1111-1111-111111111111', 'item_001', '98', '2024-03-15 10:30:00', '1001-7f9d-499a-b99c-8e7d6f8c7b6a', 'data_status002', 'verify001', NULL, '2024-03-15 10:31:00', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:55:16', '2026-02-12 12:55:16');
INSERT INTO `report_data` VALUES (2, 'rdata002-2222-2222-2222-222222222222', 'task002-2222-2222-2222-222222222222', 'obj002-2222-2222-2222-222222222222', 'item_002', '92', '2024-11-20 14:20:00', '1002-8a7b-476c-955d-4c3b2a190876', 'data_status002', 'verify001', NULL, '2024-11-20 14:21:00', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:55:16', '2026-02-12 12:55:16');
INSERT INTO `report_data` VALUES (3, 'rdata003-3333-3333-3333-333333333333', 'task003-3333-3333-3333-333333333333', 'obj003-3333-3333-3333-333333333333', 'item_003', '15', '2024-12-10 09:15:00', '1003-9b6c-454d-833e-3b2a19087654', 'data_status002', 'verify001', NULL, '2024-12-10 09:16:00', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:55:16', '2026-02-12 12:55:16');
INSERT INTO `report_data` VALUES (4, 'rdata004-4444-4444-4444-444444444444', 'task004-4444-4444-4444-444444444444', 'obj004-4444-4444-4444-444444444444', 'item_004', '58', '2024-02-25 16:00:00', '1004-0c5d-432e-711f-2a1908765432', 'data_status002', 'verify001', NULL, '2024-02-25 16:01:00', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:55:16', '2026-02-12 12:55:16');
INSERT INTO `report_data` VALUES (5, 'rdata005-5555-5555-5555-555555555555', 'task005-5555-5555-5555-555555555555', 'obj005-5555-5555-5555-555555555555', 'item_005', '88', '2024-03-10 11:00:00', '1005-1d4e-410f-6990-190876543210', 'data_status001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:55:16', '2026-02-12 12:55:16');

-- ----------------------------
-- Table structure for result_archive
-- ----------------------------
DROP TABLE IF EXISTS `result_archive`;
CREATE TABLE `result_archive`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `archive_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '结果存档ID（UUID）',
  `archive_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '存档编号',
  `task_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联评价任务ID（关联eval_task.task_id）',
  `object_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评价对象ID（关联eval_object.object_id）',
  `final_score` decimal(8, 2) NULL DEFAULT NULL COMMENT '最终得分',
  `grade_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评价等级ID（关联sys_grade.grade_id）',
  `archive_time` datetime(0) NULL DEFAULT NULL COMMENT '存档时间',
  `archive_type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '存档方式ID（关联sys_archive_type.type_id）',
  `archive_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '存档人ID（关联sys_user.user_id）',
  `attachments` json NULL COMMENT '附件列表（JSON）',
  `archive_source_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '存档来源ID（关联sys_archive_source.source_id）',
  `query_count` int NULL DEFAULT NULL COMMENT '查询次数',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '结果存档表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of result_archive
-- ----------------------------
INSERT INTO `result_archive` VALUES (6, 'arch001-1111-1111-1111-111111111111', 'ARCH-GOV-2024-001', 'task001-1111-1111-1111-111111111111', 'obj001-1111-1111-1111-111111111111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:49:57', '2026-02-12 13:49:57');
INSERT INTO `result_archive` VALUES (7, 'arch002-2222-2222-2222-222222222222', 'ARCH-HOSP-2024-001', 'task002-2222-2222-2222-222222222222', 'obj002-2222-2222-2222-222222222222', 88.00, 'grade002', '2024-12-29 10:00:00', 'archive_type001', '1002-8a7b-476c-955d-4c3b2a190876', '[\"/archive/2024/hosp_report.pdf\"]', 'archive_src001', 5, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:49:57', '2026-02-12 13:49:57');
INSERT INTO `result_archive` VALUES (8, 'arch003-3333-3333-3333-333333333333', 'ARCH-AUTO-2024-001', 'task003-3333-3333-3333-333333333333', 'obj003-3333-3333-3333-333333333333', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:49:57', '2026-02-12 13:49:57');
INSERT INTO `result_archive` VALUES (9, 'arch004-4444-4444-4444-444444444444', 'ARCH-TX-2024-002', 'task004-4444-4444-4444-444444444444', 'obj004-4444-4444-4444-444444444444', 78.00, 'grade003', '2024-03-05 09:00:00', 'archive_type003', '1004-0c5d-432e-711f-2a1908765432', '[\"https://cloud/arch/tx_credit.pdf\"]', 'archive_src002', 2, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:49:57', '2026-02-12 13:49:57');
INSERT INTO `result_archive` VALUES (10, 'arch005-5555-5555-5555-555555555555', 'ARCH-CHARITY-2024-001', 'task005-5555-5555-5555-555555555555', 'obj005-5555-5555-5555-555555555555', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:49:57', '2026-02-12 13:49:57');

-- ----------------------------
-- Table structure for result_audit
-- ----------------------------
DROP TABLE IF EXISTS `result_audit`;
CREATE TABLE `result_audit`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `audit_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '结果审核ID（UUID）',
  `audit_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审核编号',
  `task_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联评价任务ID（关联eval_task.task_id）',
  `object_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评价对象ID（关联eval_object.object_id）',
  `total_score` decimal(8, 2) NULL DEFAULT NULL COMMENT '总得分',
  `initial_grade_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '初步等级ID（关联sys_grade.grade_id）',
  `veto_check_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '否决项检查ID（关联sys_veto_check.check_id）',
  `audit_status_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审核状态ID（关联sys_audit_status.status_id）',
  `auditor_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审核人ID（关联sys_user.user_id）',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `audit_result_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审核结果ID（关联sys_audit_result.result_id）',
  `reject_reason` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '驳回原因',
  `corrected_score` decimal(8, 2) NULL DEFAULT NULL COMMENT '修正后得分',
  `corrected_grade_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '修正后等级ID（关联sys_grade.grade_id）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '结果审核表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of result_audit
-- ----------------------------
INSERT INTO `result_audit` VALUES (1, 'audit001-1111-1111-1111-111111111111', 'AUDIT-GOV-2024-001', 'task001-1111-1111-1111-111111111111', 'obj001-1111-1111-1111-111111111111', 95.50, 'grade001', NULL, 'audit_status001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:27:28', '2026-02-12 13:27:28');
INSERT INTO `result_audit` VALUES (2, 'audit002-2222-2222-2222-222222222222', 'AUDIT-HOSP-2024-001', 'task002-2222-2222-2222-222222222222', 'obj002-2222-2222-2222-222222222222', 88.00, 'grade002', NULL, 'audit_status003', '1003-9b6c-454d-833e-3b2a19087654', '2024-12-21 10:00:00', 'audit_res001', NULL, 88.00, 'grade002', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:27:28', '2026-02-12 13:27:28');
INSERT INTO `result_audit` VALUES (3, 'audit003-3333-3333-3333-333333333333', 'AUDIT-AUTO-2024-001', 'task003-3333-3333-3333-333333333333', 'obj003-3333-3333-3333-333333333333', NULL, NULL, NULL, 'audit_status001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:27:28', '2026-02-12 13:27:28');
INSERT INTO `result_audit` VALUES (4, 'audit004-4444-4444-4444-444444444444', 'AUDIT-TX-2024-002', 'task004-4444-4444-4444-444444444444', 'obj004-4444-4444-4444-444444444444', 82.50, 'grade003', NULL, 'audit_status003', '1005-1d4e-410f-6990-190876543210', '2024-02-26 11:00:00', 'audit_res002', '资产负债率数据存疑', 78.00, 'grade003', NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:27:28', '2026-02-12 13:27:28');
INSERT INTO `result_audit` VALUES (5, 'audit005-5555-5555-5555-555555555555', 'AUDIT-CHARITY-2024-001', 'task005-5555-5555-5555-555555555555', 'obj005-5555-5555-5555-555555555555', NULL, NULL, NULL, 'audit_status001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:27:28', '2026-02-12 13:27:28');

-- ----------------------------
-- Table structure for result_publicity
-- ----------------------------
DROP TABLE IF EXISTS `result_publicity`;
CREATE TABLE `result_publicity`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `publicity_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '结果公示ID（UUID）',
  `publicity_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '公示编号',
  `task_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联评价任务ID（关联eval_task.task_id）',
  `object_ids` json NULL COMMENT '评价对象ID集合（JSON）',
  `scope_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '公示范围ID（关联sys_scope.scope_id）',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '公示链接',
  `objection_count` int NULL DEFAULT NULL COMMENT '异议人数',
  `objection_status_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '异议处理状态ID（关联sys_objection_status.status_id）',
  `status_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '公示状态ID（关联sys_publicity_status.status_id）',
  `create_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人ID（关联sys_user.user_id）',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间（业务字段）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间（业务字段）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '结果公示表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of result_publicity
-- ----------------------------
INSERT INTO `result_publicity` VALUES (1, 'pub001-1111-1111-1111-111111111111', 'PUB-GOV-2024-001', 'task001-1111-1111-1111-111111111111', '[\"obj001-1111-1111-1111-111111111111\"]', 'scope001-1111-1111-1111-111111111111', NULL, NULL, NULL, 0, NULL, 'pub_status001', '1001-7f9d-499a-b99c-8e7d6f8c7b6a', '2024-02-01 23:30:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:31:00', '2026-02-12 13:31:00');
INSERT INTO `result_publicity` VALUES (2, 'pub002-2222-2222-2222-222222222222', 'PUB-HOSP-2024-001', 'task002-2222-2222-2222-222222222222', '[\"obj002-2222-2222-2222-222222222222\"]', 'scope001-1111-1111-1111-111111111111', '2024-12-22 00:00:00', '2024-12-28 23:59:59', 'http://publicity/hospital', 0, NULL, 'pub_status002', '1002-8a7b-476c-955d-4c3b2a190876', '2024-12-21 14:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:31:00', '2026-02-12 13:31:00');
INSERT INTO `result_publicity` VALUES (3, 'pub003-3333-3333-3333-333333333333', 'PUB-AUTO-2024-001', 'task003-3333-3333-3333-333333333333', '[\"obj003-3333-3333-3333-333333333333\"]', 'scope001-1111-1111-1111-111111111111', NULL, NULL, NULL, 0, NULL, 'pub_status001', '1003-9b6c-454d-833e-3b2a19087654', '2024-02-01 23:35:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:31:00', '2026-02-12 13:31:00');
INSERT INTO `result_publicity` VALUES (4, 'pub004-4444-4444-4444-444444444444', 'PUB-TX-2024-002', 'task004-4444-4444-4444-444444444444', '[\"obj004-4444-4444-4444-444444444444\"]', 'scope001-1111-1111-1111-111111111111', '2024-02-27 00:00:00', '2024-03-04 23:59:59', 'http://publicity/tencent', 1, 'objection_status001', 'pub_status002', '1004-0c5d-432e-711f-2a1908765432', '2024-02-26 14:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:31:00', '2026-02-12 13:31:00');
INSERT INTO `result_publicity` VALUES (5, 'pub005-5555-5555-5555-555555555555', 'PUB-CHARITY-2024-001', 'task005-5555-5555-5555-555555555555', '[\"obj005-5555-5555-5555-555555555555\"]', 'scope001-1111-1111-1111-111111111111', NULL, NULL, NULL, 0, NULL, 'pub_status001', '1005-1d4e-410f-6990-190876543210', '2024-02-01 23:40:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:31:00', '2026-02-12 13:31:00');

-- ----------------------------
-- Table structure for stat_report
-- ----------------------------
DROP TABLE IF EXISTS `stat_report`;
CREATE TABLE `stat_report`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `report_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报表UUID（主键，UUID）',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报表编号',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报表名称',
  `type_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报表类型ID（关联sys_report_type.type_id）',
  `task_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联评价任务ID（关联eval_task.task_id）',
  `dimension` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '统计维度（关联sys_stat_dimension.dimension_id）',
  `status` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报表状态（关联sys_report_status.status_id）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '生成人（关联sys_user.user_id）',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务生成时间（生成时间）',
  `export_time` datetime(0) NULL DEFAULT NULL COMMENT '导出时间',
  `export_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '导出人（关联sys_user.user_id）',
  `format` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报表格式',
  `data_update_time` datetime(0) NULL DEFAULT NULL COMMENT '数据更新时间',
  `cost_time` decimal(10, 2) NULL DEFAULT NULL COMMENT '生成耗时（秒）',
  `preview_count` int NULL DEFAULT NULL COMMENT '预览次数',
  `latest_preview_time` datetime(0) NULL DEFAULT NULL COMMENT '最新预览时间',
  `file_size` bigint NULL DEFAULT NULL COMMENT '报表文件大小（字节）',
  `data_source` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '数据来源',
  `export_count` int NULL DEFAULT NULL COMMENT '导出次数',
  `latest_export_time` datetime(0) NULL DEFAULT NULL COMMENT '最新导出时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '统计分析报表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stat_report
-- ----------------------------

-- ----------------------------
-- Table structure for survey_option
-- ----------------------------
DROP TABLE IF EXISTS `survey_option`;
CREATE TABLE `survey_option`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `option_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '选项ID（UUID）',
  `question_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '题目ID（关联survey_question.question_id）',
  `option_content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '选项内容',
  `sort_no` int NULL DEFAULT NULL COMMENT '排序序号',
  `score` int NULL DEFAULT NULL COMMENT '题目分值',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人，关联sys_user.user_id',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人，关联sys_user.user_id',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间（业务字段）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间（业务字段）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '选项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of survey_option
-- ----------------------------
INSERT INTO `survey_option` VALUES (1, 'opt001-1111-1111-1111-111111111111', 'q001-1111-1111-1111-111111111111', '非常满意', 1, NULL, NULL, NULL, '2024-02-20 09:20:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `survey_option` VALUES (2, 'opt002-1111-1111-1111-111111111111', 'q001-1111-1111-1111-111111111111', '满意', 2, NULL, NULL, NULL, '2024-02-20 09:20:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `survey_option` VALUES (3, 'opt001-2222-2222-2222-222222222222', 'q002-2222-2222-2222-222222222222', '非常专业', 1, NULL, NULL, NULL, '2024-01-01 10:20:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `survey_option` VALUES (4, 'opt002-2222-2222-2222-222222222222', 'q002-2222-2222-2222-222222222222', '比较专业', 2, NULL, NULL, NULL, '2024-01-01 10:20:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `survey_option` VALUES (5, 'opt001-3333-3333-3333-333333333333', 'q003-3333-3333-3333-333333333333', '很有信心', 1, NULL, NULL, NULL, '2024-06-15 14:20:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');

-- ----------------------------
-- Table structure for survey_question
-- ----------------------------
DROP TABLE IF EXISTS `survey_question`;
CREATE TABLE `survey_question`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `question_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '题目ID（UUID）',
  `questionnaire_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '问卷ID（关联survey_questionnaire.questionnaire_id）',
  `title` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '题目名称',
  `question_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '题目类型：单选/多选/打分题',
  `score_range` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分值设置，仅打分题',
  `score` int NULL DEFAULT NULL COMMENT '题目分值',
  `sort_no` int NULL DEFAULT NULL COMMENT '排序序号',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人，关联sys_user.user_id',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人，关联sys_user.user_id',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间（业务字段）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间（业务字段）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '题目表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of survey_question
-- ----------------------------
INSERT INTO `survey_question` VALUES (1, 'q001-1111-1111-1111-111111111111', 'ques001-1111-1111-1111-111111111111', '您对服务效率的评价？', '打分题', '1-10', NULL, 1, NULL, NULL, '2024-02-20 09:10:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `survey_question` VALUES (2, 'q002-2222-2222-2222-222222222222', 'ques002-2222-2222-2222-222222222222', '您对医生专业度的评价？', '打分题', '1-10', NULL, 1, NULL, NULL, '2024-01-01 10:10:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `survey_question` VALUES (3, 'q003-3333-3333-3333-333333333333', 'ques003-3333-3333-3333-333333333333', '您对公司前景的信心？', '打分题', '1-10', NULL, 1, NULL, NULL, '2024-06-15 14:10:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `survey_question` VALUES (4, 'q004-4444-4444-4444-444444444444', 'ques004-4444-4444-4444-444444444444', '该企业信用评级？', '打分题', '1-100', NULL, 1, NULL, NULL, '2024-02-01 08:40:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `survey_question` VALUES (5, 'q005-5555-5555-5555-555555555555', 'ques005-5555-5555-5555-555555555555', '组织治理规范性？', '打分题', '1-100', NULL, 1, NULL, NULL, '2024-02-10 11:10:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');

-- ----------------------------
-- Table structure for survey_questionnaire
-- ----------------------------
DROP TABLE IF EXISTS `survey_questionnaire`;
CREATE TABLE `survey_questionnaire`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `questionnaire_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '问卷ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '问卷名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '问卷编码',
  `task_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联评价任务ID（关联eval_task.task_id）',
  `object_scope` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调查对象范围',
  `scope_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调查对象范围ID（关联sys_scope.scope_id）',
  `issue_type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发放方式ID（关联sys_issue_type.type_id）',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `original_start_time` datetime(0) NULL DEFAULT NULL COMMENT '原开始时间',
  `original_end_time` datetime(0) NULL DEFAULT NULL COMMENT '原结束时间',
  `fill_count` int NULL DEFAULT NULL COMMENT '填写人数',
  `fill_rate` decimal(5, 2) NULL DEFAULT NULL COMMENT '填写率',
  `average_score` decimal(5, 2) NULL DEFAULT NULL COMMENT '平均得分（如95.50）',
  `final_fill_rate` decimal(5, 2) NULL DEFAULT NULL COMMENT '最终填写率',
  `final_average_score` decimal(5, 2) NULL DEFAULT NULL COMMENT '最终平均分',
  `index_value` json NULL COMMENT '指标值映射结果',
  `data_relation_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '数据关联状态：已关联评价/未关联评价',
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '问卷链接',
  `qrcode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '问卷二维码',
  `status_id` int NULL DEFAULT NULL COMMENT '问卷状态ID（关联sys_survey_status.status_id）',
  `create_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人ID（关联sys_user.user_id）',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间（业务字段）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间（业务字段）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '问卷表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of survey_questionnaire
-- ----------------------------
INSERT INTO `survey_questionnaire` VALUES (1, 'ques001-1111-1111-1111-111111111111', '政务服务中心满意度调查', 'SURV_GOV_01', 'task001-1111-1111-1111-111111111111', NULL, 'scope001-1111-1111-1111-111111111111', 'issue003-3333-3333-3333-333333333333', '2024-03-01 00:00:00', '2024-03-31 23:59:59', NULL, NULL, 120, NULL, 95.20, NULL, NULL, NULL, NULL, 'http://survey.com/gov', 'qrcode1', 2, '1001-7f9d-499a-b99c-8e7d6f8c7b6a', '2024-02-20 09:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `survey_questionnaire` VALUES (2, 'ques002-2222-2222-2222-222222222222', '医院服务质量调查', 'SURV_HOSP_01', 'task002-2222-2222-2222-222222222222', NULL, 'scope001-1111-1111-1111-111111111111', 'issue001-1111-1111-1111-111111111111', '2024-01-01 00:00:00', '2024-12-31 23:59:59', NULL, NULL, 500, NULL, 92.80, NULL, NULL, NULL, NULL, 'http://survey.com/hosp', 'qrcode2', 3, '1002-8a7b-476c-955d-4c3b2a190876', '2024-01-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `survey_questionnaire` VALUES (3, 'ques003-3333-3333-3333-333333333333', '员工敬业度调查', 'SURV_EMP_01', 'task003-3333-3333-3333-333333333333', NULL, 'scope001-1111-1111-1111-111111111111', 'issue002-2222-2222-2222-222222222222', '2024-07-01 00:00:00', '2024-12-31 23:59:59', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 'http://survey.com/emp', 'qrcode3', 1, '1003-9b6c-454d-833e-3b2a19087654', '2024-06-15 14:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `survey_questionnaire` VALUES (4, 'ques004-4444-4444-4444-444444444444', '客户信用评价', 'SURV_CREDIT_01', 'task004-4444-4444-4444-444444444444', NULL, 'scope001-1111-1111-1111-111111111111', 'issue004-4444-4444-4444-444444444444', '2024-02-01 00:00:00', '2024-02-29 23:59:59', NULL, NULL, 30, NULL, 88.50, NULL, NULL, NULL, NULL, 'http://survey.com/credit', 'qrcode4', 3, '1004-0c5d-432e-711f-2a1908765432', '2024-02-01 08:30:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `survey_questionnaire` VALUES (5, 'ques005-5555-5555-5555-555555555555', '社会组织能力自评', 'SURV_SOCIAL_01', 'task005-5555-5555-5555-555555555555', NULL, 'scope001-1111-1111-1111-111111111111', 'issue005-5555-5555-5555-555555555555', '2024-02-15 00:00:00', '2024-03-15 23:59:59', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 'http://survey.com/social', 'qrcode5', 1, '1005-1d4e-410f-6990-190876543210', '2024-02-10 11:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');

-- ----------------------------
-- Table structure for sys_appeal_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_appeal_status`;
CREATE TABLE `sys_appeal_status`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `status_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '申诉状态ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '申诉状态字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_appeal_status
-- ----------------------------
INSERT INTO `sys_appeal_status` VALUES (1, 'appeal001', '待受理', 'PENDING', '等待受理', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_appeal_status` VALUES (2, 'appeal002', '受理中', 'PROCESSING', '正在复核', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_appeal_status` VALUES (3, 'appeal003', '已复核', 'REVIEWED', '复核完成', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_appeal_status` VALUES (4, 'appeal004', '已驳回', 'REJECTED', '申诉驳回', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_appeal_status` VALUES (5, 'appeal005', '已撤回', 'WITHDRAWN', '申诉人撤回', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');

-- ----------------------------
-- Table structure for sys_appeal_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_appeal_type`;
CREATE TABLE `sys_appeal_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型ID（主键UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型名称',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型编码',
  `type_desc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间（创建时间）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间（更新时间）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '申诉类型字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_appeal_type
-- ----------------------------

-- ----------------------------
-- Table structure for sys_archive_source
-- ----------------------------
DROP TABLE IF EXISTS `sys_archive_source`;
CREATE TABLE `sys_archive_source`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `source_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '存档来源ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '来源名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '来源编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '来源描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '存档来源字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_archive_source
-- ----------------------------
INSERT INTO `sys_archive_source` VALUES (1, 'archive_src001', '评价任务', 'EVAL_TASK', '由评价任务生成', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_archive_source` VALUES (2, 'archive_src002', '结果审核', 'AUDIT', '审核完成后归档', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_archive_source` VALUES (3, 'archive_src003', '结果公示', 'PUBLICITY', '公示结束后归档', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_archive_source` VALUES (4, 'archive_src004', '申诉复核', 'APPEAL', '复核完成后归档', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_archive_source` VALUES (5, 'archive_src005', '手动归档', 'MANUAL', '手动操作归档', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');

-- ----------------------------
-- Table structure for sys_archive_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_archive_status`;
CREATE TABLE `sys_archive_status`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `status_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态ID（主键UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态名称',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态编码',
  `status_desc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间（创建时间）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间（更新时间）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '存档状态字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_archive_status
-- ----------------------------

-- ----------------------------
-- Table structure for sys_archive_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_archive_type`;
CREATE TABLE `sys_archive_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '存档方式ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '方式名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '方式编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '方式描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '存档方式字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_archive_type
-- ----------------------------
INSERT INTO `sys_archive_type` VALUES (1, 'archive_type001', '电子归档', 'ELECTRONIC', '存储至文件服务器', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_archive_type` VALUES (2, 'archive_type002', '纸质归档', 'PAPER', '打印纸质存档', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_archive_type` VALUES (3, 'archive_type003', '云存储', 'CLOUD', '上传至云存储', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_archive_type` VALUES (4, 'archive_type004', '数据库备份', 'DB_BACKUP', '备份至数据库', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_archive_type` VALUES (5, 'archive_type005', '双重归档', 'DUAL', '电子+纸质', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');

-- ----------------------------
-- Table structure for sys_area
-- ----------------------------
DROP TABLE IF EXISTS `sys_area`;
CREATE TABLE `sys_area`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `area_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '区域编码（业务主键）',
  `area_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '区域名称',
  `parent_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上级区域编码（关联sys_area.area_code）',
  `level` tinyint NULL DEFAULT NULL COMMENT '区域层级（1-省级，2-市级，3-区级/县级等）',
  `status_id` int NULL DEFAULT NULL COMMENT '状态ID（关联sys_status.status_id）',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '区域编码表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_area
-- ----------------------------
INSERT INTO `sys_area` VALUES (1, '110000', '北京市', NULL, 1, 1, '2024-02-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:36:32', '2026-02-12 11:36:32');
INSERT INTO `sys_area` VALUES (2, '310000', '上海市', NULL, 1, 1, '2024-02-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:36:32', '2026-02-12 11:36:32');
INSERT INTO `sys_area` VALUES (3, '440100', '广州市', '440000', 3, 1, '2024-02-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:36:32', '2026-02-12 11:36:32');
INSERT INTO `sys_area` VALUES (4, '510100', '成都市', '510000', 3, 1, '2024-02-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:36:32', '2026-02-12 11:36:32');
INSERT INTO `sys_area` VALUES (5, '330100', '杭州市', '330000', 3, 1, '2024-02-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:36:32', '2026-02-12 11:36:32');

-- ----------------------------
-- Table structure for sys_audit_result
-- ----------------------------
DROP TABLE IF EXISTS `sys_audit_result`;
CREATE TABLE `sys_audit_result`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `result_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审核结果ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '结果名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '结果编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '结果描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '审核结果字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_audit_result
-- ----------------------------
INSERT INTO `sys_audit_result` VALUES (1, 'audit_res001', '通过', 'PASS', '审核通过', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_audit_result` VALUES (2, 'audit_res002', '驳回', 'REJECT', '审核驳回', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_audit_result` VALUES (3, 'audit_res003', '转审', 'TRANSFER', '转交其他审核人', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_audit_result` VALUES (4, 'audit_res004', '暂缓', 'HOLD', '暂缓审核', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_audit_result` VALUES (5, 'audit_res005', '无需审核', 'NO_AUDIT', '无需审核', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');

-- ----------------------------
-- Table structure for sys_audit_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_audit_status`;
CREATE TABLE `sys_audit_status`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `status_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审核状态ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '审核状态字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_audit_status
-- ----------------------------
INSERT INTO `sys_audit_status` VALUES (1, 'audit_status001', '待审核', 'PENDING', '等待审核', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_audit_status` VALUES (2, 'audit_status002', '审核中', 'AUDITING', '正在审核', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_audit_status` VALUES (3, 'audit_status003', '已审核', 'AUDITED', '审核完成', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_audit_status` VALUES (4, 'audit_status004', '已退回', 'RETURNED', '退回修改', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_audit_status` VALUES (5, 'audit_status005', '已撤销', 'CANCELED', '撤销审核', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');

-- ----------------------------
-- Table structure for sys_calc_way
-- ----------------------------
DROP TABLE IF EXISTS `sys_calc_way`;
CREATE TABLE `sys_calc_way`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `way_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '方式ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '方式名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '方式编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '方式描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '计算方式字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_calc_way
-- ----------------------------
INSERT INTO `sys_calc_way` VALUES (1, 'calc_way_001', '直接取值', 'DIRECT_VALUE', '直接采用原始数据', '2024-01-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:05:25', '2026-02-11 17:05:25');
INSERT INTO `sys_calc_way` VALUES (2, 'calc_way_002', '加权求和', 'WEIGHTED_SUM', '多指标加权计算', '2024-01-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:05:25', '2026-02-11 17:05:25');
INSERT INTO `sys_calc_way` VALUES (3, 'calc_way_003', '平均值计算', 'AVERAGE', '计算平均值', '2024-01-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:05:25', '2026-02-11 17:05:25');
INSERT INTO `sys_calc_way` VALUES (4, 'calc_way_004', '百分比计算', 'PERCENTAGE', '计算百分比', '2024-01-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:05:25', '2026-02-11 17:05:25');
INSERT INTO `sys_calc_way` VALUES (5, 'calc_way_005', '复合计算', 'COMPOSITE_CALC', '多种计算方式组合', '2024-01-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:05:25', '2026-02-11 17:05:25');

-- ----------------------------
-- Table structure for sys_clean_rule
-- ----------------------------
DROP TABLE IF EXISTS `sys_clean_rule`;
CREATE TABLE `sys_clean_rule`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `rule_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '数据清洗规则ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '规则名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '规则编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '规则描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '数据清洗规则字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_clean_rule
-- ----------------------------
INSERT INTO `sys_clean_rule` VALUES (1, 'clean001', '空值填充0', 'FILL_ZERO', '将空值替换为0', '2024-02-01 18:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:43:07', '2026-02-12 12:43:07');
INSERT INTO `sys_clean_rule` VALUES (2, 'clean002', '去重', 'DEDUP', '去除重复数据', '2024-02-01 18:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:43:07', '2026-02-12 12:43:07');
INSERT INTO `sys_clean_rule` VALUES (3, 'clean003', '异常值剔除', 'OUTLIER', '剔除超过3σ的数据', '2024-02-01 18:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:43:07', '2026-02-12 12:43:07');
INSERT INTO `sys_clean_rule` VALUES (4, 'clean004', '格式标准化', 'FORMAT', '统一日期/数字格式', '2024-02-01 18:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:43:07', '2026-02-12 12:43:07');
INSERT INTO `sys_clean_rule` VALUES (5, 'clean005', '单位转换', 'UNIT', '单位统一转换', '2024-02-01 18:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:43:07', '2026-02-12 12:43:07');

-- ----------------------------
-- Table structure for sys_collect_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_collect_type`;
CREATE TABLE `sys_collect_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '采集方式ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '采集方式名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '采集方式编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '采集方式描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '采集方式字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_collect_type
-- ----------------------------
INSERT INTO `sys_collect_type` VALUES (1, 'colle001-1111-1111-1111-111111111111', '系统对接', 'API', '通过接口自动采集', '2024-02-01 12:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:45:13', '2026-02-12 11:45:13');
INSERT INTO `sys_collect_type` VALUES (2, 'colle002-2222-2222-2222-222222222222', '人工上报', 'MANUAL', '由用户填报', '2024-02-01 12:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:45:13', '2026-02-12 11:45:13');
INSERT INTO `sys_collect_type` VALUES (3, 'colle003-3333-3333-3333-333333333333', '设备实时', 'DEVICE', '物联网设备实时采集', '2024-02-01 12:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:45:13', '2026-02-12 11:45:13');
INSERT INTO `sys_collect_type` VALUES (4, 'colle004-4444-4444-4444-444444444444', '问卷调研', 'SURVEY', '通过问卷收集', '2024-02-01 12:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:45:13', '2026-02-12 11:45:13');
INSERT INTO `sys_collect_type` VALUES (5, 'colle005-5555-5555-5555-555555555555', '实地考察', 'INSPECT', '线下考察记录', '2024-02-01 12:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:45:13', '2026-02-12 11:45:13');

-- ----------------------------
-- Table structure for sys_cycle_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_cycle_type`;
CREATE TABLE `sys_cycle_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '周期类型ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '周期类型名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '周期类型编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '周期类型描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '周期类型字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_cycle_type
-- ----------------------------
INSERT INTO `sys_cycle_type` VALUES (1, 'cycle001-1111-1111-1111-111111111111', '月度', 'MONTH', '每月一次', '2024-02-01 12:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:42:55', '2026-02-12 11:42:55');
INSERT INTO `sys_cycle_type` VALUES (2, 'cycle002-2222-2222-2222-222222222222', '季度', 'QUARTER', '每季度一次', '2024-02-01 12:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:42:55', '2026-02-12 11:42:55');
INSERT INTO `sys_cycle_type` VALUES (3, 'cycle003-3333-3333-3333-333333333333', '半年', 'HALF_YEAR', '每半年一次', '2024-02-01 12:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:42:55', '2026-02-12 11:42:55');
INSERT INTO `sys_cycle_type` VALUES (4, 'cycle004-4444-4444-4444-444444444444', '年度', 'YEAR', '每年一次', '2024-02-01 12:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:42:55', '2026-02-12 11:42:55');
INSERT INTO `sys_cycle_type` VALUES (5, 'cycle005-5555-5555-5555-555555555555', '临时', 'TEMP', '不定期', '2024-02-01 12:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:42:55', '2026-02-12 11:42:55');

-- ----------------------------
-- Table structure for sys_data_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_status`;
CREATE TABLE `sys_data_status`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `status_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '数据状态ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '数据状态字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_data_status
-- ----------------------------
INSERT INTO `sys_data_status` VALUES (1, 'data_status001', '待校验', 'PENDING', '等待校验', '2024-02-01 19:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:48:45', '2026-02-12 12:48:45');
INSERT INTO `sys_data_status` VALUES (2, 'data_status002', '校验通过', 'PASSED', '通过校验', '2024-02-01 19:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:48:45', '2026-02-12 12:48:45');
INSERT INTO `sys_data_status` VALUES (3, 'data_status003', '校验失败', 'FAILED', '未通过校验', '2024-02-01 19:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:48:45', '2026-02-12 12:48:45');
INSERT INTO `sys_data_status` VALUES (4, 'data_status004', '已修正', 'FIXED', '已修正数据', '2024-02-01 19:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:48:45', '2026-02-12 12:48:45');
INSERT INTO `sys_data_status` VALUES (5, 'data_status005', '已废弃', 'ABANDONED', '废弃数据', '2024-02-01 19:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:48:45', '2026-02-12 12:48:45');

-- ----------------------------
-- Table structure for sys_dock_fail_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dock_fail_type`;
CREATE TABLE `sys_dock_fail_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型ID（主键UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型名称',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型编码',
  `type_desc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间（创建时间）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间（更新时间）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '对接失败类型字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dock_fail_type
-- ----------------------------

-- ----------------------------
-- Table structure for sys_docking_frequency
-- ----------------------------
DROP TABLE IF EXISTS `sys_docking_frequency`;
CREATE TABLE `sys_docking_frequency`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `frequency_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '对接频率ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '频率名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '频率编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '频率描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '对接频率字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_docking_frequency
-- ----------------------------
INSERT INTO `sys_docking_frequency` VALUES (1, 'dock_freq001', '实时', 'REALTIME', '实时对接', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_docking_frequency` VALUES (2, 'dock_freq002', '每日', 'DAILY', '每日一次', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_docking_frequency` VALUES (3, 'dock_freq003', '每周', 'WEEKLY', '每周一次', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_docking_frequency` VALUES (4, 'dock_freq004', '每月', 'MONTHLY', '每月一次', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_docking_frequency` VALUES (5, 'dock_freq005', '触发式', 'TRIGGER', '按需触发', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');

-- ----------------------------
-- Table structure for sys_docking_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_docking_log`;
CREATE TABLE `sys_docking_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `log_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '对接日志UUID（业务主键）',
  `docking_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '系统对接ID，关联system_docking.docking_id（文档无sys_docking_record，按现有表关联）',
  `sync_num` int NULL DEFAULT 0 COMMENT '同步数据量',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '同步完成时间',
  `fail_time` datetime(0) NULL DEFAULT NULL COMMENT '失败时间',
  `fail_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '失败原因摘要',
  `oper_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '操作人，关联sys_user.user_id',
  `sync_detail` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '同步数据明细摘要',
  `consist_result` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '数据一致性校验结果：已核对/未核对/一致/不一致',
  `response_time` int NULL DEFAULT 0 COMMENT '对接响应时长（单位：毫秒）',
  `fail_type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '失败类型ID，关联sys_dock_fail_type.id（文档无该表，按业务格式填充）',
  `error_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '外部系统错误码',
  `fix_suggest` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '修正方案建议',
  `try_sync_num` int NULL DEFAULT 0 COMMENT '尝试同步数据量',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统对接日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_docking_log
-- ----------------------------
INSERT INTO `sys_docking_log` VALUES (11, 'log_dock001-1111-1111-1111-111111111111', 'dock001-1111-1111-1111-111111111111', 120, '2024-12-30 02:00:30', NULL, '', '1002-8a7b-476c-955d-4c3b2a190876', '同步医院2024年度考核数据120条，含评分、等级等核心字段', '一致', 350, '', '', '', 120, NULL, NULL, NULL, NULL, '1002-8a7b-476c-955d-4c3b2a190876', '1002-8a7b-476c-955d-4c3b2a190876', b'0', '1', '2026-02-13 14:46:10', '2026-02-13 14:46:10');
INSERT INTO `sys_docking_log` VALUES (12, 'log_dock002-2222-2222-2222-222222222222', 'dock002-2222-2222-2222-222222222222', 5, '2024-03-05 10:05:12', NULL, '', '1004-0c5d-432e-711f-2a1908765432', '同步腾讯2月信用监测数据5条，含资产负债率、信用等级等', '已核对', 180, '', '', '', 5, NULL, NULL, NULL, NULL, '1004-0c5d-432e-711f-2a1908765432', '1004-0c5d-432e-711f-2a1908765432', b'0', '1', '2026-02-13 14:46:10', '2026-02-13 14:46:10');
INSERT INTO `sys_docking_log` VALUES (13, 'log_dock003-3333-3333-3333-333333333333', 'dock003-3333-3333-3333-333333333333', 0, NULL, '2024-02-02 00:05:30', 'OA系统接口认证超时，无法建立连接', '1001-7f9d-499a-b99c-8e7d6f8c7b6a', '尝试同步政务评价汇总数据，未获取到有效返回', '未核对', 5000, 'fail_ty001-3333-3333-3333-3333333333', 'AUTH_TIMEOUT_001', '检查OA系统对接账号权限，重新配置认证信息后重试', 20, NULL, NULL, NULL, NULL, '1001-7f9d-499a-b99c-8e7d6f8c7b6a', '1001-7f9d-499a-b99c-8e7d6f8c7b6a', b'0', '1', '2026-02-13 14:46:10', '2026-02-13 14:46:10');
INSERT INTO `sys_docking_log` VALUES (14, 'log_dock004-4444-4444-4444-444444444444', 'dock004-4444-4444-4444-444444444444', 3, NULL, '2024-02-02 00:10:15', 'HR系统返回数据字段格式不匹配，关键字段缺失', '1003-9b6c-454d-833e-3b2a19087654', '尝试同步3条经营评价数据，仅1条字段完整，2条缺失\"employee_count\"字段', '不一致', 2800, 'fail_ty002-4444-4444-4444-4444444444', 'FIELD_MISS_002', '协调HR系统补充缺失字段，更新数据映射规则后重新同步', 3, NULL, NULL, NULL, NULL, '1003-9b6c-454d-833e-3b2a19087654', '1003-9b6c-454d-833e-3b2a19087654', b'0', '1', '2026-02-13 14:46:10', '2026-02-13 14:46:10');
INSERT INTO `sys_docking_log` VALUES (15, 'log_dock005-5555-5555-5555-555555555555', 'dock005-5555-5555-5555-555555555555', 8, '2024-02-02 00:15:40', NULL, '', '1005-1d4e-410f-6990-190876543210', '同步慈善总会能力评估数据8条，含治理规范性、募捐合规性等指标', '一致', 420, '', '', '', 8, NULL, NULL, NULL, NULL, '1005-1d4e-410f-6990-190876543210', '1005-1d4e-410f-6990-190876543210', b'0', '1', '2026-02-13 14:46:10', '2026-02-13 14:46:10');

-- ----------------------------
-- Table structure for sys_docking_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_docking_record`;
CREATE TABLE `sys_docking_record`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `docking_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '对接UUID（主键，UUID）',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '对接编号',
  `system_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '外部系统ID（关联sys_external_system.system_id）',
  `type_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '对接方式ID（关联sys_docking_type.type_id）',
  `status` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '对接状态（关联sys_docking_status.status_id）',
  `freq_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '对接频率ID（关联sys_docking_frequency.frequency_id）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人（关联sys_user.user_id）',
  `config_time` datetime(0) NULL DEFAULT NULL COMMENT '配置时间',
  `latest_dock_time` datetime(0) NULL DEFAULT NULL COMMENT '最近对接时间',
  `success_rate` decimal(5, 2) NULL DEFAULT NULL COMMENT '对接成功率（%）',
  `total_sync_num` bigint NULL DEFAULT NULL COMMENT '累计同步数据量',
  `fail_count` int NULL DEFAULT NULL COMMENT '失败次数',
  `latest_fail_reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '最新失败原因',
  `map_rule` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '数据映射规则摘要',
  `stop_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '停用操作人（关联sys_user.user_id）',
  `stop_time` datetime(0) NULL DEFAULT NULL COMMENT '停用时间',
  `stop_reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '停用原因',
  `stop_hour` decimal(5, 1) NULL DEFAULT NULL COMMENT '停用时长（小时）',
  `history_success_rate` decimal(5, 2) NULL DEFAULT NULL COMMENT '历史对接成功率（%）',
  `config_check_result` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '配置有效性校验结果（有效/无效）',
  `external_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '外部系统最新状态',
  `re_dock_count` int NULL DEFAULT NULL COMMENT '重新对接次数',
  `latest_re_dock_time` datetime(0) NULL DEFAULT NULL COMMENT '最新重新对接时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统对接记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_docking_record
-- ----------------------------

-- ----------------------------
-- Table structure for sys_docking_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_docking_status`;
CREATE TABLE `sys_docking_status`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `status_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '对接状态ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '对接状态字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_docking_status
-- ----------------------------
INSERT INTO `sys_docking_status` VALUES (1, 'dock_status001', '启用', 'ENABLED', '对接已启用', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_docking_status` VALUES (2, 'dock_status002', '停用', 'DISABLED', '对接已停用', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_docking_status` VALUES (3, 'dock_status003', '异常', 'ERROR', '对接异常', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_docking_status` VALUES (4, 'dock_status004', '待配置', 'PENDING', '待配置', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_docking_status` VALUES (5, 'dock_status005', '已删除', 'DELETED', '已删除', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');

-- ----------------------------
-- Table structure for sys_docking_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_docking_type`;
CREATE TABLE `sys_docking_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型ID（主键UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型名称',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型编码',
  `type_desc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间（创建时间）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间（更新时间）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '对接方式字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_docking_type
-- ----------------------------

-- ----------------------------
-- Table structure for sys_docking_way
-- ----------------------------
DROP TABLE IF EXISTS `sys_docking_way`;
CREATE TABLE `sys_docking_way`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `way_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '对接方式ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '方式名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '方式编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '方式描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '对接方式字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_docking_way
-- ----------------------------
INSERT INTO `sys_docking_way` VALUES (1, 'docking_way001', 'API同步', 'API_SYNC', '实时接口同步', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_docking_way` VALUES (2, 'docking_way002', '数据库直连', 'DB_LINK', '直接连接数据库', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_docking_way` VALUES (3, 'docking_way003', '文件交换', 'FILE_EXCH', '通过文件传输', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_docking_way` VALUES (4, 'docking_way004', '消息订阅', 'MQ_SUB', '订阅消息', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_docking_way` VALUES (5, 'docking_way005', 'ESB总线', 'ESB', '企业服务总线', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');

-- ----------------------------
-- Table structure for sys_export_format
-- ----------------------------
DROP TABLE IF EXISTS `sys_export_format`;
CREATE TABLE `sys_export_format`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `format_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '导出格式ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '格式名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '格式编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '格式描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '导出格式字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_export_format
-- ----------------------------

-- ----------------------------
-- Table structure for sys_external_system
-- ----------------------------
DROP TABLE IF EXISTS `sys_external_system`;
CREATE TABLE `sys_external_system`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `system_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '外部系统ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '系统名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '系统编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '系统描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '外部系统字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_external_system
-- ----------------------------
INSERT INTO `sys_external_system` VALUES (1, 'ext_sys001', '数据中台', 'DMP', '集团数据中台', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_external_system` VALUES (2, 'ext_sys002', 'OA系统', 'OA', '办公自动化系统', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_external_system` VALUES (3, 'ext_sys003', 'HR系统', 'HR', '人力资源系统', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_external_system` VALUES (4, 'ext_sys004', 'CRM系统', 'CRM', '客户关系系统', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_external_system` VALUES (5, 'ext_sys005', '财务系统', 'FIN', '财务管理系统', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');

-- ----------------------------
-- Table structure for sys_field_mapping
-- ----------------------------
DROP TABLE IF EXISTS `sys_field_mapping`;
CREATE TABLE `sys_field_mapping`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `mapping_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '字段映射ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '映射名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '映射编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '映射描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '字段映射字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_field_mapping
-- ----------------------------

-- ----------------------------
-- Table structure for sys_frequency
-- ----------------------------
DROP TABLE IF EXISTS `sys_frequency`;
CREATE TABLE `sys_frequency`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `frequency_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '频率ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '频率名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '频率编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '频率描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '频率字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_frequency
-- ----------------------------
INSERT INTO `sys_frequency` VALUES (1, 'freq001-1111-1111-1111-111111111111', '实时', 'REALTIME', '实时同步', '2024-02-01 12:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:45:13', '2026-02-12 11:45:13');
INSERT INTO `sys_frequency` VALUES (2, 'freq002-2222-2222-2222-222222222222', '每小时', 'HOURLY', '每小时同步', '2024-02-01 12:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:45:13', '2026-02-12 11:45:13');
INSERT INTO `sys_frequency` VALUES (3, 'freq003-3333-3333-3333-333333333333', '每天', 'DAILY', '每日同步', '2024-02-01 12:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:45:13', '2026-02-12 11:45:13');
INSERT INTO `sys_frequency` VALUES (4, 'freq004-4444-4444-4444-444444444444', '每周', 'WEEKLY', '每周同步', '2024-02-01 12:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:45:13', '2026-02-12 11:45:13');
INSERT INTO `sys_frequency` VALUES (5, 'freq005-5555-5555-5555-555555555555', '每月', 'MONTHLY', '每月同步', '2024-02-01 12:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:45:13', '2026-02-12 11:45:13');

-- ----------------------------
-- Table structure for sys_generate_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_generate_type`;
CREATE TABLE `sys_generate_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '生成方式ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '方式名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '方式编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '方式描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '生成方式字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_generate_type
-- ----------------------------
INSERT INTO `sys_generate_type` VALUES (1, 'gen001', '自动生成', 'AUTO', '系统自动生成', '2024-02-01 21:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:08:24', '2026-02-12 13:08:24');
INSERT INTO `sys_generate_type` VALUES (2, 'gen002', '手动生成', 'MANUAL', '用户手动生成', '2024-02-01 21:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:08:24', '2026-02-12 13:08:24');
INSERT INTO `sys_generate_type` VALUES (3, 'gen003', '定时生成', 'SCHEDULED', '定时任务生成', '2024-02-01 21:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:08:24', '2026-02-12 13:08:24');
INSERT INTO `sys_generate_type` VALUES (4, 'gen004', '触发生成', 'TRIGGER', '事件触发生成', '2024-02-01 21:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:08:24', '2026-02-12 13:08:24');
INSERT INTO `sys_generate_type` VALUES (5, 'gen005', '批量生成', 'BATCH', '批量生成报告', '2024-02-01 21:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:08:24', '2026-02-12 13:08:24');

-- ----------------------------
-- Table structure for sys_grade
-- ----------------------------
DROP TABLE IF EXISTS `sys_grade`;
CREATE TABLE `sys_grade`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `grade_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '等级ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '等级名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '等级编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '等级描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '等级字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_grade
-- ----------------------------
INSERT INTO `sys_grade` VALUES (1, 'grade001', 'A级', 'A', '优秀', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_grade` VALUES (2, 'grade002', 'B级', 'B', '良好', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_grade` VALUES (3, 'grade003', 'C级', 'C', '合格', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_grade` VALUES (4, 'grade004', 'D级', 'D', '不合格', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_grade` VALUES (5, 'grade005', 'E级', 'E', '极差', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');

-- ----------------------------
-- Table structure for sys_index_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_index_type`;
CREATE TABLE `sys_index_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '指标类型字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_index_type
-- ----------------------------
INSERT INTO `sys_index_type` VALUES (1, 'idx_type_001', '定量指标', 'QUANTITATIVE', '可量化测量的指标', '2024-01-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:05:25', '2026-02-11 17:05:25');
INSERT INTO `sys_index_type` VALUES (2, 'idx_type_002', '定性指标', 'QUALITATIVE', '描述性评价指标', '2024-01-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:05:25', '2026-02-11 17:05:25');
INSERT INTO `sys_index_type` VALUES (3, 'idx_type_003', '复合指标', 'COMPOSITE', '多维度综合指标', '2024-01-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:05:25', '2026-02-11 17:05:25');
INSERT INTO `sys_index_type` VALUES (4, 'idx_type_004', '过程指标', 'PROCESS', '反映工作过程的指标', '2024-01-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:05:25', '2026-02-11 17:05:25');
INSERT INTO `sys_index_type` VALUES (5, 'idx_type_005', '结果指标', 'RESULT', '反映工作结果的指标', '2024-01-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:05:25', '2026-02-11 17:05:25');

-- ----------------------------
-- Table structure for sys_inspect_record_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_inspect_record_status`;
CREATE TABLE `sys_inspect_record_status`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `status_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态ID（主键UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态名称',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态编码',
  `status_desc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间（创建时间）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间（更新时间）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '考察记录状态字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_inspect_record_status
-- ----------------------------

-- ----------------------------
-- Table structure for sys_inspect_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_inspect_status`;
CREATE TABLE `sys_inspect_status`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `status_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态ID（主键UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态名称',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态编码',
  `status_desc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间（创建时间）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间（更新时间）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '考察计划状态字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_inspect_status
-- ----------------------------

-- ----------------------------
-- Table structure for sys_inspection_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_inspection_type`;
CREATE TABLE `sys_inspection_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '考察方式ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '方式名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '方式编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '方式描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '考察方式字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_inspection_type
-- ----------------------------
INSERT INTO `sys_inspection_type` VALUES (1, 'insp_type001', '现场走访', 'VISIT', '实地走访', '2024-02-01 20:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `sys_inspection_type` VALUES (2, 'insp_type002', '暗访', 'UNDERCOVER', '不公开身份', '2024-02-01 20:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `sys_inspection_type` VALUES (3, 'insp_type003', '会议座谈', 'MEETING', '召开座谈会', '2024-02-01 20:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `sys_inspection_type` VALUES (4, 'insp_type004', '资料审查', 'DOC_REVIEW', '查阅文档', '2024-02-01 20:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `sys_inspection_type` VALUES (5, 'insp_type005', '远程视频', 'VIDEO', '视频连线', '2024-02-01 20:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');

-- ----------------------------
-- Table structure for sys_issue_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_issue_type`;
CREATE TABLE `sys_issue_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发放方式ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发放方式名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发放方式编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发放方式描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '发放方式字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_issue_type
-- ----------------------------
INSERT INTO `sys_issue_type` VALUES (1, 'issue001-1111-1111-1111-111111111111', '邮件', 'EMAIL', '通过邮件发送问卷', '2024-02-01 12:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:45:13', '2026-02-12 11:45:13');
INSERT INTO `sys_issue_type` VALUES (2, 'issue002-2222-2222-2222-222222222222', '短信', 'SMS', '通过短信链接', '2024-02-01 12:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:45:13', '2026-02-12 11:45:13');
INSERT INTO `sys_issue_type` VALUES (3, 'issue003-3333-3333-3333-333333333333', '二维码', 'QRCODE', '扫码填写', '2024-02-01 12:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:45:13', '2026-02-12 11:45:13');
INSERT INTO `sys_issue_type` VALUES (4, 'issue004-4444-4444-4444-444444444444', '系统内通知', 'INNER', '系统站内信', '2024-02-01 12:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:45:13', '2026-02-12 11:45:13');
INSERT INTO `sys_issue_type` VALUES (5, 'issue005-5555-5555-5555-555555555555', '纸质发放', 'PAPER', '纸质问卷回收', '2024-02-01 12:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:45:13', '2026-02-12 11:45:13');

-- ----------------------------
-- Table structure for sys_mapping_rule
-- ----------------------------
DROP TABLE IF EXISTS `sys_mapping_rule`;
CREATE TABLE `sys_mapping_rule`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `rule_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '数据映射规则ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '规则名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '规则编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '规则描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '数据映射规则字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_mapping_rule
-- ----------------------------
INSERT INTO `sys_mapping_rule` VALUES (1, 'map001', '默认映射', 'DEFAULT', '字段一对一映射', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_mapping_rule` VALUES (2, 'map002', '名称转换', 'NAME_MAP', '字段名称映射', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_mapping_rule` VALUES (3, 'map003', '单位换算', 'UNIT_CONV', '单位转换映射', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_mapping_rule` VALUES (4, 'map004', '格式转换', 'FORMAT_CONV', '日期格式转换', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_mapping_rule` VALUES (5, 'map005', '值映射', 'VALUE_MAP', '代码值映射', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');

-- ----------------------------
-- Table structure for sys_notify_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_notify_status`;
CREATE TABLE `sys_notify_status`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `status_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通知状态ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '通知状态字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notify_status
-- ----------------------------
INSERT INTO `sys_notify_status` VALUES (1, 'notify001', '未通知', 'UNSENT', '尚未通知', '2024-02-01 20:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `sys_notify_status` VALUES (2, 'notify002', '已通知', 'SENT', '已发送通知', '2024-02-01 20:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `sys_notify_status` VALUES (3, 'notify003', '已确认', 'CONFIRMED', '被通知人已确认', '2024-02-01 20:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `sys_notify_status` VALUES (4, 'notify004', '已反馈', 'FEEDBACK', '收到反馈', '2024-02-01 20:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `sys_notify_status` VALUES (5, 'notify005', '失败', 'FAILED', '通知失败', '2024-02-01 20:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');

-- ----------------------------
-- Table structure for sys_object_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_object_type`;
CREATE TABLE `sys_object_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '对象类型字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_object_type
-- ----------------------------
INSERT INTO `sys_object_type` VALUES (1, 'obj_type_001', '政府部门', 'GOV_DEPT', '各级政府部门及直属机构', '2024-01-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:05:25', '2026-02-11 17:05:25');
INSERT INTO `sys_object_type` VALUES (2, 'obj_type_002', '事业单位', 'PUBLIC_INST', '教育、医疗等事业单位', '2024-01-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:05:25', '2026-02-11 17:05:25');
INSERT INTO `sys_object_type` VALUES (3, 'obj_type_003', '国有企业', 'STATE_ENTER', '国有及国有控股企业', '2024-01-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:05:25', '2026-02-11 17:05:25');
INSERT INTO `sys_object_type` VALUES (4, 'obj_type_004', '民营企业', 'PRIVATE_ENT', '民营企业和私营机构', '2024-01-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:05:25', '2026-02-11 17:05:25');
INSERT INTO `sys_object_type` VALUES (5, 'obj_type_005', '社会组织', 'SOCIAL_ORG', '社会团体、基金会等', '2024-01-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 17:05:25', '2026-02-11 17:05:25');

-- ----------------------------
-- Table structure for sys_objection_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_objection_status`;
CREATE TABLE `sys_objection_status`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `status_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '异议状态ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '异议状态字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_objection_status
-- ----------------------------
INSERT INTO `sys_objection_status` VALUES (1, 'objection_status001', '待处理', 'PENDING', '异议待处理', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:31:00', '2026-02-12 13:31:00');
INSERT INTO `sys_objection_status` VALUES (2, 'objection_status002', '处理中', 'PROCESSING', '正在处理', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:31:00', '2026-02-12 13:31:00');
INSERT INTO `sys_objection_status` VALUES (3, 'objection_status003', '已采纳', 'ACCEPTED', '异议采纳', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:31:00', '2026-02-12 13:31:00');
INSERT INTO `sys_objection_status` VALUES (4, 'objection_status004', '已驳回', 'REJECTED', '异议驳回', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:31:00', '2026-02-12 13:31:00');
INSERT INTO `sys_objection_status` VALUES (5, 'objection_status005', '已撤销', 'WITHDRAWN', '异议撤回', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:31:00', '2026-02-12 13:31:00');

-- ----------------------------
-- Table structure for sys_plan_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_plan_status`;
CREATE TABLE `sys_plan_status`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `status_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '计划状态ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '计划状态字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_plan_status
-- ----------------------------
INSERT INTO `sys_plan_status` VALUES (1, 'plan_status_001', '待通知', 'PENDING_NOTIFY', '计划已创建', '2024-02-01 20:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `sys_plan_status` VALUES (2, 'plan_status_002', '已通知', 'NOTIFIED', '已发送通知', '2024-02-01 20:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `sys_plan_status` VALUES (3, 'plan_status_003', '进行中', 'IN_PROGRESS', '正在考察', '2024-02-01 20:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `sys_plan_status` VALUES (4, 'plan_status_004', '已完成', 'COMPLETED', '考察完成', '2024-02-01 20:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `sys_plan_status` VALUES (5, 'plan_status_005', '已取消', 'CANCELED', '计划取消', '2024-02-01 20:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');

-- ----------------------------
-- Table structure for sys_public_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_public_status`;
CREATE TABLE `sys_public_status`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `status_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态ID（主键UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态名称',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态编码',
  `status_desc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间（创建时间）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间（更新时间）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '公示状态字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_public_status
-- ----------------------------

-- ----------------------------
-- Table structure for sys_publicity_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_publicity_status`;
CREATE TABLE `sys_publicity_status`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `status_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '公示状态ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '公示状态字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_publicity_status
-- ----------------------------
INSERT INTO `sys_publicity_status` VALUES (1, 'pub_status001', '待公示', 'PENDING', '等待开始', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_publicity_status` VALUES (2, 'pub_status002', '公示中', 'PUBLIC', '正在公示', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_publicity_status` VALUES (3, 'pub_status003', '已结束', 'FINISHED', '公示结束', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_publicity_status` VALUES (4, 'pub_status004', '提前终止', 'TERMINATED', '提前终止', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_publicity_status` VALUES (5, 'pub_status005', '已取消', 'CANCELED', '取消公示', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');

-- ----------------------------
-- Table structure for sys_push_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_push_status`;
CREATE TABLE `sys_push_status`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `status_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '推送状态ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '推送状态字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_push_status
-- ----------------------------
INSERT INTO `sys_push_status` VALUES (1, 'push_status001', '待推送', 'PENDING', '等待推送', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_push_status` VALUES (2, 'push_status002', '推送中', 'PUSHING', '正在推送', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_push_status` VALUES (3, 'push_status003', '推送成功', 'SUCCESS', '推送成功', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_push_status` VALUES (4, 'push_status004', '推送失败', 'FAILED', '推送失败', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_push_status` VALUES (5, 'push_status005', '部分成功', 'PARTIAL', '部分成功', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');

-- ----------------------------
-- Table structure for sys_push_target
-- ----------------------------
DROP TABLE IF EXISTS `sys_push_target`;
CREATE TABLE `sys_push_target`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `target_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '推送目标ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '目标名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '目标编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '目标描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '推送目标字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_push_target
-- ----------------------------
INSERT INTO `sys_push_target` VALUES (1, 'push_target001', '上级部门', 'SUPERIOR', '报送上级', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_push_target` VALUES (2, 'push_target002', '被评价对象', 'EVAL_OBJ', '反馈给评价对象', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_push_target` VALUES (3, 'push_target003', '评价主体', 'EVAL_SUBJECT', '发送给评价主体', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_push_target` VALUES (4, 'push_target004', '外部系统A', 'EXT_SYS_A', '对接外部系统A', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_push_target` VALUES (5, 'push_target005', '公示平台', 'PUBLICITY', '推送至公示平台', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');

-- ----------------------------
-- Table structure for sys_push_way
-- ----------------------------
DROP TABLE IF EXISTS `sys_push_way`;
CREATE TABLE `sys_push_way`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `way_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '推送方式ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '方式名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '方式编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '方式描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '推送方式字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_push_way
-- ----------------------------
INSERT INTO `sys_push_way` VALUES (1, 'push_way001', 'HTTP接口', 'HTTP', 'HTTP调用', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_push_way` VALUES (2, 'push_way002', '邮件', 'EMAIL', '邮件发送', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_push_way` VALUES (3, 'push_way003', 'FTP上传', 'FTP', 'FTP文件传输', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_push_way` VALUES (4, 'push_way004', '消息队列', 'MQ', 'MQ消息', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_push_way` VALUES (5, 'push_way005', '本地下载', 'LOCAL', '提供下载链接', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');

-- ----------------------------
-- Table structure for sys_record_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_record_status`;
CREATE TABLE `sys_record_status`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `status_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '记录状态ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '记录状态字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_record_status
-- ----------------------------
INSERT INTO `sys_record_status` VALUES (1, 'record001', '待审核', 'PENDING_AUDIT', '待审核', '2024-02-01 20:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `sys_record_status` VALUES (2, 'record002', '审核通过', 'AUDIT_PASS', '已通过', '2024-02-01 20:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `sys_record_status` VALUES (3, 'record003', '审核驳回', 'AUDIT_REJECT', '已驳回', '2024-02-01 20:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `sys_record_status` VALUES (4, 'record004', '已修正', 'FIXED', '已修正', '2024-02-01 20:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');
INSERT INTO `sys_record_status` VALUES (5, 'record005', '已归档', 'ARCHIVED', '已归档', '2024-02-01 20:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:59:21', '2026-02-12 12:59:21');

-- ----------------------------
-- Table structure for sys_report_field
-- ----------------------------
DROP TABLE IF EXISTS `sys_report_field`;
CREATE TABLE `sys_report_field`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `field_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '报表字段ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '字段名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '字段编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '字段描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '报表字段字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_report_field
-- ----------------------------

-- ----------------------------
-- Table structure for sys_report_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_report_status`;
CREATE TABLE `sys_report_status`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `status_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '报告状态ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '报告状态字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_report_status
-- ----------------------------
INSERT INTO `sys_report_status` VALUES (1, 'rpt_status001', '生成中', 'GENERATING', '报告生成中', '2024-02-01 21:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:08:24', '2026-02-12 13:08:24');
INSERT INTO `sys_report_status` VALUES (2, 'rpt_status002', '已完成', 'COMPLETED', '生成完成', '2024-02-01 21:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:08:24', '2026-02-12 13:08:24');
INSERT INTO `sys_report_status` VALUES (3, 'rpt_status003', '已发布', 'PUBLISHED', '已发布', '2024-02-01 21:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:08:24', '2026-02-12 13:08:24');
INSERT INTO `sys_report_status` VALUES (4, 'rpt_status004', '已归档', 'ARCHIVED', '已归档', '2024-02-01 21:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:08:24', '2026-02-12 13:08:24');
INSERT INTO `sys_report_status` VALUES (5, 'rpt_status005', '生成失败', 'FAILED', '生成失败', '2024-02-01 21:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:08:24', '2026-02-12 13:08:24');

-- ----------------------------
-- Table structure for sys_report_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_report_type`;
CREATE TABLE `sys_report_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '报表类型ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '报表类型字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_report_type
-- ----------------------------
INSERT INTO `sys_report_type` VALUES (1, 'rpt_type001', '汇总报表', 'SUMMARY', '总体情况汇总', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_report_type` VALUES (2, 'rpt_type002', '明细报表', 'DETAIL', '详细数据列表', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_report_type` VALUES (3, 'rpt_type003', '图表分析', 'CHART', '可视化图表', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_report_type` VALUES (4, 'rpt_type004', '排名报表', 'RANKING', '对象排名', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_report_type` VALUES (5, 'rpt_type005', '对比报表', 'COMPARE', '横向对比', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');

-- ----------------------------
-- Table structure for sys_review_result
-- ----------------------------
DROP TABLE IF EXISTS `sys_review_result`;
CREATE TABLE `sys_review_result`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `result_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '复核结果ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '结果名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '结果编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '结果描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '复核结果字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_review_result
-- ----------------------------
INSERT INTO `sys_review_result` VALUES (1, 'review001', '维持原判', 'SUSTAIN', '维持原审核结果', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_review_result` VALUES (2, 'review002', '修正分数', 'CORRECT_SCORE', '修正得分', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_review_result` VALUES (3, 'review003', '变更等级', 'CHANGE_GRADE', '调整评价等级', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_review_result` VALUES (4, 'review004', '重新评价', 'RE_EVALUATE', '需重新进行评价', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_review_result` VALUES (5, 'review005', '无效申诉', 'INVALID', '申诉无效', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');

-- ----------------------------
-- Table structure for sys_rule_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_rule_type`;
CREATE TABLE `sys_rule_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '规则类型字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_rule_type
-- ----------------------------
INSERT INTO `sys_rule_type` VALUES (1, 'rule_type001', '阈值规则', 'THRESHOLD', '根据达标阈值评分', '2024-02-01 17:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:38:10', '2026-02-12 12:38:10');
INSERT INTO `sys_rule_type` VALUES (2, 'rule_type002', '比率规则', 'RATIO', '按完成比率计分', '2024-02-01 17:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:38:10', '2026-02-12 12:38:10');
INSERT INTO `sys_rule_type` VALUES (3, 'rule_type003', '否决规则', 'VETO', '一票否决', '2024-02-01 17:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:38:10', '2026-02-12 12:38:10');
INSERT INTO `sys_rule_type` VALUES (4, 'rule_type004', '加分规则', 'BONUS', '额外加分', '2024-02-01 17:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:38:10', '2026-02-12 12:38:10');
INSERT INTO `sys_rule_type` VALUES (5, 'rule_type005', '扣分规则', 'DEDUCTION', '违规扣分', '2024-02-01 17:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:38:10', '2026-02-12 12:38:10');

-- ----------------------------
-- Table structure for sys_scope
-- ----------------------------
DROP TABLE IF EXISTS `sys_scope`;
CREATE TABLE `sys_scope`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `scope_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '范围ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '范围名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '范围编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '范围描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '范围字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_scope
-- ----------------------------
INSERT INTO `sys_scope` VALUES (1, 'scope001-1111-1111-1111-111111111111', '全部对象', 'ALL', '所有评价对象', '2024-02-01 14:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:52:52', '2026-02-12 11:52:52');
INSERT INTO `sys_scope` VALUES (2, 'scope002-2222-2222-2222-222222222222', '按区域', 'BY_AREA', '按区域筛选', '2024-02-01 14:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:52:52', '2026-02-12 11:52:52');
INSERT INTO `sys_scope` VALUES (3, 'scope003-3333-3333-3333-333333333333', '按类型', 'BY_TYPE', '按对象类型', '2024-02-01 14:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:52:52', '2026-02-12 11:52:52');
INSERT INTO `sys_scope` VALUES (4, 'scope004-4444-4444-4444-444444444444', '自定义', 'CUSTOM', '自定义范围', '2024-02-01 14:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:52:52', '2026-02-12 11:52:52');
INSERT INTO `sys_scope` VALUES (5, 'scope005-5555-5555-5555-555555555555', '测试范围', 'TEST', '测试用', '2024-02-01 14:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:52:52', '2026-02-12 11:52:52');

-- ----------------------------
-- Table structure for sys_stat_dimension
-- ----------------------------
DROP TABLE IF EXISTS `sys_stat_dimension`;
CREATE TABLE `sys_stat_dimension`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `dimension_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '统计维度ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '维度名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '维度编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '维度描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '统计维度字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_stat_dimension
-- ----------------------------
INSERT INTO `sys_stat_dimension` VALUES (1, 'dim001', '按区域', 'BY_AREA', '按区域统计', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_stat_dimension` VALUES (2, 'dim002', '按对象类型', 'BY_OBJ_TYPE', '按评价对象类型', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_stat_dimension` VALUES (3, 'dim003', '按任务', 'BY_TASK', '按评价任务', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_stat_dimension` VALUES (4, 'dim004', '按时间', 'BY_TIME', '按年月季度', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');
INSERT INTO `sys_stat_dimension` VALUES (5, 'dim005', '按等级', 'BY_GRADE', '按评价等级', '2024-02-01 23:50:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:56:07', '2026-02-12 13:56:07');

-- ----------------------------
-- Table structure for sys_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_status`;
CREATE TABLE `sys_status`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `status_id` char(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '状态字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_status
-- ----------------------------
INSERT INTO `sys_status` VALUES (1, '1', '启用', 'ENABLED', '正常使用状态', '2024-01-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 10:15:56', '2026-02-25 14:22:29');
INSERT INTO `sys_status` VALUES (2, '2', '停用', 'DISABLED', '暂停使用状态', '2024-01-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 10:15:56', '2026-02-25 14:22:35');
INSERT INTO `sys_status` VALUES (3, '3', '待审核', 'PENDING', '等待审核', '2024-02-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:36:32', '2026-02-12 11:36:32');
INSERT INTO `sys_status` VALUES (4, '4', '已归档', 'ARCHIVED', '已完成归档', '2024-02-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:36:32', '2026-02-12 11:36:32');
INSERT INTO `sys_status` VALUES (5, '5', '草稿', 'DRAFT', '暂存状态', '2024-02-01 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:36:32', '2026-02-12 11:36:32');

-- ----------------------------
-- Table structure for sys_subject_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_subject_type`;
CREATE TABLE `sys_subject_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '主体类型字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_subject_type
-- ----------------------------
INSERT INTO `sys_subject_type` VALUES (1, 'st001-2f3e-456d-876c-543b2a190876', '人工主体', 'MANUAL', '由人工组成的评价主体', '2024-01-02 10:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 10:15:56', '2026-02-11 10:15:56');
INSERT INTO `sys_subject_type` VALUES (2, 'st002-3e4f-567d-987c-654b3a201987', '系统主体', 'SYSTEM', '自动执行评价的系统主体', '2024-01-02 10:10:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 10:15:56', '2026-02-11 10:15:56');
INSERT INTO `sys_subject_type` VALUES (3, 'st003-4f5e-678d-098c-765b4a312098', '第三方主体', 'THIRD', '外部合作机构评价主体', '2024-01-02 10:20:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 10:15:56', '2026-02-11 10:15:56');
INSERT INTO `sys_subject_type` VALUES (4, 'st004-5e6f-789d-109c-876b5a423109', '混合主体', 'MIXED', '人工+系统混合评价主体', '2024-01-02 10:30:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 10:15:56', '2026-02-11 10:15:56');
INSERT INTO `sys_subject_type` VALUES (5, 'st005-6f7e-890d-210c-987b6a534210', '临时主体', 'TEMP', '短期项目专用评价主体', '2024-01-02 10:40:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 10:15:56', '2026-02-11 10:15:56');

-- ----------------------------
-- Table structure for sys_survey_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_survey_status`;
CREATE TABLE `sys_survey_status`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `status_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '问卷状态ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '问卷状态字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_survey_status
-- ----------------------------

-- ----------------------------
-- Table structure for sys_sync_freq
-- ----------------------------
DROP TABLE IF EXISTS `sys_sync_freq`;
CREATE TABLE `sys_sync_freq`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `frequency_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '同步频率ID（主键UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '频率名称',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '频率编码',
  `freq_desc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '频率描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间（创建时间）',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间（更新时间）',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '同步频率字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_sync_freq
-- ----------------------------

-- ----------------------------
-- Table structure for sys_task_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_task_status`;
CREATE TABLE `sys_task_status`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `status_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '任务状态ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '任务状态字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_task_status
-- ----------------------------
INSERT INTO `sys_task_status` VALUES (1, 'task_status_001', '待启动', 'PENDING_START', '任务未开始', '2024-02-01 14:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:52:52', '2026-02-12 11:52:52');
INSERT INTO `sys_task_status` VALUES (2, 'task_status_002', '进行中', 'IN_PROGRESS', '任务执行中', '2024-02-01 14:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:52:52', '2026-02-12 11:52:52');
INSERT INTO `sys_task_status` VALUES (3, 'task_status_003', '已完成', 'COMPLETED', '任务完成', '2024-02-01 14:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:52:52', '2026-02-12 11:52:52');
INSERT INTO `sys_task_status` VALUES (4, 'task_status_004', '已暂停', 'PAUSED', '任务暂停', '2024-02-01 14:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:52:52', '2026-02-12 11:52:52');
INSERT INTO `sys_task_status` VALUES (5, 'task_status_005', '已终止', 'TERMINATED', '任务终止', '2024-02-01 14:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 11:52:52', '2026-02-12 11:52:52');

-- ----------------------------
-- Table structure for sys_task_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_task_type`;
CREATE TABLE `sys_task_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '任务类型ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '任务类型字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_task_type
-- ----------------------------
INSERT INTO `sys_task_type` VALUES (1, 'task_type001', '年度评价', 'YEARLY', '年度评价任务', '2024-02-01 21:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:08:24', '2026-02-12 13:08:24');
INSERT INTO `sys_task_type` VALUES (2, 'task_type002', '季度评价', 'QUARTERLY', '季度评价任务', '2024-02-01 21:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:08:24', '2026-02-12 13:08:24');
INSERT INTO `sys_task_type` VALUES (3, 'task_type003', '月度评价', 'MONTHLY', '月度评价任务', '2024-02-01 21:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:08:24', '2026-02-12 13:08:24');
INSERT INTO `sys_task_type` VALUES (4, 'task_type004', '专项评价', 'SPECIAL', '专项评价任务', '2024-02-01 21:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:08:24', '2026-02-12 13:08:24');
INSERT INTO `sys_task_type` VALUES (5, 'task_type005', '临时评价', 'TEMP', '临时评价任务', '2024-02-01 21:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:08:24', '2026-02-12 13:08:24');

-- ----------------------------
-- Table structure for sys_template_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_template_status`;
CREATE TABLE `sys_template_status`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `status_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '模板状态ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '模板状态字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_template_status
-- ----------------------------
INSERT INTO `sys_template_status` VALUES (1, 'tmpl_status001', '草稿', 'DRAFT', '模板草稿', '2024-02-01 21:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:08:24', '2026-02-12 13:08:24');
INSERT INTO `sys_template_status` VALUES (2, 'tmpl_status002', '已发布', 'PUBLISHED', '正式发布', '2024-02-01 21:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:08:24', '2026-02-12 13:08:24');
INSERT INTO `sys_template_status` VALUES (3, 'tmpl_status003', '已停用', 'DISABLED', '停用模板', '2024-02-01 21:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:08:24', '2026-02-12 13:08:24');
INSERT INTO `sys_template_status` VALUES (4, 'tmpl_status004', '审核中', 'AUDITING', '待审核', '2024-02-01 21:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:08:24', '2026-02-12 13:08:24');
INSERT INTO `sys_template_status` VALUES (5, 'tmpl_status005', '已归档', 'ARCHIVED', '归档模板', '2024-02-01 21:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:08:24', '2026-02-12 13:08:24');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户ID（UUID，业务主键）',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `user_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系电话',
  `dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所属部门名称',
  `role_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色ID（关联角色表主键）',
  `status_id` int NULL DEFAULT NULL COMMENT '状态ID（关联sys_status.status_id）',
  `create_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人ID（关联sys_user.user_id）',
  `update_by` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人ID（关联sys_user.user_id）',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '1001-7f9d-499a-b99c-8e7d6f8c7b6a', '张三', '13800138000', '技术部', NULL, 1, '1001-7f9d-499a-b99c-8e7d6f8c7b6a', NULL, '2024-01-03 09:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 10:15:56', '2026-02-11 15:26:07');
INSERT INTO `sys_user` VALUES (2, '1002-8a7b-476c-955d-4c3b2a190876', '李四', '13900139000', '运营部', NULL, 1, '1001-7f9d-499a-b99c-8e7d6f8c7b6a', NULL, '2024-01-03 09:10:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 10:15:56', '2026-02-11 15:26:10');
INSERT INTO `sys_user` VALUES (3, '1003-9b6c-454d-833e-3b2a19087654', '王五', '13700137000', '财务部', NULL, 1, '1001-7f9d-499a-b99c-8e7d6f8c7b6a', NULL, '2024-01-03 09:20:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 10:15:56', '2026-02-11 15:26:13');
INSERT INTO `sys_user` VALUES (4, '1004-0c5d-432e-711f-2a1908765432', '赵六', '13600136000', '市场部', NULL, 1, '1002-8a7b-476c-955d-4c3b2a190876', NULL, '2024-01-03 09:30:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 10:15:56', '2026-02-11 15:26:14');
INSERT INTO `sys_user` VALUES (5, '1005-1d4e-410f-6990-190876543210', '孙七', '13500135000', '人事部', NULL, 1, '1002-8a7b-476c-955d-4c3b2a190876', NULL, '2024-01-03 09:40:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-11 10:15:56', '2026-02-11 15:26:19');

-- ----------------------------
-- Table structure for sys_verify_result
-- ----------------------------
DROP TABLE IF EXISTS `sys_verify_result`;
CREATE TABLE `sys_verify_result`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `result_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '校验结果ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '结果名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '结果编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '结果描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '校验结果字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_verify_result
-- ----------------------------
INSERT INTO `sys_verify_result` VALUES (1, 'verify001', '一致', 'CONSISTENT', '数据一致', '2024-02-01 19:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:48:45', '2026-02-12 12:48:45');
INSERT INTO `sys_verify_result` VALUES (2, 'verify002', '异常', 'ABNORMAL', '数据异常', '2024-02-01 19:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:48:45', '2026-02-12 12:48:45');
INSERT INTO `sys_verify_result` VALUES (3, 'verify003', '缺失', 'MISSING', '数据缺失', '2024-02-01 19:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:48:45', '2026-02-12 12:48:45');
INSERT INTO `sys_verify_result` VALUES (4, 'verify004', '超范围', 'OUT_OF_RANGE', '超出阈值范围', '2024-02-01 19:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:48:45', '2026-02-12 12:48:45');
INSERT INTO `sys_verify_result` VALUES (5, 'verify005', '格式错误', 'FORMAT_ERROR', '格式不正确', '2024-02-01 19:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 12:48:45', '2026-02-12 12:48:45');

-- ----------------------------
-- Table structure for sys_veto_check
-- ----------------------------
DROP TABLE IF EXISTS `sys_veto_check`;
CREATE TABLE `sys_veto_check`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `check_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '否决项检查ID（UUID）',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '检查名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '检查编码',
  `desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '检查描述',
  `biz_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `biz_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '否决项检查表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_veto_check
-- ----------------------------
INSERT INTO `sys_veto_check` VALUES (1, 'veto_check001', '安全否决', 'SAFETY_VETO', '重大安全事故', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_veto_check` VALUES (2, 'veto_check002', '质量否决', 'QUALITY_VETO', '严重质量问题', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_veto_check` VALUES (3, 'veto_check003', '财务否决', 'FINANCE_VETO', '财务造假', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_veto_check` VALUES (4, 'veto_check004', '合规否决', 'COMPLIANCE_VETO', '严重违规', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');
INSERT INTO `sys_veto_check` VALUES (5, 'veto_check005', '信誉否决', 'CREDIT_VETO', '严重失信', '2024-02-01 23:00:00', NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2026-02-12 13:20:34', '2026-02-12 13:20:34');

SET FOREIGN_KEY_CHECKS = 1;


create table eval_comment_statistic
(
    id             bigint auto_increment comment '主键ID'
        primary key,
    item_id        bigint                                null comment '指标项ID(关联指标项表的主键id  eval_index_item.id)',
    object_id      bigint                                null comment '街道：评价对象ID (关联eval_object.id)',
    count          bigint                                null comment '统计指标项数量',
    score          bigint                                null comment '关联到规则中回填的分数',
    address_coding char(36)                              null comment '地址编码',
    ext_common1    varchar(100)                          null comment '通用扩展字段1',
    ext_common2    varchar(100)                          null comment '通用扩展字段2',
    ext_common3    varchar(100)                          null comment '通用扩展字段3',
    ext_common4    varchar(100)                          null comment '通用扩展字段4',
    creator        varchar(64) default ''                null comment '创建者',
    updater        varchar(64) default ''                null comment '更新者',
    deleted        bit         default b'0'              null comment '删除标识',
    tenant_id      bigint      default 1                 null comment '租户ID',
    create_time    datetime    default CURRENT_TIMESTAMP null comment '创建时间',
    update_time    datetime    default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    change_log     mediumtext                            null comment '变更日志',
    status         varchar(20) default '1'               null comment '状态: 1：待审核中，2：审核通过，3：不用审核'
)
    comment '巡查巡检统计表' collate = utf8mb4_unicode_ci
                         row_format = DYNAMIC;


create table eval_patrol_inspection
(
    id             bigint auto_increment comment '主键ID'
        primary key,
    user_id        bigint                                null comment '巡检人ID(关联sys_user.id)',
    system_id      bigint                                null comment '体系ID (关联eval_index_system.id)',
    object_id      bigint                                null comment '评价对象ID (关联eval_object.id)',
    item_id        bigint                                null comment '指标项ID(关联eval_index_item)',
    category_id    bigint                                null comment '规则分类ID',
    details        text                                  null comment '评价说明',
    ext_common1    varchar(100)                          null comment '通用扩展字段1',
    ext_common2    varchar(100)                          null comment '通用扩展字段2',
    ext_common3    varchar(100)                          null comment '通用扩展字段3',
    ext_common4    varchar(100)                          null comment '通用扩展字段4',
    creator        varchar(64) default ''                null comment '创建者',
    updater        varchar(64) default ''                null comment '更新者',
    deleted        bit         default b'0'              null comment '删除标识',
    tenant_id      bigint      default 1                 null comment '租户ID',
    create_time    datetime    default CURRENT_TIMESTAMP null comment '创建时间',
    update_time    datetime    default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    change_log     mediumtext                            null comment '变更日志',
    status         varchar(20) default '1'               null comment '状态: 1：待审核中，2：审核通过，3：不用审核',
    image          blob                                  null comment '图片',
    address_coding char(36)                              null comment '地址编码'
)
    comment '巡查巡检表' collate = utf8mb4_unicode_ci
                         row_format = DYNAMIC;
CREATE TABLE eval_comment_rule (
                           id BIGINT AUTO_INCREMENT COMMENT '主键ID' PRIMARY KEY,
                           system_id BIGINT NOT NULL COMMENT '指标体系ID(关联eval_index_system)',
                           rule_category_id BIGINT NOT NULL COMMENT '规则分类ID(关联eval_rule_category)',
                           item_id BIGINT NOT NULL COMMENT '指标项ID(关联eval_index_item)',
                           rule_name VARCHAR(100) NOT NULL COMMENT '规则名称',
                           rule_type TINYINT DEFAULT 0 NULL COMMENT '规则类型（1=加分，2=扣分）',
                           status TINYINT DEFAULT 1 NULL COMMENT '状态（1=启用，2=停用）',
                           apply_object_type VARCHAR(50) NULL COMMENT '适用对象类型（如：网格/企业/个人）',
                           effective_start_time DATETIME NULL COMMENT '生效开始时间',
                           effective_end_time DATETIME NULL COMMENT '生效结束时间',
                           status_change_remark VARCHAR(200) NULL COMMENT '状态变更备注',
                           creator VARCHAR(64) DEFAULT '' NULL COMMENT '创建者',
                           updater VARCHAR(64) DEFAULT '' NULL COMMENT '更新者',
                           deleted BIT DEFAULT b'0' NULL COMMENT '删除标识',
                           tenant_id BIGINT DEFAULT 1 NULL COMMENT '租户ID',
                           create_time DATETIME DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
                           update_time DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                           operation_log MEDIUMTEXT NULL COMMENT '操作变更日志'
) COMMENT '评分规则主表' COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- 索引优化
CREATE INDEX idx_system_category_status ON eval_comment_rule (system_id, rule_category_id, status);

CREATE TABLE eval_rule_detail (
                                  id BIGINT AUTO_INCREMENT COMMENT '主键ID' PRIMARY KEY,
                                  rule_id BIGINT NOT NULL COMMENT '规则ID(eval_comment_rule.id)',
                                  min_value DECIMAL(18,6) NULL COMMENT '区间最小值（null表示无下限）',
                                  max_value DECIMAL(18,6) NULL COMMENT '区间最大值（null表示无上限）',
                                  operator_min VARCHAR(10) DEFAULT '>=' NULL COMMENT '最小值运算符（>=、>）',
                                  operator_max VARCHAR(10) DEFAULT '<=' NULL COMMENT '最大值运算符（<=、<）',
                                  score DECIMAL(10,2) NOT NULL COMMENT '该区间对应的分数',
                                  sort_order INT DEFAULT 0 NULL COMMENT '排序优先级（值越小越优先匹配）',
                                  remark VARCHAR(200) NULL COMMENT '规则描述（如：=0、>1且<5）',
                                  creator VARCHAR(64) DEFAULT '' NULL COMMENT '创建者',
                                  updater VARCHAR(64) DEFAULT '' NULL COMMENT '更新者',
                                  deleted BIT DEFAULT b'0' NULL COMMENT '删除标识',
                                  create_time DATETIME DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
                                  update_time DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '评分规则明细表' COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- 索引优化
CREATE INDEX idx_rule_id ON eval_rule_detail (rule_id);
CREATE INDEX idx_rule_sort ON eval_rule_detail (rule_id, sort_order);


create table eval_object_score
(
    id          bigint auto_increment comment '主键ID'
        primary key,
    object_id   bigint                                null comment '对象ID (关联eval_object.id)',
    system_id   bigint                                null comment '体系ID (关联eval_index_system.id)',
    user_id     bigint                                null comment '巡检人ID(关联sys_user.id)',
    score       bigint                                null comment '总得分',
    status      varchar(20) default '1'               null comment '状态: 1：待审核中，2：审核通过，3：不用审核',
    details     text                                  null comment '评价说明',
    creator     varchar(64) default ''                null comment '创建者',
    updater     varchar(64) default ''                null comment '更新者',
    deleted     bit         default b'0'              null comment '删除标识',
    tenant_id   bigint      default 1                 null comment '租户ID',
    create_time datetime    default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime    default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    change_log  mediumtext                            null comment '变更日志'
)
    comment '公司得分表' collate = utf8mb4_unicode_ci
                         row_format = DYNAMIC;

