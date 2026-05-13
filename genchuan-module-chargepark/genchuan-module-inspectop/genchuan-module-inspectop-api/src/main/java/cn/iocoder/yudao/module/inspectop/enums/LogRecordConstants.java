package cn.iocoder.yudao.module.inspectop.enums;

/**
 * 巡查巡检 操作日志枚举
 * 目的：统一管理，也减少 Service 里各种“复杂”字符串
 *
 * @author zhucongquan
 */
public interface LogRecordConstants {

    // ======================= AssetCheck 资产盘点 =======================

    String ASSET_CHECK_TYPE = "资产盘点";
    String ASSET_CHECK_CREATE_SUB_TYPE = "创建资产盘点";
    String ASSET_CHECK_CREATE_SUCCESS = "创建了资产盘点【{{#createReqVO.type}}】";
    String ASSET_CHECK_UPDATE_SUB_TYPE = "更新资产盘点";
    String ASSET_CHECK_UPDATE_SUCCESS = "更新了资产盘点【ID:{{#updateReqVO.id}}】: {_DIFF{#updateReqVO}}";
    String ASSET_CHECK_DELETE_SUB_TYPE = "删除资产盘点";
    String ASSET_CHECK_DELETE_SUCCESS = "删除了资产盘点【ID:{{#id}}】";
    String ASSET_CHECK_DELETE_LIST_SUB_TYPE = "批量删除资产盘点";
    String ASSET_CHECK_DELETE_LIST_SUCCESS = "批量删除了资产盘点【ID:{{#ids}}】";
    String ASSET_CHECK_CREATE_SIMPLE_SUB_TYPE = "创建资产盘点(简易)";
    String ASSET_CHECK_CREATE_SIMPLE_SUCCESS = "创建了资产盘点【{{#createReqVO.type}}】";
    String ASSET_CHECK_EXECUTE_SUB_TYPE = "执行资产盘点";
    String ASSET_CHECK_EXECUTE_SUCCESS = "执行了资产盘点【ID:{{#executeReqVO.id}}】";
    String ASSET_CHECK_UPDATE_PROGRESS_SUB_TYPE = "更新资产盘点进度";
    String ASSET_CHECK_UPDATE_PROGRESS_SUCCESS = "更新了资产盘点【ID:{{#updateProgressReqVO.id}}】的进度为【{{#updateProgressReqVO.progress}}%】";
    String ASSET_CHECK_CONFIRM_SUB_TYPE = "确认资产盘点";
    String ASSET_CHECK_CONFIRM_SUCCESS = "确认了资产盘点【ID:{{#confirmReqVO.id}}】";

    // ======================= AssetInfo 资产信息 =======================

    String ASSET_INFO_TYPE = "资产信息";
    String ASSET_INFO_CREATE_SUB_TYPE = "创建资产信息";
    String ASSET_INFO_CREATE_SUCCESS = "创建了资产【{{#createReqVO.name}}】";
    String ASSET_INFO_UPDATE_SUB_TYPE = "更新资产信息";
    String ASSET_INFO_UPDATE_SUCCESS = "更新了资产【ID:{{#updateReqVO.id}}】: {_DIFF{#updateReqVO}}";
    String ASSET_INFO_DELETE_SUB_TYPE = "删除资产信息";
    String ASSET_INFO_DELETE_SUCCESS = "删除了资产【ID:{{#id}}】";
    String ASSET_INFO_DELETE_LIST_SUB_TYPE = "批量删除资产信息";
    String ASSET_INFO_DELETE_LIST_SUCCESS = "批量删除了资产【ID:{{#ids}}】";
    String ASSET_INFO_DISABLE_SUB_TYPE = "禁用资产";
    String ASSET_INFO_DISABLE_SUCCESS = "禁用了资产【ID:{{#id}}】";
    String ASSET_INFO_SCRAP_SUB_TYPE = "报废资产";
    String ASSET_INFO_SCRAP_SUCCESS = "报废了资产【ID:{{#scrapReqVO.id}}】，报废原因：{{#scrapReqVO.scrapRemark}}";
    String ASSET_INFO_IMPORT_SUB_TYPE = "导入资产信息";
    String ASSET_INFO_IMPORT_SUCCESS = "导入了资产信息，成功{{#successCount}}条，失败{{#failureCount}}条";

    // ======================= AssetStock 库存管理 =======================

    String ASSET_STOCK_TYPE = "库存管理";
    String ASSET_STOCK_CREATE_SUB_TYPE = "创建库存";
    String ASSET_STOCK_CREATE_SUCCESS = "创建了库存【资产ID:{{#createReqVO.assetId}}】";
    String ASSET_STOCK_UPDATE_SUB_TYPE = "更新库存";
    String ASSET_STOCK_UPDATE_SUCCESS = "更新了库存【ID:{{#updateReqVO.id}}】: {_DIFF{#updateReqVO}}";
    String ASSET_STOCK_DELETE_SUB_TYPE = "删除库存";
    String ASSET_STOCK_DELETE_SUCCESS = "删除了库存【ID:{{#id}}】";
    String ASSET_STOCK_DELETE_LIST_SUB_TYPE = "批量删除库存";
    String ASSET_STOCK_DELETE_LIST_SUCCESS = "批量删除了库存【ID:{{#ids}}】";
    String ASSET_STOCK_ALLOCATE_SUB_TYPE = "调拨库存";
    String ASSET_STOCK_ALLOCATE_SUCCESS = "从库存【ID:{{#allocateReqVO.id}}】调拨了{{#allocateReqVO.allocateCount}}个资产到场站【ID:{{#allocateReqVO.targetStationId}}】";
    String ASSET_STOCK_ALARM_SUB_TYPE = "库存告警";
    String ASSET_STOCK_ALARM_SUCCESS = "将库存【ID:{{#alarmReqVO.id}}】的状态更新为【{{#statusName}}】";

    // ======================= CycleReport 周期报表 =======================

    String CYCLE_REPORT_TYPE = "周期报表";
    String CYCLE_REPORT_GENERATE_SUB_TYPE = "生成周期报表";
    String CYCLE_REPORT_GENERATE_SUCCESS = "生成了周期报表【场站:{{#stationName}}，周期:{{#reportCycle}}，时间范围:{{#timeRange}}】";
    String CYCLE_REPORT_EXPORT_SUB_TYPE = "导出周期报表";
    String CYCLE_REPORT_EXPORT_SUCCESS = "导出了周期报表【ID:{{#id}}】";
    String CYCLE_REPORT_CHART_SUB_TYPE = "查看报表图表";
    String CYCLE_REPORT_CHART_SUCCESS = "查看了报表图表【场站:{{#stationName}}，时间范围:{{#timeRange}}】";

    // ======================= BikeChargeMonitor 两轮充电监测 =======================

    String BIKE_CHARGE_MONITOR_TYPE = "两轮充电监测";
    String BIKE_CHARGE_MONITOR_CREATE_SUB_TYPE = "创建两轮充电监测";
    String BIKE_CHARGE_MONITOR_CREATE_SUCCESS = "创建了两轮充电监测【设备编码:{{#createReqVO.deviceCode}}】";
    String BIKE_CHARGE_MONITOR_UPDATE_SUB_TYPE = "更新两轮充电监测";
    String BIKE_CHARGE_MONITOR_UPDATE_SUCCESS = "更新了两轮充电监测【ID:{{#updateReqVO.id}}】: {_DIFF{#updateReqVO}}";
    String BIKE_CHARGE_MONITOR_DELETE_SUB_TYPE = "删除两轮充电监测";
    String BIKE_CHARGE_MONITOR_DELETE_SUCCESS = "删除了两轮充电监测【ID:{{#id}}】";
    String BIKE_CHARGE_MONITOR_DELETE_LIST_SUB_TYPE = "批量删除两轮充电监测";
    String BIKE_CHARGE_MONITOR_DELETE_LIST_SUCCESS = "批量删除了两轮充电监测【ID:{{#ids}}】";
    String BIKE_CHARGE_MONITOR_ALARM_SUB_TYPE = "两轮充电监测告警";
    String BIKE_CHARGE_MONITOR_ALARM_SUCCESS = "更新了两轮充电监测【ID:{{#alarmReqVO.id}}】的告警备注为【{{#alarmReqVO.alarmRemark}}】";

    // ======================= CarChargeMonitor 汽车充电监测 =======================

    String CAR_CHARGE_MONITOR_TYPE = "汽车充电监测";
    String CAR_CHARGE_MONITOR_CREATE_SUB_TYPE = "创建汽车充电监测";
    String CAR_CHARGE_MONITOR_CREATE_SUCCESS = "创建了汽车充电监测【设备编码:{{#createReqVO.deviceCode}}】";
    String CAR_CHARGE_MONITOR_UPDATE_SUB_TYPE = "更新汽车充电监测";
    String CAR_CHARGE_MONITOR_UPDATE_SUCCESS = "更新了汽车充电监测【ID:{{#updateReqVO.id}}】: {_DIFF{#updateReqVO}}";
    String CAR_CHARGE_MONITOR_DELETE_SUB_TYPE = "删除汽车充电监测";
    String CAR_CHARGE_MONITOR_DELETE_SUCCESS = "删除了汽车充电监测【ID:{{#id}}】";
    String CAR_CHARGE_MONITOR_DELETE_LIST_SUB_TYPE = "批量删除汽车充电监测";
    String CAR_CHARGE_MONITOR_DELETE_LIST_SUCCESS = "批量删除了汽车充电监测【ID:{{#ids}}】";
    String CAR_CHARGE_MONITOR_ALARM_SUB_TYPE = "汽车充电监测告警";
    String CAR_CHARGE_MONITOR_ALARM_SUCCESS = "更新了汽车充电监测【ID:{{#alarmReqVO.id}}】的告警备注为【{{#alarmReqVO.alarmRemark}}】";

    // ======================= OilMonitor 油车占位监测 =======================

    String OIL_MONITOR_TYPE = "油车占位监测";
    String OIL_MONITOR_CREATE_SUB_TYPE = "创建油车占位监测";
    String OIL_MONITOR_CREATE_SUCCESS = "创建了油车占位监测【设备编码:{{#createReqVO.deviceCode}}】";
    String OIL_MONITOR_UPDATE_SUB_TYPE = "更新油车占位监测";
    String OIL_MONITOR_UPDATE_SUCCESS = "更新了油车占位监测【ID:{{#updateReqVO.id}}】: {_DIFF{#updateReqVO}}";
    String OIL_MONITOR_DELETE_SUB_TYPE = "删除油车占位监测";
    String OIL_MONITOR_DELETE_SUCCESS = "删除了油车占位监测【ID:{{#id}}】";
    String OIL_MONITOR_DELETE_LIST_SUB_TYPE = "批量删除油车占位监测";
    String OIL_MONITOR_DELETE_LIST_SUCCESS = "批量删除了油车占位监测【ID:{{#ids}}】";
    String OIL_MONITOR_BATCH_PROCESS_SUB_TYPE = "批量处理油车占位监测";
    String OIL_MONITOR_BATCH_PROCESS_SUCCESS = "批量处理了油车占位监测【ID:{{#ids}}】，更新状态为【{{#processStatusName}}】，进度为【{{#processProgress}}%】";
    String OIL_MONITOR_IGNORE_SUB_TYPE = "忽略油车占位监测";
    String OIL_MONITOR_IGNORE_SUCCESS = "忽略了油车占位监测【ID:{{#ignoreReqVO.id}}】，忽略理由：{{#ignoreReqVO.ignoreReason}}";

    // ======================= ShareChargeMonitor 共享充电监测 =======================

    String SHARE_CHARGE_MONITOR_TYPE = "共享充电监测";
    String SHARE_CHARGE_MONITOR_CREATE_SUB_TYPE = "创建共享充电监测";
    String SHARE_CHARGE_MONITOR_CREATE_SUCCESS = "创建了共享充电监测【设备编码:{{#createReqVO.deviceCode}}】";
    String SHARE_CHARGE_MONITOR_UPDATE_SUB_TYPE = "更新共享充电监测";
    String SHARE_CHARGE_MONITOR_UPDATE_SUCCESS = "更新了共享充电监测【ID:{{#updateReqVO.id}}】: {_DIFF{#updateReqVO}}";
    String SHARE_CHARGE_MONITOR_DELETE_SUB_TYPE = "删除共享充电监测";
    String SHARE_CHARGE_MONITOR_DELETE_SUCCESS = "删除了共享充电监测【ID:{{#id}}】";
    String SHARE_CHARGE_MONITOR_DELETE_LIST_SUB_TYPE = "批量删除共享充电监测";
    String SHARE_CHARGE_MONITOR_DELETE_LIST_SUCCESS = "批量删除了共享充电监测【ID:{{#ids}}】";
    String SHARE_CHARGE_MONITOR_ALARM_SUB_TYPE = "共享充电监测告警";
    String SHARE_CHARGE_MONITOR_ALARM_SUCCESS = "更新了共享充电监测【ID:{{#alarmReqVO.id}}】的告警备注为【{{#alarmReqVO.alarmRemark}}】";

// ======================= SpaceMonitor 车位状态监测 =======================

    String SPACE_MONITOR_TYPE = "车位状态监测";
    String SPACE_MONITOR_CREATE_SUB_TYPE = "创建车位状态监测";
    String SPACE_MONITOR_CREATE_SUCCESS = "创建了车位状态监测【设备编码:{{#createReqVO.deviceCode}}】";
    String SPACE_MONITOR_UPDATE_SUB_TYPE = "更新车位状态监测";
    String SPACE_MONITOR_UPDATE_SUCCESS = "更新了车位状态监测【ID:{{#updateReqVO.id}}】: {_DIFF{#updateReqVO}}";
    String SPACE_MONITOR_DELETE_SUB_TYPE = "删除车位状态监测";
    String SPACE_MONITOR_DELETE_SUCCESS = "删除了车位状态监测【ID:{{#id}}】";
    String SPACE_MONITOR_DELETE_LIST_SUB_TYPE = "批量删除车位状态监测";
    String SPACE_MONITOR_DELETE_LIST_SUCCESS = "批量删除了车位状态监测【ID:{{#ids}}】";
    String SPACE_MONITOR_UPDATE_ALARM_SUB_TYPE = "更新车位状态监测告警";
    String SPACE_MONITOR_UPDATE_ALARM_SUCCESS = "更新了车位状态监测【ID:{{#alarmReqVO.id}}】的告警状态为【告警】，告警备注：{{#alarmReqVO.alarmRemark}}";

    // ======================= FenceMgmt 电子围栏 =======================

    String FENCE_MGMT_TYPE = "电子围栏";
    String FENCE_MGMT_CREATE_SUB_TYPE = "创建电子围栏";
    String FENCE_MGMT_CREATE_SUCCESS = "创建了电子围栏【名称:{{#createReqVO.name}}】";
    String FENCE_MGMT_UPDATE_SUB_TYPE = "更新电子围栏";
    String FENCE_MGMT_UPDATE_SUCCESS = "更新了电子围栏【ID:{{#updateReqVO.id}}】: {_DIFF{#updateReqVO}}";
    String FENCE_MGMT_DELETE_SUB_TYPE = "删除电子围栏";
    String FENCE_MGMT_DELETE_SUCCESS = "删除了电子围栏【ID:{{#id}}】";
    String FENCE_MGMT_DELETE_LIST_SUB_TYPE = "批量删除电子围栏";
    String FENCE_MGMT_DELETE_LIST_SUCCESS = "批量删除了电子围栏【ID:{{#ids}}】";
    String FENCE_MGMT_ENABLE_SUB_TYPE = "启用电子围栏";
    String FENCE_MGMT_ENABLE_SUCCESS = "启用了电子围栏【ID:{{#id}}】";
    String FENCE_MGMT_DISABLE_SUB_TYPE = "禁用电子围栏";
    String FENCE_MGMT_DISABLE_SUCCESS = "禁用了电子围栏【ID:{{#id}}】";

    // ======================= HandoverLog 交接日志 =======================

    String HANDOVER_LOG_TYPE = "交接日志";
    String HANDOVER_LOG_CREATE_SUB_TYPE = "创建交接日志";
    String HANDOVER_LOG_CREATE_SUCCESS = "创建了交接日志【标题:{{#createReqVO.title}}】";
    String HANDOVER_LOG_UPDATE_SUB_TYPE = "更新交接日志";
    String HANDOVER_LOG_UPDATE_SUCCESS = "更新了交接日志【ID:{{#updateReqVO.id}}】: {_DIFF{#updateReqVO}}";
    String HANDOVER_LOG_DELETE_SUB_TYPE = "删除交接日志";
    String HANDOVER_LOG_DELETE_SUCCESS = "删除了交接日志【ID:{{#id}}】";
    String HANDOVER_LOG_DELETE_LIST_SUB_TYPE = "批量删除交接日志";
    String HANDOVER_LOG_DELETE_LIST_SUCCESS = "批量删除了交接日志【ID:{{#ids}}】";
    String HANDOVER_LOG_CONFIRM_SUB_TYPE = "确认交接日志";
    String HANDOVER_LOG_CONFIRM_SUCCESS = "确认了交接日志【ID:{{#reqVO.id}}】，确认用户ID:{{#currentUserId}}";

    // ======================= InspectPlan 巡检计划 =======================

    String INSPECT_PLAN_TYPE = "巡检计划";
    String INSPECT_PLAN_CREATE_SUB_TYPE = "创建巡检计划";
    String INSPECT_PLAN_CREATE_SUCCESS = "创建了巡检计划【名称:{{#createReqVO.name}}】";
    String INSPECT_PLAN_UPDATE_SUB_TYPE = "更新巡检计划";
    String INSPECT_PLAN_UPDATE_SUCCESS = "更新了巡检计划【ID:{{#updateReqVO.id}}】: {_DIFF{#updateReqVO}}";
    String INSPECT_PLAN_DELETE_SUB_TYPE = "删除巡检计划";
    String INSPECT_PLAN_DELETE_SUCCESS = "删除了巡检计划【ID:{{#id}}】";
    String INSPECT_PLAN_DELETE_LIST_SUB_TYPE = "批量删除巡检计划";
    String INSPECT_PLAN_DELETE_LIST_SUCCESS = "批量删除了巡检计划【ID:{{#ids}}】";
    String INSPECT_PLAN_IMPORT_SUB_TYPE = "导入巡检计划";
    String INSPECT_PLAN_IMPORT_SUCCESS = "导入了巡检计划，成功{{#successCount}}条，失败{{#failureCount}}条";
    String INSPECT_PLAN_UPDATE_STATUS_SUB_TYPE = "更新巡检计划状态";
    String INSPECT_PLAN_UPDATE_STATUS_SUCCESS = "更新了巡检计划【ID:{{#id}}】的状态为【{{#statusName}}】";

    // ======================= InspectReport 巡检上报 =======================

    String INSPECT_REPORT_TYPE = "巡检上报";
    String INSPECT_REPORT_CREATE_SUB_TYPE = "创建巡检上报";
    String INSPECT_REPORT_CREATE_SUCCESS = "创建了巡检上报【标题:{{#createReqVO.title}}】";
    String INSPECT_REPORT_UPDATE_SUB_TYPE = "更新巡检上报";
    String INSPECT_REPORT_UPDATE_SUCCESS = "更新了巡检上报【ID:{{#updateReqVO.id}}】: {_DIFF{#updateReqVO}}";
    String INSPECT_REPORT_DELETE_SUB_TYPE = "删除巡检上报";
    String INSPECT_REPORT_DELETE_SUCCESS = "删除了巡检上报【ID:{{#id}}】";
    String INSPECT_REPORT_DELETE_LIST_SUB_TYPE = "批量删除巡检上报";
    String INSPECT_REPORT_DELETE_LIST_SUCCESS = "批量删除了巡检上报【ID:{{#ids}}】";
    String INSPECT_REPORT_BATCH_AUDIT_SUB_TYPE = "批量审核巡检上报";
    String INSPECT_REPORT_BATCH_AUDIT_SUCCESS = "批量审核了巡检上报【ID:{{#ids}}】，审核结果：{{#auditResultName}}";
    String INSPECT_REPORT_APPROVE_SUB_TYPE = "审核通过巡检上报";
    String INSPECT_REPORT_APPROVE_SUCCESS = "审核通过了巡检上报【ID:{{#approveReqVO.id}}】，审核备注：{{#approveReqVO.auditRemark}}";
    String INSPECT_REPORT_REJECT_SUB_TYPE = "驳回巡检上报";
    String INSPECT_REPORT_REJECT_SUCCESS = "驳回了巡检上报【ID:{{#rejectReqVO.id}}】，驳回理由：{{#rejectReqVO.auditRemark}}";
    String INSPECT_REPORT_PROCESS_SUB_TYPE = "处置巡检上报";
    String INSPECT_REPORT_PROCESS_SUCCESS = "处置了巡检上报【ID:{{#processReqVO.id}}】，处置人ID：{{#processReqVO.processUserId}}";

    // ======================= InspectTask 巡检任务 =======================

    String INSPECT_TASK_TYPE = "巡检任务";
    String INSPECT_TASK_CREATE_SUB_TYPE = "创建巡检任务";
    String INSPECT_TASK_CREATE_SUCCESS = "创建了巡检任务【计划ID:{{#createReqVO.planId}}】";
    String INSPECT_TASK_UPDATE_SUB_TYPE = "更新巡检任务";
    String INSPECT_TASK_UPDATE_SUCCESS = "更新了巡检任务【ID:{{#updateReqVO.id}}】: {_DIFF{#updateReqVO}}";
    String INSPECT_TASK_DELETE_SUB_TYPE = "删除巡检任务";
    String INSPECT_TASK_DELETE_SUCCESS = "删除了巡检任务【ID:{{#id}}】";
    String INSPECT_TASK_DELETE_LIST_SUB_TYPE = "批量删除巡检任务";
    String INSPECT_TASK_DELETE_LIST_SUCCESS = "批量删除了巡检任务【ID:{{#ids}}】";
    String INSPECT_TASK_BATCH_DISPATCH_SUB_TYPE = "批量派发巡检任务";
    String INSPECT_TASK_BATCH_DISPATCH_SUCCESS = "批量派发了巡检任务【ID:{{#ids}}】给用户【ID:{{#userId}}】";
    String INSPECT_TASK_CLAIM_SUB_TYPE = "认领巡检任务";
    String INSPECT_TASK_CLAIM_SUCCESS = "认领了巡检任务【ID:{{#claimReqVO.id}}】";
    String INSPECT_TASK_UPDATE_PROGRESS_SUB_TYPE = "更新巡检任务进度";
    String INSPECT_TASK_UPDATE_PROGRESS_SUCCESS = "更新了巡检任务【ID:{{#updateProgressReqVO.id}}】的进度为【{{#updateProgressReqVO.progress}}%】";
    String INSPECT_TASK_TRANSFER_SUB_TYPE = "转派巡检任务";
    String INSPECT_TASK_TRANSFER_SUCCESS = "将巡检任务【ID:{{#transferReqVO.id}}】转派给用户【ID:{{#transferReqVO.targetUserId}}】";
    String INSPECT_TASK_ARCHIVE_SUB_TYPE = "归档巡检任务";
    String INSPECT_TASK_ARCHIVE_SUCCESS = "归档了巡检任务【ID:{{#archiveReqVO.id}}】";

    // ======================= InspectTrack 巡检轨迹 =======================

    String INSPECT_TRACK_TYPE = "巡检轨迹";
    String INSPECT_TRACK_CREATE_SUB_TYPE = "创建巡检轨迹";
    String INSPECT_TRACK_CREATE_SUCCESS = "创建了巡检轨迹【用户ID:{{#createReqVO.userId}}】";
    String INSPECT_TRACK_UPDATE_SUB_TYPE = "更新巡检轨迹";
    String INSPECT_TRACK_UPDATE_SUCCESS = "更新了巡检轨迹【ID:{{#updateReqVO.id}}】: {_DIFF{#updateReqVO}}";
    String INSPECT_TRACK_DELETE_SUB_TYPE = "删除巡检轨迹";
    String INSPECT_TRACK_DELETE_SUCCESS = "删除了巡检轨迹【ID:{{#id}}】";
    String INSPECT_TRACK_DELETE_LIST_SUB_TYPE = "批量删除巡检轨迹";
    String INSPECT_TRACK_DELETE_LIST_SUCCESS = "批量删除了巡检轨迹【ID:{{#ids}}】";
    String INSPECT_TRACK_CHECK_SUB_TYPE = "核查巡检轨迹";
    String INSPECT_TRACK_CHECK_SUCCESS = "核查了巡检轨迹【ID:{{#checkReqVO.id}}】，核查备注：{{#checkReqVO.checkRemark}}";

    // ======================= InspectUser 巡检人员 =======================

    String INSPECT_USER_TYPE = "巡检人员";
    String INSPECT_USER_CREATE_SUB_TYPE = "创建巡检人员";
    String INSPECT_USER_CREATE_SUCCESS = "创建了巡检人员【姓名:{{#createReqVO.name}}】";
    String INSPECT_USER_UPDATE_SUB_TYPE = "更新巡检人员";
    String INSPECT_USER_UPDATE_SUCCESS = "更新了巡检人员【ID:{{#updateReqVO.id}}】: {_DIFF{#updateReqVO}}";
    String INSPECT_USER_DELETE_SUB_TYPE = "删除巡检人员";
    String INSPECT_USER_DELETE_SUCCESS = "删除了巡检人员【ID:{{#id}}】";
    String INSPECT_USER_DELETE_LIST_SUB_TYPE = "批量删除巡检人员";
    String INSPECT_USER_DELETE_LIST_SUCCESS = "批量删除了巡检人员【ID:{{#ids}}】";
    String INSPECT_USER_IMPORT_SUB_TYPE = "导入巡检人员";
    String INSPECT_USER_IMPORT_SUCCESS = "导入了巡检人员，成功{{#successCount}}条，失败{{#failureCount}}条";
    String INSPECT_USER_ENABLE_SUB_TYPE = "启用巡检人员";
    String INSPECT_USER_ENABLE_SUCCESS = "启用了巡检人员【ID:{{#id}}】";
    String INSPECT_USER_DISABLE_SUB_TYPE = "禁用巡检人员";
    String INSPECT_USER_DISABLE_SUCCESS = "禁用了巡检人员【ID:{{#id}}】";

    // ======================= ScheduleView 排班查看 =======================

    String SCHEDULE_VIEW_TYPE = "排班查看";
    String SCHEDULE_VIEW_CREATE_SUB_TYPE = "创建排班查看";
    String SCHEDULE_VIEW_CREATE_SUCCESS = "创建了排班查看【用户ID:{{#createReqVO.userId}}，日期:{{#createReqVO.scheduleDate}}】";
    String SCHEDULE_VIEW_UPDATE_SUB_TYPE = "更新排班查看";
    String SCHEDULE_VIEW_UPDATE_SUCCESS = "更新了排班查看【ID:{{#updateReqVO.id}}】: {_DIFF{#updateReqVO}}";
    String SCHEDULE_VIEW_DELETE_SUB_TYPE = "删除排班查看";
    String SCHEDULE_VIEW_DELETE_SUCCESS = "删除了排班查看【ID:{{#id}}】";
    String SCHEDULE_VIEW_DELETE_LIST_SUB_TYPE = "批量删除排班查看";
    String SCHEDULE_VIEW_DELETE_LIST_SUCCESS = "批量删除了排班查看【ID:{{#ids}}】";
    String SCHEDULE_VIEW_APPLY_SHIFT_SUB_TYPE = "申请换班";
    String SCHEDULE_VIEW_APPLY_SHIFT_SUCCESS = "申请了换班【原排班ID:{{#reqVO.id}}，目标用户ID:{{#reqVO.targetUserId}}，新日期:{{#reqVO.newDate}}】";

    // ======================= ShiftApply 换班申请 =======================

    String SHIFT_APPLY_TYPE = "换班申请";
    String SHIFT_APPLY_CREATE_SUB_TYPE = "创建换班申请";
    String SHIFT_APPLY_CREATE_SUCCESS = "创建了换班申请【申请人ID:{{#createReqVO.applyUserId}}，目标用户ID:{{#createReqVO.targetUserId}}】";
    String SHIFT_APPLY_UPDATE_SUB_TYPE = "更新换班申请";
    String SHIFT_APPLY_UPDATE_SUCCESS = "更新了换班申请【ID:{{#updateReqVO.id}}】: {_DIFF{#updateReqVO}}";
    String SHIFT_APPLY_DELETE_SUB_TYPE = "删除换班申请";
    String SHIFT_APPLY_DELETE_SUCCESS = "删除了换班申请【ID:{{#id}}】";
    String SHIFT_APPLY_DELETE_LIST_SUB_TYPE = "批量删除换班申请";
    String SHIFT_APPLY_DELETE_LIST_SUCCESS = "批量删除了换班申请【ID:{{#ids}}】";
    String SHIFT_APPLY_BATCH_AUDIT_SUB_TYPE = "批量审核换班申请";
    String SHIFT_APPLY_BATCH_AUDIT_SUCCESS = "批量审核了换班申请【ID:{{#ids}}】，审核结果：{{#auditResultName}}，审核备注：{{#reqVO.auditRemark}}";
    String SHIFT_APPLY_APPROVE_SUB_TYPE = "通过换班申请";
    String SHIFT_APPLY_APPROVE_SUCCESS = "通过了换班申请【ID:{{#reqVO.id}}】，审核备注：{{#reqVO.auditRemark}}";
    String SHIFT_APPLY_REJECT_SUB_TYPE = "驳回换班申请";
    String SHIFT_APPLY_REJECT_SUCCESS = "驳回了换班申请【ID:{{#reqVO.id}}】，驳回理由：{{#reqVO.auditRemark}}";
    String SHIFT_APPLY_CONFIRM_SUB_TYPE = "确认换班申请";
    String SHIFT_APPLY_CONFIRM_SUCCESS = "确认了换班申请【ID:{{#reqVO.id}}】生效";
    String SHIFT_APPLY_REAPPLY_SUB_TYPE = "重新申请换班";
    String SHIFT_APPLY_REAPPLY_SUCCESS = "重新申请了换班【原申请ID:{{#reqVO.id}}】，新备注：{{#reqVO.newRemark}}";

    // ======================= SpareStock 备件仓储 =======================

    String SPARE_STOCK_TYPE = "备件仓储";
    String SPARE_STOCK_CREATE_SUB_TYPE = "创建备件库存";
    String SPARE_STOCK_CREATE_SUCCESS = "创建了备件库存【备件ID:{{#createReqVO.spareId}}】";
    String SPARE_STOCK_UPDATE_SUB_TYPE = "更新备件库存";
    String SPARE_STOCK_UPDATE_SUCCESS = "更新了备件库存【ID:{{#updateReqVO.id}}】: {_DIFF{#updateReqVO}}";
    String SPARE_STOCK_DELETE_SUB_TYPE = "删除备件库存";
    String SPARE_STOCK_DELETE_SUCCESS = "删除了备件库存【ID:{{#id}}】";
    String SPARE_STOCK_DELETE_LIST_SUB_TYPE = "批量删除备件库存";
    String SPARE_STOCK_DELETE_LIST_SUCCESS = "批量删除了备件库存【ID:{{#ids}}】";
    String SPARE_STOCK_IN_SUB_TYPE = "入库备件";
    String SPARE_STOCK_IN_SUCCESS = "入库了备件【备件ID:{{#reqVO.spareId}}】，入库数量：{{#reqVO.inCount}}";
    String SPARE_STOCK_OUT_SUB_TYPE = "出库备件";
    String SPARE_STOCK_OUT_SUCCESS = "出库了备件【备件ID:{{#reqVO.spareId}}】，出库数量：{{#reqVO.outCount}}，领用人：{{#reqVO.receiver}}";
    String SPARE_STOCK_REPLENISH_SUB_TYPE = "补货备件";
    String SPARE_STOCK_REPLENISH_SUCCESS = "补货了备件【ID:{{#reqVO.id}}】，补货数量：{{#reqVO.replenishCount}}";
}
