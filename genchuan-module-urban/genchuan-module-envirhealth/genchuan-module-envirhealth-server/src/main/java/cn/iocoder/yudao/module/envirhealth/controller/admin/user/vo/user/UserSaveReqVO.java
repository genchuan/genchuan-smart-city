package cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.user;

import cn.iocoder.yudao.module.envirhealth.framework.util.json.JsonFieldUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 系统用户新增/修改 Request VO")
@Data
public class UserSaveReqVO {

    @Schema(description = "自增主键ID")
    private Long id;

    @Schema(description = "业务主键（UUID）")
    @NotEmpty(message = "业务主键（UUID）不能为空")
    private String userId;

    @Schema(description = "用户名", example = "李四")
    private String userName;

    @Schema(description = "联系电话")
    private String userPhone;

    @Schema(description = "所属部门编号", example = "uuid-dept-001")
    private String deptId;

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
    private LocalDateTime entryTime;

    @Schema(description = "累计考勤天数")
    private Integer totalAttendanceDays;

    @Schema(description = "平均考核得分（保留2位小数）")
    private BigDecimal averageScore;

    @Schema(description = "最近作业轨迹（JSON格式）")
    private String lastWorkTrace;

    public void setSkillTags(String skillTags) {
        this.skillTags = JsonFieldUtils.emptyToEmptyArray(skillTags);
    }

    public void setLastWorkTrace(String lastWorkTrace) {
        this.lastWorkTrace = JsonFieldUtils.emptyToEmptyArray(lastWorkTrace);
    }

    public void setWorkTrajectoryId(String workTrajectoryId) {
        this.workTrajectoryId = JsonFieldUtils.emptyToEmptyArray(workTrajectoryId);
    }
}