package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.assetprofile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 资产配置信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssetProfileRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4664")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "资产配置ID", example = "17085")
    @ExcelProperty("资产配置ID")
    private String profileId;

    @Schema(description = "实体类型", example = "1")
    @ExcelProperty("实体类型")
    private String entityType;

    @Schema(description = "创建时间戳")
    @ExcelProperty("创建时间戳")
    private Long createdTime;

    @Schema(description = "租户ID")
    @ExcelProperty("租户ID")
    private String tenantIdTb;

    @Schema(description = "租户实体类型", example = "2")
    @ExcelProperty("租户实体类型")
    private String tenantEntityType;

    @Schema(description = "资产配置名称", example = "张三")
    @ExcelProperty("资产配置名称")
    private String profileName;

    @Schema(description = "资产配置描述", example = "随便")
    @ExcelProperty("资产配置描述")
    private String profileDescription;

    @Schema(description = "资产配置图片")
    @ExcelProperty("资产配置图片")
    private String profileImage;

    @Schema(description = "默认规则链ID", example = "28630")
    @ExcelProperty("默认规则链ID")
    private String defaultRuleChainId;

    @Schema(description = "默认仪表板ID", example = "2833")
    @ExcelProperty("默认仪表板ID")
    private String defaultDashboardId;

    @Schema(description = "默认队列名称", example = "赵六")
    @ExcelProperty("默认队列名称")
    private String defaultQueueName;

    @Schema(description = "默认边缘规则链ID", example = "15077")
    @ExcelProperty("默认边缘规则链ID")
    private String defaultEdgeRuleChainId;

    @Schema(description = "外部ID", example = "25671")
    @ExcelProperty("外部ID")
    private String externalId;

    @Schema(description = "外部实体类型", example = "1")
    @ExcelProperty("外部实体类型")
    private String externalEntityType;

    @Schema(description = "版本号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("版本号")
    private Integer version;

    @Schema(description = "是否默认配置")
    @ExcelProperty("是否默认配置")
    private Boolean isDefault;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;


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