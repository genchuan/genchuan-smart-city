package cn.iocoder.yudao.module.waterdetection.controller.admin.dmapartition.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - DMA分区划分与调整新增/修改 Request VO")
@Data
public class DmaPartitionSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "分区ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分区ID不能为空")
    private String partitionId;

    @Schema(description = "分区名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分区名称不能为空")
    private String partitionName;

    @Schema(description = "覆盖行政村")
    private String coveredVillages;

    @Schema(description = "边界坐标")
    private String boundaryCoordinates;

    @Schema(description = "包含监测点ID")
    private String monitorPointIds;

    @Schema(description = "划分日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "划分日期不能为空")
    private LocalDateTime divisionDate;

    @Schema(description = "调整记录")
    private String adjustmentRecords;

}