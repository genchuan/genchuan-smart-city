package cn.iocoder.yudao.module.studentmgmt.controller.admin.registermgmt.vo;

import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartTrendVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 报名管理确认 response VO")
@Data
public class RegisterMgmtChartRespVO {
    @Schema(description = "总报名人数")
    private Integer totalApplyCount;
    @Schema(description = "待审核人数")
    private Integer pendingAuditCount;
    @Schema(description = "已录取人数")
    private Integer admittedCount;
    @Schema(description = "已确认人数")
    private Integer confirmedCount;
    @Schema(description = "近一周报名趋势数据")
    private List<ChartTrendVO> recentWeekApplyTrend;
}