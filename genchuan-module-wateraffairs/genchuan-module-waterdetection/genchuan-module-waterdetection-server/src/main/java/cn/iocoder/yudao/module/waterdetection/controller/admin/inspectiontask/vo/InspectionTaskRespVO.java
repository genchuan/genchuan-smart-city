package cn.iocoder.yudao.module.waterdetection.controller.admin.inspectiontask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 巡检任务派发与执行 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectionTaskRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("任务ID")
    private String taskId;

    @Schema(description = "巡检人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("巡检人员ID")
    private String inspectorId;

    @Schema(description = "任务内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("任务内容")
    private String taskContent;

    @Schema(description = "派发时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("派发时间")
    private LocalDateTime dispatchTime;

    @Schema(description = "接收时间")
    @ExcelProperty("接收时间")
    private LocalDateTime receiveTime;

    @Schema(description = "签到时间")
    @ExcelProperty("签到时间")
    private LocalDateTime checkinTime;

    @Schema(description = "检查项结果(正常/异常)")
    @ExcelProperty("检查项结果(正常/异常)")
    private String inspectionResult;

    @Schema(description = "现场照片URL")
    @ExcelProperty("现场照片URL")
    private String photoUrl;

    @Schema(description = "定位信息")
    @ExcelProperty("定位信息")
    private String locationInfo;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}