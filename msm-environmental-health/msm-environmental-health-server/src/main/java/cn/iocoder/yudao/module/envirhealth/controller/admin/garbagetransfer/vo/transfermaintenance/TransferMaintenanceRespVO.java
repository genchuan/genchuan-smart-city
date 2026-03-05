package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transfermaintenance;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 设备维护 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TransferMaintenanceRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10273")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "维护主键（UUID）", example = "560")
    @ExcelProperty("维护主键（UUID）")
    private String maintenanceId;

    @Schema(description = "关联garbage_transfer.transfer_id", example = "19812")
    @ExcelProperty("关联garbage_transfer.transfer_id")
    private String transferId;

    @Schema(description = "关联sys_equipment.id", example = "17093")
    @ExcelProperty("关联sys_equipment.id")
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
    @ExcelProperty("关联sys_user.id")
    private String handleBy;

    @Schema(description = "维护状态：待维护/维护中/已完成", example = "1")
    @ExcelProperty("维护状态：待维护/维护中/已完成")
    private String maintenanceStatus;

    @Schema(description = "预计完成时间")
    @ExcelProperty("预计完成时间")
    private LocalDateTime expectedCompleteTime;

    @Schema(description = "超时提醒：是/否")
    @ExcelProperty("超时提醒：是/否")
    private String abnormalIsTimeout;

    @Schema(description = "维护时间")
    @ExcelProperty("维护时间")
    private LocalDateTime maintenanceTime;

    @Schema(description = "更换配件")
    @ExcelProperty("更换配件")
    private String replaceParts;

    @Schema(description = "维护费用（单位：元）")
    @ExcelProperty("维护费用（单位：元）")
    private BigDecimal maintenanceCost;

    @Schema(description = "维护照片URL，JSON")
    @ExcelProperty("维护照片URL，JSON")
    private String maintenancePhoto;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}