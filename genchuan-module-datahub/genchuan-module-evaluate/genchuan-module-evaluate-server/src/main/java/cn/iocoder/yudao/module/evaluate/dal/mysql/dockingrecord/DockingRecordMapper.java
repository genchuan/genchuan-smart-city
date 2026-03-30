package cn.iocoder.yudao.module.evaluate.dal.mysql.dockingrecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.dockingrecord.vo.DockingRecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.dockingrecord.DockingRecordDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统对接记录 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface DockingRecordMapper extends BaseMapperX<DockingRecordDO> {

    default PageResult<DockingRecordDO> selectPage(DockingRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DockingRecordDO>()
                .eqIfPresent(DockingRecordDO::getDockingId, reqVO.getDockingId())
                .eqIfPresent(DockingRecordDO::getCode, reqVO.getCode())
                .eqIfPresent(DockingRecordDO::getSystemId, reqVO.getSystemId())
                .eqIfPresent(DockingRecordDO::getTypeId, reqVO.getTypeId())
                .eqIfPresent(DockingRecordDO::getStatus, reqVO.getStatus())
                .eqIfPresent(DockingRecordDO::getFreqId, reqVO.getFreqId())
                .eqIfPresent(DockingRecordDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(DockingRecordDO::getConfigTime, reqVO.getConfigTime())
                .betweenIfPresent(DockingRecordDO::getLatestDockTime, reqVO.getLatestDockTime())
                .eqIfPresent(DockingRecordDO::getSuccessRate, reqVO.getSuccessRate())
                .eqIfPresent(DockingRecordDO::getTotalSyncNum, reqVO.getTotalSyncNum())
                .eqIfPresent(DockingRecordDO::getFailCount, reqVO.getFailCount())
                .eqIfPresent(DockingRecordDO::getLatestFailReason, reqVO.getLatestFailReason())
                .eqIfPresent(DockingRecordDO::getMapRule, reqVO.getMapRule())
                .eqIfPresent(DockingRecordDO::getStopBy, reqVO.getStopBy())
                .betweenIfPresent(DockingRecordDO::getStopTime, reqVO.getStopTime())
                .eqIfPresent(DockingRecordDO::getStopReason, reqVO.getStopReason())
                .eqIfPresent(DockingRecordDO::getStopHour, reqVO.getStopHour())
                .eqIfPresent(DockingRecordDO::getHistorySuccessRate, reqVO.getHistorySuccessRate())
                .eqIfPresent(DockingRecordDO::getConfigCheckResult, reqVO.getConfigCheckResult())
                .eqIfPresent(DockingRecordDO::getExternalStatus, reqVO.getExternalStatus())
                .eqIfPresent(DockingRecordDO::getReDockCount, reqVO.getReDockCount())
                .betweenIfPresent(DockingRecordDO::getLatestReDockTime, reqVO.getLatestReDockTime())
                .eqIfPresent(DockingRecordDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DockingRecordDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(DockingRecordDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(DockingRecordDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(DockingRecordDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DockingRecordDO::getId));
    }

}