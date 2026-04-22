-- ============================================
-- 学生管理服务系统 - 芋道字典初始化SQL
-- 生成时间：2026-04-12
-- 说明：包含行为管理、资助系统、社团管理等模块的字典数据
-- ============================================

-- ==================== 零、清理已存在的字典数据 ====================

-- 删除所有相关的字典数据（必须先删除字典数据，再删除字典类型）
DELETE FROM `system_dict_data` WHERE `dict_type` IN (
                                                     'work_home_record_type',
                                                     'assess_mgmt_cycle',
                                                     'student_info_education_level',
                                                     'student_info_study_form',
                                                     'student_info_student_type',
                                                     'student_info_status',
                                                     'honor_mgmt_honor_type',
                                                     'honor_mgmt_status',
                                                     'assess_mgmt_assess_type',
                                                     'assess_mgmt_status',
                                                     'violate_mgmt_violate_type',
                                                     'violate_mgmt_punish_type',
                                                     'violate_mgmt_status',
                                                     'mental_mgmt_mental_status',
                                                     'mental_mgmt_risk_level',
                                                     'mental_mgmt_status',
                                                     'behavior_mgmt_leave_type',
                                                     'behavior_mgmt_audit_level',
                                                     'behavior_mgmt_attendance_sync',
                                                     'behavior_mgmt_status',
                                                     'fund_system_fund_type',
                                                     'fund_system_status',
                                                     'club_mgmt_club_type',
                                                     'club_mgmt_venue_apply_status',
                                                     'club_mgmt_status',
                                                     'aid_work_aid_type',
                                                     'aid_work_process_status',
                                                     'aid_work_status',
                                                     'dorm_check_check_status',
                                                     'dorm_check_status',
                                                     'dorm_check_abnormal_type',
                                                     'access_apply_apply_type',
                                                     'access_apply_status',
                                                     'repair_mgmt_repair_type',
                                                     'repair_mgmt_status',
                                                     'repair_mgmt_check_status',
                                                     'stay_mgmt_status',
                                                     'treat_mgmt_treat_type',
                                                     'treat_mgmt_status',
                                                     'register_mgmt_status',
                                                     'class_assign_status',
                                                     'check_in_account_status',
                                                     'check_in_status',
                                                     'new_push_status',
                                                     'coop_enterprise_enterprise_type',
                                                     'coop_enterprise_status',
                                                     'study_up_school_type',
                                                     'study_up_status',
                                                     'leave_handle_checkout_status',
                                                     'leave_handle_status',
                                                     'report_time_scale',
                                                     'dorm_compare_cycle',
                                                     'moral_activity_activity_type',
                                                     'moral_activity_status',
                                                     'promote_mgmt_status',
                                                     'target_mgmt_evaluator_type',
                                                     'dorm_assign_status',
                                                     'duty_mgmt_check_in_status',
                                                     'duty_mgmt_status',
                                                     'moral_activity_type',
                                                     'target_mgmt_score_type',
                                                     'target_mgmt_status',
                                                     'duty_mgmt_transfer_status',
                                                     'duty_mgmt_car_status',
                                                     'moral_resource_status'
    );

-- 删除所有相关的字典类型
DELETE FROM `system_dict_type` WHERE `type` IN (
                                                'work_home_record_type',
                                                'assess_mgmt_cycle',
                                                'student_info_education_level',
                                                'student_info_study_form',
                                                'student_info_student_type',
                                                'student_info_status',
                                                'honor_mgmt_honor_type',
                                                'honor_mgmt_status',
                                                'assess_mgmt_assess_type',
                                                'assess_mgmt_status',
                                                'violate_mgmt_violate_type',
                                                'violate_mgmt_punish_type',
                                                'violate_mgmt_status',
                                                'mental_mgmt_mental_status',
                                                'mental_mgmt_risk_level',
                                                'mental_mgmt_status',
                                                'behavior_mgmt_leave_type',
                                                'behavior_mgmt_audit_level',
                                                'behavior_mgmt_attendance_sync',
                                                'behavior_mgmt_status',
                                                'fund_system_fund_type',
                                                'fund_system_status',
                                                'club_mgmt_club_type',
                                                'club_mgmt_venue_apply_status',
                                                'club_mgmt_status',
                                                'aid_work_aid_type',
                                                'aid_work_process_status',
                                                'aid_work_status',
                                                'dorm_check_check_status',
                                                'dorm_check_status',
                                                'dorm_check_abnormal_type',
                                                'access_apply_apply_type',
                                                'access_apply_status',
                                                'repair_mgmt_repair_type',
                                                'repair_mgmt_status',
                                                'repair_mgmt_check_status',
                                                'stay_mgmt_status',
                                                'treat_mgmt_treat_type',
                                                'treat_mgmt_status',
                                                'register_mgmt_status',
                                                'class_assign_status',
                                                'check_in_account_status',
                                                'check_in_status',
                                                'new_push_status',
                                                'coop_enterprise_enterprise_type',
                                                'coop_enterprise_status',
                                                'study_up_school_type',
                                                'study_up_status',
                                                'leave_handle_checkout_status',
                                                'leave_handle_status',
                                                'report_time_scale',
                                                'dorm_compare_cycle',
                                                'moral_activity_activity_type',
                                                'moral_activity_status',
                                                'promote_mgmt_status',
                                                'target_mgmt_evaluator_type',
                                                'dorm_assign_status',
                                                'duty_mgmt_check_in_status',
                                                'duty_mgmt_status',
                                                'moral_activity_type',
                                                'target_mgmt_score_type',
                                                'target_mgmt_status',
                                                'duty_mgmt_transfer_status',
                                                'duty_mgmt_car_status',
                                                'moral_resource_status'
    );
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


-- 29. 宿舍考勤-考勤状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('宿舍考勤考勤状态', 'dorm_check_check_status', 0, '宿舍考勤考勤状态', 'admin', NOW(), 'admin', NOW(), b'0');


-- 30. 宿舍考勤-状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('宿舍考勤状态', 'dorm_check_status', 0, '宿舍考勤状态', 'admin', NOW(), 'admin', NOW(), b'0');


-- 31. 宿舍考勤-异常类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('宿舍考勤异常类型', 'dorm_check_abnormal_type', 0, '宿舍考勤异常类型', 'admin', NOW(), 'admin', NOW(), b'0');


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

-- 29. 奖助勤贷状态数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '正常', '0','dorm_check_check_status', 0, 'warning', '', '等待审核', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '迟到', '1', 'dorm_check_check_status', 0, 'success', '', '审核通过', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '未到', '2', 'dorm_check_check_status', 0, 'primary', '', '流程已完成', 'admin', NOW(), 'admin', NOW(), b'0');


-- 30. 宿舍考勤-状态
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
(1, '正常', '0', 'dorm_check_status', 0, 'success', '', '正常', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '异常', '1', 'dorm_check_status', 0, 'warning', '', '异常', 'admin', NOW(), 'admin', NOW(), b'0');

-- 31. 宿舍考勤-异常类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('宿舍考勤异常类型', 'dorm_check_abnormal_type', 0, '宿舍考勤异常类型', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
(1, '无', '0', 'dorm_check_abnormal_type', 0, 'success', '', '正常', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '晚归', '1', 'dorm_check_abnormal_type', 0, 'warning', '', '异常', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '未归', '2', 'dorm_check_abnormal_type', 0, 'warning', '', '异常', 'admin', NOW(), 'admin', NOW(), b'0');

-- 31. 宿舍考勤-异常类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('宿舍考勤异常类型', 'dorm_check_abnormal_type', 0, '宿舍考勤异常类型', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '无', '0', 'dorm_check_abnormal_type', 0, 'success', '', '正常', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '晚归', '1', 'dorm_check_abnormal_type', 0, 'warning', '', '异常', 'admin', NOW(), 'admin', NOW(), b'0'),
    (3, '未归', '2', 'dorm_check_abnormal_type', 0, 'warning', '', '异常', 'admin', NOW(), 'admin', NOW(), b'0');


-- 32. 出入申请-申请类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('出入申请类型', 'access_apply_apply_type', 0, '学生出入申请的类型分类', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
(1, '应急出入', 'emergency', 'access_apply_apply_type', 0, 'danger', '', '突发紧急情况需要外出', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '其他', 'other', 'access_apply_apply_type', 0, 'info', '', '其他原因申请出入', 'admin', NOW(), 'admin', NOW(), b'0');


-- 32. 出入申请-申请类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('出入申请类型', 'access_apply_apply_type', 0, '学生出入申请的类型分类', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '应急出入', 'emergency', 'access_apply_apply_type', 0, 'danger', '', '突发紧急情况需要外出', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '其他', 'other', 'access_apply_apply_type', 0, 'info', '', '其他原因申请出入', 'admin', NOW(), 'admin', NOW(), b'0');


-- 33. 出入申请-状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('出入申请状态', 'access_apply_status', 0, '学生出入申请的审核状态', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '待审核', 'pending', 'access_apply_status', 0, 'warning', '', '等待管理员审核', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '已通过', 'approved', 'access_apply_status', 0, 'success', '', '申请已审核通过', 'admin', NOW(), 'admin', NOW(), b'0');


-- 33. 出入申请-状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('出入申请状态', 'access_apply_status', 0, '学生出入申请的审核状态', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '待审核', 'pending', 'access_apply_status', 0, 'warning', '', '等待管理员审核', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '已通过', 'approved', 'access_apply_status', 0, 'success', '', '申请已审核通过', 'admin', NOW(), 'admin', NOW(), b'0');


-- 34. 报修管理-报修类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('报修类型', 'repair_mgmt_repair_type', 0, '宿舍报修的类型分类', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '水电', 'water_electricity', 'repair_mgmt_repair_type', 0, 'primary', '', '水管、电路等水电设施故障', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '家具', 'furniture', 'repair_mgmt_repair_type', 0, 'success', '', '桌椅、床铺等家具损坏', 'admin', NOW(), 'admin', NOW(), b'0'),
    (3, '其他', 'other', 'repair_mgmt_repair_type', 0, 'info', '', '其他类型的报修问题', 'admin', NOW(), 'admin', NOW(), b'0');


-- 35. 报修管理-状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('报修处理状态', 'repair_mgmt_status', 0, '报修工单的处理流程状态', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '待派单', 'pending_dispatch', 'repair_mgmt_status', 0, 'warning', '', '等待管理员派单', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '维修中', 'repairing', 'repair_mgmt_status', 0, 'primary', '', '维修人员正在处理', 'admin', NOW(), 'admin', NOW(), b'0'),
    (3, '已维修', 'completed', 'repair_mgmt_status', 0, 'success', '', '维修已完成', 'admin', NOW(), 'admin', NOW(), b'0');


-- 36. 报修管理-验收状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('报修验收状态', 'repair_mgmt_check_status', 0, '报修完成后的验收状态', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '未验收', 'unchecked', 'repair_mgmt_check_status', 0, 'warning', '', '维修完成后尚未验收', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '已验收', 'checked', 'repair_mgmt_check_status', 0, 'success', '', '已通过验收确认', 'admin', NOW(), 'admin', NOW(), b'0');


-- 37. 留宿管理-状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('留宿申请状态', 'stay_mgmt_status', 0, '学生留宿申请的审批流程状态', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '待确认', 'pending_confirm', 'stay_mgmt_status', 0, 'warning', '', '等待家长确认', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '待审核', 'pending_audit', 'stay_mgmt_status', 0, 'primary', '', '家长已确认，等待管理员审核', 'admin', NOW(), 'admin', NOW(), b'0'),
    (3, '已通过', 'approved', 'stay_mgmt_status', 0, 'success', '', '申请已审核通过', 'admin', NOW(), 'admin', NOW(), b'0');


-- 38. 就诊管理-就诊类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('就诊类型', 'treat_mgmt_treat_type', 0, '学生就诊的类型分类', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '门诊', 'outpatient', 'treat_mgmt_treat_type', 0, 'primary', '', '普通门诊就诊', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '急诊', 'emergency', 'treat_mgmt_treat_type', 0, 'danger', '', '紧急急诊就医', 'admin', NOW(), 'admin', NOW(), b'0'),
    (3, '其他', 'other', 'treat_mgmt_treat_type', 0, 'info', '', '其他类型的就诊', 'admin', NOW(), 'admin', NOW(), b'0');


-- 39. 报名管理-状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('报名审核状态', 'register_mgmt_status', 0, '学生报名的审核状态', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '待审核', 'pending', 'register_mgmt_status', 0, 'warning', '', '等待管理员审核', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '已录取', 'admitted', 'register_mgmt_status', 0, 'success', '', '已通过审核并录取', 'admin', NOW(), 'admin', NOW(), b'0');


-- 40. 分班管理-状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('分班状态', 'class_assign_status', 0, '学生分班的完成状态', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '未分班', 'unassigned', 'class_assign_status', 0, 'warning', '', '尚未进行分班', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '已分班', 'assigned', 'class_assign_status', 0, 'success', '', '已完成分班', 'admin', NOW(), 'admin', NOW(), b'0');

-- 41. 报到管理-账号状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('报到账号状态', 'check_in_account_status', 0, '学生报到时系统账号的创建状态', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '未创建', 'not_created', 'check_in_account_status', 0, 'warning', '', '系统账号尚未创建', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '已创建', 'created', 'check_in_account_status', 0, 'success', '', '系统账号已创建完成', 'admin', NOW(), 'admin', NOW(), b'0');


-- 42. 报到管理-报到状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('报到流程状态', 'check_in_status', 0, '学生报到的流程状态', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '待确认', 'pending_confirm', 'check_in_status', 0, 'warning', '', '等待学生或家长确认报到信息', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '待审核', 'pending_audit', 'check_in_status', 0, 'primary', '', '信息已确认，等待管理员审核', 'admin', NOW(), 'admin', NOW(), b'0'),
    (3, '已报到', 'checked_in', 'check_in_status', 0, 'success', '', '已完成报到流程', 'admin', NOW(), 'admin', NOW(), b'0');

-- 43. 迎新推送-推送状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('迎新推送状态', 'new_push_status', 0, '迎新消息推送的状态', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '未推送', 'unpushed', 'new_push_status', 0, 'warning', '', '推送任务尚未执行', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '已推送', 'pushed', 'new_push_status', 0, 'success', '', '推送任务已完成', 'admin', NOW(), 'admin', NOW(), b'0');


-- 44. 校企合作-企业类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('合作企业类型', 'coop_enterprise_enterprise_type', 0, '校企合作企业的类型分类', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '国企', 'state_owned', 'coop_enterprise_enterprise_type', 0, 'primary', '', '国有企业', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '民企', 'private', 'coop_enterprise_enterprise_type', 0, 'success', '', '民营企业', 'admin', NOW(), 'admin', NOW(), b'0'),
    (3, '外企', 'foreign', 'coop_enterprise_enterprise_type', 0, 'info', '', '外资企业', 'admin', NOW(), 'admin', NOW(), b'0');

-- 45. 校企合作-状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('合作状态', 'coop_enterprise_status', 0, '校企合作的进行状态', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '合作中', 'cooperating', 'coop_enterprise_status', 0, 'success', '', '当前正在合作中', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '已结束', 'ended', 'coop_enterprise_status', 0, 'info', '', '合作关系已结束', 'admin', NOW(), 'admin', NOW(), b'0');


-- 46. 升学管理-院校类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('升学院校类型', 'study_up_school_type', 0, '学生升学目标院校的类型分类', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '公办', 'public', 'study_up_school_type', 0, 'primary', '', '公办院校', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '民办', 'private', 'study_up_school_type', 0, 'success', '', '民办院校', 'admin', NOW(), 'admin', NOW(), b'0');


-- 47. 升学管理-状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('升学规划状态', 'study_up_status', 0, '学生升学规划的完成状态', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '待规划', 'pending_plan', 'study_up_status', 0, 'warning', '', '尚未制定升学规划', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '已规划', 'planned', 'study_up_status', 0, 'success', '', '已完成升学规划', 'admin', NOW(), 'admin', NOW(), b'0');


-- 48. 离校办理-退宿状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('离校退宿状态', 'leave_handle_checkout_status', 0, '学生离校时宿舍退宿的完成状态', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '未退宿', 'not_checked_out', 'leave_handle_checkout_status', 0, 'warning', '', '尚未办理退宿手续', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '已退宿', 'checked_out', 'leave_handle_checkout_status', 0, 'success', '', '已完成退宿手续', 'admin', NOW(), 'admin', NOW(), b'0');


-- 49. 离校办理-状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('离校流程状态', 'leave_handle_status', 0, '学生离校办理的完整流程状态', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '待确认', 'pending_confirm', 'leave_handle_status', 0, 'warning', '', '等待家长确认离校信息', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '待办理', 'pending_handle', 'leave_handle_status', 0, 'primary', '', '家长已确认，等待办理离校手续', 'admin', NOW(), 'admin', NOW(), b'0'),
    (3, '已离校', 'left', 'leave_handle_status', 0, 'success', '', '已完成所有离校手续', 'admin', NOW(), 'admin', NOW(), b'0');


-- 50. 报表统计-时间尺度
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('报表时间尺度', 'report_time_scale', 0, '数据统计报表的时间维度分类', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '日', 'day', 'report_time_scale', 0, 'info', '', '按日统计', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '周', 'week', 'report_time_scale', 0, 'primary', '', '按周统计', 'admin', NOW(), 'admin', NOW(), b'0'),
    (3, '月', 'month', 'report_time_scale', 0, 'success', '', '按月统计', 'admin', NOW(), 'admin', NOW(), b'0'),
    (4, '季', 'quarter', 'report_time_scale', 0, 'warning', '', '按季度统计', 'admin', NOW(), 'admin', NOW(), b'0'),
    (5, '半年', 'half_year', 'report_time_scale', 0, 'default', '', '按半年统计', 'admin', NOW(), 'admin', NOW(), b'0'),
    (6, '年', 'year', 'report_time_scale', 0, 'danger', '', '按年度统计', 'admin', NOW(), 'admin', NOW(), b'0');


-- 51. 宿舍评比-评比周期
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('宿舍评比周期', 'dorm_compare_cycle', 0, '宿舍评比的统计周期', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '周', 'week', 'dorm_compare_cycle', 0, 'primary', '', '按周统计评比', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '月', 'month', 'dorm_compare_cycle', 0, 'success', '', '按月统计评比', 'admin', NOW(), 'admin', NOW(), b'0'),
    (3, '学期', 'semester', 'dorm_compare_cycle', 0, 'warning', '', '按学期统计评比', 'admin', NOW(), 'admin', NOW(), b'0');


-- 52. 德育活动-活动类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('德育活动类型', 'moral_activity_activity_type', 0, '德育活动的类型分类', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '党团活动', 'party_league', 'moral_activity_activity_type', 0, 'primary', '', '党组织或团组织活动', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '志愿活动', 'volunteer', 'moral_activity_activity_type', 0, 'success', '', '志愿服务活动', 'admin', NOW(), 'admin', NOW(), b'0'),
    (3, '其他', 'other', 'moral_activity_activity_type', 0, 'info', '', '其他类型的德育活动', 'admin', NOW(), 'admin', NOW(), b'0');


-- 53. 德育活动-状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('德育活动状态', 'moral_activity_status', 0, '德育活动的进行状态', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '未发布', 'unpublished', 'moral_activity_status', 0, 'warning', '', '活动尚未发布', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '进行中', 'ongoing', 'moral_activity_status', 0, 'primary', '', '活动正在进行中', 'admin', NOW(), 'admin', NOW(), b'0'),
    (3, '已结束', 'ended', 'moral_activity_status', 0, 'success', '', '活动已结束', 'admin', NOW(), 'admin', NOW(), b'0');

-- 54. 升学管理-执行状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('升学执行状态', 'promote_mgmt_status', 0, '升学申请的执行状态', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '未执行', 'unexecuted', 'promote_mgmt_status', 0, 'warning', '', '升学申请尚未执行', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '已执行', 'executed', 'promote_mgmt_status', 0, 'success', '', '升学申请已执行完成', 'admin', NOW(), 'admin', NOW(), b'0');


-- 55. 目标管理-评价人类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('目标管理评价人类型', 'target_mgmt_evaluator_type', 0, '目标管理评价的评价人类型分类', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '教职工', 'teacher', 'target_mgmt_evaluator_type', 0, 'primary', '', '学校教职工评价', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '家长', 'parent', 'target_mgmt_evaluator_type', 0, 'success', '', '学生家长评价', 'admin', NOW(), 'admin', NOW(), b'0'),
    (3, '领导', 'leader', 'target_mgmt_evaluator_type', 0, 'warning', '', '学校领导评价', 'admin', NOW(), 'admin', NOW(), b'0');


-- 56. 就诊管理-状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('就诊管理状态', 'treat_mgmt_status', 0, '就诊申请的审核和就诊状态', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '待审核', 'pending', 'treat_mgmt_status', 0, 'warning', '', '等待管理员审核', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '已就诊', 'visited', 'treat_mgmt_status', 0, 'success', '', '已完成就诊', 'admin', NOW(), 'admin', NOW(), b'0');



INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('宿舍分配状态', 'dorm_assign_status', 0, '宿舍分配的完成状态', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
(1, '未分配', 'unassigned', 'dorm_assign_status', 0, 'warning', '', '尚未分配宿舍', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '已分配', 'assigned', 'dorm_assign_status', 0, 'success', '', '已完成宿舍分配', 'admin', NOW(), 'admin', NOW(), b'0');

 INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
 VALUES ('值班打卡状态', 'duty_mgmt_check_in_status', 0, '值班打卡的完成状态', 'admin', NOW(), 'admin', NOW(), b'0');

 INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
 VALUES
 (1, '未打卡', 'not_checked_in', 'duty_mgmt_check_in_status', 0, 'warning', '', '尚未进行打卡', 'admin', NOW(), 'admin', NOW(), b'0'),
 (2, '已打卡', 'checked_in', 'duty_mgmt_check_in_status', 0, 'success', '', '已完成打卡', 'admin', NOW(), 'admin', NOW(), b'0');


INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
 VALUES ('值班管理状态', 'duty_mgmt_status', 0, '值班管理的流程状态', 'admin', NOW(), 'admin', NOW(), b'0');

 INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
 VALUES
 (1, '待打卡', 'pending_checkin', 'duty_mgmt_status', 0, 'warning', '', '等待值班打卡', 'admin', NOW(), 'admin', NOW(), b'0'),
 (2, '待调班审批', 'pending_transfer', 'duty_mgmt_status', 0, 'primary', '', '等待调班审批', 'admin', NOW(), 'admin', NOW(), b'0'),
 (3, '待出车审批', 'pending_car', 'duty_mgmt_status', 0, 'primary', '', '等待出车审批', 'admin', NOW(), 'admin', NOW(), b'0'),
 (4, '已完成', 'completed', 'duty_mgmt_status', 0, 'success', '', '值班任务已完成', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('德育活动类型', 'moral_activity_type', 0, '德育活动的类型分类', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
(1, '党团活动', 'party_league', 'moral_activity_type', 0, 'primary', '', '党组织或团组织活动', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '志愿活动', 'volunteer', 'moral_activity_type', 0, 'success', '', '志愿服务活动', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '其他', 'other', 'moral_activity_type', 0, 'info', '', '其他类型的德育活动', 'admin', NOW(), 'admin', NOW(), b'0');


INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('目标管理评分类型', 'target_mgmt_score_type', 0, '目标管理的评分方式分类', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
(1, '累计赋分', 'cumulative', 'target_mgmt_score_type', 0, 'primary', '', '定量评分', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '接口赋分', 'api', 'target_mgmt_score_type', 0, 'success', '', '定性评分', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('目标管理状态', 'target_mgmt_status', 0, '目标管理评价的发布状态', 'admin', NOW(), 'admin', NOW(), b'0');

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
(1, '停用', 'disable', 'target_mgmt_status', 0, 'warning', '', '停用', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '启用', 'enable', 'target_mgmt_status', 0, 'success', '', '启用', 'admin', NOW(), 'admin', NOW(), b'0');


-- 插入字典类型
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES ('调班状态', 'duty_mgmt_transfer_status', 0, '值班管理调班申请状态', 'admin', NOW(), 'admin', NOW(), 0, 1);


-- 插入字典数据
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES
    (1, '无', 'none', 'duty_mgmt_transfer_status', 0, 'info', '', '未申请调班', 'admin', NOW(), 'admin', NOW(), 0, 1),
    (2, '待审批', 'pending', 'duty_mgmt_transfer_status', 0, 'warning', '', '调班申请待审批', 'admin', NOW(), 'admin', NOW(), 0, 1),
    (3, '已通过', 'approved', 'duty_mgmt_transfer_status', 0, 'success', '', '调班申请已通过', 'admin', NOW(), 'admin', NOW(), 0, 1),
    (4, '已驳回', 'rejected', 'duty_mgmt_transfer_status', 0, 'danger', '', '调班申请已驳回', 'admin', NOW(), 'admin', NOW(), 0, 1);

-- 插入字典类型
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES ('出车状态', 'duty_mgmt_car_status', 0, '值班管理出车申请状态', 'admin', NOW(), 'admin', NOW(), 0, 1);

-- 插入字典数据
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES
    (1, '无   ', 'none', 'duty_mgmt_car_status', 0, 'info', '', '未申请出车', 'admin', NOW(), 'admin', NOW(), 0, 1),
    (2, '待审批', 'pending', 'duty_mgmt_car_status', 0, 'warning', '', '出车申请待审批', 'admin', NOW(), 'admin', NOW(), 0, 1),
    (3, '已通过', 'approved', 'duty_mgmt_car_status', 0, 'success', '', '出车申请已通过', 'admin', NOW(), 'admin', NOW(), 0, 1);

-- ----------------------------
-- 德育资源状态 - 字典类型
-- ----------------------------
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2000, '德育资源状态', 'moral_resource_status', 0, '德育资源的上架状态', 'admin', SYSDATE, '', NULL, '0', NULL);

-- ----------------------------
-- 德育资源状态 - 字典数据
-- ----------------------------
INSERT INTO system_dict_data (id, sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES (2000, 1, '未上架', 'disable', 'moral_resource_status', 0, 'danger', '', '德育资源未上架状态', 'admin', SYSDATE, '', NULL, '0');

INSERT INTO system_dict_data (id, sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES (2001, 2, '已上架', 'enable', 'moral_resource_status', 0, 'success', '', '德育资源已上架状态', 'admin', SYSDATE, '', NULL, '0');
