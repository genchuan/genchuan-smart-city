package cn.iocoder.yudao.module.studentmgmt.controller.admin.staymgmt.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 报修管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class StayMgmtChartRespVO {

    @Schema(description = "总留宿申请数")
    private Integer totalStayCount;
    @Schema(description = "待确认留宿数")
    private Integer pendingConfirmCount;
    @Schema(description = "待审核留宿数")
    private Integer pendingAuditCount;
    @Schema(description = "已通过留宿数")
    private Integer passedCount;
    @Schema(description = "周末留宿趋势数据")
    private List<JSONObject> weekendTrend;
    @Schema(description = "留宿申请状态分布数据")
    private List<JSONObject> statusDistribution;

}
