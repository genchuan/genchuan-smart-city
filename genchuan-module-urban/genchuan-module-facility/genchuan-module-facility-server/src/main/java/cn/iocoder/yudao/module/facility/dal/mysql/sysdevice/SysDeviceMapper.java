package cn.iocoder.yudao.module.facility.dal.mysql.sysdevice;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.facility.controller.admin.sysdevice.vo.SysDevicePageReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.sysdevice.SysDeviceDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface SysDeviceMapper extends BaseMapperX<SysDeviceDO> {

    default PageResult<SysDeviceDO> selectPage(SysDevicePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SysDeviceDO>()
                .eqIfPresent(SysDeviceDO::getDeviceCode, reqVO.getDeviceCode())
                .likeIfPresent(SysDeviceDO::getName, reqVO.getName())
                .eqIfPresent(SysDeviceDO::getOnlineStatus, reqVO.getOnlineStatus())
                .eqIfPresent(SysDeviceDO::getCategory, reqVO.getCategory())
                .eqIfPresent(SysDeviceDO::getDescription, reqVO.getDescription())
                .betweenIfPresent(SysDeviceDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(SysDeviceDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(SysDeviceDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(SysDeviceDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(SysDeviceDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(SysDeviceDO::getId));
    }

}
