package cn.iocoder.yudao.module.waterdetection.controller.admin.testprogress.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 检测进度跟踪 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TestProgressRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "任务编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("任务编号")
    private String taskCode;

    @Schema(description = "当前进度(%)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("当前进度(%)")
    private Double progressPercent;

    @Schema(description = "已完成指标")
    @ExcelProperty("已完成指标")
    private String completedIndicators;

    @Schema(description = "未完成指标")
    @ExcelProperty("未完成指标")
    private String pendingIndicators;

    @Schema(description = "预计完成时间")
    @ExcelProperty("预计完成时间")
    private LocalDateTime estimatedCompletion;

    @Schema(description = "延迟原因(如有)")
    @ExcelProperty("延迟原因(如有)")
    private String delayReason;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}