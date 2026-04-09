package cn.iocoder.yudao.module.vehiclecharging.controller.admin.abnormalorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 异常订单处理统计 Response VO")
@Data
public class AbnormalOrderHandleCountRespVO {

    @Schema(description = "总异常订单数", example = "32")
    private Integer totalAbnormalCount;

    @Schema(description = "未核实异常数", example = "2")
    private Integer unVerifyCount;

    @Schema(description = "已核实异常数", example = "1")
    private Integer verifyCount;

    @Schema(description = "处理中异常数", example = "2")
    private Integer handlingCount;

    @Schema(description = "已完结异常数", example = "27")
    private Integer completeCount;

    @Schema(description = "处理完成率 %", example = "84.38")
    private BigDecimal handleRatio;

    @Schema(description = "平均处理时长(小时)", example = "2.5")
    private BigDecimal avgHandleTime;
}