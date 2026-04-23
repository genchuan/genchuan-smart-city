package cn.iocoder.yudao.module.studentmgmt.controller.admin.clubmgmt.vo;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 社团管理建档 response VO")
@Data
public class ClubMgmtChartRespVO {

    @Schema(description = "社团总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Long totalClubCount;
    @Schema(description = "社团成员总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
    private Long totalMemberCount;
    @Schema(description = "待审核入团申请数", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long pendingAuditCount;
    @Schema(description = "场馆申请总次数", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Long venueApplyCount;
    @Schema(description = "社团类型分布统计", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<JSONObject> clubTypeDistribution;
    @Schema(description = "每月入团申请趋势", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<JSONObject> monthlyApplyTrend;

}