package cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Schema(description = "管理后台 - 车辆审核 Request VO")
@Data
public class UserCarApproveReqVO {

    @Schema(description = "用户ID数组", requiredMode = Schema.RequiredMode.REQUIRED, example = "[1,2,3]")
    @NotEmpty(message = "用户ID列表不能为空")
    private List<Long> ids;

    @Schema(description = "审核备注", example = "审核通过")
    private String auditRemark;
}
