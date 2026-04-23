package cn.iocoder.yudao.module.studentmgmt.controller.admin.comparemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 授予 Request VO")
@Data
public class CompareMgmtAwardReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long[] ids;

    @Schema(description = "授予称号", requiredMode = Schema.RequiredMode.REQUIRED, example = "文明班级")
    @NotNull(message = "授予称号不能为空")
    private String awardName;

}