package cn.iocoder.yudao.module.inspectop.controller.admin.assetstock.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "巡查巡检 - 库存管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssetStockRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "资产ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("资产ID")
    private Long assetId;

    @Schema(description = "资产名称")
    @ExcelProperty("资产名称")
    private String assetName;

    @Schema(description = "当前库存", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("当前库存")
    private Integer currentStock;

    @Schema(description = "预警阈值", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警阈值")
    private Integer warnThreshold;

    @Schema(description = "库存状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("库存状态")
    private String status;

    @Schema(description = "所属场站ID")
    @ExcelProperty("所属场站ID")
    private Long stationId;

    @Schema(description = "场站名称")
    @ExcelProperty("场站名称")
    private String stationName;

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