package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.base.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 通用场景表，一级和二级场景新增/修改 Request VO")
@Data
public class UniversalSceneSaveReqVO {
    @Schema(hidden = true)
//    @Schema(description = "主键，自增", requiredMode = Schema.RequiredMode.REQUIRED, example = "2239")
    private Long id;

    @Schema(hidden = true)
//    @Schema(description = "场景唯一标识", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotEmpty(message = "场景唯一标识不能为空")
    private String sceneCode;

    @Schema(description = "父级ID，0表示一级场景", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "父级ID，不能为空")
    private Long parentId;

    @Schema(description = "场景等级，一级场景为1，二级为2", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "场景等级不能为空")
    private Integer level;

    @Schema(description = "场景描述", example = "你猜")
    private String description;

    @Schema(description = "场景名称，如 城管住建/市政设施", requiredMode = Schema.RequiredMode.REQUIRED,
            example = "城管住建")
    @NotEmpty(message = "场景名称，如 城管住建/市政设施不能为空")
    private String label;

    @Schema(description = "场景值，用于前端选择，如 urban_admin / urban_admin_facility",
            requiredMode = Schema.RequiredMode.REQUIRED,example = "urban_admin")
    @NotEmpty(message = "场景值，用于前端选择，如 urban_admin / urban_admin_facility不能为空")
    private String value;

    @Schema(hidden = true)
//    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(hidden = true)
//    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(hidden = true)
//    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(hidden = true)
//    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}
