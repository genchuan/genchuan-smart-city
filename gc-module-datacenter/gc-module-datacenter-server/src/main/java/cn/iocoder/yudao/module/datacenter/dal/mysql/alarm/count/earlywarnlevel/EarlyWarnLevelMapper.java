package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.count.earlywarnlevel;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnlevel.vo.EarlyWarnLevelPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarnlevel.EarlyWarnLevelDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警告警等级维度统计 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EarlyWarnLevelMapper extends BaseMapperX<EarlyWarnLevelDO> {

    default PageResult<EarlyWarnLevelDO> selectPage(EarlyWarnLevelPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EarlyWarnLevelDO>()
                .eqIfPresent(EarlyWarnLevelDO::getLevelStatId, reqVO.getLevelStatId())
                .eqIfPresent(EarlyWarnLevelDO::getStatCycle, reqVO.getStatCycle())
                .eqIfPresent(EarlyWarnLevelDO::getWarnLevel, reqVO.getWarnLevel())
                .eqIfPresent(EarlyWarnLevelDO::getWarnLevelCode, reqVO.getWarnLevelCode())
                .eqIfPresent(EarlyWarnLevelDO::getTotalWarnCount, reqVO.getTotalWarnCount())
                .eqIfPresent(EarlyWarnLevelDO::getProportion, reqVO.getProportion())
                .eqIfPresent(EarlyWarnLevelDO::getCompletedCount, reqVO.getCompletedCount())
                .eqIfPresent(EarlyWarnLevelDO::getHandleRate, reqVO.getHandleRate())
                .eqIfPresent(EarlyWarnLevelDO::getAvgHandleEndure, reqVO.getAvgHandleEndure())
                .eqIfPresent(EarlyWarnLevelDO::getMainWarnType, reqVO.getMainWarnType())
                .eqIfPresent(EarlyWarnLevelDO::getStatUserId, reqVO.getStatUserId())
                .betweenIfPresent(EarlyWarnLevelDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(EarlyWarnLevelDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EarlyWarnLevelDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EarlyWarnLevelDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EarlyWarnLevelDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EarlyWarnLevelDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EarlyWarnLevelDO::getId));
    }

}