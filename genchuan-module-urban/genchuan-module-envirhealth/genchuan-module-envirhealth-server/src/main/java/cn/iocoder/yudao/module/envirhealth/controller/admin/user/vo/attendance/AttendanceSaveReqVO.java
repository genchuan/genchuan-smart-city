package cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendance;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 考勤新增/修改 Request VO")
@Data
public class AttendanceSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6737")
    private Long id;

    @Schema(description = "主键（UUID）", example = "10646")
    private String attendanceId;

    @Schema(description = "关联sys_user.id", example = "9418")
    private String userId;

    @Schema(description = "关联sys_job_type.id", example = "7110")
    private String jobTypeId;

    @Schema(description = "关联sys_team.id", example = "13308")
    private String teamId;

    @Schema(description = "打卡日期")
    private LocalDateTime checkDate;

    @Schema(description = "到岗打卡时间")
    private LocalDateTime onDutyTime;

    @Schema(description = "离岗打卡时间")
    private LocalDateTime offDutyTime;

    @Schema(description = "关联sys_attendance_status.id", example = "21816")
    private String attendanceStatusId;

    @Schema(description = "打卡位置")
    private String checkLocation;

    @Schema(description = "考勤时长（单位：小时）")
    private BigDecimal workHours;

    @Schema(description = "关联sys_attendance_abnormal_type.id", example = "23576")
    private String abnormalTypeId;

    @Schema(description = "异常说明")
    private String abnormalDesc;

    @Schema(description = "佐证材料URL")
    private String proofMaterial;

    @Schema(description = "关联sys_review_status.id", example = "317")
    private String reviewStatusId;

    @Schema(description = "补录理由", example = "不好")
    private String supplementReason;

    @Schema(description = "补录时间")
    private LocalDateTime supplementTime;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}