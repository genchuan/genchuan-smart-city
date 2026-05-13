package cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.chart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 管理后台 - 处罚复审 撤销原因饼图 响应 VO
 */
@Schema(description = "处罚复审 - 撤销原因饼图 响应VO")
@Data
public class PunishReviewCancelReasonResp {

    @Schema(description = "撤销原因统计列表")
    private List<CancelReasonItem> list;

    @Schema(description = "撤销总数量")
    private Integer totalCount;

    @Data
    public static class CancelReasonItem {

        @Schema(description = "撤销原因名称", example = "证据不足")
        private String reasonName;

        @Schema(description = "数量", example = "8")
        private Integer count;

        @Schema(description = "占比", example = "40.00")
        private BigDecimal ratio;
    }
}
