package cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupcar.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 集团车辆统计 Response VO")
@Data
public class GroupCarChartRespVO {

    @Schema(description = "车辆类型分布列表（柱状图数据）")
    private List<CarTypeDistributionVO> carTypeDistribution;

    @Schema(description = "绑定车辆数")
    private Integer bindCarCount;

    @Schema(description = "审核通过率")
    private BigDecimal auditPassRate;

    @Data
    public static class CarTypeDistributionVO {
        @Schema(description = "车辆类型", example = "小型车")
        private String type;
        @Schema(description = "数量", example = "600")
        private Integer count;
    }
}