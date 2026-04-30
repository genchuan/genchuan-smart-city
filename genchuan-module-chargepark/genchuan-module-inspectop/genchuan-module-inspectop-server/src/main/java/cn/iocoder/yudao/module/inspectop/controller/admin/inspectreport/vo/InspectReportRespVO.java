package cn.iocoder.yudao.module.inspectop.controller.admin.inspectreport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "巡查巡检 - 巡检上报 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectReportRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "关联任务ID")
    @ExcelProperty("关联任务ID")
    private Long taskId;

    @Schema(description = "问题类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("问题类型")
    private String type;

    @Schema(description = "上报时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("上报时间")
    private LocalDateTime reportTime;

    @Schema(description = "上报状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("上报状态")
    private String status;

    @Schema(description = "审核人ID")
//    @ExcelProperty("审核人ID")
    private Long auditUserId;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "处置人ID")
//    @ExcelProperty("处置人ID")
    private Long processUserId;

    @Schema(description = "处置人姓名")
    @ExcelProperty("处置人姓名")
    private String processUserName;

    @Schema(description = "处置时间")
    @ExcelProperty("处置时间")
    private LocalDateTime processTime;

    @Schema(description = "上报内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("上报内容")
    private String content;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("审核备注")
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