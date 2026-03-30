package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletconsumable;

import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
@Schema(description = "公厕耗材待补充看板统计返回")
@Data
public class ToiletConsumablePendingRespVO {

    @Schema(description = "待补充物资总数（缺口>0 的记录数）")
    private Long pendingTotal;

    @Schema(description = "高预警物资数（严重预警 且 缺口>0）")
    private Long highWarningCount;

    @Schema(description = "各区域待补充数（区域数量，去重）")
    private Long pendingAreaCount;

    @Schema(description = "圆环图：物资类型占比（name=类型,value=数量）")
    private List<PieItemVO> typeDistribution;

    @Schema(description = "圆环图：预警状态占比（name=状态,value=数量）")
    private List<PieItemVO> warningDistribution;

    @Schema(description = "柱状图：不同物资缺口数量对比（name=物资,value=缺口总量）")
    private List<BarItemVO> gapByConsumable;
}