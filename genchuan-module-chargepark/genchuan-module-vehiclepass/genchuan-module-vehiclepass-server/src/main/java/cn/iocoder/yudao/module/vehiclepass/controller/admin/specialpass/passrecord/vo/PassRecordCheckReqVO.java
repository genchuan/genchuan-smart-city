package cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.passrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 放行记录核查 Request VO")
@Data
public class PassRecordCheckReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

    @Schema(description = "核查结果", requiredMode = Schema.RequiredMode.REQUIRED, example = "核查无误，为救护车紧急通行")
    @NotEmpty(message = "核查结果不能为空")
    private String checkResult;

}