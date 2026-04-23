package cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 离场记录修正 Request VO")
@Data
public class LeaveRecordCorrectReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

    @Schema(description = "修正后车牌", requiredMode = Schema.RequiredMode.REQUIRED, example = "闽 C54321")
    @NotBlank(message = "修正后车牌不能为空")
    private String plateNo;

    @Schema(description = "修正后入场时间，时间戳格式", requiredMode = Schema.RequiredMode.REQUIRED, example = "1774998000")
    @NotBlank(message = "修正后入场时间不能为空")
    private String enterTime;

    @Schema(description = "修正后离场时间，时间戳格式", requiredMode = Schema.RequiredMode.REQUIRED, example = "1775011986")
    @NotBlank(message = "修正后离场时间不能为空")
    private String leaveTime;

    @Schema(description = "修正后记录状态：正常记录/异常记录", requiredMode = Schema.RequiredMode.REQUIRED, example = "正常记录")
    @NotBlank(message = "修正后记录状态不能为空")
    private String status;

    @Schema(description = "场站ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "场站ID不能为空")
    private Long stationId;

    @Schema(description = "修正备注", example = "修正离场记录错误")
    private String remark;

    @Schema(description = "修正后佐证图片地址", example = "/genchuan/chargePark/vehiclePass/leaveMgmt/leaveRecord/2025/04/13/445566.jpg")
    private String proofImage;

    @Schema(description = "修正日志标记，固定为1", requiredMode = Schema.RequiredMode.REQUIRED, example = "true")
    @NotNull(message = "修正日志标记不能为空")
    private Boolean isCorrected;

}