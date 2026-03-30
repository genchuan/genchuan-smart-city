package cn.iocoder.yudao.module.waterdetection.controller.admin.waterprotectionarea.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 水源保护区管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class WaterProtectionAreaRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED, example = "31644")
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "保护区级别", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("保护区级别")
    private String protectionLevel;

    @Schema(description = "边界经纬度范围")
    @ExcelProperty("边界经纬度范围")
    private String boundaryRange;

    @Schema(description = "标识牌编号")
    @ExcelProperty("标识牌编号")
    private String signboardNo;

    @Schema(description = "标识牌位置")
    @ExcelProperty("标识牌位置")
    private String signboardLocation;

    @Schema(description = "安装时间")
    @ExcelProperty("安装时间")
    private LocalDateTime installTime;

    @Schema(description = "维护记录")
    @ExcelProperty("维护记录")
    private String maintenanceRecord;

    @Schema(description = "污染源治理状态", example = "2")
    @ExcelProperty("污染源治理状态")
    private String pollutionStatus;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}