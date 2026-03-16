package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask;

import cn.iocoder.yudao.module.envirhealth.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.util.vo.PieItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "公厕保洁任务看板统计返回")
@Data
public class ToiletCleaningTaskPendingRespVO {

    @Schema(description = "待执行保洁计划数")
    private Long pendingCount;

    @Schema(description = "按区域待执行数（区域数量，去重）")
    private Long pendingByArea;

    @Schema(description = "按人员分配数（人员数量，去重）")
    private Long assignedByCleaner;

    @Schema(description = "圆环图：保洁频次分布占比（name=频次,value=数量）")
    private List<PieItemVO> frequencyDistribution;

    @Schema(description = "圆环图：区域分布占比（name=区域,value=数量）")
    private List<PieItemVO> areaDistribution;

    @Schema(description = "柱状图：不同时段保洁计划数量对比（name=时段,value=数量）")
    private List<BarItemVO> planCountByTimeSlot;
}