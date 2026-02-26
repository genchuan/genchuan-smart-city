package cn.iocoder.yudao.module.envir.controller.admin.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 系统用户新增/修改 Request VO")
@Data
public class UserSaveReqVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9727")
    private Long id;

    @Schema(description = "业务主键（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "237")
    @NotEmpty(message = "业务主键（UUID）不能为空")
    private String userId;

    @Schema(description = "用户名", example = "芋艿")
    private String userName;

    @Schema(description = "联系电话")
    private String userPhone;

    @Schema(description = "所属部门名称", example = "芋艿")
    private String deptName;

    @Schema(description = "角色ID（关联角色表）", example = "8138")
    private String roleId;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "4141")
    private String statusId;

    @Schema(description = "创建人ID（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "更新人ID（关联sys_user.user_id）")
    private String updateBy;

    @Schema(description = "技能标签（JSON格式）")
    private String skillTags;

    @Schema(description = "作业轨迹ID（关联轨迹表）", example = "8887")
    private String workTrajectoryId;

    @Schema(description = "所属班组（关联sys_team.sys_team_id）", example = "12149")
    private String teamId;

    @Schema(description = "负责区域（关联sys_area.area_code）")
    private String areaCode;

    @Schema(description = "岗位类型（关联sys_job_type.sys_job_type_id）", example = "28015")
    private String jobTypeId;

    @Schema(description = "人员状态（关联sys_person_status.sys_person_status_id）", example = "30941")
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

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}