package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetclientattrcfg.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资产客户端属性配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AssetClientAttrCfgPageReqVO extends PageParam {

    @Schema(description = "客户端属性ID", example = "29413")
    private String assetClientAttrId;

    @Schema(description = "关联资产ID", example = "30407")
    private String relAssetId;

    @Schema(description = "关联资产名称", example = "李四")
    private String relAssetName;

    @Schema(description = "属性名称", example = "李四")
    private String attrName;

    @Schema(description = "属性代码")
    private String attrCode;

    @Schema(description = "属性数据类型", example = "2")
    private String attrDataType;

    @Schema(description = "属性值")
    private String attrValue;

    @Schema(description = "属性用途")
    private String attrPurpose;

    @Schema(description = "客户端IP")
    private String clientIp;

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

    @Schema(description = "排序的字段名", example = "cfgTime")
    private String orderByColumn;
    @Schema(description = "排序方式", example = "asc、desc")
    private String isAsc;
}