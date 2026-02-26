package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferalarm;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 转运站预警新增/修改 Request VO")
@Data
public class TransferAlarmSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21")
    private Long id;

    @Schema(description = "预警主键（UUID）", example = "3038")
    private String alarmId;

    @Schema(description = "关联garbage_transfer.transfer_id", example = "5796")
    private String transferId;

    @Schema(description = "关联sys_alarm_type.id", example = "643")
    private String alarmTypeId;

    @Schema(description = "发生时间")
    private LocalDateTime alarmTime;

    @Schema(description = "预警内容")
    private String alarmContent;

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

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}