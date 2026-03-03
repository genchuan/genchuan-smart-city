package cn.iocoder.yudao.module.evaluate.controller.admin.inspection.plan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 考察计划新增/修改 Request VO")
@Data
public class PlanSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28048")
    private Long id;

    @Schema(description = "考察计划ID（UUID）", example = "27855")
    private String planId;

    @Schema(description = "计划名称", example = "芋艿")
    private String name;

    @Schema(description = "计划编码")
    private String code;

    @Schema(description = "关联评价任务ID（关联eval_task.task_id）", example = "23147")
    private String taskId;

    @Schema(description = "考察时间")
    private LocalDateTime inspectionTime;

    @Schema(description = "考察方式ID（关联sys_inspection_type.type_id）", example = "17953")
    private String inspectionTypeId;

    @Schema(description = "通知状态ID（关联sys_notify_status.status_id）", example = "19644")
    private String notifyStatusId;

    @Schema(description = "确认人数", example = "16421")
    private Integer confirmCount;

    @Schema(description = "计划状态ID（关联sys_plan_status.status_id）", example = "17902")
    private String statusId;

    @Schema(description = "创建人ID（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "创建时间（业务字段）")
    private LocalDateTime bizCreateTime;

    @Schema(description = "更新时间（业务字段）")
    private LocalDateTime bizUpdateTime;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}