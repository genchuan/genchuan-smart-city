package cn.iocoder.yudao.module.kitchen.controller.admin.rectifynotice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 整改通知书新增/修改 Request VO")
@Data
public class RectifyNoticeUpdateReqVO {

    @Schema(description = "[主键ID] 整改通知书唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "20231")
    private Long id;

    @Schema(description = "[整改通知书编号] 唯一编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[整改通知书编号] 唯一编号不能为空")
    private String noticeCode;

    @Schema(description = "[整改复审台账ID] 关联park_rectify_review.id，唯一", requiredMode = Schema.RequiredMode.REQUIRED, example = "13277")
    @NotNull(message = "[整改复审台账ID] 关联park_rectify_review.id，唯一不能为空")
    private Long rectifyReviewId;

    @Schema(description = "[下发时间] 通知书正式下发时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[下发时间] 通知书正式下发时间不能为空")
    private LocalDateTime issueTime;

    @Schema(description = "[整改期限] 要求完成整改的截止日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[整改期限] 要求完成整改的截止日期不能为空")
    private LocalDate rectifyDeadline;

    @Schema(description = "[送达状态] 如：未送达/已送达/拒收", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "[送达状态] 如：未送达/已送达/拒收不能为空")
    private String receiveStatus;

    @Schema(description = "[送达时间] 实际送达或拒收时间")
    private LocalDateTime receiveTime;

    @Schema(description = "[通知书原件内容] 富文本内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[通知书原件内容] 富文本内容不能为空")
    private String noticeContent;

    @Schema(description = "[通用扩展字段1] 预留")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 预留")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 预留")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 预留")
    private String extCommon4;

}
