package cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupcar.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 车辆审核 Request VO")
@Data
public class GroupCarAuditReqVO {

    @Schema(description = "用户ID数组", requiredMode = Schema.RequiredMode.REQUIRED, example = "[1,2,3]")
    @NotEmpty(message = "用户ID列表不能为空")
    private List<Long> ids;

    @Schema(description = "车辆状态：待审核/正常/禁用/已驳回", example = "2")
    private String status;

    @Schema(description = "审核备注", example = "审核通过")
    private String auditRemark;

}
