package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.unitgriddiv.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UnitGridBoundaryInfo {

    @Schema(description = "单元网格ID")
    private String unitGridId;

    @Schema(description = "边界坐标")
    private String boundaryCoords;
}