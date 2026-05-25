package cn.iocoder.yudao.module.enterprisesvc.controller.admin.staffmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 企业员工 Response VO")
@Data
@ExcelIgnoreUnannotated
public class StaffMgmtRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "员工姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("员工姓名")
    private String staffName;

    @Schema(description = "企业ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("企业ID")
    private Long enterpriseId;

    @Schema(description = "所属部门")
    @ExcelProperty("所属部门")
    private String deptName;

    @Schema(description = "岗位")
    @ExcelProperty("岗位")
    private String postName;

    @Schema(description = "权限状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("权限状态")
    private String authStatus;

    @Schema(description = "通行区域")
    @ExcelProperty("通行区域")
    private String accessArea;

    @Schema(description = "授权人账号")
    @ExcelProperty("授权人账号")
    private String authUser;

    @Schema(description = "操作人账号")
    @ExcelProperty("操作人账号")
    private String handleUser;

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