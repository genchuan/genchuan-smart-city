package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingquality.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 地理编码数据质量统计报表 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GeocodingQualityRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "质量统计ID")
    @ExcelProperty("质量统计ID")
    private String qualityStatId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期")
    private String statCycle;

    @Schema(description = "统计时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计时间")
    private LocalDateTime statTime;

    @Schema(description = "行政区划代码")
    @ExcelProperty("行政区划代码")
    private String adminCode;

    @Schema(description = "行政区划名称")
    @ExcelProperty("行政区划名称")
    private String adminName;

    @Schema(description = "检查数据总量")
    @ExcelProperty("检查数据总量")
    private Integer totalCheckCount;

    @Schema(description = "属性完整性正确率(%)")
    @ExcelProperty("属性完整性正确率(%)")
    private BigDecimal attributeIntegrityRate;

    @Schema(description = "位置精度合格率(%)")
    @ExcelProperty("位置精度合格率(%)")
    private BigDecimal positionAccuracyRate;

    @Schema(description = "数据更新及时率(%)")
    @ExcelProperty("数据更新及时率(%)")
    private BigDecimal dataUpdateTimelyRate;

    @Schema(description = "属性错误数据数量")
    @ExcelProperty("属性错误数据数量")
    private Integer attributeErrorCount;

    @Schema(description = "位置精度不合格数据数量")
    @ExcelProperty("位置精度不合格数据数量")
    private Integer positionErrorCount;

    @Schema(description = "未及时更新数据数量")
    @ExcelProperty("未及时更新数据数量")
    private Integer updateDelayedCount;

    @Schema(description = "质量整改建议")
    @ExcelProperty("质量整改建议")
    private String qualityImprovementSuggestion;

    @Schema(description = "统计人")
    @ExcelProperty("统计人")
    private String statUser;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCategory1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCategory2;

    @Schema(description = "分类扩展字段3")
    @ExcelProperty("分类扩展字段3")
    private String extCategory3;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}