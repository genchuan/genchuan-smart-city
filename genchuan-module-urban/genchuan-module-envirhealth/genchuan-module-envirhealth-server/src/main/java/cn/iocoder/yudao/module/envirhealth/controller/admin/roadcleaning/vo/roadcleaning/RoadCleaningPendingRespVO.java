package cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.roadcleaning;

import cn.iocoder.yudao.module.envirhealth.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.util.vo.PieItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class RoadCleaningPendingRespVO {

    @Schema(description = "卡片：待执行计划总数")
    private Long pendingPlanCount;

    @Schema(description = "卡片：待执行涉及区域数（area_code 去重）")
    private Long pendingAreaCount;

    @Schema(description = "卡片：待执行涉及人员数（staff_ids 拆分后去重）")
    private Long pendingStaffCount;

    @Schema(description = "圆环图：清扫频次分布占比（name=频次,value=数量）")
    private List<PieItemVO> frequencyDistribution;

    @Schema(description = "圆环图：路段类型占比（name=类型,value=数量）")
    private List<PieItemVO> roadSectionTypeDistribution;

    @Schema(description = "基础柱状图：不同时段清扫计划数量对比（name=时段,value=数量）")
    private List<BarItemVO> planCountByTimePeriod;

}