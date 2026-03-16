package cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.roadcleaning;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 道路清扫计划新增/修改 Request VO")
@Data
public class RoadCleaningSaveReqVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "清扫计划主键（UUID）", example = "22156")
    private String cleaningId;

    @Schema(description = "清扫计划编号")
    private String planNo;

    @Schema(description = "关联sys_road.id", example = "9886")
    private String roadId;

    @Schema(description = "关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "清扫频次")
    private String frequency;

    @Schema(description = "清扫时段")
    private String timePeriod;

    @Schema(description = "负责人员IDs，JSON")
    private String staffIds;

    @Schema(description = "关联sys_plan_status.id", example = "27279")
    private String planStatusId;

    @Schema(description = "质量达标率")
    private BigDecimal qualityRate;

    @Schema(description = "问题处置数", example = "8204")
    private Integer problemCount;

    @Schema(description = "考勤全勤率")
    private BigDecimal attendanceRate;

    @Schema(description = "清扫工具IDs，JSON")
    private String toolIds;

    @Schema(description = "清扫标准")
    private String standard;

    @Schema(description = "到岗时间")
    private LocalDateTime checkinTime;

    @Schema(description = "当前进度")
    private String progress;

    @Schema(description = "作业状态：运行/暂停/异常", example = "1")
    private String operationStatus;

    @Schema(description = "轨迹覆盖情况：合规/偏离")
    private String trackCoverage;

    @Schema(description = "最新上报时间")
    private LocalDateTime lastReportTime;

    @Schema(description = "是否异常：是/否")
    private String isAbnormal;

    @Schema(description = "作业完成时间")
    private LocalDateTime completeTime;

    @Schema(description = "上报照片URL列表，JSON数组", example = "[\"https://www.iocoder.cn/1.jpg\", \"https://www.iocoder.cn/2.jpg\"]")
    private String checkPhotoUrl;

    @Schema(description = "核查状态：待核查/达标/不达标", example = "2")
    private String reviewStatus;

    @Schema(description = "关联sys_user.id")
    private String reviewBy;

    @Schema(description = "核查时间")
    private LocalDateTime reviewTime;

    @Schema(description = "整改要求")
    private String reformRequire;

    @Schema(description = "是否有效：是/否")
    private String isEffective;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}