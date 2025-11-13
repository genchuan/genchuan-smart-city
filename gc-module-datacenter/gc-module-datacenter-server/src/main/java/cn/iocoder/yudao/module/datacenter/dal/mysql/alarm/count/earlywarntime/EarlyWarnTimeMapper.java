package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.count.earlywarntime;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarntime.vo.EarlyWarnTimePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarntime.EarlyWarnTimeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警告警时间维度统计 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EarlyWarnTimeMapper extends BaseMapperX<EarlyWarnTimeDO> {

    default PageResult<EarlyWarnTimeDO> selectPage(EarlyWarnTimePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EarlyWarnTimeDO>()
                .eqIfPresent(EarlyWarnTimeDO::getTimeStatId, reqVO.getTimeStatId())
                .eqIfPresent(EarlyWarnTimeDO::getTimeDimension, reqVO.getTimeDimension())
                .eqIfPresent(EarlyWarnTimeDO::getStatCycleValue, reqVO.getStatCycleValue())
                .likeIfPresent(EarlyWarnTimeDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(EarlyWarnTimeDO::getTotalWarnCount, reqVO.getTotalWarnCount())
                .eqIfPresent(EarlyWarnTimeDO::getMainWarnType, reqVO.getMainWarnType())
                .eqIfPresent(EarlyWarnTimeDO::getMainTypeCount, reqVO.getMainTypeCount())
                .eqIfPresent(EarlyWarnTimeDO::getCompletedCount, reqVO.getCompletedCount())
                .eqIfPresent(EarlyWarnTimeDO::getHandleRate, reqVO.getHandleRate())
                .eqIfPresent(EarlyWarnTimeDO::getStatUserId, reqVO.getStatUserId())
                .betweenIfPresent(EarlyWarnTimeDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(EarlyWarnTimeDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EarlyWarnTimeDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EarlyWarnTimeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EarlyWarnTimeDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EarlyWarnTimeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EarlyWarnTimeDO::getId));
    }

}