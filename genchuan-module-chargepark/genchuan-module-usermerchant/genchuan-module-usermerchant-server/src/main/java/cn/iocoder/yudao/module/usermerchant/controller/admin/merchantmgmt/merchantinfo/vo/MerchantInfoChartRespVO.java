package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantinfo.vo;

import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo.vo.UserInfoChartRespVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 商户信息表图表数据 Response VO")
@Data
public class MerchantInfoChartRespVO {

    @Schema(description = "用户商户增长趋势数据")
    private List<MerchantInfoChartRespVO.MerchantGrowthTrendVO> merchantGrowthTrend;

    @Schema(description = "商户类型分布数据")
    private List<MerchantInfoChartRespVO.MerchantTypeDistributionVO> merchantTypeDistribution ;

    @Schema(description = "总商户数")
    private Long totalMerchantCount;

    @Schema(description = "新增商户数")
    private Long newMerchantCount;

    @Schema(description = "商户增长趋势数据")
    @Data
    public static class MerchantGrowthTrendVO {
        private String date;
        private Long count;
    }

    @Schema(description = "商户类型分布数据")
    @Data
    public static class MerchantTypeDistributionVO {
        private String type;
        private Long count;
    }

}
