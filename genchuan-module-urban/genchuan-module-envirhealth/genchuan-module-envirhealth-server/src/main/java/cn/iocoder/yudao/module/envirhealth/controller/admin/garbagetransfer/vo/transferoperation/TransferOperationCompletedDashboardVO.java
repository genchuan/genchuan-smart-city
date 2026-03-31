package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation;

import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.LineItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "转运作业看板统计返回")
@Data
public class TransferOperationCompletedDashboardVO {

    // ========== 卡片数据 ==========
    @Schema(description = "已完成任务总数", example = "286")
    private Long totalCompletedTasks;

    @Schema(description = "进站总量（单位：吨）", example = "8560.50")
    private BigDecimal totalInboundVolume;

    @Schema(description = "设备完好率（百分比，保留2位小数）", example = "98.50")
    private BigDecimal equipmentHealthRate;

    @Schema(description = "环境达标率（百分比，保留2位小数）", example = "96.80")
    private BigDecimal environmentComplianceRate;

    // ========== 柱状图数据 ==========
    @Schema(description = "柱状图：按日/周/月进站量对比（name=时间维度值,value=进站量/吨）")
    private List<BarItemVO> inboundVolumeByTimeDimension;

    // ========== 折线图数据 ==========
    @Schema(description = "折线图：设备完好率趋势变化（name=时间节点,value=完好率）")
    private List<LineItemVO> equipmentHealthRateTrend;

    // ========== 圆环图数据 ==========
    @Schema(description = "圆环图：各任务类型占比（name=任务类型,value=数量）")
    private List<PieItemVO> taskTypeDistribution;

    @Schema(description = "圆环图：各转运站完成量占比（name=转运站名称,value=完成量/吨）")
    private List<PieItemVO> stationCompletionDistribution;
}