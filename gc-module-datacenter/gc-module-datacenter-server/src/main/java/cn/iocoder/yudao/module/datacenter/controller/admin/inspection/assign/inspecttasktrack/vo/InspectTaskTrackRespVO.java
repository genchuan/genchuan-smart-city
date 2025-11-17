package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.assign.inspecttasktrack.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查巡检任务跟踪 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectTaskTrackRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "任务ID")
    @ExcelProperty("任务ID")
    private String taskId;

    @Schema(description = "任务编码")
    @ExcelProperty("任务编码")
    private String taskCode;

    @Schema(description = "任务名称")
    @ExcelProperty("任务名称")
    private String taskName;

    @Schema(description = "任务类型")
    @ExcelProperty("任务类型")
    private String taskType;

    @Schema(description = "负责人员ID")
    @ExcelProperty("负责人员ID")
    private String personId;

    @Schema(description = "负责人员姓名")
    @ExcelProperty("负责人员姓名")
    private String personName;

    @Schema(description = "所属区域代码")
    @ExcelProperty("所属区域代码")
    private String areaCode;

    @Schema(description = "所属区域名称")
    @ExcelProperty("所属区域名称")
    private String areaName;

    @Schema(description = "任务状态")
    @ExcelProperty("任务状态")
    private String taskStatus;

    @Schema(description = "开始时间")
    @ExcelProperty("开始时间")
    private LocalDateTime startTime;

    @Schema(description = "完成时间")
    @ExcelProperty("完成时间")
    private LocalDateTime completeTime;

    @Schema(description = "截止时间")
    @ExcelProperty("截止时间")
    private LocalDateTime deadlineTime;

    @Schema(description = "检查项总数")
    @ExcelProperty("检查项总数")
    private Integer totalCkItem;

    @Schema(description = "已完成检查项")
    @ExcelProperty("已完成检查项")
    private Integer completedCkItem;

    @Schema(description = "上传照片数")
    @ExcelProperty("上传照片数")
    private Integer uploadPhotoCount;

    @Schema(description = "是否超时")
    @ExcelProperty("是否超时")
    private Integer isOvertime;

    @Schema(description = "催办次数")
    @ExcelProperty("催办次数")
    private Integer remindCount;

    @Schema(description = "最后催办时间")
    @ExcelProperty("最后催办时间")
    private LocalDateTime lastRemindTime;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}