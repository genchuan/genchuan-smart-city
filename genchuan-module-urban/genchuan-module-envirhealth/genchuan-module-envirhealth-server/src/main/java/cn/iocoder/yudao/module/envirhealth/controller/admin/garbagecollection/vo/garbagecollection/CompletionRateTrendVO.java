package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Schema(description = "环境卫生管理模块 - 收运完成率趋势折线图 Response VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompletionRateTrendVO {

    @Schema(description = "日期", requiredMode = Schema.RequiredMode.REQUIRED, example = "2026-03-02")
    private String date;

    @Schema(description = "完成率(%)", requiredMode = Schema.RequiredMode.REQUIRED, example = "85.5")
    private BigDecimal completionRate;
}