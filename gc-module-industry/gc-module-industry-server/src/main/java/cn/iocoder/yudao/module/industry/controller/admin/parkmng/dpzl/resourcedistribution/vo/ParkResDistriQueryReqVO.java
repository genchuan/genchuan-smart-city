// ParkResDistriQueryReqVO.java
package cn.iocoder.yudao.module.industry.controller.admin.parkmng.dpzl.resourcedistribution.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 停车资源分布查询 Request VO")
@Data
@EqualsAndHashCode
@ToString
public class ParkResDistriQueryReqVO {

    @Schema(description = "统计周期：日-day, 周-week, 月-month", example = "day")
    private String statCycle;

    @Schema(description = "区域编码", example = "110101")
    private String regionCode;

    @Schema(description = "统计时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] statTime;

}