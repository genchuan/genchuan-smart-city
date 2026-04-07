package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingratio.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 分账比例新增/修改 Request VO")
@Data
public class SharingRatioSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7179")
    private Long id;

    @Schema(description = "方案编号，唯一", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "方案编号，唯一不能为空")
    private String sharingCode;

    @Schema(description = "方案名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "方案名称不能为空")
    private String sharingName;

    @Schema(description = "合作方", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "合作方不能为空")
    private String cooperator;

    @Schema(description = "分账类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "分账类型不能为空")
    private String sharingType;

    @Schema(description = "分账比例（%）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "分账比例（%）不能为空")
    private BigDecimal sharingRatio;

    @Schema(description = "适用场站，多个用逗号分隔")
    private String applyStation;

    @Schema(description = "适用渠道，多个用逗号分隔")
    private String applyChannel;

    @Schema(description = "生效时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "生效时间不能为空")
    private LocalDateTime effectTime;

    @Schema(description = "失效时间")
    private LocalDateTime expireTime;

    @Schema(description = "分账状态：未生效/已生效/已失效", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "分账状态：未生效/已生效/已失效不能为空")
    private String sharingStatus;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}