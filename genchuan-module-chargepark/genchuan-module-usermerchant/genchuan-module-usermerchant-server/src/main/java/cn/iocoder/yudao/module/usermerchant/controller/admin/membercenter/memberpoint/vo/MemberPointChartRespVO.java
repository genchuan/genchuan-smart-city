package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberpoint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 会员积分统计 Response VO")
@Data
public class MemberPointChartRespVO {

    @Schema(description = "积分趋势数据（折线图）")
    private List<PointTrendVO> pointTrend;

    @Schema(description = "总积分（所有用户当前积分总和）")
    private Long totalPoint;

    @Schema(description = "积分变动量（时间范围内的净变动总额）")
    private Long pointChangeCount;

    @Data
    public static class PointTrendVO {
        @Schema(description = "日期", example = "2025-01")
        private String date;
        @Schema(description = "净变动积分", example = "10000")
        private Long count;
    }
}