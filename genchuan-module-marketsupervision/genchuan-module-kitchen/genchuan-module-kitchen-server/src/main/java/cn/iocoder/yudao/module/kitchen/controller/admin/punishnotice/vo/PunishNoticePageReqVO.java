package cn.iocoder.yudao.module.kitchen.controller.admin.punishnotice.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 处罚通知书分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PunishNoticePageReqVO extends PageParam {

    @Schema(description = "[处罚通知书编号] 唯一编号")
    private String noticeCode;

    @Schema(description = "[处罚复审台账ID] 关联punish_review_ledger.id，唯一", example = "30647")
    private Long punishReviewId;

    @Schema(description = "[企业缴费记录表id]", example = "5943")
    private Long entPayRecordId;

    @Schema(description = "[下发时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] issueTime;

    @Schema(description = "[送达时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] receiveTime;

    @Schema(description = "[缴款期限]")
    private LocalDateTime payDeadline;

    @Schema(description = "[送达状态] 如：未送达/已送达/拒收", example = "2")
    private String receiveStatus;

    @Schema(description = "[实际处罚金额] 单位：元")
    private BigDecimal actualPunishAmt;

    @Schema(description = "[处罚决定书原件内容] 富文本")
    private String decisionContent;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
