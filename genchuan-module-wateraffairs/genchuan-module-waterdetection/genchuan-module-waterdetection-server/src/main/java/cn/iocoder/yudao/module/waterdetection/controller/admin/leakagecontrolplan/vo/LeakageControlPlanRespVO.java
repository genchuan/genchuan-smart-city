package cn.iocoder.yudao.module.waterdetection.controller.admin.leakagecontrolplan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 漏损控制方案建议 Response VO")
@Data
@ExcelIgnoreUnannotated
public class LeakageControlPlanRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "分区ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分区ID")
    private String partitionId;

    @Schema(description = "超标漏损率(%)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("超标漏损率(%)")
    private Double exceededLeakageRate;

    @Schema(description = "压力数据")
    @ExcelProperty("压力数据")
    private String pressureData;

    @Schema(description = "管道平均使用年限(年)")
    @ExcelProperty("管道平均使用年限(年)")
    private Double pipeAvgAge;

    @Schema(description = "建议方案", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("建议方案")
    private String suggestedPlan;

    @Schema(description = "方案实施时间")
    @ExcelProperty("方案实施时间")
    private LocalDateTime planImplementTime;

    @Schema(description = "实施后漏损率(%)")
    @ExcelProperty("实施后漏损率(%)")
    private Double postImplementRate;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}