package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.mnggriddiv.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class MngGridUnitDetailRespVO {
    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "管理网格名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String mngGridName;

    @Schema(description = "所属乡镇ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private String townStreetId;

    @Schema(description = "面积(m²)")
    private Integer area;

    @Schema(description = "单元网格数量")
    private Integer unitGridCount;

    @Schema(description = "单元网格ID列表")
    private List<String> unitGridIds;

    @Schema(description = "单元网格详情列表")
    private List<UnitGridSimpleInfo> unitGridInfos;

    @Schema(description = "网格员ID")
    private String staffId;
}