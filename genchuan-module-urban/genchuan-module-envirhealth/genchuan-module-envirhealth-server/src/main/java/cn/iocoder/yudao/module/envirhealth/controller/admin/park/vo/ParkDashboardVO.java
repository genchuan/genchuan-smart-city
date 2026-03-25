package cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo;

import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "环境卫生管理 - 园区（公园）看板统计返回VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkDashboardVO {

    // ========== 卡片数据 ==========
    @Schema(description = "总公园数", example = "80")
    private Long totalParkCount;

    @Schema(description = "正常运营数", example = "76")
    private Long normalOperationCount;

    @Schema(description = "保洁达标数", example = "72")
    private Long cleaningStandardMetCount;

    @Schema(description = "绿化存活达标数", example = "68")
    private Long greeningSurvivalStandardMetCount;

    @Schema(description = "设施完好数", example = "70")
    private Long facilityIntactCount;

    // ========== 圆环图数据 ==========
    @Schema(description = "运营状态占比（name=运营状态,value=公园数量）")
    private List<PieItemVO> operationStatusDistribution;

    @Schema(description = "所属区域分布占比（name=区域名称,value=公园数量）")
    private List<PieItemVO> areaDistribution;

    // ========== 基础柱状图数据 ==========
    @Schema(description = "不同公园环境达标率对比（name=公园名称,value=环境达标率/百分比）")
    private List<BarItemVO> environmentComplianceRateByPark;
}