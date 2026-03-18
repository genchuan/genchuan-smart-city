package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection;

import cn.idev.excel.annotation.ExcelIgnore;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.envirhealth.util.convert.LocalDateTimeConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 收运计划 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GarbageCollectionRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED,example = "19427")
    @ExcelIgnore
    private Long id;

    @Schema(description = "收运计划主键（UUID）", example = "uuid-collect-xxx")
    @ExcelProperty("收运计划主键")
    private String collectionId;

    @Schema(description = "收运计划单编号", example ="GC20240601xxx")
    @ExcelProperty("收运计划单编号")
    private String planNo;

    @Schema(description = "关联sys_area.area_code", example ="1001")
    @ExcelProperty("区域编码")
    private String areaCode;

    @Schema(description = "关联sys_garbage_type.id", example = "uuid-garbage-001")
    @ExcelProperty("垃圾类型编码")
    private String garbageTypeId;

    @Schema(description = "收运频次")
    @ExcelProperty("收运频次")
    private String frequency;

    @Schema(description = "收运时段")
    @ExcelProperty("收运时段")
    private String timePeriod;

    @Schema(description = "关联sys_vehicle.id", example = "uuid-vehicle-001")
    @ExcelProperty("车牌号")
    private String vehicleId;

    @Schema(description = "负责人员IDs，JSON", example = "[\"uuid-user-001\", \"uuid-user-002\"]")
    @ExcelProperty("负责人员")
    private String staffIds;

    @Schema(description = "收运点位IDs，JSON", example = "[\"uuid-point-001\", \"uuid-point-002\"]")
    @ExcelProperty("收运点位")
    private String pointIds;

    @Schema(description = "关联sys_plan_status.id", example = "uuid-plan-status-001")
    @ExcelProperty("计划状态编码")
    private String planStatusId;

    @Schema(description = "完成率")
    @ExcelProperty("完成率")
    private BigDecimal completionRate;

    @Schema(description = "异常记录数", example = "21604")
    @ExcelProperty("异常记录数")
    private Integer abnormalCount;

    @Schema(description = "创建人（关联sys_user.id）", example = "uuid-user-001")
    @ExcelProperty("创建人")
    private String createBy;

    @Schema(description = "系统创建时间")
    @ExcelIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @Schema(description = "当前进度（按点位完成率计算）", example = "75")
    @ExcelProperty("当前进度")
    private Integer progress;

    @Schema(description = "已收运量（实时上报累计）", example = "19.8")
    @ExcelProperty("已收运量")
    private BigDecimal collectedVolume;

    @Schema(description = "打卡状态", example = "到岗")
    @ExcelProperty("打卡状态")
    private String checkinStatus;

    @Schema(description = "轨迹覆盖情况", example = "90%")
    @ExcelProperty("轨迹覆盖情况")
    private String trackCoverage;

    @Schema(description = "最新上报时间")
    @ExcelProperty(value = "最新上报时间", converter = LocalDateTimeConverter.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lastReportTime;

    @Schema(description = "是否异常", example = "True")
    @ExcelProperty("异常")
    private Boolean isAbnormal;

    @Schema(description = "完成时间")
    @ExcelProperty(value = "完成时间", converter = LocalDateTimeConverter.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime completeTime;

    @Schema(description = "总收运量", example = "26.5")
    @ExcelProperty("总收运量")
    private BigDecimal totalVolume;

    @Schema(description = "异常处置结果", example = "部分办结")
    @ExcelProperty("异常处置结果")
    private String abnormalResult;

    @Schema(description = "异常办结率", example = "50")
    @ExcelProperty("异常办结率")
    private Integer abnormalCompleteRate;

    @Schema(description = "复盘意见", example = "清扫十分干净")
    @ExcelProperty("复盘意见")
    private String reviewDesc;
}