package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.garbagetransfer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 垃圾转运站 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GarbageTransferRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11765")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "转运站主键（UUID）", example = "27803")
    @ExcelProperty("转运站主键（UUID）")
    private String transferId;

    @Schema(description = "转运站名称", example = "王五")
    @ExcelProperty("转运站名称")
    private String name;

    @Schema(description = "转运站位置")
    @ExcelProperty("转运站位置")
    private String location;

    @Schema(description = "关联sys_area.area_code")
    @ExcelProperty("关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "核心设备IDs，JSON")
    @ExcelProperty("核心设备IDs，JSON")
    private String equipmentIds;

    @Schema(description = "关联sys_operation_status.id", example = "10758")
    @ExcelProperty("关联sys_operation_status.id")
    private String operationStatusId;

    @Schema(description = "关联sys_user.id", example = "14798")
    @ExcelProperty("关联sys_user.id")
    private String managerId;

    @Schema(description = "日转运量（单位：吨）")
    @ExcelProperty("日转运量（单位：吨）")
    private BigDecimal dailyTransferVolume;

    @Schema(description = "设备正常运行率")
    @ExcelProperty("设备正常运行率")
    private BigDecimal equipmentRate;

    @Schema(description = "环境达标率")
    @ExcelProperty("环境达标率")
    private BigDecimal environmentRate;

    @Schema(description = "预警未处理数", example = "19699")
    @ExcelProperty("预警未处理数")
    private Integer unhandledAlarmCount;

    @Schema(description = "设备待维护数", example = "19292")
    @ExcelProperty("设备待维护数")
    private Integer pendingMaintenanceCount;

    @Schema(description = "实时环境数据，JSON")
    @ExcelProperty("实时环境数据，JSON")
    private String environmentData;

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