-- ============================================
-- 学生管理服务系统 - 芋道字典初始化SQL
-- 生成时间：2026-04-12
-- 说明：包含行为管理、资助系统、社团管理等模块的字典数据
-- ============================================

-- ==================== 一、字典类型 (system_dict_type) ====================

-- 1. 学工首页记录类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('学工首页记录类型', 'work_home_record_type', 0, '学工首页动态记录的类型分类', 'admin', NOW(), 'admin', NOW(), b'0');

-- 2. 考评管理周期
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('考评统计周期', 'assess_mgmt_cycle', 0, '班级考评的统计周期', 'admin', NOW(), 'admin', NOW(), b'0');

-- 3. 学生信息-学历层次
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('学生学历层次', 'student_info_education_level', 0, '学生的学历层次分类', 'admin', NOW(), 'admin', NOW(), b'0');

-- 4. 学生信息-学习形式
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('学生学习形式', 'student_info_study_form', 0, '学生的学习形式分类', 'admin', NOW(), 'admin', NOW(), b'0');

-- 5. 学生信息-学生类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('学生类型', 'student_info_student_type', 0, '学生的类型分类', 'admin', NOW(), 'admin', NOW(), b'0');

-- 6. 学生信息-学籍状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('学生学籍状态', 'student_info_status', 0, '学生的学籍状态', 'admin', NOW(), 'admin', NOW(), b'0');

-- 7. 荣誉管理-荣誉类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('荣誉类型', 'honor_mgmt_honor_type', 0, '学生荣誉的类型分类', 'admin', NOW(), 'admin', NOW(), b'0');

-- 8. 荣誉管理-状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('荣誉审核状态', 'honor_mgmt_status', 0, '荣誉申请的审核状态', 'admin', NOW(), 'admin', NOW(), b'0');

-- 9. 考评管理-考评类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('考评类型', 'assess_mgmt_assess_type', 0, '班级考评的类型分类', 'admin', NOW(), 'admin', NOW(), b'0');

-- 10. 考评管理-状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('考评发布状态', 'assess_mgmt_status', 0, '考评记录的发布状态', 'admin', NOW(), 'admin', NOW(), b'0');

-- 11. 违纪管理-违纪类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('违纪类型', 'violate_mgmt_violate_type', 0, '学生违纪的类型分类', 'admin', NOW(), 'admin', NOW(), b'0');

-- 12. 违纪管理-处分类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('处分类型', 'violate_mgmt_punish_type', 0, '学生处分的类型分类', 'admin', NOW(), 'admin', NOW(), b'0');

-- 13. 违纪管理-状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('违纪处理状态', 'violate_mgmt_status', 0, '违纪记录的处理状态', 'admin', NOW(), 'admin', NOW(), b'0');

-- 14. 心理管理-心理状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('心理状态', 'mental_mgmt_mental_status', 0, '学生心理健康状态', 'admin', NOW(), 'admin', NOW(), b'0');

-- 15. 心理管理-风险等级
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('心理风险等级', 'mental_mgmt_risk_level', 0, '学生心理风险等级分类', 'admin', NOW(), 'admin', NOW(), b'0');

-- 16. 心理管理-状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('心理干预状态', 'mental_mgmt_status', 0, '心理档案的干预流程状态', 'admin', NOW(), 'admin', NOW(), b'0');

-- 17. 行为管理-请假类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('请假类型', 'behavior_mgmt_leave_type', 0, '学生请假的类型分类', 'admin', NOW(), 'admin', NOW(), b'0');

-- 18. 行为管理-审批级别
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('请假审批级别', 'behavior_mgmt_audit_level', 0, '请假申请的审批级别', 'admin', NOW(), 'admin', NOW(), b'0');

-- 19. 行为管理-考勤同步状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('考勤同步状态', 'behavior_mgmt_attendance_sync', 0, '请假记录与考勤系统的同步状态', 'admin', NOW(), 'admin', NOW(), b'0');

-- 20. 行为管理-状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('请假审批状态', 'behavior_mgmt_status', 0, '请假申请的审批状态', 'admin', NOW(), 'admin', NOW(), b'0');

-- 21. 资助系统-资助类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('资助类型', 'fund_system_fund_type', 0, '学生资助的类型分类', 'admin', NOW(), 'admin', NOW(), b'0');

-- 22. 资助系统-状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('资助审核状态', 'fund_system_status', 0, '资助申请的审核状态', 'admin', NOW(), 'admin', NOW(), b'0');

-- 23. 社团管理-社团类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('社团类型', 'club_mgmt_club_type', 0, '学生社团的类型分类', 'admin', NOW(), 'admin', NOW(), b'0');

-- 24. 社团管理-场馆申请状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('场馆申请状态', 'club_mgmt_venue_apply_status', 0, '社团场馆使用的申请状态', 'admin', NOW(), 'admin', NOW(), b'0');

-- 25. 社团管理-状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('社团入团状态', 'club_mgmt_status', 0, '社团入团申请的审核状态', 'admin', NOW(), 'admin', NOW(), b'0');

-- 26. 奖助勤贷-资助类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('奖助勤贷类型', 'aid_work_aid_type', 0, '奖助勤贷的资助类型分类', 'admin', NOW(), 'admin', NOW(), b'0');

-- 27. 奖助勤贷-流程状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('奖助勤贷流程状态', 'aid_work_process_status', 0, '奖助勤贷的流程跟进状态', 'admin', NOW(), 'admin', NOW(), b'0');

-- 28. 奖助勤贷-状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) 
VALUES ('奖助勤贷审核状态', 'aid_work_status', 0, '奖助勤贷申请的审核状态', 'admin', NOW(), 'admin', NOW(), b'0');


-- ==================== 二、字典数据 (system_dict_data) ====================

-- 1. 学工首页记录类型数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '荣誉', '1', 'work_home_record_type', 0, 'success', '', '荣誉记录', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '考评', '2', 'work_home_record_type', 0, 'primary', '', '考评记录', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '违纪', '3', 'work_home_record_type', 0, 'danger', '', '违纪记录', 'admin', NOW(), 'admin', NOW(), b'0'),
(4, '行为', '4', 'work_home_record_type', 0, 'warning', '', '行为记录', 'admin', NOW(), 'admin', NOW(), b'0'),
(5, '心理', '5', 'work_home_record_type', 0, 'info', '', '心理记录', 'admin', NOW(), 'admin', NOW(), b'0'),
(6, '资助', '6', 'work_home_record_type', 0, 'default', '', '资助记录', 'admin', NOW(), 'admin', NOW(), b'0');

-- 2. 考评周期数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '周', 'week', 'assess_mgmt_cycle', 0, 'primary', '', '按周统计', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '月', 'month', 'assess_mgmt_cycle', 0, 'success', '', '按月统计', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '学期', 'semester', 'assess_mgmt_cycle', 0, 'warning', '', '按学期统计', 'admin', NOW(), 'admin', NOW(), b'0');

-- 3. 学历层次数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '中专', '1', 'student_info_education_level', 0, 'info', '', '中等专业学校', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '大专', '2', 'student_info_education_level', 0, 'primary', '', '大学专科', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '本科', '3', 'student_info_education_level', 0, 'success', '', '大学本科', 'admin', NOW(), 'admin', NOW(), b'0'),
(4, '研究生', '4', 'student_info_education_level', 0, 'warning', '', '硕士研究生及以上', 'admin', NOW(), 'admin', NOW(), b'0');

-- 4. 学习形式数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '全日制', '1', 'student_info_study_form', 0, 'success', '', '全日制学习', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '非全日制', '2', 'student_info_study_form', 0, 'warning', '', '非全日制学习', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '函授', '3', 'student_info_study_form', 0, 'info', '', '函授学习', 'admin', NOW(), 'admin', NOW(), b'0');

-- 5. 学生类型数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '普通生', '1', 'student_info_student_type', 0, 'primary', '', '普通学生', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '特长生', '2', 'student_info_student_type', 0, 'success', '', '特长学生', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '转学生', '3', 'student_info_student_type', 0, 'warning', '', '转入学生', 'admin', NOW(), 'admin', NOW(), b'0');

-- 6. 学籍状态数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '在籍', '1', 'student_info_status', 0, 'success', '', '正常在籍', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '休学', '2', 'student_info_status', 0, 'warning', '', '休学状态', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '退学', '3', 'student_info_status', 0, 'danger', '', '已退学', 'admin', NOW(), 'admin', NOW(), b'0'),
(4, '异动', '4', 'student_info_status', 0, 'info', '', '学籍异动', 'admin', NOW(), 'admin', NOW(), b'0');

-- 7. 荣誉类型数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '优秀学生', '1', 'honor_mgmt_honor_type', 0, 'success', '', '优秀学生称号', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '奖学金', '2', 'honor_mgmt_honor_type', 0, 'primary', '', '各类奖学金', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '竞赛获奖', '3', 'honor_mgmt_honor_type', 0, 'warning', '', '竞赛获奖', 'admin', NOW(), 'admin', NOW(), b'0'),
(4, '其他', '4', 'honor_mgmt_honor_type', 0, 'info', '', '其他荣誉', 'admin', NOW(), 'admin', NOW(), b'0');

-- 8. 荣誉状态数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '待审核', '0', 'honor_mgmt_status', 0, 'warning', '', '等待审核', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '已通过', '1', 'honor_mgmt_status', 0, 'success', '', '审核通过', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '已推送', '2', 'honor_mgmt_status', 0, 'primary', '', '已推送通知', 'admin', NOW(), 'admin', NOW(), b'0'),
(4, '不通过', '3', 'honor_mgmt_status', 0, 'danger', '', '审核不通过', 'admin', NOW(), 'admin', NOW(), b'0');

-- 9. 考评类型数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '教室卫生', 'class_clean', 'assess_mgmt_assess_type', 0, 'success', '', '教室卫生评比', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '早操', 'morning_exercise', 'assess_mgmt_assess_type', 0, 'primary', '', '早操评比', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '文明班级', 'civilized_class', 'assess_mgmt_assess_type', 0, 'warning', '', '文明班级评比', 'admin', NOW(), 'admin', NOW(), b'0'),
(4, '黑板报', 'blackboard', 'assess_mgmt_assess_type', 0, 'info', '', '黑板报评比', 'admin', NOW(), 'admin', NOW(), b'0');

-- 10. 考评状态数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '未发布', 'un_publish', 'assess_mgmt_status', 0, 'warning', '', '考评未发布', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '已发布', 'published', 'assess_mgmt_status', 0, 'success', '', '考评已发布', 'admin', NOW(), 'admin', NOW(), b'0');

-- 11. 违纪类型数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '仪容仪表', 'appearance', 'violate_mgmt_violate_type', 0, 'info', '', '仪容仪表违规', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '行为违规', 'behavior', 'violate_mgmt_violate_type', 0, 'warning', '', '行为规范违规', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '其他', 'other', 'violate_mgmt_violate_type', 0, 'default', '', '其他违纪', 'admin', NOW(), 'admin', NOW(), b'0');

-- 12. 处分类型数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '警告', 'warn', 'violate_mgmt_punish_type', 0, 'warning', '', '警告处分', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '记过', 'demerit', 'violate_mgmt_punish_type', 0, 'danger', '', '记过处分', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '留校察看', 'probation', 'violate_mgmt_punish_type', 0, 'danger', '', '留校察看', 'admin', NOW(), 'admin', NOW(), b'0'),
(4, '开除', 'expulsion', 'violate_mgmt_punish_type', 0, 'danger', '', '开除学籍', 'admin', NOW(), 'admin', NOW(), b'0');

-- 13. 违纪状态数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '待审批', 'pending', 'violate_mgmt_status', 0, 'warning', '', '等待审批', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '已执行', 'executed', 'violate_mgmt_status', 0, 'success', '', '处分已执行', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '已预警', 'warned', 'violate_mgmt_status', 0, 'danger', '', '已触发预警', 'admin', NOW(), 'admin', NOW(), b'0');

-- 14. 心理状态数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '正常', 'normal', 'mental_mgmt_mental_status', 0, 'success', '', '心理状态正常', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '关注', 'focus', 'mental_mgmt_mental_status', 0, 'warning', '', '需要关注', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '高危', 'high_risk', 'mental_mgmt_mental_status', 0, 'danger', '', '高危状态', 'admin', NOW(), 'admin', NOW(), b'0');

-- 15. 心理风险等级数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '低', 'low', 'mental_mgmt_risk_level', 0, 'success', '', '低风险', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '中', 'medium', 'mental_mgmt_risk_level', 0, 'warning', '', '中风险', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '高', 'high', 'mental_mgmt_risk_level', 0, 'danger', '', '高风险', 'admin', NOW(), 'admin', NOW(), b'0');

-- 16. 心理干预状态数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '待评估', 'wait_evaluate', 'mental_mgmt_status', 0, 'info', '', '等待评估', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '咨询中', 'consulting', 'mental_mgmt_status', 0, 'warning', '', '心理咨询中', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '已干预', 'intervened', 'mental_mgmt_status', 0, 'success', '', '已完成干预', 'admin', NOW(), 'admin', NOW(), b'0');

-- 17. 请假类型数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '事假', '1', 'behavior_mgmt_leave_type', 0, 'primary', '', '因私事请假', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '病假', '2', 'behavior_mgmt_leave_type', 0, 'danger', '', '因病请假', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '其他', '3', 'behavior_mgmt_leave_type', 0, 'info', '', '其他原因请假', 'admin', NOW(), 'admin', NOW(), b'0');

-- 18. 审批级别数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '班主任', '1', 'behavior_mgmt_audit_level', 0, 'primary', '', '班主任审批', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '辅导员', '2', 'behavior_mgmt_audit_level', 0, 'success', '', '辅导员审批', 'admin', NOW(), 'admin', NOW(), b'0');

-- 19. 考勤同步状态数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '未同步', '0', 'behavior_mgmt_attendance_sync', 0, 'warning', '', '尚未同步到考勤系统', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '已同步', '1', 'behavior_mgmt_attendance_sync', 0, 'success', '', '已同步到考勤系统', 'admin', NOW(), 'admin', NOW(), b'0');

-- 20. 请假审批状态数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '待审批', '0', 'behavior_mgmt_status', 0, 'warning', '', '等待审批', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '已通过', '1', 'behavior_mgmt_status', 0, 'success', '', '审批通过', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '已驳回', '2', 'behavior_mgmt_status', 0, 'danger', '', '审批驳回', 'admin', NOW(), 'admin', NOW(), b'0');

-- 21. 资助类型数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '助学金', '1', 'fund_system_fund_type', 0, 'primary', '', '国家或学校助学金', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '勤工俭学', '2', 'fund_system_fund_type', 0, 'success', '', '校内勤工俭学岗位', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '其他', '3', 'fund_system_fund_type', 0, 'info', '', '其他资助形式', 'admin', NOW(), 'admin', NOW(), b'0');

-- 22. 资助状态数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '待审核', '0', 'fund_system_status', 0, 'warning', '', '等待审核', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '已汇总', '1', 'fund_system_status', 0, 'success', '', '已汇总上报', 'admin', NOW(), 'admin', NOW(), b'0');

-- 23. 社团类型数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '文体', '1', 'club_mgmt_club_type', 0, 'primary', '', '文化艺术体育类社团', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '学术', '2', 'club_mgmt_club_type', 0, 'success', '', '学术研究类社团', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '志愿', '3', 'club_mgmt_club_type', 0, 'warning', '', '志愿服务类社团', 'admin', NOW(), 'admin', NOW(), b'0'),
(4, '其他', '4', 'club_mgmt_club_type', 0, 'info', '', '其他类型社团', 'admin', NOW(), 'admin', NOW(), b'0');

-- 24. 场馆申请状态数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '无', '0', 'club_mgmt_venue_apply_status', 0, 'info', '', '无需申请场馆', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '待申请', '1', 'club_mgmt_venue_apply_status', 0, 'warning', '', '待提交申请', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '已通过', '2', 'club_mgmt_venue_apply_status', 0, 'success', '', '申请已通过', 'admin', NOW(), 'admin', NOW(), b'0');

-- 25. 社团状态数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '待审核', '0', 'club_mgmt_status', 0, 'warning', '', '等待审核', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '已通过', '1', 'club_mgmt_status', 0, 'success', '', '审核通过', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '已建档', '2', 'club_mgmt_status', 0, 'primary', '', '已建立档案', 'admin', NOW(), 'admin', NOW(), b'0');

-- 26. 奖助勤贷类型数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '奖学金', '1', 'aid_work_aid_type', 0, 'success', '', '各类奖学金', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '助学金', '2', 'aid_work_aid_type', 0, 'primary', '', '国家或学校助学金', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '助学贷款', '3', 'aid_work_aid_type', 0, 'warning', '', '助学贷款', 'admin', NOW(), 'admin', NOW(), b'0'),
(4, '勤工俭学', '4', 'aid_work_aid_type', 0, 'info', '', '勤工俭学', 'admin', NOW(), 'admin', NOW(), b'0');

-- 27. 奖助勤贷流程状态数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '跟进中', '1', 'aid_work_process_status', 0, 'warning', '', '流程跟进中', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '已完成', '2', 'aid_work_process_status', 0, 'success', '', '流程已完成', 'admin', NOW(), 'admin', NOW(), b'0');

-- 28. 奖助勤贷状态数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '待审核', '0', 'aid_work_status', 0, 'warning', '', '等待审核', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '已通过', '1', 'aid_work_status', 0, 'success', '', '审核通过', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '已完成', '2', 'aid_work_status', 0, 'primary', '', '流程已完成', 'admin', NOW(), 'admin', NOW(), b'0');
