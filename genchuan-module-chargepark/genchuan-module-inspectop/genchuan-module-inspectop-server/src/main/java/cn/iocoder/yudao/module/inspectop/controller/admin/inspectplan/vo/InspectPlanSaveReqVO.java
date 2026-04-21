package cn.iocoder.yudao.module.inspectop.controller.admin.inspectplan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡检计划新增/修改 Request VO")
@Data
public class InspectPlanSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "计划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "计划名称不能为空")
    private String name;

    @Schema(description = "巡检类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "巡检类型不能为空")
    private String type;

    @Schema(description = "巡检范围", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "巡检范围不能为空")
    private String scope;

    @Schema(description = "执行周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "执行周期不能为空")
    private String cycle;

    @Schema(description = "计划描述")
    private String description;

    @Schema(description = "计划状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "计划状态不能为空")
    private String status;

    @Schema(description = "执行进度")
    private Integer progress;

    @Schema(description = "审核人ID")
    private Long auditUserId;

    @Schema(description = "生效时间")
    private LocalDateTime effectTime;

    @Schema(description = "完成时间")
    private LocalDateTime finishTime;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}