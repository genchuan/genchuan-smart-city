package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/2 11:10
 */
@Data
public class GarbageAbnormalColumnAbnormalVO {
    /**
     * 责任人名称
     */
    @Schema(description = "名称", example = "张三")
    private String name;

    /**
     * 待处置异常数量
     */
    @Schema(description = "数量", example = "5")
    private Long value;
}