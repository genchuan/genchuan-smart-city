

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for road_config
-- ----------------------------
DROP TABLE IF EXISTS `road_config`;
CREATE TABLE `road_config`  (
                                `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '[主键ID] 主键，道路监测配置唯一标识',
                                `config_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[配置编码] UUID格式',
                                `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[配置名称]',
                                `road_id` bigint(0) NOT NULL COMMENT '[道路ID] 关联road_facility.id',
                                `road_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[道路名称]',
                                `collect_frequency` int(0) NOT NULL COMMENT '[采集频率] 数据采集频率，单位：分钟',
                                `pothole_num_threshold` decimal(10, 2) NULL DEFAULT NULL COMMENT '[坑洼数量阈值] 坑洼数量阈值',
                                `crack_length_threshold` decimal(10, 2) NULL DEFAULT NULL COMMENT '[裂缝长度阈值] 裂缝长度阈值，单位：米',
                                `road_temp_threshold` decimal(5, 2) NULL DEFAULT NULL COMMENT '[路面温度阈值] 路面温度阈值，单位：摄氏度',
                                `traffic_flow_threshold` decimal(10, 2) NULL DEFAULT NULL COMMENT '[交通流量阈值] 交通流量阈值，单位：辆/小时',
                                `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[创建人] 数据创建人ID',
                                `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[更新人] 数据更新人ID',
                                `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '[创建时间] 记录创建时间',
                                `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '[更新时间] 记录最后更新时间',
                                `deleted` bit(1) NULL DEFAULT b'0' COMMENT '[删除标识] 如:0-未删除/1-已删除',
                                `tenant_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '[租户ID] 租户唯一标识',
                                `ext_common1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段1] 通用扩展字段1',
                                `ext_common2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段2] 通用扩展字段2',
                                `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段3] 通用扩展字段3',
                                `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段4] 通用扩展字段4',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '道路监测配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of road_config
-- ----------------------------
INSERT INTO `road_config` VALUES (1, 'a1b2c3d4e5f678901234567890abcdef', '五四路配置', 1, '五四路', 20, 10.00, 10.00, 50.00, 1000.00, '1', '1', '2025-02-21 09:15:00', '2026-03-06 09:12:54', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_config` VALUES (2, 'b2c3d4e5f678901234567890abcdef01', '配置组2', 2, '华林路', 10, 5.00, 15.00, 65.00, 1200.00, '1', '1', '2025-02-22 11:20:00', '2026-03-02 11:40:35', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_config` VALUES (3, 'c3d4e5f678901234567890abcdef0123', '配置组3', 3, '湖滨北路', 3, 2.00, 8.20, 55.50, 600.00, '1', '1', '2025-02-23 13:30:00', '2026-03-02 11:40:35', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_config` VALUES (4, 'd4e5f678901234567890abcdef012345', '配置组4', 4, '中山路', 15, 4.00, 12.80, 58.00, 900.00, '1', '1', '2025-02-24 08:00:00', '2026-03-02 11:40:35', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_config` VALUES (5, 'e5f678901234567890abcdef01234567', '配置组5', 5, '泉秀路', 21, 6.00, 20.00, 70.00, 1500.00, '1', '1', '2025-02-21 17:45:00', '2026-03-09 10:55:46', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_config` VALUES (6, 'f678901234567890abcdef0123456789', '配置组6', 6, '坪山路', 8, 3.00, 9.75, 62.00, 750.00, '1', '1', '2025-02-22 19:00:00', '2026-03-02 11:40:35', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_config` VALUES (7, '678901234567890abcdef01234567890a', '配置组7', 7, '胜利路', 12, 5.00, 14.50, 63.50, 1100.00, '1', '1', '2025-02-23 10:30:00', '2026-03-02 11:40:35', b'0', 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for road_facility
-- ----------------------------
DROP TABLE IF EXISTS `road_facility`;
CREATE TABLE `road_facility`  (
                                  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '[主键ID] 主键，道路设施唯一标识',
                                  `road_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[道路编码] UUID格式',
                                  `road_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[路段名称] 路段名称',
                                  `area_code` char(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[所属区域编码] 12位地区码（GB/T 2260），关联sys_area.full_code',
                                  `area_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[所在地区名称]',
                                  `length` decimal(10, 2) NULL DEFAULT NULL COMMENT '[路段长度] 路段长度，数值',
                                  `width` decimal(10, 2) NULL DEFAULT NULL COMMENT '[路段宽度] 路段宽度，数值',
                                  `build_time` date NULL DEFAULT NULL COMMENT '[建成时间] 建成时间',
                                  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[使用状态] 如:正常/维修中/废弃',
                                  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[创建人] 数据创建人ID',
                                  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[更新人] 数据更新人ID',
                                  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '[创建时间] 记录创建时间',
                                  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '[更新时间] 记录最后更新时间',
                                  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '[删除标识] 如:0-未删除/1-已删除',
                                  `tenant_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '[租户ID] 租户唯一标识',
                                  `ext_common1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段1] 通用扩展字段1',
                                  `ext_common2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段2] 通用扩展字段2',
                                  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段3] 通用扩展字段3',
                                  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段4] 通用扩展字段4',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '道路设施表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of road_facility
-- ----------------------------
INSERT INTO `road_facility` VALUES (1, 'a1b2c3d4e5f678901234567890abcdef', '五四路', '350102001001', '中山社区', 1200.50, 15.00, '2010-06-15', '正常', '1', '1', '2025-02-21 08:30:00', '2026-03-03 14:25:49', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_facility` VALUES (2, 'b2c3d4e5f678901234567890abcdef01', '华林路', '350102001002', '庆城社区', 850.00, 12.50, '2012-09-20', '正常', '1', '1', '2025-02-22 10:20:00', '2026-03-03 14:25:52', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_facility` VALUES (3, 'c3d4e5f678901234567890abcdef0123', '湖滨北路', '350203001001', '沙坡尾社区', 2100.75, 18.00, '2015-11-02', '维修中', '1', '1', '2025-02-23 14:15:00', '2026-03-03 14:26:09', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_facility` VALUES (4, 'd4e5f678901234567890abcdef012345', '中山路', '350203001002', '蜂巢山社区', 950.30, 10.50, '2008-04-10', '正常', '1', '1', '2025-02-21 09:45:00', '2026-03-03 14:26:19', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_facility` VALUES (5, 'e5f678901234567890abcdef01234567', '泉秀路', '350203001002', '蜂巢山社区', 1800.00, 20.00, '2018-07-18', '正常', '1', '1', '2025-02-24 11:00:00', '2026-03-03 14:26:34', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_facility` VALUES (6, 'f678901234567890abcdef0123456789', '坪山路', '350203001002', '蜂巢山社区', 1350.60, 16.00, '2016-12-05', '废弃', '1', '1', '2025-02-22 16:30:00', '2026-03-03 14:26:53', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_facility` VALUES (7, '678901234567890abcdef01234567890a', '胜利路', '350203001002', '蜂巢山社区', 620.40, 8.50, '2013-03-22', '维修中', '1', '1', '2025-02-25 13:20:00', '2026-03-03 14:27:00', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_facility` VALUES (8, '78901234567890abcdef01234567890ab', '延安路', '350203001002', '蜂巢山社区', 1550.20, 14.00, '2019-09-11', '正常', '1', '1', '2025-02-23 07:50:00', '2026-03-03 14:27:04', b'0', 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for road_monitor
-- ----------------------------
DROP TABLE IF EXISTS `road_monitor`;
CREATE TABLE `road_monitor`  (
                                 `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '[主键ID] 主键，道路监测记录唯一标识',
                                 `monitor_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[监测编码] UUID格式',
                                 `road_id` bigint(0) NOT NULL COMMENT '[道路ID] 关联road_facility.id',
                                 `device_id` bigint(0) NOT NULL COMMENT '[设备ID] 关联park_device.id',
                                 `config_id` bigint(0) NOT NULL COMMENT '[配置ID] 关联road_config.id',
                                 `pothole_num` decimal(10, 2) NULL DEFAULT NULL COMMENT '[坑洼数量] 坑洼数量',
                                 `crack_length` decimal(10, 2) NULL DEFAULT NULL COMMENT '[裂缝长度] 裂缝长度，单位：米',
                                 `road_temp` decimal(5, 2) NULL DEFAULT NULL COMMENT '[路面温度] 路面温度，单位：摄氏度',
                                 `traffic_flow` decimal(10, 2) NULL DEFAULT NULL COMMENT '[交通流量] 交通流量，单位：辆/小时',
                                 `pothole_num_threshold` decimal(10, 2) NULL DEFAULT NULL COMMENT '[坑洼数量阈值快照] 采集时的坑洼数量阈值',
                                 `crack_length_threshold` decimal(10, 2) NULL DEFAULT NULL COMMENT '[裂缝长度阈值快照] 采集时的裂缝长度阈值',
                                 `road_temp_threshold` decimal(5, 2) NULL DEFAULT NULL COMMENT '[路面温度阈值快照] 采集时的路面温度阈值',
                                 `traffic_flow_threshold` decimal(10, 2) NULL DEFAULT NULL COMMENT '[交通流量阈值快照] 采集时的交通流量阈值',
                                 `collect_frequency_snapshot` decimal(10, 2) NULL DEFAULT NULL COMMENT '[采集频率快照] 采集时的数据采集频率，单位：分钟',
                                 `is_warning` int(0) NOT NULL COMMENT '[是否可以触发预警] 如:0-不可触发预警/1-可触发预警但还没触发/2-已预警',
                                 `warning_id_list_str` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[预警ID] 关联预警表ID列表',
                                 `monitor_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[监测状态] 如:运行中/已停止',
                                 `staff_id` bigint(0) NULL DEFAULT NULL COMMENT '[运维员ID] 关联park_user.id',
                                 `staff_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[运维员名称] 运维员名称',
                                 `sync_duration` decimal(10, 2) NULL DEFAULT NULL COMMENT '[数据同步时长] 单位：秒',
                                 `record_time` datetime(0) NOT NULL COMMENT '[记录时间] 监测数据记录时间',
                                 `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[创建人] 数据创建人ID',
                                 `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[更新人] 数据更新人ID',
                                 `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '[创建时间] 记录创建时间',
                                 `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '[更新时间] 记录最后更新时间',
                                 `deleted` bit(1) NULL DEFAULT b'0' COMMENT '[删除标识] 如:0-未删除/1-已删除',
                                 `tenant_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '[租户ID] 租户唯一标识',
                                 `ext_common1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段1] 通用扩展字段1',
                                 `ext_common2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段2] 通用扩展字段2',
                                 `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段3] 通用扩展字段3',
                                 `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段4] 通用扩展字段4',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25728 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '道路监测表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of road_monitor
-- ----------------------------
INSERT INTO `road_monitor` VALUES (1, 'a1b2c3d4e5f678901234567890abc111', 1, 1, 1, 2.00, 5.20, 58.50, 650.00, 3.00, 10.50, 60.00, 800.00, 5.00, 0, NULL, '已停止', 1, '张三', 120.00, '2025-02-25 08:30:00', '1', '1', '2025-02-25 08:35:00', '2026-03-05 09:50:38', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (2, 'b2c3d4e5f678901234567890abcde122', 2, 2, 2, 4.00, 12.80, 63.20, 1100.00, 5.00, 15.00, 65.00, 1200.00, 10.00, 0, NULL, '已停止', 2, '李四', 95.00, '2025-02-26 09:45:00', '1', '1', '2025-02-26 09:48:00', '2026-03-05 09:50:38', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (3, 'c3d4e5f678901234567890abcdef133', 3, 3, 3, 1.00, 3.50, 52.00, 451.00, 2.00, 8.20, 55.50, 600.00, 3.00, 0, NULL, '已停止', 3, '王五', 110.00, '2025-02-27 10:15:00', '1', '1', '2025-02-27 10:20:00', '2026-03-03 14:48:22', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (4, 'd4e5f678901234567890abcdefg144', 4, 4, 4, 5.00, 10.20, 60.50, 850.00, 4.00, 12.80, 58.00, 900.00, 15.00, 1, '1001', '运行中', 4, '赵六', 130.00, '2025-02-24 14:30:00', '1', '1', '2025-02-24 14:35:00', '2026-03-12 08:43:54', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (5, 'e5f678901234567890abcdefghi155', 5, 5, 5, 8.00, 22.50, 72.00, 1600.00, 6.00, 20.00, 70.00, 1500.00, 20.00, 1, '1002', '已停止', 5, '陈七', 150.00, '2025-02-25 16:20:00', '1', '1', '2025-02-25 16:25:00', '2026-03-12 08:43:54', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (6, 'f678901234567890abcdefghij166', 6, 1, 6, 3.00, 8.90, 60.80, 720.00, 3.00, 9.75, 62.00, 750.00, 8.00, 0, NULL, '运行中', 1, '张三', 105.00, '2025-02-23 11:10:00', '1', '1', '2025-02-23 11:15:00', '2026-03-12 08:43:55', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (7, '678901234567890abcdefghijk177', 7, 2, 7, 6.00, 13.60, 64.50, 1050.00, 5.00, 14.50, 63.50, 1100.00, 12.00, 1, '1003', '已停止', 2, '李四', 140.00, '2025-02-26 13:50:00', '1', '1', '2025-02-26 13:55:00', '2026-03-12 08:43:57', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (8, '78901234567890abcdefghijkl188', 8, 3, 2, 2.00, 6.80, 59.00, 550.00, 5.00, 15.00, 65.00, 1200.00, 10.00, 0, NULL, '已停止', 3, '王五', 115.00, '2025-02-27 08:05:00', '1', '1', '2025-02-27 08:10:00', '2026-03-03 14:48:22', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (25706, 'ssseew11', 1, 1, 1, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 1, '30795', '已停止', 30367, '张三', 0.00, '2025-02-27 08:05:00', '1', '1', '2026-02-28 14:47:45', '2026-03-05 09:50:38', b'0', 1, '', '', '', '');
INSERT INTO `road_monitor` VALUES (25707, 'ssseew', 1, 1, 1, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 1, '30795', '已停止', 30367, '张三', 0.00, '2025-02-27 08:05:00', '1', '1', '2026-02-28 14:52:27', '2026-03-05 09:50:38', b'0', 1, '', '', '', '');
INSERT INTO `road_monitor` VALUES (25708, 'sssss', 1, 1, 1, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 1, '30795', '已停止', 30367, '张三', 0.00, '2025-02-27 08:05:00', '1', '1', '2026-02-28 14:54:39', '2026-03-05 09:50:38', b'0', 1, '', '', '', '');
INSERT INTO `road_monitor` VALUES (25709, '5ac601d739c24fc2973cec4f5332801c', 1, 1, 1, 10.00, 10.00, 10.00, 170.00, 3.00, 10.50, 60.00, 800.00, 5.00, 0, NULL, '已停止', 1, '张三', 7.00, '2026-02-28 16:02:37', '1', '1', '2026-02-28 16:02:37', '2026-03-05 09:50:38', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (25710, '76514613bb9149c2aa9dc463592dc2e1', 1, 1, 1, 2.00, 1.00, 1.00, 1.00, 3.00, 10.50, 60.00, 800.00, 5.00, 0, NULL, '已停止', 1, 'test2', 6.00, '2026-02-28 17:46:43', '1', '1', '2026-02-28 17:46:43', '2026-03-05 09:50:38', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (25711, 'd3a84da79c3a47518098c8d5eabf18e6', 2, 3, 2, 2.00, 2.00, 2.00, 2.00, 5.00, 15.00, 65.00, 1200.00, 10.00, 1, NULL, '已停止', 2, '测试', 1.00, '2026-02-28 17:47:24', '1', '1', '2026-02-28 17:47:24', '2026-03-09 11:03:04', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (25712, '7a56fe0cb6cb4df29035dfa6094a62ee', 1, 1, 3, 1.00, 1.00, 1.00, 1.00, 3.00, 10.50, 60.00, 800.00, 5.00, 0, NULL, '已停止', 1, '测试', 5.00, '2026-03-02 09:01:55', '1', '1', '2026-03-02 09:01:55', '2026-03-05 09:50:38', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (25713, '541fe369f4994638b3569fcd442be600', 2, 1, 1, 13.00, 1.00, 1.00, 1.00, 3.00, 10.50, 60.00, 800.00, 5.00, 0, NULL, '已停止', 1, '88888', 4.00, '2026-03-02 09:03:12', '1', '1', '2026-03-02 09:03:12', '2026-03-05 09:50:38', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (25714, 'df2f66531ef84a1fb54cc9729534cdc9', 2, 1, 1, 2.00, 2.00, 1.00, 1.00, 3.00, 10.50, 60.00, 800.00, 5.00, 0, NULL, '已停止', 1, '张三', 1.00, '2026-03-02 09:08:04', '1', '1', '2026-03-02 09:08:04', '2026-03-12 09:14:35', b'1', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (25715, '8a0f1eaac05542c89cd15d21939dde13', 2, 2, 1, 2.00, 1.00, 1.00, 1.00, 3.00, 10.50, 60.00, 800.00, 5.00, 0, NULL, '已停止', 1, '5255', 8.00, '2026-03-02 09:32:12', '1', '1', '2026-03-02 09:32:12', '2026-03-12 09:14:30', b'1', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (25716, 'c455e71a8e7b416a8a05fd975ebe7b08', 1, 1, 1, 1.00, 1.00, 1.00, 1.00, 20.00, 20.00, 20.00, 210.00, 20.00, 0, NULL, '已停止', 1, 'test', 1.00, '2026-03-03 15:17:48', '1', '1', '2026-03-03 15:17:48', '2026-03-05 09:50:38', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (25717, '718ef4a9e03e4be4998eef9df42d4eaf', 2, 3, 4, 1.00, 1.00, 1.00, 1.00, 4.00, 12.80, 58.00, 900.00, 15.00, 0, NULL, '已停止', 1, 'ttteee', 3.00, '2026-03-03 15:26:44', '1', '1', '2026-03-03 15:26:44', '2026-03-09 10:55:25', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (25718, 'fb6fff1def014192b133eda81dd2fc43', 5, 1, 1, 1.00, 1.00, 1.00, 1.00, 20.00, 20.00, 20.00, 210.00, 20.00, 0, NULL, '已停止', 1, '2', 5.00, '2026-03-03 16:07:52', '1', '1', '2026-03-03 16:07:52', '2026-03-05 11:13:41', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (25719, 'b378829903994131b7195049bcf98e39', 1, 1, 1, 1111.00, 1111.00, 1.00, 1.00, 20.00, 20.00, 20.00, 210.00, 20.00, 2, '20,21', '已停止', 1, '33', 4.00, '2026-03-03 16:11:46', '1', '1', '2026-03-03 16:11:47', '2026-03-09 10:55:25', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (25720, '9f68075b5af84a8198dc264bc58cece9', 1, 1, 1, 99.00, 100.00, 70.00, 100.00, 20.00, 20.00, 20.00, 210.00, 20.00, 2, '13,14,15', '已停止', 1, '张三', 6.00, '2026-03-05 10:30:36', '1', '1', '2026-03-05 10:30:36', '2026-03-09 10:55:25', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (25721, '45e43d2b2f9d45fda6410f65da37bfb5', 1, 1, 1, 1000.00, 1000.00, 80.00, 1000.00, 20.00, 20.00, 20.00, 210.00, 20.00, 2, '16,17,18,19', '已停止', 1, '张三', 4.00, '2026-03-05 16:04:48', '1', '1', '2026-03-05 16:04:48', '2026-03-09 10:55:25', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (25722, '6c3448217bf144fcb5b3b302a69d4a73', 1, 1, 1, 15.00, 14.00, 74.00, 2101.00, 10.00, 10.00, 50.00, 1000.00, 20.00, 0, NULL, '已停止', 1, '张三', 2.00, '2026-03-06 09:13:29', '1', '1', '2026-03-06 09:13:29', '2026-03-09 10:55:25', b'1', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (25723, 'b03795fd2714413a8aa1794944eb0a1f', 1, 1, 1, 100.00, 100.00, 70.00, 100.00, 10.00, 10.00, 50.00, 1000.00, 20.00, 2, '22,23,24', '已停止', 1, '张三', 9.00, '2026-03-06 09:30:32', '1', '1', '2026-03-06 09:30:32', '2026-03-09 10:55:25', b'1', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (25724, '91793cd22c1740fabe68b90823a89320', 1, 1, 1, 20.00, 12.00, 74.00, 4121.00, 10.00, 10.00, 50.00, 1000.00, 20.00, 2, '31,32,33,34', '已停止', 1, '张三', 0.00, '2026-03-06 10:19:53', '1', '1', '2026-03-06 10:19:53', '2026-03-09 11:08:29', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (25725, '0d3b485ce73a4939b9cfca7a1b9ab04b', 1, 1, 1, 100.00, 100.00, 80.00, 10000.00, 10.00, 10.00, 50.00, 1000.00, 20.00, 0, NULL, '运行中', 1, '张三', 1.00, '2026-03-11 10:34:05', '1', '1', '2026-03-11 11:34:05', '2026-03-11 11:56:38', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (25726, 'a3d891d6db29427eb5fb975a75be866e', 1, 1, 1, 45.00, 78.00, 78.00, 4574.00, 10.00, 10.00, 50.00, 1000.00, 20.00, 2, '39,40,41,42', '运行中', 1, '张三', 10.00, '2026-03-11 11:45:57', '1', '1', '2026-03-11 11:45:57', '2026-03-11 11:45:57', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `road_monitor` VALUES (25727, '9a267050a68048d0b10540152cdca7fb', 1, 1, 1, 27.00, 78.00, 68.00, 4521.00, 10.00, 10.00, 50.00, 1000.00, 20.00, 2, '35,36,37,38', '运行中', 1, '张三', 6.00, '2026-03-12 09:39:53', '1', '1', '2026-03-12 09:39:53', '2026-03-12 09:39:53', b'0', 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_archive
-- ----------------------------
DROP TABLE IF EXISTS `sys_archive`;
CREATE TABLE `sys_archive`  (
                                `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '[主键ID] 主键，归档记录唯一标识',
                                `archive_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '[归档编号] 归档唯一编号',
                                `order_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[工单编号] 关联工单编号',
                                `order_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[工单类型] 工单类型',
                                `biz_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[工单处置类型] 工单处置类型，工单获取',
                                `complete_time` datetime(0) NULL DEFAULT NULL COMMENT '[工单完成时间] 工单完成时间',
                                `warn_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[预警编号] 预警编号',
                                `facility_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '[设施编码] 关联对应设施表编码',
                                `facility_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[设施名称] 设施名称',
                                `facility_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '[设施类型] 设施类型',
                                `assign_staff_id` bigint(0) NULL DEFAULT NULL COMMENT '[指派运维员ID] 指派运维员id，工单获取',
                                `assign_staff_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[指派运维员姓名] 指派运维员name，工单获取',
                                `check_staff_id` bigint(0) NULL DEFAULT NULL COMMENT '[核查人ID] 核查人id',
                                `check_staff_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[核查人姓名] 核查人name',
                                `area_full_code` char(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[所属区域12位编码] 所属区域12位编码，工单获取',
                                `area_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[所属区域名称] 所属区域名称，工单获取',
                                `deal_duration` int(0) NULL DEFAULT NULL COMMENT '[处理时间] 处理时间，预警产生时间到工单完成时间，单位分钟',
                                `check_result` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[核查结果]如：达标/未达标/待复核/数据异常',
                                `check_suggest` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[核查意见] 核查意见',
                                `file_num` double NULL DEFAULT NULL COMMENT '[归档资料数] 归档资料数，数值，从工单统计',
                                `over_index_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[超标指标名称] 超标指标名称（处置前），预警得到',
                                `before_index_value` decimal(10, 2) NULL DEFAULT NULL COMMENT '[处置前指标值] 处置前指标值，预警得到',
                                `after_index_value` decimal(10, 2) NULL DEFAULT NULL COMMENT '[处置后指标值] 处置后指标值，工单得到',
                                `recover_value` decimal(10, 2) NULL DEFAULT NULL COMMENT '[恢复值] = 处置前指标值 - 处置后指标值',
                                `threshold_value` decimal(10, 2) NULL DEFAULT NULL COMMENT '[指标阈值] 指标阈值，预警得到',
                                `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '[创建时间] 记录创建时间',
                                `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '[更新时间] 记录最后更新时间',
                                `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[创建人] 创建人ID',
                                `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[更新人] 更新人ID',
                                `deleted` bit(1) NULL DEFAULT b'0' COMMENT '[删除标识]如:0-未删除/1-已删除',
                                `tenant_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '[租户ID] 租户唯一标识',
                                `ext_common1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[通用扩展字段1] 通用扩展字段1',
                                `ext_common2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[通用扩展字段2] 通用扩展字段2',
                                `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[通用扩展字段3] 通用扩展字段3',
                                `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[通用扩展字段4] 通用扩展字段4',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '归档表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_archive
-- ----------------------------
INSERT INTO `sys_archive` VALUES (1, 'ARC202603100001', 'WO202603060001', '处置', '裂缝修补', '2026-03-10 10:20:00', 'WARN202603100001', 'FAC0001', '北京朝阳路段A', '道路', 101, '张伟', 201, '李强', '110105000000', '北京市朝阳区', 120, '达标', '处置后裂缝长度恢复正常', 4, '裂缝长度', 15.00, 3.00, 12.00, 10.00, '2026-03-10 14:31:03', '2026-03-12 09:31:58', '1', '1', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_archive` VALUES (3, 'ARC202603100003', 'WOR0310247140', '处置', '排水疏通', '2026-03-10 12:40:00', 'WARN202603100003', 'FAC0003', '北京丰台路段C', '道路', 103, '刘洋', 203, '陈涛', '110106000000', '北京市丰台区', 150, '达标', '积水问题已处理', 5, '积水深度', 12.00, 2.00, 10.00, 5.00, '2026-03-10 14:31:03', '2026-03-12 09:31:58', '1', '1', b'0', 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_area
-- ----------------------------
DROP TABLE IF EXISTS `sys_area`;
CREATE TABLE `sys_area`  (
                             `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                             `parent_id` bigint(0) NOT NULL COMMENT '上级行政区划ID（0表示根节点）',
                             `full_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '完整行政区划代码（12位）',
                             `short_code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '短代码（省/市/县6位，乡镇/社区3位）',
                             `comm_id` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '社区ID',
                             `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '行政区划名称',
                             `level` tinyint(0) NOT NULL COMMENT '层级：1-省级 2-市级 3-县级 4-乡镇 5-社区',
                             `area_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型（街道/镇/乡/社区/村）',
                             `effective_time` datetime(0) NULL DEFAULT NULL COMMENT '生效时间',
                             `invalid_time` datetime(0) NULL DEFAULT NULL COMMENT '失效时间',
                             `boundary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '社区边界坐标（2000坐标系）',
                             `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '备注',
                             `ext_cat1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类扩展字段1',
                             `ext_cat2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类扩展字段2',
                             `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
                             `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
                             `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
                             `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
                             `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
                             `tenant_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '租户ID',
                             `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                             `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 252 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '统一行政区划配置表（树形结构）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_area
-- ----------------------------
INSERT INTO `sys_area` VALUES (1, 0, '110000000000', '110000', NULL, '北京市', 1, '直辖市', '2025-10-01 17:01:57', '2026-02-01 17:02:06', NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-10-23 14:47:35', '2025-10-30 09:21:39');
INSERT INTO `sys_area` VALUES (2, 0, '120000000000', '120000', NULL, '天津市', 1, '直辖市', '2025-10-01 17:01:57', '2026-02-01 17:02:06', NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-10-23 14:47:35', '2025-10-30 09:21:55');
INSERT INTO `sys_area` VALUES (3, 0, '130000000000', '130000', NULL, '河北省', 1, '省', '2025-10-01 17:01:57', '2026-02-01 17:02:06', NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-10-23 14:47:35', '2025-10-30 09:21:59');
INSERT INTO `sys_area` VALUES (4, 0, '140000000000', '140000', NULL, '山西省', 1, '省', '2025-10-01 17:01:57', '2026-02-01 17:02:06', NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-10-23 14:47:35', '2025-10-30 09:22:09');
INSERT INTO `sys_area` VALUES (5, 1, '110100000000', '110100', NULL, '北京市辖区', 2, '地级市', '2025-10-01 17:01:57', '2026-02-01 17:02:06', NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-10-23 14:47:35', '2025-10-30 09:22:09');
INSERT INTO `sys_area` VALUES (6, 3, '130100000000', '130100', NULL, '石家庄市', 2, '地级市', '2025-10-01 17:01:57', '2026-02-01 17:02:06', NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-10-23 14:47:35', '2025-10-30 09:22:09');
INSERT INTO `sys_area` VALUES (8, 4, '140100000000', '140100', NULL, '太原市', 2, '地级市', '2025-10-01 17:01:57', '2026-02-01 17:02:06', NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-10-23 14:47:35', '2025-10-30 09:22:09');
INSERT INTO `sys_area` VALUES (9, 5, '110101000000', '110101', NULL, '东城区', 3, '市辖区', '2025-10-01 17:01:57', '2026-02-01 17:02:06', NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-10-23 14:47:35', '2025-10-30 09:22:09');
INSERT INTO `sys_area` VALUES (10, 5, '110102000000', '110102', NULL, '西城区', 3, '市辖区', '2025-10-01 17:01:57', '2026-02-01 17:02:06', NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-10-23 14:47:35', '2025-10-30 09:22:09');
INSERT INTO `sys_area` VALUES (11, 6, '130102000000', '130102', NULL, '长安区', 3, '市辖区', '2025-10-01 17:01:57', '2026-02-01 17:02:06', NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-10-23 14:47:35', '2025-10-30 09:22:09');
INSERT INTO `sys_area` VALUES (12, 6, '130104000000', '130104', NULL, '桥西区', 3, '市辖区', '2025-10-01 17:01:57', '2026-02-01 17:02:06', NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-10-23 14:47:35', '2025-10-30 09:22:09');
INSERT INTO `sys_area` VALUES (13, 8, '140105000000', '140105', NULL, '小店区', 3, '市辖区', '2025-10-01 17:01:57', '2026-02-01 17:02:06', NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-10-23 14:47:35', '2025-10-30 09:22:09');
INSERT INTO `sys_area` VALUES (14, 8, '140106000000', '140106', NULL, '迎泽区', 3, '市辖区', '2025-10-01 17:01:57', '2026-02-01 17:02:06', NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-10-23 14:47:35', '2025-10-30 09:22:09');
INSERT INTO `sys_area` VALUES (15, 9, '110101001000', '001', NULL, '东华门街道', 4, '街道', '2025-10-01 17:01:57', '2026-02-01 17:02:06', NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-10-23 14:47:35', '2025-10-30 09:22:09');
INSERT INTO `sys_area` VALUES (16, 9, '110101002000', '002', NULL, '景山街道', 4, '街道', '2025-10-01 17:01:57', '2026-02-01 17:02:06', NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-10-23 14:47:35', '2025-10-30 09:22:09');
INSERT INTO `sys_area` VALUES (17, 11, '130102001000', '001', NULL, '建北街道', 4, '街道', '2025-10-01 17:01:57', '2026-02-01 17:02:06', NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-10-23 14:47:35', '2025-10-30 09:22:09');
INSERT INTO `sys_area` VALUES (18, 11, '130102002000', '002', NULL, '青园街道', 4, '街道', '2025-10-01 17:01:57', '2026-02-01 17:02:06', NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-10-23 14:47:35', '2025-10-30 09:22:09');
INSERT INTO `sys_area` VALUES (19, 13, '140105001000', '001', NULL, '坞城街道', 4, '街道', '2025-10-01 17:01:57', '2026-02-01 17:02:06', NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-10-23 14:47:35', '2025-10-30 09:22:09');
INSERT INTO `sys_area` VALUES (20, 13, '140105002000', '002', NULL, '营盘街道', 4, '街道', '2025-10-01 17:01:57', '2026-02-01 17:02:06', NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-10-23 14:47:35', '2025-10-30 09:22:09');
INSERT INTO `sys_area` VALUES (21, 15, '110101001001', '001', NULL, '多福巷社区', 5, '社区', '2025-10-01 17:01:57', '2026-02-01 17:02:06', NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-10-23 14:47:35', '2025-10-30 09:22:09');
INSERT INTO `sys_area` VALUES (22, 15, '110101001002', '002', NULL, '银闸社区', 5, '社区', '2025-10-01 17:01:57', '2026-02-01 17:02:06', NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-10-23 14:47:35', '2025-10-30 09:22:09');
INSERT INTO `sys_area` VALUES (23, 17, '130102001001', '001', NULL, '华平社区', 5, '社区', '2025-10-01 17:01:57', '2026-02-01 17:02:06', NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-10-23 14:47:35', '2025-10-30 09:22:09');
INSERT INTO `sys_area` VALUES (24, 17, '130102001002', '002', NULL, '光华社区', 5, '社区', '2025-10-01 09:33:19', '2025-10-10 09:33:25', NULL, NULL, NULL, NULL, NULL, NULL, '', '1', b'0', 1, '2025-10-23 14:47:35', '2025-10-24 09:42:11');
INSERT INTO `sys_area` VALUES (25, 19, '140105001001', '001', NULL, '八一社区', 5, '社区', '2025-10-01 09:33:19', '2025-10-10 09:33:25', NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-10-23 14:47:35', '2025-10-30 09:22:20');
INSERT INTO `sys_area` VALUES (26, 19, '140105001002', '002', NULL, '长风社区', 5, '社区', '2025-10-01 09:33:19', '2025-10-10 09:33:25', NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-10-23 14:47:35', '2025-10-30 09:22:20');
INSERT INTO `sys_area` VALUES (27, 15, '110101001001', '001', NULL, '多福巷社区', 5, '社区', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 0, '2025-11-03 15:04:32', '2025-11-03 15:04:32');
INSERT INTO `sys_area` VALUES (28, 15, '110101001002', '002', NULL, '银闸社区', 5, '社区', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 0, '2025-11-03 15:04:32', '2025-11-03 15:04:32');
INSERT INTO `sys_area` VALUES (29, 17, '130102001001', '001', NULL, '华平社区', 5, '社区', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 0, '2025-11-03 15:04:32', '2025-11-03 15:04:32');
INSERT INTO `sys_area` VALUES (30, 17, '130102001002', '002', NULL, '光华社区', 5, '社区', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 0, '2025-11-03 15:04:32', '2025-11-03 15:04:32');
INSERT INTO `sys_area` VALUES (31, 19, '140105001001', '001', NULL, '八一社区', 5, '社区', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 0, '2025-11-03 15:04:32', '2025-11-03 15:04:32');
INSERT INTO `sys_area` VALUES (32, 19, '140105001002', '002', NULL, '长风社区', 5, '社区', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 0, '2025-11-03 15:04:32', '2025-11-03 15:04:32');
INSERT INTO `sys_area` VALUES (33, 5, '110105000000', '110105', NULL, '朝阳区', 3, '市辖区', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 0, '2025-11-03 15:04:32', '2025-11-03 15:04:32');
INSERT INTO `sys_area` VALUES (34, 5, '110106000000', '110106', NULL, '丰台区', 3, '市辖区', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 0, '2025-11-03 15:04:32', '2025-11-03 15:04:32');
INSERT INTO `sys_area` VALUES (35, 5, '110107000000', '110107', NULL, '石景山区', 3, '市辖区', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 0, '2025-11-03 15:04:32', '2025-11-03 15:04:32');
INSERT INTO `sys_area` VALUES (36, 5, '110108000000', '110108', NULL, '海淀区', 3, '市辖区', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 0, '2025-11-03 15:04:32', '2025-11-03 15:04:32');
INSERT INTO `sys_area` VALUES (37, 27, '110105001000', '001', NULL, '朝外街道', 4, '街道', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 0, '2025-11-03 15:04:32', '2025-11-03 15:04:32');
INSERT INTO `sys_area` VALUES (38, 27, '110105002000', '002', NULL, '建国门街道', 4, '街道', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 0, '2025-11-03 15:04:32', '2025-11-03 15:04:32');
INSERT INTO `sys_area` VALUES (39, 31, '110105001001', '001', NULL, '吉祥里社区', 5, '社区', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 0, '2025-11-03 15:04:32', '2025-11-03 15:04:32');
INSERT INTO `sys_area` VALUES (40, 31, '110105001002', '002', NULL, '三丰里社区', 5, '社区', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 0, '2025-11-03 15:04:32', '2025-11-03 15:04:32');
INSERT INTO `sys_area` VALUES (41, 32, '110105002001', '001', NULL, '赵家楼社区', 5, '社区', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 0, '2025-11-03 15:04:32', '2025-11-03 15:04:32');
INSERT INTO `sys_area` VALUES (42, 32, '110105002002', '002', NULL, '大雅宝社区', 5, '社区', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 0, '2025-11-03 15:04:32', '2025-11-03 15:04:32');
INSERT INTO `sys_area` VALUES (43, 6, '130105000000', '130105', NULL, '新华区', 3, '市辖区', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 0, '2025-11-03 15:04:32', '2025-11-03 15:04:32');
INSERT INTO `sys_area` VALUES (44, 6, '130106000000', '130106', NULL, '裕华区', 3, '市辖区', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 0, '2025-11-03 15:04:32', '2025-11-03 15:04:32');
INSERT INTO `sys_area` VALUES (45, 37, '130105001000', '001', NULL, '宁安街道', 4, '街道', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 0, '2025-11-03 15:04:32', '2025-11-03 15:04:32');
INSERT INTO `sys_area` VALUES (46, 37, '130105002000', '002', NULL, '东焦街道', 4, '街道', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 0, '2025-11-03 15:04:32', '2025-11-03 15:04:32');
INSERT INTO `sys_area` VALUES (47, 39, '130105001001', '001', NULL, '和平西路社区', 5, '社区', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 0, '2025-11-03 15:04:32', '2025-11-03 15:04:32');
INSERT INTO `sys_area` VALUES (48, 39, '130105001002', '002', NULL, '永泰街社区', 5, '社区', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 0, '2025-11-03 15:04:32', '2025-11-03 15:04:32');
INSERT INTO `sys_area` VALUES (56, 0, '440000000000', '440000', NULL, '广东省', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-12-03 16:53:20', '2025-12-03 17:03:08');
INSERT INTO `sys_area` VALUES (57, 2, '120100000000', '120100', NULL, '天津市辖区', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', b'0', 1, '2025-12-03 17:00:18', '2025-12-03 17:08:37');
INSERT INTO `sys_area` VALUES (58, 57, '120301000000', '120301', NULL, '河西区', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', b'0', 1, '2025-12-03 17:02:12', '2025-12-03 17:02:12');
INSERT INTO `sys_area` VALUES (59, 56, '440100000000', '440100', NULL, '广州市', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-12-03 17:11:24', '2025-12-03 17:11:24');
INSERT INTO `sys_area` VALUES (62, 59, '440104000000', '440104', NULL, '越秀区', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-12-03 17:27:21', '2025-12-03 17:27:21');
INSERT INTO `sys_area` VALUES (63, 59, '440103000000', '440103', NULL, '荔湾区', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', b'0', 1, '2025-12-03 17:27:28', '2025-12-03 17:27:28');
INSERT INTO `sys_area` VALUES (68, 56, '440300000000', '440300', NULL, '深圳市', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', b'0', 1, '2025-12-04 08:48:43', '2025-12-04 08:48:43');
INSERT INTO `sys_area` VALUES (69, 68, '440304', '440304', NULL, '福田区', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', b'0', 1, '2025-12-04 08:49:28', '2025-12-04 08:56:40');
INSERT INTO `sys_area` VALUES (70, 0, '350000000000', '350000', NULL, '福建省', 1, '省', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (71, 70, '350100000000', '350100', NULL, '福州市', 2, '地级市', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (72, 71, '350102000000', '350102', NULL, '鼓楼区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (73, 71, '350103000000', '350103', NULL, '台江区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (74, 71, '350104000000', '350104', NULL, '仓山区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (75, 71, '350105000000', '350105', NULL, '马尾区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (76, 71, '350111000000', '350111', NULL, '晋安区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (77, 71, '350112000000', '350112', NULL, '长乐区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (78, 71, '350121000000', '350121', NULL, '闽侯县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (79, 71, '350122000000', '350122', NULL, '连江县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (80, 70, '350200000000', '350200', NULL, '厦门市', 2, '地级市', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (81, 80, '350203000000', '350203', NULL, '思明区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (82, 80, '350205000000', '350205', NULL, '海沧区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (83, 80, '350206000000', '350206', NULL, '湖里区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (84, 80, '350211000000', '350211', NULL, '集美区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (85, 80, '350212000000', '350212', NULL, '同安区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (86, 80, '350213000000', '350213', NULL, '翔安区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (87, 70, '350300000000', '350300', NULL, '莆田市', 2, '地级市', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (88, 87, '350302000000', '350302', NULL, '城厢区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (89, 87, '350303000000', '350303', NULL, '涵江区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (90, 87, '350304000000', '350304', NULL, '荔城区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (91, 87, '350305000000', '350305', NULL, '秀屿区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (92, 87, '350322000000', '350322', NULL, '仙游县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (93, 70, '350400000000', '350400', NULL, '三明市', 2, '地级市', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (94, 93, '350402000000', '350402', NULL, '梅列区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (95, 93, '350403000000', '350403', NULL, '三元区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (96, 93, '350421000000', '350421', NULL, '明溪县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (97, 93, '350423000000', '350423', NULL, '清流县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (98, 93, '350424000000', '350424', NULL, '宁化县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (99, 93, '350425000000', '350425', NULL, '大田县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (100, 93, '350426000000', '350426', NULL, '尤溪县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (101, 93, '350427000000', '350427', NULL, '沙县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (102, 93, '350428000000', '350428', NULL, '将乐县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (103, 93, '350429000000', '350429', NULL, '泰宁县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (104, 93, '350430000000', '350430', NULL, '建宁县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (105, 93, '350481000000', '350481', NULL, '永安市', 3, '县级市', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (106, 70, '350500000000', '350500', NULL, '泉州市', 2, '地级市', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (107, 106, '350502000000', '350502', NULL, '鲤城区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (108, 106, '350503000000', '350503', NULL, '丰泽区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (109, 106, '350504000000', '350504', NULL, '洛江区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (110, 106, '350505000000', '350505', NULL, '泉港区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (111, 106, '350521000000', '350521', NULL, '惠安县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (112, 106, '350524000000', '350524', NULL, '安溪县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (113, 106, '350525000000', '350525', NULL, '永春县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (114, 106, '350526000000', '350526', NULL, '德化县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (115, 106, '350527000000', '350527', NULL, '金门县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (116, 106, '350581000000', '350581', NULL, '石狮市', 3, '县级市', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (117, 106, '350582000000', '350582', NULL, '晋江市', 3, '县级市', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (118, 106, '350583000000', '350583', NULL, '南安市', 3, '县级市', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (119, 70, '350600000000', '350600', NULL, '漳州市', 2, '地级市', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (120, 119, '350602000000', '350602', NULL, '芗城区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (121, 119, '350603000000', '350603', NULL, '龙文区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (122, 119, '350622000000', '350622', NULL, '云霄县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (123, 119, '350623000000', '350623', NULL, '漳浦县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (124, 119, '350624000000', '350624', NULL, '诏安县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (125, 119, '350625000000', '350625', NULL, '长泰县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (126, 119, '350626000000', '350626', NULL, '东山县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (127, 119, '350627000000', '350627', NULL, '南靖县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (128, 119, '350628000000', '350628', NULL, '平和县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (129, 119, '350629000000', '350629', NULL, '华安县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (130, 119, '350681000000', '350681', NULL, '龙海市', 3, '县级市', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (131, 70, '350700000000', '350700', NULL, '南平市', 2, '地级市', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (132, 131, '350702000000', '350702', NULL, '延平区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (133, 131, '350703000000', '350703', NULL, '建阳区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (134, 131, '350721000000', '350721', NULL, '顺昌县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (135, 131, '350722000000', '350722', NULL, '浦城县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (136, 131, '350723000000', '350723', NULL, '光泽县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (137, 131, '350724000000', '350724', NULL, '松溪县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (138, 131, '350725000000', '350725', NULL, '政和县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (139, 131, '350781000000', '350781', NULL, '邵武市', 3, '县级市', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (140, 131, '350782000000', '350782', NULL, '武夷山市', 3, '县级市', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (141, 131, '350783000000', '350783', NULL, '建瓯市', 3, '县级市', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (142, 70, '350800000000', '350800', NULL, '龙岩市', 2, '地级市', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (143, 142, '350802000000', '350802', NULL, '新罗区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (144, 142, '350803000000', '350803', NULL, '永定区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (145, 142, '350821000000', '350821', NULL, '长汀县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (146, 142, '350823000000', '350823', NULL, '上杭县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (147, 142, '350824000000', '350824', NULL, '武平县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (148, 142, '350825000000', '350825', NULL, '连城县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (149, 142, '350881000000', '350881', NULL, '漳平市', 3, '县级市', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (150, 70, '350900000000', '350900', NULL, '宁德市', 2, '地级市', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (151, 150, '350902000000', '350902', NULL, '蕉城区', 3, '市辖区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (152, 150, '350921000000', '350921', NULL, '霞浦县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (153, 150, '350922000000', '350922', NULL, '古田县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (154, 150, '350923000000', '350923', NULL, '屏南县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (155, 150, '350924000000', '350924', NULL, '寿宁县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (156, 150, '350925000000', '350925', NULL, '周宁县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (157, 150, '350926000000', '350926', NULL, '柘荣县', 3, '县', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (158, 150, '350981000000', '350981', NULL, '福安市', 3, '县级市', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (159, 150, '350982000000', '350982', NULL, '福鼎市', 3, '县级市', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:31:37', '2025-12-22 17:39:47');
INSERT INTO `sys_area` VALUES (160, 72, '350102001000', '001', NULL, '鼓东街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (161, 72, '350102002000', '002', NULL, '鼓西街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (162, 72, '350102003000', '003', NULL, '温泉街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (163, 72, '350102004000', '004', NULL, '东街街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (164, 72, '350102005000', '005', NULL, '南街街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (165, 72, '350102006000', '006', NULL, '安泰街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (166, 72, '350102007000', '007', NULL, '华大街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (167, 72, '350102008000', '008', NULL, '水部街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (168, 72, '350102009000', '009', NULL, '五凤街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (169, 72, '350102010000', '010', NULL, '洪山镇', 4, '镇', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (170, 160, '350102001001', '001', NULL, '中山社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (171, 160, '350102001002', '002', NULL, '庆城社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (172, 160, '350102001003', '003', NULL, '开元社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (173, 160, '350102001004', '004', NULL, '树兜社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (174, 160, '350102001005', '005', NULL, '观风亭社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (175, 160, '350102001006', '006', NULL, '贤南社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (176, 81, '350203001000', '001', NULL, '厦港街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (177, 81, '350203002000', '002', NULL, '中华街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (178, 81, '350203003000', '003', NULL, '滨海街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (179, 81, '350203004000', '004', NULL, '鹭江街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (180, 81, '350203005000', '005', NULL, '开元街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (181, 81, '350203006000', '006', NULL, '梧村街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (182, 81, '350203007000', '007', NULL, '筼筜街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (183, 81, '350203008000', '008', NULL, '莲前街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (184, 81, '350203009000', '009', NULL, '嘉莲街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (185, 81, '350203010000', '010', NULL, '鼓浪屿街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (186, 176, '350203001001', '001', NULL, '沙坡尾社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (187, 176, '350203001002', '002', NULL, '蜂巢山社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (188, 176, '350203001003', '003', NULL, '巡司顶社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (189, 176, '350203001004', '004', NULL, '鸿山社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (190, 176, '350203001005', '005', NULL, '下沃社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (191, 176, '350203001006', '006', NULL, '大学路社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (192, 107, '350502001000', '001', NULL, '开元街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (193, 107, '350502002000', '002', NULL, '鲤中街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (194, 107, '350502003000', '003', NULL, '海滨街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (195, 107, '350502004000', '004', NULL, '临江街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (196, 107, '350502005000', '005', NULL, '江南街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (197, 107, '350502006000', '006', NULL, '浮桥街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (198, 192, '350502001001', '001', NULL, '梅峰社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (199, 192, '350502001002', '002', NULL, '东北社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (200, 192, '350502001003', '003', NULL, '新春社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (201, 192, '350502001004', '004', NULL, '红梅社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (202, 192, '350502001005', '005', NULL, '双塔社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (203, 192, '350502001006', '006', NULL, '华新社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (204, 78, '350121100000', '100', NULL, '甘蔗街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (205, 78, '350121101000', '101', NULL, '白沙镇', 4, '镇', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (206, 78, '350121102000', '102', NULL, '南屿镇', 4, '镇', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (207, 78, '350121103000', '103', NULL, '尚干镇', 4, '镇', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (208, 78, '350121104000', '104', NULL, '祥谦镇', 4, '镇', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (209, 78, '350121105000', '105', NULL, '青口镇', 4, '镇', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (210, 204, '350121100001', '001', NULL, '双池社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (211, 204, '350121100002', '002', NULL, '三福社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (212, 204, '350121100003', '003', NULL, '化龙社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (213, 204, '350121100004', '004', NULL, '滨江社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (214, 204, '350121100005', '005', NULL, '昙石社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (215, 204, '350121100006', '006', NULL, '洽浦社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (216, 85, '350212001000', '001', NULL, '大同街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (217, 85, '350212002000', '002', NULL, '祥平街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (218, 85, '350212101000', '101', NULL, '莲花镇', 4, '镇', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (219, 85, '350212102000', '102', NULL, '新民镇', 4, '镇', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (220, 85, '350212103000', '103', NULL, '洪塘镇', 4, '镇', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (221, 85, '350212104000', '104', NULL, '西柯镇', 4, '镇', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (222, 216, '350212001001', '001', NULL, '三秀社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (223, 216, '350212001002', '002', NULL, '后炉社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (224, 216, '350212001003', '003', NULL, '溪边社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (225, 216, '350212001004', '004', NULL, '西安社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (226, 216, '350212001005', '005', NULL, '城西社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (227, 216, '350212001006', '006', NULL, '凤山社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (228, 120, '350602001000', '001', NULL, '东铺头街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (229, 120, '350602002000', '002', NULL, '西桥街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (230, 120, '350602003000', '003', NULL, '新桥街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (231, 120, '350602004000', '004', NULL, '巷口街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (232, 120, '350602005000', '005', NULL, '南坑街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (233, 120, '350602006000', '006', NULL, '通北街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (234, 228, '350602001001', '001', NULL, '县后社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (235, 228, '350602001002', '002', NULL, '瑞京社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (236, 228, '350602001003', '003', NULL, '西街社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (237, 228, '350602001004', '004', NULL, '加禾社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (238, 228, '350602001005', '005', NULL, '水仙花社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (239, 228, '350602001006', '006', NULL, '北塔社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (240, 151, '350902001000', '001', NULL, '蕉南街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (241, 151, '350902002000', '002', NULL, '蕉北街道', 4, '街道', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (242, 151, '350902100000', '100', NULL, '漳湾镇', 4, '镇', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (243, 151, '350902101000', '101', NULL, '七都镇', 4, '镇', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (244, 151, '350902102000', '102', NULL, '八都镇', 4, '镇', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (245, 151, '350902103000', '103', NULL, '九都镇', 4, '镇', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (246, 240, '350902001001', '001', NULL, '鹏程社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (247, 240, '350902001002', '002', NULL, '海滨社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (248, 240, '350902001003', '003', NULL, '荷园社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (249, 240, '350902001004', '004', NULL, '芦坪社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (250, 240, '350902001005', '005', NULL, '福山社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');
INSERT INTO `sys_area` VALUES (251, 240, '350902001006', '006', NULL, '中南社区', 5, '社区', '2023-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'system', 'system', b'0', 1, '2025-12-22 17:47:13', '2025-12-22 17:47:13');

-- ----------------------------
-- Table structure for sys_device
-- ----------------------------
DROP TABLE IF EXISTS `sys_device`;
CREATE TABLE `sys_device`  (
                               `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '[主键ID] 主键，设备唯一标识',
                               `device_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[设备编码] UUID格式',
                               `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[设备名称] 设备名称',
                               `online_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[设备在线状态] 如:在线/离线/异常',
                               `category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[设备分类] 如道路设施监测等等',
                               `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[设备描述] 设备描述',
                               `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[创建人] 数据创建人ID',
                               `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[更新人] 数据更新人ID',
                               `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '[创建时间] 记录创建时间',
                               `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '[更新时间] 记录最后更新时间',
                               `deleted` bit(1) NULL DEFAULT b'0' COMMENT '[删除标识] 如:0-未删除/1-已删除',
                               `tenant_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '[租户ID] 租户唯一标识',
                               `ext_common1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段1] 通用扩展字段1',
                               `ext_common2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段2] 通用扩展字段2',
                               `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段3] 通用扩展字段3',
                               `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段4] 通用扩展字段4',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '设备表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_device
-- ----------------------------
INSERT INTO `sys_device` VALUES (1, 'a1b2c3d4e5f678901234567890def001', '五四路监测设备A', '在线', '路面监测', '部署在五四路K2+300处', '1', '1', '2025-02-21 09:20:00', '2025-02-22 10:15:00', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_device` VALUES (2, 'b2c3d4e5f678901234567890def002', '华林路监测设备B', '在线', '路面监测', '部署在华林路与树汤路交叉口', '1', '1', '2025-02-22 11:30:00', '2025-02-23 14:20:00', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_device` VALUES (3, 'c3d4e5f678901234567890def003', '湖滨北路监测设备C', '异常', '路面监测', '设备通信模块故障', '1', '1', '2025-02-23 13:45:00', '2025-02-24 09:30:00', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_device` VALUES (4, 'd4e5f678901234567890def004', '中山路监测设备D', '离线', '路面监测', '因道路施工断电', '1', '1', '2025-02-24 08:15:00', '2025-02-25 16:40:00', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_device` VALUES (5, 'e5f678901234567890def005', '泉秀路监测设备E', '在线', '交通流量', '雷达式流量检测器', '1', '1', '2025-02-21 18:00:00', '2025-02-22 08:50:00', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_device` VALUES (6, 'f678901234567890def006', '坪山路监测设备F', '在线', '路面监测', '部署在坪山路高架入口', '1', '1', '2025-02-22 19:30:00', '2025-02-23 11:45:00', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_device` VALUES (7, '678901234567890def007', '胜利路监测设备G', '异常', '路面监测', '温度传感器异常', '1', '1', '2025-02-23 10:50:00', '2025-02-24 13:20:00', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_device` VALUES (8, '78901234567890def008', '延安路监测设备H', '在线', '交通流量', '地磁式流量检测器', '1', '1', '2025-02-25 14:10:00', '2025-02-26 09:05:00', b'0', 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
                             `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '[主键ID] 用户唯一标识',
                             `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[用户名称]用户姓名',
                             `username` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[唯一用户名] ',
                             `account_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[登录账号] 登录账号，需保证业务唯一，必填',
                             `role_id` bigint(0) NOT NULL COMMENT '[角色ID] 用户角色ID，关联sys_role.id，必填',
                             `dept_id` bigint(0) NULL DEFAULT NULL COMMENT '[部门ID] 所属部门ID，关联sys_dept.id',
                             `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[联系电话] 联系电话',
                             `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[账号状态]如:启用/停用',
                             `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '[创建时间] 记录创建时间，自动生成',
                             `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '[更新时间] 记录最后更新时间，自动同步',
                             `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[创建人] 数据创建人ID',
                             `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[更新人] 数据更新人ID',
                             `deleted` bit(1) NULL DEFAULT b'0' COMMENT '[删除标识]如:0-未删除/1-已删除',
                             `tenant_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '[租户ID] 租户唯一标识，必填，默认0',
                             `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段1]',
                             `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段2]',
                             `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段3]',
                             `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段4]',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (8, '张伟', 'zhangwei', 'zhangwei', 1, 101, '13800000001', '启用', '2026-03-10 09:26:57', '2026-03-10 09:37:55', 'admin', 'admin', b'0', 1, '北京', NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (9, '李娜', 'lina', 'lina', 2, 102, '13800000002', '启用', '2026-03-10 09:26:57', '2026-03-10 09:37:55', 'admin', 'admin', b'0', 1, '北京', NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (10, '王强', 'wangqiang', 'wangqiang', 2, 101, '13800000003', '启用', '2026-03-10 09:26:57', '2026-03-10 09:37:55', 'admin', 'admin', b'0', 1, '北京', NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (11, '刘洋', 'liuyang', 'liuyang', 3, 103, '13800000004', '停用', '2026-03-10 09:26:57', '2026-03-10 09:37:55', 'admin', 'admin', b'0', 1, '北京', NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (12, '陈晨', 'chenchen', 'chenchen', 2, 102, '13800000005', '启用', '2026-03-10 09:26:57', '2026-03-10 09:37:55', 'admin', 'admin', b'0', 1, '北京', NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (13, '赵磊', 'zhaolei', 'zhaolei', 3, 104, '13800000006', '启用', '2026-03-10 09:26:57', '2026-03-10 09:37:55', 'admin', 'admin', b'0', 1, '北京', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_warn
-- ----------------------------
DROP TABLE IF EXISTS `sys_warn`;
CREATE TABLE `sys_warn`  (
                             `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '[主键ID] 主键，预警记录唯一标识',
                             `warn_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[预警编号] 预警唯一编号',
                             `facility_id` bigint(0) NULL DEFAULT NULL COMMENT '[设施ID] 关联设施表ID',
                             `facility_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[设施名称] 设施名称',
                             `facility_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[设施唯一code] 设施唯一编码',
                             `work_order_id` bigint(0) NULL DEFAULT NULL COMMENT '[工单ID] 关联工单ID',
                             `work_order_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[工单唯一code] 工单唯一编码',
                             `device_id` bigint(0) NULL DEFAULT NULL COMMENT '[监测设备ID] 关联监测设备ID',
                             `device_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[监测设备唯一code] 监测设备唯一编码',
                             `monitor_id` bigint(0) NULL DEFAULT NULL COMMENT '[监测实时数据ID] 关联监测实时数据ID',
                             `monitor_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[监测实时数据唯一code] 监测实时数据唯一编码',
                             `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[预警状态] 如：待处置/有效待派单/已派单/超时/无效已归档/已归档',
                             `assign_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[派单状态] 如：未派单/已派单',
                             `facility_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[所属设施类型] 如：道路',
                             `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[预警类型] 如：坑洼数量超标/裂缝长度超标/路面温度超标/交通流量超标',
                             `way_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[预警方式] 如：自动监测/人工上报',
                             `level` int(0) NULL DEFAULT NULL COMMENT '[预警等级] 如：1-一般/2-较重/3-严重/4-紧急',
                             `trigger_time` datetime(0) NULL DEFAULT NULL COMMENT '[触发时间] 预警触发时间',
                             `deal_limit` decimal(10, 2) NULL DEFAULT NULL COMMENT '[预警处置时限] 单位：小时，可小数',
                             `over_index` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[超标指标名称] 如坑洼数量、裂缝长度等',
                             `over_value` decimal(10, 2) NULL DEFAULT NULL COMMENT '[超标数值] 实际超标的数值',
                             `threshold_value` decimal(10, 2) NULL DEFAULT NULL COMMENT '[超标阈值数值] 阈值',
                             `confirm_opinion` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[确认意见] 人工确认后的描述',
                             `invalid_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[无效原因] 如设备故障/数据波动/人为误触等',
                             `suggest` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[处理建议] 系统或人工给出的处置建议',
                             `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[创建人] 数据创建人ID',
                             `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[更新人] 数据更新人ID',
                             `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '[创建时间] 记录创建时间',
                             `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '[更新时间] 记录最后更新时间',
                             `deleted` bit(1) NULL DEFAULT b'0' COMMENT '[删除标识] 如:0-未删除/1-已删除',
                             `tenant_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '[租户ID] 租户唯一标识',
                             `ext_common1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段1]',
                             `ext_common2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段2]',
                             `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段3]',
                             `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段4]',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '预警表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_warn
-- ----------------------------
INSERT INTO `sys_warn` VALUES (1, 'WARN20250302001', 1, 'A区道路', 'FAC001', 101, 'WO001', 1001, 'DEV001', 2001, 'MON001', '无效已归档', '未派单', '道路', '坑洼数量超标', '自动监测', 2, '2026-02-24 11:54:37', 2.50, '坑洼数量', 15.50, 10.00, NULL, '设备故障', '需现场核实', '1', '1', '2026-03-02 16:40:37', '2026-03-12 09:18:06', b'1', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (2, 'WARN20250302002', 2, 'B区道路', 'FAC002', 102, 'WO002', 1002, 'DEV002', 2002, 'MON002', '无效已归档', '未派单', '道路', '裂缝长度超标', '自动监测', 3, '2026-02-26 04:38:37', 4.00, '裂缝长度', 25.00, 20.00, '确认有效', NULL, '需派单处理', '1', '1', '2026-03-02 16:40:37', '2026-03-05 16:05:16', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (3, 'WARN20250302003', 1, 'A区道路', 'FAC001', 9, 'WOR0309752512', 1003, 'DEV003', 2003, 'MON003', '已派单', '已派单', '道路', '路面温度超标', '自动监测', 4, '2026-02-26 18:51:37', 1.00, '路面温度', 65.00, 60.00, '已派工单', NULL, '紧急处理', '1', '1', '2026-03-02 16:40:37', '2026-03-12 09:13:57', b'1', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (4, 'WARN20250302004', 3, 'C区道路', 'FAC003', 103, 'WO003', 1004, 'DEV004', 2004, 'MON004', '无效已归档', '未派单', '道路', '交通流量超标', '自动监测', 1, '2026-02-24 15:38:37', 0.50, '交通流量', 1200.00, 1000.00, NULL, '设备故障', '重新评估', '1', '1', '2026-03-02 16:40:37', '2026-03-12 09:14:14', b'1', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (5, 'WARN20250302005', 2, 'B区道路', 'FAC002', NULL, NULL, 1005, 'DEV005', 2005, 'MON005', '无效已归档', '未派单', '道路', '坑洼数量超标', '人工上报', 2, '2026-02-26 04:58:37', 3.00, '坑洼数量', 8.00, 10.00, NULL, '数据波动', '忽略', '1', '1', '2026-03-02 16:40:37', '2026-03-02 16:40:37', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (6, 'WARN20250302006', 1, 'A区道路', 'FAC001', 104, 'WO004', 1006, 'DEV006', 2006, 'MON006', '有效待派单', '未派单', '道路', '裂缝长度超标', '自动监测', 3, '2026-02-26 09:14:37', 2.00, '裂缝长度', 30.00, 25.00, 'ttt', NULL, '需确认', '1', '1', '2026-03-02 16:40:37', '2026-03-12 09:18:06', b'1', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (7, 'WARN20250302007', 3, 'C区道路', 'FAC003', 105, 'WO005', 1007, 'DEV007', 2007, 'MON007', '有效待派单', '未派单', '道路', '路面温度超标', '人工上报', 2, '2026-03-01 14:38:37', 1.50, '路面温度', 55.00, 50.00, '人工确认有效', NULL, '请派单', '1', '1', '2026-03-02 16:40:37', '2026-03-02 16:40:37', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (8, 'WARN20250302008', 2, 'B区道路', 'FAC002', NULL, NULL, 1008, 'DEV008', 2008, 'MON008', '无效已归档', '已派单', '道路', '交通流量超标', '自动监测', 4, '2026-03-04 08:45:37', 2.00, '交通流量', 1500.00, 1200.00, '已派单处理中', NULL, '疏导交通', '1', '1', '2026-03-02 16:40:37', '2026-03-05 16:05:16', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (10, 'WARN0304955684', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25719, 'b378829903994131b7195049bcf98e39', '待处置', '未派单', '道路', '坑洼数量超标', '自动监测', 1, '2026-03-04 14:41:40', 10.00, '坑洼数量', 1111.00, 20.00, NULL, NULL, NULL, '1', '1', '2026-03-04 14:41:40', '2026-03-04 16:07:11', b'1', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (11, 'WARN0304056745', 1, '五四路', 'FA00245', NULL, NULL, 1, 'DEV0752', 1, 'MO25568', '无效已归档', '未派单', '道路', '坑洼数量超标', '自动监测', 1, '2026-03-04 16:40:38', 10.00, '坑洼数量', 10.00, 15.00, NULL, NULL, NULL, '1', '1', '2026-03-04 16:40:38', '2026-03-12 09:17:16', b'1', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (12, 'WARN0304081227', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 5, 'e5f678901234567890abcdefghi155', '无效已归档', '未派单', '道路', '坑洼数量超标', '自动监测', 1, '2026-03-04 17:52:51', 10.00, '坑洼数量', 8.00, 6.00, NULL, NULL, NULL, '1', '1', '2026-03-04 17:52:51', '2026-03-05 16:41:57', b'1', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (13, 'WARN0305000670', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25720, '9f68075b5af84a8198dc264bc58cece9', '无效已归档', '未派单', '道路', '坑洼数量超标', '自动监测', 1, '2026-03-05 15:46:40', 1.00, '坑洼数量', 99.00, 20.00, NULL, NULL, NULL, '1', '1', '2026-03-05 15:46:40', '2026-03-12 09:18:01', b'1', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (14, 'WARN0305132164', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25720, '9f68075b5af84a8198dc264bc58cece9', '无效已归档', '未派单', '道路', '裂缝长度超标', '自动监测', 1, '2026-03-05 15:46:40', 1.00, '裂缝长度', 100.00, 20.00, NULL, NULL, NULL, '1', '1', '2026-03-05 15:46:40', '2026-03-12 09:18:44', b'1', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (15, 'WARN0305749389', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25720, '9f68075b5af84a8198dc264bc58cece9', '无效已归档', '未派单', '道路', '路面温度超标', '自动监测', 1, '2026-03-05 15:46:40', 1.00, '路面温度', 70.00, 20.00, NULL, NULL, NULL, '1', '1', '2026-03-05 15:46:40', '2026-03-12 09:18:44', b'1', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (16, 'WARN0305589005', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25721, '45e43d2b2f9d45fda6410f65da37bfb5', '无效已归档', '未派单', '道路', '坑洼数量超标', '自动监测', 1, '2026-03-05 16:05:23', 1.00, '坑洼数量', 1000.00, 20.00, NULL, NULL, NULL, '1', '1', '2026-03-05 16:05:23', '2026-03-05 16:05:38', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (17, 'WARN0305831811', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25721, '45e43d2b2f9d45fda6410f65da37bfb5', '无效已归档', '未派单', '道路', '裂缝长度超标', '自动监测', 1, '2026-03-05 16:05:23', 1.00, '裂缝长度', 1000.00, 20.00, NULL, NULL, NULL, '1', '1', '2026-03-05 16:05:23', '2026-03-05 16:05:38', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (18, 'WARN0305332920', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25721, '45e43d2b2f9d45fda6410f65da37bfb5', '无效已归档', '未派单', '道路', '路面温度超标', '自动监测', 1, '2026-03-05 16:05:23', 1.00, '路面温度', 80.00, 20.00, NULL, NULL, NULL, '1', '1', '2026-03-05 16:05:23', '2026-03-12 09:17:14', b'1', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (19, 'WARN0305338289', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25721, '45e43d2b2f9d45fda6410f65da37bfb5', '无效已归档', '未派单', '道路', '交通流量超标', '自动监测', 1, '2026-03-05 16:05:23', 1.00, '交通流量', 1000.00, 210.00, NULL, NULL, NULL, '1', '1', '2026-03-05 16:05:23', '2026-03-12 09:17:15', b'1', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (20, 'WARN0305498141', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25719, 'b378829903994131b7195049bcf98e39', '无效已归档', '未派单', '道路', '坑洼数量超标', '人工上报', 1, '2026-03-05 16:33:52', 1.00, '坑洼数量', 1111.00, 20.00, NULL, NULL, NULL, '1', '1', '2026-03-05 16:33:52', '2026-03-06 10:20:33', b'1', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (21, 'WARN0305607464', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25719, 'b378829903994131b7195049bcf98e39', '无效已归档', '未派单', '道路', '裂缝长度超标', '人工上报', 1, '2026-03-05 16:33:52', 1.00, '裂缝长度', 1111.00, 20.00, NULL, '数据异常', NULL, '1', '1', '2026-03-05 16:33:52', '2026-03-05 16:41:54', b'1', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (22, 'WARN0306085865', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25723, 'b03795fd2714413a8aa1794944eb0a1f', '无效已归档', '未派单', '道路', '坑洼数量超标', '自动监测', 1, '2026-03-06 10:03:12', 1.00, '坑洼数量', 100.00, 10.00, NULL, NULL, NULL, '1', '1', '2026-03-06 10:03:12', '2026-03-06 10:20:34', b'1', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (23, 'WARN0306569929', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25723, 'b03795fd2714413a8aa1794944eb0a1f', '无效已归档', '未派单', '道路', '裂缝长度超标', '自动监测', 1, '2026-03-06 10:03:12', 1.00, '裂缝长度', 100.00, 10.00, NULL, '误报', NULL, '1', '1', '2026-03-06 10:03:12', '2026-03-06 10:20:35', b'1', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (24, 'WARN0306241415', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25723, 'b03795fd2714413a8aa1794944eb0a1f', '无效已归档', '未派单', '道路', '路面温度超标', '自动监测', 1, '2026-03-06 10:03:12', 1.00, '路面温度', 70.00, 50.00, '指标超出阈值', NULL, NULL, '1', '1', '2026-03-06 10:03:12', '2026-03-06 10:20:30', b'1', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (25, 'WARN0306397414', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25724, '91793cd22c1740fabe68b90823a89320', '无效已归档', '未派单', '道路', '坑洼数量超标', '自动监测', 1, '2026-03-06 10:20:01', 1.00, '坑洼数量', 20.00, 10.00, NULL, NULL, NULL, '1', '1', '2026-03-06 10:20:01', '2026-03-06 10:21:03', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (26, 'WARN0306309795', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25724, '91793cd22c1740fabe68b90823a89320', '无效已归档', '未派单', '道路', '裂缝长度超标', '自动监测', 1, '2026-03-06 10:20:01', 1.00, '裂缝长度', 12.00, 10.00, NULL, NULL, NULL, '1', '1', '2026-03-06 10:20:01', '2026-03-06 10:21:03', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (27, 'WARN0306602859', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25724, '91793cd22c1740fabe68b90823a89320', '无效已归档', '未派单', '道路', '路面温度超标', '自动监测', 1, '2026-03-06 10:20:01', 1.00, '路面温度', 74.00, 50.00, NULL, NULL, NULL, '1', '1', '2026-03-06 10:20:01', '2026-03-06 10:21:03', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (28, 'WARN0306057205', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25724, '91793cd22c1740fabe68b90823a89320', '有效待派单', '未派单', '道路', '交通流量超标', '自动监测', 1, '2026-03-06 10:20:01', 1.00, '交通流量', 4121.00, 1000.00, '指标超标', NULL, NULL, '1', '1', '2026-03-06 10:20:01', '2026-03-06 10:20:48', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (31, 'WARN0309495619', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', 11, 'WOR0310247140', 1, 'a1b2c3d4e5f678901234567890def001', 25724, '91793cd22c1740fabe68b90823a89320', '已派单', '已派单', '道路', '坑洼数量超标', '人工上报', 1, '2026-03-09 11:08:48', 1.00, '坑洼数量', 20.00, 10.00, NULL, NULL, NULL, '1', '1', '2026-03-09 11:08:48', '2026-03-09 11:08:48', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (32, 'WARN0309290222', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', 10, 'WOR0309742182', 1, 'a1b2c3d4e5f678901234567890def001', 25724, '91793cd22c1740fabe68b90823a89320', '已派单', '已派单', '道路', '裂缝长度超标', '人工上报', 1, '2026-03-09 11:08:48', 1.00, '裂缝长度', 12.00, 10.00, NULL, NULL, NULL, '1', '1', '2026-03-09 11:08:48', '2026-03-09 11:08:48', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (33, 'WARN0309850397', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25724, '91793cd22c1740fabe68b90823a89320', '无效已归档', '未派单', '道路', '路面温度超标', '人工上报', 1, '2026-03-09 11:08:48', 1.00, '路面温度', 74.00, 50.00, NULL, NULL, NULL, '1', '1', '2026-03-09 11:08:48', '2026-03-10 08:41:48', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (34, 'WARN0309333493', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25724, '91793cd22c1740fabe68b90823a89320', '有效待派单', '未派单', '道路', '交通流量超标', '人工上报', 1, '2026-03-09 11:08:48', 1.00, '交通流量', 4121.00, 1000.00, '111', NULL, NULL, '1', '1', '2026-03-09 11:08:48', '2026-03-09 11:09:26', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (35, 'WARN0312687089', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25727, '9a267050a68048d0b10540152cdca7fb', '待处置', '未派单', '道路', '坑洼数量超标', '自动监测', 1, '2026-03-12 09:40:14', 10.00, '坑洼数量', 27.00, 10.00, NULL, NULL, NULL, '1', '1', '2026-03-12 09:40:14', '2026-03-12 09:40:14', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (36, 'WARN0312628435', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25727, '9a267050a68048d0b10540152cdca7fb', '待处置', '未派单', '道路', '裂缝长度超标', '自动监测', 1, '2026-03-12 09:40:14', 10.00, '裂缝长度', 78.00, 10.00, NULL, NULL, NULL, '1', '1', '2026-03-12 09:40:14', '2026-03-12 09:40:14', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (37, 'WARN0312963694', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25727, '9a267050a68048d0b10540152cdca7fb', '待处置', '未派单', '道路', '路面温度超标', '自动监测', 1, '2026-03-12 09:40:14', 10.00, '路面温度', 68.00, 50.00, NULL, NULL, NULL, '1', '1', '2026-03-12 09:40:14', '2026-03-12 09:40:14', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (38, 'WARN0312539629', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', 12, 'WOR0312385941', 1, 'a1b2c3d4e5f678901234567890def001', 25727, '9a267050a68048d0b10540152cdca7fb', '已派单', '已派单', '道路', '交通流量超标', '自动监测', 1, '2026-03-12 09:40:14', 10.00, '交通流量', 4521.00, 1000.00, NULL, NULL, NULL, '1', '1', '2026-03-12 09:40:14', '2026-03-12 09:40:14', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (39, 'WARN0312366316', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25726, 'a3d891d6db29427eb5fb975a75be866e', '待处置', '未派单', '道路', '坑洼数量超标', '自动监测', 1, '2026-03-12 09:43:13', 1.00, '坑洼数量', 45.00, 10.00, NULL, NULL, NULL, '1', '1', '2026-03-12 09:43:13', '2026-03-12 09:43:13', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (40, 'WARN0312873167', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25726, 'a3d891d6db29427eb5fb975a75be866e', '待处置', '未派单', '道路', '裂缝长度超标', '自动监测', 1, '2026-03-12 09:43:13', 1.00, '裂缝长度', 78.00, 10.00, NULL, NULL, NULL, '1', '1', '2026-03-12 09:43:13', '2026-03-12 09:43:13', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (41, 'WARN0312471165', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25726, 'a3d891d6db29427eb5fb975a75be866e', '有效待派单', '未派单', '道路', '路面温度超标', '自动监测', 1, '2026-03-12 09:43:13', 1.00, '路面温度', 78.00, 50.00, '444', NULL, NULL, '1', '1', '2026-03-12 09:43:13', '2026-03-12 09:43:32', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_warn` VALUES (42, 'WARN0312584311', 1, '五四路', 'a1b2c3d4e5f678901234567890abcdef', NULL, NULL, 1, 'a1b2c3d4e5f678901234567890def001', 25726, 'a3d891d6db29427eb5fb975a75be866e', '待处置', '未派单', '道路', '交通流量超标', '自动监测', 1, '2026-03-12 09:43:13', 1.00, '交通流量', 4574.00, 1000.00, NULL, NULL, NULL, '1', '1', '2026-03-12 09:43:13', '2026-03-12 09:43:13', b'0', 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for work_order
-- ----------------------------
DROP TABLE IF EXISTS `work_order`;
CREATE TABLE `work_order`  (
                               `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '[工单ID] 主键，工单唯一标识',
                               `facility_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[所属设施类型] 如：道路',
                               `order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[工单编号] 工单唯一编号',
                               `warn_id` bigint(0) NULL DEFAULT NULL COMMENT '[关联预警ID] 关联的预警记录ID',
                               `warn_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[关联预警编号] 关联的预警编号',
                               `facility_id` bigint(0) NOT NULL COMMENT '[关联设施ID] 关联的设施ID',
                               `facility_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[关联设施名称] 关联的设施名称（冗余）',
                               `assign_staff_id` bigint(0) NULL DEFAULT NULL COMMENT '[指派运维员ID] 指派处理该工单的运维人员ID',
                               `assign_staff_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[指派运维员名称] 指派运维员姓名（冗余）',
                               `order_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[工单类型] 运维/养护/维修/清淤/巡检/处置',
                               `biz_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[业务类型] 业务类型，具体取值依赖于工单类型：当order_type为dredge_order时取机械清淤/人工清淤/高压冲洗；当order_type为disposal_order时取倾斜/振动/开合异常；其他情况取值与工单类型相同或业务定义',
                               `deal_limit` decimal(10, 2) NULL DEFAULT NULL COMMENT '[处置时限] 处置时限，单位：小时',
                               `arrive_time` datetime(0) NULL DEFAULT NULL COMMENT '[抵达现场时间] 运维人员抵达现场的时间',
                               `remind_time` datetime(0) NULL DEFAULT NULL COMMENT '[提醒时间] 点击“确认”后向所选工单的运维员发送待办提醒，记录提醒时间',
                               `complete_time` datetime(0) NULL DEFAULT NULL COMMENT '[工单完成时间] 工单完成的日期',
                               `area_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[所属区域名称] 工单所属区域名称',
                               `area_full_code` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[所属区域12位全码] 所属区域12位全码（GB/T 2260），到社区级',
                               `priority_level` int(0) NULL DEFAULT NULL COMMENT '[工单优先等级] 工单优先等级，如：3-高/2-中/1-低',
                               `risk_level` int(0) NULL DEFAULT NULL COMMENT '[安全风险等级] 安全风险等级，如：3-高/2-中/1-低',
                               `process_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[当前处置进度] 当前处置进度：待处置/处置中/处置完成',
                               `process_desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[录入进度说明] 录入的进度说明文本',
                               `deal_content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[处理情况说明] 处理情况说明，如处置措施、故障排查结果、巡检内容等',
                               `supervise_opinion` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[督办意见] 督办意见',
                               `site_data_url_list_str` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[现场检测数据url列表字符串] 现场检测数据URL列表字符串varchar（JSON格式）',
                               `file_desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[录入资料说明] 录入资料说明',
                               `after_index_value` decimal(10, 2) NULL DEFAULT NULL COMMENT '[处理后的指标数值]',
                               `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[创建人] 数据创建人ID',
                               `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[更新人] 数据更新人ID',
                               `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '[创建时间] 记录创建时间',
                               `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '[更新时间] 记录最后更新时间',
                               `deleted` bit(1) NULL DEFAULT b'0' COMMENT '[删除标识] 如:0-未删除/1-已删除',
                               `tenant_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '[租户ID] 租户唯一标识',
                               `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段1] 通用扩展字段1',
                               `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段2] 通用扩展字段2',
                               `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段3] 通用扩展字段3',
                               `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段4] 通用扩展字段4',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '工单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of work_order
-- ----------------------------
INSERT INTO `work_order` VALUES (1, '道路', 'WO202603060001', 1, 'WARN202603060001', 1001, 'A区道路', 3, '张新', '运维', '坑洼数量超标', 4.00, '2026-03-06 01:19:03', '2026-03-09 16:38:10', '2026-03-10 00:00:00', '福建省福州市', '350100000000', 3, 2, '处理完成', '现场修补', '已安排人员前往现场', '请尽快完成工单', '[{\"name\":\"Snipaste_2026-03-09_14-23-50.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/7dfda1f0-49eb-4ba6-bfb9-30560bdc4a21.png\"},{\"name\":\"测试.xlsx\",\"type\":\"excel\",\"url\":\"http://192.168.8.68:9000/shunchang/avatar/3f93205f-70e7-4cd3-8bf5-48b9128ab860.xlsx\"},{\"name\":\"Snipaste_2026-03-09_14-22-05.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/2eede5e1-6b48-41bc-8f03-b4b145a68681.png\"}]', '现场检测图片; 现场检测Excel文件; 现场检测图片', 9.00, '1', '1', '2026-03-06 16:26:59', '2026-03-12 09:20:39', b'1', 1, NULL, NULL, NULL, NULL);
INSERT INTO `work_order` VALUES (2, '道路', 'WO202603060002', 102, 'WARN202603060002', 1002, 'B区道路', 2002, '李四', '运维', '坑洼数量超标', 6.00, '2026-03-06 10:20:00', '2026-03-10 09:41:45', NULL, '福建省厦门市', '350200000000', 2, 2, '处置中', '正在进行管道疏通', '发现管道堵塞', NULL, NULL, NULL, NULL, '1', '1', '2026-03-06 16:26:59', '2026-03-12 09:20:34', b'1', 1, NULL, NULL, NULL, NULL);
INSERT INTO `work_order` VALUES (3, '道路', 'WO202603060003', NULL, 'WARN202603060003', 1003, 'A区道路', 2003, '王五', '运维', '坑洼数量超标', 8.00, NULL, '2026-03-10 09:41:45', NULL, '福建省泉州市', '350500000000', 2, 1, '待处置', '计划使用机械清淤', '等待安排设备', NULL, NULL, NULL, NULL, '1', '1', '2026-03-06 16:26:59', '2026-03-12 09:20:39', b'1', 1, NULL, NULL, NULL, NULL);
INSERT INTO `work_order` VALUES (4, '道路', 'WO202603060004', NULL, 'WARN202603060004', 1004, 'C区道路', 2004, '赵六', '运维', '裂缝长度超标', 2.00, NULL, '2026-03-12 09:46:18', NULL, '福建省漳州市', '350600000000', 1, 1, '待处置', '检查井结构及水位', '例行巡检任务', NULL, NULL, NULL, NULL, '1', '1', '2026-03-06 16:26:59', '2026-03-09 08:52:22', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `work_order` VALUES (5, '道路', 'WO202603060005', 103, 'WARN202603060005', 1005, 'B区道路', 2005, '孙七', '运维', '裂缝长度超标', 3.00, '2026-03-06 11:00:00', '2026-03-12 09:46:18', NULL, '福建省莆田市', '350300000000', 3, 3, '处置中', '现场排查电机故障', '闸门无法正常开启', NULL, NULL, NULL, NULL, '1', '1', '2026-03-06 16:26:59', '2026-03-09 08:52:30', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `work_order` VALUES (6, '道路', 'WO202603060006', NULL, 'WARN202603060006', 1006, 'A区道路', 2002, '李四', '运维', '裂缝长度超标', 5.00, '2026-03-06 08:40:00', '2026-03-12 09:46:18', '2026-03-06 00:00:00', '福建省三明市', '350400000000', 2, 1, '已完成', '清理管道内部沉积物', '完成日常养护', NULL, NULL, NULL, NULL, '1', '1', '2026-03-06 16:26:59', '2026-03-09 08:52:18', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `work_order` VALUES (7, '道路', 'WO202603060007', 104, 'WARN202603060007', 1007, 'C区道路', 2006, '周八', '运维', '裂缝长度超标', 4.00, '2026-03-06 13:10:00', '2026-03-12 09:46:18', NULL, '福建省南平市', '350700000000', 3, 2, '待核查', '更换井盖螺栓', '维修完成等待核查', NULL, NULL, NULL, NULL, '1', '1', '2026-03-06 16:26:59', '2026-03-09 08:52:34', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `work_order` VALUES (8, '道路', 'WO202603060008', NULL, 'WARN202603060008', 1008, 'B区道路', 2007, '吴九', '运维', '裂缝长度超标', 2.00, '2026-03-10 17:41:24', '2026-03-12 09:46:18', NULL, '福建省龙岩市', '350800000000', 1, 1, '处置中', '到达现场', '安排巡检任务', NULL, NULL, NULL, NULL, '1', '1', '2026-03-06 16:26:59', '2026-03-10 17:41:24', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `work_order` VALUES (9, '道路', 'WOR0309752512', 3, 'WARN20250302003', 1, 'A区道路', 2, '李四', '运维', '坑洼修补', 10.00, '2026-03-10 17:40:07', '2026-03-12 09:46:18', '2026-03-10 00:00:00', NULL, NULL, 2, 2, '处理完成', '处理完成了', NULL, NULL, '[{\"name\":\"icon.ico\",\"type\":\"file\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/c3a6a7ca-c617-4d4f-a04a-0982806a6c73.ico\"},{\"name\":\"icon.ico\",\"type\":\"file\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/57528b73-9065-4542-8d66-012bfbc067ec.ico\"}]', '44; 4344', 0.00, '1', '1', '2026-03-09 17:35:40', '2026-03-11 10:19:46', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `work_order` VALUES (10, '道路', 'WOR0309742182', 32, 'WARN0309290222', 1, '五四路', 11, '刘洋', '养护', '养护', 1.00, '2026-03-10 17:36:13', '2026-03-12 09:46:18', NULL, NULL, NULL, 1, 1, '处置中', '到达现场', NULL, '请速速完成WOR0309742182', '[{\"name\":\"Snipaste_2026-03-09_13-58-43.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/9d210d07-4576-4cdf-9d53-d6bca0aa3cdb.png\"},{\"name\":\"测试.xlsx\",\"type\":\"excel\",\"url\":\"http://192.168.8.68:9000/shunchang/avatar/99292575-0af0-4058-95a4-e199ec45c080.xlsx\"}]', '现场图片; 文档文件', 9.00, '1', '1', '2026-03-09 17:55:22', '2026-03-10 17:39:22', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `work_order` VALUES (11, '道路', 'WOR0310247140', 31, 'WARN0309495619', 1, '五四路', 10, '王强', '巡检', '巡检', 1.00, '2026-03-12 08:57:58', '2026-03-12 09:46:18', '2026-03-12 08:58:05', NULL, NULL, 1, 1, '处理完成', '完成', NULL, '测试测测是', '[{\"name\":\"logo.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/0dc717e4-7169-41af-9b76-fe1207dc80e2.png\"},{\"name\":\"03-市政设施监测系统-功能模块-UIUX交互设计-2.docx\",\"type\":\"word\",\"url\":\"http://192.168.8.68:9000/shunchang/avatar/eb1eba76-6024-4f6e-b6fa-81298f2c6a0f.docx\"}]', 'tt; tt', 1.00, '1', '1', '2026-03-10 08:41:05', '2026-03-12 08:58:05', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `work_order` VALUES (12, '道路', 'WOR0312385941', 38, 'WARN0312539629', 1, '五四路', 12, '陈晨', '运维', '运维', 1.00, '2026-03-12 09:43:57', '2026-03-12 09:46:18', NULL, NULL, NULL, 1, 1, '处置中', '5555', NULL, NULL, '[{\"name\":\"Penguins.jpg\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/8666e2c5-c57f-4529-b47b-ed166966e255.jpg\"},{\"name\":\"Lighthouse.jpg\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/e5ba9d33-1a57-4d30-9c83-d8681aba3f9e.jpg\"},{\"name\":\"新建 DOCX 文档.docx\",\"type\":\"word\",\"url\":\"http://192.168.8.68:9000/shunchang/avatar/b1fb4ccd-35a9-4dff-bd59-776017befbd8.docx\"}]', '4444; 6666; 000', 1.00, '1', '1', '2026-03-12 09:42:03', '2026-03-12 09:44:48', b'0', 1, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
