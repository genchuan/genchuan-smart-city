package cn.iocoder.yudao.module.envirhealth.util.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "柱状图数据项")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarItemVO {

    @Schema(description = "类目名称", example = "北京市")
    private String name;

    @Schema(description = "数值（百分比）", example = "87.5")
    private Double value;
}