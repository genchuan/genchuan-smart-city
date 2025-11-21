package cn.iocoder.yudao.module.datacenter.dal.mysql.inspectarearpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectarearpt.vo.InspectAreaRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspectarearpt.InspectAreaRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检区域统计 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectAreaRptMapper extends BaseMapperX<InspectAreaRptDO> {

    default PageResult<InspectAreaRptDO> selectPage(InspectAreaRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectAreaRptDO>()
                .eqIfPresent(InspectAreaRptDO::getAreaStatId, reqVO.getAreaStatId())
                .eqIfPresent(InspectAreaRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(InspectAreaRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(InspectAreaRptDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(InspectAreaRptDO::getAreaName, reqVO.getAreaName())
                .likeIfPresent(InspectAreaRptDO::getParentAreaName, reqVO.getParentAreaName())
                .eqIfPresent(InspectAreaRptDO::getDailyInspectCount, reqVO.getDailyInspectCount())
                .eqIfPresent(InspectAreaRptDO::getSpecInspectCount, reqVO.getSpecInspectCount())
                .eqIfPresent(InspectAreaRptDO::getEmerInspectCount, reqVO.getEmerInspectCount())
                .eqIfPresent(InspectAreaRptDO::getTotalProblemCount, reqVO.getTotalProblemCount())
                .eqIfPresent(InspectAreaRptDO::getRectifyCompleteCount, reqVO.getRectifyCompleteCount())
                .eqIfPresent(InspectAreaRptDO::getRectifyRate, reqVO.getRectifyRate())
                .eqIfPresent(InspectAreaRptDO::getConcentratedProblemType, reqVO.getConcentratedProblemType())
                .eqIfPresent(InspectAreaRptDO::getStatUser, reqVO.getStatUser())
                .betweenIfPresent(InspectAreaRptDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(InspectAreaRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectAreaRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectAreaRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectAreaRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectAreaRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectAreaRptDO::getId));
    }

}