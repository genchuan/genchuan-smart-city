package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarntime.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 预警告警时间维度统计新增/修改 Request VO")
@Data
public class EarlyWarnTimeSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "统计ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计ID不能为空")
    private String timeStatId;

    @Schema(description = "统计维度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计维度不能为空")
    private String timeDimension;

    @Schema(description = "统计周期值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期值不能为空")
    private String statCycleValue;

    @Schema(description = "统计周期名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期名称不能为空")
    private String statCycleName;

    @Schema(description = "预警总数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "预警总数不能为空")
    private Integer totalWarnCount;

    @Schema(description = "主要预警类型")
    private String mainWarnType;

    @Schema(description = "主要预警类型数量")
    private Integer mainTypeCount;

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