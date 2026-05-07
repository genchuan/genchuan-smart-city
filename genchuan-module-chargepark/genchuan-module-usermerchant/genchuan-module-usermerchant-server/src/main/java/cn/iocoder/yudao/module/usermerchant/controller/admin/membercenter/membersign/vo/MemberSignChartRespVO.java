package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membersign.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 会员签到统计 Response VO")
@Data
public class MemberSignChartRespVO {

    @Schema(description = "签到趋势数据（折线图）")
    private List<SignTrendVO> signTrend;

    @Schema(description = "签到用户分布（柱状图，按等级）")
    private List<SignUserDistributionVO> signUserDistribution;

    @Schema(description = "今日签到数")
    private Integer todaySignCount;

    @Schema(description = "签到率（今日签到数 / 总会员数）")
    private BigDecimal signRate;

    @Data
    public static class SignTrendVO {
        @Schema(description = "日期", example = "2025-03-29")
        private String date;
        @Schema(description = "签到人数", example = "80")
        private Integer count;
    }

    @Data
    public static class SignUserDistributionVO {
        @Schema(description = "会员等级名称", example = "普通会员")
        private String type;
        @Schema(description = "签到人数", example = "60")
        private Integer count;
    }
}