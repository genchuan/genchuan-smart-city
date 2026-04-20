package cn.iocoder.yudao.module.studentmgmt.controller.admin.clubmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 社团管理新增/修改 Request VO")
@Data
public class ClubMgmtSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23257")
    private Long id;

    @Schema(description = "社团名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "社团名称不能为空")
    private String clubName;

    @Schema(description = "社团类型：文体/学术/志愿/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "社团类型：文体/学术/志愿/其他不能为空")
    private String clubType;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15619")
    @NotNull(message = "学生 ID不能为空")
    private Long studentId;

    @Schema(description = "入团申请时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "入团申请时间不能为空")
    private LocalDateTime applyTime;

    @Schema(description = "审核人")
    private String auditUser;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "建档时间")
    private LocalDateTime archiveTime;

    @Schema(description = "场馆申请状态：无/待申请/已通过", example = "2")
    private String venueApplyStatus;

    @Schema(description = "状态：待审核/已通过/已建档", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "状态：待审核/已通过/已建档不能为空")
    private String status;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}