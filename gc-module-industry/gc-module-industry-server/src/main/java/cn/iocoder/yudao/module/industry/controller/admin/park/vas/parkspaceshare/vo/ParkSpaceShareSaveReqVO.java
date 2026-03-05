package cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkspaceshare.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 车位共享配置新增/修改 Request VO")
@Data
public class ParkSpaceShareSaveReqVO {

    @Schema(description = "[主键ID] 车位共享配置唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "32724")
    private Long id;

    @Schema(description = "[共享编号] 车位共享业务编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[共享编号] 车位共享业务编号不能为空")
    private String shareNo;

    @Schema(description = "[车位ID] 共享车位ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "219")
    @NotNull(message = "[车位ID] 共享车位ID不能为空")
    private Long spaceId;

    @Schema(description = "[车位所有人ID] 车位所有人ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5990")
    @NotNull(message = "[车位所有人ID] 车位所有人ID不能为空")
    private Long userId;

    @Schema(description = "[每小时价格] 车位共享每小时费用", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[每小时价格] 车位共享每小时费用不能为空")
    private BigDecimal pricePerHour;

    @Schema(description = "[可预约最大时长] 单次可预约的最大时长（小时）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[可预约最大时长] 单次可预约的最大时长（小时）不能为空")
    private Integer maxBookingDuration;

    @Schema(description = "[共享生效时间] 车位共享生效开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[共享生效时间] 车位共享生效开始时间不能为空")
    private LocalDateTime startTime;

    @Schema(description = "[共享失效时间] 车位共享失效结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[共享失效时间] 车位共享失效结束时间不能为空")
    private LocalDateTime endTime;

    @Schema(description = "[状态] 如：禁用/启用/暂停", example = "1")
    private String status;

    @Schema(description = "[共享订单数] 车位共享产生的订单数量", example = "24513")
    private Integer orderCount;

    @Schema(description = "[共享收益总额] 车位共享累计收益金额")
    private BigDecimal incomeAmount;

    @Schema(description = "[备注] 车位共享相关备注说明", example = "你猜")
    private String remark;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
