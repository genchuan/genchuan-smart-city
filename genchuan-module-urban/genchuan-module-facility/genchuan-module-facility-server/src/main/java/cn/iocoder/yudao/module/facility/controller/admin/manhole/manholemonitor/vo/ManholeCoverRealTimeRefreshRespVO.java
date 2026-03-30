package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "管理后台 - 井盖实时数据刷新响应VO")
public class ManholeCoverRealTimeRefreshRespVO {

    @Schema(description = "窨井盖唯一ID", example = "c1d2e3f4-g5h6-7890-cdef-0123456789ab")
    private Long coverId;

    @Schema(description = "井盖编码", example = "MC-2025-00189")
    private String coverCode;

    @Schema(description = "井盖状态（1-正常，2-位移，3-倾斜，4-水位异常）", example = "2")
    private Integer coverStatus;

    @Schema(description = "井盖状态名称", example = "位移")
    private String coverStatusName;

    @Schema(description = "最新采集时间", example = "2025-04-01 15:35:18")
    private String latestCollectTime;

    @Schema(description = "最新倾斜角度", example = "5.3°")
    private String tiltAngle;

    @Schema(description = "最新位移距离", example = "8.2cm")
    private String displacement;

    @Schema(description = "最新井内水位", example = "12.1cm")
    private String waterLevel;

    @Schema(description = "设备在线状态（1-在线，0-离线）", example = "1")
    private Integer onlineStatus;

    @Schema(description = "租户ID（多租户隔离）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1000001")
    @NotNull(message = "租户ID不能为空")
    private Long tenantId;

}
