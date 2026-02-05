package cn.iocoder.yudao.module.park.controller.admin.park.trade.deduction.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 计算收费时长 Request VO")
@Data
public class CalculateChargeDurationReqVO {
    @Schema(description = "入场时间", example = "2026-01-01 12:00:00")
    LocalDateTime entryTime;

    @Schema(description = "出场时间", example = "2026-01-01 16:00:00")
    LocalDateTime exitTime;

    @Schema(description = "畅停卡Id", example = "1")
    Long smoothParkingCardId;
}
