package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.thingsboard.server.common.data.alarm.AlarmSeverity;
import org.thingsboard.server.common.data.alarm.AlarmStatus;

@Schema(description = "管理后台 - 告警 Response VO")
@Data
@ToString(callSuper = true)
public class AlarmRespVO{

    @Schema(description = "告警ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private String id;

    @Schema(description = "租户ID", example = "1")
    private String tenantId;

    @Schema(description = "客户ID", example = "100")
    private String customerId;

    @Schema(description = "告警类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "高温告警")
    private String type;

    @Schema(description = "告警名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "高温告警")
    private String name;

    @Schema(description = "告警源ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "device-001")
    private String originatorId;

    @Schema(description = "告警源类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "DEVICE")
    private String originatorType;

    @Schema(description = "告警严重程度", requiredMode = Schema.RequiredMode.REQUIRED, example = "CRITICAL")
    private AlarmSeverity severity;

    @Schema(description = "是否已确认", requiredMode = Schema.RequiredMode.REQUIRED, example = "false")
    private Boolean acknowledged;

    @Schema(description = "是否已清除", requiredMode = Schema.RequiredMode.REQUIRED, example = "false")
    private Boolean cleared;

    @Schema(description = "告警状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "ACTIVE_UNACK")
    private AlarmStatus status;

    @Schema(description = "分配人ID", example = "1001")
    private String assigneeId;

    @Schema(description = "开始时间", example = "1634058704565")
    private Long startTs;

    @Schema(description = "结束时间", example = "1634111163522")
    private Long endTs;

    @Schema(description = "确认时间", example = "1634115221948")
    private Long ackTs;

    @Schema(description = "清除时间", example = "1634114528465")
    private Long clearTs;

    @Schema(description = "分配时间", example = "1634115928465")
    private Long assignTs;

    @Schema(description = "告警详情")
    private String details;

    @Schema(description = "设备名称")
    private String deviceName;

    @Schema(description = "设备类型")
    private String deviceType;
}
