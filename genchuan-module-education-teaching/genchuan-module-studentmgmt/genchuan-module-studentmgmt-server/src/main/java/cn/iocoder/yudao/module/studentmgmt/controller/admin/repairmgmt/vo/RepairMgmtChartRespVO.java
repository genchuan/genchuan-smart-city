package cn.iocoder.yudao.module.studentmgmt.controller.admin.repairmgmt.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 报修管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RepairMgmtChartRespVO {

    @Schema(description = "总报修次数")
    private Integer totalRepairCount;
    @Schema(description = "待派单报修数")
    private Integer pendingDispatchCount;
    @Schema(description = "维修中报修数")
    private Integer repairingCount;
    @Schema(description = "已维修报修数")
    private Integer repairedCount;
    @Schema(description = "已验收报修数")
    private Integer acceptedCount;
    @Schema(description = "每日报修趋势数据")
    private List<JSONObject> dailyTrend;
    @Schema(description = "报修类型分布数据")
    private List<JSONObject> typeDistribution;

}
