package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 卡种配置停用 Request VO")
@Data
public class CardConfigDisableReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "id不能为空")
    private Long id;

}
