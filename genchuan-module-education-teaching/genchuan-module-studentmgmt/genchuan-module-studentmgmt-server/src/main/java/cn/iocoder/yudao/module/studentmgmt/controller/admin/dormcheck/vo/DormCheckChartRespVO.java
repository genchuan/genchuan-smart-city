package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcheck.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 宿舍考勤预警看板 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DormCheckChartRespVO {

    @Schema(description = "总考勤人数")
    private Integer totalCheck;

    @Schema(description = "正常考勤人数")
    private Integer normalCount;

    @Schema(description = "异常考勤人数")
    private Integer abnormalCount;

    @Schema(description = "在寝率")
    private BigDecimal inRate;

    @Schema(description = "预警人数（晚归 / 未归人数）")
    private Integer warningCount;

    @Schema(description = "异常类型统计列表")
    private List<JSONObject> abnormalStats;
}
