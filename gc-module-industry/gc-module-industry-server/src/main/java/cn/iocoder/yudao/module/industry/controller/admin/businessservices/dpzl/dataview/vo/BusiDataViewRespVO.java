// DataOverviewRespVO.java
package cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.dataview.vo;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 数据概览查询 Response VO")
@Data
public class BusiDataViewRespVO {

    @Schema(description = "企业总数")
    private Integer totalEntCount;

    @Schema(description = "当日办件量")
    private Integer dailyHandleCount;

    @Schema(description = "政策兑现总额")
    private BigDecimal totalPolAmount;

    @Schema(description = "诉求响应率")
    private BigDecimal appealRespRate;

    @Schema(description = "区域企业分布列表")
    private List<RegionEntDistribution> regionEntDistributions;

    @Schema(description = "近7天办件趋势列表")
    private List<DailyHandleTrend> dailyHandleTrends;

    @Data
    @Schema(description = "区域企业分布")
    public static class RegionEntDistribution {

        @Schema(description = "区域名称")
        private String regionName;

        @Schema(description = "企业数量")
        private Integer entCount;

        @Schema(description = "区域编码")
        private String regionCode;
    }

    @Data
    @Schema(description = "每日办件趋势")
    public static class DailyHandleTrend {

        @Schema(description = "日期")
        private String date;

        @Schema(description = "办件数量")
        private Integer handleCount;
    }

}