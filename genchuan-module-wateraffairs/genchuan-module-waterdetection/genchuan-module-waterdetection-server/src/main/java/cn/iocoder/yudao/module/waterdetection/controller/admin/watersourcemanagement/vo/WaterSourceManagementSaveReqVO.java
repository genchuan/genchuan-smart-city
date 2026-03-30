package cn.iocoder.yudao.module.waterdetection.controller.admin.watersourcemanagement.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 水源类型及属性管理新增/修改 Request VO")
@Data
public class WaterSourceManagementSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "水源编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "水源编码不能为空")
    private String sourceCode;

    @Schema(description = "水源名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "水源名称不能为空")
    private String sourceName;

    @Schema(description = "水源类型")
    private String sourceType;

    @Schema(description = "经度")
    private Double longitude;

    @Schema(description = "纬度")
    private Double latitude;

    @Schema(description = "所属行政区")
    private String administrativeRegion;

    @Schema(description = "水源描述")
    private String sourceDescription;

}