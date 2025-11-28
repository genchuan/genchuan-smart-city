package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资产 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssetRespVO {

    @Schema(description = "资产ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19775")
    @ExcelProperty("资产ID")
    private String id;

    @Schema(description = "租户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10102")
    @ExcelProperty("租户ID")
    private String tenantId;

    @Schema(description = "客户ID", example = "4677")
    @ExcelProperty("客户ID")
    private String customerId;

    @Schema(description = "资产名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "资产1")
    @ExcelProperty("资产名称")
    private String name;

    @Schema(description = "资产类型", example = "building")
    @ExcelProperty("资产类型")
    private String type;

    @Schema(description = "标签")
    @ExcelProperty("标签")
    private String label;

    @Schema(description = "资产实体ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28197")
    @ExcelProperty("资产实体ID")
    private String assetProfileId;

    @Schema(description = "附加信息")
    @ExcelProperty("附加信息")
    private String additionalInfo;

    @Schema(description = "外部ID", example = "21772")
    @ExcelProperty("外部ID")
    private String externalId;

    @Schema(description = "版本")
    @ExcelProperty("版本")
    private Long version;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTime;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createdTime;

}