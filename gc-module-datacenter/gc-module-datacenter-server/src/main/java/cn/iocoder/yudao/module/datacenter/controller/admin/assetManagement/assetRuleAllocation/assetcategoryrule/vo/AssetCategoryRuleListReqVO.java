package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetcategoryrule.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资产分类规则配置列表 Request VO")
@Data
public class AssetCategoryRuleListReqVO {

    @Schema(description = "父级编号", example = "26645")
    private Long parentId;

    @Schema(description = "名字", example = "李四")
    private String name;

    @Schema(description = "分类规则ID", example = "17326")
    private String categoryRuleId;

    @Schema(description = "父类规则ID", example = "16844")
    private String parentCategoryRuleId;

    @Schema(description = "分类层级")
    private String categoryLevel;

    @Schema(description = "分类代码")
    private String categoryCode;

    @Schema(description = "分类名称", example = "李四")
    private String categoryName;

    @Schema(description = "分类说明")
    private String categoryDesc;

    @Schema(description = "启用状态", example = "1")
    private String enableStatus;

    @Schema(description = "创建人")
    private String createdUser;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createdTime;

    @Schema(description = "更新人")
    private String updatedUser;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updatedTime;

    @Schema(description = "扩展字段1")
    private String extCategory1;

    @Schema(description = "扩展字段2")
    private String extCategory2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}