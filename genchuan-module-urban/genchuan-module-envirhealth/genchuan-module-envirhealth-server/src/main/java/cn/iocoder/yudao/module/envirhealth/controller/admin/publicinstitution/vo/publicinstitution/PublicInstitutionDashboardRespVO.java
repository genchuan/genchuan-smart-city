package cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.publicinstitution;

import cn.iocoder.yudao.module.envirhealth.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.util.vo.PieItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 公共机构看板统计返回")
@Data
public class PublicInstitutionDashboardRespVO {

    // ========== 卡片数据 ==========
    @Schema(description = "总机构数", example = "100")
    private Long totalInstitutions;

    @Schema(description = "保洁达标机构数（保洁达标率≥90%的机构数）", example = "85")
    private Long cleaningStandardMetCount;

    @Schema(description = "问题办结机构数（问题办结率≥90%的机构数）", example = "92")
    private Long problemClosedCount;

    @Schema(description = "核查通过机构数（核查通过率≥90%的机构数）", example = "88")
    private Long inspectionPassCount;

    // ========== 圆环图数据 ==========
    @Schema(description = "圆环图：机构类型分布占比（name=机构类型名称,value=数量）")
    private List<PieItemVO> institutionTypeDistribution;

    @Schema(description = "圆环图：区域分布占比（name=区域名称,value=数量）")
    private List<PieItemVO> areaDistribution;

    // ========== 柱状图数据 ==========
    @Schema(description = "柱状图：不同类型机构保洁达标率对比（name=机构类型名称,value=保洁达标率%）")
    private List<BarItemVO> cleaningRateByType;
}