package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmonevt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 按监测事件预警告警统计新增/修改 Request VO")
@Data
public class EarlyWarnMonEvtSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "统计ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计ID不能为空")
    private String warnMonEvtStatId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期不能为空")
    private String statCycle;

    @Schema(description = "事件大类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "事件大类ID不能为空")
    private String evtMajorId;

    @Schema(description = "事件大类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "事件大类名称不能为空")
    private String evtMajorName;

    @Schema(description = "事件小类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "事件小类ID不能为空")
    private String evtMinorId;

    @Schema(description = "事件小类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "事件小类名称不能为空")
    private String evtMinorName;

    @Schema(description = "预警总数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "预警总数不能为空")
    private Integer totalWarnCount;

    @Schema(description = "平均响应时间")
    private BigDecimal avgRespTime;

    @Schema(description = "涉及区域数")
    private Integer regionCount;

    @Schema(description = "已办结数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "已办结数不能为空")
    private Integer completedCount;

    @Schema(description = "处置率", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "处置率不能为空")
    private BigDecimal handleRate;

    @Schema(description = "统计人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计人不能为空")
    private String statUserId;

    @Schema(description = "统计时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计时间不能为空")
    private LocalDateTime statTime;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}