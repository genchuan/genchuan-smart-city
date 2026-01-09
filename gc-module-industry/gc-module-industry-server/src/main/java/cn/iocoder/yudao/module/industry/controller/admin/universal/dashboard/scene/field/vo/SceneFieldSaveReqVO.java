package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.field.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 场景字段新增/修改 Request VO")
@Data
public class SceneFieldSaveReqVO {

    @Schema(hidden = true)
//    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "1458")
    private Long id;

    @Schema(description = "所属的场景", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "所属的场景")
    private String sceneCode;

    @Schema(description = "唯一字段标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "唯一字段标识不能为空")
    private String fieldCode;

    @Schema(description = "字段名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "设施名称")
    @NotEmpty(message = "字段名称不能为空")
    private String label;

    @Schema(description = "字段类型：normal/status/coord", requiredMode = Schema.RequiredMode.REQUIRED, example = "normal")
    @NotEmpty(message = "字段类型：normal/status/coord不能为空")
    private String type;

}
