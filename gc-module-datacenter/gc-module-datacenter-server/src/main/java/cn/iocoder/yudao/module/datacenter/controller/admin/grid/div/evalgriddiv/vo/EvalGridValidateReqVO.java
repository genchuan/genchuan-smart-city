package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.evalgriddiv.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Schema(description = "管理后台 - 评价网格校验 Request VO")
@Data
public class EvalGridValidateReqVO {

    @Schema(description = "乡镇ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10750")
    @NotEmpty(message = "乡镇ID不能为空")
    private String townStreetId;

    @Schema(description = "管理网格ID列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "管理网格ID列表不能为空")
    private List<String> mngGridIds;
}