package cn.iocoder.yudao.module.envirhealth.dal.mysql.roadcleaning;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.cleaningproblem.CleaningProblemPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.CleaningProblemDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.Detail.CleaningProblemDetailDO;
import cn.iocoder.yudao.module.envirhealth.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.util.vo.PieItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 道路清扫问题 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CleaningProblemMapper extends BaseMapperX<CleaningProblemDO> {

    default PageResult<CleaningProblemDO> selectPage(CleaningProblemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CleaningProblemDO>()
                .eqIfPresent(CleaningProblemDO::getProblemId, reqVO.getProblemId())
                .eqIfPresent(CleaningProblemDO::getPlanId, reqVO.getPlanId())
                .eqIfPresent(CleaningProblemDO::getProblemTypeId, reqVO.getProblemTypeId())
                .eqIfPresent(CleaningProblemDO::getLocation, reqVO.getLocation())
                .eqIfPresent(CleaningProblemDO::getReportBy, reqVO.getReportBy())
                .betweenIfPresent(CleaningProblemDO::getReportTime, reqVO.getReportTime())
                .eqIfPresent(CleaningProblemDO::getProblemDesc, reqVO.getProblemDesc())
                .eqIfPresent(CleaningProblemDO::getTeamId, reqVO.getTeamId())
                .eqIfPresent(CleaningProblemDO::getHandleStatus, reqVO.getHandleStatus())
                .eqIfPresent(CleaningProblemDO::getIsTimeout, reqVO.getIsTimeout())
                .eqIfPresent(CleaningProblemDO::getHandleResult, reqVO.getHandleResult())
                .betweenIfPresent(CleaningProblemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CleaningProblemDO::getId));
    }

    /**
     * 查询全局最大序号（用于problem_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(problem_id, '-', -1)), 0) FROM road_cleaning_problem")
    Integer selectMaxSeq();

    List<CleaningProblemDetailDO> selectDetailPage(@Param("reqVO") CleaningProblemPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") CleaningProblemPageReqVO pageReqVO);

    /**
     * 查询待处置问题总数
     */
    @Select("SELECT COUNT(*) FROM road_cleaning_problem " +
            "WHERE deleted = 0")
    Long selectPendingProblemCount();

    /**
     * 查询高优先级问题数（待处置中）
     */
    @Select("SELECT COUNT(*) FROM road_cleaning_problem " +
            "WHERE deleted = 0 AND handle_status <> '已办结' AND problem_priority = '高'")
    Long selectHighPriorityCount();

    /**
     * 查询超时未处理数（待处置中且超时）
     */
    @Select("SELECT COUNT(*) FROM road_cleaning_problem " +
            "WHERE deleted = 0 AND handle_status <> '已办结' AND is_timeout = '是'")
    Long selectTimeoutCount();

    /**
     * 待处置问题-问题类型占比（关联问题类型表获取名称）
     */
    @Select("SELECT " +
            "COALESCE(NULLIF(TRIM(t.name), ''), '未知类型') AS name, " +
            "COUNT(1) AS value " +
            "FROM road_cleaning_problem p " +
            "LEFT JOIN sys_problem_type t ON p.problem_type_id = t.sys_problem_type_id " +
            "WHERE p.deleted = 0 AND p.handle_status <> '已办结' " +
            "GROUP BY COALESCE(NULLIF(TRIM(t.name), ''), '未知类型') " +
            "ORDER BY value DESC")
    List<PieItemVO> selectProblemTypeDistribution();

    /**
     * 待处置问题-区域分布占比
     * 这里使用CASE WHEN语句根据location关键字分类
     */
    @Select("SELECT " +
            "CASE " +
            "  WHEN p.location LIKE '%朝阳%' THEN '朝阳区' " +
            "  WHEN p.location LIKE '%静安%' THEN '静安区' " +
            "  WHEN p.location LIKE '%天河%' THEN '天河区' " +
            "  WHEN p.location LIKE '%春熙%' THEN '锦江区' " +
            "  WHEN p.location LIKE '%西湖%' THEN '西湖区' " +
            "  WHEN p.location LIKE '%夫子庙%' THEN '秦淮区' " +
            "  WHEN p.location LIKE '%南山%' THEN '南山区' " +
            "  WHEN p.location LIKE '%王府井%' THEN '东城区' " +
            "  ELSE '其他区域' " +
            "END AS name, " +
            "COUNT(1) AS value " +
            "FROM road_cleaning_problem p " +
            "WHERE p.deleted = 0 AND p.handle_status <> '已办结' " +
            "GROUP BY name " +
            "ORDER BY value DESC")
    List<PieItemVO> selectAreaDistribution();

    /**
     * 待处置问题-处置组待处置问题数量对比
     */
    @Select("SELECT " +
            "COALESCE(NULLIF(TRIM(t.name), ''), '未知处置组') AS name, " +
            "COUNT(1) AS value " +
            "FROM road_cleaning_problem p " +
            "LEFT JOIN sys_team t ON p.team_id = t.sys_team_id " +
            "WHERE p.deleted = 0 AND p.handle_status <> '已办结' " +
            "AND p.team_id IS NOT NULL " +
            "GROUP BY COALESCE(NULLIF(TRIM(t.name), ''), '未知处置组') " +
            "ORDER BY value DESC")
    List<BarItemVO> selectTeamPendingDistribution();
}