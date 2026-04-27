package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantlink.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 商户对接 Request VO")
@Data
public class MerchantLinkLinkReqVO {

    @Schema(description = "商户对接ID数组", requiredMode = Schema.RequiredMode.REQUIRED, example = "[1,2,3]")
    @NotEmpty(message = "商户对接ID列表不能为空")
    private List<Long> ids;

}
