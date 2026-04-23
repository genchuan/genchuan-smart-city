package cn.iocoder.yudao.module.studentmgmt.controller.admin.bedmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 分配 Request VO")
@Data
public class BedMgmtAssignReqVO {

    @Schema(description = "床位 IDs", requiredMode = Schema.RequiredMode.REQUIRED, example = "15953")
    @NotEmpty(message = "床位不能为空")
    private Long[] bedIds;

    @Schema(description = "学生", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "学生不能为空")
    private Long[] studentIds;

    @Schema(description = "分配时间")
    private LocalDateTime assignTime;

}