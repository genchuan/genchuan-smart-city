package cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 企业整改记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EntRectifyRecordPageReqVO extends PageParam {

    @Schema(description = "[整改通知书ID] 关联park_rectify_notice.id，唯一", example = "1757")
    private Long rectifyNoticeId;

    @Schema(description = "[企业ID] 关联park_enterprise_info.id", example = "5748")
    private Long entId;

    @Schema(description = "[整改状态] 如：未整改/整改中/已完成/整改不合格", example = "1")
    private String rectifyStatus;

    @Schema(description = "[整改完成时间] datetime格式，仅当状态为已完成或整改不合格时有值")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] rectifyCompleteTime;

    @Schema(description = "[企业整改说明] 富文本内容，可为空")
    private String rectifyDesc;

    @Schema(description = "[整改佐证证据链接] JSON格式varchar，可为空", example = "https://www.iocoder.cn")
    private String rectifyEvidenceUrl;

    @Schema(description = "[整改审核结果] 如：合格/不合格，可为空")
    private String auditResult;

    @Schema(description = "[整改审核人ID] 关联park_user.id，可为空")
    private Long auditBy;

    @Schema(description = "[整改审核驳回原因] 文本，可为空", example = "不香")
    private String rejectReason;

    @Schema(description = "[整改审核时间] 可为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] auditTime;

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
