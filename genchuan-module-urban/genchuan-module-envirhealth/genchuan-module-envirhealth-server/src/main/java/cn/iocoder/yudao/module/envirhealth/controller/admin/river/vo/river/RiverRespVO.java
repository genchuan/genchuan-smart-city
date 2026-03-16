package cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.river;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 河道 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RiverRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11598")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "主键（UUID）", example = "30212")
    @ExcelProperty("主键（UUID）")
    private String riverId;

    @Schema(description = "河道名称", example = "赵六")
    @ExcelProperty("河道名称")
    private String name;

    @Schema(description = "责任河段")
    @ExcelProperty("责任河段")
    private String responsibilitySection;

    @Schema(description = "关联sys_area.area_code")
    @ExcelProperty("关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "河道长度，单位：公里")
    @ExcelProperty("河道长度，单位：公里")
    private BigDecimal length;

    @Schema(description = "关联sys_user.id", example = "24833")
    @ExcelProperty("关联sys_user.id")
    private String managerId;

    @Schema(description = "关联sys_operation_status.id", example = "8849")
    @ExcelProperty("关联sys_operation_status.id")
    private String operationStatusId;

    @Schema(description = "保洁覆盖率")
    @ExcelProperty("保洁覆盖率")
    private BigDecimal cleaningCoverage;

    @Schema(description = "水质达标率")
    @ExcelProperty("水质达标率")
    private BigDecimal waterQualityRate;

    @Schema(description = "垃圾打捞总量（单位：吨）")
    @ExcelProperty("垃圾打捞总量（单位：吨）")
    private BigDecimal wasteFishingVolume;

    @Schema(description = "问题办结率")
    @ExcelProperty("问题办结率")
    private BigDecimal problemCompleteRate;

    @Schema(description = "关联sys_cleaning_type.id", example = "28471")
    @ExcelProperty("关联sys_cleaning_type.id")
    private String cleaningTypeId;

    @Schema(description = "保洁频次")
    @ExcelProperty("保洁频次")
    private String waterCleaningFrequency;

    @Schema(description = "保洁时段")
    @ExcelProperty("保洁时段")
    private String cleaningTime;

    @Schema(description = "负责人员IDs，JSON")
    @ExcelProperty("负责人员IDs，JSON")
    private String staffIds;

    @Schema(description = "保洁工具IDs，JSON")
    @ExcelProperty("保洁工具IDs，JSON")
    private String toolIds;

    @Schema(description = "垃圾打捞预估量（单位：吨）")
    @ExcelProperty("垃圾打捞预估量（单位：吨）")
    private BigDecimal wasteFishingEstimate;

    @Schema(description = "关联sys_monitor_type.id", example = "3153")
    @ExcelProperty("关联sys_monitor_type.id")
    private String monitorTypeId;

    @Schema(description = "监测周期")
    @ExcelProperty("监测周期")
    private String waterQualityCycle;

    @Schema(description = "监测指标，JSON")
    @ExcelProperty("监测指标，JSON")
    private String monitorIndicators;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("关联sys_user.id")
    private String monitorBy;

    @Schema(description = "计划监测时间")
    @ExcelProperty("计划监测时间")
    private LocalDateTime planMonitorTime;

    @Schema(description = "关联sys_monitor_status.id", example = "8944")
    @ExcelProperty("关联sys_monitor_status.id")
    private String monitorStatusId;

    @Schema(description = "上次监测时间")
    @ExcelProperty("上次监测时间")
    private LocalDateTime lastMonitorTime;

    @Schema(description = "下次监测提醒时间")
    @ExcelProperty("下次监测提醒时间")
    private LocalDateTime nextMonitorRemindTime;

    @Schema(description = "监测数据达标率")
    @ExcelProperty("监测数据达标率")
    private BigDecimal monitorDataQualifiedRate;

    @Schema(description = "预警次数", example = "28796")
    @ExcelProperty("预警次数")
    private Integer warningCount;

    @Schema(description = "关联sys_problem_type.id", example = "4445")
    @ExcelProperty("关联sys_problem_type.id")
    private String problemTypeId;

    @Schema(description = "问题位置")
    @ExcelProperty("问题位置")
    private String problemLocation;

    @Schema(description = "问题描述")
    @ExcelProperty("问题描述")
    private String problemDesc;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("关联sys_user.id")
    private String reportBy;

    @Schema(description = "上报时间")
    @ExcelProperty("上报时间")
    private LocalDateTime reportTime;

    @Schema(description = "现场照片/视频URL", example = "https://www.iocoder.cn")
    @ExcelProperty("现场照片/视频URL")
    private String problemMediaUrl;

    @Schema(description = "关联sys_dept.id", example = "16969")
    @ExcelProperty("关联sys_dept.id")
    private String deptId;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("关联sys_user.id")
    private String handleBy;

    @Schema(description = "派单时间")
    @ExcelProperty("派单时间")
    private LocalDateTime dispatchTime;

    @Schema(description = "关联sys_handle_status.id", example = "12805")
    @ExcelProperty("关联sys_handle_status.id")
    private String handleStatusId;

    @Schema(description = "超时提醒：是/否")
    @ExcelProperty("超时提醒：是/否")
    private String isTimeout;

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