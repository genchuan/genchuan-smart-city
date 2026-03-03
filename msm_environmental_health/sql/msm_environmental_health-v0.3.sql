ALTER TABLE garbage_collection AUTO_INCREMENT = 15;

UPDATE garbage_collection
SET abnormal_result='无'
WHERE id='6'

update garbage_collection
SET plan_status_id='uuid-plan-status-004'
where plan_status_id='uuid-plan-status-007'

UPDATE garbage_collection 
SET staff_ids = '["uuid-user-007", "uuid-user-009"]'  -- 替换为你需要的人员ID数组
WHERE id = 7;

UPDATE garbage_collection 
SET point_ids = '["uuid-point-001", "uuid-user-004"]'  -- 替换为你需要的人员ID数组
WHERE id = 7;


TRUNCATE TABLE sys_handle_status;
INSERT INTO `sys_handle_status` VALUES (1, 'uuid-handle-001', '待处置', 'PENDING_HANDLE', 1, 1, '问题已上报，等待派单处置', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-24 15:57:00', '2026-02-24 15:57:00');
INSERT INTO `sys_handle_status` VALUES (2, 'uuid-handle-002', '处理中', 'HANDLING', 1, 2, '已派单，处置人员正在处理', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-24 15:57:00', '2026-02-24 15:57:00');
INSERT INTO `sys_handle_status` VALUES (3, 'uuid-handle-003', '已办结', 'FINISHED', 1, 3, '问题处置完成，结果确认', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-24 15:57:00', '2026-02-24 15:57:00');
INSERT INTO `sys_handle_status` VALUES (4, 'uuid-handle-004', '退回', 'RETURNED', 1, 4, '处置结果不达标，退回重新处理', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-02-24 15:57:00', '2026-02-24 15:57:00');
INSERT INTO `sys_handle_status` VALUES (5, 'uuid-handle-005', '待复核', 'REVIEW', 1, 5, '问题处置完成，等待复核', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-03-02 14:20:09', '2026-03-02 14:20:53');

TRUNCATE TABLE garbage_collection;
INSERT INTO `garbage_collection` VALUES (1, 'uuid-collect-001', 'GC20240601001', '1001', 'uuid-garbage-001', '每日', '07:30-11:30', 'uuid-vehicle-001', '[\"uuid-user-001\", \"uuid-user-002\"]', '[\"uuid-point-001\", \"uuid-point-008\"]', 'uuid-plan-status-003', 100.00, 0, NULL, NULL, 'uuid-user-001', 100.00, 28.50, '离岗', '100%', '2024-06-01 10:50:00', b'0', '2024-06-01 11:00:00', 28.50, '无', 0.00, NULL, NULL, NULL, NULL, 'admin', '1', b'0', 1, '2026-02-26 10:00:00', '2026-03-02 09:07:54');
INSERT INTO `garbage_collection` VALUES (2, 'uuid-collect-002', 'GC20240601002', '1002', 'uuid-garbage-002', '每日', '12:00-16:00', 'uuid-vehicle-002', '[\"uuid-user-003\", \"uuid-user-004\"]', '[\"uuid-point-002\"]', 'uuid-plan-status-002', 75.00, 1, '2024-06-01 13:15:00', '2024-06-01 14:30:00', 'uuid-user-002', 75.00, 19.80, '到岗', '90%', '2024-06-01 14:25:00', b'1', NULL, 26.50, '部分办结', 50.00, NULL, NULL, NULL, NULL, 'admin', '1', b'1', 1, '2026-02-26 10:00:00', '2026-03-02 14:57:21');
INSERT INTO `garbage_collection` VALUES (3, 'uuid-collect-003', 'GC20240601003', '1003', 'uuid-garbage-003', '每周', '09:00-11:00', 'uuid-vehicle-003', '[\"uuid-user-005\"]', '[\"uuid-point-003\"]', 'uuid-plan-status-001', 0.00, 0, NULL, NULL, 'uuid-user-003', 0.00, 0.00, '', '', NULL, b'0', NULL, 5.00, '无', 0.00, NULL, NULL, NULL, NULL, 'admin', '1', b'0', 1, '2026-02-26 10:00:00', '2026-02-28 15:26:31');
INSERT INTO `garbage_collection` VALUES (4, 'uuid-collect-004', 'GC20240601004', '1004', 'uuid-garbage-004', '每日', '14:00-18:00', 'uuid-vehicle-004', '[\"uuid-user-006\", \"uuid-user-007\"]', '[\"uuid-point-004\"]', 'uuid-plan-status-002', 100.00, 2, '2024-06-01 15:00:00', '2024-06-01 17:40:00', 'uuid-user-004', 100.00, 42.30, '离岗', '100%', '2024-06-01 17:30:00', b'1', '2024-06-01 18:00:00', 42.30, '已办结', 100.00, NULL, NULL, NULL, NULL, 'admin', '1', b'1', 1, '2026-02-26 10:00:00', '2026-03-02 14:57:09');
INSERT INTO `garbage_collection` VALUES (5, 'uuid-collect-005', 'GC20240601005', '1005', 'uuid-garbage-005', '每周', '10:00-14:00', 'uuid-vehicle-005', '[\"uuid-user-001\", \"uuid-user-003\"]', '[\"uuid-point-005\"]', 'uuid-plan-status-002', 40.00, 3, '2024-06-01 10:30:00', '2024-06-01 13:15:00', 'uuid-user-005', 40.00, 8.20, '到岗', '65%', '2024-06-01 13:10:00', b'1', NULL, 20.50, '部分办结', 33.33, NULL, NULL, NULL, NULL, 'admin', '1', b'1', 1, '2026-02-26 10:00:00', '2026-03-02 14:57:03');
INSERT INTO `garbage_collection` VALUES (6, 'uuid-collect-006', 'GC20240601006', '1006', 'uuid-garbage-006', '每月', '08:00-12:00', 'uuid-vehicle-006', '[\"uuid-user-002\", \"uuid-user-004\"]', '[\"uuid-point-006\"]', 'uuid-plan-status-002', 30.00, 1, '2024-06-01 08:10:00', '2024-06-01 09:45:00', 'uuid-user-006', 30.00, 6.80, '', '40%', '2024-06-01 09:40:00', b'1', NULL, 22.50, '无', 0.00, NULL, NULL, NULL, NULL, 'admin', '1', b'1', 1, '2026-02-26 10:00:00', '2026-03-02 14:57:02');
INSERT INTO `garbage_collection` VALUES (7, 'uuid-collect-007', 'GC20240601007', '1007', 'uuid-garbage-007', '每周', '16:00-19:00', 'uuid-vehicle-002', '[\"uuid-user-007\", \"uuid-user-009\"]', '[\"uuid-point-001\", \"uuid-user-004\"]', 'uuid-plan-status-002', 85.00, 0, NULL, NULL, 'uuid-user-007', 85.00, 21.70, '到岗', '95%', '2024-06-01 19:10:00', b'0', NULL, 25.50, '无', 0.00, NULL, NULL, NULL, NULL, 'admin', '1', b'0', 1, '2026-02-26 10:00:00', '2026-03-02 11:57:43');
INSERT INTO `garbage_collection` VALUES (8, 'uuid-collect-008', 'GC20240601008', '1001', 'uuid-garbage-001', '应急', '13:00-17:00', 'uuid-vehicle-001', '[\"uuid-user-001\"]', '[\"uuid-point-001\", \"uuid-point-008\"]', 'uuid-plan-status-001', 0.00, 0, NULL, NULL, 'uuid-user-001', 0.00, 0.00, '', '', NULL, b'0', '1970-01-01 08:00:00', 10.50, '无', 0.00, NULL, NULL, NULL, NULL, 'admin', '1', b'0', 1, '2026-02-26 10:00:00', '2026-03-02 14:34:03');
INSERT INTO `garbage_collection` VALUES (9, 'uuid-collect-009', 'GC20240601009', '1005', 'uuid-garbage-002', '每日', '08:00-12:00', 'uuid-vehicle-002', '[\"uuid-user-008\"]', '[\"uuid-point-004\", \"uuid-point-002\"]', 'uuid-plan-status-002', 60.00, 0, '2024-06-01 09:20:00', '2024-06-01 10:15:00', 'uuid-user-008', 60.00, 15.60, '到岗', '70%', '2024-06-01 10:10:00', b'1', NULL, 26.00, '无', 0.00, NULL, NULL, NULL, NULL, 'admin', '1', b'0', 1, '2024-06-01 08:00:00', '2026-03-02 11:54:29');
INSERT INTO `garbage_collection` VALUES (10, 'uuid-collect-010', 'GC20260228010', '1002', 'uuid-garbage-002', '每日', '08:30-12:30', 'uuid-vehicle-002', '[\"uuid-user-003\", \"uuid-user-009\"]', '[\"uuid-point-002\", \"uuid-point-004\"]', 'uuid-plan-status-002', 45.50, 0, NULL, NULL, 'uuid-user-003', 45.50, 12.80, '到岗', '85%', '2026-02-28 10:25:00', b'0', NULL, 28.00, '无', 0.00, NULL, NULL, NULL, NULL, 'admin', '1', b'0', 1, '2026-02-28 08:00:00', '2026-03-02 09:07:54');
INSERT INTO `garbage_collection` VALUES (11, 'uuid-collect-011', 'GC20260228011', '1004', 'uuid-garbage-004', '每日', '09:00-11:00', 'uuid-vehicle-004', '[\"uuid-user-006\", \"uuid-user-011\"]', '[\"uuid-point-004\"]', 'uuid-plan-status-001', 30.00, 0, '2026-02-28 10:15:00', '2026-02-28 11:20:00', 'uuid-user-006', 30.00, 15.60, '到岗', '60%', '2026-02-28 11:15:00', b'0', '2026-03-02 11:14:08', 52.00, '无', 0.00, NULL, NULL, NULL, NULL, 'admin', '1', b'0', 1, '2026-02-28 09:00:00', '2026-03-02 14:35:56');
INSERT INTO `garbage_collection` VALUES (12, 'uuid-collect-012', 'GC20260228012', '1001', 'uuid-garbage-001', '每周', '14:00-18:00', 'uuid-vehicle-001', '[\"uuid-user-001\", \"uuid-user-008\"]', '[\"uuid-point-001\", \"uuid-point-008\"]', 'uuid-plan-status-001', 70.00, 0, NULL, NULL, 'uuid-user-001', 70.00, 35.20, '到岗', '100%', '2026-02-28 09:45:00', b'0', '2026-03-02 11:14:08', 50.00, '无', 0.00, NULL, NULL, NULL, NULL, 'admin', '1', b'0', 1, '2026-02-28 07:00:00', '2026-03-02 13:51:45');
INSERT INTO `garbage_collection` VALUES (13, 'uuid-collect-013', 'GC20260228013', '1006', 'uuid-garbage-006', '每月', '08:00-12:00', 'uuid-vehicle-006', '[\"uuid-user-006\", \"uuid-user-013\"]', '[\"uuid-point-006\"]', 'uuid-plan-status-001', 0.00, 0, NULL, NULL, 'uuid-user-006', 0.00, 0.00, '', '', NULL, b'0', '2026-03-02 11:14:08', 45.00, '无', 0.00, NULL, NULL, NULL, NULL, 'admin', '1', b'0', 1, '2026-02-28 13:00:00', '2026-03-02 13:51:38');
INSERT INTO `garbage_collection` VALUES (14, 'uuid-collect-014', 'GC20260228014', '1003', 'uuid-garbage-003', '应急', '16:00-19:00', 'uuid-vehicle-003', '[\"uuid-user-003\"]', '[\"uuid-point-003\"]', 'uuid-plan-status-003', 85.00, 0, '2026-02-28 11:30:00', '2026-02-28 12:45:00', 'uuid-user-003', 85.00, 18.50, '到岗', '95%', '2026-02-28 12:40:00', b'1', '2026-03-02 11:14:08', 22.00, '部分办结', 50.00, NULL, NULL, NULL, NULL, 'admin', '1', b'0', 1, '2026-02-28 10:00:00', '2026-03-02 13:51:32');

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Records of garbage_collection - 添加成功状态数据（完成时间间隔较大）
-- ----------------------------
INSERT INTO `garbage_collection` VALUES 
(20, 'uuid-collect-020', 'GC20260115020', '1002', 'uuid-garbage-002', '每日', '08:00-12:00', 'uuid-vehicle-002', '[\"uuid-user-003\", \"uuid-user-004\"]', '[\"uuid-point-002\", \"uuid-point-004\"]', 'uuid-plan-status-003', 100.00, 0, NULL, NULL, 'uuid-user-003', 100.00, 32.50, '离岗', '100%', '2026-01-15 11:50:00', b'0', '2026-01-15 12:00:00', 32.50, '无', 0.00, NULL, NULL, NULL, NULL, 'admin', '1', b'0', 1, '2026-01-15 08:00:00', '2026-01-15 12:00:00'),

(21, 'uuid-collect-021', 'GC20260205021', '1004', 'uuid-garbage-004', '每日', '09:00-13:00', 'uuid-vehicle-004', '[\"uuid-user-006\", \"uuid-user-011\"]', '[\"uuid-point-004\"]', 'uuid-plan-status-003', 100.00, 0, NULL, NULL, 'uuid-user-006', 100.00, 45.80, '离岗', '100%', '2026-02-05 12:55:00', b'0', '2026-02-05 13:00:00', 45.80, '无', 0.00, NULL, NULL, NULL, NULL, 'admin', '1', b'0', 1, '2026-02-05 09:00:00', '2026-02-05 13:00:00'),

(22, 'uuid-collect-022', 'GC20260220022', '1001', 'uuid-garbage-001', '每周', '13:00-17:00', 'uuid-vehicle-001', '[\"uuid-user-001\", \"uuid-user-008\"]', '[\"uuid-point-001\", \"uuid-point-008\"]', 'uuid-plan-status-003', 100.00, 0, NULL, NULL, 'uuid-user-001', 100.00, 38.20, '离岗', '100%', '2026-02-20 16:50:00', b'0', '2026-02-20 17:00:00', 38.20, '无', 0.00, NULL, NULL, NULL, NULL, 'admin', '1', b'0', 1, '2026-02-20 13:00:00', '2026-02-20 17:00:00'),

(23, 'uuid-collect-023', 'GC20260225023', '1005', 'uuid-garbage-007', '每月', '10:00-14:00', 'uuid-vehicle-003', '[\"uuid-user-005\", \"uuid-user-012\"]', '[\"uuid-point-005\"]', 'uuid-plan-status-003', 100.00, 0, NULL, NULL, 'uuid-user-005', 100.00, 25.60, '离岗', '100%', '2026-02-25 13:55:00', b'0', '2026-02-25 14:00:00', 25.60, '无', 0.00, NULL, NULL, NULL, NULL, 'admin', '1', b'0', 1, '2026-02-25 10:00:00', '2026-02-25 14:00:00'),

(24, 'uuid-collect-024', 'GC20260301024', '1003', 'uuid-garbage-003', '应急', '15:00-18:00', 'uuid-vehicle-005', '[\"uuid-user-009\", \"uuid-user-010\"]', '[\"uuid-point-003\"]', 'uuid-plan-status-003', 100.00, 0, NULL, NULL, 'uuid-user-009', 100.00, 18.90, '离岗', '100%', '2026-03-01 17:55:00', b'0', '2026-03-01 18:00:00', 18.90, '无', 0.00, NULL, NULL, NULL, NULL, 'admin', '1', b'0', 1, '2026-03-01 15:00:00', '2026-03-01 18:00:00');