package cn.iocoder.yudao.module.studentmgmt.controller.admin.clubmgmt.vo;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 各社团人数 / 类型分布统计 response VO")
@Data
public class ClubMgmtClubDistributionRespVO {

    @Schema(description = "各社团统计数据，包含社团名称、成员人数、社团类型", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<JSONObject> clubStatistics;
    @Schema(description = "各类型社团的成员人数分布，包含类型名称、对应成员总数", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<JSONObject> typeMemberDistribution;

}