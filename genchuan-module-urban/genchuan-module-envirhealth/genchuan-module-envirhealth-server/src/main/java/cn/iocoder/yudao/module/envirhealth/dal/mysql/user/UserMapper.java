package cn.iocoder.yudao.module.envirhealth.dal.mysql.user;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.user.UserPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.UserDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.UserDetailDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 系统用户 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface UserMapper extends BaseMapperX<UserDO> {

    default PageResult<UserDO> selectPage(UserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserDO>()
                .eqIfPresent(UserDO::getUserId, reqVO.getUserId())
                .likeIfPresent(UserDO::getUserName, reqVO.getUserName())
                .eqIfPresent(UserDO::getUserPhone, reqVO.getUserPhone())
                .likeIfPresent(UserDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(UserDO::getRoleId, reqVO.getRoleId())
                .eqIfPresent(UserDO::getStatusId, reqVO.getStatusId())
                .eqIfPresent(UserDO::getCreateBy, reqVO.getCreateBy())
                .eqIfPresent(UserDO::getUpdateBy, reqVO.getUpdateBy())
                .eqIfPresent(UserDO::getSkillTags, reqVO.getSkillTags())
                .eqIfPresent(UserDO::getWorkTrajectoryId, reqVO.getWorkTrajectoryId())
                .eqIfPresent(UserDO::getTeamId, reqVO.getTeamId())
                .eqIfPresent(UserDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(UserDO::getJobTypeId, reqVO.getJobTypeId())
                .eqIfPresent(UserDO::getPersonStatusId, reqVO.getPersonStatusId())
                .eqIfPresent(UserDO::getPhone, reqVO.getPhone())
                .betweenIfPresent(UserDO::getEntryTime, reqVO.getEntryTime())
                .eqIfPresent(UserDO::getTotalAttendanceDays, reqVO.getTotalAttendanceDays())
                .eqIfPresent(UserDO::getAverageScore, reqVO.getAverageScore())
                .eqIfPresent(UserDO::getLastWorkTrace, reqVO.getLastWorkTrace())
                .betweenIfPresent(UserDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UserDO::getId));
    }

    /**
     * 查询全局最大序号（用于user_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(user_id, '-', -1)), 0) FROM sys_user")
    Integer selectMaxSeq();

    List<UserDetailDO> selectDetailPage(@Param("reqVO") UserPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") UserPageReqVO pageReqVO);

    // ========== 卡片数据 ==========

    /**
     * 总人数
     */
    @Select("SELECT COUNT(*) FROM sys_user WHERE deleted = 0")
    Long selectTotalUserCount();

    /**
     * 在岗人数
     */
    @Select("SELECT COUNT(*) " +
            "FROM sys_user u " +
            " LEFT JOIN sys_person_status s ON u.person_status_id = s.person_status_id " +
            "WHERE u.deleted = 0 AND s.person_status_id = 'uuid-pstatus-001'")
    Long selectOnDutyCount();

    @Select("SELECT COUNT(*) FROM sys_user u " +
            "WHERE u.deleted = 0 AND u.month_full_attendance = 1 ")
    Long selectFullAttendanceCount();

    @Select({
            "SELECT COUNT(DISTINCT a.user_id) "+
            "FROM sys_assessment a "+
            "JOIN sys_assessment_grade g ON a.assessment_grade_id = g.assessment_grade_id "+
            "WHERE g.assessment_grade_id = 'uuid-agrade-001' "+
            "  AND a.create_time BETWEEN DATE_FORMAT(CURDATE(), '%Y-%m-01') AND LAST_DAY(CURDATE())"+
            "  AND a.deleted = 0"
    })
    Long selectExcellentAssessmentCount();

    @Select({
            "SELECT COUNT(DISTINCT u.user_id) AS wait_schedule_user_count "+
            "FROM sys_user u "+
            "JOIN sys_schedule s ON u.user_id = s.user_id "+
            "JOIN sys_schedule_status ss ON s.schedule_status_id = ss.schedule_status_id "+
            "WHERE u.deleted = 0 "+
            "  AND s.deleted = 0" +
            "  AND ss.schedule_status_id = 'uuid-schstatus-001'"
    })
    Long selectPendingScheduleCount();

    // ========== 圆环图数据 ==========
    @Select("SELECT " +
            "CASE " +
            "   WHEN r.name IS NULL THEN '未知' " +
            "   ELSE r.name " +
            "END as name, " +
            "COUNT(*) as value " +
            "FROM sys_user u " +
            "LEFT JOIN sys_job_type r ON u.job_type_id = r.job_type_id " +
            "WHERE u.deleted = 0 " +
            "GROUP BY " +
            "CASE " +
            "   WHEN r.name IS NULL THEN '未知' " +
            "   ELSE r.name " +
            "END")
    List<PieItemVO> selectPositionTypeDistribution();

    @Select("SELECT " +
            "COALESCE(s.name, '未知') as name, " +
            "COUNT(*) as value " +
            "FROM sys_user u " +
            "LEFT JOIN sys_person_status s ON u.person_status_id = s.person_status_id " +
            "WHERE u.deleted = 0 " +
            "GROUP BY COALESCE(s.name, '未知')")  // 按状态名称分组
    List<PieItemVO> selectUserStatusDistribution();

    @Select("SELECT " +
            "CASE " +
            "   WHEN t.name IS NULL THEN '未知' " +
            "   ELSE t.name " +
            "END as name, " +
            "COUNT(*) as value " +
            "FROM sys_user u " +
            "LEFT JOIN sys_team t ON u.team_id = t.sys_team_id " +
            "WHERE u.deleted = 0 " +
            "GROUP BY " +
            "CASE " +
            "   WHEN t.name IS NULL THEN '未知' " +
            "   ELSE t.name " +
            "END")
    List<PieItemVO> selectTeamDistribution();

    // ========== 柱状图数据 ==========
    @Select("SELECT " +
            "CASE " +
            "   WHEN t.name IS NULL THEN '未知' " +
            "   ELSE t.name " +
            "END as name, " +
            "COUNT(*) as value " +
            "FROM sys_user u " +
            "LEFT JOIN sys_team t ON u.team_id = t.sys_team_id " +
            "WHERE u.deleted = 0 " +
            "GROUP BY " +
            "CASE " +
            "   WHEN t.name IS NULL THEN '未知' " +
            "   ELSE t.name " +
            "END " +
            "ORDER BY value DESC")
    List<BarItemVO> selectUserCountByTeam();

    /**
     * 查询本月不同岗位平均考核得分（按创建时间筛选）
     * @return 结果列表：key为"岗位名称"、"最终平均分"，value为对应值
     */
    @Select("""
        SELECT
            jt.name AS name,
            ROUND(AVG(sa.final_total_score), 2) AS value
        FROM
            sys_assessment sa
        LEFT JOIN
            sys_job_type jt 
            ON TRIM(sa.job_type_id) = TRIM(jt.job_type_id)
        WHERE
            sa.deleted = '0'
            AND jt.deleted = 0
            -- 按创建时间筛选本月数据
            AND sa.create_time >= DATE_FORMAT(CURDATE(), '%Y-%m-01')
            AND sa.create_time < DATE_FORMAT(CURDATE(), '%Y-%m-01') + INTERVAL 1 month
        GROUP BY
            jt.name
        ORDER BY
            value DESC
    """)
    List<BarItemVO> selectAvgAssessmentScoreByPosition();
}