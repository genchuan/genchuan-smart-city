package cn.iocoder.yudao.module.studentmgmt.controller.admin.accessapply.vo;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 审核 response VO")
@Data
public class AccessApplyChartRespVO {
    @Schema(description = "总申请次数")
    private Integer totalApplyCount;
    @Schema(description = "待审核申请数")
    private Integer pendingAuditCount;
    @Schema(description = "已通过申请数")
    private Integer passedCount;
    @Schema(description = "每日申请趋势数据")
    private List<JSONObject> dailyTrend;
    @Schema(description = "申请类型分布数据")
    private List<JSONObject> typeDistribution;


}