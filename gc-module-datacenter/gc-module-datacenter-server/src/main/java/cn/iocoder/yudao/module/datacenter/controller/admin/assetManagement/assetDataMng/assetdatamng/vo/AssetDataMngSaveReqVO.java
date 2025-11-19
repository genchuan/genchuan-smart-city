package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdatamng.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资产数据管理新增/修改 Request VO")
@Data
public class AssetDataMngSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17423")
    private Long id;

    @Schema(description = "资产数据ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1418")
//    @NotEmpty(message = "资产数据ID不能为空")
    private String assetDataId;

    @Schema(description = "关联资产分类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15050")
    @NotEmpty(message = "关联资产分类ID不能为空")
    private String relAssetCatId;

    @Schema(description = "关联资产分类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "关联资产分类名称不能为空")
    private String relAssetCatName;

    @Schema(description = "资产编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "资产编码不能为空")
    private String assetCode;

    @Schema(description = "资产名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "资产名称不能为空")
    private String assetName;

    @Schema(description = "资产状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "资产状态不能为空")
    private String assetStatus;

    @Schema(description = "安装时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "安装时间不能为空")
    private LocalDateTime installTime;

    @Schema(description = "使用年限")
    private Integer serviceLife;

    @Schema(description = "资产负责人")
    private String assetManager;

    @Schema(description = "资产负责人电话")
    private String assetManagerTel;

    @Schema(description = "资产描述")
    private String assetDesc;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人不能为空")
    private String createUser;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间不能为空")
    private LocalDateTime createdTime;

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