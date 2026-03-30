package cn.iocoder.yudao.module.envirhealth.controller.admin.commercialstreet.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 商业街新增/修改 Request VO")
@Data
public class CommercialStreetSaveReqVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "主键（UUID）", example = "979")
    private String streetId;

    @Schema(description = "商业街名称", example = "赵六")
    private String name;

    @Schema(description = "商业街地址")
    private String address;

    @Schema(description = "关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "保洁频次")
    private String cleaningFrequency;

    @Schema(description = "垃圾清运间隔")
    private String transferInterval;

    @Schema(description = "关联sys_user.id", example = "6337")
    private String managerId;

    @Schema(description = "关联sys_operation_status.id", example = "24016")
    private String operationStatusId;

    @Schema(description = "保洁覆盖率")
    private BigDecimal cleaningCoverage;

    @Schema(description = "设施完好率")
    private BigDecimal facilityRate;

    @Schema(description = "问题平均处置时长（单位：小时）")
    private BigDecimal disposalDuration;

    @Schema(description = "收运完成率")
    private BigDecimal collectionCompleteRate;

    @Schema(description = "巡回保洁间隔")
    private String patrolInterval;

    @Schema(description = "保洁时段")
    private String cleaningTime;

    @Setter(AccessLevel.NONE)
    @Schema(description = "保洁人员IDs，JSON")
    private String cleanerIds;

    @Schema(description = "责任区域")
    private String responsibilityArea;

    @Schema(description = "垃圾收集点位数量")
    private Integer collectionPoints;

    @Schema(description = "异常记录数", example = "20539")
    private Integer abnormalCount;

    @Setter(AccessLevel.NONE)
    @Schema(description = "设施类型IDs，JSON")
    private String facilityIds;

    @Schema(description = "设施位置")
    private String facilityLocation;

    @Schema(description = "损坏描述")
    private String damageDesc;

    @Schema(description = "关联sys_user.id")
    private String reportBy;

    @Schema(description = "上报时间")
    private LocalDateTime reportTime;

    @Setter(AccessLevel.NONE)
    @Schema(description = "上报照片URL，JSON", example = "[\"https://www.iocoder.cn/photo1.jpg\", \"https://www.iocoder.cn/photo2.jpg\"]")
    private String problemPhotoUrl;

    @Schema(description = "关联sys_user.id")
    private String handleBy;

    @Schema(description = "派单时间")
    private LocalDateTime dispatchTime;

    @Schema(description = "关联sys_maintain_status.id", example = "28532")
    private String maintainStatusId;

    @Schema(description = "预计完成时间")
    private LocalDateTime expectedCompleteTime;

    @Schema(description = "关联sys_problem_type.id", example = "22795")
    private String problemTypeId;

    @Schema(description = "问题位置")
    private String problemLocation;

    @Schema(description = "问题描述")
    private String problemDesc;

    @Schema(description = "关联sys_handle_status.id", example = "15614")
    private String handleStatusId;

    @Schema(description = "处置结果")
    private String handleResult;

    @Schema(description = "负责车辆ID，关联sys_vehicle.sys_vehicle_id（支持车辆钻取）", example = "888")
    private String vehicleId;

    @Setter(AccessLevel.NONE)
    @Schema(description = "负责人员IDs，JSON格式，关联sys_user.user_id", example = "[1,2,3]")
    private String staffIds;

    @Schema(description = "收运计划状态ID，关联sys_plan_status.sys_plan_status_id（支持状态筛选钻取）", example = "999")
    private String planStatusId;

    @Schema(description = "任务类型ID，关联sys_task_type.name（支持任务类型钻取，筛选同类型已完成任务）")
    private String taskTypeId;

    public void setProblemPhotoUrl(String problemPhotoUrl) {
        this.problemPhotoUrl = (problemPhotoUrl == null || problemPhotoUrl.trim().isEmpty()) ? "[]" : problemPhotoUrl;
    }

    public void setStaffIds(String staffIds) {
        this.staffIds = (staffIds == null || staffIds.trim().isEmpty()) ? "[]" : staffIds;
    }

    public void setCleanerIds(String cleanerIds) {
        this.cleanerIds = (cleanerIds == null || cleanerIds.trim().isEmpty()) ? "[]" : cleanerIds;
    }

    public void setFacilityIds(String facilityIds) {
        this.facilityIds = (facilityIds == null || facilityIds.trim().isEmpty()) ? "[]" : facilityIds;
    }

}