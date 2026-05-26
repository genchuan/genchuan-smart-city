package cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 离场记录编辑 Request VO")
@Data
public class LeaveRecordUpdateReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED, example = "闽 C12345")
    @NotBlank(message = "车牌不能为空")
    private String plateNo;

    @Schema(description = "入场时间，时间戳格式", requiredMode = Schema.RequiredMode.REQUIRED, example = "1774998000")
    @NotNull(message = "入场时间不能为空")
    private LocalDateTime enterTime;

    @Schema(description = "离场时间，时间戳格式", requiredMode = Schema.RequiredMode.REQUIRED, example = "1775011986")
    @NotNull(message = "离场时间不能为空")
    private LocalDateTime leaveTime;

    @Schema(description = "记录状态：正常记录/异常记录", requiredMode = Schema.RequiredMode.REQUIRED, example = "正常记录")
    @NotBlank(message = "记录状态不能为空")
    private String status;

    @Schema(description = "场站ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "场站ID不能为空")
    private Long stationId;

    @Schema(description = "备注", example = "编辑离场记录备注")
    private String remark;

    @Schema(description = "佐证图片地址", example = "/genchuan/chargePark/vehiclePass/leaveMgmt/leaveRecord/2025/04/13/112233.jpg")
    private String proofImage;

}