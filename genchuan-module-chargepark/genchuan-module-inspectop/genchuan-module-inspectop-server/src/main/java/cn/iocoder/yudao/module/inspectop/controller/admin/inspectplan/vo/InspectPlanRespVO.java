package cn.iocoder.yudao.module.inspectop.controller.admin.inspectplan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "巡查巡检 - 巡检计划 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectPlanRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "计划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("计划名称")
    private String name;

    @Schema(description = "巡检类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("巡检类型")
    private String type;

    @Schema(description = "巡检范围", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("巡检范围")
    private String scope;

    @Schema(description = "执行周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("执行周期")
    private String cycle;

    @Schema(description = "计划描述")
    @ExcelProperty("计划描述")
    private String description;

    @Schema(description = "计划状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("计划状态")
    private String status;

    @Schema(description = "执行进度")
    @ExcelProperty("执行进度")
    private Integer progress;

    @Schema(description = "审核人ID")
    @ExcelProperty("审核人ID")
    private Long auditUserId;

    @Schema(description = "生效时间")
    @ExcelProperty("生效时间")
    private LocalDateTime effectTime;

    @Schema(description = "完成时间")
    @ExcelProperty("完成时间")
    private LocalDateTime finishTime;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
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