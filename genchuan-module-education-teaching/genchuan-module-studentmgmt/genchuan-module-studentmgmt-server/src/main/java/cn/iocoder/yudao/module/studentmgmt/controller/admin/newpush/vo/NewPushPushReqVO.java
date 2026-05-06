package cn.iocoder.yudao.module.studentmgmt.controller.admin.newpush.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "管理后台 - 推送 Request VO")
@Data
public class NewPushPushReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5383")
    private Long[] ids;

}