package cn.iocoder.yudao.module.smartcity.controller.admin.patrolresources.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 巡查资源新增/修改 Request VO")
@Data
public class PatrolResourcesSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "999")
    private Long id;

    @Schema(description = "资源名称", example = "张三")
    private String resourceName;

    @Schema(description = "资源编号")
    private String resourceNumber;

    @Schema(description = "资源类型", example = "1")
    private String resourceType;

    @Schema(description = "地理位置")
    private String geographicLocation;

    @Schema(description = "区域划分")
    private String regionalDivision;

    @Schema(description = "规格型号")
    private String specificationAndModel;

}