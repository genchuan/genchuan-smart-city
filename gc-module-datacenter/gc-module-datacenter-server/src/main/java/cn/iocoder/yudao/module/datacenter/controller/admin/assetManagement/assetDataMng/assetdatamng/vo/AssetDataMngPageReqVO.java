package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdatamng.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资产数据管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AssetDataMngPageReqVO extends PageParam {

    @Schema(description = "资产数据ID", example = "1418")
    private String assetDataId;

    @Schema(description = "关联资产分类ID", example = "15050")
    private String relAssetCatId;

    @Schema(description = "关联资产分类名称", example = "张三")
    private String relAssetCatName;

    @Schema(description = "资产编码")
    private String assetCode;

    @Schema(description = "资产名称", example = "李四")
    private String assetName;

    @Schema(description = "资产状态", example = "1")
    private String assetStatus;

    @Schema(description = "安装时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] installTime;

    @Schema(description = "使用年限")
    private Integer serviceLife;

    @Schema(description = "资产负责人")
    private String assetManager;

    @Schema(description = "资产负责人电话")
    private String assetManagerTel;

    @Schema(description = "资产描述")
    private String assetDesc;

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

    @Schema(description = "排序的字段，如：createdTime")
    private String orderByColumn;
    @Schema(description = "排序方式，asc升序、desc降序")
    private String isAsc;
}