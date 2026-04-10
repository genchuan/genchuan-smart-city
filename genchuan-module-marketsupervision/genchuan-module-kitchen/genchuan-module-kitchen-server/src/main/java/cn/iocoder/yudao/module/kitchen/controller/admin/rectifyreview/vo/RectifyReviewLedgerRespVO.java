package cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "整改通知书复审执法台账 Response VO")
public class RectifyReviewLedgerRespVO {



    @Schema(description = "台账ID")
    @ExcelProperty("台账ID")
    private Long id;

    @Schema(description = "整改通知书ID")
    @ExcelProperty("整改通知书ID")
    private Long rectifyNoticeId;

    @Schema(description = "台账编号")
    @ExcelProperty("台账编号")
    private String ledgerCode;

    @Schema(description = "逾期标识")
    @ExcelProperty("逾期标识")
    private Integer overdueFlag;

    @Schema(description = "[企业ID] ", requiredMode = Schema.RequiredMode.REQUIRED, example = "4078")
    @ExcelProperty("[企业ID]")
    private Long entId;

    @Schema(description = "企业名称")
    @ExcelProperty("企业名称")
    private String entName;

    @Schema(description = "[违规类型ID] 关联park_illegal_type_dict.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "16642")
    @ExcelProperty("[违规类型ID] 关联park_illegal_type_dict.id")
    private Long illegalTypeId;

    @Schema(description = "违规类型")
    @ExcelProperty("违规类型")
    private String illegalTypeName;

    @Schema(description = "违规等级")
    @ExcelProperty("违规等级")
    private String illegalLevelName;

    @Schema(description = "违规证据链接")
    @ExcelProperty("违规证据链接")
    private String evidenceUrl;

    @Schema(description = "草拟时间")
    @ExcelProperty("草拟时间")
    private LocalDateTime draftTime;

    @Schema(description = "整改截止时间")
    @ExcelProperty("整改截止时间")
    private LocalDateTime rectifyDeadlineTime;

    @Schema(description = "复审状态")
    @ExcelProperty("复审状态")
    private String reviewStatus;

    @Schema(description = "复审人")
    @ExcelProperty("复审人")
    private String reviewUserName;

    @Schema(description = "复审时间")
    @ExcelProperty("复审时间")
    private LocalDateTime reviewTime;

    @Schema(description = "下发时间")
    @ExcelProperty("下发时间")
    private LocalDateTime issueTime;

    @Schema(description = "撤销时间")
    @ExcelProperty("撤销时间")
    private LocalDateTime cancelTime;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "撤销原因")
    @ExcelProperty("撤销原因")
    private String cancelReason;

    @Schema(description = "送达状态")
    @ExcelProperty("送达状态")
    private String receiveStatus;

//    @Schema(description = "企业整改反馈状态")
//    @ExcelProperty("企业整改反馈状态")
//    private String rectifyStatus;

//    @Schema(description = "执法复审台账编号")
//    @ExcelProperty("执法复审台账编号")
//    private String lawReviewLedgerCode;

//    @Schema(description = "整改通知书编号")
//    @ExcelProperty("整改通知书编号")
//    private String rectifyNoticeCode;

}
