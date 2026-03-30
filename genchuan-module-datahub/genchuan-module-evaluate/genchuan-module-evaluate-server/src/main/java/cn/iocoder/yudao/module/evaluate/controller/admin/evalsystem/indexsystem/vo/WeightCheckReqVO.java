package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexsystem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 权重校验请求 VO")
@Data
public class WeightCheckReqVO {

    @Schema(description = "指标体系ID（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "system001")
    @NotNull(message = "指标体系ID不能为空")
    private String systemId;

    @Schema(description = "分类ID（UUID）", example = "cate001")
    private String categoryId;
}

