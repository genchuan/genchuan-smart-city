package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.module.ordertrade.enums.CollectMethodEnum;
import cn.iocoder.yudao.module.ordertrade.enums.CollectTrackStatusEnum;
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

@Schema(description = "管理后台 - CollectTrack 分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CollectTrackPageReqVO extends PageParam {

    @Schema(description = "车牌，模糊查询")
    private String plateNo;
    @Schema(description = "追缴方式")
    @ExcelProperty(value = "追缴方式", converter = EnumExcelConverter.class)
    @EnumFormat(CollectMethodEnum.class)
    private String collectMethod;
    @Schema(description = "状态")
    @ExcelProperty(value = "状态", converter = EnumExcelConverter.class)
    @EnumFormat(CollectTrackStatusEnum.class)
    private String status;
    @Schema(description = "片区ID")
    private Long areaId;
    @Schema(description = "追缴时间-开始，格式 yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime collectTimeStart;

    @Schema(description = "追缴时间-结束，格式 yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime collectTimeEnd;
}
