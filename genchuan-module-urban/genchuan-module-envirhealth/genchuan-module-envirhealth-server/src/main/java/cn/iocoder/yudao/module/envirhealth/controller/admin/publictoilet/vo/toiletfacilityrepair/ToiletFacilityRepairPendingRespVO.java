package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletfacilityrepair;

import cn.iocoder.yudao.module.envirhealth.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.util.vo.PieItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 公厕设施维修看板统计返回VO
 *
 * @author 芋道源码
 */
@Schema(description = "公厕设施维修看板统计返回")
@Data
public class ToiletFacilityRepairPendingRespVO {

    // ========== 卡片统计项 ==========
    @Schema(description = "待维修设施总数", example = "50")
    private Long toRepairTotal;

    @Schema(description = "已派单维修数", example = "35")
    private Long dispatchedTotal;

    // ========== 圆环图统计项 ==========
    @Schema(description = "圆环图：设施类型占比（name=设施类型,value=数量）")
    private List<PieItemVO> facilityTypeRatio;

    @Schema(description = "圆环图：维修状态占比（name=维修状态,value=数量）")
    private List<PieItemVO> repairStatusRatio;

    // ========== 柱状图统计项 ==========
    @Schema(description = "柱状图：不同区域设施损坏数量对比（name=区域,value=数量）")
    private List<BarItemVO> damageCountByArea;

    @Schema(description = "柱状图：按类型维修数对比（name=设施类型,value=维修数量）", example = "[{\"name\":\"冲水阀\",\"value\":10},{\"name\":\"照明\",\"value\":8}]")
    private List<BarItemVO> repairCountByType;
}