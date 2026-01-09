package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.config.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 保存场景配置 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class SceneConfigSaveReqVO extends SceneConfigRespVO {

    @Schema(description = "场景唯一标识", example = "0101", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "sceneKey不能为空")
    private String sceneKey;
}
