package cn.iocoder.yudao.module.waterdetection.controller.admin.equipmentmaintenance.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备保养计划管理新增/修改 Request VO")
@Data
public class EquipmentMaintenanceSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备ID不能为空")
    private String equipmentId;

    @Schema(description = "设备类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备类型不能为空")
    private String equipmentType;

    @Schema(description = "保养周期(天)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "保养周期(天)不能为空")
    private Double maintenanceCycle;

    @Schema(description = "计划保养日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "计划保养日期不能为空")
    private LocalDateTime planMaintenanceDate;

    @Schema(description = "实际保养日期")
    private LocalDateTime actualMaintenanceDate;

    @Schema(description = "保养内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "保养内容不能为空")
    private String maintenanceContent;

    @Schema(description = "更换部件名称")
    private String replacedParts;

    @Schema(description = "保养后运行参数")
    private String postMaintenanceParams;

    @Schema(description = "维护人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "维护人员ID不能为空")
    private String maintenanceStaffId;

}