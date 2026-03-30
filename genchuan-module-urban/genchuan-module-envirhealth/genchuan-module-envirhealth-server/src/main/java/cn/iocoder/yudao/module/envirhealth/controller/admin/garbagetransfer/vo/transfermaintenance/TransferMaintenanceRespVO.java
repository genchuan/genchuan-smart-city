package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transfermaintenance;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 设备维护 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TransferMaintenanceRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "维护主键（UUID）", example = "560")
    @ExcelProperty("维护主键")
    private String maintenanceId;

    @Schema(description = "关联garbage_transfer.transfer_id", example = "19812")
    @ExcelProperty("转运编号")
    private String transferId;

    @Schema(description = "关联sys_equipment.id", example = "17093")
    @ExcelProperty("设备编码")
    private String equipmentId;

    @Schema(description = "维护周期")
    @ExcelProperty("维护周期")
    private String maintenanceCycle;

    @Schema(description = "上次维护时间")
    @ExcelProperty("上次维护时间")
    private LocalDateTime lastMaintenanceTime;

    @Schema(description = "维护内容")
    @ExcelProperty("维护内容")
    private String maintenanceContent;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("处置人员")
    private String handleBy;

    @Schema(description = "维护状态：待维护/维护中/已完成", example = "1")
    @ExcelProperty("维护状态")
    private String maintenanceStatus;

    @Schema(description = "预计完成时间")
    @ExcelProperty("预计完成时间")
    private LocalDateTime expectedCompleteTime;

    @Schema(description = "超时提醒：是/否")
    @ExcelProperty("超时提醒")
    private String abnormalIsTimeout;

    @Schema(description = "维护时间")
    @ExcelProperty("维护时间")
    private LocalDateTime maintenanceTime;

    @Schema(description = "更换配件")
    @ExcelProperty("更换配件")
    private String replaceParts;

    @Schema(description = "维护费用（单位：元）")
    @ExcelProperty("维护费用(元)")
    private BigDecimal maintenanceCost;

    @Schema(description = "维护照片URL，JSON")
    @ExcelProperty("维护照片")
    private String maintenancePhoto;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}