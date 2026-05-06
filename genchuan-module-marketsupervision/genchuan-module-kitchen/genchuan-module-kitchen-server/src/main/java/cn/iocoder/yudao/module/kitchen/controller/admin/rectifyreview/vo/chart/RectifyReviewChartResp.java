package cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.chart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 管理后台 - 整改复审 统计响应 VO
 * 用途：复审状态统计卡片、全部统计
 *
 * @author 开发者
 */
@Schema(description = "管理后台 - 整改复审 卡片及环形图统计响应 VO")
@Data
public class RectifyReviewChartResp {

    @Schema(description = "待复审数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    private Integer pendingReviewCount;

    @Schema(description = "已下发数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "20")
    private Integer issuedCount;

    @Schema(description = "已撤销数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "5")
    private Integer canceledCount;

    @Schema(description = "已完成数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "15")
    private Integer completedCount;

    @Schema(description = "总计数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "50")
    private Integer totalCount;

    @Schema(description = "待复审占比", requiredMode = Schema.RequiredMode.REQUIRED, example = "20.00")
    private BigDecimal pendingReviewRatio;

    @Schema(description = "已下发占比", requiredMode = Schema.RequiredMode.REQUIRED, example = "40.00")
    private BigDecimal issuedRatio;

    @Schema(description = "已撤销占比", requiredMode = Schema.RequiredMode.REQUIRED, example = "10.00")
    private BigDecimal canceledRatio;

    @Schema(description = "已完成占比", requiredMode = Schema.RequiredMode.REQUIRED, example = "30.00")
    private BigDecimal completedRatio;

    // ====================== 新增：违规等级统计 ======================
    @Schema(description = "一般违规数量", example = "20")
    private Integer levelNormalCount;

    @Schema(description = "较重违规数量", example = "10")
    private Integer levelSeriousCount;

    @Schema(description = "严重违规数量", example = "5")
    private Integer levelVerySeriousCount;

    @Schema(description = "一般违规占比", example = "50.00")
    private BigDecimal levelNormalRatio;

    @Schema(description = "较重违规占比", example = "30.00")
    private BigDecimal levelSeriousRatio;

    @Schema(description = "严重违规占比", example = "20.00")
    private BigDecimal levelVerySeriousRatio;
}
