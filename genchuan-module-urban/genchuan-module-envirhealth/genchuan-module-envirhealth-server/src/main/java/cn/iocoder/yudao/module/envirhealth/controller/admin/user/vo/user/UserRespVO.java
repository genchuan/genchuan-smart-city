package cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.user;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 系统用户 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UserRespVO {

    @Schema(description = "自增主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "业务主键（UUID）")
    @ExcelProperty("业务主键")
    private String userId;

    @Schema(description = "用户名", example = "李四")
    @ExcelProperty("用户名")
    private String userName;

    @Schema(description = "联系电话")
    @ExcelProperty("联系电话")
    private String userPhone;

    @Schema(description = "所属部门编号", example = "uuid-dept-001")
    @ExcelProperty("所属部门编号")
    private String deptId;

    @Schema(description = "角色ID（关联角色表）", example = "10816")
    @ExcelProperty("角色ID")
    private String roleId;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "23528")
    @ExcelProperty("状态ID")
    private String statusId;

    @Schema(description = "创建人ID（关联sys_user.user_id）")
    @ExcelProperty("创建人ID")
    private String createBy;

    @Schema(description = "更新人ID（关联sys_user.user_id）")
    @ExcelProperty("更新人ID")
    private String updateBy;

    @Schema(description = "技能标签（JSON格式）")
    @ExcelProperty("技能标签")
    private String skillTags;

    @Schema(description = "作业轨迹ID（关联轨迹表）", example = "1238")
    @ExcelProperty("作业轨迹ID")
    private String workTrajectoryId;

    @Schema(description = "所属班组（关联sys_team.sys_team_id）", example = "17040")
    @ExcelProperty("所属班组")
    private String teamId;

    @Schema(description = "负责区域（关联sys_area.area_code）")
    @ExcelProperty("负责区域")
    private String areaCode;

    @Schema(description = "岗位类型（关联sys_job_type.sys_job_type_id）", example = "27643")
    @ExcelProperty("岗位类型")
    private String jobTypeId;

    @Schema(description = "人员状态（关联sys_person_status.sys_person_status_id）", example = "23833")
    @ExcelProperty("人员状态")
    private String personStatusId;

    @Schema(description = "联系方式（补充备用电话字段）")
    @ExcelProperty("联系方式")
    private String phone;

    @Schema(description = "入职时间")
    @ExcelProperty("入职时间")
    private LocalDateTime entryTime;

    @Schema(description = "累计考勤天数")
    @ExcelProperty("累计考勤天数")
    private Integer totalAttendanceDays;

    @Schema(description = "平均考核得分（保留2位小数）")
    @ExcelProperty("平均考核得分")
    private BigDecimal averageScore;

    @Schema(description = "最近作业轨迹（JSON格式）")
    @ExcelProperty("最近作业轨迹")
    private String lastWorkTrace;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}