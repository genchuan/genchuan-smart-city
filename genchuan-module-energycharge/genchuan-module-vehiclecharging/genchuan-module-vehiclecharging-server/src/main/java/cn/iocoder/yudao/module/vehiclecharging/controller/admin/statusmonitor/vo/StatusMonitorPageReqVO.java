package cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 实时监测分页 Request VO")
@Data
public class StatusMonitorPageReqVO extends PageParam {

    @Schema(description = "[设备编号] 设备编号")
    private String deviceCode;

    @Schema(description = "[所属场站ID] 所属场站ID", example = "6947")
    private Long stationId;

    @Schema(description = "[所属场站名称] 所属场站名称", example = "6947")
    private String stationName;

    @Schema(description = "[所属车位ID] 所属车位ID", example = "11972")
    private Long lotId;

    @Schema(description = "[设备类型] 如：充电桩/车位", example = "2")
    private String deviceType;

    @Schema(description = "[电压] 单位：V")
    private BigDecimal voltage;

    @Schema(description = "[电流] 单位：A")
    private BigDecimal current;

    @Schema(description = "[功率] 单位：kW")
    private BigDecimal power;

    @Schema(description = "[告警等级] 如：无/一般/严重")
    private String alarmLevel;

    @Schema(description = "[监测状态] 如：正常/异常/处置中/已恢复", example = "2")
    private String monitorStatus;

    @Schema(description = "[处置人员] 处置人员")
    private String disposeUser;

    @Schema(description = "[处置措施] 处置措施")
    private String disposeMeasure;

    @Schema(description = "[处置时间] 处置时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] disposeTime;

    @Schema(description = "[监测时间] 监测时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] monitorTime;

    @Schema(description = "[备注] 备注", example = "你猜")
    private String remark;

    @Schema(description = "[备用字段1] 备用字段1")
    private String reserve1;

    @Schema(description = "[备用字段2] 备用字段2")
    private String reserve2;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
