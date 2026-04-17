package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.serviceconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "管理后台 - 话术管理新增/修改 Request VO")
@Data
public class WordingMgmtSaveReqVO {

    @Schema(description = "主键 ID", example = "1024")
    private Long id;

    @Schema(description = "话术名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "欢迎语")
    @NotBlank(message = "话术名称不能为空")
    @Size(max = 64, message = "话术名称长度不能超过 64")
    private String name;

    @Schema(description = "话术内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "话术内容不能为空")
    private String content;

    @Schema(description = "话术类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "快捷回复")
    @NotBlank(message = "话术类型不能为空")
    private String type;

    @Schema(description = "状态", example = "未生效")
    private String status;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}
