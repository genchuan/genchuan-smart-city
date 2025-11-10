package cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.taskoverallmng.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 任务统筹管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskOverallMngPageReqVO extends PageParam {

    @Schema(description = "统筹ID，唯一编码，采用UUID生成", example = "953")
    private String overallId;

    @Schema(description = "任务编号，关联调度指令/跨域协调编号，如“CMD_DISP2025000001”")
    private String taskNo;

    @Schema(description = "任务名称，如“XX内涝救援冲锋舟调度”", example = "张三")
    private String taskName;

    @Schema(description = "任务类型：物资调度/队伍调度/车辆调度/设备调度/跨域协调，标识任务类型", example = "2")
    private String taskType;

    @Schema(description = "关联方案ID，关联调度方案表（biz_dispatch_plan）", example = "20153")
    private String planId;

    @Schema(description = "关联指令ID，关联调度指令表（biz_dispatch_command）", example = "27910")
    private String commandId;

    @Schema(description = "关联跨域ID，关联跨域调度协调表（biz_cross_region_dispatch）", example = "7185")
    private String crossId;

    @Schema(description = "任务状态：待执行/执行中/已完成/已取消/超时，根据关联表状态同步", example = "2")
    private String taskStatus;

    @Schema(description = "优先级：高/中/低，按事件等级设定（特急→高，紧急→中，一般→低）")
    private String priority;

    @Schema(description = "任务负责人账号，关联用户信息表（sys_user）", example = "10337")
    private String chargerId;

    @Schema(description = "任务负责人姓名，与负责人ID同步，关联用户信息表（sys_user）", example = "张三")
    private String chargerName;

    @Schema(description = "计划完成时间，格式：yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] planCompleteTime;

    @Schema(description = "实际完成时间，格式：yyyy-MM-dd HH:mm:ss，已完成时必填")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] actualCompleteTime;

    @Schema(description = "延迟时长，如“30分钟”“2小时”，超时任务填写")
    private String delayEndure;

    @Schema(description = "创建时间，格式：yyyy-MM-dd HH:mm:ss，系统自动生成")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "分类扩展字段1，预留，如“任务来源(本地/跨域)”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“任务来源(本地/跨域)”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储任务关联文档路径")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储任务关联文档路径")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}