package cn.iocoder.yudao.module.studentmgmt.dal.mysql.assessmgmt;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.assessmgmt.AssessMgmtDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.assessmgmt.vo.*;
import org.apache.ibatis.annotations.Param;

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
                .eqIfPresent(AssessMgmtDO::getRank, reqVO.getRank())
                .eqIfPresent(AssessMgmtDO::getAssessUser, reqVO.getAssessUser())
                .betweenIfPresent(AssessMgmtDO::getPublishTime, reqVO.getPublishTime())
                .eqIfPresent(AssessMgmtDO::getStatus, reqVO.getStatus())
                .eqIfPresent(AssessMgmtDO::getRemark, reqVO.getRemark())
                .eqIfPresent(AssessMgmtDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(AssessMgmtDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(AssessMgmtDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssessMgmtDO::getId));
    }

    Integer selectTotalAssessCount(@Param("grade") String grade, @Param("major") String major,
                                   @Param("status") String status, @Param("assessType") String assessType);

    BigDecimal selectAssessScore(@Param("grade") String grade, @Param("major") String major,
                                 @Param("status") String status, @Param("assessType") String assessType);

    Integer selectTodayPublishCount(@Param("grade") String grade, @Param("major") String major);

    AssessMgmtTypeCountRespVO typeCount(@Param("grade") String grade);

    AssessMgmtCoreIndexReqVO getCoreIndex(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);
}