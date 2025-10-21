package cn.iocoder.yudao.module.datacenter.controller.admin.assetattrrule.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资产属性规则配置列表 Request VO")
@Data
public class AssetAttrRuleListReqVO {

    @Schema(description = "父级编号", example = "19931")
    private Long parentId;

    @Schema(description = "属性规则ID", example = "8208")
    private String attrRuleId;

    @Schema(description = "资产分类ID", example = "19918")
    private String assetCategoryId;

    @Schema(description = "资产分类名称", example = "李四")
    private String assetCategoryName;

    @Schema(description = "属性名称", example = "王五")
    private String attrName;

    @Schema(description = "属性代码")
    private String attrCode;

    @Schema(description = "数据类型", example = "2")
    private String dataType;

    @Schema(description = "字段长度")
    private Integer fieldLength;

    @Schema(description = "是否必选")
    private String isRequired;

    @Schema(description = "计量单位")
    private String unit;

    @Schema(description = "值域范围")
    private String valueRange;

    @Schema(description = "默认值")
    private String defaultValue;

    @Schema(description = "属性说明")
    private String attrDesc;

    @Schema(description = "启用状态", example = "2")
    private String enableStatus;

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
    private String extCategory1;

    @Schema(description = "分类扩展字段2")
    private String extCategory2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间（系统）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "名字", example = "芋艿")
    private String name;

}