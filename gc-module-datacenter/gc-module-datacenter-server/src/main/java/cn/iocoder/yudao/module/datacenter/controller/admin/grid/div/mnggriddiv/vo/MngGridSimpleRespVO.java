package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.mnggriddiv.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(description = "管理后台 - 管理网格简单 Response VO")
@Data
public class MngGridSimpleRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "管理网格ID", example = "mng_001")
    private String mngGridId;

    @Schema(description = "管理网格名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "交道口街道001")
    private String mngGridName;

    @Schema(description = "所属乡镇ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10958")
    private String townStreetId;

    @Schema(description = "所含单元网格IDs", requiredMode = Schema.RequiredMode.REQUIRED)
    private String includedUnitIds;

    @Schema(description = "面积(m²)", example = "30000")
    private Integer area;

    @Schema(description = "单元网格数量", example = "3")
    private Integer unitGridCount;

    @Schema(description = "网格员ID", example = "staff_001")
    private String staffId;

}