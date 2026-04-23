package cn.iocoder.yudao.module.inspectop.controller.admin.inspectuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "巡查巡检 - 巡检人员状态修改 Request VO")
@Data
public class InspectUserStatusReqVO {

    @Schema(description = "人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "人员ID不能为空")
    private Long id;
}