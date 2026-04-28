package cn.iocoder.yudao.module.studentmgmt.controller.admin.repairmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 反馈 Request VO")
@Data
public class RepairMgmtFeedbackReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5870")
    private Long[] ids;

    @Schema(description = "维修反馈")
    private String feedbackContent;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}