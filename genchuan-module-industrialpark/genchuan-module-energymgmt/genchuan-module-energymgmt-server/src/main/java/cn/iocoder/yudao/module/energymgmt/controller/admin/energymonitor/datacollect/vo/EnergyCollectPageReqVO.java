package cn.iocoder.yudao.module.energymgmt.controller.admin.energymonitor.datacollect.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 能耗采集分页 Request VO")
@Data
public class EnergyCollectPageReqVO extends PageParam {

    @Schema(description = "设备名称", example = "李四")
    private String deviceName;

    @Schema(description = "设备类型：电表/水表/气表", example = "2")
    private String deviceType;

    @Schema(description = "能耗类型：电/水/气/热", example = "1")
    private String energyType;

    @Schema(description = "采集状态：采集正常/采集异常", example = "2")
    private String collectStatus;

    @Schema(description = "采集时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] collectTime;

}