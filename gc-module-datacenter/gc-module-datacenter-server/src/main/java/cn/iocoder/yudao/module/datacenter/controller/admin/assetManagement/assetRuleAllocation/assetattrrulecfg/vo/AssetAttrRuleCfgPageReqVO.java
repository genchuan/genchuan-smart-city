package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetattrrulecfg.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资产属性规则配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AssetAttrRuleCfgPageReqVO extends PageParam {

    @Schema(description = "属性规则ID", example = "24587")
    private String assetAttrRuleId;

    @Schema(description = "关联资产分类ID", example = "27262")
    private String relAssetCatId;

    @Schema(description = "关联资产分类名称", example = "芋艿")
    private String relAssetCatName;

    @Schema(description = "属性名称", example = "芋艿")
    private String assetAttrName;

    @Schema(description = "属性代码")
    private String assetAttrCode;

    @Schema(description = "属性数据类型", example = "1")
    private String attrDataType;

    @Schema(description = "属性长度")
    private String attrLength;

    @Schema(description = "属性值域")
    private String attrValueRange;

    @Schema(description = "是否必选")
    private String isRequired;

    @Schema(description = "属性说明")
    private String attrDesc;

    @Schema(description = "创建人")
    private String createUser;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createdTime;

    @Schema(description = "更新人")
    private String updateUser;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updatedTime;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "排序的字段，如：createdTime")
    private String orderByColumn;
    @Schema(description = "排序方式，asc升序、desc降序")
    private String isAsc;
}