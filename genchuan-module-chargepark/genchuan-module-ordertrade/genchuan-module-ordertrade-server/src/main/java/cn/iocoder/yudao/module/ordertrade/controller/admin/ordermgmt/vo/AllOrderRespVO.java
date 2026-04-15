package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 全部订单 Response VO")
@Data
public class AllOrderRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "订单编号")
    @ExcelProperty("订单编号")
    private String orderNo;

    @Schema(description = "用户ID")
    @ExcelProperty("用户ID")
    private Long userId;

    @Schema(description = "场站ID")
    @ExcelProperty("场站ID")
    private Long stationId;

    @Schema(description = "订单类型")
    @ExcelProperty("订单类型")
    private String type;

    @Schema(description = "总金额")
    @ExcelProperty("总金额")
    private BigDecimal totalAmount;

    @Schema(description = "实付金额")
    @ExcelProperty("实付金额")
    private BigDecimal payAmount;

    @Schema(description = "订单状态")
    @ExcelProperty("订单状态")
    private String status;

    @Schema(description = "支付时间")
    @ExcelProperty("支付时间")
    private LocalDateTime payTime;

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
