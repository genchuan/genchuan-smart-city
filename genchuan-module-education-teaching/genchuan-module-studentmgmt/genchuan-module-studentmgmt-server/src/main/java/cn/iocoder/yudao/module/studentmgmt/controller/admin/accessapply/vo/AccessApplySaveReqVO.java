package cn.iocoder.yudao.module.studentmgmt.controller.admin.accessapply.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 出入申请新增/修改 Request VO")
@Data
public class AccessApplySaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28041")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22874")
    @NotNull(message = "学生 ID不能为空")
    private Long studentId;

    @Schema(description = "申请类型：应急出入/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "申请类型：应急出入/其他不能为空")
    private String applyType;

    @Schema(description = "申请原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "不喜欢")
    @NotEmpty(message = "申请原因不能为空")
    private String applyReason;

    @Schema(description = "申请时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "申请时间不能为空")
    private LocalDateTime applyTime;

    @Schema(description = "审核人")
    private String auditUser;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "状态：待审核/已通过", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "状态：待审核/已通过不能为空")
    private String status;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}