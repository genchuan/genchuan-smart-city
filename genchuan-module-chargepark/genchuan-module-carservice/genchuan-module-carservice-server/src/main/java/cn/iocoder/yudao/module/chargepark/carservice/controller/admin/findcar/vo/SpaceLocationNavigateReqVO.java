package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 车位定位导航跳转 Request VO")
@Data
public class SpaceLocationNavigateReqVO {

    @Schema(description = "车位定位查询记录 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "查询记录 ID 不能为空")
    private Long id;

    @Schema(description = "目标车位 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "201")
    @NotNull(message = "目标车位 ID 不能为空")
    private Long spaceId;

}
