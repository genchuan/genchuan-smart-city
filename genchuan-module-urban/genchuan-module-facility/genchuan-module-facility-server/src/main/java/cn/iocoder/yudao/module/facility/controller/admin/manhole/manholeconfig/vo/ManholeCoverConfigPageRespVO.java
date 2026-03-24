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

//    @Schema(description = "井盖名称")
//    private String coverName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "操作人")
    private String operateUserName;

    @Schema(description = "租户ID")
    private String tenantId;

    @Schema(description = "阈值配置")
    private ThresholdConfig thresholdConfig;

    @Schema(description = "采集配置")
    private CollectConfig collectConfig;

    @Schema(description = "报警配置")
    private AlarmConfig alarmConfig;

    @Data
    @Schema(description = "阈值配置")
    public static class ThresholdConfig {
        @Schema(description = "倾斜角度阈值")
        private String tiltAngleThreshold;
        @Schema(description = "位移阈值")
        private String displacementThreshold;
        @Schema(description = "水位阈值")
        private String waterLevelThreshold;
    }

    @Data
    @Schema(description = "采集配置")
    public static class CollectConfig {
        @Schema(description = "采集频率")
        private Integer collectFrequency;
    }

    @Data
    @Schema(description = "报警配置")
    public static class AlarmConfig {
        @Schema(description = "报警类型")
        private String alarmType;
        @Schema(description = "报警接收人")
        @TableField(typeHandler = JacksonTypeHandler.class)
        private List<String> alarmRecipient;
    }
}