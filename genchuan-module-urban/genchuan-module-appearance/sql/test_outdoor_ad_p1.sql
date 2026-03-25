CREATE TABLE `outdoor_ad` (
  -- 系统主键
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  
  -- 业务主键
  `outdoor_ad_id` VARCHAR(36) NOT NULL COMMENT '广告ID',
  
  -- 业务字段
  `name` VARCHAR(255) COMMENT '广告名称',
  `location` VARCHAR(255) COMMENT '广告位置',
  `approved_size` VARCHAR(50) COMMENT '审批尺寸',
  `actual_size` VARCHAR(50) COMMENT '实际尺寸',
  `tilt_angle` DECIMAL(5, 2) COMMENT '倾斜角度',
  `damage_status_id` VARCHAR(36) COMMENT '关联sys_damage_status.id，破损状态',
  `ad_status_id` VARCHAR(36) COMMENT '关联sys_ad_status.id，广告状态',
  `area_code` VARCHAR(20) COMMENT '关联sys_area.area_code，所属区域',
  `supervisor_id` VARCHAR(36) COMMENT '关联sys_user.id，监管员',
  `warning_type_id` VARCHAR(36) COMMENT '关联sys_warning_type.id，预警类型',
  `warning_time` DATETIME COMMENT '预警时间',
  
  -- 拓展字段
  `ext_common1` VARCHAR(100) COMMENT '通用扩展字段1',
  `ext_common2` VARCHAR(100) COMMENT '通用扩展字段2',
  `ext_common3` VARCHAR(100) COMMENT '通用扩展字段3',
  `ext_common4` VARCHAR(100) COMMENT '通用扩展字段4',
  
  -- 系统字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
  `tenant_id` BIGINT DEFAULT 1 COMMENT '租户ID',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='户外广告表';
-- 分割线
CREATE TABLE `rectification_order` (
  -- 系统主键
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  
  -- 业务字段
  `rectification_order_id` VARCHAR(36) COMMENT '工单ID',
  `order_no` VARCHAR(50) COMMENT '工单编号',
  `outdoor_ad_id` VARCHAR(36) COMMENT '关联outdoor_ad.outdoor_ad_id，关联广告',
  `deal_by` VARCHAR(36) COMMENT '关联sys_user.id，处置人',
  `reviewer_id` VARCHAR(36) COMMENT '关联sys_user.id，复核人',
  `order_status_id` VARCHAR(36) COMMENT '关联sys_order_status.id，工单状态',
  `review_result_id` VARCHAR(36) COMMENT '关联sys_review_result.id，复核结果',
  `deal_measure` VARCHAR(255) COMMENT '处置措施',
  `deal_photos` JSON COMMENT '整改照片URL',
  `on_site_detection_data` JSON COMMENT '现场检测数据',
  `review_opinion` VARCHAR(255) COMMENT '复核意见',
  `return_reason` VARCHAR(255) COMMENT '退回原因',
  `dispatch_time` DATETIME COMMENT '派单时间',
  `deal_time` DATETIME COMMENT '处置时间',
  `review_time` DATETIME COMMENT '复核时间',
  
  -- 拓展字段
  `ext_common1` VARCHAR(100) COMMENT '通用扩展字段1',
  `ext_common2` VARCHAR(100) COMMENT '通用扩展字段2',
  `ext_common3` VARCHAR(100) COMMENT '通用扩展字段3',
  `ext_common4` VARCHAR(100) COMMENT '通用扩展字段4',
  
  -- 系统字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
  `tenant_id` BIGINT DEFAULT 1 COMMENT '租户ID',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='整改工单表';
-- 分割线
CREATE TABLE `sys_warning_type` (
  -- 系统主键
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  
  -- 业务主键
  `warning_type_id` VARCHAR(36) COMMENT '预警类型ID',
  
  -- 业务字段
  `type_code` VARCHAR(50) COMMENT '类型编码',
  `name` VARCHAR(50) COMMENT '类型名称：尺寸超规/倾斜超标/存在破损/合规预警/安全预警/数据异常/路线偏离/载重超标/未密闭/无证清运/沿途遗撒/乱倾倒/占道经营/乱贴乱画/违停',
  `sort` INT COMMENT '排序号',
  `remark` VARCHAR(255) COMMENT '备注',
  
  -- 拓展字段
  `ext_common1` VARCHAR(100) COMMENT '通用扩展字段1',
  `ext_common2` VARCHAR(100) COMMENT '通用扩展字段2',
  `ext_common3` VARCHAR(100) COMMENT '通用扩展字段3',
  `ext_common4` VARCHAR(100) COMMENT '通用扩展字段4',
  
  -- 系统字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
  `tenant_id` BIGINT DEFAULT 1 COMMENT '租户ID',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='预警类型字典表';
-- 分割线
CREATE TABLE `sys_damage_status` (
  -- 系统主键
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  
  -- 业务字段
  `damage_status_id` VARCHAR(36) COMMENT '破损状态ID，UUID',
  `status_code` VARCHAR(50) COMMENT '状态编码',
  `name` VARCHAR(50) COMMENT '状态名称：无破损/轻微破损/中度破损/严重破损',
  `sort` INT COMMENT '排序号',
  `remark` VARCHAR(255) COMMENT '备注',
  `del_flag` TINYINT COMMENT '删除标记，0=未删除/1=已删除',
  
  -- 拓展字段
  `ext_common1` VARCHAR(100) COMMENT '通用扩展字段1',
  `ext_common2` VARCHAR(100) COMMENT '通用扩展字段2',
  `ext_common3` VARCHAR(100) COMMENT '通用扩展字段3',
  `ext_common4` VARCHAR(100) COMMENT '通用扩展字段4',
  
  -- 系统字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
  `tenant_id` BIGINT DEFAULT 1 COMMENT '租户ID',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='破损状态字典表';
-- 分割线
CREATE TABLE `sys_ad_status` (
  -- 系统主键
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  
  -- 业务字段
  `ad_status_id` VARCHAR(36) COMMENT '广告状态ID，UUID',
  `status_code` VARCHAR(50) COMMENT '状态编码，唯一',
  `name` VARCHAR(50) COMMENT '状态名称：正常/预警/整改中/已整改/过期未拆除/未经审批/超期设置',
  `sort` INT COMMENT '排序号',
  `remark` VARCHAR(255) COMMENT '备注',
  `del_flag` TINYINT COMMENT '删除标记，0=未删除/1=已删除',
  
  -- 拓展字段
  `ext_common1` VARCHAR(100) COMMENT '通用扩展字段1',
  `ext_common2` VARCHAR(100) COMMENT '通用扩展字段2',
  `ext_common3` VARCHAR(100) COMMENT '通用扩展字段3',
  `ext_common4` VARCHAR(100) COMMENT '通用扩展字段4',
  
  -- 系统字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
  `tenant_id` BIGINT DEFAULT 1 COMMENT '租户ID',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='广告状态字典表';
-- 分割线
CREATE TABLE `sys_stat_date` (
  -- 系统主键
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  
  -- 业务字段
  `stat_date` DATE COMMENT '统计日期',
  `remark` VARCHAR(255) COMMENT '备注',
  `del_flag` TINYINT COMMENT '删除标记，0=未删除/1=已删除',
  
  -- 拓展字段
  `ext_common1` VARCHAR(100) COMMENT '通用扩展字段1',
  `ext_common2` VARCHAR(100) COMMENT '通用扩展字段2',
  `ext_common3` VARCHAR(100) COMMENT '通用扩展字段3',
  `ext_common4` VARCHAR(100) COMMENT '通用扩展字段4',
  
  -- 系统字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
  `tenant_id` BIGINT DEFAULT 1 COMMENT '租户ID',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='统计日期表';
-- 分割线
CREATE TABLE `sys_stat_week` (
  -- 系统主键
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  
  -- 业务字段
  `week_no` VARCHAR(50) COMMENT '周次编号，格式如"2026W23"',
  `start_date` DATE COMMENT '周起始日期',
  `end_date` DATE COMMENT '周结束日期',
  `year` INT COMMENT '年份',
  `remark` VARCHAR(255) COMMENT '备注',
  `del_flag` TINYINT COMMENT '删除标记，0=未删除/1=已删除',
  
  -- 拓展字段
  `ext_common1` VARCHAR(100) COMMENT '通用扩展字段1',
  `ext_common2` VARCHAR(100) COMMENT '通用扩展字段2',
  `ext_common3` VARCHAR(100) COMMENT '通用扩展字段3',
  `ext_common4` VARCHAR(100) COMMENT '通用扩展字段4',
  
  -- 系统字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
  `tenant_id` BIGINT DEFAULT 1 COMMENT '租户ID',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='统计周次表';
-- 分割线
CREATE TABLE `sys_stat_month` (
  -- 系统主键
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  
  -- 业务字段
  `month` VARCHAR(6) COMMENT '统计月份，格式如"202606"',
  `year` INT COMMENT '年份',
  `remark` VARCHAR(255) COMMENT '备注',
  `del_flag` TINYINT COMMENT '删除标记，0=未删除/1=已删除',
  
  -- 拓展字段
  `ext_common1` VARCHAR(100) COMMENT '通用扩展字段1',
  `ext_common2` VARCHAR(100) COMMENT '通用扩展字段2',
  `ext_common3` VARCHAR(100) COMMENT '通用扩展字段3',
  `ext_common4` VARCHAR(100) COMMENT '通用扩展字段4',
  
  -- 系统字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
  `tenant_id` BIGINT DEFAULT 1 COMMENT '租户ID',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='统计月份表';
-- 分割线
CREATE TABLE `sys_stat_quarter` (
  -- 系统主键
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  
  -- 业务字段
  `quarter` VARCHAR(20) COMMENT '季度编号，格式如"2026Q2"',
  `start_date` DATE COMMENT '季度起始日期',
  `end_date` DATE COMMENT '季度结束日期',
  `year` INT COMMENT '年份',
  `remark` VARCHAR(255) COMMENT '备注',
  `del_flag` TINYINT COMMENT '删除标记，0=未删除/1=已删除',
  
  -- 拓展字段
  `ext_common1` VARCHAR(100) COMMENT '通用扩展字段1',
  `ext_common2` VARCHAR(100) COMMENT '通用扩展字段2',
  `ext_common3` VARCHAR(100) COMMENT '通用扩展字段3',
  `ext_common4` VARCHAR(100) COMMENT '通用扩展字段4',
  
  -- 系统字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
  `tenant_id` BIGINT DEFAULT 1 COMMENT '租户ID',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='统计季度表';
-- 分割线
CREATE TABLE `sys_stat_halfyear` (
  -- 系统主键
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  
  -- 业务字段
  `halfyear` VARCHAR(20) COMMENT '半年编号，格式如"2026H1"',
  `start_date` DATE COMMENT '半年起始日期',
  `end_date` DATE COMMENT '半年结束日期',
  `year` INT COMMENT '年份',
  `remark` VARCHAR(255) COMMENT '备注',
  `del_flag` TINYINT COMMENT '删除标记，0=未删除/1=已删除',
  
  -- 拓展字段
  `ext_common1` VARCHAR(100) COMMENT '通用扩展字段1',
  `ext_common2` VARCHAR(100) COMMENT '通用扩展字段2',
  `ext_common3` VARCHAR(100) COMMENT '通用扩展字段3',
  `ext_common4` VARCHAR(100) COMMENT '通用扩展字段4',
  
  -- 系统字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
  `tenant_id` BIGINT DEFAULT 1 COMMENT '租户ID',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='统计半年表';
-- 分割线
CREATE TABLE `sys_stat_year` (
  -- 系统主键
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  
  -- 业务字段
  `year` INT COMMENT '统计年份',
  `remark` VARCHAR(255) COMMENT '备注',
  `del_flag` TINYINT COMMENT '删除标记，0=未删除/1=已删除',
  
  -- 拓展字段
  `ext_common1` VARCHAR(100) COMMENT '通用扩展字段1',
  `ext_common2` VARCHAR(100) COMMENT '通用扩展字段2',
  `ext_common3` VARCHAR(100) COMMENT '通用扩展字段3',
  `ext_common4` VARCHAR(100) COMMENT '通用扩展字段4',
  
  -- 系统字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
  `tenant_id` BIGINT DEFAULT 1 COMMENT '租户ID',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='统计年度表';
-- 分割线
CREATE TABLE `sys_area` (
  -- 系统主键
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  
  -- 业务字段
  `area_code` VARCHAR(64) COMMENT '区域编码',
  `area_name` VARCHAR(255) COMMENT '区域名称',
  `parent_code` VARCHAR(64) COMMENT '上级区域编码',
  `level` VARCHAR(50) COMMENT '区域级别：省/市/区/街道/社区',
  `boundary` JSON COMMENT '区域边界坐标',
  `sort` INT COMMENT '排序号',
  `remark` VARCHAR(255) COMMENT '备注',
  `del_flag` TINYINT COMMENT '删除标记，0=未删除/1=已删除',
  
  -- 拓展字段
  `ext_common1` VARCHAR(100) COMMENT '通用扩展字段1',
  `ext_common2` VARCHAR(100) COMMENT '通用扩展字段2',
  `ext_common3` VARCHAR(100) COMMENT '通用扩展字段3',
  `ext_common4` VARCHAR(100) COMMENT '通用扩展字段4',
  
  -- 系统字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
  `tenant_id` BIGINT DEFAULT 1 COMMENT '租户ID',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='区域编码表';
-- 分割线
CREATE TABLE `sys_user` (
    -- 系统主键
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',

    -- 业务字段
    `user_id` VARCHAR(36) COMMENT '用户ID，UUID',
    `user_name` VARCHAR(50) COMMENT '用户名',
    `real_name` VARCHAR(50) COMMENT '真实姓名',
    `dept_id` VARCHAR(36) COMMENT '部门ID，UUID',
    `dept_name` VARCHAR(100) COMMENT '部门名称',
    `role_id` VARCHAR(36) COMMENT '角色ID，UUID',
    `role_name` VARCHAR(50) COMMENT '角色名称：管理员/监管员/执法员/复核员/保洁员/核查员',
    `phone` VARCHAR(20) COMMENT '联系电话',
    `status` VARCHAR(20) COMMENT '用户状态：启用/禁用',
    `user_create_time` DATETIME COMMENT '用户创建时间',
    `user_update_time` DATETIME COMMENT '用户更新时间',
    `del_flag` TINYINT COMMENT '删除标记，0=未删除/1=已删除',

    -- 拓展字段
    `ext_common1` VARCHAR(100) COMMENT '通用扩展字段1',
    `ext_common2` VARCHAR(100) COMMENT '通用扩展字段2',
    `ext_common3` VARCHAR(100) COMMENT '通用扩展字段3',
    `ext_common4` VARCHAR(100) COMMENT '通用扩展字段4',

    -- 系统字段
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统用户表';
-- 分割线
CREATE TABLE `sys_order_status` (
    -- 系统主键
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',

    -- 业务字段
    `order_status_id` VARCHAR(36) COMMENT '工单状态ID，UUID',
    `status_code` VARCHAR(50) COMMENT '状态编码',
    `name` VARCHAR(50) COMMENT '状态名称：待处置/待派单/处置中/清理中/执法中/待复核/待核查/已闭环/已归档/已退回/无效',
    `sort` INT COMMENT '排序号',
    `remark` VARCHAR(255) COMMENT '备注',
    `del_flag` TINYINT COMMENT '删除标记，0=未删除/1=已删除',

    -- 拓展字段
    `ext_common1` VARCHAR(100) COMMENT '通用扩展字段1',
    `ext_common2` VARCHAR(100) COMMENT '通用扩展字段2',
    `ext_common3` VARCHAR(100) COMMENT '通用扩展字段3',
    `ext_common4` VARCHAR(100) COMMENT '通用扩展字段4',

    -- 系统字段
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='工单状态字典表';
-- 分割线
CREATE TABLE `sys_review_result` (
    -- 系统主键
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',

    -- 业务字段
    `review_result_id` VARCHAR(36) COMMENT '复核结果ID，UUID',
    `result_code` VARCHAR(50) COMMENT '结果编码',
    `name` VARCHAR(50) COMMENT '结果名称：合格/不合格/待复核/数据异常',
    `sort` INT COMMENT '排序号',
    `remark` VARCHAR(255) COMMENT '备注',
    `del_flag` TINYINT COMMENT '删除标记，0=未删除/1=已删除',

    -- 拓展字段
    `ext_common1` VARCHAR(100) COMMENT '通用扩展字段1',
    `ext_common2` VARCHAR(100) COMMENT '通用扩展字段2',
    `ext_common3` VARCHAR(100) COMMENT '通用扩展字段3',
    `ext_common4` VARCHAR(100) COMMENT '通用扩展字段4',

    -- 系统字段
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='复核结果字典表';
