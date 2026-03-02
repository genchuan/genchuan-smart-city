package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.alibaba.excel.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 收运计划 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GarbageCollectionRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED,example = "19427")
    @ExcelProperty("id")
    private Long id;

    @Schema(description = "收运计划主键（UUID）", example = "uuid-collect-xxx")
    @ExcelProperty("collection_id")
    private String collectionId;

    @Schema(description = "收运计划单编号", example ="GC20240601xxx")
    @ExcelProperty("plan_no")
    private String planNo;

    @Schema(description = "关联sys_area.area_code", example ="1001")
    @ExcelProperty("area_code")
    private String areaCode;

    @Schema(description = "关联sys_garbage_type.id", example = "uuid-garbage-001")
    @ExcelProperty("garbage_type_id")
    private String garbageTypeId;

    @Schema(description = "收运频次")
    @ExcelProperty("frequency")
    private String frequency;

    @Schema(description = "收运时段")
    @ExcelProperty("time_period")
    private String timePeriod;

    @Schema(description = "关联sys_vehicle.id", example = "uuid-vehicle-001")
    @ExcelProperty("vehicle_id")
    private String vehicleId;

    @Schema(description = "负责人员IDs，JSON", example = "[\"uuid-user-001\", \"uuid-user-002\"]")
    @ExcelProperty("staff_ids")
    private String staffIds;

    @Schema(description = "收运点位IDs，JSON", example = "[\"uuid-point-001\", \"uuid-point-002\"]")
    @ExcelProperty("point_ids")
    private String pointIds;

    @Schema(description = "关联sys_plan_status.id", example = "uuid-plan-status-001")
    @ExcelProperty("plan_status_id")
    private String planStatusId;

    @Schema(description = "完成率")
    @ExcelProperty("completion_rate")
    private BigDecimal completionRate;

    @Schema(description = "异常记录数", example = "21604")
    @ExcelProperty("abnormal_count")
    private Integer abnormalCount;

    @Schema(description = "收运计划创建时间（原create_time）")
    @ExcelProperty("abnormal_create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime abnormalCreateTime;

    @Schema(description = "收运计划更新时间（原update_time）")
    @ExcelProperty("abnormal_update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime abnormalUpdateTime;

    @Schema(description = "创建人（关联sys_user.id）", example = "uuid-user-001")
    @ExcelProperty("create_by")
    private String createBy;

    @JsonIgnore
    @Schema(description = "通用扩展字段1")
    @ExcelProperty("ext_common1")
    private BigDecimal extCommon1;

    @JsonIgnore
    @Schema(description = "通用扩展字段2")
    @ExcelProperty("ext_common2")
    private BigDecimal extCommon2;

    @JsonIgnore
    @Schema(description = "通用扩展字段3")
    @ExcelProperty("ext_common3")
    private BigDecimal extCommon3;

    @JsonIgnore
    @Schema(description = "通用扩展字段4")
    @ExcelProperty("ext_common4")
    private BigDecimal extCommon4;

    @Schema(description = "系统创建时间")
    @ExcelProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @Schema(description = "当前进度（按点位完成率计算）", example = "75")
    @ExcelProperty("progress")
    private Integer progress;

    @Schema(description = "已收运量（实时上报累计）", example = "19.8")
    @ExcelProperty("collected_quantity")
    private BigDecimal collectedQuantity;

    @Schema(description = "打卡状态", example = "到岗")
    @ExcelProperty("check_in_status")
    private String checkInStatus;

    @Schema(description = "轨迹覆盖情况", example = "90%")
    @ExcelProperty("track_coverage")
    private String trackCoverage;

    @Schema(description = "最新上报时间")
    @ExcelProperty("latest_report_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime latestReportTime;

    @Schema(description = "是否异常", example = "True")
    @ExcelProperty("is_abnormal")
    private Boolean isAbnormal;

    @Schema(description = "完成时间")
    @ExcelProperty("complete_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime completeTime;

    @Schema(description = "总收运量", example = "26.5")
    @ExcelProperty("total_collected_quantity")
    private BigDecimal totalCollectedQuantity;

    @Schema(description = "异常处置结果", example = "部分办结")
    @ExcelProperty("abnormal_disposal_result")
    private String abnormalDisposalResult;

    @Schema(description = "异常办结率", example = "50")
    @ExcelProperty("abnormal_completion_rate")
    private Integer abnormalCompletionRate;
}