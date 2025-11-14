package cn.iocoder.yudao.module.datacenter.dal.mysql.device.data.deviceserverattr;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceserverattr.vo.DeviceServerAttrPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.deviceserverattr.DeviceServerAttrDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备服务器属性配置 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface DeviceServerAttrMapper extends BaseMapperX<DeviceServerAttrDO> {

    default PageResult<DeviceServerAttrDO> selectPage(DeviceServerAttrPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceServerAttrDO>()
                .eqIfPresent(DeviceServerAttrDO::getServerAttrCfgId, reqVO.getServerAttrCfgId())
                .eqIfPresent(DeviceServerAttrDO::getDeviceId, reqVO.getDeviceId())
                .likeIfPresent(DeviceServerAttrDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(DeviceServerAttrDO::getDeviceCode, reqVO.getDeviceCode())
                .likeIfPresent(DeviceServerAttrDO::getAttrName, reqVO.getAttrName())
                .eqIfPresent(DeviceServerAttrDO::getAttrCode, reqVO.getAttrCode())
                .eqIfPresent(DeviceServerAttrDO::getAttrValue, reqVO.getAttrValue())
                .eqIfPresent(DeviceServerAttrDO::getDataType, reqVO.getDataType())
                .eqIfPresent(DeviceServerAttrDO::getIsRequired, reqVO.getIsRequired())
                .eqIfPresent(DeviceServerAttrDO::getAttrDesc, reqVO.getAttrDesc())
                .betweenIfPresent(DeviceServerAttrDO::getCfgTime, reqVO.getCfgTime())
                .eqIfPresent(DeviceServerAttrDO::getCfgUser, reqVO.getCfgUser())
                .eqIfPresent(DeviceServerAttrDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .eqIfPresent(DeviceServerAttrDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DeviceServerAttrDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DeviceServerAttrDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DeviceServerAttrDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DeviceServerAttrDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceServerAttrDO::getId));
    }

}