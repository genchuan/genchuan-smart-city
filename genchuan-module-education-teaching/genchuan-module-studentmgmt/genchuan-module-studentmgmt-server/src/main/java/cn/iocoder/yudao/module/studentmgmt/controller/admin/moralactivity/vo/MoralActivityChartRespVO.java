package cn.iocoder.yudao.module.studentmgmt.controller.admin.moralactivity.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 德育活动态势看板 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MoralActivityChartRespVO {

    @Schema(description = "活动状态分布统计")
    private JSONObject statusCount;

    @Schema(description = "活动类型分布统计")
    private JSONObject activityTypeCount;

    @Schema(description = "月度活动数量趋势")
    private List<JSONObject> monthTrend;

    @Schema(description = "月度参与人数趋势")
    private List<JSONObject> joinTrend;

}
