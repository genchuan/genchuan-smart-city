

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for student_archive
-- ----------------------------
CREATE TABLE IF NOT EXISTS `student_archive` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_no` VARCHAR(32) NOT NULL COMMENT '学生编号',
  `name` VARCHAR(64) NOT NULL COMMENT '姓名',
  `class_id` BIGINT NOT NULL COMMENT '班级ID，关联system_dept.id',
  `major` VARCHAR(64) NOT NULL COMMENT '专业',
  `level` VARCHAR(32) NOT NULL COMMENT '层次',
  `study_type` VARCHAR(32) NOT NULL COMMENT '学习形式',
  `id_card` VARCHAR(18) NOT NULL COMMENT '身份证号',
  `phone` VARCHAR(11) NOT NULL COMMENT '联系电话',
  `parent_phone` VARCHAR(11) DEFAULT NULL COMMENT '家长电话',
  `status` VARCHAR(20) NOT NULL COMMENT '学籍状态：在籍/休学/退学/异动，关联字典student_archive_status.label',
  `archive_time` DATETIME NOT NULL COMMENT '建档时间',
  `process_status` VARCHAR(20) NOT NULL COMMENT '流程状态：待审核/正常/已归档，关联字典student_archive_process_status.label',
  `reject_reason` VARCHAR(255) DEFAULT NULL COMMENT '驳回原因',
  `change_reason` VARCHAR(255) DEFAULT NULL COMMENT '异动原因',
  `evidence_url` VARCHAR(255) DEFAULT NULL COMMENT '佐证材料',
  `punish_valid_time` DATETIME DEFAULT NULL COMMENT '处分有效期',
  `remark` TEXT COMMENT '备注',
  `extension` JSON NOT NULL COMMENT '扩展字段，JSON格式（插入时需显式提供值）',  -- 已移除 DEFAULT '{}' 避免报错
  `creator` BIGINT NOT NULL DEFAULT 0 COMMENT '创建人ID，关联system_user.id',
  `updater` BIGINT NOT NULL DEFAULT 0 COMMENT '更新人ID，关联system_user.id',
  `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '删除标识：0-未删除，1-已删除',
  `tenant_id` BIGINT NOT NULL DEFAULT 1 COMMENT '租户ID，关联system_tenant.id',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_student_no` (`student_no`),
  UNIQUE KEY `uk_id_card` (`id_card`),
  KEY `idx_class_id` (`class_id`),
  KEY `idx_status` (`status`),
  KEY `idx_process_status` (`process_status`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_creator` (`creator`),
  KEY `idx_updater` (`updater`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学生学籍档案表';

INSERT INTO `student_archive` (`id`, `student_no`, `name`, `class_id`, `major`, `level`, `study_type`, `id_card`, `phone`, `parent_phone`, `status`, `archive_time`, `process_status`, `reject_reason`, `change_reason`, `evidence_url`, `punish_valid_time`, `remark`, `extension`, `creator`, `updater`, `deleted`, `tenant_id`, `create_time`, `update_time`) VALUES (1, '20210001', '张三', 101, '计算机科学与技术', '本科', '全日制', '11010119900307663X', '13800138001', '13800138011', '在籍', '2021-09-01 08:00:00', '正常', NULL, NULL, NULL, NULL, '优秀学生', '{\"source\": \"统招\", \"hometown\": \"北京\"}', 'admin', 'admin', b'0', 1, '2026-05-26 09:04:52', '2026-05-26 15:09:27');
INSERT INTO `student_archive` (`id`, `student_no`, `name`, `class_id`, `major`, `level`, `study_type`, `id_card`, `phone`, `parent_phone`, `status`, `archive_time`, `process_status`, `reject_reason`, `change_reason`, `evidence_url`, `punish_valid_time`, `remark`, `extension`, `creator`, `updater`, `deleted`, `tenant_id`, `create_time`, `update_time`) VALUES (2, '20210002', '李四', 102, '软件工程', '本科', '全日制', '110101199102034567', '13800138002', '13800138012', '在籍', '2021-09-01 09:30:00', '正常', NULL, NULL, NULL, NULL, NULL, '{\"source\": \"统招\"}', 'admin', 'admin', b'0', 1, '2026-05-26 09:04:52', '2026-05-26 15:09:28');
INSERT INTO `student_archive` (`id`, `student_no`, `name`, `class_id`, `major`, `level`, `study_type`, `id_card`, `phone`, `parent_phone`, `status`, `archive_time`, `process_status`, `reject_reason`, `change_reason`, `evidence_url`, `punish_valid_time`, `remark`, `extension`, `creator`, `updater`, `deleted`, `tenant_id`, `create_time`, `update_time`) VALUES (3, '20210003', '王芳', 103, '数据科学与大数据', '本科', '全日制', '420106199203157894', '13800138003', '13800138013', '休学', '2021-09-02 10:15:00', '正常', NULL, '个人健康原因', '/evidence/001.pdf', '2023-09-01 00:00:00', '休学期满需复学申请', '{\"source\": \"统招\", \"medical_cert\": \"有\"}', 'admin', 'admin', b'0', 1, '2026-05-26 09:04:52', '2026-05-26 15:09:29');
INSERT INTO `student_archive` (`id`, `student_no`, `name`, `class_id`, `major`, `level`, `study_type`, `id_card`, `phone`, `parent_phone`, `status`, `archive_time`, `process_status`, `reject_reason`, `change_reason`, `evidence_url`, `punish_valid_time`, `remark`, `extension`, `creator`, `updater`, `deleted`, `tenant_id`, `create_time`, `update_time`) VALUES (4, '20210004', '赵磊', 104, '人工智能', '本科', '全日制', '440301199305224567', '13800138004', NULL, '退学', '2021-09-03 11:20:00', '已归档', NULL, '成绩不合格且自愿退学', '/evidence/002.pdf', NULL, '已办理退学手续', '{\"reason\": \"成绩问题\", \"source\": \"统招\"}', 'admin', 'admin', b'0', 1, '2026-05-26 09:04:52', '2026-05-26 15:09:30');
INSERT INTO `student_archive` (`id`, `student_no`, `name`, `class_id`, `major`, `level`, `study_type`, `id_card`, `phone`, `parent_phone`, `status`, `archive_time`, `process_status`, `reject_reason`, `change_reason`, `evidence_url`, `punish_valid_time`, `remark`, `extension`, `creator`, `updater`, `deleted`, `tenant_id`, `create_time`, `update_time`) VALUES (5, '20210005', '孙丽', 101, '计算机科学与技术', '本科', '非全日制', '310101199406303218', '13800138005', '13800138015', '在籍', '2021-09-05 14:00:00', '待审核', '请补充工作单位证明', NULL, NULL, NULL, '材料不齐', '{\"source\": \"成人高考\"}', 'admin', 'admin', b'0', 1, '2026-05-26 09:04:52', '2026-05-26 15:09:30');
INSERT INTO `student_archive` (`id`, `student_no`, `name`, `class_id`, `major`, `level`, `study_type`, `id_card`, `phone`, `parent_phone`, `status`, `archive_time`, `process_status`, `reject_reason`, `change_reason`, `evidence_url`, `punish_valid_time`, `remark`, `extension`, `creator`, `updater`, `deleted`, `tenant_id`, `create_time`, `update_time`) VALUES (6, '20210006', '周强', 105, '网络工程', '专科', '全日制', '510107199507151234', '13800138006', '13800138016', '异动', '2021-09-06 15:45:00', '正常', NULL, '转专业至软件工程', '/evidence/003.pdf', NULL, '异动流程已完成', '{\"source\": \"统招\", \"new_major\": \"软件工程\"}', 'admin', 'admin', b'0', 1, '2026-05-26 09:04:52', '2026-05-26 15:09:31');
INSERT INTO `student_archive` (`id`, `student_no`, `name`, `class_id`, `major`, `level`, `study_type`, `id_card`, `phone`, `parent_phone`, `status`, `archive_time`, `process_status`, `reject_reason`, `change_reason`, `evidence_url`, `punish_valid_time`, `remark`, `extension`, `creator`, `updater`, `deleted`, `tenant_id`, `create_time`, `update_time`) VALUES (7, '20210007', '吴敏', 102, '软件工程', '本科', '全日制', '130182199608162345', '13800138007', '13800138017', '在籍', '2021-09-07 09:10:00', '正常', NULL, NULL, NULL, NULL, '获校级奖学金', '{\"source\": \"统招\", \"scholarship\": \"一等奖\"}', 'admin', 'admin', b'0', 1, '2026-05-26 09:04:52', '2026-05-26 15:09:32');
INSERT INTO `student_archive` (`id`, `student_no`, `name`, `class_id`, `major`, `level`, `study_type`, `id_card`, `phone`, `parent_phone`, `status`, `archive_time`, `process_status`, `reject_reason`, `change_reason`, `evidence_url`, `punish_valid_time`, `remark`, `extension`, `creator`, `updater`, `deleted`, `tenant_id`, `create_time`, `update_time`) VALUES (8, '20210008', '郑浩', 106, '信息安全', '本科', '全日制', '330106199709273456', '13800138008', NULL, '在籍', '2021-09-08 10:30:00', '已归档', NULL, NULL, NULL, NULL, '已毕业离校', '{\"source\": \"统招\", \"graduate_date\": \"2025-06-30\"}', 'admin', 'admin', b'0', 1, '2026-05-26 09:04:52', '2026-05-26 15:09:33');
INSERT INTO `student_archive` (`id`, `student_no`, `name`, `class_id`, `major`, `level`, `study_type`, `id_card`, `phone`, `parent_phone`, `status`, `archive_time`, `process_status`, `reject_reason`, `change_reason`, `evidence_url`, `punish_valid_time`, `remark`, `extension`, `creator`, `updater`, `deleted`, `tenant_id`, `create_time`, `update_time`) VALUES (9, '20210009', '陈晨', 103, '数据科学与大数据', '本科', '全日制', '500112199810184567', '13800138009', '13800138019', '休学', '2021-09-09 13:20:00', '正常', NULL, '创业实践', '/evidence/004.pdf', '2024-09-01 00:00:00', '休学两年', '{\"source\": \"统招\", \"venture\": \"已注册公司\"}', 'admin', 'admin', b'0', 1, '2026-05-26 09:04:52', '2026-05-26 15:09:33');
INSERT INTO `student_archive` (`id`, `student_no`, `name`, `class_id`, `major`, `level`, `study_type`, `id_card`, `phone`, `parent_phone`, `status`, `archive_time`, `process_status`, `reject_reason`, `change_reason`, `evidence_url`, `punish_valid_time`, `remark`, `extension`, `creator`, `updater`, `deleted`, `tenant_id`, `create_time`, `update_time`) VALUES (10, '20210010', '林欣', 107, '物联网工程', '本科', '全日制', '210103199911195678', '13800138010', '13800138020', '在籍', '2021-09-10 16:00:00', '待审核', '照片不清晰，请重新上传', NULL, NULL, NULL, '需补交材料', '{\"source\": \"统招\"}', 'admin', 'admin', b'0', 1, '2026-05-26 09:04:52', '2026-05-26 15:09:34');
INSERT INTO `student_archive` (`id`, `student_no`, `name`, `class_id`, `major`, `level`, `study_type`, `id_card`, `phone`, `parent_phone`, `status`, `archive_time`, `process_status`, `reject_reason`, `change_reason`, `evidence_url`, `punish_valid_time`, `remark`, `extension`, `creator`, `updater`, `deleted`, `tenant_id`, `create_time`, `update_time`) VALUES (11, '20210011', '郭峰', 108, '数字媒体技术', '本科', '全日制', '350203200012206789', '13800138011', '13800138021', '退学', '2021-09-11 08:45:00', '已归档', NULL, '违纪被开除', '/evidence/005.pdf', '2023-10-01 00:00:00', '处分期已过', '{\"punish\": \"严重警告\", \"source\": \"统招\"}', 'admin', 'admin', b'0', 1, '2026-05-26 09:04:52', '2026-05-26 15:09:35');
INSERT INTO `student_archive` (`id`, `student_no`, `name`, `class_id`, `major`, `level`, `study_type`, `id_card`, `phone`, `parent_phone`, `status`, `archive_time`, `process_status`, `reject_reason`, `change_reason`, `evidence_url`, `punish_valid_time`, `remark`, `extension`, `creator`, `updater`, `deleted`, `tenant_id`, `create_time`, `update_time`) VALUES (12, '20210012', '刘洋', 101, '计算机科学与技术', '本科', '全日制', '440304200101217890', '13800138012', NULL, '异动', '2021-09-12 12:10:00', '正常', NULL, '降级处理', '/evidence/006.pdf', NULL, '学业预警后降级', '{\"source\": \"统招\", \"new_grade\": \"2022级\", \"original_grade\": \"2021级\"}', 'admin', 'admin', b'0', 1, '2026-05-26 09:04:52', '2026-05-26 15:09:36');
INSERT INTO `student_archive` (`id`, `student_no`, `name`, `class_id`, `major`, `level`, `study_type`, `id_card`, `phone`, `parent_phone`, `status`, `archive_time`, `process_status`, `reject_reason`, `change_reason`, `evidence_url`, `punish_valid_time`, `remark`, `extension`, `creator`, `updater`, `deleted`, `tenant_id`, `create_time`, `update_time`) VALUES (13, '20210013', '黄娟', 109, '电子商务', '专科', '全日制', '320505200202228901', '13800138013', '13800138023', '在籍', '2021-09-13 14:50:00', '正常', NULL, NULL, NULL, NULL, '积极参加实践活动', '{\"source\": \"统招\", \"internship\": \"已完成\"}', 'admin', 'admin', b'0', 1, '2026-05-26 09:04:52', '2026-05-26 15:09:36');
INSERT INTO `student_archive` (`id`, `student_no`, `name`, `class_id`, `major`, `level`, `study_type`, `id_card`, `phone`, `parent_phone`, `status`, `archive_time`, `process_status`, `reject_reason`, `change_reason`, `evidence_url`, `punish_valid_time`, `remark`, `extension`, `creator`, `updater`, `deleted`, `tenant_id`, `create_time`, `update_time`) VALUES (14, '20210014', '许文', 110, '信息管理与信息系统', '本科', '全日制', '370202200303239012', '13800138014', '13800138024', '在籍', '2021-09-14 17:30:00', '已归档', NULL, NULL, NULL, NULL, '档案已移交就业办', '{\"source\": \"统招\", \"archive_location\": \"就业办\"}', 'admin', 'admin', b'0', 2, '2026-05-26 09:04:52', '2026-05-26 15:09:37');
INSERT INTO `student_archive` (`id`, `student_no`, `name`, `class_id`, `major`, `level`, `study_type`, `id_card`, `phone`, `parent_phone`, `status`, `archive_time`, `process_status`, `reject_reason`, `change_reason`, `evidence_url`, `punish_valid_time`, `remark`, `extension`, `creator`, `updater`, `deleted`, `tenant_id`, `create_time`, `update_time`) VALUES (15, '20210015', '何晴', 102, '软件工程', '本科', '非全日制', '410105200404241234', '13800138015', '13800138025', '休学', '2021-09-15 09:00:00', '待审核', '休学申请缺少家长签名', '身体原因', '/evidence/007.pdf', '2025-03-01 00:00:00', '等待补充材料', '{\"source\": \"成人高考\", \"medical_report\": \"有\"}', 'admin', 'admin', b'0', 1, '2026-05-26 09:04:52', '2026-05-26 15:09:39');

-- 恢复外键检查
SET FOREIGN_KEY_CHECKS = 1;