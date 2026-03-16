package cn.iocoder.yudao.module.kitchen.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * System 错误码枚举类
 * system 系统，使用 1-002-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 整改通知书复审台账==========
    ErrorCode RECTIFY_REVIEW_NOT_EXISTS = new ErrorCode(500, "整改通知书复审台账不存在");
    // ========== AI告警消息==========
    ErrorCode AI_ALERT_MESSAGE_NOT_EXISTS = new ErrorCode(500, "AI告警消息不存在");
    // ========== 整改通知书==========
    ErrorCode RECTIFY_NOTICE_NOT_EXISTS = new ErrorCode(500, "整改通知书不存在");
    // ========== 撤销原因字典==========
    ErrorCode CANCEL_REASON_DICT_NOT_EXISTS = new ErrorCode(500, "撤销原因字典不存在");
    // ========== 违规等级字典==========
    ErrorCode ILLEGAL_LEVEL_DICT_NOT_EXISTS = new ErrorCode(500, "违规等级字典不存在");
    // ========== 违规类型字典==========
    ErrorCode ILLEGAL_TYPE_DICT_NOT_EXISTS = new ErrorCode(500, "违规类型字典不存在");

    // ========== 企业信息==========
    ErrorCode ENTERPRISE_INFO_NOT_EXISTS = new ErrorCode(500, "企业信息不存在");
    // ========== 企业整改记录==========
    ErrorCode ENT_RECTIFY_RECORD_NOT_EXISTS = new ErrorCode(500, "企业整改记录不存在");
    // ========== 执法复审总台账==========
    ErrorCode LAW_REVIEW_LEDGER_NOT_EXISTS = new ErrorCode(500, "执法复审总台账不存在");

}
