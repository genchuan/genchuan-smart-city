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
    String STUDENT_HONOR_UPDATE_AUDIT_STATUS_SUCCESS = "更新了荣誉【{{#honorName}}】的审核状态为【{{#status ? '已审核' : '未审核'}}】";

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
    String MENTAL_WARN_SUB_TYPE = "预警心理";
    String MENTAL_WARN_SUCCESS = "更新了心理【{{#studentName}}】的预警状态为【{{#status ? '已预警' : '未预警'}}】";
    String MENTAL_CONSULT_SUB_TYPE = "预约心理";
    String MENTAL_CONSULT_SUCCESS = "【{{username}}】预约了【{{#studentName}}】心理";

    String MENTAL_INTERVENE_SUB_TYPE = "跟进心理";
    String MENTAL_INTERVENE_SUCCESS = "【{{username}}】跟进了【{{#studentName}}】心理";

    String MENTAL_UPDATE_STATUS_SUB_TYPE = "更新心理状态";
    String MENTAL_UPDATE_STATUS_SUCCESS = "更新了心理状态【{{#studentName}}】为【{{{#status}}】";


}
