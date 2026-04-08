-- ============================================
-- 学工管理模块数据库建表 SQL
-- 数据库名：student_mgmt
-- ============================================

-- （1）学生信息表
DROP TABLE IF EXISTS `student_info`;
CREATE TABLE `student_info` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `student_no` VARCHAR(32) NOT NULL COMMENT '学号',
    `name` VARCHAR(64) NOT NULL COMMENT '姓名',
    `id_card` VARCHAR(18) NOT NULL COMMENT '身份证号',
    `photo` VARCHAR(255) DEFAULT NULL COMMENT '学生照片地址',
    `education_level` VARCHAR(20) NOT NULL COMMENT '学历层次：中专/大专/本科/研究生',
    `study_form` VARCHAR(20) NOT NULL COMMENT '学习形式：全日制/非全日制/函授',
    `major` VARCHAR(64) NOT NULL COMMENT '专业',
    `class_name` VARCHAR(64) NOT NULL COMMENT '班级',
    `student_type` VARCHAR(20) NOT NULL COMMENT '学生类型：普通生/特长生/转学生',
    `status` VARCHAR(20) NOT NULL COMMENT '学籍状态：在籍/休学/退学/异动',
    `phone` VARCHAR(20) DEFAULT NULL COMMENT '联系电话',
    `parent_phone` VARCHAR(20) DEFAULT NULL COMMENT '家长联系电话',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_student_no` (`student_no`),
    UNIQUE KEY `uk_id_card` (`id_card`),
    INDEX `idx_class_name` (`class_name`),
    INDEX `idx_major` (`major`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生信息表';

-- （2）荣誉管理表
DROP TABLE IF EXISTS `honor_mgmt`;
CREATE TABLE `honor_mgmt` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `student_id` BIGINT NOT NULL COMMENT '学生 ID',
    `honor_type` VARCHAR(20) NOT NULL COMMENT '荣誉类型：优秀学生/奖学金/竞赛获奖/其他',
    `honor_name` VARCHAR(100) NOT NULL COMMENT '荣誉名称',
    `get_time` DATETIME NOT NULL COMMENT '获得时间',
    `audit_user` VARCHAR(64) DEFAULT NULL COMMENT '审核人',
    `audit_time` DATETIME DEFAULT NULL COMMENT '审核时间',
    `push_time` DATETIME DEFAULT NULL COMMENT '推送时间',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：待审核/已通过/已推送',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_student_id` (`student_id`),
    INDEX `idx_honor_type` (`honor_type`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='荣誉管理表';

-- （3）考评管理表
DROP TABLE IF EXISTS `assess_mgmt`;
CREATE TABLE `assess_mgmt` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `class_name` VARCHAR(64) NOT NULL COMMENT '班级',
    `assess_type` VARCHAR(20) NOT NULL COMMENT '考评类型：教室卫生/早操/文明班级/黑板报',
    `cycle` VARCHAR(20) NOT NULL COMMENT '统计周期：周/月/学期',
    `score` DECIMAL(5,2) NOT NULL COMMENT '考评得分',
    `rank` INT DEFAULT NULL COMMENT '班级排名',
    `assess_user` VARCHAR(64) DEFAULT NULL COMMENT '考评人',
    `publish_time` DATETIME DEFAULT NULL COMMENT '发布时间',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：未发布/已发布',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_class_name` (`class_name`),
    INDEX `idx_assess_type` (`assess_type`),
    INDEX `idx_cycle` (`cycle`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='考评管理表';

-- （4）违纪管理表
DROP TABLE IF EXISTS `violate_mgmt`;
CREATE TABLE `violate_mgmt` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `student_id` BIGINT NOT NULL COMMENT '学生 ID',
    `violate_type` VARCHAR(20) NOT NULL COMMENT '违纪类型：仪容仪表/行为违规/其他',
    `punish_type` VARCHAR(20) NOT NULL COMMENT '处分类型：警告/记过/留校察看/开除',
    `violate_time` DATETIME NOT NULL COMMENT '违纪时间',
    `violate_reason` VARCHAR(200) DEFAULT NULL COMMENT '违纪原因',
    `audit_user` VARCHAR(64) DEFAULT NULL COMMENT '审批人',
    `audit_time` DATETIME DEFAULT NULL COMMENT '审批时间',
    `push_time` DATETIME DEFAULT NULL COMMENT '家长推送时间',
    `warn_time` DATETIME DEFAULT NULL COMMENT '预警时间',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：待审批/已执行/已预警',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_student_id` (`student_id`),
    INDEX `idx_violate_type` (`violate_type`),
    INDEX `idx_punish_type` (`punish_type`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='违纪管理表';

-- （5）心理管理表
DROP TABLE IF EXISTS `mental_mgmt`;
CREATE TABLE `mental_mgmt` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `student_id` BIGINT NOT NULL COMMENT '学生 ID',
    `mental_status` VARCHAR(20) NOT NULL COMMENT '心理状态：正常/关注/高危',
    `risk_level` VARCHAR(20) NOT NULL COMMENT '风险等级：低/中/高',
    `evaluate_time` DATETIME DEFAULT NULL COMMENT '评估时间',
    `consult_time` DATETIME DEFAULT NULL COMMENT '咨询预约时间',
    `intervene_time` DATETIME DEFAULT NULL COMMENT '干预时间',
    `intervene_content` TEXT DEFAULT NULL COMMENT '干预内容',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：待评估/咨询中/已干预',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_student_id` (`student_id`),
    INDEX `idx_mental_status` (`mental_status`),
    INDEX `idx_risk_level` (`risk_level`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='心理管理表';

-- （6）行为管理表
DROP TABLE IF EXISTS `behavior_mgmt`;
CREATE TABLE `behavior_mgmt` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `student_id` BIGINT NOT NULL COMMENT '学生 ID',
    `leave_type` VARCHAR(20) NOT NULL COMMENT '请假类型：事假/病假/其他',
    `start_time` DATETIME NOT NULL COMMENT '请假开始时间',
    `end_time` DATETIME NOT NULL COMMENT '请假结束时间',
    `leave_reason` VARCHAR(200) DEFAULT NULL COMMENT '请假原因',
    `audit_level` VARCHAR(20) DEFAULT NULL COMMENT '审批级别：班主任/辅导员',
    `audit_user` VARCHAR(64) DEFAULT NULL COMMENT '审批人',
    `audit_time` DATETIME DEFAULT NULL COMMENT '审批时间',
    `attendance_sync` VARCHAR(20) DEFAULT NULL COMMENT '考勤同步状态：未同步/已同步',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：待审批/已通过/已驳回',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_student_id` (`student_id`),
    INDEX `idx_leave_type` (`leave_type`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='行为管理表';

-- （7）资助系统表
DROP TABLE IF EXISTS `fund_system`;
CREATE TABLE `fund_system` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `student_id` BIGINT NOT NULL COMMENT '学生 ID',
    `fund_type` VARCHAR(20) NOT NULL COMMENT '资助类型：助学金/勤工俭学/其他',
    `apply_amount` DECIMAL(10,2) DEFAULT NULL COMMENT '申请金额',
    `apply_time` DATETIME NOT NULL COMMENT '申请时间',
    `audit_user` VARCHAR(64) DEFAULT NULL COMMENT '审核人',
    `audit_time` DATETIME DEFAULT NULL COMMENT '审核时间',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：待审核/已汇总',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_student_id` (`student_id`),
    INDEX `idx_fund_type` (`fund_type`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资助系统表';

-- （8）社团管理表
DROP TABLE IF EXISTS `club_mgmt`;
CREATE TABLE `club_mgmt` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `club_name` VARCHAR(64) NOT NULL COMMENT '社团名称',
    `club_type` VARCHAR(20) NOT NULL COMMENT '社团类型：文体/学术/志愿/其他',
    `student_id` BIGINT NOT NULL COMMENT '学生 ID',
    `apply_time` DATETIME NOT NULL COMMENT '入团申请时间',
    `audit_user` VARCHAR(64) DEFAULT NULL COMMENT '审核人',
    `audit_time` DATETIME DEFAULT NULL COMMENT '审核时间',
    `archive_time` DATETIME DEFAULT NULL COMMENT '建档时间',
    `venue_apply_status` VARCHAR(20) DEFAULT NULL COMMENT '场馆申请状态：无/待申请/已通过',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：待审核/已通过/已建档',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_club_name` (`club_name`),
    INDEX `idx_club_type` (`club_type`),
    INDEX `idx_student_id` (`student_id`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='社团管理表';

-- （9）奖助勤贷表
DROP TABLE IF EXISTS `aid_work`;
CREATE TABLE `aid_work` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `student_id` BIGINT NOT NULL COMMENT '学生 ID',
    `aid_type` VARCHAR(20) NOT NULL COMMENT '资助类型：奖学金/助学金/助学贷款/勤工俭学',
    `apply_amount` DECIMAL(10,2) DEFAULT NULL COMMENT '申请金额',
    `apply_time` DATETIME NOT NULL COMMENT '申报时间',
    `audit_user` VARCHAR(64) DEFAULT NULL COMMENT '审核人',
    `audit_time` DATETIME DEFAULT NULL COMMENT '审核时间',
    `process_status` VARCHAR(20) DEFAULT NULL COMMENT '流程状态：跟进中/已完成',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：待审核/已通过/已完成',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_student_id` (`student_id`),
    INDEX `idx_aid_type` (`aid_type`),
    INDEX `idx_process_status` (`process_status`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='奖助勤贷表';

-- （10）值班管理表
DROP TABLE IF EXISTS `duty_mgmt`;
CREATE TABLE `duty_mgmt` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `duty_date` DATE NOT NULL COMMENT '值班日期',
    `duty_user` VARCHAR(64) NOT NULL COMMENT '值班人',
    `check_in_time` DATETIME DEFAULT NULL COMMENT '打卡时间',
    `check_in_status` VARCHAR(20) DEFAULT NULL COMMENT '打卡状态：未打卡/已打卡',
    `transfer_reason` VARCHAR(200) DEFAULT NULL COMMENT '调班原因',
    `transfer_user` VARCHAR(64) DEFAULT NULL COMMENT '调班替代人',
    `transfer_status` VARCHAR(20) DEFAULT NULL COMMENT '调班状态：无/待审批/已通过/已驳回',
    `car_reason` VARCHAR(200) DEFAULT NULL COMMENT '出车事由',
    `car_destination` VARCHAR(100) DEFAULT NULL COMMENT '出车目的地',
    `car_status` VARCHAR(20) DEFAULT NULL COMMENT '出车状态：无/待审批/已通过',
    `record_content` TEXT DEFAULT NULL COMMENT '值班记录',
    `record_upload_time` DATETIME DEFAULT NULL COMMENT '记录上传时间',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：待打卡/待调班审批/待出车审批/已完成',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_duty_date` (`duty_date`),
    INDEX `idx_duty_user` (`duty_user`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='值班管理表';





-- ============================================
-- 德育管理模块数据库建表 SQL
-- ============================================

-- （1）指标管理表
DROP TABLE IF EXISTS `target_mgmt`;
CREATE TABLE `target_mgmt` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `target_name` VARCHAR(64) NOT NULL COMMENT '指标名称',
    `total_score` DECIMAL(5,2) NOT NULL COMMENT '指标总分',
    `warn_threshold` DECIMAL(5,2) DEFAULT NULL COMMENT '预警阈值',
    `evaluator_type` VARCHAR(20) NOT NULL COMMENT '评价人类型：教职工/家长/领导',
    `score_type` VARCHAR(20) NOT NULL COMMENT '计分方式：累计赋分/接口赋分',
    `enable_time` DATETIME DEFAULT NULL COMMENT '启用时间',
    `disable_time` DATETIME DEFAULT NULL COMMENT '停用时间',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：未启用/已启用',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='指标管理表';

-- （2）评比管理表
DROP TABLE IF EXISTS `compare_mgmt`;
CREATE TABLE `compare_mgmt` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `class_name` VARCHAR(64) NOT NULL COMMENT '班级',
    `cycle` VARCHAR(20) NOT NULL COMMENT '评比周期：周/月/学期',
    `total_score` DECIMAL(5,2) NOT NULL COMMENT '总得分',
    `rank` INT DEFAULT NULL COMMENT '排名',
    `award_name` VARCHAR(64) DEFAULT NULL COMMENT '授予称号',
    `award_time` DATETIME DEFAULT NULL COMMENT '授予时间',
    `score_user` VARCHAR(64) DEFAULT NULL COMMENT '打分人',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：打分中/已汇总',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_class_name` (`class_name`),
    INDEX `idx_cycle` (`cycle`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评比管理表';

-- （3）德育活动表
DROP TABLE IF EXISTS `moral_activity`;
CREATE TABLE `moral_activity` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `activity_name` VARCHAR(100) NOT NULL COMMENT '活动名称',
    `activity_type` VARCHAR(20) NOT NULL COMMENT '活动类型：党团活动/志愿活动/其他',
    `host_dept` BIGINT NOT NULL COMMENT '主办部门',
    `start_time` DATETIME NOT NULL COMMENT '开始时间',
    `end_time` DATETIME NOT NULL COMMENT '结束时间',
    `join_num` INT DEFAULT NULL COMMENT '参与人数',
    `photo` VARCHAR(255) DEFAULT NULL COMMENT '活动照片地址',
    `content` TEXT DEFAULT NULL COMMENT '活动详情',
    `publish_time` DATETIME DEFAULT NULL COMMENT '发布时间',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：未发布/进行中/已结束',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_activity_type` (`activity_type`),
    INDEX `idx_host_dept` (`host_dept`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='德育活动表';

-- （4）德育资源表
DROP TABLE IF EXISTS `moral_resource`;
CREATE TABLE `moral_resource` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `resource_name` VARCHAR(100) NOT NULL COMMENT '资源名称',
    `resource_type` VARCHAR(20) NOT NULL COMMENT '资源类型：课程/图书/专题包',
    `resource_url` VARCHAR(255) DEFAULT NULL COMMENT '资源地址',
    `learn_num` INT DEFAULT NULL COMMENT '学习人数',
    `learn_rate` DECIMAL(5,2) DEFAULT NULL COMMENT '学习完成率',
    `publish_time` DATETIME DEFAULT NULL COMMENT '上架时间',
    `off_time` DATETIME DEFAULT NULL COMMENT '下架时间',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：未上架/已上架',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_resource_type` (`resource_type`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='德育资源表';




-- ============================================
-- 宿舍管理模块数据库建表 SQL
-- ============================================

-- （1）床位管理表
DROP TABLE IF EXISTS `bed_mgmt`;
CREATE TABLE `bed_mgmt` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `building` VARCHAR(32) NOT NULL COMMENT '楼栋',
    `floor` INT NOT NULL COMMENT '楼层',
    `room_num` VARCHAR(32) NOT NULL COMMENT '房间号',
    `bed_num` VARCHAR(10) NOT NULL COMMENT '床位号',
    `student_id` BIGINT DEFAULT NULL COMMENT '学生 ID',
    `assign_time` DATETIME DEFAULT NULL COMMENT '分配时间',
    `adjust_time` DATETIME DEFAULT NULL COMMENT '调整时间',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：未分配/已分配',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_building` (`building`),
    INDEX `idx_floor` (`floor`),
    INDEX `idx_room_num` (`room_num`),
    INDEX `idx_student_id` (`student_id`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='床位管理表';

-- （2）宿舍评比表
DROP TABLE IF EXISTS `dorm_compare`;
CREATE TABLE `dorm_compare` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `dorm_id` BIGINT DEFAULT NULL COMMENT '宿舍 ID',
    `dorm_num` VARCHAR(32) NOT NULL COMMENT '宿舍号',
    `cycle` VARCHAR(20) NOT NULL COMMENT '评比周期：周/月/学期',
    `score` DECIMAL(5,2) NOT NULL COMMENT '得分',
    `rank` INT DEFAULT NULL COMMENT '排名',
    `score_user` VARCHAR(64) DEFAULT NULL COMMENT '打分人',
    `sum_time` DATETIME DEFAULT NULL COMMENT '汇总时间',
    `push_time` DATETIME DEFAULT NULL COMMENT '推送时间',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：打分中/已汇总',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_dorm_id` (`dorm_id`),
    INDEX `idx_dorm_num` (`dorm_num`),
    INDEX `idx_cycle` (`cycle`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='宿舍评比表';

-- （3）宿舍考勤表
DROP TABLE IF EXISTS `dorm_check`;
CREATE TABLE `dorm_check` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `student_id` BIGINT NOT NULL COMMENT '学生 ID',
    `check_time` DATETIME NOT NULL COMMENT '考勤时间',
    `check_status` VARCHAR(20) NOT NULL COMMENT '考勤状态：正常/迟到/未到',
    `abnormal_type` VARCHAR(20) DEFAULT NULL COMMENT '异常类型：无/晚归/未归',
    `repair_time` DATETIME DEFAULT NULL COMMENT '补卡时间',
    `repair_user` VARCHAR(64) DEFAULT NULL COMMENT '补卡人',
    `push_time` DATETIME DEFAULT NULL COMMENT '推送时间',
    `in_rate` DECIMAL(5,2) DEFAULT NULL COMMENT '在寝率',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：正常/异常',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_student_id` (`student_id`),
    INDEX `idx_check_time` (`check_time`),
    INDEX `idx_check_status` (`check_status`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='宿舍考勤表';

-- （4）出入申请表
DROP TABLE IF EXISTS `access_apply`;
CREATE TABLE `access_apply` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `student_id` BIGINT NOT NULL COMMENT '学生 ID',
    `apply_type` VARCHAR(20) NOT NULL COMMENT '申请类型：应急出入/其他',
    `apply_reason` VARCHAR(200) NOT NULL COMMENT '申请原因',
    `apply_time` DATETIME NOT NULL COMMENT '申请时间',
    `audit_user` VARCHAR(64) DEFAULT NULL COMMENT '审核人',
    `audit_time` DATETIME DEFAULT NULL COMMENT '审核时间',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：待审核/已通过',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_student_id` (`student_id`),
    INDEX `idx_apply_type` (`apply_type`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='出入申请表';

-- （5）报修管理表
DROP TABLE IF EXISTS `repair_mgmt`;
CREATE TABLE `repair_mgmt` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `dorm_num` VARCHAR(32) NOT NULL COMMENT '宿舍号',
    `repair_type` VARCHAR(20) NOT NULL COMMENT '报修类型：水电/家具/其他',
    `apply_time` DATETIME NOT NULL COMMENT '申请时间',
    `dispatch_user` VARCHAR(64) DEFAULT NULL COMMENT '派单人',
    `dispatch_time` DATETIME DEFAULT NULL COMMENT '派单时间',
    `repair_user` VARCHAR(64) DEFAULT NULL COMMENT '维修人',
    `feedback_content` TEXT DEFAULT NULL COMMENT '维修反馈',
    `feedback_time` DATETIME DEFAULT NULL COMMENT '反馈时间',
    `check_user` VARCHAR(64) DEFAULT NULL COMMENT '验收人',
    `check_time` DATETIME DEFAULT NULL COMMENT '验收时间',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：待派单/维修中/已维修',
    `check_status` VARCHAR(20) DEFAULT NULL COMMENT '验收状态：未验收/已验收',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_dorm_num` (`dorm_num`),
    INDEX `idx_repair_type` (`repair_type`),
    INDEX `idx_status` (`status`),
    INDEX `idx_check_status` (`check_status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='报修管理表';

-- （6）留宿管理表
DROP TABLE IF EXISTS `stay_mgmt`;
CREATE TABLE `stay_mgmt` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `student_id` BIGINT NOT NULL COMMENT '学生 ID',
    `stay_date` DATE NOT NULL COMMENT '留宿日期',
    `stay_reason` VARCHAR(200) DEFAULT NULL COMMENT '留宿原因',
    `apply_time` DATETIME NOT NULL COMMENT '申请时间',
    `parent_confirm_time` DATETIME DEFAULT NULL COMMENT '家长确认时间',
    `audit_user` VARCHAR(64) DEFAULT NULL COMMENT '审核人',
    `audit_time` DATETIME DEFAULT NULL COMMENT '审核时间',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：待确认/待审核/已通过',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_student_id` (`student_id`),
    INDEX `idx_stay_date` (`stay_date`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='留宿管理表';


-- ============================================
-- 就医管理、家校互通、招生管理模块数据库建表 SQL
-- ============================================

-- ====================
-- 四、就医管理模块
-- ====================

-- （1）就诊管理表
DROP TABLE IF EXISTS `treat_mgmt`;
CREATE TABLE `treat_mgmt` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `student_id` BIGINT NOT NULL COMMENT '学生 ID',
    `treat_type` VARCHAR(20) NOT NULL COMMENT '就诊类型：门诊/急诊/其他',
    `symptom` TEXT DEFAULT NULL COMMENT '症状描述',
    `register_time` DATETIME NOT NULL COMMENT '就诊登记时间',
    `treat_content` TEXT DEFAULT NULL COMMENT '就诊内容',
    `apply_time` DATETIME DEFAULT NULL COMMENT '预约时间',
    `audit_user` VARCHAR(64) DEFAULT NULL COMMENT '审核人',
    `audit_time` DATETIME DEFAULT NULL COMMENT '审核时间',
    `feedback_time` DATETIME DEFAULT NULL COMMENT '家长反馈时间',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：待审核/已就诊',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_student_id` (`student_id`),
    INDEX `idx_treat_type` (`treat_type`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='就诊管理表';


-- ====================
-- 五、家校互通模块
-- ====================

-- （1）沟通管理表
DROP TABLE IF EXISTS `communicate_mgmt`;
CREATE TABLE `communicate_mgmt` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `title` VARCHAR(100) NOT NULL COMMENT '消息标题',
    `content` TEXT NOT NULL COMMENT '消息内容',
    `send_user` VARCHAR(64) DEFAULT NULL COMMENT '发布人',
    `send_time` DATETIME DEFAULT NULL COMMENT '发布时间',
    `reply_content` TEXT DEFAULT NULL COMMENT '家长反馈内容',
    `reply_time` DATETIME DEFAULT NULL COMMENT '反馈时间',
    `interact_rate` DECIMAL(5,2) DEFAULT NULL COMMENT '互动率',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：未发布/已发布',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_status` (`status`),
    INDEX `idx_send_time` (`send_time`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='沟通管理表';


-- ====================
-- 六、招生管理模块
-- ====================

-- （1）报名管理表
DROP TABLE IF EXISTS `register_mgmt`;
CREATE TABLE `register_mgmt` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `student_name` VARCHAR(64) NOT NULL COMMENT '学生姓名',
    `id_card` VARCHAR(18) NOT NULL COMMENT '身份证号',
    `phone` VARCHAR(20) DEFAULT NULL COMMENT '联系电话',
    `major` VARCHAR(64) NOT NULL COMMENT '意向专业',
    `apply_time` DATETIME NOT NULL COMMENT '报名时间',
    `audit_user` VARCHAR(64) DEFAULT NULL COMMENT '审核人',
    `audit_time` DATETIME DEFAULT NULL COMMENT '审核时间',
    `confirm_time` DATETIME DEFAULT NULL COMMENT '录取确认时间',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：待审核/已录取',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_id_card` (`id_card`),
    INDEX `idx_major` (`major`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='报名管理表';

-- （2）分班管理表
DROP TABLE IF EXISTS `class_assign`;
CREATE TABLE `class_assign` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `rule_content` TEXT NOT NULL COMMENT '分班规则',
    `student_num` INT DEFAULT NULL COMMENT '分班学生数',
    `assign_time` DATETIME DEFAULT NULL COMMENT '分班时间',
    `confirm_user` VARCHAR(64) DEFAULT NULL COMMENT '确认人',
    `confirm_time` DATETIME DEFAULT NULL COMMENT '确认时间',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：未分班/已分班',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分班管理表';

-- （3）报到管理表
DROP TABLE IF EXISTS `check_in`;
CREATE TABLE `check_in` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `student_id` BIGINT NOT NULL COMMENT '学生 ID',
    `exam_score` DECIMAL(5,1) DEFAULT NULL COMMENT '中考成绩',
    `补充信息` TEXT DEFAULT NULL COMMENT '补充信息',
    `confirm_time` DATETIME DEFAULT NULL COMMENT '报到确认时间',
    `audit_user` VARCHAR(64) DEFAULT NULL COMMENT '审核人',
    `audit_time` DATETIME DEFAULT NULL COMMENT '审核时间',
    `account_create_time` DATETIME DEFAULT NULL COMMENT '账号创建时间',
    `account_status` VARCHAR(20) DEFAULT NULL COMMENT '账号状态：未创建/已创建',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：待确认/待审核/已报到',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_student_id` (`student_id`),
    INDEX `idx_account_status` (`account_status`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='报到管理表';

-- （4）宿舍分配表
DROP TABLE IF EXISTS `dorm_assign`;
CREATE TABLE `dorm_assign` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `student_id` BIGINT NOT NULL COMMENT '学生 ID',
    `dorm_num` VARCHAR(32) DEFAULT NULL COMMENT '宿舍号',
    `bed_id` BIGINT DEFAULT NULL COMMENT '床位 ID',
    `rule_content` TEXT DEFAULT NULL COMMENT '分配规则',
    `assign_time` DATETIME DEFAULT NULL COMMENT '分配时间',
    `adjust_time` DATETIME DEFAULT NULL COMMENT '调整时间',
    `finish_rate` DECIMAL(5,2) DEFAULT NULL COMMENT '分配完成率',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：未分配/已分配',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_student_id` (`student_id`),
    INDEX `idx_dorm_num` (`dorm_num`),
    INDEX `idx_bed_id` (`bed_id`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='宿舍分配表';

-- （5）宣传管理表
DROP TABLE IF EXISTS `promote_mgmt`;
CREATE TABLE `promote_mgmt` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `task_name` VARCHAR(100) NOT NULL COMMENT '宣传任务名称',
    `site` VARCHAR(64) NOT NULL COMMENT '宣传站点',
    `promote_num` INT DEFAULT NULL COMMENT '宣传人数',
    `intent_num` INT DEFAULT NULL COMMENT '意向学生数',
    `execute_user` VARCHAR(64) DEFAULT NULL COMMENT '执行人',
    `execute_time` DATETIME DEFAULT NULL COMMENT '执行时间',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：未执行/已执行',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_site` (`site`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='宣传管理表';

-- （6）迎新推送表
DROP TABLE IF EXISTS `new_push`;
CREATE TABLE `new_push` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `task_name` VARCHAR(100) NOT NULL COMMENT '推送任务名称',
    `push_content` TEXT NOT NULL COMMENT '推送内容',
    `push_num` INT DEFAULT NULL COMMENT '推送人数',
    `push_time` DATETIME DEFAULT NULL COMMENT '推送时间',
    `finish_rate` DECIMAL(5,2) DEFAULT NULL COMMENT '推送完成率',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：未推送/已推送',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_status` (`status`),
    INDEX `idx_push_time` (`push_time`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='迎新推送表';



-- ============================================
-- 就业管理、离校管理模块数据库建表 SQL
-- ============================================

-- ====================
-- 七、就业管理模块
-- ====================

-- （1）校企合作表
DROP TABLE IF EXISTS `coop_enterprise`;
CREATE TABLE `coop_enterprise` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `enterprise_name` VARCHAR(100) NOT NULL COMMENT '企业名称',
    `enterprise_type` VARCHAR(20) NOT NULL COMMENT '企业类型：国企/民企/外企',
    `dept_id` BIGINT NOT NULL COMMENT '负责系部',
    `contact_user` VARCHAR(64) DEFAULT NULL COMMENT '联系人',
    `contact_phone` VARCHAR(20) DEFAULT NULL COMMENT '联系电话',
    `coop_start_time` DATETIME DEFAULT NULL COMMENT '合作开始时间',
    `coop_end_time` DATETIME DEFAULT NULL COMMENT '合作结束时间',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：合作中/已结束',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_enterprise_name` (`enterprise_name`),
    INDEX `idx_enterprise_type` (`enterprise_type`),
    INDEX `idx_dept_id` (`dept_id`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='校企合作表';

-- （2）升学管理表
DROP TABLE IF EXISTS `study_up`;
CREATE TABLE `study_up` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `student_id` BIGINT NOT NULL COMMENT '学生 ID',
    `school_name` VARCHAR(100) NOT NULL COMMENT '目标院校名称',
    `school_type` VARCHAR(20) NOT NULL COMMENT '院校类型：公办/民办',
    `major` VARCHAR(64) DEFAULT NULL COMMENT '意向专业',
    `plan_content` TEXT DEFAULT NULL COMMENT '升学规划内容',
    `plan_time` DATETIME DEFAULT NULL COMMENT '规划时间',
    `record_time` DATETIME DEFAULT NULL COMMENT '跟踪记录时间',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：待规划/已规划',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_student_id` (`student_id`),
    INDEX `idx_school_name` (`school_name`),
    INDEX `idx_school_type` (`school_type`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='升学管理表';


-- ====================
-- 八、离校管理模块
-- ====================

-- （1）离校办理表
DROP TABLE IF EXISTS `leave_handle`;
CREATE TABLE `leave_handle` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `student_id` BIGINT NOT NULL COMMENT '学生 ID',
    `leave_time` DATETIME NOT NULL COMMENT '离校时间',
    `leave_address` VARCHAR(200) DEFAULT NULL COMMENT '离校去处',
    `parent_confirm_time` DATETIME DEFAULT NULL COMMENT '家长确认时间',
    `handle_user` VARCHAR(64) DEFAULT NULL COMMENT '办理人',
    `handle_time` DATETIME DEFAULT NULL COMMENT '办理时间',
    `checkout_time` DATETIME DEFAULT NULL COMMENT '退宿时间',
    `checkout_status` VARCHAR(20) DEFAULT NULL COMMENT '退宿状态：未退宿/已退宿',
    `finish_rate` DECIMAL(5,2) DEFAULT NULL COMMENT '办理完成率',
    `status` VARCHAR(20) NOT NULL COMMENT '状态：待确认/待办理/已离校',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `reserve1` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 1',
    `reserve2` VARCHAR(100) DEFAULT NULL COMMENT '备用字段 2',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建人账号/姓名',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新人账号/姓名',
    `deleted` BIT(1) DEFAULT 0 COMMENT '删除标识 0-未删除 1-已删除',
    `tenant_id` BIGINT DEFAULT 1 COMMENT '租户 ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_student_id` (`student_id`),
    INDEX `idx_leave_time` (`leave_time`),
    INDEX `idx_checkout_status` (`checkout_status`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='离校办理表';
