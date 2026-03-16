package cn.iocoder.yudao.module.envirhealth.util.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "圆环图数据项")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PieItemVO {

    @Schema(description = "名称", example = "正常运营")
    private String name;

    @Schema(description = "数量", example = "12")
    private Long value;
}
