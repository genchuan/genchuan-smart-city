package cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.park;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 公园 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21689")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "主键（UUID）", example = "25807")
    @ExcelProperty("主键（UUID）")
    private String parkId;

    @Schema(description = "公园名称", example = "李四")
    @ExcelProperty("公园名称")
    private String name;

    @Schema(description = "公园地址")
    @ExcelProperty("公园地址")
    private String address;

    @Schema(description = "关联sys_area.area_code")
    @ExcelProperty("关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "保洁频次")
    @ExcelProperty("保洁频次")
    private String cleaningFrequency;

    @Schema(description = "绿化养护周期")
    @ExcelProperty("绿化养护周期")
    private String greenMaintenanceCycle;

    @Schema(description = "关联sys_user.id", example = "7468")
    @ExcelProperty("关联sys_user.id")
    private String managerId;

    @Schema(description = "关联sys_operation_status.id", example = "28276")
    @ExcelProperty("关联sys_operation_status.id")
    private String operationStatusId;

    @Schema(description = "保洁达标率")
    @ExcelProperty("保洁达标率")
    private BigDecimal cleaningRate;

    @Schema(description = "绿化存活率")
    @ExcelProperty("绿化存活率")
    private BigDecimal greenSurvivalRate;

    @Schema(description = "设施完好率")
    @ExcelProperty("设施完好率")
    private BigDecimal facilityRate;

    @Schema(description = "环境达标率")
    @ExcelProperty("环境达标率")
    private BigDecimal environmentRate;

    @Schema(description = "垃圾清运完成率")
    @ExcelProperty("垃圾清运完成率")
    private BigDecimal wasteTransferCompleteRate;

    @Schema(description = "保洁区域")
    @ExcelProperty("保洁区域")
    private String cleaningArea;

    @Schema(description = "保洁标准")
    @ExcelProperty("保洁标准")
    private String cleaningStandard;

    @Schema(description = "负责人员IDs，JSON")
    @ExcelProperty("负责人员IDs，JSON")
    private String staffIds;

    @Schema(description = "绿化品类IDs，JSON")
    @ExcelProperty("绿化品类IDs，JSON")
    private String greenTypeIds;

    @Schema(description = "养护区域")
    @ExcelProperty("养护区域")
    private String greenArea;

    @Schema(description = "养护内容")
    @ExcelProperty("养护内容")
    private String greenMaintenanceContent;

    @Schema(description = "养护人员IDs，JSON")
    @ExcelProperty("养护人员IDs，JSON")
    private String greenStaffIds;

    @Schema(description = "垃圾收集点位")
    @ExcelProperty("垃圾收集点位")
    private Integer wasteCollectionPoints;

    @Schema(description = "垃圾清运频次")
    @ExcelProperty("垃圾清运频次")
    private String wasteTransferFrequency;

    @Schema(description = "清运时段")
    @ExcelProperty("清运时段")
    private String wasteTransferTime;

    @Schema(description = "关联sys_vehicle.id", example = "11391")
    @ExcelProperty("关联sys_vehicle.id")
    private String vehicleId;

    @Schema(description = "垃圾清运量（单位：吨）")
    @ExcelProperty("垃圾清运量（单位：吨）")
    private BigDecimal wasteVolume;

    @Schema(description = "设施类型IDs，JSON")
    @ExcelProperty("设施类型IDs，JSON")
    private String facilityIds;

    @Schema(description = "设施位置")
    @ExcelProperty("设施位置")
    private String facilityLocation;

    @Schema(description = "损坏描述")
    @ExcelProperty("损坏描述")
    private String facilityDamageDesc;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("关联sys_user.id")
    private String reportBy;

    @Schema(description = "上报时间")
    @ExcelProperty("上报时间")
    private LocalDateTime reportTime;

    @Schema(description = "上报照片URL，JSON", example = "https://www.iocoder.cn")
    @ExcelProperty("上报照片URL，JSON")
    private String facilityPhotoUrl;

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

    @Schema(description = "计划状态ID（关联sys_plan_status.id）", example = "10001")
    @ExcelProperty("计划状态ID")
    private String planStatusId;

    @Schema(description = "任务类型ID（关联sys_task_type.id，对应sys_task_type.name）", example = "10001")
    @ExcelProperty("任务类型ID")
    private String taskTypeName;
}