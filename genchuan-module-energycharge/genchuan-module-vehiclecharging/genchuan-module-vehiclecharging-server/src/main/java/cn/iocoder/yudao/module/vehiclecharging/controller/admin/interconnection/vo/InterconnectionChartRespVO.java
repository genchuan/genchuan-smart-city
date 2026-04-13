package cn.iocoder.yudao.module.vehiclecharging.controller.admin.interconnection.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Schema(description = "汽车充电 - 互联互通表图表数据 Response VO")
@Data
public class InterconnectionChartRespVO {

    @Schema(description = "对接总数量")
    private Integer totalCount;

    @Schema(description = "已开通对接数量")
    private Integer openedCount;

    @Schema(description = "审核中对接数量")
    private Integer auditingCount;

    @Schema(description = "未申请对接数量")
    private Integer waitApplyCount;

    @Schema(description = "已关闭对接数量")
    private Integer closedCount;

    @Schema(description = "对接状态占比数据")
    private List<InterconnectionStatusRatioVO> statusRatio;

    @Schema(description = "各合作方对接数量统计")
    private List<InterconnectionCooperatorCountVO> cooperatorCount;

    @Schema(description = "对接状态占比数据")
    @Data
    public static class InterconnectionStatusRatioVO {
        private String status;
        private Integer count;
        private BigDecimal ratio;
    }

    @Schema(description = "各合作方对接数量统计")
    @Data
    public static class InterconnectionCooperatorCountVO {
        private String cooperatorName;
        private Integer count;
    }

}
