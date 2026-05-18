package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 奖品管理更新 Request VO")
@Data
public class PrizeMgmtUpdateReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主键ID不能为空")
    private Long id;

    @Schema(description = "奖品名称")
    private String name;

    @Schema(description = "当前库存")
    private Integer stock;

    @Schema(description = "预警阈值")
    private Integer warnThreshold;

    @Schema(description = "绑定活动ID")
    private Long activityId;

    @Schema(description = "奖品描述")
    private String description;

    @Schema(description = "类型")
    private String type;
}
