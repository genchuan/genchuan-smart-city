package cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 集团信息表图表数据 Response VO")
@Data
public class GroupInfoChartRespVO {

    @Schema(description = "集团增长趋势数据")
    private List<GroupInfoChartRespVO.GroupGrowthTrendVO> groupGrowthTrend;

    @Schema(description = "总集团数")
    private Long totalGroupCount;

    @Schema(description = "新增集团数")
    private Long newGroupCount;

    @Schema(description = "认证量趋势数据")
    @Data
    public static class GroupGrowthTrendVO {
        private String date;
        private Long count;
    }

}
