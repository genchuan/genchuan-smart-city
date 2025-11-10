package cn.iocoder.yudao.module.datacenter.dal.mysql.device.data.deviceclientattr;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceclientattr.vo.DeviceClientAttrPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.deviceclientattr.DeviceClientAttrDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备客户端属性配置 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface DeviceClientAttrMapper extends BaseMapperX<DeviceClientAttrDO> {

    default PageResult<DeviceClientAttrDO> selectPage(DeviceClientAttrPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceClientAttrDO>()
                .eqIfPresent(DeviceClientAttrDO::getClientAttrCfgId, reqVO.getClientAttrCfgId())
                .eqIfPresent(DeviceClientAttrDO::getDeviceId, reqVO.getDeviceId())
                .likeIfPresent(DeviceClientAttrDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(DeviceClientAttrDO::getDeviceCode, reqVO.getDeviceCode())
                .likeIfPresent(DeviceClientAttrDO::getAttrName, reqVO.getAttrName())
                .eqIfPresent(DeviceClientAttrDO::getAttrCode, reqVO.getAttrCode())
                .eqIfPresent(DeviceClientAttrDO::getAttrValue, reqVO.getAttrValue())
                .eqIfPresent(DeviceClientAttrDO::getDataType, reqVO.getDataType())
                .eqIfPresent(DeviceClientAttrDO::getIsRequired, reqVO.getIsRequired())
                .eqIfPresent(DeviceClientAttrDO::getAttrDesc, reqVO.getAttrDesc())
                .betweenIfPresent(DeviceClientAttrDO::getCfgTime, reqVO.getCfgTime())
                .eqIfPresent(DeviceClientAttrDO::getCfgUser, reqVO.getCfgUser())
                .eqIfPresent(DeviceClientAttrDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .eqIfPresent(DeviceClientAttrDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DeviceClientAttrDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DeviceClientAttrDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DeviceClientAttrDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DeviceClientAttrDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceClientAttrDO::getId));
    }

}