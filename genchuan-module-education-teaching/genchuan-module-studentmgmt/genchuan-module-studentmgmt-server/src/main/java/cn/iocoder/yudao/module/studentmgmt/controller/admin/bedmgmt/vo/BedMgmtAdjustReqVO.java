package cn.iocoder.yudao.module.studentmgmt.controller.admin.bedmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 分配 Request VO")
@Data
public class BedMgmtAdjustReqVO {

    @Schema(description = "原床位 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
    @NotEmpty(message = "原床位不能为空")
    private Long oldBedId;
    @Schema(description = "新床位 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4")
    @NotEmpty(message = "新床位不能为空")
    private Long newBedId;

    @Schema(description = "学生信息", requiredMode = Schema.RequiredMode.REQUIRED, example = "8")
    private Long studentId;
    @Schema(description = "调整时间")
    private LocalDateTime adjustTime;

}