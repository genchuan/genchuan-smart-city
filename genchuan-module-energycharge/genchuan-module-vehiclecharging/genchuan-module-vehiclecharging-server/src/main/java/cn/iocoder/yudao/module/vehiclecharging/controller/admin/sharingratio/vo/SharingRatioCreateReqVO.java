package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingratio.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 分账比例新增 Request VO")
@Data
public class SharingRatioCreateReqVO {

    @Schema(description = "方案名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "新场站合作分账方案")
    @NotEmpty(message = "方案名称不能为空")
    private String sharingName;

    @Schema(description = "合作方", requiredMode = Schema.RequiredMode.REQUIRED, example = "莲梅莉新能源有限公司")
    @NotEmpty(message = "合作方不能为空")
    private String cooperator;

    @Schema(description = "分账类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "服务费分账")
    @NotEmpty(message = "分账类型不能为空")
    private String sharingType;

    @Schema(description = "分账比例（%）", requiredMode = Schema.RequiredMode.REQUIRED, example = "25.50")
    @NotNull(message = "分账比例（%）不能为空")
    private BigDecimal sharingRatio;

    @Schema(description = "适用场站，多个用逗号分隔", example = "泉州洛阳场站")
    private String applyStation;

    @Schema(description = "适用渠道，多个用逗号分隔", example = "小程序")
    private String applyChannel;

    @Schema(description = "生效时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025-03-01 00:00:00")
    @NotNull(message = "生效时间不能为空")
    private LocalDateTime effectTime;

    @Schema(description = "失效时间", example = "2026-02-27 00:00:00")
    private LocalDateTime expireTime;

    @Schema(description = "备注", example = "新合作场站分账方案")
    private String remark;

}