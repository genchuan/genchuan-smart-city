package cn.iocoder.yudao.module.studentmgmt.controller.admin.checkin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 新生报到进度看板 Request VO")
@Data
public class CheckInChartRespVO {
    //waitConfirmCount (integer): 待确认人数。
    //waitAuditCount (integer): 待审核人数。
    //finishedCount (integer): 已报到人数。
    //totalCount (integer): 总新生人数。
    //progress (decimal): 报到完成进度。
    //dateList (array): 日期列表。
    //dailyConfirmList (array): 每日确认人数列表。
    //dailyAuditList (array): 每日审核人数列表。
    @Schema(description = "待确认人数")
    private Integer waitConfirmCount;
    @Schema(description = "待审核人数")
    private Integer waitAuditCount;
    @Schema(description = "已报到人数")
    private Integer finishedCount;
    @Schema(description = "总新生人数")
    private Integer totalCount;
    @Schema(description = "报到完成进度")
    private BigDecimal progress;
    @Schema(description = "日期列表")
    private List<String> dateList;
    @Schema(description = "每日确认人数列表")
    private List<Integer> dailyConfirmList;
    @Schema(description = "每日审核人数列表")
    private List<Integer> dailyAuditList;



}