package cn.iocoder.yudao.module.system.controller.admin.user.vo.profile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotEmpty;

@Schema(description = "管理后台 - 用户头像更新 Request VO")
@Data
public class UserProfileUpdateAvatarReqVO {

    @Schema(description = "头像URL", requiredMode = Schema.RequiredMode.REQUIRED, example = "http://127.0.0.18:9000/shunchang/avatar/xxx.jpg")
    @NotEmpty(message = "头像URL不能为空")
    @URL(message = "头像地址格式不正确")
    private String avatarUrl;

}
