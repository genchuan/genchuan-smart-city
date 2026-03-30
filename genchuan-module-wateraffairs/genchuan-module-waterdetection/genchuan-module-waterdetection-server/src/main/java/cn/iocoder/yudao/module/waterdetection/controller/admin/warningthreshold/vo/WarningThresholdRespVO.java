package cn.iocoder.yudao.module.waterdetection.controller.admin.warningthreshold.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 预警阈值管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class WarningThresholdRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "指标名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("指标名称")
    private String indicatorName;

    @Schema(description = "阈值类型(上限/下限)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("阈值类型(上限/下限)")
    private String thresholdType;

    @Schema(description = "阈值数值", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("阈值数值")
    private Double thresholdValue;

    @Schema(description = "单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("单位")
    private String unit;

    @Schema(description = "适用场景(如管网末梢)")
    @ExcelProperty("适用场景(如管网末梢)")
    private String applicableScene;

    @Schema(description = "生效时间")
    @ExcelProperty("生效时间")
    private LocalDateTime effectiveTime;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}