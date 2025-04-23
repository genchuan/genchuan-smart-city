package cn.iocoder.yudao.module.smartcity.dal.mysql.monitoringdevice;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.monitoringdevice.MonitoringDeviceDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.monitoringdevice.vo.*;

/**
 * 监测设备 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface MonitoringDeviceMapper extends BaseMapperX<MonitoringDeviceDO> {

    default PageResult<MonitoringDeviceDO> selectPage(MonitoringDevicePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MonitoringDeviceDO>()
                .likeIfPresent(MonitoringDeviceDO::getDeviceNumber, reqVO.getDeviceNumber())
                .likeIfPresent(MonitoringDeviceDO::getDeviceName, reqVO.getDeviceName())
                .betweenIfPresent(MonitoringDeviceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MonitoringDeviceDO::getId));
    }

}