package cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 用户车辆表图表数据 Response VO")
@Data
public class UserCarChartRespVO {

    @Schema(description = "车辆类型分布数据")
    private List<cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar.vo.UserCarChartRespVO.CarTypeDistributionVO> carTypeDistribution ;

    @Schema(description = "绑定车辆数")
    private Long bindCarCount;

    @Schema(description = "审核通过率")
    private BigDecimal auditPassRate;

    @Schema(description = "车辆类型分布数据")
    @Data
    public static class CarTypeDistributionVO {
        private String type;
        private Long count;
    }

}
