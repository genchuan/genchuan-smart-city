package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.module.ordertrade.enums.AbnormalStatusEnum;
import cn.iocoder.yudao.module.ordertrade.enums.AbnormalTypeEnum;
import cn.iocoder.yudao.module.ordertrade.enums.DebtRecordStatusEnum;
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

@Schema(description = "管理后台 - AbnormalOrder 分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AbnormalOrderPageReqVO extends PageParam {

    @Schema(description = "关联订单ID")
    private Long orderId;

    @Schema(description = "订单类型：temp_park=临时停车, offtime_park=错时停车, car_charge=汽车充电, bike_charge=两轮充电, share_charge=共享充电")
    @ExcelProperty(value = "订单类型", converter = EnumExcelConverter.class)
    @EnumFormat(OrderTypeEnum.class)
    private String orderType;

    @Schema(description = "异常类型：payment_error=支付异常, billing_error=计费异常, status_error=状态异常")
    @ExcelProperty(value = "异常类型", converter = EnumExcelConverter.class)
    @EnumFormat(AbnormalTypeEnum.class)
    private String abnormalType;

    @Schema(description = "处置状态：unhandled=未处理, handling=处理中, closed=已关闭")
    @ExcelProperty(value = "处置状态", converter = EnumExcelConverter.class)
    @EnumFormat(AbnormalStatusEnum.class)
    private String status;

    @Schema(description = "场站名称，模糊查询")
    private String stationName;

    @Schema(description = "识别时间-开始，格式 yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime identifyTimeStart;

    @Schema(description = "识别时间-结束，格式 yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime identifyTimeEnd;
}
