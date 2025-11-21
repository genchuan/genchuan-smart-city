package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnhandletrace.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 预警告警处置跟踪分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EarlyWarnHandleTracePageReqVO extends PageParam {

    @Schema(description = "跟踪ID")
    private String handleTraceId;

    @Schema(description = "派单ID")
    private String dispatchId;

    @Schema(description = "派单编号")
    private String dispatchNo;

    @Schema(description = "预警ID")
    private String earlyWarnId;

    @Schema(description = "处置进度")
    private String handleProgress;

    @Schema(description = "进度描述")
    private String progressDesc;

    @Schema(description = "操作人ID")
    private String operUserId;

    @Schema(description = "操作人姓名")
    private String operUserName;

    @Schema(description = "操作时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] operTime;

    @Schema(description = "附件URL")
    private String attachUrl;

    @Schema(description = "是否关键节点")
    private String isKeyNode;

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