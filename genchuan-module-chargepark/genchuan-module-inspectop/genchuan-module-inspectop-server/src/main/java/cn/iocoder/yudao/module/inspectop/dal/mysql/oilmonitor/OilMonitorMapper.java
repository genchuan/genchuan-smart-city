package cn.iocoder.yudao.module.inspectop.dal.mysql.oilmonitor;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.oilmonitor.OilMonitorDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.inspectop.controller.admin.oilmonitor.vo.*;

/**
 * 油车占位监测 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface OilMonitorMapper extends BaseMapperX<OilMonitorDO> {

    default PageResult<OilMonitorDO> selectPage(OilMonitorPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OilMonitorDO>()
                .eqIfPresent(OilMonitorDO::getSpaceId, reqVO.getSpaceId())
                .eqIfPresent(OilMonitorDO::getStationId, reqVO.getStationId())
                .betweenIfPresent(OilMonitorDO::getIdentifyTime, reqVO.getIdentifyTime())
                .eqIfPresent(OilMonitorDO::getProcessStatus, reqVO.getProcessStatus())
                .eqIfPresent(OilMonitorDO::getProcessUserId, reqVO.getProcessUserId())
                .betweenIfPresent(OilMonitorDO::getProcessTime, reqVO.getProcessTime())
                .eqIfPresent(OilMonitorDO::getIgnoreReason, reqVO.getIgnoreReason())
                .eqIfPresent(OilMonitorDO::getProcessProgress, reqVO.getProcessProgress())
                .eqIfPresent(OilMonitorDO::getLongitude, reqVO.getLongitude())
                .eqIfPresent(OilMonitorDO::getLatitude, reqVO.getLatitude())
                .eqIfPresent(OilMonitorDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(OilMonitorDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(OilMonitorDO::getCreator, reqVO.getCreator())
                .eqIfPresent(OilMonitorDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(OilMonitorDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(OilMonitorDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(OilMonitorDO::getId));
    }

}