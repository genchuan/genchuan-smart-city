package cn.iocoder.yudao.module.smartcity.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * System 错误码枚举类
 *
 * system 系统，使用 1-002-000-000 段
 */
public interface ErrorCodeConstants {
    // =============================================================================================================
    // =============================================== 行业应用模块 ==================================================
    // =============================================================================================================

    // ========== 警报处理类别  1_000_001 ==========
    ErrorCode ALARM_HANDLING_CATEGORY_NOT_EXISTS = new ErrorCode(1_000_001, "警报处理类别不存在");

    // ========== 风险管控 1_000_002==========
    ErrorCode RISK_CONTROL_NOT_EXISTS = new ErrorCode(1_000_002, "风险管控不存在");

    // ========== 养护考核评价 1_000_003==========
    ErrorCode ASSESSMENT_AND_EVALUATION_NOT_EXISTS = new ErrorCode(1_000_003, "养护考核评价不存在");

    // ========== 养护任务 1_000_004 ==========
    ErrorCode MAINTENANCE_TASKS_NOT_EXISTS = new ErrorCode(1_000_004, "养护任务不存在");

    // ========== 养护地块 1_000_005 ==========
    ErrorCode MAINTENANCE_PLOT_NOT_EXISTS = new ErrorCode(1_000_005, "养护地块不存在");

    // ========== 养护计划 1_000_006 ==========
    ErrorCode MAINTENANCE_PLAN_NOT_EXISTS = new ErrorCode(1_000_006, "养护计划不存在");

}
