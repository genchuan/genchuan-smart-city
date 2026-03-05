package cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkspaceshareorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 车位共享订单 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkSpaceShareOrderRespVO {

    @Schema(description = "[主键ID] 车位共享订单唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "23095")
    @ExcelProperty("[主键ID] 车位共享订单唯一标识")
    private Long id;

    @Schema(description = "[订单编号] 车位共享订单业务编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[订单编号] 车位共享订单业务编号")
    private String orderNo;

    @Schema(description = "[共享配置ID] 绑定的车位共享配置ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17326")
    @ExcelProperty("[共享配置ID] 绑定的车位共享配置ID")
    private Long shareId;

    @Schema(description = "[使用用户ID] 使用用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28739")
    @ExcelProperty("[使用用户ID] 使用用户ID")
    private Long userId;

    @Schema(description = "[车牌号] 使用车辆车牌号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[车牌号] 使用车辆车牌号")
    private String carNumber;

    @Schema(description = "[使用日期] 车位实际使用日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[使用日期] 车位实际使用日期")
    private LocalDate useDate;

    @Schema(description = "[使用开始时间] 车位使用开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[使用开始时间] 车位使用开始时间")
    private LocalDateTime startTime;

    @Schema(description = "[使用结束时间] 车位使用结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[使用结束时间] 车位使用结束时间")
    private LocalDateTime endTime;

    @Schema(description = "[使用时长] 实际使用时长，单位：分钟", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[使用时长] 实际使用时长，单位：分钟")
    private Integer useDuration;

    @Schema(description = "[费用金额] 车位共享产生的费用金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[费用金额] 车位共享产生的费用金额")
    private BigDecimal feeAmount;

    @Schema(description = "[支付状态] 如：待支付/已支付/已取消", example = "1")
    @ExcelProperty("[支付状态] 如：待支付/已支付/已取消")
    private String payStatus;

    @Schema(description = "[支付记录ID] 支付记录ID", example = "26099")
    @ExcelProperty("[支付记录ID] 支付记录ID")
    private Long paymentId;

    @Schema(description = "[结算状态] 如：未结算/已结算", example = "1")
    @ExcelProperty("[结算状态] 如：未结算/已结算")
    private String settlementStatus;

    @Schema(description = "[结算时间] 订单结算完成时间")
    @ExcelProperty("[结算时间] 订单结算完成时间")
    private LocalDateTime settlementTime;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注] 车位共享订单相关备注说明", example = "你猜")
    @ExcelProperty("[备注] 车位共享订单相关备注说明")
    private String remark;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    @ExcelProperty("[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    @ExcelProperty("[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    @ExcelProperty("[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    @ExcelProperty("[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
