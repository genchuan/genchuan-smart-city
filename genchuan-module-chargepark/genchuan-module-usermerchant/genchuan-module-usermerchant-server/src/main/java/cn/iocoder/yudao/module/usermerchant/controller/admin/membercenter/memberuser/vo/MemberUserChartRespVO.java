package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(description = "管理后台 - 会员统计 Response VO")
@Data
public class MemberUserChartRespVO {
    @Schema(description = "会员增长趋势")
    private List<MemberGrowthTrendVO> memberGrowthTrend;

    @Schema(description = "总会员数")
    private Integer totalMemberCount;

    @Schema(description = "新增会员数（近30天）")
    private Integer newMemberCount;

    @Data
    public static class MemberGrowthTrendVO {
        @Schema(description = "日期", example = "2025-01")
        private String date;
        @Schema(description = "数量", example = "200")
        private Integer count;
    }
}