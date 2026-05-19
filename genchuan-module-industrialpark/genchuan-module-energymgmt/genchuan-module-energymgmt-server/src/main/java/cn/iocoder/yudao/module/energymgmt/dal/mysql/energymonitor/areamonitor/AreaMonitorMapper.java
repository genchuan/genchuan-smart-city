package cn.iocoder.yudao.module.energymgmt.dal.mysql.energymonitor.areamonitor;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.energymgmt.dal.dataobject.energymonitor.areamonitor.AreaMonitorDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.energymgmt.controller.admin.energymonitor.areamonitor.vo.*;

/**
 * 分区能耗 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AreaMonitorMapper extends BaseMapperX<AreaMonitorDO> {

    default PageResult<AreaMonitorDO> selectPage(AreaMonitorPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AreaMonitorDO>()
                .likeIfPresent(AreaMonitorDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(AreaMonitorDO::getAreaSize, reqVO.getAreaSize())
                .eqIfPresent(AreaMonitorDO::getTotalEnergy, reqVO.getTotalEnergy())
                .eqIfPresent(AreaMonitorDO::getUnitEnergy, reqVO.getUnitEnergy())
                .eqIfPresent(AreaMonitorDO::getEnergyStatus, reqVO.getEnergyStatus())
                .eqIfPresent(AreaMonitorDO::getDeviceCount, reqVO.getDeviceCount())
                .eqIfPresent(AreaMonitorDO::getYoyChange, reqVO.getYoyChange())
                .eqIfPresent(AreaMonitorDO::getMomChange, reqVO.getMomChange())
                .eqIfPresent(AreaMonitorDO::getHandleUser, reqVO.getHandleUser())
                .eqIfPresent(AreaMonitorDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(AreaMonitorDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(AreaMonitorDO::getCreator, reqVO.getCreator())
                .betweenIfPresent(AreaMonitorDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(AreaMonitorDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(AreaMonitorDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(AreaMonitorDO::getId));
    }

}