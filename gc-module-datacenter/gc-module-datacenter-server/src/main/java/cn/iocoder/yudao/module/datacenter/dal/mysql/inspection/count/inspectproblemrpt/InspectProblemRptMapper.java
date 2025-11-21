package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.count.inspectproblemrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectproblemrpt.vo.InspectProblemRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.count.inspectproblemrpt.InspectProblemRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检问题统计 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectProblemRptMapper extends BaseMapperX<InspectProblemRptDO> {

    default PageResult<InspectProblemRptDO> selectPage(InspectProblemRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectProblemRptDO>()
                .eqIfPresent(InspectProblemRptDO::getProblemStatId, reqVO.getProblemStatId())
                .eqIfPresent(InspectProblemRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(InspectProblemRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(InspectProblemRptDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(InspectProblemRptDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(InspectProblemRptDO::getProblemTypeId, reqVO.getProblemTypeId())
                .likeIfPresent(InspectProblemRptDO::getProblemTypeName, reqVO.getProblemTypeName())
                .eqIfPresent(InspectProblemRptDO::getRptProblemCount, reqVO.getRptProblemCount())
                .eqIfPresent(InspectProblemRptDO::getLevel1ProblemCount, reqVO.getLevel1ProblemCount())
                .eqIfPresent(InspectProblemRptDO::getRectifyCompleteCount, reqVO.getRectifyCompleteCount())
                .eqIfPresent(InspectProblemRptDO::getRectifyRate, reqVO.getRectifyRate())
                .eqIfPresent(InspectProblemRptDO::getUnrectifiedReason, reqVO.getUnrectifiedReason())
                .eqIfPresent(InspectProblemRptDO::getConcentratedArea, reqVO.getConcentratedArea())
                .eqIfPresent(InspectProblemRptDO::getStatUser, reqVO.getStatUser())
                .betweenIfPresent(InspectProblemRptDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(InspectProblemRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectProblemRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectProblemRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectProblemRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectProblemRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectProblemRptDO::getId));
    }

}