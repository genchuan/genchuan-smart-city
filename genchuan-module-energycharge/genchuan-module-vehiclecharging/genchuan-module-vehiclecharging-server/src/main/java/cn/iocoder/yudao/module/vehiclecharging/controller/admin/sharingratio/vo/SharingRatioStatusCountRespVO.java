package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingratio.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 分账方案状态统计响应 VO")
@Data
public class SharingRatioStatusCountRespVO {

    @Schema(description = "各状态方案数量")
    private StatusCount statusCount;

    @Data
    public static class StatusCount {
        @Schema(description = "未生效方案数", example = "2")
        private Integer pending;
        @Schema(description = "已生效方案数", example = "18")
        private Integer valid;
        @Schema(description = "已失效方案数", example = "5")
        private Integer invalid;
    }
}