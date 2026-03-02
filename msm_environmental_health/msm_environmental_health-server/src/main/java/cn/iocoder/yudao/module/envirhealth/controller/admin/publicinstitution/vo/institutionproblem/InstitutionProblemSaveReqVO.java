package cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.institutionproblem;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 公共机构问题新增/修改 Request VO")
@Data
public class InstitutionProblemSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3726")
    private Long id;

    @Schema(description = "问题主键（UUID）", example = "394")
    private String problemId;

    @Schema(description = "关联public_institution.institution_id", example = "26033")
    private String institutionId;

    @Schema(description = "关联sys_problem_type.id", example = "22687")
    private String problemTypeId;

    @Schema(description = "问题位置")
    private String location;

    @Schema(description = "关联sys_user.id")
    private String reportBy;

    @Schema(description = "上报时间")
    private LocalDateTime reportTime;

    @Schema(description = "问题描述")
    private String desc;

    @Schema(description = "派单状态：待派单/已派单/已处置", example = "1")
    private String dispatchStatus;

    @Schema(description = "关联sys_dept.id", example = "15563")
    private String deptId;

    @Schema(description = "关联sys_user.id")
    private String handleBy;

    @Schema(description = "超时提醒：是/否")
    private String isTimeout;

    @Schema(description = "处置结果")
    private String handleResult;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}