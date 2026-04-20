-- =====================================================================
-- 车务服务 carservice 数据库初始化脚本
-- 数据库名：car_service
-- 适用：MySQL 8.x
-- 模块：genchuan-module-chargepark/genchuan-module-carservice
-- 包路径：cn.iocoder.yudao.module.chargepark.carservice
-- 表清单（11 张业务表）：
--   1. rescue_info        救援信息
--   2. charge_park_map    充停地图
--   3. near_station       周边场站
--   4. space_push         空位推送
--   5. reserve_list       预约列表
--   6. space_location     车位定位
--   7. path_plan          路径规划
--   8. suggestion         意见建议
--   9. user_appeal        用户申诉
--  10. dispute_mediate    纠纷调解
--  11. wording_mgmt       话术管理
-- 决策分析（service_op_report）不独立建表，基于现有业务表 SQL 聚合实现。
-- =====================================================================

CREATE DATABASE IF NOT EXISTS `car_service`
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;

USE `car_service`;

-- ---------------------------------------------------------------------
-- 1. 救援信息 rescue_info（救援服务 rescue-service）
-- ---------------------------------------------------------------------
DROP TABLE IF EXISTS `rescue_info`;
CREATE TABLE `rescue_info` (
    `id`                BIGINT          NOT NULL AUTO_INCREMENT                COMMENT '主键 ID',
    `user_id`           BIGINT          NOT NULL                                COMMENT '用户 ID，关联芋道用户表 system_user',
    `location`          VARCHAR(255)    NOT NULL                                COMMENT '救援位置，记录救援地址或经纬度信息',
    `rescue_type`       VARCHAR(20)     NOT NULL                                COMMENT '救援类型：道路救援/充电故障救援/停车故障救援，关联芋道字典表 rescue_info_rescue_type',
    `dispatch_time`     DATETIME        DEFAULT NULL                            COMMENT '派发时间',
    `status`            VARCHAR(20)     NOT NULL                                COMMENT '救援状态：待派发/待认领/处理中/已完成，关联芋道字典表 rescue_info_status',
    `rescue_user_id`    BIGINT          DEFAULT NULL                            COMMENT '救援人员 ID，关联芋道用户表 system_user',
    `finish_time`       DATETIME        DEFAULT NULL                            COMMENT '完成时间',
    `handle_duration`   INT             DEFAULT NULL                            COMMENT '处理时长（秒），救援全流程处理耗时',
    `score`             TINYINT         DEFAULT NULL                            COMMENT '评价得分，1-5 分',
    `archive_status`    VARCHAR(20)     NOT NULL DEFAULT '未归档'                COMMENT '归档状态：未归档/已归档，关联芋道字典表 rescue_info_archive_status',
    `dispatch_remark`   VARCHAR(255)    DEFAULT NULL                            COMMENT '派发备注',
    `transfer_reason`   VARCHAR(255)    DEFAULT NULL                            COMMENT '转派理由',
    `progress`          VARCHAR(255)    DEFAULT NULL                            COMMENT '救援进度，当前救援处理进度描述',
    `photo`             VARCHAR(255)    DEFAULT NULL                            COMMENT '现场照片，存储地址',
    `evaluate_content`  TEXT                                                    COMMENT '评价内容',
    `reserve1`          VARCHAR(100)    DEFAULT NULL                            COMMENT '备用字段 1',
    `reserve2`          VARCHAR(100)    DEFAULT NULL                            COMMENT '备用字段 2',
    `creator`           VARCHAR(64)     DEFAULT ''                              COMMENT '创建者',
    `updater`           VARCHAR(64)     DEFAULT ''                              COMMENT '更新者',
    `deleted`           BIT(1)          NOT NULL DEFAULT b'0'                   COMMENT '删除标识：0-未删除，1-已删除',
    `tenant_id`         BIGINT          NOT NULL DEFAULT 1                      COMMENT '租户 ID，关联芋道租户表 system_tenant',
    `create_time`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP      COMMENT '创建时间',
    `update_time`       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_user_id` (`user_id`),
    KEY `idx_status` (`status`),
    KEY `idx_create_time` (`create_time`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC COMMENT = '救援信息表';

-- ---------------------------------------------------------------------
-- 2. 充停地图 charge_park_map（车辆引导 car-guide）
-- ---------------------------------------------------------------------
DROP TABLE IF EXISTS `charge_park_map`;
CREATE TABLE `charge_park_map` (
    `id`                 BIGINT         NOT NULL AUTO_INCREMENT                COMMENT '主键 ID',
    `user_id`            BIGINT         NOT NULL                                COMMENT '用户 ID，关联芋道用户表 system_user',
    `query_location`     VARCHAR(255)   NOT NULL                                COMMENT '查询位置，记录用户查询时的位置信息',
    `query_time`         DATETIME       NOT NULL                                COMMENT '查询时间',
    `result_count`       INT            NOT NULL DEFAULT 0                      COMMENT '查询结果数，本次查询返回的场站/车位结果数量',
    `response_duration`  INT            NOT NULL DEFAULT 0                      COMMENT '响应时长（毫秒）',
    `reserve1`           VARCHAR(100)   DEFAULT NULL                            COMMENT '备用字段 1',
    `reserve2`           VARCHAR(100)   DEFAULT NULL                            COMMENT '备用字段 2',
    `creator`            VARCHAR(64)    DEFAULT ''                              COMMENT '创建者',
    `updater`            VARCHAR(64)    DEFAULT ''                              COMMENT '更新者',
    `deleted`            BIT(1)         NOT NULL DEFAULT b'0'                   COMMENT '删除标识：0-未删除，1-已删除',
    `tenant_id`          BIGINT         NOT NULL DEFAULT 1                      COMMENT '租户 ID',
    `create_time`        DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP      COMMENT '创建时间',
    `update_time`        DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_user_id` (`user_id`),
    KEY `idx_query_time` (`query_time`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC COMMENT = '充停地图表';

-- ---------------------------------------------------------------------
-- 3. 周边场站 near_station（车辆引导 car-guide）
-- ---------------------------------------------------------------------
DROP TABLE IF EXISTS `near_station`;
CREATE TABLE `near_station` (
    `id`                  BIGINT        NOT NULL AUTO_INCREMENT                COMMENT '主键 ID',
    `user_id`             BIGINT        NOT NULL                                COMMENT '用户 ID，关联芋道用户表 system_user',
    `query_location`      VARCHAR(255)  NOT NULL                                COMMENT '查询位置',
    `query_time`          DATETIME      NOT NULL                                COMMENT '查询时间',
    `station_count`       INT           NOT NULL DEFAULT 0                      COMMENT '周边场站数',
    `empty_station_count` INT           NOT NULL DEFAULT 0                      COMMENT '空位场站数',
    `reserve1`            VARCHAR(100)  DEFAULT NULL                            COMMENT '备用字段 1',
    `reserve2`            VARCHAR(100)  DEFAULT NULL                            COMMENT '备用字段 2',
    `creator`             VARCHAR(64)   DEFAULT ''                              COMMENT '创建者',
    `updater`             VARCHAR(64)   DEFAULT ''                              COMMENT '更新者',
    `deleted`             BIT(1)        NOT NULL DEFAULT b'0'                   COMMENT '删除标识：0-未删除，1-已删除',
    `tenant_id`           BIGINT        NOT NULL DEFAULT 1                      COMMENT '租户 ID',
    `create_time`         DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP      COMMENT '创建时间',
    `update_time`         DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_user_id` (`user_id`),
    KEY `idx_query_time` (`query_time`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC COMMENT = '周边场站表';

-- ---------------------------------------------------------------------
-- 4. 空位推送 space_push（车辆引导 car-guide）
-- ---------------------------------------------------------------------
DROP TABLE IF EXISTS `space_push`;
CREATE TABLE `space_push` (
    `id`            BIGINT       NOT NULL AUTO_INCREMENT                COMMENT '主键 ID',
    `user_id`       BIGINT       NOT NULL                                COMMENT '用户 ID，关联芋道用户表 system_user',
    `station_id`    BIGINT       NOT NULL                                COMMENT '场站 ID，关联场站模块场站表',
    `space_info`    VARCHAR(255) NOT NULL                                COMMENT '空位信息，如车位编号、剩余时长等',
    `push_time`     DATETIME     DEFAULT NULL                            COMMENT '推送时间',
    `status`        VARCHAR(20)  NOT NULL DEFAULT '待推送'                COMMENT '推送状态：待推送/已推送，关联芋道字典表 space_push_status',
    `push_result`   VARCHAR(20)  DEFAULT NULL                            COMMENT '推送结果：成功/失败，关联芋道字典表 space_push_push_result',
    `feedback_time` DATETIME     DEFAULT NULL                            COMMENT '反馈时间，记录用户接收推送后的反馈时间',
    `reserve1`      VARCHAR(100) DEFAULT NULL                            COMMENT '备用字段 1',
    `reserve2`      VARCHAR(100) DEFAULT NULL                            COMMENT '备用字段 2',
    `creator`       VARCHAR(64)  DEFAULT ''                              COMMENT '创建者',
    `updater`       VARCHAR(64)  DEFAULT ''                              COMMENT '更新者',
    `deleted`       BIT(1)       NOT NULL DEFAULT b'0'                   COMMENT '删除标识：0-未删除，1-已删除',
    `tenant_id`     BIGINT       NOT NULL DEFAULT 1                      COMMENT '租户 ID',
    `create_time`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP      COMMENT '创建时间',
    `update_time`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_user_id` (`user_id`),
    KEY `idx_station_id` (`station_id`),
    KEY `idx_status` (`status`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC COMMENT = '空位推送表';

-- ---------------------------------------------------------------------
-- 5. 预约列表 reserve_list（预约服务 reserve-service）
-- ---------------------------------------------------------------------
DROP TABLE IF EXISTS `reserve_list`;
CREATE TABLE `reserve_list` (
    `id`               BIGINT       NOT NULL AUTO_INCREMENT                COMMENT '主键 ID',
    `user_id`          BIGINT       NOT NULL                                COMMENT '用户 ID，关联芋道用户表 system_user',
    `station_id`       BIGINT       NOT NULL                                COMMENT '场站 ID，关联场站模块场站表',
    `space_id`         BIGINT       NOT NULL                                COMMENT '车位 ID，关联车位模块车位表',
    `reserve_time`     DATETIME     NOT NULL                                COMMENT '预约时间，用户预约的使用时间',
    `reserve_type`     VARCHAR(20)  NOT NULL                                COMMENT '预约类型：停车预约/充电预约，关联芋道字典表 reserve_list_reserve_type',
    `status`           VARCHAR(20)  NOT NULL                                COMMENT '预约状态：待审核/已生效/已完成/已取消，关联芋道字典表 reserve_list_status',
    `audit_user_id`    BIGINT       DEFAULT NULL                            COMMENT '审核人 ID，关联芋道用户表 system_user',
    `audit_time`       DATETIME     DEFAULT NULL                            COMMENT '审核时间',
    `finish_time`      DATETIME     DEFAULT NULL                            COMMENT '完成时间',
    `score`            TINYINT      DEFAULT NULL                            COMMENT '评价得分，1-5 分',
    `audit_remark`     VARCHAR(255) DEFAULT NULL                            COMMENT '审核备注',
    `reject_reason`    VARCHAR(255) DEFAULT NULL                            COMMENT '驳回理由',
    `evaluate_content` TEXT                                                 COMMENT '评价内容',
    `reserve1`         VARCHAR(100) DEFAULT NULL                            COMMENT '备用字段 1',
    `reserve2`         VARCHAR(100) DEFAULT NULL                            COMMENT '备用字段 2',
    `creator`          VARCHAR(64)  DEFAULT ''                              COMMENT '创建者',
    `updater`          VARCHAR(64)  DEFAULT ''                              COMMENT '更新者',
    `deleted`          BIT(1)       NOT NULL DEFAULT b'0'                   COMMENT '删除标识：0-未删除，1-已删除',
    `tenant_id`        BIGINT       NOT NULL DEFAULT 1                      COMMENT '租户 ID',
    `create_time`      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP      COMMENT '创建时间',
    `update_time`      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_user_id` (`user_id`),
    KEY `idx_station_id` (`station_id`),
    KEY `idx_space_id` (`space_id`),
    KEY `idx_status` (`status`),
    KEY `idx_reserve_time` (`reserve_time`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC COMMENT = '预约列表表';

-- ---------------------------------------------------------------------
-- 6. 车位定位 space_location（反向寻车 reverse-find-car）
-- ---------------------------------------------------------------------
DROP TABLE IF EXISTS `space_location`;
CREATE TABLE `space_location` (
    `id`                BIGINT       NOT NULL AUTO_INCREMENT                COMMENT '主键 ID',
    `user_id`           BIGINT       NOT NULL                                COMMENT '用户 ID，关联芋道用户表 system_user',
    `plate_no`          VARCHAR(20)  NOT NULL                                COMMENT '车牌号码',
    `query_time`        DATETIME     NOT NULL                                COMMENT '查询时间',
    `space_id`          BIGINT       DEFAULT NULL                            COMMENT '车位 ID，关联车位模块车位表',
    `location_result`   VARCHAR(20)  DEFAULT NULL                            COMMENT '定位结果：成功/失败，关联芋道字典表 space_location_location_result',
    `response_duration` INT          NOT NULL DEFAULT 0                      COMMENT '响应时长（毫秒）',
    `reserve1`          VARCHAR(100) DEFAULT NULL                            COMMENT '备用字段 1',
    `reserve2`          VARCHAR(100) DEFAULT NULL                            COMMENT '备用字段 2',
    `creator`           VARCHAR(64)  DEFAULT ''                              COMMENT '创建者',
    `updater`           VARCHAR(64)  DEFAULT ''                              COMMENT '更新者',
    `deleted`           BIT(1)       NOT NULL DEFAULT b'0'                   COMMENT '删除标识：0-未删除，1-已删除',
    `tenant_id`         BIGINT       NOT NULL DEFAULT 1                      COMMENT '租户 ID',
    `create_time`       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP      COMMENT '创建时间',
    `update_time`       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_user_id` (`user_id`),
    KEY `idx_plate_no` (`plate_no`),
    KEY `idx_query_time` (`query_time`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC COMMENT = '车位定位表';

-- ---------------------------------------------------------------------
-- 7. 路径规划 path_plan（反向寻车 reverse-find-car）
-- ---------------------------------------------------------------------
DROP TABLE IF EXISTS `path_plan`;
CREATE TABLE `path_plan` (
    `id`              BIGINT       NOT NULL AUTO_INCREMENT                COMMENT '主键 ID',
    `user_id`         BIGINT       NOT NULL                                COMMENT '用户 ID，关联芋道用户表 system_user',
    `start_location`  VARCHAR(255) NOT NULL                                COMMENT '起点位置',
    `end_location`    VARCHAR(255) NOT NULL                                COMMENT '终点位置',
    `plan_time`       DATETIME     NOT NULL                                COMMENT '规划时间',
    `path_length`     INT          NOT NULL DEFAULT 0                      COMMENT '路径长度（米）',
    `expect_duration` INT          NOT NULL DEFAULT 0                      COMMENT '预计时长（秒）',
    `reserve1`        VARCHAR(100) DEFAULT NULL                            COMMENT '备用字段 1',
    `reserve2`        VARCHAR(100) DEFAULT NULL                            COMMENT '备用字段 2',
    `creator`         VARCHAR(64)  DEFAULT ''                              COMMENT '创建者',
    `updater`         VARCHAR(64)  DEFAULT ''                              COMMENT '更新者',
    `deleted`         BIT(1)       NOT NULL DEFAULT b'0'                   COMMENT '删除标识：0-未删除，1-已删除',
    `tenant_id`       BIGINT       NOT NULL DEFAULT 1                      COMMENT '租户 ID',
    `create_time`     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP      COMMENT '创建时间',
    `update_time`     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_user_id` (`user_id`),
    KEY `idx_plan_time` (`plan_time`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC COMMENT = '路径规划表';

-- ---------------------------------------------------------------------
-- 8. 意见建议 suggestion（投诉调解 complaint-mediate）
-- ---------------------------------------------------------------------
DROP TABLE IF EXISTS `suggestion`;
CREATE TABLE `suggestion` (
    `id`               BIGINT       NOT NULL AUTO_INCREMENT                COMMENT '主键 ID',
    `user_id`          BIGINT       NOT NULL                                COMMENT '用户 ID，关联芋道用户表 system_user',
    `content`          TEXT         NOT NULL                                COMMENT '意见内容',
    `submit_time`      DATETIME     NOT NULL                                COMMENT '提交时间',
    `status`           VARCHAR(20)  NOT NULL                                COMMENT '处理状态：待处理/处理中/已完成，关联芋道字典表 suggestion_status',
    `handle_user_id`   BIGINT       DEFAULT NULL                            COMMENT '处理人 ID，关联芋道用户表 system_user',
    `progress`         VARCHAR(255) DEFAULT NULL                            COMMENT '处理进度描述',
    `feedback_content` TEXT                                                 COMMENT '反馈内容',
    `feedback_time`    DATETIME     DEFAULT NULL                            COMMENT '反馈时间',
    `reserve1`         VARCHAR(100) DEFAULT NULL                            COMMENT '备用字段 1',
    `reserve2`         VARCHAR(100) DEFAULT NULL                            COMMENT '备用字段 2',
    `creator`          VARCHAR(64)  DEFAULT ''                              COMMENT '创建者',
    `updater`          VARCHAR(64)  DEFAULT ''                              COMMENT '更新者',
    `deleted`          BIT(1)       NOT NULL DEFAULT b'0'                   COMMENT '删除标识：0-未删除，1-已删除',
    `tenant_id`        BIGINT       NOT NULL DEFAULT 1                      COMMENT '租户 ID',
    `create_time`      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP      COMMENT '创建时间',
    `update_time`      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_user_id` (`user_id`),
    KEY `idx_status` (`status`),
    KEY `idx_submit_time` (`submit_time`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC COMMENT = '意见建议表';

-- ---------------------------------------------------------------------
-- 9. 用户申诉 user_appeal（投诉调解 complaint-mediate）
-- ---------------------------------------------------------------------
DROP TABLE IF EXISTS `user_appeal`;
CREATE TABLE `user_appeal` (
    `id`               BIGINT       NOT NULL AUTO_INCREMENT                COMMENT '主键 ID',
    `user_id`          BIGINT       NOT NULL                                COMMENT '用户 ID，关联芋道用户表 system_user',
    `order_id`         BIGINT       DEFAULT NULL                            COMMENT '订单 ID，关联订单模块订单表',
    `content`          TEXT         NOT NULL                                COMMENT '申诉内容',
    `submit_time`      DATETIME     NOT NULL                                COMMENT '提交时间',
    `status`           VARCHAR(20)  NOT NULL                                COMMENT '申诉状态：待审核/待处置/已完成，关联芋道字典表 user_appeal_status',
    `audit_user_id`    BIGINT       DEFAULT NULL                            COMMENT '审核人 ID，关联芋道用户表 system_user',
    `audit_time`       DATETIME     DEFAULT NULL                            COMMENT '审核时间',
    `handle_user_id`   BIGINT       DEFAULT NULL                            COMMENT '处置人 ID，关联芋道用户表 system_user',
    `progress`         VARCHAR(255) DEFAULT NULL                            COMMENT '处置进度描述',
    `feedback_content` TEXT                                                 COMMENT '反馈内容',
    `feedback_time`    DATETIME     DEFAULT NULL                            COMMENT '反馈时间',
    `audit_remark`     VARCHAR(255) DEFAULT NULL                            COMMENT '审核备注',
    `reject_reason`    VARCHAR(255) DEFAULT NULL                            COMMENT '驳回理由',
    `reserve1`         VARCHAR(100) DEFAULT NULL                            COMMENT '备用字段 1',
    `reserve2`         VARCHAR(100) DEFAULT NULL                            COMMENT '备用字段 2',
    `creator`          VARCHAR(64)  DEFAULT ''                              COMMENT '创建者',
    `updater`          VARCHAR(64)  DEFAULT ''                              COMMENT '更新者',
    `deleted`          BIT(1)       NOT NULL DEFAULT b'0'                   COMMENT '删除标识：0-未删除，1-已删除',
    `tenant_id`        BIGINT       NOT NULL DEFAULT 1                      COMMENT '租户 ID',
    `create_time`      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP      COMMENT '创建时间',
    `update_time`      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_user_id` (`user_id`),
    KEY `idx_order_id` (`order_id`),
    KEY `idx_status` (`status`),
    KEY `idx_submit_time` (`submit_time`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC COMMENT = '用户申诉表';

-- ---------------------------------------------------------------------
-- 10. 纠纷调解 dispute_mediate（投诉调解 complaint-mediate）
-- ---------------------------------------------------------------------
DROP TABLE IF EXISTS `dispute_mediate`;
CREATE TABLE `dispute_mediate` (
    `id`               BIGINT       NOT NULL AUTO_INCREMENT                COMMENT '主键 ID',
    `user_id`          BIGINT       NOT NULL                                COMMENT '用户 ID，关联芋道用户表 system_user',
    `merchant_id`      BIGINT       NOT NULL                                COMMENT '商户 ID，关联商户模块商户表',
    `content`          TEXT         NOT NULL                                COMMENT '纠纷内容',
    `submit_time`      DATETIME     NOT NULL                                COMMENT '发起时间',
    `status`           VARCHAR(20)  NOT NULL                                COMMENT '调解状态：待调解/调解中/已完成，关联芋道字典表 dispute_mediate_status',
    `mediate_user_id`  BIGINT       DEFAULT NULL                            COMMENT '调解人 ID，关联芋道用户表 system_user',
    `progress`         VARCHAR(255) DEFAULT NULL                            COMMENT '调解进度描述',
    `confirm_time`     DATETIME     DEFAULT NULL                            COMMENT '确认时间，记录双方确认调解结果的时间',
    `reserve1`         VARCHAR(100) DEFAULT NULL                            COMMENT '备用字段 1',
    `reserve2`         VARCHAR(100) DEFAULT NULL                            COMMENT '备用字段 2',
    `creator`          VARCHAR(64)  DEFAULT ''                              COMMENT '创建者',
    `updater`          VARCHAR(64)  DEFAULT ''                              COMMENT '更新者',
    `deleted`          BIT(1)       NOT NULL DEFAULT b'0'                   COMMENT '删除标识：0-未删除，1-已删除',
    `tenant_id`        BIGINT       NOT NULL DEFAULT 1                      COMMENT '租户 ID',
    `create_time`      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP      COMMENT '创建时间',
    `update_time`      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_user_id` (`user_id`),
    KEY `idx_merchant_id` (`merchant_id`),
    KEY `idx_status` (`status`),
    KEY `idx_submit_time` (`submit_time`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC COMMENT = '纠纷调解表';

-- ---------------------------------------------------------------------
-- 11. 话术管理 wording_mgmt（客服配置 service-config）
-- ---------------------------------------------------------------------
DROP TABLE IF EXISTS `wording_mgmt`;
CREATE TABLE `wording_mgmt` (
    `id`          BIGINT       NOT NULL AUTO_INCREMENT                COMMENT '主键 ID',
    `name`        VARCHAR(64)  NOT NULL                                COMMENT '话术名称',
    `content`     TEXT         NOT NULL                                COMMENT '话术内容，具体回复内容',
    `type`        VARCHAR(20)  NOT NULL                                COMMENT '话术类型：快捷回复/自动回复/投诉回复，关联芋道字典表 wording_mgmt_type',
    `status`      VARCHAR(20)  NOT NULL DEFAULT '未生效'                COMMENT '状态：未生效/已生效，关联芋道字典表 wording_mgmt_status',
    `reserve1`    VARCHAR(100) DEFAULT NULL                            COMMENT '备用字段 1',
    `reserve2`    VARCHAR(100) DEFAULT NULL                            COMMENT '备用字段 2',
    `creator`     VARCHAR(64)  DEFAULT ''                              COMMENT '创建者',
    `updater`     VARCHAR(64)  DEFAULT ''                              COMMENT '更新者',
    `deleted`     BIT(1)       NOT NULL DEFAULT b'0'                   COMMENT '删除标识：0-未删除，1-已删除',
    `tenant_id`   BIGINT       NOT NULL DEFAULT 1                      COMMENT '租户 ID',
    `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP      COMMENT '创建时间',
    `update_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_type` (`type`),
    KEY `idx_status` (`status`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC COMMENT = '话术管理表';
