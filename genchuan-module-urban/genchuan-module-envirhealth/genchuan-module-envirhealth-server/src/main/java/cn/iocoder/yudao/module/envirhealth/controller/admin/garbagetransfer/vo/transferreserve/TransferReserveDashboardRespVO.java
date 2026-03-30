package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferreserve;

import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "进站预约看板统计返回")
@Data
public class TransferReserveDashboardRespVO {

    // ========== 卡片数据 ==========
    @Schema(description = "待进站车辆数", example = "8")
    private Long pendingVehicles;

    @Schema(description = "已排序车辆数", example = "12")
    private Long sortedVehicles;

    @Schema(description = "今日预约总数", example = "20")
    private Long todayTotalReserves;

    // ========== 圆环图数据 ==========
    @Schema(description = "圆环图：垃圾品类分布占比（name=垃圾品类,value=数量）")
    private List<PieItemVO> garbageTypeDistribution;

    @Schema(description = "圆环图：区域分布占比（name=区域名称,value=数量）")
    private List<PieItemVO> areaDistribution;

    // ========== 柱状图数据 ==========
    @Schema(description = "柱状图：不同时段预约车辆数量对比（name=时段,value=数量）")
    private List<BarItemVO> reserveCountByTimeSlot;
}