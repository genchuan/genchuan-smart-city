package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.assign.inspecttaskallocaterec.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查巡检任务分配记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectTaskAllocateRecRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "分配ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分配ID")
    private String allocateId;

    @Schema(description = "任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("任务ID")
    private String taskId;

    @Schema(description = "任务编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("任务编码")
    private String taskCode;

    @Schema(description = "原负责人员ID")
    @ExcelProperty("原负责人员ID")
    private String oldPersonId;

    @Schema(description = "原负责人员姓名")
    @ExcelProperty("原负责人员姓名")
    private String oldPersonName;

    @Schema(description = "新负责人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("新负责人员ID")
    private String newPersonId;

    @Schema(description = "新负责人员姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("新负责人员姓名")
    private String newPersonName;

    @Schema(description = "分配原因", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分配原因")
    private String allocateReason;

    @Schema(description = "分配时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分配时间")
    private LocalDateTime allocateTime;

    @Schema(description = "分配人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分配人")
    private String allocateUser;

    @Schema(description = "任务状态变更", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("任务状态变更")
    private String taskStatusChange;

    @Schema(description = "任务类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("任务类型")
    private String taskType;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}