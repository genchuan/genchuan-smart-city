package cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emermatcat.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 应急物资分类配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmerMatCatPageReqVO extends PageParam {

    @Schema(description = "分类ID，唯一编码，UUID", example = "15606")
    private String matCatId;

    @Schema(description = "父分类ID，关联本表“分类ID”", example = "8326")
    private String parentCatId;

    @Schema(description = "分类层级，1（大类）/2（中类）/3（小类）")
    private String catLevel;

    @Schema(description = "分类编码，001 - 999（大类01 - 99、中类01 - 99、小类001 - 999）")
    private String catCode;

    @Schema(description = "分类名称，标准名称", example = "王五")
    private String catName;

    @Schema(description = "分类说明，描述分类范围")
    private String catDesc;

    @Schema(description = "适用区域代码，关联行政区划表")
    private String applyRegionCode;

    @Schema(description = "适用区域名称，与区域代码同步", example = "赵六")
    private String applyRegionName;

}