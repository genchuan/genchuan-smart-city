package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.count.inspectcyclerpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectcyclerpt.vo.InspectCycleRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.count.inspectcyclerpt.InspectCycleRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检周期统计 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectCycleRptMapper extends BaseMapperX<InspectCycleRptDO> {

    default PageResult<InspectCycleRptDO> selectPage(InspectCycleRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectCycleRptDO>()
                .eqIfPresent(InspectCycleRptDO::getCycleStatId, reqVO.getCycleStatId())
                .eqIfPresent(InspectCycleRptDO::getStatDimension, reqVO.getStatDimension())
                .eqIfPresent(InspectCycleRptDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(InspectCycleRptDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(InspectCycleRptDO::getCycleList, reqVO.getCycleList())
                .eqIfPresent(InspectCycleRptDO::getInspectCountList, reqVO.getInspectCountList())
                .eqIfPresent(InspectCycleRptDO::getProblemCountList, reqVO.getProblemCountList())
                .eqIfPresent(InspectCycleRptDO::getRectifyRateList, reqVO.getRectifyRateList())
                .eqIfPresent(InspectCycleRptDO::getAvgFoundRateList, reqVO.getAvgFoundRateList())
                .eqIfPresent(InspectCycleRptDO::getStatUser, reqVO.getStatUser())
                .eqIfPresent(InspectCycleRptDO::getStatTimeBiz, reqVO.getStatTimeBiz())
                .eqIfPresent(InspectCycleRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectCycleRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectCycleRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectCycleRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectCycleRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectCycleRptDO::getId));
    }

}