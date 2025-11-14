package cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatchcommand.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 调度指令分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DispatchCommandPageReqVO extends PageParam {

    @Schema(description = "指令ID，唯一编码，UUID生成", example = "26688")
    private String commandId;

    @Schema(description = "指令编号，系统自动生成，格式“CMD_DISP+年份（4位）+6位流水号”")
    private String commandNo;

    @Schema(description = "关联方案ID，关联调度方案表", example = "2730")
    private String planId;

    @Schema(description = "方案编号，与方案ID同步，关联调度方案表（biz_dispatch_plan）")
    private String planNo;

    @Schema(description = "执行单位ID，关联联动单位信息表", example = "5386")
    private String execUnitId;

    @Schema(description = "执行单位名称，与执行单位ID同步，联动单位信息表（biz_coop_unit_info）", example = "王五")
    private String execUnitName;

    @Schema(description = "任务内容，从方案同步，可补充细节，如“派遣2辆救援车，携带救生设备，1小时内到达XX内涝点”")
    private String taskContent;

    @Schema(description = "执行要求，如“注意行车安全，到达后立即反馈”")
    private String execRequirement;

    @Schema(description = "指令状态，待下发/已下发/已接收/执行中/已完成/已驳回，默认待下发", example = "2")
    private String commandStatus;

    @Schema(description = "下发人ID，指令下发人账号，已下发时必填，关联用户信息表（sys_user）", example = "16834")
    private String issueUserId;

    @Schema(description = "下发人姓名，与下发人ID同步，关联用户信息表（sys_user）", example = "赵六")
    private String issueUserName;

    @Schema(description = "下发时间，格式yyyy-MM-dd HH:mm:ss，已下发时必填")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] issueTime;

    @Schema(description = "接收人ID，执行单位接收人账号，已接收时必填，关联用户信息表（sys_user）", example = "737")
    private String receiveUserId;

    @Schema(description = "接收人姓名，与接收人ID同步，关联用户信息表（sys_user）", example = "赵六")
    private String receiveUserName;

    @Schema(description = "接收时间，格式yyyy-MM-dd HH:mm:ss，已接收时必填")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] receiveTime;

    @Schema(description = "执行反馈，如“已派遣2辆救援车，预计30分钟后到达”")
    private String execFeedback;

    @Schema(description = "完成时间，格式yyyy-MM-dd HH:mm:ss，已完成时必填")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] completeTime;

    @Schema(description = "分类扩展字段1，预留，如“指令紧急程度”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“指令紧急程度”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储执行现场照片路径")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储执行现场照片路径")
    private String extCommon2;

}