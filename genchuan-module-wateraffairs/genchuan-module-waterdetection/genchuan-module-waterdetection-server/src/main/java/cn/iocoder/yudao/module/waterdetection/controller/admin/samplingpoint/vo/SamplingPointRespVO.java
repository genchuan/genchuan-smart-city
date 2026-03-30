package cn.iocoder.yudao.module.waterdetection.controller.admin.samplingpoint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 采样点规划 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SamplingPointRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "采样点编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("采样点编号")
    private String pointCode;

    @Schema(description = "经度", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("经度")
    private Double longitude;

    @Schema(description = "纬度", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("纬度")
    private Double latitude;

    @Schema(description = "类型(水源/水厂/管网/末梢)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("类型(水源/水厂/管网/末梢)")
    private String pointType;

    @Schema(description = "覆盖人口")
    @ExcelProperty("覆盖人口")
    private Double coveredPopulation;

    @Schema(description = "周边环境描述")
    @ExcelProperty("周边环境描述")
    private String surroundingDesc;

    @Schema(description = "规划依据")
    @ExcelProperty("规划依据")
    private String planningBasis;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}