package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "窨井盖监测配置删除 Request VO")
public class ManholeCoverConfigDeleteReqVO {

    @NotBlank(message = "租户ID不能为空")
    @Schema(description = "租户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "e3f4g5h6-i7j8-9012-efgh-23456789abcd")
    private String tenantId;

    @NotBlank(message = "操作人ID不能为空")
    @Schema(description = "操作人ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "f5g6h7i8-j9k0-1234-fghi-456789abcdef")
    private String operateUserId;

}