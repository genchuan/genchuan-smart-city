package cn.iocoder.yudao.module.studentmgmt.controller.admin.promotemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 招生宣传统计看板 Request VO")
@Data
public class PromoteMgmtChartRespVO {

    @Schema(description = "未执行任务数")
    private Integer waitExecuteCount;
    @Schema(description = "已执行任务数")
    private Integer finishedCount;
    @Schema(description = "总任务数")
    private Integer totalCount;
    @Schema(description = "总宣传人数")
    private Integer totalPromoteNum;
    @Schema(description = "总意向学生数")
    private Integer totalIntentNum;
    @Schema(description = "意向转化率")
    private Double intentRate;
    @Schema(description = "日期列表")
    private List<String> dateList;
    @Schema(description = "每日宣传人数列表")
    private List<Integer> dailyPromoteList;
    @Schema(description = "每日意向学生数列表")
    private List<Integer> dailyIntentList;

}