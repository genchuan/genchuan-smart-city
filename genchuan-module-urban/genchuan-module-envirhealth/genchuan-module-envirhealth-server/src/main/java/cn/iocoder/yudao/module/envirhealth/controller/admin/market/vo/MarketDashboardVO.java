package cn.iocoder.yudao.module.envirhealth.controller.admin.market.vo;

import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "环境卫生管理 - 集贸市场看板统计返回VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketDashboardVO {

    // ========== 卡片数据 ==========
    @Schema(description = "总市场数", example = "120")
    private Long totalMarketCount;

    @Schema(description = "卫生达标数", example = "105")
    private Long hygieneQualifiedCount;

    @Schema(description = "收运完成数", example = "98")
    private Long wasteTransferCompletedCount;

    @Schema(description = "污水处置合格数", example = "90")
    private Long sewageDisposalQualifiedCount;

    // ========== 圆环图数据 ==========
    @Schema(description = "运营状态占比（name=运营状态,value=市场数量）")
    private List<PieItemVO> operationStatusDistribution;

    @Schema(description = "所属区域分布占比（name=区域名称,value=市场数量）")
    private List<PieItemVO> areaDistribution;

    // ========== 基础柱状图数据 ==========
    @Schema(description = "不同市场卫生达标率对比（name=市场名称,value=卫生达标率/百分比）")
    private List<BarItemVO> hygieneComplianceRateByMarket;
}