package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectemerexecrec.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 应急巡查执行记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InspectEmerExecRecPageReqVO extends PageParam {

    @Schema(description = "记录ID")
    private String emerExecId;

    @Schema(description = "记录编码")
    private String emerExecCode;

    @Schema(description = "关联应急计划ID")
    private String emerPlanId;

    @Schema(description = "关联应急计划名称")
    private String emerPlanName;

    @Schema(description = "关联应急事件ID")
    private String emerEvtId;

    @Schema(description = "关联应急事件名称")
    private String emerEvtName;

    @Schema(description = "关联任务ID")
    private String taskId;

    @Schema(description = "关联任务编码")
    private String taskCode;

    @Schema(description = "执行人员ID")
    private String personId;

    @Schema(description = "执行人员姓名")
    private String personName;

    @Schema(description = "执行开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] execStartTime;

    @Schema(description = "执行完成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] execCompleteTime;

    @Schema(description = "应急检查项明细")
    private String emerCkDetail;

    @Schema(description = "紧急级别")
    private String urgencyLevel;

    @Schema(description = "应急问题描述")
    private String emerProblemDesc;

    @Schema(description = "处置建议")
    private String disposalSugg;

    @Schema(description = "执行设备编码")
    private String deviceCode;

    @Schema(description = "应急地点坐标X")
    private BigDecimal emerLocX;

    @Schema(description = "应急地点坐标Y")
    private BigDecimal emerLocY;

    @Schema(description = "是否已推送指挥中心")
    private Boolean isPushedCmd;

    @Schema(description = "推送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] pushTime;

    @Schema(description = "创建人")
    private String bizCreateUser;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizCreateTime;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}