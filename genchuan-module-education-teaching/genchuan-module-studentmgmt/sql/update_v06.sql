
-- ============================================
-- 学生管理服务系统 - 字典数据值更新SQL（完整版）
-- 说明：将数据库中存储的字典label值（中文）更新为对应的value值
-- 生成时间：2026-04-15
-- 依据：update_v03-insert-dict-data.sql
-- ============================================

-- ==================== 1. 学生信息表 (student_info) ====================
-- 学历层次：中专->1, 大专->2, 本科->3, 研究生->4
UPDATE `student_info` SET `education_level` = '1' WHERE `education_level` = '中专' AND `deleted` = 0;
UPDATE `student_info` SET `education_level` = '2' WHERE `education_level` = '大专' AND `deleted` = 0;
UPDATE `student_info` SET `education_level` = '3' WHERE `education_level` = '本科' AND `deleted` = 0;
UPDATE `student_info` SET `education_level` = '4' WHERE `education_level` = '研究生' AND `deleted` = 0;

-- 学习形式：全日制->1, 非全日制->2, 函授->3
UPDATE `student_info` SET `study_form` = '1' WHERE `study_form` = '全日制' AND `deleted` = 0;
UPDATE `student_info` SET `study_form` = '2' WHERE `study_form` = '非全日制' AND `deleted` = 0;
UPDATE `student_info` SET `study_form` = '3' WHERE `study_form` = '函授' AND `deleted` = 0;

-- 学生类型：普通生->1, 特长生->2, 转学生->3
UPDATE `student_info` SET `student_type` = '1' WHERE `student_type` = '普通生' AND `deleted` = 0;
UPDATE `student_info` SET `student_type` = '2' WHERE `student_type` = '特长生' AND `deleted` = 0;
UPDATE `student_info` SET `student_type` = '3' WHERE `student_type` = '转学生' AND `deleted` = 0;

-- 学籍状态：在籍->1, 休学->2, 退学->3, 异动->4
UPDATE `student_info` SET `status` = '1' WHERE `status` = '在籍' AND `deleted` = 0;
UPDATE `student_info` SET `status` = '2' WHERE `status` = '休学' AND `deleted` = 0;
UPDATE `student_info` SET `status` = '3' WHERE `status` = '退学' AND `deleted` = 0;
UPDATE `student_info` SET `status` = '4' WHERE `status` = '异动' AND `deleted` = 0;


-- ==================== 2. 荣誉管理表 (honor_mgmt) ====================
-- 荣誉类型：优秀学生->1, 奖学金->2, 竞赛获奖->3, 其他->4
UPDATE `honor_mgmt` SET `honor_type` = '1' WHERE `honor_type` = '优秀学生' AND `deleted` = 0;
UPDATE `honor_mgmt` SET `honor_type` = '2' WHERE `honor_type` = '奖学金' AND `deleted` = 0;
UPDATE `honor_mgmt` SET `honor_type` = '3' WHERE `honor_type` = '竞赛获奖' AND `deleted` = 0;
UPDATE `honor_mgmt` SET `honor_type` = '4' WHERE `honor_type` = '其他' AND `deleted` = 0;

-- 荣誉状态：待审核->0, 已通过->1, 已推送->2, 不通过->3
UPDATE `honor_mgmt` SET `status` = '0' WHERE `status` = '待审核' AND `deleted` = 0;
UPDATE `honor_mgmt` SET `status` = '1' WHERE `status` = '已通过' AND `deleted` = 0;
UPDATE `honor_mgmt` SET `status` = '2' WHERE `status` = '已推送' AND `deleted` = 0;
UPDATE `honor_mgmt` SET `status` = '3' WHERE `status` = '不通过' AND `deleted` = 0;


-- ==================== 3. 考评管理表 (assess_mgmt) ====================
-- 考评类型：教室卫生->class_clean, 早操->morning_exercise, 文明班级->civilized_class, 黑板报->blackboard
UPDATE `assess_mgmt` SET `assess_type` = 'class_clean' WHERE `assess_type` = '教室卫生' AND `deleted` = 0;
UPDATE `assess_mgmt` SET `assess_type` = 'morning_exercise' WHERE `assess_type` = '早操' AND `deleted` = 0;
UPDATE `assess_mgmt` SET `assess_type` = 'civilized_class' WHERE `assess_type` = '文明班级' AND `deleted` = 0;
UPDATE `assess_mgmt` SET `assess_type` = 'blackboard' WHERE `assess_type` = '黑板报' AND `deleted` = 0;

-- 考评周期：周->week, 月->month, 学期->semester
UPDATE `assess_mgmt` SET `cycle` = 'week' WHERE `cycle` = '周' AND `deleted` = 0;
UPDATE `assess_mgmt` SET `cycle` = 'month' WHERE `cycle` = '月' AND `deleted` = 0;
UPDATE `assess_mgmt` SET `cycle` = 'semester' WHERE `cycle` = '学期' AND `deleted` = 0;

-- 考评状态：未发布->un_publish, 已发布->published
UPDATE `assess_mgmt` SET `status` = 'un_publish' WHERE `status` = '未发布' AND `deleted` = 0;
UPDATE `assess_mgmt` SET `status` = 'published' WHERE `status` = '已发布' AND `deleted` = 0;


-- ==================== 4. 违纪管理表 (violate_mgmt) ====================
-- 违纪类型：仪容仪表->appearance, 行为违规->behavior, 其他->other
UPDATE `violate_mgmt` SET `violate_type` = 'appearance' WHERE `violate_type` = '仪容仪表' AND `deleted` = 0;
UPDATE `violate_mgmt` SET `violate_type` = 'behavior' WHERE `violate_type` = '行为违规' AND `deleted` = 0;
UPDATE `violate_mgmt` SET `violate_type` = 'other' WHERE `violate_type` = '其他' AND `deleted` = 0;

-- 处分类型：警告->warn, 记过->demerit, 留校察看->probation, 开除->expulsion
UPDATE `violate_mgmt` SET `punish_type` = 'warn' WHERE `punish_type` = '警告' AND `deleted` = 0;
UPDATE `violate_mgmt` SET `punish_type` = 'demerit' WHERE `punish_type` = '记过' AND `deleted` = 0;
UPDATE `violate_mgmt` SET `punish_type` = 'probation' WHERE `punish_type` = '留校察看' AND `deleted` = 0;
UPDATE `violate_mgmt` SET `punish_type` = 'expulsion' WHERE `punish_type` = '开除' AND `deleted` = 0;

-- 违纪状态：待审批->pending, 已执行->executed, 已预警->warned
UPDATE `violate_mgmt` SET `status` = 'pending' WHERE `status` = '待审批' AND `deleted` = 0;
UPDATE `violate_mgmt` SET `status` = 'executed' WHERE `status` = '已执行' AND `deleted` = 0;
UPDATE `violate_mgmt` SET `status` = 'warned' WHERE `status` = '已预警' AND `deleted` = 0;


-- ==================== 5. 心理管理表 (mental_mgmt) ====================
-- 心理状态：正常->normal, 关注->focus, 高危->high_risk
UPDATE `mental_mgmt` SET `mental_status` = 'normal' WHERE `mental_status` = '正常' AND `deleted` = 0;
UPDATE `mental_mgmt` SET `mental_status` = 'focus' WHERE `mental_status` = '关注' AND `deleted` = 0;
UPDATE `mental_mgmt` SET `mental_status` = 'high_risk' WHERE `mental_status` = '高危' AND `deleted` = 0;

-- 风险等级：低->low, 中->medium, 高->high
UPDATE `mental_mgmt` SET `risk_level` = 'low' WHERE `risk_level` = '低' AND `deleted` = 0;
UPDATE `mental_mgmt` SET `risk_level` = 'medium' WHERE `risk_level` = '中' AND `deleted` = 0;
UPDATE `mental_mgmt` SET `risk_level` = 'high' WHERE `risk_level` = '高' AND `deleted` = 0;

-- 心理干预状态：待评估->wait_evaluate, 咨询中->consulting, 已干预->intervened
UPDATE `mental_mgmt` SET `status` = 'wait_evaluate' WHERE `status` = '待评估' AND `deleted` = 0;
UPDATE `mental_mgmt` SET `status` = 'consulting' WHERE `status` = '咨询中' AND `deleted` = 0;
UPDATE `mental_mgmt` SET `status` = 'intervened' WHERE `status` = '已干预' AND `deleted` = 0;


-- ==================== 6. 行为管理表 (behavior_mgmt) ====================
-- 请假类型：事假->1, 病假->2, 其他->3
UPDATE `behavior_mgmt` SET `leave_type` = '1' WHERE `leave_type` = '事假' AND `deleted` = 0;
UPDATE `behavior_mgmt` SET `leave_type` = '2' WHERE `leave_type` = '病假' AND `deleted` = 0;
UPDATE `behavior_mgmt` SET `leave_type` = '3' WHERE `leave_type` = '其他' AND `deleted` = 0;

-- 审批级别：班主任->1, 辅导员->2
UPDATE `behavior_mgmt` SET `audit_level` = '1' WHERE `audit_level` = '班主任' AND `deleted` = 0;
UPDATE `behavior_mgmt` SET `audit_level` = '2' WHERE `audit_level` = '辅导员' AND `deleted` = 0;

-- 考勤同步状态：未同步->0, 已同步->1
UPDATE `behavior_mgmt` SET `attendance_sync` = '0' WHERE `attendance_sync` = '未同步' AND `deleted` = 0;
UPDATE `behavior_mgmt` SET `attendance_sync` = '1' WHERE `attendance_sync` = '已同步' AND `deleted` = 0;

-- 请假审批状态：待审批->0, 已通过->1, 已驳回->2
UPDATE `behavior_mgmt` SET `status` = '0' WHERE `status` = '待审批' AND `deleted` = 0;
UPDATE `behavior_mgmt` SET `status` = '1' WHERE `status` = '已通过' AND `deleted` = 0;
UPDATE `behavior_mgmt` SET `status` = '2' WHERE `status` = '已驳回' AND `deleted` = 0;


-- ==================== 7. 资助系统表 (fund_system) ====================
-- 资助类型：助学金->1, 勤工俭学->2, 其他->3
UPDATE `fund_system` SET `fund_type` = '1' WHERE `fund_type` = '助学金' AND `deleted` = 0;
UPDATE `fund_system` SET `fund_type` = '2' WHERE `fund_type` = '勤工俭学' AND `deleted` = 0;
UPDATE `fund_system` SET `fund_type` = '3' WHERE `fund_type` = '其他' AND `deleted` = 0;

-- 资助状态：待审核->0, 已汇总->1
UPDATE `fund_system` SET `status` = '0' WHERE `status` = '待审核' AND `deleted` = 0;
UPDATE `fund_system` SET `status` = '1' WHERE `status` = '已汇总' AND `deleted` = 0;


-- ==================== 8. 社团管理表 (club_mgmt) ====================
-- 社团类型：文体->1, 学术->2, 志愿->3, 其他->4
UPDATE `club_mgmt` SET `club_type` = '1' WHERE `club_type` = '文体' AND `deleted` = 0;
UPDATE `club_mgmt` SET `club_type` = '2' WHERE `club_type` = '学术' AND `deleted` = 0;
UPDATE `club_mgmt` SET `club_type` = '3' WHERE `club_type` = '志愿' AND `deleted` = 0;
UPDATE `club_mgmt` SET `club_type` = '4' WHERE `club_type` = '其他' AND `deleted` = 0;

-- 场馆申请状态：无->0, 待申请->1, 已通过->2
UPDATE `club_mgmt` SET `venue_apply_status` = '0' WHERE `venue_apply_status` = '无' AND `deleted` = 0;
UPDATE `club_mgmt` SET `venue_apply_status` = '1' WHERE `venue_apply_status` = '待申请' AND `deleted` = 0;
UPDATE `club_mgmt` SET `venue_apply_status` = '2' WHERE `venue_apply_status` = '已通过' AND `deleted` = 0;

-- 社团状态：待审核->0, 已通过->1, 已建档->2
UPDATE `club_mgmt` SET `status` = '0' WHERE `status` = '待审核' AND `deleted` = 0;
UPDATE `club_mgmt` SET `status` = '1' WHERE `status` = '已通过' AND `deleted` = 0;
UPDATE `club_mgmt` SET `status` = '2' WHERE `status` = '已建档' AND `deleted` = 0;


-- ==================== 9. 奖助勤贷表 (aid_work) ====================
-- 奖助勤贷类型：奖学金->1, 助学金->2, 助学贷款->3, 勤工俭学->4
UPDATE `aid_work` SET `aid_type` = '1' WHERE `aid_type` = '奖学金' AND `deleted` = 0;
UPDATE `aid_work` SET `aid_type` = '2' WHERE `aid_type` = '助学金' AND `deleted` = 0;
UPDATE `aid_work` SET `aid_type` = '3' WHERE `aid_type` = '助学贷款' AND `deleted` = 0;
UPDATE `aid_work` SET `aid_type` = '4' WHERE `aid_type` = '勤工俭学' AND `deleted` = 0;

-- 流程状态：跟进中->1, 已完成->2
UPDATE `aid_work` SET `process_status` = '1' WHERE `process_status` = '跟进中' AND `deleted` = 0;
UPDATE `aid_work` SET `process_status` = '2' WHERE `process_status` = '已完成' AND `deleted` = 0;

-- 奖助勤贷状态：待审核->0, 已通过->1, 已完成->2
UPDATE `aid_work` SET `status` = '0' WHERE `status` = '待审核' AND `deleted` = 0;
UPDATE `aid_work` SET `status` = '1' WHERE `status` = '已通过' AND `deleted` = 0;
UPDATE `aid_work` SET `status` = '2' WHERE `status` = '已完成' AND `deleted` = 0;


-- ==================== 10. 宿舍考勤表 (dorm_check) ====================
-- 考勤状态：正常->0, 迟到->1, 未到->2
UPDATE `dorm_check` SET `check_status` = '0' WHERE `check_status` = '正常' AND `deleted` = 0;
UPDATE `dorm_check` SET `check_status` = '1' WHERE `check_status` = '迟到' AND `deleted` = 0;
UPDATE `dorm_check` SET `check_status` = '2' WHERE `check_status` = '未到' AND `deleted` = 0;

-- 异常类型：无->0, 晚归->1, 未归->2
UPDATE `dorm_check` SET `abnormal_type` = '0' WHERE `abnormal_type` = '无' AND `deleted` = 0;
UPDATE `dorm_check` SET `abnormal_type` = '1' WHERE `abnormal_type` = '晚归' AND `deleted` = 0;
UPDATE `dorm_check` SET `abnormal_type` = '2' WHERE `abnormal_type` = '未归' AND `deleted` = 0;

-- 宿舍考勤状态：正常->0, 异常->1
UPDATE `dorm_check` SET `status` = '0' WHERE `status` = '正常' AND `deleted` = 0;
UPDATE `dorm_check` SET `status` = '1' WHERE `status` = '异常' AND `deleted` = 0;


-- ============================================
-- 重要说明：
--
-- 1. 本SQL仅处理已在 update_v03-insert-dict-data.sql 中定义字典的字段
-- 2. 经检查 student_mgmt.sql 文件，当前数据库中的数据已经使用value值存储
-- 3. 本SQL用于处理可能存在的历史数据或手动录入的label值
-- 4. 如果WHERE条件不匹配任何记录，说明数据已经是正确的value格式
--
-- 以下表的字段未在字典配置中定义，因此无需更新：
--    - duty_mgmt, access_apply, repair_mgmt, stay_mgmt, treat_mgmt
--    - communicate_mgmt, register_mgmt, class_assign, check_in
--    - dorm_assign, promote_mgmt, new_push, target_mgmt, compare_mgmt
--    - moral_activity, moral_resource, bed_mgmt, dorm_compare
--    - coop_enterprise, study_up, leave_handle
-- ============================================


-- ============================================
-- 验证SQL（执行UPDATE后可运行以下语句验证）：
-- ============================================
-- SELECT DISTINCT education_level FROM student_info WHERE deleted = 0;
-- SELECT DISTINCT honor_type FROM honor_mgmt WHERE deleted = 0;
-- SELECT DISTINCT assess_type, cycle, status FROM assess_mgmt WHERE deleted = 0;
-- SELECT DISTINCT violate_type, punish_type, status FROM violate_mgmt WHERE deleted = 0;
-- SELECT DISTINCT mental_status, risk_level, status FROM mental_mgmt WHERE deleted = 0;
-- SELECT DISTINCT leave_type, audit_level, attendance_sync, status FROM behavior_mgmt WHERE deleted = 0;
-- SELECT DISTINCT fund_type, status FROM fund_system WHERE deleted = 0;
-- SELECT DISTINCT club_type, venue_apply_status, status FROM club_mgmt WHERE deleted = 0;
-- SELECT DISTINCT aid_type, process_status, status FROM aid_work WHERE deleted = 0;
-- SELECT DISTINCT check_status, abnormal_type, status FROM dorm_check WHERE deleted = 0;
-- ============================================




-- 申请类型：应急出入->emergency, 其他->other
UPDATE `access_apply` SET `apply_type` = 'emergency' WHERE `apply_type` = '应急出入' AND `deleted` = 0;
UPDATE `access_apply` SET `apply_type` = 'other' WHERE `apply_type` = '其他' AND `deleted` = 0;

-- 状态：待审核->pending, 已通过->approved
UPDATE `access_apply` SET `status` = 'pending' WHERE `status` = '待审核' AND `deleted` = 0;
UPDATE `access_apply` SET `status` = 'approved' WHERE `status` = '已通过' AND `deleted` = 0;


-- 状态：未分班->unassigned, 已分班->assigned
UPDATE `class_assign` SET `status` = 'unassigned' WHERE `status` = '未分班' AND `deleted` = 0;
UPDATE `class_assign` SET `status` = 'assigned' WHERE `status` = '已分班' AND `deleted` = 0;


-- 评比周期：周->week, 月->month, 学期->semester
UPDATE `compare_mgmt` SET `cycle` = 'week' WHERE `cycle` = '周' AND `deleted` = 0;
UPDATE `compare_mgmt` SET `cycle` = 'month' WHERE `cycle` = '月' AND `deleted` = 0;
UPDATE `compare_mgmt` SET `cycle` = 'semester' WHERE `cycle` = '学期' AND `deleted` = 0;


-- 企业类型：国企->state_owned, 民企->private, 外企->foreign
UPDATE `coop_enterprise` SET `enterprise_type` = 'state_owned' WHERE `enterprise_type` = '国企' AND `deleted` = 0;
UPDATE `coop_enterprise` SET `enterprise_type` = 'private' WHERE `enterprise_type` = '民企' AND `deleted` = 0;
UPDATE `coop_enterprise` SET `enterprise_type` = 'foreign' WHERE `enterprise_type` = '外企' AND `deleted` = 0;

-- 状态：合作中->cooperating, 已结束->ended
UPDATE `coop_enterprise` SET `status` = 'cooperating' WHERE `status` = '合作中' AND `deleted` = 0;
UPDATE `coop_enterprise` SET `status` = 'ended' WHERE `status` = '已结束' AND `deleted` = 0;



-- 状态：未分配->unassigned, 已分配->assigned
UPDATE `dorm_assign` SET `status` = 'unassigned' WHERE `status` = '未分配' AND `deleted` = 0;
UPDATE `dorm_assign` SET `status` = 'assigned' WHERE `status` = '已分配' AND `deleted` = 0;



-- 评比周期：周->week, 月->month, 学期->semester
UPDATE `dorm_compare` SET `cycle` = 'week' WHERE `cycle` = '周' AND `deleted` = 0;
UPDATE `dorm_compare` SET `cycle` = 'month' WHERE `cycle` = '月' AND `deleted` = 0;
UPDATE `dorm_compare` SET `cycle` = 'semester' WHERE `cycle` = '学期' AND `deleted` = 0;


-- 打卡状态：未打卡->not_checked_in, 已打卡->checked_in
UPDATE `duty_mgmt` SET `check_in_status` = 'not_checked_in' WHERE `check_in_status` = '未打卡' AND `deleted` = 0;
UPDATE `duty_mgmt` SET `check_in_status` = 'checked_in' WHERE `check_in_status` = '已打卡' AND `deleted` = 0;



-- 状态：待打卡->pending_checkin, 待调班审批->pending_transfer, 待出车审批->pending_car, 已完成->completed
UPDATE `duty_mgmt` SET `status` = 'pending_checkin' WHERE `status` = '待打卡' AND `deleted` = 0;
UPDATE `duty_mgmt` SET `status` = 'pending_transfer' WHERE `status` = '待调班审批' AND `deleted` = 0;
UPDATE `duty_mgmt` SET `status` = 'pending_car' WHERE `status` = '待出车审批' AND `deleted` = 0;
UPDATE `duty_mgmt` SET `status` = 'completed' WHERE `status` = '已完成' AND `deleted` = 0;


-- 退宿状态：未退宿->not_checked_out, 已退宿->checked_out
UPDATE `leave_handle` SET `checkout_status` = 'not_checked_out' WHERE `checkout_status` = '未退宿' AND `deleted` = 0;
UPDATE `leave_handle` SET `checkout_status` = 'checked_out' WHERE `checkout_status` = '已退宿' AND `deleted` = 0;

-- 状态：待确认->pending_confirm, 待办理->pending_handle, 已离校->left
UPDATE `leave_handle` SET `status` = 'pending_confirm' WHERE `status` = '待确认' AND `deleted` = 0;
UPDATE `leave_handle` SET `status` = 'pending_handle' WHERE `status` = '待办理' AND `deleted` = 0;
UPDATE `leave_handle` SET `status` = 'left' WHERE `status` = '已离校' AND `deleted` = 0;


-- 活动类型：党团活动->party_league, 志愿活动->volunteer, 其他->other
UPDATE `moral_activity` SET `activity_type` = 'party_league' WHERE `activity_type` = '党团活动' AND `deleted` = 0;
UPDATE `moral_activity` SET `activity_type` = 'volunteer' WHERE `activity_type` = '志愿活动' AND `deleted` = 0;
UPDATE `moral_activity` SET `activity_type` = 'other' WHERE `activity_type` = '其他' AND `deleted` = 0;

-- 状态：未发布->unpublished, 进行中->ongoing, 已结束->ended
UPDATE `moral_activity` SET `status` = 'unpublished' WHERE `status` = '未发布' AND `deleted` = 0;
UPDATE `moral_activity` SET `status` = 'ongoing' WHERE `status` = '进行中' AND `deleted` = 0;
UPDATE `moral_activity` SET `status` = 'ended' WHERE `status` = '已结束' AND `deleted` = 0;




-- 活动类型：党团活动->party_league, 志愿活动->volunteer, 其他->other
UPDATE `moral_activity` SET `activity_type` = 'party_league' WHERE `activity_type` = '党团活动' AND `deleted` = 0;
UPDATE `moral_activity` SET `activity_type` = 'volunteer' WHERE `activity_type` = '志愿活动' AND `deleted` = 0;
UPDATE `moral_activity` SET `activity_type` = 'other' WHERE `activity_type` = '其他' AND `deleted` = 0;

-- 状态：未发布->unpublished, 进行中->ongoing, 已结束->ended
UPDATE `moral_activity` SET `status` = 'unpublished' WHERE `status` = '未发布' AND `deleted` = 0;
UPDATE `moral_activity` SET `status` = 'ongoing' WHERE `status` = '进行中' AND `deleted` = 0;
UPDATE `moral_activity` SET `status` = 'ended' WHERE `status` = '已结束' AND `deleted` = 0;



-- 状态：未推送->unpushed, 已推送->pushed
UPDATE `new_push` SET `status` = 'unpushed' WHERE `status` = '未推送' AND `deleted` = 0;
UPDATE `new_push` SET `status` = 'pushed' WHERE `status` = '已推送' AND `deleted` = 0;

-- 状态：待审核->pending, 已通过->approved, 已驳回->rejected
UPDATE `promote_mgmt` SET `status` = 'pending' WHERE `status` = '待审核' AND `deleted` = 0;
UPDATE `promote_mgmt` SET `status` = 'approved' WHERE `status` = '已通过' AND `deleted` = 0;
UPDATE `promote_mgmt` SET `status` = 'rejected' WHERE `status` = '已驳回' AND `deleted` = 0;



-- 状态：待审核->pending, 已录取->admitted
UPDATE `register_mgmt` SET `status` = 'pending' WHERE `status` = '待审核' AND `deleted` = 0;
UPDATE `register_mgmt` SET `status` = 'admitted' WHERE `status` = '已录取' AND `deleted` = 0;


-- 报修类型：水电->water_electricity, 家具->furniture, 其他->other
UPDATE `repair_mgmt` SET `repair_type` = 'water_electricity' WHERE `repair_type` = '水电' AND `deleted` = 0;
UPDATE `repair_mgmt` SET `repair_type` = 'furniture' WHERE `repair_type` = '家具' AND `deleted` = 0;
UPDATE `repair_mgmt` SET `repair_type` = 'other' WHERE `repair_type` = '其他' AND `deleted` = 0;


-- 状态：待确认->pending_confirm, 待审核->pending_audit, 已通过->approved
UPDATE `stay_mgmt` SET `status` = 'pending_confirm' WHERE `status` = '待确认' AND `deleted` = 0;
UPDATE `stay_mgmt` SET `status` = 'pending_audit' WHERE `status` = '待审核' AND `deleted` = 0;
UPDATE `stay_mgmt` SET `status` = 'approved' WHERE `status` = '已通过' AND `deleted` = 0;


-- 院校类型：公办->public, 民办->private
UPDATE `study_up` SET `school_type` = 'public' WHERE `school_type` = '公办' AND `deleted` = 0;
UPDATE `study_up` SET `school_type` = 'private' WHERE `school_type` = '民办' AND `deleted` = 0;

-- 状态：待规划->pending_plan, 已规划->planned
UPDATE `study_up` SET `status` = 'pending_plan' WHERE `status` = '待规划' AND `deleted` = 0;
UPDATE `study_up` SET `status` = 'planned' WHERE `status` = '已规划' AND `deleted` = 0;



-- 评价人类型：教职工->teacher, 家长->parent, 领导->leader
UPDATE `target_mgmt` SET `evaluator_type` = 'teacher' WHERE `evaluator_type` = '教职工' AND `deleted` = 0;
UPDATE `target_mgmt` SET `evaluator_type` = 'parent' WHERE `evaluator_type` = '家长' AND `deleted` = 0;
UPDATE `target_mgmt` SET `evaluator_type` = 'leader' WHERE `evaluator_type` = '领导' AND `deleted` = 0;

-- 评分类型：定量->quantitative, 定性->qualitative
UPDATE `target_mgmt` SET `score_type` = 'quantitative' WHERE `score_type` = '定量' AND `deleted` = 0;
UPDATE `target_mgmt` SET `score_type` = 'qualitative' WHERE `score_type` = '定性' AND `deleted` = 0;

-- 状态：未发布->unpublished, 已发布->published
UPDATE `target_mgmt` SET `status` = 'unpublished' WHERE `status` = '未发布' AND `deleted` = 0;
UPDATE `target_mgmt` SET `status` = 'published' WHERE `status` = '已发布' AND `deleted` = 0;



-- 就诊类型：门诊->outpatient, 急诊->emergency, 其他->other
UPDATE `treat_mgmt` SET `treat_type` = 'outpatient' WHERE `treat_type` = '门诊' AND `deleted` = 0;
UPDATE `treat_mgmt` SET `treat_type` = 'emergency' WHERE `treat_type` = '急诊' AND `deleted` = 0;
UPDATE `treat_mgmt` SET `treat_type` = 'other' WHERE `treat_type` = '其他' AND `deleted` = 0;

-- 状态：待审核->pending, 已就诊->visited
UPDATE `treat_mgmt` SET `status` = 'pending' WHERE `status` = '待审核' AND `deleted` = 0;
UPDATE `treat_mgmt` SET `status` = 'visited' WHERE `status` = '已就诊' AND `deleted` = 0;


-- 评分类型：定量->quantitative, 定性->qualitative
UPDATE `target_mgmt` SET `score_type` = 'quantitative' WHERE `score_type` = '定量' AND `deleted` = 0;
UPDATE `target_mgmt` SET `score_type` = 'qualitative' WHERE `score_type` = '定性' AND `deleted` = 0;

