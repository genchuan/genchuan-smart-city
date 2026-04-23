package cn.iocoder.yudao.module.studentmgmt.controller.admin.targetmgmt.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 指标管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TargetMgmtChartRespVO {

    @Schema(description = "状态分布统计", requiredMode = Schema.RequiredMode.REQUIRED, example = " {\"unEnableCount\": 2, \"enabledCount\": 8}")
    @ExcelProperty("状态分布统计")
    private JSONObject statusCount;
    @Schema(description = "评价人类型分布统计", requiredMode = Schema.RequiredMode.REQUIRED, example = " {\"teacherCount\": 2, \"parentCount\": 8, \"leaderCount\": 5}")
    @ExcelProperty("评价人类型分布统计")
    private JSONObject evaluatorTypeCount;
    @Schema(description = "计分方式分布统计", requiredMode = Schema.RequiredMode.REQUIRED, example = " {\"accumulateCount\": 2, \"apiCount\": 8}")
    @ExcelProperty("计分方式分布统计")
    private JSONObject scoreTypeCount;
    @Schema(description = "指标得分分布数据", requiredMode = Schema.RequiredMode.REQUIRED, example = " [{\"interval\": \"0-10\", \"count\": 2}, {\"interval\": \"10-20\", \"count\": 8}, {\"interval\": \"20-30\", \"count\": 5}]")
    @ExcelProperty("指标得分分布数据")
    private List<JSONObject> scoreDistribution;

}
