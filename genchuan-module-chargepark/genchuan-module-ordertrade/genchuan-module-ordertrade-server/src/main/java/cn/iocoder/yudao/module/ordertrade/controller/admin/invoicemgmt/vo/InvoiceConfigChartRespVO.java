package cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 发票配置统计 Response VO")
@Data
public class InvoiceConfigChartRespVO {

    @Schema(description = "配置类型占比数据（饼图）")
    private List<Map<String, Object>> categoryData;

    @Schema(description = "生效配置数")
    private Long enabledCount;
}
