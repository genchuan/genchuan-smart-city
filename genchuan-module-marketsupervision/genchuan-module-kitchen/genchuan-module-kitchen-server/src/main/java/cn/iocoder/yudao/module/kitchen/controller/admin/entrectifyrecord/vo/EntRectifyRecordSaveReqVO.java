package cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 企业整改记录新增/修改 Request VO")
@Data
public class EntRectifyRecordSaveReqVO {

    @Schema(description = "[主键ID] 企业整改记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "28473")
    private Long id;

    @Schema(description = "[整改通知书ID] 关联park_rectify_notice.id，唯一", requiredMode = Schema.RequiredMode.REQUIRED, example = "1757")
    @NotNull(message = "[整改通知书ID] 关联park_rectify_notice.id，唯一不能为空")
    private Long rectifyNoticeId;

    @Schema(description = "[企业ID] 关联park_enterprise_info.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "5748")
    @NotNull(message = "[企业ID] 关联park_enterprise_info.id不能为空")
    private Long entId;

    @Schema(description = "[整改状态] 如：未整改/整改中/已完成/整改不合格", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "[整改状态] 如：未整改/整改中/已完成/整改不合格不能为空")
    private String rectifyStatus;

    @Schema(description = "[整改完成时间] datetime格式，仅当状态为已完成或整改不合格时有值")
    private LocalDateTime rectifyCompleteTime;

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
    private LocalDateTime auditTime;

    @Schema(description = "[通用扩展字段1] 预留")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 预留")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 预留")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 预留")
    private String extCommon4;

}
