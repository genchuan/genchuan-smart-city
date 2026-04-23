package cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 稽查任务 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CheckTaskRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31752")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "任务类型：违规通行稽查/欠费逃费稽查/其他 关联字典inspect_task_task_type", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("任务类型：违规通行稽查/欠费逃费稽查/其他 关联字典inspect_task_task_type")
    private String taskType;

    @Schema(description = "派发时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("派发时间")
    private LocalDateTime dispatchTime;

    @Schema(description = "截止时间")
    @ExcelProperty("截止时间")
    private LocalDateTime deadlineTime;

    @Schema(description = "状态：待派发/待认领/处理中/已完成/已归档 关联字典inspect_task_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态：待派发/待认领/处理中/已完成/已归档 关联字典inspect_task_status")
    private String status;

    @Schema(description = "片区ID，关联片区表", example = "7751")
    @ExcelProperty("片区ID")
    private Long areaId;

    @Schema(description = "片区名称")
    @ExcelProperty("片区名称")
    private String areaName;

    @Schema(description = "执行人ID，关联system_user用户表", example = "10872")
    @ExcelProperty("执行人ID")
    private Long executeUserId;

    @Schema(description = "执行人姓名")
    @ExcelProperty("执行人姓名")
    private String executeUserName;

    @Schema(description = "完成时间")
    @ExcelProperty("完成时间")
    private LocalDateTime finishTime;

    @Schema(description = "任务进度")
    @ExcelProperty("任务进度")
    private String taskProgress;

    @Schema(description = "转派理由", example = "不香")
    @ExcelProperty("转派理由")
    private String transferReason;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}