package cn.iocoder.yudao.module.studentmgmt.controller.admin.newpush.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 迎新推送统计看板 response VO")
@Data
public class NewPushChartRespVO {

    @Schema(description = "未推送任务数")
    private Integer waitPushCount;
    @Schema(description = "已推送任务数")
    private Integer finishedCount;
    @Schema(description = "总任务数")
    private Integer totalCount;
    @Schema(description = "总推送人数")
    private Integer totalPushNum;
    @Schema(description = "平均推送完成率")
    private BigDecimal avgFinishRate;
    @Schema(description = "日期列表")
    private List<String> dateList;
    @Schema(description = "每日推送人数列表")
    private List<Integer> dailyPushList;

}