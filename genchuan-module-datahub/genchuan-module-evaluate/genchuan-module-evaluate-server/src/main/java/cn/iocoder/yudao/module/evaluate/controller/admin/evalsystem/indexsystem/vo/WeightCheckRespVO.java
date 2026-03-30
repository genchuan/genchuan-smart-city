package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexsystem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 权重校验响应 VO")
@Data
public class WeightCheckRespVO {

    @Schema(description = "校验类型", example = "CATEGORY_WEIGHT")
    private String checkType;

    @Schema(description = "权重总和", example = "100.00")
    private Double totalWeight;

    @Schema(description = "是否通过", example = "true")
    private Boolean passed;

    @Schema(description = "错误信息")
    private String errorMessage;
}
