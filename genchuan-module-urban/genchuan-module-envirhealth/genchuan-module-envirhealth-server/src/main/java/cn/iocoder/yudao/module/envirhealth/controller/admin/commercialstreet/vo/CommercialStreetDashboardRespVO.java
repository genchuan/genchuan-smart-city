package cn.iocoder.yudao.module.envirhealth.controller.admin.commercialstreet.vo;

import cn.iocoder.yudao.module.envirhealth.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.util.vo.PieItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "环境卫生管理 - 商业街看板统计返回VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommercialStreetDashboardRespVO {

    // ========== 卡片数据 ==========
    @Schema(description = "总商业街数", example = "50")
    private Long totalStreetCount;

    @Schema(description = "保洁覆盖达标数", example = "45")
    private Long cleaningCoverageMetCount;

    @Schema(description = "设施完好数", example = "42")
    private Long facilityIntactCount;

    @Schema(description = "收运完成数", example = "48")
    private Long collectionCompletedCount;

    // ========== 圆环图数据 ==========
    @Schema(description = "区域分布占比（name=区域名称,value=商业街数量）")
    private List<PieItemVO> areaDistribution;

    @Schema(description = "运营状态占比（name=运营状态,value=商业街数量）")
    private List<PieItemVO> operationStatusDistribution;

    // ========== 柱状图数据 ==========
    @Schema(description = "不同商业街问题处置时长对比（name=商业街名称,value=平均处置时长/小时）")
    private List<BarItemVO> problemDisposalDurationByStreet;
}