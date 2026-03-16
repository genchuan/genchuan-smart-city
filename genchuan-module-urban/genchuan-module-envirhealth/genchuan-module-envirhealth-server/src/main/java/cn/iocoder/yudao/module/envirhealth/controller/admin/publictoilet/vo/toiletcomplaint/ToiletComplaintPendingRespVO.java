package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcomplaint;

import cn.iocoder.yudao.module.envirhealth.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.util.vo.PieItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "公厕投诉看板统计返回")
@Data
public class ToiletComplaintPendingRespVO {

    @Schema(description = "待处置投诉总数")
    private Long pendingTotal;

    @Schema(description = "按类型投诉数（类型数量，去重）")
    private Long typeCount;

    @Schema(description = "超时未处理数")
    private Long timeoutUnHandledCount;

    @Schema(description = "圆环图：投诉类型占比（name=类型,value=数量）")
    private List<PieItemVO> typeDistribution;

    @Schema(description = "圆环图：区域分布占比（name=区域,value=数量）")
    private List<PieItemVO> areaDistribution;

    @Schema(description = "柱状图：不同区域投诉数量对比（name=区域,value=数量）")
    private List<BarItemVO> complaintCountByArea;
}