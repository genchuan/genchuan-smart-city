package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.column.all;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/26 14:17
 */
@Data
public class AreaCompletionRateColumnAllVO {
    /** 区域名称 */
    @Schema(description = "区域名称", example = "北京市")
    private String areaName;
    /** 平均完成率 */
    @Schema(description = "平均完成率", example = "25%")
    private BigDecimal completionRate;
}