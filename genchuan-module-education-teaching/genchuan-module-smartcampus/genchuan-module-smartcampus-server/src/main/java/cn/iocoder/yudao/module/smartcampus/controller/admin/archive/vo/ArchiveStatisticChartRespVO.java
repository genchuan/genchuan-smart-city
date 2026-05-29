package cn.iocoder.yudao.module.smartcampus.controller.admin.archive.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "学籍统计图表数据")
@Data
public class ArchiveStatisticChartRespVO {

    @Schema(description = "学籍状态分布（饼图）")
    private List<StatusPieVO> statusPieList;

    @Schema(description = "学籍异动趋势（折线图）")
    private List<ChangeTrendVO> changeTrendList;

    @Data
    @Schema(description = "状态分布项")
    public static class StatusPieVO {
        @Schema(description = "状态编码", example = "0")
        private String status;
        @Schema(description = "状态名称", example = "在籍")
        private String statusName;
        @Schema(description = "数量")
        private Integer count;
        @Schema(description = "占比(%)")
        private BigDecimal ratio;
    }

    @Data
    @Schema(description = "异动趋势项")
    public static class ChangeTrendVO {
        @Schema(description = "时段（年月）", example = "2025-09")
        private String period;
        @Schema(description = "异动人数")
        private Integer count;
    }
}