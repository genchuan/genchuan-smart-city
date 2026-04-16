package cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Schema(description = "管理后台 - 用户禁用 Request VO")
@Data
public class UserInfoDisableReqVO {

    @Schema(description = "用户ID数组", requiredMode = Schema.RequiredMode.REQUIRED, example = "[1,2,3]")
    @NotEmpty(message = "用户ID列表不能为空")
    private List<Long> ids;
}
