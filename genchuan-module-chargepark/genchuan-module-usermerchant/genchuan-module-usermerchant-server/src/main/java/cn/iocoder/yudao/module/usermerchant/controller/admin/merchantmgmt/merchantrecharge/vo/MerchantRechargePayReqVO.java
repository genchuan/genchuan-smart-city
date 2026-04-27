package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantrecharge.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 商户支付 Request VO")
@Data
public class MerchantRechargePayReqVO {

    @TableField(exist = false)
    @Schema(description = "支付ID数组")
    private List<Long> ids;

    @Schema(description = "支付渠道：微信/支付宝/银行转账/平台余额")
    private String payChannel;

}
