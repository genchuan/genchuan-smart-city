package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
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

@Schema(description = "管理后台 - DebtRecord 分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DebtRecordPageReqVO extends PageParam {

    @Schema(description = "车牌，模糊查询")
    private String plateNo;
    @Schema(description = "追缴状态")
    @ExcelProperty(value = "追缴状态", converter = EnumExcelConverter.class)
    @EnumFormat(DebtRecordStatusEnum.class)
    private String status;
    @Schema(description = "场站ID")
    private Long stationId;
    @Schema(description = "创建时间-开始，格式 yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime createTimeStart;
    @Schema(description = "创建时间-结束，格式 yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime createTimeEnd;
}
