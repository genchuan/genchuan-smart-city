package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "分账报表打印 VO")
@Data
public class SharingReportPrintVO {

    private String reportCode;

    private String reportName;

    private String reportType;

    private String timeRange;

    private String cooperator;

    private BigDecimal totalSettlementAmount;

    private BigDecimal totalSharingAmount;

    private Integer billCount;

    private LocalDateTime createTime;

    private String creator;
}