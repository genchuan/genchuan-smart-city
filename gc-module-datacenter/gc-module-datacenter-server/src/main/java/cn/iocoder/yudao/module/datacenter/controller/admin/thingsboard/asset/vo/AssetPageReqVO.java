package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资产分页 Request VO")
@Data
public class AssetPageReqVO extends PageParam {

    @Schema(description = "资产ID", example = "32353")
    private String assetId;

    @Schema(description = "实体类型", example = "1")
    private String entityType;

    @Schema(description = "创建时间戳")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Long[] createdTime;

    @Schema(description = "租户实体类型", example = "1")
    private String tenantEntityType;

    @Schema(description = "客户ID", example = "29253")
    private String customerId;

    @Schema(description = "客户实体类型", example = "2")
    private String customerEntityType;

    @Schema(description = "资产档案ID", example = "31817")
    private String assetProfileId;

    @Schema(description = "资产档案实体类型", example = "1")
    private String assetProfileEntityType;

    @Schema(description = "资产名称", example = "张三")
    private String assetName;

    @Schema(description = "资产类型", example = "2")
    private String assetType;

    @Schema(description = "资产标签")
    private String assetLabel;

    @Schema(description = "外部ID", example = "3674")
    private String externalId;

    @Schema(description = "版本号")
    private Integer version;

    @Schema(description = "客户标题")
    private String customerTitle;

    @Schema(description = "客户是否公开")
    private Boolean customerIsPublic;

    @Schema(description = "资产档案名称", example = "王五")
    private String assetProfileName;

    @Schema(description = "附加信息")
    private String additionalInfo;

    @Schema(description = "属性列表")
    private String attributes;

    @Schema(description = "关联设备列表")
    private String contextDevices;

    @Schema(description = "关联资产列表")
    private String contextAsset;

    @Schema(description = "系统租户ID")
    private Long tenantIdSys;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;
}