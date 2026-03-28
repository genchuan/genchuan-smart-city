package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "单井盖监测启动 Response VO")
public class ManholeMonitorOperateRespVO {

    @Schema(description = "窨井盖唯一ID", example = "1")
    private String coverId;

    @Schema(description = "监测状态 1-已启动", example = "1")
    private Integer monitorStatus;

    @Schema(description = "操作时间", example = "2025-04-01 16:00:00")
    private String operateTime;

    @Schema(description = "租户ID", example = "1")
    private String tenantId;
}