package cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.cleaningproblem;

import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "道路清扫问题待处理仪表盘响应 VO")
public class CleaningProblemPendingRespVO {

    @Schema(description = "卡片：待处置问题总数")
    private Long pendingProblemCount;

    @Schema(description = "卡片：高优先级问题数")
    private Long highPriorityCount;

    @Schema(description = "卡片：超时未处理数")
    private Long timeoutCount;

    @Schema(description = "圆环图：问题类型占比（name=问题类型,value=数量）")
    private List<PieItemVO> problemTypeDistribution;

    @Schema(description = "圆环图：区域分布占比（name=区域名称,value=数量）")
    private List<PieItemVO> areaDistribution;

    @Schema(description = "基础柱状图：不同处置组待处置问题数量对比（name=处置组名称,value=数量）")
    private List<BarItemVO> teamPendingDistribution;
}