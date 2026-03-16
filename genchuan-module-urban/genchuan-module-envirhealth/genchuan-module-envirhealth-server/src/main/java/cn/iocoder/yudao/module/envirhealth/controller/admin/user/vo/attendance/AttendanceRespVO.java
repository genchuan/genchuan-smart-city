package cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendance;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 考勤 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AttendanceRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6737")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "主键（UUID）", example = "10646")
    @ExcelProperty("主键（UUID）")
    private String attendanceId;

    @Schema(description = "关联sys_user.id", example = "9418")
    @ExcelProperty("关联sys_user.id")
    private String userId;

    @Schema(description = "关联sys_job_type.id", example = "7110")
    @ExcelProperty("关联sys_job_type.id")
    private String jobTypeId;

    @Schema(description = "关联sys_team.id", example = "13308")
    @ExcelProperty("关联sys_team.id")
    private String teamId;

    @Schema(description = "打卡日期")
    @ExcelProperty("打卡日期")
    private LocalDateTime checkDate;

    @Schema(description = "到岗打卡时间")
    @ExcelProperty("到岗打卡时间")
    private LocalDateTime onDutyTime;

    @Schema(description = "离岗打卡时间")
    @ExcelProperty("离岗打卡时间")
    private LocalDateTime offDutyTime;

    @Schema(description = "关联sys_attendance_status.id", example = "21816")
    @ExcelProperty("关联sys_attendance_status.id")
    private String attendanceStatusId;

    @Schema(description = "打卡位置")
    @ExcelProperty("打卡位置")
    private String checkLocation;

    @Schema(description = "考勤时长（单位：小时）")
    @ExcelProperty("考勤时长（单位：小时）")
    private BigDecimal workHours;

    @Schema(description = "关联sys_attendance_abnormal_type.id", example = "23576")
    @ExcelProperty("关联sys_attendance_abnormal_type.id")
    private String abnormalTypeId;

    @Schema(description = "异常说明")
    @ExcelProperty("异常说明")
    private String abnormalDesc;

    @Schema(description = "佐证材料URL")
    @ExcelProperty("佐证材料URL")
    private String proofMaterial;

    @Schema(description = "关联sys_review_status.id", example = "317")
    @ExcelProperty("关联sys_review_status.id")
    private String reviewStatusId;

    @Schema(description = "补录理由", example = "不好")
    @ExcelProperty("补录理由")
    private String supplementReason;

    @Schema(description = "补录时间")
    @ExcelProperty("补录时间")
    private LocalDateTime supplementTime;

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