package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetshareattrcfg.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资产共享属性配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AssetShareAttrCfgPageReqVO extends PageParam {

    @Schema(description = "共享属性ID", example = "11393")
    private String assetShareAttrId;

    @Schema(description = "关联资产ID", example = "23228")
    private String relAssetId;

    @Schema(description = "关联资产名称", example = "芋艿")
    private String relAssetName;

    @Schema(description = "属性名称", example = "张三")
    private String attrName;

    @Schema(description = "属性代码")
    private String attrCode;

    @Schema(description = "属性值")
    private String attrValue;

    @Schema(description = "共享对象类型", example = "2")
    private String shareObjType;

    @Schema(description = "共享对象ID", example = "2367")
    private String shareObjId;

    @Schema(description = "共享对象名称", example = "芋艿")
    private String shareObjName;

    @Schema(description = "共享权限")
    private String sharePerm;

    @Schema(description = "配置时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] cfgTime;

    @Schema(description = "操作人")
    private String operUser;

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