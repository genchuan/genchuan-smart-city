package cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.chart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 管理后台 - 处罚复审 统计响应 VO
 * 用途：复审状态统计卡片、饼图
 *
 * @author 开发者
 */
@Schema(description = "管理后台 - 处罚复审 卡片及饼图统计响应 VO")
@Data
public class PunishReviewChartResp {

    @Schema(description = "待复审数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    private Integer pendingReviewCount;

    @Schema(description = "已下发数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "20")
    private Integer issuedCount;

    @Schema(description = "已撤销数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "5")
    private Integer canceledCount;

    @Schema(description = "总计数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "35")
    private Integer totalCount;

    @Schema(description = "待复审占比", requiredMode = Schema.RequiredMode.REQUIRED, example = "28.57")
    private BigDecimal pendingReviewRatio;

    @Schema(description = "已下发占比", requiredMode = Schema.RequiredMode.REQUIRED, example = "57.14")
    private BigDecimal issuedRatio;

    @Schema(description = "已撤销占比", requiredMode = Schema.RequiredMode.REQUIRED, example = "14.29")
    private BigDecimal canceledRatio;

}
