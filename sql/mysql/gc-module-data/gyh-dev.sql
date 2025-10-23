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

