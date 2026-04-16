package cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 用户信息表图表数据 Response VO")
@Data
public class UserInfoChartRespVO {

    @Schema(description = "用户增长趋势数据")
    private List<UserGrowthTrendVO> userGrowthTrend;

    @Schema(description = "用户类型分布数据")
    private List<UserTypeDistributionVO> userTypeDistribution ;

    @Schema(description = "总用户数")
    private Long totalUserCount;

    @Schema(description = "新增用户数")
    private Long newUserCount;

    @Schema(description = "用户增长趋势数据")
    @Data
    public static class UserGrowthTrendVO {
        private String date;
        private Long count;
    }

    @Schema(description = "用户类型分布数据")
    @Data
    public static class UserTypeDistributionVO {
        private String type;
        private Long count;
    }

}
