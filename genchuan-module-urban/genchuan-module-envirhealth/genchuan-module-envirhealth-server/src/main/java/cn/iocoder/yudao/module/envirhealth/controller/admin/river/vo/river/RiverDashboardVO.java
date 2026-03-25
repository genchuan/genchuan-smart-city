package cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.river;

import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "环境卫生管理 - 河道看板统计返回VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RiverDashboardVO {

    // ========== 卡片数据 ==========
    @Schema(description = "总河道数", example = "50")
    private Long totalRiverCount;

    @Schema(description = "保洁覆盖达标数", example = "45")
    private Long cleaningCoverageMetCount;

    @Schema(description = "水质达标数", example = "42")
    private Long waterQualityMetCount;

    @Schema(description = "问题办结数", example = "38")
    private Long problemCompletedCount;

    // ========== 圆环图数据 ==========
    @Schema(description = "所属区域分布占比（name=区域名称,value=河道数量）")
    private List<PieItemVO> areaDistribution;

    @Schema(description = "运营状态占比（name=运营状态,value=河道数量）")
    private List<PieItemVO> operationStatusDistribution;

    // ========== 基础柱状图数据 ==========
    @Schema(description = "不同河道水质达标率对比（name=河道名称,value=水质达标率/百分比）")
    private List<BarItemVO> waterQualityRateByRiver;
}