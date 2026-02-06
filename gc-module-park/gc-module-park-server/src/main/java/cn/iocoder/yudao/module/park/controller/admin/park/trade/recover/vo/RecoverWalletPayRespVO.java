package cn.iocoder.yudao.module.park.controller.admin.park.trade.recover.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "追缴服务 - 钱包支付 Resp VO")
public class RecoverWalletPayRespVO {
    @Schema(description = "是否支付成功")
    private Boolean isSuccess;
}
