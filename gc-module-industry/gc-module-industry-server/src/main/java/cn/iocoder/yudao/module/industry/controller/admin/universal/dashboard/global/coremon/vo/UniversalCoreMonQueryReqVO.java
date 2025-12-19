package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coremon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 通用大屏核心要素运行监测 查询 Request VO")
@Data
public class UniversalCoreMonQueryReqVO {

    @Schema(description = "查询地区-省市县三级shortCode码，6位", example = "110000")
    private String regionShortCode;

    @Schema(description = "统计开始时间（含）,yyyy-MM-dd HH:mm:ss", example = "2025-01-01 09:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @Schema(description = "统计结束时间（含）,yyyy-MM-dd HH:mm:ss", example = "2025-11-01 23:59:59")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @Schema(description = "设施类型", example = "燃气管道")
    private String facilityType;

    @Schema(description = "预警等级", example = "高")
    private String warnLevel;

    @Schema(description = "故障类型", example = "泄漏")
    private String faultType;

    @Schema(description = "工单状态", example = "待处理")
    private String woStatus;
}
