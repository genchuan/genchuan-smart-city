package cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Data
@Schema(description = "整改通知书复审执法台账分页 Request VO")
public class RectifyReviewLedgerPageReqVO extends PageParam {

    @Schema(description = "id列表")
    private List<Long> idList;

    @Schema(description = "台账编号")
    private String ledgerCode;

    @Schema(description = "企业ID")
    private Long entId;

    @Schema(description = "企业名称")
    private String entName;

    @Schema(description = "违规类型ID")
    private Long illegalTypeId;

    @Schema(description = "违规类型名称")
    private String illegalTypeName;

    @Schema(description = "违规等级ID")
    private Long illegalLevelId;

    @Schema(description = "违规等级名称")
    private String illegalLevelName;

    @Schema(description = "复审状态")
    private String reviewStatus;

    @Schema(description = "复审人")
    private Long reviewBy;

//    @Schema(description = "[草拟时间] 整改通知书草拟时间")
//    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
//    private LocalDateTime[] draftTime;
//
//    @Schema(description = "[下发时间] 通过整改通知书获取")
//    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
//    private LocalDateTime[] issue_time;
//
//    @Schema(description = "[撤销时间] 仅当状态为已撤销时有值")
//    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
//    private LocalDateTime[] cancelTime;
// 草拟时间

    // ================== 草拟时间 ==================

    @Schema(description = "草拟开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime draftTimeStart;

    @Schema(description = "草拟结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime draftTimeEnd;

    // ================== 下发时间 ==================

    @Schema(description = "下发开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime issueTimeStart;

    @Schema(description = "下发结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime issueTimeEnd;

    // ================== 撤销时间 ==================

    @Schema(description = "撤销开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime cancelTimeStart;

    @Schema(description = "撤销结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime cancelTimeEnd;
}
