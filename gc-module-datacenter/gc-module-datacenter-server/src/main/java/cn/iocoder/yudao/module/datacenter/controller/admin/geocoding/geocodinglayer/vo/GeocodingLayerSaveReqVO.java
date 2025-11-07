package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodinglayer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 图层代码配置新增/修改 Request VO")
@Data
public class GeocodingLayerSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "配置ID")
    private String layerConfigId;

    @Schema(description = "图层代码")
    private String layerCode;

    @Schema(description = "图层名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "图层名称不能为空")
    private String layerName;

    @Schema(description = "图层说明")
    private String layerDesc;

    @Schema(description = "启用状态(0-禁用,1-启用)")
    private Integer enableStatus;

    @Schema(description = "配置人")
    private String configUser;

    @Schema(description = "配置时间")
    private LocalDateTime configTime;

    @Schema(description = "分类扩展字段1")
    private String extCategory1;

    @Schema(description = "分类扩展字段2")
    private String extCategory2;

    @Schema(description = "分类扩展字段3")
    private String extCategory3;

}