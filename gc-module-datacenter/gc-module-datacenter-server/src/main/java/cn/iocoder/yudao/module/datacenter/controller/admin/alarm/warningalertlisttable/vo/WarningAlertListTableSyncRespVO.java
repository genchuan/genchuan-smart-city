package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 告警同步 Response VO")
@Data
@Builder
public class WarningAlertListTableSyncRespVO {

    @Schema(description = "同步成功的告警编号数组", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> successAlarmIds;

    @Schema(description = "同步失败的告警集合，key 为告警ID，value 为失败原因", requiredMode = Schema.RequiredMode.REQUIRED)
    private Map<String, String> failureAlarmIds;

    @Schema(description = "新增条数")
    private Integer createCount;

    @Schema(description = "更新条数")
    private Integer updateCount;

    @Schema(description = "失败条数")
    private Integer failureCount;

    @Schema(description = "成功条数")
    private Integer successCount;

    @Schema(description = "总处理条数")
    private Integer totalCount;
}