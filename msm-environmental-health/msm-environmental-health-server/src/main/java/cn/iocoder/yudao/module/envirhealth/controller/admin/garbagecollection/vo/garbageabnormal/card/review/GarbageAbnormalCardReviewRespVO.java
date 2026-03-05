package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.card.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/28 14:37
 */
@Data
public class GarbageAbnormalCardReviewRespVO {

    @Schema(description = "待复核异常数", example = "25")
    private Long reviewTotal;

    @Schema(description = "已通过数", example = "8")
    private Long passedCount;

    @Schema(description = "已退回数", example = "12")
    private Long returnCount;
}