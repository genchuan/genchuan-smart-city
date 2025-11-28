package cn.iocoder.yudao.module.datacenter.controller.admin.eventdisposition.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 监测事件关联处置新增/修改 Request VO")
@Data
public class EventDispositionSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "事件类型ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "事件类型ID不能为空")
    private String eventTypeId;

    @Schema(description = "事件类型名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "事件类型名称不能为空")
    private String eventTypeName;

    @Schema(description = "行政区划编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划编码不能为空")
    private String divisionCode;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划名称不能为空")
    private String divisionName;

    @Schema(description = "流程模型ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "流程模型ID不能为空")
    private String processModelId;

    @Schema(description = "流程模型名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "流程模型名称不能为空")
    private String processModelName;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}