package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 奖品管理创建 Request VO")
@Data
public class PrizeMgmtCreateReqVO {

    @Schema(description = "奖品名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "奖品名称不能为空")
    private String name;

    @Schema(description = "奖品类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "奖品类型不能为空")
    private String type;

    @Schema(description = "当前库存", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "库存不能为空")
    private Integer stock;

    @Schema(description = "预警阈值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "预警阈值不能为空")
    private Integer warnThreshold;

    @Schema(description = "绑定活动ID")
    private Long activityId;

    @Schema(description = "奖品描述")
    private String description;

}
