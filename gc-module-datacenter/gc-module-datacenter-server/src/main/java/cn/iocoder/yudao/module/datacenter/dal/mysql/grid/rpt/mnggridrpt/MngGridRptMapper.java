package cn.iocoder.yudao.module.datacenter.dal.mysql.grid.rpt.mnggridrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.mnggridrpt.vo.MngGridRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.rpt.mnggridrpt.MngGridRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理网格统计 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface MngGridRptMapper extends BaseMapperX<MngGridRptDO> {

    default PageResult<MngGridRptDO> selectPage(MngGridRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MngGridRptDO>()
                .eqIfPresent(MngGridRptDO::getMgStatId, reqVO.getMgStatId())
                .eqIfPresent(MngGridRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(MngGridRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(MngGridRptDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(MngGridRptDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(MngGridRptDO::getGridUserId, reqVO.getGridUserId())
                .likeIfPresent(MngGridRptDO::getGridUserName, reqVO.getGridUserName())
                .eqIfPresent(MngGridRptDO::getUnitCountRange, reqVO.getUnitCountRange())
                .eqIfPresent(MngGridRptDO::getTotalCount, reqVO.getTotalCount())
                .eqIfPresent(MngGridRptDO::getTotalArea, reqVO.getTotalArea())
                .eqIfPresent(MngGridRptDO::getAvgUnitCount, reqVO.getAvgUnitCount())
                .eqIfPresent(MngGridRptDO::getTotalUnitCount, reqVO.getTotalUnitCount())
                .eqIfPresent(MngGridRptDO::getGridUserCoverCount, reqVO.getGridUserCoverCount())
                .eqIfPresent(MngGridRptDO::getNewCount, reqVO.getNewCount())
                .eqIfPresent(MngGridRptDO::getStatUserId, reqVO.getStatUserId())
                .betweenIfPresent(MngGridRptDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(MngGridRptDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(MngGridRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(MngGridRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(MngGridRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MngGridRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(MngGridRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MngGridRptDO::getId));
    }

}