package cn.iocoder.yudao.module.waterdetection.controller.admin.waterbalance.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 水量平衡与漏损分析新增/修改 Request VO")
@Data
public class WaterBalanceSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "分区ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分区ID不能为空")
    private String partitionId;

    @Schema(description = "统计周期(日/月/年)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期(日/月/年)不能为空")
    private String statisticsPeriod;

    @Schema(description = "统计日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计日期不能为空")
    private LocalDateTime statisticsDate;

    @Schema(description = "供水量(立方米)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "供水量(立方米)不能为空")
    private Double supplyVolume;

    @Schema(description = "售水量(立方米)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "售水量(立方米)不能为空")
    private Double salesVolume;

    @Schema(description = "合理损耗量(立方米)")
    private Double reasonableLoss;

    @Schema(description = "漏损量(立方米)")
    private Double leakageVolume;

    @Schema(description = "漏损率(%)")
    private Double leakageRate;

    @Schema(description = "是否超标(0否1是)")
    private Boolean isExceeded;

}