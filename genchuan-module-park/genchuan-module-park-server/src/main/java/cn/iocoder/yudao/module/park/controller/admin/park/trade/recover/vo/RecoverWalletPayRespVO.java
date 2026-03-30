package cn.iocoder.yudao.module.park.controller.admin.park.trade.recover.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "追缴服务 - 钱包支付 Resp VO")
public class RecoverWalletPayRespVO {
    @Schema(description = "是否支付成功")
    private Boolean isSuccess;
}
