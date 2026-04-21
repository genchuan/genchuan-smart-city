package cn.iocoder.yudao.module.studentmgmt.dal.mysql.targetmgmt;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.targetmgmt.TargetMgmtDO;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.targetmgmt.vo.*;

/**
 * 指标管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TargetMgmtMapper extends BaseMapperX<TargetMgmtDO> {

    default PageResult<TargetMgmtDO> selectPage(TargetMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TargetMgmtDO>()
                .likeIfPresent(TargetMgmtDO::getTargetName, reqVO.getTargetName())
                .eqIfPresent(TargetMgmtDO::getTotalScore, reqVO.getTotalScore())
                .eqIfPresent(TargetMgmtDO::getWarnThreshold, reqVO.getWarnThreshold())
                .eqIfPresent(TargetMgmtDO::getEvaluatorType, reqVO.getEvaluatorType())
                .eqIfPresent(TargetMgmtDO::getScoreType, reqVO.getScoreType())
                .betweenIfPresent(TargetMgmtDO::getEnableTime, reqVO.getEnableTime())
                .betweenIfPresent(TargetMgmtDO::getDisableTime, reqVO.getDisableTime())
                .eqIfPresent(TargetMgmtDO::getStatus, reqVO.getStatus())
                .eqIfPresent(TargetMgmtDO::getRemark, reqVO.getRemark())
                .eqIfPresent(TargetMgmtDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(TargetMgmtDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(TargetMgmtDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TargetMgmtDO::getId));
    }

    List<JSONObject> selectStatusCount(LocalDateTime startTime, LocalDateTime endTime);

    List<JSONObject> selectEvaluatorTypeCount(LocalDateTime startTime, LocalDateTime endTime);

    List<JSONObject> selectScoreTypeCount(LocalDateTime startTime, LocalDateTime endTime);

    List<JSONObject> selectScoreDistribution(LocalDateTime startTime, LocalDateTime endTime);

    TargetMgmtChartIndexRespVO selectTotalIndex(String status);
}