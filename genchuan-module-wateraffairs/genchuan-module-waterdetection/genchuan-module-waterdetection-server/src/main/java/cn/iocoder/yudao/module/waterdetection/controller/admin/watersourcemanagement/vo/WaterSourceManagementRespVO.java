package cn.iocoder.yudao.module.waterdetection.controller.admin.watersourcemanagement.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 水源类型及属性管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class WaterSourceManagementRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "水源编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("水源编码")
    private String sourceCode;

    @Schema(description = "水源名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("水源名称")
    private String sourceName;

    @Schema(description = "水源类型")
    @ExcelProperty("水源类型")
    private String sourceType;

    @Schema(description = "经度")
    @ExcelProperty("经度")
    private Double longitude;

    @Schema(description = "纬度")
    @ExcelProperty("纬度")
    private Double latitude;

    @Schema(description = "所属行政区")
    @ExcelProperty("所属行政区")
    private String administrativeRegion;

    @Schema(description = "水源描述")
    @ExcelProperty("水源描述")
    private String sourceDescription;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}