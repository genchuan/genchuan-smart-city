package cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.chart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 按月统计 子项
 */
@Schema(description = "处罚复审 - 月度统计项")
@Data
public class PunishReviewBarItemResp {

    @Schema(description = "时间", example = "2026-01")
    private String time;

    @Schema(description = "当月新增数量", example = "18")
    private Integer count;

}
