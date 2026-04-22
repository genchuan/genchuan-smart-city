package cn.iocoder.yudao.module.studentmgmt.enums;

/**
 * 学生管理 操作日志枚举
 * 目的：统一管理，也减少 Service 里各种“复杂”字符串
 *
 * @author HUIHUI
 */
public interface LogRecordConstants {

    // ======================= STUDENT_INFO 学生信息 =======================

    String STUDENT_INFO_TYPE = "学生信息";
    String STUDENT_INFO_CREATE_SUB_TYPE = "创建学生信息";
    String STUDENT_INFO_CREATE_SUCCESS = "创建了学生信息{{#studentInfo.name}}";
    String STUDENT_INFO_UPDATE_SUB_TYPE = "更新学生信息";
    String STUDENT_INFO_UPDATE_SUCCESS = "更新了学生信息【{{#studentInfo.name}}】】: {_DIFF{#updateReqVO}}";
    String STUDENT_INFO_DELETE_SUB_TYPE = "删除学生信息";
    String STUDENT_INFO_DELETE_SUCCESS = "删除了学生信息【{{#studentName}}】";

    // ======================= STUDENT_HONOR 荣誉管理 =======================

    String STUDENT_HONOR_TYPE = "STUDENT 荣誉管理";
    String STUDENT_HONOR_CREATE_SUB_TYPE = "创建荣誉";
    String STUDENT_HONOR_CREATE_SUCCESS = "创建了荣誉{{#honorMgmt.honorName}}";
    String STUDENT_HONOR_UPDATE_SUB_TYPE = "更新荣誉";
    String STUDENT_HONOR_UPDATE_SUCCESS = "更新了荣誉【{{#honorName}}】: {_DIFF{#updateReqVO}}";
    String STUDENT_HONOR_DELETE_SUB_TYPE = "删除荣誉";
    String STUDENT_HONOR_DELETE_SUCCESS = "删除了荣誉【{{#honorName}}】";
    String STUDENT_HONOR_PUSH_SUB_TYPE = "推送荣誉";
    String STUDENT_HONOR_PUSH_SUCCESS = "推送了荣誉【{{#honorName}}】";
    String STUDENT_HONOR_EXPORT_SUB_TYPE = "导出荣誉";
    String STUDENT_HONOR_EXPORT_SUCCESS = "导出了荣誉";
    String STUDENT_HONOR_UPDATE_AUDIT_STATUS_SUB_TYPE = "更新荣誉审核状态";
    String STUDENT_HONOR_UPDATE_AUDIT_STATUS_SUCCESS = "更新了荣誉【{{#honorName}}】的审核状态为【{{#status}}】";

    // ======================= STUDENT_ASSESS 考评管理 =======================

    String STUDENT_ASSESS_TYPE = "STUDENT 考评管理";
    String STUDENT_ASSESS_CREATE_SUB_TYPE = "创建考评";
    String STUDENT_ASSESS_CREATE_SUCCESS = "创建了{{#assessMgmt.className}}的考评";
    String STUDENT_ASSESS_UPDATE_SUB_TYPE = "更新考评";
    String STUDENT_ASSESS_UPDATE_SUCCESS = "更新了考评【{{#assessMgmt.className}}】: {_DIFF{#updateReqVO}}";
    String STUDENT_ASSESS_DELETE_SUB_TYPE = "删除考评";
    String STUDENT_ASSESS_DELETE_SUCCESS = "删除了考评【{{#assessMgmt.className}}】";
    String STUDENT_ASSESS_PUBLISH_SUB_TYPE = "发布考评";
    String STUDENT_ASSESS_PUBLISH_SUCCESS = "发布了考评【{{#assessMgmt.className}}】";
    String STUDENT_ASSESS_EXPORT_SUB_TYPE = "导出考评";
    String STUDENT_ASSESS_EXPORT_SUCCESS = "导出了考评";
    String STUDENT_ASSESS_UPDATE_AUDIT_STATUS_SUB_TYPE = "更新考评审核状态";
    String STUDENT_ASSESS_UPDATE_AUDIT_STATUS_SUCCESS = "更新了考评【{{#className}}】的审核状态为【{{#status ? '已审核' : '未审核'}}】";


    // ======================= Violate 违纪管理 =======================

    String VIOLATE_TYPE = "STUDENT 违纪管理";
    String VIOLATE_CREATE_SUB_TYPE = "创建违纪";
    String VIOLATE_CREATE_SUCCESS = "创建了{{#studentName}}的违纪";
    String VIOLATE_UPDATE_SUB_TYPE = "更新违纪";
    String VIOLATE_UPDATE_SUCCESS = "更新了违纪【{{#violate.studentId}}】: {_DIFF{#updateReqVO}}";
    String VIOLATE_WARN_SUB_TYPE = "预警违纪";
    String VIOLATE_WARN_SUCCESS = "更新了违纪【{{#studentName}}】的预警状态为【{{#status ? '已预警' : '未预警'}}】";
    String VIOLATE_PUSH_SUB_TYPE = "推送违纪";
    String VIOLATE_PUSH_SUCCESS = "推送了【{{#studentName}}】违纪";
    String VIOLATE_UPDATE_AUDIT_STATUS_SUB_TYPE = "更新违纪审批状态";
    String VIOLATE_UPDATE_AUDIT_STATUS_SUCCESS = "更新了违纪【{{#studentName}}】的审批状态为【{{#status ? '已审批' : '未审批'}}】";



    // ======================= mental 心理管理 =======================

    String MENTAL_TYPE = "STUDENT 心理管理";
    String MENTAL_CREATE_SUB_TYPE = "创建心理";
    String MENTAL_CREATE_SUCCESS = "创建了{{#studentName}}的心理";
    String MENTAL_UPDATE_SUB_TYPE = "更新心理";
    String MENTAL_UPDATE_SUCCESS = "更新了心理【{{#mental.studentId}}】: {_DIFF{#updateReqVO}}";
    String MENTAL_CONSULT_SUB_TYPE = "预约心理";
    String MENTAL_CONSULT_SUCCESS = "预约了【{{#studentName}}】心理";

    String MENTAL_INTERVENE_SUB_TYPE = "跟进心理";
    String MENTAL_INTERVENE_SUCCESS = "跟进了【{{#studentName}}】心理";

    String MENTAL_UPDATE_STATUS_SUB_TYPE = "更新心理状态";
    String MENTAL_UPDATE_STATUS_SUCCESS = "更新了心理状态【{{#studentName}}】为【{{#status}}】";


    // ======================= behavior 行为管理 =======================

    String BEHAVIOR_TYPE = "STUDENT 行为管理";
    String BEHAVIOR_CREATE_SUB_TYPE = "创建行为";
    String BEHAVIOR_CREATE_SUCCESS = "创建了{{#studentName}}的行为";
    String BEHAVIOR_UPDATE_SUB_TYPE = "更新行为";
    String BEHAVIOR_UPDATE_SUCCESS = "更新了行为【{{#behavior.studentId}}】: {_DIFF{#updateReqVO}}";
    String BEHAVIOR_AUDIT_SUB_TYPE = "预警行为";
    String BEHAVIOR_AUDIT_SUCCESS = "审核了行为";;
    String BEHAVIOR_CANCEL_SUB_TYPE = "取消行为";
    String BEHAVIOR_CANCEL_SUCCESS = "取消了行为";

    // ======================= FundSystem 资助系统管理 =======================

    String FUND_SYSTEM_TYPE = "STUDENT 资助系统管理";
    String FUND_SYSTEM_CREATE_SUB_TYPE = "创建资助系统";
    String FUND_SYSTEM_CREATE_SUCCESS = "创建了{{#studentName}}的资助系统";
    String FUND_SYSTEM_UPDATE_SUB_TYPE = "更新资助系统";
    String FUND_SYSTEM_UPDATE_SUCCESS = "更新了资助系统【{{#behavior.studentId}}】: {_DIFF{#updateReqVO}}";
    String FUND_SYSTEM_AUDIT_SUB_TYPE = "预警资助系统";
    String FUND_SYSTEM_AUDIT_SUCCESS = "资助系统审核成功";;

    // ======================= club 社团管理 =======================
    String CLUB_TYPE = "STUDENT 社团管理";
    String CLUB_CREATE_SUB_TYPE = "创建社团";
    String CLUB_CREATE_SUCCESS = "创建了{{club.clubName}}的社团";
    String CLUB_UPDATE_SUB_TYPE = "更新社团";
    String CLUB_UPDATE_SUCCESS = "更新了社团【{{#club.clubName}}】: {_DIFF{#updateReqVO}}";
    String CLUB_AUDIT_SUB_TYPE = "审核社团";
    String CLUB_AUDIT_SUCCESS = "审核了社团";

    // ======================= Aid 奖助勤贷 =======================

    String AID_TYPE = "奖助勤贷管理";
    String AID_CREATE_SUB_TYPE = "创建奖助勤贷";
    String AID_CREATE_SUCCESS = "创建了{{#aid.studentId}}的奖助勤贷";
    String AID_UPDATE_SUB_TYPE = "更新奖助勤贷";
    String AID_UPDATE_SUCCESS = "【{{#aid.auditUser}}】更新了学生【{{#aid.studentId}}】: {_DIFF{#updateReqVO}}奖助勤贷";
    String AID_DELETE_SUB_TYPE = "删除奖助勤贷";
    String AID_DELETE_SUCCESS = "删除了奖助勤贷【{{#aid.studentId}}】";
    String AID_FOLLOW_SUB_TYPE = "跟进奖助勤贷";
    String AID_FOLLOW_SUCCESS = "跟进了奖助勤贷【{{#aid.id}}】";
    String AID_EXPORT_SUB_TYPE = "导出奖助勤贷";
    String AID_EXPORT_SUCCESS = "导出了奖助勤贷";
    String AID_AUDIT_SUB_TYPE = "审核奖助勤贷";
    String AID_AUDIT_SUCCESS = "学生【{{#aid.studentId}}】奖助勤贷的审核状态为【{{#status ? '已审核' : '未审核'}}】";

// ======================= Duty 值班管理 =======================

    String DUTY_TYPE = "值班管理";
    String DUTY_CREATE_SUB_TYPE = "排班";
    String DUTY_CREATE_SUCCESS = "排班了【{{#reqVO.dutyUser}}】的值班{{#total}}天";
    String DUTY_UPDATE_SUB_TYPE = "更新排班";
    String DUTY_UPDATE_SUCCESS = "更新排班【{{#updateReqVO.dutyUser}}】的值班";


    String DUTY_CHECK_IN_SUB_TYPE = "打卡";
    String DUTY_CHECK_IN_SUCCESS = "【{{#duty.dutyUser}}】打卡了值班";

    String DUTY_SHIFT_APPLY_SUB_TYPE = "调班申请";
    String DUTY_SHIFT_APPLY_SUCCESS = "【{{#duty.dutyUser}}】调班申请";

    String DUTY_SHIFT_AUDIT_SUB_TYPE = "调班审批";
    String DUTY_SHIFT_AUDIT_SUCCESS = "调班审批成功";

    String DUTY_VEHICLE_APPLY_SUB_TYPE = "出车申请";
    String DUTY_VEHICLE_APPLY_SUCCESS = "【{{#duty.dutyUser}}】出车申请";

    String DUTY_VEHICLE_AUDIT_SUB_TYPE = "出车审批";
    String DUTY_VEHICLE_AUDIT_SUCCESS = "出车审批成功";


    String DUTY_UPLOAD_RECORD_SUB_TYPE = "值班记录上传";
    String DUTY_UPLOAD_RECORD_SUCCESS = "值班记录上传成功，内容：【{{#duty.recordContent}}】";

// ======================= target 指标管理 =======================

    String TARGET_TYPE = "指标管理";
    String TARGET_CREATE_SUB_TYPE = "新建指标";
    String TARGET_CREATE_SUCCESS = "新建指标成功【{{#createReqVO.targetName}}】";

    String TARGET_UPDATE_SUB_TYPE = "更新指标";
    String TARGET_UPDATE_SUCCESS = "更新【{{#updateReqVO.targetName}}】的指标";

    String TARGET_CONFIG_SUB_TYPE = "配置指标";
    String TARGET_CONFIG_SUCCESS = "配置【{{#target.targetName}}】的指标";

    String TARGET_ENABLE_SUB_TYPE = "启用指标";
    String TARGET_ENABLE_SUCCESS = "启用的指标";

    String TARGET_DISABLE_SUB_TYPE = "停用指标";
    String TARGET_DISABLE_SUCCESS = "停用的指标";

    String COMPARE_TYPE = "评比管理";
    String COMPARE_CREATE_SUB_TYPE = "新建评比";
    String COMPARE_CREATE_SUCCESS = "新建【{{#createReqVO.className}}】评比成功";

    String COMPARE_UPDATE_SUB_TYPE = "更新评比";
    String COMPARE_UPDATE_SUCCESS = "更新【{{#updateReqVO.className}}】的评比";

    String COMPARE_SCORE_SUB_TYPE = "打分评比";
    String COMPARE_SCORE_SUCCESS = "评分人【{{#reqVO.scoreUser}}】打分【{{#compare.className}}】的评比【{{#reqVO.totalScore}}】分";

    String COMPARE_AWARD_SUB_TYPE = "授予评比";
    String COMPARE_AWARD_SUCCESS = "授予【{{#id}}】等的评比【{{#reqVO.awardName}}】称号";



    // ======================= 德育活动 =======================

    String MORAL_ACTIVITY_TYPE = "德育活动";
    String MORAL_ACTIVITY_PUBLISH_SUB_TYPE = "德育活动";
    String MORAL_ACTIVITY_PUBLISH_SUCCESS = "发布了德育活动";
    String MORAL_ACTIVITY_JOIN_SUB_TYPE = "德育活动";
    String MORAL_ACTIVITY_JOIN_SUCCESS = "报名了德育活动";
    String MORAL_ACTIVITY_RECORD_SUB_TYPE = "德育活动";
    String MORAL_ACTIVITY_RECORD_SUCCESS = "记录了德育活动";

    String MORAL_ACTIVITY_UPDATE_SUB_TYPE = "更新德育活动";
    String MORAL_ACTIVITY_UPDATE_SUCCESS = "更新了德育活动【{{#honorName}}】: {_DIFF{#updateReqVO}}";

    // ======================= STUDENT_ASSESS 德育资源 =======================

    String MORAL_RESOURCE_TYPE = "德育资源";
    String MORAL_RESOURCE_ONLINE_SUB_TYPE = "上架德育资源";
    String MORAL_RESOURCE_ONLINE_SUCCESS = "上架了德育资源";

    String MORAL_RESOURCE_OFFLINE_SUB_TYPE = "上架德育资源";
    String MORAL_RESOURCE_OFFLINE_SUCCESS = "上架了德育资源";

    String MORAL_RESOURCE_UPDATE_SUB_TYPE = "更新德育资源";
    String MORAL_RESOURCE_UPDATE_SUCCESS = "更新了德育资源【{{#assessMgmt.className}}】: {_DIFF{#updateReqVO}}";
}
