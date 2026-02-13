/*
 Navicat Premium Data Transfer

 Source Server         : gc_cp
 Source Server Type    : MySQL
 Source Server Version : 80405
 Source Host           : 192.168.8.67:3306
 Source Schema         : genchuan-park

 Target Server Type    : MySQL
 Target Server Version : 80405
 File Encoding         : 65001

 Date: 12/02/2026 17:44:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for park_lot
-- ----------------------------
DROP TABLE IF EXISTS `park_lot`;
CREATE TABLE `park_lot`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `lot_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '车场ID（UUID）',
  `asset_extend_id` bigint NOT NULL COMMENT '关联资产扩展ID',
  `region_full_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '12位地区码',
  `total_space` int NOT NULL DEFAULT 0 COMMENT '总车位数',
  `available_space` int NOT NULL DEFAULT 0 COMMENT '当前可用车位数',
  `park_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '车场类型：地面/地下/立体/路侧',
  `open_time` time(0) NOT NULL COMMENT '开放时间',
  `close_time` time(0) NULL DEFAULT NULL COMMENT '关闭时间，24小时为NULL',
  `management_merchant_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '运营商户ID',
  `fee_strategy_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '默认费率策略ID',
  `lot_create_time` datetime(0) NULL DEFAULT NULL COMMENT '业务创建时间',
  `lot_update_time` datetime(0) NULL DEFAULT NULL COMMENT '业务更新时间',
  `lot_remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '业务备注',
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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '车场信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of park_lot
-- ----------------------------
INSERT INTO `park_lot` VALUES (1, 'lot_001_uuid_abcd1234efgh5678', 1, '350102001001', 200, 50, '地面', '00:00:00', NULL, 'merchant_001_uuid_1111', 'fee_001_uuid_aaaa', '2023-01-10 09:00:00', '2024-01-15 10:00:00', '王府井商业停车场', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-01-21 16:19:45', '2026-01-23 15:59:38');
INSERT INTO `park_lot` VALUES (2, 'lot_002_uuid_ijkl9012mnop3456', 2, '350102001001', 300, 120, '地下', '06:00:00', '23:00:00', 'merchant_002_uuid_2222', 'fee_002_uuid_bbbb', '2023-02-15 10:00:00', '2024-01-20 14:30:00', '西单购物中心停车场', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-01-21 16:19:45', '2026-01-23 15:59:38');
INSERT INTO `park_lot` VALUES (3, 'lot_003_uuid_qrst7890uvwx1234', 6, '350102001001', 500, 200, '立体', '00:00:00', NULL, 'merchant_003_uuid_3333', 'fee_003_uuid_cccc', '2023-06-10 15:00:00', '2024-02-05 16:00:00', '朝阳门立体停车库', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-01-21 16:19:45', '2026-01-23 15:59:38');
INSERT INTO `park_lot` VALUES (4, 'lot_004_uuid_yzab5678cdef9012', 1, '350102001001', 150, 30, '地面', '05:00:00', '22:00:00', 'merchant_001_uuid_1111', 'fee_001_uuid_aaaa', '2023-08-20 08:00:00', '2024-02-10 09:00:00', '王府井地面停车场', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-01-21 16:19:45', '2026-01-23 15:59:38');
INSERT INTO `park_lot` VALUES (5, 'lot_005_uuid_ghij2345klmn6789', 2, '350102001001', 100, 10, '地下', '07:00:00', '21:00:00', 'merchant_002_uuid_2222', 'fee_002_uuid_bbbb', '2023-09-15 14:00:00', '2024-02-12 16:00:00', '西单二期停车场', NULL, NULL, NULL, NULL, 'admin', 'admin', b'0', 1, '2026-01-21 16:19:45', '2026-01-23 15:59:38');

SET FOREIGN_KEY_CHECKS = 1;
