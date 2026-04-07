package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingratio.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 复制分账方案 Request VO")
@Data
public class SharingRatioCopyReqVO {

    @Schema(description = "源分账比例主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "源分账比例ID不能为空")
    private Long id;
}
