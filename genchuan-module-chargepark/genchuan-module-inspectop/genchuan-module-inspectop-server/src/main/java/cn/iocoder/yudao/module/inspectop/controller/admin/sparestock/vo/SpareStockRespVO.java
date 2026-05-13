package cn.iocoder.yudao.module.inspectop.controller.admin.sparestock.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "巡查巡检 - 备件仓储 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SpareStockRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "备件ID", requiredMode = Schema.RequiredMode.REQUIRED)
//    @ExcelProperty("备件ID")
    private Long spareId;

    @Schema(description = "备件名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("备件名称")
    private String spareName;

    @Schema(description = "当前库存", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("当前库存")
    private Integer currentStock;

    @Schema(description = "库存状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("库存状态")
    private String status;

    @Schema(description = "入库时间")
    @ExcelProperty("入库时间")
    private LocalDateTime inTime;

    @Schema(description = "出库时间")
    @ExcelProperty("出库时间")
    private LocalDateTime outTime;

    @Schema(description = "供应商")
    @ExcelProperty("供应商")
    private String reserve1;

    @Schema(description = "领用人")
    @ExcelProperty("领用人")
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