package cn.iocoder.yudao.module.waterdetection.controller.admin.structureparammanage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 构建筑物参数管理新增/修改 Request VO")
@Data
public class StructureParamManageSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "构建筑物名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "构建筑物名称不能为空")
    private String structureName;

    @Schema(description = "类型(沉淀池/滤池/清水池等)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "类型(沉淀池/滤池/清水池等)不能为空")
    private String structureType;

    @Schema(description = "长度(米)")
    private Double length;

    @Schema(description = "宽度(米)")
    private Double width;

    @Schema(description = "深度(米)")
    private Double depth;

    @Schema(description = "有效容积(立方米)")
    private Double effectiveVolume;

    @Schema(description = "建设时间")
    private LocalDateTime constructionTime;

}