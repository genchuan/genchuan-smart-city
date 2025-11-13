package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.region.earlywarnmonevt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmonevt.vo.EarlyWarnMonEvtPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnmonevt.EarlyWarnMonEvtDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 按监测事件预警告警统计 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EarlyWarnMonEvtMapper extends BaseMapperX<EarlyWarnMonEvtDO> {

    default PageResult<EarlyWarnMonEvtDO> selectPage(EarlyWarnMonEvtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EarlyWarnMonEvtDO>()
                .eqIfPresent(EarlyWarnMonEvtDO::getWarnMonEvtStatId, reqVO.getWarnMonEvtStatId())
                .eqIfPresent(EarlyWarnMonEvtDO::getStatCycle, reqVO.getStatCycle())
                .eqIfPresent(EarlyWarnMonEvtDO::getEvtMajorId, reqVO.getEvtMajorId())
                .likeIfPresent(EarlyWarnMonEvtDO::getEvtMajorName, reqVO.getEvtMajorName())
                .eqIfPresent(EarlyWarnMonEvtDO::getEvtMinorId, reqVO.getEvtMinorId())
                .likeIfPresent(EarlyWarnMonEvtDO::getEvtMinorName, reqVO.getEvtMinorName())
                .eqIfPresent(EarlyWarnMonEvtDO::getTotalWarnCount, reqVO.getTotalWarnCount())
                .betweenIfPresent(EarlyWarnMonEvtDO::getAvgRespTime, reqVO.getAvgRespTime())
                .eqIfPresent(EarlyWarnMonEvtDO::getRegionCount, reqVO.getRegionCount())
                .eqIfPresent(EarlyWarnMonEvtDO::getCompletedCount, reqVO.getCompletedCount())
                .eqIfPresent(EarlyWarnMonEvtDO::getHandleRate, reqVO.getHandleRate())
                .eqIfPresent(EarlyWarnMonEvtDO::getStatUserId, reqVO.getStatUserId())
                .betweenIfPresent(EarlyWarnMonEvtDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(EarlyWarnMonEvtDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EarlyWarnMonEvtDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EarlyWarnMonEvtDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EarlyWarnMonEvtDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EarlyWarnMonEvtDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EarlyWarnMonEvtDO::getId));
    }

}