package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.circle.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/2 11:15
 */
@Data
public class GarbageAbnormalCircleReviewVO {
    /**
     * 分类名称
     */
    @Schema(description = "复核结果/异常类型")
    private String name;
    /**
     * 分类值（数量/重量）
     */
    @Schema(description = "分类值", example = "数量/重量")
    private Double value;
    /**
     * 占比（百分比，保留2位小数）
     */
    @Schema(description = "占比", example = "百分比，保留2位小数")
    private Double proportion;
}