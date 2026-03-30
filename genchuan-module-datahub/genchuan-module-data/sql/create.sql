CREATE TABLE `geo_code` (
  -- 主键
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  
  -- 业务字段
  `code` VARCHAR(50) COMMENT '地理编码',
  `location_name` VARCHAR(200) COMMENT '地点名称',
  `area_code` VARCHAR(20) COMMENT '关联行政区划代码',
  `layer_type_id` VARCHAR(36) COMMENT '关联图层类型ID',
  `beidou_grid_code` VARCHAR(20) COMMENT '北斗网格码（6-8级）',
  `longitude` DECIMAL(9,6) COMMENT '经度（2000国家大地坐标系，精度6位小数）',
  `latitude` DECIMAL(8,6) COMMENT '纬度（2000国家大地坐标系，精度6位小数）',
  `admin_code` VARCHAR(20) COMMENT '行政区划代码',
  `unique_code` VARCHAR(15) COMMENT '15位标识码（6位行政码+3位街道码+1位图层码+5位顺序码）',
  `status_id` VARCHAR(36) COMMENT '关联状态ID',
  `check_result_id` VARCHAR(36) COMMENT '关联检查结果ID',
  `rule_enable_flag` BIT(1) COMMENT '编码规则启用状态（布尔值）',
  `rule_audit_status_id` VARCHAR(36) COMMENT '编码规则审核状态ID',
  `parent_geo_code_id` VARCHAR(36) COMMENT '父级地理编码ID',
  `change_log` JSON COMMENT '变更日志（JSON格式）',
  `coord_verify_flag` BIT(1) COMMENT '坐标校验标识（布尔值）',
  `remark` VARCHAR(500) COMMENT '备注',
  
  -- 扩展字段
  `ext_common1` VARCHAR(100) COMMENT '通用扩展字段1',
  `ext_common2` VARCHAR(100) COMMENT '通用扩展字段2',
  `ext_common3` VARCHAR(100) COMMENT '通用扩展字段3',
  `ext_common4` VARCHAR(100) COMMENT '通用扩展字段4',
  `ext_common5` VARCHAR(100) COMMENT '通用扩展字段5',
  `ext_common6` VARCHAR(100) COMMENT '通用扩展字段6',
  
  -- 系统字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
  `tenant_id` BIGINT DEFAULT 1 COMMENT '租户ID',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='地理编码表';



CREATE TABLE `part_category` (
  -- 主键
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  
  -- 业务字段
  `name` VARCHAR(100) COMMENT '分类名称',
  `code` VARCHAR(20) COMMENT '分类代码',
  `code_sort_type` VARCHAR(20) COMMENT '编码排序类型：国标正排/扩展倒排',
  `parent_id` VARCHAR(36) COMMENT '上级分类ID',
  `parent_name` VARCHAR(100) COMMENT '上级分类名称',
  `icon_id` VARCHAR(36) COMMENT '关联图标ID',
  `icon_audit_status_id` VARCHAR(36) COMMENT '图示审核状态ID',
  `category_type_id` VARCHAR(36) COMMENT '分类类型ID',
  `status_id` VARCHAR(36) COMMENT '关联状态ID',
  `audit_status_id` VARCHAR(36) COMMENT '关联审核状态ID',
  `instance_count` INT COMMENT '关联实例数',
  `purpose` VARCHAR(500) COMMENT '用途说明',
  `notify_flag` BIT(1) COMMENT '分类变更通知标识',
  `remark` VARCHAR(500) COMMENT '备注',
  
  -- 扩展字段
  `ext_common1` VARCHAR(100) COMMENT '通用扩展字段1',
  `ext_common2` VARCHAR(100) COMMENT '通用扩展字段2',
  
  -- 系统字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
  `tenant_id` BIGINT DEFAULT 1 COMMENT '租户ID',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='管理部件分类表';



CREATE TABLE `part_instance`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `part_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '部件名称',
  `unique_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '16位标识码',
  `parent_category_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联分类ID',
  `grid_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '网格ID',
  `grid_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所在网格',
  `longitude` decimal(9, 6) NULL DEFAULT NULL COMMENT '经度',
  `latitude` decimal(8, 6) NULL DEFAULT NULL COMMENT '纬度',
  `coord_verify_flag` bit(1) NULL DEFAULT NULL COMMENT '坐标校验标识',
  `coordinate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '坐标信息',
  `run_status` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '运行状态',
  `dept_name` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '主管部门',
  `area_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联行政区划代码',
  `areaName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '行政区划归属',
  `monitor_ids` json NULL COMMENT '关联监测部件ID列表',
  `monitor_count` int(0) NULL DEFAULT NULL COMMENT '关联监测部件数',
  `event_count` int(0) NULL DEFAULT NULL COMMENT '关联事件数',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `ext_common1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段1',
  `ext_common2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通用扩展字段2',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '删除标识',
  `tenant_id` bigint(0) NULL DEFAULT 1 COMMENT '租户ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理部件实例表' ROW_FORMAT = Dynamic;


CREATE TABLE `matter_category` (
  -- 主键
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  
  -- 业务字段
	`matter_category_id` VARCHAR(36) NOT NULL COMMENT '管理事项分类ID',
  `category_name` VARCHAR(100) COMMENT '分类名称',
  `category_code` VARCHAR(20) COMMENT '分类代码',
  `parent_id` VARCHAR(36) COMMENT '上级分类ID',
  `parent_name` VARCHAR(100) COMMENT '上级分类名称',
  `dept_id` VARCHAR(36) COMMENT '主管部门ID',
  `dept_name` VARCHAR(100) COMMENT '主管部门名称',
  `deal_limit` INT COMMENT '处置时限（小时）',
  `workflow_id` VARCHAR(36) COMMENT '工作流ID',
  `workflow_code` VARCHAR(50) COMMENT '工作流编码',
  `workflow_desc` VARCHAR(500) COMMENT '工作流描述',
  `category_type_id` VARCHAR(36) COMMENT '分类类型ID',
  `category_type_name` VARCHAR(50) COMMENT '分类类型名称',
  `status_name` VARCHAR(50) COMMENT '状态',
  `audit_status_name` VARCHAR(50) COMMENT '审核状态',
  `related_matter_count` INT COMMENT '关联事项数',
  `purpose` VARCHAR(500) COMMENT '用途说明',
  `remark` VARCHAR(500) COMMENT '备注',
  
  -- 扩展字段
  `ext_common1` VARCHAR(100) COMMENT '通用扩展字段1',
  `ext_common2` VARCHAR(100) COMMENT '通用扩展字段2',
  
  -- 系统字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
  `tenant_id` BIGINT DEFAULT 1 COMMENT '租户ID',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='管理事项分类表';


CREATE TABLE `matter_instance` (
  -- 主键
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  
  -- 业务字段
	`matter_instance_id` VARCHAR(36) COMMENT '管理事项实例ID',
  `name` VARCHAR(200) COMMENT '事项名称',
  `unique_code` VARCHAR(16) COMMENT '16位标识码',
  `category_id` VARCHAR(36) COMMENT '所属分类ID',
  `category_name` VARCHAR(100) COMMENT '所属分类名称',
  `parent_category_id` VARCHAR(36) COMMENT '上级分类ID',
  `location` VARCHAR(200) COMMENT '事发位置',
  `grid_id` VARCHAR(36) COMMENT '所在网格ID',
  `grid_name` VARCHAR(100) COMMENT '所在网格名称',
  `description` TEXT COMMENT '描述信息',
  `status_id` VARCHAR(36) COMMENT '状态ID',
  `status_name` VARCHAR(50) COMMENT '状态名称',
  `dept_id` VARCHAR(36) COMMENT '主管部门ID',
  `dept_name` VARCHAR(100) COMMENT '主管部门名称',
  `attachment_info` JSON COMMENT '附件信息列表',
  `part_ids` JSON COMMENT '关联管理部件ID列表',
  `part_count` INT COMMENT '关联部件数',
  `timeout_flag` BIT(1) COMMENT '超时标识',
  `timeout_duration` INT COMMENT '超时时长（分钟）',
  `deal_opinion` VARCHAR(500) COMMENT '处置意见',
  `create_by` VARCHAR(36) COMMENT '创建人ID',
  `creator_name` VARCHAR(50) COMMENT '创建人名称',
  `matter_create_time` DATETIME COMMENT '业务创建时间',
  `deal_by` VARCHAR(36) COMMENT '处置人ID',
  `handler_name` VARCHAR(50) COMMENT '处置人名称',
  `deal_time` DATETIME COMMENT '处置时间',
  `remark` TEXT COMMENT '备注',
  
  -- 扩展字段
  `ext_common1` VARCHAR(100) COMMENT '通用扩展字段1',
  `ext_common2` VARCHAR(100) COMMENT '通用扩展字段2',
  
  -- 系统字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
  `tenant_id` BIGINT DEFAULT 1 COMMENT '租户ID',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='管理事项实例表';










