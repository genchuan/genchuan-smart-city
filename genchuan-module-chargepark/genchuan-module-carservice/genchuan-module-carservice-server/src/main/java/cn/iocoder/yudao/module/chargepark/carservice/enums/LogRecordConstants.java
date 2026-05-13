package cn.iocoder.yudao.module.chargepark.carservice.enums;

/**
 * 业务操作日志常量
 * 配合 mzt-biz-log {@code @LogRecord} 使用,落 system_operate_log 表
 */
public interface LogRecordConstants {

    // ================ 车辆引导 - 空位推送 ================
    String SPACE_PUSH_TYPE = "车辆引导-空位推送";
    String SPACE_PUSH_PUSH_SUB = "推送空位";
    String SPACE_PUSH_PUSH_SUCCESS = "推送空位:用户{{#user}}/场站{{#station}}/{{#info}}";
    String SPACE_PUSH_BATCH_SUB = "批量推送空位";
    String SPACE_PUSH_BATCH_SUCCESS = "批量推送空位{{#count}}条:ids={{#ids}}";

    // ================ 投诉调解 - 用户申诉 ================
    String USER_APPEAL_TYPE = "投诉调解-用户申诉";
    String USER_APPEAL_AUDIT_SUB = "审核申诉";
    String USER_APPEAL_AUDIT_SUCCESS = "{{#verb}}申诉【{{#reqVO.id}}】{{#reason}}";
    String USER_APPEAL_BATCH_AUDIT_SUB = "批量审核申诉";
    String USER_APPEAL_BATCH_AUDIT_SUCCESS = "{{#verb}}批量审核申诉:{{#reqVO.ids}}{{#reason}}";
    String USER_APPEAL_EXECUTE_SUB = "处置申诉";
    String USER_APPEAL_EXECUTE_SUCCESS = "处置申诉【{{#reqVO.id}}】完成";
    String USER_APPEAL_FEEDBACK_SUB = "反馈申诉";
    String USER_APPEAL_FEEDBACK_SUCCESS = "反馈申诉【{{#reqVO.id}}】:{{#reqVO.feedbackContent}}";

    // ================ 投诉调解 - 纠纷调解 ================
    String DISPUTE_MEDIATE_TYPE = "投诉调解-纠纷调解";
    String DISPUTE_MEDIATE_MEDIATE_SUB = "开始调解";
    String DISPUTE_MEDIATE_MEDIATE_SUCCESS = "开始调解【{{#reqVO.id}}】";
    String DISPUTE_MEDIATE_PROGRESS_SUB = "更新调解进度";
    String DISPUTE_MEDIATE_PROGRESS_SUCCESS = "更新调解【{{#reqVO.id}}】进度:{{#reqVO.progress}}";
    String DISPUTE_MEDIATE_CONFIRM_SUB = "确认调解";
    String DISPUTE_MEDIATE_CONFIRM_SUCCESS = "确认调解【{{#reqVO.id}}】:{{#reqVO.confirmResult}}";

    // ================ 投诉调解 - 意见建议 ================
    String SUGGESTION_TYPE = "投诉调解-意见建议";
    String SUGGESTION_HANDLE_SUB = "处理意见";
    String SUGGESTION_HANDLE_SUCCESS = "受理意见【{{#reqVO.id}}】,转入处理中";
    String SUGGESTION_PROGRESS_SUB = "更新意见进度";
    String SUGGESTION_PROGRESS_SUCCESS = "更新意见【{{#reqVO.id}}】进度:{{#reqVO.progress}}";
    String SUGGESTION_FEEDBACK_SUB = "反馈意见";
    String SUGGESTION_FEEDBACK_SUCCESS = "反馈意见【{{#reqVO.id}}】:{{#reqVO.feedbackContent}}";

    // ================ 客服配置 - 话术管理 ================
    String WORDING_TYPE = "客服配置-话术管理";
    String WORDING_SAVE_SUB = "保存话术";
    String WORDING_SAVE_SUCCESS = "保存话术{{#count}}条";
    String WORDING_ENABLE_SUB = "启用话术";
    String WORDING_ENABLE_SUCCESS = "启用话术【{{#id}}】";
    String WORDING_DISABLE_SUB = "停用话术";
    String WORDING_DISABLE_SUCCESS = "停用话术【{{#id}}】";

    // ================ 救援服务 ================
    String RESCUE_TYPE = "救援服务";
    String RESCUE_DISPATCH_SUB = "派单";
    String RESCUE_DISPATCH_SUCCESS = "派单救援【{{#reqVO.id}}】给:{{#reqVO.rescueUserId}}";
    String RESCUE_BATCH_DISPATCH_SUB = "批量派单";
    String RESCUE_BATCH_DISPATCH_SUCCESS = "批量派单救援:{{#reqVO.ids}},处理人:{{#reqVO.rescueUserId}}";
    String RESCUE_CLAIM_SUB = "接单";
    String RESCUE_CLAIM_SUCCESS = "接单救援【{{#id}}】";
    String RESCUE_PROGRESS_SUB = "更新救援进度";
    String RESCUE_PROGRESS_SUCCESS = "更新救援【{{#reqVO.id}}】进度:{{#reqVO.progress}}";
    String RESCUE_TRANSFER_SUB = "转单";
    String RESCUE_TRANSFER_SUCCESS = "转单救援【{{#reqVO.id}}】给:{{#reqVO.newRescueUserId}},原因:{{#reqVO.transferReason}}";
    String RESCUE_COMPLETE_SUB = "完成救援";
    String RESCUE_COMPLETE_SUCCESS = "完成救援【{{#id}}】";
    String RESCUE_EVALUATE_SUB = "评价";
    String RESCUE_EVALUATE_SUCCESS = "评价救援【{{#reqVO.id}}】:{{#reqVO.score}}星";
    String RESCUE_ARCHIVE_SUB = "归档";
    String RESCUE_ARCHIVE_SUCCESS = "归档救援【{{#id}}】";

    // ================ 预约管理 ================
    String RESERVE_TYPE = "预约管理";
    String RESERVE_AUDIT_SUB = "审核预约";
    String RESERVE_AUDIT_SUCCESS = "{{#verb}}预约【{{#reqVO.id}}】{{#reason}}";
    String RESERVE_BATCH_AUDIT_SUB = "批量审核预约";
    String RESERVE_BATCH_AUDIT_SUCCESS = "{{#verb}}批量审核预约:{{#reqVO.ids}}{{#reason}}";
    String RESERVE_CANCEL_SUB = "取消预约";
    String RESERVE_CANCEL_SUCCESS = "取消预约【{{#id}}】";
    String RESERVE_COMPLETE_SUB = "完成预约";
    String RESERVE_COMPLETE_SUCCESS = "完成预约【{{#id}}】";
    String RESERVE_EVALUATE_SUB = "评价预约";
    String RESERVE_EVALUATE_SUCCESS = "评价预约【{{#reqVO.id}}】:{{#reqVO.score}}星";

}
