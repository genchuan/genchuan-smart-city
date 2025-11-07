package cn.iocoder.yudao.module.smartcity.controller.admin.pipegallerysafety.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 管廊安全 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PipeGallerySafetyRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "8064")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "管廊名称", example = "赵六")
    @ExcelProperty("管廊名称")
    private String pipeGalleryName;

    @Schema(description = "管廊位置")
    @ExcelProperty("管廊位置")
    private String locationGallery;

    @Schema(description = "管廊编号")
    @ExcelProperty("管廊编号")
    private String pipeGalleryNumber;

    @Schema(description = "入廊管线类型")
    @ExcelProperty("入廊管线类型")
    private String typePipeline;

    @Schema(description = "入廊管线数量")
    @ExcelProperty("入廊管线数量")
    private String numberOfEntrancePipelines;

    @Schema(description = "管廊结构安全状况")
    @ExcelProperty("管廊结构安全状况")
    private String safetyStatusStructure;

    @Schema(description = "管廊渗漏水情况")
    @ExcelProperty("管廊渗漏水情况")
    private String leakagePipeGallery;

    @Schema(description = "温湿度")
    @ExcelProperty("温湿度")
    private String temperatureAndHumidity;

    @Schema(description = "有害气体浓度")
    @ExcelProperty("有害气体浓度")
    private String harmfulGasConcentration;

    @Schema(description = "氧气含量")
    @ExcelProperty("氧气含量")
    private String oxygenContent;

    @Schema(description = "通风设备运行状态")
    @ExcelProperty("通风设备运行状态")
    private String operationVentilationEquipment;

    @Schema(description = "预警级别")
    @ExcelProperty("预警级别")
    private String warningLevel;

    @Schema(description = "维修记录")
    @ExcelProperty("维修记录")
    private String maintenanceRecord;

    @Schema(description = "维修时间")
    @ExcelProperty("维修时间")
    private LocalDateTime maintenanceTime;

    @Schema(description = "应急物资储备情况")
    @ExcelProperty("应急物资储备情况")
    private String emergencyMaterialSituation;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}