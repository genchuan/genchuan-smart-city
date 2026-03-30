package cn.iocoder.yudao.module.waterdetection.controller.admin.taskdispatch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 任务派发 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TaskDispatchRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "任务编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("任务编号")
    private String taskCode;

    @Schema(description = "任务类型(常规/应急)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("任务类型(常规/应急)")
    private String taskType;

    @Schema(description = "检测点清单", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("检测点清单")
    private String testPoints;

    @Schema(description = "指标清单", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("指标清单")
    private String indicators;

    @Schema(description = "截止日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("截止日期")
    private LocalDateTime deadline;

    @Schema(description = "派发部门", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("派发部门")
    private String dispatchDept;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}