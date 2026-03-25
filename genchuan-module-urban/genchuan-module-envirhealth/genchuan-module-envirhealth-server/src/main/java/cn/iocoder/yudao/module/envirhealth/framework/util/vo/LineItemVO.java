package cn.iocoder.yudao.module.envirhealth.framework.util.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "折线图数据项")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineItemVO {

    @Schema(description = "时间点", example = "2024-03-01")
    private String timePoint;

    @Schema(description = "数值", example = "85.5")
    private Double value;
}