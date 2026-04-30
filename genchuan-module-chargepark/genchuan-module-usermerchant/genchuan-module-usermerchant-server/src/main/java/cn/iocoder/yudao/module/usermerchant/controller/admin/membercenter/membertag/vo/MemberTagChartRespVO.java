package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membertag.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 会员标签统计 Response VO")
@Data
public class MemberTagChartRespVO {

    @Schema(description = "标签分布数据（饼图）")
    private List<TagDistributionVO> tagDistribution;

    @Schema(description = "标签总数")
    private Integer tagCount;

    @Schema(description = "绑定标签的用户数（至少一个标签）")
    private Integer tagUserCount;

    @Data
    public static class TagDistributionVO {
        @Schema(description = "标签名称", example = "新用户")
        private String type;
        @Schema(description = "绑定用户数", example = "300")
        private Integer count;
    }
}