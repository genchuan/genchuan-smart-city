package cn.iocoder.yudao.module.smartcampus.controller.admin.archive.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "智慧校园管理后台 - 学籍状态维护 Request VO")
public class ArchiveMaintainReqVO {

    @Schema(description = "档案ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "档案ID不能为空")
    private Long id;

    @Schema(description = "新的学籍状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "休学", allowableValues = {"休学","退学","异动"})
    @NotBlank(message = "学籍状态不能为空")
    private String status;

    @Schema(description = "异动原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "因病休学")
    @NotBlank(message = "异动原因不能为空")
    private String changeReason;

    @Schema(description = "佐证材料地址", example = "/genchuan/.../xxx.pdf")
    private String evidenceUrl;

}
