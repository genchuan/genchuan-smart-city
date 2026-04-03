package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 充电桩状态统计 Response VO")
@Data
public class PileStatusStatRespVO {

    @Schema(description = "设备状态ID（1=已启用，2=已停用，3=未调试，4=已调试）", example = "1")
    private Long pileStatus;

    @Schema(description = "设备状态名称", example = "已启用")
    private String pileStatusName;

    @Schema(description = "该状态的充电桩数量", example = "380")
    private Integer count;

}
