package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormassign.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 调整 Request VO")
@Data
public class DormAssignAdjustReqVO {
    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Long[] ids;
    @Schema(description = "新宿舍 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8156")
    @NotNull(message = "新宿舍 ID不能为空")
    private Long newDormId;
    @Schema(description = "新床位 ID 列表", example = "26304")
    private Long[] newBedIds;


}