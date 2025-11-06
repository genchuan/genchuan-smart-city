package cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdevicecat.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 救援设备分类配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EmerDeviceCatRespVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1161")
    @ExcelProperty("自增主键ID")
    private Long id;

    @Schema(description = "分类ID，唯一编码，UUID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24535")
    @ExcelProperty("分类ID，唯一编码，UUID")
    private String deviceCatId;

    @Schema(description = "父分类ID，关联本表“分类ID”", requiredMode = Schema.RequiredMode.REQUIRED, example = "17879")
    @ExcelProperty("父分类ID，关联本表“分类ID”")
    private String parentCatId;

    @Schema(description = "分类层级，1（大类）/2（中类）/3（小类）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分类层级，1（大类）/2（中类）/3（小类）")
    private String catLevel;

    @Schema(description = "分类编码，001 - 999（大类01 - 99、中类01 - 99、小类001 - 999）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分类编码，001 - 999（大类01 - 99、中类01 - 99、小类001 - 999）")
    private String catCode;

    @Schema(description = "分类名称，标准名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("分类名称，标准名称")
    private String catName;

    @Schema(description = "分类说明，描述分类范围")
    @ExcelProperty("分类说明，描述分类范围")
    private String catDesc;

    @Schema(description = "适用区域代码，关联行政区划表")
    @ExcelProperty("适用区域代码，关联行政区划表")
    private String applyRegionCode;

    @Schema(description = "适用区域名称，与区域代码同步", example = "李四")
    @ExcelProperty("适用区域名称，与区域代码同步")
    private String applyRegionName;

}