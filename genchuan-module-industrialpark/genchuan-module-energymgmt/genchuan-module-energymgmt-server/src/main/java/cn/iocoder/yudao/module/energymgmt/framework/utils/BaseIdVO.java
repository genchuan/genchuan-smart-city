package cn.iocoder.yudao.module.energymgmt.framework.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BaseIdVO {

    @Schema(description = "用户ID数组")
    private Long id;

}
