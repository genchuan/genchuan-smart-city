TRUNCATE TABLE `garbage_collection`;

INSERT INTO `garbage_collection` (
  `id`, `collection_id`, `plan_no`, `area_code`, `garbage_type_id`,
  `frequency`, `time_period`, `vehicle_id`, `staff_ids`, `point_ids`,
  `plan_status_id`, `completion_rate`, `abnormal_count`, `abnormal_create_time`,
  `abnormal_update_time`, `create_by`, `progress`, `collected_volume`,
  `checkin_status`, `track_coverage`, `last_report_time`, `is_abnormal`,
  `complete_time`, `total_volume`, `abnormal_result`, `abnormal_complete_rate`,
  `ext_common1`, `ext_common2`, `ext_common3`, `ext_common4`,
  `creator`, `updater`, `deleted`, `tenant_id`, `create_time`, `update_time`
) VALUES
-- 1. 无异常数，状态：已完成（003）
('1', 'uuid-collect-001', 'GC20240601001', '1001', 'uuid-garbage-001',
 '每日', '07:00-11:00', 'uuid-vehicle-001', '["uuid-user-001", "uuid-user-002"]', '["uuid-point-001", "uuid-point-008"]',
 'uuid-plan-status-003', '100.00', '0', NULL, NULL, 'uuid-user-001', '100.00', '28.50',
 '离岗', '100%', '2024-06-01 10:50:00', '\0', '2024-06-01 11:00:00', '28.50', '无', '0.00',
 NULL, NULL, NULL, NULL, 'admin', 'admin', '\0', '1', '2026-02-26 10:00:00', '2026-02-26 10:00:00'),

-- 2. 异常数=1，状态：异常（004）
('2', 'uuid-collect-002', 'GC20240601002', '1002', 'uuid-garbage-002',
 '每日', '12:00-16:00', 'uuid-vehicle-002', '["uuid-user-003", "uuid-user-004"]', '["uuid-point-002"]',
 'uuid-plan-status-004', '75.00', '1', '2024-06-01 13:15:00', '2024-06-01 14:30:00', 'uuid-user-002', '75.00', '19.80',
 '到岗', '90%', '2024-06-01 14:25:00', '', NULL, '26.50', '部分办结', '50.00',
 NULL, NULL, NULL, NULL, 'admin', 'admin', '\0', '1', '2026-02-26 10:00:00', '2026-02-26 10:00:00'),

-- 3. 无异常数，状态：未执行（001）
('3', 'uuid-collect-003', 'GC20240601003', '1003', 'uuid-garbage-003',
 '每周', '09:00-11:00', 'uuid-vehicle-003', '["uuid-user-005"]', '["uuid-point-003"]',
 'uuid-plan-status-001', '0.00', '0', NULL, NULL, 'uuid-user-003', '0.00', '0.00',
 '', '', NULL, '\0', NULL, '5.00', '无', '0.00',
 NULL, NULL, NULL, NULL, 'admin', 'admin', '\0', '1', '2026-02-26 10:00:00', '2026-02-26 10:00:00'),

-- 4. 异常数=2，状态：异常（004）
('4', 'uuid-collect-004', 'GC20240601004', '1004', 'uuid-garbage-004',
 '每日', '14:00-18:00', 'uuid-vehicle-004', '["uuid-user-006", "uuid-user-007"]', '["uuid-point-004"]',
 'uuid-plan-status-004', '100.00', '2', '2024-06-01 15:00:00', '2024-06-01 17:40:00', 'uuid-user-004', '100.00', '42.30',
 '离岗', '100%', '2024-06-01 17:30:00', '\0', '2024-06-01 18:00:00', '42.30', '已办结', '100.00',
 NULL, NULL, NULL, NULL, 'admin', 'admin', '\0', '1', '2026-02-26 10:00:00', '2026-02-26 10:00:00'),

-- 5. 异常数=3，状态：异常（004）
('5', 'uuid-collect-005', 'GC20240601005', '1005', 'uuid-garbage-005',
 '每周', '10:00-14:00', 'uuid-vehicle-005', '["uuid-user-001", "uuid-user-003"]', '["uuid-point-005"]',
 'uuid-plan-status-004', '40.00', '3', '2024-06-01 10:30:00', '2024-06-01 13:15:00', 'uuid-user-005', '40.00', '8.20',
 '到岗', '65%', '2024-06-01 13:10:00', '', NULL, '20.50', '部分办结', '33.33',
 NULL, NULL, NULL, NULL, 'admin', 'admin', '\0', '1', '2026-02-26 10:00:00', '2026-02-26 10:00:00'),

-- 6. 异常数=1，状态：异常（004）
('6', 'uuid-collect-006', 'GC20240601006', '1006', 'uuid-garbage-006',
 '每月', '08:00-12:00', 'uuid-vehicle-006', '["uuid-user-002", "uuid-user-004"]', '["uuid-point-006"]',
 'uuid-plan-status-004', '30.00', '1', '2024-06-01 08:10:00', '2024-06-01 09:45:00', 'uuid-user-006', '30.00', '6.80',
 '', '40%', '2024-06-01 09:40:00', '', NULL, '22.50', '待办结', '0.00',
 NULL, NULL, NULL, NULL, 'admin', 'admin', '\0', '1', '2026-02-26 10:00:00', '2026-02-26 10:00:00'),

-- 7. 无异常数，状态：执行中（002）
('7', 'uuid-collect-007', 'GC20240601007', '1007', 'uuid-garbage-007',
 '每周', '16:00-20:00', 'uuid-vehicle-007', '["uuid-user-005", "uuid-user-007"]', '["uuid-point-007"]',
 'uuid-plan-status-002', '85.00', '0', NULL, NULL, 'uuid-user-007', '85.00', '21.70',
 '到岗', '95%', '2024-06-01 19:10:00', '\0', NULL, '25.50', '无', '0.00',
 NULL, NULL, NULL, NULL, 'admin', 'admin', '\0', '1', '2026-02-26 10:00:00', '2026-02-26 10:00:00'),

-- 8. 无异常数，状态：已取消（005）
('8', 'uuid-collect-008', 'GC20240601008', '1001', 'uuid-garbage-001',
 '应急', '13:00-17:00', 'uuid-vehicle-001', '["uuid-user-001"]', '["uuid-point-001", "uuid-point-008"]',
 'uuid-plan-status-005', '0.00', '0', NULL, NULL, 'uuid-user-001', '0.00', '0.00',
 '', '', NULL, '\0', NULL, '0.00', '无', '0.00',
 NULL, NULL, NULL, NULL, 'admin', 'admin', '\0', '1', '2026-02-26 10:00:00', '2026-02-26 10:00:00');

INSERT INTO `sys_plan_status` (
  `sys_plan_status_id`,
  `name`,
  `code`,
  `status`,
  `sort`,
  `ext_common1`,
  `ext_common2`,
  `ext_common3`,
  `ext_common4`,
  `creator`,
  `updater`,
  `deleted`,
  `tenant_id`,
  `create_time`,
  `update_time`
) 
VALUES (
  'uuid-plan-status-007',
  '处置待复核',
  '007',
  '1',
  '7',
  null,
  null,
  null,
  null,
  'admin',
  'admin',
  '\0',
  '1',
  '2026-02-14 10:58:21',
  '2026-02-14 10:58:21'
);

-- 新增异常待处置状态的垃圾收运计划测试数据
INSERT INTO `garbage_collection` (
  `collection_id`,
  `plan_no`,
  `area_code`,
  `garbage_type_id`,
  `frequency`,
  `time_period`,
  `vehicle_id`,
  `staff_ids`,
  `point_ids`,
  `plan_status_id`,
  `completion_rate`,
  `abnormal_count`,
  `abnormal_create_time`,
  `abnormal_update_time`,
  `create_by`,
  `progress`,
  `collected_volume`,
  `checkin_status`,
  `track_coverage`,
  `last_report_time`,
  `is_abnormal`,
  `complete_time`,
  `total_volume`,
  `abnormal_result`,
  `abnormal_complete_rate`,
  `creator`,
  `updater`,
  `deleted`,
  `tenant_id`,
  `create_time`,
  `update_time`
) VALUES (
  'uuid-collect-009',        -- 收运计划主键（UUID）
  'GC20240601009',           -- 收运计划单编号
  '1005',                    -- 区域编码
  'uuid-garbage-002',        -- 垃圾类型ID
  '每日',                    -- 收运频次
  '08:00-12:00',             -- 收运时段
  'uuid-vehicle-002',        -- 车辆ID
  '["uuid-user-008"]',       -- 负责人员IDs
  '["uuid-point-004"]',      -- 收运点位IDs
  'uuid-plan-status-007',    -- 计划状态ID（关联异常待处置）
  '60.00',                   -- 完成率
  '2',                       -- 异常记录数
  '2024-06-01 09:20:00',     -- 异常创建时间
  '2024-06-01 10:15:00',     -- 异常更新时间
  'uuid-user-008',           -- 创建人ID
  '60.00',                   -- 当前进度
  '15.60',                   -- 已收运量
  '到岗',                    -- 打卡状态
  '70%',                     -- 轨迹覆盖情况
  '2024-06-01 10:10:00',     -- 最新上报时间
  b'1',                      -- 是否异常（是）
  NULL,                      -- 完成时间（未完成）
  '26.00',                   -- 总收运量
  '待处置',                  -- 异常处置结果
  '0.00',                    -- 异常办结率
  'admin',                   -- 创建者
  'admin',                   -- 更新者
  b'0',                      -- 未删除
  '1',                       -- 租户ID
  '2024-06-01 08:00:00',     -- 创建时间
  '2024-06-01 10:15:00'      -- 更新时间
);

DROP TABLE IF EXISTS `sys_collection_frequency`;
CREATE TABLE `sys_collection_frequency`  (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `frequency_code` VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '频次编码（如：uuid-frequency-001）',
  `frequency_name` VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '频次名称（如：每日/每周/每月/应急）',
  `sort` INT NULL DEFAULT 0 COMMENT '排序号',
  `remark` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  -- 系统必填字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `deleted` BIT(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` BIGINT DEFAULT 1 COMMENT '租户ID',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '收运频次字典表' ROW_FORMAT = DYNAMIC;

INSERT INTO `sys_collection_frequency` VALUES 
(1, 'uuid-frequency-001', '每日', 1, '日常收运频次', 'admin', 'admin', b'0', 1, '2026-02-27 11:20:00', '2026-02-27 11:20:00'),
(2, 'uuid-frequency-002', '每周', 2, '每周收运频次', 'admin', 'admin', b'0', 1, '2026-02-27 11:20:00', '2026-02-27 11:20:00'),
(3, 'uuid-frequency-003', '每月', 3, '每月收运频次', 'admin', 'admin', b'0', 1, '2026-02-27 11:20:00', '2026-02-27 11:20:00'),
(4, 'uuid-frequency-004', '应急', 4, '应急收运频次', 'admin', 'admin', b'0', 1, '2026-02-27 11:20:00', '2026-02-27 11:20:00');

DROP TABLE IF EXISTS `sys_collection_time_period`;
CREATE TABLE `sys_collection_time_period`  (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `period_code` VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '时段编码（如：uuid-time-period-001）',
  `period_name` VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '时段名称（如：07:30-11:30）',
  `start_time` TIME NOT NULL COMMENT '时段开始时间',
  `end_time` TIME NOT NULL COMMENT '时段结束时间',
  `sort` INT NULL DEFAULT 0 COMMENT '排序号',
  `remark` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  -- 系统必填字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `deleted` BIT(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` BIGINT DEFAULT 1 COMMENT '租户ID',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '收运时段字典表' ROW_FORMAT = DYNAMIC;

INSERT INTO `sys_collection_time_period` VALUES 
(1, 'uuid-time-period-001', '07:30-11:30', '07:30:00', '11:30:00', 1, '早间收运时段', 'admin', 'admin', b'0', 1, '2026-02-27 11:25:00', '2026-02-27 11:25:00'),
(2, 'uuid-time-period-002', '08:00-12:00', '08:00:00', '12:00:00', 2, '上午收运时段', 'admin', 'admin', b'0', 1, '2026-02-27 11:25:00', '2026-02-27 11:25:00'),
(3, 'uuid-time-period-003', '09:00-11:00', '09:00:00', '11:00:00', 3, '上午收运时段', 'admin', 'admin', b'0', 1, '2026-02-27 11:25:00', '2026-02-27 11:25:00'),
(4, 'uuid-time-period-004', '10:00-14:00', '10:00:00', '14:00:00', 4, '日间收运时段', 'admin', 'admin', b'0', 1, '2026-02-27 11:25:00', '2026-02-27 11:25:00'),
(5, 'uuid-time-period-005', '12:00-16:00', '12:00:00', '16:00:00', 5, '午间收运时段', 'admin', 'admin', b'0', 1, '2026-02-27 11:25:00', '2026-02-27 11:25:00'),
(6, 'uuid-time-period-006', '13:00-17:00', '13:00:00', '17:00:00', 6, '下午应急收运时段', 'admin', 'admin', b'0', 1, '2026-02-27 11:25:00', '2026-02-27 11:25:00'),
(7, 'uuid-time-period-007', '14:00-18:00', '14:00:00', '18:00:00', 7, '下午收运时段', 'admin', 'admin', b'0', 1, '2026-02-27 11:25:00', '2026-02-27 11:25:00'),
(8, 'uuid-time-period-008', '16:00-19:00', '16:00:00', '19:00:00', 8, '晚间收运时段', 'admin', 'admin', b'0', 1, '2026-02-27 11:25:00', '2026-02-27 11:25:00')

UPDATE `point` 
SET `status` = '1' 
WHERE `status` = '启用';

UPDATE `sys_plan_status`
SET `name`='待执行'
WHERE `name`='未执行';

UPDATE `sys_plan_status`
SET `name`='待复核'
WHERE `name`='处置待复核';

DELETE FROM sys_plan_status 
WHERE `id` = 5;

DELETE FROM sys_plan_status 
WHERE `id` = 6;


-- ----------------------------
-- New Records of garbage_collection (2026-02-28)
-- ----------------------------
INSERT INTO `garbage_collection` VALUES (10, 'uuid-collect-010', 'GC20260228010', '1002', 'uuid-garbage-002', '每日', '08:30-12:30', 'uuid-vehicle-002', '[\"uuid-user-003\", \"uuid-user-009\"]', '[\"uuid-point-002\", \"uuid-point-004\"]', 'uuid-plan-status-002', 45.50, 0, NULL, NULL, 'uuid-user-003', 45.50, 12.80, '到岗', '85%', '2026-02-28 10:25:00', b'0', NULL, 28.00, '无', 0.00, NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-28 08:00:00', '2026-02-28 10:30:00');

INSERT INTO `garbage_collection` VALUES (11, 'uuid-collect-011', 'GC20260228011', '1004', 'uuid-garbage-004', '每日', '09:00-13:00', 'uuid-vehicle-004', '[\"uuid-user-006\", \"uuid-user-011\"]', '[\"uuid-point-004\"]', 'uuid-plan-status-004', 30.00, 2, '2026-02-28 10:15:00', '2026-02-28 11:20:00', 'uuid-user-006', 30.00, 15.60, '到岗', '60%', '2026-02-28 11:15:00', b'1', NULL, 52.00, '待处置', 0.00, NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-28 09:00:00', '2026-02-28 11:30:00');

INSERT INTO `garbage_collection` VALUES (12, 'uuid-collect-012', 'GC20260228012', '1001', 'uuid-garbage-001', '每周', '07:00-11:00', 'uuid-vehicle-001', '[\"uuid-user-001\", \"uuid-user-008\"]', '[\"uuid-point-001\", \"uuid-point-008\"]', 'uuid-plan-status-002', 70.00, 0, NULL, NULL, 'uuid-user-001', 70.00, 35.20, '到岗', '100%', '2026-02-28 09:45:00', b'0', NULL, 50.00, '无', 0.00, NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-28 07:00:00', '2026-02-28 09:50:00');

INSERT INTO `garbage_collection` VALUES (13, 'uuid-collect-013', 'GC20260228013', '1006', 'uuid-garbage-006', '每月', '13:00-17:00', 'uuid-vehicle-006', '[\"uuid-user-006\", \"uuid-user-013\"]', '[\"uuid-point-006\"]', 'uuid-plan-status-001', 0.00, 0, NULL, NULL, 'uuid-user-006', 0.00, 0.00, '', '', NULL, b'0', NULL, 45.00, '无', 0.00, NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-28 13:00:00', '2026-02-28 13:00:00');

INSERT INTO `garbage_collection` VALUES (14, 'uuid-collect-014', 'GC20260228014', '1003', 'uuid-garbage-003', '应急', '10:00-14:00', 'uuid-vehicle-003', '[\"uuid-user-003\"]', '[\"uuid-point-003\"]', 'uuid-plan-status-007', 85.00, 1, '2026-02-28 11:30:00', '2026-02-28 12:45:00', 'uuid-user-003', 85.00, 18.50, '到岗', '95%', '2026-02-28 12:40:00', b'1', NULL, 22.00, '部分办结', 50.00, NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-28 10:00:00', '2026-02-28 12:50:00');

update garbage_collection
SET abnormal_count = '0'
WHERE plan_status_id = 'uuid-plan-status-007'

update garbage_collection
SET abnormal_count = '1'
WHERE id = '8'

UPDATE garbage_collection
SET is_abnormal = b'1'
WHERE abnormal_count > 0
  AND deleted = b'0'; -- 仅更新未删除的记录，符合业务常规逻辑

-- ----------------------------
-- Records of garbage_abnormal
-- ----------------------------
-- 清空表数据（TRUNCATE会重置自增主键，不可回滚；如需可回滚，改用 DELETE FROM garbage_abnormal;）
TRUNCATE TABLE garbage_abnormal;

-- 重新插入10条与garbage_collection异常状态匹配的记录（修复列数不匹配问题）
INSERT INTO `garbage_abnormal` VALUES 
(1, 'uuid-abnormal-001', 'GC20240601002', 'uuid-abnormal-type-001', '1002', 'uuid-user-002', '2024-06-01 13:15:00', '中', 'uuid-user-002', '处理中', '否', '现场督导分类，持续整改', '[\"https://xxx.com/handle/2-1.jpg\"]', '待复核', 'uuid-user-003', NULL, NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-14 10:58:21', '2026-02-26 17:35:40'),
(2, 'uuid-abnormal-002', 'GC20240601004', 'uuid-abnormal-type-002', '1004', 'uuid-user-004', '2024-06-01 15:00:00', '高', 'uuid-user-004', '已办结', '是', '加班完成清运，异常办结', '[\"https://xxx.com/handle/4-1.jpg\"]', '通过', 'uuid-user-005', '2024-06-01 17:40:00', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-14 10:58:21', '2026-02-26 17:35:49'),
(3, 'uuid-abnormal-003', 'GC20240601004', 'uuid-abnormal-type-003', '1004', 'uuid-user-004', '2024-06-01 15:30:00', '中', 'uuid-user-004', '已办结', '否', '补全清运轨迹，系统校验通过', '[\"https://xxx.com/handle/4-2.jpg\"]', '通过', 'uuid-user-005', '2024-06-01 17:45:00', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-14 10:58:21', '2026-02-26 17:35:49'),
(4, 'uuid-abnormal-004', 'GC20240601005', 'uuid-abnormal-type-004', '1005', 'uuid-user-005', '2024-06-01 10:30:00', '高', 'uuid-user-005', '部分办结', '是', '修复1个点位清运异常，剩余2个待处理', '[\"https://xxx.com/handle/5-1.jpg\"]', '待复核', 'uuid-user-006', NULL, NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-14 10:58:21', '2026-02-26 17:35:49'),
(5, 'uuid-abnormal-005', 'GC20240601005', 'uuid-abnormal-type-005', '1005', 'uuid-user-005', '2024-06-01 11:00:00', '高', 'uuid-user-005', '部分办结', '是', '修复1个点位清运异常，剩余1个待处理', '[\"https://xxx.com/handle/5-2.jpg\"]', '待复核', 'uuid-user-006', NULL, NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-14 10:58:21', '2026-02-26 17:35:49'),
(6, 'uuid-abnormal-006', 'GC20240601005', 'uuid-abnormal-type-006', '1005', 'uuid-user-005', '2024-06-01 12:00:00', '中', 'uuid-user-005', '待处置', '是', '剩余1个点位清运异常，待安排人员', '[\"https://xxx.com/handle/5-3.jpg\"]', '待复核', 'uuid-user-006', NULL, NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-14 10:58:21', '2026-02-26 17:35:49'),
(7, 'uuid-abnormal-007', 'GC20240601006', 'uuid-abnormal-type-007', '1006', 'uuid-user-006', '2024-06-01 08:10:00', '高', 'uuid-user-006', '待处置', '是', '清运轨迹覆盖不足，待补全', '[\"https://xxx.com/handle/6-1.jpg\"]', '待复核', 'uuid-user-007', NULL, NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-14 10:58:21', '2026-02-26 17:35:49'),
(8, 'uuid-abnormal-008', 'GC20240601008', 'uuid-abnormal-type-002', '1001', 'uuid-user-001', '2024-06-01 13:00:00', '低', 'uuid-user-001', '待处置', '否', '应急清运计划未启动，待确认原因', '[\"https://xxx.com/handle/8-1.jpg\"]', '待复核', 'uuid-user-002', NULL, NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-14 10:58:21', '2026-02-26 17:35:49'),
(9, 'uuid-abnormal-009', 'GC20260228011', 'uuid-abnormal-type-008', '1004', 'uuid-user-006', '2026-02-28 10:15:00', '中', 'uuid-user-006', '待处置', '否', '清运进度未达标，待督导', '[\"https://xxx.com/handle/11-1.jpg\"]', '待复核', 'uuid-user-011', NULL, NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-28 10:15:00', '2026-02-28 11:20:00'),
(10, 'uuid-abnormal-010', 'GC20260228011', 'uuid-abnormal-type-009', '1004', 'uuid-user-006', '2026-02-28 10:30:00', '低', 'uuid-user-006', '待处置', '否', '轨迹覆盖不足60%，待补传', '[\"https://xxx.com/handle/11-2.jpg\"]', '待复核', 'uuid-user-011', NULL, NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-28 10:30:00', '2026-02-28 11:20:00');

/*
  重新插入sys_abnormal_type数据，确保与garbage_abnormal关联的异常类型ID完全一致
*/
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- 清空sys_abnormal_type原有数据（保留表结构）
TRUNCATE TABLE `sys_abnormal_type`;

-- 重新插入与garbage_abnormal匹配的异常类型数据
INSERT INTO `sys_abnormal_type` VALUES 
(1, 'uuid-abnormal-type-001', '垃圾满溢', '点位垃圾未及时清运导致满溢', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-14 10:58:21', '2026-02-14 10:58:21'),
(2, 'uuid-abnormal-type-002', '混投混放', '不同品类垃圾混合投放', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-14 10:58:21', '2026-02-14 10:58:21'),
(3, 'uuid-abnormal-type-003', '设备损坏', '垃圾收集设备（垃圾桶/压缩箱）损坏', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-14 10:58:21', '2026-02-14 10:58:21'),
(4, 'uuid-abnormal-type-004', '清运延迟', '未按计划时间完成清运作业', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-14 10:58:21', '2026-02-14 10:58:21'),
(5, 'uuid-abnormal-type-005', '车辆故障', '清运车辆作业中故障', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-14 10:58:21', '2026-02-14 10:58:21'),
(6, 'uuid-abnormal-type-006', '安全隐患', '点位存在易燃易爆等安全风险', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-14 10:58:21', '2026-02-14 10:58:21'),
(7, 'uuid-abnormal-type-007', '投诉举报', '居民投诉垃圾清运相关问题', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-14 10:58:21', '2026-02-14 10:58:21'),
(8, 'uuid-abnormal-type-008', '清运进度未达标', '垃圾清运作业进度未达到计划要求', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-14 10:58:21', '2026-02-14 10:58:21'),
(9, 'uuid-abnormal-type-009', '轨迹覆盖不足', '清运车辆轨迹覆盖点位比例未达标', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-14 10:58:21', '2026-02-14 10:58:21');

SET FOREIGN_KEY_CHECKS = 1;

UPDATE garbage_abnormal
SET deleted = 0
WHERE deleted = 1;

UPDATE `garbage_abnormal` 
SET `handle_status` = '处理中',
    `update_time` = CURRENT_TIMESTAMP  -- 同步更新时间戳
WHERE `handle_status` NOT IN ('待处置', '处理中', '已办结', '退回');


/*
 Navicat Premium Dump SQL

 调整后：仅保留待处置/处置中/已办结/已退回四种处置状态，移除超时未处置
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- 清空原有数据（保留表结构）
TRUNCATE TABLE `sys_handle_status`;

-- 重新插入仅四种状态的测试数据
INSERT INTO `sys_handle_status` VALUES 
(1, 'uuid-handle-001', '待处置', 'PENDING_HANDLE', 1, 1, '问题已上报，等待派单处置', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-24 15:57:00', '2026-02-24 15:57:00'),
(2, 'uuid-handle-002', '处理中', 'HANDLING', 1, 2, '已派单，处置人员正在处理', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-24 15:57:00', '2026-02-24 15:57:00'),
(3, 'uuid-handle-003', '已办结', 'FINISHED', 1, 3, '问题处置完成，结果确认', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-24 15:57:00', '2026-02-24 15:57:00'),
(4, 'uuid-handle-004', '退回', 'RETURNED', 1, 4, '处置结果不达标，退回重新处理', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-24 15:57:00', '2026-02-24 15:57:00');

-- 重置自增ID（避免后续插入ID断层）
ALTER TABLE `sys_handle_status` AUTO_INCREMENT = 5;

SET FOREIGN_KEY_CHECKS = 1;


-- 清空原有复核状态记录（保留表结构）
DELETE FROM `sys_review_status`;
-- 重置自增ID（可选，使ID从1重新开始）
ALTER TABLE `sys_review_status` AUTO_INCREMENT = 1;

-- 插入新的复核状态记录
INSERT INTO `sys_review_status` VALUES (1, 'review_001', '待复核', '申请/记录等待复核', 1, 1, NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-12 17:06:16', '2026-02-12 17:06:16');
INSERT INTO `sys_review_status` VALUES (2, 'review_002', '通过', '复核结果为通过', 1, 2, NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-12 17:06:16', '2026-02-12 17:06:16');
INSERT INTO `sys_review_status` VALUES (3, 'review_003', '退回', '复核结果为退回', 1, 3, NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-12 17:06:16', '2026-02-12 17:06:16');

UPDATE garbage_collection 
SET abnormal_result = '无',
    update_time = CURRENT_TIMESTAMP -- 同步更新修改时间
WHERE abnormal_result NOT IN ('无', '已办结', '部分办结') 
  AND deleted = b'0'; -- 仅更新未删除的数据
