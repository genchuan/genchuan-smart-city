package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资产分类管理新增/修改 Request VO")
@Data
public class AssetCatMngSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21063")
    private Long id;

    @Schema(description = "父级编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "28154")
    @NotNull(message = "父级编号不能为空")
    private Long parentId;

    @Schema(description = "名字", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "名字不能为空")
    private String name;

    @Schema(description = "资产分类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20927")
    @NotEmpty(message = "资产分类ID不能为空")
    private String assetCatId;

    @Schema(description = "关联分类规则ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29078")
    @NotEmpty(message = "关联分类规则ID不能为空")
    private String relCatRuleId;

    @Schema(description = "资产分类编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "资产分类编码不能为空")
    private String assetCatCode;

    @Schema(description = "资产分类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "资产分类名称不能为空")
    private String assetCatName;

    @Schema(description = "分类层级", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分类层级不能为空")
    private String catLevel;

    @Schema(description = "上级分类ID", example = "987")
    private String parentCatId;

    @Schema(description = "上级分类名称", example = "赵六")
    private String parentCatName;

    @Schema(description = "分类说明")
    private String catDesc;

    @Schema(description = "启用状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "启用状态不能为空")
    private String enableStatus;

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