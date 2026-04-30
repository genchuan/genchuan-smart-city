package cn.iocoder.yudao.module.studentmgmt.controller.admin.classassign.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartTrendVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 分班管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ClassAssignChartRespVO {

    @Schema(description = "总分班任务数")
    private Integer totalAssignTaskCount;
    @Schema(description = "未分班任务数")
    private Integer unassignedCount;
    @Schema(description = "已分班任务数")
    private Integer assignedCount;
    @Schema(description = "已分班学生总数")
    private Integer totalAssignedStudentCount;
    @Schema(description = "近一周分班趋势数据")
    private List<ChartTrendVO> recentWeekAssignTrend;


}
