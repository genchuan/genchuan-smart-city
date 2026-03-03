package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.card.abnormal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/28 14:37
 */
@Data
public class GarbageAbnormalCardAbnormalRespVO {
    @Schema(description = "待处置异常总数", example = "25")
    private Long toHandleTotal;

    @Schema(description = "高优先级异常数", example = "8")
    private Long highPriorityTotal;

    @Schema(description = "超时未处理异常数", example = "12")
    private Long timeoutTotal;
}