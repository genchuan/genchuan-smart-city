package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetspatialdata.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资产空间数据新增/修改 Request VO")
@Data
public class AssetSpatialDataSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3246")
    private Long id;

    @Schema(description = "空间数据ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16354")
//    @NotEmpty(message = "空间数据ID不能为空")
    private String assetSpatialId;

    @Schema(description = "关联资产ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5840")
    @NotEmpty(message = "关联资产ID不能为空")
    private String relAssetId;

    @Schema(description = "关联资产名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "关联资产名称不能为空")
    private String relAssetName;

    @Schema(description = "坐标系类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "坐标系类型不能为空")
    private String coordSystemType;

    @Schema(description = "坐标X", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "坐标X不能为空")
    private BigDecimal coordX;

    @Schema(description = "坐标Y", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "坐标Y不能为空")
    private BigDecimal coordY;

    @Schema(description = "高程")
    private BigDecimal elevation;

    @Schema(description = "边界坐标")
    private String boundaryCoords;

    @Schema(description = "空间数据来源", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "空间数据来源不能为空")
    private String spatialDataSource;

    @Schema(description = "录入时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "录入时间不能为空")
    private LocalDateTime inputTime;

    @Schema(description = "操作人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "操作人不能为空")
    private String operUser;

    @Schema(description = "更新人")
    private String updateUser;

    @Schema(description = "更新时间")
    private LocalDateTime updatedTime;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}