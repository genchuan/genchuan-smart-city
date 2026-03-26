package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
@Schema(description = "窨井盖监测配置编辑 Request VO")
public class ManholeCoverConfigEditReqVO {

    @NotBlank(message = "窨井盖唯一ID不能为空")
    @Schema(description = "窨井盖唯一ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "c1d2e3f4-g5h6-7890-cdef-0123456789ab")
    private String coverId;

    @Schema(description = "阈值配置")
    private ThresholdConfig thresholdConfig;

    @Schema(description = "采集配置")
    private CollectConfig collectConfig;

    @Schema(description = "报警配置")
    private AlarmConfig alarmConfig;

    @Schema(description = "配置状态", example = "1")
    private Integer configStatus;

    @NotBlank(message = "租户ID不能为空")
    @Schema(description = "租户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private String tenantId;

    @NotBlank(message = "操作人ID不能为空")
    @Schema(description = "操作人ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private String operateUserId;

    // ========== 阈值配置 ==========
    @Data
    @Schema(description = "窨井盖监测阈值配置")
    public static class ThresholdConfig {
        @Schema(description = "倾斜角度阈值", example = "15")
        private Double tiltAngleThreshold;
        @Schema(description = "位移距离阈值", example = "10")
        private Double displacementThreshold;
        @Schema(description = "水位阈值", example = "30")
        private Double waterLevelThreshold;
        @Schema(description = "开启时长阈值", example = "5")
        private Double openDurationThreshold;
    }

    // ========== 采集配置 ==========
    @Data
    @Schema(description = "窨井盖监测采集配置")
    public static class CollectConfig {
        @Schema(description = "采集频率(秒)", example = "60")
        private Integer collectFrequency;
        @Schema(description = "离线超时时间(分钟)", example = "10")
        private Integer offlineTimeout;
        @Schema(description = "数据上传模式 0-实时 1-缓存", example = "0")
        private Integer dataUploadMode;
    }

    // ========== 报警配置 ==========
    @Data
    @Schema(description = "窨井盖监测报警配置")
    public static class AlarmConfig {
        @Schema(description = "报警方式", example = "[0,1]")
        private List<Integer> alarmType;
        @Schema(description = "报警接收人", example = "[\"13800138000\",\"admin@genchuan.com\"]")
        private List<String> alarmRecipient;
        @Schema(description = "报警级别", example = "1")
        private Integer alarmLevel;
        @Schema(description = "报警延迟秒数", example = "30")
        private Integer alarmDelay;
    }
}