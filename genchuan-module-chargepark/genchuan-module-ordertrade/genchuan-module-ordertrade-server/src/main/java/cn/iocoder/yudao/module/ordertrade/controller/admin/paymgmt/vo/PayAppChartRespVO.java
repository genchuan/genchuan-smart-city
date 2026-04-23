package cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 支付应用统计 Response VO")
@Data
public class PayAppChartRespVO {

    @Schema(description = "应用类型占比数据（饼图）")
    private List<Map<String, Object>> typeData;

    @Schema(description = "生效应用数")
    private Long enabledCount;

    @Schema(description = "总应用数")
    private Long totalCount;
}
