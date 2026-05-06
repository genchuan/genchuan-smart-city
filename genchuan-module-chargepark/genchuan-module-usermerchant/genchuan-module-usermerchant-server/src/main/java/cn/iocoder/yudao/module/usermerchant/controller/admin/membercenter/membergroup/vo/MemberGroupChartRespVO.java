package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membergroup.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 会员分组统计 Response VO")
@Data
public class MemberGroupChartRespVO {

    @Schema(description = "分组用户分布列表（饼图数据）")
    private List<GroupUserDistributionVO> groupUserDistribution;

    @Schema(description = "分组总数")
    private Integer groupCount;

    @Schema(description = "至少属于一个分组的用户数")
    private Integer groupUserCount;

    @Data
    public static class GroupUserDistributionVO {
        @Schema(description = "分组名称", example = "新用户组")
        private String group;
        @Schema(description = "用户数量", example = "300")
        private Integer count;
    }
}