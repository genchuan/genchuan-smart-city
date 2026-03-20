package cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo;

import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "环境卫生管理 - 车辆看板统计返回VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDashboardVO {

    // ========== 卡片数据 ==========
    @Schema(description = "总车辆数", example = "50")
    private Long totalVehicleCount;

    @Schema(description = "正常运行数", example = "35")
    private Long normalOperationCount;

    @Schema(description = "维护中数", example = "8")
    private Long maintenanceCount;

    @Schema(description = "违规告警数", example = "12")
    private Long violationAlertCount;

    @Schema(description = "待作业车辆数", example = "15")
    private Long pendingWorkCount;

    // ========== 圆环图数据 ==========
    @Schema(description = "车辆类型占比（name=车辆类型,value=车辆数量）")
    private List<PieItemVO> vehicleTypeDistribution;

    @Schema(description = "车辆状态占比（name=车辆状态,value=车辆数量）")
    private List<PieItemVO> vehicleStatusDistribution;

    @Schema(description = "所属部门分布占比（name=部门名称,value=车辆数量）")
    private List<PieItemVO> deptDistribution;

    // ========== 基础柱状图数据 ==========
    @Schema(description = "不同部门车辆数量对比（name=部门名称,value=车辆数量）")
    private List<BarItemVO> vehicleCountByDept;

    @Schema(description = "不同类型车辆完好率对比（name=车辆类型,value=完好率%）")
    private List<BarItemVO> vehicleIntegrityRateByType;
}