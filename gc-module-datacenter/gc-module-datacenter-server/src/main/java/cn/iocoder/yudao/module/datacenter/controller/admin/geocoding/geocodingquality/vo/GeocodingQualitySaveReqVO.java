package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingquality.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 地理编码数据质量统计报表新增/修改 Request VO")
@Data
public class GeocodingQualitySaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "质量统计ID")
    private String qualityStatId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期不能为空")
    private String statCycle;

    @Schema(description = "统计时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计时间不能为空")
    private LocalDateTime statTime;

    @Schema(description = "行政区划代码")
    private String adminCode;

    @Schema(description = "行政区划名称")
    private String adminName;

    @Schema(description = "检查数据总量")
    private Integer totalCheckCount;

    @Schema(description = "属性完整性正确率(%)")
    private BigDecimal attributeIntegrityRate;

    @Schema(description = "位置精度合格率(%)")
    private BigDecimal positionAccuracyRate;

    @Schema(description = "数据更新及时率(%)")
    private BigDecimal dataUpdateTimelyRate;

    @Schema(description = "属性错误数据数量")
    private Integer attributeErrorCount;

    @Schema(description = "位置精度不合格数据数量")
    private Integer positionErrorCount;

    @Schema(description = "未及时更新数据数量")
    private Integer updateDelayedCount;

    @Schema(description = "质量整改建议")
    private String qualityImprovementSuggestion;

    @Schema(description = "统计人")
    private String statUser;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "分类扩展字段1")
    private String extCategory1;

    @Schema(description = "分类扩展字段2")
    private String extCategory2;

    @Schema(description = "分类扩展字段3")
    private String extCategory3;

}