package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 会员配置统计响应 VO")
@Data
public class MemberConfigChartRespVO {

    @Schema(description = "配置类型占比数据（饼图）")
    private List<ConfigTypeDistributionVO> configTypeDistribution;

    @Schema(description = "生效配置数")
    private Long effectConfigCount;

    @Schema(description = "会员匹配率")
    private BigDecimal memberMatchRate;

    @Data
    public static class ConfigTypeDistributionVO {
        @Schema(description = "配置类型", example = "权益配置")
        private String type;
        @Schema(description = "数量", example = "10")
        private Integer count;
    }
}