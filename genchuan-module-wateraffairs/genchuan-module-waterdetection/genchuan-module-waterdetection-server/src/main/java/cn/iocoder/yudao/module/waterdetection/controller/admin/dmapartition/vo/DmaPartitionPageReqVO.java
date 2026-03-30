package cn.iocoder.yudao.module.waterdetection.controller.admin.dmapartition.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - DMA分区划分与调整分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DmaPartitionPageReqVO extends PageParam {

    @Schema(description = "分区ID")
    private String partitionId;

    @Schema(description = "分区名称")
    private String partitionName;

    @Schema(description = "覆盖行政村")
    private String coveredVillages;

    @Schema(description = "边界坐标")
    private String boundaryCoordinates;

    @Schema(description = "包含监测点ID")
    private String monitorPointIds;

    @Schema(description = "划分日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] divisionDate;

    @Schema(description = "调整记录")
    private String adjustmentRecords;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}