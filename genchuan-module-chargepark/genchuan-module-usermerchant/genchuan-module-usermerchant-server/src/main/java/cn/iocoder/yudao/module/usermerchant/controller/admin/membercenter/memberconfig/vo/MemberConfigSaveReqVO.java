package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 会员配置保存 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberConfigSaveReqVO extends MemberConfigBaseVO {
}
