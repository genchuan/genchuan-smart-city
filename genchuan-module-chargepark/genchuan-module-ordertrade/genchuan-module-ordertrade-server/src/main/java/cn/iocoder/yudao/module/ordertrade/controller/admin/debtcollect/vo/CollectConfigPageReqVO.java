package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - CollectConfig 分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CollectConfigPageReqVO extends PageParam {

    @Schema(description = "追缴方式")
    private String collectMethod;
    @Schema(description = "状态")
    private String status;
    @Schema(description = "创建时间开始，格式 yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime createTimeStart;
    @Schema(description = "创建时间结束，格式 yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime createTimeEnd;
}
