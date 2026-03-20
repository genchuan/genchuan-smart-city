package cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo;

import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "环境卫生管理 - 城中村看板统计返回VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrbanVillageDashboardVO {

    // ========== 卡片数据 ==========
    @Schema(description = "总城中村数", example = "120")
    private Long totalVillageCount;

    @Schema(description = "正常运营数", example = "110")
    private Long normalOperationCount;

    @Schema(description = "保洁达标数", example = "105")
    private Long cleaningStandardMetCount;

    @Schema(description = "问题处置完成数", example = "98")
    private Long problemHandledCount;

    @Schema(description = "复核通过数", example = "90")
    private Long reviewPassedCount;

    // ========== 圆环图数据 ==========
    @Schema(description = "运营状态占比（name=运营状态,value=城中村数量）")
    private List<PieItemVO> operationStatusDistribution;

    @Schema(description = "所属区域分布占比（name=区域名称,value=城中村数量）")
    private List<PieItemVO> areaDistribution;

    // ========== 基础柱状图数据 ==========
    @Schema(description = "不同城中村考核得分对比（name=城中村名称,value=考核得分）")
    private List<BarItemVO> assessmentScoreByVillage;
}