package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.module.ordertrade.enums.ParkOrderStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - OfftimeParkOrder 分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OfftimeParkOrderPageReqVO extends PageParam {

    @Schema(description = "订单编号，模糊查询")
    private String orderNo;
    @Schema(description = "车牌，模糊查询")
    private String plateNo;
    @Schema(description = "支付状态")
    @ExcelProperty(value = "支付状态", converter = EnumExcelConverter.class)
    @EnumFormat(ParkOrderStatusEnum.class)   // ← 指向枚举类
    private String status;
    @Schema(description = "场站名称，模糊查询")
    private String stationName;
    @Schema(description = "订单生成时间-开始，格式 yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime createOrderTimeStart;

    @Schema(description = "订单生成时间-结束，格式 yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime createOrderTimeEnd;
}
