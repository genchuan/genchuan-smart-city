package cn.iocoder.yudao.module.vehiclecharging.controller.admin.abnormalorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "汽车充电 - 异常订单新增/修改 Request VO")
@Data
public class AbnormalOrderSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20517")
    private Long id;

    @Schema(description = "订单编号，关联订单列表表order_list", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "订单编号，关联订单列表表order_list不能为空")
    private String orderCode;

    @Schema(description = "异常类型：支付异常/充电中断/设备故障，关联字典abnormal_order_abnormal_type", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "异常类型：支付异常/充电中断/设备故障，关联字典abnormal_order_abnormal_type不能为空")
    private String abnormalType;

    @Schema(description = "异常原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "不好")
    @NotEmpty(message = "异常原因不能为空")
    private String abnormalReason;

    @Schema(description = "排查人员")
    private String checkUser;

    @Schema(description = "排查时间")
    private LocalDateTime checkTime;

    @Schema(description = "处理措施")
    private String handleMeasure;

    @Schema(description = "退款金额")
    private BigDecimal refundAmount;

    @Schema(description = "异常状态：未核实/已核实/处理中/已完结，关联字典abnormal_order_abnormal_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "异常状态：未核实/已核实/处理中/已完结，关联字典abnormal_order_abnormal_status不能为空")
    private String abnormalStatus;

    @Schema(description = "处理时间")
    private LocalDateTime handleTime;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}