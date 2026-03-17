package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.garbagetransfer;

import cn.iocoder.yudao.module.envirhealth.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.util.vo.LineItemVO;
import cn.iocoder.yudao.module.envirhealth.util.vo.PieItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "垃圾转运站看板统计返回")
@Data
public class GarbageTransferDashboardRespVO {

    // ========== 卡片数据 ==========
    @Schema(description = "总转运站数", example = "42")
    private Long totalStations;

    @Schema(description = "正常运营数", example = "38")
    private Long normalOperationCount;

    @Schema(description = "设备正常数（设备正常运行率≥95%的转运站数）", example = "35")
    private Long equipmentNormalCount;

    @Schema(description = "环境达标数（环境达标率≥90%的转运站数）", example = "36")
    private Long environmentStandardCount;

    // ========== 圆环图数据 ==========
    @Schema(description = "圆环图：运营状态分布占比（name=运营状态,value=数量）")
    private List<PieItemVO> operationStatusDistribution;

    @Schema(description = "圆环图：区域分布占比（name=区域名称,value=数量）")
    private List<PieItemVO> areaDistribution;

    // ========== 柱状图数据 ==========
    @Schema(description = "柱状图：不同转运站日转运量对比（name=转运站名称,value=日转运量/吨）")
    private List<BarItemVO> dailyTransferVolumeComparison;

    // ========== 折线图数据 ==========
    @Schema(description = "折线图：近7日环境指标变化趋势（环境达标率）")
    private List<LineItemVO> environmentTrend7Days;
}