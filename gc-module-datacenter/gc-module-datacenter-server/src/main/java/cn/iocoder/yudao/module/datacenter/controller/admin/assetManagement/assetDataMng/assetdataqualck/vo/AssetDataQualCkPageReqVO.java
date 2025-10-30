package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdataqualck.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资产数据质量检查分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AssetDataQualCkPageReqVO extends PageParam {

    @Schema(description = "质量检查ID", example = "31677")
    private String assetDataQualId;

    @Schema(description = "关联资产ID", example = "9910")
    private String relAssetId;

    @Schema(description = "关联资产名称", example = "张三")
    private String relAssetName;

    @Schema(description = "检查数据类型", example = "1")
    private String ckDataType;

    @Schema(description = "检查项名称", example = "李四")
    private String ckItemName;

    @Schema(description = "检查项代码")
    private String ckItemCode;

    @Schema(description = "检查结果")
    private String ckResult;

    @Schema(description = "错误描述")
    private String errorDesc;

    @Schema(description = "检查时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] ckTime;

    @Schema(description = "检查方式")
    private String ckMethod;

    @Schema(description = "处理状态", example = "1")
    private String handleStatus;

    @Schema(description = "处理人")
    private String handleUser;

    @Schema(description = "处理时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] handleTime;

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

    @Schema(description = "排序的列名")
    private String orderByColumn;
    @Schema(description = "排序方式")
    private String isAsc;
}