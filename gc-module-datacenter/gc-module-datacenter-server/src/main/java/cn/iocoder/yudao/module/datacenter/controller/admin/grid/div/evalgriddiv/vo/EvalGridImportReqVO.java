package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.evalgriddiv.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Schema(description = "管理后台 - 评价网格导入 Request VO")
@Data
public class EvalGridImportReqVO {

    @Schema(description = "评价网格名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "南塔街道01B类")
    @NotEmpty(message = "评价网格名称不能为空")
    private String evalGridName;

    @Schema(description = "所属乡镇ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10750")
    @NotEmpty(message = "所属乡镇ID不能为空")
    private String townStreetId;

    @Schema(description = "评价网格类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "B")
    @NotEmpty(message = "评价网格类型不能为空")
    private String gridType;

    @Schema(description = "管理网格ID列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "管理网格ID列表不能为空")
    private List<String> mngGridIds;

    @Schema(description = "备注", example = "包含商业区，划分为A类")
    private String remark;

}