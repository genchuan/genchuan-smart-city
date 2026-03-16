package cn.iocoder.yudao.module.kitchen.controller.admin.rectifynotice.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 整改通知书分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RectifyNoticePageReqVO extends PageParam {

    @Schema(description = "[整改通知书编号] 唯一编号")
    private String noticeCode;

    @Schema(description = "[整改复审台账ID] 关联park_rectify_review.id，唯一", example = "13277")
    private Long rectifyReviewId;

    @Schema(description = "[下发时间] 通知书正式下发时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] issueTime;

    @Schema(description = "[整改期限] 要求完成整改的截止日期")
    private LocalDate rectifyDeadline;

    @Schema(description = "[送达状态] 如：未送达/已送达/拒收", example = "1")
    private String receiveStatus;

    @Schema(description = "[送达时间] 实际送达或拒收时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] receiveTime;

    @Schema(description = "[通知书原件内容] 富文本内容")
    private String noticeContent;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[通用扩展字段1] 预留")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 预留")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 预留")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 预留")
    private String extCommon4;

}
