package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 积分活动创建 Request VO")
@Data
public class PointActivityCreateReqVO {

    @Schema(description = "活动名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "活动名称不能为空")
    private String name;

    @Schema(description = "活动类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "活动类型不能为空")
    private String type;

    @Schema(description = "开始时间（时间戳，毫秒）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "开始时间不能为空")
    private Long startTime;

    @Schema(description = "结束时间（时间戳，毫秒）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "结束时间不能为空")
    private Long endTime;

    @Schema(description = "积分规则", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "积分规则不能为空")
    private String rule;

    @Schema(description = "活动描述")
    private String description;

    @Schema(description = "适用场站")
    private String stationIds;

}