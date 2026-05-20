package cn.iocoder.yudao.module.inspectop.controller.admin.inspecttask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "巡查巡检 - 巡检任务 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectTaskRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "关联计划ID")
    @ExcelProperty("关联计划ID")
    private Long planId;

    @Schema(description = "所属计划")
    @ExcelProperty("所属计划")
    private String planName;

    @Schema(description = "任务类型")
//    @ExcelProperty("任务类型")
    private String planTypeName;

    @Schema(description = "巡检人员ID")
//    @ExcelProperty("巡检人员ID")
    private Long userId;

    @Schema(description = "巡检人员")
    @ExcelProperty("巡检人员")
    private String userName;

    @Schema(description = "派发时间")
    @ExcelProperty("派发时间")
    private LocalDateTime dispatchTime;

    @Schema(description = "认领时间")
    @ExcelProperty("认领时间")
    private LocalDateTime claimTime;

    @Schema(description = "任务状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("任务状态")
    private String status;

    @Schema(description = "执行进度")
    @ExcelProperty("执行进度")
    private Integer progress;

    @Schema(description = "是否归档")
    @ExcelProperty("是否归档")
    private Boolean isArchive;

    @Schema(description = "备用字段1")
//    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
//    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}