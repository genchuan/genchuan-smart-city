CREATE TABLE `gc_event_type_management` (
    -- 主键
                                            `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',

    -- 业务字段
                                            `event_type_id` VARCHAR(32) COMMENT '事件类型ID，唯一编码，采用UUID',
                                            `event_big_code` VARCHAR(2) COMMENT '事件大类代码，01-99',
                                            `event_big_name` VARCHAR(50) COMMENT '事件大类名称，公共设施类/交通管理类/环境管理类等',
                                            `event_mid_code` VARCHAR(4) COMMENT '事件中类代码，0101-9999',
                                            `event_CREATE TABLE ` gc_event_type_management ` (
    -- 主键
                                            ` id ` BIGINT NOT NULL AUTO_INCREMENT COMMENT ''主键ID'',

    -- 业务字段
                                            ` event_type_id ` VARCHAR(32) COMMENT ''事件类型ID，唯一编码，采用UUID'',
                                            ` event_big_code ` VARCHAR(2) COMMENT ''事件大类代码，01-99'',
                                            ` event_big_name ` VARCHAR(50) COMMENT ''事件大类名称，公共设施类/交通管理类/环境管理类等'',
                                            ` event_mid_code ` VARCHAR(4) COMMENT ''事件中类代码，0101-9999'',
                                            ` event_mid_name ` VARCHAR(50) COMMENT ''事件中类名称，燃气设施/供水设施/道路设施等'',
                                            ` event_small_code ` VARCHAR(6) COMMENT ''事件小类代码，010101-999999'',
                                            ` event_small_name ` VARCHAR(50) COMMENT ''事件小类名称，燃气泄漏/供水管道破裂/道路坑洼等'',
                                            ` event_type_desc ` VARCHAR(200) COMMENT ''事件类型描述'',
                                            ` enable_status ` VARCHAR(10) COMMENT ''启用状态，启用/禁用'',
                                            ` create_user ` VARCHAR(32) COMMENT ''创建人账号'',
                                            ` create_time ` DATETIME COMMENT ''创建时间，格式：yyyy-MM-dd HH:mm:ss'',
                                            ` update_user ` VARCHAR(32) COMMENT ''更新人账号'',
                                            ` update_time ` DATETIME COMMENT ''更新时间，格式：yyyy-MM-dd HH:mm:ss'',
                                            ` extend_category1 ` VARCHAR(30) COMMENT ''扩展分类字段1，预留用于自定义标签'',
                                            ` extend_category2 ` VARCHAR(30) COMMENT ''扩展分类字段2，预留用于自定义标签'',
                                            ` extend_category3 ` VARCHAR(30) COMMENT ''扩展分类字段3，预留用于自定义标签'',

    -- 系统字段
                                            ` creator ` VARCHAR(64) DEFAULT '''' COMMENT ''创建者'',
                                            ` updater ` VARCHAR(64) DEFAULT '''' COMMENT ''更新者'',
                                            ` deleted ` BIT(1) DEFAULT 0 COMMENT ''删除标识'',
                                            ` tenant_id ` BIGINT DEFAULT 0 NOT NULL COMMENT ''租户ID'',
                                            ` create_time_sys ` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT ''系统创建时间'',
                                            ` update_time_sys ` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''系统更新时间'',

                                            PRIMARY KEY (` id `)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT=''事件类型管理表'';CREATE TABLE `gc_event_type_management` (
    -- 主键
                                            `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT ''主键ID'',

    -- 业务字段
                                            `event_type_id` VARCHAR(32) COMMENT ''事件类型ID，唯一编码，采用UUID'',
                                            `event_big_code` VARCHAR(2) COMMENT ''事件大类代码，01-99'',
                                            `event_big_name` VARCHAR(50) COMMENT ''事件大类名称，公共设施类/交通管理类/环境管理类等'',
                                            `event_mid_code` VARCHAR(4) COMMENT ''事件中类代码，0101-9999'',
                                            `event_mid_name` VARCHAR(50) COMMENT ''事件中类名称，燃气设施/供水设施/道路设施等'',
                                            `event_small_code` VARCHAR(6) COMMENT ''事件小类代码，010101-999999'',
                                            `event_small_name` VARCHAR(50) COMMENT ''事件小类名称，燃气泄漏/供水管道破裂/道路坑洼等'',
                                            `event_type_desc` VARCHAR(200) COMMENT ''事件类型描述'',
                                            `enable_status` VARCHAR(10) COMMENT ''启用状态，启用/禁用'',
                                            `create_user` VARCHAR(32) COMMENT ''创建人账号'',
                                            `create_time` DATETIME COMMENT ''创建时间，格式：yyyy-MM-dd HH:mm:ss'',
                                            `update_user` VARCHAR(32) COMMENT ''更新人账号'',
                                            `update_time` DATETIME COMMENT ''更新时间，格式：yyyy-MM-dd HH:mm:ss'',
                                            `extend_category1` VARCHAR(30) COMMENT ''扩展分类字段1，预留用于自定义标签'',
                                            `extend_category2` VARCHAR(30) COMMENT ''扩展分类字段2，预留用于自定义标签'',
                                            `extend_category3` VARCHAR(30) COMMENT ''扩展分类字段3，预留用于自定义标签'',

    -- 系统字段
                                            `creator` VARCHAR(64) DEFAULT '''' COMMENT ''创建者'',
                                            `updater` VARCHAR(64) DEFAULT '''' COMMENT ''更新者'',
                                            `deleted` BIT(1) DEFAULT 0 COMMENT ''删除标识'',
                                            `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT ''租户ID'',
                                            `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT ''系统创建时间'',
                                            `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''系统更新时间'',

                                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT=''事件类型管理表'';mid_name` VARCHAR(50) COMMENT '事件中类名称，燃气设施/供水设施/道路设施等',
                                            `event_small_code` VARCHAR(6) COMMENT '事件小类代码，010101-999999',
                                            `event_small_name` VARCHAR(50) COMMENT '事件小类名称，燃气泄漏/供水管道破裂/道路坑洼等',
                                            `event_type_desc` VARCHAR(200) COMMENT '事件类型描述',
                                            `enable_status` VARCHAR(10) COMMENT '启用状态，启用/禁用',
                                            `create_user` VARCHAR(32) COMMENT '创建人账号',
                                            `create_time` DATETIME COMMENT '创建时间，格式：yyyy-MM-dd HH:mm:ss',
                                            `update_user` VARCHAR(32) COMMENT '更新人账号',
                                            `update_time` DATETIME COMMENT '更新时间，格式：yyyy-MM-dd HH:mm:ss',
                                            `extend_category1` VARCHAR(30) COMMENT '扩展分类字段1，预留用于自定义标签',
                                            `extend_category2` VARCHAR(30) COMMENT '扩展分类字段2，预留用于自定义标签',
                                            `extend_category3` VARCHAR(30) COMMENT '扩展分类字段3，预留用于自定义标签',

    -- 系统字段
                                            `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                            `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                            `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                            `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                            `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                            `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',

                                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='事件类型管理表';