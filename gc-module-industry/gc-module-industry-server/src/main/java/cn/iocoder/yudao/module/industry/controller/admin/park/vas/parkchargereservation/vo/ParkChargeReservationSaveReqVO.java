package cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkchargereservation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 充电预约新增/修改 Request VO")
@Data
public class ParkChargeReservationSaveReqVO {

    @Schema(description = "[主键ID] 充电预约记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "26258")
    private Long id;

    @Schema(description = "[预约编号] 充电预约唯一编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[预约编号] 充电预约唯一编号不能为空")
    private String reservationNo;

    @Schema(description = "[用户ID] 用户唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "31127")
    @NotNull(message = "[用户ID] 用户唯一标识不能为空")
    private Long userId;

    @Schema(description = "[车牌号码] 用户车辆车牌号码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[车牌号码] 用户车辆车牌号码不能为空")
    private String carNumber;

    @Schema(description = "[充电桩ID] 充电桩唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "951")
    @NotNull(message = "[充电桩ID] 充电桩唯一标识不能为空")
    private Long chargePileId;

    @Schema(description = "[预约日期] 用户预约充电的日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[预约日期] 用户预约充电的日期不能为空")
    private LocalDate reserveDate;

    @Schema(description = "[预约开始时间] 预约充电开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[预约开始时间] 预约充电开始时间不能为空")
    private LocalDateTime startTime;

    @Schema(description = "[预约结束时间] 预约充电结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[预约结束时间] 预约充电结束时间不能为空")
    private LocalDateTime endTime;

    @Schema(description = "[状态] 如:待使用/已使用/已取消/已过期", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "[状态] 如:待使用/已使用/已取消/已过期不能为空")
    private String status;

    @Schema(description = "[实际充电时长] 实际充电时长，单位：分钟")
    private Integer actualChargeTime;

    @Schema(description = "[充电度数] 实际充电电量度数")
    private BigDecimal chargeAmount;

    @Schema(description = "[充电费用] 实际产生的充电费用")
    private BigDecimal chargeFee;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

    @Schema(description = "[备注] 充电预约相关备注说明", example = "随便")
    private String remark;

}
