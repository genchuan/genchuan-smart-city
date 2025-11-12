package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonevtrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 基础监测事件报新增/修改 Request VO")
@Data
public class BasicMonEvtRptSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "报表ID")
    private String rptId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期不能为空")
    private String statCycle;

    @Schema(description = "统计周期名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期名称不能为空")
    private String statCycleName;

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划代码不能为空")
    private String adminCode;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划名称不能为空")
    private String adminName;

    @Schema(description = "事件类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "事件类型不能为空")
    private String evtType;

    @Schema(description = "事件总数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "事件总数不能为空")
    private Integer totalEvtCount;

    @Schema(description = "待处置事件数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "待处置事件数不能为空")
    private Integer pendEvtCount;

    @Schema(description = "处置中事件数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "处置中事件数不能为空")
    private Integer handlEvtCount;

    @Schema(description = "已办结事件数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "已办结事件数不能为空")
    private Integer completedEvtCount;

    @Schema(description = "事件办结率(%)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "事件办结率(%)不能为空")
    private BigDecimal evtCompleteRate;

    @Schema(description = "平均处置时长(分钟)")
    private BigDecimal avgDealEndure;

    @Schema(description = "报表生成时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "报表生成时间不能为空")
    private LocalDateTime rptCreateTime;

    @Schema(description = "报表生成人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "报表生成人不能为空")
    private Long rptCreateUser;

    @Schema(description = "报表备注")
    private String rptRemark;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}