package cn.iocoder.yudao.module.vehiclecharging.controller.admin.interconnection.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "每日申请数量统计")
@Data
public class InterconnectionApplyDailyCountVO {
    @Schema(description = "日期", example = "2025-03-01")
    private String date;
    @Schema(description = "当日申请数量", example = "2")
    private Integer count;
}
