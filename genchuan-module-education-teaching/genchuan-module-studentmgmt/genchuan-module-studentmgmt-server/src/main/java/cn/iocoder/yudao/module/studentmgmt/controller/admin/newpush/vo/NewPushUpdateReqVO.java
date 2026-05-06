package cn.iocoder.yudao.module.studentmgmt.controller.admin.newpush.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "管理后台 - 迎新推修改 Request VO")
@Data
public class NewPushUpdateReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5383")
    private Long id;

    @Schema(description = "推送任务名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "推送任务名称不能为空")
    private String taskName;

    @Schema(description = "推送内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "推送内容不能为空")
    private String pushContent;

    @Schema(description = "备注", example = "随便")
    private String remark;
}