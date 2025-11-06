package cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.riskview.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 风险分布视图 Response VO")
@Data
public class EmergRiskViewRespVO {

    @Schema(description = "风险隐患ID", example = "9f1a2b3c4d5e6f7890123456789abcde")
    @ExcelProperty("风险隐患ID")
    private String hazardId;  // 来源：biz_risk_hazard.hazard_id

    @Schema(description = "风险等级(如：（低/中/高）)", example = "低")
    @ExcelProperty("风险等级")
    private String riskLevel;  // 来源：biz_risk_hazard.risk_level

    @Schema(description = "风险类型", example = "消防安全")
    @ExcelProperty("风险类型")
    private String hazardType;  // 来源：biz_risk_hazard.hazard_type

    @Schema(description = "所在网格名称", example = "XX街道第三网格")
    @ExcelProperty("所在网格")
    private String gridName;  // 来源：sys_grid_cfg.grid_name

    @Schema(description = "所在区域名称", example = "XX市XX区")
    @ExcelProperty("所在区域")
    private String regionName;  // 来源：sys_area.region_name

    @Schema(description = "发现时间", example = "2025-01-15 14:23:00")
    @ExcelProperty("发现时间")
    private LocalDateTime discoverTime;  // 来源：biz_risk_hazard.discover_time
}
