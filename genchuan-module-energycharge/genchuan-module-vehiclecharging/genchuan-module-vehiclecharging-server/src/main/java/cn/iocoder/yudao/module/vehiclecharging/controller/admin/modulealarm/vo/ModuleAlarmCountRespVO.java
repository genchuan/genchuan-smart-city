package cn.iocoder.yudao.module.vehiclecharging.controller.admin.modulealarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 模块告警状态数量统计 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleAlarmCountRespVO {

    @Schema(description = "未排查告警数量", example = "2")
    private Long unCheckCount;

    @Schema(description = "已排查告警数量", example = "1")
    private Long checkedCount;

    @Schema(description = "修复中告警数量", example = "0")
    private Long repairingCount;

    @Schema(description = "已销账告警数量", example = "39")
    private Long closedCount;

    @Schema(description = "总告警数量", example = "42")
    private Long totalCount;

}
