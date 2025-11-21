package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetshareattrcfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资产共享属性配置新增/修改 Request VO")
@Data
public class AssetShareAttrCfgSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2312")
    private Long id;

    @Schema(description = "共享属性ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11393")
//    @NotEmpty(message = "共享属性ID不能为空")
    private String assetShareAttrId;

    @Schema(description = "关联资产ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23228")
    @NotEmpty(message = "关联资产ID不能为空")
    private String relAssetId;

    @Schema(description = "关联资产名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "关联资产名称不能为空")
    private String relAssetName;

    @Schema(description = "属性名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "属性名称不能为空")
    private String attrName;

    @Schema(description = "属性代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "属性代码不能为空")
    private String attrCode;

    @Schema(description = "属性值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "属性值不能为空")
    private String attrValue;

    @Schema(description = "共享对象类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "共享对象类型不能为空")
    private String shareObjType;

    @Schema(description = "共享对象ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2367")
    @NotEmpty(message = "共享对象ID不能为空")
    private String shareObjId;

    @Schema(description = "共享对象名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "共享对象名称不能为空")
    private String shareObjName;

    @Schema(description = "共享权限", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "共享权限不能为空")
    private String sharePerm;

    @Schema(description = "配置时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "配置时间不能为空")
    private LocalDateTime cfgTime;

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