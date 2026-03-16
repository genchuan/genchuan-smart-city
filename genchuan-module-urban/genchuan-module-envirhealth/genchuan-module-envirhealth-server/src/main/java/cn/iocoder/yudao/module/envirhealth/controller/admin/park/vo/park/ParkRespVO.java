package cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.park;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 公园 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "主键（UUID）", example = "25807")
    @ExcelProperty("公园主键")
    private String parkId;

    @Schema(description = "公园名称", example = "李四")
    @ExcelProperty("公园名称")
    private String name;

    @Schema(description = "公园地址")
    @ExcelProperty("公园地址")
    private String address;

    @Schema(description = "关联sys_area.area_code")
    @ExcelProperty("区域编码")
    private String areaCode;

    @Schema(description = "保洁频次")
    @ExcelProperty("保洁频次")
    private String cleaningFrequency;

    @Schema(description = "绿化养护周期")
    @ExcelProperty("绿化养护周期")
    private String greenMaintenanceCycle;

    @Schema(description = "关联sys_user.id", example = "7468")
    @ExcelProperty("管理人员")
    private String managerId;

    @Schema(description = "关联sys_operation_status.id", example = "28276")
    @ExcelProperty("运行状态编码")
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
    @ExcelProperty("负责人员")
    private String staffIds;

    @Schema(description = "绿化品类IDs，JSON")
    @ExcelProperty("绿化品类")
    private String greenTypeIds;

    @Schema(description = "养护区域")
    @ExcelProperty("养护区域")
    private String greenArea;

    @Schema(description = "养护内容")
    @ExcelProperty("养护内容")
    private String greenMaintenanceContent;

    @Schema(description = "养护人员IDs，JSON")
    @ExcelProperty("养护人员")
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
    @ExcelProperty("车辆编码")
    private String vehicleId;

    @Schema(description = "垃圾清运量（单位：吨）")
    @ExcelProperty("垃圾清运量")
    private BigDecimal wasteVolume;

    @Schema(description = "设施类型IDs，JSON")
    @ExcelProperty("设施类型")
    private String facilityIds;

    @Schema(description = "设施位置")
    @ExcelProperty("设施位置")
    private String facilityLocation;

    @Schema(description = "损坏描述")
    @ExcelProperty("损坏描述")
    private String facilityDamageDesc;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("上报人员")
    private String reportBy;

    @Schema(description = "上报时间")
    @ExcelProperty("上报时间")
    private LocalDateTime reportTime;

    @Schema(description = "上报照片URL，JSON", example = "https://www.iocoder.cn")
    @ExcelProperty("上报照片")
    private String facilityPhotoUrl;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "计划状态ID（关联sys_plan_status.id）", example = "10001")
    @ExcelProperty("计划状态编码")
    private String planStatusId;

    @Schema(description = "任务类型ID（关联sys_task_type.id，对应sys_task_type.name）")
    @ExcelProperty("任务类型编码")
    private String taskTypeId;
}