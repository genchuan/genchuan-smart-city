package cn.iocoder.yudao.module.datacenter.controller.admin.staffareaassignment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 人员区域分配 Response VO")
@Data
@ExcelIgnoreUnannotated
public class StaffAreaAssignmentRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "分配ID")
    @ExcelProperty("分配ID")
    private String assignmentId;

    @Schema(description = "人员ID")
    @ExcelProperty("人员ID")
    private String staffId;

    @Schema(description = "人员姓名")
    @ExcelProperty("人员姓名")
    private String staffName;

    @Schema(description = "区域ID")
    @ExcelProperty("区域ID")
    private String areaId;

    @Schema(description = "区域名称")
    @ExcelProperty("区域名称")
    private String areaName;

    @Schema(description = "分配类型")
    @ExcelProperty("分配类型")
    private String assignmentType;

    @Schema(description = "分配周期")
    @ExcelProperty("分配周期")
    private String assignmentCycle;

    @Schema(description = "生效时间")
    @ExcelProperty("生效时间")
    private LocalDateTime effectiveTime;

    @Schema(description = "失效时间")
    @ExcelProperty("失效时间")
    private LocalDateTime expiryTime;

    @Schema(description = "分配状态")
    @ExcelProperty("分配状态")
    private String assignmentStatus;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}