package cn.iocoder.yudao.module.datacenter.dal.mysql.evtmanager.evtanalysis;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtanalysis.vo.EvtAnalysisPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtanalysis.EvtAnalysisDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 事件研判分析 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EvtAnalysisMapper extends BaseMapperX<EvtAnalysisDO> {

    default PageResult<EvtAnalysisDO> selectPage(EvtAnalysisPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvtAnalysisDO>()
                .eqIfPresent(EvtAnalysisDO::getAnalysisId, reqVO.getAnalysisId())
                .eqIfPresent(EvtAnalysisDO::getEvtCode, reqVO.getEvtCode())
                .likeIfPresent(EvtAnalysisDO::getEvtName, reqVO.getEvtName())
                .eqIfPresent(EvtAnalysisDO::getAnalystId, reqVO.getAnalystId())
                .likeIfPresent(EvtAnalysisDO::getAnalystName, reqVO.getAnalystName())
                .betweenIfPresent(EvtAnalysisDO::getAnalysisTime, reqVO.getAnalysisTime())
                .eqIfPresent(EvtAnalysisDO::getEvtReason, reqVO.getEvtReason())
                .eqIfPresent(EvtAnalysisDO::getImpactRange, reqVO.getImpactRange())
                .eqIfPresent(EvtAnalysisDO::getUrgencyLevel, reqVO.getUrgencyLevel())
                .eqIfPresent(EvtAnalysisDO::getConclusion, reqVO.getConclusion())
                .eqIfPresent(EvtAnalysisDO::getRelMonDataId, reqVO.getRelMonDataId())
                .eqIfPresent(EvtAnalysisDO::getMonDataAbstract, reqVO.getMonDataAbstract())
                .eqIfPresent(EvtAnalysisDO::getNeedAllocate, reqVO.getNeedAllocate())
                .orderByDesc(EvtAnalysisDO::getId));
    }

}