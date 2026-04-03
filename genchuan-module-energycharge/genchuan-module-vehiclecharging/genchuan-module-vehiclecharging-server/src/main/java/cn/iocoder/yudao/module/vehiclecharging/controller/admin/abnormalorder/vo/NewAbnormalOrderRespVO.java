package cn.iocoder.yudao.module.vehiclecharging.controller.admin.abnormalorder.vo;

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

    @Schema(description = "处理人", example = "运维李四")
    private String handleUser;

    @Schema(description = "处理时间", example = "1775007986")
    private Long handleTime;

    @Schema(description = "完结时间", example = "1775008986")
    private Long completeTime;

    @Schema(description = "备注", example = "充电桩故障")
    private String remark;

    @Schema(description = "创建时间", example = "1775005986")
    private Long createTime;

    @Schema(description = "更新时间", example = "1775007986")
    private Long updateTime;
}