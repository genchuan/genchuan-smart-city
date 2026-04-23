package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 套牌管控忽略 Request VO")
@Data
public class FakePlateControlIgnoreReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

    @Schema(description = "忽略理由", requiredMode = Schema.RequiredMode.REQUIRED, example = "车辆过户，信息已更新")
    @NotBlank(message = "忽略理由不能为空")
    private String ignoreReason;

}