package cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "管理后台 - 整改工单详情 Request VO")
@Data
public class OutdoorAdOrderGetReqVO {

    @Schema(description = "工单主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "ffaae237-af5d-4189-afcf-ff9e21e8acbe")
    @NotBlank(message = "工单ID不能为空")
    private String id;
}