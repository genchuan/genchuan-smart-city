/*

 Source Schema         : genchuan-module-kitchen

*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ai_alert_message
-- ----------------------------
DROP TABLE IF EXISTS `ai_alert_message`;
CREATE TABLE `ai_alert_message`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '[主键ID] 主键，告警记录唯一标识',
  `user_ids` json NULL COMMENT '[设备关联用户ID列表] JSON格式存储的用户ID列表',
  `scene_id` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[场景实例ID] 场景实例ID',
  `ai_ability_code` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[功能算法编码] 功能算法编码',
  `alert_type` int(0) NULL DEFAULT NULL COMMENT '[告警类型] 告警类型',
  `alert_create_time` datetime(0) NULL DEFAULT NULL COMMENT '[消息产生时间] 消息产生时间(特别注意）',
  `device_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[设备编码] 设备编码',
  `feature_id` int(0) NULL DEFAULT 1 COMMENT '[功能标识] 默认为1',
  `alert_source` int(0) NULL DEFAULT NULL COMMENT '[消息来源] 如：1-端侧/2-云化/3-云侧/6-盒子',
  `src_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[图片地址] 通用图片地址或视频地址（人脸布控和车牌布控时为空），时光缩影下为视频下载地址',
  `src_token` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[图片刷新token] 	通用图片刷新token（人脸布控和车牌布控时为空）时光缩影下为视频刷新token',
  `device_account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[设备手机号] 设备手机号(盒子类消息为空)',
  `msg_version` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[消息版本] 消息版本',
  `alert_id` bigint(0) NULL DEFAULT NULL COMMENT '[平台告警ID] 能力开放平台告警ID\r\n（可能重复）',
  `ai_platform_msg_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[AI平台消息ID] AI平台唯一消息ID',
  `bbox` json NULL COMMENT '[检测框] 所有云侧都支持画框。这个字段默认存在，端侧（云化和端侧）不支持画框的能力为：口罩识别、电动车识别、火情告警、区域入侵、设备巡检、人脸布控、车牌识别、静态客流统计、动态客流统计、客流统计（云眼专用）、车辆占道违停',
  `repeat_alarm` int(0) NULL DEFAULT NULL COMMENT '[重复告警] 1:重复告警 0:非重复告警',
  `leave_time` int(0) NULL DEFAULT NULL COMMENT '[离岗时间] 作用：设置的离岗时间，超过这个时间触发离岗告警，单位（秒）',
  `time_slot_end` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[结束时间] 一天内的结束时间，格式为hh:mm，比如15:00',
  `interval_time` int(0) NULL DEFAULT NULL COMMENT '[间隔时间]',
  `alert_params` json NULL COMMENT '[完整告警参数JSON] 原始alertParams数据',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[创建人ID] 数据创建人ID',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[更新人ID] 数据更新人ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '[创建时间] 记录创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '[更新时间] 记录最后更新时间',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '[删除标识] 如：0-未删除/1-已删除',
  `tenant_id` bigint(0) NULL DEFAULT 0 COMMENT '[租户ID] 租户唯一标识',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段1] 通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段2] 通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段3] 通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段4] 通用扩展字段4',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'AI告警消息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ai_alert_message
-- ----------------------------
INSERT INTO `ai_alert_message` VALUES (1, '[101, 102]', 'SCENE_10001', '101400', 5, '2026-03-13 10:01:11', 'CAM_001', 1, 1, 'http://img.ai/1.jpg', 'token_001', '13800138001', '1.0', 10001, 'MSG_10001', '{\"conf\": 0.92, \"class\": \"person\", \"point\": [[100, 120, 220, 340]], \"monitorAreaId\": \"A1\"}', 0, NULL, NULL, 30, '{\"alarmDetail\": {\"type\": \"intrusion\", \"areaId\": \"A1\"}}', 'system', 'system', '2026-03-13 15:41:16', '2026-03-13 15:48:06', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `ai_alert_message` VALUES (2, '[101]', 'SCENE_10002', '101100', 6, '2026-03-13 10:02:10', 'CAM_002', 1, 1, 'http://img.ai/2.jpg', 'token_002', '13800138002', '1.0', 10002, 'MSG_10002', '{\"conf\": 0.95, \"class\": \"car\", \"point\": [[80, 90, 260, 300]], \"monitorAreaId\": \"B1\"}', 0, NULL, NULL, 30, '{\"conf\": \"0.96\", \"bgImageUrl\": \"http://img.ai/bg1.jpg\", \"bgImageToken\": \"bg1\", \"catchPatImageUrl\": \"http://img.ai/car1.jpg\", \"licensePlateType\": \"蓝牌\", \"catchPatImageToken\": \"ct1\", \"licensePlateNumber\": \"京A12345\"}', 'system', 'system', '2026-03-13 15:41:16', '2026-03-13 15:48:06', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `ai_alert_message` VALUES (3, '[103]', 'SCENE_10003', '100300', 7, '2026-03-13 10:03:12', 'CAM_003', 1, 1, 'http://img.ai/3.jpg', 'token_003', '13800138003', '1.0', 10003, 'MSG_10003', '{\"conf\": 0.98, \"class\": \"face\", \"point\": [[60, 80, 150, 210]], \"monitorAreaId\": \"C1\"}', 0, NULL, NULL, 30, '{\"configId\": \"FACE_001\", \"bgImageUrl\": \"http://img.ai/bg2.jpg\", \"similarity\": \"0.91\", \"bgImageToken\": \"bg2\", \"faceAlertType\": \"1\", \"catchPatImageUrl\": \"http://img.ai/face1.jpg\", \"catchPatImageToken\": \"f1\"}', 'system', 'system', '2026-03-13 15:41:16', '2026-03-13 15:48:06', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `ai_alert_message` VALUES (4, '[104]', 'SCENE_10004', '100400', 12, '2026-03-13 10:04:15', 'CAM_004', 1, 2, 'http://img.ai/4.jpg', 'token_004', '13800138004', '1.0', 10004, 'MSG_10004', '{\"conf\": 0.9, \"class\": \"person\", \"point\": [[50, 100, 180, 320]], \"monitorAreaId\": \"D1\"}', 0, NULL, NULL, 60, '{\"personNum\": 12}', 'system', 'system', '2026-03-13 15:41:16', '2026-03-13 15:48:06', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `ai_alert_message` VALUES (5, '[105]', 'SCENE_10005', '100500', 14, '2026-03-13 10:05:20', 'CAM_005', 1, 1, 'http://img.ai/5.jpg', 'token_005', '13800138005', '1.0', 10005, 'MSG_10005', '{\"conf\": 0.88, \"class\": \"smoke\", \"point\": [[110, 120, 210, 260]], \"monitorAreaId\": \"E1\"}', 0, NULL, NULL, 30, '{\"alarmDetail\": {\"type\": \"smoking\"}}', 'system', 'system', '2026-03-13 15:41:16', '2026-03-13 15:48:06', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `ai_alert_message` VALUES (6, '[106]', 'SCENE_10006', '100200', 15, '2026-03-13 10:06:22', 'CAM_006', 1, 1, 'http://img.ai/6.jpg', 'token_006', '13800138006', '1.0', 10006, 'MSG_10006', '{\"conf\": 0.87, \"class\": \"no_mask\", \"point\": [[90, 110, 200, 280]], \"monitorAreaId\": \"F1\"}', 0, NULL, NULL, 30, '{\"alarmDetail\": {\"type\": \"no_mask\"}}', 'system', 'system', '2026-03-13 15:41:16', '2026-03-13 15:48:06', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `ai_alert_message` VALUES (7, '[107]', 'SCENE_10007', '100900', 16, '2026-03-13 10:07:33', 'CAM_007', 1, 1, 'http://img.ai/7.jpg', 'token_007', '13800138007', '1.0', 10007, 'MSG_10007', '{\"conf\": 0.85, \"class\": \"phone\", \"point\": [[100, 120, 220, 300]], \"monitorAreaId\": \"G1\"}', 0, NULL, NULL, 30, '{\"alarmDetail\": {\"type\": \"phone_usage\"}}', 'system', 'system', '2026-03-13 15:41:16', '2026-03-13 15:48:06', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `ai_alert_message` VALUES (8, '[108]', 'SCENE_10008', '101500', 25, '2026-03-13 10:08:41', 'CAM_008', 1, 1, 'http://img.ai/8.jpg', 'token_008', '13800138008', '1.0', 10008, 'MSG_10008', '{\"conf\": 0.93, \"class\": \"crowd\", \"point\": [[40, 70, 300, 350]], \"monitorAreaId\": \"H1\"}', 0, NULL, NULL, 30, '{\"alarmDetail\": {\"type\": \"crowd\"}}', 'system', 'system', '2026-03-13 15:41:16', '2026-03-13 15:48:06', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `ai_alert_message` VALUES (9, '[109]', 'SCENE_10009', '100700', 28, '2026-03-13 10:09:55', 'CAM_009', 1, 1, 'http://img.ai/9.jpg', 'token_009', '13800138009', '1.0', 10009, 'MSG_10009', '{\"conf\": 0.91, \"class\": \"car\", \"point\": [[60, 90, 240, 310]], \"monitorAreaId\": \"I1\"}', 0, NULL, NULL, 60, '{\"alarmDetail\": {\"type\": \"illegal_parking\"}}', 'system', 'system', '2026-03-13 15:41:16', '2026-03-13 15:48:06', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `ai_alert_message` VALUES (10, '[110]', 'SCENE_10010', '100000', 4, '2026-03-13 10:10:15', 'CAM_010', 1, 3, 'http://video.ai/1.mp4', 'token_v1', '13800138010', '1.0', 10010, 'MSG_10010', NULL, 0, NULL, NULL, 120, '{\"endTime\": \"1000\", \"dateTime\": \"20260313\", \"fileName\": \"timelapse1.mp4\", \"objectId\": \"o1\", \"startTime\": \"0800\", \"containerId\": \"c1\", \"picSrcToken\": \"p1\", \"videoDuration\": \"2\"}', 'system', 'system', '2026-03-13 15:41:16', '2026-03-13 15:48:06', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `ai_alert_message` VALUES (11, '[111]', 'SCENE_10011', '101600', 27, '2026-03-13 10:11:17', 'CAM_011', 1, 1, 'http://img.ai/11.jpg', 'token_011', '13800138011', '1.0', 10011, 'MSG_10011', '{\"conf\": 0.82, \"class\": \"object\", \"point\": [[120, 150, 260, 330]], \"monitorAreaId\": \"J1\"}', 0, NULL, NULL, 30, '{\"alarmDetail\": {\"type\": \"throw_object\"}}', 'system', 'system', '2026-03-13 15:41:16', '2026-03-13 15:48:06', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `ai_alert_message` VALUES (12, '[112]', 'SCENE_10012', '104002', 104002, '2026-03-13 10:12:30', 'CAM_012', 1, 1, 'http://img.ai/12.jpg', 'token_012', '13800138012', '1.0', 10012, 'MSG_10012', '{\"conf\": 0.89, \"class\": \"person_fall\", \"point\": [[90, 100, 200, 280]], \"monitorAreaId\": \"K1\"}', 0, NULL, NULL, 30, '{\"alarmDetail\": {\"type\": \"fall_detect\"}}', 'system', 'system', '2026-03-13 15:41:16', '2026-03-13 15:48:06', b'0', 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for cancel_reason_dict
-- ----------------------------
DROP TABLE IF EXISTS `cancel_reason_dict`;
CREATE TABLE `cancel_reason_dict`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '[主键ID] 撤销原因记录唯一标识，自增主键',
  `reason_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '[撤销原因编码] 撤销原因唯一编码',
  `reason_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '[撤销原因名称] 如：证据不足/违规事实认定错误/企业已整改完成/适用法规错误/其他',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '[排序序号] 排序序号，整型，默认0',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[创建人] 数据创建人ID',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[更新人] 数据更新人ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '[创建时间] 记录创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '[更新时间] 记录最后更新时间',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '[删除标识] 如：0-未删除/1-已删除',
  `tenant_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '[租户ID] 租户唯一标识',
  `ext_common1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[通用扩展字段1] 通用扩展字段1',
  `ext_common2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[通用扩展字段2] 通用扩展字段2',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[通用扩展字段3] 通用扩展字段3',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[通用扩展字段4] 通用扩展字段4',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '撤销原因字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cancel_reason_dict
-- ----------------------------
INSERT INTO `cancel_reason_dict` VALUES (1, 'REASON001', '证据不足', 1, '1', '1', '2026-03-11 15:29:45', '2026-03-13 22:00:45', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `cancel_reason_dict` VALUES (2, 'REASON002', '违规事实认定错误', 2, '1', '1', '2026-03-11 05:37:45', '2026-03-12 00:10:45', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `cancel_reason_dict` VALUES (3, 'REASON003', '企业已整改完成', 3, '1', '1', '2026-03-09 22:04:45', '2026-03-11 03:58:45', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `cancel_reason_dict` VALUES (4, 'REASON004', '适用法规错误', 4, '1', '1', '2026-03-09 15:42:45', '2026-03-12 08:40:45', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `cancel_reason_dict` VALUES (5, 'REASON005', '其他', 5, '1', '1', '2026-03-09 10:22:45', '2026-03-14 15:56:45', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `cancel_reason_dict` VALUES (6, 'REASON006', '证据不足', 6, '1', '1', '2026-03-14 14:38:45', '2026-03-12 15:27:45', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `cancel_reason_dict` VALUES (7, 'REASON007', '违规事实认定错误', 7, '1', '1', '2026-03-09 23:28:45', '2026-03-09 13:02:45', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `cancel_reason_dict` VALUES (8, 'REASON008', '企业已整改完成', 8, '1', '1', '2026-03-15 08:55:45', '2026-03-10 19:31:45', b'0', 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for ent_rectify_record
-- ----------------------------
DROP TABLE IF EXISTS `ent_rectify_record`;
CREATE TABLE `ent_rectify_record`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '[主键ID] 企业整改记录唯一标识',
  `rectify_notice_id` bigint(0) NOT NULL COMMENT '[整改通知书ID] 关联park_rectify_notice.id，唯一',
  `ent_id` bigint(0) NOT NULL COMMENT '[企业ID] 关联park_enterprise_info.id',
  `rectify_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[整改状态] 如：未整改/整改中/已完成/整改不合格',
  `rectify_complete_time` datetime(0) NULL DEFAULT NULL COMMENT '[整改完成时间] datetime格式，仅当状态为已完成或整改不合格时有值',
  `rectify_desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '[企业整改说明] 富文本内容，可为空',
  `rectify_evidence_url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[整改佐证证据链接] JSON格式varchar，如[\"url1\",\"url2\"]，可为空',
  `audit_result` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[整改审核结果] 如：合格/不合格，可为空',
  `audit_by` bigint(0) NULL DEFAULT NULL COMMENT '[整改审核人ID] 关联park_user.id，可为空',
  `reject_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[整改审核驳回原因] 文本，可为空',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '[整改审核时间] 可为空',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[创建人ID] 关联park_user.id',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[更新人ID] 关联park_user.id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '[创建时间] 记录创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '[更新时间] 记录最后更新时间',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '[删除标识] 如：0-未删除/1-已删除',
  `tenant_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '[租户ID] 租户唯一标识',
  `ext_common1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段1] 预留',
  `ext_common2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段2] 预留',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段3] 预留',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段4] 预留',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '企业整改记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ent_rectify_record
-- ----------------------------
INSERT INTO `ent_rectify_record` VALUES (1, 1, 1, '已完成', '2026-03-08 04:34:53', '已按要求完成整改，员工已佩戴工牌，并提交培训记录。', '[\"http://example.com/rectify_evi1.jpg\",\"http://example.com/rectify_evi2.pdf\"]', '合格', 1, NULL, '2026-03-08 06:28:53', '1', '1', '2026-03-06 21:47:53', '2026-03-12 17:18:53', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `ent_rectify_record` VALUES (2, 2, 2, '整改中', NULL, '正在整改中，预计3天内完成。', '[\"http://example.com/rectify_progress1.jpg\"]', NULL, NULL, NULL, NULL, '1', '1', '2026-03-10 16:43:53', '2026-03-12 17:18:53', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `ent_rectify_record` VALUES (3, 3, 3, '整改不合格', '2026-03-06 00:58:53', '已整改，但卫生状况仍未达标。', '[\"http://example.com/rectify_fail1.jpg\"]', '不合格', 1, '现场复查发现操作区仍有油污，未彻底清洁。', '2026-03-06 03:40:53', '1', '1', '2026-03-08 02:32:53', '2026-03-12 17:18:53', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `ent_rectify_record` VALUES (4, 4, 4, '已完成', '2026-03-08 15:20:53', '设备已全部检修完毕，并建立定期检修制度。', '[\"http://example.com/rectify_evi3.jpg\",\"http://example.com/rectify_report.pdf\"]', '合格', 1, NULL, '2026-03-08 19:05:53', '1', '1', '2026-03-06 03:34:53', '2026-03-12 17:18:53', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `ent_rectify_record` VALUES (5, 5, 5, '未整改', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', '2026-03-06 13:09:53', '2026-03-12 17:18:53', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `ent_rectify_record` VALUES (6, 6, 6, '已完成', '2026-03-08 13:46:53', '已补办所有从业人员健康证，并公示。', '[\"http://example.com/health_cert1.jpg\",\"http://example.com/health_cert2.jpg\"]', '合格', 1, NULL, '2026-03-08 14:24:53', '1', '1', '2026-03-07 01:51:53', '2026-03-12 17:18:53', b'0', 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for enterprise_info
-- ----------------------------
DROP TABLE IF EXISTS `enterprise_info`;
CREATE TABLE `enterprise_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '[主键ID] 企业信息唯一标识',
  `ent_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[企业编码] 唯一编码',
  `ent_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[企业名称] 企业全称',
  `area_id` bigint(0) NOT NULL COMMENT '[所属区域ID] 关联area_dict.id',
  `area_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[地区名] 冗余的地区名称',
  `ent_type_id` bigint(0) NOT NULL COMMENT '[企业类型ID] 关联ent_type_dict.id',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[详细地址] 企业注册或经营地址',
  `contact_person` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[联系人] 企业联系人姓名',
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[联系电话] 企业联系电话',
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[企业经营状态] 如：正常/停业/注销',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[创建人ID] 关联park_user.id',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[更新人ID] 关联park_user.id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '[创建时间] 记录创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '[更新时间] 记录最后更新时间',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '[删除标识] 如：0-未删除/1-已删除',
  `tenant_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '[租户ID] 租户唯一标识',
  `ext_common1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段1] 预留',
  `ext_common2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段2] 预留',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段3] 预留',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段4] 预留',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '企业信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of enterprise_info
-- ----------------------------
INSERT INTO `enterprise_info` VALUES (1, 'ENT20250312001', '福建科技有限公司', 1, '福州市鼓楼区', 1, '福建省福州市鼓楼区软件大道89号', '张三', '0591-12345678', '正常', '1', '1', '2026-03-10 10:34:29', '2026-03-10 11:10:29', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `enterprise_info` VALUES (2, 'ENT20250312002', '厦门贸易有限公司', 2, '厦门市思明区', 2, '福建省厦门市思明区湖滨南路55号', '李四', '0592-23456789', '正常', '1', '1', '2026-03-12 08:22:29', '2026-03-12 08:48:29', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `enterprise_info` VALUES (3, 'ENT20250312003', '泉州制造厂', 3, '泉州市晋江市', 1, '福建省泉州市晋江市经济开发区', '王五', '0595-34567890', '停业', '1', '1', '2026-03-12 05:26:29', '2026-03-12 05:26:29', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `enterprise_info` VALUES (4, 'ENT20250312004', '漳州农业合作社', 4, '漳州市龙海区', 3, '福建省漳州市龙海区紫泥镇', '赵六', '0596-45678901', '正常', '1', '1', '2026-03-06 17:38:29', '2026-03-06 17:51:29', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `enterprise_info` VALUES (5, 'ENT20250312005', '宁德新能源有限公司', 5, '宁德市蕉城区', 2, '福建省宁德市蕉城区东侨开发区', '陈七', '0593-56789012', '正常', '1', '1', '2026-03-08 11:44:29', '2026-03-08 12:03:29', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `enterprise_info` VALUES (6, 'ENT20250312006', '龙岩矿业集团', 6, '龙岩市新罗区', 1, '福建省龙岩市新罗区红坊镇', '刘八', '0597-67890123', '注销', '1', '1', '2026-03-07 06:11:29', '2026-03-07 07:07:29', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `enterprise_info` VALUES (7, 'ENT20250312007', '南平生态农业', 7, '南平市建阳区', 3, '福建省南平市建阳区将口镇', '黄九', '0599-78901234', '正常', '1', '1', '2026-03-10 01:25:29', '2026-03-10 01:28:29', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `enterprise_info` VALUES (8, 'ENT20250312008', '莆田鞋业有限公司', 8, '莆田市荔城区', 2, '福建省莆田市荔城区黄石镇', '林十', '0594-89012345', '正常', '1', '1', '2026-03-11 09:02:29', '2026-03-11 09:47:29', b'0', 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for illegal_level_dict
-- ----------------------------
DROP TABLE IF EXISTS `illegal_level_dict`;
CREATE TABLE `illegal_level_dict`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '[主键ID] 违规等级唯一标识',
  `level_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[违规等级编码] 唯一编码',
  `level_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[违规等级名称] 如：一般/较重/严重',
  `sort` int(0) NULL DEFAULT 0 COMMENT '[排序序号] 数值越小越靠前',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[创建人ID] 关联park_user.id',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[更新人ID] 关联park_user.id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '[创建时间] 记录创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '[更新时间] 记录最后更新时间',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '[删除标识] 如：0-未删除/1-已删除',
  `tenant_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '[租户ID] 租户唯一标识',
  `ext_common1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段1] 预留',
  `ext_common2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段2] 预留',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段3] 预留',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段4] 预留',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '违规等级字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of illegal_level_dict
-- ----------------------------
INSERT INTO `illegal_level_dict` VALUES (1, 'LEVEL_001', '一般', 1, '1', '1', '2026-03-11 13:34:08', '2026-03-11 13:53:08', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `illegal_level_dict` VALUES (2, 'LEVEL_002', '较重', 2, '1', '1', '2026-03-11 13:20:08', '2026-03-11 14:10:08', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `illegal_level_dict` VALUES (3, 'LEVEL_003', '严重', 3, '1', '1', '2026-03-08 00:30:08', '2026-03-08 01:21:08', b'0', 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for illegal_type_dict
-- ----------------------------
DROP TABLE IF EXISTS `illegal_type_dict`;
CREATE TABLE `illegal_type_dict`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '[主键ID] 违规类型唯一标识',
  `type_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[违规类型编码] 唯一编码',
  `type_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[违规类型名称] 如：未佩戴工牌/未穿工作服/从业人员未持健康证/操作区卫生不达标/食材存放不规范/设备未定期检修/操作流程不规范',
  `sort` int(0) NULL DEFAULT 0 COMMENT '[排序序号] 数值越小越靠前',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[创建人ID] 关联park_user.id',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[更新人ID] 关联park_user.id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '[创建时间] 记录创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '[更新时间] 记录最后更新时间',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '[删除标识] 如：0-未删除/1-已删除',
  `tenant_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '[租户ID] 租户唯一标识',
  `ext_common1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段1] 预留',
  `ext_common2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段2] 预留',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段3] 预留',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段4] 预留',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '违规类型字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of illegal_type_dict
-- ----------------------------
INSERT INTO `illegal_type_dict` VALUES (1, 'ILLEGAL_TYPE_001', '未佩戴工牌', 1, '1', '1', '2026-03-11 04:44:57', '2026-03-11 05:32:57', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `illegal_type_dict` VALUES (2, 'ILLEGAL_TYPE_002', '未穿工作服', 2, '1', '1', '2026-03-09 21:54:57', '2026-03-09 22:28:57', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `illegal_type_dict` VALUES (3, 'ILLEGAL_TYPE_003', '从业人员未持健康证', 3, '1', '1', '2026-03-08 03:16:57', '2026-03-08 03:49:57', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `illegal_type_dict` VALUES (4, 'ILLEGAL_TYPE_004', '操作区卫生不达标', 4, '1', '1', '2026-03-06 22:31:57', '2026-03-06 22:58:57', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `illegal_type_dict` VALUES (5, 'ILLEGAL_TYPE_005', '食材存放不规范', 5, '1', '1', '2026-03-07 03:57:57', '2026-03-07 04:33:57', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `illegal_type_dict` VALUES (6, 'ILLEGAL_TYPE_006', '设备未定期检修', 6, '1', '1', '2026-03-08 05:53:57', '2026-03-08 06:15:57', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `illegal_type_dict` VALUES (7, 'ILLEGAL_TYPE_007', '操作流程不规范', 7, '1', '1', '2026-03-05 17:57:57', '2026-03-05 18:46:57', b'0', 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for law_review_ledger
-- ----------------------------
DROP TABLE IF EXISTS `law_review_ledger`;
CREATE TABLE `law_review_ledger`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '[主键ID] 执法复审总台账唯一标识',
  `ledger_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[执法复审台账编号] 唯一，按执法区域编码+年份+序号自动生成',
  `rectify_review_id` bigint(0) NULL DEFAULT NULL COMMENT '[整改复审台账ID] 关联park_rectify_review.id，与punish_review_id互斥',
  `punish_review_id` bigint(0) NULL DEFAULT NULL COMMENT '[处罚复审台账ID] 关联park_punish_review_ledger.id，与rectify_review_id互斥',
  `ent_id` bigint(0) NOT NULL COMMENT '[企业ID] 关联park_enterprise_info.id',
  `law_area_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[执法区域编码] 关联area_dict.area_code',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[创建人ID] 关联park_user.id',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[更新人ID] 关联park_user.id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '[创建时间] 台账生成时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '[更新时间] 记录最后更新时间',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '[删除标识] 如：0-未删除/1-已删除',
  `tenant_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '[租户ID] 租户唯一标识',
  `ext_common1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段1] 预留',
  `ext_common2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段2] 预留',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段3] 预留',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段4] 预留',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '执法复审总台账表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of law_review_ledger
-- ----------------------------
INSERT INTO `law_review_ledger` VALUES (1, 'LAW3501022026016', 1, NULL, 1, '350102001001', '1', '1', '2026-03-06 19:54:52', '2026-03-06 20:03:52', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `law_review_ledger` VALUES (2, 'LAW3502112026281', 2, NULL, 2, '350211001001', '1', '1', '2026-03-06 06:39:52', '2026-03-06 07:24:52', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `law_review_ledger` VALUES (3, 'LAW3505022026044', 3, NULL, 3, '350502001001', '1', '1', '2026-03-06 04:03:52', '2026-03-06 04:35:52', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `law_review_ledger` VALUES (4, 'LAW3506022026939', 4, NULL, 4, '350602001001', '1', '1', '2026-03-12 09:17:52', '2026-03-12 09:42:52', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `law_review_ledger` VALUES (5, 'LAW3507022026964', 5, NULL, 5, '350702001001', '1', '1', '2026-03-08 19:22:52', '2026-03-08 20:16:52', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `law_review_ledger` VALUES (6, 'LAW3508022026833', 6, NULL, 6, '350802001001', '1', '1', '2026-03-09 11:45:52', '2026-03-09 12:33:52', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `law_review_ledger` VALUES (7, 'LAW3509022026654', 7, NULL, 7, '350902001001', '1', '1', '2026-03-06 18:49:52', '2026-03-06 19:05:52', b'0', 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for rectify_notice
-- ----------------------------
DROP TABLE IF EXISTS `rectify_notice`;
CREATE TABLE `rectify_notice`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '[主键ID] 整改通知书唯一标识',
  `notice_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[整改通知书编号] 唯一编号',
  `rectify_review_id` bigint(0) NOT NULL COMMENT '[整改复审台账ID] 关联rectify_review.id，唯一',
  `issue_time` datetime(0) NOT NULL COMMENT '[下发时间] 通知书正式下发时间',
  `rectify_deadline` date NOT NULL COMMENT '[整改期限] 要求完成整改的截止日期',
  `receive_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[送达状态] 如：未送达/已送达/拒收',
  `receive_time` datetime(0) NULL DEFAULT NULL COMMENT '[送达时间] 实际送达或拒收时间',
  `notice_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[通知书原件内容] 富文本内容',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[创建人ID] 关联sys_user.id',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[更新人ID] 关联sys_user.id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '[创建时间] 记录创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '[更新时间] 记录最后更新时间',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '[删除标识] 如：0-未删除/1-已删除',
  `tenant_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '[租户ID] 租户唯一标识',
  `ext_common1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段1] 预留',
  `ext_common2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段2] 预留',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段3] 预留',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '[通用扩展字段4] 预留',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '整改通知书表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rectify_notice
-- ----------------------------
INSERT INTO `rectify_notice` VALUES (1, 'NOTICE20250312001', 1, '2026-03-10 22:47:30', '2026-03-21', '已送达', '2026-03-11 22:47:30', '<p>你单位存在未佩戴工牌等违规行为，责令于规定期限内完成整改。</p>', '1', '1', '2026-03-07 19:37:30', '2026-03-12 17:13:30', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `rectify_notice` VALUES (2, 'NOTICE20250312002', 2, '2026-03-11 05:26:30', '2026-03-24', '未送达', NULL, '<p>操作区卫生不达标，请立即整改，否则将依法处理。</p>', '1', '1', '2026-03-11 10:08:30', '2026-03-12 17:13:30', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `rectify_notice` VALUES (3, 'NOTICE20250312003', 3, '2026-03-12 12:00:30', '2026-03-23', '拒收', '2026-03-12 12:00:30', '<p>食材存放不符合规范，限7日内完成整改。</p>', '1', '1', '2026-03-07 13:59:30', '2026-03-12 17:13:30', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `rectify_notice` VALUES (4, 'NOTICE20250312004', 4, '2026-03-06 01:58:30', '2026-03-16', '已送达', '2026-03-07 01:58:30', '<p>设备未定期检修，存在安全隐患，请立即整改。</p>', '1', '1', '2026-03-08 06:24:30', '2026-03-12 17:13:30', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `rectify_notice` VALUES (5, 'NOTICE20250312005', 5, '2026-03-08 07:45:30', '2026-03-16', '已送达', '2026-03-08 07:45:30', '<p>操作流程不规范，请加强员工培训，限期内完成整改。</p>', '1', '1', '2026-03-07 23:02:30', '2026-03-12 17:13:30', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `rectify_notice` VALUES (6, 'NOTICE20250312006', 6, '2026-03-08 16:50:30', '2026-03-20', '未送达', NULL, '<p>从业人员未持健康证，请立即办理并提交证明。</p>', '1', '1', '2026-03-09 17:26:30', '2026-03-12 17:13:30', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `rectify_notice` VALUES (7, 'WW12356', 1, '1970-01-01 08:00:00', '2026-03-12', '1', '1970-01-01 08:00:00', '<h2 style=\"text-align:center\">市场监督管理局</h2>\n<h3 style=\"text-align:center\">责令改正通知书</h3>\n\n<p style=\"text-align:center\">\n市监责改〔1970〕WW12356号\n</p>\n\n<p>相关单位：</p>\n\n<p>\n经查，你单位存在食品安全管理问题，\n现责令你单位在 2026-03-12 前完成整改。\n</p>\n\n<p style=\"text-align:right\">\n市场监督管理局\n</p>\n\n<p style=\"text-align:right\">\n1970-01-01\n</p>\n', '1', '1', '2026-03-14 09:05:58', '2026-03-14 09:05:58', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `rectify_notice` VALUES (8, 'WW12356', 1, '1970-01-01 08:00:00', '2026-03-12', '1', '1970-01-01 08:00:00', '<div style=\"font-family:SimSun;font-size:16px;line-height:30px\">\n\n<div style=\"text-align:center;font-size:22px;font-weight:bold\">\n__________市场监督管理局\n</div>\n\n<div style=\"text-align:center;font-size:20px;font-weight:bold;margin-top:10px\">\n责令改正通知书\n</div>\n\n<div style=\"text-align:center;margin-top:10px\">\n_市监责改〔1970〕WW12356号\n</div>\n\n<br/>\n\n<p>________________________：</p>\n\n<p style=\"text-indent:2em\">\n经查，你（单位）在校园餐饮后厨操作过程中，\n存在________________________\n（如：从业人员未按规定佩戴工作帽/口罩、操作区卫生不达标等）的行为，\n违反了《中华人民共和国食品安全法》第四十七条\n（食品生产经营者应当建立并执行从业人员健康管理制度）、\n《餐饮服务食品安全操作规范》第十六条\n（从业人员个人卫生要求）等相关规定。\n</p>\n\n<p style=\"text-indent:2em\">\n依据《中华人民共和国行政处罚法》第二十八条、\n《中华人民共和国食品安全法》第一百二十六条的规定，\n现责令你（单位）在 2026年3月12日 前改正。\n</p>\n\n<p style=\"text-indent:2em\">\n（改正内容及要求：立即组织后厨从业人员开展食品安全操作规范培训，\n严格落实穿戴工作帽、口罩等个人卫生要求。）\n</p>\n\n<p style=\"text-indent:2em\">\n（逾期不改的，本局将依据《中华人民共和国食品安全法》第一百二十六条的规定，\n依法给予行政处罚；情节严重的，责令停产停业，直至吊销许可证。）\n</p>\n\n<p style=\"text-indent:2em\">\n如对本责令改正决定不服，可以自收到本通知书之日起六十日内向\n________________________申请行政复议；\n也可以在六个月内依法向________________人民法院提起行政诉讼。\n</p>\n\n<br/>\n\n<p>\n联系人：________________\n&nbsp;&nbsp;&nbsp;&nbsp;\n联系电话：________________\n</p>\n\n<p>\n联系地址：________________________________________________\n</p>\n\n<br/><br/>\n\n<div style=\"text-align:right\">\n________________市场监督管理局\n</div>\n\n<div style=\"text-align:right;margin-top:10px\">\n1970 年 1 月 1 日\n</div>\n\n<br/><br/>\n\n<p>\n本文书一式____份，____份送达，一份归档，________________。\n</p>\n\n</div>\n', '1', '1', '2026-03-14 09:10:28', '2026-03-14 09:10:28', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `rectify_notice` VALUES (9, 'RNTC202603145d5aafc3', 1, '2026-03-14 11:33:20', '2026-03-12', '未送达', NULL, '<div style=\"font-family:SimSun;font-size:16px;line-height:30px\">\n\n<div style=\"text-align:center;font-size:22px;font-weight:bold\">\n__________市场监督管理局\n</div>\n\n<div style=\"text-align:center;font-size:20px;font-weight:bold;margin-top:10px\">\n责令改正通知书\n</div>\n\n<div style=\"text-align:center;margin-top:10px\">\n_市监责改〔1970〕WW12356号\n</div>\n\n<br/>\n\n<p>________________________：</p>\n\n<p style=\"text-indent:2em\">\n经查，你（单位）在校园餐饮后厨操作过程中，\n存在________________________\n（如：从业人员未按规定佩戴工作帽/口罩、操作区卫生不达标等）的行为，\n违反了《中华人民共和国食品安全法》第四十七条\n（食品生产经营者应当建立并执行从业人员健康管理制度）、\n《餐饮服务食品安全操作规范》第十六条\n（从业人员个人卫生要求）等相关规定。\n</p>\n\n<p style=\"text-indent:2em\">\n依据《中华人民共和国行政处罚法》第二十八条、\n《中华人民共和国食品安全法》第一百二十六条的规定，\n现责令你（单位）在 2026年3月12日 前改正。\n</p>\n\n<p style=\"text-indent:2em\">\n（改正内容及要求：立即组织后厨从业人员开展食品安全操作规范培训，\n严格落实穿戴工作帽、口罩等个人卫生要求。）\n</p>\n\n<p style=\"text-indent:2em\">\n（逾期不改的，本局将依据《中华人民共和国食品安全法》第一百二十六条的规定，\n依法给予行政处罚；情节严重的，责令停产停业，直至吊销许可证。）\n</p>\n\n<p style=\"text-indent:2em\">\n如对本责令改正决定不服，可以自收到本通知书之日起六十日内向\n________________________申请行政复议；\n也可以在六个月内依法向________________人民法院提起行政诉讼。\n</p>\n\n<br/>\n\n<p>\n联系人：________________\n&nbsp;&nbsp;&nbsp;&nbsp;\n联系电话：________________\n</p>\n\n<p>\n联系地址：________________________________________________\n</p>\n\n<br/><br/>\n\n<div style=\"text-align:right\">\n________________市场监督管理局\n</div>\n\n<div style=\"text-align:right;margin-top:10px\">\n1970 年 1 月 1 日\n</div>\n\n<br/><br/>\n\n<p>\n本文书一式____份，____份送达，一份归档，________________。\n</p>\n\n</div>\n', '1', '1', '2026-03-14 11:33:20', '2026-03-14 11:33:20', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `rectify_notice` VALUES (10, 'RNTC202603143c54d49c', 1, '2026-03-14 11:42:06', '2026-05-25', '未送达', NULL, '<div style=\"font-family:SimSun;font-size:16px;line-height:30px\">\n\n<div style=\"text-align:center;font-size:22px;font-weight:bold\">\n__________市场监督管理局\n</div>\n\n<div style=\"text-align:center;font-size:20px;font-weight:bold;margin-top:10px\">\n责令改正通知书\n</div>\n\n<div style=\"text-align:center;margin-top:10px\">\n_市监责改〔2026〕RNTC202603143c54d49c号\n</div>\n\n<br/>\n\n<p>________________________：</p>\n\n<p style=\"text-indent:2em\">\n经查，你（单位）在校园餐饮后厨操作过程中，\n存在________________________\n（如：从业人员未按规定佩戴工作帽/口罩、操作区卫生不达标等）的行为，\n违反了《中华人民共和国食品安全法》第四十七条\n（食品生产经营者应当建立并执行从业人员健康管理制度）、\n《餐饮服务食品安全操作规范》第十六条\n（从业人员个人卫生要求）等相关规定。\n</p>\n\n<p style=\"text-indent:2em\">\n依据《中华人民共和国行政处罚法》第二十八条、\n《中华人民共和国食品安全法》第一百二十六条的规定，\n现责令你（单位）在 2026年5月25日 前改正。\n</p>\n\n<p style=\"text-indent:2em\">\n（改正内容及要求：立即组织后厨从业人员开展食品安全操作规范培训，\n严格落实穿戴工作帽、口罩等个人卫生要求。）\n</p>\n\n<p style=\"text-indent:2em\">\n（逾期不改的，本局将依据《中华人民共和国食品安全法》第一百二十六条的规定，\n依法给予行政处罚；情节严重的，责令停产停业，直至吊销许可证。）\n</p>\n\n<p style=\"text-indent:2em\">\n如对本责令改正决定不服，可以自收到本通知书之日起六十日内向\n________________________申请行政复议；\n也可以在六个月内依法向________________人民法院提起行政诉讼。\n</p>\n\n<br/>\n\n<p>\n联系人：________________\n&nbsp;&nbsp;&nbsp;&nbsp;\n联系电话：________________\n</p>\n\n<p>\n联系地址：________________________________________________\n</p>\n\n<br/><br/>\n\n<div style=\"text-align:right\">\n________________市场监督管理局\n</div>\n\n<div style=\"text-align:right;margin-top:10px\">\n2026 年 3 月 14 日\n</div>\n\n<br/><br/>\n\n<p>\n本文书一式____份，____份送达，一份归档，________________。\n</p>\n\n</div>\n', '1', '1', '2026-03-14 11:42:06', '2026-03-14 11:42:06', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `rectify_notice` VALUES (11, 'RNTC20260314e16c7955', 1, '2026-03-14 11:42:08', '2026-05-25', '未送达', NULL, '<div style=\"font-family:SimSun;font-size:16px;line-height:30px\">\n\n<div style=\"text-align:center;font-size:22px;font-weight:bold\">\n__________市场监督管理局\n</div>\n\n<div style=\"text-align:center;font-size:20px;font-weight:bold;margin-top:10px\">\n责令改正通知书\n</div>\n\n<div style=\"text-align:center;margin-top:10px\">\n_市监责改〔2026〕RNTC20260314e16c7955号\n</div>\n\n<br/>\n\n<p>________________________：</p>\n\n<p style=\"text-indent:2em\">\n经查，你（单位）在校园餐饮后厨操作过程中，\n存在________________________\n（如：从业人员未按规定佩戴工作帽/口罩、操作区卫生不达标等）的行为，\n违反了《中华人民共和国食品安全法》第四十七条\n（食品生产经营者应当建立并执行从业人员健康管理制度）、\n《餐饮服务食品安全操作规范》第十六条\n（从业人员个人卫生要求）等相关规定。\n</p>\n\n<p style=\"text-indent:2em\">\n依据《中华人民共和国行政处罚法》第二十八条、\n《中华人民共和国食品安全法》第一百二十六条的规定，\n现责令你（单位）在 2026年5月25日 前改正。\n</p>\n\n<p style=\"text-indent:2em\">\n（改正内容及要求：立即组织后厨从业人员开展食品安全操作规范培训，\n严格落实穿戴工作帽、口罩等个人卫生要求。）\n</p>\n\n<p style=\"text-indent:2em\">\n（逾期不改的，本局将依据《中华人民共和国食品安全法》第一百二十六条的规定，\n依法给予行政处罚；情节严重的，责令停产停业，直至吊销许可证。）\n</p>\n\n<p style=\"text-indent:2em\">\n如对本责令改正决定不服，可以自收到本通知书之日起六十日内向\n________________________申请行政复议；\n也可以在六个月内依法向________________人民法院提起行政诉讼。\n</p>\n\n<br/>\n\n<p>\n联系人：________________\n&nbsp;&nbsp;&nbsp;&nbsp;\n联系电话：________________\n</p>\n\n<p>\n联系地址：________________________________________________\n</p>\n\n<br/><br/>\n\n<div style=\"text-align:right\">\n________________市场监督管理局\n</div>\n\n<div style=\"text-align:right;margin-top:10px\">\n2026 年 3 月 14 日\n</div>\n\n<br/><br/>\n\n<p>\n本文书一式____份，____份送达，一份归档，________________。\n</p>\n\n</div>\n', '1', '1', '2026-03-14 11:42:08', '2026-03-14 11:42:08', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `rectify_notice` VALUES (12, 'RNTC202603164824aee1', 1, '2026-03-16 08:59:00', '2026-04-15', '未送达', NULL, '<div style=\"font-family:SimSun;font-size:16px;line-height:30px\">\n\n<div style=\"text-align:center;font-size:22px;font-weight:bold\">\n__________市场监督管理局\n</div>\n\n<div style=\"text-align:center;font-size:20px;font-weight:bold;margin-top:10px\">\n责令改正通知书\n</div>\n\n<div style=\"text-align:center;margin-top:10px\">\n_市监责改〔2026〕RNTC202603164824aee1号\n</div>\n\n<br/>\n\n<p>________________________：</p>\n\n<p style=\"text-indent:2em\">\n经查，你（单位）在校园餐饮后厨操作过程中，\n存在________________________\n（如：从业人员未按规定佩戴工作帽/口罩、操作区卫生不达标等）的行为，\n违反了《中华人民共和国食品安全法》第四十七条\n（食品生产经营者应当建立并执行从业人员健康管理制度）、\n《餐饮服务食品安全操作规范》第十六条\n（从业人员个人卫生要求）等相关规定。\n</p>\n\n<p style=\"text-indent:2em\">\n依据《中华人民共和国行政处罚法》第二十八条、\n《中华人民共和国食品安全法》第一百二十六条的规定，\n现责令你（单位）在 2026年4月15日 前改正。\n</p>\n\n<p style=\"text-indent:2em\">\n（改正内容及要求：立即组织后厨从业人员开展食品安全操作规范培训，\n严格落实穿戴工作帽、口罩等个人卫生要求。）\n</p>\n\n<p style=\"text-indent:2em\">\n（逾期不改的，本局将依据《中华人民共和国食品安全法》第一百二十六条的规定，\n依法给予行政处罚；情节严重的，责令停产停业，直至吊销许可证。）\n</p>\n\n<p style=\"text-indent:2em\">\n如对本责令改正决定不服，可以自收到本通知书之日起六十日内向\n________________________申请行政复议；\n也可以在六个月内依法向________________人民法院提起行政诉讼。\n</p>\n\n<br/>\n\n<p>\n联系人：________________\n&nbsp;&nbsp;&nbsp;&nbsp;\n联系电话：________________\n</p>\n\n<p>\n联系地址：________________________________________________\n</p>\n\n<br/><br/>\n\n<div style=\"text-align:right\">\n________________市场监督管理局\n</div>\n\n<div style=\"text-align:right;margin-top:10px\">\n2026 年 3 月 16 日\n</div>\n\n<br/><br/>\n\n<p>\n本文书一式____份，____份送达，一份归档，________________。\n</p>\n\n</div>\n', '1', '1', '2026-03-16 08:59:00', '2026-03-16 08:59:00', b'0', 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for rectify_review
-- ----------------------------
DROP TABLE IF EXISTS `rectify_review`;
CREATE TABLE `rectify_review`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '[主键ID] 自增主键',
  `ledger_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '[台账编号] 整改通知书复审台账唯一编号',
  `ent_id` bigint(0) NOT NULL COMMENT '[企业ID] 关联park_enterprise_info.id',
  `illegal_type_id` bigint(0) NOT NULL COMMENT '[违规类型ID] 关联park_illegal_type_dict.id',
  `illegal_level_id` bigint(0) NOT NULL COMMENT '[违规等级ID] 关联park_illegal_level_dict.id',
  `evidence_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '[违规证据链接] 多链接以英文逗号分隔，varchar类型',
  `draft_time` datetime(0) NOT NULL COMMENT '[草拟时间] 整改通知书草拟时间',
  `review_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '[复审状态] 如：待复审/已下发/已撤销/已完成',
  `review_by` bigint(0) NULL DEFAULT NULL COMMENT '[复审人ID] 关联park_user.id',
  `review_time` datetime(0) NULL DEFAULT NULL COMMENT '[复审时间] 实际复审操作时间',
  `cancel_time` datetime(0) NULL DEFAULT NULL COMMENT '[撤销时间] 仅当状态为已撤销时有值',
  `cancel_reason_id` bigint(0) NULL DEFAULT NULL COMMENT '[撤销原因ID] 关联park_cancel_reason_dict.id，仅已撤销状态赋值',
  `law_ledger_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[执法复审台账编号] 关联park_law_review_ledger.ledger_code',
  `rectify_notice_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[整改通知书code]关联rectify_notice',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[创建人ID] 关联park_user.id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '[创建时间] 记录创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[更新人ID] 关联park_user.id',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '[更新时间] 记录最后更新时间',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '[删除标识] 如：0-未删除/1-已删除',
  `tenant_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '[租户ID] 租户唯一标识',
  `ext_common1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[通用扩展字段1] 预留',
  `ext_common2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[通用扩展字段2] 预留',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[通用扩展字段3] 预留',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[通用扩展字段4] 预留',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '整改通知书复审台账表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rectify_review
-- ----------------------------
INSERT INTO `rectify_review` VALUES (1, 'RECTIFY20260312685', 1, 2, 1, '[{\"name\":\"Snipaste_2026-03-09_14-23-50.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/7dfda1f0-49eb-4ba6-bfb9-30560bdc4a21.png\"},{\"name\":\"Snipaste_2026-03-09_14-22-05.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/2eede5e1-6b48-41bc-8f03-b4b145a68681.png\"}]', '2026-03-11 19:07:44', '已下发', 1, '2026-03-16 08:59:00', NULL, NULL, 'LAW20260312589', NULL, '1', '2026-03-12 16:50:44', '1', '2026-03-13 11:45:46', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `rectify_review` VALUES (2, 'RECTIFY20260312558', 2, 1, 2, '[{\"name\":\"Snipaste_2026-03-09_14-23-50.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/7dfda1f0-49eb-4ba6-bfb9-30560bdc4a21.png\"},{\"name\":\"Snipaste_2026-03-09_14-22-05.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/2eede5e1-6b48-41bc-8f03-b4b145a68681.png\"}]', '2026-03-12 13:02:44', '已下发', 1, '2026-03-12 14:02:44', NULL, NULL, 'LAW20260312127', NULL, '1', '2026-03-12 16:50:44', '1', '2026-03-13 11:46:04', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `rectify_review` VALUES (3, 'RECTIFY20260312319', 3, 3, 3, '[{\"name\":\"Snipaste_2026-03-09_14-23-50.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/7dfda1f0-49eb-4ba6-bfb9-30560bdc4a21.png\"},{\"name\":\"Snipaste_2026-03-09_14-22-05.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/2eede5e1-6b48-41bc-8f03-b4b145a68681.png\"}]', '2026-03-11 04:58:44', '已撤销', 1, '2026-03-11 04:58:44', '2026-03-12 13:58:44', 2, 'LAW20260312227', NULL, '1', '2026-03-12 16:50:44', '1', '2026-03-13 11:46:04', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `rectify_review` VALUES (4, 'RECTIFY20260312402', 4, 2, 2, '[{\"name\":\"Snipaste_2026-03-09_14-23-50.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/7dfda1f0-49eb-4ba6-bfb9-30560bdc4a21.png\"},{\"name\":\"Snipaste_2026-03-09_14-22-05.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/2eede5e1-6b48-41bc-8f03-b4b145a68681.png\"}]', '2026-03-10 09:33:44', '已撤销', 1, '2026-03-16 10:10:41', '2026-03-16 10:10:41', 1, 'LAW20260312438', NULL, '1', '2026-03-12 16:50:44', '1', '2026-03-13 11:46:04', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `rectify_review` VALUES (5, 'RECTIFY20260312204', 5, 1, 1, '[{\"name\":\"Snipaste_2026-03-09_14-23-50.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/7dfda1f0-49eb-4ba6-bfb9-30560bdc4a21.png\"},{\"name\":\"Snipaste_2026-03-09_14-22-05.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/2eede5e1-6b48-41bc-8f03-b4b145a68681.png\"}]', '2026-03-07 17:55:44', '已下发', 1, '2026-03-07 20:55:44', NULL, NULL, 'LAW20260312504', NULL, '1', '2026-03-12 16:50:44', '1', '2026-03-13 11:46:04', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `rectify_review` VALUES (6, 'RECTIFY20260312743', 1, 3, 2, '[{\"name\":\"Snipaste_2026-03-09_14-23-50.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/7dfda1f0-49eb-4ba6-bfb9-30560bdc4a21.png\"},{\"name\":\"Snipaste_2026-03-09_14-22-05.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/2eede5e1-6b48-41bc-8f03-b4b145a68681.png\"}]', '2026-03-11 06:02:44', '已撤销', 1, '2026-03-11 10:02:44', '2026-03-12 04:02:44', 1, 'LAW20260312587', NULL, '1', '2026-03-12 16:50:44', '1', '2026-03-13 11:46:04', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `rectify_review` VALUES (7, 'RECTIFY20260312737', 2, 2, 3, '[{\"name\":\"Snipaste_2026-03-09_14-23-50.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/7dfda1f0-49eb-4ba6-bfb9-30560bdc4a21.png\"},{\"name\":\"Snipaste_2026-03-09_14-22-05.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/2eede5e1-6b48-41bc-8f03-b4b145a68681.png\"}]', '2026-03-06 05:18:44', '待复审', NULL, NULL, NULL, NULL, 'LAW20260312415', NULL, '1', '2026-03-12 16:50:44', '1', '2026-03-13 11:46:04', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `rectify_review` VALUES (8, 'RECTIFY20260312301', 3, 1, 1, '[{\"name\":\"Snipaste_2026-03-09_14-23-50.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/7dfda1f0-49eb-4ba6-bfb9-30560bdc4a21.png\"},{\"name\":\"Snipaste_2026-03-09_14-22-05.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/2eede5e1-6b48-41bc-8f03-b4b145a68681.png\"}]', '2026-03-10 20:55:44', '已下发', 1, '2026-03-10 20:55:44', NULL, NULL, 'LAW20260312224', NULL, '1', '2026-03-12 16:50:44', '1', '2026-03-13 11:46:04', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `rectify_review` VALUES (9, 'RECTIFY20260312918', 4, 3, 2, '[{\"name\":\"Snipaste_2026-03-09_14-23-50.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/7dfda1f0-49eb-4ba6-bfb9-30560bdc4a21.png\"},{\"name\":\"Snipaste_2026-03-09_14-22-05.png\",\"type\":\"image\",\"url\":\"http://112.47.127.21:59000/shunchang/avatar/2eede5e1-6b48-41bc-8f03-b4b145a68681.png\"}]', '2026-03-06 06:20:44', '已撤销', 1, '2026-03-06 11:20:44', '2026-03-07 05:20:44', 3, 'LAW20260312728', NULL, '1', '2026-03-12 16:50:44', '1', '2026-03-13 11:46:04', b'0', 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '[主键ID] 自增主键',
  `user_account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '[用户账号] 唯一，登录账号',
  `user_pwd` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '[用户密码] 加密存储',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '[用户名称] 真实姓名',
  `role_id` bigint(0) NOT NULL COMMENT '[系统角色ID] 关联park_role.id，权限管控用',
  `manage_area_code` char(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '[管辖区域编码] 关联area_dict.area_code，限定用户监管范围，12位地区码',
  `contact_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[联系电话] 用户手机号',
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '[用户状态] 如：启用/禁用',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[创建人] 数据创建人ID',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[更新人] 数据最后更新人ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '[创建时间] 记录创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '[更新时间] 记录最后更新时间',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '[删除标识] 如：0-未删除/1-已删除',
  `tenant_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '[租户ID] 租户唯一标识',
  `ext_common1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[通用扩展字段1] 预留',
  `ext_common2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[通用扩展字段2] 预留',
  `ext_common3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[通用扩展字段3] 预留',
  `ext_common4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '[通用扩展字段4] 预留',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin1', '$2a$10$X7VYx8fG3kH9mNq2rP5sQeJ6tL9aBcDfGhIjKlMnOpQrStUvWxYz', '管理员一', 1, '350102001001', '13900001111', '启用', '1', '1', '2026-03-10 02:58:11', '2026-03-11 13:11:11', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (2, 'operator1', '$2a$10$X7VYx8fG3kH9mNq2rP5sQeJ6tL9aBcDfGhIjKlMnOpQrStUvWxYz', '操作员一', 2, '350102002002', '13900002222', '启用', '1', '1', '2026-03-07 17:00:11', '2026-03-12 05:28:11', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (3, 'finance1', '$2a$10$X7VYx8fG3kH9mNq2rP5sQeJ6tL9aBcDfGhIjKlMnOpQrStUvWxYz', '财务一', 3, '350105001001', '13900003333', '启用', '1', '1', '2026-03-11 08:24:11', '2026-03-07 09:35:11', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (4, 'auditor1', '$2a$10$X7VYx8fG3kH9mNq2rP5sQeJ6tL9aBcDfGhIjKlMnOpQrStUvWxYz', '审核员一', 4, '350211001001', '13900004444', '启用', '1', '1', '2026-03-11 06:44:11', '2026-03-07 12:56:11', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (5, 'manager1', '$2a$10$X7VYx8fG3kH9mNq2rP5sQeJ6tL9aBcDfGhIjKlMnOpQrStUvWxYz', '经理一', 5, '350211002001', '13900005555', '启用', '1', '1', '2026-03-12 04:32:11', '2026-03-11 15:51:11', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (6, 'tester1', '$2a$10$X7VYx8fG3kH9mNq2rP5sQeJ6tL9aBcDfGhIjKlMnOpQrStUvWxYz', '测试员一', 6, '350502001001', '13900006666', '禁用', '1', '1', '2026-03-09 01:44:11', '2026-03-11 17:05:11', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (7, 'guest1', '$2a$10$X7VYx8fG3kH9mNq2rP5sQeJ6tL9aBcDfGhIjKlMnOpQrStUvWxYz', '访客一', 7, '350502002001', '13900007777', '启用', '1', '1', '2026-03-11 16:16:11', '2026-03-10 14:08:11', b'0', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (8, 'supervisor1', '$2a$10$X7VYx8fG3kH9mNq2rP5sQeJ6tL9aBcDfGhIjKlMnOpQrStUvWxYz', '监督员一', 8, '350602001001', '13900008888', '启用', '1', '1', '2026-03-12 05:52:11', '2026-03-09 18:59:11', b'0', 1, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
