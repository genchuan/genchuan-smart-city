package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.region.earlywarngrid;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarngrid.vo.EarlyWarnGridPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarngrid.EarlyWarnGridDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 按网格分域预警告警统计 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EarlyWarnGridMapper extends BaseMapperX<EarlyWarnGridDO> {

    default PageResult<EarlyWarnGridDO> selectPage(EarlyWarnGridPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EarlyWarnGridDO>()
                .eqIfPresent(EarlyWarnGridDO::getWarnGridStatId, reqVO.getWarnGridStatId())
                .eqIfPresent(EarlyWarnGridDO::getStatCycle, reqVO.getStatCycle())
                .eqIfPresent(EarlyWarnGridDO::getGridType, reqVO.getGridType())
                .eqIfPresent(EarlyWarnGridDO::getGridId, reqVO.getGridId())
                .eqIfPresent(EarlyWarnGridDO::getGridCode, reqVO.getGridCode())
                .likeIfPresent(EarlyWarnGridDO::getGridName, reqVO.getGridName())
                .eqIfPresent(EarlyWarnGridDO::getStreetCode, reqVO.getStreetCode())
                .likeIfPresent(EarlyWarnGridDO::getStreetName, reqVO.getStreetName())
                .eqIfPresent(EarlyWarnGridDO::getTotalWarnCount, reqVO.getTotalWarnCount())
                .eqIfPresent(EarlyWarnGridDO::getCompletedCount, reqVO.getCompletedCount())
                .eqIfPresent(EarlyWarnGridDO::getPendCount, reqVO.getPendCount())
                .eqIfPresent(EarlyWarnGridDO::getMainWarnType, reqVO.getMainWarnType())
                .eqIfPresent(EarlyWarnGridDO::getHandleRate, reqVO.getHandleRate())
                .eqIfPresent(EarlyWarnGridDO::getStatUserId, reqVO.getStatUserId())
                .betweenIfPresent(EarlyWarnGridDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(EarlyWarnGridDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EarlyWarnGridDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EarlyWarnGridDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EarlyWarnGridDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EarlyWarnGridDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EarlyWarnGridDO::getId));
    }

}