package cn.iocoder.yudao.module.studentmgmt.controller.admin.staymgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 留宿管理新增/修改 Request VO")
@Data
public class StayMgmtSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10528")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30478")
    @NotNull(message = "学生 ID不能为空")
    private Long studentId;

    @Schema(description = "留宿日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "留宿日期不能为空")
    private LocalDate stayDate;

    @Schema(description = "留宿原因", example = "不好")
    private String stayReason;

    @Schema(description = "申请时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "申请时间不能为空")
    private LocalDateTime applyTime;

    @Schema(description = "家长确认时间")
    private LocalDateTime parentConfirmTime;

    @Schema(description = "审核人")
    private String auditUser;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "状态：待确认/待审核/已通过", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "状态：待确认/待审核/已通过不能为空")
    private String status;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}