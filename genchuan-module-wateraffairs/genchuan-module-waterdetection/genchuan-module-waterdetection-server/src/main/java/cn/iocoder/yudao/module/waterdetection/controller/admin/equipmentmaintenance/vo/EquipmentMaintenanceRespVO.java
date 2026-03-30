package cn.iocoder.yudao.module.waterdetection.controller.admin.equipmentmaintenance.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 设备保养计划管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EquipmentMaintenanceRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备ID")
    private String equipmentId;

    @Schema(description = "设备类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备类型")
    private String equipmentType;

    @Schema(description = "保养周期(天)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("保养周期(天)")
    private Double maintenanceCycle;

    @Schema(description = "计划保养日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("计划保养日期")
    private LocalDateTime planMaintenanceDate;

    @Schema(description = "实际保养日期")
    @ExcelProperty("实际保养日期")
    private LocalDateTime actualMaintenanceDate;

    @Schema(description = "保养内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("保养内容")
    private String maintenanceContent;

    @Schema(description = "更换部件名称")
    @ExcelProperty("更换部件名称")
    private String replacedParts;

    @Schema(description = "保养后运行参数")
    @ExcelProperty("保养后运行参数")
    private String postMaintenanceParams;

    @Schema(description = "维护人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("维护人员ID")
    private String maintenanceStaffId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}