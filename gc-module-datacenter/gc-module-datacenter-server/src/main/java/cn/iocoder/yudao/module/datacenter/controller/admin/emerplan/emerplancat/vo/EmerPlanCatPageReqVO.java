package cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerplancat.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 预案分类配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmerPlanCatPageReqVO extends PageParam {

    @Schema(description = "分类ID，唯一编码，UUID", example = "7075")
    private String planCatId;

    @Schema(description = "父分类ID，关联本表“分类ID”，大类父ID为“0”", example = "1547")
    private String parentCatId;

    @Schema(description = "分类层级，1（大类）/2（中类）/3（小类）")
    private String catLevel;

    @Schema(description = "分类编码，001 - 999（大类01 - 99、中类01 - 99、小类001 - 999）")
    private String catCode;

    @Schema(description = "分类名称，标准名称，如“自然灾害预案”“洪水预案”", example = "王五")
    private String catName;

    @Schema(description = "分类说明，描述分类范围，如“城市内涝预案：适用于城市暴雨内涝应急处置”")
    private String catDesc;

    @Schema(description = "适用区域代码，关联行政区划表，限定区域适用")
    private String applyRegionCode;

    @Schema(description = "适用区域名称，与区域代码同步", example = "赵六")
    private String applyRegionName;

}