package cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.forceview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "管理后台 - 执法力量分布视图 Response VO")
public class LawForceViewRespVO {

    @Schema(description = "执法人员实时位置列表")
    private List<LawForceStaffLocVO> staffLocList;
    @Schema(description = "执法人员总数")
    private int staffLocListCount;

    @Schema(description = "执法车辆实时位置列表")
    private List<LawForceVehicleLocVO> vehicleLocList;
    @Schema(description = "执法车辆总数")
    private int vehicleLocListCount;

    @Schema(description = "执法站点位置列表")
    private List<LawForceStationLocVO> stationLocList;
    @Schema(description = "执法站点总数")
    private int stationLocListCount;

//    @Schema(description = "各区域执法人员数量")
//    private LawForceStaffCountVO regionStaffCount;


}
