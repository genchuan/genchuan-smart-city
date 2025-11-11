package cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.datamap.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class DataMapRespVO {
    @Schema(description = "坐标X", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("坐标X")
    private BigDecimal coordX;

    @Schema(description = "坐标Y", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("坐标Y")
    private BigDecimal coordY;

    @Schema(description = "统计周期内该维度下事件上报总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "26160")
    @ExcelProperty("统计周期内该维度下事件上报总数")
    private Integer totalRptCount;

    @Schema(description = "事发坐标X", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事发坐标X")
    private BigDecimal incidentX;

    @Schema(description = "事发坐标Y", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事发坐标Y")
    private BigDecimal incidentY;
}
