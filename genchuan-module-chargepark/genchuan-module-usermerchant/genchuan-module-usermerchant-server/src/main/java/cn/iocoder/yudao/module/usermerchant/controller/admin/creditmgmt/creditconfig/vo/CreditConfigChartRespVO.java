package cn.iocoder.yudao.module.usermerchant.controller.admin.creditmgmt.creditconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Schema(description = "管理后台 - 信用配置统计响应 VO")
public class CreditConfigChartRespVO {

    @Schema(description = "配置类型占比数据（饼图）")
    private List<CreditConfigChartRespVO.ConfigTypeDistributionVO> configTypeDistribution;

    @Schema(description = "生效配置数")
    private Long effectConfigCount;

    @Schema(description = "信用评分准确率")
    private BigDecimal creditScoreAccuracy;

    @Data
    public static class ConfigTypeDistributionVO {
        @Schema(description = "配置类型", example = "权益配置")
        private String type;
        @Schema(description = "数量", example = "10")
        private Integer count;
    }

}
