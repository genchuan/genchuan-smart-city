package cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.institutioninspection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 公共机构核查新增/修改 Request VO")
@Data
public class InstitutionInspectionSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "301")
    private Long id;

    @Schema(description = "核查主键（UUID）", example = "11975")
    private String inspectionId;

    @Schema(description = "关联public_institution.institution_id", example = "607")
    private String institutionId;

    @Schema(description = "关联task.task_id", example = "15463")
    private String taskId;

    @Schema(description = "关联sys_task_type.id", example = "5363")
    private String taskTypeId;

    @Schema(description = "关联sys_user.id")
    private String reportBy;

    @Schema(description = "上报时间")
    private LocalDateTime reportTime;

    @Schema(description = "上报结果")
    private String reportResult;

    @Schema(description = "核查状态：待核查/达标/不达标", example = "1")
    private String inspectionStatus;

    @Schema(description = "关联sys_user.id")
    private String inspectBy;

    @Schema(description = "核查时间")
    private LocalDateTime inspectionTime;

    @Schema(description = "整改要求")
    private String reformRequire;

    @Schema(description = "核查照片URL，JSON")
    private String inspectionPhoto;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}