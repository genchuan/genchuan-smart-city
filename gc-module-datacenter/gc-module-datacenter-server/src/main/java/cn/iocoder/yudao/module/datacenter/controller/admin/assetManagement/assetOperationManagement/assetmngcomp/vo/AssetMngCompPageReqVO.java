package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetmngcomp.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资产关联管理部件分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AssetMngCompPageReqVO extends PageParam {

    @Schema(description = "关联ID", example = "25311")
    private String assetRelMngCompId;

    @Schema(description = "关联资产ID", example = "24803")
    private String relAssetId;

    @Schema(description = "关联资产名称", example = "赵六")
    private String relAssetName;

    @Schema(description = "管理部件ID", example = "30706")
    private String mngCompId;

    @Schema(description = "管理部件编码")
    private String mngCompCode;

    @Schema(description = "管理部件名称", example = "张三")
    private String mngCompName;

    @Schema(description = "关联时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] relTime;

    @Schema(description = "操作人")
    private String operUser;

    @Schema(description = "关联说明")
    private String relDesc;

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

    @Schema(description = "排序的字段，如：createdTime")
    private String orderByColumn;
    @Schema(description = "排序方式，asc升序、desc降序")
    private String isAsc;
}