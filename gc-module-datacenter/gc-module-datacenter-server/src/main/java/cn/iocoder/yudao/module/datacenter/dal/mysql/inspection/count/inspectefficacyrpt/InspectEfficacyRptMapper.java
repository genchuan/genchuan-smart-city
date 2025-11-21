package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.count.inspectefficacyrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectefficacyrpt.vo.InspectEfficacyRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.count.inspectefficacyrpt.InspectEfficacyRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检效率统计 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectEfficacyRptMapper extends BaseMapperX<InspectEfficacyRptDO> {

    default PageResult<InspectEfficacyRptDO> selectPage(InspectEfficacyRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectEfficacyRptDO>()
                .eqIfPresent(InspectEfficacyRptDO::getEfficacyStatId, reqVO.getEfficacyStatId())
                .eqIfPresent(InspectEfficacyRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(InspectEfficacyRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(InspectEfficacyRptDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(InspectEfficacyRptDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(InspectEfficacyRptDO::getParticipantCount, reqVO.getParticipantCount())
                .eqIfPresent(InspectEfficacyRptDO::getTotalInspectCount, reqVO.getTotalInspectCount())
                .eqIfPresent(InspectEfficacyRptDO::getPerPersonInspectCount, reqVO.getPerPersonInspectCount())
                .eqIfPresent(InspectEfficacyRptDO::getAvgSingleEndure, reqVO.getAvgSingleEndure())
                .eqIfPresent(InspectEfficacyRptDO::getTotalProblemCount, reqVO.getTotalProblemCount())
                .eqIfPresent(InspectEfficacyRptDO::getAvgRectifyEndure, reqVO.getAvgRectifyEndure())
                .eqIfPresent(InspectEfficacyRptDO::getEfficacyEval, reqVO.getEfficacyEval())
                .eqIfPresent(InspectEfficacyRptDO::getStatUser, reqVO.getStatUser())
                .betweenIfPresent(InspectEfficacyRptDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(InspectEfficacyRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectEfficacyRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectEfficacyRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectEfficacyRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectEfficacyRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectEfficacyRptDO::getId));
    }

}