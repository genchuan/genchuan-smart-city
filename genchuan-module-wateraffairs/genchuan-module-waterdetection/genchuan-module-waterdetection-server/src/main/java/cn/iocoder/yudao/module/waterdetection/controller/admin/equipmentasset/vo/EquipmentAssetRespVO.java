package cn.iocoder.yudao.module.waterdetection.controller.admin.equipmentasset.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 设备资产台账管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EquipmentAssetRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "设备编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备编号")
    private String equipmentCode;

    @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备名称")
    private String equipmentName;

    @Schema(description = "型号")
    @ExcelProperty("型号")
    private String model;

    @Schema(description = "规格")
    @ExcelProperty("规格")
    private String specification;

    @Schema(description = "安装位置")
    @ExcelProperty("安装位置")
    private String installLocation;

    @Schema(description = "安装日期")
    @ExcelProperty("安装日期")
    private LocalDateTime installDate;

    @Schema(description = "生产厂家")
    @ExcelProperty("生产厂家")
    private String manufacturer;

    @Schema(description = "维护记录")
    @ExcelProperty("维护记录")
    private String maintenanceRecord;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}