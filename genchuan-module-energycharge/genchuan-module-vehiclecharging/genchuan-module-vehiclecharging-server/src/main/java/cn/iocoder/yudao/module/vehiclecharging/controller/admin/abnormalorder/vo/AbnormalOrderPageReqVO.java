package cn.iocoder.yudao.module.vehiclecharging.controller.admin.abnormalorder.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 异常订单分页 Request VO")
@Data
public class AbnormalOrderPageReqVO extends PageParam {

    @Schema(description = "订单编号，关联订单列表表order_list")
    private String orderCode;

    @Schema(description = "异常类型：支付异常/充电中断/设备故障，关联字典abnormal_order_abnormal_type", example = "2")
    private String abnormalType;

    @Schema(description = "异常原因", example = "不好")
    private String abnormalReason;

    @Schema(description = "排查人员")
    private String checkUser;

    @Schema(description = "排查时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] checkTime;

    @Schema(description = "处理措施")
    private String handleMeasure;

    @Schema(description = "退款金额")
    private BigDecimal refundAmount;

    @Schema(description = "异常状态：未核实/已核实/处理中/已完结，关联字典abnormal_order_abnormal_status", example = "1")
    private String abnormalStatus;

    @Schema(description = "处理时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] handleTime;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}