-- ============================================================
-- 订单交易模块 (order_trade) 建表 + 测试数据
-- 数据库: order_trade
-- 生成时间: 2026-04-19
-- ============================================================

CREATE DATABASE IF NOT EXISTS `order_trade` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `order_trade`;

-- -----------------------------------------------------------
-- 4-1. 代付规则 agent_rule
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS `agent_rule` (
  `id`               BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name`             VARCHAR(64)  NOT NULL                COMMENT '规则名称',
  `merchant_id`      BIGINT       NOT NULL                COMMENT '所属商户ID',
  `agent_type`       VARCHAR(20)  NOT NULL                COMMENT '代付类型(merchant/enterprise/public)',
  `single_limit`     DECIMAL(10,2) NOT NULL               COMMENT '单次限额(元)',
  `day_limit`        DECIMAL(10,2) NOT NULL               COMMENT '日累计限额(元)',
  `scene`            VARCHAR(64)  DEFAULT NULL            COMMENT '适用场景',
  `status`           VARCHAR(20)  NOT NULL DEFAULT 'pending' COMMENT '规则状态(pending/enabled/disabled)',
  `use_count`        INT          NOT NULL DEFAULT 0      COMMENT '规则使用次数',
  `auditor_id`       BIGINT       DEFAULT NULL            COMMENT '审核人ID',
  `audit_time`       DATETIME     DEFAULT NULL            COMMENT '审核时间',
  `last_update_time` DATETIME     DEFAULT NULL            COMMENT '最后变更时间',
  `remark`           TEXT         DEFAULT NULL            COMMENT '备注',
  `reserve1`         VARCHAR(100) DEFAULT NULL            COMMENT '备用字段1',
  `reserve2`         VARCHAR(100) DEFAULT NULL            COMMENT '备用字段2',
  `creator`          VARCHAR(64)  NOT NULL DEFAULT ''     COMMENT '创建者',
  `updater`          VARCHAR(64)  NOT NULL DEFAULT ''     COMMENT '更新者',
  `deleted`          BIT(1)       NOT NULL DEFAULT 0      COMMENT '删除标识(0未删除/1已删除)',
  `tenant_id`        BIGINT       NOT NULL DEFAULT 1      COMMENT '租户ID',
  `create_time`      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='代付规则表';

-- -----------------------------------------------------------
-- 4-2. 代付码 agent_code
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS `agent_code` (
  `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `code`        VARCHAR(64)  NOT NULL UNIQUE          COMMENT '代付码',
  `merchant_id` BIGINT       NOT NULL                 COMMENT '所属商户ID',
  `rule_id`     BIGINT       NOT NULL                 COMMENT '关联规则ID(agent_rule)',
  `expire_time` DATETIME     NOT NULL                 COMMENT '过期时间',
  `status`      VARCHAR(20)  NOT NULL DEFAULT 'unused' COMMENT '码状态(unused/used/expired)',
  `user_id`     BIGINT       DEFAULT NULL             COMMENT '使用人ID',
  `use_time`    DATETIME     DEFAULT NULL             COMMENT '使用时间',
  `order_id`    BIGINT       DEFAULT NULL             COMMENT '关联订单ID(agent_order)',
  `remark`      TEXT         DEFAULT NULL             COMMENT '备注',
  `reserve1`    VARCHAR(100) DEFAULT NULL             COMMENT '备用字段1',
  `reserve2`    VARCHAR(100) DEFAULT NULL             COMMENT '备用字段2',
  `creator`     VARCHAR(64)  NOT NULL DEFAULT ''      COMMENT '创建者',
  `updater`     VARCHAR(64)  NOT NULL DEFAULT ''      COMMENT '更新者',
  `deleted`     BIT(1)       NOT NULL DEFAULT 0       COMMENT '删除标识',
  `tenant_id`   BIGINT       NOT NULL DEFAULT 1       COMMENT '租户ID',
  `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='代付码表';

-- -----------------------------------------------------------
-- 4-3. 代付订单 agent_order
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS `agent_order` (
  `id`          BIGINT        NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `order_no`    VARCHAR(32)   NOT NULL UNIQUE         COMMENT '订单编号',
  `merchant_id` BIGINT        NOT NULL                COMMENT '所属商户ID',
  `car_no`      VARCHAR(16)   NOT NULL                COMMENT '车辆车牌',
  `amount`      DECIMAL(10,2) NOT NULL                COMMENT '代付金额(元)',
  `pay_type`    VARCHAR(20)   NOT NULL                COMMENT '支付方式(wechat/alipay/bank)',
  `status`      VARCHAR(20)   NOT NULL DEFAULT 'pending_pay' COMMENT '订单状态(pending_pay/paid/completed/cancelled)',
  `pay_time`    DATETIME      DEFAULT NULL            COMMENT '支付时间',
  `invoice_id`  BIGINT        DEFAULT NULL            COMMENT '关联发票ID',
  `operator_id` BIGINT        DEFAULT NULL            COMMENT '操作人ID',
  `remark`      TEXT          DEFAULT NULL            COMMENT '备注',
  `reserve1`    VARCHAR(100)  DEFAULT NULL            COMMENT '备用字段1',
  `reserve2`    VARCHAR(100)  DEFAULT NULL            COMMENT '备用字段2',
  `creator`     VARCHAR(64)   NOT NULL DEFAULT ''     COMMENT '创建者',
  `updater`     VARCHAR(64)   NOT NULL DEFAULT ''     COMMENT '更新者',
  `deleted`     BIT(1)        NOT NULL DEFAULT 0      COMMENT '删除标识',
  `tenant_id`   BIGINT        NOT NULL DEFAULT 1      COMMENT '租户ID',
  `create_time` DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='代付订单表';

-- -----------------------------------------------------------
-- 4-4. 代付记录 agent_record
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS `agent_record` (
  `id`           BIGINT        NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `record_no`    VARCHAR(32)   NOT NULL UNIQUE         COMMENT '记录编号',
  `order_id`     BIGINT        NOT NULL                COMMENT '关联订单ID(agent_order)',
  `merchant_id`  BIGINT        NOT NULL                COMMENT '所属商户ID',
  `amount`       DECIMAL(10,2) NOT NULL                COMMENT '变动金额(元)',
  `trade_time`   DATETIME      NOT NULL                COMMENT '交易时间',
  `status`       VARCHAR(20)   NOT NULL DEFAULT 'normal' COMMENT '记录状态(normal/abnormal)',
  `checker_id`   BIGINT        DEFAULT NULL            COMMENT '核查人ID',
  `check_time`   DATETIME      DEFAULT NULL            COMMENT '核查时间',
  `check_result` TEXT          DEFAULT NULL            COMMENT '核查结果',
  `remark`       TEXT          DEFAULT NULL            COMMENT '备注',
  `reserve1`     VARCHAR(100)  DEFAULT NULL            COMMENT '备用字段1',
  `reserve2`     VARCHAR(100)  DEFAULT NULL            COMMENT '备用字段2',
  `creator`      VARCHAR(64)   NOT NULL DEFAULT ''     COMMENT '创建者',
  `updater`      VARCHAR(64)   NOT NULL DEFAULT ''     COMMENT '更新者',
  `deleted`      BIT(1)        NOT NULL DEFAULT 0      COMMENT '删除标识',
  `tenant_id`    BIGINT        NOT NULL DEFAULT 1      COMMENT '租户ID',
  `create_time`  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='代付记录表';

-- -----------------------------------------------------------
-- 6-1. 结算单据 settle_bill
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS `settle_bill` (
  `id`            BIGINT        NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `bill_no`       VARCHAR(32)   NOT NULL UNIQUE         COMMENT '单据编号',
  `partner_id`    BIGINT        NOT NULL                COMMENT '合作方ID',
  `total_amount`  DECIMAL(10,2) NOT NULL                COMMENT '收费总额(元)',
  `split_amount`  DECIMAL(10,2) NOT NULL                COMMENT '分成金额(元)',
  `status`        VARCHAR(20)   NOT NULL DEFAULT 'pending_audit' COMMENT '状态(pending_audit/pending_settle/settled/rejected)',
  `auditor_id`    BIGINT        DEFAULT NULL            COMMENT '审核人ID',
  `audit_time`    DATETIME      DEFAULT NULL            COMMENT '审核时间',
  `settle_time`   DATETIME      DEFAULT NULL            COMMENT '结算时间',
  `remark`        TEXT          DEFAULT NULL            COMMENT '备注',
  `reserve1`      VARCHAR(100)  DEFAULT NULL            COMMENT '备用字段1',
  `reserve2`      VARCHAR(100)  DEFAULT NULL            COMMENT '备用字段2',
  `creator`       VARCHAR(64)   NOT NULL DEFAULT ''     COMMENT '创建者',
  `updater`       VARCHAR(64)   NOT NULL DEFAULT ''     COMMENT '更新者',
  `deleted`       BIT(1)        NOT NULL DEFAULT 0      COMMENT '删除标识',
  `tenant_id`     BIGINT        NOT NULL DEFAULT 1      COMMENT '租户ID',
  `create_time`   DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`   DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='结算单据表';

-- -----------------------------------------------------------
-- 6-2. 分账比例 split_rate
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS `split_rate` (
  `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `partner_id`  BIGINT       NOT NULL                COMMENT '合作方ID',
  `split_mode`  VARCHAR(20)  NOT NULL                COMMENT '分账模式(fixed/ladder)',
  `rate_value`  DECIMAL(5,2) NOT NULL                COMMENT '比例值(%)',
  `status`      VARCHAR(20)  NOT NULL DEFAULT 'pending' COMMENT '状态(pending/enabled/disabled)',
  `auditor_id`  BIGINT       DEFAULT NULL            COMMENT '审核人ID',
  `audit_time`  DATETIME     DEFAULT NULL            COMMENT '审核时间',
  `remark`      TEXT         DEFAULT NULL            COMMENT '备注',
  `reserve1`    VARCHAR(100) DEFAULT NULL            COMMENT '备用字段1',
  `reserve2`    VARCHAR(100) DEFAULT NULL            COMMENT '备用字段2',
  `creator`     VARCHAR(64)  NOT NULL DEFAULT ''     COMMENT '创建者',
  `updater`     VARCHAR(64)  NOT NULL DEFAULT ''     COMMENT '更新者',
  `deleted`     BIT(1)       NOT NULL DEFAULT 0      COMMENT '删除标识',
  `tenant_id`   BIGINT       NOT NULL DEFAULT 1      COMMENT '租户ID',
  `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分账比例表';

-- -----------------------------------------------------------
-- 6-3. 结算状态 settle_status
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS `settle_status` (
  `id`           BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `bill_id`      BIGINT       NOT NULL                COMMENT '关联单据ID(settle_bill)',
  `status`       VARCHAR(20)  NOT NULL DEFAULT 'normal' COMMENT '状态(normal/abnormal)',
  `update_time`  DATETIME     NOT NULL                COMMENT '状态更新时间',
  `error_reason` TEXT         DEFAULT NULL            COMMENT '异常原因',
  `checker_id`   BIGINT       DEFAULT NULL            COMMENT '核查人ID',
  `check_time`   DATETIME     DEFAULT NULL            COMMENT '核查时间',
  `remark`       TEXT         DEFAULT NULL            COMMENT '备注',
  `reserve1`     VARCHAR(100) DEFAULT NULL            COMMENT '备用字段1',
  `reserve2`     VARCHAR(100) DEFAULT NULL            COMMENT '备用字段2',
  `creator`      VARCHAR(64)  NOT NULL DEFAULT ''     COMMENT '创建者',
  `updater`      VARCHAR(64)  NOT NULL DEFAULT ''     COMMENT '更新者',
  `deleted`      BIT(1)       NOT NULL DEFAULT 0      COMMENT '删除标识',
  `tenant_id`    BIGINT       NOT NULL DEFAULT 1      COMMENT '租户ID',
  `create_time`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='结算状态表';

-- -----------------------------------------------------------
-- 7-1. 发票列表 invoice_list
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS `invoice_list` (
  `id`            BIGINT        NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `invoice_no`    VARCHAR(32)   NOT NULL UNIQUE         COMMENT '发票编号',
  `order_id`      BIGINT        NOT NULL                COMMENT '关联订单ID',
  `title`         VARCHAR(64)   NOT NULL                COMMENT '发票抬头',
  `tax_no`        VARCHAR(20)   NOT NULL                COMMENT '税号',
  `amount`        DECIMAL(10,2) NOT NULL                COMMENT '开票金额(元)',
  `status`        VARCHAR(20)   NOT NULL DEFAULT 'pending_audit' COMMENT '状态(pending_audit/pending_invoice/invoiced/rejected)',
  `auditor_id`    BIGINT        DEFAULT NULL            COMMENT '审核人ID',
  `audit_time`    DATETIME      DEFAULT NULL            COMMENT '审核时间',
  `invoice_time`  DATETIME      DEFAULT NULL            COMMENT '开票时间',
  `push_time`     DATETIME      DEFAULT NULL            COMMENT '推送时间',
  `download_url`  VARCHAR(255)  DEFAULT NULL            COMMENT '发票下载地址',
  `remark`        TEXT          DEFAULT NULL            COMMENT '备注',
  `reserve1`      VARCHAR(100)  DEFAULT NULL            COMMENT '备用字段1',
  `reserve2`      VARCHAR(100)  DEFAULT NULL            COMMENT '备用字段2',
  `creator`       VARCHAR(64)   NOT NULL DEFAULT ''     COMMENT '创建者',
  `updater`       VARCHAR(64)   NOT NULL DEFAULT ''     COMMENT '更新者',
  `deleted`       BIT(1)        NOT NULL DEFAULT 0      COMMENT '删除标识',
  `tenant_id`     BIGINT        NOT NULL DEFAULT 1      COMMENT '租户ID',
  `create_time`   DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`   DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='发票列表表';

-- -----------------------------------------------------------
-- 7-2. 开票审核 invoice_audit
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS `invoice_audit` (
  `id`           BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `apply_id`     BIGINT       NOT NULL                COMMENT '申请ID(invoice_list)',
  `applicant_id` BIGINT       NOT NULL                COMMENT '申请人ID',
  `apply_time`   DATETIME     NOT NULL                COMMENT '申请时间',
  `status`       VARCHAR(20)  NOT NULL DEFAULT 'pending' COMMENT '状态(pending/approved/rejected)',
  `auditor_id`   BIGINT       DEFAULT NULL            COMMENT '审核人ID',
  `audit_time`   DATETIME     DEFAULT NULL            COMMENT '审核时间',
  `audit_result` TEXT         DEFAULT NULL            COMMENT '审核结果',
  `remark`       TEXT         DEFAULT NULL            COMMENT '备注',
  `reserve1`     VARCHAR(100) DEFAULT NULL            COMMENT '备用字段1',
  `reserve2`     VARCHAR(100) DEFAULT NULL            COMMENT '备用字段2',
  `creator`      VARCHAR(64)  NOT NULL DEFAULT ''     COMMENT '创建者',
  `updater`      VARCHAR(64)  NOT NULL DEFAULT ''     COMMENT '更新者',
  `deleted`      BIT(1)       NOT NULL DEFAULT 0      COMMENT '删除标识',
  `tenant_id`    BIGINT       NOT NULL DEFAULT 1      COMMENT '租户ID',
  `create_time`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='开票审核表';

-- -----------------------------------------------------------
-- 7-3. 发票配置 invoice_config
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS `invoice_config` (
  `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `category`    VARCHAR(64)  NOT NULL                COMMENT '开票类目',
  `tax_rate`    DECIMAL(5,2) NOT NULL                COMMENT '税率(%)',
  `tax_body`    VARCHAR(64)  NOT NULL                COMMENT '开票主体',
  `status`      VARCHAR(20)  NOT NULL DEFAULT 'pending' COMMENT '状态(pending/enabled/disabled)',
  `auditor_id`  BIGINT       DEFAULT NULL            COMMENT '审核人ID',
  `audit_time`  DATETIME     DEFAULT NULL            COMMENT '审核时间',
  `remark`      TEXT         DEFAULT NULL            COMMENT '备注',
  `reserve1`    VARCHAR(100) DEFAULT NULL            COMMENT '备用字段1',
  `reserve2`    VARCHAR(100) DEFAULT NULL            COMMENT '备用字段2',
  `creator`     VARCHAR(64)  NOT NULL DEFAULT ''     COMMENT '创建者',
  `updater`     VARCHAR(64)  NOT NULL DEFAULT ''     COMMENT '更新者',
  `deleted`     BIT(1)       NOT NULL DEFAULT 0      COMMENT '删除标识',
  `tenant_id`   BIGINT       NOT NULL DEFAULT 1      COMMENT '租户ID',
  `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='发票配置表';

-- -----------------------------------------------------------
-- 8-1. 对账单据 reconcile_bill
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS `reconcile_bill` (
  `id`               BIGINT        NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `bill_no`          VARCHAR(32)   NOT NULL UNIQUE         COMMENT '单据编号',
  `merchant_id`      BIGINT        NOT NULL                COMMENT '商户ID',
  `cycle`            VARCHAR(32)   NOT NULL                COMMENT '对账周期',
  `platform_amount`  DECIMAL(10,2) NOT NULL                COMMENT '平台金额(元)',
  `merchant_amount`  DECIMAL(10,2) NOT NULL                COMMENT '商户金额(元)',
  `status`           VARCHAR(20)   NOT NULL DEFAULT 'pending' COMMENT '状态(pending/reconciled/abnormal)',
  `reconciler_id`    BIGINT        DEFAULT NULL            COMMENT '对账人ID',
  `reconcile_time`   DATETIME      DEFAULT NULL            COMMENT '对账时间',
  `confirm_time`     DATETIME      DEFAULT NULL            COMMENT '确认时间',
  `remark`           TEXT          DEFAULT NULL            COMMENT '备注',
  `reserve1`         VARCHAR(100)  DEFAULT NULL            COMMENT '备用字段1',
  `reserve2`         VARCHAR(100)  DEFAULT NULL            COMMENT '备用字段2',
  `creator`          VARCHAR(64)   NOT NULL DEFAULT ''     COMMENT '创建者',
  `updater`          VARCHAR(64)   NOT NULL DEFAULT ''     COMMENT '更新者',
  `deleted`          BIT(1)        NOT NULL DEFAULT 0      COMMENT '删除标识',
  `tenant_id`        BIGINT        NOT NULL DEFAULT 1      COMMENT '租户ID',
  `create_time`      DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`      DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='对账单据表';

-- -----------------------------------------------------------
-- 8-2. 对账记录 reconcile_record
-- -----------------------------------------------------------
CREATE TABLE IF NOT EXISTS `reconcile_record` (
  `id`           BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `bill_id`      BIGINT       NOT NULL                COMMENT '关联单据ID(reconcile_bill)',
  `status`       VARCHAR(20)  NOT NULL DEFAULT 'normal' COMMENT '状态(normal/abnormal)',
  `update_time`  DATETIME     NOT NULL                COMMENT '状态更新时间',
  `error_reason` TEXT         DEFAULT NULL            COMMENT '异常原因',
  `checker_id`   BIGINT       DEFAULT NULL            COMMENT '核查人ID',
  `check_time`   DATETIME     DEFAULT NULL            COMMENT '核查时间',
  `remark`       TEXT         DEFAULT NULL            COMMENT '备注',
  `reserve1`     VARCHAR(100) DEFAULT NULL            COMMENT '备用字段1',
  `reserve2`     VARCHAR(100) DEFAULT NULL            COMMENT '备用字段2',
  `creator`      VARCHAR(64)  NOT NULL DEFAULT ''     COMMENT '创建者',
  `updater`      VARCHAR(64)  NOT NULL DEFAULT ''     COMMENT '更新者',
  `deleted`      BIT(1)       NOT NULL DEFAULT 0      COMMENT '删除标识',
  `tenant_id`    BIGINT       NOT NULL DEFAULT 1      COMMENT '租户ID',
  `create_time`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='对账记录表';


-- ============================================================
-- 测试数据
-- ============================================================

-- agent_rule 测试数据
INSERT INTO `agent_rule` (`id`,`name`,`merchant_id`,`agent_type`,`single_limit`,`day_limit`,`scene`,`status`,`use_count`,`auditor_id`,`audit_time`,`remark`,`creator`,`updater`,`tenant_id`,`create_time`,`update_time`) VALUES
(1, '商户代付基础规则', 1001, 'merchant',  500.00, 2000.00, '停车场代付', 'enabled',  12, 1, '2026-04-01 09:00:00', '适用于标准停车场代付场景', 'admin', 'admin', 1, '2026-04-01 08:00:00', '2026-04-01 09:00:00'),
(2, '企业代付高额规则', 1002, 'enterprise', 2000.00, 10000.00, '企业停车补贴', 'enabled', 5,  1, '2026-04-02 10:00:00', '企业员工停车补贴使用', 'admin', 'admin', 1, '2026-04-02 09:00:00', '2026-04-02 10:00:00'),
(3, '公益代付规则',     1003, 'public',      200.00, 800.00,  '公益停车减免', 'pending', 0,  NULL, NULL, '公益活动减免规则', 'admin', 'admin', 1, '2026-04-10 10:00:00', '2026-04-10 10:00:00'),
(4, '商户VIP代付规则',  1001, 'merchant',   1000.00, 5000.00, 'VIP停车', 'disabled',  3, 1, '2026-03-15 09:00:00', 'VIP会员专属', 'admin', 'admin', 1, '2026-03-15 08:00:00', '2026-04-05 09:00:00'),
(5, '临时代付规则',     1004, 'merchant',    300.00, 1200.00, '临时活动', 'enabled',   8, 2, '2026-04-08 11:00:00', '节假日临时活动代付', 'admin', 'admin', 1, '2026-04-08 10:00:00', '2026-04-08 11:00:00');

-- agent_code 测试数据
INSERT INTO `agent_code` (`id`,`code`,`merchant_id`,`rule_id`,`expire_time`,`status`,`user_id`,`use_time`,`order_id`,`creator`,`updater`,`tenant_id`,`create_time`,`update_time`) VALUES
(1, 'AC20260401000001', 1001, 1, '2026-05-01 23:59:59', 'used',    2001, '2026-04-02 10:30:00', 1, 'admin', 'admin', 1, '2026-04-01 10:00:00', '2026-04-02 10:30:00'),
(2, 'AC20260403000002', 1001, 1, '2026-05-03 23:59:59', 'unused',  NULL, NULL,                 NULL, 'admin', 'admin', 1, '2026-04-03 09:00:00', '2026-04-03 09:00:00'),
(3, 'AC20260405000003', 1002, 2, '2026-04-10 23:59:59', 'expired', NULL, NULL,                 NULL, 'admin', 'admin', 1, '2026-04-05 09:00:00', '2026-04-11 00:00:01'),
(4, 'AC20260408000004', 1004, 5, '2026-05-08 23:59:59', 'used',    2003, '2026-04-09 14:20:00', 3, 'admin', 'admin', 1, '2026-04-08 12:00:00', '2026-04-09 14:20:00'),
(5, 'AC20260410000005', 1001, 1, '2026-05-10 23:59:59', 'unused',  NULL, NULL,                 NULL, 'admin', 'admin', 1, '2026-04-10 11:00:00', '2026-04-10 11:00:00');

-- agent_order 测试数据
INSERT INTO `agent_order` (`id`,`order_no`,`merchant_id`,`car_no`,`amount`,`pay_type`,`status`,`pay_time`,`operator_id`,`creator`,`updater`,`tenant_id`,`create_time`,`update_time`) VALUES
(1, 'AO20260402001',  1001, '粤A12345', 30.00, 'wechat', 'paid',        '2026-04-02 10:31:00', 1, 'admin', 'admin', 1, '2026-04-02 10:00:00', '2026-04-02 10:31:00'),
(2, 'AO20260403002',  1001, '粤B67890', 15.00, 'alipay', 'completed',   '2026-04-03 14:20:00', 1, 'admin', 'admin', 1, '2026-04-03 14:00:00', '2026-04-03 15:00:00'),
(3, 'AO20260409003',  1004, '粤C11111', 50.00, 'wechat', 'paid',        '2026-04-09 14:21:00', 2, 'admin', 'admin', 1, '2026-04-09 14:00:00', '2026-04-09 14:21:00'),
(4, 'AO20260410004',  1001, '粤D22222', 25.00, 'bank',   'pending_pay', NULL,                  1, 'admin', 'admin', 1, '2026-04-10 09:00:00', '2026-04-10 09:00:00'),
(5, 'AO20260411005',  1002, '粤E33333', 80.00, 'alipay', 'cancelled',   NULL,                  2, 'admin', 'admin', 1, '2026-04-11 08:00:00', '2026-04-11 08:30:00');

-- agent_record 测试数据
INSERT INTO `agent_record` (`id`,`record_no`,`order_id`,`merchant_id`,`amount`,`trade_time`,`status`,`checker_id`,`check_time`,`check_result`,`creator`,`updater`,`tenant_id`,`create_time`,`update_time`) VALUES
(1, 'AR20260402001', 1, 1001, 30.00, '2026-04-02 10:31:00', 'normal',   NULL, NULL,                 NULL,                   'admin', 'admin', 1, '2026-04-02 10:31:01', '2026-04-02 10:31:01'),
(2, 'AR20260403002', 2, 1001, 15.00, '2026-04-03 14:20:00', 'normal',   NULL, NULL,                 NULL,                   'admin', 'admin', 1, '2026-04-03 14:20:01', '2026-04-03 14:20:01'),
(3, 'AR20260409003', 3, 1004, 50.00, '2026-04-09 14:21:00', 'abnormal', 1,    '2026-04-10 09:00:00','金额与账单不符，已复核', 'admin', 'admin', 1, '2026-04-09 14:21:01', '2026-04-10 09:00:00'),
(4, 'AR20260410004', 4, 1001, 25.00, '2026-04-10 09:00:00', 'normal',   NULL, NULL,                 NULL,                   'admin', 'admin', 1, '2026-04-10 09:00:01', '2026-04-10 09:00:01'),
(5, 'AR20260411005', 5, 1002, 80.00, '2026-04-11 08:30:00', 'normal',   NULL, NULL,                 NULL,                   'admin', 'admin', 1, '2026-04-11 08:30:01', '2026-04-11 08:30:01');

-- settle_bill 测试数据
INSERT INTO `settle_bill` (`id`,`bill_no`,`partner_id`,`total_amount`,`split_amount`,`status`,`auditor_id`,`audit_time`,`settle_time`,`remark`,`creator`,`updater`,`tenant_id`,`create_time`,`update_time`) VALUES
(1, 'SB20260331001', 2001, 50000.00, 5000.00, 'settled',       1, '2026-04-01 10:00:00', '2026-04-02 14:00:00', '3月份结算单', 'admin', 'admin', 1, '2026-04-01 09:00:00', '2026-04-02 14:00:00'),
(2, 'SB20260407002', 2002, 32000.00, 3200.00, 'pending_settle', 1, '2026-04-08 09:00:00', NULL,                  '4月第一周', 'admin', 'admin', 1, '2026-04-07 10:00:00', '2026-04-08 09:00:00'),
(3, 'SB20260410003', 2001, 18000.00, 1800.00, 'pending_audit',  NULL, NULL,               NULL,                  '4月第二周', 'admin', 'admin', 1, '2026-04-10 09:00:00', '2026-04-10 09:00:00'),
(4, 'SB20260315004', 2003, 75000.00, 7500.00, 'rejected',       1, '2026-03-16 09:00:00', NULL,                  '数据有误需重提', 'admin', 'admin', 1, '2026-03-15 10:00:00', '2026-03-16 09:00:00'),
(5, 'SB20260105005', 2002, 120000.00,12000.00,'settled',        2, '2026-01-06 10:00:00', '2026-01-07 15:00:00', '1月份结算单', 'admin', 'admin', 1, '2026-01-05 09:00:00', '2026-01-07 15:00:00');

-- split_rate 测试数据
INSERT INTO `split_rate` (`id`,`partner_id`,`split_mode`,`rate_value`,`status`,`auditor_id`,`audit_time`,`remark`,`creator`,`updater`,`tenant_id`,`create_time`,`update_time`) VALUES
(1, 2001, 'fixed',  10.00, 'enabled',  1, '2026-01-02 09:00:00', '固定10%分成', 'admin', 'admin', 1, '2026-01-01 10:00:00', '2026-01-02 09:00:00'),
(2, 2002, 'fixed',  8.00,  'enabled',  1, '2026-01-02 09:00:00', '固定8%分成',  'admin', 'admin', 1, '2026-01-01 10:00:00', '2026-01-02 09:00:00'),
(3, 2003, 'ladder', 12.00, 'disabled', 1, '2026-02-01 09:00:00', '阶梯分成已停用', 'admin', 'admin', 1, '2026-02-01 08:00:00', '2026-03-01 09:00:00'),
(4, 2004, 'fixed',  15.00, 'pending',  NULL, NULL,               '新合作方待审核', 'admin', 'admin', 1, '2026-04-10 10:00:00', '2026-04-10 10:00:00'),
(5, 2001, 'ladder', 18.00, 'pending',  NULL, NULL,               '高额阶梯方案待审核', 'admin', 'admin', 1, '2026-04-12 09:00:00', '2026-04-12 09:00:00');

-- settle_status 测试数据
INSERT INTO `settle_status` (`id`,`bill_id`,`status`,`update_time`,`error_reason`,`checker_id`,`check_time`,`creator`,`updater`,`tenant_id`,`create_time`) VALUES
(1, 1, 'normal',   '2026-04-02 14:00:00', NULL,         NULL, NULL,                 'admin', 'admin', 1, '2026-04-02 14:00:01'),
(2, 2, 'normal',   '2026-04-08 09:00:00', NULL,         NULL, NULL,                 'admin', 'admin', 1, '2026-04-08 09:00:01'),
(3, 3, 'normal',   '2026-04-10 09:00:00', NULL,         NULL, NULL,                 'admin', 'admin', 1, '2026-04-10 09:00:01'),
(4, 4, 'abnormal', '2026-03-16 09:00:00', '单据金额与实际收款不符', 1, '2026-03-17 10:00:00', 'admin', 'admin', 1, '2026-03-16 09:00:01'),
(5, 5, 'normal',   '2026-01-07 15:00:00', NULL,         NULL, NULL,                 'admin', 'admin', 1, '2026-01-07 15:00:01');

-- invoice_list 测试数据
INSERT INTO `invoice_list` (`id`,`invoice_no`,`order_id`,`title`,`tax_no`,`amount`,`status`,`auditor_id`,`audit_time`,`invoice_time`,`push_time`,`download_url`,`creator`,`updater`,`tenant_id`,`create_time`,`update_time`) VALUES
(1, 'INV20260402001', 1, '广州某科技有限公司', '91440101MA9XXXXX01', 30.00, 'invoiced',      1, '2026-04-02 11:00:00', '2026-04-02 14:00:00', '2026-04-02 14:30:00', 'https://oss.example.com/invoice/INV20260402001.pdf', 'admin', 'admin', 1, '2026-04-02 10:35:00', '2026-04-02 14:30:00'),
(2, 'INV20260403002', 2, '深圳某贸易有限公司', '91440300MA9XXXXX02', 15.00, 'invoiced',      1, '2026-04-03 15:00:00', '2026-04-03 16:00:00', '2026-04-03 16:30:00', 'https://oss.example.com/invoice/INV20260403002.pdf', 'admin', 'admin', 1, '2026-04-03 14:30:00', '2026-04-03 16:30:00'),
(3, 'INV20260410003', 4, '东莞某实业有限公司', '91441900MA9XXXXX03', 25.00, 'pending_audit', NULL, NULL,               NULL,               NULL,               NULL, 'admin', 'admin', 1, '2026-04-10 09:30:00', '2026-04-10 09:30:00'),
(4, 'INV20260411004', 3, '佛山某集团有限公司', '91440600MA9XXXXX04', 50.00, 'pending_invoice',1, '2026-04-11 10:00:00',NULL,               NULL,               NULL, 'admin', 'admin', 1, '2026-04-11 09:00:00', '2026-04-11 10:00:00'),
(5, 'INV20260412005', 5, '珠海某科技有限公司', '91440400MA9XXXXX05', 80.00, 'rejected',      1, '2026-04-12 09:00:00', NULL,               NULL,               NULL, 'admin', 'admin', 1, '2026-04-12 08:00:00', '2026-04-12 09:00:00');

-- invoice_audit 测试数据
INSERT INTO `invoice_audit` (`id`,`apply_id`,`applicant_id`,`apply_time`,`status`,`auditor_id`,`audit_time`,`audit_result`,`creator`,`updater`,`tenant_id`,`create_time`,`update_time`) VALUES
(1, 1, 2001, '2026-04-02 10:35:00', 'approved', 1, '2026-04-02 11:00:00', '审核通过，资料齐全', 'admin', 'admin', 1, '2026-04-02 10:36:00', '2026-04-02 11:00:00'),
(2, 2, 2002, '2026-04-03 14:30:00', 'approved', 1, '2026-04-03 15:00:00', '审核通过',          'admin', 'admin', 1, '2026-04-03 14:31:00', '2026-04-03 15:00:00'),
(3, 3, 2001, '2026-04-10 09:30:00', 'pending',  NULL, NULL,               NULL,                'admin', 'admin', 1, '2026-04-10 09:31:00', '2026-04-10 09:31:00'),
(4, 4, 2003, '2026-04-11 09:00:00', 'approved', 1, '2026-04-11 10:00:00', '金额核实正确，通过', 'admin', 'admin', 1, '2026-04-11 09:01:00', '2026-04-11 10:00:00'),
(5, 5, 2002, '2026-04-12 08:00:00', 'rejected', 1, '2026-04-12 09:00:00', '税号填写有误，请修正后重新提交', 'admin', 'admin', 1, '2026-04-12 08:01:00', '2026-04-12 09:00:00');

-- invoice_config 测试数据
INSERT INTO `invoice_config` (`id`,`category`,`tax_rate`,`tax_body`,`status`,`auditor_id`,`audit_time`,`remark`,`creator`,`updater`,`tenant_id`,`create_time`,`update_time`) VALUES
(1, '停车费',   6.00, '广州耕传科技有限公司', 'enabled',  1, '2026-01-02 09:00:00', '停车场收费发票配置', 'admin', 'admin', 1, '2026-01-01 10:00:00', '2026-01-02 09:00:00'),
(2, '充电服务费', 9.00, '广州耕传科技有限公司', 'enabled',  1, '2026-01-02 09:00:00', '充电桩收费发票配置', 'admin', 'admin', 1, '2026-01-01 10:00:00', '2026-01-02 09:00:00'),
(3, '代付服务费', 6.00, '广州耕传科技有限公司', 'pending',  NULL, NULL,               '代付业务发票待生效', 'admin', 'admin', 1, '2026-04-10 10:00:00', '2026-04-10 10:00:00'),
(4, '会员服务费', 6.00, '广州耕传科技有限公司', 'disabled', 1, '2026-02-01 09:00:00', '已停用', 'admin', 'admin', 1, '2026-02-01 08:00:00', '2026-03-01 09:00:00'),
(5, '平台技术服务费', 13.00,'广州耕传科技有限公司','enabled', 1, '2026-03-01 09:00:00', 'SaaS平台服务发票', 'admin', 'admin', 1, '2026-03-01 08:00:00', '2026-03-01 09:00:00');

-- reconcile_bill 测试数据
INSERT INTO `reconcile_bill` (`id`,`bill_no`,`merchant_id`,`cycle`,`platform_amount`,`merchant_amount`,`status`,`reconciler_id`,`reconcile_time`,`confirm_time`,`remark`,`creator`,`updater`,`tenant_id`,`create_time`,`update_time`) VALUES
(1, 'RB20260401001', 1001, '2026-03', 50000.00, 50000.00, 'reconciled', 1, '2026-04-01 10:00:00', '2026-04-02 09:00:00', '3月份对账，数据一致', 'admin', 'admin', 1, '2026-04-01 09:00:00', '2026-04-02 09:00:00'),
(2, 'RB20260401002', 1002, '2026-03', 32000.00, 31800.00, 'abnormal',   1, '2026-04-01 11:00:00', NULL,                  '差额200元待核实',     'admin', 'admin', 1, '2026-04-01 09:00:00', '2026-04-01 11:00:00'),
(3, 'RB20260408003', 1003, '2026-W14', 18000.00, 18000.00, 'reconciled', 2, '2026-04-08 09:00:00', '2026-04-09 10:00:00', '第14周对账完成',      'admin', 'admin', 1, '2026-04-08 08:00:00', '2026-04-09 10:00:00'),
(4, 'RB20260410004', 1004, '2026-W15', 9500.00,  9500.00, 'pending',    NULL, NULL,                NULL,                  '第15周对账待处理',    'admin', 'admin', 1, '2026-04-10 08:00:00', '2026-04-10 08:00:00'),
(5, 'RB20260101005', 1001, '2025-12', 88000.00, 88000.00, 'reconciled', 1, '2026-01-02 10:00:00', '2026-01-03 09:00:00', '12月份对账一致',      'admin', 'admin', 1, '2026-01-01 09:00:00', '2026-01-03 09:00:00');

-- reconcile_record 测试数据
INSERT INTO `reconcile_record` (`id`,`bill_id`,`status`,`update_time`,`error_reason`,`checker_id`,`check_time`,`creator`,`updater`,`tenant_id`,`create_time`) VALUES
(1, 1, 'normal',   '2026-04-01 10:00:00', NULL,                    NULL, NULL,                 'admin', 'admin', 1, '2026-04-01 10:00:01'),
(2, 2, 'abnormal', '2026-04-01 11:00:00', '平台与商户金额差异200元', 1,   '2026-04-02 10:00:00', 'admin', 'admin', 1, '2026-04-01 11:00:01'),
(3, 3, 'normal',   '2026-04-08 09:00:00', NULL,                    NULL, NULL,                 'admin', 'admin', 1, '2026-04-08 09:00:01'),
(4, 4, 'normal',   '2026-04-10 08:00:00', NULL,                    NULL, NULL,                 'admin', 'admin', 1, '2026-04-10 08:00:01'),
(5, 5, 'normal',   '2026-01-02 10:00:00', NULL,                    NULL, NULL,                 'admin', 'admin', 1, '2026-01-02 10:00:01');
