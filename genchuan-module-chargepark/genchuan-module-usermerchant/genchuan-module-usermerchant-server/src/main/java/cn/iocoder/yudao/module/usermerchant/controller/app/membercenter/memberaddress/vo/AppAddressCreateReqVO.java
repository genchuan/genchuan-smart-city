package cn.iocoder.yudao.module.usermerchant.controller.app.membercenter.memberaddress.vo;

import cn.iocoder.yudao.module.usermerchant.controller.app.membercenter.memberaddress.vo.AppAddressBaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "用户 APP - 用户收件地址创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppAddressCreateReqVO extends AppAddressBaseVO {

}
