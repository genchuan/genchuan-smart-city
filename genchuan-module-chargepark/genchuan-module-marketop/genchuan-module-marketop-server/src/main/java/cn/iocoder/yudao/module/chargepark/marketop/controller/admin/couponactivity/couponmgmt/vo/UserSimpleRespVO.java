package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 用户精简信息 Response VO")
@Data
public class UserSimpleRespVO {

    @Schema(description = "用户ID")
    private Long id;

    @Schema(description = "用户昵称")
    private String name;

}
