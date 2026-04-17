package cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.plateauth.vo;

import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo.vo.UserInfoChartRespVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 用户车辆表图表数据 Response VO")
@Data
public class PlateAuthChartRespVO {

    @Schema(description = "用户增长趋势数据")
    private List<PlateAuthChartRespVO.AuthTrendVO> authTrend;

    @Schema(description = "认证数")
    private Long authCount;

    @Schema(description = "审核通过率")
    private BigDecimal authPassRate;

    @Schema(description = "用户增长趋势数据")
    @Data
    public static class AuthTrendVO {
        private String date;
        private Long count;
    }

}
