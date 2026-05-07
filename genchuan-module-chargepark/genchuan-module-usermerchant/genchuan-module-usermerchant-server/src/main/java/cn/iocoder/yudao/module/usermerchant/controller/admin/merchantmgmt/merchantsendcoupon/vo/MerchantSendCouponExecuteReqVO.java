package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantsendcoupon.vo;

import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.BaseIdsVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 执行发券 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class MerchantSendCouponExecuteReqVO extends BaseIdsVO {
}