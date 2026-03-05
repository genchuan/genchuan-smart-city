package cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.user;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 系统用户分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserPageReqVO extends PageParam {

    @Schema(description = "业务主键（UUID）", example = "30453")
    private String userId;

    @Schema(description = "用户名", example = "李四")
    private String userName;

    @Schema(description = "联系电话")
    private String userPhone;

    @Schema(description = "所属部门名称", example = "李四")
    private String deptName;

    @Schema(description = "角色ID（关联角色表）", example = "10816")
    private String roleId;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "23528")
    private String statusId;

    @Schema(description = "创建人ID（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "更新人ID（关联sys_user.user_id）")
    private String updateBy;

    @Schema(description = "技能标签（JSON格式）")
    private String skillTags;

    @Schema(description = "作业轨迹ID（关联轨迹表）", example = "1238")
    private String workTrajectoryId;

    @Schema(description = "所属班组（关联sys_team.sys_team_id）", example = "17040")
    private String teamId;

    @Schema(description = "负责区域（关联sys_area.area_code）")
    private String areaCode;

    @Schema(description = "岗位类型（关联sys_job_type.sys_job_type_id）", example = "27643")
    private String jobTypeId;

    @Schema(description = "人员状态（关联sys_person_status.sys_person_status_id）", example = "23833")
    private String personStatusId;

    @Schema(description = "联系方式（补充备用电话字段）")
    private String phone;

    @Schema(description = "入职时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] entryTime;

    @Schema(description = "累计考勤天数")
    private Integer totalAttendanceDays;

    @Schema(description = "平均考核得分（保留2位小数）")
    private BigDecimal averageScore;

    @Schema(description = "最近作业轨迹（JSON格式）")
    private String lastWorkTrace;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}