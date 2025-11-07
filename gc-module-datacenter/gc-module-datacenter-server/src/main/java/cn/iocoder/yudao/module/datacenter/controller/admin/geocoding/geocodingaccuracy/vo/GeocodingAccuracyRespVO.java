package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingaccuracy.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 位置精度标准配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GeocodingAccuracyRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15822")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "精度配置ID", example = "27116")
    @ExcelProperty("精度配置ID")
    private String accuracyConfigId;

    @Schema(description = "地形类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("地形类型")
    private String terrainType;

    @Schema(description = "点位中误差标准(米)")
    @ExcelProperty("点位中误差标准(米)")
    private BigDecimal positionErrorStd;

    @Schema(description = "最大限差(米)")
    @ExcelProperty("最大限差(米)")
    private BigDecimal maxTolerance;

    @Schema(description = "适用数据类型", example = "1")
    @ExcelProperty("适用数据类型")
    private String applicableDataType;

    @Schema(description = "比例尺说明")
    @ExcelProperty("比例尺说明")
    private String scaleDesc;

    @Schema(description = "启用状态(0-禁用,1-启用)", example = "1")
    @ExcelProperty("启用状态(0-禁用,1-启用)")
    private Integer enableStatus;

    @Schema(description = "配置人")
    @ExcelProperty("配置人")
    private String configUser;

    @Schema(description = "配置时间")
    @ExcelProperty("配置时间")
    private LocalDateTime configTime;

    @Schema(description = "备注", example = "你猜")
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