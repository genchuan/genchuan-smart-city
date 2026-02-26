package cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.roadcleaning;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 道路清扫计划 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RoadCleaningRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23766")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "清扫计划主键（UUID）", example = "22156")
    @ExcelProperty("清扫计划主键（UUID）")
    private String cleaningId;

    @Schema(description = "清扫计划编号")
    @ExcelProperty("清扫计划编号")
    private String planNo;

    @Schema(description = "关联sys_road.id", example = "9886")
    @ExcelProperty("关联sys_road.id")
    private String roadId;

    @Schema(description = "关联sys_area.area_code")
    @ExcelProperty("关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "清扫频次")
    @ExcelProperty("清扫频次")
    private String frequency;

    @Schema(description = "清扫时段")
    @ExcelProperty("清扫时段")
    private String timePeriod;

    @Schema(description = "负责人员IDs，JSON")
    @ExcelProperty("负责人员IDs，JSON")
    private String staffIds;

    @Schema(description = "关联sys_plan_status.id", example = "27279")
    @ExcelProperty("关联sys_plan_status.id")
    private String planStatusId;

    @Schema(description = "质量达标率")
    @ExcelProperty("质量达标率")
    private BigDecimal qualityRate;

    @Schema(description = "问题处置数", example = "8204")
    @ExcelProperty("问题处置数")
    private Integer problemCount;

    @Schema(description = "考勤全勤率")
    @ExcelProperty("考勤全勤率")
    private BigDecimal attendanceRate;

    @Schema(description = "清扫工具IDs，JSON")
    @ExcelProperty("清扫工具IDs，JSON")
    private String toolIds;

    @Schema(description = "清扫标准")
    @ExcelProperty("清扫标准")
    private String standard;

    @Schema(description = "到岗时间")
    @ExcelProperty("到岗时间")
    private LocalDateTime checkinTime;

    @Schema(description = "当前进度")
    @ExcelProperty("当前进度")
    private String progress;

    @Schema(description = "作业状态：运行/暂停/异常", example = "1")
    @ExcelProperty("作业状态：运行/暂停/异常")
    private String operationStatus;

    @Schema(description = "轨迹覆盖情况：合规/偏离")
    @ExcelProperty("轨迹覆盖情况：合规/偏离")
    private String trackCoverage;

    @Schema(description = "最新上报时间")
    @ExcelProperty("最新上报时间")
    private LocalDateTime lastReportTime;

    @Schema(description = "是否异常：是/否")
    @ExcelProperty("是否异常：是/否")
    private String isAbnormal;

    @Schema(description = "作业完成时间")
    @ExcelProperty("作业完成时间")
    private LocalDateTime completeTime;

    @Schema(description = "上报照片URL，JSON", example = "https://www.iocoder.cn")
    @ExcelProperty("上报照片URL，JSON")
    private String checkPhotoUrl;

    @Schema(description = "核查状态：待核查/达标/不达标", example = "2")
    @ExcelProperty("核查状态：待核查/达标/不达标")
    private String reviewStatus;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("关联sys_user.id")
    private String reviewBy;

    @Schema(description = "核查时间")
    @ExcelProperty("核查时间")
    private LocalDateTime reviewTime;

    @Schema(description = "整改要求")
    @ExcelProperty("整改要求")
    private String reformRequire;

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

    @Schema(description = "是否有效：是/否")
    @ExcelProperty("是否有效：是/否")
    private String isEffective;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}