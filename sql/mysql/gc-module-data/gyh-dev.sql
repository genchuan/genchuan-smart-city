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