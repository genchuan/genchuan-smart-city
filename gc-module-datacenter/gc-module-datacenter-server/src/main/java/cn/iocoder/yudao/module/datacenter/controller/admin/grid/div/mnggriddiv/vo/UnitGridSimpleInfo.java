package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.mnggriddiv.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UnitGridSimpleInfo {

    @Schema(description = "单元网格ID")
    private String unitGridId;

    @Schema(description = "单元网格名称")
    private String unitGridName;

    @Schema(description = "面积(m²)")
    private Integer area;

    @Schema(description = "边界坐标")
    private String boundaryCoords;
}
