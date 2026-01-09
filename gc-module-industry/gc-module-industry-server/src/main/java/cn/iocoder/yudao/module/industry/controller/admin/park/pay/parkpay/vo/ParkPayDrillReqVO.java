package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkpay.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 停车缴费服务钻取 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkPayDrillReqVO extends PageParam {

    @Schema(description = "钻取行政区划全码（12位）", example = "350100000000")
    private String regionFullCode;


    @Schema(description = "钻取起始时间", requiredMode = Schema.RequiredMode.REQUIRED,
            example = "2025-10-01 00:00:00")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime startTime;

    @Schema(description = "钻取截止时间", requiredMode = Schema.RequiredMode.REQUIRED,
            example = "2025-10-31 23:59:59")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endTime;
}
