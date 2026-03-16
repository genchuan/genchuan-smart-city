package cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.roadcleaning;

import cn.iocoder.yudao.module.envirhealth.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.util.vo.PieItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "道路清扫计划看板统计返回")
@Data
public class RoadCleaningAllRespVO {

    @Schema(description = "总计划数")
    private Long totalPlanCount;

    @Schema(description = "执行中计划数")
    private Long executingPlanCount;

    @Schema(description = "质量达标数（复核状态=达标）")
    private Long qualityQualifiedCount;

    @Schema(description = "全勤人员数（执行中计划涉及人员去重）")
    private Long fullAttendanceStaffCount;

    @Schema(description = "圆环图：计划状态占比（name=状态,value=数量）")
    private List<PieItemVO> planStatusDistribution;

    @Schema(description = "圆环图：路段类型占比（name=类型,value=数量）")
    private List<PieItemVO> roadSectionTypeDistribution;

    @Schema(description = "柱状图：不同区域清扫质量达标率对比（name=区域,value=达标率%）")
    private List<BarItemVO> qualityQualifiedRateByArea;
}