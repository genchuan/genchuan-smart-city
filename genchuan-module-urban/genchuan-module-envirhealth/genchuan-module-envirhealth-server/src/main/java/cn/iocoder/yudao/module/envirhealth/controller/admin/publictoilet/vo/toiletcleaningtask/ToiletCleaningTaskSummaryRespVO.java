package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask;

import cn.iocoder.yudao.module.envirhealth.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.util.vo.PieItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "公厕保洁任务总览统计返回")
@Data
public class ToiletCleaningTaskSummaryRespVO {

    @Schema(description = "已完成任务总数")
    private Long completedTotal;

    @Schema(description = "保洁达标率（%）")
    private Double cleaningQualifiedRate;

    @Schema(description = "投诉办结率（%）")
    private Double complaintFinishRate;

    @Schema(description = "设施完好率（%）")
    private Double facilityGoodRate;

    @Schema(description = "柱状图：按日/周/月任务完成量对比（name=日/周/月,value=数量）")
    private List<BarItemVO> completionCountByPeriod;

    @Schema(description = "折线图：保洁达标率趋势变化（name=日/周/月,value=达标率%）")
    private List<BarItemVO> cleaningQualifiedTrend;

    @Schema(description = "圆环图：各任务类型占比（这里按保洁频次统计，name=频次,value=数量）")
    private List<PieItemVO> taskTypeDistribution;

    @Schema(description = "圆环图：各区域完成量占比（name=区域,value=完成任务数）")
    private List<PieItemVO> areaCompletionDistribution;
}