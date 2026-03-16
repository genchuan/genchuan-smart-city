package cn.iocoder.yudao.module.envirhealth.dal.dataobject.user;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 系统用户 DO
 *
 * @author 芋道源码
 */
@TableName("sys_user")
@KeySequence("sys_user_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDO extends BaseDO {

    /**
     * 自增主键ID
     */
    @TableId
    private Long id;
    /**
     * 业务主键（UUID）
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 联系电话
     */
    private String userPhone;
    /**
     * 所属部门名称
     */
    private String deptName;
    /**
     * 角色ID（关联角色表）
     */
    private String roleId;
    /**
     * 状态ID（关联sys_status.status_id）
     */
    private String statusId;
    /**
     * 创建人ID（关联sys_user.user_id）
     */
    private String createBy;
    /**
     * 更新人ID（关联sys_user.user_id）
     */
    private String updateBy;
    /**
     * 技能标签（JSON格式）
     */
    private String skillTags;
    /**
     * 作业轨迹ID（关联轨迹表）
     */
    private String workTrajectoryId;
    /**
     * 所属班组（关联sys_team.sys_team_id）
     */
    private String teamId;
    /**
     * 负责区域（关联sys_area.area_code）
     */
    private String areaCode;
    /**
     * 岗位类型（关联sys_job_type.sys_job_type_id）
     */
    private String jobTypeId;
    /**
     * 人员状态（关联sys_person_status.sys_person_status_id）
     */
    private String personStatusId;
    /**
     * 联系方式（补充备用电话字段）
     */
    private String phone;
    /**
     * 入职时间
     */
    private LocalDateTime entryTime;
    /**
     * 累计考勤天数
     */
    private Integer totalAttendanceDays;
    /**
     * 平均考核得分（保留2位小数）
     */
    private BigDecimal averageScore;
    /**
     * 最近作业轨迹（JSON格式）
     */
    private String lastWorkTrace;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;

}