package cn.iocoder.yudao.module.vehiclecharging.controller.admin.interconnection.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 互联互通表关闭 Request VO")
@Data
public class InterconnectionCloseReqVO {
    @Schema(description = "对接申请主键 ID", example = "4002")
    private Integer id;

    @Schema(description = "关闭原因", example = "合作到期，不再续期")
    private String closeReason;
}
