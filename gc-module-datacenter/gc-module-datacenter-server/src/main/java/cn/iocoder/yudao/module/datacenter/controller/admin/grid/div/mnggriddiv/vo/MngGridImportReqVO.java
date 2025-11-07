package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.mnggriddiv.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "管理后台 - 管理网格导入 Request VO")
@Data
public class MngGridImportReqVO {

    @Schema(description = "管理网格名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "交道口街道001")
    @NotEmpty(message = "管理网格名称不能为空")
    private String mngGridName;

    @Schema(description = "所属乡镇ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10958")
    @NotEmpty(message = "所属乡镇ID不能为空")
    private String townStreetId;

    @Schema(description = "单元网格ID列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "单元网格ID列表不能为空")
    private List<String> unitGridIds;

    @Schema(description = "备注", example = "覆盖3个单元网格，匹配1名网格员")
    private String remark;

    @Schema(description = "网格员ID", example = "staff_001")
    private String staffId;

}