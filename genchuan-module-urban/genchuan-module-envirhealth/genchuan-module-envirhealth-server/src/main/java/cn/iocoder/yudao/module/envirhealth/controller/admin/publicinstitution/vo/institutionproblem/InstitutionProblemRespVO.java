package cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.institutionproblem;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 公共机构问题 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InstitutionProblemRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "问题主键（UUID）", example = "394")
    @ExcelProperty("问题主键")
    private String problemId;

    @Schema(description = "关联public_institution.institution_id", example = "26033")
    @ExcelProperty("机构编号")
    private String institutionId;

    @Schema(description = "关联sys_problem_type.id", example = "22687")
    @ExcelProperty("问题类型编号")
    private String problemTypeId;

    @Schema(description = "问题位置")
    @ExcelProperty("问题位置")
    private String location;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("上报人员")
    private String reportBy;

    @Schema(description = "上报时间")
    @ExcelProperty("上报时间")
    private LocalDateTime reportTime;

    @Schema(description = "问题描述")
    @ExcelProperty("问题描述")
    private String problemDesc;

    @Schema(description = "派单状态：待派单/已派单/已处置", example = "1")
    @ExcelProperty("派单状态")
    private String dispatchStatus;

    @Schema(description = "关联sys_dept.id", example = "15563")
    @ExcelProperty("部门编号")
    private String deptId;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("处置人员")
    private String handleBy;

    @Schema(description = "超时提醒：是/否")
    @ExcelProperty("超时提醒")
    private String isTimeout;

    @Schema(description = "处置结果")
    @ExcelProperty("处置结果")
    private String handleResult;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}