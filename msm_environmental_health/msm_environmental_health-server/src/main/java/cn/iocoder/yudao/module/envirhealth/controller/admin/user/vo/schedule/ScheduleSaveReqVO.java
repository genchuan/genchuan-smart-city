package cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.schedule;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 排班计划新增/修改 Request VO")
@Data
public class ScheduleSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17499")
    private Long id;

    @Schema(description = "主键（UUID）", example = "12824")
    private String scheduleId;

    @Schema(description = "关联sys_user.id", example = "15771")
    private String userId;

    @Schema(description = "关联sys_job_type.id", example = "6339")
    private String jobTypeId;

    @Schema(description = "关联sys_team.id", example = "22729")
    private String teamId;

    @Schema(description = "关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "排班周期：日/周/月")
    private String cycle;

    @Schema(description = "作业时段")
    private String workTimePeriod;

    @Schema(description = "关联sys_schedule_status.id", example = "29252")
    private String scheduleStatusId;

    @Schema(description = "换班申请状态：无/待审核/已通过/已拒绝", example = "1")
    private String swapStatus;

    @Schema(description = "换班申请人ID，关联sys_user.id", example = "21280")
    private String swapApplicantId;

    @Schema(description = "被换班人员ID，关联sys_user.id", example = "13966")
    private String swapTargetId;

    @Schema(description = "换班日期")
    private LocalDateTime swapDate;

    @Schema(description = "换班理由", example = "不喜欢")
    private String swapReason;

    @Schema(description = "审核结果：通过/拒绝")
    private String reviewResult;

    @Schema(description = "审核意见")
    private String reviewOpinion;

    @Schema(description = "排班覆盖率")
    private BigDecimal coverageRate;

    @Schema(description = "岗位空缺提醒：是/否")
    private String vacancyReminder;

    @Schema(description = "换班申请数", example = "9095")
    private Integer swapApplyCount;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "关联sys_user.id")
    private String createBy;

}