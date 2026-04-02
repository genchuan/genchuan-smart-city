package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "窨井盖异常报警触发响应")
public class ManholeCoverWarnTriggerAlarmRespVO {

    @Schema(description = "报警记录ID", example = "alarm-202504-00189")
    private String alarmId;

    @Schema(description = "窨井盖唯一ID", example = "c1d2e3f4-g5h6-7890-cdef-0123456789ab")
    private String coverId;

    @Schema(description = "报警状态 1-已触发", example = "1")
    private Integer alarmStatus;

    @Schema(description = "租户ID", example = "e3f4g5h6-i7j8-9012-efgh-23456789abcd")
    private String tenantId;
}
