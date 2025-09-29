package cn.iocoder.yudao.module.datacenter.controller.admin.staffareaassignment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 人员区域分配新增/修改 Request VO")
@Data
public class StaffAreaAssignmentSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "分配ID")
    private String assignmentId;

    @Schema(description = "人员ID")
    private String staffId;

    @Schema(description = "人员姓名")
    private String staffName;

    @Schema(description = "区域ID")
    private String areaId;

    @Schema(description = "区域名称")
    private String areaName;

    @Schema(description = "分配类型")
    private String assignmentType;

    @Schema(description = "分配周期")
    private String assignmentCycle;

    @Schema(description = "生效时间")
    private LocalDateTime effectiveTime;

    @Schema(description = "失效时间")
    private LocalDateTime expiryTime;

    @Schema(description = "分配状态")
    private String assignmentStatus;

}