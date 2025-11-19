package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.result.inspectresulteval;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresulteval.vo.InspectResultEvalPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.result.inspectresulteval.InspectResultEvalDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检结果评估 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectResultEvalMapper extends BaseMapperX<InspectResultEvalDO> {

    default PageResult<InspectResultEvalDO> selectPage(InspectResultEvalPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectResultEvalDO>()
                .eqIfPresent(InspectResultEvalDO::getEvalId, reqVO.getEvalId())
                .eqIfPresent(InspectResultEvalDO::getEvalCode, reqVO.getEvalCode())
                .eqIfPresent(InspectResultEvalDO::getEvalCycle, reqVO.getEvalCycle())
                .likeIfPresent(InspectResultEvalDO::getEvalCycleName, reqVO.getEvalCycleName())
                .eqIfPresent(InspectResultEvalDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(InspectResultEvalDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(InspectResultEvalDO::getDailyQualifiedRate, reqVO.getDailyQualifiedRate())
                .eqIfPresent(InspectResultEvalDO::getSpecRectifyRate, reqVO.getSpecRectifyRate())
                .eqIfPresent(InspectResultEvalDO::getLevel1TimelyRate, reqVO.getLevel1TimelyRate())
                .eqIfPresent(InspectResultEvalDO::getProblemConcentratedArea, reqVO.getProblemConcentratedArea())
                .eqIfPresent(InspectResultEvalDO::getMainProblemType, reqVO.getMainProblemType())
                .eqIfPresent(InspectResultEvalDO::getEvalOpinion, reqVO.getEvalOpinion())
                .eqIfPresent(InspectResultEvalDO::getEvalUser, reqVO.getEvalUser())
                .betweenIfPresent(InspectResultEvalDO::getEvalTime, reqVO.getEvalTime())
                .eqIfPresent(InspectResultEvalDO::getEvalRptUrl, reqVO.getEvalRptUrl())
                .eqIfPresent(InspectResultEvalDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(InspectResultEvalDO::getBizCreateTime, reqVO.getBizCreateTime())
                .eqIfPresent(InspectResultEvalDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectResultEvalDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectResultEvalDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectResultEvalDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectResultEvalDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectResultEvalDO::getId));
    }

}