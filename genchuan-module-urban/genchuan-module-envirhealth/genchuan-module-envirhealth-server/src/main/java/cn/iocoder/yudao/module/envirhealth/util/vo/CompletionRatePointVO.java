package cn.iocoder.yudao.module.envirhealth.util.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CompletionRatePointVO {

    @Schema(description = "时间点（如：08:00, 09:00）")
    private String timePoint;

    @Schema(description = "完成率（百分比，如：25.5）")
    private Double completionRate;
}