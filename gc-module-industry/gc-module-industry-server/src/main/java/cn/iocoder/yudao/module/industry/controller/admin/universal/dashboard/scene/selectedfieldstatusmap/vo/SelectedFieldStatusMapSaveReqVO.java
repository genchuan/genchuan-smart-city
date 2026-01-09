package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.selectedfieldstatusmap.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 场景字段状态映射新增/修改 Request VO")
@Data
public class SelectedFieldStatusMapSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8366")
    private Long id;

    @Schema(description = "所属场景唯一标识码（sceneKey）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属场景唯一标识码（sceneKey）不能为空")
    private String sceneCode;

    @Schema(description = "字段唯一标识码（对应 selectedFieldsConfig.key，仅 status 类型字段有效）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "字段唯一标识码（对应 selectedFieldsConfig.key，仅 status 类型字段有效）不能为空")
    private String fieldCode;

    @Schema(description = "状态原始值（来自业务数据，如“正常开放”）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "状态原始值（来自业务数据，如“正常开放”）不能为空")
    private String rawValue;

    @Schema(description = "状态展示文本（前端显示用）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "状态展示文本（前端显示用）不能为空")
    private String displayText;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}
