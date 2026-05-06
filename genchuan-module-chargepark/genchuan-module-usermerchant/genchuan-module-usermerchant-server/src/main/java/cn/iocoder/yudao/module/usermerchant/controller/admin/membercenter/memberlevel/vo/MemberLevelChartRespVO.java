package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberlevel.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 会员等级统计 Response VO")
@Data
public class MemberLevelChartRespVO {

    @Schema(description = "等级用户分布列表（柱状图数据）")
    private List<LevelUserDistributionVO> levelUserDistribution;

    @Schema(description = "等级数量")
    private Integer levelCount;

    @Schema(description = "等级升级率（非最低等级用户占比）")
    private BigDecimal levelUpgradeRate;

    @Data
    public static class LevelUserDistributionVO {
        @Schema(description = "等级名称", example = "普通会员")
        private String level;
        @Schema(description = "用户数量", example = "500")
        private Integer count;
    }
}