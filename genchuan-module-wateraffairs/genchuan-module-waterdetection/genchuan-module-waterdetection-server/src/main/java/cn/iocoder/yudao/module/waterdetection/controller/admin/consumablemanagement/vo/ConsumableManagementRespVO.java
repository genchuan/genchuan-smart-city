package cn.iocoder.yudao.module.waterdetection.controller.admin.consumablemanagement.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;


@Schema(description = "管理后台 - 耗材库存与更换管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ConsumableManagementRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "耗材ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("耗材ID")
    private String consumableId;

    @Schema(description = "耗材类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("耗材类型")
    private String consumableType;

    @Schema(description = "库存余量", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("库存余量")
    private Double stockQuantity;

    @Schema(description = "预警阈值", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警阈值")
    private Double warningThreshold;

    @Schema(description = "上次更换日期")
    @ExcelProperty("上次更换日期")
    private LocalDateTime lastReplacementDate;

    @Schema(description = "预计下次更换日期")
    @ExcelProperty("预计下次更换日期")
    private LocalDateTime nextReplacementDate;

    @Schema(description = "更换数量")
    @ExcelProperty("更换数量")
    private Double replacementQuantity;

    @Schema(description = "关联设备ID")
    @ExcelProperty("关联设备ID")
    private String relatedEquipmentId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}