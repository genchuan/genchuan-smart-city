package cn.iocoder.yudao.module.waterdetection.controller.admin.leakagecontrolplan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 漏损控制方案建议新增/修改 Request VO")
@Data
public class LeakageControlPlanSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "分区ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分区ID不能为空")
    private String partitionId;

    @Schema(description = "超标漏损率(%)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "超标漏损率(%)不能为空")
    private Double exceededLeakageRate;

    @Schema(description = "压力数据")
    private String pressureData;

    @Schema(description = "管道平均使用年限(年)")
    private Double pipeAvgAge;

    @Schema(description = "建议方案", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "建议方案不能为空")
    private String suggestedPlan;

    @Schema(description = "方案实施时间")
    private LocalDateTime planImplementTime;

    @Schema(description = "实施后漏损率(%)")
    private Double postImplementRate;

}