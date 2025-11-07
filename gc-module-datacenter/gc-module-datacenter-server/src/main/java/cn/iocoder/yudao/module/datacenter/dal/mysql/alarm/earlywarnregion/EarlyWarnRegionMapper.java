package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.earlywarnregion;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.earlywarnregion.vo.EarlyWarnRegionPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.earlywarnregion.EarlyWarnRegionDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 按行政区划预警告警统计 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EarlyWarnRegionMapper extends BaseMapperX<EarlyWarnRegionDO> {

    default PageResult<EarlyWarnRegionDO> selectPage(EarlyWarnRegionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EarlyWarnRegionDO>()
                .eqIfPresent(EarlyWarnRegionDO::getWarnRegionStatId, reqVO.getWarnRegionStatId())
                .eqIfPresent(EarlyWarnRegionDO::getStatCycle, reqVO.getStatCycle())
                .eqIfPresent(EarlyWarnRegionDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(EarlyWarnRegionDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(EarlyWarnRegionDO::getRegionLevel, reqVO.getRegionLevel())
                .eqIfPresent(EarlyWarnRegionDO::getTotalWarnCount, reqVO.getTotalWarnCount())
                .eqIfPresent(EarlyWarnRegionDO::getNormalLevelCount, reqVO.getNormalLevelCount())
                .eqIfPresent(EarlyWarnRegionDO::getHeavyLevelCount, reqVO.getHeavyLevelCount())
                .eqIfPresent(EarlyWarnRegionDO::getSeriousLevelCount, reqVO.getSeriousLevelCount())
                .eqIfPresent(EarlyWarnRegionDO::getCriticalLevelCount, reqVO.getCriticalLevelCount())
                .eqIfPresent(EarlyWarnRegionDO::getCompletedCount, reqVO.getCompletedCount())
                .eqIfPresent(EarlyWarnRegionDO::getPendCount, reqVO.getPendCount())
                .eqIfPresent(EarlyWarnRegionDO::getHandleRate, reqVO.getHandleRate())
                .eqIfPresent(EarlyWarnRegionDO::getStatUserId, reqVO.getStatUserId())
                .betweenIfPresent(EarlyWarnRegionDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(EarlyWarnRegionDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EarlyWarnRegionDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EarlyWarnRegionDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EarlyWarnRegionDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EarlyWarnRegionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EarlyWarnRegionDO::getId));
    }

}