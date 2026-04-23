package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 充停地图导航跳转 Request VO")
@Data
public class ChargeParkMapNavigateReqVO {

    @Schema(description = "充停地图查询记录 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "查询记录 ID 不能为空")
    private Long id;

    @Schema(description = "目标场站 / 车位 ID（前端从查询结果中选中的场站或车位）", requiredMode = Schema.RequiredMode.REQUIRED, example = "101")
    @NotNull(message = "目标 ID 不能为空")
    private Long targetId;

}
