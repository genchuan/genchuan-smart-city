package cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkchargereservation.vo;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 充电预约分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkChargeReservationPageReqVO extends PageParam {

    @Schema(description = "[预约编号] 充电预约唯一编号")
    private String reservationNo;

    @Schema(description = "[用户ID] 用户唯一标识", example = "31127")
    private Long userId;

    @Schema(description = "[车牌号码] 用户车辆车牌号码")
    private String carNumber;

    @Schema(description = "[充电桩ID] 充电桩唯一标识", example = "951")
    private Long chargePileId;

    @Schema(description = "[预约日期] 用户预约充电的日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] reserveDate;

    @Schema(description = "[预约开始时间] 预约充电开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] startTime;

    @Schema(description = "[预约结束时间] 预约充电结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] endTime;

    @Schema(description = "[状态] 如:待使用/已使用/已取消/已过期", example = "1")
    private String status;

    @Schema(description = "[实际充电时长] 实际充电时长，单位：分钟")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Integer[] actualChargeTime;

    @Schema(description = "[充电度数] 实际充电电量度数")
    private BigDecimal chargeAmount;

    @Schema(description = "[充电费用] 实际产生的充电费用")
    private BigDecimal chargeFee;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

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
