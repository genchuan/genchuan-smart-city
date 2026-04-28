package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 会员配置更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberConfigUpdateReqVO extends MemberConfigBaseVO {

    @Schema(description = "自增主键")
    private Long id;

}
