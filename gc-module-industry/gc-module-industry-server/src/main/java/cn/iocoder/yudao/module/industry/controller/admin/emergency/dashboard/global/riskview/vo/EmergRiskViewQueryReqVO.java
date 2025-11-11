package cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.riskview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 风险分布视图 查询 Request VO")
@Data
public class EmergRiskViewQueryReqVO {

    @Schema(description = "查询地区-12位全码的 full_code",example = "110101002000")
    private String regionFullCode;

    @Schema(description = "查询的风险等级，例如高/中/低",example = "低")
    private String riskLevel;

    @Schema(description = "查询的风险类型，例如危险源/灾害隐患等",example = "自然灾害")
    private String hazardType;

    @Schema(description = "查询的起始时间,格式yyyy-MM-dd HH:mm:ss",example = "2025-01-01 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @Schema(description = "查询的结束时间,格式yyyy-MM-dd HH:mm:ss",example = "2025-11-04 14:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
