package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "窨井盖监测配置详情 Response VO")
public class ManholeCoverConfigDetailRespVO {

    @Schema(description = "配置ID", example = "21008")
    private String configId;

    @Schema(description = "窨井盖ID", example = "1001")
    private String coverId;

    @Schema(description = "井盖编号", example = "MC-2025-0001")
    private String coverCode;

    @Schema(description = "配置状态", example = "1")
    private Integer configStatus;

    @Schema(description = "配置状态名称", example = "已生效")
    private String configStatusName;

    @Schema(description = "创建时间", example = "2025-01-01 00:00:00")
    private LocalDateTime createTime;

    @Schema(description = "创建人ID", example = "1001")
    private String createUserId;

    @Schema(description = "创建人名称", example = "张三")
    private String createUserName;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "更新人ID")
    private String updateUserId;

    @Schema(description = "更新人名称")
    private String updateUserName;

    @Schema(description = "区块链哈希")
    private String chainHash;

    @Schema(description = "租户ID")
    private Long tenantId;

    // ========== 嵌套结构 ==========
    @Schema(description = "阈值配置")
    private ThresholdConfig thresholdConfig;

    @Schema(description = "采集配置")
    private CollectConfig collectConfig;

    @Schema(description = "报警配置")
    private AlarmConfig alarmConfig;

    // ========== 内部静态类 ==========
    @Data
    @Schema(description = "阈值配置")
    public static class ThresholdConfig {
        @Schema(description = "倾斜角度阈值", example = "15")
        private BigDecimal tiltAngleThreshold;
        @Schema(description = "位移距离阈值", example = "10")
        private BigDecimal displacementThreshold;
        @Schema(description = "水位阈值", example = "30")
        private BigDecimal waterLevelThreshold;
        @Schema(description = "开启时长阈值", example = "5")
        private BigDecimal openDurationThreshold;
    }

    @Data
    @Schema(description = "采集配置")
    public static class CollectConfig {
        @Schema(description = "采集频率", example = "60")
        private Integer collectFrequency;
        @Schema(description = "离线超时时间", example = "10")
        private Integer offlineTimeout;
        @Schema(description = "上传模式 0-实时 1-批量", example = "0")
        private Integer dataUploadMode;
    }

    @Data
    @Schema(description = "报警配置")
    public static class AlarmConfig {
        @Schema(description = "报警方式 0-短信 1-邮件", example = "[0,1]")
        private List<Integer> alarmType;
        @Schema(description = "报警接收人")
        private List<String> alarmRecipient;
        @Schema(description = "报警级别", example = "1")
        private Integer alarmLevel;
        @Schema(description = "报警延迟秒数", example = "30")
        private Integer alarmDelay;
    }
}
