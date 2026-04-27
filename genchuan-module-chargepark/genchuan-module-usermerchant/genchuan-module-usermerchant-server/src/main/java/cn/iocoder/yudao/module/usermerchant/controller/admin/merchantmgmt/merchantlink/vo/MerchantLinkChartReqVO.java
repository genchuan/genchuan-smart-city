package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantlink.vo;

import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo.vo.UserInfoChartReqVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 商户对接表图表数据 Request VO")
@Data
public class MerchantLinkChartReqVO extends UserInfoChartReqVO {
}
