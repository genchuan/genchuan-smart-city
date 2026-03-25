package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "窨井盖监测配置新增 Request VO")
public class ManholeCoverConfigAddReqVO {

    @NotBlank(message = "窨井盖唯一ID不能为空")
    @Schema(description = "窨井盖唯一ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "c1d2e3f4-g5h6-7890-cdef-0123456789ab")
    private String coverId;

    @NotNull(message = "阈值配置不能为空")
    @Schema(description = "阈值配置", requiredMode = Schema.RequiredMode.REQUIRED)
    private ThresholdConfig thresholdConfig;

    @NotNull(message = "采集配置不能为空")
    @Schema(description = "采集配置", requiredMode = Schema.RequiredMode.REQUIRED)
    private CollectConfig collectConfig;

    @NotNull(message = "报警配置不能为空")
    @Schema(description = "报警配置", requiredMode = Schema.RequiredMode.REQUIRED)
    private AlarmConfig alarmConfig;

    @Schema(description = "配置状态，默认0（未生效）", example = "0")
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

        @NotNull(message = "倾斜角度阈值不能为空")
        @Schema(description = "倾斜角度阈值", requiredMode = Schema.RequiredMode.REQUIRED, example = "15")
        private Double tiltAngleThreshold;

        @NotNull(message = "位移距离阈值不能为空")
        @Schema(description = "位移距离阈值", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
        private Double displacementThreshold;

        @NotNull(message = "水位阈值不能为空")
        @Schema(description = "水位阈值", requiredMode = Schema.RequiredMode.REQUIRED, example = "30")
        private Double waterLevelThreshold;

        @NotNull(message = "开启时长阈值不能为空")
        @Schema(description = "开启时长阈值", requiredMode = Schema.RequiredMode.REQUIRED, example = "5")
        private Double openDurationThreshold;
    }

    // ========== 采集配置 ==========
    @Data
    @Schema(description = "窨井盖监测采集配置")
    public static class CollectConfig {

        @NotNull(message = "采集频率不能为空")
        @Schema(description = "采集频率(秒)", requiredMode = Schema.RequiredMode.REQUIRED, example = "60")
        private Integer collectFrequency;

        @NotNull(message = "离线超时时间不能为空")
        @Schema(description = "离线超时时间(分钟)", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
        private Integer offlineTimeout;

        @Schema(description = "数据上传模式 0-实时 1-缓存", example = "0")
        private Integer dataUploadMode;
    }

    // ========== 报警配置 ==========
    @Data
    @Schema(description = "窨井盖监测报警配置")
    public static class AlarmConfig {

        @NotEmpty(message = "报警方式不能为空")
        @Schema(description = "报警方式", requiredMode = Schema.RequiredMode.REQUIRED, example = "[0,1]")
        @TableField(typeHandler = JacksonTypeHandler.class)
        private List<Integer> alarmType;

        @NotEmpty(message = "报警接收人不能为空")
        @Schema(description = "报警接收人", requiredMode = Schema.RequiredMode.REQUIRED, example = "[\"13800138000\",\"admin@genchuan.com\"]")
        @TableField(typeHandler = JacksonTypeHandler.class)
        private List<String> alarmRecipient;

        @Schema(description = "报警级别", example = "1")
        private Integer alarmLevel;

        @Schema(description = "报警延迟秒数", example = "30")
        private Integer alarmDelay;
    }
}
