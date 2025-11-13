package cn.iocoder.yudao.module.datacenter.dal.mysql.decisionoptsugg;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.decisionoptsugg.DecisionOptSuggDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.decisionoptsugg.vo.*;

/**
 * 决策优化建议 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface DecisionOptSuggMapper extends BaseMapperX<DecisionOptSuggDO> {

    default PageResult<DecisionOptSuggDO> selectPage(DecisionOptSuggPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DecisionOptSuggDO>()
                .eqIfPresent(DecisionOptSuggDO::getOptSuggId, reqVO.getOptSuggId())
                .eqIfPresent(DecisionOptSuggDO::getRelEvalId, reqVO.getRelEvalId())
                .eqIfPresent(DecisionOptSuggDO::getRelSchemeId, reqVO.getRelSchemeId())
                .likeIfPresent(DecisionOptSuggDO::getRelSchemeName, reqVO.getRelSchemeName())
                .eqIfPresent(DecisionOptSuggDO::getSuggType, reqVO.getSuggType())
                .eqIfPresent(DecisionOptSuggDO::getSuggContent, reqVO.getSuggContent())
                .eqIfPresent(DecisionOptSuggDO::getRefCaseId, reqVO.getRefCaseId())
                .likeIfPresent(DecisionOptSuggDO::getRefCaseName, reqVO.getRefCaseName())
                .eqIfPresent(DecisionOptSuggDO::getSuggPriority, reqVO.getSuggPriority())
                .eqIfPresent(DecisionOptSuggDO::getSuggStatus, reqVO.getSuggStatus())
                .eqIfPresent(DecisionOptSuggDO::getProposer, reqVO.getProposer())
                .likeIfPresent(DecisionOptSuggDO::getProposerName, reqVO.getProposerName())
                .betweenIfPresent(DecisionOptSuggDO::getProposeTime, reqVO.getProposeTime())
                .eqIfPresent(DecisionOptSuggDO::getHandler, reqVO.getHandler())
                .betweenIfPresent(DecisionOptSuggDO::getHandleTime, reqVO.getHandleTime())
                .eqIfPresent(DecisionOptSuggDO::getHandleOpinion, reqVO.getHandleOpinion())
                .eqIfPresent(DecisionOptSuggDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DecisionOptSuggDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DecisionOptSuggDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DecisionOptSuggDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(DecisionOptSuggDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(DecisionOptSuggDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(DecisionOptSuggDO::getId));
    }

}