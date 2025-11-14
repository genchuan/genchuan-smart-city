package cn.iocoder.yudao.module.datacenter.dal.mysql.decisionexectrack;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.decisionexectrack.DecisionExecTrackDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.decisionexectrack.vo.*;

/**
 * 决策执行跟踪 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface DecisionExecTrackMapper extends BaseMapperX<DecisionExecTrackDO> {

    default PageResult<DecisionExecTrackDO> selectPage(DecisionExecTrackPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DecisionExecTrackDO>()
                .eqIfPresent(DecisionExecTrackDO::getExecTrackId, reqVO.getExecTrackId())
                .eqIfPresent(DecisionExecTrackDO::getRelSchemeId, reqVO.getRelSchemeId())
                .likeIfPresent(DecisionExecTrackDO::getRelSchemeName, reqVO.getRelSchemeName())
                .eqIfPresent(DecisionExecTrackDO::getPlannedExecCycle, reqVO.getPlannedExecCycle())
                .eqIfPresent(DecisionExecTrackDO::getExecutedDays, reqVO.getExecutedDays())
                .eqIfPresent(DecisionExecTrackDO::getExecProgress, reqVO.getExecProgress())
                .eqIfPresent(DecisionExecTrackDO::getExpectedEffectIdx, reqVO.getExpectedEffectIdx())
                .eqIfPresent(DecisionExecTrackDO::getActualEffectIdx, reqVO.getActualEffectIdx())
                .eqIfPresent(DecisionExecTrackDO::getEffectAchievementRate, reqVO.getEffectAchievementRate())
                .eqIfPresent(DecisionExecTrackDO::getUnmetItems, reqVO.getUnmetItems())
                .eqIfPresent(DecisionExecTrackDO::getTrackStatus, reqVO.getTrackStatus())
                .eqIfPresent(DecisionExecTrackDO::getTrackUser, reqVO.getTrackUser())
                .betweenIfPresent(DecisionExecTrackDO::getTrackTime, reqVO.getTrackTime())
                .betweenIfPresent(DecisionExecTrackDO::getLastTrackTime, reqVO.getLastTrackTime())
                .eqIfPresent(DecisionExecTrackDO::getRectificationSugg, reqVO.getRectificationSugg())
                .eqIfPresent(DecisionExecTrackDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DecisionExecTrackDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DecisionExecTrackDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DecisionExecTrackDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(DecisionExecTrackDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(DecisionExecTrackDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(DecisionExecTrackDO::getId));
    }

}