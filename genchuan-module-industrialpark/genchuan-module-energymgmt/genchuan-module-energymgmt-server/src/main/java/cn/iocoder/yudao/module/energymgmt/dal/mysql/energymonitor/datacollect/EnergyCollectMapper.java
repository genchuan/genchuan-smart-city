package cn.iocoder.yudao.module.energymgmt.dal.mysql.energymonitor.datacollect;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.energymgmt.dal.dataobject.energymonitor.datacollect.EnergyCollectDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.energymgmt.controller.admin.energymonitor.datacollect.vo.*;

/**
 * 能耗采集 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EnergyCollectMapper extends BaseMapperX<EnergyCollectDO> {

    default PageResult<EnergyCollectDO> selectPage(EnergyCollectPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EnergyCollectDO>()
                .likeIfPresent(EnergyCollectDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(EnergyCollectDO::getDeviceType, reqVO.getDeviceType())
                .eqIfPresent(EnergyCollectDO::getEnergyType, reqVO.getEnergyType())
                .betweenIfPresent(EnergyCollectDO::getCollectTime, reqVO.getCollectTime())
                .eqIfPresent(EnergyCollectDO::getCollectStatus, reqVO.getCollectStatus())
                .orderByDesc(EnergyCollectDO::getId));
    }

}