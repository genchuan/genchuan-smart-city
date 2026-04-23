package cn.iocoder.yudao.module.studentmgmt.controller.admin.behaviormgmt.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 学生心理健康看板 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BehaviorMgmtChartRespVO {

    @ExcelProperty("请假总次数")
    @Schema(description = "请假总次数")
    private Integer totalLeaveCount;
    @ExcelProperty("待审批请假申请数")
    @Schema(description = "待审批请假申请数")
    private Integer pendingAuditCount;
    @ExcelProperty("考勤异常人数")
    @Schema(description = "考勤异常人数")
    private Integer attendanceAbnormalCount;
    @ExcelProperty("已同步考勤记录数")
    @Schema(description = "已同步考勤记录数")
    private Integer syncCount;
    @ExcelProperty("请假类型分布统计，包含类型名称、对应数量。")
    @Schema(description = "请假类型分布统计，包含类型名称、对应数量。")
    private List<JSONObject> leaveTypeDistribution;
    @ExcelProperty("每日请假趋势，包含日期、对应请假人数。")
    @Schema(description = "每日请假趋势，包含日期、对应请假人数。")
    private List<JSONObject> dailyLeaveTrend;

}
