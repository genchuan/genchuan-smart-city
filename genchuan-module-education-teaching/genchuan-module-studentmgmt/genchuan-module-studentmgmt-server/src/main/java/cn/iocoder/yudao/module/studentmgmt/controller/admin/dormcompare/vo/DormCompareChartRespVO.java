package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcompare.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 宿舍评比 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DormCompareChartRespVO {

    @Schema(description = "总评比记录数", requiredMode = Schema.RequiredMode.REQUIRED, example = "4709")
    private Integer totalCompare;
    @Schema(description = "平均得分", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private BigDecimal avgScore;
    @Schema(description = "最高得分", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private BigDecimal highScore;
    @Schema(description = "最低得分", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private BigDecimal lowScore;
    @Schema(description = "宿舍得分统计列表")
    private List<JSONObject> dormStats;


}
