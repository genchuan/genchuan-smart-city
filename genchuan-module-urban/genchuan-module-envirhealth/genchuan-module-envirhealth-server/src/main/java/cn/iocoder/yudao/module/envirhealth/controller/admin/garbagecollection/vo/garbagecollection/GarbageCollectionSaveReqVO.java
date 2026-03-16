package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection;


import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 收运计划新增/修改 Request VO")
@Data
@ExcelIgnoreUnannotated
public class GarbageCollectionSaveReqVO {

    @Schema(description = "主键ID", example = "15")
    private Long id;

    @Schema(description = "收运计划主键（UUID）", example = "uuid-collect-xxx")
    private String collectionId;

    @Schema(description = "收运计划单编号", example ="GC20240601xxx")
    private String planNo;

    @Schema(description = "关联sys_area.area_code", example ="1001")
    private String areaCode;

    @Schema(description = "关联sys_garbage_type.id", example = "uuid-garbage-001")
    private String garbageTypeId;

    @Schema(description = "收运频次")
    private String frequency;

    @Schema(description = "收运时段")
    private String timePeriod;

    @Schema(description = "关联sys_vehicle.id", example = "uuid-vehicle-001")
    private String vehicleId;

    @Schema(description = "负责人员IDs，JSON", example = "[\"uuid-user-001\",\"uuid-user-002\"]")
    @Pattern(regexp = "^$|^\\[.*\\]$", message = "staffIds必须为JSON数组格式（如[]）")
    private String staffIds;

    @Schema(description = "收运点位IDs，JSON", example = "[\"uuid-point-001\",\"uuid-point-002\"]")
    @Pattern(regexp = "^$|^\\[.*\\]$", message = "pointIds必须为JSON数组格式（如[]）")
    private String pointIds;

    @Schema(description = "关联sys_plan_status.id", example = "uuid-plan-status-001")
    private String planStatusId;

    @Schema(description = "完成率")
    private BigDecimal completionRate;

    @Schema(description = "异常记录数", example = "21604")
    private Integer abnormalCount;

    @Schema(description = "关联sys_user.id")
    private String createBy;

    @Schema(description = "当前进度（按点位完成率计算）", example = "85")
    private Integer progress;

    @Schema(description = "已收运量（实时上报累计）", example = "22.80")
    private BigDecimal collectedVolume;

    @Schema(description = "打卡状态：到岗/离岗", example = "到岗")
    private String checkinStatus;

    @Schema(description = "轨迹覆盖情况（系统自动校验）", example = "95%")
    private String trackCoverage;

    @Schema(description = "最新上报时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lastReportTime;

    @Schema(description = "是否异常（系统自动标记）", example = "true")
    private Boolean isAbnormal;

    @Schema(description = "完成时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime completeTime;

    @Schema(description = "总收运量", example = "100.50")
    private BigDecimal totalVolume;

    @Schema(description = "异常处置结果：无/已办结/部分办结", example = "已办结")
    private String abnormalResult;

    @Schema(description = "异常办结率（自动计算）", example = "98")
    private Integer abnormalCompleteRate;

    // setter方法保持不变
    public void setStaffIds(String staffIds) {
        this.staffIds = (staffIds == null || staffIds.trim().isEmpty()) ? "[]" : staffIds;
    }

    public void setPointIds(String pointIds) {
        this.pointIds = (pointIds == null || pointIds.trim().isEmpty()) ? "[]" : pointIds;
    }
}