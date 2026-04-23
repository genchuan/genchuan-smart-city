package cn.iocoder.yudao.module.inspectop.controller.admin.assetinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资产信息新增/修改 Request VO")
@Data
public class AssetInfoSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "资产名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "资产名称不能为空")
    private String name;

    @Schema(description = "资产类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "资产类型不能为空")
    private String type;

    @Schema(description = "采购时间")
    private LocalDateTime purchaseTime;

    @Schema(description = "资产状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "资产状态不能为空")
    private String status;

    @Schema(description = "所属场站ID")
    private Long stationId;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}