package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 积分活动更新 Request VO")
@Data
public class PointActivityUpdateReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主键ID不能为空")
    private Long id;

    @Schema(description = "活动描述")
    private String description;

    @Schema(description = "适用场站")
    private String stationIds;

}
