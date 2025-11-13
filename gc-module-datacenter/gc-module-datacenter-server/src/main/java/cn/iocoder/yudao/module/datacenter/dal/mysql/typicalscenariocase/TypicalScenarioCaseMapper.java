package cn.iocoder.yudao.module.datacenter.dal.mysql.typicalscenariocase;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.typicalscenariocase.TypicalScenarioCaseDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.typicalscenariocase.vo.*;

/**
 * 典型场景案例库 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface TypicalScenarioCaseMapper extends BaseMapperX<TypicalScenarioCaseDO> {

    default PageResult<TypicalScenarioCaseDO> selectPage(TypicalScenarioCasePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TypicalScenarioCaseDO>()
                .eqIfPresent(TypicalScenarioCaseDO::getTypicalCaseId, reqVO.getTypicalCaseId())
                .likeIfPresent(TypicalScenarioCaseDO::getCaseName, reqVO.getCaseName())
                .eqIfPresent(TypicalScenarioCaseDO::getRelSchemeId, reqVO.getRelSchemeId())
                .eqIfPresent(TypicalScenarioCaseDO::getRelEvalId, reqVO.getRelEvalId())
                .eqIfPresent(TypicalScenarioCaseDO::getCaseTags, reqVO.getCaseTags())
                .eqIfPresent(TypicalScenarioCaseDO::getApplicableScenario, reqVO.getApplicableScenario())
                .eqIfPresent(TypicalScenarioCaseDO::getCoreMeasures, reqVO.getCoreMeasures())
                .eqIfPresent(TypicalScenarioCaseDO::getResourceInput, reqVO.getResourceInput())
                .eqIfPresent(TypicalScenarioCaseDO::getImplementationEffect, reqVO.getImplementationEffect())
                .eqIfPresent(TypicalScenarioCaseDO::getEvalGrade, reqVO.getEvalGrade())
                .eqIfPresent(TypicalScenarioCaseDO::getSuccessExp, reqVO.getSuccessExp())
                .eqIfPresent(TypicalScenarioCaseDO::getImproveSuggs, reqVO.getImproveSuggs())
                .eqIfPresent(TypicalScenarioCaseDO::getArchUser, reqVO.getArchUser())
                .betweenIfPresent(TypicalScenarioCaseDO::getArchTime, reqVO.getArchTime())
                .eqIfPresent(TypicalScenarioCaseDO::getCaseStatus, reqVO.getCaseStatus())
                .eqIfPresent(TypicalScenarioCaseDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(TypicalScenarioCaseDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(TypicalScenarioCaseDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(TypicalScenarioCaseDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(TypicalScenarioCaseDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(TypicalScenarioCaseDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(TypicalScenarioCaseDO::getId));
    }

}