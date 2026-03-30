package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferalarm;

import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "转运站预警看板统计返回")
@Data
public class TransferAlarmDashboardRespVO {

    // ========== 卡片数据 ==========
    @Schema(description = "待处置预警总数", example = "15")
    private Long totalPendingAlarm;

    @Schema(description = "高优先级预警数", example = "5")
    private Long highPriorityCount;

    @Schema(description = "超时未处理预警数", example = "3")
    private Long timeoutUnprocessedCount;

    // ========== 圆环图数据 ==========
    @Schema(description = "圆环图：预警类型占比（name=预警类型名称,value=数量）")
    private List<PieItemVO> alarmTypeDistribution;

    @Schema(description = "圆环图：转运站分布占比（name=转运站名称,value=预警数量）")
    private List<PieItemVO> transferStationDistribution;

    // ========== 柱状图数据 ==========
    @Schema(description = "柱状图：不同责任人待处置预警数量对比（name=责任人名称,value=预警数量）")
    private List<BarItemVO> handlerPendingAlarmComparison;
}