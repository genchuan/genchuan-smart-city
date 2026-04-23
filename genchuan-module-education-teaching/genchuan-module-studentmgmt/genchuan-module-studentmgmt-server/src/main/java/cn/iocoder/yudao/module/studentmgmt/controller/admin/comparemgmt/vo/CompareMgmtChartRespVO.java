package cn.iocoder.yudao.module.studentmgmt.controller.admin.comparemgmt.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 德育评比排名看板 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CompareMgmtChartRespVO {

    @Schema(description = "班级排名列表", requiredMode = Schema.RequiredMode.REQUIRED, example = "[\n" +
            "{\"className\": \"高一 (1) 班\", \"totalScore\":92.5, \"rank\":1},\n" +
            "{\"className\": \"高一 (3) 班\", \"totalScore\":90.0, \"rank\":2},\n" +
            "{\"className\": \"高一 (2) 班\", \"totalScore\":88.0, \"rank\":3}\n" +
            "]")
    @ExcelProperty("班级排名列表")
    private List<JSONObject> rankList;
    @Schema(description = "状态分布统计", requiredMode = Schema.RequiredMode.REQUIRED, example = "{\"scoringCount\": 10, \"finishedCount\": 5}")
    @ExcelProperty("状态分布统计")
    private JSONObject statusCount;
    @Schema(description = "周期分布统计", requiredMode = Schema.RequiredMode.REQUIRED, example = "{\"weekCount\": 10, \"monthCount\": 5, \"termCount\": 2}")
    @ExcelProperty("周期分布统计")
    private JSONObject cycleCount;


}
