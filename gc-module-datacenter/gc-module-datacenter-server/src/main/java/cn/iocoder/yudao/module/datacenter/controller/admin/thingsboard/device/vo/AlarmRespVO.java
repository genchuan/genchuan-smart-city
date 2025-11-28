package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.thingsboard.server.common.data.alarm.AlarmSeverity;
import org.thingsboard.server.common.data.alarm.AlarmStatus;
import org.thingsboard.server.common.data.id.*;
import org.thingsboard.server.common.data.alarm.AlarmAssignee;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

@Schema(description = "管理后台 - 告警 Response VO")
@Data
@ToString(callSuper = true)
public class AlarmRespVO {

    @Schema(description = "告警ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private AlarmId id;

    @Schema(description = "创建时间")
    private Long createdTime;

    @Schema(description = "租户ID")
    private TenantId tenantId;

    @Schema(description = "客户ID")
    private CustomerId customerId;

    @Schema(description = "告警类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "高温告警")
    private String type;

    @Schema(description = "告警源")
    private EntityId originator;

    @Schema(description = "告警严重程度", requiredMode = Schema.RequiredMode.REQUIRED)
    private AlarmSeverity severity;

    @Schema(description = "是否已确认", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean acknowledged;

    @Schema(description = "是否已清除", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean cleared;

    @Schema(description = "分配人ID")
    private UserId assigneeId;

    @Schema(description = "开始时间")
    private Long startTs;

    @Schema(description = "结束时间")
    private Long endTs;

    @Schema(description = "确认时间")
    private Long ackTs;

    @Schema(description = "清除时间")
    private Long clearTs;

    @Schema(description = "分配时间")
    private Long assignTs;

    @Schema(description = "是否传播")
    private Boolean propagate;

    @Schema(description = "是否传播给所有者")
    private Boolean propagateToOwner;

    @Schema(description = "是否传播给租户")
    private Boolean propagateToTenant;

    @Schema(description = "传播关系类型")
    private List<String> propagateRelationTypes;

    @Schema(description = "告警源名称")
    private String originatorName;

    @Schema(description = "告警源标签")
    private String originatorLabel;

    @Schema(description = "分配人信息")
    private AlarmAssignee assignee;

    @Schema(description = "告警名称")
    private String name;

    @Schema(description = "告警状态")
    private AlarmStatus status;

    @Schema(description = "告警详情")
    private JsonNode details;

    // 新增：设备属性列表
    @Schema(description = "设备属性列表")
    private List<DeviceAttributeRespVO> deviceAttributes;
}