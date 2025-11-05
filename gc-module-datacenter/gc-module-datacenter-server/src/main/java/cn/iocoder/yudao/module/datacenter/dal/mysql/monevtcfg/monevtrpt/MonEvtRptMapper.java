package cn.iocoder.yudao.module.datacenter.dal.mysql.monevtcfg.monevtrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtrpt.vo.MonEvtRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtrpt.MonEvtRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 监测事件统计报 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MonEvtRptMapper extends BaseMapperX<MonEvtRptDO> {

    default PageResult<MonEvtRptDO> selectPage(MonEvtRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MonEvtRptDO>()
                .eqIfPresent(MonEvtRptDO::getStatId, reqVO.getStatId())
                .eqIfPresent(MonEvtRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(MonEvtRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(MonEvtRptDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(MonEvtRptDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(MonEvtRptDO::getEvtMajorId, reqVO.getEvtMajorId())
                .likeIfPresent(MonEvtRptDO::getEvtMajorName, reqVO.getEvtMajorName())
                .eqIfPresent(MonEvtRptDO::getEvtMinorId, reqVO.getEvtMinorId())
                .likeIfPresent(MonEvtRptDO::getEvtMinorName, reqVO.getEvtMinorName())
                .eqIfPresent(MonEvtRptDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(MonEvtRptDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(MonEvtRptDO::getTotalRptCount, reqVO.getTotalRptCount())
                .eqIfPresent(MonEvtRptDO::getPendCount, reqVO.getPendCount())
                .eqIfPresent(MonEvtRptDO::getHandlCount, reqVO.getHandlCount())
                .eqIfPresent(MonEvtRptDO::getCompletedCount, reqVO.getCompletedCount())
                .eqIfPresent(MonEvtRptDO::getRejectedCount, reqVO.getRejectedCount())
                .eqIfPresent(MonEvtRptDO::getLevel1Count, reqVO.getLevel1Count())
                .orderByDesc(MonEvtRptDO::getId));
    }

}