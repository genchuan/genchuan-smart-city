package cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.institutioninspection;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "环境卫生管理模块 - 公共机构核查 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InstitutionInspectionRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "301")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "核查主键（UUID）", example = "11975")
    @ExcelProperty("核查主键")
    private String inspectionId;

    @Schema(description = "关联public_institution.institution_id", example = "607")
    @ExcelProperty("机构编号")
    private String institutionId;

    @Schema(description = "关联task.task_id", example = "15463")
    @ExcelProperty("任务编号")
    private String taskId;

    @Schema(description = "关联sys_task_type.id", example = "5363")
    @ExcelProperty("任务类型")
    private String taskTypeId;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("上报人员")
    private String reportBy;

    @Schema(description = "上报时间")
    @ExcelProperty("上报时间")
    private LocalDateTime reportTime;

    @Schema(description = "上报结果")
    @ExcelProperty("上报结果")
    private String reportResult;

    @Schema(description = "核查状态：待核查/达标/不达标", example = "1")
    @ExcelProperty("核查状态")
    private String inspectionStatus;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("监管人员")
    private String inspectBy;

    @Schema(description = "核查时间")
    @ExcelProperty("核查时间")
    private LocalDateTime inspectionTime;

    @Schema(description = "整改要求")
    @ExcelProperty("整改要求")
    private String reformRequire;

    @Schema(description = "核查照片URL，JSON")
    @ExcelProperty("核查照片")
    private String inspectionPhoto;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}