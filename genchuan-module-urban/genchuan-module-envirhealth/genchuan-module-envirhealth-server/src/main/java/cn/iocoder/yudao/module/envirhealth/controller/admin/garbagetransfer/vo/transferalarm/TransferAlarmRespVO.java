package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferalarm;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 转运站预警 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TransferAlarmRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "预警主键（UUID）", example = "3038")
    @ExcelProperty("预警主键")
    private String alarmId;

    @Schema(description = "关联garbage_transfer.transfer_id", example = "5796")
    @ExcelProperty("转运编号")
    private String transferId;

    @Schema(description = "关联sys_alarm_type.id", example = "643")
    @ExcelProperty("预警类型编码")
    private String alarmTypeId;

    @Schema(description = "发生时间")
    @ExcelProperty("发生时间")
    private LocalDateTime alarmTime;

    @Schema(description = "预警内容")
    @ExcelProperty("预警内容")
    private String alarmContent;

    @Schema(description = "预警照片，JSON")
    @ExcelProperty("预警照片")
    private String alarmPhoto;

    @Schema(description = "关联设备/区域")
    @ExcelProperty("关联设备")
    private String relevantInfo;

    @Schema(description = "处置状态：待处置/处理中/已解除", example = "1")
    @ExcelProperty("处置状态")
    private String handleStatus;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("处置人员")
    private String handleBy;

    @Schema(description = "超时提醒：是/否")
    @ExcelProperty("超时提醒")
    private String abnormalIsTimeout;

    @Schema(description = "处置进度")
    @ExcelProperty("处置进度")
    private String handleProgress;

    @Schema(description = "处置结果")
    @ExcelProperty("处置结果")
    private String handleResult;

    @Schema(description = "佐证材料URL，JSON")
    @ExcelProperty("佐证材料")
    private String proofMaterial;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}