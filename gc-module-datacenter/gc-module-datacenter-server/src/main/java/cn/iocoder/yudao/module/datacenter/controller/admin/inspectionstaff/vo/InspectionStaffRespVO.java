package cn.iocoder.yudao.module.datacenter.controller.admin.inspectionstaff.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查人员信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectionStaffRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "人员ID")
    @ExcelProperty("人员ID")
    private String staffId;

    @Schema(description = "人员姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("人员姓名")
    private String staffName;

    @Schema(description = "性别")
    @ExcelProperty("性别")
    private String gender;

    @Schema(description = "联系电话", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("联系电话")
    private String contactPhone;

    @Schema(description = "所属部门ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属部门ID")
    private Long deptId;

    @Schema(description = "所属部门名称")
    @ExcelProperty("所属部门名称")
    private String deptName;

    @Schema(description = "人员类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("人员类型")
    private String staffType;

    @Schema(description = "资质证书路径")
    @ExcelProperty("资质证书路径")
    private String qualificationPath;

    @Schema(description = "作业权限", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("作业权限")
    private String workPermission;

    @Schema(description = "入职时间")
    @ExcelProperty("入职时间")
    private LocalDateTime entryTime;

    @Schema(description = "离职状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("离职状态")
    private String dimissionStatus;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}