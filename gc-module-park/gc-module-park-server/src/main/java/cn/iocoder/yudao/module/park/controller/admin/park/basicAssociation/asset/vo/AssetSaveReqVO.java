package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.asset.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资产-thingsboard新增/修改 Request VO")
@Data
public class AssetSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30235")
    private Long id;

    @Schema(description = "资产编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "资产编码不能为空")
    private String assetCode;

    @Schema(description = "资产名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "资产名称不能为空")
    private String assetName;

    @Schema(description = "资产类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "资产类型不能为空")
    private String assetType;

    @Schema(description = "所属区域编码")
    private String regionCode;

    @Schema(description = "状态：正常/停用", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "状态：正常/停用不能为空")
    private String assetStatus;

    @Schema(description = "入账时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "入账时间不能为空")
    private LocalDateTime entryTime;

    @Schema(description = "业务创建时间")
    private LocalDateTime assetCreateTime;

    @Schema(description = "业务更新时间")
    private LocalDateTime assetUpdateTime;

    @Schema(description = "业务备注", example = "你说的对")
    private String assetRemark;

}