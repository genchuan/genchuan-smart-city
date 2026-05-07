package cn.iocoder.yudao.module.vehiclecharging.controller.admin.abnormalorder.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "异常订单分页查询 Response VO")
@Data
public class NewAbnormalOrderRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "异常编号", example = "ABN-20250301001")
    private String abnormalCode;

    @Schema(description = "订单ID", example = "1")
    private Long orderId;

    @Schema(description = "订单编号", example = "ORD-20250301001")
    private String orderCode;

    @Schema(description = "车牌号", example = "闽C12345")
    private String plateNo;

    @Schema(description = "所属场站名称", example = "泉州丰泽充电站")
    private String stationName;

    @Schema(description = "异常类型", example = "充电中断")
    private String abnormalType;

    @Schema(description = "异常原因")
    private String abnormalReason;

    @Schema(description = "异常发生时间", example = "1775005986")
    private Long abnormalTime;

    @Schema(description = "异常状态", example = "处理中")
    private String abnormalStatus;

    @Schema(description = "核实人", example = "运维张三")
    private String verifyUser;

    @Schema(description = "核实时间", example = "1775006986")
    private Long verifyTime;

    @Schema(description = "核实结果", example = "异常")
    private String verifyResult;

    @Schema(description = "异常订单核实备注")
    @ExcelProperty("异常订单核实备注")
    private String verifyRemark;

    @Schema(description = "处理措施", example = "爱咋咋")
    private String handleMeasure;

    @Schema(description = "处理人", example = "运维李四")
    private String handleUser;

    @Schema(description = "退款金额", example = "0.01")
    private BigDecimal refundAmount;

    @Schema(description = "退款原因")
    @ExcelProperty("退款原因")
    private String refundReason;

    @Schema(description = "处理时间", example = "1775007986")
    private Long handleTime;

    @Schema(description = "排查人")
    private String checkUser;

    @Schema(description = "排查时间", example = "1775008986")
    private Long checkTime;

    @Schema(description = "完结时间", example = "1775008986")
    private Long completeTime;

    @Schema(description = "备注", example = "充电桩故障")
    private String remark;

    @Schema(description = "创建人")
    private String creator;

    @Schema(description = "更新人")
    private String updater;

    @Schema(description = "创建时间", example = "1775005986")
    private Long createTime;

    @Schema(description = "更新时间", example = "1775007986")
    private Long updateTime;
}