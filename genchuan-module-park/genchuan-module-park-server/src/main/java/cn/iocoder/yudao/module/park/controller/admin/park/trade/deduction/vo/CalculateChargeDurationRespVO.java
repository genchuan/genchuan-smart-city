package cn.iocoder.yudao.module.park.controller.admin.park.trade.deduction.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 计算收费时长 Response VO")
@Data
public class CalculateChargeDurationRespVO {
    @Schema(description = "收费时长（分钟）", example = "240")
    Integer chargeDuration;
}
