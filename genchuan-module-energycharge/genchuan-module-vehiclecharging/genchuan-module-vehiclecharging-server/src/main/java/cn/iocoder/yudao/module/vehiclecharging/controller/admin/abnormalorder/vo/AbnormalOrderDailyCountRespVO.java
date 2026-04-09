package cn.iocoder.yudao.module.vehiclecharging.controller.admin.abnormalorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 每日异常订单统计 Response VO")
@Data
public class AbnormalOrderDailyCountRespVO {

    @Schema(description = "日期 yyyy-MM-dd", example = "2025-03-01")
    private String date;

    @Schema(description = "当日异常订单数", example = "5")
    private Integer abnormalCount;

    @Schema(description = "当日处理完成数", example = "4")
    private Integer handleCount;
}