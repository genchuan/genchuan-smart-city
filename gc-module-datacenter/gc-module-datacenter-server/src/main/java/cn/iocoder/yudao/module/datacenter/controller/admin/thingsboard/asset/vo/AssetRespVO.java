package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo;

import cn.iocoder.yudao.module.datacenter.framework.util.RawJsonSerializer;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.asset.AssetServiceImpl;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.*;

@Schema(description = "管理后台 - 资产 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssetRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16638")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "资产ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "32353")
    @ExcelProperty("资产ID")
    private String assetId;

    @Schema(description = "实体类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("实体类型")
    private String entityType;

    @Schema(description = "创建时间戳", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间戳")
    private Long createdTime;

    @Schema(description = "租户实体类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("租户实体类型")
    private String tenantEntityType;

    @Schema(description = "客户ID", example = "29253")
    @ExcelProperty("客户ID")
    private String customerId;

    @Schema(description = "客户实体类型", example = "2")
    @ExcelProperty("客户实体类型")
    private String customerEntityType;

    @Schema(description = "资产档案ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31817")
    @ExcelProperty("资产档案ID")
    private String assetProfileId;

    @Schema(description = "资产档案实体类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("资产档案实体类型")
    private String assetProfileEntityType;

    @Schema(description = "资产名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("资产名称")
    private String assetName;

    @Schema(description = "资产类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("资产类型")
    private String assetType;

    @Schema(description = "资产标签")
    @ExcelProperty("资产标签")
    private String assetLabel;

    @Schema(description = "外部ID", example = "3674")
    @ExcelProperty("外部ID")
    private String externalId;

    @Schema(description = "版本号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("版本号")
    private Integer version;

    @Schema(description = "客户标题")
    @ExcelProperty("客户标题")
    private String customerTitle;

    @Schema(description = "客户是否公开")
    @ExcelProperty("客户是否公开")
    private Boolean customerIsPublic;

    @Schema(description = "资产档案名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("资产档案名称")
    private String assetProfileName;

    @Schema(description = "附加信息")
    @ExcelProperty("附加信息")
    private String additionalInfo;

    @Schema(description = "属性列表")
    @JsonSerialize(using = RawJsonSerializer.class)  // 使用自定义序列化器
    private String attributes;

    @Schema(description = "关联设备列表")
    @JsonSerialize(using = RawJsonSerializer.class)  // 使用自定义序列化器
    private String contextDevices;

    @Schema(description = "关联资产列表")
    @JsonSerialize(using = RawJsonSerializer.class)
    private String contextAsset;

    @Schema(description = "系统租户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("系统租户ID")
    private Long tenantIdSys;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    private static final Logger log = LoggerFactory.getLogger(AssetServiceImpl.class);

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;
}