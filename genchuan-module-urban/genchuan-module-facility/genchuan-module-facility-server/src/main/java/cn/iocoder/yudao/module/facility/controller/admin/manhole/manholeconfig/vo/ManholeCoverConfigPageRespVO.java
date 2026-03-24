package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "井盖配置分页响应VO")
public class ManholeCoverConfigPageRespVO {

    @Schema(description = "配置ID")
    private String configId;

    @Schema(description = "井盖ID")
    private String coverId;

    @Schema(description = "井盖编号")
    private String coverCode;

    @Schema(description = "配置状态")
    private Integer configStatus;

    @Schema(description = "配置状态名称")
    private String configStatusName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "最后操作人姓名")
    private String operateUserName;

    @Schema(description = "租户ID")
    private String tenantId;

    @Schema(description = "阈值配置")
    private ThresholdConfig thresholdConfig;

    @Schema(description = "采集配置")
    private CollectConfig collectConfig;

    @Schema(description = "报警配置")
    private AlarmConfig alarmConfig;

    // ===================== 内部VO =====================
    @Data
    @Schema(description = "阈值配置")
    public static class ThresholdConfig {
        @Schema(description = "倾斜角度阈值°")
        private BigDecimal tiltAngleThreshold;

        @Schema(description = "位移距离阈值cm")
        private BigDecimal displacementThreshold;

        @Schema(description = "水位阈值cm")
        private BigDecimal waterLevelThreshold;

        @Schema(description = "开启时长阈值分钟")
        private BigDecimal openDurationThreshold;
    }

    @Data
    @Schema(description = "采集配置")
    public static class CollectConfig {
        @Schema(description = "采集频率秒")
        private Integer collectFrequency;

        @Schema(description = "离线超时时间分钟")
        private Integer offlineTimeout;
    }

    @Data
    @Schema(description = "报警配置")
    public static class AlarmConfig {
        @Schema(description = "报警方式 0-平台 1-短信 2-电话")
        @TableField(typeHandler = JacksonTypeHandler.class)
        private List<Integer> alarmType;

        @Schema(description = "报警接收人")
        @TableField(typeHandler = JacksonTypeHandler.class)
        private List<String> alarmRecipient;
    }
}