package cn.iocoder.yudao.module.datacenter.dal.mysql.device.data.deviceshareattr;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceshareattr.vo.DeviceShareAttrPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.deviceshareattr.DeviceShareAttrDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备共享属性配置 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface DeviceShareAttrMapper extends BaseMapperX<DeviceShareAttrDO> {

    default PageResult<DeviceShareAttrDO> selectPage(DeviceShareAttrPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceShareAttrDO>()
                .eqIfPresent(DeviceShareAttrDO::getShareAttrCfgId, reqVO.getShareAttrCfgId())
                .eqIfPresent(DeviceShareAttrDO::getDeviceId, reqVO.getDeviceId())
                .likeIfPresent(DeviceShareAttrDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(DeviceShareAttrDO::getDeviceCode, reqVO.getDeviceCode())
                .likeIfPresent(DeviceShareAttrDO::getAttrName, reqVO.getAttrName())
                .eqIfPresent(DeviceShareAttrDO::getAttrCode, reqVO.getAttrCode())
                .eqIfPresent(DeviceShareAttrDO::getAttrValue, reqVO.getAttrValue())
                .eqIfPresent(DeviceShareAttrDO::getDataType, reqVO.getDataType())
                .eqIfPresent(DeviceShareAttrDO::getValueRange, reqVO.getValueRange())
                .eqIfPresent(DeviceShareAttrDO::getIsRequired, reqVO.getIsRequired())
                .eqIfPresent(DeviceShareAttrDO::getAttrDesc, reqVO.getAttrDesc())
                .eqIfPresent(DeviceShareAttrDO::getMntDeptCode, reqVO.getMntDeptCode())
                .likeIfPresent(DeviceShareAttrDO::getMntDeptName, reqVO.getMntDeptName())
                .betweenIfPresent(DeviceShareAttrDO::getCfgTime, reqVO.getCfgTime())
                .eqIfPresent(DeviceShareAttrDO::getCfgUser, reqVO.getCfgUser())
                .eqIfPresent(DeviceShareAttrDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .eqIfPresent(DeviceShareAttrDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DeviceShareAttrDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DeviceShareAttrDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DeviceShareAttrDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DeviceShareAttrDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceShareAttrDO::getId));
    }

}