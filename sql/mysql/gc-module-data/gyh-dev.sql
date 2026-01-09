# 事件类型管理表
CREATE TABLE ` gc_event_type_management ` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='事件类型管理表';

# 国家政策信息表
CREATE TABLE `gc_national_policy_info` (
    -- 主键
                                           `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',

    -- 业务字段
                                           `policy_id` VARCHAR(32) COMMENT '政策ID，唯一编码，采用UUID',
                                           `policy_no` VARCHAR(50) COMMENT '政策编号，如"国办发〔2025〕XX号"',
                                           `policy_name` VARCHAR(200) COMMENT '政策完整名称',
                                           `policy_type` VARCHAR(30) COMMENT '政策类型，规划类/管理类/保障类/技术类',
                                           `issue_dept` VARCHAR(100) COMMENT '发布单位，如"国务院办公厅""国家发展改革委"',
                                           `issue_time` DATETIME COMMENT '发布时间，格式：yyyy-MM-dd HH:mm:ss',
                                           `effective_time` DATE COMMENT '生效时间，格式：yyyy-MM-dd',
                                           `expire_time` DATE COMMENT '失效时间，格式：yyyy-MM-dd，永久有效则为空',
                                           `policy_file_path` VARCHAR(255) COMMENT '政策原文在服务器的存储路径',
                                           `policy_abstract` VARCHAR(1000) COMMENT '政策摘要，简要描述政策核心内容',
                                           `policy_status` VARCHAR(10) COMMENT '政策状态，未生效/有效/已失效',
                                           `upload_user_id` VARCHAR(32) COMMENT '上传政策的管理员账号',
                                           `upload_time` DATETIME COMMENT '上传时间，格式：yyyy-MM-dd HH:mm:ss',
                                           `update_user_id` VARCHAR(32) COMMENT '修改政策信息的管理员账号',
                                           `update_time` DATETIME COMMENT '更新时间，格式：yyyy-MM-dd HH:mm:ss',
                                           `extend_category` VARCHAR(30) COMMENT '扩展分类字段，预留用于自定义标签',

    -- 系统字段
                                           `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                           `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                           `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                           `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                           `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                           `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',

                                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='国家政策信息表';

#地方法规信息表
CREATE TABLE `gc_local_regulation_info` (
    -- 主键
                                            `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',

    -- 业务字段
                                            `regulation_id` VARCHAR(32) COMMENT '法规ID，唯一编码，采用UUID',
                                            `regulation_no` VARCHAR(50) COMMENT '法规编号，如"XX省人民政府令〔2025〕XX号"',
                                            `regulation_name` VARCHAR(200) COMMENT '法规完整名称',
                                            `regulation_type` VARCHAR(30) COMMENT '法规类型，政府规章/地方性法规/规范性文件',
                                            `admin_code` VARCHAR(6) COMMENT '所属行政区划代码，符合GB/T 2260',
                                            `admin_name` VARCHAR(50) COMMENT '所属行政区划名称',
                                            `issue_dept` VARCHAR(100) COMMENT '发布单位，如"XX省人民政府""XX市人大常委会"',
                                            `issue_time` DATETIME COMMENT '发布时间，格式：yyyy-MM-dd HH:mm:ss',
                                            `effective_time` DATE COMMENT '生效时间，格式：yyyy-MM-dd',
                                            `expire_time` DATE COMMENT '失效时间，格式：yyyy-MM-dd，永久有效则为空',
                                            `revision_basis` VARCHAR(500) COMMENT '修订依据，如"根据《XX国家政策》修订"',
                                            `related_national_policy_id` VARCHAR(32) COMMENT '关联依据的国家政策ID',
                                            `regulation_file_path` VARCHAR(255) COMMENT 'PDF格式原文存储路径',
                                            `regulation_abstract` VARCHAR(1000) COMMENT '法规核心内容摘要',
                                            `regulation_status` VARCHAR(10) COMMENT '法规状态，未生效/有效/已修订/已废止',
                                            `upload_user_id` VARCHAR(32) COMMENT '上传管理员账号',
                                            `upload_time` DATETIME COMMENT '上传时间，格式：yyyy-MM-dd HH:mm:ss',
                                            `update_user_id` VARCHAR(32) COMMENT '修改管理员账号',
                                            `update_time` DATETIME COMMENT '更新时间，格式：yyyy-MM-dd HH:mm:ss',
                                            `extend_category` VARCHAR(30) COMMENT '扩展分类字段，预留用于自定义标签',

    -- 系统字段
                                            `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                            `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                            `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                            `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                            `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                            `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',

                                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='地方法规信息表';

# 行业规范信息表
CREATE TABLE `gc_industry_standard_info` (
    -- 主键
                                             `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',

    -- 业务字段
                                             `standard_id` VARCHAR(32) COMMENT '规范ID，唯一编码，采用UUID',
                                             `standard_no` VARCHAR(50) COMMENT '规范编号，如"GB/T XXXXX-2025""CJ/T XXXXX-2025"',
                                             `standard_name` VARCHAR(200) COMMENT '规范名称，如"智慧城市数据安全规范"',
                                             `industry_field` VARCHAR(30) COMMENT '行业领域，数据安全/市政设施/交通管理/环境保护',
                                             `issue_organization` VARCHAR(100) COMMENT '发布机构，如"国家市场监督管理总局""中国城市规划协会"',
                                             `issue_time` DATETIME COMMENT '发布时间，格式：yyyy-MM-dd HH:mm:ss',
                                             `implementation_time` DATE COMMENT '实施时间，格式：yyyy-MM-dd',
                                             `replace_old_no` VARCHAR(50) COMMENT '替代旧规范号，如"替代GB/T XXXXX-2020"',
                                             `standard_file_path` VARCHAR(255) COMMENT 'PDF格式原文路径',
                                             `interpretation_file_path` VARCHAR(255) COMMENT '配套解读文件路径，多个用英文分号分隔',
                                             `standard_abstract` VARCHAR(1000) COMMENT '核心内容摘要',
                                             `standard_status` VARCHAR(10) COMMENT '规范状态，未实施/实施中/已替代/已废止',
                                             `related_business_module` VARCHAR(100) COMMENT '关联业务模块，如"管理部件事项管理/监测部件事件管理"',
                                             `upload_user_id` VARCHAR(32) COMMENT '上传管理员账号',
                                             `upload_time` DATETIME COMMENT '上传时间，格式：yyyy-MM-dd HH:mm:ss',
                                             `update_user_id` VARCHAR(32) COMMENT '修改管理员账号',
                                             `update_time` DATETIME COMMENT '更新时间，格式：yyyy-MM-dd HH:mm:ss',
                                             `extend_category` VARCHAR(30) COMMENT '扩展分类字段，预留用于自定义标签',

    -- 系统字段
                                             `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                             `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                             `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                             `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                             `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                             `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',

                                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='行业规范信息表';

# 政策解读信息表
CREATE TABLE `gc_policy_interpretation_info` (
    -- 主键
                                                 `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',

    -- 业务字段
                                                 `interpretation_id` VARCHAR(32) COMMENT '解读ID，唯一编码，采用UUID',
                                                 `interpretation_title` VARCHAR(200) COMMENT '解读标题',
                                                 `related_policy_type` VARCHAR(10) COMMENT '关联政策类型，国家政策/地方法规',
                                                 `related_policy_id` VARCHAR(32) COMMENT '关联国家政策ID或地方法规ID',
                                                 `related_policy_name` VARCHAR(200) COMMENT '关联政策名称',
                                                 `interpretation_content` TEXT COMMENT '富文本内容，含背景、条款解读、实施要求',
                                                 `interpretation_type` VARCHAR(20) COMMENT '解读类型，官方解读/专家解读/实务解读',
                                                 `issue_user_id` VARCHAR(32) COMMENT '发布解读的管理员账号',
                                                 `issue_user_name` VARCHAR(40) COMMENT '发布人姓名',
                                                 `issue_time` DATETIME COMMENT '发布时间，格式：yyyy-MM-dd HH:mm:ss',
                                                 `interpretation_status` VARCHAR(10) COMMENT '解读状态，已发布/已下架',
                                                 `off_shelf_reason` VARCHAR(500) COMMENT '下架原因',
                                                 `update_user_id` VARCHAR(32) COMMENT '修改解读的管理员账号',
                                                 `update_time` DATETIME COMMENT '更新时间，格式：yyyy-MM-dd HH:mm:ss',
                                                 `extend_category` VARCHAR(30) COMMENT '扩展分类字段',

    -- 系统字段
                                                 `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                                 `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                                 `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                                 `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                                 `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                                 `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',

                                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='政策解读信息表';

CREATE TABLE `gc_component_category` (
    -- 主键
                                         `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',

    -- 业务字段
                                         `category_id` CHAR(32) COMMENT '分类ID，UUID',
                                         `parent_id` CHAR(32) COMMENT '父类ID，0表示大类',
                                         `level` CHAR(1) COMMENT '层级：1-大类/2-中类/3-小类',
                                         `code` CHAR(2) COMMENT '分类代码：01-99',
                                         `name` VARCHAR(50) COMMENT '分类名称',
                                         `description` VARCHAR(200) COMMENT '分类说明',
                                         `status` CHAR(1) COMMENT '状态：1-启用/0-禁用',
                                         `create_user` CHAR(32) COMMENT '创建人ID',
                                         `create_time` DATETIME COMMENT '创建时间',
                                         `update_user` CHAR(32) COMMENT '更新人ID',
                                         `update_time` DATETIME COMMENT '更新时间',
                                         `ext1` VARCHAR(50) COMMENT '扩展字段1',
                                         `ext2` VARCHAR(50) COMMENT '扩展字段2',
                                         `ext3` VARCHAR(50) COMMENT '扩展字段3',

    -- 系统字段
                                         `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                         `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                         `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                         `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                         `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                         `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',

                                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='部件分类表';

-- 管理部件表
CREATE TABLE `gc_biz_mng_comp` (
    -- 主键
                                   `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    -- 业务字段
                                   `mng_comp_id` CHAR(32) NOT NULL COMMENT '部件ID，唯一编码，UUID生成',
                                   `comp_code` CHAR(16) NOT NULL COMMENT '部件标识码，格式6位行政码+2位大类码+3位小类码+5位顺序码，唯一',
                                   `comp_name` VARCHAR(50) NOT NULL COMMENT '部件名称，关联小类名称+位置，如“XX路电力井盖”，关联管理部件小类表(gc_biz_mng_comp_minor)',
                                   `minor_id` CHAR(32) NOT NULL COMMENT '关联管理部件小类ID，关联管理部件小类表(gc_biz_mng_comp_minor)',
                                   `minor_name` VARCHAR(50) NOT NULL COMMENT '关联管理部件小类名称，与小类ID同步，不可改，关联管理部件小类表(gc_biz_mng_comp_minor)',
                                   `dept_code` CHAR(18) NOT NULL COMMENT '主管部门代码，主管部门信用代码，关联部门信息表(sys_org)',
                                   `dept_name` VARCHAR(60) NOT NULL COMMENT '主管部门名称，与部门代码同步，关联部门信息表(sys_org)',
                                   `grid_id` CHAR(32) NOT NULL COMMENT '关联单元网格ID，关联网格信息表(biz_grid_info)',
                                   `grid_name` VARCHAR(50) NOT NULL COMMENT '关联单元网格名称，与网格ID同步，关联网格信息表(biz_grid_info)',
                                   `comp_status` CHAR(10) NOT NULL COMMENT '部件状态，如完好/破损/丢失/废弃，关联部件状态字典表(sys_dict_mng_comp_state)',
                                   `init_date` DATE NOT NULL COMMENT '部件普查日期，格式YYYYMMDD',
                                   `change_date` DATE COMMENT '状态/权属变更时更新日期，格式YYYYMMDD',
                                   `data_source` VARCHAR(30) COMMENT '数据来源，如实测/人工上报/普查，关联数据来源字典表(sys_dict_data_source)',
                                   `create_user` CHAR(32) NOT NULL COMMENT '录入人账号，关联用户信息表(sys_user)',
                                   `create_time` DATETIME NOT NULL COMMENT '系统生成，格式yyyy - MM - dd HH:mm:ss',
                                   `update_user` CHAR(32) COMMENT '修改人账号，关联用户信息表(sys_user)',
                                   `update_time` DATETIME COMMENT '系统生成，格式yyyy - MM - dd HH:mm:ss',
                                   `ext_cat1` VARCHAR(50) COMMENT '分类扩展字段1，预留，如“部件材质”',
                                   `ext_cat2` VARCHAR(50) COMMENT '分类扩展字段2，预留，如“部件材质”',
                                   `ext_common1` VARCHAR(100) COMMENT '通用扩展字段1，预留，如“安装时间”',
                                   `ext_common2` VARCHAR(100) COMMENT '通用扩展字段2，预留，如“安装时间”',
    -- 系统字段
                                   `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                   `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                   `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                   `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                   `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                   `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
                                   PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理部件表';

-- 管理部件空间数据表
CREATE TABLE `gc_biz_mng_comp_spatial` (
    -- 主键
                                           `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    -- 业务字段
                                           `mng_comp_spatial_id` CHAR(32) NOT NULL COMMENT '空间数据ID，唯一编码，UUID生成',
                                           `mng_comp_id` CHAR(32) NOT NULL COMMENT '关联管理部件ID，一对一，关联管理部件表(gc_biz_mng_comp)',
                                           `comp_name` VARCHAR(50) NOT NULL COMMENT '关联部件名称，与部件ID同步，不可改，关联管理部件表(gc_biz_mng_comp)',
                                           `coord_system` VARCHAR(50) NOT NULL DEFAULT '2000国家大地坐标系' COMMENT '坐标系类型，固定，符合国标，2000国家大地坐标系',
                                           `coord_x` DECIMAL(15, 2) NOT NULL COMMENT '坐标X，经度，保留2位小数，范围 - 180.00至180.00',
                                           `coord_y` DECIMAL(15, 2) NOT NULL COMMENT '坐标Y，纬度，保留2位小数，范围 - 90.00至90.00',
                                           `elevation` DECIMAL(10, 3) COMMENT '高程，可选，米，1985国家高程基准，保留3位小数',
                                           `accuracy` DECIMAL(6, 2) NOT NULL COMMENT '定位精度，米，±0.5/±1.0/±10.0，定位精度等级对应的误差',
                                           `accuracy_level` CHAR(1) NOT NULL COMMENT '定位精度等级，A/B/C，A类±0.5m、B类±1.0m、C类±10.0m',
                                           `survey_unit` VARCHAR(100) COMMENT '测绘单位，测绘实施单位',
    -- 系统字段
                                           `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                           `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                           `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                           `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                           `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                           `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
                                           PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理部件空间数据表';

-- 管理部件图示关联表
CREATE TABLE `gc_rel_mng_comp_symbol` (
    -- 主键
                                          `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    -- 业务字段
                                          `mng_comp_symbol_id` CHAR(32) NOT NULL COMMENT '关联ID，唯一编码，UUID生成',
                                          `minor_id` CHAR(32) NOT NULL COMMENT '所属小类ID，关联管理部件小类ID，一个小类关联一个图示，关联管理部件小类表(gc_biz_mng_comp_minor)',
                                          `minor_name` VARCHAR(50) NOT NULL COMMENT '所属小类名称，与小类ID同步，不可改，关联管理部件小类表(gc_biz_mng_comp_minor)',
                                          `symbol_id` CHAR(32) NOT NULL COMMENT '图示ID，关联图示符号库ID，关联图示符号库表(gc_biz_mng_comp_symbol_lib)',
                                          `symbol_name` VARCHAR(50) NOT NULL COMMENT '图示名称，如“上水井盖图示”，关联图示符号库表(gc_biz_mng_comp_symbol_lib)',
                                          `symbol_path` VARCHAR(255) NOT NULL COMMENT '图示路径，如“/static/symbols/water_cover.png”，支持PNG/SVG，关联图示符号库表(gc_biz_mng_comp_symbol_lib)',
    -- 系统字段
                                          `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                          `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                          `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                          `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                          `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                          `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
                                          PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理部件图示关联表';
-- 管理部件图示符号库表
CREATE TABLE `gc_biz_mng_comp_symbol_lib` (
    -- 主键
                                              `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    -- 业务字段
                                              `symbol_lib_id` CHAR(32) NOT NULL COMMENT '符号库ID，唯一编码，UUID生成',
                                              `symbol_name` VARCHAR(50) COMMENT '符号名称',
                                              `symbol_path` VARCHAR(255) COMMENT '符号路径',
    -- 系统字段
                                              `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                              `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                              `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                              `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                              `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                              `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
                                              PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理部件图示符号库表';

CREATE TABLE `gc_biz_mng_comp_ext` (
    -- 主键
                                       `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    -- 业务字段
                                       `mng_comp_ext_id` CHAR(32) NOT NULL COMMENT '扩展ID，唯一编码，UUID生成',
                                       `major_id` CHAR(32) NOT NULL COMMENT '归属大类ID，无则归“其他”，关联管理部件大类ID，关联管理部件大类表(gc_biz_mng_comp_major)',
                                       `major_name` VARCHAR(50) NOT NULL COMMENT '归属大类名称，与大类ID同步，关联管理部件大类表(gc_biz_mng_comp_major)',
                                       `ext_minor_code` CHAR(3) NOT NULL COMMENT '扩展小类代码，080 - 999，倒排编码，避免冲突',
                                       `ext_minor_name` VARCHAR(50) NOT NULL COMMENT '扩展小类名称，名称加“（自定义）”',
                                       `ext_minor_desc` VARCHAR(255) NOT NULL COMMENT '扩展小类说明，描述用途，如“智能充电桩: 电动汽车充电设备”',
                                       `suggest_dept_code` CHAR(18) COMMENT '建议主管部门代码，建议部门代码，关联部门信息表(sys_org)',
                                       `suggest_dept_name` VARCHAR(60) COMMENT '建议主管部门名称，与部门代码同步，关联部门信息表(sys_org)',
    -- 系统字段
                                       `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                       `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                       `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                       `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                       `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                       `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
                                       PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理部件扩展管理部件配置表';

-- 管理事项大类表
CREATE TABLE `gc_biz_mng_matter_major` (
    -- 主键
                                           `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    -- 业务字段
                                           `mng_matter_major_id` CHAR(32) NOT NULL COMMENT '管理事项大类ID，唯一编码，UUID生成',
                                           `matter_major_code` CHAR(2) NOT NULL COMMENT '大类代码，2位字符，01 - 99，顺序编排，同一系统唯一，符合GB/T 30428.2',
                                           `matter_major_name` VARCHAR(50) NOT NULL COMMENT '大类名称，如市容环境/宣传广告等，国标名称，扩展大类加“（自定义）”',
                                           `matter_major_desc` VARCHAR(255) COMMENT '大类说明，描述大类覆盖事项范围，如“宣传广告：含违规广告、招牌破损”',
                                           `sort_num` INT COMMENT '排序序号，1 - 999，列表展示优先级，数值越小越靠前',
                                           `enable_status` CHAR(1) NOT NULL DEFAULT '1' COMMENT '启用状态，1（启用）/0（禁用），默认1，禁用后关联小类同步禁用',
                                           `create_user` CHAR(32) NOT NULL COMMENT '创建人，配置人账号，关联用户信息表(sys_user)',
                                           `create_time` DATETIME NOT NULL COMMENT '创建时间，系统生成，格式yyyy - MM - dd HH:mm:ss',
                                           `update_user` CHAR(32) COMMENT '更新人，修改人账号，关联用户信息表(sys_user)',
                                           `update_time` DATETIME COMMENT '更新时间，系统生成，格式yyyy - MM - dd HH:mm:ss',
    -- 系统字段
                                           `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                           `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                           `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                           `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                           `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                           `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
                                           PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理事项大类表';

-- 管理事项小类表
CREATE TABLE `gc_biz_mng_matter_minor` (
    -- 主键
                                           `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    -- 业务字段
                                           `mng_matter_minor_id` CHAR(32) NOT NULL COMMENT '管理事项小类ID，唯一编码，UUID生成',
                                           `parent_major_id` CHAR(32) NOT NULL COMMENT '所属大类ID，关联管理事项大类ID，关联管理事项大类表(gc_biz_mng_matter_major)',
                                           `parent_major_name` VARCHAR(50) NOT NULL COMMENT '所属大类名称，与所属大类ID同步，不可改，关联管理事项大类表(gc_biz_mng_matter_major)',
                                           `matter_minor_code` CHAR(3) NOT NULL COMMENT '小类代码，3位字符，001 - 999，标准类001 - 079、扩展类080 - 999倒排，同一大类下唯一',
                                           `matter_minor_name` VARCHAR(50) NOT NULL COMMENT '小类名称，如私搭乱建/垃圾乱堆乱放等，国标名，扩展类加“（自定义）”',
                                           `matter_minor_desc` VARCHAR(255) COMMENT '小类说明，描述小类定义，如“私搭乱建：未经审批搭建”',
                                           `dept_code` CHAR(18) NOT NULL COMMENT '主管部门代码，主管部门信用代码，关联部门信息表(sys_org)',
                                           `dept_name` VARCHAR(60) NOT NULL COMMENT '主管部门名称，与主管部门代码同步，关联部门信息表(sys_org)',
                                           `is_ext` CHAR(1) NOT NULL DEFAULT '0' COMMENT '是否扩展类，0（标准类）/1（扩展类），按代码自动判断',
                                           `enable_status` CHAR(1) NOT NULL DEFAULT '1' COMMENT '启用状态，1（启用）/0（禁用），默认继承大类状态',
                                           `create_user` CHAR(32) NOT NULL COMMENT '创建人，配置人账号，关联用户信息表(sys_user)',
                                           `create_time` DATETIME NOT NULL COMMENT '创建时间，系统生成，格式yyyy - MM - dd HH:mm:ss',
                                           `update_user` CHAR(32) COMMENT '更新人，修改人账号，关联用户信息表(sys_user)',
                                           `update_time` DATETIME COMMENT '更新时间，系统生成，格式yyyy - MM - dd HH:mm:ss',
    -- 系统字段
                                           `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                           `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                           `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                           `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                           `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                           `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
                                           PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理事项小类表';

-- 管理事项大小类关联表
CREATE TABLE `gc_rel_mng_matter_maj_min` (
    -- 主键
                                             `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    -- 业务字段
                                             `mng_matter_major_minor_id` CHAR(32) NOT NULL COMMENT '关联ID，唯一编码，UUID生成',
                                             `major_id` CHAR(32) NOT NULL COMMENT '关联管理事项大类ID，关联管理事项大类表(gc_biz_mng_matter_major)',
                                             `major_name` VARCHAR(50) NOT NULL COMMENT '关联管理事项大类名称，与大类ID同步，不可手动修改，关联管理事项大类表(gc_biz_mng_matter_major)',
                                             `minor_id` CHAR(32) NOT NULL COMMENT '关联管理事项小类ID，关联管理事项小类表(gc_biz_mng_matter_minor)',
                                             `minor_name` VARCHAR(50) NOT NULL COMMENT '关联管理事项小类名称，与小类ID同步，不可手动修改，关联管理事项小类表(gc_biz_mng_matter_minor)',
                                             `rel_status` CHAR(1) NOT NULL DEFAULT '1' COMMENT '关联状态，1（有效）/0（无效），1表示正常关联，0表示已解除关联',
    -- 系统字段
                                             `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                             `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                             `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                             `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                             `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                             `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
                                             PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理事项大小类关联表';

-- 管理事项信息表
CREATE TABLE `gc_biz_mng_matter` (
    -- 主键
                                     `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    -- 业务字段
                                     `mng_matter_id` CHAR(32) NOT NULL COMMENT '事项ID，唯一编码，UUID生成',
                                     `matter_code` CHAR(16) NOT NULL COMMENT '事项标识码，格式6位行政码+2位大类码+3位小类码+5位顺序码，唯一',
                                     `matter_name` VARCHAR(50) NOT NULL COMMENT '事项名称，关联小类名称+位置，如“XX路私搭乱建”，关联管理事项小类表(gc_biz_mng_matter_minor)',
                                     `minor_id` CHAR(32) NOT NULL COMMENT '关联管理事项小类ID，关联管理事项小类表(gc_biz_mng_matter_minor)',
                                     `minor_name` VARCHAR(50) NOT NULL COMMENT '关联管理事项小类名称，与小类ID同步，不可修改，关联管理事项小类表(gc_biz_mng_matter_minor)',
                                     `grid_id` CHAR(32) NOT NULL COMMENT '关联单元网格ID，关联网格信息表(biz_grid_info)',
                                     `grid_name` VARCHAR(50) NOT NULL COMMENT '关联单元网格名称，与网格ID同步，关联网格信息表(biz_grid_info)',
                                     `matter_status` CHAR(10) NOT NULL COMMENT '事项状态，如待处置/处置中/已办结/已驳回，关联问题状态字典表(sys_dict_problem_status)',
                                     `matter_level` CHAR(10) COMMENT '事项等级，如一级/二级/三级，按影响范围判定',
                                     `dept_code` CHAR(18) NOT NULL COMMENT '主管部门代码，关联主管部门信用代码，关联部门信息表(sys_org)',
                                     `dept_name` VARCHAR(60) NOT NULL COMMENT '主管部门名称，与部门代码同步，关联部门信息表(sys_org)',
                                     `incident_location` VARCHAR(100) COMMENT '事发位置，详细位置描述，如“XX路与XX路交叉口东北侧”',
                                     `create_user` CHAR(32) NOT NULL COMMENT '录入人账号，关联用户信息表(sys_user)',
                                     `create_time` DATETIME NOT NULL COMMENT '创建时间，系统生成，格式yyyy - MM - dd HH:mm:ss',
                                     `update_user` CHAR(32) COMMENT '修改人账号，关联用户信息表(sys_user)',
                                     `update_time` DATETIME COMMENT '修改时间，系统生成，格式yyyy - MM - dd HH:mm:ss',
    -- 系统字段
                                     `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                     `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                     `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                     `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                     `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                     `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
                                     PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理事项信息表';
-- 管理事项 扩展管理事项配置表
CREATE TABLE `gc_biz_mng_matter_ext` (
    -- 主键
                                         `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    -- 业务字段
                                         `mng_matter_ext_id` CHAR(32) NOT NULL COMMENT '扩展ID，唯一编码，UUID生成',
                                         `matter_major_id` CHAR(32) NOT NULL COMMENT '归属大类ID，无则归“其他管理事项”，关联管理事项大类ID，关联管理事项大类表(gc_biz_mng_matter_major)',
                                         `matter_major_name` VARCHAR(50) NOT NULL COMMENT '归属大类名称，与大类ID同步，关联管理事项大类表(gc_biz_mng_matter_major)',
                                         `ext_minor_code` CHAR(3) NOT NULL COMMENT '扩展小类代码，080 - 999，倒排编码，避免与标准类冲突',
                                         `ext_minor_name` VARCHAR(50) NOT NULL COMMENT '扩展小类名称，名称加“(自定义)”，如“共享单车乱停放(自定义)”',
                                         `ext_minor_desc` VARCHAR(255) NOT NULL COMMENT '扩展小类说明，描述用途，如“共享单车未停指定区域，影响市容”',
                                         `suggest_dept_code` CHAR(18) COMMENT '建议主管部门代码，建议主管部门信用代码，关联部门信息表(sys_org)',
                                         `suggest_dept_name` VARCHAR(60) COMMENT '建议主管部门名称，与部门代码同步，关联部门信息表(sys_org)',
    -- 系统字段
                                         `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                         `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                         `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                         `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                         `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                         `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
                                         PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理事项扩展管理事项配置表';
-- 管理部件统计报表
CREATE TABLE `stat_mng_comp` (
    -- 主键
                                 `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    -- 业务字段
                                 `stat_mng_comp_id` CHAR(32) NOT NULL COMMENT '统计ID，唯一编码，UUID生成',
                                 `stat_cycle` CHAR(10) NOT NULL COMMENT '统计周期，如年/季/月，格式:YYYY/YYYYQn/YYYYMM',
                                 `stat_cycle_name` VARCHAR(20) NOT NULL COMMENT '统计周期名称，如“2025年9月”',
                                 `area_code` CHAR(6) NOT NULL COMMENT '行政区划代码，符合GB/T 2260，关联行政区划表(sys_area)',
                                 `area_name` VARCHAR(50) NOT NULL COMMENT '行政区划名称，与代码同步，关联行政区划表(sys_area)',
                                 `comp_major_id` CHAR(32) NOT NULL COMMENT '部件大类ID，关联管理部件大类ID，关联管理部件大类表(biz_mng_comp_major)',
                                 `comp_major_name` VARCHAR(50) NOT NULL COMMENT '部件大类名称，与大类ID同步，关联管理部件大类表(biz_mng_comp_major)',
                                 `comp_minor_id` CHAR(32) COMMENT '部件小类ID，关联管理部件小类ID（钻取时必填），关联管理部件小类表(biz_mng_comp_minor)',
                                 `comp_minor_name` VARCHAR(50) COMMENT '部件小类名称，与小类ID同步，关联管理部件小类表(biz_mng_comp_minor)',
                                 `total_comp_count` INT COMMENT '部件总数，该维度下部件总数，关联管理部件表(biz_mng_comp)',
                                 `normal_comp_count` INT COMMENT '完好部件数，状态为“完好”的数量，关联管理部件表(biz_mng_comp)',
                                 `damaged_comp_count` INT COMMENT '破损部件数，状态为“破损”的数量，关联管理部件表(biz_mng_comp)',
                                 `lost_comp_count` INT COMMENT '丢失部件数，状态为“丢失”的数量，关联管理部件表(biz_mng_comp)',
                                 `discarded_comp_count` INT COMMENT '废弃部件数，状态为“废弃”的数量，关联管理部件表(biz_mng_comp)',
                                 `new_comp_count` INT COMMENT '新增部件数，统计周期内新增数量，关联管理部件表(biz_mng_comp)',
                                 `update_comp_count` INT COMMENT '更新部件数，统计周期内更新数量，关联管理部件表(biz_mng_comp)',
    -- 系统字段
                                 `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                 `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                 `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                 `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                 `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                 `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
                                 PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理部件统计表';
-- 管理事项统计表
CREATE TABLE `stat_mng_matter` (
    -- 主键
                                   `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    -- 业务字段
                                   `stat_mng_matter_id` CHAR(32) NOT NULL COMMENT '统计ID，唯一编码，UUID生成',
                                   `stat_cycle` CHAR(10) NOT NULL COMMENT '统计周期，如年/季/月，格式:YYYY/YYYYQn/YYYYMM',
                                   `stat_cycle_name` VARCHAR(20) NOT NULL COMMENT '统计周期名称，如“2025年Q3”',
                                   `area_code` CHAR(6) NOT NULL COMMENT '行政区划代码，符合GB/T 2260，关联行政区划表(sys_area)',
                                   `area_name` VARCHAR(50) NOT NULL COMMENT '行政区划名称，与代码同步，关联行政区划表(sys_area)',
                                   `matter_major_id` CHAR(32) NOT NULL COMMENT '事项大类ID，关联管理事项大类ID，关联管理事项大类表(biz_mng_matter_major)',
                                   `matter_major_name` VARCHAR(50) NOT NULL COMMENT '事项大类名称，与大类ID同步，关联管理事项大类表(biz_mng_matter_major)',
                                   `matter_minor_id` CHAR(32) COMMENT '事项小类ID，关联管理事项小类ID(钻取时必填)，关联管理事项小类表(biz_mng_matter_minor)',
                                   `matter_minor_name` VARCHAR(50) COMMENT '事项小类名称，与小类ID同步，关联管理事项小类表(biz_mng_matter_minor)',
                                   `dept_code` CHAR(18) COMMENT '处置部门代码，关联主管部门信用代码，关联部门信息表(sys_org)',
                                   `dept_name` VARCHAR(60) COMMENT '处置部门名称，与部门代码同步，关联部门信息表(sys_org)',
                                   `total_rpt_count` INT COMMENT '上报总数，统计周期内上报总数，关联管理事项表(biz_mng_matter)',
                                   `pend_count` INT COMMENT '待处置数，状态为“待处置”的数量，关联管理事项表（biz_mng_matter）',
                                   `handl_count` INT COMMENT '处置中数，状态为“处置中”的数量，关联管理事项表（biz_mng_matter）',
                                   `completed_count` INT COMMENT '已办结数，状态为“已办结”的数量，关联管理事项表（biz_mng_matter）',
                                   `rejected_count` INT COMMENT '已驳回数，状态为“已驳回”的数量，关联管理事项表（biz_mng_matter）',
                                   `complete_rate` DECIMAL(5, 2) COMMENT '办结率，（已办结数/（上报总数 - 已驳回数））×100，关联管理事项表（biz_mng_matter）',
                                   `avg_handle_endure` DECIMAL(10, 2) COMMENT '平均处置时长，已办结事项处置时长平均值，关联管理事项表（biz_mng_matter）',
    -- 系统字段
                                   `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                   `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                   `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                   `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                   `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                   `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
                                   PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理事项统计表';
--  监测部件分类配置表
CREATE TABLE `sys_mon_comp_cat` (
    -- 主键
                                    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    -- 业务字段
                                    `mon_comp_cat_id` CHAR(32) NOT NULL COMMENT '分类ID，唯一编码，UUID生成',
                                    `parent_cat_id` CHAR(32) NOT NULL COMMENT '父类ID，关联本表格“分类ID”，大类父类ID为“0”；监测部件分类配置表（sys_mon_comp_cat）',
                                    `cat_level` CHAR(1) NOT NULL COMMENT '分类层级，标识分类所处层级，1对应大类、2对应中类、3对应小类',
                                    `cat_code` CHAR(3) NOT NULL COMMENT '分类代码，大类/中类从01顺序编排，小类从001顺序编排，扩展类小类从080倒排',
                                    `cat_name` VARCHAR(50) NOT NULL COMMENT '分类名称，参照GB/T XXXXX.6标准名称，扩展类标注“自定义”',
                                    `cat_desc` VARCHAR(255) COMMENT '分类说明，描述分类包含的部件范围，如“燃气压力传感器：监测燃气管道压力的设备”',
    -- 系统字段
                                    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                    `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                    `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                    `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
                                    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '监测部件分类配置表';

-- 监测部件标识码规则配置
CREATE TABLE `sys_mon_comp_code_rule` (
    -- 主键
                                          `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    -- 业务字段
                                          `code_rule_id` CHAR(32) NOT NULL COMMENT '规则ID，唯一编码，UUID生成',
                                          `rule_name` VARCHAR(50) NOT NULL COMMENT '规则名称',
                                          `rule_desc` VARCHAR(255) COMMENT '规则说明',
                                          `code_format` VARCHAR(100) NOT NULL COMMENT '编码格式',
    -- 系统字段
                                          `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                          `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                          `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                          `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                          `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                          `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
                                          PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '监测部件标识码规则表';

--监测部件数据配置表
CREATE TABLE `sys_mon_comp_data_cfg` (
    -- 主键
                                         `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    -- 业务字段
                                         `mon_comp_data_id` CHAR(32) NOT NULL COMMENT '配置ID，唯一编码，UUID生成',
                                         `comp_cat_id` CHAR(32) NOT NULL COMMENT '部件分类ID，关联监测部件分类表小类ID；监测部件分类配置表(sys_mon_comp_cat)',
                                         `comp_cat_name` VARCHAR(50) NOT NULL COMMENT '部件分类名称，与分类ID同步，自动填充；监测部件分类配置表(sys_mon_comp_cat)',
                                         `data_type` CHAR(10) NOT NULL COMMENT '数据类型，标识数据类型，空间对应坐标等，属性对应字段信息',
                                         `coord_system` VARCHAR(50) COMMENT '坐标系类型，条件必选(数据类型为空间数据时)，固定为2000国家大地坐标系，不可修改',
                                         `accuracy_level` CHAR(1) COMMENT '定位精度级别，条件必选(数据类型为空间数据时)，A类(±0.5m)、B类(±1.0m)，参照GB/T XXXXX.6',
                                         `elevation_datum` VARCHAR(50) COMMENT '高程基准，条件必选(数据类型为空间数据时)，固定为1985国家高程基准，不可修改',
                                         `field_name` VARCHAR(50) COMMENT '字段名称，条件必选（数据类型为属性数据时），部件标识码/主管部门代码等',
                                         `field_code` VARCHAR(50) COMMENT '字段代码，条件必选（数据类型为属性数据时），采用蛇形命名法，与数据库字段一致',
    -- 系统字段
                                         `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                         `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                         `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                         `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                         `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                         `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
                                         PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '监测部件数据配置表';

-- 监测部件信息表
CREATE TABLE `biz_mon_comp_info` (
    -- 主键
                                     `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    -- 业务字段
                                     `mon_comp_id` CHAR(32) NOT NULL COMMENT '部件ID，唯一编码，UUID生成',
                                     `comp_code` CHAR(18) NOT NULL COMMENT '部件标识码，按18位规则生成，如110101010200100001；监测部件标识码规则表(sys_mon_comp_code_rule)',
                                     `comp_name` VARCHAR(50) NOT NULL COMMENT '部件名称，关联分类名称，如“燃气压力传感器”；监测部件分类配置表(sys_mon_comp_cat)',
                                     `comp_cat_id` CHAR(32) NOT NULL COMMENT '部件分类ID，关联监测部件分类表小类ID；监测部件分类配置表(sys_mon_comp_cat)',
                                     `dept_code` CHAR(18) NOT NULL COMMENT '主管部门代码，主管部门统一社会信用代码；部门信息表(sys_org)',
                                     `dept_name` VARCHAR(60) NOT NULL COMMENT '主管部门名称，与部门代码同步，自动填充；部门信息表(sys_org)',
                                     `region_code` CHAR(6) NOT NULL COMMENT '所在行政区域代码，符合GB/T 2260，县级行政区域代码；行政区划表(sys_area)',
                                     `region_name` VARCHAR(50) NOT NULL COMMENT '所在行政区域名称，与行政代码同步，自动填充；行政区划表(sys_area)',
                                     `grid_id` CHAR(32) NOT NULL COMMENT '所在网格ID，关联单元网格ID；单元网格划分表(biz_unit_grid_div)',
                                     `grid_name` VARCHAR(50) NOT NULL COMMENT '所在网格名称，与网格ID同步，自动填充；单元网格划分表(biz_unit_grid_div)',
                                     `coord_x` DECIMAL(15, 2) NOT NULL COMMENT '坐标X，经度，2000国家大地坐标系',
                                     `coord_y` DECIMAL(15, 2) NOT NULL COMMENT '坐标Y，纬度，2000国家大地坐标系',
    -- 系统字段
                                     `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                     `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                     `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                     `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                     `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                     `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
                                     PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '监测部件信息表';
-- 扩展监测部件配置表
CREATE TABLE `sys_mon_comp_ext` (
    -- 主键
                                    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    -- 业务字段
                                    `ext_id` CHAR(32) NOT NULL COMMENT '扩展配置ID，唯一编码，UUID生成',
                                    `comp_id` CHAR(32) COMMENT '部件ID，关联监测部件信息表ID；监测部件信息表(biz_mon_comp_info)',
                                    `ext_key` VARCHAR(50) COMMENT '扩展键',
                                    `ext_value` VARCHAR(255) COMMENT '扩展值',
    -- 系统字段
                                    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                    `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                    `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                    `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
                                    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '扩展监测部件配置表';

-- 监测事件分类配置表
CREATE TABLE `sys_mon_evt_cat` (
    -- 主键
                                   `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    -- 业务字段
                                   `mon_evt_cat_id` CHAR(32) NOT NULL COMMENT '分类ID，唯一编码，UUID生成',
                                   `parent_evt_id` CHAR(32) NOT NULL COMMENT '父类ID，关联本表格“分类ID”，大类父类ID为“0”；监测事件分类配置表（sys_mon_evt_cat）',
                                   `evt_cat_level` CHAR(1) NOT NULL COMMENT '分类层级，标识分类所处层级，1 (大类)/2 (中类)/3 (小类)',
                                   `evt_cat_code` CHAR(3) NOT NULL COMMENT '分类代码，大类/中类从01顺序编排，小类从001顺序编排，扩展类小类从080倒排',
                                   `evt_cat_name` VARCHAR(50) NOT NULL COMMENT '分类名称，参照GB/T XXXXX.6标准名称，扩展类标注 “自定义”',
                                   `evt_cat_desc` VARCHAR(255) COMMENT '分类说明，描述事件范围，如“燃气泄漏：燃气从管道或设备泄漏的事件”',
                                   `enable_status` CHAR(1) NOT NULL DEFAULT '1' COMMENT '启用状态，标识分类是否可用，默认1',
                                   `create_user` CHAR(32) NOT NULL COMMENT '创建人，填写创建人账号，用户信息表(sys_user)',
                                   `create_time` DATETIME NOT NULL COMMENT '创建时间，格式：yyyy-MM-dd HH:mm:ss，系统自动生成',
                                   `update_user` CHAR(32) COMMENT '更新人，填写更新人账号，用户信息表(sys_user)',
                                   `update_time` DATETIME COMMENT '更新时间，格式：yyyy-MM-dd HH:mm:ss，系统自动生成',
                                   `ext_cat1` VARCHAR(50) COMMENT '分类扩展字段1，预留字段，用于存储分类额外属性（如“事件响应时效”）',
                                   `ext_cat2` VARCHAR(50) COMMENT '分类扩展字段2，预留字段，用于存储分类额外属性（如“事件响应时效”）',
                                   `ext_common1` VARCHAR(100) COMMENT '通用扩展字段1，预留通用字段，存储额外配置信息',
                                   `ext_common2` VARCHAR(100) COMMENT '通用扩展字段2，预留通用字段，存储额外配置信息',
    -- 系统字段
                                   `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                   `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                   `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                   `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                   `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                   `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
                                   PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '监测事件分类配置表';

-- 监测事件标识码规则表
CREATE TABLE `sys_mon_evt_code_rule` (
    -- 主键
                                         `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    -- 业务字段
                                         `mon_evt_rule_id` CHAR(32) NOT NULL COMMENT '规则ID，唯一编码，UUID生成',
                                         `rule_name` VARCHAR(50) NOT NULL COMMENT '规则名称，如“监测事件18位标识码规则”',
                                         `admin_code_len` INT NOT NULL COMMENT '行政代码位数，固定为6位，符合GB/T 2260',
                                         `major_code_len` INT NOT NULL COMMENT '大类代码位数，固定为2位，关联事件分类表大类代码；监测事件分类配置表（sys_mon_evt_cat）',
                                         `mid_code_len` INT NOT NULL COMMENT '中类代码位数，固定为2位，关联事件分类表中类代码；监测事件分类配置表（sys_mon_evt_cat）',
                                         `minor_code_len` INT NOT NULL COMMENT '小类代码位数，固定为2位，关联事件分类表小类代码；监测事件分类配置表（sys_mon_evt_cat）',
                                         `seq_code_len` INT NOT NULL COMMENT '顺序码位数，固定为6位，从000001顺序编排',
                                         `seq_gen_rule` VARCHAR(100) NOT NULL COMMENT '顺序码生成规则，同一行政+小类下按上报时间递增，默认“同一县级行政区域+小类下按上报时间递增”',
                                         `enable_status` CHAR(1) NOT NULL COMMENT '启用状态，同一时间仅一个规则启用，1（启用）/0（禁用）',
                                         `create_user` CHAR(32) NOT NULL COMMENT '创建人，填写创建人账号，用户信息表(sys_user)',
                                         `create_time` DATETIME NOT NULL COMMENT '创建时间，格式：yyyy-MM-dd HH:mm:ss，系统自动生成',
                                         `update_user` CHAR(32) COMMENT '更新人，填写更新人账号，用户信息表(sys_user)',
                                         `update_time` DATETIME COMMENT '更新时间，格式：yyyy-MM-dd HH:mm:ss，系统自动生成',
    -- 系统字段
                                         `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                         `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                         `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                         `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                         `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                         `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
                                         PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '监测事件标识码规则表';

-- 监测事件数据配置表
CREATE TABLE `sys_mon_evt_data_cfg` (
    -- 主键
                                        `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    -- 业务字段
                                        `mon_evt_data_id` CHAR(32) NOT NULL COMMENT '配置ID，唯一编码，UUID生成',
                                        `evt_cat_id` CHAR(32) NOT NULL COMMENT '事件分类ID，关联事件分类表小类ID，监测事件分类配置表(sys_mon_evt_cat)',
                                        `evt_cat_name` VARCHAR(50) NOT NULL COMMENT '事件分类名称，与分类ID同步，自动填充，监测事件分类配置表(sys_mon_evt_cat)',
                                        `field_name` VARCHAR(50) NOT NULL COMMENT '字段名称，属性字段名称，参照GB/T XXXXX.7',
                                        `field_code` VARCHAR(50) NOT NULL COMMENT '字段代码，蛇形命名法，与数据库字段一致',
                                        `field_type` VARCHAR(20) NOT NULL COMMENT '字段类型，char/varchar/int等，字段数据类型',
                                        `field_length` VARCHAR(20) NOT NULL COMMENT '字段长度，18/32/10等，字段长度及精度',
                                        `constraint_type` CHAR(10) NOT NULL COMMENT '约束条件，必选/可选，标识字段是否必选',
                                        `value_range` VARCHAR(100) COMMENT '值域范围，字段允许值域，如“一级/二级/三级”',
                                        `field_desc` VARCHAR(255) COMMENT '字段说明，字段填写说明，如“事发位置需含具体路名”',
                                        `create_user` CHAR(32) NOT NULL COMMENT '创建人，填写创建人账号，用户信息表(sys_user)',
                                        `create_time` DATETIME NOT NULL COMMENT '创建时间，格式：yyyy-MM-dd HH:mm:ss，系统自动生成',
                                        `update_user` CHAR(32) COMMENT '更新人，填写更新人账号，用户信息表(sys_user)',
                                        `update_time` DATETIME COMMENT '更新时间，格式：yyyy-MM-dd HH:mm:ss，系统自动生成',
                                        `ext_cat1` VARCHAR(50) COMMENT '分类扩展字段1，预留字段，用于存储配置额外属性（如“字段校验规则”）',
                                        `ext_cat2` VARCHAR(50) COMMENT '分类扩展字段2，预留字段，用于存储配置额外属性（如“字段校验规则”）',
                                        `ext_common1` VARCHAR(100) COMMENT '通用扩展字段1，预留通用字段，存储额外配置信息',
                                        `ext_common2` VARCHAR(100) COMMENT '通用扩展字段2，预留通用字段，存储额外配置信息',
    -- 系统字段
                                        `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                        `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                        `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                        `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                        `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                        `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
                                        PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '监测事件数据配置表';

-- 监测事件信息表
CREATE TABLE `biz_mon_evt_info` (
    -- 主键
                                    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    -- 业务字段
                                    `mon_evt_id` CHAR(32) NOT NULL COMMENT '事件ID，唯一编码，UUID生成',
                                    `evt_code` CHAR(18) NOT NULL COMMENT '事件标识码，按18位规则生成，如110101020300200001；监测事件标识码规则表 (sys_mon_evt_code_rule)',
                                    `evt_name` VARCHAR(50) NOT NULL COMMENT '事件名称，关联分类名称，如 “燃气泄漏事件”；监测事件分类配置表 (sys_mon_evt_cat)',
                                    `evt_cat_id` CHAR(32) NOT NULL COMMENT '事件分类ID，关联事件分类表小类ID，监测事件分类配置表 (sys_mon_evt_cat)',
                                    `rel_comp_id` CHAR(32) NOT NULL COMMENT '关联部件ID，关联监测部件信息表部件ID，监测部件信息表 (biz_mon_comp_info)',
                                    `rel_comp_name` VARCHAR(50) NOT NULL COMMENT '关联部件名称，与部件ID同步，自动填充，监测部件信息表 (biz_mon_comp_info)',
                                    `incident_pos` VARCHAR(100) NOT NULL COMMENT '事发位置，事件发生位置，如 “XX路与XX路交叉口”',
                                    `incident_x` DECIMAL(15, 2) NOT NULL COMMENT '事发坐标X，经度，2000国家大地坐标系',
                                    `incident_y` DECIMAL(15, 2) NOT NULL COMMENT '事发坐标Y，纬度，2000国家大地坐标系',
                                    `evt_level` CHAR(10) NOT NULL COMMENT '事件等级，一级/二级/三级，事件严重程度，参照GB/T XXXXX.6',
                                    `handle_status` CHAR(10) NOT NULL COMMENT '处置状态，待处置/处置中/已办结/已驳回，事件处置进度',
    -- 系统字段
                                    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                    `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                    `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                    `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
                                    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '监测事件信息表';

-- 扩展监测事件配置表
CREATE TABLE `sys_mon_evt_ext` (
    -- 主键
                                   `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    -- 业务字段
                                   `mon_evt_ext_id` CHAR(32) NOT NULL COMMENT '扩展ID，唯一编码，UUID生成',
                                   `ext_minor_code` CHAR(3) NOT NULL COMMENT '扩展小类代码，从080倒排',
                                   `ext_minor_name` VARCHAR(50) NOT NULL COMMENT '扩展小类名称，如“智能充电桩过载事件”',
                                   `parent_mid_id` CHAR(32) NOT NULL COMMENT '所属中类ID，关联事件分类表中类ID；监测事件分类配置表(sys_mon_evt_cat)',
                                   `parent_mid_name` VARCHAR(50) NOT NULL COMMENT '所属中类名称，与中类ID同步，自动填充；监测事件分类配置表(sys_mon_evt_cat)',
                                   `rel_comp_cat_id` CHAR(32) COMMENT '关联部件分类ID，关联部件分类表小类ID；监测部件分类配置表(sys_mon_comp_cat)',
                                   `rel_comp_cat_name` VARCHAR(50) COMMENT '关联部件分类名称，与部件分类ID同步，自动填充；监测部件分类配置表(sys_mon_comp_cat)',
                                   `ext_desc` VARCHAR(255) NOT NULL COMMENT '扩展说明，描述扩展小类用途，如“用于记录充电桩过载事件”',
                                   `apply_reason` VARCHAR(255) NOT NULL COMMENT '申请原因，说明扩展必要性，如“现有分类无法覆盖充电桩过载场景”',
    -- 系统字段
                                   `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
                                   `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
                                   `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识',
                                   `tenant_id` BIGINT DEFAULT 0 NOT NULL COMMENT '租户ID',
                                   `create_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '系统创建时间',
                                   `update_time_sys` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
                                   PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '扩展监测事件配置表';

CREATE TABLE `stat_mon_evt_rpt` (
    -- 自增主键
                                    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
    -- 统计ID
                                    `stat_id` CHAR(32) NOT NULL COMMENT '唯一编码，采用UUID生成',
    -- 统计周期
                                    `stat_cycle` CHAR(10) NOT NULL COMMENT '统计周期类型，格式:“YYYY”“YYYYQn”“YYYYMM”',
    -- 统计周期名称
                                    `stat_cycle_name` VARCHAR(20) NOT NULL COMMENT '如“2025年09月”',
    -- 行政区划代码
                                    `region_code` CHAR(6) NOT NULL COMMENT '符合GB/T 2260，统计区域的行政区划代码',
    -- 行政区划名称
                                    `region_name` VARCHAR(50) NOT NULL COMMENT '与行政区划代码关联，自动同步名称',
    -- 事件大类ID
                                    `evt_major_id` CHAR(32) NOT NULL COMMENT '关联监测事件分类配置表的大类ID',
    -- 事件大类名称
                                    `evt_major_name` VARCHAR(50) NOT NULL COMMENT '与事件大类ID关联，自动同步名称',
    -- 事件小类ID
                                    `evt_minor_id` CHAR(32) COMMENT '关联监测事件分类配置表的小类ID（钻取时必填）',
    -- 事件小类名称
                                    `evt_minor_name` VARCHAR(50) COMMENT '与事件小类ID关联，自动同步名称',
    -- 处置部门代码
                                    `dept_code` CHAR(18) COMMENT '事件处置部门统一社会信用代码',
    -- 处置部门名称
                                    `dept_name` VARCHAR(60) COMMENT '与处置部门代码关联，自动同步名称',
    -- 上报总数
                                    `total_rpt_count` INT(10) NOT NULL COMMENT '统计周期内该维度下事件上报总数',
    -- 待处置数
                                    `pend_count` INT(10) NOT NULL COMMENT '状态为“待处置”的事件数量',
    -- 处置中数
                                    `handl_count` INT(10) NOT NULL COMMENT '状态为“处置中”的事件数量',
    -- 已办结数
                                    `completed_count` INT(10) NOT NULL COMMENT '状态为“已办结”的事件数量',
    -- 已驳回数
                                    `rejected_count` INT(10) NOT NULL COMMENT '状态为“已驳回”的事件数量',
    -- 一级事件数
                                    `level1_count` INT(10) NOT NULL COMMENT '事件等级为“一级”的数量',
    -- 主键
                                    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '监测事件统计报表';

CREATE TABLE `stat_mon_comp_rpt` (
    -- 自增主键
                                     `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
    -- 统计ID
                                     `stat_id` CHAR(32) NOT NULL COMMENT '唯一编码，采用UUID生成',
    -- 统计周期
                                     `stat_cycle` CHAR(10) NOT NULL COMMENT '统计周期类型，格式:YYYY/YYYYQn/YYYYMM',
    -- 统计周期名称
                                     `stat_cycle_name` VARCHAR(20) NOT NULL COMMENT '统计周期的中文描述，如“2025年Q3”“2025年09月”',
    -- 行政区划代码
                                     `region_code` CHAR(6) NOT NULL COMMENT '符合GB/T 2260，统计区域的行政区划代码',
    -- 行政区划名称
                                     `region_name` VARCHAR(50) NOT NULL COMMENT '与行政区划代码关联，自动同步区域名称',
    -- 部件大类ID
                                     `comp_major_id` CHAR(32) NOT NULL COMMENT '关联监测部件分类配置表的大类ID',
    -- 部件大类名称
                                     `comp_major_name` VARCHAR(50) NOT NULL COMMENT '与部件大类ID关联，自动同步名称',
    -- 部件小类ID
                                     `comp_minor_id` CHAR(32) COMMENT '关联监测部件分类配置表的小类ID(钻取统计时必填)',
    -- 部件小类名称
                                     `comp_minor_name` VARCHAR(50) COMMENT '与部件小类ID关联，自动同步名称',
    -- 部件总数
                                     `total_comp_count` INT(10) NOT NULL COMMENT '该维度下监测部件总数量',
    -- 正常部件数
                                     `normal_comp_count` INT(10) NOT NULL COMMENT '状态为“正常”的部件数量',
    -- 异常部件数
                                     `abn_comp_count` INT(10) NOT NULL COMMENT '状态为“异常”的部件数量',
    -- 维护部件数
                                     `mnt_comp_count` INT(10) NOT NULL COMMENT '状态为“维护”的部件数量',
    -- 废弃部件数
                                     `discard_comp_count` INT(10) NOT NULL COMMENT '状态为“废弃”的部件数量',
    -- 新增部件数
                                     `new_comp_count` INT(10) COMMENT '统计周期内新增的部件数量（创建时间在周期内）',
    -- 更新部件数
                                     `update_comp_count` INT(10) COMMENT '统计周期内更新的部件数量（更新时间在周期内）',
    -- 统计人
                                     `stat_user` CHAR(32) NOT NULL COMMENT '生成报表的用户ID',
    -- 统计时间
                                     `stat_time` DATETIME NOT NULL COMMENT '报表生成时间，格式：yyyy-MM-dd HH:mm:ss',
    -- 报表备注
                                     `rpt_remark` VARCHAR(255) COMMENT '报表说明，如“统计范围：XX市建成区监测部件”',
    -- 分类扩展字段1
                                     `ext_cat1` VARCHAR(50) COMMENT '预留字段，存储统计额外维度（如“统计范围”）',
    -- 分类扩展字段2
                                     `ext_cat2` VARCHAR(50) COMMENT '预留字段，存储统计额外维度（如“统计范围”）',
    -- 通用扩展字段1
                                     `ext_common1` VARCHAR(100) COMMENT '预留通用字段，存储额外统计信息',
    -- 通用扩展字段2
                                     `ext_common2` VARCHAR(100) COMMENT '预留通用字段，存储额外统计信息',
    -- 主键
                                     PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '监测部件统计报表';

CREATE TABLE `biz_evt_type_cfg` (
    -- 自增主键
                                    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
    -- 事件类型ID
                                    `evt_type_id` CHAR(32) NOT NULL COMMENT '类型ID，唯一编码，UUID',
    -- 父类型ID
                                    `parent_type_id` CHAR(32) NOT NULL COMMENT '父类型ID，关联本表“事件类型ID”，大类父ID为“0”',
    -- 类型层级
                                    `type_level` CHAR(1) NOT NULL COMMENT '类型层级，1（大类）/2（小类）',
    -- 类型编码
                                    `type_code` CHAR(3) NOT NULL COMMENT '类型编码，001 - 999（大类01 - 99、小类001 - 999，扩展类080 - 999倒排），同一父类型下编码唯一',
    -- 类型名称
                                    `type_name` VARCHAR(50) NOT NULL COMMENT '类型名称，标准名称，扩展类加“（自定义）”',
    -- 类型说明
                                    `type_desc` VARCHAR(255) COMMENT '类型说明，描述类型范围，如“占道经营：商户违规占用道路经营”',
    -- 所属区域代码
                                    `region_code` CHAR(6) COMMENT '所属区域代码，关联行政区划表，限定区域适用',
    -- 所属区域名称
                                    `region_name` VARCHAR(50) COMMENT '所属区域名称，与区域代码同步',
    -- 启用状态
                                    `enable_status` CHAR(1) DEFAULT '1' COMMENT '启用状态，1（启用）/0（禁用），默认1',
    -- 创建人
                                    `create_user` CHAR(32) COMMENT '创建人，创建人账号，关联用户信息表(sys_user)',
    -- 创建时间
                                    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，格式yyyy - MM - dd HH:mm:ss，系统自动生成',
    -- 更新人
                                    `update_user` CHAR(32) COMMENT '更新人，更新人账号，关联用户信息表(sys_user)',
    -- 更新时间
                                    `update_time` DATETIME COMMENT '更新时间，格式yyyy - MM - dd HH:mm:ss，系统自动生成',
    -- 分类扩展字段1
                                    `ext_cat1` VARCHAR(50) COMMENT '分类扩展字段1，预留，如“处置时限”',
    -- 分类扩展字段2
                                    `ext_cat2` VARCHAR(50) COMMENT '分类扩展字段2，预留，如“处置时限”',
    -- 通用扩展字段1
                                    `ext_common1` VARCHAR(100) COMMENT '通用扩展字段1，预留，存储参考标准',
    -- 通用扩展字段2
                                    `ext_common2` VARCHAR(100) COMMENT '通用扩展字段2，预留，存储参考标准',
                                    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '事件类型配置表';

CREATE TABLE `biz_evt_rpt_reg` (
    -- 自增主键
                                   `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
    -- 接报ID
                                   `rpt_id` CHAR(32) NOT NULL COMMENT '接报ID，唯一编码，UUID',
    -- 事件编码
                                   `evt_code` CHAR(18) NOT NULL COMMENT '事件编码，系统自动生成，格式“6位区域码+2位大类码+3位小类码+7位顺序码”',
    -- 事件类型ID
                                   `evt_type_id` CHAR(32) NOT NULL COMMENT '事件类型ID，关联事件类型配置表小类ID',
    -- 事件类型名称
                                   `evt_type_name` VARCHAR(50) NOT NULL COMMENT '事件类型名称，与类型ID同步',
    -- 事发区域代码
                                   `incident_region_code` CHAR(6) NOT NULL COMMENT '事发区域代码，关联行政区划表',
    -- 事发区域名称
                                   `incident_region_name` VARCHAR(50) NOT NULL COMMENT '事发区域名称，与区域代码同步',
    -- 事发位置
                                   `incident_location` VARCHAR(100) NOT NULL COMMENT '事发位置，详细位置，如“XX路与XX路交叉口东北侧”',
    -- 事发坐标X
                                   `incident_coord_x` DECIMAL(15, 2) COMMENT '事发坐标X，度，2000国家大地坐标系经度',
    -- 事发坐标Y
                                   `incident_coord_y` DECIMAL(15, 2) COMMENT '事发坐标Y，度，2000国家大地坐标系纬度',
    -- 事件描述
                                   `evt_desc` VARCHAR(500) NOT NULL COMMENT '事件描述，事件详情，如“商贩占用人行道售卖水果”',
                                   PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '事件接报登记表';


