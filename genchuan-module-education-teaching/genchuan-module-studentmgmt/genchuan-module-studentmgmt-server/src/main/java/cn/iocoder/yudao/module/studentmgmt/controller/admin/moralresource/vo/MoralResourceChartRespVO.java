package cn.iocoder.yudao.module.studentmgmt.controller.admin.moralresource.vo;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;


@Schema(description = "管理后台 - 德育资源学习看板 Response VO")
@Data
public class MoralResourceChartRespVO {

    @Schema(description = "资源状态分布统计")
    private JSONObject statusCount;
    @Schema(description = "资源类型分布统计")
    private JSONObject resourceTypeCount;
    @Schema(description = "月度学习人数趋势")
    private List<JSONObject> learnTrend;
    @Schema(description = "月度学习完成率趋势")
    private List<JSONObject> rateTrend;


}