package cn.iocoder.yudao.module.kitchen.controller.admin.rectifynotice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 整改通知书新增/修改 Request VO")
@Data
public class RectifyNoticeSaveReqVO {



    @Schema(description = "[整改通知书编号] 唯一编号", hidden = true)
//    @NotEmpty(message = "[整改通知书编号] 唯一编号不能为空")
    private String noticeCode;

    @Schema(description = "[整改复审台账ID] 关联park_rectify_review.id，唯一", requiredMode = Schema.RequiredMode.REQUIRED, example = "13277")
    @NotNull(message = "[整改复审台账ID] 关联park_rectify_review.id，唯一不能为空")
    private Long rectifyReviewId;

    //自动算
    @Schema(description = "[下发时间] 通知书正式下发时间", hidden = true)
//    @NotNull(message = "[下发时间] 通知书正式下发时间不能为空")
    private LocalDateTime issueTime;

    @Schema(description = "[整改期限] 要求完成整改的截止日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[整改期限] 要求完成整改的截止日期不能为空")
    private LocalDate rectifyDeadline;

    //自动
    @Schema(description = "[送达状态] 如：未送达/已送达/拒收", hidden = true)
//    @NotEmpty(message = "[送达状态] 如：未送达/已送达/拒收不能为空")
    private String receiveStatus;


    //自动生成
    @Schema(description = "[通知书原件内容] 富文本内容", hidden = true)
//    @NotEmpty(message = "[通知书原件内容] 富文本内容不能为空")
    private String noticeContent;



}
