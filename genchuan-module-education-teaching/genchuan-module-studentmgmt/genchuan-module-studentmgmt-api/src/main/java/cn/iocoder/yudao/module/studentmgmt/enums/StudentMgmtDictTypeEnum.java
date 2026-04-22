package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 学生管理所有的字典类型
 */
public enum StudentMgmtDictTypeEnum {

    // ==================== 学工首页 ====================
    /**
     * 学工首页记录类型（荣誉 / 考评 / 违纪 / 行为 / 心理 / 资助）
     */
    WORK_HOME_RECORD_TYPE("work_home_record_type", "学工首页记录类型"),

    // ==================== 学生信息 ====================
    /**
     * 学生学历层次（中专 / 大专 / 本科 / 研究生）
     */
    STUDENT_INFO_EDUCATION_LEVEL("student_info_education_level", "学生学历层次"),
    /**
     * 学生学习形式（全日制 / 非全日制 / 函授）
     */
    STUDENT_INFO_STUDY_FORM("student_info_study_form", "学生学习形式"),
    /**
     * 学生类型（普通生 / 特长生 / 转学生）
     */
    STUDENT_INFO_STUDENT_TYPE("student_info_student_type", "学生类型"),
    /**
     * 学生学籍状态（在籍 / 休学 / 退学 / 异动）
     */
    STUDENT_INFO_STATUS("student_info_status", "学生学籍状态"),

    // ==================== 荣誉管理 ====================
    /**
     * 荣誉类型（优秀学生 / 奖学金 / 竞赛获奖 / 其他）
     */
    HONOR_MGMT_HONOR_TYPE("honor_mgmt_honor_type", "荣誉类型"),
    /**
     * 荣誉审核状态（待审核 / 已通过 / 已推送）
     */
    HONOR_MGMT_STATUS("honor_mgmt_status", "荣誉审核状态"),

    // ==================== 考评管理 ====================
    /**
     * 考评统计周期（周 / 月 / 学期）
     */
    ASSESS_MGMT_CYCLE("assess_mgmt_cycle", "考评统计周期"),
    /**
     * 考评类型（教室卫生 / 早操 / 文明班级 / 黑板报）
     */
    ASSESS_MGMT_ASSESS_TYPE("assess_mgmt_assess_type", "考评类型"),
    /**
     * 考评发布状态（未发布 / 已发布）
     */
    ASSESS_MGMT_STATUS("assess_mgmt_status", "考评发布状态"),

    // ==================== 违纪管理 ====================
    /**
     * 违纪类型（仪容仪表 / 行为违规 / 其他）
     *
     */
    VIOLATE_MGMT_VIOLATE_TYPE("violate_mgmt_violate_type", "违纪类型"),
    /**
     * 处分类型（警告 / 记过 / 留校察看 / 开除）
     */
    VIOLATE_MGMT_PUNISH_TYPE("violate_mgmt_punish_type", "处分类型"),
    /**
     * 违纪处理状态（待审批 / 已执行 / 已预警）
     */
    VIOLATE_MGMT_STATUS("violate_mgmt_status", "违纪处理状态"),

    // ==================== 心理管理 ====================
    /**
     * 心理状态（正常 / 关注 / 高危）
     */
    MENTAL_MGMT_MENTAL_STATUS("mental_mgmt_mental_status", "心理状态"),
    /**
     * 心理风险等级（低 / 中 / 高）
     */
    MENTAL_MGMT_RISK_LEVEL("mental_mgmt_risk_level", "心理风险等级"),
    /**
     * 心理干预状态（待评估 / 咨询中 / 已干预）
     */
    MENTAL_MGMT_STATUS("mental_mgmt_status", "心理干预状态"),

    // ==================== 行为管理 ====================
    /**
     * 请假类型（事假 / 病假 / 其他）
     */
    BEHAVIOR_MGMT_LEAVE_TYPE("behavior_mgmt_leave_type", "请假类型"),
    /**
     * 请假审批级别（班主任 / 辅导员）
     */
    BEHAVIOR_MGMT_AUDIT_LEVEL("behavior_mgmt_audit_level", "请假审批级别"),
    /**
     * 考勤同步状态（未同步 / 已同步）
     */
    BEHAVIOR_MGMT_ATTENDANCE_SYNC("behavior_mgmt_attendance_sync", "考勤同步状态"),
    /**
     * 请假审批状态（待审批 / 已通过 / 已驳回）
     */
    BEHAVIOR_MGMT_STATUS("behavior_mgmt_status", "请假审批状态"),

    // ==================== 资助系统 ====================
    /**
     * 资助类型（助学金 / 勤工俭学 / 其他）
     */
    FUND_SYSTEM_FUND_TYPE("fund_system_fund_type", "资助类型"),
    /**
     * 资助审核状态（待审核 / 已汇总）
     */
    FUND_SYSTEM_STATUS("fund_system_status", "资助审核状态"),

    // ==================== 社团管理 ====================
    /**
     * 社团类型（文体 / 学术 / 志愿 / 其他）
     */
    CLUB_MGMT_CLUB_TYPE("club_mgmt_club_type", "社团类型"),
    /**
     * 场馆申请状态（无 / 待申请 / 已通过）
     */
    CLUB_MGMT_VENUE_APPLY_STATUS("club_mgmt_venue_apply_status", "场馆申请状态"),
    /**
     * 社团入团状态（待审核 / 已通过 / 已建档）
     */
    CLUB_MGMT_STATUS("club_mgmt_status", "社团入团状态"),

    // ==================== 奖助勤贷 ====================
    /**
     * 奖助勤贷类型（奖学金 / 助学金 / 助学贷款 / 勤工俭学）
     */
    AID_WORK_AID_TYPE("aid_work_aid_type", "奖助勤贷类型"),
    /**
     * 奖助勤贷流程状态（跟进中 / 已完成）
     */
    AID_WORK_PROCESS_STATUS("aid_work_process_status", "奖助勤贷流程状态"),
    /**
     * 奖助勤贷审核状态（待审核 / 已通过 / 已完成）
     */
    AID_WORK_STATUS("aid_work_status", "奖助勤贷审核状态"),
    // ==================== 德育资源 ====================
    MORAL_RESOURCE_RESOURCE_TYPE("moral_resource_resource_type", "德育资源类型"),
    /**
     * 德育资源审核状态（待审核 / 已通过 / 已完成）
     */
    MORAL_RESOURCE_STATUS("moral_resource_status", "德育资源审核状态"),



    ;
    // ==================== 枚举属性 ====================
    /**
     * 字典类型
     */
    private final String type;

    /**
     * 字典名称
     */
    private final String name;

    }
