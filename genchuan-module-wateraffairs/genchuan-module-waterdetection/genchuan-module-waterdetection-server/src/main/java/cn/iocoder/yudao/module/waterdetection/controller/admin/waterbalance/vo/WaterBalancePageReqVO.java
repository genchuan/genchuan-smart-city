package cn.iocoder.yudao.module.waterdetection.controller.admin.waterbalance.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 水量平衡与漏损分析分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WaterBalancePageReqVO extends PageParam {

    @Schema(description = "分区ID")
    private String partitionId;

    @Schema(description = "统计周期(日/月/年)")
    private String statisticsPeriod;

    @Schema(description = "统计日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] statisticsDate;

    @Schema(description = "供水量(立方米)")
    private Double supplyVolume;

    @Schema(description = "售水量(立方米)")
    private Double salesVolume;

    @Schema(description = "合理损耗量(立方米)")
    private Double reasonableLoss;

    @Schema(description = "漏损量(立方米)")
    private Double leakageVolume;

    @Schema(description = "漏损率(%)")
    private Double leakageRate;

    @Schema(description = "是否超标(0否1是)")
    private Boolean isExceeded;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}