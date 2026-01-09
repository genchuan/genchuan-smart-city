// ServiceItemsRespVO.java
package cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.serviceitems.vo;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 服务事项统计 Response VO")
@Data
public class BusiServiceItemsRespVO {

    @Schema(description = "服务事项总数")
    private Integer totalSvcCount;

    @Schema(description = "当日待办量")
    private Integer dailyPendCount;

    @Schema(description = "事项办结率")
    private BigDecimal svcCompleteRate;

    @Schema(description = "超期事项数")
    private Integer overdueSvcCount;

    @Schema(description = "事项类型分布")
    private List<Map<String, Object>> typeDistribution;

    @Schema(description = "近24小时事项办理趋势")
    private List<Map<String, Object>> hourlyTrend;

}