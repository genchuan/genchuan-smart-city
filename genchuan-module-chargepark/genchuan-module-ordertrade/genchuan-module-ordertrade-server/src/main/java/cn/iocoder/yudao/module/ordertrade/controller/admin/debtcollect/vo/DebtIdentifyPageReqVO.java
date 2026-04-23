package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.module.ordertrade.enums.DebtIdentifyStatusEnum;
import cn.iocoder.yudao.module.ordertrade.enums.DebtRecordStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - DebtIdentify 分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DebtIdentifyPageReqVO extends PageParam {

    @Schema(description = "车牌，模糊查询")
    private String plateNo;
    @Schema(description = "状态")
    @ExcelProperty(value = "状态", converter = EnumExcelConverter.class)
    @EnumFormat(DebtIdentifyStatusEnum.class)
    private String status;
    @Schema(description = "场站ID")
    private Long stationId;
    @Schema(description = "识别时间范围")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] identifyTime;
}
