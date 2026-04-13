package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingratio.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(description = "汽车充电 - 各合作方分账比例占比响应 VO")
@Data
public class SharingRatioCooperatorRatioRespVO {

    @Schema(description = "合作方占比列表")
    private List<CooperatorRatio> list;

    @Data
    public static class CooperatorRatio {
        @Schema(description = "合作方名称", example = "XX 能源科技有限公司")
        private String name;
        @Schema(description = "分账方案数量占比（%）", example = "40")
        private Integer value;
    }
}