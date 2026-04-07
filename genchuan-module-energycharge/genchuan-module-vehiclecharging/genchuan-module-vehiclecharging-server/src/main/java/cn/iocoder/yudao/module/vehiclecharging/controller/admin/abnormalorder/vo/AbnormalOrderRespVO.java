package cn.iocoder.yudao.module.vehiclecharging.controller.admin.abnormalorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 异常订单 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AbnormalOrderRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20517")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "订单编号，关联订单列表表order_list", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("订单编号，关联订单列表表order_list")
    private String orderCode;

    @Schema(description = "异常类型：支付异常/充电中断/设备故障，关联字典abnormal_order_abnormal_type", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("异常类型：支付异常/充电中断/设备故障，关联字典abnormal_order_abnormal_type")
    private String abnormalType;

    @Schema(description = "异常原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "不好")
    @ExcelProperty("异常原因")
    private String abnormalReason;

    @Schema(description = "排查人员")
    @ExcelProperty("排查人员")
    private String checkUser;

    @Schema(description = "排查时间")
    @ExcelProperty("排查时间")
    private LocalDateTime checkTime;

    @Schema(description = "处理措施")
    @ExcelProperty("处理措施")
    private String handleMeasure;

    @Schema(description = "退款金额")
    @ExcelProperty("退款金额")
    private BigDecimal refundAmount;

    @Schema(description = "异常状态：未核实/已核实/处理中/已完结，关联字典abnormal_order_abnormal_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("异常状态：未核实/已核实/处理中/已完结，关联字典abnormal_order_abnormal_status")
    private String abnormalStatus;

    @Schema(description = "处理时间")
    @ExcelProperty("处理时间")
    private LocalDateTime handleTime;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}