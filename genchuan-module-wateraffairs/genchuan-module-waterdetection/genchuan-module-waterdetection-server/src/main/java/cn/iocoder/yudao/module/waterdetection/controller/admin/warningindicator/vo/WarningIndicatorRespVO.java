package cn.iocoder.yudao.module.waterdetection.controller.admin.warningindicator.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 预警指标配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class WarningIndicatorRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "预警指标名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警指标名称")
    private String indicatorName;

    @Schema(description = "指标类型(水质/设备)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("指标类型(水质/设备)")
    private String indicatorType;

    @Schema(description = "关联监测点类型(水源/水厂/管网)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联监测点类型(水源/水厂/管网)")
    private String relatedPointType;

    @Schema(description = "数据来源(在线监测/人工检测)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("数据来源(在线监测/人工检测)")
    private String dataSource;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}