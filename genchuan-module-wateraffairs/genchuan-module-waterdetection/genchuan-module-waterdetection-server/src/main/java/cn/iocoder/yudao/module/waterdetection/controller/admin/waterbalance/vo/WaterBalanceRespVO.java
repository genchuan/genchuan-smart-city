package cn.iocoder.yudao.module.waterdetection.controller.admin.waterbalance.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 水量平衡与漏损分析 Response VO")
@Data
@ExcelIgnoreUnannotated
public class WaterBalanceRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "分区ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分区ID")
    private String partitionId;

    @Schema(description = "统计周期(日/月/年)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期(日/月/年)")
    private String statisticsPeriod;

    @Schema(description = "统计日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计日期")
    private LocalDateTime statisticsDate;

    @Schema(description = "供水量(立方米)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("供水量(立方米)")
    private Double supplyVolume;

    @Schema(description = "售水量(立方米)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("售水量(立方米)")
    private Double salesVolume;

    @Schema(description = "合理损耗量(立方米)")
    @ExcelProperty("合理损耗量(立方米)")
    private Double reasonableLoss;

    @Schema(description = "漏损量(立方米)")
    @ExcelProperty("漏损量(立方米)")
    private Double leakageVolume;

    @Schema(description = "漏损率(%)")
    @ExcelProperty("漏损率(%)")
    private Double leakageRate;

    @Schema(description = "是否超标(0否1是)")
    @ExcelProperty("是否超标(0否1是)")
    private Boolean isExceeded;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}