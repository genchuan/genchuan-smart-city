package cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkspaceshareorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 车位共享订单新增/修改 Request VO")
@Data
public class ParkSpaceShareOrderSaveReqVO {

    @Schema(description = "[主键ID] 车位共享订单唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "23095")
    private Long id;

    @Schema(description = "[订单编号] 车位共享订单业务编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[订单编号] 车位共享订单业务编号不能为空")
    private String orderNo;

    @Schema(description = "[共享配置ID] 绑定的车位共享配置ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17326")
    @NotNull(message = "[共享配置ID] 绑定的车位共享配置ID不能为空")
    private Long shareId;

    @Schema(description = "[使用用户ID] 使用用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28739")
    @NotNull(message = "[使用用户ID] 使用用户ID不能为空")
    private Long userId;

    @Schema(description = "[车牌号] 使用车辆车牌号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[车牌号] 使用车辆车牌号不能为空")
    private String carNumber;

    @Schema(description = "[使用日期] 车位实际使用日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[使用日期] 车位实际使用日期不能为空")
    private LocalDate useDate;

    @Schema(description = "[使用开始时间] 车位使用开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[使用开始时间] 车位使用开始时间不能为空")
    private LocalDateTime startTime;

    @Schema(description = "[使用结束时间] 车位使用结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[使用结束时间] 车位使用结束时间不能为空")
    private LocalDateTime endTime;

    @Schema(description = "[使用时长] 实际使用时长，单位：分钟", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[使用时长] 实际使用时长，单位：分钟不能为空")
    private Integer useDuration;

    @Schema(description = "[费用金额] 车位共享产生的费用金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[费用金额] 车位共享产生的费用金额不能为空")
    private BigDecimal feeAmount;

    @Schema(description = "[支付状态] 如：待支付/已支付/已取消", example = "1")
    private String payStatus;

    @Schema(description = "[支付记录ID] 支付记录ID", example = "26099")
    private Long paymentId;

    @Schema(description = "[结算状态] 如：未结算/已结算", example = "1")
    private String settlementStatus;

    @Schema(description = "[结算时间] 订单结算完成时间")
    private LocalDateTime settlementTime;

    @Schema(description = "[备注] 车位共享订单相关备注说明", example = "你猜")
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
