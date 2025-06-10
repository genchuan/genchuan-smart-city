package cn.iocoder.yudao.module.smartcity.controller.admin.monitoringrule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 监测项规则设置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MonitoringRuleRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "27259")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "水位超限预警")
    @ExcelProperty("水位超限预警")
    private String waterLevelOverlimitWarning;

    @Schema(description = "流量异常预警")
    @ExcelProperty("流量异常预警")
    private String flowAbnormalityWarning;

    @Schema(description = "水质超标预警")
    @ExcelProperty("水质超标预警")
    private String waterQualityExceedanceWarning;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}