package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalplatformrptdata.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 平台上报数据分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvalPlatformRptDataPageReqVO extends PageParam {

    @Schema(description = "上报数据ID")
    private String platformRptDataId;

    @Schema(description = "关联任务ID")
    private String evalTaskId;

    @Schema(description = "关联任务名称")
    private String evalTaskName;

    @Schema(description = "评价对象ID")
    private String evalObjectId;

    @Schema(description = "评价对象名称")
    private String evalObjectName;

    @Schema(description = "关联指标项ID")
    private String idxItemId;

    @Schema(description = "关联指标项名称")
    private String idxItemName;

    @Schema(description = "数据值")
    private String dataValue;

    @Schema(description = "数据单位")
    private String dataUnit;

    @Schema(description = "上报时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] rptTime;

    @Schema(description = "上报人ID")
    private String rptUserId;

    @Schema(description = "上报人姓名")
    private String rptUserName;

    @Schema(description = "数据状态")
    private String dataStatus;

    @Schema(description = "校验失败原因")
    private String verifyFailReason;

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