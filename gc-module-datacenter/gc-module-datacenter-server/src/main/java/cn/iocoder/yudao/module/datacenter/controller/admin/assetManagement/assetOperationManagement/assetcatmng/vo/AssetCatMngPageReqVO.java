package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资产分类管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AssetCatMngPageReqVO extends PageParam {

    @Schema(description = "资产分类ID", example = "30359")
    private String assetCatId;

    @Schema(description = "关联分类规则ID", example = "30418")
    private String relCatRuleId;

    @Schema(description = "资产分类编码")
    private String assetCatCode;

    @Schema(description = "资产分类名称", example = "王五")
    private String assetCatName;

    @Schema(description = "分类层级")
    private String catLevel;

    @Schema(description = "上级分类ID", example = "25267")
    private String parentCatId;

    @Schema(description = "上级分类名称", example = "王五")
    private String parentCatName;

    @Schema(description = "分类说明")
    private String catDesc;

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

    @Schema(description = "排序的列")
    private String orderByColumn;
    @Schema(description = "排序方式")
    private String isAsc;
}