package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "属性VO")
@Data
public class AttributeVO {
    @Schema(description = "最后更新时间戳")
    private Long lastUpdateTs;

    @Schema(description = "属性键")
    private String key;

    @Schema(description = "属性值")
    private Object value;
}
