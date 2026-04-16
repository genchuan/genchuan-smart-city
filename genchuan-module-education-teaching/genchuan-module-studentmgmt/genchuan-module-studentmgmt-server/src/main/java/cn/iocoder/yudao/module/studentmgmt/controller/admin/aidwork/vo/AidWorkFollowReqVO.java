package cn.iocoder.yudao.module.studentmgmt.controller.admin.aidwork.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 奖助勤贷审核 Request VO")
@Data
public class AidWorkFollowReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19452")
    private Long id;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "流程状态（跟进中 / 已完成）")
    private String processStatus;


}