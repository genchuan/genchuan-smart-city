package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 救援信息批量派发 Request VO")
@Data
public class RescueInfoBatchDispatchReqVO {

    @Schema(description = "救援信息 ID 列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "救援信息 ID 列表不能为空")
    private List<Long> ids;

    @Schema(description = "救援人员 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2001")
    @NotNull(message = "救援人员 ID 不能为空")
    private Long rescueUserId;

    @Schema(description = "派发备注")
    private String dispatchRemark;

}
