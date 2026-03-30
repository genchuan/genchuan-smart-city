package cn.iocoder.yudao.module.waterdetection.controller.admin.waterhydrologyparam.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 水源水文参数管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WaterHydrologyParamPageReqVO extends PageParam {

    @Schema(description = "监测时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] monitorTime;

    @Schema(description = "水位值(米)")
    private Double waterLevel;

    @Schema(description = "含水层厚度(米)")
    private Double aquiferThickness;

    @Schema(description = "渗透系数(m/d)")
    private Double permeabilityCoefficient;

    @Schema(description = "数据采集人")
    private String dataCollector;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}