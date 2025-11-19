package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvtask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查巡检重点督办任务 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectSupvTaskRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "督办任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("督办任务ID")
    private String supvTaskId;

    @Schema(description = "督办任务编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("督办任务编码")
    private String supvTaskCode;

    @Schema(description = "关联整改任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联整改任务ID")
    private String rectifyTaskId;

    @Schema(description = "关联整改任务编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联整改任务编码")
    private String rectifyTaskCode;

    @Schema(description = "关联问题ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联问题ID")
    private String problemId;

    @Schema(description = "关联问题名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联问题名称")
    private String problemName;

    @Schema(description = "督办人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("督办人ID")
    private String supvUserId;

    @Schema(description = "督办人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("督办人姓名")
    private String supvUserName;

    @Schema(description = "督办频次", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("督办频次")
    private String supvFrequency;

    @Schema(description = "督办截止时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("督办截止时间")
    private LocalDateTime supvDeadlineTime;

    @Schema(description = "督办要求", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("督办要求")
    private String supvRequirement;

    @Schema(description = "督办任务状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("督办任务状态")
    private String supvStatus;

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