package cn.iocoder.yudao.module.usermerchant.controller.admin.creditmgmt.usercredit.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Schema(description = "管理后台 - 用户信用提醒 Request VO")
@Data
public class UserCreditRemindReqVO {

    @Schema(description = "信用ID数组", requiredMode = Schema.RequiredMode.REQUIRED, example = "[1,2]")
    @NotEmpty(message = "信用ID列表不能为空")
    private List<Long> ids;
}