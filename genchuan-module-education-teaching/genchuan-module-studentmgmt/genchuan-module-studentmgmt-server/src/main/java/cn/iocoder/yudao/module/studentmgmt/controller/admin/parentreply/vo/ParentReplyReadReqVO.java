package cn.iocoder.yudao.module.studentmgmt.controller.admin.parentreply.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 家长回复标记已读 Request VO")
@Data
public class ParentReplyReadReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1782")
    private Long[] ids;

}