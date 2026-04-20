package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "汽车充电 - 分账报表响应 VO")
@Data
public class SharingReportPageRespVO {

    @Schema(description = "报表主键ID", example = "1")
    private Long id;

    @Schema(description = "报表编号", example = "SRP-202503-001")
    private String reportCode;

    @Schema(description = "报表名称", example = "2025年3月分账报表")
    private String reportName;

    @Schema(description = "报表类型", example = "月")
    private String reportType;

    @Schema(description = "报表时间范围", example = "2025-03-01 00:00:00~2025-03-01 23:59:59")
    private String timeRange;

    @Schema(description = "合作方", example = "XX 能源科技有限公司")
    private String cooperator;

    @Schema(description = "总结算金额", example = "15680.50")
    private BigDecimal totalSettlementAmount;

    @Schema(description = "总分账金额", example = "4704.15")
    private BigDecimal totalSharingAmount;

    @Schema(description = "关联结算单数量", example = "1")
    private Integer billCount;

    @Schema(description = "创建时间", example = "1743062400")
    private LocalDateTime createTime;
}