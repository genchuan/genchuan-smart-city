package cn.iocoder.yudao.module.waterdetection.controller.admin.testprogress.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 检测进度跟踪新增/修改 Request VO")
@Data
public class TestProgressSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "任务编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "任务编号不能为空")
    private String taskCode;

    @Schema(description = "当前进度(%)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "当前进度(%)不能为空")
    private Double progressPercent;

    @Schema(description = "已完成指标")
    private String completedIndicators;

    @Schema(description = "未完成指标")
    private String pendingIndicators;

    @Schema(description = "预计完成时间")
    private LocalDateTime estimatedCompletion;

    @Schema(description = "延迟原因(如有)")
    private String delayReason;

}