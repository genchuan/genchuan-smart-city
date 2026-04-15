package cn.iocoder.yudao.module.inspectop.controller.admin.oilmonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 油车占位监测 Response VO")
@Data
@ExcelIgnoreUnannotated
public class OilMonitorRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "车位ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车位ID")
    private Long spaceId;

    @Schema(description = "场站ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("场站ID")
    private Long stationId;

    @Schema(description = "识别时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("识别时间")
    private LocalDateTime identifyTime;

    @Schema(description = "处置状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("处置状态")
    private String processStatus;

    @Schema(description = "处置人ID")
    @ExcelProperty("处置人ID")
    private Long processUserId;

    @Schema(description = "处置时间")
    @ExcelProperty("处置时间")
    private LocalDateTime processTime;

    @Schema(description = "忽略理由")
    @ExcelProperty("忽略理由")
    private String ignoreReason;

    @Schema(description = "处置进度")
    @ExcelProperty("处置进度")
    private Integer processProgress;

    @Schema(description = "经度")
    @ExcelProperty("经度")
    private BigDecimal longitude;

    @Schema(description = "纬度")
    @ExcelProperty("纬度")
    private BigDecimal latitude;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}