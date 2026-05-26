package cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.module.ordertrade.enums.AmountCheckStatusEnum;
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

@Schema(description = "管理后台 - AmountCheck 分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AmountCheckPageReqVO extends PageParam {

    @Schema(description = "关联订单ID")
    private Long orderId;
    @Schema(description = "状态")
    @ExcelProperty(value = "状态", converter = EnumExcelConverter.class)
    @EnumFormat(AmountCheckStatusEnum.class)   // ← 指向枚举类
    private String status;
    @Schema(description = "核算结果")
    private String checkResult;
    @Schema(description = "创建时间-开始，格式 yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime createTimeStart;

    @Schema(description = "创建时间-结束，格式 yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime createTimeEnd;
}
