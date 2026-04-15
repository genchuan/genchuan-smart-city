package cn.iocoder.yudao.module.inspectop.dal.mysql.carchargemonitor;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.carchargemonitor.CarChargeMonitorDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.inspectop.controller.admin.carchargemonitor.vo.*;

/**
 * 汽车充电监测 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface CarChargeMonitorMapper extends BaseMapperX<CarChargeMonitorDO> {

    default PageResult<CarChargeMonitorDO> selectPage(CarChargeMonitorPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CarChargeMonitorDO>()
                .eqIfPresent(CarChargeMonitorDO::getDeviceId, reqVO.getDeviceId())
                .eqIfPresent(CarChargeMonitorDO::getStationId, reqVO.getStationId())
                .betweenIfPresent(CarChargeMonitorDO::getMonitorTime, reqVO.getMonitorTime())
                .eqIfPresent(CarChargeMonitorDO::getMonitorStatus, reqVO.getMonitorStatus())
                .eqIfPresent(CarChargeMonitorDO::getAlarmStatus, reqVO.getAlarmStatus())
                .betweenIfPresent(CarChargeMonitorDO::getAlarmTime, reqVO.getAlarmTime())
                .eqIfPresent(CarChargeMonitorDO::getAlarmRemark, reqVO.getAlarmRemark())
                .eqIfPresent(CarChargeMonitorDO::getProcessStatus, reqVO.getProcessStatus())
                .eqIfPresent(CarChargeMonitorDO::getLongitude, reqVO.getLongitude())
                .eqIfPresent(CarChargeMonitorDO::getLatitude, reqVO.getLatitude())
                .eqIfPresent(CarChargeMonitorDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(CarChargeMonitorDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(CarChargeMonitorDO::getCreator, reqVO.getCreator())
                .eqIfPresent(CarChargeMonitorDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(CarChargeMonitorDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(CarChargeMonitorDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(CarChargeMonitorDO::getId));
    }

}