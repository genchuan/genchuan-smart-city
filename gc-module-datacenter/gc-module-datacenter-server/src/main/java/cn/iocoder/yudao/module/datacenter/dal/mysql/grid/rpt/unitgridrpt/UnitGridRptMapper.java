package cn.iocoder.yudao.module.datacenter.dal.mysql.grid.rpt.unitgridrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.unitgridrpt.vo.UnitGridRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.rpt.unitgridrpt.UnitGridRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 单元网格统计 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface UnitGridRptMapper extends BaseMapperX<UnitGridRptDO> {

    default PageResult<UnitGridRptDO> selectPage(UnitGridRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UnitGridRptDO>()
                .eqIfPresent(UnitGridRptDO::getUnitStatId, reqVO.getUnitStatId())
                .eqIfPresent(UnitGridRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(UnitGridRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(UnitGridRptDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(UnitGridRptDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(UnitGridRptDO::getRegionLevel, reqVO.getRegionLevel())
                .eqIfPresent(UnitGridRptDO::getScale, reqVO.getScale())
                .eqIfPresent(UnitGridRptDO::getTotalCount, reqVO.getTotalCount())
                .eqIfPresent(UnitGridRptDO::getInUseCount, reqVO.getInUseCount())
                .eqIfPresent(UnitGridRptDO::getStoppedCount, reqVO.getStoppedCount())
                .eqIfPresent(UnitGridRptDO::getTotalArea, reqVO.getTotalArea())
                .eqIfPresent(UnitGridRptDO::getAvgArea, reqVO.getAvgArea())
                .eqIfPresent(UnitGridRptDO::getNewCount, reqVO.getNewCount())
                .eqIfPresent(UnitGridRptDO::getUpdateCount, reqVO.getUpdateCount())
                .eqIfPresent(UnitGridRptDO::getStatUserId, reqVO.getStatUserId())
                .betweenIfPresent(UnitGridRptDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(UnitGridRptDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(UnitGridRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(UnitGridRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(UnitGridRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(UnitGridRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(UnitGridRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UnitGridRptDO::getId));
    }

}