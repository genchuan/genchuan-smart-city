package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.mnggriddiv.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "管理后台 - 管理网格校验 Request VO")
@Data
public class MngGridValidateReqVO {

    @Schema(description = "单元网格ID列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "单元网格ID列表不能为空")
    private List<String> unitGridIds;

    @Schema(description = "乡镇ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10958")
    @NotEmpty(message = "乡镇ID不能为空")
    private String townStreetId;

    @Schema(description = "排除的管理网格ID（修改时使用）", example = "1024")
    private Long excludeMngGridId;

}