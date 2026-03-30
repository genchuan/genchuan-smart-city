package cn.iocoder.yudao.module.park.controller.admin.park.user.blackwhitelist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "管理后台 - 黑白名单验证 Request VO")
@Data
public class VerifyWhitelistReqVO {
    @NotBlank(message = "车牌号不能为空")
    private String carNumber;
}
