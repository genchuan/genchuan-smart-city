package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnrelease.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 预警告警解除分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EarlyWarnReleasePageReqVO extends PageParam {

    @Schema(description = "解除ID")
    private String releaseId;

    @Schema(description = "预警ID")
    private String earlyWarnId;

    @Schema(description = "预警名称")
    private String earlyWarnName;

    @Schema(description = "解除申请人ID")
    private String applyUserId;

    @Schema(description = "解除申请人姓名")
    private String applyUserName;

    @Schema(description = "解除审核人ID")
    private String auditUserId;

    @Schema(description = "解除审核人姓名")
    private String auditUserName;

    @Schema(description = "解除状态")
    private String releaseStatus;

    @Schema(description = "解除原因")
    private String releaseReason;

    @Schema(description = "申请时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] applyTime;

    @Schema(description = "审核时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] auditTime;

    @Schema(description = "驳回原因")
    private String rejectReason;

    @Schema(description = "是否复发")
    private String isRecurrence;

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