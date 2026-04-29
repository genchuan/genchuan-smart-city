package cn.iocoder.yudao.module.usermerchant.controller.admin.creditmgmt.usercredit.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(description = "管理后台 - 用户信用统计响应 VO")
@Data
public class UserCreditChartRespVO {

    @Schema(description = "信用等级分布数据")
    private List<CreditLevelDistributionVO> creditLevelDistribution;

    @Schema(description = "平均信用分")
    private Integer avgCreditScore;

    @Schema(description = "低信用用户数")
    private Integer lowCreditUserCount;

    @Data
    public static class CreditLevelDistributionVO {
        @Schema(description = "信用等级", example = "优秀")
        private String level;
        @Schema(description = "用户数量", example = "500")
        private Integer count;
    }
}