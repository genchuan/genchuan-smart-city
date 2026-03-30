package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferalarm;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 转运站预警新增/修改 Request VO")
@Data
public class TransferAlarmSaveReqVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "预警主键（UUID）", example = "3038")
    private String alarmId;

    @Schema(description = "关联garbage_transfer.transfer_id", requiredMode = Schema.RequiredMode.REQUIRED, example = "5796")
    @NotBlank(message = "转运站ID（transferId）不能为空")
    private String transferId;

    @Schema(description = "关联sys_alarm_type.id", example = "643")
    private String alarmTypeId;

    @Schema(description = "发生时间")
    private LocalDateTime alarmTime;

    @Schema(description = "预警内容")
    private String alarmContent;

    @Schema(description = "预警照片，JSON")
    private String alarmPhoto;

    @Schema(description = "关联设备/区域")
    private String relevantInfo;

    @Schema(description = "处置状态：待处置/处理中/已解除", example = "1")
    private String handleStatus;

    @Schema(description = "关联sys_user.id")
    private String handleBy;

    @Schema(description = "超时提醒：是/否")
    private String abnormalIsTimeout;

    @Schema(description = "处置进度")
    private String handleProgress;

    @Schema(description = "处置结果")
    private String handleResult;

    @Schema(description = "佐证材料URL，JSON")
    private String proofMaterial;

    public void setProofMaterial(String proofMaterial) {
        this.proofMaterial = (proofMaterial == null || proofMaterial.trim().isEmpty()) ? "[]" : proofMaterial;
    }

    public void setAlarmPhoto(String alarmPhoto) {
        this.alarmPhoto = (alarmPhoto == null || alarmPhoto.trim().isEmpty()) ? "[]" : alarmPhoto;
    }

}