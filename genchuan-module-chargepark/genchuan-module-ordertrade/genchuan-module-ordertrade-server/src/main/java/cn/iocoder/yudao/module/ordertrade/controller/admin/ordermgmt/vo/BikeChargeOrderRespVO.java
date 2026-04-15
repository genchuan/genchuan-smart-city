package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 两轮充电订单 Response VO")
@Data
public class BikeChargeOrderRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "主订单ID")
    @ExcelProperty("主订单ID")
    private Long orderId;

    @Schema(description = "设备ID")
    @ExcelProperty("设备ID")
    private Long deviceId;

    @Schema(description = "开始充电时间")
    @ExcelProperty("开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束充电时间")
    @ExcelProperty("结束时间")
    private LocalDateTime endTime;

    @Schema(description = "充电度数（kWh）")
    @ExcelProperty("充电度数")
    private BigDecimal chargeDegree;

    @Schema(description = "费用")
    @ExcelProperty("费用")
    private BigDecimal fee;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
