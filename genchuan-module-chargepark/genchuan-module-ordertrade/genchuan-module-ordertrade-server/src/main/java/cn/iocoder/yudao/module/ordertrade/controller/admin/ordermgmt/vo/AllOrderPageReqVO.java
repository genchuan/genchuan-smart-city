package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.module.ordertrade.enums.AllOrderStatusEnum;
import cn.iocoder.yudao.module.ordertrade.enums.OrderTypeEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - AllOrder 分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AllOrderPageReqVO extends PageParam {

    @Schema(description = "订单编号，模糊查询")
    private String orderNo;
    @Schema(description = "订单类型：temp_park=临时停车, offtime_park=错时停车, car_charge=汽车充电, bike_charge=两轮充电, share_charge=共享充电")
    @ExcelProperty(value = "订单类型", converter = EnumExcelConverter.class)
    @EnumFormat(OrderTypeEnum.class)
    private String orderType;
    @Schema(description = "车牌，模糊查询")
    private String plateNo;

    @Schema(description = "支付状态：pending_pay=待支付, paid=已支付, completed=已完成, cancelled=已取消, refunding=退款中, refunded=已退款")
    @ExcelProperty(value = "支付状态", converter = EnumExcelConverter.class)
    @EnumFormat(AllOrderStatusEnum.class)
    private String status;

    @Schema(description = "场站ID")
    private Long stationId;
    @Schema(description = "订单生成时间-开始，格式 yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime createOrderTimeStart;

    @Schema(description = "订单生成时间-结束，格式 yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime createOrderTimeEnd;

    @Schema(description = "支付时间-开始，格式 yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime payTimeStart;

    @Schema(description = "支付时间-结束，格式 yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime payTimeEnd;
}
