package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.circle.all;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/26 13:32
 */
@Data
public class GarbageCollectionCircleAllVO {
    /**
     * 分类名称（如：厨余垃圾、执行中、区域1001）
     */
    @Schema(description = "分类名称", example = "如：厨余垃圾、执行中、区域1001")
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