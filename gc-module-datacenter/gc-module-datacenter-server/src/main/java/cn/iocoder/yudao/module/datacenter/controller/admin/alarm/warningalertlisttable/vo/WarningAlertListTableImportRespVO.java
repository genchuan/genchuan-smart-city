package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 预警告警记录导入 Response VO")
@Data
@Builder
public class WarningAlertListTableImportRespVO {

    @Schema(description = "创建成功的告警编号数组", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> createAlertCodes;

    @Schema(description = "更新成功的告警编号数组", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> updateAlertCodes;

    @Schema(description = "导入失败的告警集合，key 为告警编号，value 为失败原因", requiredMode = Schema.RequiredMode.REQUIRED)
    private Map<String, String> failureAlertCodes;

    @Schema(description = "新增条数")
    private Integer createCount;

    @Schema(description = "更新条数")
    private Integer updateCount;

    @Schema(description = "失败条数")
    private Integer failureCount;

    @Schema(description = "成功条数")
    private Integer successCount;

}
