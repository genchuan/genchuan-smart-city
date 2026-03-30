package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subject.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "管理后台 - 评价主体详情查询 Request VO")
@Data
public class SubjectDetailReqVO {
    @Schema(description = "评价主体ID（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "uuid-xxx")
    @NotBlank(message = "评价主体ID不能为空")
    private String subjectId;
}
