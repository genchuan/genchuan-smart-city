package cn.iocoder.yudao.module.studentmgmt.dal.mysql.assessmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.assessmgmt.vo.AssessMgmtCycleTrendRespVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.assessmgmt.vo.AssessMgmtDimensionScoreRespVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.assessmgmt.vo.AssessMgmtPageReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.workhome.vo.WorkHomeScoreAnalysisRespVO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.assessmgmt.AssessMgmtDO;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 考评管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface AssessMgmtMapper extends BaseMapperX<AssessMgmtDO> {

    default PageResult<AssessMgmtDO> selectPage(AssessMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AssessMgmtDO>()
                .likeIfPresent(AssessMgmtDO::getClassName, reqVO.getClassName())
                .eqIfPresent(AssessMgmtDO::getAssessType, reqVO.getAssessType())
                .eqIfPresent(AssessMgmtDO::getCycle, reqVO.getCycle())
                .eqIfPresent(AssessMgmtDO::getScore, reqVO.getScore())
                .eqIfPresent(AssessMgmtDO::getRankNo, reqVO.getRankNo())
                .eqIfPresent(AssessMgmtDO::getAssessUser, reqVO.getAssessUser())
                .betweenIfPresent(AssessMgmtDO::getPublishTime, reqVO.getPublishTime())
                .eqIfPresent(AssessMgmtDO::getStatus, reqVO.getStatus())
                .eqIfPresent(AssessMgmtDO::getRemark, reqVO.getRemark())
                .eqIfPresent(AssessMgmtDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(AssessMgmtDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(AssessMgmtDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssessMgmtDO::getId));
    }

    Integer selectTotalAssessCount(@Param("cycle") String cycle,
                                   @Param("status") String status, @Param("assessType") String assessType);

    BigDecimal selectAssessScore(@Param("grade") String grade, @Param("major") String major,
                                 @Param("status") String status, @Param("assessType") String assessType);

    Integer selectTodayPublishCount(@Param("grade") String grade, @Param("major") String major);

    List<AssessMgmtDimensionScoreRespVO> dimensionScore(@Param("cycle") String cycle);

    List<AssessMgmtCycleTrendRespVO> cycleTrend(@Param("className") String className, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    BigDecimal selectAvgScore(@Param("cycle") String cycle, String status);

    String selectTopRankClass(@Param("cycle") String cycle, @Param("status") String status);

    List<JSONObject> selectAssessTypeCount(@Param("cycle") String cycle,@Param("status") String status);

    List<JSONObject> selectStatusCount(String cycle, String status);

    AssessMgmtDO selectAvgScoreByTime(String timeScale, LocalDateTime lastStartTime, LocalDateTime lastEndTime);

    Integer selectTotalAssess(LocalDateTime startTime, LocalDateTime endTime, String className, String grade);

    List<WorkHomeScoreAnalysisRespVO> selectScoreAnalysis(String grade, String cycle);

    List<JSONObject> selectTotalCountByDate(LocalDateTime startTime, LocalDateTime endTime, String cycle);
}