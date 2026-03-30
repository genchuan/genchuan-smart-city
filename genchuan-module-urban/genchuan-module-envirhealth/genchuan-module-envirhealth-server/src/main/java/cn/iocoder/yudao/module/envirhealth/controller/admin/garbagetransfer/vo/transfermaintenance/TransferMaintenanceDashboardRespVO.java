package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transfermaintenance;

import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "设备维护看板统计返回")
@Data
public class TransferMaintenanceDashboardRespVO {

    // ========== 卡片数据 ==========
    @Schema(description = "待维护设备总数", example = "120")
    private Long totalPendingMaintenance;

    @Schema(description = "按类型待维护数（key=设备类型，value=数量）", example = "{'压缩机':50,'输送机':70}")
    private Long typePendingMaintenance;

    @Schema(description = "超时未维护数", example = "25")
    private Long timeoutUnmaintainedCount;

    // ========== 圆环图数据 ==========
    @Schema(description = "圆环图：设备类型占比（name=设备类型,value=数量）")
    private List<PieItemVO> equipmentTypeDistribution;

    @Schema(description = "圆环图：维护状态占比（name=维护状态,value=数量）")
    private List<PieItemVO> maintenanceStatusDistribution;

    // ========== 柱状图数据 ==========
    @Schema(description = "柱状图：不同转运站待维护设备数量对比（name=转运站名称,value=待维护数量）")
    private List<BarItemVO> stationPendingMaintenanceComparison;
}