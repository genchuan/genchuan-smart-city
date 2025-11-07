package cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerplancat.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 预案分类配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EmerPlanCatRespVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17071")
    @ExcelProperty("自增主键ID")
    private Long id;

    @Schema(description = "分类ID，唯一编码，UUID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7075")
    @ExcelProperty("分类ID，唯一编码，UUID")
    private String planCatId;

    @Schema(description = "父分类ID，关联本表“分类ID”，大类父ID为“0”", requiredMode = Schema.RequiredMode.REQUIRED, example = "1547")
    @ExcelProperty("父分类ID，关联本表“分类ID”，大类父ID为“0”")
    private String parentCatId;

    @Schema(description = "分类层级，1（大类）/2（中类）/3（小类）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分类层级，1（大类）/2（中类）/3（小类）")
    private String catLevel;

    @Schema(description = "分类编码，001 - 999（大类01 - 99、中类01 - 99、小类001 - 999）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分类编码，001 - 999（大类01 - 99、中类01 - 99、小类001 - 999）")
    private String catCode;

    @Schema(description = "分类名称，标准名称，如“自然灾害预案”“洪水预案”", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("分类名称，标准名称，如“自然灾害预案”“洪水预案”")
    private String catName;

    @Schema(description = "分类说明，描述分类范围，如“城市内涝预案：适用于城市暴雨内涝应急处置”")
    @ExcelProperty("分类说明，描述分类范围，如“城市内涝预案：适用于城市暴雨内涝应急处置”")
    private String catDesc;

    @Schema(description = "适用区域代码，关联行政区划表，限定区域适用")
    @ExcelProperty("适用区域代码，关联行政区划表，限定区域适用")
    private String applyRegionCode;

    @Schema(description = "适用区域名称，与区域代码同步", example = "赵六")
    @ExcelProperty("适用区域名称，与区域代码同步")
    private String applyRegionName;

}