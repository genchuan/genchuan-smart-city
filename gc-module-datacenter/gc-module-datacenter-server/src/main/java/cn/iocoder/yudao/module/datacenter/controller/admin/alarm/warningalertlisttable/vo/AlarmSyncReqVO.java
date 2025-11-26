package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "管理后台 - 告警同步 Request VO")
@Data
public class AlarmSyncReqVO {

    @Schema(description = "告警ID列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "告警ID列表不能为空")
    private List<String> alarmIds;

    @Schema(description = "是否覆盖已存在的记录", example = "false")
    private Boolean overwrite = false;
}