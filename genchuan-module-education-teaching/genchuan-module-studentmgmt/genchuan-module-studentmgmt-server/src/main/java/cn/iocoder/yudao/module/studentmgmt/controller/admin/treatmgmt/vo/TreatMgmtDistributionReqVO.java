package cn.iocoder.yudao.module.studentmgmt.controller.admin.treatmgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 就诊类型 / 年级分布统计 Response VO")
@Data
public class TreatMgmtDistributionReqVO {

    @Schema(description = "总就诊次数")
    private Integer totalTreatCount;
    @Schema(description = "待审核预约数")
    private Integer pendingAuditCount;
    @Schema(description = "已完成就诊数")
    private Integer finishedTreatCount;
    @Schema(description = "门诊就诊数")
    private Integer outpatientCount;
    @Schema(description = "急诊就诊数")
    private Integer emergencyCount;
    @Schema(description = "其他就诊数")
    private Integer otherCount;
    @Schema(description = "近一周就诊趋势数据")
    @ExcelProperty("近一周就诊趋势数据")
    private List<JSONObject> recentWeekTreatTrend;


}