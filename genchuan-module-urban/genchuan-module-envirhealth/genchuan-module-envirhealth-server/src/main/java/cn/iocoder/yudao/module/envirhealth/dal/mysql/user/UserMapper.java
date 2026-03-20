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
            " LEFT JOIN sys_person_status s ON u.person_status_id = s.sys_person_status_id " +
            "WHERE u.deleted = 0 AND s.sys_person_status_id = 'uuid-pstatus-001'")
    Long selectOnDutyCount();

    /*@Select("SELECT COUNT(*) FROM sys_attendance a " +
            "INNER JOIN sys_user u ON a.user_id = u.user_id " +
            "WHERE u.deleted = 0 AND a.s = '全勤' " +
            "AND DATE_FORMAT(a.attendance_date, '%Y-%m') = DATE_FORMAT(CURDATE(), '%Y-%m')")
    Long selectFullAttendanceCount();

    @Select("SELECT COUNT(*) FROM sys_assessment a " +
            "INNER JOIN sys_user u ON a.user_id = u.user_id " +
            "WHERE u.deleted = 0 AND a.assessment_level = '优秀' " +
            "AND DATE_FORMAT(a.assessment_date, '%Y-%m') = DATE_FORMAT(CURDATE(), '%Y-%m')")
    Long selectExcellentAssessmentCount();

    @Select("SELECT COUNT(*) FROM sys_user u " +
            "LEFT JOIN sys_schedule s ON u.user_id = s.user_id " +
            "WHERE u.deleted = 0 AND (s.user_id IS NULL OR s.schedule_date < CURDATE())")
    Long selectPendingScheduleCount();

    // ========== 圆环图数据 ==========
    @Select("SELECT " +
            "CASE " +
            "   WHEN r.role_name IS NULL THEN '未知' " +
            "   ELSE r.role_name " +
            "END as name, " +
            "COUNT(*) as value " +
            "FROM sys_user u " +
            "LEFT JOIN sys_role r ON u.role_id = r.role_id " +
            "WHERE u.deleted = 0 " +
            "GROUP BY " +
            "CASE " +
            "   WHEN r.role_name IS NULL THEN '未知' " +
            "   ELSE r.role_name " +
            "END")
    List<PieItemVO> selectPositionTypeDistribution();

    @Select("SELECT " +
            "CASE " +
            "   WHEN u.user_status = '1' THEN '在岗' " +
            "   WHEN u.user_status = '0' THEN '离岗' " +
            "   ELSE '未知' " +
            "END as name, " +
            "COUNT(*) as value " +
            "FROM sys_user u " +
            "WHERE u.deleted = 0 " +
            "GROUP BY " +
            "CASE " +
            "   WHEN u.user_status = '1' THEN '在岗' " +
            "   WHEN u.user_status = '0' THEN '离岗' " +
            "   ELSE '未知' " +
            "END")
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

    @Select("SELECT " +
            "CASE " +
            "   WHEN r.role_name IS NULL THEN '未知' " +
            "   ELSE r.role_name " +
            "END as name, " +
            "ROUND(AVG(a.assessment_score), 2) as value " +
            "FROM sys_assessment a " +
            "INNER JOIN sys_user u ON a.user_id = u.user_id " +
            "LEFT JOIN sys_role r ON u.role_id = r.role_id " +
            "WHERE u.deleted = 0 AND a.deleted = 0 " +
            "AND DATE_FORMAT(a.assessment_date, '%Y-%m') = DATE_FORMAT(CURDATE(), '%Y-%m') " +
            "GROUP BY " +
            "CASE " +
            "   WHEN r.role_name IS NULL THEN '未知' " +
            "   ELSE r.role_name " +
            "END " +
            "ORDER BY value DESC")
    List<BarItemVO> selectAvgAssessmentScoreByPosition();*/
}