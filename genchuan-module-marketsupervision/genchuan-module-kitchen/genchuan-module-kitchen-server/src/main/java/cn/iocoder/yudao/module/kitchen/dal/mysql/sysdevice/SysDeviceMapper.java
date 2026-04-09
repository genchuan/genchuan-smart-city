package cn.iocoder.yudao.module.kitchen.dal.mysql.sysdevice;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.kitchen.controller.admin.sysdevice.vo.SysDevicePageReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.sysdevice.SysDeviceDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface SysDeviceMapper extends BaseMapperX<SysDeviceDO> {

    default PageResult<SysDeviceDO> selectPage(SysDevicePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SysDeviceDO>()
                .eqIfPresent(SysDeviceDO::getDeviceCode, reqVO.getDeviceCode())
                .likeIfPresent(SysDeviceDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(SysDeviceDO::getDeviceType, reqVO.getDeviceType())
                .eqIfPresent(SysDeviceDO::getEntId, reqVO.getEntId())
                .eqIfPresent(SysDeviceDO::getAreaId, reqVO.getAreaId())
                .eqIfPresent(SysDeviceDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(SysDeviceDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(SysDeviceDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(SysDeviceDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(SysDeviceDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(SysDeviceDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(SysDeviceDO::getId));
    }

}
