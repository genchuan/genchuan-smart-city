package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.ordertrade.enums.ParkOrderStatusEnum;
import cn.iocoder.yudao.module.ordertrade.enums.PayMethodEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 错时停车订单 Response VO")
@Data
public class OfftimeParkOrderRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "订单编号")
    @ExcelProperty("订单编号")
    private String orderNo;

    @Schema(description = "车牌")
    @ExcelProperty("车牌")
    private String plateNo;

    @Schema(description = "预约开始时间")
    @ExcelProperty("预约开始时间")
    private LocalDateTime reserveStartTime;

    @Schema(description = "预约结束时间")
    @ExcelProperty("预约结束时间")
    private LocalDateTime reserveEndTime;

    @Schema(description = "实际使用开始时间")
    @ExcelProperty("实际使用开始时间")
    private LocalDateTime actualStartTime;

    @Schema(description = "实际使用结束时间")
    @ExcelProperty("实际使用结束时间")
    private LocalDateTime actualEndTime;

    @Schema(description = "订单金额")
    @ExcelProperty("订单金额")
    private BigDecimal amount;

    @Schema(description = "支付状态")
    @ExcelProperty(value = "支付状态", converter = EnumExcelConverter.class)
    @EnumFormat(ParkOrderStatusEnum.class)
    private String status;

    @Schema(description = "订单生成时间")
    @ExcelProperty("订单生成时间")
    private LocalDateTime createOrderTime;

    @Schema(description = "所属场站ID")
    @ExcelProperty("所属场站ID")
    private Long stationId;

    @Schema(description = "支付时间")
    @ExcelProperty("支付时间")
    private LocalDateTime payTime;

    @Schema(description = "支付方式")
    @ExcelProperty(value = "支付方式", converter = EnumExcelConverter.class)
    @EnumFormat(PayMethodEnum.class)
    private String payMethod;

    @Schema(description = "归档时间")
    @ExcelProperty("归档时间")
    private LocalDateTime archiveTime;

    @Schema(description = "操作人ID")
    @ExcelProperty("操作人ID")
    private Long operatorId;
/*
    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;*/

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;
}
