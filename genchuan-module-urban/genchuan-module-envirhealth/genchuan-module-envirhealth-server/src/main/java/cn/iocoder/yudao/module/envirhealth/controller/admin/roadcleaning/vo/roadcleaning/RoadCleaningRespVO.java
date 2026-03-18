package cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.roadcleaning;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 道路清扫计划 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RoadCleaningRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "清扫计划主键（UUID）", example = "22156")
    @ExcelProperty("清扫计划主键")
    private String cleaningId;

    @Schema(description = "清扫计划编号")
    @ExcelProperty("清扫计划编号")
    private String planNo;

    @Schema(description = "关联sys_road.id", example = "9886")
    @ExcelProperty("道路编码")
    private String roadId;

    @Schema(description = "关联sys_area.area_code")
    @ExcelProperty("区域编码")
    private String areaCode;

    @Schema(description = "清扫频次")
    @ExcelProperty("清扫频次")
    private String frequency;

    @Schema(description = "清扫时段")
    @ExcelProperty("清扫时段")
    private String timePeriod;

    @Schema(description = "负责人员IDs，JSON")
    @ExcelProperty("负责人员")
    private String staffIds;

    @Schema(description = "关联sys_plan_status.id", example = "27279")
    @ExcelProperty("计划状态编码")
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
    @ExcelProperty("清扫工具")
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
    @ExcelProperty("作业状态")
    private String operationStatus;

    @Schema(description = "轨迹覆盖情况：合规/偏离")
    @ExcelProperty("轨迹覆盖情况")
    private String trackCoverage;

    @Schema(description = "最新上报时间")
    @ExcelProperty("最新上报时间")
    private LocalDateTime lastReportTime;

    @Schema(description = "是否异常：是/否")
    @ExcelProperty("异常")
    private String isAbnormal;

    @Schema(description = "作业完成时间")
    @ExcelProperty("作业完成时间")
    private LocalDateTime completeTime;

    @Schema(description = "上报照片URL，JSON", example = "https://www.iocoder.cn")
    @ExcelProperty("上报照片")
    private String checkPhotoUrl;

    @Schema(description = "核查状态：待核查/达标/不达标", example = "2")
    @ExcelProperty("核查状态")
    private String reviewStatus;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("审查人员")
    private String reviewBy;

    @Schema(description = "核查时间")
    @ExcelProperty("核查时间")
    private LocalDateTime reviewTime;

    @Schema(description = "整改要求")
    @ExcelProperty("整改要求")
    private String reformRequire;

    @Schema(description = "是否有效：是/否")
    @ExcelProperty("是否有效")
    private String isEffective;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "复盘意见")
    @ExcelProperty("复盘意见")
    private String reviewDesc;

}