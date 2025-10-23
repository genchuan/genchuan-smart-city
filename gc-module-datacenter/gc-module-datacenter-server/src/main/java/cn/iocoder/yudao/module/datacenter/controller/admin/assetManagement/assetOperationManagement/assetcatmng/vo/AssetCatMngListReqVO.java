package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资产分类管理列表 Request VO")
@Data
public class AssetCatMngListReqVO {

    @Schema(description = "父级编号", example = "28154")
    private Long parentId;

    @Schema(description = "名字", example = "张三")
    private String name;

    @Schema(description = "资产分类ID", example = "20927")
    private String assetCatId;

    @Schema(description = "关联分类规则ID", example = "29078")
    private String relCatRuleId;

    @Schema(description = "资产分类编码")
    private String assetCatCode;

    @Schema(description = "资产分类名称", example = "赵六")
    private String assetCatName;

    @Schema(description = "分类层级")
    private String catLevel;

    @Schema(description = "上级分类ID", example = "987")
    private String parentCatId;

    @Schema(description = "上级分类名称", example = "赵六")
    private String parentCatName;

    @Schema(description = "分类说明")
    private String catDesc;

    @Schema(description = "启用状态", example = "1")
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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}