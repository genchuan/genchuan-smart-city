package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 套牌管控核查 Request VO")
@Data
public class FakePlateControlCheckReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

}