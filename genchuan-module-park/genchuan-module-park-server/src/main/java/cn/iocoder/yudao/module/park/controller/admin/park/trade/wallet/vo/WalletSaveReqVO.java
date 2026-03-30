package cn.iocoder.yudao.module.park.controller.admin.park.trade.wallet.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 用户钱包新增/修改 Request VO")
@Data
public class WalletSaveReqVO {

    @Schema(description = "[主键ID] 用户钱包唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "20842")
    private Long id;

    @Schema(description = "[用户ID] 用户唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "28666")
    @NotNull(message = "[用户ID] 用户唯一标识不能为空")
    private Long userId;

    @Schema(description = "[当前余额] 用户钱包当前余额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[当前余额] 用户钱包当前余额不能为空")
    private BigDecimal balance;

    @Schema(description = "[状态] 如:冻结/正常", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[状态] 如:冻结/正常不能为空")
    private String status;

    @Schema(description = "[版本号] 版本号(乐观锁)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[版本号] 版本号(乐观锁)不能为空")
    private Integer version;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
