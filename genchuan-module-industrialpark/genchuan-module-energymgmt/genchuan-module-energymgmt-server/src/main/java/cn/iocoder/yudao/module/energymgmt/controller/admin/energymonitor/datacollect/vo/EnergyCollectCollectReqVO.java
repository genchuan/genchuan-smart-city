package cn.iocoder.yudao.module.energymgmt.controller.admin.energymonitor.datacollect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 能耗采集采集 Request VO")
@Data
public class EnergyCollectCollectReqVO {

    @Schema(description = "主键ID", example = "1")
    private Long id;

    @Schema(description = "采集时间", example = "2026-05-15 18:00:00")
    private LocalDateTime collectTime;

    @Schema(description = "采集状态：采集正常/采集异常", example = "采集异常")
    private String collectStatus;

    @Schema(description = "能耗数值", example = "20")
    private BigDecimal energyValue;

    @Schema(description = "异常次数", example = "1")
    private Integer exceptionCount;

}
