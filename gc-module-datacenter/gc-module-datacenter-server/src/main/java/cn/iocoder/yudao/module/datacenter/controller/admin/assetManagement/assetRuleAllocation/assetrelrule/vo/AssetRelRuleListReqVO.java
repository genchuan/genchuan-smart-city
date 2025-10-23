package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetrelrule.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资产关联规则配置列表 Request VO")
@Data
public class AssetRelRuleListReqVO {

    @Schema(description = "父级编号", example = "25172")
    private Long parentId;

    @Schema(description = "名字", example = "李四")
    private String name;

    @Schema(description = "关联规则ID（UUID）", example = "19823")
    private String relRuleId;

    @Schema(description = "资产分类ID（小类）", example = "18297")
    private String assetCategoryId;

    @Schema(description = "资产分类名称", example = "赵六")
    private String assetCategoryName;

    @Schema(description = "关联对象类型", example = "2")
    private String relObjType;

    @Schema(description = "关联对象ID", example = "17845")
    private String relObjId;

    @Schema(description = "关联对象名称", example = "赵六")
    private String relObjName;

    @Schema(description = "关联必填标识：1必选/0可选", example = "0")
    private String isRequired;

    @Schema(description = "关联校验规则")
    private String relCheckRule;

    @Schema(description = "启用状态：1启用/0禁用", example = "2")
    private String enableStatus;

    @Schema(description = "创建人（用户ID）")
    private String createUser;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createdTime;

    @Schema(description = "更新人（用户ID）")
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

}