package cn.iocoder.yudao.module.waterdetection.controller.admin.dmapartition.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - DMA分区划分与调整 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DmaPartitionRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "分区ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分区ID")
    private String partitionId;

    @Schema(description = "分区名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分区名称")
    private String partitionName;

    @Schema(description = "覆盖行政村")
    @ExcelProperty("覆盖行政村")
    private String coveredVillages;

    @Schema(description = "边界坐标")
    @ExcelProperty("边界坐标")
    private String boundaryCoordinates;

    @Schema(description = "包含监测点ID")
    @ExcelProperty("包含监测点ID")
    private String monitorPointIds;

    @Schema(description = "划分日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("划分日期")
    private LocalDateTime divisionDate;

    @Schema(description = "调整记录")
    @ExcelProperty("调整记录")
    private String adjustmentRecords;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}