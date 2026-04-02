package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "窨井盖预警状态更新 Response VO")
@Data
public class ManholeCoverWarnUpdateStatusRespVO {

    @Schema(description = "预警主键ID")
    private String warnId;

    @Schema(description = "预警状态 1-处理中，2-已解决，3-已忽略")
    private Integer warnStatus;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "租户ID")
    private String tenantId;
}