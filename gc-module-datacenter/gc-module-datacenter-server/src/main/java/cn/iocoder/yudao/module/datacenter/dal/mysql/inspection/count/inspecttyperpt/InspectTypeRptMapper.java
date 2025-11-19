package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.count.inspecttyperpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspecttyperpt.vo.InspectTypeRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.count.inspecttyperpt.InspectTypeRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检类型统计 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectTypeRptMapper extends BaseMapperX<InspectTypeRptDO> {

    default PageResult<InspectTypeRptDO> selectPage(InspectTypeRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectTypeRptDO>()
                .eqIfPresent(InspectTypeRptDO::getTypeStatId, reqVO.getTypeStatId())
                .eqIfPresent(InspectTypeRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(InspectTypeRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(InspectTypeRptDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(InspectTypeRptDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(InspectTypeRptDO::getInspectType, reqVO.getInspectType())
                .eqIfPresent(InspectTypeRptDO::getInspectCount, reqVO.getInspectCount())
                .eqIfPresent(InspectTypeRptDO::getParticipantCount, reqVO.getParticipantCount())
                .eqIfPresent(InspectTypeRptDO::getFoundProblemCount, reqVO.getFoundProblemCount())
                .eqIfPresent(InspectTypeRptDO::getProblemFoundRate, reqVO.getProblemFoundRate())
                .eqIfPresent(InspectTypeRptDO::getRectifyCompleteCount, reqVO.getRectifyCompleteCount())
                .eqIfPresent(InspectTypeRptDO::getRectifyRate, reqVO.getRectifyRate())
                .eqIfPresent(InspectTypeRptDO::getAvgInspectEndure, reqVO.getAvgInspectEndure())
                .eqIfPresent(InspectTypeRptDO::getStatUser, reqVO.getStatUser())
                .betweenIfPresent(InspectTypeRptDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(InspectTypeRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectTypeRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectTypeRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectTypeRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectTypeRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectTypeRptDO::getId));
    }

}