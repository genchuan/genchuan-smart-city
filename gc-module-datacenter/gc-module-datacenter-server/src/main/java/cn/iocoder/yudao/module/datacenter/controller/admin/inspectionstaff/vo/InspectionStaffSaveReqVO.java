package cn.iocoder.yudao.module.datacenter.controller.admin.inspectionstaff.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡查人员信息新增/修改 Request VO")
@Data
public class InspectionStaffSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "人员ID")
    private String staffId;

    @Schema(description = "人员姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "人员姓名不能为空")
    private String staffName;

    @Schema(description = "性别")
    private String gender;

    @Schema(description = "联系电话", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "联系电话不能为空")
    private String contactPhone;

    @Schema(description = "所属部门ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long deptId;

    @Schema(description = "所属部门名称")
    private String deptName;

    @Schema(description = "人员类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "人员类型不能为空")
    private String staffType;

    @Schema(description = "资质证书路径")
    private String qualificationPath;

    @Schema(description = "作业权限", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "作业权限不能为空")
    private String workPermission;

    @Schema(description = "入职时间")
    private LocalDateTime entryTime;

    @Schema(description = "离职状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "离职状态不能为空")
    private String dimissionStatus;

}