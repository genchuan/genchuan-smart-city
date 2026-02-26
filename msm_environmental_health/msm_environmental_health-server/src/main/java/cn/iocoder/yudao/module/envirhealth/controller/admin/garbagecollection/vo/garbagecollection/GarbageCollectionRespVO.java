package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 收运计划 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GarbageCollectionRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19421")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "收运计划主键（UUID）", example = "8255")
    @ExcelProperty("收运计划主键（UUID）")
    private String collectionId;

    @Schema(description = "收运计划单编号")
    @ExcelProperty("收运计划单编号")
    private String planNo;

    @Schema(description = "关联sys_area.area_code")
    @ExcelProperty("关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "关联sys_garbage_type.id", example = "16464")
    @ExcelProperty("关联sys_garbage_type.id")
    private String garbageTypeId;

    @Schema(description = "收运频次")
    @ExcelProperty("收运频次")
    private String frequency;

    @Schema(description = "收运时段")
    @ExcelProperty("收运时段")
    private String timePeriod;

    @Schema(description = "关联sys_vehicle.id", example = "28972")
    @ExcelProperty("关联sys_vehicle.id")
    private String vehicleId;

    @Schema(description = "负责人员IDs，JSON")
    @ExcelProperty("负责人员IDs，JSON")
    private String staffIds;

    @Schema(description = "收运点位IDs，JSON")
    @ExcelProperty("收运点位IDs，JSON")
    private String pointIds;

    @Schema(description = "关联sys_plan_status.id", example = "26053")
    @ExcelProperty("关联sys_plan_status.id")
    private String planStatusId;

    @Schema(description = "完成率")
    @ExcelProperty("完成率")
    private BigDecimal completionRate;

    @Schema(description = "异常记录数", example = "21604")
    @ExcelProperty("异常记录数")
    private Integer abnormalCount;

    @Schema(description = "收运计划创建时间（原create_time）")
    @ExcelProperty("收运计划创建时间（原create_time）")
    private LocalDateTime abnormalCreateTime;

    @Schema(description = "收运计划更新时间（原update_time）")
    @ExcelProperty("收运计划更新时间（原update_time）")
    private LocalDateTime abnormalUpdateTime;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("关联sys_user.id")
    private String createBy;

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

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTime;

    @Schema(description = "当前进度（按点位完成率计算）", example = "85.50")
    @ExcelProperty("当前进度（按点位完成率计算）")
    private BigDecimal progress;

    @Schema(description = "已收运量（实时上报累计）", example = "22.80")
    @ExcelProperty("已收运量（实时上报累计）")
    private BigDecimal collectedVolume;

    @Schema(description = "打卡状态：到岗/离岗", example = "到岗")
    @ExcelProperty("打卡状态")
    private String checkinStatus;

    @Schema(description = "轨迹覆盖情况（系统自动校验）", example = "95%")
    @ExcelProperty("轨迹覆盖情况")
    private String trackCoverage;

    @Schema(description = "最新上报时间")
    @ExcelProperty("最新上报时间")
    private LocalDateTime[] lastReportTime;

    @Schema(description = "是否异常（系统自动标记）", example = "true")
    @ExcelProperty("是否异常")
    private Boolean isAbnormal;

    @Schema(description = "完成时间")
    @ExcelProperty("完成时间")
    private LocalDateTime completeTime;

    @Schema(description = "总收运量", example = "100.50")
    @ExcelProperty("总收运量")
    private BigDecimal totalVolume;

    @Schema(description = "异常处置结果：无/已办结/部分办结", example = "已办结")
    @ExcelProperty("异常处置结果")
    private String abnormalResult;

    @Schema(description = "异常办结率（自动计算）", example = "98.00")
    @ExcelProperty("异常办结率")
    private BigDecimal abnormalCompleteRate;
}