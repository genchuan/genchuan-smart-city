package cn.iocoder.yudao.module.studentmgmt.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * studentmgmt 错误码枚举类
 *
 * studentmgmt 系统
 */
public interface ErrorCodeConstants {


    // =============================================================================================================
    // =============================================== 学 生 管 理 模 块 ==================================================
    // =============================================================================================================

    ErrorCode DEPT_NOT_EXISTS = new ErrorCode(500, "班级（部门）不存在");

    // ========== 学生信息管理 ==========
    ErrorCode STUDENT_INFO_NOT_EXISTS = new ErrorCode(500, "学生信息管理不存在");
    ErrorCode STUDENT_INFO_IS_EXISTS = new ErrorCode(500, "学生信息管理已存在");

    // ========== 荣誉管理==========
    ErrorCode HONOR_MGMT_NOT_EXISTS = new ErrorCode(500, "荣誉管理不存在");

    // ========== 考评管理 ==========
    ErrorCode ASSESS_MGMT_NOT_EXISTS = new ErrorCode(500, "考评管理不存在");

    // ========== 违纪管理 ==========
    ErrorCode VIOLATE_MGMT_NOT_EXISTS = new ErrorCode(500, "违纪管理不存在");

    // ========== 行为管理 ==========
    ErrorCode BEHAVIOR_MGMT_NOT_EXISTS = new ErrorCode(500, "行为管理不存在");

    // ========== 心理管理 ==========
    ErrorCode MENTAL_MGMT_NOT_EXISTS = new ErrorCode(500, "心理管理不存在");

    // ========== 离校办理 ==========
    ErrorCode LEAVE_HANDLE_NOT_EXISTS = new ErrorCode(500, "离校办理不存在");

    // ========== 出入申请 ==========
    ErrorCode ACCESS_APPLY_NOT_EXISTS = new ErrorCode(500, "出入申请不存在");

    // ========== 奖助勤贷 ==========
    ErrorCode AID_WORK_NOT_EXISTS = new ErrorCode(500, "奖助勤贷不存在");

    // ========== 床位管理 ==========
    ErrorCode BED_MGMT_NOT_EXISTS = new ErrorCode(500, "床位管理不存在");

    // ========== 分班管理 ==========
    ErrorCode CLASS_ASSIGN_NOT_EXISTS = new ErrorCode(500, "分班管理不存在");

    // ========== 沟通管理 ==========
    ErrorCode COMMUNICATE_MGMT_NOT_EXISTS = new ErrorCode(500, "沟通管理不存在");

    // ========== 评比管理 ==========
    ErrorCode COMPARE_MGMT_NOT_EXISTS = new ErrorCode(500, "评比管理不存在");

    // ========== 校企合作 ==========
    ErrorCode COOP_ENTERPRISE_NOT_EXISTS = new ErrorCode(500, "校企合作不存在");

    // ========== 宿舍分配 ==========
    ErrorCode DORM_ASSIGN_NOT_EXISTS = new ErrorCode(500, "宿舍分配不存在");
    // ========== 宿舍考勤 ==========
    ErrorCode DORM_CHECK_NOT_EXISTS = new ErrorCode(500, "宿舍考勤不存在");
    // ========== 宿舍评比 ==========
    ErrorCode DORM_COMPARE_NOT_EXISTS = new ErrorCode(500, "宿舍评比不存在");
    // 自动校验记录是否为未打分状态，避免重复打分；
    ErrorCode DORM_COMPARE_STATUS_SCORING = new ErrorCode(500, "已打分");

    // ========== 值班管理 ==========
    ErrorCode DUTY_MGMT_NOT_EXISTS = new ErrorCode(500, "值班管理不存在");
    ErrorCode DUTY_MGMT_CHECK_IN_STATUS_CHECKED_IN = new ErrorCode(500, "该用户已打卡，请勿重复打卡");
    ErrorCode DUTY_MGMT_NOT_PENDING_CHECKIN = new ErrorCode(500, "待打卡状态，不可操作");
    // ========== 资助系统 ==========
    ErrorCode FUND_SYSTEM_NOT_EXISTS = new ErrorCode(500, "资助系统不存在");
    // ========== 德育活动 ==========
    ErrorCode MORAL_ACTIVITY_NOT_EXISTS = new ErrorCode(500, "德育活动不存在");
    // ========== 德育资源 ==========
    ErrorCode MORAL_RESOURCE_NOT_EXISTS = new ErrorCode(500, "德育资源不存在");
    // ========== 宣传管理 ==========
    ErrorCode PROMOTE_MGMT_NOT_EXISTS = new ErrorCode(500, "宣传管理不存在");
    // ========== 报名管理 ==========
    ErrorCode REGISTER_MGMT_NOT_EXISTS = new ErrorCode(500, "报名管理不存在");
    // ========== 报修管理 ==========
    ErrorCode REPAIR_MGMT_NOT_EXISTS = new ErrorCode(500, "报修管理不存在");
    // ========== 留宿管理 ==========
    ErrorCode STAY_MGMT_NOT_EXISTS = new ErrorCode(500, "留宿管理不存在");
    // ========== 升学管理 ==========
    ErrorCode STUDY_UP_NOT_EXISTS = new ErrorCode(500, "升学管理不存在");
    // ========== 指标管理 ==========
    ErrorCode TARGET_MGMT_NOT_EXISTS = new ErrorCode(500, "指标管理不存在");
    // ========== 就诊管理 ==========
    ErrorCode TREAT_MGMT_NOT_EXISTS = new ErrorCode(500, "就诊管理不存在");
    // ========== 社团管理  ==========
    ErrorCode CLUB_MGMT_NOT_EXISTS = new ErrorCode(500, "社团管理不存在");
    // ========== 报到管理 ==========
    ErrorCode CHECK_IN_NOT_EXISTS = new ErrorCode(500, "报到管理不存在");
    // ========== 迎新推送 ==========
    ErrorCode NEW_PUSH_NOT_EXISTS = new ErrorCode(500, "迎新推送不存在");
    // ========== 家长回复 ==========
    ErrorCode PARENT_REPLY_NOT_EXISTS = new ErrorCode(500, "家长回复不存在");
}
