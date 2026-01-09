package cn.iocoder.yudao.module.smartcity.controller.admin.pipegallerysafety.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 管廊安全新增/修改 Request VO")
@Data
public class PipeGallerySafetySaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "8064")
    private Long id;

    @Schema(description = "管廊名称", example = "赵六")
    private String pipeGalleryName;

    @Schema(description = "管廊位置")
    private String locationGallery;

    @Schema(description = "管廊编号")
    private String pipeGalleryNumber;

    @Schema(description = "入廊管线类型")
    private String typePipeline;

    @Schema(description = "入廊管线数量")
    private String numberOfEntrancePipelines;

    @Schema(description = "管廊结构安全状况")
    private String safetyStatusStructure;

    @Schema(description = "管廊渗漏水情况")
    private String leakagePipeGallery;

    @Schema(description = "温湿度")
    private String temperatureAndHumidity;

    @Schema(description = "有害气体浓度")
    private String harmfulGasConcentration;

    @Schema(description = "氧气含量")
    private String oxygenContent;

    @Schema(description = "通风设备运行状态")
    private String operationVentilationEquipment;

    @Schema(description = "预警级别")
    private String warningLevel;

    @Schema(description = "维修记录")
    private String maintenanceRecord;

    @Schema(description = "维修时间")
    private LocalDateTime maintenanceTime;

    @Schema(description = "应急物资储备情况")
    private String emergencyMaterialSituation;

}