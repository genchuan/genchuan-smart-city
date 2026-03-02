package cn.iocoder.yudao.module.envirhealth.util.circle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/28 16:24
 */
@Data
public class CircleVO {
    /**
     * 分类名称
     */
    @Schema(description = "分类名称")
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