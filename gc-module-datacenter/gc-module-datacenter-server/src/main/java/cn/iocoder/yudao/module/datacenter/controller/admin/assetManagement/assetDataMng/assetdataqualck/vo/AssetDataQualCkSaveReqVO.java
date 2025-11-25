package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdataqualck.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资产数据质量检查新增/修改 Request VO")
@Data
public class AssetDataQualCkSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1042")
    private Long id;

    @Schema(description = "质量检查ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31677")
//    @NotEmpty(message = "质量检查ID不能为空")
    private String assetDataQualId;

    @Schema(description = "关联资产ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9910")
    @NotEmpty(message = "关联资产ID不能为空")
    private String relAssetId;

    @Schema(description = "关联资产名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "关联资产名称不能为空")
    private String relAssetName;

    @Schema(description = "检查数据类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "检查数据类型不能为空")
    private String ckDataType;

    @Schema(description = "检查项名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "检查项名称不能为空")
    private String ckItemName;

    @Schema(description = "检查项代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "检查项代码不能为空")
    private String ckItemCode;

    @Schema(description = "检查结果", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "检查结果不能为空")
    private String ckResult;

    @Schema(description = "错误描述")
    private String errorDesc;

    @Schema(description = "检查时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "检查时间不能为空")
    private LocalDateTime ckTime;

    @Schema(description = "检查方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "检查方式不能为空")
    private String ckMethod;

    @Schema(description = "处理状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "处理状态不能为空")
    private String handleStatus;

    @Schema(description = "处理人")
    private String handleUser;

    @Schema(description = "处理时间")
    private LocalDateTime handleTime;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}