package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetserverattrcfg.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资产服务端属性配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AssetServerAttrCfgPageReqVO extends PageParam {

    @Schema(description = "服务端属性ID", example = "6917")
    private String assetServerAttrId;

    @Schema(description = "关联资产ID", example = "4342")
    private String relAssetId;

    @Schema(description = "关联资产名称", example = "张三")
    private String relAssetName;

    @Schema(description = "属性名称", example = "芋艿")
    private String attrName;

    @Schema(description = "属性代码")
    private String attrCode;

    @Schema(description = "属性数据类型", example = "2")
    private String attrDataType;

    @Schema(description = "属性值")
    private String attrValue;

    @Schema(description = "采集频率")
    private String collectFreq;

    @Schema(description = "最后采集时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastCollectTime;

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

    @Schema(description = "排序的字段")
    private String orderByColumn;
    @Schema(description = "排序方式", example = "asc、desc")
    private String isAsc;
}