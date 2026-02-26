/*
Navicat MySQL Data Transfer

Source Server         : mydb
Source Server Version : 80032
Source Host           : localhost:3306
Source Database       : msm_environmental_health

Target Server Type    : MYSQL
Target Server Version : 80032
File Encoding         : 65001

Date: 2026-02-26 09:39:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for commercial_street
-- ----------------------------
DROP TABLE IF EXISTS `commercial_street`;
CREATE TABLE `commercial_street` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `street_id` varchar(64) DEFAULT NULL COMMENT '主键（UUID）',
  `name` varchar(128) DEFAULT NULL COMMENT '商业街名称',
  `address` varchar(256) DEFAULT NULL COMMENT '商业街地址',
  `area_code` varchar(64) DEFAULT NULL COMMENT '关联sys_area.area_code',
  `cleaning_frequency` varchar(32) DEFAULT NULL COMMENT '保洁频次',
  `transfer_interval` varchar(32) DEFAULT NULL COMMENT '垃圾清运间隔',
  `manager_id` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `operation_status_id` varchar(64) DEFAULT NULL COMMENT '关联sys_operation_status.id',
  `cleaning_coverage` decimal(5,2) DEFAULT NULL COMMENT '保洁覆盖率',
  `facility_rate` decimal(5,2) DEFAULT NULL COMMENT '设施完好率',
  `disposal_duration` decimal(5,2) DEFAULT NULL COMMENT '问题平均处置时长（单位：小时）',
  `collection_complete_rate` decimal(5,2) DEFAULT NULL COMMENT '收运完成率',
  `patrol_interval` varchar(32) DEFAULT NULL COMMENT '巡回保洁间隔',
  `cleaning_time` varchar(64) DEFAULT NULL COMMENT '保洁时段',
  `cleaner_ids` json DEFAULT NULL COMMENT '保洁人员IDs，JSON',
  `responsibility_area` varchar(256) DEFAULT NULL COMMENT '责任区域',
  `collection_points` int DEFAULT NULL COMMENT '垃圾收集点位数量',
  `abnormal_count` int DEFAULT NULL COMMENT '异常记录数',
  `facility_ids` json DEFAULT NULL COMMENT '设施类型IDs，JSON',
  `facility_location` varchar(256) DEFAULT NULL COMMENT '设施位置',
  `damage_desc` varchar(1024) DEFAULT NULL COMMENT '损坏描述',
  `report_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `report_time` datetime DEFAULT NULL COMMENT '上报时间',
  `problem_photo_url` json DEFAULT NULL COMMENT '上报照片URL，JSON',
  `handle_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `dispatch_time` datetime DEFAULT NULL COMMENT '派单时间',
  `maintain_status_id` varchar(64) DEFAULT NULL COMMENT '关联sys_maintain_status.id',
  `expected_complete_time` datetime DEFAULT NULL COMMENT '预计完成时间',
  `problem_type_id` varchar(64) DEFAULT NULL COMMENT '关联sys_problem_type.id',
  `problem_location` varchar(256) DEFAULT NULL COMMENT '问题位置',
  `problem_desc` varchar(1024) DEFAULT NULL COMMENT '问题描述',
  `handle_status_id` varchar(64) DEFAULT NULL COMMENT '关联sys_handle_status.id',
  `handle_result` varchar(1024) DEFAULT NULL COMMENT '处置结果',
  `vehicle_id` varchar(64) DEFAULT NULL COMMENT '负责车辆ID，关联sys_vehicle.sys_vehicle_id（支持车辆钻取）',
  `staff_ids` json DEFAULT NULL COMMENT '负责人员IDs，JSON格式，关联sys_user.user_id',
  `plan_status_id` varchar(64) DEFAULT NULL COMMENT '收运计划状态ID，关联sys_plan_status.sys_plan_status_id（支持状态筛选钻取）',
  `task_type_id` varchar(64) DEFAULT NULL COMMENT '任务类型ID，关联sys_task_type.name（支持任务类型钻取，筛选同类型已完成任务）',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商业街表';

-- ----------------------------
-- Records of commercial_street
-- ----------------------------
INSERT INTO `commercial_street` VALUES ('1', 'uuid-street-001', '王府井商业街', '北京市东城区王府井大街1号', '1001', '每30分钟1次', '每1小时1次', 'uuid-user-007', 'uuid-op-status-001', '98.50', '95.00', '2.50', '99.00', '每15分钟1次', '00:00-24:00（轮班）', '[\"uuid-user-001\", \"uuid-user-002\"]', '东城区东华门街道全部片区', '50', '3', '[\"uuid-facility-001\", \"uuid-facility-002\", \"uuid-facility-003\"]', '主街两侧/广场区域/卫生间', '主街中段垃圾桶破损3个，影响垃圾收集', 'uuid-user-001', '2024-05-18 09:00:00', '[\"https://xxx.com/street/1-1.jpg\", \"https://xxx.com/street/1-2.jpg\"]', 'uuid-user-001', '2024-05-18 09:10:00', 'uuid-maintain-003', '2024-05-18 11:00:00', 'uuid-problem-type-003', '主街中段', '主街中段3个垃圾桶破损，垃圾外溢，影响市容', 'uuid-handle-003', '已更换破损垃圾桶，清理外溢垃圾，增加垃圾桶巡检频次至每15分钟1次', 'uuid-vehicle-001', '[\"uuid-user-001\", \"uuid-user-002\", \"uuid-user-007\"]', 'uuid-plan-status-001', 'uuid-task-type-002', 'ext1-001', 'ext2-001', 'ext3-001', 'ext4-001', 'admin', 'admin');
INSERT INTO `commercial_street` VALUES ('2', 'uuid-street-002', '南京路步行街', '上海市黄浦区南京东路800号', '3101', '每20分钟1次', '每1.5小时1次', 'uuid-user-008', 'uuid-op-status-001', '99.00', '96.50', '1.80', '98.50', '每10分钟1次', '00:00-24:00（轮班）', '[\"uuid-user-003\", \"uuid-user-004\"]', '黄浦区外滩街道全部片区', '65', '2', '[\"uuid-facility-001\", \"uuid-facility-004\"]', '步行街主道/地铁口/商场周边', '地铁口垃圾桶满溢，清运不及时', 'uuid-user-003', '2024-05-19 10:30:00', '[\"https://xxx.com/street/2-1.jpg\"]', 'uuid-user-003', '2024-05-19 10:40:00', 'uuid-maintain-002', '2024-05-19 12:00:00', 'uuid-problem-type-002', '地铁口周边', '地铁口垃圾桶满溢，垃圾堆积，行人投诉', 'uuid-handle-002', '已加急清运垃圾，临时增加2个垃圾桶，调整清运频次至每1小时1次', 'uuid-vehicle-002', '[\"uuid-user-003\", \"uuid-user-004\", \"uuid-user-008\"]', 'uuid-plan-status-001', 'uuid-task-type-002', 'ext1-002', 'ext2-002', 'ext3-002', 'ext4-002', 'admin', 'admin');
INSERT INTO `commercial_street` VALUES ('3', 'uuid-street-003', '春熙路商业街', '四川省成都市锦江区上东大街6号', '5101', '每25分钟1次', '每2小时1次', 'uuid-user-009', 'uuid-op-status-001', '97.80', '94.00', '3.20', '97.00', '每20分钟1次', '07:00-23:00', '[\"uuid-user-005\", \"uuid-user-006\"]', '锦江区春熙路街道核心片区', '45', '4', '[\"uuid-facility-001\", \"uuid-facility-002\"]', '步行街核心区/小吃街/公交站', '小吃街地面垃圾较多，保洁不及时', 'uuid-user-005', '2024-05-20 14:00:00', '[\"https://xxx.com/street/3-1.jpg\", \"https://xxx.com/street/3-2.jpg\"]', 'uuid-user-005', '2024-05-20 14:15:00', 'uuid-maintain-001', '2024-05-20 16:00:00', 'uuid-problem-type-001', '小吃街中段', '小吃街地面油污、食物残渣较多，保洁频次不足', 'uuid-handle-001', '已增加保洁人员2名，调整保洁频次至每15分钟1次，使用专用清洁剂清理油污', 'uuid-vehicle-003', '[\"uuid-user-005\", \"uuid-user-006\", \"uuid-user-009\"]', 'uuid-plan-status-001', 'uuid-task-type-001', 'ext1-003', 'ext2-003', 'ext3-003', 'ext4-003', 'admin', 'admin');
INSERT INTO `commercial_street` VALUES ('4', 'uuid-street-004', '夫子庙商业街', '江苏省南京市秦淮区贡院街152号', '3201', '每30分钟1次', '每1小时1次', 'uuid-user-010', 'uuid-op-status-002', '96.50', '92.50', '4.00', '98.00', '每15分钟1次', '08:00-22:00', '[\"uuid-user-007\", \"uuid-user-008\"]', '秦淮区夫子庙街道全域', '55', '1', '[\"uuid-facility-003\", \"uuid-facility-005\"]', '景区核心区/游船码头/停车场', '游船码头护栏松动，存在安全隐患', 'uuid-user-007', '2024-05-21 09:30:00', '[\"https://xxx.com/street/4-1.jpg\"]', 'uuid-user-007', '2024-05-21 09:45:00', 'uuid-maintain-004', '2024-05-21 15:00:00', 'uuid-problem-type-004', '游船码头', '游船码头护栏多处松动，游客倚靠有坠落风险', 'uuid-handle-004', '已临时封闭松动护栏区域，安排维修人员加固，完成后增加每日安全巡检1次', 'uuid-vehicle-004', '[\"uuid-user-007\", \"uuid-user-008\", \"uuid-user-010\"]', 'uuid-plan-status-002', 'uuid-task-type-003', 'ext1-004', 'ext2-004', 'ext3-004', 'ext4-004', 'admin', 'admin');
INSERT INTO `commercial_street` VALUES ('5', 'uuid-street-005', '宽窄巷子商业街', '四川省成都市青羊区金河路口', '5101', '每20分钟1次', '每1小时1次', 'uuid-user-011', 'uuid-op-status-001', '98.20', '95.50', '2.00', '99.50', '每10分钟1次', '09:00-21:00', '[\"uuid-user-009\", \"uuid-user-010\"]', '青羊区少城街道宽窄巷子片区', '40', '2', '[\"uuid-facility-001\", \"uuid-facility-006\"]', '巷子主道/特色商铺前/公厕旁', '公厕旁垃圾桶未加盖，异味严重', 'uuid-user-009', '2024-05-22 11:00:00', '[\"https://xxx.com/street/5-1.jpg\"]', 'uuid-user-009', '2024-05-22 11:10:00', 'uuid-maintain-002', '2024-05-22 13:00:00', 'uuid-problem-type-002', '公厕旁', '公厕旁垃圾桶未加盖，夏季异味扩散，游客反馈差', 'uuid-handle-002', '已为所有垃圾桶加装防溢盖，每日3次喷洒除臭剂，安排专人监督垃圾桶加盖情况', 'uuid-vehicle-005', '[\"uuid-user-009\", \"uuid-user-010\", \"uuid-user-011\"]', 'uuid-plan-status-001', 'uuid-task-type-004', 'ext1-005', 'ext2-005', 'ext3-005', 'ext4-005', 'admin', 'admin');
INSERT INTO `commercial_street` VALUES ('6', 'uuid-street-006', '观前街', '江苏省苏州市姑苏区观前街1号', '3205', '每25分钟1次', '每1.5小时1次', 'uuid-user-012', 'uuid-op-status-002', '97.00', '93.00', '3.50', '98.00', '每20分钟1次', '08:00-22:00', '[\"uuid-user-011\", \"uuid-user-012\"]', '姑苏区观前街道核心片区', '58', '3', '[\"uuid-facility-002\", \"uuid-facility-003\", \"uuid-facility-007\"]', '主街/老字号商铺前/步行街入口', '步行街入口路灯故障，垃圾桶破损', 'uuid-user-011', '2024-05-23 15:00:00', '[\"https://xxx.com/street/6-1.jpg\", \"https://xxx.com/street/6-2.jpg\"]', 'uuid-user-011', '2024-05-23 15:20:00', 'uuid-maintain-004', '2024-05-23 18:00:00', 'uuid-problem-type-004', '步行街入口', '入口路灯2盏故障不亮，垃圾桶1个破损，夜间视线差', 'uuid-handle-004', '已更换故障路灯，更换破损垃圾桶，增加设施巡检频次至每日2次', 'uuid-vehicle-006', '[\"uuid-user-011\", \"uuid-user-012\", \"uuid-user-012\"]', 'uuid-plan-status-002', 'uuid-task-type-003', 'ext1-006', 'ext2-006', 'ext3-006', 'ext4-006', 'admin', 'admin');

-- ----------------------------
-- Table structure for garbage_abnormal
-- ----------------------------
DROP TABLE IF EXISTS `garbage_abnormal`;
CREATE TABLE `garbage_abnormal` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `abnormal_id` varchar(64) DEFAULT NULL COMMENT '异常记录主键（UUID）',
  `plan_id` varchar(64) DEFAULT NULL COMMENT '关联garbage_collection.collection_id',
  `abnormal_type_id` varchar(64) DEFAULT NULL COMMENT '关联sys_abnormal_type.id',
  `area_code` varchar(64) DEFAULT NULL COMMENT '关联sys_area.area_code',
  `report_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `report_time` datetime DEFAULT NULL COMMENT '上报时间',
  `priority` varchar(16) DEFAULT NULL COMMENT '优先级：高/中/低',
  `handler_id` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `handle_status` varchar(16) DEFAULT NULL COMMENT '处置状态：待处置/处理中/已办结/退回',
  `is_timeout` varchar(8) DEFAULT NULL COMMENT '超时提醒：是/否',
  `handle_desc` varchar(512) DEFAULT NULL COMMENT '整改说明',
  `handle_photo_url` json DEFAULT NULL COMMENT '整改照片URL，JSON',
  `review_status` varchar(16) DEFAULT NULL COMMENT '复核状态：待复核/通过/退回',
  `review_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `review_time` datetime DEFAULT NULL COMMENT '复核时间',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='异常记录表';

-- ----------------------------
-- Records of garbage_abnormal
-- ----------------------------
INSERT INTO `garbage_abnormal` VALUES ('1', 'uuid-abnormal-001', 'uuid-plan-001', 'uuid-abnormal-type-001', '1001', 'uuid-user-001', '2024-02-01 08:30:00', '高', 'uuid-user-001', '已办结', '否', '已完成垃圾清运，清理完毕', '[\"https://xxx.com/handle/1-1.jpg\", \"https://xxx.com/handle/1-2.jpg\"]', '通过', 'uuid-user-002', '2024-02-01 10:00:00', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21');
INSERT INTO `garbage_abnormal` VALUES ('2', 'uuid-abnormal-002', 'uuid-plan-002', 'uuid-abnormal-type-002', '1002', 'uuid-user-002', '2024-02-01 09:15:00', '中', 'uuid-user-002', '处理中', '否', '已现场督导分类，持续整改', '[\"https://xxx.com/handle/2-1.jpg\"]', '待复核', 'uuid-user-003', null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21');
INSERT INTO `garbage_abnormal` VALUES ('3', 'uuid-abnormal-003', 'uuid-plan-003', 'uuid-abnormal-type-003', '1003', 'uuid-user-003', '2024-02-01 10:00:00', '高', 'uuid-user-003', '已办结', '是', '更换损坏垃圾桶，完成维修', '[\"https://xxx.com/handle/3-1.jpg\"]', '通过', 'uuid-user-004', '2024-02-02 09:00:00', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21');
INSERT INTO `garbage_abnormal` VALUES ('4', 'uuid-abnormal-004', 'uuid-plan-004', 'uuid-abnormal-type-004', '1004', 'uuid-user-004', '2024-02-01 11:20:00', '中', 'uuid-user-004', '待处置', '否', '待安排加班清运', null, '待复核', 'uuid-user-005', null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21');
INSERT INTO `garbage_abnormal` VALUES ('5', 'uuid-abnormal-005', 'uuid-plan-005', 'uuid-abnormal-type-005', '1005', 'uuid-user-005', '2024-02-01 14:00:00', '高', 'uuid-user-005', '已办结', '是', '车辆抢修完成，恢复清运', '[\"https://xxx.com/handle/5-1.jpg\"]', '退回', 'uuid-user-006', '2024-02-01 16:00:00', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21');
INSERT INTO `garbage_abnormal` VALUES ('6', 'uuid-abnormal-006', 'uuid-plan-006', 'uuid-abnormal-type-006', '1006', 'uuid-user-006', '2024-02-02 08:00:00', '高', 'uuid-user-006', '处理中', '是', '已设置警示标识，待专业人员处理', '[\"https://xxx.com/handle/6-1.jpg\"]', '待复核', 'uuid-user-007', null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21');
INSERT INTO `garbage_abnormal` VALUES ('7', 'uuid-abnormal-007', 'uuid-plan-007', 'uuid-abnormal-type-007', '1007', 'uuid-user-007', '2024-02-02 09:30:00', '低', 'uuid-user-007', '待处置', '否', '已联系投诉人，待上门沟通', null, '待复核', 'uuid-user-001', null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21');
INSERT INTO `garbage_abnormal` VALUES ('8', 'uuid-abnormal-008', 'uuid-plan-001', 'uuid-abnormal-type-002', '1001', 'uuid-user-001', '2024-02-02 10:15:00', '低', 'uuid-user-002', '已办结', '否', '完成分类宣传，居民已整改', '[\"https://xxx.com/handle/8-1.jpg\"]', '通过', 'uuid-user-003', '2024-02-02 11:00:00', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21');

-- ----------------------------
-- Table structure for garbage_collection
-- ----------------------------
DROP TABLE IF EXISTS `garbage_collection`;
CREATE TABLE `garbage_collection` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `collection_id` varchar(64) NOT NULL COMMENT '收运计划主键（UUID）',
  `plan_no` varchar(64) NOT NULL COMMENT '收运计划单编号',
  `area_code` varchar(64) DEFAULT NULL COMMENT '区域编码（关联sys_area.area_code）',
  `garbage_type_id` varchar(64) DEFAULT NULL COMMENT '垃圾类型ID（关联sys_garbage_type.id）',
  `frequency` varchar(32) DEFAULT NULL COMMENT '收运频次',
  `time_period` varchar(32) DEFAULT NULL COMMENT '收运时段',
  `vehicle_id` varchar(64) DEFAULT NULL COMMENT '车辆ID（关联sys_vehicle.id）',
  `staff_ids` json DEFAULT NULL COMMENT '负责人员IDs',
  `point_ids` json DEFAULT NULL COMMENT '收运点位IDs',
  `plan_status_id` varchar(64) DEFAULT NULL COMMENT '计划状态ID（关联sys_plan_status.id）',
  `completion_rate` decimal(5,2) DEFAULT '0.00' COMMENT '完成率',
  `abnormal_count` int DEFAULT '0' COMMENT '异常记录数',
  `abnormal_create_time` datetime DEFAULT NULL COMMENT '异常创建时间',
  `abnormal_update_time` datetime DEFAULT NULL COMMENT '异常更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人ID（关联sys_user.id）',
  `progress` decimal(5,2) DEFAULT '0.00' COMMENT '当前进度（按点位完成率计算）',
  `collected_volume` decimal(10,2) DEFAULT '0.00' COMMENT '已收运量（实时上报累计）',
  `checkin_status` varchar(32) DEFAULT NULL COMMENT '打卡状态：到岗/离岗',
  `track_coverage` varchar(32) DEFAULT NULL COMMENT '轨迹覆盖情况（系统自动校验）',
  `last_report_time` datetime DEFAULT NULL COMMENT '最新上报时间',
  `is_abnormal` bit(1) DEFAULT b'0' COMMENT '是否异常（系统自动标记）',
  `complete_time` datetime DEFAULT NULL COMMENT '完成时间',
  `total_volume` decimal(10,2) DEFAULT '0.00' COMMENT '总收运量',
  `abnormal_result` varchar(32) DEFAULT NULL COMMENT '异常处置结果：无/已办结/部分办结',
  `abnormal_complete_rate` decimal(5,2) DEFAULT '0.00' COMMENT '异常办结率（自动计算）',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（0-未删，1-已删）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='收运计划表';

-- ----------------------------
-- Records of garbage_collection
-- ----------------------------
INSERT INTO `garbage_collection` VALUES ('1', 'uuid-collect-001', 'GC20240601001', '1001', 'uuid-garbage-001', '每日', '07:00-11:00', 'uuid-vehicle-001', '[\"uuid-user-001\", \"uuid-user-002\"]', '[\"uuid-point-001\", \"uuid-point-008\"]', 'uuid-plan-status-003', '100.00', '0', null, null, 'uuid-user-001', '100.00', '28.50', '离岗', '100%', '2024-06-01 10:50:00', '\0', '2024-06-01 11:00:00', '28.50', '无', '0.00', null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `garbage_collection` VALUES ('2', 'uuid-collect-002', 'GC20240601002', '1002', 'uuid-garbage-002', '每日', '12:00-16:00', 'uuid-vehicle-002', '[\"uuid-user-003\", \"uuid-user-004\"]', '[\"uuid-point-002\"]', 'uuid-plan-status-002', '75.00', '1', '2024-06-01 13:15:00', '2024-06-01 14:30:00', 'uuid-user-002', '75.00', '19.80', '到岗', '90%', '2024-06-01 14:25:00', '', null, '26.50', '部分办结', '50.00', null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `garbage_collection` VALUES ('3', 'uuid-collect-003', 'GC20240601003', '1003', 'uuid-garbage-003', '每周', '09:00-11:00', 'uuid-vehicle-003', '[\"uuid-user-005\"]', '[\"uuid-point-003\"]', 'uuid-plan-status-001', '0.00', '0', null, null, 'uuid-user-003', '0.00', '0.00', '', '', null, '\0', null, '5.00', '无', '0.00', null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `garbage_collection` VALUES ('4', 'uuid-collect-004', 'GC20240601004', '1004', 'uuid-garbage-004', '每日', '14:00-18:00', 'uuid-vehicle-004', '[\"uuid-user-006\", \"uuid-user-007\"]', '[\"uuid-point-004\"]', 'uuid-plan-status-003', '100.00', '2', '2024-06-01 15:00:00', '2024-06-01 17:40:00', 'uuid-user-004', '100.00', '42.30', '离岗', '100%', '2024-06-01 17:30:00', '\0', '2024-06-01 18:00:00', '42.30', '已办结', '100.00', null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `garbage_collection` VALUES ('5', 'uuid-collect-005', 'GC20240601005', '1005', 'uuid-garbage-005', '每周', '10:00-14:00', 'uuid-vehicle-005', '[\"uuid-user-001\", \"uuid-user-003\"]', '[\"uuid-point-005\"]', 'uuid-plan-status-004', '40.00', '3', '2024-06-01 10:30:00', '2024-06-01 13:15:00', 'uuid-user-005', '40.00', '8.20', '到岗', '65%', '2024-06-01 13:10:00', '', null, '20.50', '部分办结', '33.33', null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `garbage_collection` VALUES ('6', 'uuid-collect-006', 'GC20240601006', '1006', 'uuid-garbage-006', '每月', '08:00-12:00', 'uuid-vehicle-006', '[\"uuid-user-002\", \"uuid-user-004\"]', '[\"uuid-point-006\"]', 'uuid-plan-status-006', '30.00', '1', '2024-06-01 08:10:00', '2024-06-01 09:45:00', 'uuid-user-006', '30.00', '6.80', '', '40%', '2024-06-01 09:40:00', '', null, '22.50', '待办结', '0.00', null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `garbage_collection` VALUES ('7', 'uuid-collect-007', 'GC20240601007', '1007', 'uuid-garbage-007', '每周', '16:00-20:00', 'uuid-vehicle-007', '[\"uuid-user-005\", \"uuid-user-007\"]', '[\"uuid-point-007\"]', 'uuid-plan-status-002', '85.00', '0', null, null, 'uuid-user-007', '85.00', '21.70', '到岗', '95%', '2024-06-01 19:10:00', '\0', null, '25.50', '无', '0.00', null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `garbage_collection` VALUES ('8', 'uuid-collect-008', 'GC20240601008', '1001', 'uuid-garbage-001', '应急', '13:00-17:00', 'uuid-vehicle-001', '[\"uuid-user-001\"]', '[\"uuid-point-001\", \"uuid-point-008\"]', 'uuid-plan-status-005', '0.00', '0', null, null, 'uuid-user-001', '0.00', '0.00', '', '', null, '\0', null, '0.00', '无', '0.00', null, null, null, null, 'admin', 'admin', '\0', '1');

-- ----------------------------
-- Table structure for garbage_transfer
-- ----------------------------
DROP TABLE IF EXISTS `garbage_transfer`;
CREATE TABLE `garbage_transfer` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `transfer_id` varchar(64) DEFAULT NULL COMMENT '转运站主键（UUID）',
  `name` varchar(128) DEFAULT NULL COMMENT '转运站名称',
  `location` varchar(256) DEFAULT NULL COMMENT '转运站位置',
  `area_code` varchar(64) DEFAULT NULL COMMENT '关联sys_area.area_code',
  `equipment_ids` json DEFAULT NULL COMMENT '核心设备IDs，JSON',
  `operation_status_id` varchar(64) DEFAULT NULL COMMENT '关联sys_operation_status.id',
  `manager_id` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `daily_transfer_volume` decimal(10,2) DEFAULT NULL COMMENT '日转运量（单位：吨）',
  `equipment_rate` decimal(5,2) DEFAULT NULL COMMENT '设备正常运行率',
  `environment_rate` decimal(5,2) DEFAULT NULL COMMENT '环境达标率',
  `unhandled_alarm_count` int DEFAULT NULL COMMENT '预警未处理数',
  `pending_maintenance_count` int DEFAULT NULL COMMENT '设备待维护数',
  `environment_data` json DEFAULT NULL COMMENT '实时环境数据，JSON',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='垃圾转运站表';

-- ----------------------------
-- Records of garbage_transfer
-- ----------------------------
INSERT INTO `garbage_transfer` VALUES ('1', 'uuid-transfer-001', '城东垃圾转运站', '北京市朝阳区东四环北路18号', '1001', '[\"uuid-equip-001\", \"uuid-equip-002\", \"uuid-equip-003\"]', 'uuid-op-status-001', 'uuid-user-001', '200.50', '99.00', '98.50', '0', '1', '{\"odor_value\": 0.02, \"noise_value\": 55, \"sewage_standard\": \"达标\"}', null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `garbage_transfer` VALUES ('2', 'uuid-transfer-002', '城西垃圾转运站', '上海市黄浦区南京西路258号', '1002', '[\"uuid-equip-001\", \"uuid-equip-004\"]', 'uuid-op-status-002', 'uuid-user-002', '150.20', '85.00', '90.00', '2', '3', '{\"odor_value\": 0.05, \"noise_value\": 62, \"sewage_standard\": \"达标\"}', null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `garbage_transfer` VALUES ('3', 'uuid-transfer-003', '城南垃圾转运站', '广州市天河区天河路388号', '1003', '[\"uuid-equip-001\", \"uuid-equip-002\", \"uuid-equip-005\", \"uuid-equip-006\"]', 'uuid-op-status-001', 'uuid-user-003', '300.80', '97.50', '96.00', '1', '0', '{\"odor_value\": 0.03, \"noise_value\": 58, \"sewage_standard\": \"达标\"}', null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `garbage_transfer` VALUES ('4', 'uuid-transfer-004', '城北垃圾转运站', '深圳市南山区科技园北区', '1004', '[]', 'uuid-op-status-003', 'uuid-user-004', '0.00', '0.00', '0.00', '0', '5', '{\"odor_value\": 0.0, \"noise_value\": 0, \"sewage_standard\": \"未检测\"}', null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `garbage_transfer` VALUES ('5', 'uuid-transfer-005', '西湖景区垃圾转运站', '杭州市西湖区西湖大道128号', '1005', '[\"uuid-equip-002\", \"uuid-equip-004\", \"uuid-equip-007\"]', 'uuid-op-status-001', 'uuid-user-005', '80.60', '99.50', '99.00', '0', '0', '{\"odor_value\": 0.01, \"noise_value\": 45, \"sewage_standard\": \"优\"}', null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `garbage_transfer` VALUES ('6', 'uuid-transfer-006', '夫子庙景区垃圾转运站', '南京市秦淮区贡院街88号', '1006', '[\"uuid-equip-001\", \"uuid-equip-003\"]', 'uuid-op-status-002', 'uuid-user-006', '60.30', '88.00', '85.00', '1', '2', '{\"odor_value\": 0.06, \"noise_value\": 65, \"sewage_standard\": \"轻微超标\"}', null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `garbage_transfer` VALUES ('7', 'uuid-transfer-007', '春熙路商圈垃圾转运站', '成都市锦江区春熙路步行街66号', '1007', '[\"uuid-equip-001\", \"uuid-equip-002\", \"uuid-equip-005\", \"uuid-equip-008\"]', 'uuid-op-status-001', 'uuid-user-007', '280.90', '95.00', '92.00', '2', '1', '{\"odor_value\": 0.04, \"noise_value\": 60, \"sewage_standard\": \"达标\"}', null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `garbage_transfer` VALUES ('8', 'uuid-transfer-008', '王府井商圈垃圾转运站', '北京市东城区王府井大街99号', '1001', '[\"uuid-equip-001\", \"uuid-equip-004\", \"uuid-equip-006\", \"uuid-equip-007\"]', 'uuid-op-status-001', 'uuid-user-001', '220.70', '98.00', '97.00', '0', '0', '{\"odor_value\": 0.02, \"noise_value\": 56, \"sewage_standard\": \"达标\"}', null, null, null, null, 'admin', 'admin', '\0', '1');

-- ----------------------------
-- Table structure for garbage_transfer_alarm
-- ----------------------------
DROP TABLE IF EXISTS `garbage_transfer_alarm`;
CREATE TABLE `garbage_transfer_alarm` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `alarm_id` varchar(64) DEFAULT NULL COMMENT '预警主键（UUID）',
  `transfer_id` varchar(64) DEFAULT NULL COMMENT '关联garbage_transfer.transfer_id',
  `alarm_type_id` varchar(64) DEFAULT NULL COMMENT '关联sys_alarm_type.id',
  `alarm_time` datetime DEFAULT NULL COMMENT '发生时间',
  `alarm_content` varchar(1024) DEFAULT NULL COMMENT '预警内容',
  `relevant_info` varchar(256) DEFAULT NULL COMMENT '关联设备/区域',
  `handle_status` varchar(16) DEFAULT NULL COMMENT '处置状态：待处置/处理中/已解除',
  `handle_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `abnormal_is_timeout` varchar(8) DEFAULT NULL COMMENT '超时提醒：是/否',
  `handle_progress` varchar(16) DEFAULT NULL COMMENT '处置进度',
  `handle_result` varchar(512) DEFAULT NULL COMMENT '处置结果',
  `proof_material` json DEFAULT NULL COMMENT '佐证材料URL，JSON',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='转运站预警表';

-- ----------------------------
-- Records of garbage_transfer_alarm
-- ----------------------------
INSERT INTO `garbage_transfer_alarm` VALUES ('1', 'uuid-alarm-001', 'uuid-transfer-002', 'uuid-alarm-type-001', '2024-05-19 14:20:00', '压缩机电机故障，无法正常压缩垃圾', '设备ID：uuid-equip-004', '已解除', 'uuid-user-002', '否', '100%', '已更换压缩机电机，设备恢复正常运行', '[\"https://xxx.com/alarm/1-1.jpg\", \"https://xxx.com/alarm/1-2.jpg\"]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20');
INSERT INTO `garbage_transfer_alarm` VALUES ('2', 'uuid-alarm-002', 'uuid-transfer-006', 'uuid-alarm-type-002', '2024-05-20 08:10:00', '站内臭气浓度0.06mg/m³，轻微超标', '区域：作业区1号', '处理中', 'uuid-user-006', '否', '60%', '已开启除臭设备，持续通风中', '[\"https://xxx.com/alarm/2-1.jpg\"]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20');
INSERT INTO `garbage_transfer_alarm` VALUES ('3', 'uuid-alarm-003', 'uuid-transfer-007', 'uuid-alarm-type-003', '2024-05-20 09:20:00', '日转运量已达280.9吨，超出设计250吨承载能力', '统计时段：2024-05-20 00:00-09:20', '处理中', 'uuid-user-007', '否', '50%', '已增派转运车辆，加快外运速度', '[\"https://xxx.com/alarm/3-1.jpg\"]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20');
INSERT INTO `garbage_transfer_alarm` VALUES ('4', 'uuid-alarm-004', 'uuid-transfer-002', 'uuid-alarm-type-005', '2024-05-18 00:00:00', '输送机未按每月1次周期维护，已超期20天', '设备ID：uuid-equip-001', '待处置', null, '是', '0%', '', '[]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20');
INSERT INTO `garbage_transfer_alarm` VALUES ('5', 'uuid-alarm-005', 'uuid-transfer-003', 'uuid-alarm-type-001', '2024-05-20 10:15:00', '输送机皮带打滑，影响垃圾转运效率', '设备ID：uuid-equip-005', '处理中', 'uuid-user-003', '否', '40%', '已安排维修人员现场处理，准备更换皮带', '[\"https://xxx.com/alarm/5-1.jpg\"]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20');
INSERT INTO `garbage_transfer_alarm` VALUES ('6', 'uuid-alarm-006', 'uuid-transfer-007', 'uuid-alarm-type-007', '2024-05-20 08:35:00', '有害垃圾转运车预约超时35分钟，未进站', '车辆ID：uuid-vehicle-006，预约时间：08:00', '待处置', null, '否', '0%', '', '[]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20');
INSERT INTO `garbage_transfer_alarm` VALUES ('7', 'uuid-alarm-007', 'uuid-transfer-006', 'uuid-alarm-type-006', '2024-05-19 10:00:00', '作业区消防栓压力不足，存在安全风险', '区域：作业区2号消防栓', '已解除', 'uuid-user-006', '否', '100%', '已对消防栓进行加压维护，压力恢复正常', '[\"https://xxx.com/alarm/7-1.jpg\"]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20');
INSERT INTO `garbage_transfer_alarm` VALUES ('8', 'uuid-alarm-008', 'uuid-transfer-007', 'uuid-alarm-type-004', '2024-05-19 18:00:00', '站内其他垃圾积压约50吨，未及时外运', '区域：库存区3号', '处理中', 'uuid-user-007', '是', '70%', '已联系垃圾填埋场，安排夜间转运', '[\"https://xxx.com/alarm/8-1.jpg\"]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20');

-- ----------------------------
-- Table structure for garbage_transfer_maintenance
-- ----------------------------
DROP TABLE IF EXISTS `garbage_transfer_maintenance`;
CREATE TABLE `garbage_transfer_maintenance` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `maintenance_id` varchar(64) DEFAULT NULL COMMENT '维护主键（UUID）',
  `transfer_id` varchar(64) DEFAULT NULL COMMENT '关联garbage_transfer.transfer_id',
  `equipment_id` varchar(64) DEFAULT NULL COMMENT '关联sys_equipment.id',
  `maintenance_cycle` varchar(32) DEFAULT NULL COMMENT '维护周期',
  `last_maintenance_time` datetime DEFAULT NULL COMMENT '上次维护时间',
  `maintenance_content` varchar(1024) DEFAULT NULL COMMENT '维护内容',
  `handle_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `maintenance_status` varchar(16) DEFAULT NULL COMMENT '维护状态：待维护/维护中/已完成',
  `expected_complete_time` datetime DEFAULT NULL COMMENT '预计完成时间',
  `abnormal_is_timeout` varchar(8) DEFAULT NULL COMMENT '超时提醒：是/否',
  `maintenance_time` datetime DEFAULT NULL COMMENT '维护时间',
  `replace_parts` varchar(512) DEFAULT NULL COMMENT '更换配件',
  `maintenance_cost` decimal(10,2) DEFAULT NULL COMMENT '维护费用（单位：元）',
  `maintenance_photo` json DEFAULT NULL COMMENT '维护照片URL，JSON',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='设备维护表';

-- ----------------------------
-- Records of garbage_transfer_maintenance
-- ----------------------------
INSERT INTO `garbage_transfer_maintenance` VALUES ('1', 'uuid-maintain-001', 'uuid-transfer-001', 'uuid-equip-001', '每月1次', '2024-04-20 09:00:00', '压缩机常规保养：清洁滤网、检查油路、测试运行状态', 'uuid-user-001', '已完成', '2024-05-20 12:00:00', '否', '2024-05-20 10:30:00', '滤网1个', '200.00', '[\"https://xxx.com/maintain/1-1.jpg\"]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20');
INSERT INTO `garbage_transfer_maintenance` VALUES ('2', 'uuid-maintain-002', 'uuid-transfer-002', 'uuid-equip-004', '每月1次', '2024-04-15 14:00:00', '压缩机电机故障维修：更换电机、调试线路、测试运行', 'uuid-user-002', '已完成', '2024-05-19 18:00:00', '否', '2024-05-19 16:00:00', '压缩机电机1台、线路若干', '5800.00', '[\"https://xxx.com/maintain/2-1.jpg\", \"https://xxx.com/maintain/2-2.jpg\"]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20');
INSERT INTO `garbage_transfer_maintenance` VALUES ('3', 'uuid-maintain-003', 'uuid-transfer-002', 'uuid-equip-001', '每月1次', '2024-03-18 10:00:00', '输送机常规保养：清洁滚筒、检查皮带、加注润滑油', 'uuid-user-002', '待维护', '2024-04-18 12:00:00', '是', null, '', '0.00', '[]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20');
INSERT INTO `garbage_transfer_maintenance` VALUES ('4', 'uuid-maintain-004', 'uuid-transfer-003', 'uuid-equip-005', '每月1次', '2024-04-19 08:00:00', '输送机皮带打滑维修：更换皮带、调整滚筒、测试运行', 'uuid-user-003', '维护中', '2024-05-20 18:00:00', '否', null, '输送机皮带1条', '1200.00', '[\"https://xxx.com/maintain/4-1.jpg\"]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20');
INSERT INTO `garbage_transfer_maintenance` VALUES ('5', 'uuid-maintain-005', 'uuid-transfer-005', 'uuid-equip-007', '每季度1次', '2024-02-28 10:00:00', '除臭设备常规保养：清洁滤芯、检查风机、测试除臭效果', 'uuid-user-005', '已完成', '2024-05-20 15:00:00', '否', '2024-05-20 14:00:00', '滤芯2个', '800.00', '[\"https://xxx.com/maintain/5-1.jpg\"]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20');
INSERT INTO `garbage_transfer_maintenance` VALUES ('6', 'uuid-maintain-006', 'uuid-transfer-006', 'uuid-equip-009', '每半年1次', '2023-11-19 09:00:00', '消防栓压力不足维修：检查管道、加压、测试出水状态', 'uuid-user-006', '已完成', '2024-05-19 12:00:00', '否', '2024-05-19 10:30:00', '密封垫3个', '150.00', '[\"https://xxx.com/maintain/6-1.jpg\"]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20');
INSERT INTO `garbage_transfer_maintenance` VALUES ('7', 'uuid-maintain-007', 'uuid-transfer-007', 'uuid-equip-008', '每3个月1次', '2024-02-20 10:00:00', '地磅常规校准：调试传感器、校验精度、测试称重功能', 'uuid-user-007', '维护中', '2024-05-20 17:00:00', '否', null, '传感器校准配件1套', '500.00', '[\"https://xxx.com/maintain/7-1.jpg\"]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20');
INSERT INTO `garbage_transfer_maintenance` VALUES ('8', 'uuid-maintain-008', 'uuid-transfer-004', 'uuid-equip-010', '每年1次', '2023-05-01 08:00:00', '转运站整体设备大修：压缩机、输送机、除臭设备全面维护', 'uuid-user-004', '待维护', '2024-06-01 18:00:00', '否', null, '', '0.00', '[]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20');

-- ----------------------------
-- Table structure for garbage_transfer_operation
-- ----------------------------
DROP TABLE IF EXISTS `garbage_transfer_operation`;
CREATE TABLE `garbage_transfer_operation` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `operation_id` varchar(64) DEFAULT NULL COMMENT '作业主键（UUID）',
  `vehicle_id` varchar(64) DEFAULT NULL COMMENT '关联sys_vehicle.id',
  `garbage_type_id` varchar(64) DEFAULT NULL COMMENT '关联sys_garbage_type.id',
  `entry_time` datetime DEFAULT NULL COMMENT '进站时间',
  `garbage_weight` decimal(8,2) DEFAULT NULL COMMENT '垃圾重量（单位：吨）',
  `plan_id` varchar(64) DEFAULT NULL COMMENT '关联garbage_collection.collection_id',
  `equipment_status` json DEFAULT NULL COMMENT '核心设备状态，JSON',
  `progress` varchar(16) DEFAULT NULL COMMENT '作业进度',
  `destination` varchar(256) DEFAULT NULL COMMENT '转运去向',
  `abnormal_is_abnormal` varchar(8) DEFAULT NULL COMMENT '异常标记：是/否',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='转运作业表';

-- ----------------------------
-- Records of garbage_transfer_operation
-- ----------------------------
INSERT INTO `garbage_transfer_operation` VALUES ('1', 'uuid-operation-001', 'uuid-vehicle-001', 'uuid-garbage-001', '2024-05-20 08:35:00', '15.50', 'uuid-collect-001', '{\"uuid-equip-001\": \"正常\", \"uuid-equip-002\": \"正常\", \"uuid-equip-003\": \"正常\"}', '100%', '城东垃圾焚烧厂', '否', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20');
INSERT INTO `garbage_transfer_operation` VALUES ('2', 'uuid-operation-002', 'uuid-vehicle-002', 'uuid-garbage-002', '2024-05-20 09:05:00', '12.30', 'uuid-collect-002', '{\"uuid-equip-001\": \"正常\", \"uuid-equip-004\": \"轻微故障\"}', '70%', '城西厨余垃圾处理厂', '否', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20');
INSERT INTO `garbage_transfer_operation` VALUES ('3', 'uuid-operation-003', 'uuid-vehicle-004', 'uuid-garbage-002', '2024-05-20 08:05:00', '8.60', 'uuid-collect-003', '{\"uuid-equip-002\": \"正常\", \"uuid-equip-004\": \"正常\", \"uuid-equip-007\": \"正常\"}', '100%', '景区垃圾处理中心', '否', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20');
INSERT INTO `garbage_transfer_operation` VALUES ('4', 'uuid-operation-004', 'uuid-vehicle-005', 'uuid-garbage-001', '2024-05-20 09:35:00', '6.20', 'uuid-collect-004', '{\"uuid-equip-001\": \"正常\", \"uuid-equip-003\": \"待维护\"}', '60%', '秦淮区可回收垃圾分拣中心', '否', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20');
INSERT INTO `garbage_transfer_operation` VALUES ('5', 'uuid-operation-005', 'uuid-vehicle-007', 'uuid-garbage-004', '2024-05-20 09:15:00', '18.90', 'uuid-collect-005', '{\"uuid-equip-001\": \"正常\", \"uuid-equip-004\": \"正常\", \"uuid-equip-006\": \"正常\", \"uuid-equip-007\": \"正常\"}', '100%', '城北垃圾填埋场', '否', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20');
INSERT INTO `garbage_transfer_operation` VALUES ('6', 'uuid-operation-006', 'uuid-vehicle-006', 'uuid-garbage-003', '2024-05-20 10:35:00', '3.50', 'uuid-collect-006', '{\"uuid-equip-001\": \"正常\", \"uuid-equip-002\": \"正常\", \"uuid-equip-005\": \"正常\", \"uuid-equip-008\": \"正常\"}', '0%', '成都市有害垃圾处理中心', '否', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20');
INSERT INTO `garbage_transfer_operation` VALUES ('7', 'uuid-operation-007', 'uuid-vehicle-003', 'uuid-garbage-004', '2024-05-20 10:10:00', '20.80', 'uuid-collect-007', '{\"uuid-equip-001\": \"故障\", \"uuid-equip-002\": \"正常\", \"uuid-equip-005\": \"正常\", \"uuid-equip-006\": \"正常\"}', '30%', '城南垃圾焚烧厂', '是', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20');
INSERT INTO `garbage_transfer_operation` VALUES ('8', 'uuid-operation-008', 'uuid-vehicle-008', 'uuid-garbage-002', '2024-05-20 11:05:00', '25.10', 'uuid-collect-008', '{\"uuid-equip-001\": \"正常\", \"uuid-equip-002\": \"正常\", \"uuid-equip-005\": \"正常\", \"uuid-equip-006\": \"正常\"}', '0%', '城南厨余垃圾处理厂', '否', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20');

-- ----------------------------
-- Table structure for garbage_transfer_reserve
-- ----------------------------
DROP TABLE IF EXISTS `garbage_transfer_reserve`;
CREATE TABLE `garbage_transfer_reserve` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `reserve_id` varchar(64) DEFAULT NULL COMMENT '预约主键（UUID）',
  `vehicle_id` varchar(64) DEFAULT NULL COMMENT '关联sys_vehicle.id',
  `garbage_type_id` varchar(64) DEFAULT NULL COMMENT '关联sys_garbage_type.id',
  `expected_time` datetime DEFAULT NULL COMMENT '预计进站时间',
  `garbage_weight` decimal(8,2) DEFAULT NULL COMMENT '垃圾重量（单位：吨）',
  `area_code` varchar(64) DEFAULT NULL COMMENT '关联sys_area.area_code',
  `reserve_status` varchar(16) DEFAULT NULL COMMENT '预约状态：待排序/已排序/已进站',
  `sort_no` int DEFAULT NULL COMMENT '排序序号',
  `abnormal_create_time` datetime DEFAULT NULL COMMENT '创建时间（业务字段）',
  `handle_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='进站预约表';

-- ----------------------------
-- Records of garbage_transfer_reserve
-- ----------------------------
INSERT INTO `garbage_transfer_reserve` VALUES ('1', 'uuid-reserve-001', 'uuid-vehicle-001', 'uuid-garbage-001', '2024-05-20 08:30:00', '15.50', '1001', '已进站', '1', '2024-05-20 07:00:00', 'uuid-user-001', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-14 17:17:37');
INSERT INTO `garbage_transfer_reserve` VALUES ('2', 'uuid-reserve-002', 'uuid-vehicle-002', 'uuid-garbage-002', '2024-05-20 09:00:00', '12.30', '1002', '已排序', '2', '2024-05-20 07:30:00', 'uuid-user-002', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-14 17:17:39');
INSERT INTO `garbage_transfer_reserve` VALUES ('3', 'uuid-reserve-003', 'uuid-vehicle-003', 'uuid-garbage-004', '2024-05-20 10:00:00', '20.80', '1003', '待排序', '0', '2024-05-20 08:00:00', 'uuid-user-003', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-14 17:17:42');
INSERT INTO `garbage_transfer_reserve` VALUES ('4', 'uuid-reserve-004', 'uuid-vehicle-004', 'uuid-garbage-002', '2024-05-20 08:00:00', '8.60', '1005', '已进站', '1', '2024-05-20 06:30:00', 'uuid-user-005', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-14 17:17:45');
INSERT INTO `garbage_transfer_reserve` VALUES ('5', 'uuid-reserve-005', 'uuid-vehicle-005', 'uuid-garbage-001', '2024-05-20 09:30:00', '6.20', '1006', '已排序', '1', '2024-05-20 07:40:00', 'uuid-user-006', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-14 17:17:47');
INSERT INTO `garbage_transfer_reserve` VALUES ('6', 'uuid-reserve-006', 'uuid-vehicle-006', 'uuid-garbage-003', '2024-05-20 10:30:00', '3.50', '1007', '待排序', '0', '2024-05-20 08:30:00', 'uuid-user-007', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-14 17:17:50');
INSERT INTO `garbage_transfer_reserve` VALUES ('7', 'uuid-reserve-007', 'uuid-vehicle-007', 'uuid-garbage-004', '2024-05-20 09:10:00', '18.90', '1001', '已进站', '2', '2024-05-20 07:20:00', 'uuid-user-001', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-14 17:17:52');
INSERT INTO `garbage_transfer_reserve` VALUES ('8', 'uuid-reserve-008', 'uuid-vehicle-008', 'uuid-garbage-002', '2024-05-20 11:00:00', '25.10', '1003', '已排序', '3', '2024-05-20 08:50:00', 'uuid-user-003', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-14 17:17:55');

-- ----------------------------
-- Table structure for market
-- ----------------------------
DROP TABLE IF EXISTS `market`;
CREATE TABLE `market` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `market_id` varchar(64) DEFAULT NULL COMMENT '主键（UUID）',
  `name` varchar(128) DEFAULT NULL COMMENT '市场名称',
  `address` varchar(256) DEFAULT NULL COMMENT '市场地址',
  `area_code` varchar(64) DEFAULT NULL COMMENT '关联sys_area.area_code',
  `stall_count` int DEFAULT NULL COMMENT '摊位数量',
  `manager_id` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `operation_status_id` varchar(64) DEFAULT NULL COMMENT '关联sys_operation_status.id',
  `hygiene_rate` decimal(5,2) DEFAULT NULL COMMENT '卫生达标率',
  `waste_transfer_rate` decimal(5,2) DEFAULT NULL COMMENT '收运完成率',
  `sewage_rate` decimal(5,2) DEFAULT NULL COMMENT '污水处置合格率',
  `unfinished_task_count` int DEFAULT NULL COMMENT '未完成任务数',
  `cleaning_frequency` varchar(32) DEFAULT NULL COMMENT '保洁频次',
  `cleaning_time` json DEFAULT NULL COMMENT '保洁时段，JSON',
  `cleaning_area` varchar(256) DEFAULT NULL COMMENT '保洁区域',
  `staff_ids` json DEFAULT NULL COMMENT '负责人员IDs，JSON',
  `cleaning_standard` varchar(512) DEFAULT NULL COMMENT '保洁标准',
  `garbage_type_ids` json DEFAULT NULL COMMENT '垃圾类型IDs，JSON',
  `garbage_container_count` int DEFAULT NULL COMMENT '收集容器数量',
  `waste_transfer_interval` varchar(32) DEFAULT NULL COMMENT '收运间隔',
  `waste_transfer_time` varchar(64) DEFAULT NULL COMMENT '收运时段',
  `vehicle_id` varchar(64) DEFAULT NULL COMMENT '关联sys_vehicle.id',
  `sewage_discharge_area` varchar(256) DEFAULT NULL COMMENT '污水排放区域',
  `sewage_disposal_way` varchar(128) DEFAULT NULL COMMENT '污水处置方式',
  `sewage_cleaning_frequency` varchar(32) DEFAULT NULL COMMENT '清理频次',
  `sewage_problem_desc` varchar(1024) DEFAULT NULL COMMENT '问题描述',
  `last_sewage_cleaning_time` datetime DEFAULT NULL COMMENT '上次清理时间',
  `next_sewage_cleaning_time` datetime DEFAULT NULL COMMENT '下次清理时间',
  `sewage_disposal_log` varchar(2048) DEFAULT NULL COMMENT '处置日志',
  `hygiene_check_time` varchar(64) DEFAULT NULL COMMENT '核查时段',
  `check_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `hygiene_check_date` datetime DEFAULT NULL COMMENT '核查日期',
  `previous_problem` varchar(1024) DEFAULT NULL COMMENT '前期问题',
  `qualified_item_count` int DEFAULT NULL COMMENT '达标项数',
  `unqualified_item_count` int DEFAULT NULL COMMENT '不达标项数',
  `reform_require` varchar(1024) DEFAULT NULL COMMENT '整改要求',
  `reform_deadline` datetime DEFAULT NULL COMMENT '整改期限',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `handle_status_id` varchar(64) DEFAULT NULL COMMENT '处理状态ID，关联sys_handle_status.id',
  `check_result_id` varchar(64) DEFAULT NULL COMMENT '核查结果ID，关联sys_check_result.id',
  `task_type_id` varchar(64) DEFAULT NULL COMMENT '任务类型ID，关联sys_task_type.id',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='集贸市场表';

-- ----------------------------
-- Records of market
-- ----------------------------
INSERT INTO `market` VALUES ('1', 'uuid-market-001', '北京新发地农产品批发市场', '北京市丰台区京开高速新发地桥西侧', '1001', '2000', 'uuid-user-001', 'uuid-op-status-001', '99.00', '99.50', '98.00', '0', '每15分钟1次（交易区）/每30分钟1次（非交易区）', '[\"04:00-08:00(早高峰)\", \"08:00-18:00(平峰)\", \"18:00-24:00(晚高峰)\"]', '交易区、停车场、公厕、污水区、办公区', '[\"uuid-user-001\", \"uuid-user-002\"]', '交易区地面无积水、无散落垃圾；公厕每15分钟巡检1次；污水区无异味', '[\"uuid-garbage-001\", \"uuid-garbage-002\", \"uuid-garbage-003\", \"uuid-garbage-004\"]', '500', '每30分钟1次（交易区）/每1小时1次（非交易区）', '04:00-24:00（轮班）', 'uuid-vehicle-001', '水产区、肉类区、蔬菜区', '三级沉淀池+生化处理+市政管网排放', '每2小时1次（交易时段）/每日1次（非交易时段）', '', '2024-05-18 04:00:00', '2024-05-18 06:00:00', '2024-05-18 04:00 完成水产区污水清理，水质达标；2024-05-18 02:00 完成肉类区污水管道疏通', '08:00-10:00（日常）/每月1日（月度）', 'uuid-user-011', '2024-05-17 09:00:00', '', '20', '0', '', null, null, null, null, null, 'uuid-handle-001', 'uuid-check-001', 'uuid-task-type-001', 'admin', 'admin', '\0');
INSERT INTO `market` VALUES ('2', 'uuid-market-002', '上海江杨农产品批发市场', '上海市宝山区江杨北路98号', '1002', '1500', 'uuid-user-002', 'uuid-op-status-002', '80.00', '85.00', '75.00', '5', '每20分钟1次（交易区）/每40分钟1次（非交易区）', '[\"05:00-09:00(早高峰)\", \"09:00-19:00(平峰)\", \"19:00-23:00(晚高峰)\"]', '交易区、停车场、公厕、污水区、仓储区', '[\"uuid-user-001\", \"uuid-user-002\"]', '交易区地面无大块垃圾；公厕每30分钟巡检1次；污水区每日清理1次', '[\"uuid-garbage-001\", \"uuid-garbage-002\", \"uuid-garbage-003\", \"uuid-garbage-005\"]', '400', '每40分钟1次（交易区）/每1.5小时1次（非交易区）', '05:00-23:00（轮班）', 'uuid-vehicle-002', '水产区、家禽区、水果区', '二级沉淀池+市政管网排放', '每3小时1次（交易时段）/每日1次（非交易时段）', '水产区污水管道堵塞，污水外溢，异味扰民；家禽区污水处置不达标，氨氮含量超标', '2024-05-19 05:00:00', '2024-05-19 08:00:00', '2024-05-19 05:00 清理家禽区污水池，水质仍不达标；2024-05-18 22:00 尝试疏通水产区管道未成功', '09:00-11:00（日常）/每月2日（月度）', 'uuid-user-010', '2024-05-18 10:00:00', '上月核查发现水产区污水排放不达标，未完成整改', '12', '8', '1. 24小时内疏通水产区污水管道，解决外溢问题；2. 3日内完成家禽区污水处置设备升级，确保氨氮含量达标；3. 增加污水清理频次至每2小时1次', '2024-05-22 18:00:00', null, null, null, null, 'uuid-handle-002', 'uuid-check-002', 'uuid-task-type-002', 'admin', 'admin', '\0');
INSERT INTO `market` VALUES ('3', 'uuid-market-003', '广州黄沙水产交易市场', '广州市荔湾区黄沙大道16号', '1003', '800', 'uuid-user-002', 'uuid-op-status-001', '98.00', '99.00', '97.00', '0', '每10分钟1次（交易区）/每20分钟1次（非交易区）', '[\"03:00-07:00(早高峰)\", \"07:00-18:00(平峰)\", \"18:00-22:00(晚高峰)\"]', '交易区、装卸区、公厕、污水沟、冷库区', '[\"uuid-user-001\", \"uuid-user-002\"]', '交易区无积水、无鱼腥垃圾堆积；公厕每10分钟巡检1次；污水沟每小时清理1次', '[\"uuid-garbage-001\", \"uuid-garbage-002\", \"uuid-garbage-004\", \"uuid-garbage-006\"]', '300', '每20分钟1次（交易区）/每1小时1次（非交易区）', '03:00-22:00（轮班）', 'uuid-vehicle-003', '各水产交易档位、中央污水沟', '四级沉淀池+消毒处理+市政管网排放', '每1小时1次（交易时段）/每日2次（非交易时段）', '', '2024-05-20 03:00:00', '2024-05-20 04:00:00', '2024-05-20 03:00 完成中央污水沟清理，水质达标；2024-05-19 23:00 完成各档位污水池清理', '08:00-10:00（日常）/每月3日（月度）', 'uuid-user-009', '2024-05-19 09:00:00', '', '18', '0', '', null, null, null, null, null, 'uuid-handle-001', 'uuid-check-001', 'uuid-task-type-001', 'admin', 'admin', '\0');
INSERT INTO `market` VALUES ('4', 'uuid-market-004', '杭州勾庄农副产品物流中心', '杭州市余杭区博园路1号', '1004', '1800', 'uuid-user-002', 'uuid-op-status-001', '88.00', '90.00', '85.00', '3', '每15分钟1次（交易区）/每30分钟1次（非交易区）', '[\"04:00-08:00(早高峰)\", \"08:00-19:00(平峰)\", \"19:00-24:00(晚高峰)\"]', '交易区、停车场、公厕、污水区、配送区', '[\"uuid-user-001\", \"uuid-user-002\"]', '交易区地面无明显垃圾；公厕每20分钟巡检1次；污水区每2小时清理1次', '[\"uuid-garbage-001\", \"uuid-garbage-002\", \"uuid-garbage-003\", \"uuid-garbage-005\"]', '450', '每30分钟1次（交易区）/每1小时1次（非交易区）', '04:00-24:00（轮班）', 'uuid-vehicle-004', '蔬菜区、肉类区、豆制品区', '三级沉淀池+市政管网排放', '每2小时1次（交易时段）/每日1次（非交易时段）', '豆制品区污水池清理不及时，悬浮物超标；配送区垃圾桶满溢未及时清运', '2024-05-21 04:00:00', '2024-05-21 06:00:00', '2024-05-21 04:00 完成蔬菜区、肉类区污水清理，豆制品区暂未处理；2024-05-20 23:00 清运配送区垃圾，仍有3个垃圾桶满溢', '09:00-11:00（日常）/每月4日（月度）', 'uuid-user-008', '2024-05-20 10:00:00', '上月核查发现配送区垃圾清运不及时，整改不彻底', '15', '5', '1. 12小时内清理豆制品区污水池，确保悬浮物达标；2. 增加配送区垃圾清运频次至每20分钟1次；3. 每日核查垃圾桶使用状态', '2024-05-23 12:00:00', null, null, null, null, 'uuid-handle-002', 'uuid-check-003', 'uuid-task-type-003', 'admin', 'admin', '\0');
INSERT INTO `market` VALUES ('5', 'uuid-market-005', '成都白家农产品批发市场', '成都市双流区成白路98号', '1005', '1200', 'uuid-user-002', 'uuid-op-status-002', '70.00', '75.00', '65.00', '8', '每25分钟1次（交易区）/每50分钟1次（非交易区）', '[\"05:00-09:00(早高峰)\", \"09:00-18:00(平峰)\", \"18:00-22:00(晚高峰)\"]', '交易区、停车场、公厕、污水区、仓储区', '[\"uuid-user-001\", \"uuid-user-002\"]', '交易区垃圾堆积不超过30分钟；公厕每40分钟巡检1次；污水区每日清理1次', '[\"uuid-garbage-001\", \"uuid-garbage-002\", \"uuid-garbage-003\", \"uuid-garbage-006\"]', '350', '每50分钟1次（交易区）/每2小时1次（非交易区）', '05:00-22:00（轮班）', 'uuid-vehicle-005', '水果区、水产区、熟食区', '一级沉淀池+市政管网排放', '每4小时1次（交易时段）/每日1次（非交易时段）', '熟食区污水直排，未经过滤处理；水产区污水池长期未清理，淤泥堆积；整体污水处置合格率仅65%', '2024-05-17 05:00:00', '2024-05-17 09:00:00', '2024-05-17 05:00 尝试清理水产区污水池，仅完成30%；2024-05-16 22:00 劝阻熟食区直排未成功', '10:00-12:00（日常）/每月5日（月度）', 'uuid-user-007', '2024-05-16 11:00:00', '连续3个月核查污水处置不达标，整改措施未落实', '8', '12', '1. 48小时内完成熟食区污水过滤设备安装，禁止直排；2. 72小时内彻底清理水产区污水池淤泥；3. 将污水清理频次提升至每2小时1次；4. 更换老旧沉淀池，升级为二级处理', '2024-05-20 18:00:00', null, null, null, null, 'uuid-handle-003', 'uuid-check-004', 'uuid-task-type-005', 'admin', 'admin', '\0');
INSERT INTO `market` VALUES ('6', 'uuid-market-006', '苏州南环桥农副产品批发市场', '苏州市吴中区东方大道1688号', '1006', '1600', 'uuid-user-002', 'uuid-op-status-001', '98.50', '99.00', '98.00', '0', '每15分钟1次（交易区）/每30分钟1次（非交易区）', '[\"04:00-08:00(早高峰)\", \"08:00-19:00(平峰)\", \"19:00-24:00(晚高峰)\"]', '交易区、停车场、公厕、污水区、冷链区', '[\"uuid-user-001\", \"uuid-user-002\"]', '交易区地面无垃圾、无积水；公厕每15分钟巡检1次；污水区每1.5小时清理1次', '[\"uuid-garbage-001\", \"uuid-garbage-002\", \"uuid-garbage-003\", \"uuid-garbage-004\"]', '480', '每30分钟1次（交易区）/每1小时1次（非交易区）', '04:00-24:00（轮班）', 'uuid-vehicle-006', '蔬菜区、肉类区、水产区', '三级沉淀池+消毒处理+市政管网排放', '每1.5小时1次（交易时段）/每日1次（非交易时段）', '', '2024-05-20 04:00:00', '2024-05-20 05:30:00', '2024-05-20 04:00 完成全市场污水清理，各区域水质均达标；2024-05-19 23:00 完成污水管道巡检，无堵塞', '08:00-10:00（日常）/每月6日（月度）', 'uuid-user-006', '2024-05-19 09:00:00', '', '20', '0', '', null, null, null, null, null, 'uuid-handle-001', 'uuid-check-001', 'uuid-task-type-001', 'admin', 'admin', '\0');
INSERT INTO `market` VALUES ('7', 'uuid-market-007', '长沙马王堆蔬菜批发市场', '长沙市芙蓉区远大一路1号', '1007', '1000', 'uuid-user-002', 'uuid-op-status-001', '89.00', '92.00', '88.00', '2', '每20分钟1次（交易区）/每40分钟1次（非交易区）', '[\"05:00-09:00(早高峰)\", \"09:00-18:00(平峰)\", \"18:00-22:00(晚高峰)\"]', '交易区、停车场、公厕、污水区、配送区', '[\"uuid-user-001\", \"uuid-user-002\"]', '交易区无明显垃圾堆积；公厕每30分钟巡检1次；污水区每2小时清理1次', '[\"uuid-garbage-001\", \"uuid-garbage-002\", \"uuid-garbage-003\", \"uuid-garbage-005\"]', '320', '每40分钟1次（交易区）/每1.5小时1次（非交易区）', '05:00-22:00（轮班）', 'uuid-vehicle-007', '蔬菜区、水果区、干货区', '二级沉淀池+市政管网排放', '每2小时1次（交易时段）/每日1次（非交易时段）', '干货区污水管道轻微渗漏，未及时修复；水果区垃圾桶数量不足，部分垃圾落地', '2024-05-19 05:00:00', '2024-05-19 07:00:00', '2024-05-19 05:00 完成蔬菜区、水果区污水清理；2024-05-18 23:00 发现干货区管道渗漏，暂未修复', '09:00-11:00（日常）/每月7日（月度）', 'uuid-user-005', '2024-05-18 10:00:00', '上月核查发现水果区垃圾桶不足，新增10个后仍有缺口', '16', '4', '1. 24小时内修复干货区污水管道渗漏问题；2. 新增20个垃圾桶至水果区；3. 每日巡检垃圾桶使用状态，及时补充', '2024-05-21 18:00:00', null, null, null, null, 'uuid-handle-002', 'uuid-check-003', 'uuid-task-type-003', 'admin', 'admin', '\0');
INSERT INTO `market` VALUES ('8', 'uuid-market-008', '武汉白沙洲农副产品大市场', '武汉市洪山区青菱寺西路1号', '1008', '0', 'uuid-user-002', 'uuid-op-status-003', '0.00', '0.00', '0.00', '0', '无', '[]', '全区域', '[]', '暂停运营，无保洁标准', '[]', '0', '无', '无', null, '全区域', '暂停运营无处置', '无', '', null, null, '因整体升级改造暂停运营，无污水处置记录', '无', null, null, '', '0', '0', '', null, null, null, null, null, 'uuid-handle-004', 'uuid-check-005', 'uuid-task-type-005', 'admin', 'admin', '\0');

-- ----------------------------
-- Table structure for park
-- ----------------------------
DROP TABLE IF EXISTS `park`;
CREATE TABLE `park` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `park_id` varchar(64) DEFAULT NULL COMMENT '主键（UUID）',
  `name` varchar(128) DEFAULT NULL COMMENT '公园名称',
  `address` varchar(256) DEFAULT NULL COMMENT '公园地址',
  `area_code` varchar(64) DEFAULT NULL COMMENT '关联sys_area.area_code',
  `cleaning_frequency` varchar(32) DEFAULT NULL COMMENT '保洁频次',
  `green_maintenance_cycle` varchar(32) DEFAULT NULL COMMENT '绿化养护周期',
  `manager_id` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `operation_status_id` varchar(64) DEFAULT NULL COMMENT '关联sys_operation_status.id',
  `cleaning_rate` decimal(5,2) DEFAULT NULL COMMENT '保洁达标率',
  `green_survival_rate` decimal(5,2) DEFAULT NULL COMMENT '绿化存活率',
  `facility_rate` decimal(5,2) DEFAULT NULL COMMENT '设施完好率',
  `environment_rate` decimal(5,2) DEFAULT NULL COMMENT '环境达标率',
  `waste_transfer_complete_rate` decimal(5,2) DEFAULT NULL COMMENT '垃圾清运完成率',
  `cleaning_area` varchar(256) DEFAULT NULL COMMENT '保洁区域',
  `cleaning_standard` varchar(512) DEFAULT NULL COMMENT '保洁标准',
  `staff_ids` json DEFAULT NULL COMMENT '负责人员IDs，JSON',
  `green_type_ids` json DEFAULT NULL COMMENT '绿化品类IDs，JSON',
  `green_area` varchar(256) DEFAULT NULL COMMENT '养护区域',
  `green_maintenance_content` varchar(1024) DEFAULT NULL COMMENT '养护内容',
  `green_staff_ids` json DEFAULT NULL COMMENT '养护人员IDs，JSON',
  `waste_collection_points` int DEFAULT NULL COMMENT '垃圾收集点位',
  `waste_transfer_frequency` varchar(32) DEFAULT NULL COMMENT '垃圾清运频次',
  `waste_transfer_time` varchar(64) DEFAULT NULL COMMENT '清运时段',
  `vehicle_id` varchar(64) DEFAULT NULL COMMENT '关联sys_vehicle.id',
  `waste_volume` decimal(10,2) DEFAULT NULL COMMENT '垃圾清运量（单位：吨）',
  `facility_ids` json DEFAULT NULL COMMENT '设施类型IDs，JSON',
  `facility_location` varchar(256) DEFAULT NULL COMMENT '设施位置',
  `facility_damage_desc` varchar(1024) DEFAULT NULL COMMENT '损坏描述',
  `report_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `report_time` datetime DEFAULT NULL COMMENT '上报时间',
  `facility_photo_url` json DEFAULT NULL COMMENT '上报照片URL，JSON',
  `plan_status_id` varchar(64) DEFAULT NULL COMMENT '计划状态ID（关联sys_plan_status.id，对应sys_plan_status.name）',
  `task_type_id` varchar(64) DEFAULT NULL COMMENT '任务类型ID（关联sys_task_type.id，对应sys_task_type.name），支持钻取筛选同类型已完成任务',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公园表';

-- ----------------------------
-- Records of park
-- ----------------------------
INSERT INTO `park` VALUES ('1', 'uuid-park-001', '颐和园', '北京市海淀区新建宫门路19号', '1001', '每30分钟1次（核心区）/每1小时1次（外围区）', '乔木：季度/灌木：月度/草坪：周度', 'uuid-user-001', 'uuid-op-status-001', '99.00', '98.50', '97.00', '99.50', '99.00', '昆明湖周边、长廊、十七孔桥、东宫门等全园区', '地面无垃圾、水面无漂浮物、卫生间无异味、设施无积尘，核心区实时保洁', '[\"uuid-user-001\", \"uuid-user-002\", \"uuid-user-003\", \"uuid-user-004\"]', '[\"uuid-green-001\", \"uuid-green-002\", \"uuid-green-003\", \"uuid-green-006\"]', '昆明湖周边、万寿山、长廊两侧', '乔木修剪300株，灌木修剪500㎡，草坪修剪20000㎡，水生植物清理1000㎡，施肥5吨', '[\"uuid-user-001\", \"uuid-user-002\", \"uuid-user-003\", \"uuid-user-004\"]', '80', '每1小时1次（核心区）/每2小时1次（外围区）', '06:00-22:00（轮班）', 'uuid-vehicle-001', '50.50', '[\"uuid-facility-001\", \"uuid-facility-002\", \"uuid-facility-008\"]', '长廊东段、十七孔桥南侧', '长廊东段10张座椅漆面脱落，十七孔桥南侧5个垃圾桶轻微破损', 'uuid-user-002', '2024-05-18 09:00:00', '[\"https://xxx.com/park/1-1.jpg\", \"https://xxx.com/park/1-2.jpg\"]', 'uuid-plan-status-001', 'uuid-task-type-001', null, null, null, null, 'admin');
INSERT INTO `park` VALUES ('2', 'uuid-park-002', '世纪公园', '上海市浦东新区锦绣路1001号', '1002', '每20分钟1次（中心广场）/每40分钟1次（园区）', '乔木：季度/灌木：月度/草坪：周度/花卉：每日', 'uuid-user-001', 'uuid-op-status-002', '85.00', '88.00', '80.00', '86.00', '85.00', '中心广场、镜天湖、樱花岛、森林区', '地面垃圾≤5处/100㎡，水面漂浮物≤3处/100㎡，卫生间每30分钟清洁1次', '[\"uuid-user-002\", \"uuid-user-003\", \"uuid-user-004\", \"uuid-user-005\"]', '[\"uuid-green-001\", \"uuid-green-002\", \"uuid-green-003\", \"uuid-green-004\"]', '中心广场、镜天湖周边、樱花岛', '乔木修剪200株（病虫害10株），灌木修剪800㎡，草坪修剪15000㎡，花卉更换5000株', '[\"uuid-user-001\", \"uuid-user-002\", \"uuid-user-003\", \"uuid-user-004\"]', '60', '每40分钟1次（中心区）/每1小时1次（园区）', '05:00-23:00（轮班）', 'uuid-vehicle-002', '45.80', '[\"uuid-facility-001\", \"uuid-facility-003\", \"uuid-facility-009\"]', '镜天湖码头、樱花岛步道', '镜天湖码头10个护栏松动，樱花岛步道20米木栈道破损，5个休闲座椅断裂', 'uuid-user-003', '2024-05-19 10:30:00', '[\"https://xxx.com/park/2-1.jpg\", \"https://xxx.com/park/2-2.jpg\"]', 'uuid-plan-status-002', 'uuid-task-type-003', null, null, null, null, 'admin');
INSERT INTO `park` VALUES ('3', 'uuid-park-003', '西湖公园', '杭州市西湖区西湖风景区', '1003', '每15分钟1次（核心景区）/每30分钟1次（外围）', '乔木：季度/灌木：月度/草坪：周度/水生植物：月度', 'uuid-user-001', 'uuid-op-status-001', '99.50', '99.00', '98.50', '99.00', '99.50', '苏堤、白堤、三潭印月、曲院风荷', '核心景区无可见垃圾，水面无漂浮物，设施每日巡检，保洁人员实时在岗', '[\"uuid-user-003\", \"uuid-user-004\", \"uuid-user-005\", \"uuid-user-006\"]', '[\"uuid-green-001\", \"uuid-green-002\", \"uuid-green-003\", \"uuid-green-006\"]', '苏堤两侧、白堤、西湖水面', '乔木修剪500株，灌木修剪1000㎡，草坪修剪30000㎡，水生植物清理2000㎡，施肥10吨', '[\"uuid-user-001\", \"uuid-user-002\", \"uuid-user-003\", \"uuid-user-004\"]', '100', '每30分钟1次（核心区）/每1小时1次（外围）', '04:00-24:00（轮班）', 'uuid-vehicle-003', '80.20', '[\"uuid-facility-001\", \"uuid-facility-002\", \"uuid-facility-008\"]', '苏堤南段、三潭印月码头', '苏堤南段5个路灯故障，三潭印月码头2个垃圾桶满溢未及时清运', 'uuid-user-004', '2024-05-20 08:15:00', '[\"https://xxx.com/park/3-1.jpg\", \"https://xxx.com/park/3-2.jpg\"]', 'uuid-plan-status-001', 'uuid-task-type-001', null, null, null, null, 'admin');
INSERT INTO `park` VALUES ('4', 'uuid-park-004', '越秀公园', '广州市越秀区解放北路960号', '1004', '每25分钟1次（核心区）/每50分钟1次（外围）', '乔木：季度/灌木：月度/草坪：周度/花卉：每日', 'uuid-user-001', 'uuid-op-status-001', '98.00', '97.50', '96.00', '98.50', '98.00', '五羊石像、镇海楼、北秀湖、南秀花苑', '地面垃圾≤3处/100㎡，设施无破损，绿化无枯萎，卫生间无异味', '[\"uuid-user-001\", \"uuid-user-002\", \"uuid-user-003\", \"uuid-user-004\"]', '[\"uuid-green-001\", \"uuid-green-002\", \"uuid-green-003\", \"uuid-green-004\"]', '五羊石像周边、镇海楼、北秀湖', '乔木修剪150株，灌木修剪600㎡，草坪修剪10000㎡，花卉更换3000株，施肥6吨', '[\"uuid-user-001\", \"uuid-user-002\", \"uuid-user-003\", \"uuid-user-004\"]', '70', '每50分钟1次（核心区）/每2小时1次（外围）', '06:00-22:00（轮班）', 'uuid-vehicle-004', '35.60', '[\"uuid-facility-001\", \"uuid-facility-003\", \"uuid-facility-007\"]', '北秀湖步道、南秀花苑', '北秀湖步道10米护栏锈蚀，南秀花苑5个休闲座椅螺丝松动', 'uuid-user-005', '2024-05-21 09:30:00', '[\"https://xxx.com/park/4-1.jpg\", \"https://xxx.com/park/4-2.jpg\"]', 'uuid-plan-status-001', 'uuid-task-type-001', null, null, null, null, 'admin');
INSERT INTO `park` VALUES ('5', 'uuid-park-005', '武侯祠博物馆公园', '成都市武侯区武侯祠大街231号', '1005', '每20分钟1次（核心区）/每40分钟1次（外围）', '乔木：季度/灌木：月度/草坪：周度/竹类：半年', 'uuid-user-001', 'uuid-op-status-002', '80.00', '85.00', '82.00', '83.00', '82.00', '武侯祠主体建筑周边、锦里、荷塘区', '核心区每20分钟巡检1次，外围每40分钟1次，垃圾清运不超过30分钟/次', '[\"uuid-user-001\", \"uuid-user-002\", \"uuid-user-003\", \"uuid-user-004\"]', '[\"uuid-green-001\", \"uuid-green-002\", \"uuid-green-005\", \"uuid-green-008\"]', '武侯祠周边、锦里、荷塘区', '乔木修剪80株（病虫害20株），灌木修剪400㎡，竹类修剪500㎡，地被植物补植1000㎡', '[\"uuid-user-001\", \"uuid-user-002\", \"uuid-user-003\", \"uuid-user-004\"]', '50', '每40分钟1次（核心区）/每1.5小时1次（外围）', '07:00-21:00（轮班）', 'uuid-vehicle-005', '25.80', '[\"uuid-facility-001\", \"uuid-facility-004\", \"uuid-facility-009\"]', '锦里古街、荷塘区', '锦里古街15个灯笼破损，荷塘区5个木桥护栏松动，10个垃圾桶破损', 'uuid-user-006', '2024-05-17 14:00:00', '[\"https://xxx.com/park/5-1.jpg\", \"https://xxx.com/park/5-2.jpg\"]', 'uuid-plan-status-002', 'uuid-task-type-003', null, null, null, null, 'admin');
INSERT INTO `park` VALUES ('6', 'uuid-park-006', '拙政园', '苏州市姑苏区东北街178号', '1006', '每15分钟1次（核心景区）/每30分钟1次（外围）', '乔木：季度/灌木：月度/草坪：周度/水生植物：月度', 'uuid-user-001', 'uuid-op-status-001', '99.00', '98.50', '99.00', '99.00', '99.00', '远香堂、香洲、荷风四面亭、卅六鸳鸯馆', '世界文化遗产标准，无可见垃圾，设施每日维护，绿化实时养护', '[\"uuid-user-001\", \"uuid-user-002\", \"uuid-user-003\", \"uuid-user-004\"]', '[\"uuid-green-001\", \"uuid-green-002\", \"uuid-green-006\", \"uuid-green-007\"]', '全园景观区、亭台楼阁周边、荷花池', '乔木修剪100株，灌木修剪300㎡，水生植物清理800㎡，藤本植物牵引500米', '[\"uuid-user-001\", \"uuid-user-002\", \"uuid-user-003\", \"uuid-user-004\"]', '40', '每30分钟1次（核心区）/每1小时1次（外围）', '07:00-19:00（轮班）', 'uuid-vehicle-006', '15.50', '[\"uuid-facility-001\", \"uuid-facility-002\", \"uuid-facility-008\"]', '香洲、荷风四面亭', '香洲2个木柱轻微开裂，荷风四面亭1个座椅漆面脱落', 'uuid-user-007', '2024-05-20 11:00:00', '[\"https://xxx.com/park/6-1.jpg\", \"https://xxx.com/park/6-2.jpg\"]', 'uuid-plan-status-001', 'uuid-task-type-001', null, null, null, null, 'admin');
INSERT INTO `park` VALUES ('7', 'uuid-park-007', '湘江风光带公园', '长沙市岳麓区湘江中路一段', '1007', '每20分钟1次（江滩区）/每40分钟1次（堤岸区）', '乔木：季度/灌木：月度/草坪：周度/花卉：每日', 'uuid-user-001', 'uuid-op-status-001', '97.00', '96.50', '95.00', '98.00', '97.50', '湘江江滩、风帆广场、渔人码头、滨江步道', '江滩区无白色垃圾，堤岸区无积尘，水面无漂浮物，设施无破损', '[\"uuid-user-007\", \"uuid-user-008\", \"uuid-user-009\", \"uuid-user-010\"]', '[\"uuid-green-001\", \"uuid-green-002\", \"uuid-green-003\", \"uuid-green-004\"]', '江滩区、风帆广场、滨江步道', '乔木修剪200株，灌木修剪800㎡，草坪修剪20000㎡，花卉更换4000株，施肥8吨', '[\"uuid-user-001\", \"uuid-user-002\", \"uuid-user-003\", \"uuid-user-004\"]', '90', '每40分钟1次（江滩区）/每1小时1次（堤岸区）', '05:00-23:00（轮班）', 'uuid-vehicle-007', '60.80', '[\"uuid-facility-001\", \"uuid-facility-003\", \"uuid-facility-009\"]', '风帆广场、渔人码头', '风帆广场8个健身器材螺丝松动，渔人码头5个垃圾桶满溢', 'uuid-user-008', '2024-05-19 15:00:00', '[\"https://xxx.com/park/7-1.jpg\", \"https://xxx.com/park/7-2.jpg\"]', 'uuid-plan-status-001', 'uuid-task-type-001', null, null, null, null, 'admin');
INSERT INTO `park` VALUES ('8', 'uuid-park-008', '武汉市中央公园', '武汉市江汉区常青路1号', '1008', '无', '无', 'uuid-user-001', 'uuid-op-status-003', '0.00', '75.00', '60.00', '0.00', '0.00', '全园区', '暂停开放期间无保洁要求', '[]', '[\"uuid-green-001\", \"uuid-green-002\", \"uuid-green-003\"]', '全园区', '暂停养护', '[]', '30', '无', '无', null, '0.00', '[\"uuid-facility-001\", \"uuid-facility-002\", \"uuid-facility-003\"]', '全园区', '全园区50%垃圾桶破损，30%路灯故障，20%休闲座椅断裂，需整体维修', 'uuid-user-009', '2024-05-21 10:00:00', '[\"https://xxx.com/park/8-1.jpg\", \"https://xxx.com/park/8-2.jpg\"]', 'uuid-plan-status-003', 'uuid-task-type-009', null, null, null, null, 'admin');

-- ----------------------------
-- Table structure for point
-- ----------------------------
DROP TABLE IF EXISTS `point`;
CREATE TABLE `point` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `point_id` varchar(64) DEFAULT NULL COMMENT '点位主键（UUID）',
  `point_name` varchar(128) DEFAULT NULL COMMENT '点位名称',
  `area_code` varchar(64) DEFAULT NULL COMMENT '关联sys_area.area_code',
  `point_address` varchar(256) DEFAULT NULL COMMENT '点位地址',
  `longitude` decimal(10,6) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(10,6) DEFAULT NULL COMMENT '纬度',
  `status` varchar(16) DEFAULT NULL COMMENT '状态：启用/停用',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='点位表';

-- ----------------------------
-- Records of point
-- ----------------------------
INSERT INTO `point` VALUES ('1', 'uuid-point-001', '北京朝阳小区垃圾站', '1001', '北京市朝阳区XX街道XX路1号', '116.481028', '39.921983', '启用', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `point` VALUES ('2', 'uuid-point-002', '上海静安写字楼垃圾点', '1002', '上海市静安区XX街道XX路2号', '121.473701', '31.230416', '启用', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `point` VALUES ('3', 'uuid-point-003', '广州天河社区回收站', '1003', '广州市天河区XX街道XX路3号', '113.328593', '23.147268', '启用', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `point` VALUES ('4', 'uuid-point-004', '深圳南山产业园垃圾点', '1004', '深圳市南山区XX街道XX路4号', '113.940019', '22.544801', '启用', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `point` VALUES ('5', 'uuid-point-005', '杭州西湖小区垃圾站', '1005', '杭州市西湖区XX街道XX路5号', '120.153576', '30.287459', '停用', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `point` VALUES ('6', 'uuid-point-006', '南京鼓楼商业体垃圾点', '1006', '南京市鼓楼区XX街道XX路6号', '118.778074', '32.047235', '启用', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `point` VALUES ('7', 'uuid-point-007', '成都武侯小区回收站', '1007', '成都市武侯区XX街道XX路7号', '104.065735', '30.659462', '启用', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `point` VALUES ('8', 'uuid-point-008', '北京海淀学校垃圾点', '1001', '北京市海淀区XX街道XX路8号', '116.307589', '39.995984', '启用', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');

-- ----------------------------
-- Table structure for public_institution
-- ----------------------------
DROP TABLE IF EXISTS `public_institution`;
CREATE TABLE `public_institution` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `institution_id` varchar(64) DEFAULT NULL COMMENT '机构主键（UUID）',
  `name` varchar(128) DEFAULT NULL COMMENT '机构名称',
  `institution_type_id` varchar(64) DEFAULT NULL COMMENT '关联sys_institution_type.id',
  `address` varchar(256) DEFAULT NULL COMMENT '机构地址',
  `area_code` varchar(64) DEFAULT NULL COMMENT '关联sys_area.area_code',
  `manager_id` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `operation_status_id` varchar(64) DEFAULT NULL COMMENT '关联sys_operation_status.id',
  `cleaning_rate` decimal(5,2) DEFAULT NULL COMMENT '保洁达标率',
  `problem_rate` decimal(5,2) DEFAULT NULL COMMENT '问题办结率',
  `waste_volume` decimal(10,2) DEFAULT NULL COMMENT '垃圾清运量（单位：吨）',
  `inspection_pass_rate` decimal(5,2) DEFAULT NULL COMMENT '核查通过率',
  `cleaning_standard` varchar(512) DEFAULT NULL COMMENT '保洁标准',
  `cleaning_frequency` varchar(32) DEFAULT NULL COMMENT '保洁频次',
  `cleaning_time` varchar(64) DEFAULT NULL COMMENT '保洁时段',
  `cleaner_ids` json DEFAULT NULL COMMENT '保洁人员IDs，JSON',
  `responsibility_area` varchar(256) DEFAULT NULL COMMENT '责任区域',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公共机构表';

-- ----------------------------
-- Records of public_institution
-- ----------------------------
INSERT INTO `public_institution` VALUES ('1', 'uuid-inst-001', '北京市第一中学', 'uuid-inst-type-001', '北京市东城区东单北大街3号', '1001', 'uuid-user-001', 'uuid-op-status-001', '98.50', '99.00', '15.80', '98.00', '地面无垃圾、卫生间无异味、教室每日清扫2次', '每日3次（早/中/晚）', '06:00-07:00,12:00-13:00,18:00-19:00', '[\"uuid-cleaner-001\", \"uuid-cleaner-002\", \"uuid-cleaner-003\"]', '东城区东单街道1-5片区', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58');
INSERT INTO `public_institution` VALUES ('2', 'uuid-inst-002', '上海市人民医院', 'uuid-inst-type-002', '上海市黄浦区南京西路128号', '1002', 'uuid-user-002', 'uuid-op-status-002', '85.00', '88.00', '35.20', '85.00', '病房每日消毒3次、走廊每2小时清扫1次、医疗垃圾分类存放', '每2小时1次（全天）', '00:00-24:00（轮班）', '[\"uuid-cleaner-004\", \"uuid-cleaner-005\", \"uuid-cleaner-006\", \"uuid-cleaner-007\"]', '黄浦区南京西路街道全部片区', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58');
INSERT INTO `public_institution` VALUES ('3', 'uuid-inst-003', '广州市人民政府大楼', 'uuid-inst-type-003', '广州市天河区珠江新城华利路61号', '1003', 'uuid-user-003', 'uuid-op-status-001', '99.00', '100.00', '8.50', '100.00', '办公区每日清扫2次、会议室用后即清、公共区域实时保洁', '每日4次（早/午/下午/晚）', '07:00-08:00,12:00-13:00,17:00-18:00,21:00-22:00', '[\"uuid-cleaner-008\", \"uuid-cleaner-009\", \"uuid-cleaner-010\"]', '天河区冼村街道核心片区', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58');
INSERT INTO `public_institution` VALUES ('4', 'uuid-inst-004', '杭州市中央公园', 'uuid-inst-type-004', '杭州市西湖区天目山路18号', '1004', 'uuid-user-004', 'uuid-op-status-001', '95.00', '95.00', '45.60', '96.00', '园区道路每1小时清扫1次、公厕实时保洁、垃圾桶满溢即清', '每小时1次（06:00-22:00）', '06:00-22:00（轮班）', '[\"uuid-cleaner-011\", \"uuid-cleaner-012\", \"uuid-cleaner-013\", \"uuid-cleaner-014\", \"uuid-cleaner-015\"]', '西湖区灵隐街道全部片区', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58');
INSERT INTO `public_institution` VALUES ('5', 'uuid-inst-005', '南京市图书馆', 'uuid-inst-type-005', '南京市玄武区长江路262号', '1005', 'uuid-user-005', 'uuid-op-status-001', '97.50', '98.00', '6.30', '99.00', '阅览区每日清扫2次、书架每周除尘1次、卫生间每2小时清洁1次', '每日2次（开馆前/闭馆后）', '08:00-09:00,21:00-22:00', '[\"uuid-cleaner-016\", \"uuid-cleaner-017\"]', '玄武区梅园新村街道2-8片区', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58');
INSERT INTO `public_institution` VALUES ('6', 'uuid-inst-006', '成都市体育馆', 'uuid-inst-type-006', '成都市锦江区人民中路一段11号', '1006', 'uuid-user-006', 'uuid-op-status-002', '75.00', '80.00', '12.80', '80.00', '赛场用后即清、观众席每场清扫1次、卫生间实时保洁', '赛事期间实时/非赛事每日2次', '赛事期间随场/非赛事09:00-10:00,18:00-19:00', '[\"uuid-cleaner-018\", \"uuid-cleaner-019\", \"uuid-cleaner-020\"]', '锦江区盐市口街道全部片区', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58');
INSERT INTO `public_institution` VALUES ('7', 'uuid-inst-007', '西安市博物馆', 'uuid-inst-type-007', '西安市碑林区友谊西路72号', '1007', 'uuid-user-007', 'uuid-op-status-001', '99.50', '100.00', '4.20', '100.00', '展厅每日清扫1次（闭馆后）、文物展柜每周除尘1次、公共区域实时保洁', '每日1次（闭馆后）+实时巡检', '21:00-23:00（集中清扫）+09:00-18:00（实时巡检）', '[\"uuid-cleaner-021\", \"uuid-cleaner-022\", \"uuid-cleaner-023\"]', '碑林区南院门街道1-6片区', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58');
INSERT INTO `public_institution` VALUES ('8', 'uuid-inst-008', '武汉市汽车客运总站', 'uuid-inst-type-008', '武汉市江汉区发展大道170号', '1008', 'uuid-user-008', 'uuid-op-status-002', '80.00', '85.00', '68.90', '88.00', '候车厅每30分钟清扫1次、站台实时保洁、卫生间每15分钟清洁1次', '每30分钟1次（全天）', '00:00-24:00（轮班）', '[\"uuid-cleaner-024\", \"uuid-cleaner-025\", \"uuid-cleaner-026\", \"uuid-cleaner-027\", \"uuid-cleaner-028\"]', '江汉区唐家墩街道全部片区', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58');

-- ----------------------------
-- Table structure for public_institution_inspection
-- ----------------------------
DROP TABLE IF EXISTS `public_institution_inspection`;
CREATE TABLE `public_institution_inspection` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `inspection_id` varchar(64) DEFAULT NULL COMMENT '核查主键（UUID）',
  `institution_id` varchar(64) DEFAULT NULL COMMENT '关联public_institution.institution_id',
  `task_id` varchar(64) DEFAULT NULL COMMENT '关联task.task_id',
  `task_type_id` varchar(64) DEFAULT NULL COMMENT '关联sys_task_type.id',
  `report_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `report_time` datetime DEFAULT NULL COMMENT '上报时间',
  `report_result` varchar(512) DEFAULT NULL COMMENT '上报结果',
  `inspection_status` varchar(16) DEFAULT NULL COMMENT '核查状态：待核查/达标/不达标',
  `inspect_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `inspection_time` datetime DEFAULT NULL COMMENT '核查时间',
  `reform_require` varchar(512) DEFAULT NULL COMMENT '整改要求',
  `inspection_photo` json DEFAULT NULL COMMENT '核查照片URL，JSON',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公共机构核查表';

-- ----------------------------
-- Records of public_institution_inspection
-- ----------------------------
INSERT INTO `public_institution_inspection` VALUES ('1', 'uuid-inspection-001', 'uuid-inst-001', 'uuid-task-001', 'uuid-task-type-001', 'uuid-user-001', '2024-05-19 08:00:00', '经核查，学校保洁达标率98.5%，问题办结率99%，垃圾清运及时，符合保洁标准', '达标', 'uuid-user-009', '2024-05-19 09:30:00', '', '[\"https://xxx.com/inspection/1-1.jpg\", \"https://xxx.com/inspection/1-2.jpg\"]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58');
INSERT INTO `public_institution_inspection` VALUES ('2', 'uuid-inspection-002', 'uuid-inst-002', 'uuid-task-002', 'uuid-task-type-002', 'uuid-user-002', '2024-05-18 09:00:00', '经核查，医院保洁达标率85%，存在医疗垃圾堆积问题，问题办结率88%，未达到专项核查标准', '不达标', 'uuid-user-010', '2024-05-18 10:45:00', '1. 立即清运堆积医疗垃圾；2. 增加医疗垃圾清运频次至每2小时1次；3. 3日内完成整改并提交复查申请', '[\"https://xxx.com/inspection/2-1.jpg\", \"https://xxx.com/inspection/2-2.jpg\"]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58');
INSERT INTO `public_institution_inspection` VALUES ('3', 'uuid-inspection-003', 'uuid-inst-003', 'uuid-task-003', 'uuid-task-type-001', 'uuid-user-003', '2024-05-20 08:30:00', '经核查，市政府大楼保洁达标率99%，问题办结率100%，所有保洁标准均达标', '达标', 'uuid-user-011', '2024-05-20 10:00:00', '', '[\"https://xxx.com/inspection/3-1.jpg\", \"https://xxx.com/inspection/3-2.jpg\"]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58');
INSERT INTO `public_institution_inspection` VALUES ('4', 'uuid-inspection-004', 'uuid-inst-004', 'uuid-task-004', 'uuid-task-type-002', 'uuid-user-004', '2024-05-17 10:00:00', '经核查，中央公园保洁达标率95%，垃圾清运及时，设施完好，符合专项核查标准', '达标', 'uuid-user-012', '2024-05-17 11:30:00', '', '[\"https://xxx.com/inspection/4-1.jpg\", \"https://xxx.com/inspection/4-2.jpg\"]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58');
INSERT INTO `public_institution_inspection` VALUES ('5', 'uuid-inspection-005', 'uuid-inst-006', 'uuid-task-005', 'uuid-task-type-003', 'uuid-user-005', '2024-05-20 14:00:00', '体育馆已提交整改申请，申请对保洁不达标、设施损坏问题进行复查', '待核查', null, null, '要求核查人员1日内完成现场复查，重点检查设施维修情况和保洁达标率', '[\"https://xxx.com/inspection/5-1.jpg\"]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58');
INSERT INTO `public_institution_inspection` VALUES ('6', 'uuid-inspection-006', 'uuid-inst-007', 'uuid-task-006', 'uuid-task-type-001', 'uuid-user-006', '2024-05-19 13:00:00', '经核查，博物馆保洁达标率99.5%，问题办结率100%，所有保洁标准均达标，文物区域保洁符合特殊要求', '达标', 'uuid-user-013', '2024-05-19 14:30:00', '', '[\"https://xxx.com/inspection/6-1.jpg\", \"https://xxx.com/inspection/6-2.jpg\"]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58');
INSERT INTO `public_institution_inspection` VALUES ('7', 'uuid-inspection-007', 'uuid-inst-008', 'uuid-task-007', 'uuid-task-type-002', 'uuid-user-007', '2024-05-18 14:00:00', '经核查，汽车总站保洁达标率80%，存在保洁不达标、垃圾堆积问题，核查通过率88%，未达到专项核查标准', '不达标', 'uuid-user-014', '2024-05-18 15:30:00', '1. 增派保洁人员，确保候车厅每30分钟清扫1次；2. 增加垃圾桶清运频次至每1小时1次；3. 2日内完成整改并提交复查申请', '[\"https://xxx.com/inspection/7-1.jpg\", \"https://xxx.com/inspection/7-2.jpg\"]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58');
INSERT INTO `public_institution_inspection` VALUES ('8', 'uuid-inspection-008', 'uuid-inst-005', 'uuid-task-008', 'uuid-task-type-003', 'uuid-user-008', '2024-05-20 16:00:00', '经复查，图书馆照明灯已维修完毕，保洁达标率97.5%，所有整改要求均已完成', '达标', 'uuid-user-015', '2024-05-20 17:30:00', '', '[\"https://xxx.com/inspection/8-1.jpg\", \"https://xxx.com/inspection/8-2.jpg\"]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58');

-- ----------------------------
-- Table structure for public_institution_problem
-- ----------------------------
DROP TABLE IF EXISTS `public_institution_problem`;
CREATE TABLE `public_institution_problem` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `problem_id` varchar(64) DEFAULT NULL COMMENT '问题主键（UUID）',
  `institution_id` varchar(64) DEFAULT NULL COMMENT '关联public_institution.institution_id',
  `problem_type_id` varchar(64) DEFAULT NULL COMMENT '关联sys_problem_type.id',
  `location` varchar(256) DEFAULT NULL COMMENT '问题位置',
  `report_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `report_time` datetime DEFAULT NULL COMMENT '上报时间',
  `desc` varchar(1024) DEFAULT NULL COMMENT '问题描述',
  `dispatch_status` varchar(16) DEFAULT NULL COMMENT '派单状态：待派单/已派单/已处置',
  `dept_id` varchar(64) DEFAULT NULL COMMENT '关联sys_dept.id',
  `handle_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `is_timeout` varchar(8) DEFAULT NULL COMMENT '超时提醒：是/否',
  `handle_result` varchar(512) DEFAULT NULL COMMENT '处置结果',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公共机构问题表';

-- ----------------------------
-- Records of public_institution_problem
-- ----------------------------
INSERT INTO `public_institution_problem` VALUES ('1', 'uuid-problem-001', 'uuid-inst-002', 'uuid-problem-type-002', '住院部3楼走廊', 'uuid-user-009', '2024-05-18 10:20:00', '住院部3楼走廊医疗垃圾暂存点堆积过多，未及时清运，存在卫生隐患', '已处置', 'uuid-dept-001', 'uuid-user-002', '否', '已安排专人清运堆积垃圾，增加医疗垃圾清运频次至每2小时1次，现场已清理完毕', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58', '2026-02-12 16:20:58');
INSERT INTO `public_institution_problem` VALUES ('2', 'uuid-problem-002', 'uuid-inst-006', 'uuid-problem-type-003', '东看台卫生间', 'uuid-user-010', '2024-05-19 14:30:00', '东看台卫生间洗手池水龙头损坏，无法出水，影响使用', '已派单', 'uuid-dept-002', 'uuid-user-006', '否', '', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58', '2026-02-12 16:20:58');
INSERT INTO `public_institution_problem` VALUES ('3', 'uuid-problem-003', 'uuid-inst-008', 'uuid-problem-type-001', '候车大厅A区', 'uuid-user-011', '2024-05-17 08:15:00', '候车大厅A区地面有大量果皮纸屑，保洁人员未及时清扫，保洁达标率仅70%', '已派单', 'uuid-dept-003', 'uuid-user-008', '是', '已增派保洁人员清扫，目前正在整改中，预计1小时内完成', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58', '2026-02-12 16:20:58');
INSERT INTO `public_institution_problem` VALUES ('4', 'uuid-problem-004', 'uuid-inst-001', 'uuid-problem-type-004', '食堂后厨', 'uuid-user-012', '2024-05-20 09:10:00', '食堂后厨垃圾桶未及时清理，产生异味，影响师生就餐环境', '已处置', 'uuid-dept-001', 'uuid-user-001', '否', '已清理后厨垃圾桶，更换防臭垃圾袋，增加垃圾桶清理频次至每1小时1次，异味已消除', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58', '2026-02-12 16:20:58');
INSERT INTO `public_institution_problem` VALUES ('5', 'uuid-problem-005', 'uuid-inst-004', 'uuid-problem-type-005', '儿童游乐区', 'uuid-user-013', '2024-05-20 11:25:00', '儿童游乐区滑梯扶手松动，存在坠落风险，需立即维修', '待派单', null, null, '否', '', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58', '2026-02-12 16:20:58');
INSERT INTO `public_institution_problem` VALUES ('6', 'uuid-problem-006', 'uuid-inst-006', 'uuid-problem-type-001', '西观众席', 'uuid-user-014', '2024-05-20 15:40:00', '西观众席有大量饮料瓶和零食包装袋，赛后未及时清扫', '待派单', null, null, '否', '', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58', '2026-02-12 16:20:58');
INSERT INTO `public_institution_problem` VALUES ('7', 'uuid-problem-007', 'uuid-inst-008', 'uuid-problem-type-002', '出站口广场', 'uuid-user-015', '2024-05-19 07:30:00', '出站口广场垃圾桶满溢，垃圾堆积至地面，影响市容', '已处置', 'uuid-dept-003', 'uuid-user-008', '否', '已清运满溢垃圾桶，增加垃圾桶清运频次，现场已恢复整洁', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58', '2026-02-12 16:20:58');
INSERT INTO `public_institution_problem` VALUES ('8', 'uuid-problem-008', 'uuid-inst-005', 'uuid-problem-type-006', '借阅区3楼', 'uuid-user-010', '2024-05-20 13:15:00', '借阅区3楼照明灯故障，部分区域光线不足，影响读者借阅', '已派单', 'uuid-dept-004', 'uuid-user-005', '否', '已联系维修人员，预计今日17:00前完成维修', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58', '2026-02-24 14:32:47');

-- ----------------------------
-- Table structure for public_toilet
-- ----------------------------
DROP TABLE IF EXISTS `public_toilet`;
CREATE TABLE `public_toilet` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `toilet_id` varchar(64) DEFAULT NULL COMMENT '公厕主键（UUID）',
  `name` varchar(128) DEFAULT NULL COMMENT '公厕名称',
  `location` varchar(256) DEFAULT NULL COMMENT '公厕位置',
  `area_code` varchar(64) DEFAULT NULL COMMENT '关联sys_area.area_code',
  `open_hours` varchar(64) DEFAULT NULL COMMENT '开放时段',
  `stall_count` int DEFAULT NULL COMMENT '蹲位数量',
  `operation_status_id` varchar(64) DEFAULT NULL COMMENT '关联sys_operation_status.id',
  `manager_id` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `cleaning_rate` decimal(5,2) DEFAULT NULL COMMENT '保洁达标率',
  `complaint_rate` decimal(5,2) DEFAULT NULL COMMENT '投诉办结率',
  `warning_count` int DEFAULT NULL COMMENT '耗材库存预警数',
  `facility_rate` decimal(5,2) DEFAULT NULL COMMENT '设施完好率',
  `cleaning_frequency` varchar(32) DEFAULT NULL COMMENT '保洁频次',
  `cleaning_time` varchar(64) DEFAULT NULL COMMENT '保洁时段',
  `cleaning_content` varchar(512) DEFAULT NULL COMMENT '保洁内容',
  `cleaning_standard` varchar(512) DEFAULT NULL COMMENT '保洁标准',
  `cleaner_ids` json DEFAULT NULL COMMENT '保洁人员IDs，JSON',
  `consumable_stock` varchar(256) DEFAULT NULL COMMENT '耗材库存',
  `consumable_threshold` int DEFAULT NULL COMMENT '预警阈值',
  `consumable_gap` int DEFAULT NULL COMMENT '缺口数量',
  `last_supply_time` datetime DEFAULT NULL COMMENT '上次补充时间',
  `supply_cycle` varchar(32) DEFAULT NULL COMMENT '补充周期',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公厕表';

-- ----------------------------
-- Records of public_toilet
-- ----------------------------
INSERT INTO `public_toilet` VALUES ('1', 'uuid-toilet-001', '朝阳公园南门公厕', '北京市朝阳区朝阳公园南路1号', '1001', '06:00-22:00', '12', 'uuid-op-status-001', 'uuid-user-001', '98.50', '100.00', '1', '99.00', '每小时1次', '06:00-22:00', '地面清洁、便池冲刷、耗材补充', '地面无污渍、便池无异味、耗材充足', '[\"uuid-user-008\", \"uuid-user-009\"]', '卫生纸:50包,洗手液:20瓶,消毒液:15瓶', '10', '0', '2024-02-10 09:00:00', '每日', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `public_toilet` VALUES ('2', 'uuid-toilet-002', '南京路步行街公厕', '上海市黄浦区南京东路123号', '1002', '05:30-23:00', '18', 'uuid-op-status-001', 'uuid-user-002', '95.00', '95.00', '2', '95.00', '每30分钟1次', '05:30-23:00', '高频清洁、异味处理、耗材检查', '无明显异味、地面无积水、每小时巡检', '[\"uuid-user-010\", \"uuid-user-011\"]', '卫生纸:8包,洗手液:5瓶,消毒液:10瓶', '10', '2', '2024-02-08 10:00:00', '每日', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `public_toilet` VALUES ('3', 'uuid-toilet-003', '天河城购物中心公厕', '广州市天河区天河路208号', '1003', '10:00-22:00', '20', 'uuid-op-status-002', 'uuid-user-003', '90.00', '90.00', '0', '85.00', '每小时1次', '10:00-22:00', '常规清洁、设施巡检', '表面无污渍、设施可正常使用', '[\"uuid-user-012\", \"uuid-user-013\"]', '卫生纸:30包,洗手液:15瓶,消毒液:20瓶', '15', '0', '2024-02-11 14:00:00', '每2天', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `public_toilet` VALUES ('4', 'uuid-toilet-004', '南山科技园公厕', '深圳市南山区科技园南区', '1004', '07:00-21:00', '10', 'uuid-op-status-001', 'uuid-user-004', '85.00', '80.00', '0', '90.00', '每小时1次', '07:00-21:00', '地面清洁、异味处理', '基本无异味、地面无垃圾', '[\"uuid-user-014\", \"uuid-user-015\"]', '卫生纸:25包,洗手液:10瓶,消毒液:8瓶', '8', '0', '2024-02-09 08:00:00', '每日', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `public_toilet` VALUES ('5', 'uuid-toilet-005', '西湖断桥公厕', '杭州市西湖区断桥景区', '1005', '05:00-23:30', '15', 'uuid-op-status-001', 'uuid-user-005', '99.00', '98.00', '0', '98.00', '每20分钟1次', '05:00-23:30', '全区域清洁、耗材实时补充', '无任何卫生死角、耗材不间断供应', '[\"uuid-user-012\", \"uuid-user-005\", \"uuid-user-006\"]', '卫生纸:100包,洗手液:50瓶,消毒液:30瓶', '20', '0', '2024-02-12 07:00:00', '每日2次', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `public_toilet` VALUES ('6', 'uuid-toilet-006', '夫子庙景区公厕', '南京市秦淮区夫子庙步行街', '1006', '06:00-22:30', '14', 'uuid-op-status-002', 'uuid-user-006', '88.00', '92.00', '1', '75.00', '每小时1次', '06:00-22:30', '基础清洁、损坏设施标记', '地面清洁、损坏设施挂牌提醒', '[\"uuid-user-009\", \"uuid-user-010\"]', '卫生纸:15包,洗手液:8瓶,消毒液:12瓶', '10', '0', '2024-02-07 15:00:00', '每2天', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `public_toilet` VALUES ('7', 'uuid-toilet-007', '春熙路步行街公厕', '成都市锦江区春熙路街道', '1007', '05:30-23:00', '16', 'uuid-op-status-001', 'uuid-user-007', '92.00', '96.00', '3', '90.00', '每40分钟1次', '05:30-23:00', '清洁+耗材补充', '无污渍、耗材不低于阈值', '[\"uuid-user-011\", \"uuid-user-012\"]', '卫生纸:5包,洗手液:3瓶,消毒液:8瓶', '8', '3', '2024-02-05 10:00:00', '每日', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `public_toilet` VALUES ('8', 'uuid-toilet-008', '王府井大街公厕', '北京市东城区王府井大街', '1001', '暂停运营', '0', 'uuid-op-status-003', 'uuid-user-001', '0.00', '0.00', '0', '0.00', '暂停', '暂停', '无', '无', '[]', '无', '0', '0', null, '暂停', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');

-- ----------------------------
-- Table structure for public_toilet_complaint
-- ----------------------------
DROP TABLE IF EXISTS `public_toilet_complaint`;
CREATE TABLE `public_toilet_complaint` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `complaint_id` varchar(64) DEFAULT NULL COMMENT '投诉主键（UUID）',
  `toilet_id` varchar(64) DEFAULT NULL COMMENT '关联public_toilet.toilet_id',
  `complaint_type_id` varchar(64) DEFAULT NULL COMMENT '关联sys_complaint_type.id',
  `content` varchar(1024) DEFAULT NULL COMMENT '投诉内容',
  `complaint_name` varchar(64) DEFAULT NULL COMMENT '投诉人',
  `phone` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `complaint_time` datetime DEFAULT NULL COMMENT '投诉时间',
  `dispatch_status` varchar(16) DEFAULT NULL COMMENT '派单状态：待派单/已派单/已处置',
  `handler_id` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `is_timeout` varchar(8) DEFAULT NULL COMMENT '是否超时：是/否',
  `handle_measure` varchar(512) DEFAULT NULL COMMENT '处置措施',
  `handle_result` varchar(512) DEFAULT NULL COMMENT '处置结果',
  `reform_photo` varchar(1024) DEFAULT NULL COMMENT '整改照片URL',
  `feedback_content` varchar(1024) DEFAULT NULL COMMENT '反馈内容',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公厕投诉表';

-- ----------------------------
-- Records of public_toilet_complaint
-- ----------------------------
INSERT INTO `public_toilet_complaint` VALUES ('1', 'uuid-complaint-001', 'uuid-toilet-001', 'uuid-complaint-type-001', '男卫地面有积水，未及时清理', '张先生', '13800138000', '2024-02-10 10:30:00', '已处置', 'uuid-user-008', '否', '立即安排保洁清理积水，增加防滑垫', '积水已清理，防滑垫已放置，现场拍照确认', '[\"https://xxx.com/complaint/1-1.jpg\"]', '处理及时，满意', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `public_toilet_complaint` VALUES ('2', 'uuid-complaint-002', 'uuid-toilet-002', 'uuid-complaint-type-003', '女卫卫生纸用完，无人补充', '李女士', '13900139000', '2024-02-11 14:20:00', '已派单', 'uuid-user-010', '否', '紧急配送卫生纸，增加巡检频次', '', '', '', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `public_toilet_complaint` VALUES ('3', 'uuid-complaint-003', 'uuid-toilet-003', 'uuid-complaint-type-002', '冲水按钮损坏，无法冲水', '王先生', '13700137000', '2024-02-12 09:15:00', '待派单', null, '否', '', '', '', '', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `public_toilet_complaint` VALUES ('4', 'uuid-complaint-004', 'uuid-toilet-004', 'uuid-complaint-type-004', '卫生间异味严重，影响使用', '赵女士', '13600136000', '2024-02-09 16:40:00', '已处置', 'uuid-user-014', '是', '使用除臭剂，加强通风，增加清洁频次', '异味已消除，后续会每小时巡检', '[\"https://xxx.com/complaint/4-1.jpg\"]', '处理后异味消失，满意', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `public_toilet_complaint` VALUES ('5', 'uuid-complaint-005', 'uuid-toilet-005', 'uuid-complaint-type-005', '公示5点开放，但5点10分仍未开门', '孙先生', '13500135000', '2024-02-11 05:15:00', '已处置', 'uuid-user-013', '否', '对管理员批评教育，张贴整改通知', '已按规定时间开放，管理员已道歉', '[\"https://xxx.com/complaint/5-1.jpg\"]', '后续按时开放，接受整改', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-14 14:04:52');
INSERT INTO `public_toilet_complaint` VALUES ('6', 'uuid-complaint-006', 'uuid-toilet-006', 'uuid-complaint-type-002', '无障碍扶手松动，有安全隐患', '周女士', '13400134000', '2024-02-12 11:00:00', '已派单', 'uuid-user-012', '否', '安排维修人员加固扶手', '', '', '', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-14 14:04:54');
INSERT INTO `public_toilet_complaint` VALUES ('7', 'uuid-complaint-007', 'uuid-toilet-007', 'uuid-complaint-type-003', '洗手液空瓶未更换，连续3天', '吴先生', '13300133000', '2024-02-08 15:20:00', '已处置', 'uuid-user-011', '是', '紧急更换洗手液，追责保洁人员', '已更换洗手液，后续每日检查耗材', '[\"https://xxx.com/complaint/7-1.jpg\"]', '处理及时，希望后续保持', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-14 14:05:01');
INSERT INTO `public_toilet_complaint` VALUES ('8', 'uuid-complaint-008', 'uuid-toilet-008', 'uuid-complaint-type-008', '未提前通知暂停运营，导致不便', '郑女士', '13200132000', '2024-02-09 08:30:00', '已处置', 'uuid-user-001', '否', '在周边张贴暂停通知，设置临时指引', '已张贴通知，指引至附近公厕', '[\"https://xxx.com/complaint/8-1.jpg\"]', '有指引后方便多了，接受处理', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');

-- ----------------------------
-- Table structure for public_toilet_facility_repair
-- ----------------------------
DROP TABLE IF EXISTS `public_toilet_facility_repair`;
CREATE TABLE `public_toilet_facility_repair` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `repair_id` varchar(64) DEFAULT NULL COMMENT '维修主键（UUID）',
  `toilet_id` varchar(64) DEFAULT NULL COMMENT '关联public_toilet.toilet_id',
  `facility_id` varchar(64) DEFAULT NULL COMMENT '关联sys_facility.id',
  `damage_desc` varchar(512) DEFAULT NULL COMMENT '损坏情况',
  `report_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `report_time` datetime DEFAULT NULL COMMENT '上报时间',
  `photo_url` varchar(1024) DEFAULT NULL COMMENT '现场照片URL',
  `repair_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `repair_status` varchar(16) DEFAULT NULL COMMENT '维修状态：待维修/维修中/已完成/不合格',
  `expected_complete_time` datetime DEFAULT NULL COMMENT '预计完成时间',
  `accept_result` varchar(16) DEFAULT NULL COMMENT '验收结果：合格/不合格',
  `accept_opinion` varchar(512) DEFAULT NULL COMMENT '验收意见',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公厕设施维修表';

-- ----------------------------
-- Records of public_toilet_facility_repair
-- ----------------------------
INSERT INTO `public_toilet_facility_repair` VALUES ('1', 'uuid-repair-001', 'uuid-toilet-001', 'uuid-facility-001', '男卫3号便池冲水阀漏水', 'uuid-user-008', '2024-02-08 09:10:00', '[\"https://xxx.com/repair/1-1.jpg\"]', 'uuid-user-008', '已完成', '2024-02-08 12:00:00', '合格', '冲水阀更换完成，无漏水', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-14 14:25:29');
INSERT INTO `public_toilet_facility_repair` VALUES ('2', 'uuid-repair-002', 'uuid-toilet-002', 'uuid-facility-002', '女卫照明灯管损坏3根', 'uuid-user-010', '2024-02-12 08:30:00', '[\"https://xxx.com/repair/2-1.jpg\"]', 'uuid-user-010', '维修中', '2024-02-12 18:00:00', null, '', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-14 14:25:24');
INSERT INTO `public_toilet_facility_repair` VALUES ('3', 'uuid-repair-003', 'uuid-toilet-003', 'uuid-facility-003', '单间门锁无法反锁', 'uuid-user-012', '2024-02-11 14:00:00', '[\"https://xxx.com/repair/3-1.jpg\"]', null, '待维修', '2024-02-13 10:00:00', null, '', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `public_toilet_facility_repair` VALUES ('4', 'uuid-repair-004', 'uuid-toilet-004', 'uuid-facility-004', '洗手台水龙头松动', 'uuid-user-014', '2024-02-07 16:20:00', '[\"https://xxx.com/repair/4-1.jpg\"]', 'uuid-user-015', '已完成', '2024-02-07 18:00:00', '不合格', '加固不牢固，仍有松动', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-14 14:25:20');
INSERT INTO `public_toilet_facility_repair` VALUES ('5', 'uuid-repair-005', 'uuid-toilet-005', 'uuid-facility-005', '无障碍扶手脱落', 'uuid-user-015', '2024-02-09 11:30:00', '[\"https://xxx.com/repair/5-1.jpg\"]', 'uuid-user-005', '已完成', '2024-02-09 15:00:00', '合格', '扶手重新固定，牢固无松动', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-14 14:25:16');
INSERT INTO `public_toilet_facility_repair` VALUES ('6', 'uuid-repair-006', 'uuid-toilet-006', 'uuid-facility-006', '通风扇故障，无法排风', 'uuid-user-014', '2024-02-12 10:15:00', '[\"https://xxx.com/repair/6-1.jpg\"]', 'uuid-user-003', '维修中', '2024-02-12 19:00:00', null, '', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-14 14:25:12');
INSERT INTO `public_toilet_facility_repair` VALUES ('7', 'uuid-repair-007', 'uuid-toilet-007', 'uuid-facility-001', '多个便池冲水无力', 'uuid-user-008', '2024-02-10 13:40:00', '[\"https://xxx.com/repair/7-1.jpg\"]', null, '待维修', '2024-02-14 09:00:00', null, '', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-14 14:24:39');
INSERT INTO `public_toilet_facility_repair` VALUES ('8', 'uuid-repair-008', 'uuid-toilet-008', 'uuid-facility-007', '整体翻新维修（水管/地面/设施）', 'uuid-user-001', '2024-02-01 08:00:00', '[\"https://xxx.com/repair/8-1.jpg\"]', 'uuid-user-002', '已完成', '2024-02-08 18:00:00', '合格', '整体维修完成，符合运营标准', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-14 14:25:07');

-- ----------------------------
-- Table structure for river
-- ----------------------------
DROP TABLE IF EXISTS `river`;
CREATE TABLE `river` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `river_id` varchar(64) DEFAULT NULL COMMENT '主键（UUID）',
  `name` varchar(128) DEFAULT NULL COMMENT '河道名称',
  `responsibility_section` varchar(256) DEFAULT NULL COMMENT '责任河段',
  `area_code` varchar(64) DEFAULT NULL COMMENT '关联sys_area.area_code',
  `length` decimal(8,2) DEFAULT NULL COMMENT '河道长度，单位：公里',
  `manager_id` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `operation_status_id` varchar(64) DEFAULT NULL COMMENT '关联sys_operation_status.id',
  `cleaning_coverage` decimal(5,2) DEFAULT NULL COMMENT '保洁覆盖率',
  `water_quality_rate` decimal(5,2) DEFAULT NULL COMMENT '水质达标率',
  `waste_fishing_volume` decimal(8,2) DEFAULT NULL COMMENT '垃圾打捞总量（单位：吨）',
  `problem_complete_rate` decimal(5,2) DEFAULT NULL COMMENT '问题办结率',
  `cleaning_type_id` varchar(64) DEFAULT NULL COMMENT '关联sys_cleaning_type.id',
  `water_cleaning_frequency` varchar(32) DEFAULT NULL COMMENT '保洁频次',
  `cleaning_time` varchar(64) DEFAULT NULL COMMENT '保洁时段',
  `staff_ids` json DEFAULT NULL COMMENT '负责人员IDs，JSON',
  `tool_ids` json DEFAULT NULL COMMENT '保洁工具IDs，JSON',
  `waste_fishing_estimate` decimal(8,2) DEFAULT NULL COMMENT '垃圾打捞预估量（单位：吨）',
  `monitor_type_id` varchar(64) DEFAULT NULL COMMENT '关联sys_monitor_type.id',
  `water_quality_cycle` varchar(32) DEFAULT NULL COMMENT '监测周期',
  `monitor_indicators` json DEFAULT NULL COMMENT '监测指标，JSON',
  `monitor_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `plan_monitor_time` datetime DEFAULT NULL COMMENT '计划监测时间',
  `monitor_status_id` varchar(64) DEFAULT NULL COMMENT '关联sys_monitor_status.id',
  `last_monitor_time` datetime DEFAULT NULL COMMENT '上次监测时间',
  `next_monitor_remind_time` datetime DEFAULT NULL COMMENT '下次监测提醒时间',
  `monitor_data_qualified_rate` decimal(5,2) DEFAULT NULL COMMENT '监测数据达标率',
  `warning_count` int DEFAULT NULL COMMENT '预警次数',
  `problem_type_id` varchar(64) DEFAULT NULL COMMENT '关联sys_problem_type.id',
  `problem_location` varchar(256) DEFAULT NULL COMMENT '问题位置',
  `problem_desc` varchar(1024) DEFAULT NULL COMMENT '问题描述',
  `report_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `report_time` datetime DEFAULT NULL COMMENT '上报时间',
  `problem_media_url` json DEFAULT NULL COMMENT '现场照片/视频URL',
  `dept_id` varchar(64) DEFAULT NULL COMMENT '关联sys_dept.id',
  `handle_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `dispatch_time` datetime DEFAULT NULL COMMENT '派单时间',
  `handle_status_id` varchar(64) DEFAULT NULL COMMENT '关联sys_handle_status.id',
  `is_timeout` varchar(8) DEFAULT NULL COMMENT '超时提醒：是/否',
  `task_type_id` varchar(64) DEFAULT NULL COMMENT '关联sys_task_type.id（任务类型ID）',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='河道表';

-- ----------------------------
-- Records of river
-- ----------------------------
INSERT INTO `river` VALUES ('1', 'uuid-river-001', '京杭大运河', '通州区潞城镇至朝阳区八里桥（全长15.8公里）', '1001', '15.80', 'uuid-user-001', 'uuid-op-status-001', '99.00', '95.00', '120.50', '98.00', 'uuid-clean-type-003', '每2小时1次（水域）/每4小时1次（陆域）', '06:00-22:00（轮班）', '[\"uuid-user-001\"]', '[\"uuid-tool-001\", \"uuid-tool-002\", \"uuid-tool-003\", \"uuid-tool-004\"]', '125.00', 'uuid-monitor-type-007', '每日1次（常规）/每小时1次（预警期）', '[\"pH值\", \"溶解氧\", \"氨氮\", \"COD\", \"异味等级\", \"水生植物覆盖度\"]', 'uuid-user-001', '2024-05-22 08:00:00', 'uuid-monitor-status-002', '2024-05-21 09:00:00', '2024-05-22 07:00:00', '98.00', '0', '', '', '', '', null, '[]', 'uuid-dept-001', '', null, '', '否', 'uuid-task-type-001', '', '', '', '', 'admin', 'admin');
INSERT INTO `river` VALUES ('2', 'uuid-river-002', '黄浦江', '浦东新区陆家嘴至徐汇区滨江（全长12.5公里）', '1002', '12.50', 'uuid-user-002', 'uuid-op-status-002', '85.00', '75.00', '205.80', '80.00', 'uuid-clean-type-001', '每1小时1次（水域）/每3小时1次（陆域）', '05:00-23:00（轮班）', '[\"uuid-user-002\"]', '[\"uuid-tool-001\", \"uuid-tool-002\", \"uuid-tool-005\", \"uuid-tool-006\"]', '210.00', 'uuid-monitor-type-001', '每2小时1次（预警期）', '[\"pH值\", \"溶解氧\", \"氨氮\", \"COD\", \"总磷\", \"总氮\"]', 'uuid-user-001', '2024-05-22 10:00:00', 'uuid-monitor-status-003', '2024-05-21 10:00:00', '2024-05-22 09:00:00', '70.00', '3', 'uuid-problem-type-001', '陆家嘴段江面', '陆家嘴段江面发现油污漂浮，氨氮含量超标（1.5mg/L），超出Ⅴ类水标准，水生植物过度生长（覆盖度30%）', 'uuid-user-002', '2024-05-20 08:00:00', '[\"https://xxx.com/river/2-1.jpg\", \"https://xxx.com/river/2-2.mp4\"]', 'uuid-dept-002', 'uuid-user-002', '2024-05-20 08:30:00', 'uuid-handle-002', '否', 'uuid-task-type-002', '', '', '', '', 'admin', 'admin');
INSERT INTO `river` VALUES ('3', 'uuid-river-003', '珠江', '天河区猎德至海珠区琶洲（全长8.6公里）', '1003', '8.60', 'uuid-user-001', 'uuid-op-status-001', '98.00', '96.00', '95.20', '99.00', 'uuid-clean-type-003', '每1.5小时1次（水域）/每3小时1次（陆域）', '06:00-22:00（轮班）', '[\"uuid-user-003\"]', '[\"uuid-tool-001\", \"uuid-tool-003\", \"uuid-tool-004\", \"uuid-tool-007\"]', '98.00', 'uuid-monitor-type-007', '每日1次（常规）', '[\"pH值\", \"溶解氧\", \"氨氮\", \"COD\", \"异味等级\", \"水生植物覆盖度\"]', 'uuid-user-001', '2024-05-22 09:00:00', 'uuid-monitor-status-002', '2024-05-21 08:00:00', '2024-05-22 08:00:00', '99.00', '0', '', '', '', '', null, '[]', 'uuid-dept-003', '', null, '', '否', 'uuid-task-type-001', '', '', '', '', 'admin', 'admin');
INSERT INTO `river` VALUES ('4', 'uuid-river-004', '钱塘江', '上城区钱江新城至滨江区钱江世纪城（全长10.2公里）', '1004', '10.20', 'uuid-user-001', 'uuid-op-status-001', '90.00', '88.00', '150.30', '92.00', 'uuid-clean-type-001', '每2小时1次（水域）/每4小时1次（陆域）', '05:00-23:00（轮班）', '[\"uuid-user-004\"]', '[\"uuid-tool-001\", \"uuid-tool-002\", \"uuid-tool-005\", \"uuid-tool-008\"]', '155.00', 'uuid-monitor-type-001', '每6小时1次（常规）', '[\"pH值\", \"溶解氧\", \"氨氮\", \"COD\", \"总磷\"]', 'uuid-user-001', '2024-05-22 11:00:00', 'uuid-monitor-status-003', '2024-05-21 11:00:00', '2024-05-22 10:00:00', '85.00', '1', 'uuid-problem-type-002', '钱江世纪城段江面', '钱江世纪城段江面总磷含量轻微超标（0.4mg/L），水面漂浮塑料垃圾较多，保洁覆盖率90%未达95%标准', 'uuid-user-011', '2024-05-21 09:00:00', '[\"https://xxx.com/river/4-1.jpg\", \"https://xxx.com/river/4-2.jpg\"]', 'uuid-dept-004', 'uuid-user-003', '2024-05-21 09:30:00', 'uuid-handle-003', '否', 'uuid-task-type-003', '', '', '', '', 'admin', 'admin');
INSERT INTO `river` VALUES ('5', 'uuid-river-005', '锦江', '锦江区合江亭至武侯区九眼桥（全长6.8公里）', '1005', '6.80', 'uuid-user-002', 'uuid-op-status-002', '75.00', '70.00', '85.60', '75.00', 'uuid-clean-type-002', '每3小时1次（水域）/每5小时1次（陆域）', '06:00-22:00（轮班）', '[\"uuid-user-005\"]', '[\"uuid-tool-002\", \"uuid-tool-004\", \"uuid-tool-006\", \"uuid-tool-009\"]', '90.00', 'uuid-monitor-type-002', '每4小时1次（预警期）', '[\"异味等级\", \"硫化氢\", \"氨味\", \"挥发性有机物\"]', 'uuid-user-007', '2024-05-22 12:00:00', 'uuid-monitor-status-003', '2024-05-20 12:00:00', '2024-05-22 11:00:00', '70.00', '2', 'uuid-problem-type-003', '九眼桥段两岸', '九眼桥段两岸堤岸垃圾堆积，水体异味等级3级（超标），油污未清理，保洁人员不足导致覆盖率仅75%', 'uuid-user-010', '2024-05-19 10:00:00', '[\"https://xxx.com/river/5-1.jpg\", \"https://xxx.com/river/5-2.mp4\"]', 'uuid-dept-005', 'uuid-user-005', '2024-05-19 10:30:00', 'uuid-handle-005', '是', 'uuid-task-type-002', '', '', '', '', 'admin', 'admin');
INSERT INTO `river` VALUES ('6', 'uuid-river-006', '金鸡湖', '工业园区金鸡湖全段（周长14.0公里）', '1006', '14.00', 'uuid-user-001', 'uuid-op-status-001', '99.50', '98.00', '75.80', '100.00', 'uuid-clean-type-003', '每1小时1次（水域）/每2小时1次（陆域）', '05:00-24:00（轮班）', '[\"uuid-user-006\"]', '[\"uuid-tool-001\", \"uuid-tool-003\", \"uuid-tool-007\", \"uuid-tool-008\"]', '78.00', 'uuid-monitor-type-007', '每日2次（常规）', '[\"pH值\", \"溶解氧\", \"氨氮\", \"COD\", \"异味等级\", \"水生植物覆盖度\", \"透明度\"]', 'uuid-user-008', '2024-05-22 13:00:00', 'uuid-monitor-status-002', '2024-05-21 13:00:00', '2024-05-22 12:00:00', '100.00', '0', '', '', '', '', null, '[]', 'uuid-dept-006', '', null, '', '否', 'uuid-task-type-001', '', '', '', '', 'admin', 'admin');
INSERT INTO `river` VALUES ('7', 'uuid-river-007', '湘江', '芙蓉区橘子洲至岳麓区滨江新城（全长9.5公里）', '1007', '9.50', 'uuid-user-001', 'uuid-op-status-001', '92.00', '90.00', '110.40', '95.00', 'uuid-clean-type-001', '每2小时1次（水域）/每3小时1次（陆域）', '06:00-23:00（轮班）', '[\"uuid-user-007\"]', '[\"uuid-tool-001\", \"uuid-tool-002\", \"uuid-tool-004\", \"uuid-tool-005\"]', '115.00', 'uuid-monitor-type-003', '每8小时1次（常规）', '[\"水生植物种类\", \"覆盖面积\", \"生长密度\", \"清理频次\"]', 'uuid-user-009', '2024-05-22 14:00:00', 'uuid-monitor-status-004', '2024-05-21 14:00:00', '2024-05-22 13:00:00', '90.00', '0', 'uuid-problem-type-004', '橘子洲段江面', '橘子洲段江面水生植物（水葫芦）少量生长，覆盖度5%，未超标，正在监测生长趋势', 'uuid-user-012', '2024-05-21 11:00:00', '[\"https://xxx.com/river/7-1.jpg\"]', 'uuid-dept-007', 'uuid-user-006', '2024-05-21 11:30:00', 'uuid-handle-003', '否', 'uuid-task-type-004', '', '', '', '', 'admin', 'admin');
INSERT INTO `river` VALUES ('8', 'uuid-river-008', '长江', '洪山区长江二桥至青山区天兴洲（全长18.3公里）', '1008', '18.30', 'uuid-user-003', 'uuid-op-status-003', '0.00', '0.00', '0.00', '0.00', '', '无', '无', '[]', '[]', '0.00', '', '无', '[]', '', null, 'uuid-monitor-status-001', null, null, '0.00', '0', '', '', '', '', null, '[]', null, '', null, '', '否', 'uuid-task-type-005', '', '', '', '', 'admin', 'admin');

-- ----------------------------
-- Table structure for road_cleaning
-- ----------------------------
DROP TABLE IF EXISTS `road_cleaning`;
CREATE TABLE `road_cleaning` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `cleaning_id` varchar(64) DEFAULT NULL COMMENT '清扫计划主键（UUID）',
  `plan_no` varchar(64) DEFAULT NULL COMMENT '清扫计划编号',
  `road_id` varchar(64) DEFAULT NULL COMMENT '关联sys_road.id',
  `area_code` varchar(64) DEFAULT NULL COMMENT '关联sys_area.area_code',
  `frequency` varchar(32) DEFAULT NULL COMMENT '清扫频次',
  `time_period` varchar(64) DEFAULT NULL COMMENT '清扫时段',
  `staff_ids` json DEFAULT NULL COMMENT '负责人员IDs，JSON',
  `plan_status_id` varchar(64) DEFAULT NULL COMMENT '关联sys_plan_status.id',
  `quality_rate` decimal(5,2) DEFAULT NULL COMMENT '质量达标率',
  `problem_count` int DEFAULT NULL COMMENT '问题处置数',
  `attendance_rate` decimal(5,2) DEFAULT NULL COMMENT '考勤全勤率',
  `tool_ids` json DEFAULT NULL COMMENT '清扫工具IDs，JSON',
  `standard` varchar(512) DEFAULT NULL COMMENT '清扫标准',
  `checkin_time` datetime DEFAULT NULL COMMENT '到岗时间',
  `progress` varchar(16) DEFAULT NULL COMMENT '当前进度',
  `operation_status` varchar(16) DEFAULT NULL COMMENT '作业状态：运行/暂停/异常',
  `track_coverage` varchar(16) DEFAULT NULL COMMENT '轨迹覆盖情况：合规/偏离',
  `last_report_time` datetime DEFAULT NULL COMMENT '最新上报时间',
  `is_abnormal` varchar(8) DEFAULT NULL COMMENT '是否异常：是/否',
  `is_effective` varchar(8) DEFAULT NULL COMMENT '是否生效：是/否',
  `complete_time` datetime DEFAULT NULL COMMENT '作业完成时间',
  `check_photo_url` json DEFAULT NULL COMMENT '上报照片URL，JSON',
  `review_status` varchar(16) DEFAULT NULL COMMENT '核查状态：待核查/达标/不达标',
  `review_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `review_time` datetime DEFAULT NULL COMMENT '核查时间',
  `reform_require` varchar(512) DEFAULT NULL COMMENT '整改要求',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='道路清扫计划表';

-- ----------------------------
-- Records of road_cleaning
-- ----------------------------
INSERT INTO `road_cleaning` VALUES ('1', 'uuid-clean-001', 'RC20240515001', 'uuid-road-001', '1001', '每日2次', '06:00-11:00,14:00-19:00', '[\"uuid-user-001\", \"uuid-user-004\", \"uuid-user-005\"]', 'uuid-plan-status-003', '98.50', '2', '100.00', '[\"uuid-tool-001\", \"uuid-tool-003\"]', '路面无可见垃圾、绿化带无白色垃圾、垃圾桶周边整洁', '2024-05-15 05:50:00', '100%', '运行', '合规', '2024-05-15 18:30:00', '否', '是', '2024-05-15 19:10:00', '[\"https://xxx.com/clean/1-1.jpg\", \"https://xxx.com/clean/1-2.jpg\"]', '达标', 'uuid-user-006', '2024-05-15 20:00:00', '', null, null, null, null, 'admin', 'admin', '\0');
INSERT INTO `road_cleaning` VALUES ('2', 'uuid-clean-002', 'RC20240515002', 'uuid-road-002', '1002', '隔日1次', '07:00-12:00', '[\"uuid-user-002\", \"uuid-user-006\"]', 'uuid-plan-status-002', '92.00', '1', '95.00', '[\"uuid-tool-002\", \"uuid-tool-004\"]', '路面无大块垃圾、下水道口无堵塞、人行道无杂物', '2024-05-15 06:55:00', '80%', '运行', '合规', '2024-05-15 10:15:00', '否', '是', null, '[\"https://xxx.com/clean/2-1.jpg\"]', '待核查', '', null, '', null, null, null, null, 'admin', 'admin', '\0');
INSERT INTO `road_cleaning` VALUES ('3', 'uuid-clean-003', 'RC20240515003', 'uuid-road-003', '1003', '临时1次', '08:00-11:00', '[\"uuid-user-003\"]', 'uuid-plan-status-004', '75.00', '3', '80.00', '[\"uuid-tool-003\", \"uuid-tool-005\"]', '清理路面碎石、清扫落叶、擦拭公交站台', '2024-05-15 08:10:00', '50%', '异常', '偏离', '2024-05-15 09:30:00', '是', '是', null, '[\"https://xxx.com/clean/3-1.jpg\"]', '不达标', 'uuid-user-006', '2024-05-15 10:00:00', '12点前完成碎石清理，补充清扫人员', null, null, null, null, 'admin', 'admin', '\0');
INSERT INTO `road_cleaning` VALUES ('4', 'uuid-clean-004', 'RC20240515004', 'uuid-road-004', '1004', '每日1次', '05:00-09:00', '[\"uuid-user-007\", \"uuid-user-008\"]', 'uuid-plan-status-001', '0.00', '0', '0.00', '[\"uuid-tool-001\", \"uuid-tool-004\"]', '全路段深度清扫，含护栏擦拭', null, '0%', '暂停', '合规', null, '否', '否', null, '[]', '待核查', '', null, '', null, null, null, null, 'admin', 'admin', '\0');
INSERT INTO `road_cleaning` VALUES ('5', 'uuid-clean-005', 'RC20240515005', 'uuid-road-005', '1005', '每周3次', '09:00-14:00', '[\"uuid-user-009\", \"uuid-user-010\"]', 'uuid-plan-status-003', '95.00', '1', '98.00', '[\"uuid-tool-002\", \"uuid-tool-003\"]', '路面无积尘、盲道无遮挡、绿化带无垃圾', '2024-05-15 08:50:00', '100%', '运行', '合规', '2024-05-15 13:40:00', '否', '是', '2024-05-15 14:00:00', '[\"https://xxx.com/clean/5-1.jpg\"]', '达标', 'uuid-user-006', '2024-05-15 14:30:00', '', null, null, null, null, 'admin', 'admin', '\0');
INSERT INTO `road_cleaning` VALUES ('6', 'uuid-clean-006', 'RC20240515006', 'uuid-road-006', '1006', '每日2次', '10:00-15:00,16:00-21:00', '[\"uuid-user-001\", \"uuid-user-009\"]', 'uuid-plan-status-002', '88.00', '2', '90.00', '[\"uuid-tool-003\", \"uuid-tool-005\"]', '景区道路精细化清扫，无烟头、纸屑', '2024-05-15 09:55:00', '70%', '运行', '合规', '2024-05-15 14:20:00', '否', '是', null, '[\"https://xxx.com/clean/6-1.jpg\"]', '待核查', '', null, '', null, null, null, null, 'admin', 'admin', '\0');
INSERT INTO `road_cleaning` VALUES ('7', 'uuid-clean-007', 'RC20240515007', 'uuid-road-007', '1007', '高峰时段每小时1次', '08:00-22:00', '[\"uuid-user-002\", \"uuid-user-007\", \"uuid-user-010\"]', 'uuid-plan-status-004', '80.00', '4', '85.00', '[\"uuid-tool-001\", \"uuid-tool-002\", \"uuid-tool-003\"]', '商业路段高频清扫，无垃圾堆积', '2024-05-15 07:50:00', '60%', '异常', '偏离', '2024-05-15 12:10:00', '是', '是', null, '[\"https://xxx.com/clean/7-1.jpg\"]', '不达标', 'uuid-user-006', '2024-05-15 13:00:00', '增加1名清扫人员，每30分钟上报一次进度', null, null, null, null, 'admin', 'admin', '\0');
INSERT INTO `road_cleaning` VALUES ('8', 'uuid-clean-008', 'RC20240515008', 'uuid-road-008', '1001', '专项1次', '13:00-17:00', '[\"uuid-user-004\", \"uuid-user-005\"]', 'uuid-plan-status-005', '0.00', '0', '0.00', '[\"uuid-tool-001\", \"uuid-tool-004\"]', '节日前专项清扫，含地面油污清理', null, '0%', '暂停', '合规', null, '否', '否', null, '[]', '待核查', '', null, '', null, null, null, null, 'admin', 'admin', '\0');

-- ----------------------------
-- Table structure for road_cleaning_problem
-- ----------------------------
DROP TABLE IF EXISTS `road_cleaning_problem`;
CREATE TABLE `road_cleaning_problem` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `problem_id` varchar(64) DEFAULT NULL COMMENT '问题主键（UUID）',
  `plan_id` varchar(64) DEFAULT NULL COMMENT '关联road_cleaning.cleaning_id',
  `problem_type_id` varchar(64) DEFAULT NULL COMMENT '关联sys_problem_type.id',
  `location` varchar(256) DEFAULT NULL COMMENT '问题位置',
  `report_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `report_time` datetime DEFAULT NULL COMMENT '上报时间',
  `desc` varchar(1024) DEFAULT NULL COMMENT '问题描述',
  `team_id` varchar(64) DEFAULT NULL COMMENT '关联sys_team.id',
  `handle_status` varchar(16) DEFAULT NULL COMMENT '处置状态：待处置/处理中/已办结',
  `is_timeout` varchar(8) DEFAULT NULL COMMENT '超时提醒：是/否',
  `handle_result` varchar(512) DEFAULT NULL COMMENT '处置结果',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='道路清扫问题表';

-- ----------------------------
-- Records of road_cleaning_problem
-- ----------------------------
INSERT INTO `road_cleaning_problem` VALUES ('1', 'uuid-problem-001', 'uuid-clean-001', 'uuid-problem-type-001', '朝阳大道东段300米处', 'uuid-user-001', '2024-05-15 09:10:00', '早高峰后快餐盒、塑料袋堆积，影响通行', 'uuid-team-001', '已办结', '否', '已安排人员清理，现场恢复整洁', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `road_cleaning_problem` VALUES ('2', 'uuid-problem-002', 'uuid-clean-002', 'uuid-problem-type-002', '静安路中段公交站台旁', 'uuid-user-002', '2024-05-15 10:05:00', '手推扫地机电池故障，无法正常使用', 'uuid-team-002', '处理中', '否', '已联系设备组更换电池，预计1小时内修复', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `road_cleaning_problem` VALUES ('3', 'uuid-problem-003', 'uuid-clean-003', 'uuid-problem-type-004', '天河路西段人行道', 'uuid-user-003', '2024-05-15 09:20:00', '碎石清理不彻底，盲道有杂物遮挡', 'uuid-team-003', '已办结', '是', '增派2名人员重新清扫，盲道已清理完毕', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `road_cleaning_problem` VALUES ('4', 'uuid-problem-004', 'uuid-clean-007', 'uuid-problem-type-003', '春熙路核心商圈', 'uuid-user-007', '2024-05-15 11:30:00', '1名清扫人员未到岗，导致清扫频次不足', 'uuid-team-002', '处理中', '是', '已协调备用人员到岗，后续核实缺勤原因', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `road_cleaning_problem` VALUES ('5', 'uuid-problem-005', 'uuid-clean-005', 'uuid-problem-type-005', '西湖路北侧非机动车道', 'uuid-user-009', '2024-05-15 11:00:00', '路面有破碎玻璃，易划伤行人轮胎', 'uuid-team-001', '已办结', '否', '已用高压水枪清理，铺设警示标志直至清理完毕', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `road_cleaning_problem` VALUES ('6', 'uuid-problem-006', 'uuid-clean-006', 'uuid-problem-type-006', '夫子庙路景区入口', 'uuid-user-001', '2024-05-15 13:20:00', '餐饮门店污水直排路面，有异味', 'uuid-team-003', '处理中', '否', '已通知环保部门，现场先用吸污车清理', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `road_cleaning_problem` VALUES ('7', 'uuid-problem-007', 'uuid-clean-004', 'uuid-problem-type-009', '南山路西段', 'uuid-user-008', '2024-05-15 08:00:00', '清扫车GPS定位偏离作业区域', 'uuid-team-001', '已办结', '否', '重新校准GPS设备，恢复正常定位', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `road_cleaning_problem` VALUES ('8', 'uuid-problem-008', 'uuid-clean-008', 'uuid-problem-type-010', '王府井大街北段', 'uuid-user-004', '2024-05-15 12:40:00', '市民投诉节日前清扫不彻底，有烟头、纸屑', 'uuid-team-002', '待处置', '否', '', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');

-- ----------------------------
-- Table structure for sys_abnormal_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_abnormal_type`;
CREATE TABLE `sys_abnormal_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `abnormal_type_id` varchar(64) DEFAULT NULL COMMENT '异常类型主键（UUID）',
  `abnormal_name` varchar(128) DEFAULT NULL COMMENT '异常类型名称',
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='异常类型字典表';

-- ----------------------------
-- Records of sys_abnormal_type
-- ----------------------------
INSERT INTO `sys_abnormal_type` VALUES ('1', 'uuid-abnormal-type-001', '垃圾满溢', '点位垃圾未及时清运导致满溢', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `sys_abnormal_type` VALUES ('2', 'uuid-abnormal-type-002', '混投混放', '不同品类垃圾混合投放', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `sys_abnormal_type` VALUES ('3', 'uuid-abnormal-type-003', '设备损坏', '垃圾收集设备（垃圾桶/压缩箱）损坏', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `sys_abnormal_type` VALUES ('4', 'uuid-abnormal-type-004', '清运延迟', '未按计划时间完成清运作业', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `sys_abnormal_type` VALUES ('5', 'uuid-abnormal-type-005', '车辆故障', '清运车辆作业中故障', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `sys_abnormal_type` VALUES ('6', 'uuid-abnormal-type-006', '安全隐患', '点位存在易燃易爆等安全风险', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `sys_abnormal_type` VALUES ('7', 'uuid-abnormal-type-007', '投诉举报', '居民投诉垃圾清运相关问题', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');

-- ----------------------------
-- Table structure for sys_alarm_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_alarm_type`;
CREATE TABLE `sys_alarm_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `alarm_type_id` varchar(64) DEFAULT NULL COMMENT '预警类型主键（UUID）',
  `alarm_name` varchar(128) DEFAULT NULL COMMENT '预警类型名称',
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='预警类型字典表';

-- ----------------------------
-- Records of sys_alarm_type
-- ----------------------------
INSERT INTO `sys_alarm_type` VALUES ('1', 'uuid-alarm-type-001', '设备故障', '转运核心设备（压缩机/输送机）故障', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-13 08:33:20');
INSERT INTO `sys_alarm_type` VALUES ('2', 'uuid-alarm-type-002', '环境超标', '臭气/噪音/污水排放未达环保标准', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-13 08:33:20');
INSERT INTO `sys_alarm_type` VALUES ('3', 'uuid-alarm-type-003', '转运量超载', '日转运量超出设计承载能力', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-13 08:33:20');
INSERT INTO `sys_alarm_type` VALUES ('4', 'uuid-alarm-type-004', '库存积压', '垃圾未及时转运导致站内积压', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-13 08:33:20');
INSERT INTO `sys_alarm_type` VALUES ('5', 'uuid-alarm-type-005', '维护超时', '设备未按周期完成维护', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-13 08:33:20');
INSERT INTO `sys_alarm_type` VALUES ('6', 'uuid-alarm-type-006', '安全隐患', '消防/用电/操作存在安全风险', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-13 08:33:20');
INSERT INTO `sys_alarm_type` VALUES ('7', 'uuid-alarm-type-007', '预约异常', '进站预约超时/爽约/重量不符', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-13 08:33:20');
INSERT INTO `sys_alarm_type` VALUES ('8', 'uuid-alarm-type-008', '其他预警', '非上述类型的转运站异常情况', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-13 08:33:20');

-- ----------------------------
-- Table structure for sys_area
-- ----------------------------
DROP TABLE IF EXISTS `sys_area`;
CREATE TABLE `sys_area` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `area_code` char(36) NOT NULL COMMENT '主键（区域编码）',
  `area_name` varchar(128) DEFAULT NULL COMMENT '区域名称',
  `parent_code` char(36) DEFAULT NULL COMMENT '上级区域编码（关联sys_area.area_code，顶级区域填0）',
  `level` tinyint DEFAULT NULL COMMENT '区域层级（可选值：1-省级/2-市级/3-区级/4-街道/5-社区）',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_area_code` (`area_code`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='区域编码表';

-- ----------------------------
-- Records of sys_area
-- ----------------------------
INSERT INTO `sys_area` VALUES ('1', '1001', '北京市', '0', '1', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 12:00:37', '2026-02-14 12:00:37');
INSERT INTO `sys_area` VALUES ('2', '1002', '上海市', '0', '1', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 12:00:37', '2026-02-14 12:00:37');
INSERT INTO `sys_area` VALUES ('3', '1003', '广州市', '0', '1', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 12:00:37', '2026-02-14 12:00:37');
INSERT INTO `sys_area` VALUES ('4', '1004', '深圳市', '0', '1', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 12:00:37', '2026-02-14 12:00:37');
INSERT INTO `sys_area` VALUES ('5', '1005', '杭州市', '0', '1', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 12:00:37', '2026-02-14 12:00:37');
INSERT INTO `sys_area` VALUES ('6', '1006', '南京市', '0', '1', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 12:00:37', '2026-02-14 12:00:37');
INSERT INTO `sys_area` VALUES ('7', '1007', '成都市', '0', '1', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 12:00:37', '2026-02-14 12:00:37');

-- ----------------------------
-- Table structure for sys_assessment
-- ----------------------------
DROP TABLE IF EXISTS `sys_assessment`;
CREATE TABLE `sys_assessment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `assessment_id` char(36) DEFAULT NULL COMMENT '主键（UUID）',
  `user_id` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `job_type_id` varchar(64) DEFAULT NULL COMMENT '关联sys_job_type.id',
  `team_id` varchar(64) DEFAULT NULL COMMENT '关联sys_team.id',
  `area_code` varchar(64) DEFAULT NULL COMMENT '关联sys_area.area_code',
  `cycle` varchar(8) DEFAULT NULL COMMENT '考核周期',
  `attendance_score` decimal(5,2) DEFAULT NULL COMMENT '考勤得分',
  `work_quality_score` decimal(5,2) DEFAULT NULL COMMENT '作业质量得分',
  `problem_solving_score` decimal(5,2) DEFAULT NULL COMMENT '问题处置得分',
  `initial_total_score` decimal(5,2) DEFAULT NULL COMMENT '初始总分',
  `final_total_score` decimal(5,2) DEFAULT NULL COMMENT '最终总分',
  `assessment_grade_id` varchar(64) DEFAULT NULL COMMENT '关联sys_assessment_grade.id',
  `review_opinion` varchar(1024) DEFAULT NULL COMMENT '考核意见',
  `assess_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `review_time` datetime DEFAULT NULL COMMENT '审核时间',
  `proof_url` json DEFAULT NULL COMMENT '佐证材料URL',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='考核表';

-- ----------------------------
-- Records of sys_assessment
-- ----------------------------
INSERT INTO `sys_assessment` VALUES ('1', 'ass_001', 'user_001', 'job_001', 'team_001', 'area_001', '月', '25.00', '30.00', '35.00', '90.00', '90.00', 'grade_001', '本月作业质量优秀，考勤无异常', 'user_004', '2024-05-05 15:00:00', '[\"https://test.com/assess/1.jpg\"]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16');
INSERT INTO `sys_assessment` VALUES ('2', 'ass_002', 'user_002', 'job_002', 'team_001', 'area_002', '月', '20.00', '28.00', '25.00', '73.00', '73.00', 'grade_003', '本月有1次迟到，作业质量基本达标', 'user_004', '2024-05-05 15:30:00', '[\"https://test.com/assess/2.jpg\"]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16');
INSERT INTO `sys_assessment` VALUES ('3', 'ass_003', 'user_003', 'job_003', 'team_002', 'area_003', '月', '10.00', '20.00', '15.00', '45.00', '45.00', 'grade_004', '本月多次旷工，作业完成率低', 'user_004', '2024-05-05 16:00:00', '[\"https://test.com/assess/3.jpg\"]', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16');

-- ----------------------------
-- Table structure for sys_assessment_grade
-- ----------------------------
DROP TABLE IF EXISTS `sys_assessment_grade`;
CREATE TABLE `sys_assessment_grade` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `assessment_grade_id` char(36) DEFAULT NULL COMMENT '主键（UUID）',
  `grade_name` varchar(32) DEFAULT NULL COMMENT '考核等级名称',
  `score_range` varchar(32) DEFAULT NULL COMMENT '分数区间',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述',
  `status` tinyint DEFAULT '1' COMMENT '状态（可选值：0-禁用/1-启用）',
  `sort` int DEFAULT '0' COMMENT '排序值',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='考核等级字典表【通用复用】';

-- ----------------------------
-- Records of sys_assessment_grade
-- ----------------------------
INSERT INTO `sys_assessment_grade` VALUES ('1', 'grade_001', '优秀', '90-100', '考核成绩优秀', '1', '1', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-12 17:06:16');
INSERT INTO `sys_assessment_grade` VALUES ('2', 'grade_002', '良好', '80-89', '考核成绩良好', '1', '2', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-12 17:06:16');
INSERT INTO `sys_assessment_grade` VALUES ('3', 'grade_003', '合格', '60-79', '考核成绩合格', '1', '3', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-12 17:06:16');
INSERT INTO `sys_assessment_grade` VALUES ('4', 'grade_004', '不合格', '0-59', '考核成绩不合格', '1', '4', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-12 17:06:16');

-- ----------------------------
-- Table structure for sys_attendance
-- ----------------------------
DROP TABLE IF EXISTS `sys_attendance`;
CREATE TABLE `sys_attendance` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `attendance_id` char(36) DEFAULT NULL COMMENT '主键（UUID）',
  `user_id` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `job_type_id` varchar(64) DEFAULT NULL COMMENT '关联sys_job_type.id',
  `team_id` varchar(64) DEFAULT NULL COMMENT '关联sys_team.id',
  `check_date` date DEFAULT NULL COMMENT '打卡日期',
  `on_duty_time` datetime DEFAULT NULL COMMENT '到岗打卡时间',
  `off_duty_time` datetime DEFAULT NULL COMMENT '离岗打卡时间',
  `attendance_status_id` varchar(64) DEFAULT NULL COMMENT '关联sys_attendance_status.id',
  `check_location` varchar(256) DEFAULT NULL COMMENT '打卡位置',
  `work_hours` decimal(5,2) DEFAULT NULL COMMENT '考勤时长（单位：小时）',
  `abnormal_type_id` varchar(64) DEFAULT NULL COMMENT '关联sys_attendance_abnormal_type.id',
  `abnormal_desc` varchar(1024) DEFAULT NULL COMMENT '异常说明',
  `proof_material` json DEFAULT NULL COMMENT '佐证材料URL',
  `review_status_id` varchar(64) DEFAULT NULL COMMENT '关联sys_review_status.id',
  `supplement_reason` varchar(1024) DEFAULT NULL COMMENT '补录理由',
  `supplement_time` datetime DEFAULT NULL COMMENT '补录时间',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='考勤表';

-- ----------------------------
-- Records of sys_attendance
-- ----------------------------
INSERT INTO `sys_attendance` VALUES ('1', 'att_001', 'user_001', 'job_001', 'team_001', '2024-05-19', '2024-05-19 08:00:00', '2024-05-19 18:00:00', 'att_status_001', 'XX路XX段作业区域', '10.00', '', '', '[]', 'review_002', '', null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16');
INSERT INTO `sys_attendance` VALUES ('2', 'att_002', 'user_002', 'job_002', 'team_001', '2024-05-19', '2024-05-19 08:30:00', '2024-05-19 18:00:00', 'att_status_002', 'XX小区保洁区域', '9.50', 'abn_type_001', '因交通拥堵迟到30分钟', '[\"https://test.com/proof/1.jpg\"]', 'review_001', '', null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16');
INSERT INTO `sys_attendance` VALUES ('3', 'att_003', 'user_003', 'job_003', 'team_002', '2024-05-19', null, null, 'att_status_003', '', '0.00', 'abn_type_003', '当日忘记打卡', '[]', 'review_001', '家中突发疾病未打卡', '2024-05-20 10:00:00', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16');

-- ----------------------------
-- Table structure for sys_attendance_abnormal_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_attendance_abnormal_type`;
CREATE TABLE `sys_attendance_abnormal_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `attendance_abnormal_type_id` char(36) DEFAULT NULL COMMENT '主键（UUID）',
  `abnormal_type_name` varchar(32) DEFAULT NULL COMMENT '异常类型名称：迟到/早退/旷工/未打卡/定位异常',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述',
  `status` tinyint DEFAULT '1' COMMENT '状态（可选值：0-禁用/1-启用）',
  `sort` int DEFAULT '0' COMMENT '排序值',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='考勤异常类型字典表【通用复用】';

-- ----------------------------
-- Records of sys_attendance_abnormal_type
-- ----------------------------
INSERT INTO `sys_attendance_abnormal_type` VALUES ('1', 'abn_type_001', '迟到', '到岗打卡时间晚于规定时间', '1', '1', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-12 17:06:16');
INSERT INTO `sys_attendance_abnormal_type` VALUES ('2', 'abn_type_002', '早退', '离岗打卡时间早于规定时间', '1', '2', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-12 17:06:16');
INSERT INTO `sys_attendance_abnormal_type` VALUES ('3', 'abn_type_003', '旷工', '当日未打卡且无请假记录', '1', '3', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-12 17:06:16');
INSERT INTO `sys_attendance_abnormal_type` VALUES ('4', 'abn_type_004', '定位异常', '打卡位置不在指定作业区域', '1', '4', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-12 17:06:16');

-- ----------------------------
-- Table structure for sys_attendance_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_attendance_status`;
CREATE TABLE `sys_attendance_status` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `attendance_status_id` char(36) DEFAULT NULL COMMENT '主键（UUID）',
  `attendance_status_name` varchar(32) DEFAULT NULL COMMENT '考勤状态名称：正常/异常/未打卡',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述',
  `status` tinyint DEFAULT '1' COMMENT '状态（可选值：0-禁用/1-启用）',
  `sort` int DEFAULT '0' COMMENT '排序值',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='考勤状态字典表【通用复用】';

-- ----------------------------
-- Records of sys_attendance_status
-- ----------------------------
INSERT INTO `sys_attendance_status` VALUES ('1', 'att_status_001', '正常', '考勤打卡无异常', '1', '1', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-12 17:06:16');
INSERT INTO `sys_attendance_status` VALUES ('2', 'att_status_002', '异常', '考勤打卡存在迟到/早退等问题', '1', '2', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-12 17:06:16');
INSERT INTO `sys_attendance_status` VALUES ('3', 'att_status_003', '未打卡', '当日未进行打卡操作', '1', '3', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-12 17:06:16');

-- ----------------------------
-- Table structure for sys_check_result
-- ----------------------------
DROP TABLE IF EXISTS `sys_check_result`;
CREATE TABLE `sys_check_result` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `check_result_id` char(36) DEFAULT NULL COMMENT '主键（UUID）',
  `check_result_name` varchar(32) DEFAULT NULL COMMENT '核查结果名称：达标/不达标',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述',
  `status` tinyint DEFAULT '1' COMMENT '状态（可选值：0-禁用/1-启用）',
  `sort` int DEFAULT '0' COMMENT '排序值',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='核查结果字典表【通用复用】';

-- ----------------------------
-- Records of sys_check_result
-- ----------------------------
INSERT INTO `sys_check_result` VALUES ('1', 'uuid-check-001', '达标', '卫生、收运、污水处置等全部核查项符合要求，整体达标', '1', '1', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:44:38', '2026-02-12 16:44:38');
INSERT INTO `sys_check_result` VALUES ('2', 'uuid-check-002', '不达标', '核心核查项不符合要求，整体不达标，需限期整改', '1', '2', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:44:38', '2026-02-12 16:44:38');
INSERT INTO `sys_check_result` VALUES ('3', 'uuid-check-003', '部分达标', '核心项达标，次要项不达标，需针对性整改', '1', '3', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:44:38', '2026-02-12 16:44:38');
INSERT INTO `sys_check_result` VALUES ('4', 'uuid-check-004', '待核查', '已提交核查申请，待核查人员现场检查', '1', '4', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:44:38', '2026-02-12 16:44:38');
INSERT INTO `sys_check_result` VALUES ('5', 'uuid-check-005', '核查中', '正在进行现场核查，暂未出最终结果', '1', '5', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:44:38', '2026-02-12 16:44:38');
INSERT INTO `sys_check_result` VALUES ('6', 'uuid-check-006', '核查超时', '超出核查时限未完成现场核查', '1', '6', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:44:38', '2026-02-12 16:44:38');
INSERT INTO `sys_check_result` VALUES ('7', 'uuid-check-007', '重新核查', '首次核查不达标，整改后重新申请核查', '1', '7', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:44:38', '2026-02-12 16:44:38');
INSERT INTO `sys_check_result` VALUES ('8', 'uuid-check-008', '核查取消', '因市场暂停运营等原因取消本次核查', '1', '8', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:44:38', '2026-02-12 16:44:38');

-- ----------------------------
-- Table structure for sys_cleaning_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_cleaning_type`;
CREATE TABLE `sys_cleaning_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `cleaning_type_id` char(36) DEFAULT NULL COMMENT '主键（UUID）',
  `cleaning_name` varchar(32) DEFAULT NULL COMMENT '保洁类型名称：水域/陆域',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述',
  `status` tinyint DEFAULT '1' COMMENT '状态（可选值：0-禁用/1-启用）',
  `sort` int DEFAULT '0' COMMENT '排序值',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='保洁类型字典表【通用复用】';

-- ----------------------------
-- Records of sys_cleaning_type
-- ----------------------------
INSERT INTO `sys_cleaning_type` VALUES ('1', 'uuid-clean-type-001', '水域', '河道水面垃圾打捞、水生植物清理、水体保洁等水域相关保洁', '1', '1', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:48:13', '2026-02-12 16:48:13');
INSERT INTO `sys_cleaning_type` VALUES ('2', 'uuid-clean-type-002', '陆域', '河道两岸堤岸、绿化带、步道等陆域区域保洁', '1', '2', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:48:13', '2026-02-12 16:48:13');
INSERT INTO `sys_cleaning_type` VALUES ('3', 'uuid-clean-type-003', '全域', '水域+陆域一体化保洁', '1', '3', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:48:13', '2026-02-12 16:48:13');
INSERT INTO `sys_cleaning_type` VALUES ('4', 'uuid-clean-type-004', '专项', '针对突发垃圾、油污等专项保洁', '1', '4', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:48:13', '2026-02-12 16:48:13');
INSERT INTO `sys_cleaning_type` VALUES ('5', 'uuid-clean-type-005', '日常', '每日常规性水域/陆域保洁', '1', '5', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:48:13', '2026-02-12 16:48:13');
INSERT INTO `sys_cleaning_type` VALUES ('6', 'uuid-clean-type-006', '应急', '暴雨、洪水等应急情况下的快速保洁', '1', '6', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:48:13', '2026-02-12 16:48:13');
INSERT INTO `sys_cleaning_type` VALUES ('7', 'uuid-clean-type-007', '机械化', '使用保洁船、清扫车等机械设备进行保洁', '1', '7', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:48:13', '2026-02-12 16:48:13');
INSERT INTO `sys_cleaning_type` VALUES ('8', 'uuid-clean-type-008', '人工', '纯人工方式进行水域/陆域保洁', '1', '8', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:48:13', '2026-02-12 16:48:13');

-- ----------------------------
-- Table structure for sys_complaint_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_complaint_type`;
CREATE TABLE `sys_complaint_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `complaint_type_id` varchar(64) DEFAULT NULL COMMENT '投诉类型主键（UUID）',
  `complaint_name` varchar(128) DEFAULT NULL COMMENT '投诉类型名称',
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='投诉类型字典表';

-- ----------------------------
-- Records of sys_complaint_type
-- ----------------------------
INSERT INTO `sys_complaint_type` VALUES ('1', 'uuid-complaint-type-001', '卫生脏乱', '地面/便池/洗手台脏乱差', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_complaint_type` VALUES ('2', 'uuid-complaint-type-002', '设施损坏', '冲水系统/门锁/照明等损坏', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_complaint_type` VALUES ('3', 'uuid-complaint-type-003', '无耗材', '卫生纸/洗手液等耗材缺失', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_complaint_type` VALUES ('4', 'uuid-complaint-type-004', '异味严重', '卫生间异味未及时处理', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_complaint_type` VALUES ('5', 'uuid-complaint-type-005', '开放时间违规', '未按公示时间开放', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_complaint_type` VALUES ('6', 'uuid-complaint-type-006', '保洁态度差', '保洁人员服务态度恶劣', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_complaint_type` VALUES ('7', 'uuid-complaint-type-007', '无障碍设施缺失', '无轮椅通道/扶手等', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_complaint_type` VALUES ('8', 'uuid-complaint-type-008', '其他', '非上述类型的投诉', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');

-- ----------------------------
-- Table structure for sys_consumable
-- ----------------------------
DROP TABLE IF EXISTS `sys_consumable`;
CREATE TABLE `sys_consumable` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `consumable_id` varchar(64) DEFAULT NULL COMMENT '耗材主键（UUID）',
  `consumable_name` varchar(128) DEFAULT NULL COMMENT '耗材名称',
  `specification` varchar(128) DEFAULT NULL COMMENT '规格',
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='耗材字典表';

-- ----------------------------
-- Records of sys_consumable
-- ----------------------------
INSERT INTO `sys_consumable` VALUES ('1', 'uuid-consum-001', '卫生纸', '100抽/包', '公厕专用原生木浆卫生纸', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_consumable` VALUES ('2', 'uuid-consum-002', '洗手液', '500ml/瓶', '抑菌型洗手液', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_consumable` VALUES ('3', 'uuid-consum-003', '消毒液', '1L/瓶', '84消毒液（1:100稀释）', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_consumable` VALUES ('4', 'uuid-consum-004', '垃圾袋', '60*80cm', '加厚防漏黑色垃圾袋', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_consumable` VALUES ('5', 'uuid-consum-005', '洁厕灵', '500ml/瓶', '强效除垢洁厕剂', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_consumable` VALUES ('6', 'uuid-consum-006', '芳香球', '10颗/盒', '卫生间除臭芳香球', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_consumable` VALUES ('7', 'uuid-consum-007', '一次性手套', 'M码', '保洁用一次性丁腈手套', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_consumable` VALUES ('8', 'uuid-consum-008', '拖把', '棉线款', '吸水耐磨清洁拖把', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_dept_id` char(36) DEFAULT NULL COMMENT '业务主键（UUID）',
  `name` varchar(64) DEFAULT NULL COMMENT '部门名称',
  `parent_id` char(36) DEFAULT NULL COMMENT '父部门ID（关联自身sys_dept_id，顶级部门填0）',
  `dept_code` varchar(32) DEFAULT NULL COMMENT '部门编码',
  `status` tinyint DEFAULT NULL COMMENT '状态（可选值：0-禁用/1-启用）',
  `sort` int DEFAULT NULL COMMENT '排序号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', 'uuid-dept-001', '环卫管理中心', '0', 'HWGLZX', '1', '1', '一级部门，统筹所有环卫业务', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-24 14:29:27');
INSERT INTO `sys_dept` VALUES ('2', 'uuid-dept-002', '清运作业部', '11111111-1111-1111-1111-111111111111', 'QY-ZYB', '1', '2', '负责垃圾清运作业管理', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-24 14:29:31');
INSERT INTO `sys_dept` VALUES ('3', 'uuid-dept-003', '清扫作业部', '11111111-1111-1111-1111-111111111111', 'QS-ZYB', '1', '3', '负责道路清扫作业管理', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-24 14:29:35');
INSERT INTO `sys_dept` VALUES ('4', 'uuid-dept-004', '车辆维保部', '11111111-1111-1111-1111-111111111111', 'CL-WBB', '1', '4', '负责环卫车辆维护保养', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-24 14:29:40');
INSERT INTO `sys_dept` VALUES ('5', 'uuid-dept-005', '巡查监督部', '11111111-1111-1111-1111-111111111111', 'XC-JDB', '1', '5', '负责作业巡查和违规监督', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-24 14:29:44');
INSERT INTO `sys_dept` VALUES ('6', 'uuid-dept-006', '应急保障部', '11111111-1111-1111-1111-111111111111', 'YJ-BZB', '1', '6', '负责环卫应急作业保障', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-24 14:29:49');

-- ----------------------------
-- Table structure for sys_equipment
-- ----------------------------
DROP TABLE IF EXISTS `sys_equipment`;
CREATE TABLE `sys_equipment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_equipment_id` char(36) DEFAULT NULL COMMENT '业务主键（UUID）',
  `name` varchar(64) DEFAULT NULL COMMENT '设备名称',
  `code` varchar(32) DEFAULT NULL COMMENT '设备编码',
  `type` varchar(32) DEFAULT NULL COMMENT '设备类型',
  `model` varchar(64) DEFAULT NULL COMMENT '设备型号',
  `specification` varchar(255) DEFAULT NULL COMMENT '规格参数',
  `maintenance_cycle` int DEFAULT NULL COMMENT '维护周期（单位：天）',
  `status` tinyint DEFAULT NULL COMMENT '状态：启用/禁用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='设备表';

-- ----------------------------
-- Records of sys_equipment
-- ----------------------------
INSERT INTO `sys_equipment` VALUES ('1', 'uuid-equip-001', '大型垃圾压缩机', 'EQP-COMP-001', '压缩机', 'YJ-2000', '压缩力2000KN，处理能力30吨/小时，功率45KW', '30', '1', '核心压缩设备，用于垃圾减容', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-13 08:33:20');
INSERT INTO `sys_equipment` VALUES ('2', 'uuid-equip-002', '皮带输送机', 'EQP-CONV-001', '输送设备', 'PD-1200', '输送宽度1200mm，输送速度0.5m/s，处理能力25吨/小时', '30', '1', '垃圾输送设备', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-13 08:33:20');
INSERT INTO `sys_equipment` VALUES ('3', 'uuid-equip-003', '负压除臭系统', 'EQP-DEOD-001', '环保设备', 'FY-3000', '处理风量30000m³/h，除臭效率95%，功率18.5KW', '45', '1', '站内空气净化设备', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-13 08:33:20');
INSERT INTO `sys_equipment` VALUES ('4', 'uuid-equip-004', '垃圾压缩机', 'EQP-COMP-002', '压缩机', 'YL-1500', '压缩力1500KN，处理能力20吨/小时，功率37KW', '30', '1', '中型压缩设备', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-13 08:33:20');
INSERT INTO `sys_equipment` VALUES ('5', 'uuid-equip-005', '自动称重系统', 'EQP-SCALE-001', '称重设备', 'XK3190', '最大称量80吨，分度值20kg，精度0.1%', '90', '1', '车辆进出称重', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-13 08:33:20');
INSERT INTO `sys_equipment` VALUES ('6', 'uuid-equip-006', '链板输送机', 'EQP-CONV-002', '输送设备', 'LB-1000', '输送宽度1000mm，输送速度0.3m/s，处理能力18吨/小时', '30', '1', '重载垃圾输送', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-13 08:33:20');
INSERT INTO `sys_equipment` VALUES ('7', 'uuid-equip-007', '光氧除臭设备', 'EQP-DEOD-002', '环保设备', 'GY-2000', '处理风量20000m³/h，UV光解，功率12KW', '90', '1', '光氧催化除臭', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-13 08:33:20');
INSERT INTO `sys_equipment` VALUES ('8', 'uuid-equip-008', '电子地磅', 'EQP-SCALE-002', '称重设备', 'SCS-100', '最大称量100吨，台面尺寸3×18m，精度0.1%', '90', '1', '垃圾车称重', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-13 08:33:20');
INSERT INTO `sys_equipment` VALUES ('9', 'uuid-equip-009', '智能消防系统', 'EQP-FIRE-001', '安全设备', 'XF-2000', '包含烟感、温感、自动喷淋，覆盖面积500㎡', '180', '1', '消防报警及灭火系统', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-13 08:33:20');
INSERT INTO `sys_equipment` VALUES ('10', 'uuid-equip-010', '站内综合控制系统', 'EQP-CONTROL-001', '控制设备', 'PLC-8000', '包含PLC控制柜、监控系统、数据采集模块', '365', '1', '转运站自动化控制', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 08:33:20', '2026-02-13 08:33:20');

-- ----------------------------
-- Table structure for sys_facility
-- ----------------------------
DROP TABLE IF EXISTS `sys_facility`;
CREATE TABLE `sys_facility` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_facility_id` char(36) DEFAULT NULL COMMENT '业务主键（UUID）',
  `name` varchar(64) DEFAULT NULL COMMENT '设施名称',
  `code` varchar(32) DEFAULT NULL COMMENT '设施编码',
  `type` varchar(32) DEFAULT NULL COMMENT '设施类型',
  `status` tinyint DEFAULT NULL COMMENT '状态：启用/禁用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='设施字典表';

-- ----------------------------
-- Records of sys_facility
-- ----------------------------
INSERT INTO `sys_facility` VALUES ('1', 'uuid-facility-001', '冲水系统', 'FLUSH_SYSTEM', '卫生洁具', '1', '包含冲水阀、水箱、管道等', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_facility` VALUES ('2', 'uuid-facility-002', '照明设施', 'LIGHTING', '电气设备', '1', '灯管、开关、应急灯等', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_facility` VALUES ('3', 'uuid-facility-003', '门锁', 'DOOR_LOCK', '五金配件', '1', '单间门锁、门把手', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_facility` VALUES ('4', 'uuid-facility-004', '洗手台', 'WASH_STAND', '卫生洁具', '1', '水龙头、台面、下水器', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_facility` VALUES ('5', 'uuid-facility-005', '无障碍扶手', 'BARRIER_FREE_HANDRAIL', '安全设施', '1', '卫生间无障碍专用扶手', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_facility` VALUES ('6', 'uuid-facility-006', '通风系统', 'VENTILATION', '暖通设备', '1', '排风扇、通风管道', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_facility` VALUES ('7', 'uuid-facility-007', '整体设施', 'OVERALL_FACILITY', '综合设施', '1', '包含水管、地面、墙面等整体维修', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_facility` VALUES ('8', 'uuid-facility-008', '烘手机', 'HAND_DRyer', '电气设备', '1', '全自动感应烘手机', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_facility` VALUES ('9', 'uuid-facility-009', '应急呼叫器', 'EMERGENCY_CALLER', '安全设施', '1', '无障碍卫生间应急呼叫装置', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_facility` VALUES ('10', 'uuid-facility-010', '防滑地面', 'ANTI_SLIP_FLOOR', '基础设施', '1', '卫生间防滑地砖及处理', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');

-- ----------------------------
-- Table structure for sys_garbage_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_garbage_type`;
CREATE TABLE `sys_garbage_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_garbage_type_id` char(36) DEFAULT NULL COMMENT '业务主键（UUID）',
  `name` varchar(64) DEFAULT NULL COMMENT '品类名称',
  `code` varchar(64) DEFAULT NULL COMMENT '品类编码',
  `status` tinyint DEFAULT NULL COMMENT '状态：启用/禁用',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='垃圾品类字典表';

-- ----------------------------
-- Records of sys_garbage_type
-- ----------------------------
INSERT INTO `sys_garbage_type` VALUES ('1', 'uuid-garbage-001', '可回收物', '001', '1', '纸类、塑料、玻璃等', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `sys_garbage_type` VALUES ('2', 'uuid-garbage-002', '厨余垃圾', '002', '1', '剩菜剩饭、果皮等', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `sys_garbage_type` VALUES ('3', 'uuid-garbage-003', '有害垃圾', '003', '1', '电池、灯管、药品等', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `sys_garbage_type` VALUES ('4', 'uuid-garbage-004', '其他垃圾', '004', '1', '砖瓦陶瓷、一次性餐具等', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `sys_garbage_type` VALUES ('5', 'uuid-garbage-005', '大件垃圾', '005', '1', '家具、家电、床垫等', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `sys_garbage_type` VALUES ('6', 'uuid-garbage-006', '装修垃圾', '006', '1', '水泥、沙子、瓷砖等', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `sys_garbage_type` VALUES ('7', 'uuid-garbage-007', '绿化垃圾', '007', '1', '树枝、落叶、草屑等', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');

-- ----------------------------
-- Table structure for sys_green_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_green_type`;
CREATE TABLE `sys_green_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `green_type_id` char(36) DEFAULT NULL COMMENT '主键（UUID）',
  `green_name` varchar(64) DEFAULT NULL COMMENT '绿化品类名称',
  `maintenance_require` varchar(512) DEFAULT NULL COMMENT '养护要求',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述',
  `status` tinyint DEFAULT '1' COMMENT '状态（可选值：0-禁用/1-启用）',
  `sort` int DEFAULT '0' COMMENT '排序值',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='绿化品类字典表【通用复用】';

-- ----------------------------
-- Records of sys_green_type
-- ----------------------------
INSERT INTO `sys_green_type` VALUES ('1', 'uuid-green-001', '乔木', '每季度修剪1次，每月浇水2次，每年施肥1次，及时清除病虫害', '高大乔木（香樟、悬铃木、银杏等），主要用于公园行道、景观区绿化', '1', '1', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:33:56', '2026-02-12 16:33:56');
INSERT INTO `sys_green_type` VALUES ('2', 'uuid-green-002', '灌木', '每月修剪1次，每周浇水1次，每季度施肥1次，定期除草', '低矮灌木（冬青、红叶石楠、金森女贞等），用于绿篱、花坛绿化', '1', '2', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:33:56', '2026-02-12 16:33:56');
INSERT INTO `sys_green_type` VALUES ('3', 'uuid-green-003', '草坪', '每周修剪1次，每日浇水1次（夏季）/每3日1次（冬季），每季度补播1次', '冷季型/暖季型草坪，用于公园休闲区、广场绿化', '1', '3', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:33:56', '2026-02-12 16:33:56');
INSERT INTO `sys_green_type` VALUES ('4', 'uuid-green-004', '花卉', '每日浇水1次，每半月施肥1次，及时更换枯萎花卉，定期除虫', '时令花卉（月季、菊花、三角梅等），用于花坛、花境装饰', '1', '4', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:33:56', '2026-02-12 16:33:56');
INSERT INTO `sys_green_type` VALUES ('5', 'uuid-green-005', '竹类', '每半年修剪1次，每月浇水1次，每年松土1次，防止过度蔓延', '毛竹、紫竹等竹类植物，用于公园竹林景观区', '1', '5', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:33:56', '2026-02-12 16:33:56');
INSERT INTO `sys_green_type` VALUES ('6', 'uuid-green-006', '水生植物', '每月清理水面杂物，每季度换水1次，及时清除腐烂植株，控制生长范围', '荷花、睡莲、菖蒲等，用于公园人工湖、溪流绿化', '1', '6', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:33:56', '2026-02-12 16:33:56');
INSERT INTO `sys_green_type` VALUES ('7', 'uuid-green-007', '藤本植物', '每月牵引1次，每季度修剪1次，每周浇水1次，每半年施肥1次', '爬山虎、紫藤、凌霄等，用于廊架、墙体绿化', '1', '7', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:33:56', '2026-02-12 16:33:56');
INSERT INTO `sys_green_type` VALUES ('8', 'uuid-green-008', '地被植物', '每季度除草1次，每月浇水1次，每年补植1次，保持覆盖率95%以上', '麦冬、沿阶草、酢浆草等，用于林下、路边地面覆盖', '1', '8', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:33:56', '2026-02-12 16:33:56');

-- ----------------------------
-- Table structure for sys_handle_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_handle_status`;
CREATE TABLE `sys_handle_status` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_handle_status_id` char(36) DEFAULT NULL COMMENT '业务主键（UUID）',
  `name` varchar(64) DEFAULT NULL COMMENT '处置状态名称（可选值：待处置/处置中/已办结/已退回/超时未处置）',
  `code` varchar(32) DEFAULT NULL COMMENT '处置状态编码',
  `status` tinyint DEFAULT '1' COMMENT '状态（可选值：0-禁用/1-启用）',
  `sort` int DEFAULT '0' COMMENT '排序值',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='处置状态字典表【通用复用】';

-- ----------------------------
-- Records of sys_handle_status
-- ----------------------------
INSERT INTO `sys_handle_status` VALUES ('1', 'uuid-handle-001', '待处置', 'PENDING_HANDLE', '1', '1', '问题已上报，等待派单处置', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-24 15:57:00', '2026-02-24 15:57:00');
INSERT INTO `sys_handle_status` VALUES ('2', 'uuid-handle-002', '处置中', 'HANDLING', '1', '2', '已派单，处置人员正在处理', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-24 15:57:00', '2026-02-24 15:57:00');
INSERT INTO `sys_handle_status` VALUES ('3', 'uuid-handle-003', '已办结', 'FINISHED', '1', '3', '问题处置完成，结果确认', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-24 15:57:00', '2026-02-24 15:57:00');
INSERT INTO `sys_handle_status` VALUES ('4', 'uuid-handle-004', '已退回', 'RETURNED', '1', '4', '处置结果不达标，退回重新处理', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-24 15:57:00', '2026-02-24 15:57:00');
INSERT INTO `sys_handle_status` VALUES ('5', 'uuid-handle-005', '超时未处置', 'TIMEOUT', '1', '5', '超出规定处置时长未完成', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-24 15:57:00', '2026-02-24 15:57:00');

-- ----------------------------
-- Table structure for sys_institution_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_institution_type`;
CREATE TABLE `sys_institution_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_institution_type_id` char(36) DEFAULT NULL COMMENT '业务主键（UUID）',
  `name` varchar(64) DEFAULT NULL COMMENT '类型名称（可选值：学校/医院/政府机关/公园/图书馆/体育馆/博物馆/车站）',
  `code` varchar(32) DEFAULT NULL COMMENT '类型编码',
  `status` tinyint DEFAULT NULL COMMENT '状态（可选值：0-禁用/1-启用）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='机构类型字典表';

-- ----------------------------
-- Records of sys_institution_type
-- ----------------------------
INSERT INTO `sys_institution_type` VALUES ('1', 'uuid-inst-type-001', '学校', 'SCHOOL', '1', '中小学/高校等教育机构', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58', '2026-02-12 16:20:58');
INSERT INTO `sys_institution_type` VALUES ('2', 'uuid-inst-type-002', '医院', 'HOSPITAL', '1', '综合医院/专科医院等医疗机构', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58', '2026-02-12 16:20:58');
INSERT INTO `sys_institution_type` VALUES ('3', 'uuid-inst-type-003', '政府机关', 'GOVERNMENT', '1', '各级政府办公场所', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58', '2026-02-12 16:20:58');
INSERT INTO `sys_institution_type` VALUES ('4', 'uuid-inst-type-004', '公园', 'PARK', '1', '城市公园/湿地公园等', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58', '2026-02-12 16:20:58');
INSERT INTO `sys_institution_type` VALUES ('5', 'uuid-inst-type-005', '图书馆', 'LIBRARY', '1', '公共图书馆/少儿图书馆等', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58', '2026-02-12 16:20:58');
INSERT INTO `sys_institution_type` VALUES ('6', 'uuid-inst-type-006', '体育馆', 'GYMNASIUM', '1', '综合体育馆/专项场馆等', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58', '2026-02-12 16:20:58');
INSERT INTO `sys_institution_type` VALUES ('7', 'uuid-inst-type-007', '博物馆', 'MUSEUM', '1', '综合博物馆/专题博物馆等', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58', '2026-02-12 16:20:58');
INSERT INTO `sys_institution_type` VALUES ('8', 'uuid-inst-type-008', '车站', 'STATION', '1', '火车站/汽车站/地铁站等交通枢纽', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:20:58', '2026-02-12 16:20:58');

-- ----------------------------
-- Table structure for sys_job_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_type`;
CREATE TABLE `sys_job_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_job_type_id` char(36) DEFAULT NULL COMMENT '业务主键（UUID）',
  `name` varchar(64) DEFAULT NULL COMMENT '岗位名称（可选值：清扫工/保洁员/督导员/驾驶员/维修工/管理员/考核员/转运工）',
  `code` varchar(32) DEFAULT NULL COMMENT '岗位编码',
  `status` tinyint DEFAULT NULL COMMENT '状态（可选值：0-禁用/1-启用）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='岗位类型字典表';

-- ----------------------------
-- Records of sys_job_type
-- ----------------------------
INSERT INTO `sys_job_type` VALUES ('1', 'uuid-job-001', '清扫工', 'QS001', '1', '道路清扫作业人员', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-26 08:57:08');
INSERT INTO `sys_job_type` VALUES ('2', 'uuid-job-002', '保洁员', 'BJ001', '1', '公共区域保洁人员', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-26 08:57:11');
INSERT INTO `sys_job_type` VALUES ('3', 'uuid-job-003', '驾驶员', 'JS001', '1', '环卫车辆驾驶员', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-26 08:57:17');
INSERT INTO `sys_job_type` VALUES ('4', 'uuid-job-004', '管理员', 'GL001', '1', '片区管理人员', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-26 08:57:20');

-- ----------------------------
-- Table structure for sys_maintain_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_maintain_status`;
CREATE TABLE `sys_maintain_status` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_maintain_status_id` char(36) DEFAULT NULL COMMENT '业务主键（UUID）',
  `name` varchar(64) DEFAULT NULL COMMENT '维护状态名称（可选值：待维护/维护中/已完成/已验收/退回整改）',
  `code` varchar(32) DEFAULT NULL COMMENT '维护状态编码',
  `status` tinyint DEFAULT '1' COMMENT '状态（可选值：0-禁用/1-启用）',
  `sort` int DEFAULT '0' COMMENT '排序值',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='维护状态字典表【通用复用】';

-- ----------------------------
-- Records of sys_maintain_status
-- ----------------------------
INSERT INTO `sys_maintain_status` VALUES ('1', 'uuid-maintain-001', '待维护', 'PENDING_MAINTAIN', '1', '1', '问题已上报，等待维护人员处理', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-24 15:57:00', '2026-02-24 15:57:00');
INSERT INTO `sys_maintain_status` VALUES ('2', 'uuid-maintain-002', '维护中', 'MAINTAINING', '1', '2', '维护人员已接单，正在处理问题', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-24 15:57:00', '2026-02-24 15:57:00');
INSERT INTO `sys_maintain_status` VALUES ('3', 'uuid-maintain-003', '已完成', 'COMPLETED', '1', '3', '问题已维护完成，待验收', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-24 15:57:00', '2026-02-24 15:57:00');
INSERT INTO `sys_maintain_status` VALUES ('4', 'uuid-maintain-004', '已验收', 'ACCEPTED', '1', '4', '维护结果通过验收，问题闭环', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-24 15:57:00', '2026-02-24 15:57:00');
INSERT INTO `sys_maintain_status` VALUES ('5', 'uuid-maintain-005', '退回整改', 'RETURNED', '1', '5', '维护结果不达标，退回重新整改', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-24 15:57:00', '2026-02-24 15:57:00');

-- ----------------------------
-- Table structure for sys_maintenance_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_maintenance_type`;
CREATE TABLE `sys_maintenance_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `maintenance_type_id` char(36) DEFAULT NULL COMMENT '主键（UUID）',
  `maintenance_name` varchar(32) DEFAULT NULL COMMENT '维护类型名称',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='维护类型字典表【通用复用】';

-- ----------------------------
-- Records of sys_maintenance_type
-- ----------------------------
INSERT INTO `sys_maintenance_type` VALUES ('1', 'uuid-maintenance-type-001', '日常保养', '车辆常规检查、机油更换、车身清洁、轮胎补气等基础维护', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:54:01');
INSERT INTO `sys_maintenance_type` VALUES ('2', 'uuid-maintenance-type-002', '故障维修', '车辆故障排查、零部件更换、功能修复，如发动机/液压故障', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:54:05');
INSERT INTO `sys_maintenance_type` VALUES ('3', 'uuid-maintenance-type-003', '定期检修', '按周期进行的深度检测和维护（月度/季度/年度检修）', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:54:09');
INSERT INTO `sys_maintenance_type` VALUES ('4', 'uuid-maintenance-type-004', '专项维保', '针对特定部件的专项维护（如液压系统、清扫刷、储水箱）', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:54:14');
INSERT INTO `sys_maintenance_type` VALUES ('5', 'uuid-maintenance-type-005', '年检准备', '为车辆年检进行的预检、故障整改、资料准备', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:54:18');
INSERT INTO `sys_maintenance_type` VALUES ('6', 'uuid-maintenance-type-006', '应急维修', '车辆作业中突发故障的紧急维修处理', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:54:24');

-- ----------------------------
-- Table structure for sys_monitor_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_monitor_status`;
CREATE TABLE `sys_monitor_status` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `monitor_status_id` char(36) DEFAULT NULL COMMENT '主键（UUID）',
  `monitor_status_name` varchar(32) DEFAULT NULL COMMENT '监测状态名称：待监测/已完成/预警',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述',
  `status` tinyint DEFAULT '1' COMMENT '状态（可选值：0-禁用/1-启用）',
  `sort` int DEFAULT '0' COMMENT '排序值',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='监测状态字典表【通用复用】';

-- ----------------------------
-- Records of sys_monitor_status
-- ----------------------------
INSERT INTO `sys_monitor_status` VALUES ('1', 'uuid-monitor-status-001', '待监测', '已制定监测计划，尚未开展现场监测', '1', '1', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:48:14', '2026-02-12 16:48:14');
INSERT INTO `sys_monitor_status` VALUES ('2', 'uuid-monitor-status-002', '已完成', '按计划完成现场监测，数据已录入并分析', '1', '2', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:48:14', '2026-02-12 16:48:14');
INSERT INTO `sys_monitor_status` VALUES ('3', 'uuid-monitor-status-003', '预警', '监测数据超标，触发预警机制', '1', '3', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:48:14', '2026-02-12 16:48:14');
INSERT INTO `sys_monitor_status` VALUES ('4', 'uuid-monitor-status-004', '监测中', '正在开展现场监测工作，暂未出结果', '1', '4', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:48:14', '2026-02-12 16:48:14');
INSERT INTO `sys_monitor_status` VALUES ('5', 'uuid-monitor-status-005', '超时', '超出计划监测时间仍未完成监测', '1', '5', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:48:14', '2026-02-12 16:48:14');
INSERT INTO `sys_monitor_status` VALUES ('6', 'uuid-monitor-status-006', '数据异常', '监测数据异常，需重新监测', '1', '6', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:48:14', '2026-02-12 16:48:14');
INSERT INTO `sys_monitor_status` VALUES ('7', 'uuid-monitor-status-007', '已整改', '预警后完成整改，复测达标', '1', '7', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:48:14', '2026-02-12 16:48:14');
INSERT INTO `sys_monitor_status` VALUES ('8', 'uuid-monitor-status-008', '暂停', '因天气/施工等原因暂停监测', '1', '8', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:48:14', '2026-02-12 16:48:14');

-- ----------------------------
-- Table structure for sys_monitor_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_monitor_type`;
CREATE TABLE `sys_monitor_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `monitor_type_id` char(36) DEFAULT NULL COMMENT '主键（UUID）',
  `monitor_name` varchar(32) DEFAULT NULL COMMENT '监测类型名称：水质/异味/水生植物',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述',
  `status` tinyint DEFAULT '1' COMMENT '状态（可选值：0-禁用/1-启用）',
  `sort` int DEFAULT '0' COMMENT '排序值',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='监测类型字典表【通用复用】';

-- ----------------------------
-- Records of sys_monitor_type
-- ----------------------------
INSERT INTO `sys_monitor_type` VALUES ('1', 'uuid-monitor-type-001', '水质', '监测pH值、溶解氧、氨氮、COD等水质核心指标', '1', '1', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:48:14', '2026-02-12 16:48:14');
INSERT INTO `sys_monitor_type` VALUES ('2', 'uuid-monitor-type-002', '异味', '监测河道水体/周边空气异味等级、挥发性有机物等指标', '1', '2', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:48:14', '2026-02-12 16:48:14');
INSERT INTO `sys_monitor_type` VALUES ('3', 'uuid-monitor-type-003', '水生植物', '监测水生植物种类、覆盖面积、生长密度等指标', '1', '3', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:48:14', '2026-02-12 16:48:14');
INSERT INTO `sys_monitor_type` VALUES ('4', 'uuid-monitor-type-004', '垃圾存量', '监测河道水面/岸线垃圾存量、分布等指标', '1', '4', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:48:14', '2026-02-12 16:48:14');
INSERT INTO `sys_monitor_type` VALUES ('5', 'uuid-monitor-type-005', '流速', '监测河道水流速度、流量等水文指标', '1', '5', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:48:14', '2026-02-12 16:48:14');
INSERT INTO `sys_monitor_type` VALUES ('6', 'uuid-monitor-type-006', '底泥', '监测河道底泥厚度、污染物含量等指标', '1', '6', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:48:14', '2026-02-12 16:48:14');
INSERT INTO `sys_monitor_type` VALUES ('7', 'uuid-monitor-type-007', '综合', '水质+异味+水生植物等多维度综合监测', '1', '7', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:48:14', '2026-02-12 16:48:14');
INSERT INTO `sys_monitor_type` VALUES ('8', 'uuid-monitor-type-008', '专项', '针对突发污染的专项监测', '1', '8', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:48:14', '2026-02-12 16:48:14');

-- ----------------------------
-- Table structure for sys_operation_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_status`;
CREATE TABLE `sys_operation_status` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_operation_status_id` char(36) DEFAULT NULL COMMENT '业务主键（UUID）',
  `name` varchar(64) DEFAULT NULL COMMENT '状态名称（如正常运营/暂停开放/维修中）',
  `code` varchar(32) DEFAULT NULL COMMENT '状态编码',
  `status` tinyint DEFAULT NULL COMMENT '状态：启用/禁用',
  `sort` int DEFAULT NULL COMMENT '排序号',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='运营状态字典表';

-- ----------------------------
-- Records of sys_operation_status
-- ----------------------------
INSERT INTO `sys_operation_status` VALUES ('1', 'uuid-op-status-001', '正常运营', 'NORMAL', '1', '1', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_operation_status` VALUES ('2', 'uuid-op-status-002', '维修中（部分开放）', 'REPAIRING', '1', '2', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_operation_status` VALUES ('3', 'uuid-op-status-003', '暂停开放', 'SUSPENDED', '1', '3', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_operation_status` VALUES ('4', 'uuid-op-status-004', '临时关闭', 'TEMP_CLOSED', '1', '4', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_operation_status` VALUES ('5', 'uuid-op-status-005', '改造升级', 'UPGRADING', '0', '5', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');

-- ----------------------------
-- Table structure for sys_person_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_person_status`;
CREATE TABLE `sys_person_status` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_person_status_id` char(36) DEFAULT NULL COMMENT '业务主键（UUID）',
  `name` varchar(64) DEFAULT NULL COMMENT '状态名称（可选值：在岗/休假/请假/离职/待入职/调岗/停薪留职）',
  `code` varchar(32) DEFAULT NULL COMMENT '状态编码',
  `status` tinyint DEFAULT NULL COMMENT '状态（可选值：0-禁用/1-启用）',
  `sort` int DEFAULT NULL COMMENT '排序号',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='人员状态字典表';

-- ----------------------------
-- Records of sys_person_status
-- ----------------------------
INSERT INTO `sys_person_status` VALUES ('1', 'uuid-pstatus-001', '在岗', 'ZG001', '1', '1', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-26 08:57:50');
INSERT INTO `sys_person_status` VALUES ('2', 'uuid-pstatus-002', '休假', 'XJ001', '1', '2', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-26 08:57:53');
INSERT INTO `sys_person_status` VALUES ('3', 'uuid-pstatus-003', '离职', 'LZ001', '1', '3', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-26 08:57:56');
INSERT INTO `sys_person_status` VALUES ('4', 'uuid-pstatus-004', '请假', 'QJ001', '1', '4', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-26 08:58:01');

-- ----------------------------
-- Table structure for sys_plan_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_plan_status`;
CREATE TABLE `sys_plan_status` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_plan_status_id` char(36) DEFAULT NULL COMMENT '业务主键（UUID）',
  `name` varchar(64) DEFAULT NULL COMMENT '状态名称（如未执行/执行中/已完成/异常）',
  `code` varchar(64) DEFAULT NULL COMMENT '状态编码',
  `status` tinyint DEFAULT NULL COMMENT '状态：启用/禁用',
  `sort` int DEFAULT NULL COMMENT '排序号',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='计划状态字典表';

-- ----------------------------
-- Records of sys_plan_status
-- ----------------------------
INSERT INTO `sys_plan_status` VALUES ('1', 'uuid-plan-status-001', '未执行', '001', '1', '1', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `sys_plan_status` VALUES ('2', 'uuid-plan-status-002', '执行中', '002', '1', '2', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `sys_plan_status` VALUES ('3', 'uuid-plan-status-003', '已完成', '003', '1', '3', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `sys_plan_status` VALUES ('4', 'uuid-plan-status-004', '异常', '004', '1', '4', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `sys_plan_status` VALUES ('5', 'uuid-plan-status-005', '已取消', '005', '1', '5', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `sys_plan_status` VALUES ('6', 'uuid-plan-status-006', '暂停执行', '006', '1', '6', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');

-- ----------------------------
-- Table structure for sys_problem_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_problem_type`;
CREATE TABLE `sys_problem_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_problem_type_id` char(36) DEFAULT NULL COMMENT '业务主键（UUID）',
  `name` varchar(64) DEFAULT NULL COMMENT '问题类型名称（可选值：污水排放/垃圾堆积/水生植物泛滥/设施损坏/保洁不达标/收运不及时/定位异常/投诉反馈/其他问题）',
  `code` varchar(32) DEFAULT NULL COMMENT '问题类型编码',
  `status` tinyint DEFAULT '1' COMMENT '状态（可选值：0-禁用/1-启用）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='问题类型字典表【通用复用】';

-- ----------------------------
-- Records of sys_problem_type
-- ----------------------------
INSERT INTO `sys_problem_type` VALUES ('1', 'uuid-problem-type-001', '垃圾堆积', 'PROB-001', '1', '各类垃圾未及时清理堆积', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `sys_problem_type` VALUES ('2', 'uuid-problem-type-002', '设施损坏', 'PROB-002', '1', '清扫工具/设备故障、损坏', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `sys_problem_type` VALUES ('3', 'uuid-problem-type-003', '人员缺勤', 'PROB-003', '1', '清扫人员未到岗、缺勤', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `sys_problem_type` VALUES ('4', 'uuid-problem-type-004', '保洁不达标', 'PROB-004', '1', '未达到既定清扫标准', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `sys_problem_type` VALUES ('5', 'uuid-problem-type-005', '安全隐患', 'PROB-005', '1', '路面杂物引发的安全风险', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `sys_problem_type` VALUES ('6', 'uuid-problem-type-006', '污水排放', 'PROB-006', '1', '路面污水横流未清理', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `sys_problem_type` VALUES ('7', 'uuid-problem-type-007', '水生植物泛滥', 'PROB-007', '1', '道路两侧水生植物疯长', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `sys_problem_type` VALUES ('8', 'uuid-problem-type-008', '收运不及时', 'PROB-008', '1', '垃圾收运频次不足、延迟', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `sys_problem_type` VALUES ('9', 'uuid-problem-type-009', '定位异常', 'PROB-009', '1', '作业人员/设备定位偏离', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `sys_problem_type` VALUES ('10', 'uuid-problem-type-010', '投诉反馈', 'PROB-010', '1', '市民投诉道路清扫问题', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');

-- ----------------------------
-- Table structure for sys_review_result
-- ----------------------------
DROP TABLE IF EXISTS `sys_review_result`;
CREATE TABLE `sys_review_result` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `review_result_id` char(36) DEFAULT NULL COMMENT '主键（UUID）',
  `review_result_name` varchar(32) DEFAULT NULL COMMENT '复核结果名称：通过/不通过',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述',
  `status` tinyint DEFAULT '1' COMMENT '状态（可选值：0-禁用/1-启用）',
  `sort` int DEFAULT '0' COMMENT '排序值',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='复核结果字典表【通用复用】';

-- ----------------------------
-- Records of sys_review_result
-- ----------------------------
INSERT INTO `sys_review_result` VALUES ('1', 'uuid-review-001', '通过', '问题处置符合要求，整改到位，复核通过', '1', '1', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:37:37', '2026-02-12 16:37:37');
INSERT INTO `sys_review_result` VALUES ('2', 'uuid-review-002', '不通过', '问题处置不到位，整改未达标，复核不通过', '1', '2', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:37:37', '2026-02-12 16:37:37');
INSERT INTO `sys_review_result` VALUES ('3', 'uuid-review-003', '部分通过', '核心问题处置到位，次要问题未整改，复核部分通过', '1', '3', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:37:37', '2026-02-12 16:37:37');
INSERT INTO `sys_review_result` VALUES ('4', 'uuid-review-004', '待复核', '整改完成待复核人员现场核查', '1', '4', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:37:37', '2026-02-12 16:37:37');
INSERT INTO `sys_review_result` VALUES ('5', 'uuid-review-005', '复核中', '正在进行现场复核，暂未出结果', '1', '5', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:37:37', '2026-02-12 16:37:37');
INSERT INTO `sys_review_result` VALUES ('6', 'uuid-review-006', '复核超时', '超出复核时限未完成复核', '1', '6', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:37:37', '2026-02-12 16:37:37');
INSERT INTO `sys_review_result` VALUES ('7', 'uuid-review-007', '重新复核', '首次复核不通过，整改后重新复核', '1', '7', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:37:37', '2026-02-12 16:37:37');
INSERT INTO `sys_review_result` VALUES ('8', 'uuid-review-008', '复核取消', '因特殊原因取消本次复核', '1', '8', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 16:37:37', '2026-02-12 16:37:37');

-- ----------------------------
-- Table structure for sys_review_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_review_status`;
CREATE TABLE `sys_review_status` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `review_status_id` char(36) DEFAULT NULL COMMENT '主键（UUID）',
  `review_status_name` varchar(32) DEFAULT NULL COMMENT '审核状态名称：待审核/通过/不通过',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述',
  `status` tinyint DEFAULT '1' COMMENT '状态（可选值：0-禁用/1-启用）',
  `sort` int DEFAULT '0' COMMENT '排序值',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='审核状态字典表【通用复用】';

-- ----------------------------
-- Records of sys_review_status
-- ----------------------------
INSERT INTO `sys_review_status` VALUES ('1', 'review_001', '待审核', '申请/记录等待审核', '1', '1', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-12 17:06:16');
INSERT INTO `sys_review_status` VALUES ('2', 'review_002', '通过', '审核结果为通过', '1', '2', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-12 17:06:16');
INSERT INTO `sys_review_status` VALUES ('3', 'review_003', '不通过', '审核结果为不通过', '1', '3', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-12 17:06:16');

-- ----------------------------
-- Table structure for sys_road
-- ----------------------------
DROP TABLE IF EXISTS `sys_road`;
CREATE TABLE `sys_road` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `road_id` varchar(64) DEFAULT NULL COMMENT '道路主键（UUID）',
  `road_name` varchar(128) DEFAULT NULL COMMENT '道路名称',
  `area_code` varchar(64) DEFAULT NULL COMMENT '关联sys_area.area_code',
  `road_level` varchar(32) DEFAULT NULL COMMENT '道路等级',
  `length` decimal(8,2) DEFAULT NULL COMMENT '长度，单位：公里',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='道路表';

-- ----------------------------
-- Records of sys_road
-- ----------------------------
INSERT INTO `sys_road` VALUES ('1', 'uuid-road-001', '朝阳大道', '1001', '主干道', '8.50', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `sys_road` VALUES ('2', 'uuid-road-002', '静安路', '1002', '次干道', '5.20', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `sys_road` VALUES ('3', 'uuid-road-003', '天河路', '1003', '支路', '3.80', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `sys_road` VALUES ('4', 'uuid-road-004', '南山路', '1004', '主干道', '7.60', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `sys_road` VALUES ('5', 'uuid-road-005', '西湖路', '1005', '次干道', '4.90', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `sys_road` VALUES ('6', 'uuid-road-006', '夫子庙路', '1006', '支路', '2.70', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `sys_road` VALUES ('7', 'uuid-road-007', '春熙路', '1007', '主干道', '6.30', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `sys_road` VALUES ('8', 'uuid-road-008', '王府井大街', '1001', '主干道', '4.10', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');

-- ----------------------------
-- Table structure for sys_route
-- ----------------------------
DROP TABLE IF EXISTS `sys_route`;
CREATE TABLE `sys_route` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_route_id` char(36) DEFAULT NULL COMMENT '业务主键（UUID）',
  `name` varchar(128) DEFAULT NULL COMMENT '路线名称',
  `code` varchar(32) DEFAULT NULL COMMENT '路线编码',
  `start_point` json DEFAULT NULL COMMENT '起点（含经度、纬度、具体地址）',
  `end_point` json DEFAULT NULL COMMENT '终点（含经度、纬度、具体地址）',
  `route_points` json DEFAULT NULL COMMENT '路线经纬度坐标（数组格式：[{"lng":116.40,"lat":39.91},{"lng":116.41,"lat":39.92}]）',
  `length` decimal(10,2) DEFAULT NULL COMMENT '路线长度（单位：公里）',
  `status` tinyint DEFAULT NULL COMMENT '状态（可选值：0-禁用/1-启用）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='路线表';

-- ----------------------------
-- Records of sys_route
-- ----------------------------
INSERT INTO `sys_route` VALUES ('1', 'uuid-route-001', '城东主干道清扫路线', 'LX-QS001', '{\"lat\": 39.92, \"lng\": 116.45, \"address\": \"北京市东城区建国门南大街1号\"}', '{\"lat\": 39.93, \"lng\": 116.48, \"address\": \"北京市东城区朝阳门北大街8号\"}', '[{\"lat\": 39.92, \"lng\": 116.45}, {\"lat\": 39.92, \"lng\": 116.46}, {\"lat\": 39.93, \"lng\": 116.47}, {\"lat\": 39.93, \"lng\": 116.48}]', '2.50', '1', '城东核心道路日常清扫', '', '', '', '', 'admin', 'admin', '\0');
INSERT INTO `sys_route` VALUES ('2', 'uuid-route-002', '城西居民区清运路线', 'LX-QY001', '{\"lat\": 39.9, \"lng\": 116.38, \"address\": \"北京市西城区西直门外大街1号\"}', '{\"lat\": 39.91, \"lng\": 116.35, \"address\": \"北京市西城区垃圾转运站\"}', '[{\"lat\": 39.9, \"lng\": 116.38}, {\"lat\": 39.9, \"lng\": 116.37}, {\"lat\": 39.91, \"lng\": 116.36}, {\"lat\": 39.91, \"lng\": 116.35}]', '3.20', '1', '城西居民区生活垃圾清运', '', '', '', '', 'admin', 'admin', '\0');
INSERT INTO `sys_route` VALUES ('3', 'uuid-route-003', '城南洒水降尘路线', 'LX-SS001', '{\"lat\": 39.88, \"lng\": 116.42, \"address\": \"北京市丰台区南三环中路1号\"}', '{\"lat\": 39.89, \"lng\": 116.44, \"address\": \"北京市丰台区南四环东路8号\"}', '[{\"lat\": 39.88, \"lng\": 116.42}, {\"lat\": 39.88, \"lng\": 116.43}, {\"lat\": 39.89, \"lng\": 116.44}]', '4.80', '1', '城南主干道夏季洒水降尘', '', '', '', '', 'admin', 'admin', '\0');
INSERT INTO `sys_route` VALUES ('4', 'uuid-route-004', '城北洗扫作业路线', 'LX-XS001', '{\"lat\": 39.95, \"lng\": 116.49, \"address\": \"北京市朝阳区北三环东路1号\"}', '{\"lat\": 39.96, \"lng\": 116.51, \"address\": \"北京市朝阳区北苑路8号\"}', '[{\"lat\": 39.95, \"lng\": 116.49}, {\"lat\": 39.95, \"lng\": 116.5}, {\"lat\": 39.96, \"lng\": 116.51}]', '3.60', '1', '城北道路洗扫作业', '', '', '', '', 'admin', 'admin', '\0');
INSERT INTO `sys_route` VALUES ('5', 'uuid-route-005', '市中心巡查路线', 'LX-XC001', '{\"lat\": 39.91, \"lng\": 116.4, \"address\": \"北京市东城区天安门广场东侧\"}', '{\"lat\": 39.9, \"lng\": 116.41, \"address\": \"北京市西城区西单北大街1号\"}', '[{\"lat\": 39.91, \"lng\": 116.4}, {\"lat\": 39.9, \"lng\": 116.4}, {\"lat\": 39.9, \"lng\": 116.41}]', '2.10', '1', '市中心环卫作业巡查', '', '', '', '', 'admin', 'admin', '\0');
INSERT INTO `sys_route` VALUES ('6', 'uuid-route-006', '城郊结合部转运路线', 'LX-ZY001', '{\"lat\": 39.85, \"lng\": 116.55, \"address\": \"北京市通州区新华大街1号\"}', '{\"lat\": 39.87, \"lng\": 116.52, \"address\": \"北京市通州区大型垃圾处理厂\"}', '[{\"lat\": 39.85, \"lng\": 116.55}, {\"lat\": 39.86, \"lng\": 116.54}, {\"lat\": 39.87, \"lng\": 116.52}]', '5.80', '1', '城郊垃圾长途转运', '', '', '', '', 'admin', 'admin', '\0');

-- ----------------------------
-- Table structure for sys_schedule
-- ----------------------------
DROP TABLE IF EXISTS `sys_schedule`;
CREATE TABLE `sys_schedule` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `schedule_id` char(36) DEFAULT NULL COMMENT '主键（UUID）',
  `user_id` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `job_type_id` varchar(64) DEFAULT NULL COMMENT '关联sys_job_type.id',
  `team_id` varchar(64) DEFAULT NULL COMMENT '关联sys_team.id',
  `area_code` varchar(64) DEFAULT NULL COMMENT '关联sys_area.area_code',
  `cycle` varchar(8) DEFAULT NULL COMMENT '排班周期：日/周/月',
  `work_time_period` varchar(64) DEFAULT NULL COMMENT '作业时段',
  `schedule_status_id` varchar(64) DEFAULT NULL COMMENT '关联sys_schedule_status.id',
  `swap_status` varchar(16) DEFAULT NULL COMMENT '换班申请状态：无/待审核/已通过/已拒绝',
  `swap_applicant_id` varchar(64) DEFAULT NULL COMMENT '换班申请人ID，关联sys_user.id',
  `swap_target_id` varchar(64) DEFAULT NULL COMMENT '被换班人员ID，关联sys_user.id',
  `swap_date` date DEFAULT NULL COMMENT '换班日期',
  `swap_reason` varchar(1024) DEFAULT NULL COMMENT '换班理由',
  `review_result` varchar(8) DEFAULT NULL COMMENT '审核结果：通过/拒绝',
  `review_opinion` varchar(1024) DEFAULT NULL COMMENT '审核意见',
  `coverage_rate` decimal(5,2) DEFAULT NULL COMMENT '排班覆盖率',
  `vacancy_reminder` varchar(8) DEFAULT NULL COMMENT '岗位空缺提醒：是/否',
  `swap_apply_count` int DEFAULT '0' COMMENT '换班申请数',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `create_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='排班计划表';

-- ----------------------------
-- Records of sys_schedule
-- ----------------------------
INSERT INTO `sys_schedule` VALUES ('1', 'sch_001', 'user_001', 'job_001', 'team_001', 'area_001', '日', '08:00-18:00', 'sch_status_002', '无', '', '', null, '', '', '无', '98.50', '否', '0', null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-12 17:06:16');
INSERT INTO `sys_schedule` VALUES ('2', 'sch_002', 'user_002', 'job_002', 'team_001', 'area_002', '日', '08:00-18:00', 'sch_status_001', '待审核', 'user_002', 'user_003', '2024-05-20', '家中有事需换班', '', '', '95.00', '否', '1', null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-12 17:06:16');

-- ----------------------------
-- Table structure for sys_schedule_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_schedule_status`;
CREATE TABLE `sys_schedule_status` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `schedule_status_id` char(36) DEFAULT NULL COMMENT '主键（UUID）',
  `schedule_status_name` varchar(32) DEFAULT NULL COMMENT '排班状态名称：待执行/执行中/已完成/已取消',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述',
  `status` tinyint DEFAULT '1' COMMENT '状态（可选值：0-禁用/1-启用）',
  `sort` int DEFAULT '0' COMMENT '排序值',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='排班状态字典表【通用复用】';

-- ----------------------------
-- Records of sys_schedule_status
-- ----------------------------
INSERT INTO `sys_schedule_status` VALUES ('1', 'sch_status_001', '待执行', '排班计划尚未开始执行', '1', '1', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-12 17:06:16');
INSERT INTO `sys_schedule_status` VALUES ('2', 'sch_status_002', '执行中', '排班计划正在执行', '1', '2', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-12 17:06:16');
INSERT INTO `sys_schedule_status` VALUES ('3', 'sch_status_003', '已完成', '排班计划执行完毕', '1', '3', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-12 17:06:16');
INSERT INTO `sys_schedule_status` VALUES ('4', 'sch_status_004', '已取消', '排班计划已作废', '1', '4', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-12 17:06:16', '2026-02-12 17:06:16');

-- ----------------------------
-- Table structure for sys_task_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_task_type`;
CREATE TABLE `sys_task_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_task_type_id` char(36) DEFAULT NULL COMMENT '业务主键（UUID）',
  `name` varchar(64) DEFAULT NULL COMMENT '任务类型名称（可选值：保洁任务/收运任务/设施维修任务/问题处置任务/核查任务/监测任务/养护任务/污水处置任务）',
  `code` varchar(32) DEFAULT NULL COMMENT '任务类型编码',
  `status` tinyint DEFAULT '1' COMMENT '状态（可选值：0-禁用/1-启用）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='任务类型字典表【通用复用】';

-- ----------------------------
-- Records of sys_task_type
-- ----------------------------
INSERT INTO `sys_task_type` VALUES ('1', 'uuid-task-type-001', '保洁任务', 'CLEANING', '1', '公厕日常保洁、专项保洁等任务', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_task_type` VALUES ('2', 'uuid-task-type-002', '收运任务', 'COLLECTION', '1', '公厕垃圾收运、耗材回收任务', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_task_type` VALUES ('3', 'uuid-task-type-003', '设施维修任务', 'FACILITY_REPAIR', '1', '公厕设施故障维修、保养任务', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_task_type` VALUES ('4', 'uuid-task-type-004', '问题处置任务', 'PROBLEM_HANDLING', '1', '投诉、巡检发现问题的处置任务', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_task_type` VALUES ('5', 'uuid-task-type-005', '核查任务', 'INSPECTION', '1', '保洁质量、设施状态核查任务', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_task_type` VALUES ('6', 'uuid-task-type-006', '监测任务', 'MONITORING', '1', '环境、异味、耗材库存监测任务', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_task_type` VALUES ('7', 'uuid-task-type-007', '养护任务', 'MAINTENANCE', '1', '公厕设施定期养护任务', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_task_type` VALUES ('8', 'uuid-task-type-008', '污水处置任务', 'SEWAGE_DISPOSAL', '1', '公厕污水排放、处理任务', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');
INSERT INTO `sys_task_type` VALUES ('9', 'uuid-task-type-009', '应急任务', 'EMERGENCY', '1', '突发情况应急处置任务', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20', '2026-02-13 10:42:20');

-- ----------------------------
-- Table structure for sys_team
-- ----------------------------
DROP TABLE IF EXISTS `sys_team`;
CREATE TABLE `sys_team` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_team_id` char(36) DEFAULT NULL COMMENT '业务主键（UUID）',
  `name` varchar(64) DEFAULT NULL COMMENT '班组名称',
  `dept_id` char(36) DEFAULT NULL COMMENT '所属部门（关联sys_dept.sys_dept_id）',
  `team_leader_id` char(36) DEFAULT NULL COMMENT '班组长（关联sys_user.id）',
  `status` tinyint DEFAULT NULL COMMENT '状态（可选值：0-禁用/1-启用）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `team_photo_url` varchar(512) DEFAULT NULL COMMENT '班组合影URL',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='班组表';

-- ----------------------------
-- Records of sys_team
-- ----------------------------
INSERT INTO `sys_team` VALUES ('1', 'uuid-team-001', '城东清扫班组', '1001', 'uuid-user-001', '1', null, null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `sys_team` VALUES ('2', 'uuid-team-002', '城西清扫班组', '1002', 'uuid-user-002', '1', null, null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `sys_team` VALUES ('3', 'uuid-team-003', '城南清扫班组', '1003', 'uuid-user-003', '1', null, null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');

-- ----------------------------
-- Table structure for sys_tool
-- ----------------------------
DROP TABLE IF EXISTS `sys_tool`;
CREATE TABLE `sys_tool` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_tool_id` char(36) DEFAULT NULL COMMENT '业务主键（UUID）',
  `name` varchar(64) DEFAULT NULL COMMENT '工具名称',
  `code` varchar(32) DEFAULT NULL COMMENT '工具编码',
  `type` varchar(32) DEFAULT NULL COMMENT '工具类型',
  `specification` varchar(64) DEFAULT NULL COMMENT '规格型号',
  `status` tinyint DEFAULT NULL COMMENT '状态：启用/禁用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='工具字典表';

-- ----------------------------
-- Records of sys_tool
-- ----------------------------
INSERT INTO `sys_tool` VALUES ('1', 'uuid-tool-001', '电动清扫车', null, '大型设备', 'XLC-100型', '1', null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `sys_tool` VALUES ('2', 'uuid-tool-002', '手推扫地机', null, '中型设备', 'STJ-50型', '1', null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `sys_tool` VALUES ('3', 'uuid-tool-003', '竹扫把', null, '手动工具', '1.2m长', '1', null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `sys_tool` VALUES ('4', 'uuid-tool-004', '高压水枪', null, '清洁工具', '150bar', '1', null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');
INSERT INTO `sys_tool` VALUES ('5', 'uuid-tool-005', '垃圾夹', null, '手动工具', '60cm长', '1', null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-14 16:13:19', '2026-02-14 16:13:19');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `user_id` char(36) NOT NULL COMMENT '业务主键（UUID）',
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `user_phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `dept_name` varchar(64) DEFAULT NULL COMMENT '所属部门名称',
  `role_id` char(36) DEFAULT NULL COMMENT '角色ID（关联角色表）',
  `status_id` char(36) DEFAULT NULL COMMENT '状态ID（关联sys_status.status_id）',
  `create_by` char(36) DEFAULT NULL COMMENT '创建人ID（关联sys_user.user_id）',
  `update_by` char(36) DEFAULT NULL COMMENT '更新人ID（关联sys_user.user_id）',
  `skill_tags` json DEFAULT NULL COMMENT '技能标签（JSON格式）',
  `work_trajectory_id` char(36) DEFAULT NULL COMMENT '作业轨迹ID（关联轨迹表）',
  `team_id` char(36) DEFAULT NULL COMMENT '所属班组（关联sys_team.sys_team_id）',
  `area_code` varchar(20) DEFAULT NULL COMMENT '负责区域（关联sys_area.area_code）',
  `job_type_id` char(36) DEFAULT NULL COMMENT '岗位类型（关联sys_job_type.sys_job_type_id）',
  `person_status_id` char(36) DEFAULT NULL COMMENT '人员状态（关联sys_person_status.sys_person_status_id）',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系方式（补充备用电话字段）',
  `entry_time` datetime DEFAULT NULL COMMENT '入职时间',
  `total_attendance_days` int DEFAULT '0' COMMENT '累计考勤天数',
  `average_score` decimal(5,2) DEFAULT '0.00' COMMENT '平均考核得分（保留2位小数）',
  `last_work_trace` json DEFAULT NULL COMMENT '最近作业轨迹（JSON格式）',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'uuid-user-001', '张三', '13800138001', '环卫管理中心', 'uuid-role-001', 'uuid-pstatus-001', 'admin', 'admin', null, null, 'uuid-team-001', '1001', 'uuid-job-001', 'uuid-pstatus-001', '13800138001', '2023-01-01 00:00:00', '300', '95.50', null, null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `sys_user` VALUES ('2', 'uuid-user-002', '李四', '13800138002', '清运作业部', 'uuid-role-001', 'uuid-pstatus-001', 'admin', 'admin', null, null, 'uuid-team-002', '1002', 'uuid-job-001', 'uuid-pstatus-001', '13800138002', '2023-01-02 00:00:00', '298', '92.00', null, null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `sys_user` VALUES ('3', 'uuid-user-003', '王五', '13800138003', '清扫作业部', 'uuid-role-001', 'uuid-pstatus-001', 'admin', 'admin', null, null, 'uuid-team-003', '1003', 'uuid-job-001', 'uuid-pstatus-001', '13800138003', '2023-01-03 00:00:00', '295', '88.75', null, null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `sys_user` VALUES ('4', 'uuid-user-004', '赵六', '13800138004', '车辆维保部', 'uuid-role-001', 'uuid-pstatus-001', 'admin', 'admin', null, null, 'uuid-team-004', '1004', 'uuid-job-001', 'uuid-pstatus-001', '13800138004', '2023-01-04 00:00:00', '290', '90.20', null, null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `sys_user` VALUES ('5', 'uuid-user-005', '孙七', '13800138005', '巡查监督部', 'uuid-role-001', 'uuid-pstatus-001', 'admin', 'admin', null, null, 'uuid-team-005', '1005', 'uuid-job-001', 'uuid-pstatus-001', '13800138005', '2023-01-05 00:00:00', '288', '96.80', null, null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `sys_user` VALUES ('6', 'uuid-user-006', '周八', '13800138006', '应急保障部', 'uuid-role-001', 'uuid-pstatus-001', 'admin', 'admin', null, null, 'uuid-team-006', '1006', 'uuid-job-001', 'uuid-pstatus-001', '13800138006', '2023-01-06 00:00:00', '285', '89.50', null, null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `sys_user` VALUES ('7', 'uuid-user-007', '吴九', '13800138007', '环卫管理中心', 'uuid-role-001', 'uuid-pstatus-001', 'admin', 'admin', null, null, 'uuid-team-007', '1007', 'uuid-job-001', 'uuid-pstatus-001', '13800138007', '2023-01-07 00:00:00', '280', '91.30', null, null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `sys_user` VALUES ('8', 'uuid-user-008', '郑十', '13800138008', '清运作业部', 'uuid-role-001', 'uuid-pstatus-001', 'admin', 'admin', null, null, 'uuid-team-008', '1001', 'uuid-job-001', 'uuid-pstatus-001', '13800138008', '2023-01-08 00:00:00', '278', '87.90', null, null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `sys_user` VALUES ('9', 'uuid-user-009', '冯十一', '13800138009', '巡查监督部', 'uuid-role-001', 'uuid-pstatus-001', 'admin', 'admin', null, null, 'uuid-team-009', '1002', 'uuid-job-001', 'uuid-pstatus-001', '13800138009', '2023-01-09 00:00:00', '275', '93.60', null, null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `sys_user` VALUES ('10', 'uuid-user-010', '陈十二', '13800138010', '清运作业部', 'uuid-role-001', 'uuid-pstatus-001', 'admin', 'admin', null, null, 'uuid-team-010', '1003', 'uuid-job-001', 'uuid-pstatus-001', '13800138010', '2023-01-10 00:00:00', '272', '86.85', null, null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `sys_user` VALUES ('11', 'uuid-user-011', '褚十三', '13800138011', '应急保障部', 'uuid-role-001', 'uuid-pstatus-001', 'admin', 'admin', null, null, 'uuid-team-011', '1004', 'uuid-job-001', 'uuid-pstatus-001', '13800138011', '2023-01-11 00:00:00', '270', '94.20', null, null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `sys_user` VALUES ('12', 'uuid-user-012', '卫十四', '13800138012', '清运作业部', 'uuid-role-001', 'uuid-pstatus-001', 'admin', 'admin', null, null, 'uuid-team-012', '1005', 'uuid-job-001', 'uuid-pstatus-001', '13800138012', '2023-01-12 00:00:00', '268', '89.80', null, null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `sys_user` VALUES ('13', 'uuid-user-013', '蒋十五', '13800138013', '清运作业部', 'uuid-role-001', 'uuid-pstatus-001', 'admin', 'admin', null, null, 'uuid-team-013', '1006', 'uuid-job-001', 'uuid-pstatus-001', '13800138013', '2023-01-13 00:00:00', '265', '92.75', null, null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `sys_user` VALUES ('14', 'uuid-user-014', '沈十六', '13800138014', '车辆维保部', 'uuid-role-001', 'uuid-pstatus-001', 'admin', 'admin', null, null, 'uuid-team-014', '1007', 'uuid-job-001', 'uuid-pstatus-001', '13800138014', '2023-01-14 00:00:00', '262', '88.40', null, null, null, null, null, 'admin', 'admin', '\0', '1');
INSERT INTO `sys_user` VALUES ('15', 'uuid-user-015', '韩十七', '13800138015', '应急保障部', 'uuid-role-001', 'uuid-pstatus-001', 'admin', 'admin', null, null, 'uuid-team-015', '1001', 'uuid-job-001', 'uuid-pstatus-001', '13800138015', '2023-01-15 00:00:00', '260', '95.10', null, null, null, null, null, 'admin', 'admin', '\0', '1');

-- ----------------------------
-- Table structure for sys_vehicle
-- ----------------------------
DROP TABLE IF EXISTS `sys_vehicle`;
CREATE TABLE `sys_vehicle` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_vehicle_id` char(36) DEFAULT NULL COMMENT '业务主键（UUID）',
  `license_plate` varchar(32) DEFAULT NULL COMMENT '车牌号码',
  `vehicle_type_id` char(36) DEFAULT NULL COMMENT '车辆类型（关联sys_vehicle_type.sys_vehicle_type_id）',
  `model` varchar(64) DEFAULT NULL COMMENT '车辆型号',
  `dept_id` char(36) DEFAULT NULL COMMENT '所属部门（关联sys_dept.sys_dept_id）',
  `route_id` char(36) DEFAULT NULL COMMENT '作业路线（关联sys_route.sys_route_id）',
  `maintenance_cycle` int DEFAULT NULL COMMENT '维护周期（单位：天）',
  `driver_id` char(36) DEFAULT NULL COMMENT '驾驶员（关联sys_user.id）',
  `vehicle_status_id` char(36) DEFAULT NULL COMMENT '车辆状态（关联sys_vehicle_status.sys_vehicle_status_id）',
  `create_by` char(36) DEFAULT NULL COMMENT '业务创建人（关联sys_user.id）',
  `abnormal_create_time` datetime DEFAULT NULL COMMENT '业务创建时间',
  `abnormal_update_time` datetime DEFAULT NULL COMMENT '业务更新时间',
  `total_work_hours` decimal(10,2) DEFAULT NULL COMMENT '累计作业时长',
  `last_maintenance_time` datetime DEFAULT NULL COMMENT '最近维护时间',
  `alarm_count` int DEFAULT NULL COMMENT '违规告警次数',
  `vehicle_photo_url` varchar(512) DEFAULT NULL COMMENT '车辆照片URL',
  `plan_status_id` char(36) DEFAULT NULL COMMENT '计划状态（关联sys_plan_status.sys_plan_status_id）',
  `work_status_id` char(36) DEFAULT NULL COMMENT '作业状态（关联sys_work_status.sys_work_status_id）',
  `violation_type_id` char(36) DEFAULT NULL COMMENT '违规类型（关联sys_violation_type.sys_violation_type_id）',
  `violation_status_id` char(36) DEFAULT NULL COMMENT '违规状态（关联sys_violation_status.sys_violation_status_id）',
  `maintenance_type_id` char(36) DEFAULT NULL COMMENT '维护类型（关联sys_maintenance_type.sys_maintenance_type_id）',
  `task_type_id` char(36) DEFAULT NULL COMMENT '任务类型（关联sys_task_type.sys_task_type_id）',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='车辆表';

-- ----------------------------
-- Records of sys_vehicle
-- ----------------------------
INSERT INTO `sys_vehicle` VALUES ('1', 'uuid-vehicle-001', '京A12345', 'uuid-vehicle-type-001', '东风天龙', 'uuid-dept-001', 'uuid-route-001', '30', 'uuid-user-001', 'uuid-vehicle-status-001', 'uuid-user-001', '2026-02-14 10:58:21', '2026-02-14 10:58:21', '1200.50', '2024-01-10 00:00:00', '2', 'https://xxx.com/vehicle/1.jpg', 'uuid-plan-status-001', 'uuid-work-status-001', 'uuid-violation-type-001', 'uuid-violation-status-001', 'uuid-maintenance-type-001', 'uuid-task-type-001', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `sys_vehicle` VALUES ('2', 'uuid-vehicle-002', '沪B67890', 'uuid-vehicle-type-001', '解放J6', 'uuid-dept-002', 'uuid-route-002', '30', 'uuid-user-002', 'uuid-vehicle-status-001', 'uuid-user-002', '2026-02-14 10:58:21', '2026-02-14 10:58:21', '1150.20', '2024-01-12 00:00:00', '1', 'https://xxx.com/vehicle/2.jpg', 'uuid-plan-status-002', 'uuid-work-status-002', 'uuid-violation-type-002', 'uuid-violation-status-002', 'uuid-maintenance-type-002', 'uuid-task-type-002', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `sys_vehicle` VALUES ('3', 'uuid-vehicle-003', '粤A34567', 'uuid-vehicle-type-002', '福田欧曼', 'uuid-dept-003', 'uuid-route-003', '25', 'uuid-user-003', 'uuid-vehicle-status-002', 'uuid-user-003', '2026-02-14 10:58:21', '2026-02-14 10:58:21', '980.80', '2024-01-15 00:00:00', '5', 'https://xxx.com/vehicle/3.jpg', 'uuid-plan-status-001', 'uuid-work-status-003', 'uuid-violation-type-001', 'uuid-violation-status-003', 'uuid-maintenance-type-001', 'uuid-task-type-003', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `sys_vehicle` VALUES ('4', 'uuid-vehicle-004', '粤B89012', 'uuid-vehicle-type-002', '重汽豪沃', 'uuid-dept-004', 'uuid-route-004', '25', 'uuid-user-004', 'uuid-vehicle-status-001', 'uuid-user-004', '2026-02-14 10:58:21', '2026-02-14 10:58:21', '1050.00', '2024-01-08 00:00:00', '0', 'https://xxx.com/vehicle/4.jpg', 'uuid-plan-status-003', 'uuid-work-status-001', 'uuid-violation-type-003', 'uuid-violation-status-001', 'uuid-maintenance-type-003', 'uuid-task-type-001', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `sys_vehicle` VALUES ('5', 'uuid-vehicle-005', '浙A45678', 'uuid-vehicle-type-003', '江淮帅铃', 'uuid-dept-005', 'uuid-route-005', '20', 'uuid-user-005', 'uuid-vehicle-status-001', 'uuid-user-005', '2026-02-14 10:58:21', '2026-02-14 10:58:21', '890.30', '2024-01-20 00:00:00', '3', 'https://xxx.com/vehicle/5.jpg', 'uuid-plan-status-002', 'uuid-work-status-002', 'uuid-violation-type-002', 'uuid-violation-status-002', 'uuid-maintenance-type-002', 'uuid-task-type-002', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `sys_vehicle` VALUES ('6', 'uuid-vehicle-006', '苏A90123', 'uuid-vehicle-type-003', '江铃凯运', 'uuid-dept-006', 'uuid-route-006', '20', 'uuid-user-006', 'uuid-vehicle-status-003', 'uuid-user-006', '2026-02-14 10:58:21', '2026-02-14 10:58:21', '780.60', '2024-01-18 00:00:00', '4', 'https://xxx.com/vehicle/6.jpg', 'uuid-plan-status-001', 'uuid-work-status-003', 'uuid-violation-type-001', 'uuid-violation-status-003', 'uuid-maintenance-type-001', 'uuid-task-type-003', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');
INSERT INTO `sys_vehicle` VALUES ('7', 'uuid-vehicle-007', '川A23456', 'uuid-vehicle-type-001', '陕汽德龙', 'uuid-dept-007', 'uuid-route-007', '30', 'uuid-user-007', 'uuid-vehicle-status-001', 'uuid-user-007', '2026-02-14 10:58:21', '2026-02-14 10:58:21', '1320.90', '2024-01-05 00:00:00', '1', 'https://xxx.com/vehicle/7.jpg', 'uuid-plan-status-003', 'uuid-work-status-001', 'uuid-violation-type-003', 'uuid-violation-status-001', 'uuid-maintenance-type-003', 'uuid-task-type-001', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-14 10:58:21', '2026-02-14 10:58:21');

-- ----------------------------
-- Table structure for sys_vehicle_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_vehicle_status`;
CREATE TABLE `sys_vehicle_status` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_vehicle_status_id` char(36) DEFAULT NULL COMMENT '业务主键（UUID）',
  `name` varchar(64) DEFAULT NULL COMMENT '状态名称（可选值：正常运行/维护中/故障/闲置/报废/待年检/停运）',
  `code` varchar(32) DEFAULT NULL COMMENT '状态编码',
  `status` tinyint DEFAULT NULL COMMENT '状态（可选值：0-禁用/1-启用）',
  `sort` int DEFAULT NULL COMMENT '排序号',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='车辆状态字典表';

-- ----------------------------
-- Records of sys_vehicle_status
-- ----------------------------
INSERT INTO `sys_vehicle_status` VALUES ('1', 'uuid-vehicle-status-001', '正常运行', 'ZT001', '1', '1', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:55:25');
INSERT INTO `sys_vehicle_status` VALUES ('2', 'uuid-vehicle-status-002', '维护中', 'ZT002', '1', '2', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:56:40');
INSERT INTO `sys_vehicle_status` VALUES ('3', 'uuid-vehicle-status-003', '故障', 'ZT003', '1', '3', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:56:43');
INSERT INTO `sys_vehicle_status` VALUES ('4', 'uuid-vehicle-status-004', '闲置', 'ZT004', '1', '4', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:56:45');
INSERT INTO `sys_vehicle_status` VALUES ('5', 'uuid-vehicle-status-005', '报废', 'ZT005', '1', '5', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:56:47');
INSERT INTO `sys_vehicle_status` VALUES ('6', 'uuid-vehicle-status-006', '待年检', 'ZT006', '1', '6', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:56:50');
INSERT INTO `sys_vehicle_status` VALUES ('7', 'uuid-vehicle-status-007', '停运', 'ZT007', '1', '7', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:56:53');

-- ----------------------------
-- Table structure for sys_vehicle_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_vehicle_type`;
CREATE TABLE `sys_vehicle_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_vehicle_type_id` char(36) DEFAULT NULL COMMENT '业务主键（UUID）',
  `name` varchar(64) DEFAULT NULL COMMENT '类型名称（可选值：清运车/清扫车/洒水车/洗扫车/垃圾转运车/吸污车/巡查车）',
  `code` varchar(32) DEFAULT NULL COMMENT '类型编码',
  `status` tinyint DEFAULT NULL COMMENT '状态（可选值：0-禁用/1-启用）',
  `description` varchar(255) DEFAULT NULL COMMENT '类型描述',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='车辆类型字典表';

-- ----------------------------
-- Records of sys_vehicle_type
-- ----------------------------
INSERT INTO `sys_vehicle_type` VALUES ('1', 'uuid-vehicle-type-001', '清运车', 'QY001', '1', '用于生活垃圾清运的专用车辆', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:57:49');
INSERT INTO `sys_vehicle_type` VALUES ('2', 'uuid-vehicle-type-002', '清扫车', 'QS001', '1', '道路清扫作业车辆，含扫刷和吸尘功能', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:57:49');
INSERT INTO `sys_vehicle_type` VALUES ('3', 'uuid-vehicle-type-003', '洒水车', 'SS001', '1', '道路降尘、洒水作业专用车', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:57:49');
INSERT INTO `sys_vehicle_type` VALUES ('4', 'uuid-vehicle-type-004', '洗扫车', 'XS001', '1', '兼具清洗和清扫功能的环卫车辆', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:57:49');
INSERT INTO `sys_vehicle_type` VALUES ('5', 'uuid-vehicle-type-005', '垃圾转运车', 'ZY001', '1', '短途垃圾转运专用车辆', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:57:49');
INSERT INTO `sys_vehicle_type` VALUES ('6', 'uuid-vehicle-type-006', '吸污车', 'XW001', '1', '化粪池、污水井吸污作业车辆', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:57:49');
INSERT INTO `sys_vehicle_type` VALUES ('7', 'uuid-vehicle-type-007', '巡查车', 'XC001', '1', '环卫作业区域巡查专用车辆', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:57:58');

-- ----------------------------
-- Table structure for sys_violation_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_violation_status`;
CREATE TABLE `sys_violation_status` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `violation_status_id` char(36) DEFAULT NULL COMMENT '主键（UUID）',
  `violation_status_name` varchar(32) DEFAULT NULL COMMENT '违规状态名称：待处理/整改中/已办结/重新整改',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='违规状态字典表【通用复用】';

-- ----------------------------
-- Records of sys_violation_status
-- ----------------------------
INSERT INTO `sys_violation_status` VALUES ('1', 'uuid-violation-status-001', '待处理', '违规事件已上报，等待工作人员受理处理', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:53:33');
INSERT INTO `sys_violation_status` VALUES ('2', 'uuid-violation-status-002', '整改中', '违规事件已受理，责任部门正在整改过程中', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:53:37');
INSERT INTO `sys_violation_status` VALUES ('3', 'uuid-violation-status-003', '已办结', '违规问题已整改完成，验收通过，事件闭环', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:53:40');
INSERT INTO `sys_violation_status` VALUES ('4', 'uuid-violation-status-004', '重新整改', '整改结果未通过验收，需责任部门重新整改', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:53:45');

-- ----------------------------
-- Table structure for sys_violation_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_violation_type`;
CREATE TABLE `sys_violation_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `violation_type_id` char(36) DEFAULT NULL COMMENT '主键（UUID）',
  `violation_name` varchar(32) DEFAULT NULL COMMENT '违规类型名称',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='违规类型字典表【通用复用】';

-- ----------------------------
-- Records of sys_violation_type
-- ----------------------------
INSERT INTO `sys_violation_type` VALUES ('1', 'uuid-violation-type-001', '作业未达标', '清扫/清运/洒水等作业质量未达到环卫规范要求', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:52:55');
INSERT INTO `sys_violation_type` VALUES ('2', 'uuid-violation-type-002', '车辆违规操作', '司机违规驾驶、车辆违规停放、作业时违规占道', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:53:00');
INSERT INTO `sys_violation_type` VALUES ('3', 'uuid-violation-type-003', '作业路线偏离', '未按系统指定路线开展环卫作业，擅自更改路线', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:53:04');
INSERT INTO `sys_violation_type` VALUES ('4', 'uuid-violation-type-004', '作业时间违规', '未在规定时间段开展作业，早收工/晚开工/错峰作业违规', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:53:07');
INSERT INTO `sys_violation_type` VALUES ('5', 'uuid-violation-type-005', '设备未及时维保', '车辆未按周期进行维护保养，超期未检/未保', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:53:12');
INSERT INTO `sys_violation_type` VALUES ('6', 'uuid-violation-type-006', '垃圾滴漏', '清运/转运车辆作业时出现垃圾、污水滴漏现象', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:53:15');
INSERT INTO `sys_violation_type` VALUES ('7', 'uuid-violation-type-007', '人员脱岗', '作业期间保洁/司机人员擅自脱岗、离岗', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:53:20');

-- ----------------------------
-- Table structure for sys_work_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_work_status`;
CREATE TABLE `sys_work_status` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `work_status_id` char(36) DEFAULT NULL COMMENT '主键（UUID）',
  `work_status_name` varchar(32) DEFAULT NULL COMMENT '作业状态名称：作业中/暂停/已完成/异常',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='作业状态字典表【通用复用】';

-- ----------------------------
-- Records of sys_work_status
-- ----------------------------
INSERT INTO `sys_work_status` VALUES ('1', 'uuid-work-status-001', '作业中', '环卫车辆/人员正在按计划正常进行作业', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:52:18');
INSERT INTO `sys_work_status` VALUES ('2', 'uuid-work-status-002', '暂停', '因交通拥堵、设备小故障、天气等临时情况暂停作业', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:52:22');
INSERT INTO `sys_work_status` VALUES ('3', 'uuid-work-status-003', '已完成', '按计划完成全部作业任务，达到作业要求', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:52:29');
INSERT INTO `sys_work_status` VALUES ('4', 'uuid-work-status-004', '异常', '作业过程中出现重大问题（如车辆故障、安全事故、人员受伤）', '', '', '', '', 'admin', 'admin', '\0', '1', '2026-02-12 16:58:32', '2026-02-25 16:52:34');

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `task_id` char(36) DEFAULT NULL COMMENT '主键（UUID）',
  `task_type_id` varchar(64) DEFAULT NULL COMMENT '关联sys_task_type.sys_task_type_id',
  `toilet_id` varchar(64) DEFAULT NULL COMMENT '关联public_toilet.id（公厕相关任务）',
  `transfer_id` varchar(64) DEFAULT NULL COMMENT '关联garbage_transfer.id（转运站相关任务）',
  `institution_id` varchar(64) DEFAULT NULL COMMENT '关联public_institution.id（公共机构相关任务）',
  `street_id` varchar(64) DEFAULT NULL COMMENT '关联commercial_street.id（商业街相关任务）',
  `park_id` varchar(64) DEFAULT NULL COMMENT '关联park.id（公园相关任务）',
  `village_id` varchar(64) DEFAULT NULL COMMENT '关联urban_village.id（城中村相关任务）',
  `market_id` varchar(64) DEFAULT NULL COMMENT '关联market.id（集贸市场相关任务）',
  `river_id` varchar(64) DEFAULT NULL COMMENT '关联river.id（河道相关任务）',
  `area_code` varchar(64) DEFAULT NULL COMMENT '关联sys_area.area_code',
  `handle_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id（处置人员）',
  `handle_result` varchar(1024) DEFAULT NULL COMMENT '处置结果',
  `proof_url` json DEFAULT NULL COMMENT '佐证材料URL（照片/文档链接，多个用逗号分隔）',
  `complete_time` datetime DEFAULT NULL COMMENT '完成时间',
  `handle_duration` decimal(10,2) DEFAULT NULL COMMENT '任务耗时（单位：分钟）',
  `stat_period` varchar(8) DEFAULT NULL COMMENT '统计周期（可选值：日/周/月）',
  `cleaning_qualified_rate` decimal(5,2) DEFAULT NULL COMMENT '保洁达标率（仅保洁类任务）',
  `problem_complete_rate` decimal(5,2) DEFAULT NULL COMMENT '问题办结率（仅问题处置类任务）',
  `inspection_pass_rate` decimal(5,2) DEFAULT NULL COMMENT '核查通过率（仅核查类任务）',
  `total_entry_volume` decimal(10,2) DEFAULT NULL COMMENT '进站总量（仅转运作业类任务，单位：吨）',
  `equipment_intact_rate` decimal(5,2) DEFAULT NULL COMMENT '设备完好率（仅维护类任务）',
  `environment_qualified_rate` decimal(5,2) DEFAULT NULL COMMENT '环境达标率（仅预警类任务）',
  `satisfaction` varchar(16) DEFAULT NULL COMMENT '满意度（仅投诉类任务，可选值：满意/基本满意/不满意）',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识（可选值：0-未删除/1-已删除）',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='任务表';

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', 'uuid-task-001', 'uuid-task-type-001', 'uuid-toilet-001', null, null, null, null, null, null, null, '1001', 'uuid-user-008', '完成当日8次保洁，地面/便池清洁达标，耗材补充到位', '[\"https://xxx.com/task/1-1.jpg\", \"https://xxx.com/task/1-2.jpg\"]', '2024-02-12 22:00:00', '120.00', '日', '98.50', null, null, null, null, null, null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `task` VALUES ('2', 'uuid-task-002', 'uuid-task-type-002', 'uuid-toilet-002', null, null, null, null, null, null, null, '1002', 'uuid-user-010', '完成卫生纸、洗手液补充，回收空瓶30个', '[\"https://xxx.com/task/2-1.jpg\"]', '2024-02-12 15:30:00', '60.00', '日', null, null, null, null, null, null, null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `task` VALUES ('3', 'uuid-task-003', 'uuid-task-type-003', 'uuid-toilet-003', null, null, null, null, null, null, null, '1003', 'uuid-user-009', '完成3处门锁维修、2处冲水阀更换，验收合格', '[\"https://xxx.com/task/3-1.jpg\", \"https://xxx.com/task/3-2.jpg\"]', '2024-02-11 18:00:00', '240.00', '周', null, null, null, null, '85.00', null, null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `task` VALUES ('4', 'uuid-task-004', 'uuid-task-type-004', 'uuid-toilet-004', null, null, null, null, null, null, null, '1004', 'uuid-user-014', '完成异味投诉处置，使用除臭剂+加强通风，投诉人反馈满意', '[\"https://xxx.com/task/4-1.jpg\"]', '2024-02-09 18:00:00', '90.00', '日', null, '80.00', null, null, null, null, '满意', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `task` VALUES ('5', 'uuid-task-005', 'uuid-task-type-005', 'uuid-toilet-005', null, null, null, null, null, null, null, '1005', 'uuid-user-013', '完成本周3次核查，保洁达标率99%，设施完好率98%', '[\"https://xxx.com/task/5-1.jpg\", \"https://xxx.com/task/5-2.jpg\"]', '2024-02-11 10:00:00', '180.00', '周', null, null, '99.00', null, null, null, null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `task` VALUES ('6', 'uuid-task-006', 'uuid-task-type-006', 'uuid-toilet-006', null, null, null, null, null, null, null, '1006', 'uuid-user-015', '完成月度环境监测，异味浓度达标，耗材库存充足', '[\"https://xxx.com/task/6-1.jpg\"]', '2024-02-10 09:00:00', '120.00', '月', null, null, null, null, null, '90.00', null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `task` VALUES ('7', 'uuid-task-007', 'uuid-task-type-007', 'uuid-toilet-007', null, null, null, null, null, null, null, '1007', 'uuid-user-011', '完成月度设施养护，冲水系统、照明设施检查保养', '[\"https://xxx.com/task/7-1.jpg\", \"https://xxx.com/task/7-2.jpg\"]', '2024-02-09 14:00:00', '300.00', '月', null, null, null, null, '90.00', null, null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `task` VALUES ('8', 'uuid-task-008', 'uuid-task-type-009', 'uuid-toilet-008', null, null, null, null, null, null, null, '1001', 'uuid-user-001', '完成暂停运营应急指引设置，周边张贴通知5处', '[\"https://xxx.com/task/8-1.jpg\"]', '2024-02-08 10:00:00', '45.00', '日', null, null, null, null, null, null, null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `task` VALUES ('9', 'uuid-task-009', 'uuid-task-type-008', 'uuid-toilet-001', null, null, null, null, null, null, null, '1001', 'uuid-user-008', '完成本周污水抽排，排水量5吨，处理达标', '[\"https://xxx.com/task/9-1.jpg\"]', '2024-02-12 14:00:00', '150.00', '周', null, null, null, null, null, '98.00', null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `task` VALUES ('10', 'uuid-task-010', 'uuid-task-type-002', null, 'uuid-transfer-001', null, null, null, null, null, null, '1001', 'uuid-user-001', '完成当日垃圾收运，进站总量120吨，压缩设备运行正常', '[\"https://xxx.com/task/10-1.jpg\", \"https://xxx.com/task/10-2.jpg\"]', '2024-02-12 20:00:00', '360.00', '日', null, null, null, '120.50', '95.00', null, null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `task` VALUES ('11', 'uuid-task-011', 'uuid-task-type-007', null, 'uuid-transfer-002', null, null, null, null, null, null, '1001', 'uuid-user-001', '完成压缩机保养、输送带检修，设备完好率提升至98%', '[\"https://xxx.com/task/11-1.jpg\"]', '2024-02-11 18:00:00', '480.00', '周', null, null, null, null, '98.00', null, null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `task` VALUES ('12', 'uuid-task-012', 'uuid-task-type-004', null, 'uuid-transfer-003', null, null, null, null, null, null, '1001', 'uuid-user-001', '处置异味预警，增加喷淋频次，环境达标率92%', '[\"https://xxx.com/task/12-1.jpg\"]', '2024-02-10 15:00:00', '120.00', '日', null, null, null, null, null, '92.00', null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `task` VALUES ('13', 'uuid-task-013', 'uuid-task-type-001', null, null, 'uuid-inst-001', null, null, null, null, null, '1001', 'uuid-user-001', '完成政府大楼每日保洁，办公区/卫生间达标率99%', '[\"https://xxx.com/task/13-1.jpg\"]', '2024-02-12 17:00:00', '240.00', '日', '99.00', null, null, null, null, null, null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `task` VALUES ('14', 'uuid-task-014', 'uuid-task-type-005', null, null, 'uuid-inst-002', null, null, null, null, null, '1001', 'uuid-user-001', '核查食堂垃圾分类问题，整改后通过率95%', '[\"https://xxx.com/task/14-1.jpg\", \"https://xxx.com/task/14-2.jpg\"]', '2024-02-11 10:00:00', '180.00', '周', null, null, '95.00', null, null, null, null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `task` VALUES ('15', 'uuid-task-015', 'uuid-task-type-001', null, null, null, 'uuid-street-001', null, null, null, null, '1001', 'uuid-user-001', '完成步行街夜间保洁，垃圾清运及时，达标率98%', '[\"https://xxx.com/task/15-1.jpg\"]', '2024-02-12 23:00:00', '300.00', '日', '98.00', null, null, null, null, null, null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `task` VALUES ('16', 'uuid-task-016', 'uuid-task-type-004', null, null, null, null, 'uuid-park-001', null, null, null, '1001', 'uuid-user-001', '清理人工湖水生植物，环境达标率96%', '[\"https://xxx.com/task/16-1.jpg\"]', '2024-02-10 16:00:00', '420.00', '周', null, null, null, null, null, '96.00', null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `task` VALUES ('17', 'uuid-task-017', 'uuid-task-type-004', null, null, null, null, null, 'uuid-village-001', null, null, '1001', 'uuid-user-001', '处置垃圾堆积投诉，清运8车垃圾，居民反馈满意', '[\"https://xxx.com/task/17-1.jpg\"]', '2024-02-09 14:00:00', '240.00', '日', null, '100.00', null, null, null, null, '满意', null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `task` VALUES ('18', 'uuid-task-018', 'uuid-task-type-006', null, null, null, null, null, null, 'uuid-market-001', null, '1001', 'uuid-user-001', '月度环境监测，消杀到位，异味浓度达标', '[\"https://xxx.com/task/18-1.jpg\"]', '2024-02-08 09:00:00', '180.00', '月', null, null, null, null, null, '94.00', null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');
INSERT INTO `task` VALUES ('19', 'uuid-task-019', 'uuid-task-type-008', null, null, null, null, null, null, null, 'uuid-river-001', '1001', 'uuid-user-001', '处置河道污水排放问题，封堵排污口，水质达标率90%', '[\"https://xxx.com/task/19-1.jpg\", \"https://xxx.com/task/19-2.jpg\"]', '2024-02-07 17:00:00', '600.00', '周', null, null, null, null, null, '90.00', null, null, null, null, null, 'admin', 'admin', '\0', '1', '2026-02-13 10:42:20');

-- ----------------------------
-- Table structure for urban_village
-- ----------------------------
DROP TABLE IF EXISTS `urban_village`;
CREATE TABLE `urban_village` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `village_id` varchar(64) DEFAULT NULL COMMENT '主键（UUID）',
  `name` varchar(128) DEFAULT NULL COMMENT '城中村名称',
  `address` varchar(256) DEFAULT NULL COMMENT '城中村地址',
  `area_code` varchar(64) DEFAULT NULL COMMENT '关联sys_area.area_code',
  `responsibility_areas` int DEFAULT NULL COMMENT '责任区域数',
  `road_cleaning_frequency` varchar(32) DEFAULT NULL COMMENT '道路保洁频次',
  `manager_id` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `operation_status_id` varchar(64) DEFAULT NULL COMMENT '关联sys_operation_status.id',
  `cleaning_rate` decimal(5,2) DEFAULT NULL COMMENT '保洁达标率',
  `problem_rate` decimal(5,2) DEFAULT NULL COMMENT '问题处置完成率',
  `review_pass_rate` decimal(5,2) DEFAULT NULL COMMENT '复核通过率',
  `assessment_score` decimal(5,2) DEFAULT NULL COMMENT '考核得分（满分100）',
  `responsibility_area_name` varchar(256) DEFAULT NULL COMMENT '责任区域名称',
  `cleaning_standard` varchar(512) DEFAULT NULL COMMENT '保洁标准',
  `staff_ids` json DEFAULT NULL COMMENT '负责人员IDs，JSON',
  `problem_location` varchar(256) DEFAULT NULL COMMENT '问题位置',
  `problem_desc` varchar(1024) DEFAULT NULL COMMENT '问题描述',
  `report_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `report_time` datetime DEFAULT NULL COMMENT '上报时间',
  `problem_photo_url` json DEFAULT NULL COMMENT '现场照片URL，JSON',
  `dept_id` varchar(64) DEFAULT NULL COMMENT '关联sys_dept.id',
  `handle_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `dispatch_time` datetime DEFAULT NULL COMMENT '派单时间',
  `handle_status_id` varchar(64) DEFAULT NULL COMMENT '关联sys_handle_status.id',
  `is_timeout` varchar(8) DEFAULT NULL COMMENT '超时提醒：是/否',
  `handle_desc` varchar(1024) DEFAULT NULL COMMENT '处置说明',
  `reform_photo_url` json DEFAULT NULL COMMENT '整改照片URL，JSON',
  `review_by` varchar(64) DEFAULT NULL COMMENT '关联sys_user.id',
  `review_time` datetime DEFAULT NULL COMMENT '复核时间',
  `review_result_id` varchar(64) DEFAULT NULL COMMENT '关联sys_review_result.id',
  `review_opinion` varchar(1024) DEFAULT NULL COMMENT '复核意见',
  `plan_status_id` varchar(64) DEFAULT NULL COMMENT '关联sys_plan_status.id（计划状态），支持钻取筛选同状态保洁计划',
  `problem_type_id` varchar(64) DEFAULT NULL COMMENT '关联sys_problem_type.id（问题类型），支持钻取筛选同类型问题处置任务',
  `task_type_id` varchar(64) DEFAULT NULL COMMENT '关联sys_task_type.id（任务类型），支持钻取筛选同类型已完成任务',
  `ext_common1` varchar(100) DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) DEFAULT NULL COMMENT '通用扩展字段2',
  `ext_common3` varchar(100) DEFAULT NULL COMMENT '通用扩展字段3',
  `ext_common4` varchar(100) DEFAULT NULL COMMENT '通用扩展字段4',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint DEFAULT '1' COMMENT '租户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='城中村表';

-- ----------------------------
-- Records of urban_village
-- ----------------------------
INSERT INTO `urban_village` VALUES ('1', 'uuid-village-001', '唐家岭村', '北京市海淀区西北旺镇唐家岭村', '1001', '8', '每1小时1次（主路）/每2小时1次（支路）', 'uuid-user-001', 'uuid-op-status-001', '90.00', '95.00', '100.00', '92.50', '唐家岭村1-8片区（主路/支路/广场/菜市场）', '主路无垃圾、支路杂物≤3处/100㎡、垃圾桶满溢≤10分钟、卫生间每日清洁3次', '[\"uuid-user-006\", \"uuid-user-008\", \"uuid-user-009\", \"uuid-user-015\"]', '村中心菜市场周边', '菜市场周边垃圾堆积，垃圾桶满溢未及时清运，异味扰民', 'uuid-user-001', '2024-05-18 09:00:00', '[\"https://xxx.com/village/1-1.jpg\", \"https://xxx.com/village/1-2.jpg\"]', 'uuid-dept-001', 'uuid-user-002', '2024-05-18 09:10:00', 'uuid-handle-003', '否', '增派2名保洁人员，清理堆积垃圾，更换满溢垃圾桶，增加清运频次至每30分钟1次，现场已整改完毕', '[\"https://xxx.com/village/reform1-1.jpg\", \"https://xxx.com/village/reform1-2.jpg\"]', 'uuid-user-002', '2024-05-18 15:00:00', 'uuid-review-001', '菜市场周边垃圾已清理，垃圾桶清运及时，异味消除，符合保洁标准，复核通过', 'uuid-plan-status-001', 'uuid-problem-type-001', 'uuid-task-type-001', null, null, null, null, 'admin', 'admin', '\0');
INSERT INTO `urban_village` VALUES ('2', 'uuid-village-002', '三林新村', '上海市浦东新区三林镇三林新村', '1002', '10', '每40分钟1次（主路）/每1.5小时1次（支路）', 'uuid-user-002', 'uuid-op-status-002', '75.00', '80.00', '85.00', '78.00', '三林新村1-10片区（主路/小区/商业街/河道）', '主路每40分钟清扫1次、小区楼道每日清扫1次、河道无漂浮物、垃圾桶每日清运4次', '[\"uuid-user-002\", \"uuid-user-003\", \"uuid-user-004\", \"uuid-user-006\"]', '村西侧河道旁', '河道旁建筑垃圾堆积，占用消防通道，保洁人员未及时清理，存在安全隐患', 'uuid-user-001', '2024-05-19 10:30:00', '[\"https://xxx.com/village/2-1.jpg\", \"https://xxx.com/village/2-2.jpg\"]', 'uuid-dept-002', 'uuid-user-003', '2024-05-19 10:40:00', 'uuid-handle-002', '否', '已联系清运公司，正在清理建筑垃圾，预计今日18:00前完成，已设置警示标识，禁止占用消防通道', '[]', null, null, 'uuid-review-004', '', 'uuid-plan-status-002', 'uuid-problem-type-002', 'uuid-task-type-002', null, null, null, null, 'admin', 'admin', '\0');
INSERT INTO `urban_village` VALUES ('3', 'uuid-village-003', '转塘新村', '杭州市西湖区转塘街道转塘新村', '1003', '6', '每20分钟1次（核心区）/每40分钟1次（外围）', 'uuid-user-003', 'uuid-op-status-001', '95.00', '98.00', '99.00', '96.50', '转塘新村1-6片区（主路/菜市场/小区/公园）', '核心区无可见垃圾，外围杂物≤2处/100㎡，垃圾桶满溢≤5分钟，卫生间每小时清洁1次', '[\"uuid-user-005\", \"uuid-user-007\", \"uuid-user-008\", \"uuid-user-010\"]', '村东菜市场门口', '菜市场门口非机动车乱停放，垃圾清运不及时，地面油污未清理', 'uuid-user-002', '2024-05-20 08:15:00', '[\"https://xxx.com/village/3-1.jpg\", \"https://xxx.com/village/3-2.jpg\"]', 'uuid-dept-003', 'uuid-user-004', '2024-05-20 08:25:00', 'uuid-handle-001', '否', '安排专人疏导非机动车，清理地面油污，增加垃圾桶清运频次至每20分钟1次', '[\"https://xxx.com/village/reform3-1.jpg\", \"https://xxx.com/village/reform3-2.jpg\"]', 'uuid-user-004', '2024-05-20 14:00:00', 'uuid-review-001', '非机动车停放有序，地面油污清理干净，垃圾桶清运及时，复核通过', 'uuid-plan-status-001', 'uuid-problem-type-003', 'uuid-task-type-003', null, null, null, null, 'admin', 'admin', '\0');
INSERT INTO `urban_village` VALUES ('4', 'uuid-village-004', '棠下村', '广州市天河区棠下街道棠下村', '1004', '12', '每30分钟1次（主路）/每1小时1次（支路）', 'uuid-user-004', 'uuid-op-status-001', '92.00', '94.00', '97.00', '93.00', '棠下村1-12片区（主路/商业街/城中村内部道路）', '主路每30分钟清扫1次，支路每小时1次，商业街无占道经营，垃圾桶无满溢', '[\"uuid-user-001\", \"uuid-user-002\", \"uuid-user-003\", \"uuid-user-004\"]', '村北商业街', '商业街占道经营严重，垃圾堆积，垃圾桶破损未更换', 'uuid-user-003', '2024-05-21 09:30:00', '[\"https://xxx.com/village/4-1.jpg\", \"https://xxx.com/village/4-2.jpg\"]', 'uuid-dept-004', 'uuid-user-005', '2024-05-21 09:40:00', 'uuid-handle-003', '否', '联合城管清理占道经营，更换破损垃圾桶10个，增派保洁人员3名', '[\"https://xxx.com/village/reform4-1.jpg\", \"https://xxx.com/village/reform4-2.jpg\"]', 'uuid-user-005', '2024-05-21 16:00:00', 'uuid-review-002', '占道经营已清理，垃圾桶更换完毕，保洁人员到位，复核通过', 'uuid-plan-status-001', 'uuid-problem-type-004', 'uuid-task-type-004', null, null, null, null, 'admin', 'admin', '\0');
INSERT INTO `urban_village` VALUES ('5', 'uuid-village-005', '曹家巷', '成都市金牛区曹家巷街道曹家巷', '1005', '5', '每25分钟1次（核心区）/每50分钟1次（外围）', 'uuid-user-005', 'uuid-op-status-002', '80.00', '85.00', '88.00', '82.50', '曹家巷1-5片区（老小区/菜市场/主干道）', '核心区每25分钟巡检1次，外围每50分钟1次，老小区楼道每日清扫2次', '[\"uuid-user-006\", \"uuid-user-008\", \"uuid-user-009\", \"uuid-user-015\"]', '老小区3栋楼下', '老小区3栋楼下杂物堆积，下水道堵塞，异味扰民，保洁未覆盖', 'uuid-user-004', '2024-05-17 14:00:00', '[\"https://xxx.com/village/5-1.jpg\", \"https://xxx.com/village/5-2.jpg\"]', 'uuid-dept-005', 'uuid-user-006', '2024-05-17 14:10:00', 'uuid-handle-002', '是', '清理杂物堆积，疏通下水道，安排专人每日清洁该区域，超时1小时完成整改', '[\"https://xxx.com/village/reform5-1.jpg\", \"https://xxx.com/village/reform5-2.jpg\"]', 'uuid-user-006', '2024-05-17 18:00:00', 'uuid-review-003', '杂物清理完毕，下水道疏通，异味消除，但整改超时，复核通过（扣1分）', 'uuid-plan-status-002', 'uuid-problem-type-005', 'uuid-task-type-005', null, null, null, null, 'admin', 'admin', '\0');
INSERT INTO `urban_village` VALUES ('6', 'uuid-village-006', '葑门新村', '苏州市姑苏区葑门街道葑门新村', '1006', '7', '每15分钟1次（核心景区旁）/每30分钟1次（普通区）', 'uuid-user-006', 'uuid-op-status-001', '98.00', '99.00', '100.00', '98.50', '葑门新村1-7片区（景区旁/老巷/居民区）', '景区旁无可见垃圾，老巷无积尘，居民区垃圾桶每日清运3次', '[\"uuid-user-001\", \"uuid-user-002\", \"uuid-user-003\", \"uuid-user-004\"]', '景区旁老巷', '老巷墙面小广告未清理，地面青苔未铲除，影响景区周边环境', 'uuid-user-005', '2024-05-20 11:00:00', '[\"https://xxx.com/village/6-1.jpg\", \"https://xxx.com/village/6-2.jpg\"]', 'uuid-dept-006', 'uuid-user-007', '2024-05-20 11:10:00', 'uuid-handle-001', '否', '清理墙面小广告50处，铲除地面青苔，涂刷防滑层，景区周边环境恢复整洁', '[\"https://xxx.com/village/reform6-1.jpg\", \"https://xxx.com/village/reform6-2.jpg\"]', 'uuid-user-007', '2024-05-20 17:00:00', 'uuid-review-001', '小广告清理完毕，青苔铲除干净，防滑层涂刷到位，复核通过', 'uuid-plan-status-001', 'uuid-problem-type-006', 'uuid-task-type-006', null, null, null, null, 'admin', 'admin', '\0');
INSERT INTO `urban_village` VALUES ('7', 'uuid-village-007', '观沙岭村', '长沙市岳麓区观沙岭街道观沙岭村', '1007', '9', '每20分钟1次（江滩旁）/每40分钟1次（村内）', 'uuid-user-007', 'uuid-op-status-001', '94.00', '96.00', '98.00', '95.00', '观沙岭村1-9片区（江滩/主干道/村内小巷）', '江滩旁无白色垃圾，主干道每20分钟清扫1次，村内小巷无杂物堆积', '[\"uuid-user-007\", \"uuid-user-008\", \"uuid-user-009\", \"uuid-user-010\"]', '江滩旁300米处', '江滩旁300米处建筑垃圾倾倒，未及时清理，影响江滩环境', 'uuid-user-006', '2024-05-19 15:00:00', '[\"https://xxx.com/village/7-1.jpg\", \"https://xxx.com/village/7-2.jpg\"]', 'uuid-dept-007', 'uuid-user-008', '2024-05-19 15:10:00', 'uuid-handle-003', '否', '联系城管查处倾倒行为，安排清运车辆清理建筑垃圾，设置监控摄像头', '[\"https://xxx.com/village/reform7-1.jpg\", \"https://xxx.com/village/reform7-2.jpg\"]', 'uuid-user-008', '2024-05-19 20:00:00', 'uuid-review-002', '建筑垃圾清理完毕，监控已安装，倾倒行为已查处，复核通过', 'uuid-plan-status-001', 'uuid-problem-type-002', 'uuid-task-type-002', null, null, null, null, 'admin', 'admin', '\0');
INSERT INTO `urban_village` VALUES ('8', 'uuid-village-008', '常青村', '武汉市江汉区常青街道常青村', '1008', '4', '无', 'uuid-user-008', 'uuid-op-status-003', '0.00', '70.00', '65.00', '0.00', '常青村1-4片区（全区域）', '暂停开放期间无保洁要求', '[]', '全区域', '全区域80%垃圾桶破损，50%道路坑洼，30%路灯故障，需整体改造', 'uuid-user-007', '2024-05-21 10:00:00', '[\"https://xxx.com/village/8-1.jpg\", \"https://xxx.com/village/8-2.jpg\"]', 'uuid-dept-008', null, null, 'uuid-handle-004', '是', '暂未处置，需申报改造资金，预计3个月内启动整改，已超时7天', '[]', null, null, 'uuid-review-005', '暂未复核，需整改完成后验收', 'uuid-plan-status-003', 'uuid-problem-type-007', 'uuid-task-type-007', null, null, null, null, 'admin', 'admin', '\0');
