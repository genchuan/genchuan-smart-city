package cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatchcommand.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 调度指令 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DispatchCommandRespVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20531")
    @ExcelProperty("自增主键ID")
    private Long id;

    @Schema(description = "指令ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "26688")
    @ExcelProperty("指令ID，唯一编码，UUID生成")
    private String commandId;

    @Schema(description = "指令编号，系统自动生成，格式“CMD_DISP+年份（4位）+6位流水号”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("指令编号，系统自动生成，格式“CMD_DISP+年份（4位）+6位流水号”")
    private String commandNo;

    @Schema(description = "关联方案ID，关联调度方案表", requiredMode = Schema.RequiredMode.REQUIRED, example = "2730")
    @ExcelProperty("关联方案ID，关联调度方案表")
    private String planId;

    @Schema(description = "方案编号，与方案ID同步，关联调度方案表（biz_dispatch_plan）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("方案编号，与方案ID同步，关联调度方案表（biz_dispatch_plan）")
    private String planNo;

    @Schema(description = "执行单位ID，关联联动单位信息表", requiredMode = Schema.RequiredMode.REQUIRED, example = "5386")
    @ExcelProperty("执行单位ID，关联联动单位信息表")
    private String execUnitId;

    @Schema(description = "执行单位名称，与执行单位ID同步，联动单位信息表（biz_coop_unit_info）", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("执行单位名称，与执行单位ID同步，联动单位信息表（biz_coop_unit_info）")
    private String execUnitName;

    @Schema(description = "任务内容，从方案同步，可补充细节，如“派遣2辆救援车，携带救生设备，1小时内到达XX内涝点”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("任务内容，从方案同步，可补充细节，如“派遣2辆救援车，携带救生设备，1小时内到达XX内涝点”")
    private String taskContent;

    @Schema(description = "执行要求，如“注意行车安全，到达后立即反馈”")
    @ExcelProperty("执行要求，如“注意行车安全，到达后立即反馈”")
    private String execRequirement;

    @Schema(description = "指令状态，待下发/已下发/已接收/执行中/已完成/已驳回，默认待下发", example = "2")
    @ExcelProperty("指令状态，待下发/已下发/已接收/执行中/已完成/已驳回，默认待下发")
    private String commandStatus;

    @Schema(description = "下发人ID，指令下发人账号，已下发时必填，关联用户信息表（sys_user）", example = "16834")
    @ExcelProperty("下发人ID，指令下发人账号，已下发时必填，关联用户信息表（sys_user）")
    private String issueUserId;

    @Schema(description = "下发人姓名，与下发人ID同步，关联用户信息表（sys_user）", example = "赵六")
    @ExcelProperty("下发人姓名，与下发人ID同步，关联用户信息表（sys_user）")
    private String issueUserName;

    @Schema(description = "下发时间，格式yyyy-MM-dd HH:mm:ss，已下发时必填")
    @ExcelProperty("下发时间，格式yyyy-MM-dd HH:mm:ss，已下发时必填")
    private LocalDateTime issueTime;

    @Schema(description = "接收人ID，执行单位接收人账号，已接收时必填，关联用户信息表（sys_user）", example = "737")
    @ExcelProperty("接收人ID，执行单位接收人账号，已接收时必填，关联用户信息表（sys_user）")
    private String receiveUserId;

    @Schema(description = "接收人姓名，与接收人ID同步，关联用户信息表（sys_user）", example = "赵六")
    @ExcelProperty("接收人姓名，与接收人ID同步，关联用户信息表（sys_user）")
    private String receiveUserName;

    @Schema(description = "接收时间，格式yyyy-MM-dd HH:mm:ss，已接收时必填")
    @ExcelProperty("接收时间，格式yyyy-MM-dd HH:mm:ss，已接收时必填")
    private LocalDateTime receiveTime;

    @Schema(description = "执行反馈，如“已派遣2辆救援车，预计30分钟后到达”")
    @ExcelProperty("执行反馈，如“已派遣2辆救援车，预计30分钟后到达”")
    private String execFeedback;

    @Schema(description = "完成时间，格式yyyy-MM-dd HH:mm:ss，已完成时必填")
    @ExcelProperty("完成时间，格式yyyy-MM-dd HH:mm:ss，已完成时必填")
    private LocalDateTime completeTime;

    @Schema(description = "分类扩展字段1，预留，如“指令紧急程度”")
    @ExcelProperty("分类扩展字段1，预留，如“指令紧急程度”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“指令紧急程度”")
    @ExcelProperty("分类扩展字段2，预留，如“指令紧急程度”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储执行现场照片路径")
    @ExcelProperty("通用扩展字段1，预留，存储执行现场照片路径")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储执行现场照片路径")
    @ExcelProperty("通用扩展字段2，预留，存储执行现场照片路径")
    private String extCommon2;

}