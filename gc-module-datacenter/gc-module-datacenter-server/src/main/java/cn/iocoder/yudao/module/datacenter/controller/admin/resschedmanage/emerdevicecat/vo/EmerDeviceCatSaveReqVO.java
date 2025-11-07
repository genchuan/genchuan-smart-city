package cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdevicecat.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 救援设备分类配置新增/修改 Request VO")
@Data
public class EmerDeviceCatSaveReqVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1161")
    private Long id;

    @Schema(description = "分类ID，唯一编码，UUID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24535")
    @NotEmpty(message = "分类ID，唯一编码，UUID不能为空")
    private String deviceCatId;

    @Schema(description = "父分类ID，关联本表“分类ID”", requiredMode = Schema.RequiredMode.REQUIRED, example = "17879")
    @NotEmpty(message = "父分类ID，关联本表“分类ID”不能为空")
    private String parentCatId;

    @Schema(description = "分类层级，1（大类）/2（中类）/3（小类）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分类层级，1（大类）/2（中类）/3（小类）不能为空")
    private String catLevel;

    @Schema(description = "分类编码，001 - 999（大类01 - 99、中类01 - 99、小类001 - 999）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分类编码，001 - 999（大类01 - 99、中类01 - 99、小类001 - 999）不能为空")
    private String catCode;

    @Schema(description = "分类名称，标准名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "分类名称，标准名称不能为空")
    private String catName;

    @Schema(description = "分类说明，描述分类范围")
    private String catDesc;

    @Schema(description = "适用区域代码，关联行政区划表")
    private String applyRegionCode;

    @Schema(description = "适用区域名称，与区域代码同步", example = "李四")
    private String applyRegionName;

}