package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 窨井盖预警监测 Response VO")
@Data
public class ManholeMonitorWarningRespVO {

    // ========== 基础展示字段（输入数据） ==========

    @Schema(description = "预警编号", example = "W20260320001")
    private String warnNo;

    @Schema(description = "井盖编号", example = "YGM2024001")
    private String coverNo;

    @Schema(description = "路段名称", example = "中山路")
    private String roadName;

    @Schema(description = "异常类型", example = "倾斜超标")
    private String abnormalType;

    @Schema(description = "开合状态", example = "闭合")
    private String openStatus;

    @Schema(description = "倾斜角度", example = "15.50")
    private BigDecimal tiltAngle;

    @Schema(description = "振动数据", example = "8.25")
    private BigDecimal vibrationData;

    @Schema(description = "触发时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    // ========== 交互后新增展示字段（输出数据） ==========

    @Schema(description = "处置时限（小时）", example = "24.0")
    private BigDecimal dealLimit;

    @Schema(description = "剩余处置时间（小时）", example = "18.5")
    private BigDecimal remainingTime;

    @Schema(description = "派单状态", example = "已派单")
    private String assignStatus;

    @Schema(description = "风险等级", example = "较重")
    private String levelName;

    @Schema(description = "处置建议", example = "立即前往现场核查处理")
    private String suggest;

}
